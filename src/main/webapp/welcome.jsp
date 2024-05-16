<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.cashReg.CashRegister" %>
<%@ page import="com.cashReg.Initializer" %>

<html>

<head> <fmt:message key="hello"/> </head>
<body>

<div>
    <button onclick = "location.href='/sign-up'">
       <fmt:message key="sign_up"/>
    </button>
</div>
<div>
    <button onclick = "location.href='/login'">
        <fmt:message key="login"/>
    </button>
</div>
</body>

</html>

