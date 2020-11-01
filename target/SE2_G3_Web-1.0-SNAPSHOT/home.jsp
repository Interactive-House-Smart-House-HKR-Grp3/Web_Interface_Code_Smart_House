<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 2020-10-17
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        UserName: <input type="text" name="username">
        <br />
        Password: <input type="text" name="password">
        <button name="button" type="submit" value="login">Login</button>
    </form>
</body>
</html>
