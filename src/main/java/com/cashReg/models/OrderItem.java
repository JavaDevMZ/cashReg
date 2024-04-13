package com.cashReg.models;

//Wrapper
public class OrderItem extends Product{

    private Product origin;
    private long id;
    private long productId;
    private long quantity;

    public OrderItem(Product origin, long quantity){
        this.origin = origin;
        productId = origin.getId();
    }

    public long getProductId(){
        return origin.getId();
    }

    public long getQuantity(){
        return quantity;
    }

    public void setQuantity(long quantity){
        this.quantity = quantity;
    }
    @Override
    public void setPrice(float price){
        origin.setPrice(price);
    }

    @Override
    public float getPrice(){
        return origin.getPrice();
    }

    public long getQuantityInWarehouse(){
        return origin.getQuantity();
    }

    public Product getProduct(){
        return origin;
    }
}
