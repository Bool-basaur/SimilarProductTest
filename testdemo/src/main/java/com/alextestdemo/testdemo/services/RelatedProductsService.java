package com.alextestdemo.testdemo.services;

import com.alextestdemo.testdemo.models.Product;

import java.util.List;

public interface RelatedProductsService {
    public List<Product> getSimilarProducts(String id);

    public Product getProductRequest(String id);

    public List<Integer> getSimilarProductsRequest(String id);
}
