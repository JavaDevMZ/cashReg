<%@ page import="com.cashReg.controllers.SignUpServlet" %>
<%@ page import="com.cashReg.Initializer" %>
<%--
  Created by IntelliJ IDEA.
  User: maksimzelinskyi
  Date: 27/03/2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%Initializer.initializeAll();%>
<head>
    <title><fmt:message key="sign_up"/></title>
    <style><%@include file="/WEB-INF/assets/css/root.css"%></style>
</head>
<body>
     <form name="sign up"
           style="color: white; position: relative"
           class="form flex flexDir-c gap-5"
           method="POST"
           id="sign_up_form"
     >

         <img src="https://www.freeiconspng.com/download/41942"
              style="rotate: -90deg; cursor: pointer"
              height="50"
              width="50"
              onclick="history.back()"
              alt="back"
              class="backBtn"
         >
        <label for="username">
            <fmt:message key="username"/>
        </label>

        <input class="input"
               type="text"
               id="username"
               name="username"
        >

        <label for="password">
            <fmt:message key="password"/>
        </label>

        <input class="input"
               type="password"
               id="password"
               aria-label="Password:"
               name="password"
        >

        <select class="btn" name="role">
            <fmt:message key="choose_role"/>
            <option class="btn" datatype="text" value="Cashier">
                <fmt:message key="cashier"/>
            </option>
            <option datatype="text" value="SeniorCashier">
                <fmt:message key="sCashier"/>
            </option>
            <option datatype="text" value="CommodityExpert">
                <fmt:message key="cExpert"/>
            </option>
        </select>
         <button class="btn p-10-15"
                 form="sign_up_form"
                 formmethod="POST"
                 type="submit"
         >
             OK
         </button>
     </form>
     <% if (SignUpServlet.isUserAlreadyExists()) {
         out.print("<h1 color='red'><fmt:message key=\"user_already_exists\"/></h1>");
     }%>
</body>
</html>
