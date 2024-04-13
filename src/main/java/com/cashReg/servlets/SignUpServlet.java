package com.cashReg.servlets;

import com.cashReg.conrollers.UserController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet{

    private UserController userController = new UserController();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String username = req.getParameter("username");
     String password = req.getParameter("password");
     String role = req.getParameter("role");

     userController.createUser(username, password, role);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
        dispatcher.forward(req, resp);
    }
}
