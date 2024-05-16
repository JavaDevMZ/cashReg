package com.cashReg.models;

//Wrapper
public class OrderItem extends Product{

    private Product origin;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    private long orderId;
    private long productId;

    public long getOrderId() {
        return orderId;
    }

    public OrderItem(Product origin, long orderId){
        this.origin = origin;
        this.orderId = orderId;
        productId = origin.getId();
    }

    public long getProductId(){
        return origin.getId();
    }

    @Override
    public void setPrice(float price){
        origin.setPrice(price);
    }

    @Override
    public float getPrice(){
        return origin.getPrice();
    }

    public String getName(){
        return origin.getName();
    }

    public void setName(String name){
        origin.setName(name);
    }

    public Product getProduct(){
        return origin;
    }

    @Override
    public String toString(){
        String result = "<form>" +
                "<input name=\"set_qty_id\" readonly value=%d>" +
                "%s %f$ * <input name=\"set_quantity\" type=\"number\" value=%%d> = %%f" +
                "<button type=\"submit\" formmethod=\"post\">OK</button>"+
                "</form>";
        return String.format(result, getId(), getName(), getPrice());
    }

}
