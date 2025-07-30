<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart - Supply Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css"> <%-- For navbar styling --%>
</head>
<body>
    <div class="navbar">
        <div>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            <a href="${pageContext.request.contextPath}/cart">Cart</a>
            <a href="${pageContext.request.contextPath}/orders">My Orders</a>
            <a href="${pageContext.request.contextPath}/profile">Profile</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>

    <div class="header">
        <h1>Your Shopping Cart</h1>
    </div>

    <div class="cart-container">
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>

        <c:choose>
            <c:when test="${empty cartItems}">
                <p>Your cart is empty. <a href="${pageContext.request.contextPath}/dashboard">Start shopping!</a></p>
            </c:when>
            <c:otherwise>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartItem" items="${cartItems}">
                            <c:set var="item" value="${itemDetailsMap[cartItem.itemId]}" />
                            <tr>
                                <td><c:out value="${item.name}"/></td>
                                <td>$<c:out value="${item.price}"/></td>
                                <td><c:out value="${cartItem.quantity}"/></td>
                                <td>$<c:out value="${item.price * cartItem.quantity}"/></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/cart/remove" method="post">
                                        <input type="hidden" name="cartItemId" value="${cartItem.id}">
                                        <button type="submit" class="remove-btn">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="cart-total">
                    Total: $<c:out value="${totalAmount}"/>
                </div>
                <div style="text-align: center; margin-top: 20px;">
                    <form action="${pageContext.request.contextPath}/placeOrder" method="post">
                        <button type="submit" style="padding: 10px 20px; background-color: #28a745; color: white; border: none; border-radius: 5px; cursor: pointer;">Place Order</button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>