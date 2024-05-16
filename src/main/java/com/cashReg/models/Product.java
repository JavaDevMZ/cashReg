package com.cashReg.models;

public class Product extends Model implements HasQuantity{

    private String name;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product(long id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
            return (id == other.id || name.equals(other.name));
    }

    @Override
    public int hashCode() {
            return 31 + 17 * (int) id + 17;
    }

    @Override
    public String toString(){
        String result = "<input name=\"set_qty_id\" readonly value=%d> %s %f$ ";
        return String.format(result, getId(), getName(), getPrice());}
}
