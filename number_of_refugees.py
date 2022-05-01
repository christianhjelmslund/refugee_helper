from flask import Flask
import random 

app = Flask(__name__)

refugees_for_countries = {
    "Germany": 100000, # 100.000
    "Spain": 55000, #55.000
    "Denmark": 21000, #21.000
    "Sweden": 28000, #28.000
    "Poland" : 2000000, #2.000.000
    "Hungary":120000, #120.000
    "France": 81000, # 81.000
    "United Kingdom": 113000, #113.000
    "Russia": 100000, 
    "Belarus": 5000,
    "Lithuania": 50000,
    "Latvia": 50000,
    "Estonia": 55000,
    "Finland": 15000,
    "Norway": 18000,
    "Ireland": 45000,
    "Netherlands": 60000,
    "Belgium": 65000,
    "Luxembourg": 2000,
    "Switzerland": 6000,
    "Austria": 78000,
    "Czechia": 150000,
    "Slovakia": 110000,
    "Hungary": 130000,
    "Serbia": 7000,
    "Bulgaria": 78000,
    "Greece": 13000,
    "Slovenia": 32000,
    "Italy": 41000,
    "Portugal": 17000,
    "Moldova": 2000,
    "Turkey": 1000000,
    "Georgia": 35000,
    "Armenia": 3000,
    "Azerbaijan": 1000,
    "Croata": 6000,
    "Iceland": 500,
    "Bosnia and Herzegovina": 1000
}

def randomize_number_of_refugees(number_of_refugees):
    return random.randint(number_of_refugees-(number_of_refugees*0.01),number_of_refugees+(number_of_refugees*0.01))

@app.route("/<country>")
def number_of_refugees(country):
    number_of_refugees = -1
    if (refugees_for_countries.get(country)):
        number_of_refugees = refugees_for_countries.get(country)
    return f'{randomize_number_of_refugees(number_of_refugees)}'