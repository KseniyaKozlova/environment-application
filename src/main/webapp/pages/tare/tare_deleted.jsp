<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>TareDelete</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>id</th>
        </tr>
        </thead>

        <tbody>
                <form action = "tares/delete" method = "post">
                    <tr>
                        <td><input type = "text" name = "id" placeholder = "type id"></td>
                        <td><input type = "submit" value = "Delete"></td>
                    </tr>
                </form>
        </tbody>
    </table>

</body>

</html>