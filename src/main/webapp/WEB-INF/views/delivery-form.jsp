<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <form:form modelAttribute="deliveryPlan" action="/delivery/add" method="post">
            <table class="table">
                <c:forEach items="${deliveryPlan.places}" var="place" varStatus="loopStatus">
                    <c:choose>
                        <c:when test="${loopStatus.first}">
                            <tr>
                                <th>Start from:</th>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>${place.shortcut}</td>
                                <td>
                                    <c:if test="${place.shortcut.equals('--- , ---')}">
                                        <form:errors path="places" cssClass="errorMessage"/>
                                    </c:if>
                                </td>
                                <td><a class="btn btn-warning" href="/place/add/${deliveryPlan.getPlace(loopStatus.index).charRepresentation}" role="button">Edit</a></td>
                            </tr>
                            <tr>
                                <th>Deliver packages to:</th>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>${place.shortcut}</td>
                                <td>
                                    <c:if test="${place.shortcut.equals('--- , ---')}">
                                        <form:errors path="places" cssClass="errorMessage"/>
                                    </c:if>
                                </td>
                                <td><a class="btn btn-warning" href="/place/add/${deliveryPlan.getPlace(loopStatus.index).charRepresentation}" role="button">Edit</a></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <tr>
                    <th>Delivery date (yyyy-MM-dd):</th>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><form:input path="deliveryDate"/></td>
                    <td><form:errors path="deliveryDate" cssClass="errorMessage"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><a class="btn btn-secondary" href="/delivery/list" role="button">Back</a></td>
                    <td></td>
                    <td><input class="btn btn-primary" type="submit" value="Save" role="button"/></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
