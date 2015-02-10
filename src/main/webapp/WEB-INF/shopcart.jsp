<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shop Cart</title>
</head>
<body>
${signIn.user.username}
${noItems}
${cartNoEmpty}
<form action="logout" method="post">

    <input type="submit" name="cart" value="очистить корзину и выйти">
</form>
<table border="1" bgcolor="#00bfff">


    <tr>
        <th>Название товара</th>
        <th>цена за единицу</th>
        <th>количество</th>
        <th>удалить</th>
    </tr>

    <c:forEach items="${orderItemList}" var="o">
    <tr>

            <c:forEach items="${itemList}" var="i">
                <c:if test="${i.id==o.itemId}">
                    <td>

                        <c:out value="${i.name}"/>
                    </td>
                    <td>
                        <c:out value="${i.cost}"/>
                    </td>
                </c:if>

            </c:forEach>

            <td>
                <form action="itemCountUpdate" method="get" name="itemCount">

                    <input type="hidden" name="itemId" value="${o.itemId}">

                    <input type="hidden" name="orderId" value="${o.orderId}">

                    <input type="hidden" name="count" value="${o.itemQty}">

                    <input type="text" name="count" value="${o.itemQty}" disabled>


                    <input type="submit" name="cart" value="изменить количество">
                </form>
            </td>
            <td>
                <form action="deleteFromCart" method="get">
                    <input type="text" name="itemId" value="${o.itemId}" hidden="hidden">
                    <input type="text" name="orderId" value="${o.orderId}" hidden="hidden">
                    <input type="submit" name="cart" value="удалить">
                </form>
            </td>

    </tr>
    </c:forEach>
</table>


</body>
<form action="address.jsf" method="post">

    <input type="submit" value="далее">
</form>

</html>
