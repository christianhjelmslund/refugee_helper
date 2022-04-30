from sqlite3 import dbapi2
from bson.objectid import ObjectId
import motor.motor_asyncio

MONGO_DETAILS = "mongodb://localhost:27017"

client = motor.motor_asyncio.AsyncIOMotorClient(MONGO_DETAILS)

database = client['refugee-db']

cities_collection = database.get_collection("cities")
country_collection = database.get_collection("countries")

# helpers

def city_helper(city) -> dict:
    return {
        "id": str(city["_id"]),
        "name": city["name"],
        "capacity": city["capacity"]
    }


def country_helper(country) -> dict:
    print(country)
    return {
        "id": str(country["_id"]),
        "name": country["name"],
        "cities": str(country["city_ids"]),
        "capacity": country["capacity"],
        "visa_available": country["visa_available"],
        "visa_requirements": country["visa_requirements"],
        "banned_countries": country["banned_countries"]
    }

# retrieve country by id
async def retrieve_country(id: str) -> dict:
    country = await country_collection.find_one({"_id": ObjectId(id)})
    if country:
        print(country)
        return country

# retrieve country by name
async def retrieve_country_by_name(name: str) -> dict:
    country = await country_collection.find_one({"name": name})
    if country:
        print(country)
        return country

# retrieve cities by country name
async def retrieve_cities_by_country_name(name: str) -> dict:
    country = await country_collection.find_one({"name": name})
    cursor = cities_collection.find({"_id" : {"$in": country["city_ids"]}})
    cities = {'cities': []}
    for city in await cursor.to_list(length=100):
        cities["cities"].append(city)
    if cities:
        return cities

# retrieve all countries
async def retrieve_all_countries():
    cursor = country_collection.find({})
    countriesObj = {'countries': []}
    for country in await cursor.to_list(length=100):
        print(country)
        countriesObj["countries"].append(country)
    if countriesObj:
        return countriesObj

# retrieve city by id
async def retrieve_city(id: str) -> dict:
    city = await cities_collection.find_one({"_id": ObjectId(id)})
    if city:
        print(city)
        return city

