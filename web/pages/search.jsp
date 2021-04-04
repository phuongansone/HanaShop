<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="common/navbar.jspf" %>
        <h1>Menu</h1>
        <div>
            Category: 
            <select name="categoryId" id="category">
                <option selected value="0">All</option>
                <c:forEach items="${requestScope.CATEGORIES}" var="category">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select>
            <div id="searchResult">
                
            </div>
        </div>
        
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/search.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
