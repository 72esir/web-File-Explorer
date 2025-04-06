<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.nio.file.Files,java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>File Manager</title>
    <style>
            body { font-family: Arial, sans-serif; margin: 20px; }
            table { width: 100%; border-collapse: collapse; }
            th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
            tr:hover { background-color: #f5f5f5; }
            .parent-link { margin-bottom: 10px; display: inline-block; }
        </style>
</head>
<body>
<p class="typewriter">${createDatingPage}</p>
<h1 class="typewriter">${path}</h1>
<hr />

<form action="logout" method="post">
        <button type="submit">Выход</button>
</form>

<form style="display: ${directoryVisibilityOnTop};" action="./main-servlet" method="get">
    <button type="submit" name="path" value="${backToParent}">
        <span>⬆  Назад</span>
    </button>
</form>

<table>
    <tr>
        <th class="th-first">Файл</th>
        <th style="padding: 0 30px 0 30px;">Размер</th>
        <th style="padding: 0 100px 0 100px;">Дата</th>
    </tr>

    <c:forEach var="directory" items="${directories}">
        <tr>
            <td>
                <form action="main-servlet" method="get" style="display:inline;">
                    <input type="hidden" name="path" value="${directory.getAbsolutePath()}" />
                    <button type="submit">📁 ${directory.getName()}</button>
                </form>
            </td>
            <td></td>
            <td>
                <span>
                    <%= Files.getAttribute(((File) pageContext.getAttribute("directory")).toPath(), "lastModifiedTime").toString() %>
                </span>
            </td>
        </tr>
    </c:forEach>


    <form action="./main-servlet/download" method="get">
        <c:forEach var="file" items="${files}">
            <tr>
                <td>
                    <button type="submit" name="path" value="${file.getPath()}">
                        <span>📄 ${file.getName()}</span>
                    </button>
                </td>
                <td>
                    <span>${Files.size(file.toPath())} B</span>
                </td>
                <td>
              <span>${Files.getAttribute(file.toPath(), "lastModifiedTime").toString()}</span>
                </td>
            </tr>
        </c:forEach>
    </form>

</table>
</body>
</html>