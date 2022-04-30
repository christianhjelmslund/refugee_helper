from dataclasses import field
from typing import Optional, List
from models.mongo_model import PyObjectId, MongoModel
from pydantic import Field


class CityModel(MongoModel):
    id: Optional[PyObjectId] = Field(alias='_id')
    name: str = Field(...)
    capacity: int = Field(...)

class CitiesModel(MongoModel):
    cities: List[CityModel] = Field(...)

