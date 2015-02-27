<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/15/2015
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:forEach items="${list}" var="ord">
    <table border="1" bgcolor="#00bfff">
        <caption>Заказы пользователя ${username}</caption>
        <tr>


            <th>Стоимость</th>
            <th>Дата заказа</th>
        </tr>

        <tr>


            <td>${ord.cost}</td>
            <td>${ord.date}</td>
            <td>
                <form action="orderEditServlet" method="get">
                    <input type="text" name="id" value="${ord.id}" hidden="hidden">
                    <input type="submit" name="editOrder" value="изменить" >
                </form>
            </td>
            <td>
                <form action="orderDeleteServlet" method="get">
                    <input type="text" name="id" value="${ord.id}" hidden="hidden">
                    <input type="submit" name="deleteOrder" value="удалить" >
                </form>
            </td>
            <td>

            </td>
        </tr>
    </table>

</c:forEach>

<form action="orderAddServlet" method="get">
<input type="text" name="id" value="${id}" hidden="hidden">
<input type="submit" name="orders" value="добавить заказ" >
</form>
</body>
</html>
