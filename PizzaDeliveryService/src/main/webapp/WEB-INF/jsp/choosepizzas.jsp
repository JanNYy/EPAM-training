<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 22.08.2015
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose pizzas</title>
</head>
<body>

<h2>Choose pizzas</h2>

<sec:authorize access="hasRole('USER')">
  <a href="../">Return main</a>
  <br/>

  Number of pizzas in your cart:
  <c:choose>
    <c:when test="${not empty number_of_pizzas}">
      ${number_of_pizzas}
    </c:when>
    <c:otherwise>
      0
    </c:otherwise>
  </c:choose>
  <br/>
  <form name="clearCart" method="post" action="clearcart">
    <button type="submit">
      Clear cart
    </button>
    <sec:csrfInput/>
  </form>
  <a href="viewcart">View cart</a>
</sec:authorize>

<br/>
<sec:authorize access="hasRole('ANONYMOUS')">
  <a href="..">Log in as Client</a>
</sec:authorize>
<br/>
<br/>

<table border="1" cellspacing="0">

  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Type</th>
    <th>Order</th>
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
        <form name="orderPizza" method="get" action="order">
          <input type="hidden" name="id" value="${pizza.id}"/>
          <button type="submit">
            Add to Shopping Cart
          </button>
        </form>
      </td>

    </tr>
  </c:forEach>

</table>

</body>
</html>
