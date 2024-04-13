package com.cashReg.conrollers;

import com.cashReg.CashRegister;
import com.cashReg.models.User;

import java.util.List;

public class UserController {
    private User user;

    private CashRegister cashRegister = CashRegister.getInstance();
    private List<User> users = cashRegister.getUsers();

    public User getUser() {
        return user;
    }

    public void signUp(User user){
        if(users.contains(user)){
            throw new IllegalArgumentException("User is already registered!");
        }
        users.add(user);
        login(user);

    }

    public void login(User user) {
        if(!users.contains(user)) {
       throw new IllegalArgumentException("User is not registered!");
        }
        cashRegister.setCurrentUser(user);
    }
}
