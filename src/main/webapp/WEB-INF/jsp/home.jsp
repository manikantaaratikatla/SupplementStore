<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Supply Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f3f7;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            text-align: center;
        }
        .welcome-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 500px;
        }
        .welcome-container h1 {
            color: #0077cc;
            margin-bottom: 20px;
        }
        .welcome-container p {
            font-size: 1.1em;
            color: #555;
            margin-bottom: 30px;
        }
        .welcome-container .button-group a {
            display: inline-block;
            margin: 0 10px;
            padding: 12px 25px;
            background-color: #0077cc;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .welcome-container .button-group a:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome to the Supply Store!</h1>
        <p>Your one-stop shop for all your supplement needs.</p>
        <div class="button-group">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </div>
    </div>
</body>
</html>