<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.cashReg.CashRegister" %>
<%@ page import="com.cashReg.Initializer" %>

<html>

<head>
    <style><%@include file="/WEB-INF/assets/css/root.css"%></style>
</head>
<body>

<div class="flex flexDir-c gap-5">

    <button class="btn p-15-85" onclick = "location.href='/sign-up'">
       <fmt:message key="sign_up"/>
    </button>
    <button class="btn p-15-85" onclick = "location.href='/login'">
        <fmt:message key="login"/>
    </button>
</div>
</body>

</html>

