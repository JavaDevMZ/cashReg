package com.cashReg.controllers;

import com.cashReg.CashRegister;
import com.cashReg.models.Cashier;
import com.cashReg.models.SeniorCashier;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/senior_cashier")
public class SeniorCashierController extends CashierController{

    protected Cashier cashier = (SeniorCashier)CashRegister.getInstance().getCurrentUser();

    @Override
    protected void doIfSeniorCashier(HttpServletRequest req) {
        if("true".equals(req.getParameter("x_report"))){
            req.setAttribute("x_report", xReport());
        }

        if("true".equals(req.getParameter("z_report"))){
            req.setAttribute("z_report", zReport());
        }
    }

    public String zReport(){
            return ((SeniorCashier)cashier).zReport();
    }

    public String xReport(){
            return ((SeniorCashier)cashier).xReport();
    }

    public void removeProduct(long id){
        ((SeniorCashier)cashier).cancelProductInOrder(id);
    }

    public void cancelOrder(){
        ((SeniorCashier)cashier).cancelOrder();
    }
}
