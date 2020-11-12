<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 2020-10-29
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body>
    <br/>
    <h1>This is a placeholder for the statistics page for the devices</h1>
    <br/>
    <br/>
    <form action="${pageContext.request.contextPath}/button" method="post">
        <button name="button" type="submit" value="dashboard">Back</button>
    </form>
    <br/>
    <form action="${pageContext.request.contextPath}/button" method="post">
        <button name="button" type="submit" value="logout">Logout</button>
    </form>
</body>
</html>
