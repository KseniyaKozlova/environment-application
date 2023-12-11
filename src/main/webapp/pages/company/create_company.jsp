<html>
<head>
    <title>Company creating</title>
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
        <form action = "companies/create" method = "post">
            <tr>
                <td><input type = "text" name = "name" placeholder = "type company name"></td>
                <td><input type = "text" name = "details" placeholder = "type company details"></td>
                <td><input type = "submit" value = "Create"></td>
            </tr>
        </form>
        </tbody>
    </table>
</body>

</html>