<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>

</head>
<body>

<form action="registration" method="POST" title="Заполните поля:">
    <table style="align-content: center" class="box">
        <tr>
            <label for="username" style="align-content: center">Username</label>
            <input id="username" type="text" name="username" value="${username}">
        </tr>
        <br/>

        <p>
            <tr>
                <label for="password" style="align-content: center">Password</label>
                <input id="password" type="text" name="password" value="${password}">
            </tr>
            <br/>
        <p>
            <tr>
                <label for="firstName" style="align-content: center">First Name</label>
                <input id="firstName" type="text" name="firstName" value="${firstName}">
            </tr>
            <br/>
        <p>
            <tr>
                <label for="lastName" style="align-content: center">Last Name</label>
                <input id="lastName" type="text" name="lastName" value="${lastName}">
            </tr>
            <br/>
        <p>

            <tr>
                <label for="email" style="align-content: center">Email</label>


                <input id="email" type="text" name="email" value="${email}">
            </tr>
    </table>


    <input type="submit" name="addUser" value="sign">

</form>
<h1>${successfully}</h1>
<a href="/TestJPAJSP_Web_exploded/">go to home</a>
</body>
</html>
