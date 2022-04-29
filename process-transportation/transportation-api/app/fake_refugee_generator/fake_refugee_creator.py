from mimesis import Person
from multipledispatch import dispatch
from fake_refugee_generator.refugee_schema import RefugeeSchemaGenerator


class RefugeeCreator:
    locale: str

    @dispatch()
    def generate_single_refugee(self):
        schema_generator = RefugeeSchemaGenerator(main_localization="uk", second_localization="en", country="Ukraine", nationality="Ukrainian")
        schema = schema_generator.get_general_refugee_schema()
        return schema.create(iterations=1)
    
    @dispatch(str)
    def generate_single_refugee(self, gender):
        schema_generator = RefugeeSchemaGenerator(main_localization="uk", second_localization="en", country="Ukraine", nationality="Ukrainian")
        schema = schema_generator.get_gender_sepecific_refugee_schema(gender)
        return schema.create(iterations=1)
    
    @dispatch(str, str)
    def generate_single_refugee(self, gender, age_category):
        schema_generator = RefugeeSchemaGenerator(main_localization="uk", second_localization="en", country="Ukraine", nationality="Ukrainian")
        schema = schema_generator.get_age_specific_refugee_schema(age_category)
        return schema.create(iterations=1)


    def generate_mulitple_refugees(self, amount: int):
        schema_generator = RefugeeSchemaGenerator(main_localization="uk", second_localization="en", country="Ukraine", nationality="Ukrainian")
        schema = schema_generator.get_general_refugee_schema()
        return schema.create(iterations=amount)


