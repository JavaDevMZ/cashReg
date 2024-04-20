package com.cashReg.util.sql;

import com.cashReg.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Inserter extends SQLExecutor {

    public long insertUser(User user){
        try {
            String query = String.format(INSERT, "\"user\"(username, password, role_id)", "'%s', '%s', %d");

            ResultSet resultSet = execute(String.format(
                    query, user.getUsername(), user.getPassword(), user.getRole().getOrdinal()));
             long id = resultSet.getLong(1);
            user.setId(id);

            return id;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public long insertOrder(Order order){
        long customerId = order.getCustomerId();
        float amount = order.getAmount();
        Map<OrderItem, Long> items = order.getItems();
        for(OrderItem item : items.keySet()){
            insertOrderItem(item, items.get(item));
        }
        String query = String.format(INSERT, "\"order\"(customer_id, amount)", "%d, %f");
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

    public long insertOrderItem(OrderItem item, long quantity){
        long id;
        long orderId = item.getOrderId();
        long productId = item.getProductId();

        String query = String.format(INSERT, "\"order_item\"(order_id, product_id, quantity)", "%d, %d, %d");
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

    public long insertProduct(Product product, long quantity){
        String name = product.getName();
        float price = product.getPrice();

        String query = String.format(INSERT, "\"product\"(name, price, quantity)", "'%s', %f, %d");
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
}
