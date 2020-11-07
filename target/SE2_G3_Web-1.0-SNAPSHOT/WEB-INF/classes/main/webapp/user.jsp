<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 2020-10-30
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's homepage</title>
</head>
<body>
    <%
        String test = (String) request.getAttribute("test");
    %>
    Here's the attribute "test": <%=test%>
    <br/>
    <br/>
    <%
        String query = (String) request.getAttribute("queryResult");
    %>
    Here's the attribute "query": <%=query%>
    <br/>
    <br/>

    <t1>User's Devices</t1>
    <form action="${pageContext.request.contextPath}/button" method="post">
        <button name="button" type="submit" value="statistics">Statistics</button>
    </form>
    <br/>
    <form action="${pageContext.request.contextPath}/button" method="post">
        <button name="button" type="submit" value="logout">Logout</button>
    </form>
</body>
</html>
