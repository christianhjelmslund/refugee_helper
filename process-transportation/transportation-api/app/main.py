from fastapi import FastAPI
import routes.locations as locations
import routes.refugee_faker as refugee_faker

from fastapi import BackgroundTasks
from fastapi.exceptions import RequestValidationError
import logging

from models.route_model import SimpleRouteModel, LocationEventModel, LocationEventSuccessResponseModel, LocationEventFailureResponseModel, RouteRequestModel, AddressRequestModel, AddressRequestResponseModel

from services.gmaps_service import (
    _get_directions,
    start_location_tracking
)



app = FastAPI()

app.include_router(refugee_faker.router)
app.include_router(locations.router)

@app.get("/")
async def root():
    return {"status": "Server Running"}

@app.post("/tracking/start/", response_description="Start Tracking")
@app.exception_handler(RequestValidationError)
async def caluclate_directions(route_request: RouteRequestModel, exc: RequestValidationError, background_tasks: BackgroundTasks) -> RouteRequestModel:
     exc_str = f'{exc}'.replace('\n', ' ').replace('   ', ' ')
     logging.error(exc_str)
     route = await _get_directions(destination=route_request.destination, origin=route_request.origin, mode=route_request.mode, departure=route_request.departure)
     background_tasks.add_task(start_location_tracking, route=route)
     return route

