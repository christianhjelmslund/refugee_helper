# syntax=docker/dockerfile:1

FROM python:3.8-slim-buster
WORKDIR /app

COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

COPY job_applicants.py job_applicants.py

CMD [ "python3", "./job_applicants.py"]