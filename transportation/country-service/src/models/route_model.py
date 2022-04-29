from pydantic import Field
from typing import Optional
from pydantic import BaseModel

class TimeModel(BaseModel):
    text: str
    time_zone: str
    value: int

class TextValueModel(BaseModel):
    text: str
    value: int

class LocationModel(BaseModel):
    lat: float
    lng: float

class RouteRequestModel(BaseModel):
    destination: str
    origin: str
    mode: str
    departure: str

class SimpleRouteModel(BaseModel):
    arrival_time: Optional[TimeModel] = Field(alias='arrival_time')
    departure_time: Optional[TimeModel] = Field(alias='departure_time')
    distance: TextValueModel = Field(alias='distance')
    duration: TextValueModel = Field(alias='duration')
    end_address: str = Field(alias='end_address')
    end_location: LocationModel = Field(alias='end_location')
    start_address: str = Field(alias='start_address')
    start_location: LocationModel = Field(alias='start_location')

class LocationEventBody(BaseModel):
    time: int
    lat: float
    lng: float
    destination: Optional[str] = Field(...)

class LocationEventModel(BaseModel):
    event: LocationEventBody

class LocationEventSuccessResponseModel(BaseModel):
    time: int
    lat: float
    lng: float
    country: str


class LocationEventFailureResponseModel(BaseModel):
    type: bool
    country: str
