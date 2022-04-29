from typing import Optional, List
from models.mongo_model import PyObjectId, MongoModel
from pydantic import Field


class CountryModel(MongoModel):
    id: Optional[PyObjectId] = Field(alias='_id')
    name: str = Field(...)
    cities: List[PyObjectId] = Field(alias='city_ids')
    capacity: int = Field(...)
    visa_available: bool = Field(...)
    visa_requirements: str = Field(...)
    banned_countries: List[PyObjectId] = Field(...)