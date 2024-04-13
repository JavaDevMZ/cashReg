package com.cashReg.models;

import com.cashReg.util.Role;

public abstract class User extends Model{

    private String username;
    private final Role role;
    private String password;

    public User(String userame, String password){
        this.username = username;
        this.password = password;
        switch(this.getClass().getName()){
            case "CommodityExpert":
                role=Role.COMMODITY_EXPERT;
                break;
            case "Cashier":
                role=Role.CASHIER;
                break;
            case "SeniorCashier":
                role=Role.SENIOR_CASHIER;
                break;
            default:
                throw new RuntimeException("invalid user role");
        }
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return id == other.getId() || username.equals(other.username);
    }

    @Override
    public int hashCode(){
        return 31+17*username.hashCode() + 17*(int)id;
    }
}
