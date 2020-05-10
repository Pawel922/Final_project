<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <form:form id="register-form" modelAttribute="user" method="post">
            <div class="form-group">
                <form:label path="username">Username:</form:label>
                <form:input path="username" cssClass="form-control"/>
                <small class="form-text text-muted"><form:errors path="username" cssClass="errorMessage"/></small>
            </div>
            <div class="form-group">
                <form:label path="password">Password:</form:label>
                <form:password path="password" cssClass="form-control"/>
                <small class="form-text text-muted"><form:errors path="password" cssClass="errorMessage"/></small>
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Register" role="button">
            </div>
        </form:form>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
