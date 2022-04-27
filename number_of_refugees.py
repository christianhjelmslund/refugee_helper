from flask import Flask
import random 

app = Flask(__name__)

refugees_for_countries = {
    "germany": 100000, # 100.000
    "spain": 55000, #55.000
    "denmark": 21000, #21.000
    "sweden": 28000, #28.000
    "romania": 400000, #400.000
    "poland" : 2000000, #2.000.000
    "hungary":120000, #120.000
    "france": 81000, # 81.000
    "united_kingdom": 113000 #113.000
}

def randomize_number_of_refugees(number_of_refugees):
    return random.randint(number_of_refugees-(number_of_refugees*0.01),number_of_refugees+(number_of_refugees*0.01))

@app.route("/<country>")
def number_of_refugees(country):
    number_of_refugees = -1
    if (refugees_for_countries.get(country)):
        number_of_refugees = refugees_for_countries.get(country)
    return f'{randomize_number_of_refugees(number_of_refugees)}'