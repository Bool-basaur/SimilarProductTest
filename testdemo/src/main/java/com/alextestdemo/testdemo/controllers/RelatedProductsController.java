package com.alextestdemo.testdemo.controllers;

import com.alextestdemo.testdemo.models.Product;
import com.alextestdemo.testdemo.services.RelatedProductsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class RelatedProductsController {

    @Autowired
    RelatedProductsService service;
    Logger logger = LoggerFactory.getLogger(RelatedProductsController.class);
    @ApiOperation(value = "Controller that returns the similar products given an ID of a product", nickname = "getSimilarProducts")
    @RequestMapping(value = "/{productId}/similar", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable("productId") String id){
        HttpStatus status = null;
        HttpHeaders headers = new HttpHeaders();
        logger.debug("Entering in the getSimilarProducts function of RelatedProductsController.java");
        headers.add("Content-Type", "application/json");
        List<Product> similarProductsList = service.getSimilarProducts(id);
        if(similarProductsList != null){
            logger.debug("The service returned the list without errors");
            status = HttpStatus.OK;
        }
        else{
            logger.debug("The service returned the list with errors");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logger.info("Status of the request in the RelatedProductsController: " + status.toString());
        return new ResponseEntity<>(similarProductsList, headers, status);
    }
}
