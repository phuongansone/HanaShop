<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí sản phẩm</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../common/navbar.jspf" %>
        <div class="container page-content">
            <c:if test="${sessionScope.UPDATE}">
                <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" scope="page"/> 
                <div class="alert alert-success" role="alert">
                    <fmt:message key="food.update.success" bundle="${msg_bundle}"/>
                </div>
            </c:if>
                
            <c:if test="${sessionScope.DELETE}">
                <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" scope="page"/> 
                <div class="alert alert-success" role="alert">
                    <fmt:message key="food.delete.success" bundle="${msg_bundle}"/>
                </div>
            </c:if>
            <c:remove var="UPDATE" scope="session"/>
            <c:remove var="DELETE" scope="session"/>
            
            <h1 class="mt-1">Quản lí sản phẩm</h1>

            <div id="foodList">
                <!-- div that will contain food list for managing -->
            </div>            
        </div>

    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/utils.js"></script>
    <script src="resources/js/manage-food.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
