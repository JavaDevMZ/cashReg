<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.cashReg.CashRegister" %>
<%@ page import="com.cashReg.Initializer" %>
<html lang="en">
<body>

<% Initializer.initializeAll(); %>
<h2>Hello World!</h2>
    <div>
<button onclick = "location.href='/sign-up'">
    Sign up
</button>
    </div>
    <div>
<button onclick = "location.href='/login'">
    Log in
</button>
    </div>
</body>
</html>
