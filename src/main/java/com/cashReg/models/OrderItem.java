package com.cashReg.models;

//Wrapper
public class OrderItem extends Product{

    private Product origin;
    private long id;
    private long orderId;
    private long productId;
    private long quantity;
    public long getOrderId() {
        return orderId;
    }

    public OrderItem(Product origin, long orderId, long quantity){
        this.origin = origin;
        this.orderId = orderId;
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
