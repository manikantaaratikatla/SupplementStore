<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Previous Orders</title></head>
<body>
    <h2>Your Previous Orders</h2>
    <c:forEach var="order" items="${orders}">
        <div>
            <p><strong>Order ID:</strong> ${order.id}</p>
            <p><strong>Date:</strong> ${order.orderDate}</p>
            <p><strong>Items:</strong> ${order.itemsSummary}</p>
            <p><strong>Total:</strong> ₹${order.totalAmount}</p>
            <hr/>
        </div>
    </c:forEach>
</body>
</html>
