<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>TareTable</title>
<head>

<body>
    <table>
        <thead>
        <tr>
            <th>tareCategory</th>
            <th>litresVolume</th>
            <th>bonusesToAccounting</th>
        </tr>
        </thead>

        <tbody>
        <core:forEach var="tare" items="${tares}">
            <tr>
                <td>${tare.tareCategory}</td>
                <td>${tare.litresVolume}</td>
                <td>${tare.bonusesToAccounting}</td>
            </tr>
        </core:forEach>
        </tbody>
    </table>

     <input type=button value="Back" onCLick="history.back()">
     <button onclick="window.location='/environment-application/exit'">Exit</button>
</body>

</html>