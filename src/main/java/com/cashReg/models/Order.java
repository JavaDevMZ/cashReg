package com.cashReg.models;

import com.cashReg.util.SQLArrayList;

public class Order extends Model {

    private long customerId;
    private float amount;
    private SQLArrayList<OrderItem> items;
    private boolean isClosed = false;

    public SQLArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(SQLArrayList<OrderItem> items) {
        this.items = items;
    }

    public void close(){
        isClosed=true;
    }

    public float getAmount() {
        float amount = 0.0f;
        for(OrderItem item : items){
            amount += item.getQuantity() * item.getPrice();
        }
        this.amount = amount;
        return amount;
    }

    public void addProduct(Product product, Long quantity){
        if(quantity > product.getQuantity() || quantity <=0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        items.add(new OrderItem(product, quantity));
    }

    public void addProduct(long productId, long quantity){
        Product product = warehouse.getProduct(productId);
        addProduct(product, quantity);
    }

    public void addProduct(String productName, long quantity){
        Product product = warehouse.getProduct(productName);
        addProduct(product, quantity);
    }

    public void setQuantity(long productId, long quantity){
      for(OrderItem item : items){
          if(item.getProductId()==productId){
              item.setQuantity(quantity);
          }
      }
    }

    public boolean isClosed(){return isClosed;}

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        if(isClosed){throw new RuntimeException("Order is closed");}
        this.customerId = customerId;
    }

    public void cancel(){
        items.clear();
        items = null;
        amount = 0.0f;
        customerId = -1;
    }

    public void remove(long productId){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getProductId()==productId){
                items.remove(i);
            }
        }
    }
}
