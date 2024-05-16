package com.cashReg.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends InternationalizableImpl {

    public static boolean isUserAlreadyExists() {
        return userAlreadyExists;
    }

    private static boolean userAlreadyExists = false;

    UserController userController = new UserController();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(changeLanguageIfNeeded(req)){
            doGet(req, resp);
        }
     String username = req.getParameter("username");
     String password = req.getParameter("password");
     String role = req.getParameter("role");
    try {
    userController.signUp(username, password, role);
    resp.sendRedirect(userController.getUserPage());
    }catch(IllegalArgumentException e){
   userAlreadyExists =true;
   doGet(req, resp);
    }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
        dispatcher.forward(req, resp);

    }
}
