<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Supply Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css"> <%-- For navbar styling --%>
    <style>
        .profile-container {
            max-width: 600px;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .profile-container h2 {
            color: #0077cc;
            margin-bottom: 20px;
            text-align: center;
        }
        .profile-info p {
            font-size: 1.1em;
            margin-bottom: 10px;
            border-bottom: 1px solid #eee;
            padding-bottom: 8px;
        }
        .profile-info p strong {
            color: #333;
            display: inline-block;
            width: 120px; /* Align labels */
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
        <h1>My Profile</h1>
    </div>

    <div class="profile-container">
        <h2>User Information</h2>
        <div class="profile-info">
            <p><strong>Name:</strong> <c:out value="${customer.name}"/></p>
            <p><strong>Email:</strong> <c:out value="${customer.email}"/></p>
            <p><strong>Phone:</strong> <c:out value="${customer.phone}"/></p>
            <p><strong>Address:</strong> <c:out value="${customer.address}"/></p>
        </div>
    </div>
</body>
</html>