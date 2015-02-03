<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/14/2015
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="addUserServlet" method="POST">
    <label for="username">Username</label>

    <input id="username" type="text" name="username" value="${username}">
    <label for="password">Password</label>
    <input id="password" type="text" name="password" value="${password}">

    <label for="role">Role</label>

    <select name="role" id="role">
    <c:forEach items="${roleList}" var="r">

            <option value="${r.name}">${r.name}</option>


    </c:forEach>
    </select>

    <input type="submit" name="addUser" value="add user">

</form>
<hr>

<c:forEach items="${userList}" var="us">
    <table border="1" bgcolor="#00bfff">
        <caption>Список пользователей</caption>
        <tr>
            <th>Имя Пользователя</th>
            <th>Пароль</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Email</th>
            <th>Роль</th>
            <th>Редактировать пользователя</th>
            <th>Удалить пользователя</th>
            <th>Заказы Пользователя</th>
            <th>Добавить Заказ</th>
        </tr>
    <tr>
        <td>${us.username}</td>
        <td>${us.password}</td>
        <td>${us.firstName}</td>
        <td>${us.lastName}</td>
        <td>${us.email}</td>
        <td>${us.role.name}</td>
        <td>
            <form action="userEditServlet" method="get">
                <input type="text" name="id" value="${us.id}" hidden="hidden">
                <input type="submit" name="editUser" value="изменить" >
            </form>
        </td>
        <td>
            <form action="userDeleteServlet" method="get">
                <input type="text" name="id" value="${us.id}" hidden="hidden">
                <input type="submit" name="deleteUser" value="удалить" >
            </form>
        </td>
        <td>
            <form action="userOrderServlet" method="get">
                <input type="text" name="id" value="${us.id}" hidden="hidden">
                <input type="submit" name="orders" value="заказы" >
            </form>
        </td>
        <td>
            <form action="orderAddServlet" method="get">
                <input type="text" name="id" value="${us.id}" hidden="hidden">
                <input type="submit" name="orders" value="добавить заказ" >
            </form>
        </td>
    </tr>
</table>

    </c:forEach>

${update}
</body>
<a href="/TestJPAJSP_Web_exploded/">go to home</a>
</html>
