<%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 18/04/2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.cashReg.CashRegister" %>
<%--@ page errorPage="order.jsp"--%>
<%@ page import="com.cashReg.models.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cashReg.models.Cashier" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.cashReg.controllers.CashierController" %>
<%@ page import="com.cashReg.util.Role" %>

<html>
<head>
    <title> <fmt:message key="new_order"/></title>
</head>
<body>

        <%
        CashierController cashierController = new CashierController();
        Cashier cashier = (Cashier) CashRegister.getInstance().getCurrentUser();
        %>
        <form>
    <input name="customer_email" formethod="post" value="<%out.print(cashierController.getCurrentOrder().getCustomerEmail());%>">
        </form>

    <form>

        <label for="product"> <fmt:message key="product_name/id"/></label><input type="text" name="product" id="product">

        <label for="quantity"> <fmt:message key="quantity"/></label><input type="number" name="quantity" id="quantity">

        <button type="submit" formmethod="post">OK</button>

    </form>

       <%

              Map<OrderItem, Long> orderItems = cashierController.getCurrentOrder().getItems();

             if(!orderItems.isEmpty()){
                 boolean canRemove = cashier.getRole()==Role.SENIOR_CASHIER;
                 for (Map.Entry<OrderItem, Long> entry : orderItems.entrySet()) {
                     OrderItem k = entry.getKey();
                     Long v = entry.getValue();
                     out.println(("<li>" +
                             String.format(k.toString(), v, (v*k.getPrice())) +
                             "</li>"));
                     if(canRemove){ %>
        <form>
        <button type="submit" formmethod="post" name="remove" value="<%=k.getId()%>"></button>
        </form>
            <%
                        }
                 }
             }else{%>
               <h3> <fmt:message key="empty_order"/></h3>
          <% } %>

<form>
    <button type="submit" name="close" formmethod="post" value="true">
        <fmt:message key="close_order"/>
    </button>
</form>
    <% if(cashier.getRole()== Role.SENIOR_CASHIER){%>
    <form>
        <button name="cancel" value="true" type="submit" formmethod="post">
            <fmt:message key="cancel_order"/>
        </button>
    </form>
<% } %>
</body>
</html>
