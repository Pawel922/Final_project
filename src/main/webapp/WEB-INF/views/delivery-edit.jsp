<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new delivery plan</title>
    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
    <form:form modelAttribute="planToEdit" action="/delivery/details/${planToEdit.id}" method="post">
        <table>
            <c:forEach items="${planToEdit.places}" var="place" varStatus="loopStatus">
                <c:choose>
                    <c:when test="${loopStatus.first}">
                        <tr>
                            <td>Start from:</td>
                        </tr>
                        <tr>
                            <td>${place.shortcut}</td>
                            <td><a href="/place/edit/${planToEdit.getPlace(loopStatus.index).id}">Edit</a></td>
                            <td><form:errors path="places" cssClass="errorMessage"/></td>
                        </tr>
                        <tr>
                            <td>Deliver packages to:</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${place.shortcut}</td>
                            <td><a href="/place/edit/${planToEdit.getPlace(loopStatus.index).id}">Edit</a></td>
                            <td><form:errors path="places" cssClass="errorMessage"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <tr>
                <td>Delivery date (yyyy-MM-dd):</td>
            </tr>
            <tr>
                <td><form:input path="deliveryDate"/></td>
                <td></td>
                <td><form:errors path="deliveryDate" cssClass="errorMessage"/></td>
            </tr>
            <tr>
                <td><form:hidden path="calculationRequiredFlag"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td><a href="/delivery/list">Back</a></td>
                <td></td>
                <td><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
