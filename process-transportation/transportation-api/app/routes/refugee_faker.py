from fastapi import APIRouter
from fake_refugee_generator.fake_refugee_creator import RefugeeCreator




router = APIRouter()

@router.get("/refugee")
async def generate_refugee():
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee()
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@router.get("/refugee/gender/{gender}")
async def generate_refugee(gender):
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee(gender)
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@router.get("/refugee/age/{age_category}")
async def generate_refugee(age_category):
    refugee_creator = RefugeeCreator()
    single_refugee = refugee_creator.generate_single_refugee("", age_category)
    print(single_refugee[0])
    return {"refugee": single_refugee[0]}

@router.get("/refugees/{amount}")
async def generate_refugees(amount):
    refugee_creator = RefugeeCreator()
    mulitple_refugees = refugee_creator.generate_mulitple_refugees(amount=int(amount))
    print(mulitple_refugees)
    return {"refugee": str(mulitple_refugees)}