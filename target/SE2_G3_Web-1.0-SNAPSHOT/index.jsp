<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 2020-09-25
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%
    request.getRequestDispatcher("/homePage").forward(request, response);
%>
</body>
</html>
<%--
    This is just a redirect to the home via a servlet when someone goes to our url address
--%>