
import requests
import sys
import random
from datetime import datetime
import  time

NEWS_API_KEY = 'bc4e90e0e45142ee91e0c2ad557129f9'
SIDDHI_URL = 'http://localhost:8006'

news_headers = { 'Authorization': NEWS_API_KEY }
siddhi_headers = { 'Content-Type': 'application/json'}
top_headlines = requests.get('https://newsapi.org/v2/top-headlines?q=war&language=en', headers=news_headers).json()

# Remember to start Siddhi script first (./CrisisDetector.siddhi)
while True:
    date_now = datetime.today().strftime('%Y-%m-%d')
    countries = ["Ukraine", "Denmark", "Russia", "Belarus", "Sweden", "France", "Poland", "Lithuania", "Latvia", "Estonia", "Finland", "Norway", "United Kingdom", "Ireland", "Netherlands", "Belgium", "Luxembourg", "Switzerland", "Austria", "Czechia", "Slovakia", "Hungary", "Serbia", "Bulgaria", "Greece", "Luxembourg", "Slovenia", "Italy", "Spain", "Portugal", "Moldova", "Turkey", "Georgia", "Armenia", "Azerbaijan", "Croatia", "Iceland", "Bosnia and Herzegovina"]
    for country in countries:
        news = requests.get('https://newsapi.org/v2/everything?q=war+' + country + '&language=en&from=' + date_now, headers=news_headers).json()
        news_count = news["totalResults"] + random.randint(-8,8) # This creates some randomness to fake a crisis happening
        print(country, news_count)
        data = '{"event":{"news_count":' + str(news_count) + ', "country": "' + country + '"}}'
        requests.post(SIDDHI_URL+'/news', headers=siddhi_headers, data=data)

    sys.stdout.flush()
    time.sleep(3)
