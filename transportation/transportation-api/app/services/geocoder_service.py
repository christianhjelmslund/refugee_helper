from geopy.geocoders import Nominatim

async def reverse_geocoder(lat: float, lng: float):
    geolocator = Nominatim(user_agent="RefugeeGecoder")
    location = f"{lat}, {lng}"
    address = geolocator.reverse(location, language="en")
    return address

async def check_destination_country(destination_country, lat, lng):
    address = await reverse_geocoder(lat=lat, lng=lng)
    current_country = address.raw['address']['country']
    if(current_country == destination_country):
        return True, current_country
    else:
        return False, current_country