<html>
<head>
    <title>Tare create</title>
</head>

<body>
    <table>
        <thead>
        <tr>
            <th>category</th>
            <th>volume (in litres)</th>
            <th>bonuses to accounting<th>
        </tr>
        </thead>

        <tbody>
        <form action = "tares/create" method = "post">
            <tr>
                <td><input type = "text" name = "tareCategory" placeholder = "type category"></td>
                <td><input type = "text" name = "litresVolume" placeholder = "type volume"></td>
                <td><input type = "text" name = "bonusesToAccounting" placeholder = "type bonuses"></td>
                <td><input type = "submit" value = "Create"></td>
            </tr>
        </form>
        </tbody>
    </table>
</body>

</html>