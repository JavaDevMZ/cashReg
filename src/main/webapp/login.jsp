<%--
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

        <button type="submit" formmethod="post">Login</button>
    </form>
</body>
</html>
