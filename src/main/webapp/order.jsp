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
    <style><%@include file="/WEB-INF/assets/css/root.css"%></style>
</head>
<body>

        <%
        CashierController cashierController = new CashierController();
        Cashier cashier = (Cashier) CashRegister.getInstance().getCurrentUser();
        %>

    <form class="form flex flexDir-c gap-5" >
        <label for="email">Email</label>
            <input id="email"
                   class="input"
                   name="customer_email"
                   formmethod="post"
                   value="<%cashierController.getCurrentOrder().getCustomerEmail();%>"
            >

        <label for="product">
            <fmt:message key="product_name/id"/>
        </label>
        <input class="input" type="text" name="product" id="product">

        <label for="quantity">
            <fmt:message key="quantity"/>
        </label>
        <input type="number"
               class="input"
               name="quantity"
               id="quantity"
        >

        <button class="btn" type="submit" formmethod="post">OK</button>

    </form>
        <div class="flex flexDir-c gap-5">
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
                <button class="btn"
                        type="submit"
                        formmethod="post"
                        name="remove"
                        value="<%=k.getId()%>">
                </button>

            </form>
            <%
                    }
                }
            }else{%>
            <h3 style="color: white"> <fmt:message key="empty_order"/></h3>
            <% } %>
        </div>


<form>
    <button class="btn p-10-15" type="submit" name="close" formmethod="post" value="true">
        <fmt:message key="close_order"/>
    </button>
</form>
    <% if(cashier.getRole()== Role.SENIOR_CASHIER){%>
    <form>
        <button class="btn" name="cancel" value="true" type="submit" formmethod="post">
            <fmt:message key="cancel_order"/>
        </button>
    </form>
<% } %>
</body>
</html>
