package com.cashReg.models;

public class SeniorCashier extends Cashier {

    public SeniorCashier(String username, String password){
        super(username, password);
    }

    public void cancelOrder(){
        order.cancel();
        order=null;
    }

    public void cancelProductInOrder(long id){
        order.remove(id);
    }

        //todo after having implemented web pages
    public void xReport(){

    }

    public void zReport(){

    }
}
