package com.cashReg.models;

import com.cashReg.Warehouse;
import com.cashReg.dao.SQLExecutor;
import com.cashReg.util.SQLArrayList;

import java.util.List;

public class CommodityExpert extends User {

    Warehouse warehouse = Warehouse.getInstance();
    private List<Product> products = warehouse.getProducts();
    private SQLExecutor sqlExecutor = new SQLExecutor();

    public CommodityExpert(String username, String password) {
        super(username, password);
    }

    public Product createGood(String productName, float price, long quantity){

      Product result = new Product();
      result.setName(productName);
      result.setPrice(price);
      result.setQuantity(quantity);
      products.add(result);
        return result;
    }

    public void setQuantity(long id, long quantity){
        SQLArrayList<Product> products = (SQLArrayList<Product>)this.products;
        products.getById(id).setQuantity(quantity);
    }

    public long getQuantity(long id){
        SQLArrayList<Product> products = (SQLArrayList<Product>)this.products;
        return products.getById(id).getQuantity();
    }
}
