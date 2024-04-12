package com.cashReg.models;

public class CommodityExpert extends User {

    public CommodityExpert(String username, String password) {
        super(username, password);
    }

    public void createGood(){

    }

    public long getQuantity(Product product){
return product.getQuantity();
    }
}
