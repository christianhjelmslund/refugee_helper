
import requests
import json
from newsapi import NewsApiClient
import sys
import random


NEWS_API_KEY = '93ff7e0e52884b04824ac8f584c56991'
SIDDHI_URL = 'http://localhost:8006'

news_headers = { 'Authorization': NEWS_API_KEY }
siddhi_headers = { 'Content-Type': 'application/json'}
print("yo")
top_headlines = requests.get('https://newsapi.org/v2/top-headlines?q=war&language=en', headers=news_headers).json()
# print(top_headlines)
# if (top_headlines['articles']):
#     for article in top_headlines['articles']:
#         title = article['title']
#         data = '{"event":{"title":"title"}}'
#         print(title)
#         requests.post(SIDDHI_URL+'/init_crisis', headers=siddhi_headers, data=data)


import  time
#
while True:
    countries = ["Ukraine", "Denmark"]
    for country in countries:
        news = requests.get('https://newsapi.org/v2/everything?q=war+' + country + '&language=en&from=2022-04-05', headers=news_headers).json()
        # print(top_headlines)
        news_count = news["totalResults"] + random.randint(-8,8)
        print(news_count)
        data = '{"event":{"news_count":' + str(news_count) + ', "country": "' + country + '"}}'
        requests.post(SIDDHI_URL+'/news', headers=siddhi_headers, data=data)

    sys.stdout.flush()
    time.sleep(2)
