package com.cashReg.servlets;

import com.cashReg.CashRegister;
import com.cashReg.Warehouse;
import com.cashReg.util.sql.SQLExecutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.SQLException;

@WebServlet("/")
public class StartServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQLExecutor sqlExecutor = new SQLExecutor();
        String initQuery = "";
        try(BufferedReader sqlReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("cashRegister.sql")))){
           while(sqlReader.ready()) {
               initQuery += sqlReader.readLine() + "\n";
           }
           sqlExecutor.execute(initQuery);
        }catch(IOException ioe){
            throw new RuntimeException("An exception occured while reading the initial SQL script");
        }catch(SQLException sqlE){
            throw new RuntimeException("An exception occurred while initializing the database");
        }
        CashRegister cashRegister= CashRegister.getInstance();
            cashRegister.setUsers(sqlExecutor.getAllUsers());
            cashRegister.setOrders(sqlExecutor.getAllOrders());
            Warehouse warehouse = Warehouse.getInstance();
            warehouse.setProducts(sqlExecutor.getAllProducts());


    }
}
