package com.cashReg.util;

public enum Locale {

    EN,
    UA;

    public static Locale forOrdinal(int ordinal){
        return values()[ordinal];
    }
}
