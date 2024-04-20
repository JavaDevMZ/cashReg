package com.cashReg.servlets;

import com.cashReg.conrollers.UserController;
import com.cashReg.util.UserUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static boolean alreadyInvoked=false;
    UserController userController = new UserController();

    public static boolean isAlreadyInvoked() {
        return alreadyInvoked;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       try {
         userController.login(username, password);
       }catch(IllegalAccessException e){
         doGet(request, response);
       }
      response.sendRedirect(UserUtil.getUserPage());
    }
}
