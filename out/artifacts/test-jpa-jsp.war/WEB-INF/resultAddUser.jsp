<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/14/2015
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="/TestJPAJSP_Web_exploded/">go to home</a>

<h1>Был добавлен новый пользователь</h1>
<c:forEach items="${list}" var="us">
    <table>
        <tr>
            <td>${us.username}</td>
            <td>${us.password}</td>
            <td>${us.firstName}</td>
            <td>${us.lastName}</td>
            <td>${us.email}</td>
            <td>${us.role.name}</td>
        </tr>
    </table>

</c:forEach>
</body>
</html>
