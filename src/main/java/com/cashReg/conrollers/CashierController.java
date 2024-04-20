package com.cashReg.conrollers;

import com.cashReg.CashRegister;
import com.cashReg.models.Cashier;
import com.cashReg.models.User;

public class CashierController {

    private Cashier cashier = (Cashier)CashRegister.getInstance().getCurrentUser();

    public void createOrder(){
        cashier.createOrder();
    }

    public void addToOrder(String nameOrId, long quantity){
        try {
            long productId = Long.parseLong(nameOrId);
            cashier.addToOrder(productId, quantity);
        }catch(IllegalArgumentException e) {
            String productName = nameOrId;
            cashier.addToOrder(productName, quantity);
        }
    }

    public void setQuantity(long itemId, long quantity){
        cashier.setQuantity(itemId, quantity);
    }
}
