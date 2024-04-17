package com.cashReg.conrollers;

import com.cashReg.CashRegister;
import com.cashReg.models.User;
import com.cashReg.util.UserUtil;

import java.util.List;

public class UserController {
    private User user;

    private CashRegister cashRegister = CashRegister.getInstance();
    private List<User> users = cashRegister.getUsers();

    public User getUser() {
        return user;
    }

    public void signUp(String username, String password, String role){
        signUp(UserUtil.createUser(username, password, role));
    }
    public void signUp(User user){
        if(users.contains(user)){
            throw new IllegalArgumentException("User is already registered!");
        }
        users.add(user);
      cashRegister.setCurrentUser(user);
    }

    public User login(String username, String password) throws IllegalAccessException{
     User user = UserUtil.getUserByName(username);
if(password.equals(user.getPassword())){
    cashRegister.setCurrentUser(user);
     return user;
        }else{
    throw new IllegalAccessException("Wrong password!");
        }
    }

}
