<%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 18/04/2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.cashReg.CashRegister" %>
<%@ page import="com.cashReg.models.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cashReg.models.Cashier" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.IOException" %>
<html>
<head>
    <title>New order</title>
</head>
<body>

        <% Cashier cashier = (Cashier) CashRegister.getInstance().getCurrentUser(); %>
    <form id="order_field">

        <label for="product">Product name/id</label><input type="text" name="product" id="product">

        <label for="quantity">Quantity</label><input type="number" name="quantity" id="quantity">

        <button type="submit" formmethod="post">Add to the order</button>
    </form>

    <ul>
       <%
           try{
               Map<OrderItem, Long> orderItems = cashier.getOrder().getItems();
               for (Map.Entry<OrderItem, Long> entry : orderItems.entrySet()) {
                   OrderItem k = entry.getKey();
                   Long v = entry.getValue();
                   out.print(("<li>" +
                           String.format(k.toString(), v, v * k.getPrice()) +
                           "</li>"));
               }
           }catch(NullPointerException e) {}
       %>
    </ul>
</body>
</html>
