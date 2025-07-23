<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Register - SupplyStore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-box {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 350px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label, input {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        input {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
        }
        .error {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
        .success {
            color: green;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="register-box">
    <h2>Register</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="success">${message}</div>
    </c:if>

    <form action="/register/save" method="post">
        <label for="name">Full Name</label>
        <input type="text" name="name" id="name" required>

        <label for="email">Email</label>
        <input type="email" name="email" id="email" required>

        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>

        <label for="phone">Phone</label>
        <input type="text" name="phone" id="phone" required>

        <label for="address">Address</label>
        <input type="text" name="address" id="address" required>

        <button type="submit" class="btn">Register</button>
    </form>
</div>
</body>
</html>
