<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 10.08.2015
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit/create pizza</title>
</head>
<body>

<h2>Edit/create pizza</h2>

<a href="..">Return main</a>
<br/>

<a href=".">Return back</a>
<br/>
<br/>

<form name="fmSavePizza" method="post" action="save">

  <input type="hidden" name="id" value="${pizza.id}"/>

  <table>

    <tr>
      <td>
        <label for="name">Name:</label>
      </td>
      <td>
        <input type="text" id="name" name="name" value="${pizza.name}" required/>
      </td>
    </tr>

    <tr>
      <td>
        <label for="type">Type:</label>
      </td>
      <td>
        <select name="type" id="type">
          <c:forEach var="type" items="${types}">
            <option value="${type}">${type}</option>
          </c:forEach>
        </select>
      </td>
    </tr>

    <tr>
      <td>
        <label for="price">Price:</label>
      </td>
      <td>
        <input name="price" id="price" type="text" value="${pizza.price}" required/>
      </td>
    </tr>

  </table>

  <button type="submit">
    Save
  </button>

  <sec:csrfInput/>

</form>

</body>
</html>
