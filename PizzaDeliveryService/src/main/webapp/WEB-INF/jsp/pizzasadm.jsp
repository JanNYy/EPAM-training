<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 10.08.2015
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>View all pizzas</title>
</head>
<body>

<h2>All pizzas</h2>

<%--User name: ${name} <br/>--%>
<%--Role: ${role} <br/>--%>

<%--<c:url var="logoutURL" value="/logout"/>--%>
<%--<form action="${logoutURL}" method="post">--%>
  <%--<input type="submit" value="Logout"/>--%>
  <%--<input type="hidden"--%>
         <%--name="${_csrf.parameterName}"--%>
         <%--value="${_csrf.token}"/>--%>
<%--</form>--%>

<a href="..">Return main</a>
<br/>
<br/>

<c:choose>
  <c:when test="${not empty pizzas}">
    <table border="2" cellspacing="0">

      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Type</th>
        <th>Actions</th>
      </tr>

      <c:forEach var="pizza" items="${pizzas}">
        <tr>
          <td>
            <c:out value="${pizza.id}"/>
          </td>
          <td>
            <c:out value="${pizza.name}"/>
          </td>
          <td>
            <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">
              <c:out value="${pizza.price}"/>
            </fmt:formatNumber>
          </td>
          <td>
            <c:out value="${pizza.type}"/>
          </td>

          <td>
            <form name="editPizza" method="get" action="edit">
              <input type="hidden" name="id" value="${pizza.id}"/>
              <button type="submit">
                Edit
              </button>
            </form>

            <%--<form name="deletePizza" method="post" action="delete">--%>
              <%--<input type="hidden" name="id" value="${pizza.id}"/>--%>
              <%--<button type="submit">--%>
                <%--Delete--%>
              <%--</button>--%>
              <%--<sec:csrfInput/>--%>
            <%--</form>--%>
          </td>

        </tr>
      </c:forEach>

    </table>
  </c:when>

  <c:otherwise>
    <h3>Pizzas list is empty :(</h3>
  </c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ADMIN')">
  <a href="./create">Create new pizza</a>
</sec:authorize>

<%--<c:forEach var="pizza" items="${pizzas}">
  <c:out value="${pizza}"/><br/>
</c:forEach>--%>

</body>
</html>