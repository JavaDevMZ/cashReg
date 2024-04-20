<%@ page import="com.cashReg.servlets.SignUpServlet" %><%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 27/03/2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
     <form name="sign up" method="POST" id="sign_up_form">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
        <label for="password">Password:</label>
        <input type="password" id="password" aria-label="Password:" name="password">
        <select  name="role">
            Choose your role
            <option datatype="text" value="Cashier">Cashier</option>
            <option datatype="text" value="SeniorCashier">Senior Cashier</option>
            <option datatype="text" value="CommodityExpert">Commodity Expert</option>
        </select>
    </form>
   <button form="sign_up_form" formmethod="POST" type="submit" >Submit</button>
     <% if(SignUpServlet.isAlreadyInvoked()){
         out.print("<h1 color='red'>Wrong username or password!<h1> <h2>Try again.</h2>");
     }%>
</body>
</html>
