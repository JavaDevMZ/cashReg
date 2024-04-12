package com.cashReg;

import java.util.ArrayList;
import java.util.List;

import com.cashReg.dao.SQLExecutor;
import com.cashReg.models.Order;
import com.cashReg.models.User;
import com.cashReg.models.Warehouse;
import com.cashReg.conrollers.UserController;

public class CashRegister {

        private List<User> users;
        private Warehouse warehouse;
        private List<Order> orders;
        private UserController userController;
        private final SQLExecutor SQL_EXECUTOR = new SQLExecutor();
        private static CashRegister instance;

        public static CashRegister getInstance() {
                if (instance == null) {
                        instance = new CashRegister(new ArrayList<User>(), new Warehouse(), new ArrayList<>());
                }
                return instance;
        }
        private CashRegister(List<User> users, Warehouse warehouse, List<Order> orders) {
                this.users = users;
                this.warehouse = warehouse;
                this.orders = orders;
        }


        public List<User> getUsers() {
                return users;
        }

        public void setUsers(List<User> users) {
                this.users = users;
        }

        public Warehouse getWarehouse() {
                return warehouse;
        }

        public void setWarehouse(Warehouse warehouse) {
                this.warehouse = warehouse;
        }

        public List<Order> getOrders() {
                return orders;
        }

        public void setOrders(List<Order> orders) {
                this.orders = orders;
        }

        public UserController getUserController() {
                return userController;
        }

        public void setUserController(UserController userController) {
                this.userController = userController;
        }
        public SQLExecutor getSqlExecutor() {
                return SQL_EXECUTOR;
        }
}

