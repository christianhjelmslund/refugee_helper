from models.route_model import SimpleRouteModel, LocationEventModel, LocationEventBody
import time
from decouple import config
import googlemaps
from googlemaps.convert import decode_polyline, encode_polyline
import json
from datetime import datetime
import math
import numpy
from collections import OrderedDict
import sys
import httpx
from fastapi.encoders import jsonable_encoder

GMAPS_API_KEY = config('API_KEY')
SIDDIH_URL = 'http://0.0.0.0:8006/'

def _calculate_distance(origin, destination):
    """
    Calculate the Haversine distance. 
    This isn't accurate for large distances, but for our purposes it is good enough
    """
    lat1, lon1 = origin['lat'], origin['lng']
    lat2, lon2 = destination['lat'], destination['lng']
    radius = 6371000  # metres

    dlat = math.radians(lat2 - lat1)
    dlon = math.radians(lon2 - lon1)
    a = (math.sin(dlat / 2) * math.sin(dlat / 2) +
         math.cos(math.radians(lat1)) * math.cos(math.radians(lat2)) *
         math.sin(dlon / 2) * math.sin(dlon / 2))
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
    d = radius * c

    return d

def _round_up_time(time, period):
    """
    Rounds up time to the higher multiple of period
    For example, if period=5, then time=16s will be rounded to 20s
    if time=15, then time will remain 15
    """
    # If time is an exact multiple of period, don't round up
    if time % period == 0:
        return time

    time = round(time)
    return time + period - (time % period)

def _fill_missing_times(times, lats, lngs, period):
    start_time = times[0]
    end_time = times[-1]
    
    new_times = range(start_time, end_time + 1, period)
    new_lats = numpy.interp(new_times, times, lats).tolist()
    new_lngs = numpy.interp(new_times, times, lngs).tolist()

    return new_times, new_lats, new_lngs

async def _get_directions(destination, origin, mode, departure):
    """
    Generates a series of points along the route, such that it would take approx `period` seconds to travel between consecutive points
    
    This function is primarily meant to simulate a car along a route. The output of this function is equivalent to the geo coordinates 
    of the car every 5 seconds (assuming period = 5)
    
    _from = human friendly from address that google maps can understand
    _to = human friendly to address that google maps can understand
    departure_time - primarily used to identify traffic model, defaults to current time
    period = how frequently should co-ordinates be tracked? Defaults to 5 seconds
    The output is an OrderedDict. Key is the time in seconds since trip start, value is a tuple representing (lat, long) in float

    """
    if departure == "now":
        departure = datetime.now()
    else:
        departure = datetime.strptime(departure, "%d/%m/%Y %H:%M:%S")

    gmaps = googlemaps.Client(key=GMAPS_API_KEY)
    directions = gmaps.directions(origin=origin, destination=destination, mode=mode, departure_time=departure, units="metric")

    return directions


def get_points_along_path(route, period=10):
    print("Period: ")
    print(period)
    steps = route['steps']
    all_lats = []
    all_lngs = []
    all_times = []

    step_start_duration = 0
    step_end_duration = 0

    for step in steps:
        step_end_duration += step['duration']['value']
        points = decode_polyline(step['polyline']['points'])
        distances = []
        lats = []
        lngs = []
        start = None
        for point in points:
            if not start:
                start = point
                distance = 0
            else:
                distance = _calculate_distance(start, point)
            distances.append(distance)
            lats.append(point['lat'])
            lngs.append(point['lng'])
            
        missing_times = numpy.interp(distances[1:-1], [distances[0], distances[-1]], [step_start_duration, step_end_duration]).tolist()
        times = [step_start_duration] + missing_times + [step_end_duration]
        times = [_round_up_time(t, period) for t in times]
        
        times, lats, lngs = _fill_missing_times(times, lats, lngs, period)
        
        all_lats += lats
        all_lngs += lngs
        all_times += times

        step_start_duration = step_end_duration

    points = OrderedDict()
    for p in zip(all_times, all_lats,all_lngs):
        points[p[0]] = (round(p[1], 5), round(p[2],5))
        
    return points

def generate_polyline(points):
    return encode_polyline(points.values())


async def movement_simulation(route, max_time, period):
    """
    Needs the legs from Google Maps and the Maximum Run Time in Minutes plus the period,
    after which amount of seconds siddhi is called.
    """
    destination_country = route['end_address'].split(',')[1].strip()
    print('Country: ', destination_country)
    points = get_points_along_path(route=route, period=period)
    skip_index = math.floor(len(points) / ((max_time * 60) / period))
    index = 0
    while index < len(points):
        await publish_event(points, index, destination_country)
        index += skip_index
        time.sleep(period)
    

    #await publish_arrival_event(points.items()[len(points)-1], len(points)-1)


async def publish_event(points, index, destination_country):
    time_var, geo = list(points.items())[index]
    event_obj = {
        'time': time_var,
        'lat': geo[0],
        'lng': geo[1],
        'destination': destination_country
    }
    event_obj = LocationEventBody.parse_obj(event_obj)
    json_event_obj = jsonable_encoder(event_obj)
    print("JSON Object: ", json_event_obj)

    r = httpx.post(SIDDIH_URL + 'location_event', json=json_event_obj)
    print(r)


async def publish_arrival_event(points, index):
    time_var, geo = list(points.items())[index]
    event_obj = {
        'time': time_var,
        'lat': geo[0],
        'lng': geo[1]
    }
    event_obj = LocationEventModel.parse_obj(event_obj)
    json_event_obj = jsonable_encoder(event_obj)

    r = httpx.post(SIDDIH_URL + 'arrival_event', data=json_event_obj)
    print(r)


async def get_route(destination, origin, mode, departure):
    route = await _get_directions(destination=destination, origin=origin, mode=mode, departure=departure)
    JSONRoute = jsonable_encoder(route)
    return JSONRoute


async def get_route_simple(destination, origin, mode, departure):
    route = await _get_directions(destination=destination, origin=origin, mode=mode, departure=departure)
    route_object = route[0]['legs'][0]
    print(route_object)
    return SimpleRouteModel.parse_obj(route_object)


async def start_location_tracking(route):
    ## Maximum Runtime (Min)
    MAX_RUNTIME = 2
    ## Period in which a new Event is generated
    PERIOD = 10

    route_object = route[0]['legs'][0]
    print(route_object)
    await movement_simulation(route_object, max_time=MAX_RUNTIME, period=PERIOD)


