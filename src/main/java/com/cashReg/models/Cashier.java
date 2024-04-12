package com.cashReg.models;

public class Cashier extends User {

    private final Role role = Role.CASHIER;
    public Cashier(String username, String password){
        super(username, password);
    }
    public void createOrder(){

    }

    public void addToOrder(int code){

    }

    public void addToOrder(String name){

    }

    public void setQuantity(Product product, int quantity){

    }

    public void closeOrder(){

    }
}
