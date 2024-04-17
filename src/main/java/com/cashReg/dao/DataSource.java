package com.cashReg.dao;

import java.sql.Connection;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static final String URL = "db.URL";

    private static final String USERNAME = "db.USERNAME";

    private static final String PASSWORD = "db.PASSWORD";

    private DataSource(){}

    static{
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        }

   public static Connection getConnection(){
       try {
           PGSimpleDataSource ds = new PGSimpleDataSource();
           ds.setURL(PropertiesUtil.get(URL));
           ds.setUser(PropertiesUtil.get(USERNAME));
           ds.setPassword(PropertiesUtil.get(PASSWORD));
           return ds.getConnection();
       }catch(Throwable throwable){
           System.out.println(throwable.getMessage());
           throw new RuntimeException(throwable.getMessage());
       }
   }

}
