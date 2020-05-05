<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <style>
        #map {
            height: 100%;
        }
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        th {
            text-align: left;
        }

    </style>
</head>
<body>
<div>
    <input id="city" type="text" placeholder="Enter the address">
</div>
<div>
    <table>
        <tr>
            <th>City:</th>
        </tr>
        <tr>
            <td><input id="city_name" type="text"></td>
        </tr>
        <tr>
            <th>Street:</th>
        </tr>
        <tr>
            <td><input id="street_name" type="text"></td>
        </tr>
        <tr>
            <th>House number:</th>
        </tr>
        <tr>
            <td><input id="house_number", type="text"></td>
        </tr>
    </table>
</div>
<div id="map">
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="/resources/js/test.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&libraries=places"></script>
</body>
</html>
