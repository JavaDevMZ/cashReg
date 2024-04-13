package com.cashReg.util;

import com.cashReg.models.User;

public final class UserUtil {


    public static User createUser(String username, String password, String role){
        try {
            User user = (User) Class.forName(role).getConstructor().newInstance(username, password);
            return user;
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public static User createUser(String username, String password, Role role){
      return createUser(username, password, role.toString());
    }

    public static User createUser(String username, String password, int roleId){
       return createUser(username, password, Role.getByOrdinal(roleId));//counting starts by 1 in SQL
    }
}
