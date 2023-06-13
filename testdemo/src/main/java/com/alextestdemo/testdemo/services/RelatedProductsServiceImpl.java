package com.alextestdemo.testdemo.services;

import com.alextestdemo.testdemo.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatedProductsServiceImpl implements RelatedProductsService {

    private List<Product> relatedProductsList;

    Logger logger = LoggerFactory.getLogger(RelatedProductsServiceImpl.class);

    @Override
    public List<Product> getSimilarProducts(String id) {
        logger.debug("Entering in the getSimilarProducts function of RelatedProductsServiceImpl.java");
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> idList = getSimilarProductsRequest(id, restTemplate);
        relatedProductsList = null;
        if(idList != null && idList.size()>0){
            relatedProductsList = new ArrayList<>();
            for(Integer similarProductId : idList){
                relatedProductsList.add(getProductRequest(similarProductId.toString(), restTemplate));
            }
            logger.info("The returned list of similarProducts length is " + relatedProductsList.size());
        }
        return relatedProductsList;
    }

    private Product getProductRequest(String id, RestTemplate restTemplate) {
        logger.debug("Entering in the getProductRequest function of RelatedProductsServiceImpl.java");
        Product product = null;
        ResponseEntity<Product> response =
                restTemplate.getForEntity(
                        "http://localhost:3001/product/" + id,
                        Product.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            product = response.getBody();
            logger.debug("URL request for product was successful");
            logger.info("The product " + product.getName() + " was obtained successfully");
        }
        else logger.info("There was an error while getting the product with id " + id);
        return product;
    }

    private List<Integer> getSimilarProductsRequest(String id, RestTemplate restTemplate){
        logger.debug("Entering in the getSimilarProductsRequest function of RelatedProductsServiceImpl.java");
        List<Integer> idsList = null;
        ResponseEntity<List> response =
                restTemplate.getForEntity(
                        "http://localhost:3001/product/" + id + "/similarids",
                        List.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            idsList = response.getBody();
            logger.debug("URL request for similarProducts ids was successful");
            logger.info("There were in total " + idsList.size() + " similar products to the one with id " + id);
        }
        else logger.info("There was an error while getting the ids of similar products with the id " + id);
        return idsList;
    }



}