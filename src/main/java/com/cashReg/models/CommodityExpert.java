package com.cashReg.models;

import com.cashReg.Warehouse;
import com.cashReg.util.SQLList;

import java.util.List;

public class CommodityExpert extends User {

    Warehouse warehouse = Warehouse.getInstance();
    private List<Product> products = warehouse.getProducts();

    public CommodityExpert(String username, String password) {
        super(username, password);
    }

    public Product createProduct(String productName, float price, long quantity){
      Product result = new Product();
      result.setName(productName);
      result.setPrice(price);
      result.setQuantity(quantity);
      products.add(result);
        return result;
    }

    public void setQuantity(long id, long quantity){
        SQLList<Product> products = (SQLList<Product>)this.products;
        products.getById(id).setQuantity(quantity);
    }

    public long getQuantity(long id){
        SQLList<Product> products = (SQLList<Product>)this.products;
        return products.getById(id).getQuantity();
    }
}
