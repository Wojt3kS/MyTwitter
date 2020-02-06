<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tweet edit</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/application.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-between">
        <a href="/" class="navbar-brand main-logo main-logo-smaller">
            <span>My Twitter</span>
        </a>
        <div class="d-flex justify-content-around">
            <h4 class="text-light mr-3">${user.username}</h4>
            <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
            <form action="<c:url value="/logout"/>" method="post">
                <input class="fa fa-id-badge" type="submit" value="Logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </nav>
</header>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="../application/side-nav.jsp" %>
        <div class="m-4 p-4 width-medium">
            <div class="m-4 p-4 border-dashed">
                <form:form modelAttribute="tweet" method="post" action="/tweet/edit">
                    <input type="hidden" name="id" value="${tweet.id}">
                    <input type="hidden" name="formattedTime" value="${tweet.formattedTime}">
                    <form:input path="text" size="80"/><br>
                    <form:errors path="text" cssClass="error"/>
                    <input type="hidden" name="jspAddress" value="/tweet/${tweet.id}">
                    <input type="submit" value="Tweet it">
                </form:form>
            </div>
        </div>
    </div>
</section>
</body>
</html>