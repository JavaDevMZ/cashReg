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
<fmt:setLocale value="<%=locale%>"/>

<html>
<head>
    <title><fmt:message key="login"/></title>
    <style><%@include file="/WEB-INF/assets/css/root.css"%></style>
    <style><%@include file="/WEB-INF/assets/css/login.css"%></style>
</head>
<body>

<%--    <button class="btn" onclick="history.back()"><--- Back</button>--%>
    <div class="wrapper" style="">
        <img src="https://www.freeiconspng.com/download/41942"
             style="rotate: -90deg; cursor: pointer"
             height="50"
             width="50"
             onclick="history.back()"
             alt="back"
             class="backBtn"
        >

        <form method="post" class="form flex flexDir-c gap-5">
            <label for="username"> <fmt:message key="username"/></label>
            <input class="input" type="text" id="username" name="username">
            <label for="password"> <fmt:message key="password"/></label>
            <input class="input" type="password" id="password" name="password">

            <button class="btn p-10-15" type="submit" formmethod="post"><fmt:message key="login"/></button>
        </form>
    </div>

    <% if (LoginServlet.isWrongCredentials()) {
        out.print("<h1 color='red'>" +
                "<fmt:message key=\"wrong_credentials\"/>" +
                "<h1>");
    }%>

</body>
</html>
