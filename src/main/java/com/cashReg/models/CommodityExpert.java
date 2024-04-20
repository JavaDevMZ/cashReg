package com.cashReg.models;

import com.cashReg.Warehouse;
import com.cashReg.util.SQLList;
import com.cashReg.util.SQLMap;

import java.util.List;
import java.util.Map;

public class CommodityExpert extends User {

    Warehouse warehouse = Warehouse.getInstance();
    private Map<Product, Long> products = warehouse.getProducts();

    public CommodityExpert(String username, String password) {
        super(username, password);
    }

    public Product createProduct(String productName, float price, long quantity){
      Product result = new Product();
      result.setName(productName);
      result.setPrice(price);

      products.put(result, quantity);
        return result;
    }

    public void setQuantity(String productName, long quantity){
        warehouse.setQuantity(products.get(warehouse.getProduct(productName)), quantity);
    }

    public long getQuantity(String productName){
        return products.get(warehouse.getProduct(productName));
    }
}
