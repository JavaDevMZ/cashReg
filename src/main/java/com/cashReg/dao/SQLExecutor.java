package com.cashReg.dao;

import com.cashReg.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLExecutor {

      private DataSource dataSource = new DataSource();
      private Connection connection = dataSource.getConnection();

      public int insertUser(User user){
          try {
              String query = "INSERT INTO User(username, password) VALUES(%s, %s)";
              PreparedStatement statement = connection.prepareStatement(String.format(query, user.getUsername(), user.getPassword()));
              statement.execute();
              ResultSet resultSet = statement.getGeneratedKeys();
              int id = (int) resultSet.getLong("id");

              statement.close();
              resultSet.close();
              return id;
          }catch(Exception e){
              throw new RuntimeException(e.getMessage());
          }
          }
}
