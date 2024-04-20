package com.cashReg.servlets;

import com.cashReg.CashRegister;
import com.cashReg.conrollers.CashierController;
import com.cashReg.models.Cashier;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static CashRegister cashRegister = CashRegister.getInstance();
    private CashierController cashierController = new CashierController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("order.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String nameOrId = req.getParameter("product");
        long quantity = Long.parseLong(req.getParameter("quantity"));

    }
}
