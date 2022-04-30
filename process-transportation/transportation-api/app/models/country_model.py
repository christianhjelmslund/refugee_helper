from typing import Optional, List
from models.mongo_model import PyObjectId, MongoModel
from pydantic import Field


class CountryModel(MongoModel):
    id: Optional[PyObjectId] = Field(default_factory=PyObjectId, alias="_id")
    name: str = Field(...)
    cities: List[PyObjectId] = Field(default_factory=PyObjectId, alias='city_ids')
    capacity: int = Field(...)
    visa_available: bool = Field(...)
    visa_requirements: str = Field(...)
    banned_countries: List[PyObjectId] = Field(default_factory=PyObjectId, alias="banned_countries")

class CountriesModel(MongoModel):
    countries: List[CountryModel] = Field(...)