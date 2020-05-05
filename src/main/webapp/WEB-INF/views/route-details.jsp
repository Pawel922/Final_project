<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route details</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<table>
    <tr>
        <th>No.</th>
        <th>From</th>
        <th>To</th>
        <th>Distance [km]</th>
    </tr>
    <c:forEach items="${route.roads}" var="road" varStatus="loopCounter">
        <tr>
            <td>${loopCounter.count}</td>
            <td>${road.startPlace.shortcut}</td>
            <td>${road.endPlace.shortcut}</td>
            <td>${road.roundDistance()}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <th>Total distance</th>
        <td>${route.roundTotalDistance()}</td>
    </tr>
    </table>
</body>
</html>
