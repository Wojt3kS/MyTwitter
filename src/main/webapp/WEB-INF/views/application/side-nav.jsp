<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Side nav</title>
</head>
<body>
<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href='<c:url value = "/application/my-tweets"/>'>
            <span>My tweets</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href='<c:url value = "/application/tweets"/>'>
            <span>All tweets</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href='<c:url value = "/application/messages"/>'>
            <span>Messeges</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/user/edit">
            <span>Edit user</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
</li>
</ul>
</body>
</html>