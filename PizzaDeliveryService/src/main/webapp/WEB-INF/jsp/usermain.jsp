<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 21.08.2015
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User main page</title>
</head>
<body>

<h2>User main page</h2>

Welcome, ${name} <br/>
Your balance:
<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">
${accumulative_card.sum}
</fmt:formatNumber>

<c:url var="logoutURL" value="/logout"/>
<form action="${logoutURL}" method="post">
  <input type="submit" value="Logout"/>
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>

<a href="./pizzas/">Choose pizzas</a>
<br/>

<a href="./orders/">History</a>

</body>
</html>
