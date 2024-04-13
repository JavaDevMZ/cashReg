package com.cashReg.servlets;

import com.cashReg.CashRegister;
import com.cashReg.Warehouse;
import com.cashReg.dao.SQLExecutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class StartServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQLExecutor sqlExecutor = new SQLExecutor();
        CashRegister cashRegister= CashRegister.getInstance();
            cashRegister.setUsers(sqlExecutor.getAllUsers());
        Warehouse warehouse = Warehouse.getInstance();
            warehouse.setProducts(sqlExecutor.getAllProducts());

    }
}
