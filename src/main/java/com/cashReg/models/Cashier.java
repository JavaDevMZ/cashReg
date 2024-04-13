package com.cashReg.models;

import com.cashReg.CashRegister;
import com.cashReg.util.Role;

public class Cashier extends User {

    public Order getOrder() {
        return order;
    }

    protected Order order;

    public Cashier(String username, String password){
        super(username, password);
    }

    public Order createOrder(){
        if(order!=null && !order.isClosed()){
            throw new RuntimeException("There is a non-closed order");
        }
        order = new Order();
        return order;
    }

    public void addToOrder(long productCode, long quantity){
        order.addProduct(productCode, quantity);
    }

    public void addToOrder(String productName, long quantity){
        order.addProduct(productName, quantity);
    }

    public void setQuantity(long productId, long quantity){
        if(order.isClosed()){throw new RuntimeException("Order is closed");}
        order.setQuantity(productId, quantity);
    }

    public void closeOrder(){
        order.close();
        cashRegister.addOrder(order);
    }
}
