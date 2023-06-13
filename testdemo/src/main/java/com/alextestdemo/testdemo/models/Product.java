package com.alextestdemo.testdemo.models;

public class Product {
    private String id;
    private String name;

    private float price;

    private boolean availability;


    public Product(){
        super();
    }
    public Product(String id, String name, float price, boolean availability){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    public String getId(){ return id; }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){ return name; }

    public void setName(String name){
        this.name = name;
    }
    public float getPrice(){ return price; }

    public void setPrice(float price){
        this.price = price;
    }

    public boolean getAvailability(){ return availability; }

    public void setAvailability(boolean availability){
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", availability=" + availability + "]";
    }
}
