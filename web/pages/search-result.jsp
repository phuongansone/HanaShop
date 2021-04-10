<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="enums.Role" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .food-items {
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;
            }
            
            .food-item {
                margin: 1rem;
            }
        </style>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}" />
        <c:if test="${user.roleId != Role.ADMIN.value}">
            <%@include file="search-result-user.jsp" %>
        </c:if>
        
        <c:if test="${user.roleId == Role.ADMIN.value}">
            Only for admin
        </c:if>
    </body>
</html>
