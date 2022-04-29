mongoimport --db=refugee-db --collection=countries --jsonArray --file=docker-entrypoint-initdb.d/data/countries.json
mongoimport --db=refugee-db --collection=cities --jsonArray --file=docker-entrypoint-initdb.d/data/cities.json
mongoimport --db=refugee-db --collection=jobs --jsonArray --file=docker-entrypoint-initdb.d/data/jobs.json
mongoimport --db=refugee-db --collection=requirements --jsonArray --file=docker-entrypoint-initdb.d/data/requirements.json