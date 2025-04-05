<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Файловый менеджер</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        tr:hover { background-color: #f5f5f5; }
        .parent-link { margin-bottom: 10px; display: inline-block; }
    </style>
</head>
<body>

<h2>Файловый менеджер</h2>
<p><b>Текущяя директория:</b> <%= request.getAttribute("currentPath") %></p>
<p><b>Дата генерации страницы:</b> <%= request.getAttribute("currentTime") %></p>

<%
    String parentPath = (String) request.getAttribute("parentPath");
    if (parentPath != null) {
%>
    <a href="logout" style="float: right;">Выйти</a>

    <a href="?path=<%= parentPath %>" class="parent-link">⬆ Вверх</a>
<%
    }
%>

<table>
    <tr>
        <th>Имя</th>
        <th>Тип</th>
        <th>Действие</th>
    </tr>
    <%
        File[] files = (File[]) request.getAttribute("files");
        if (files != null) {
            for (File file : files) {
    %>
    <tr>
        <td><%= file.getName() %></td>
        <td><%= file.isDirectory() ? "Папка" : "Файл" %></td>
        <td>
            <% if (file.isDirectory()) { %>
                <a href="?path=<%= file.getAbsolutePath() %>"> Открыть</a>
            <% } else { %>
                <a href="downloadFile?path=<%= file.getAbsolutePath() %>"> Скачать</a>
            <% } %>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="3">Нет файлов в данной директории</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
