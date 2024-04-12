package com.cashReg.models;

import com.cashReg.dao.SQLExecutor;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public final class Warehouse {

    private static Warehouse instance;

    private final List<OrderItem> products;
    private SQLExecutor sqlExecutor = new SQLExecutor();


    public static Warehouse getInstance(){
        if(instance==null){
            instance = new Warehouse();
        }
        return instance;
    }

    private Warehouse(){
        products = sqlExecutor.getAllProducts();
    }

    public Product getProduct(long id){
        for(Product product : products.keySet()){
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
    public Product getProduct(String name){
        for(Product product : products.keySet()){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public void setQuantity(Product product, long quantity){
        if(!products.containsKey(product)){
            throw new IllegalArgumentException("product not found");
        }
        products.put(product, quantity);
    }
    public List<OrderItem> getProducts() {
        return products;
    }

}
