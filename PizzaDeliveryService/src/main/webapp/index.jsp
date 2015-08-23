<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 07.08.2015
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pizza Delivery Service</title>
</head>
<body>

<%--<h1>Hello world!!!</h1>--%>

<%--${1+1}--%>

<h1>Welcome to Pizza Delivery Service</h1>

<sec:authorize access="hasRole('ANONYMOUS')">
    <a href="./pages/adm/">Log in as Admin</a>
    <br/>
    <a href="./pages/">Log in as Client</a>
    <br/>
</sec:authorize>

<sec:authorize access="hasAnyRole('USER', 'ANONYMOUS')">
    <a href="./pages/pizzas/">Choose pizzas</a>
    <br/>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    <a href="./pages/adm/">Admin Home page</a>
    <br/>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    <a href="./pages/">Client Home page</a>
</sec:authorize>

<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
  <c:url var="logoutURL" value="/logout"/>
    <form action="${logoutURL}" method="post">
        <input type="submit" value="Logout"/>
        <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    </form>
</sec:authorize>

</body>
</html>
