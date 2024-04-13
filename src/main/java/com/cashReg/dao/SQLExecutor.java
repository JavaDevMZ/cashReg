package com.cashReg.dao;

import com.cashReg.Warehouse;
import com.cashReg.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cashReg.util.SQLList;
import com.cashReg.util.UserUtil;

public class SQLExecutor {

    private final static String SELECT_ALL = "SELECT * FROM %s";
    private final static String INSERT = "INSERT INTO %s VALUES (%s)";
    private final static String UPDATE = "UPDATE %s SET(%s) WHERE %s";

      private static DataSource dataSource = new DataSource();
      private static Connection connection = dataSource.getConnection();

      public long insertObject(Object object){
          if(object instanceof User){
              return insertUser((User)object);
          }else if(object instanceof Order){
              return insertOrder((Order)object);
          }else if(object instanceof Product) {
              return insertProduct((Product)object);
          }else{
              throw new IllegalArgumentException();
          }
      }

      public long insertUser(User user){
          try {
              String query = String.format(INSERT, "User(username, password, role_id)", "%s, %s %d");

              PreparedStatement statement = connection.prepareStatement(String.format(
                      query, user.getUsername(), user.getPassword(), user.getRole().getOrdinal()));
              statement.execute();
              ResultSet resultSet = statement.getGeneratedKeys();
              long id = resultSet.getLong("id");
              user.setId(id);

              return id;
          }catch(Exception e){
              throw new RuntimeException(e.getMessage());
          }
      }

      public List<User> getAllUsers(){

          try(ResultSet resultSet = executeSelect(SELECT_ALL+"user")) {
              List<User> result = new ArrayList<>();
              while (resultSet.next()) {
                 long id = resultSet.getLong(1);
                 String username = resultSet.getString(2);
                 String password = resultSet.getString(3);
                 int roleId = resultSet.getInt(4);
                  result.add(UserUtil.createUser(username, password, roleId));
              }
              return new SQLList<>(result);
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }

      }

      public List<Product> getAllProducts(){
            List<Product> result = new ArrayList<>();
          try(ResultSet resultSet = executeSelect(SELECT_ALL+"product")) {
              while (resultSet.next()) {
                  long id = resultSet.getLong(1);
                  String name = resultSet.getString(2);
                  float price = resultSet.getFloat(3);
                  long quantity = resultSet.getLong(4);
                  result.add(new Product(id, name, price, quantity));
              }
              return new SQLList<>(result);
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }
      }

      public List<Order> getAllOrders(){
            Warehouse warehouse = Warehouse.getInstance();
          try(ResultSet resultSet = executeSelect(SELECT_ALL+"order")){
           List<Order> result = new ArrayList<>();
            while(resultSet.next()){
                long orderId = resultSet.getLong(1);
                long customerId = resultSet.getLong(2);
                float amount = resultSet.getFloat(3);
                List<OrderItem> products = new ArrayList<>();
                ResultSet orderItems = executeSelect(SELECT_ALL+"order_item WHERE order_id = " + orderId);
                Order order = new Order();
                    while(orderItems.next()){
                        long itemId = orderItems.getLong(1);
                        long productId = orderItems.getLong(2);
                        long quantity = orderItems.getLong(4);
                        Product product = Warehouse.getInstance().getProduct(productId);
                        OrderItem item = new OrderItem(product, orderId, quantity);
                        item.setId(itemId);
                        order.addProduct(product, quantity);
                    }
                    order.setItems(new SQLList<>(products));
                    order.setClosed();
                result.add(order);
            }
            return new SQLList<>(result);
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }
      }

      public long insertOrder(Order order){
            long customerId = order.getCustomerId();
            float amount = order.getAmount();
            for(OrderItem item : order.getItems()){
                insertOrderItem(item);
            }
            String query = String.format(INSERT, "order(customer_id, amount)", "%d, %f");
            try{
                ResultSet resultSet = execute(String.format(query, customerId, amount));
                resultSet.next();
                long id = resultSet.getLong(1);
                order.setId(id);
                return id;
            }catch(SQLException sqlE){
                throw new RuntimeException(sqlE.getMessage());
            }
          }

      public long insertOrderItem(OrderItem item){
          long id;
          long orderId = item.getOrderId();
          long productId = item.getProductId();
          long quantity = item.getQuantity();

          String query = String.format(INSERT, "order_item(order_id, product_id, quantity)", "%d, %d");
          try{
              ResultSet resultSet = execute(String.format(query, orderId, productId, quantity));
              resultSet.next();
              id = resultSet.getLong(1);
              item.setId(id);
              return id;
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }
      }

      public long insertProduct(Product product){
          String name = product.getName();
          float price = product.getPrice();
          long quantity = product.getQuantity();
          String query = String.format(INSERT, "product(name, price, quantity)", "%s, %f, %d");
          try{
            ResultSet resultSet = execute(String.format(query, name, price, quantity));
            resultSet.next();
                long id =resultSet.getLong(1);
                product.setId(id);
                return id;
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }
      }

      public void updateProductQty(long productId, long quantity){
          String query = String.format(UPDATE, "product(quantity)", "%d", "id = %d");
         try {
             execute(String.format(query, quantity, productId));
         }catch(SQLException sqlE){
             throw new RuntimeException(sqlE.getMessage());
         }
      }

      public void updateOrderItemQuantity(long orderId, long productId, long quantity) {
          String query = String.format(UPDATE, "order_item(quantity)", "%d", "order_id=%d AND product_id=%d");
          try{
              execute(String.format(query, quantity, orderId, productId));
          }catch(SQLException sqlE){
              throw new RuntimeException(sqlE.getMessage());
          }
      }

      public ResultSet executeSelect(String query) throws SQLException{
          PreparedStatement statement = connection.prepareStatement(query);
              return statement.executeQuery();
          }

    /**Executes any non-select query
     * returns what has been inserted
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet execute(String query) throws SQLException{
           PreparedStatement statement = connection.prepareStatement(query);
           statement.execute();
           return statement.getGeneratedKeys();
      }
}
