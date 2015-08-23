<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Admin main page</title>
</head>
<body>

<h2>Admin main page</h2>

User name: ${name} <br/>
Role: ${role} <br/>

<c:url var="logoutURL" value="/logout"/>
<form action="${logoutURL}" method="post">
  <input type="submit" value="Logout"/>
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>

<a href="./pizzas/">Pizzas</a>
<br/>
<a href="./orders/">Orders</a>

</body>
</html>
