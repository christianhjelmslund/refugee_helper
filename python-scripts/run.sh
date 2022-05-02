#!/bin/bash

set -m

python3 -m flask run --host=0.0.0.0 --port=4999 &

python3 crisis_detector.py

fg %1