package com.cashReg.util.sql;

import com.cashReg.Warehouse;
import com.cashReg.controllers.OrderController;
import com.cashReg.util.dao.DataSource;
import com.cashReg.models.Order;
import com.cashReg.models.OrderItem;
import com.cashReg.models.Product;
import com.cashReg.models.User;
import com.cashReg.util.SQLList;
import com.cashReg.util.SQLMap;
import com.cashReg.util.UserUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Selector extends AbstractExecutor {

    private static final Logger log = Logger.getLogger(Selector.class);

    static {
        try {
            connection = DataSource.getConnection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){

        try(ResultSet resultSet = executeSelect(SELECT_ALL + "\"user\"")) {
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int roleId = resultSet.getInt(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);

                User user = UserUtil.createUser(username, password, roleId);
                user.setId(id);
                result.add(user);
            }
            return new SQLList<>(result);
        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }

    }

    public Map<Product, Long> getAllProducts(){
        Map<Product, Long> result = new HashMap<>();
        try(ResultSet resultSet = executeSelect(SELECT_ALL+"\"product\"")) {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                long quantity = resultSet.getLong(4);
                result.put(new Product(id, name, price), quantity);
            }
            return new SQLMap<>(result);
        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }
    }

    public List<Order> getAllOrders(){
        Warehouse warehouse = Warehouse.getInstance();
        try(ResultSet resultSet = executeSelect("select o.id, o.customer_id, o.amount, o.customer_email, o.cashier_id, o.date from \"order\" o")){
            List<Order> result = new ArrayList<>();
            while(resultSet.next()){
                long orderId = resultSet.getLong(1);
                float amount = resultSet.getFloat(3);
                String customerEmail = resultSet.getString(4);
                long cashierId = resultSet.getLong(5);
//                log.info("order date: " +resultSet.getString(5));
//                log.info("test: " + resultSet.getLong(1));
                LocalDate date = (LocalDate) (resultSet.getObject(6, LocalDate.class));

                Map<OrderItem, Long> products = new HashMap<>();
                Order order = new Order();
                order.setId(orderId);
                order.setCustomerEmail(customerEmail);
                order.setAmount(amount);
                order.setCashierId(cashierId);
                order.setDate(date);

                ResultSet orderItems = executeSelect(
                        SELECT_ALL+"\"order_item\" WHERE order_id = " + orderId
                );
                while(orderItems.next()){
                    long itemId = orderItems.getLong(1);
                    long productId = orderItems.getLong(3);
                    long quantity = orderItems.getLong(4);
                    Product product = Warehouse.getInstance().getProduct(productId);
                    OrderItem item = new OrderItem(product, orderId);
                    item.setId(itemId);
                    order.addProduct(product, quantity);
                }
                order.setItems(new HashMap<>(products));
                order.setClosed();
                result.add(order);
            }
            return new SQLList<>(result);
        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }
    }
}
