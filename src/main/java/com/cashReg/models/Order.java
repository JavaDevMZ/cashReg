package com.cashReg.models;

import com.cashReg.util.SQLList;
import java.util.List;

public class Order extends Model {

    private long customerId;
    private float amount;
    private List<OrderItem> items;
    private boolean isClosed = false;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
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

    public void addProduct(Product product, long quantity){
        if(quantity > product.getQuantity() || quantity <=0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        items.add(new OrderItem(product, id, quantity));
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

    public void setClosed(){
        isClosed=true;
    }

    public Product remove(long productId){
       Product result = null;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getProductId()==productId){
                result = items.remove(i).getProduct();
            }
        }
        return result;
    }
}
