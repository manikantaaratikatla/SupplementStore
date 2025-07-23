<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<html>
<head>
    <title>Login - SupplyStore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-box {
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
        .register-btn {
            margin-top: 10px;
            background-color: #28a745;
        }
        .error {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>Login</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="/login/auth" method="post">
        <label for="username">Email</label>
        <input type="text" name="username" id="username" required>

        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>

        <button type="submit" class="btn">Login</button>
    </form>

    <!-- Register Button -->
    <form action="/register" method="get">
        <button type="submit" class="btn register-btn">Register</button>
    </form>
</div>
</body>
</html>
