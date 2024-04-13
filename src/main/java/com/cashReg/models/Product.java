package com.cashReg.models;

public class Product extends Model{

    private String name;
    private float price;
    private long quantity;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product(long id, String name, float price, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(o==null){return false;}
        if(o==this){return true;}

        if(!(getClass()!=o.getClass())){return false;}
            Product other = (Product) o;
            return id == other.id || name.equals(other.name);
    }

    @Override
    public int hashCode(){
        return 31 + 17*(int)id + 17*name.hashCode() + 17*(int)price;
    }
   }
