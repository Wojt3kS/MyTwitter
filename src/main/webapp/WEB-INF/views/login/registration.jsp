<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="/registration.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card card-signin flex-row my-5">
                <div class="card-img-left d-none d-md-flex">
                    <!-- Background image for card set in CSS! -->
                </div>
                <div class="card-body">
                    <h5 class="card-title text-center">Register</h5>
                    <form:form class="form-signin" modelAttribute="user">
                        <div class="form-label-group">
                            <form:input  type="text" id="inputUserame" class="form-control" placeholder="Username"  name="username" path="username"/>
                            <label for="inputUserame">Username</label><br>
                            <form:errors path="username" cssClass="error"/>
                        </div>

                        <div class="form-label-group">
                            <form:input type="text" id="inputEmail" class="form-control" placeholder="Email address" required="true" name="email" path="email"/>
                            <label for="inputEmail">Email address</label>
                            <form:errors path="email" cssClass="error"/>
                        </div>

                        <div class="form-label-group">
                            <form:input type="text" id="inputFirstName" class="form-control" placeholder="First name" required="true" name="firstName" path="firstName"/>
                            <label for="inputEmail">First name</label>
                            <form:errors path="firstName" cssClass="error"/>
                        </div>

                        <div class="form-label-group">
                            <form:input type="text" id="inputLastName" class="form-control" placeholder="Last name" required="true" name="lastName" path="lastName"/>
                            <label for="inputEmail">Last name</label>
                            <form:errors path="lastName" cssClass="error"/>
                        </div>

                        <hr>

                        <div class="form-label-group">
                            <form:input type="password" id="inputPassword" class="form-control" placeholder="Password" required="true" name="password" path="password"/>
                            <label for="inputPassword">Password</label>
                            <form:errors path="password" cssClass="error"/>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputConfirmPassword" class="form-control" placeholder="Password" required="true"/>
                            <label for="inputConfirmPassword">Confirm password</label>
                            <form:errors path="password" cssClass="error"/>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
                        <a class="d-block text-center mt-2 small" href="/login">Sign In</a>
                        <hr class="my-4">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>