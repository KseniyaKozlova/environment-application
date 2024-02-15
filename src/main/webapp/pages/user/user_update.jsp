<html>
<head>
    <title>User create</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>login</th>
            <th>password</th>
            <th>name<th>
            <th>role</th>
        </tr>
        </thead>

        <tbody>
        <form action = "users/update" method = "post">
            <tr>
                <td><input type = "text" name = "id" placeholder = "type user id"></td>
                <td><input type = "text" name = "login" placeholder = "type user login"></td>
                <td><input type = "text" name = "password" placeholder = "type user password"></td>
                <td><input type = "text" name = "name" placeholder = "type user password"></td>
                <td><input type = "text" name = "role" placeholder = "type user role"></td>
                <td><input type = "submit" value = "Update"></td>
            </tr>
        </form>
        </tbody>
    </table>
</body>

</html>