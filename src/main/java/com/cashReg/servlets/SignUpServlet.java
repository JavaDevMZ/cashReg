package com.cashReg.servlets;

import com.cashReg.CashRegister;
import com.cashReg.conrollers.UserController;
import com.cashReg.models.Role;
import com.cashReg.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    UserController userController = CashRegister.getInstance().getUserController();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 User user = userController.createUser(req.getParameter("username"), req.getParameter("password"), Role.valueOf(req.getParameter("role")));
        userController.setUser(user);
        System.out.println("Post is done");
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get of signup servlet");
        req.setAttribute("Welcome", "Welcome!!! ");
        RequestDispatcher dispatcher = req.getRequestDispatcher("webapp/signUp.jsp");
        dispatcher.forward(req, resp);
    }
}
