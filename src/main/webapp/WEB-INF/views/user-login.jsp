<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle-form-login">
        <form method="post" action="/user/login">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <input type="text" name="username" id="username" class="form-control" placeholder="username">
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="password">
            <div class="checkbox mb-3">
                <label>
                    Are you a new user? <a class="btn btn-secondary" href="/user/register" role="button">Register</a>
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>