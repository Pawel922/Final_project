<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <form:form id="place-form" modelAttribute="place" method="post">
            <div class="form-group">
                <form:label path="city">City:</form:label>
                <form:input path="city" cssClass="form-control"/>
                <small class="form-text text-muted"><form:errors path="city" cssClass="errorMessage"/></small>
            </div>
            <div class="form-group">
                <form:label path="street">Street:</form:label>
                <form:input path="street" cssClass="form-control"/>
                <small class="form-text text-muted"><form:errors path="street" cssClass="errorMessage"/></small>
            </div>
            <div class="form-group">
                <form:label path="houseNumber">House number:</form:label>
                <form:input path="houseNumber" cssClass="form-control"/>
                <small class="form-text text-muted"><form:errors path="houseNumber" cssClass="errorMessage"/></small>
            </div>
            <div class="form-group">
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-secondary" href="/delivery/add" role="button">Back</a>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <a class="btn btn-secondary" href="/home/trial" role="button">Back</a>
                </sec:authorize>
                <form:hidden path="charRepresentation"/>
                <input class="btn btn-primary" type="submit" value="Add" role="button">
            </div>
        </form:form>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
