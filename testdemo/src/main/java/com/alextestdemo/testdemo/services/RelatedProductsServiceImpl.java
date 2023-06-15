package com.alextestdemo.testdemo.services;

import com.alextestdemo.testdemo.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatedProductsServiceImpl implements RelatedProductsService {

    private final String REQUEST_URL="http://localhost:3001/product/";

    Logger logger = LoggerFactory.getLogger(RelatedProductsServiceImpl.class);

    @Cacheable(value="products", key="#id")
    @Override
    public List<Product> getSimilarProducts(String id) {
        logger.debug("Entering in the getSimilarProducts function of RelatedProductsServiceImpl.java");
        List<Product> relatedProductsList = null;
        List<Integer> idList = getSimilarProductsRequest(id);
        if(idList != null && idList.size()>0){
            relatedProductsList = new ArrayList<>();
            Product p = null;
            for(Integer similarProductId : idList){
                p = getProductRequest(similarProductId.toString());
                if(p!=null) relatedProductsList.add(p);
            }
            logger.info("The returned list of similarProducts length is " + relatedProductsList.size());
        }
        return relatedProductsList;
    }
    @Cacheable(value="products", key="#id")
    public Product getProductRequest(String id) {
        logger.debug("Entering in the getProductRequest function of RelatedProductsServiceImpl.java");
        RestTemplate restTemplate = new RestTemplate();
        Product product = null;
        try {
            ResponseEntity<Product> response =
                    restTemplate.getForEntity(
                            REQUEST_URL + id,
                            Product.class);
            if(response.getStatusCode().equals(HttpStatus.OK)){
                product = response.getBody();
                logger.debug("URL request for product was successful");
                logger.info("The product " + product.getName() + " was obtained successfully");
            }
            else logger.info("The request of getting the product with id " + id + " wasn't successful");
        } catch(Exception e){
            logger.error("There was an error while getting the product with id " + id + " : " + e.getMessage());
        }
        return product;
    }

    @Cacheable(value="products", key="#id")
    public List<Integer> getSimilarProductsRequest(String id){
        logger.debug("Entering in the getSimilarProductsRequest function of RelatedProductsServiceImpl.java");
        List<Integer> idsList = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<List> response =
                    restTemplate.getForEntity(
                            REQUEST_URL + id + "/similarids",
                            List.class);
            if(response.getStatusCode().equals(HttpStatus.OK)){
                idsList = response.getBody();
                logger.debug("URL request for similarProducts ids was successful");
                logger.info("There were in total " + idsList.size() + " similar products to the one with id " + id);
            }
            else logger.info("The request of getting the list of ids of similar products of the product with id " + id + " wasn't successful");
        } catch(Exception e){
            logger.error("There was an error while getting the ids of similar products of the product with id " + id);
        }
        return idsList;
    }

}