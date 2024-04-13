package com.cashReg;

import com.cashReg.dao.SQLExecutor;
import com.cashReg.models.Product;
import com.cashReg.util.SQLList;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public final class Warehouse {

    private static Warehouse instance;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private List<Product> products;
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
        for(Product product : products){
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
    public Product getProduct(String name){
        for(Product product : products){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public void setQuantity(long id, long quantity){

        ((SQLList<Product>)products).getById(id).setQuantity(quantity);
    }

    public List<Product> getProducts() {
        return products;
    }

}
