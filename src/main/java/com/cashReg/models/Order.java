package com.cashReg.models;

public abstract class AbstractOrder {
    private long id;
    private long customerId;
    private float amount;

    public AbstractOrder(long id, long customerId, float amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
