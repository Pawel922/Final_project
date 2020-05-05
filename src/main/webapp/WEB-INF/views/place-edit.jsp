<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit place</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
    <form:form modelAttribute="placeToEdit" method="post">
        <table>
            <tr><td><form:label path="city">City</form:label></td></tr>
            <tr><td><form:input path="city"/></td></tr>
            <tr><td><form:errors path="city" cssClass="errorMessage"/></td></tr>

            <tr><td><form:label path="street">Street</form:label></td></tr>
            <tr><td><form:input path="street"/></td></tr>
            <tr><td><form:errors path="street" cssClass="errorMessage"/></td></tr>

            <tr><td><form:label path="houseNumber">House number:</form:label></td></tr>
            <tr><td><form:input path="houseNumber"/></td>
            <tr><td><form:errors path="houseNumber" cssClass="errorMessage"/></td></tr>

            <tr><td><form:hidden path="charRepresentation"/></td></tr>
            <tr>
                <td><a href="/delivery/details/${planToEdit.id}">Back</a></td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
