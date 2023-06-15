package com.alextestdemo.testdemo;

import com.alextestdemo.testdemo.models.Product;
import com.alextestdemo.testdemo.services.RelatedProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RelatedProductsServiceTest {

    @Autowired
    RelatedProductsService service;

    @Test
    void testGetNotFoundProductRequest(){
        String id = "198247";
        Product p = service.getProductRequest(id);
        assertEquals(null, p);
    }

    @Test
    void testGetNotFoundProductsAssociatedRequest(){
        String id = "6";
        List<Integer> list = service.getSimilarProductsRequest(id);
        assertEquals(null, list);
    }

    @Test
    void testGetFoundProductRequest(){
        String id = "1";
        Product p = service.getProductRequest(id);
        Product testP = new Product("1", "Shirt", 9.99F, true);
        assertNotNull(p);
        assertEquals(testP.getId(), p.getId());
        assertEquals(testP.getName(), p.getName());
        assertEquals(testP.getPrice(), p.getPrice());
        assertEquals(testP.getAvailability(), p.getAvailability());
    }

    @Test
    void testGetFoundProductsAssociatedRequest(){
        String id = "1";
        List<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(3);
        testList.add(4);
        List<Integer> list = service.getSimilarProductsRequest(id);
        assertNotNull(list);
        assertEquals(testList.size(), list.size());
        for(int i=0; i<list.size(); i++){
            assertEquals(testList.get(i), list.get(i));
        }

    }

    @Test
    void testGetNotFoundProduct(){
        String id = "152351235";
        List<Product> list = service.getSimilarProducts(id);
        assertEquals(null, list);
    }

    @Test
    void testGetNotFoundProductAssociations(){
        String id = "6";
        List<Product> list = service.getSimilarProducts(id);
        assertEquals(null, list);
    }

    @Test
    void testGetFoundProductsSimilarToAnotherOne(){
        String id = "1";
        List<Product> testList = new ArrayList<>();
        testList.add(new Product("2", "Dress", 19.99F, true));
        testList.add(new Product("3", "Blazer", 29.99F, false));
        testList.add(new Product("4", "Boots", 39.99F, true));
        List<Product> list = service.getSimilarProducts(id);

        assertNotNull(list);
        assertEquals(testList.size(), list.size());

        for(int i=0; i<list.size(); i++){
            assertEquals(testList.get(i).getId(), list.get(i).getId());
            assertEquals(testList.get(i).getName(), list.get(i).getName());
            assertEquals(testList.get(i).getPrice(), list.get(i).getPrice());
            assertEquals(testList.get(i).getAvailability(), list.get(i).getAvailability());
        }
    }

    @Test
    void testGetFoundProductsSimilarToAnotherOneWithOneProductNotAvailable(){
        String id = "5";
        List<Product> testList = new ArrayList<>();
        testList.add(new Product("1", "Shirt", 9.99F, true));
        testList.add(new Product("2", 	"Dress", 19.99F, true));
        List<Product> list = service.getSimilarProducts(id);

        assertNotNull(list);
        assertEquals(testList.size(), list.size());

        for(int i=0; i<list.size(); i++){
            assertEquals(testList.get(i).getId(), list.get(i).getId());
            assertEquals(testList.get(i).getName(), list.get(i).getName());
            assertEquals(testList.get(i).getPrice(), list.get(i).getPrice());
            assertEquals(testList.get(i).getAvailability(), list.get(i).getAvailability());
        }
    }


}
