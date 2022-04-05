
import requests
import json
from newsapi import NewsApiClient
import sys

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
    news = requests.get('https://newsapi.org/v2/everything?q=war+ukraine&language=en&from=2022-04-05', headers=news_headers).json()
    # print(top_headlines)
    print(news["totalResults"])
    if news["totalResults"] > 10:
        print(news["totalResults"])
#     if (top_headlines['articles']):
#         for article in top_headlines['articles']:
#             title = article['title']
#             data = '{"event":{"title":"title"}}'
#             print(title)
#             requests.post(SIDDHI_URL+'/init_crisis', headers=siddhi_headers, data=data)

    sys.stdout.flush()
    time.sleep(2)
