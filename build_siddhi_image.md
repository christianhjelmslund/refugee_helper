## How to build Siddhi-Tooling Image with extensions

For the base, navigate to <DOCKERFILE_HOME>/base directory. 
`docker build -t siddhiio/siddhi-tooling-base:5.1.x .`

`docker run -it -p 9390:9390 -p 8006:8006 siddhiio/siddhi-tooling`

Go to http://localhost:9390/editor

## Add external dependencies with docker run command
There is a situation that you wanted to add any external dependencies.
In that case, you can directories locally as per your requirement and issue a similar command as shown below:

`docker run -it -p 9390:9390 \ 
  -v <absolute path>/configs:/artifacts \
  -v <absolute path>/workspace:/home/siddhi_user/siddhi-tooling/wso2/tooling/deployment/workspace \
  -v <absolute path>/jars:/home/siddhi_user/siddhi-tooling/jars \
  -v <absolute path>/bundles:/home/siddhi_user/siddhi-tooling/bundles \
  siddhiio/siddhi-tooling:5.1.0`
--- 