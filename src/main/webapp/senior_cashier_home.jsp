<%@ page import="com.cashReg.CashRegister" %><%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 14/04/2024
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="cashier_home.jsp"%>
<body>
<form>
    <button type="submit" name="x_report" value="true" formmethod="post">
        <fmt:message key="x_report"/>
    </button>
</form>

<form>
    <button type="submit" name="z_report" value="true" formmethod="post">
        <fmt:message key="z_report"/>
    </button>
</form>

<%  if(request.getAttribute("x_report")!=null&&!request.getAttribute("x_report").equals("")){
    out.println(request.getAttribute("x_report"));
        }

    if(request.getAttribute("z_report")!=null&&!request.getAttribute("z_report").equals("")){
        out.println(request.getAttribute("z_report"));
        }
%>

</body>