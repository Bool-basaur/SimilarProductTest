package com.alextestdemo.testdemo;

import com.alextestdemo.testdemo.controllers.RelatedProductsController;
import com.alextestdemo.testdemo.models.Product;
import com.alextestdemo.testdemo.services.RelatedProductsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RelatedProductsController.class)
public class RelatedProductsControllerTest {

    @MockBean
    RelatedProductsService service;

    @Autowired
    RelatedProductsController controller;
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenAnIDGetProductAssociationsStatus200(){
        String id = "1";
        List<Product> testList = new ArrayList<>();
        testList.add(new Product("2", "Dress", 19.99F, true));
        testList.add(new Product("3", "Blazer", 29.99F, false));
        testList.add(new Product("4", "Boots", 39.99F, true));

        Mockito.when(service.getSimilarProducts(id)).thenReturn(testList);

        try {
            mvc.perform(get("/product/" + id + "/similar"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.hasSize(3)))
                    .andExpect(jsonPath("$[0].id", Matchers.is("2")))
                    .andExpect(jsonPath("$[0].name", Matchers.is("Dress")))
                    .andExpect(jsonPath("$[0].price", Matchers.is(19.99)))
                    .andExpect(jsonPath("$[0].availability", Matchers.is(true)))
                    .andExpect(jsonPath("$[1].id", Matchers.is("3")))
                    .andExpect(jsonPath("$[1].name", Matchers.is("Blazer")))
                    .andExpect(jsonPath("$[1].price", Matchers.is(29.99)))
                    .andExpect(jsonPath("$[1].availability", Matchers.is(false)))
                    .andExpect(jsonPath("$[2].id", Matchers.is("4")))
                    .andExpect(jsonPath("$[2].name", Matchers.is("Boots")))
                    .andExpect(jsonPath("$[2].price", Matchers.is(39.99)))
                    .andExpect(jsonPath("$[2].availability", Matchers.is(true)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAnIDGetProductThatReturnsNullStatus404(){
        String id = "6";
        List<Product> testList = null;

        Mockito.when(service.getSimilarProducts(id)).thenReturn(testList);

        try {
            mvc.perform(get("/product/" + id + "/similar"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$", Matchers.is("Product Not found")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAnEmptyIDGetProductWithAssociationsStatus404(){
        String id = "";
        List<Product> testList = null;

        Mockito.when(service.getSimilarProducts(id)).thenReturn(testList);

        try {
            mvc.perform(get("/product/" + id + "/similar"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Test
    public void givenAnIDGetProductTheServiceThrowsExceptionStatus400(){
        String id = "";

        Mockito.when(service.getSimilarProducts(id)).thenThrow(new NullPointerException());

        try {
            mvc.perform(get("/product/" + id + "/similar"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullPointerExceptionInControllerStatus400(){
        String id = "";

        Mockito.when(controller.getSimilarProducts("1")).thenThrow(new NullPointerException());

        try {
            mvc.perform(get("/product/" + id + "/similar"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
