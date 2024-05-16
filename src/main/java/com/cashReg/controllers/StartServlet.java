package com.cashReg.controllers;

import com.cashReg.Initializer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/")
public class StartServlet extends InternationalizableImpl {

    @Override
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      resp.setCharacterEncoding("ISO-8859-5");
        /*  try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }catch(Throwable e){
       e.printStackTrace();
            System.out.println("Throwable caused by requestDispatcher");
            }*/
       // System.out.println(ResourceBundle.getBundle("messages").getString("hello"));
      Initializer.initializeAll();
        RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(changeLanguageIfNeeded(req)){
    doGet(req, resp);
}
        System.out.println("post was executed");
    }
}