package com.cashReg.controllers;

import com.cashReg.CashRegister;
import com.cashReg.models.Cashier;
import com.cashReg.models.Order;
import com.cashReg.models.SeniorCashier;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cashier")
public class CashierController extends InternationalizableImpl {

    protected Cashier cashier = (Cashier)CashRegister.getInstance().getCurrentUser();

    public void createOrder(){
        cashier.createOrder();
    }

    public void addToOrder(String nameOrId, long quantity){
        try {
            long productId = Long.parseLong(nameOrId);
            cashier.addToOrder(productId, quantity);
        }catch(IllegalArgumentException e) {
            String productName = nameOrId;
            cashier.addToOrder(productName, quantity);
        }
    }

    public void setQuantity(long itemId, long quantity){
        cashier.setQuantity(itemId, quantity);
    }

    public void closeOrder(){
        cashier.closeOrder();
    }

    public Order getCurrentOrder(){
       return cashier.getOrder();
    }

    public void setCustomerEmail(String email){
        cashier.setCustomerEmail(email);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        if("/cashier".equals(req.getRequestURI())) {
            dispatcher = req.getRequestDispatcher("/cashier_home.jsp");
        }
        if("/senior_cashier".equals(req.getRequestURI())){
            dispatcher = req.getRequestDispatcher("/senior_cashier_home.jsp");
        }
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(changeLanguageIfNeeded(req)){
            doGet(req, resp);
        }
        if("/senior_cashier".equals(req.getRequestURI())){
          cashier = (SeniorCashier)CashRegister.getInstance().getCurrentUser();
        }else{
            cashier = (Cashier)CashRegister.getInstance().getCurrentUser();
        }

        if(req.getParameter("create_order")!=null){
            createOrder();
            resp.sendRedirect(req.getRequestURI()+"/order");
        }else {
            doIfSeniorCashier(req);
            doGet(req, resp);
        }
    }

    protected void doIfSeniorCashier(HttpServletRequest req){}
}
