package com.cashReg.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order extends Model {

    private String customerEmail;
    private float amount;
    private Map<OrderItem, Long> items = new HashMap<>();
    private boolean isClosed = false;
    private long itemsCount = 0;
    private long cashierId;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Map<OrderItem, Long> getItems() {
        return items;
    }

    public void setItems(Map<OrderItem, Long> items) {
        this.items = items;
    }

    public void close(){
            isClosed = true;
    }

    public float getAmount() {
        float amount = 0.0f;
        for(OrderItem item : items.keySet()){
            amount += items.get(item) * item.getPrice();
        }
        this.amount = amount;
        return amount;
    }

    public void addProduct(Product product, long quantity){
        if(quantity > warehouse.getQuantity(product) || quantity <=0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        OrderItem orderItem = new OrderItem(product, id);
        orderItem.setId(items.size());
        items.put(orderItem, quantity);
    }

    public void addProduct(long productId, long quantity){
        Product product = warehouse.getProduct(productId);
        addProduct(product, quantity);
    }

    public void addProduct(String productName, long quantity){
        Product product = warehouse.getProduct(productName);
        addProduct(product, quantity);
    }

    public void setQuantity(long itemId, long quantity){
        for(OrderItem item : items.keySet()){
            if(item.getId() == itemId){
                items.put(item, quantity);
            }
        }
    }

    public boolean isClosed(){return isClosed;}

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        if(isClosed){throw new RuntimeException("Order is closed");}
        this.customerEmail = customerEmail;
    }

    public void cancel(){
        items.clear();
        items = null;
        amount = 0.0f;
        customerEmail = null;
    }

    public void setClosed(){
        isClosed=true;
    }

    public Product remove(long productId){
       Product result = null;
        for(OrderItem item : items.keySet()){
            if(item.getProductId()==productId){
                result = item;
                items.remove(items);
            }
        }
        return result;
    }

    public long getCashierId(){
        return cashierId;
    }

    public void setCashierId(long cashierId){
        this.cashierId=cashierId;
    }

    public String toString(){
        return " " + id + "; <fmt:message key=\"customer_email\"/>: " + customerEmail +
                " <fmt:message key=\"date\"/>: " + date + " <fmt:message key=\"amount\"/>: " + amount;
    }
}
