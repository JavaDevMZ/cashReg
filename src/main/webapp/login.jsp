<%@ page import="com.cashReg.controllers.LoginServlet" %>
<%@ page import="com.cashReg.CashRegister" %><%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 29/03/2024
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="messages"/>
<fmt:setLocale value= "<%=locale%>" />

<html>
<head>
    <title><fmt:message key="login"/></title>
</head>
<body>
        <form method="post">
            <label for="username"> <fmt:message key="username"/></label>
        <input type="text" id="username" name="username">
            <label for="password"> <fmt:message key="password"/></label>
        <input type="password" id="password" name="password">

        <button type="submit" formmethod="post" > <fmt:message key="login"/></button>
    </form>

    <% if(LoginServlet.isWrongCredentials()){
        out.print("<h1 color='red'>" +
                "<fmt:message key=\"wrong_credentials\"/>" +
                "<h1>");
}%>
</body>
</html>
