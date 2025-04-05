<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required>
  <button type="submit">Login</button>
</form>
<p>Don't have an account? <a href="register.jsp">Register here</a></p>

<%
  String error = request.getParameter("error");
  if (error != null) {
%>
<p style="color: red;">Invalid username or password</p>
<%
  }
%>
</body>
</html>