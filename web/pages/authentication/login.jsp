<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="enums.CRUDStatus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body class="text-center">
        <c:if test="${requestScope.LOGIN == 'FAIL'}">
            <div class="alert alert-danger" role="alert">
                <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" 
                               scope="page"/>
                <fmt:message key="user.login.fail" bundle="${msg_bundle}"/>
            </div>            
        </c:if>
        
        <c:if test="${requestScope.LOGOUT == 'SUCCESS'}">
            <div class="alert alert-success" role="alert">
                <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" 
                               scope="page"/>
                <fmt:message key="user.logout.success" bundle="${msg_bundle}"/>
            </div>            
        </c:if>
        
        <form class="form-signin" name="loginForm" 
              action="/HanaShop/LoginServlet" method="POST">
            <h1 class="h3 mb-3 font-weight-normal">Login</h1>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" class="form-control" 
                   name="username" placeholder="Username" 
                   required autofocus>

            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" class="form-control" 
                   name="password" placeholder="Password" required>
            <input type="submit" class="btn btn-lg btn-primary btn-block" 
                   value="Login" >
        </form>
   </body>
   <script src="resources/js/bootstrap.min.js" />
   <script src="resources/js/jquery-3.6.0.min.js" />
</html>
        
