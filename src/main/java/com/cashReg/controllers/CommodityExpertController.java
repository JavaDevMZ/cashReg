package com.cashReg.controllers;

import com.cashReg.CashRegister;
import com.cashReg.Warehouse;
import com.cashReg.models.CommodityExpert;
import com.cashReg.models.Product;
import com.cashReg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/commodity_expert")
public class CommodityExpertController extends InternationalizableImpl{

    private CommodityExpert commodityExpert;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("commodity_expert_home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(changeLanguageIfNeeded(req)){
            doGet(req, resp);
        }
        if("true".equals(req.getParameter("create_product"))) {
            String productName = req.getParameter("name");
            String quantity = req.getParameter("quantity");
            String price = req.getParameter("price");
            createProduct(productName, Float.parseFloat(price), Long.parseLong(quantity));
        }else if("true".equals(req.getParameter("change_quantity"))) {
            Long changeQtyId = Long.parseLong(req.getParameter("set_qty_id"));
            String quantity = req.getParameter("new_quantity");
            setQuantity(changeQtyId, Long.parseLong(quantity));
        }
            doGet(req, resp);
    }

    public CommodityExpertController(){
     User currentUser = CashRegister.getInstance().getCurrentUser();
       if(currentUser instanceof CommodityExpert) {
           commodityExpert = (CommodityExpert)currentUser;
       }
    }

    public void createProduct(String name, float price, long quantity){
        commodityExpert.createProduct(name, price, quantity);
    }

    public Map<Product, Long> getProducts(){
        return Warehouse.getInstance().getProducts();
    }

    public void setQuantity(long id, long quantity){
            commodityExpert.setQuantity(id, quantity);
        }
}
