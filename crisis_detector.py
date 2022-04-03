
import requests
import json
from newsapi import NewsApiClient

NEWS_API_KEY = '93ff7e0e52884b04824ac8f584c56991'
SIDDHI_URL = 'http://localhost:8006'

news_headers = { 'Authorization': NEWS_API_KEY }
siddhi_headers = { 'Content-Type': 'application/json'}

top_headlines = requests.get('https://newsapi.org/v2/top-headlines?q=war+kyiv&language=en', headers=news_headers).json()
if (top_headlines['articles']):
    for article in top_headlines['articles']:
        title = article['title']
        data = '{"event":{"title":'+title+'}}'

        requests.post(SIDDHI_URL+'/init_crisis', headers=siddhi_headers, data=data)
