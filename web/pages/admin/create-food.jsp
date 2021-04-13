<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new food</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="../common/navbar.jspf" %>
        <div class="container page-content">
            <c:if test="${requestScope.INSERT == 'SUCCESS'}">
                <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" scope="page"/> 
                <div class="alert alert-success" role="alert">
                    <fmt:message key="food.insert.success" bundle="${msg_bundle}"/>
                </div>
            </c:if>
            <h1>Add new food</h1>
            <form name="addFoodForm" method="POST" 
                  action="MainServlet" enctype="multipart/form-data"
                  autocomplete="off">
                <div>
                    <label for="foodName">Name</label>
                    <input type="text" id="foodName" 
                           name="foodName" placeholder="Food Name" required/> 
                </div>
                <div>
                    <label for="category">Category</label>
                    <select id="category" name="categoryId" required>
                        <c:forEach items="${requestScope.CATEGORIES}" var="category">
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="description">Description</label>
                    <textarea type="text" id="description" 
                              name="description" placeholder="Description"></textarea>
                </div>
                <div>
                    <label for="price">Price</label>
                    <input type="number" id="price" 
                           name="foodPrice" placeholder="Price" 
                           step="500"
                           min="1000" max="1000000" required />
                </div>
                <div>
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" 
                           name="foodQuantity" placeholder="Quantity" 
                           min="1" max="1000" required />
                </div>
                <div>
                    Food image: <input type="file" name="foodImage" accept="image/png, image/jpeg" required/>
                </div>
                <div>
                    <button type="submit" name="action" value="createFood">Add Food</button>
                </div>
            </form>            
        </div>
    </body>
    <script src="resources/js/bootstrap.min.js" />
</html>
