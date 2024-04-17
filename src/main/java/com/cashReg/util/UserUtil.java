package com.cashReg.util;

import com.cashReg.CashRegister;
import com.cashReg.models.Cashier;
import com.cashReg.models.User;

public final class UserUtil {

    private static CashRegister cashRegister = CashRegister.getInstance();

    private UserUtil(){}

    public static User createUser(String username, String password, String role){

        try {
            User user = (User) Class.forName("com.cashReg.models."+role).getConstructor(String.class, String.class).newInstance(username, password);
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public static User createUser(String username, String password, Role role){
      return createUser(username, password, role.toString());
    }

    public static User createUser(String username, String password, int roleId){
       return createUser(username, password, Role.getByOrdinal(roleId));
       //numeration starts by 1 in SQL
    }

    public static User getUserByName(String name){
      User result = null;
       for(User user : cashRegister.getUsers()){
           if(user.getUsername().equals(name)){
               result = user;
           }
       }
       return result;
    }
}
