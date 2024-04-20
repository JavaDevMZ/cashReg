<%@ page import="com.cashReg.servlets.LoginServlet" %><%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 29/03/2024
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
        <form method="post">
            <label for="username">Username</label>
        <input type="text" id="username" name="username">
            <label for="password">Password</label>
        <input type="password" id="password" name="password">

        <button type="submit" formmethod="post" >Login</button>
    </form>

    <% if(LoginServlet.isAlreadyInvoked()){
        out.print("<h1 color='red'>Wrong username or password!<h1> <h2>Try again.</h2>");
}%>
</body>
</html>
