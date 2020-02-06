<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/login.css"/>
</head>


<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Sign In</h5>
                    <c:if test="${not empty param.error}">
                        <div>
                            Invalid username or/and password
                        </div>
                    </c:if>
                    <form class="form-signin" method="post"/>
                        <div class="form-label-group">
                            <input type="text" id="inputEmail" class="form-control" placeholder="Username" name="username" required autofocus>
                            <label for="inputEmail">Username</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                            <label for="inputPassword">Password</label>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                        <hr class="my-4">
                    </form>
                    <a class="small" href="/registration">Don't have an account?</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</body>
</html>