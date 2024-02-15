<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>UserTable</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>login</th>
            <th>password</th>
            <th>name</th>
            <th>bonuses</th>
            <th>bonuses</th>
        </tr>
        </thead>

        <tbody>
        <core:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.bonuses}</td>
                <td>${user.role}</td>
            </tr>
        </core:forEach>
        </tbody>
    </table>

     <input type=button value="Back" onCLick="history.back()">
     <button onclick="window.location='/environment-application/exit'">Exit</button>
</body>

</html>