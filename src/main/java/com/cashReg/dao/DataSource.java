package com.cashReg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static volatile Connection connection;

    private static final String URL = "db.URL";

    private static final String USERNAME = "db.USERNAME";

    private static final String PASSWORD = "db.PASSWORD";

    static{
        try {
            connection = DriverManager.getConnection(PropertiesUtil.get(URL), PropertiesUtil.get(USERNAME), PropertiesUtil.get(PASSWORD));
        }catch(SQLException sqle){
            throw new RuntimeException(sqle.getMessage());
        }
    }
   public Connection getConnection(){
        return connection;
   }

}
