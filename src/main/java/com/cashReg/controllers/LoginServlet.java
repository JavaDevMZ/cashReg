package com.cashReg.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends InternationalizableImpl {

    private static boolean wrongCredentials =false;
    UserController userController = new UserController();

    public static boolean isWrongCredentials() {
        return wrongCredentials;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(changeLanguageIfNeeded(req)){
doGet(req, resp);
return;
}
        String username = req.getParameter("username");
        String password = req.getParameter("password");
       try {
         userController.login(username, password);
       }catch(IllegalAccessException e){
         doGet(req, resp);
       }
      resp.sendRedirect(userController.getUserPage());
    }
}
