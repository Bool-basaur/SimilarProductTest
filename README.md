# SimilarProductTest 

-This Spring boot application includes the use of cache, logger, y swagger. Swagger doc is available in http://localhost:5000/swagger-ui.html#/

-The code is located in testdemo > src > main > java > com.alextestdemo.testdemo

-Tests that have been done with JUnit5 are in testdemo > src > test > java > com.alextestdemo.testdemo
(Manual tests have been done using Google Chrome)

-Request examples: 
Product with some related products that are ok:
http://localhost:5000/product/1/similar
Product with some related products and one of them doesn't return the product info:
http://localhost:5000/product/4/similar
Product that can't be find:
http://localhost:5000/product/6/similar
