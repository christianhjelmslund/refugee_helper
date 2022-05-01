from fastapi import BackgroundTasks, APIRouter
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder

from models.country_model import CountryModel, CountriesModel
from models.city_model import CityModel, CitiesModel
from models.route_model import SimpleRouteModel, LocationEventModel, LocationEventSuccessResponseModel, LocationEventFailureResponseModel, RouteRequestModel, AddressRequestModel, AddressRequestResponseModel
from services.mongo_connector import (
    retrieve_city,
    retrieve_country,
    retrieve_country_by_name,
    retrieve_cities_by_country_name,
    retrieve_all_countries
)
from services.gmaps_service import (
    get_route,
    get_route_simple,
    _get_directions,
    start_location_tracking
)
from services.geocoder_service import (
    check_destination_country,
    check_address
)


router = APIRouter()

'''
THIS IS THE COUNTRY SECTION
'''


@router.get("/countries", response_model=CountriesModel)
async def get_countries():
    countries = await retrieve_all_countries()
    return countries


@router.get("/country/name/{country_name}", response_description="Get country by Name", response_model=CountryModel)
async def get_country_by_name(country_name):
    country = await retrieve_country_by_name(country_name)
    print(country)
    return country


@router.get("/country/name/{country_name}/cities", response_description="Get cities by Country Name", response_model=CitiesModel)
async def get_country_by_name(country_name):
    country = await retrieve_cities_by_country_name(country_name)
    return country


@router.get("/country/id/{country_id}", response_description="Get country by Id", response_model=CountryModel)
async def get_country_by_id(country_id):
    country = await retrieve_country(country_id)
    return country


@router.get("/city/id/{city_id}", response_description="Get city by Id", response_model=CityModel)
async def get_city_by_name(city_id):
    city = await retrieve_city(city_id)
    return city


@router.get("/directions/")
async def caluclate_directions(destination: str = "Berlin", origin: str = "Kiev", mode: str = "driving", departure: str = "now"):
    route = await get_route(destination=destination, origin=origin, mode=mode, departure=departure)
    return route


@router.get("/directions/simple/", response_description="Get Directions Simple", response_model=SimpleRouteModel)
async def caluclate_directions(destination: str = "Berlin", origin: str = "Kiev", mode: str = "driving", departure: str = "now"):
    route = await get_route_simple(destination=destination, origin=origin, mode=mode, departure=departure)
    return route


@router.post("/tracking/start/", response_description="Start Tracking")
async def caluclate_directions(route_request: RouteRequestModel, background_tasks: BackgroundTasks) -> RouteRequestModel:
    route = await _get_directions(destination=route_request.destination, origin=route_request.origin, mode=route_request.mode, departure=route_request.departure)
    background_tasks.add_task(start_location_tracking, route=route)
    return route


@router.post("/address/", response_description="Get Current Address")
async def get_current_location(address_request: AddressRequestModel) -> AddressRequestModel:
    print(address_request)
    address = await check_address(lat=address_request.lat, lng=address_request.lng)
    response = AddressRequestResponseModel(address=address)
    return response


@router.post("/check_location/", response_description="Get Current Country")
async def get_current_country(location_event: LocationEventModel) -> LocationEventModel:
    print(location_event)
    arrived, current_country = await check_destination_country(destination_country=location_event.event.destination, lat=location_event.event.lat, lng=location_event.event.lng)
    if arrived:
        success = LocationEventSuccessResponseModel(
            time=location_event.event.time, lat=location_event.event.lat, lng=location_event.event.lng, country=current_country)
        print("Successful Arrived in Country")
        return success
    else:
        failure = LocationEventFailureResponseModel(
            type=False, country=current_country)
        print("Not Arrived in Country")
        failureJSON = jsonable_encoder(failure)
        return JSONResponse(status_code=202, content=failureJSON)
