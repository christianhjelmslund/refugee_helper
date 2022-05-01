#!/usr/bin/env python
# coding: utf-8

# In[100]:


import requests
import json
import sys
import random
import time
import names
import datetime

SIDDHI_URL = 'http://localhost:8006'
siddhi_headers = { 'Content-Type': 'application/json'}

print("Jo Jo")

while True:
    
    # Generate random job id
    job_id = random.randint(57489,57495)
    
    # Generate random first and last name
    female_firstname = names.get_first_name(gender='female')
    male_firstname = names.get_first_name(gender='male')
    first_name = random.choices([female_firstname, male_firstname])[0]
    last_name = names.get_last_name()
    
    # Generate random birthday
    start_date = datetime.date(1976, 1, 1)
    end_date = datetime.date(1999, 1, 1)

    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = random.randrange(days_between_dates)
    birthday = start_date + datetime.timedelta(days=random_number_of_days)
    
    data = '{"event":{"job_id":' + str(job_id) + ', "first_name": "' + str(first_name) + '"' + ', "last_name": "' + str(last_name) + '"' + ', "birthday": "' + str(birthday) + '"}}'
    
    print(data)
    requests.post(SIDDHI_URL+'/jobs', headers=siddhi_headers, data=data)
    
    sys.stdout.flush()
    time.sleep(1)


# In[ ]:




