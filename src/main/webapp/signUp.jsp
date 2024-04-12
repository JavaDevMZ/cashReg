<%--
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
     <form name="sign up" method="post" id="sign_up_form">
        <label for="username">Username:</label>
        <input type="text" id="username" name="">
        <label for="password">Password:</label>
        <input type="password" id="password" aria-label="Password:" name="password">
        <select  name="role">
            Choose your role
            <option value=0>Cashier</option>
            <option value=1>Senior Cashier</option>
            <option value=2>Commodity Expert</option>
        </select>


    </form>
   <button form="sign_up_form" formmethod="post" type="submit" >Submit</button>
     <% out.println("<p>"+request.getAttribute("Welcome")+"</p>"); %>
</body>
</html>
