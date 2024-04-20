package com.cashReg;

import com.cashReg.util.sql.SQLExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Initializer {

    public static synchronized void initializeAll(){
           SQLExecutor sqlExecutor = new SQLExecutor();
       String initQuery = "";
        try(BufferedReader sqlReader = new BufferedReader(new InputStreamReader(Initializer.class.getClassLoader().getResourceAsStream("cashRegister.sql")))){
            while(sqlReader.ready()) {
                initQuery += sqlReader.readLine();
            }
            initQuery = initQuery.replaceAll("\\n", "");
            sqlExecutor.execute(initQuery);
        }catch(IOException ioe){
            throw new RuntimeException("An exception occured while reading the initial SQL script");
        }catch(SQLException sqlE){
            throw new RuntimeException("An exception occurred while initializing the database");
        }
        CashRegister cashRegister = CashRegister.getInstance();
        cashRegister.setUsers(sqlExecutor.getAllUsers());
        cashRegister.setOrders(sqlExecutor.getAllOrders());
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.setProducts(sqlExecutor.getAllProducts());
    }
}