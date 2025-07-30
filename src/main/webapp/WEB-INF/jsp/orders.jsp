<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders - Supply Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css"> <%-- For navbar styling --%>
    <style>
        .order-container {
            max-width: 900px;
            margin: 30px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .order-card {
            border: 1px solid #eee;
            border-radius: 8px;
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f9f9f9;
        }
        .order-card h3 {
            color: #0077cc;
            margin-top: 0;
            margin-bottom: 10px;
        }
        .order-card p {
            margin: 5px 0;
        }
        .order-details-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        .order-details-table th, .order-details-table td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .order-details-table th {
            background-color: #e9e9e9;
        }
    </style>
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
        <h1>My Previous Orders</h1>
    </div>

    <div class="order-container">
        <c:choose>
            <c:when test="${empty orders}">
                <p>You have not placed any orders yet.</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="order" items="${orders}">
                    <div class="order-card">
                        <h3>Order ID: <c:out value="${order.orderId}"/></h3>
                        <p>Order Date: <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm"/></p>
                        <p>Total Amount: $<c:out value="${order.totalAmount}"/></p>

                        <h4>Items in this Order:</h4>
                        <table class="order-details-table">
                            <thead>
                                <tr>
                                    <th>Item Name</th>
                                    <th>Quantity</th>
                                    <th>Price at Purchase</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${requestScope['orderDetails_' + order.orderId]}">
                                    <c:set var="item" value="${requestScope['itemDetailsMap_' + order.orderId][detail.itemId]}" />
                                    <tr>
                                        <td><c:out value="${item.name}"/></td>
                                        <td><c:out value="${detail.quantity}"/></td>
                                        <td>$<c:out value="${detail.priceAtPurchase}"/></td>
                                        <td>$<c:out value="${detail.quantity * detail.priceAtPurchase}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>