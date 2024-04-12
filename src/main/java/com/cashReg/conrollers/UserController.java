package com.cashReg.conrollers;

import com.cashReg.CashRegister;
import com.cashReg.models.Role;
import com.cashReg.models.User;

import java.util.List;

public class UserController {
    private User user;
    CashRegister cashRegister = CashRegister.getInstance();
    List<User> users = cashRegister.getUsers();
    public User getUser() {
        return user;
    }

    public User createUser(String username, String password, Role role){
        try {

            User user = (User) Class.forName(role.toString()).getConstructor().newInstance(username, password);
            if (!users.contains(user)){
            users.add(user);
            cashRegister.getSqlExecutor().insertUser(user);
        }
            setUser(user);
            return user;
    }catch(Exception e){
        throw new IllegalArgumentException(e.getMessage());
            }
    }
    public void setUser(User user) {
        if(users.contains(user)) {
            this.user = user;
        }else{
            throw new IllegalArgumentException("User doesn't exist");
        }
        System.out.println(user.getUsername()+"is the current user");
    }
}
