
## SimilarProductTest Project ##

## Annotations ##

-This Spring boot application includes the use of cache, logger, y swagger. Swagger doc is available in http://localhost:5000/swagger-ui.html#/

-The code is located in testdemo > src > main > java > com.alextestdemo.testdemo

-Tests that have been done with JUnit5 are in testdemo > src > test > java > com.alextestdemo.testdemo
(Manual tests have been done using various browsers as Google Chrome, Mozilla Firefox...)

-Request examples: 
Product with some related products that are ok:
http://localhost:5000/product/1/similar
Product with some related products and one of them doesn't return the product info:
http://localhost:5000/product/4/similar
Product that can't be found:
http://localhost:5000/product/6/similar



## Steps followed to dockerize the app (the containers refuse the connection from the one that I've created): ##


1.- Compile the project with the command "mvn clean install -DskipTests" in order to generate the .jar in the target folder.

2.- Make sure that the other containers (the ones in the backendDevTest projects) are up.

3.- In the project folder, "testdemo", where the docker files are configured, you can generate the docker image using the command "docker build -t docker-related-products:latest ." 

4.- After generating the image, use the command "docker-compose up" to create the container.


## Steps followed to test the app in an IDE: ##

1.- The project must have 1.8 jdk version.

2.- Load all dependencies.

3.- Make sure that the other containers (the ones in the backendDevTest projects) are up.

4.- Run a Java Application using "TestdemoApplication.java" as the main class.

