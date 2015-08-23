<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 23.08.2015
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h2>Shopping cart</h2>

<a href="../">Return main</a>
<br/>

<a href="./">Choose pizzas</a>
<br/>
<br/>

<c:choose>
  <c:when test="${not empty pizzas_in_order}">

    <form name="shoppingCart" method="post" action="submitorder">

      <table border="1" cellspacing="0">

        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Price</th>
          <th>Type</th>
          <th>Amount</th>
        </tr>

        <c:forEach var="pizza" items="${pizzas_in_order}">
          <tr>
            <td>
              <c:out value="${pizza.key.id}"/>
            </td>
            <td>
              <c:out value="${pizza.key.name}"/>
            </td>
            <td>
              <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">
                <c:out value="${pizza.key.price}"/>
              </fmt:formatNumber>
            </td>
            <td>
              <c:out value="${pizza.key.type}"/>
            </td>
            <td>
              <c:out value="${pizza.value}"/>
            </td>
          </tr>
        </c:forEach>

      </table>

      Total order price:
      <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">
      ${order_price}
      </fmt:formatNumber>
      <br/>

      <button type="submit">
        Make order
      </button>

      <sec:csrfInput/>

    </form>

    <form name="clearCart" method="post" action="clearcart">
      <button type="submit">
        Clear cart
      </button>
      <sec:csrfInput/>
    </form>

  </c:when>

  <c:otherwise>
    <h3>Your shopping cart is empty :(</h3>
  </c:otherwise>
</c:choose>

</body>
</html>
