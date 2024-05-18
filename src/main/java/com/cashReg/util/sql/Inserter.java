package com.cashReg.util.sql;

import com.cashReg.models.*;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class Inserter extends AbstractExecutor {

    private static final Logger log = Logger.getLogger(Inserter.class);

    public long insertUser(User user){
        try {
            long id = -1;
            String query = String.format(INSERT, "\"user\"(username, password, role_id)", "'%s', '%s', %d");

            ResultSet resultSet = execute(String.format(
                    query, user.getUsername(), user.getPassword(), user.getRole().getOrdinal()));

            if(resultSet.next()) {
                id = resultSet.getLong(1);
                user.setId(id);
            }
            return id;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public long insertOrder(Order order){
        String customerEmail = order.getCustomerEmail();
        float amount = order.getAmount();
        Map<OrderItem, Long> items = order.getItems();
        long cashierId = order.getCashierId();
        LocalDate date = order.getDate();
        long id = 0;

        String query = String.format(INSERT, "\"order\"(amount, customer_email, cashier_id, date)", "%f, %s, %d, %s");
        log.info("insertOrder: " + String.format(query, amount, escapeStr(customerEmail), cashierId, escapeStr(date.toString())));
        try{
            ResultSet resultSet = execute(String.format(query, amount, escapeStr(customerEmail), cashierId, escapeStr(date.toString())));

            if(resultSet!=null&&resultSet.next()) {
                id = resultSet.getLong(1);
                order.setId(id);

            }else{
                throw new RuntimeException();
            }
            for(OrderItem item : items.keySet()){
                item.setOrderId(id);
                insertOrderItem(item, items.get(item));
            }
        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }
        return id;
    }

    private static String escapeStr(String str) {
        return "'" + str + "'";
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
