<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery plans list</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<table>
    <tr>
        <th>No.</th>
        <th>Delivery date</th>
        <th>Action</th>
        <th>Action</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${deliveryPlans}" var="deliveryPlan" varStatus="loopCounter">
        <tr>
            <td>${loopCounter.count}</td>
            <td>${deliveryPlan.deliveryDate}</td>
            <td><a href="/delivery/details/${deliveryPlan.id}">Details</a></td>
            <td><a href="/route/find/${deliveryPlan.id}">Show route</a></td>
            <td><a href="/delivery/delete/${deliveryPlan.id}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><a href="/delivery/add">Add new</a></td>
    </tr>
    </table>
</body>
</html>
