<html>
<head>
    <title>Tare create</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>category</th>
            <th>volume (in litres)</th>
            <th>bonuses to accounting<th>
        </tr>
        </thead>

        <tbody>
        <form action = "tares/update" method = "post">
            <tr>
                <td><input type = "text" name = "id" placeholder = "type id"></td>
                <td><input type = "text" name = "tareCategory" placeholder = "type category"></td>
                <td><input type = "text" name = "litresVolume" placeholder = "type volume"></td>
                <td><input type = "text" name = "bonusesToAccounting" placeholder = "type bonuses"></td>
                <td><input type = "submit" value = "Update"></td>
            </tr>
        </form>
        </tbody>
    </table>
</body>

</html>