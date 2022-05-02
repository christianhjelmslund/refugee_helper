# syntax=docker/dockerfile:1

FROM python:3.8-slim-buster
WORKDIR /app

COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

COPY . .

COPY run.sh run.sh

ENV FLASK_APP=number_of_refugees.py

CMD ./run.sh