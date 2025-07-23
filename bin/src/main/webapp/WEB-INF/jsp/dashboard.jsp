<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<div class="header">
    Welcome to the Supplements Store |
    <a href="${pageContext.request.contextPath}/cart/view">View Cart</a>
</div>

<div class="item-grid">
    <c:forEach var="item" items="${items}">
        <div class="item-card">
            <h3>${item.itemName}</h3>
            <p>Price: ₹${item.price}</p>
            <p>Weight: ${item.weight}g</p>
            <p>Available: ${item.availableQuantity}</p>
            <form action="${pageContext.request.contextPath}/cart/add" method="post">
                <input type="hidden" name="id" value="${item.itemId}">
                <input type="hidden" name="name" value="${item.itemName}">
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
