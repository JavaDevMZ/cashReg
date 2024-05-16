<%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 14/04/2024
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="messages"/>
<fmt:setLocale value= "<%=locale%>" />

<html>
<head>
    <title><fmt:message key="cashier"/></title>
</head>
<body>
        <form>
        <button type="submit" name="create_order" formmethod="post" value="create_order"><fmt:message key="new_order"/></button>
        </form>
</body>
</html>
