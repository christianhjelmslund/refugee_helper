import json
from re import A
from fastapi import FastAPI

from services.fake_refugee_creator import RefugeeCreator

app = FastAPI()

@app.get("/")
async def root():
    return {"status": "Server Running"}

@app.get("/refugee")
async def generate_refugee():
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee()
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@app.get("/refugee/gender/{gender}")
async def generate_refugee(gender):
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee(gender)
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@app.get("/refugee/age/{age_category}")
async def generate_refugee(age_category):
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee("", age_category)
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@app.get("/refugees/{amount}")
async def generate_refugees(amount):
    refugee_creator = RefugeeCreator()
    mulitple_refugees = refugee_creator.generate_mulitple_refugees(amount=int(amount))
    print(mulitple_refugees)
    return {"refugee": str(mulitple_refugees)}