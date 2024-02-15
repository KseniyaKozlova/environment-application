<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>UserDelete</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>choice</th>
            <th>login</th>
            <th>password</th>
            <th>name</th>
            <th>bonuses</th>
            <th>bonuses</th>
        </tr>
        </thead>

        <tbody>
        <core:forEach var="user" items="${users}">
        <form action = "users/delete" method = "post">
            <tr>
                <td><input type = "radio" name = "id" value = "${user.id}"/></td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.bonuses}</td>
                <td>${user.role}</td>
                <td><input type = "submit" value = "Delete"></td>
            </tr>
        </form>
        </core:forEach>
        </tbody>
    </table>

</body>

</html>