<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<div class="navbar">
    <a href="${pageContext.request.contextPath}/dashboard">Home</a>
    <a href="${pageContext.request.contextPath}/cart/view">Cart</a>
    <a href="${pageContext.request.contextPath}/orders">Previous Orders</a>
    <a href="${pageContext.request.contextPath}/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>

    <form action="${pageContext.request.contextPath}/dashboard" method="get" style="display:inline;">
        <input type="text" name="search" placeholder="Search items..." value="${search}" />
        <button type="submit">Search</button>
    </form>
</div>

<div class="item-grid">
    <c:forEach var="item" items="${items}">
        <div class="item-card">
            <img src="${item.imageurl}" alt="${item.itemname}" style="width: 150px; height: 150px;" />
            <h3>${item.itemname}</h3>
            <p>Price: ₹${item.price}</p>
            <p>Weight: ${item.weight}g</p>
            <p>Available: ${item.availQuantity}</p>
            <form action="${pageContext.request.contextPath}/cart/add" method="post">
                <input type="hidden" name="id" value="${item.itemid}">
                <input type="hidden" name="name" value="${item.itemname}">
                <input type="hidden" name="price" value="${item.price}">
                <label>Quantity:</label>
                <input type="number" name="quantity" value="1" min="1" required>
                <button type="submit">Add to Cart</button>
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>
