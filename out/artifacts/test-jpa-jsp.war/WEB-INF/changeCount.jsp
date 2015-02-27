<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/27/2015
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Подтвердите количество</h1>
<form action="itemCountUpdate" method="post" name="itemCount">
      <%--onchange="document.forms['itemCount'].submit()">--%>
    <input type="text" name="itemId" value="${itemId}" >

    <input type="text" name="orderId" value="${orderId}">

    <input type="text" name="count" value="${count}">


    <input type="submit" name="cart" value="изменить количество">
</form>
</body>
</html>
