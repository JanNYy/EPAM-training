<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 22.08.2015
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all orders</title>
</head>
<body>

<h2>All orders</h2>

<a href="..">Return main</a>
<br/>
<br/>

<c:choose>
  <c:when test="${not empty orders}">
    <table border="2" cellspacing="0">

      <tr>
        <th>ID</th>
        <th>Date and time</th>
        <th>Customer</th>
        <th>Address</th>
        <th>Sum</th>
        <th>Pizzas</th>
      </tr>

      <c:forEach var="order" items="${orders}">
        <tr>
          <td>
            <c:out value="${order.id}"/>
          </td>
          <td>
            <c:out value="${order.dateTime}"/>
          </td>
          <td>
            <c:out value="${order.customer.firstName}"/>
            <c:out value="${order.customer.lastName}"/>
          </td>
          <td>
            City: <c:out value="${order.address.city.name}"/>
            <br/>
            Street: <c:out value="${order.address.street.name}"/>
            <br/>
            Building: <c:out value="${order.address.building}"/>
            <br/>
            Apart. :<c:out value="${order.address.apartment}"/>
          </td>
          <td>
            <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">
              <c:out value="${order.sum}"/>
            </fmt:formatNumber>
          </td>

          <td>
            <table border="0" rules="rows" cellspacing="0" width="100%">
              <c:forEach var="pizza" items="${order.pizzas}">
                <tr>
                    <%--<td>--%>
                    <%--<c:out value="${pizza.key.id}"/>--%>
                    <%--</td>--%>
                  <td>
                    <c:out value="${pizza.key.name}"/>
                  </td>

                  <td>
                    -
                  </td>
                    <%--<td>--%>
                    <%--<c:out value="${pizza.key.price}"/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<c:out value="${pizza.key.type}"/>--%>
                    <%--</td>--%>
                  <td>
                    <c:out value="${pizza.value}"/>
                  </td>
                </tr>
              </c:forEach>
            </table>
          </td>

        </tr>
      </c:forEach>

    </table>
  </c:when>

  <c:otherwise>
    <h3>Orders list is empty :(</h3>
  </c:otherwise>
</c:choose>

</body>
</html>
