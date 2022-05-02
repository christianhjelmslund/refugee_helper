# Crisis Response: Ukraine Refugee Support

We have developed an event-driven process landscape to help refugees flee crisis areas. We used different data sources (News API, Google Maps API) and connected them to Camunda. 
Furthermore, two self-developed REST APIs connected to a Mongodb and a Python script are used in this project.

The following sections describe how to get the project running.

## Prerequisites
### Clone Git Repository
Prerequisite: Have git installed.

`git clone https://github.com/christianhjelmslund/refugee_helper.git`

### Docker installed

Docker running locally. Follow the instructions to [download and install Docker](https://docs.docker.com/desktop/).

### Java installed

Have minimum Java JDK 9 installed on your computer.

### Add Environment Variables

Add inside the process-transportation/transporation-api Folder a .env file with the following content:
`API_KEY={GOOGLE_MAPS_API}`
[Here](https://developers.google.com/maps/documentation/javascript/get-api-key) you would need to get your own GOOGLE MAPS API key this is only needed for the transportation process.

## Run the project
To run the whole backend, you need to start docker and go into the root folder of the project and run in your terminal:
`docker-compose up`
This will start all the backend services and siddhi. To be able to access the database in the background your IP needs to be added to the mongodb database cluster (DTU should work).
If it's not working please contact the repository owner.

### Setup Camunda Project
To start the Camunda Spring application go to folder refugee-helper and open the pom.xml in your preferred Editor and run the application.
Now you only have to deploy the processes to Camunda from the resource folder of refugee-helper app (/src/main/resources) and open this [Link](http://localhost:8080/).
Go to Camunda Tasklist and start the *main_process*.

