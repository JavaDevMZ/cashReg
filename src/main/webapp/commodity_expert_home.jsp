<%@ page import="com.cashReg.CashRegister" %>
<%@ page import="com.cashReg.controllers.CommodityExpertController" %>
<%@ page import="com.cashReg.models.Product" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 14/04/2024
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message key="cExpert"/></title>
</head>
<body>
        <% CommodityExpertController controller = new CommodityExpertController();%>
    <form>
        <label for="name"><fmt:message key="product_name"/></label> <input id="name" name="name" datatype="text">
        <label for="price"><fmt:message key="price"/></label> <input id="price" name="price" datatype="number">
        <label for="quantity"><fmt:message key="quantity"/></label> <input  id="quantity" name="quantity" datatype="number">
        <button name="create_product" type="submit" formmethod="post" value="true"><fmt:message key="new_product"/></button>
    </form>

        <%
            Map<Product, Long> products = controller.getProducts();
            if(products!=null && products.size()>0) {
                for (Product product : products.keySet()) {%>
                  <form>
                    <%=product.toString()%>
        <fmt:message key="quantity"/>
            <input name="new_quantity" type="number" value=<%=products.get(product)%>>
            <button name="change_quantity" type="submit" formmethod="post" value="true">OK</button> </br>
                        </form>
         <%
                 }
            }
        %>

</body>
</html>