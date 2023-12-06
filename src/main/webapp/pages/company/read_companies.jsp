<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CompanyTable</title>
<head>

<body>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>details</th>
        </tr>
        </thead>

        <tbody>
        <core:forEach var="company" items="${companies}">
            <tr>
                <td>${company.companyName}</td>
                <td>${company.details}</td>
            </tr>
        </core:forEach>

         <button onclick="window.location='exit'">Exit</button>
        </tbody>
    </table>
</body>

</html>