<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <form:form modelAttribute="planToEdit" action="/delivery/details/${planToEdit.id}" method="post">
            <table class="table">
                <c:forEach items="${planToEdit.places}" var="place" varStatus="loopStatus">
                    <c:choose>
                        <c:when test="${loopStatus.first}">
                            <tr>
                                <th>Start from:</th>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>${place.shortcut}</td>
                                <td><form:errors path="places" cssClass="errorMessage"/></td>
                                <td><a class="btn btn-warning" href="/place/edit/${planToEdit.getPlace(loopStatus.index).id}" role="button">Edit</a></td>
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
                                <td><form:errors path="places" cssClass="errorMessage"/></td>
                                <td><a class="btn btn-warning" href="/place/edit/${planToEdit.getPlace(loopStatus.index).id}" role="button">Edit</a></td>
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
                    <td><form:hidden path="calculationRequiredFlag"/></td>
                    <td></td>
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
