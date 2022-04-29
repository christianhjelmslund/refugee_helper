import json
import random
from geopy.geocoders import Nominatim
from mimesis.providers.base import BaseProvider
from mimesis import Address
import numpy as np

class AgeCategoryProvider(BaseProvider):
    class Meta:
        name = "age_category_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def age_category(self, age=int):
        if(age >= 18 and age < 60):
            age_catgory = "adult"
        elif(age < 18):
            age_catgory = "child"
        elif(age >= 60):
            age_catgory = "senior"
        return age_catgory

class ChildrenAmountProvider(BaseProvider):
    class Meta:
        name = "children_amount_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def children_amount(self, age=int):
       if(age >= 18 and age < 60):
            children = np.random.binomial(8, 0.15, 1)
       elif(age < 18):
            children = 0
       elif(age >= 60):
            children = np.random.binomial(8, 0.3, 1)
       return str(children[0])

class DisabledProvider(BaseProvider):
    class Meta:
        name = "disabled_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def disabled(self, age=int):
       if(age >= 25 and age < 55):
            disabled = np.random.choice([False, True], p=[1-0.018, 0.018])
       elif(age < 25):
            disabled = np.random.choice([False, True], p=[1-0.009, 0.009])
       elif(age >= 55):
            disabled = np.random.choice([False, True], p=[1-0.03, 0.03])
       return str(disabled)

class RealLocationProvider(BaseProvider):
    class Meta:
        name = "real_location_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def real_position(self, city=str):
       geolocator = Nominatim(user_agent="Daniel")
       location = geolocator.geocode(city)
       position_object = {"latitude": str(location.latitude),"longitude": str(location.longitude)}
       return position_object #json.dumps(position_object, separators=(',', ':'))


class TransportationProvider(BaseProvider):
    class Meta:
        name = "transportation_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def transportation(self):
        car_available = np.random.choice([False, True], p=[1-0.6, 0.4])
        return car_available

class ForeignRelativesProvider(BaseProvider):
    class Meta:
        name = "foreign_relatives_provider"
    
    def __init__(self, seed) -> None:
        super().__init__(seed=seed)

    def foreign_relatives(self):
       relatives_available = np.random.choice([False, True], p=[1-0.5, 0.5])
       return str(relatives_available)

    def foreign_address(self, relatives=bool, base_country=str, banned_countries=[str]):
        country_list = ["cs", "da", "de", "de-at", "de-ch", "en-gb", "es", "et", "fi", "fr", "hu", "it", "kk", "nl", "no", "pl", "pt", "ru", "sk", "sv", "tr", "uk"]
        if(relatives):
            countries_filtered = [i for i in country_list if base_country not in i and i not in banned_countries]
            random_index = random.randint(0, len(countries_filtered))
            random_localization = countries_filtered[random_index]
            address_obj = Address(locale=random_localization)
            full_address = address_obj.address()
            city = address_obj.city()
            country = random_localization
            postal_code = address_obj.postal_code()
            state = address_obj.state()
            street_name = address_obj.street_name()
            street_number = address_obj.street_number()
            latitude = address_obj.latitude()
            longitude = address_obj.longitude()
            address = {
                "full_address": full_address,
                "country": country,
                "city": city,
                "postal_code": postal_code,
                "state": state,
                "street_name": street_name,
                "steet_number": street_number,
                "position": {
                "lat": latitude,
                "long": longitude,}
            }
        else:
            address = {}

        return address #json.dumps(address, separators=(',', ':'))
