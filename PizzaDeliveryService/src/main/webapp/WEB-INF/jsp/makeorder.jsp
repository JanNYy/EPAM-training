<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 23.08.2015
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make order</title>
</head>
<body>

<h2>Order details</h2>

<a href="../">Return main</a>
<br/>

<a href="./">Choose pizzas</a>
<br/>

<form name="fmOrderData" action="confirmorder" method="post">
  <%--<h2>Time and Date</h2>--%>
  <%--<input type="datetime-local" name="dateTime"/>--%>

  <h3>Address</h3>
    <table>
      <tr>
        <td>
          <label for="city">City:</label>
        </td>
        <td>
          <select id="city" name="city">
            <c:forEach var="city" items="${cities}">
              <option value="${city.name}">${city.name}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <label for="street">Street:</label>
        </td>
        <td>
          <input type="text" id="street" name="street" required/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="building">Building:</label>
        </td>
        <td>
          <input type="text" id="building" name="building" required/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="apartment">Apartment:</label>
        </td>
        <td>
          <input type="text" id="apartment" name="apartment"/>
        </td>
      </tr>
    </table>

  <button type="submit">
    Make order
  </button>
  <sec:csrfInput/>
</form>

</body>
</html>
