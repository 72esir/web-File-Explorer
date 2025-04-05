<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <button type="submit">Register</button>
</form>
<p>Already have an account? <a href="login.jsp">Login here</a></p>

<%
    String error = request.getParameter("error");
    if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<%
    }
%>

<%
    String success = request.getParameter("success");
    if (success != null) {
%>
<p style="color: green;"><%= success %></p>
<%
    }
%>
</body>
</html>