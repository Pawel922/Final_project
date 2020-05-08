<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <form:form id="place-edit" modelAttribute="placeToEdit" method="post">
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
                <a class="btn btn-secondary" href="/delivery/details/${planToEdit.id}" role="button">Back</a>
                <form:hidden path="charRepresentation" cssClass="form-control"/>
                <input class="btn btn-primary" type="submit" value="Save" role="button">
            </div>
        </form:form>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
