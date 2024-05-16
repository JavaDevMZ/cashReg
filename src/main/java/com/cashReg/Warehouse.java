package com.cashReg;

import com.cashReg.util.sql.SQLExecutor;
import com.cashReg.models.Product;

import javax.inject.Singleton;
import java.util.Map;

@Singleton
public final class Warehouse {

    private static volatile Warehouse instance;
    private volatile Map<Product, Long> products;
    private SQLExecutor sqlExecutor = new SQLExecutor();

    public void setProducts(Map<Product, Long> products) {
        this.products = products;
    }

    public static Warehouse getInstance(){
             if(instance==null) {
                 instance = new Warehouse();
             }
        return instance;
    }

    private Warehouse(){
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

    public void setQuantity(long id, long quantity){
       products.put(getProduct(id), quantity);
    }

    public long getQuantity(long productId){
       return getQuantity(getProduct(productId));
    }

    public long getQuantity(Product product){
       return products.get(product);
    }

    public Map<Product, Long> getProducts() {
        return products;
    }

}
