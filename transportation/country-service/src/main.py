import json
from fastapi import FastAPI
from models.response_model import ResponseModel, ErrorResponseModel
from models.country_model import CountryModel
from models.city_model import CityModel, CitiesModel
from models.route_model import SimpleRouteModel
from services.mongo_connector import (
    retrieve_city,
    retrieve_country,
    retrieve_country_by_name,
    retrieve_cities_by_country_name
)
from services.gmaps_service import (
    get_route,
    get_route_simple
)

app = FastAPI()

@app.get("/")
async def root():
    return {"status": "Server Running"}

@app.get("/countries")
async def get_countries():
   return None

@app.get("/country/name/{country_name}", response_description="Get country by Name", response_model=CountryModel)
async def get_country_by_name(country_name):
    country = await retrieve_country_by_name(country_name)
    return country

@app.get("/country/name/{country_name}/cities", response_description="Get cities by Country Name", response_model=CitiesModel)
async def get_country_by_name(country_name):
    country = await retrieve_cities_by_country_name(country_name)
    return country

@app.get("/country/id/{country_id}", response_description="Get country by Id", response_model=CountryModel)
async def get_country_by_id(country_id):
    country = await retrieve_country(country_id)
    return country


@app.get("/city/id/{city_id}", response_description="Get city by Id", response_model=CityModel)
async def get_city_by_name(city_id):
    city = await retrieve_city(city_id)
    return city

@app.get("/directions/")
async def caluclate_directions(destination: str = "Berlin", origin: str = "Kiev", mode: str = "driving", departure: str = "now"):
   route = await get_route(destination=destination, origin=origin, mode=mode, departure=departure)
   return route

@app.get("/directions/simple/", response_description="Get Directions Simple", response_model=SimpleRouteModel)
async def caluclate_directions(destination: str = "Berlin", origin: str = "Kiev", mode: str = "driving", departure: str = "now"):
   route = await get_route_simple(destination=destination, origin=origin, mode=mode, departure=departure)
   return route