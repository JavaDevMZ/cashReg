package com.cashReg.util.sql;

import com.cashReg.util.dao.DataSource;
import com.cashReg.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SQLExecutor extends AbstractExecutor {

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

}
