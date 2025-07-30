<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Supply Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f9f9f9;
        }

        .navbar {
            background-color: #333;
            padding: 10px 20px;
            color: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar a {
            color: #fff;
            margin-right: 15px;
            text-decoration: none;
        }

        .navbar a:hover {
            text-decoration: underline;
        }

        .navbar form input[type="text"] {
            padding: 5px;
        }

        .navbar form button {
            padding: 5px 10px;
        }

        .header {
            padding: 20px;
            background-color: #ffffff;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .item-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        .item-card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            text-align: center;
        }

        .item-card img {
            max-width: 100%;
            max-height: 150px;
            object-fit: contain;
            margin-bottom: 10px;
        }

        .item-card h3 {
            margin: 10px 0 5px 0;
        }

        .item-card p {
            margin: 5px 0;
        }

        .item-card form input[type="number"] {
            width: 60px;
            padding: 5px;
        }

        .item-card form button {
            padding: 5px 10px;
            margin-top: 5px;
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
        <form action="${pageContext.request.contextPath}/dashboard/search" method="get">
            <input type="text" name="keyword" placeholder="Search items...">
            <button type="submit">Search</button>
        </form>
    </div>

    <div class="header">
        <h1>Welcome to Supply Store, <c:out value="${sessionScope.loggedInCustomer.name}"/>!</h1>
        <p>Browse our wide range of groceries and health products.</p>
    </div>

    <div class="item-grid">
        <c:forEach var="item" items="${items}">
            <div class="item-card">
                <img src="${pageContext.request.contextPath}/imageurl/${item.imageurl}" alt="${item.name}">
                <h3><c:out value="${item.name}"/></h3>
                <p>Price: ₹<c:out value="${item.price}"/></p>
                <p><c:out value="${item.description}"/></p>
                <p>In Stock: <c:out value="${item.availQuantity}"/></p>
                <form action="${pageContext.request.contextPath}/cart/add" method="post">
                    <input type="hidden" name="itemId" value="${item.id}">
                    <label for="quantity_${item.id}">Quantity:</label>
                    <input type="number" id="quantity_${item.id}" name="quantity" value="1" min="1" max="${item.availQuantity}">
                    <button type="submit">Add to Cart</button>
                </form>
            </div>
        </c:forEach>
    </div>

</body>
</html>
