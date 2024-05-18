<%@ page import="com.cashReg.CashRegister" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!--%@page pageEncoding="ISO-8859-5" %-->

<%
    CashRegister cashRegister = CashRegister.getInstance();
    String language = cashRegister.getLocale().toString();
   String locale = "";
   String flagLink = "";

    if("UA".equalsIgnoreCase(language))
    {
        locale="ua";
        flagLink="https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Flag_of_Ukraine.svg/800px-Flag_of_Ukraine.svg.png";
    }
    if("EN".equalsIgnoreCase(language)){
        locale = "en";
        flagLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/1200px-Flag_of_the_United_Kingdom_%283-5%29.svg.png";
    }
%>
<fmt:setBundle basename="messages" />
<fmt:setLocale value="<%=locale%>" scope="application" />
<span style="color: white; font-size: 24px">
       <style><%@include file="/WEB-INF/assets/css/locale.css"%></style>
<fmt:message  key="hello"/>
</span>
<form method="post">
<button class="localeBtn"
        formmethod="post"
        type="submit"
        name="change_language"
        value="true"
>
    <img height="30px" width="30px" src="<%=flagLink%>"/>
</button>
</form>
