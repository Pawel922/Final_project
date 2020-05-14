<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Delivery assistant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/style.css"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<header class="headers-region">
    <div class="card text-center">
        <div class="card-header">
            <ul class="nav nav-pills card-header-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/home/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/delivery/list">Delivery plans</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/home/about" tabindex="-1" aria-disabled="true">About us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/home/contact" tabindex="-1" aria-disabled="true">Contact</a>
                </li>
                <li class="nav-item">
                    <sec:authorize access="isAuthenticated()">
                        <form action="/logout" method="post">
                            <input type="submit" class="btn btn-primary" value="Log out"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </sec:authorize>
                </li>
                <li class="nav-item">
                    <sec:authorize access="isAuthenticated()">
                        <p class="nav-link disabled" tabindex="-1" aria-disabled="true">You are logged as <sec:authentication property="principal.username" /></p>
                    </sec:authorize>
                </li>
                <li class="nav-item">
                    <sec:authorize access="!isAuthenticated()">
                        <a class="nav-link active" href="/user/login">Sign in</a>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</header>
