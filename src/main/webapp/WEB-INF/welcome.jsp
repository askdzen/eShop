
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="goToCart" method="get">
    <label for="go">Количество товаров в корзине: ${cartSize}</label>
    <input type="submit" value="Оформить заказ" id="go">
</form>
<form action="logout" method="get">

    <input type="submit" value="Выйти из системы" >
</form>
<h1>Welcome!</h1>
<table>

    <table border="1" bgcolor="#00bfff">
        <caption>Список товаров</caption>
        <tr>
            <th>Название товара</th>
            <th>стоимость</th>
            <th>купить</th>
        </tr>

        <c:forEach items="${itemList}" var="i">
            <tr>
                <td>${i.name}</td>
                <td>${i.cost}</td>

                <td>
                    <form action="cartAdd" method="get">
                        <input type="text" name="id" value="${i.id}" hidden="hidden" >
                        <input type="submit" name="cart" value="купить" ${disabled}>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>


</table>
</body>
</html>
