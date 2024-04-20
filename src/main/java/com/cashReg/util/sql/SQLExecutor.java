package com.cashReg.util.sql;

import com.cashReg.util.dao.DataSource;
import com.cashReg.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SQLExecutor {

    protected final static String SELECT_ALL = "SELECT * FROM ";
    protected final static String UPDATE = "UPDATE \"%s\" SET(%s) WHERE %s";
    protected final static String INSERT = "INSERT INTO \"%s\"(%s)";

    private static Connection connection;

    static {
        try {
            connection = DataSource.getConnection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private Selector selector = new Selector();
    private Inserter inserter = new Inserter();
    private Updater updater = new Updater();

    public List<User> getAllUsers(){
       return selector.getAllUsers();
      }

      public Map<Product, Long> getAllProducts(){
           return selector.getAllProducts();
      }

      public List<Order> getAllOrders(){
           return selector.getAllOrders();
      }

    public long insertModel(Model object){
        if(object instanceof User){
            return insertUser((User)object);
        }else if(object instanceof Order){
            return insertOrder((Order)object);
        }else{
            throw new IllegalArgumentException();
        }
    }
      public long insertHasQuantity(HasQuantity hasQuantity, long quantity){
        if(hasQuantity.getClass()== Product.class){
            return insertProduct((Product) hasQuantity, quantity);
        }else{
            throw new IllegalArgumentException("Doesn't have a quantity");
        }
      }

      public long insertUser(User user){
        return inserter.insertUser(user);
      }

      public long insertProduct(Product product, long quantity){
        return inserter.insertProduct(product, quantity);
      }

      public long insertOrder(Order order){
        return inserter.insertOrder(order);
      }

      public void updateProductQty(long productId, long quantity){
       updater.updateProductQty(productId, quantity);
      }

      public void updateOrderItemQuantity(long orderId, long productId, long quantity) {
       updater.updateOrderItemQuantity(orderId, productId, quantity);
      }

    /**
     * Executes a query which returns a selection
     * @param query
     * @return the selection retrieved
     * @throws SQLException
     */
    public ResultSet executeSelect(String query) throws SQLException{
          PreparedStatement statement = connection.prepareStatement(query);
              return statement.executeQuery();
          }

    /**
     * Executes any non-select query
     * returns what has been inserted
     * @param query
     * @return the rows inserted
     * @throws SQLException
     */
    public ResultSet execute(String query) throws SQLException{
           PreparedStatement statement = connection.prepareStatement(query);
           statement.execute();
           return statement.getGeneratedKeys();
      }
}
