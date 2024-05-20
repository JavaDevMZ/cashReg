package com.cashReg.controllers;

import com.cashReg.CashRegister;
import com.cashReg.util.UserUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.logging.LogManager;

@WebServlet({"/cashier/order", "/senior_cashier/order"})
public class OrderController extends InternationalizableImpl{

    private static final Logger log = Logger.getLogger(OrderController.class);

    private static CashRegister cashRegister = CashRegister.getInstance();
    private CashierController cashierController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/order.jsp");
            dispatcher.forward(req, resp);
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(changeLanguageIfNeeded(req)){
            doGet(req, resp);
        }
        if("/senior_cashier/order".equals(req.getRequestURI())){
            cashierController = new SeniorCashierController();
        }else{
            cashierController = new CashierController();
        }

        String customerEmail = req.getParameter("customer_email");
        log.info("new Order!");
        log.info(customerEmail);
        if(customerEmail !=null){
            cashierController.setCustomerEmail(customerEmail);
        }
        String nameOrId = req.getParameter("product");
        String quantity = req.getParameter("quantity");
        if(nameOrId!=null && quantity!=null) {
            cashierController.addToOrder(nameOrId, Long.parseLong(quantity));
        }

        String setQtyId = req.getParameter("set_qty_id");
        String newQty = req.getParameter("set_quantity");
        if(setQtyId!=null && newQty!=null){
            cashierController.setQuantity(Long.parseLong(setQtyId), Long.parseLong(newQty));
        }

        String removeParam = req.getParameter("remove");
        if(removeParam!=null){
            ((SeniorCashierController)cashierController).removeProduct(Long.parseLong(removeParam));
        }

        String cancelParam = req.getParameter("cancel");
        if(cancelParam!=null){
            ((SeniorCashierController)cashierController).cancelOrder();
        }

        String closeParam = req.getParameter("close");
        if("true".equals(closeParam)){
            cashierController.closeOrder();
            resp.sendRedirect(UserUtil.getUserPage(cashRegister.getCurrentUser()));
        }
        doGet(req, resp);
    }
}
