package com.cashReg.models;

import java.util.Arrays;

public enum Role {
    CASHIER("Cashier", 1),
    SENIOR_CASHIER("SeniorCashier", 2),
    COMMODITY_EXPERT("CommodityExpert", 3);

    private final String value;
    private final int ordinal;

    private Role(String value, int ordinal){
        this.value=value;
        this.ordinal=ordinal;
    }

    public static Role getByOrdinal(int ordinal) {
        return values()[ordinal-1];
    }

    public int getOrdinal(){
       return ordinal;
    }
    @Override
    public String toString() {
        return value;
    }
}
