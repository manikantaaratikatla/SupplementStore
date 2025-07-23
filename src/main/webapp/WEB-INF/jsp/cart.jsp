<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>

<div class="header">
    Your Shopping Cart
</div>

<div class="cart-container">
    <c:choose>
        <c:when test="${not empty cart}">
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0" />
                    <c:forEach var="item" items="${cart}">
                        <c:set var="subtotal" value="${item.price * item.quantity}" />
                        <tr>
                            <td>${item.name}</td>
                            <td>₹${item.price}</td>
                            <td>${item.quantity}</td>
                            <td>₹${subtotal}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/cart/remove" method="post">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <button class="remove-btn" type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <c:set var="total" value="${total + subtotal}" />
                    </c:forEach>
                </tbody>
            </table>

            <div class="cart-total">
                <strong>Total: ₹${total}</strong>
            </div>

            <div class="cart-actions">
                
                <form action="${pageContext.request.contextPath}/cart/clear" method="post">
                    <button type="submit">Clear Cart</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
