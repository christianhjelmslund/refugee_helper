## How to run Siddhi with docker

`docker run -it -p 9390:9390 -p 8006:8006 siddhiio/siddhi-tooling`

Go to http://localhost:9390/editor

Use this as the sample app 

```
@App:name("SiddhiApp")
@App:description("Description of the plan")

@sink(type='log', prefix='LOGGER')
@source(type = 'http', receiver.url = "http://0.0.0.0:8006/init_crisis", @map(type = 'json'))
define stream InitCrisisStream (title string);
```

Test if the outside integration works using curl

`curl -X POST http://localhost:8006/init_crisis -H "Content-Type:application/json" -d '{"event":{"title":"123"}}'`

--- 
## How to use camunda bpm run
Download from here https://downloads.camunda.cloud/release/camunda-bpm/run/

Hava java8 up to java15 installed.

`./start.sh`

If connection issues, move the two files (shoutout Pushkar) into `configuration/userlib`

```
camunda-engine-plugin-connect-7.16.0.jar
camunda-connect-connectors-all-1.5.2.jar
```
(find them in Teams chat if needed)

Start the application again and you should be able to access the services and add the connection

Access the Camunda webapps (Cockpit, Tasklist, Admin) via http://localhost:8080/camunda/app/.

Access the REST API via http://localhost:8080/engine-rest (e.g. http://localhost:8080/engine-rest/engine).

Access Swagger UI via http://localhost:8080/swaggerui/ to try out the REST API.

--- 
The rest should be doable by watching the video https://www.youtube.com/watch?v=Mq--oYzJmrk