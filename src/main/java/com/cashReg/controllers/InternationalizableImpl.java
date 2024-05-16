package com.cashReg.controllers;

import com.cashReg.CashRegister;
import com.cashReg.util.Locale;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public abstract class InternationalizableImpl extends HttpServlet implements Internationalizable{

    CashRegister cashRegister = CashRegister.getInstance();

    public boolean changeLanguageIfNeeded(HttpServletRequest req){
        if("true".equals(req.getParameter("change_language"))) {
            Locale currentLocale = cashRegister.getLocale();
            int currentOrdinal = currentLocale.ordinal();
            int ordinalsNumber = Locale.values().length;
            cashRegister.setLocale(Locale.forOrdinal((currentOrdinal + 1) % ordinalsNumber));
            return true;
        }else{
            return false;
        }
    }
}
