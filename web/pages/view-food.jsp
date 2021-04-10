<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View food</title>
    </head>
    <body>
        <c:set var="food" value="${requestScope.FOOD}"></c:set>
        <h1>${food.foodName}</h1>
        <div>
            <img src="resources/images/${food.foodImage}"/>
        </div>
            <div><b>Category</b>: ${food.category.categoryName}</div>
        <div>
            <h4>Description</h4>
            <p>${food.description}</p>
        </div>
        <div><b>${food.foodPrice}</b></div>
        <div>${food.createAt}</div>
    </body>
</html>
