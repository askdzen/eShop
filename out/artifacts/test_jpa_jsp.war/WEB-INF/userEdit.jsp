<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/15/2015
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Редактирование данных пользователя</h1>

<form action="userEditServlet" method="POST">
    <input id="id" type="text" name="id" value="${id}" hidden="hidden">
    <label for="username" style="align-content: center">Username</label>
    <input id="username" type="text" name="username" value="${username}">

    <br/>

    <p>

        <label for="password" style="align-content: center">Password</label>
        <input id="password" type="text" name="password" value="${password}">

        <br/>

    <p>

        <label for="firstName" style="align-content: center">First Name</label>
        <input id="firstName" type="text" name="firstName" value="${firstName}">

        <br/>

    <p>

        <label for="lastName" style="align-content: center">Last Name</label>
        <input id="lastName" type="text" name="lastName" value="${lastName}">

        <br/>

    <p>


        <label for="email" style="align-content: center">Email</label>
        <input id="email" type="text" name="email" value="${email}">

        <br/>

    <p>
        <label for="role" style="align-content: center">Role</label>
    <select name="role" id="role" >
        <c:forEach items="${roles}" var="r">

            <option value="${r.name}" >${r.name}</option>


        </c:forEach>
    </select>

        <input type="submit" name="save" value="Сохранить">
</form>
</body>
</html>
