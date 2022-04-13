import time
from mimesis.enums import Gender
from mimesis.locales import Locale
from mimesis.schema import Field, Schema
from services.custom_providers import AgeCategoryProvider, ChildrenAmountProvider, DisabledProvider, ForeignRelativesProvider, RealLocationProvider, TransportationProvider

class RefugeeSchemaGenerator:
    main_localization = 'uk'
    second_localization = 'en'
    UK_ = ''

    def __init__(self, country: str, nationality: str, main_localization: str, second_localization: str):
        self.main_localization = main_localization
        self.second_localization = second_localization
        self.country = country
        self.nationality = nationality
        self.custom_providers = (
        AgeCategoryProvider,
        ChildrenAmountProvider,
        DisabledProvider,
        ForeignRelativesProvider,
        RealLocationProvider,
        TransportationProvider
    )
        self.UK_ = Field(locale=self.main_localization)
        self.EN_ = Field(locale=self.second_localization, providers=self.custom_providers)
    
    def get_general_refugee_schema(self):
        schema = Schema(schema=self.refugee_general)
        return schema
    
    def get_gender_sepecific_refugee_schema(self, gender):
        if(gender == "Female"):
            schema = Schema(schema=self.refugee_female)
        elif(gender == "Male"):
            schema = Schema(schema=self.refugee_male)
        
        return schema
    
    def get_age_specific_refugee_schema(self, age_category):
        if(age_category == "Adult"):
            schema = Schema(schema=self.refugee_adult)
        elif(age_category == "Child"):
            schema = Schema(schema=self.refugee_child)
        elif(age_category == "Senior"):
            schema = Schema(schema=self.refugee_senior)
        
        return schema


    def schema_blueprint(self, age: int, gender: str, foreign_relatives: bool):
        city = self.UK_("address.city")

        return {
        "pk": self.UK_("increment"),
        "uid": self.UK_("uuid"),
        "timestamp": str(time.time()),
        "name": self.UK_("full_name"),
        "gender": gender,
        "age": age,
        "age_category": self.EN_("age_category", age=age),
        "transportation_available": self.EN_("transportation"),
        "children_amount": self.EN_("children_amount", age=age),
        "disabled": self.EN_("disabled", age=age),
        "foreign_relatives": str(foreign_relatives),
        "relatives_address": self.EN_("foreign_address", relatives=foreign_relatives, base_country="uk", banned_countries=["ru", "kk"]),
        "address": {
            "street_name": self.UK_("address.street_name"),
            "street_number": self.UK_("street_number"),
            "country": self.country,
            "city": city,
            "postal_code": self.UK_("address.postal_code"),
            "state": self.UK_("address.state"),
            "province": self.UK_("address.province"),

        },
        "position": self.EN_("real_position", city=city), 
        "email": self.UK_("email"),
        "education": self.EN_("academic_degree"),
        "nationality": self.nationality,
        "first_language": self.nationality,
        "second_language": self.EN_("language"),
        "last_job": self.EN_("occupation"),
        "work_experience": self.UK_("work_experience", working_start_age=18),
        "political_view": self.EN_("political_views"),
        "telephone_number": self.UK_("telephone"),
        }


    def refugee_general(self):
        age = self.UK_("age", maximum=100)
        gender = self.EN_("gender")
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)
    
    def refugee_female(self):
        age = self.UK_("age", maximum=100)
        gender = "Female"
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)
    
    def refugee_male(self):
        age = self.UK_("age", maximum=100)
        gender = "Male"
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)

    def refugee_child(self):
        age = self.UK_("age", maximum=17)
        gender = self.EN_("gender")
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)
    
    def refugee_adult(self):
        age = self.UK_("age", minimum=18, maximum=59)
        gender = self.EN_("gender")
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)
    
    def refugee_senior(self):
        age = self.UK_("age", minimum=59, maximum=100)
        gender = self.EN_("gender")
        foreign_relatives = self.EN_("foreign_relatives")

        return self.schema_blueprint(age=age, gender=gender, foreign_relatives=foreign_relatives)
    



   
