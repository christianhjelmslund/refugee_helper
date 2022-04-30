from fastapi import FastAPI
import routes.locations as locations
import routes.refugee_faker as refugee_faker

app = FastAPI()

app.include_router(refugee_faker.router)
app.include_router(locations.router)

@app.get("/")
async def root():
    return {"status": "Server Running"}



