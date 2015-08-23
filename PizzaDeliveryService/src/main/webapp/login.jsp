<%--
  Created by IntelliJ IDEA.
  User: Anna
  Date: 14.08.2015
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<form action="j_spring_security_check" method="post">

  <p>
    <label for="username">Username</label>
    <input type="text" id="username" name="j_username"/>
  </p>

  <p>
    <label for="password">Password</label>
    <input type="password" id="password" name="j_password"/>
  </p>

  <input id="remember_me" name="spring_security_remember_me"
         type="checkbox">
  <label for="remember_me" class="inline">Remember me</label>

  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
  <button type="submit" class="btn">Login</button>
</form>

</body>
</html>
