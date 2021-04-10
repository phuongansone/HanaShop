<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit food</title>
    </head>
    <body>
        <c:set var="food" value="${requestScope.FOOD}"></c:set>
        <form name="editFoodForm" method="POST" 
              action="MainServlet" enctype="multipart/form-data"
              autocomplete="off">
            <input type="hidden" name="foodId" value="${food.foodId}" />
            <div>
                <label for="foodName">Name</label>
                <input type="text" id="foodName" 
                       name="foodName" value="${food.foodName}" required/> 
            </div>
            <div>
                <label for="category">Category</label>
                <select id="category" name="categoryId" required>
                    <c:forEach items="${requestScope.CATEGORIES}" var="category">
                        <option value="${category.categoryId}" 
                                ${food.category.categoryId == category.categoryId ? "selected" : ""}>
                            ${category.categoryName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="description">Description</label>
                <textarea type="text" id="description" name="description">
                    ${food.description}
                </textarea>
            </div>
            <div>
                <label for="price">Price</label>
                <input type="number" id="price" 
                       name="foodPrice" value="${food.foodPrice}" 
                       min="1000" max="1000000" required />
            </div>
            <div>
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" 
                       name="foodQuantity" value="${food.foodQuantity}" 
                       min="1" max="1000" required />
            </div>
            <div>
                <img src="resources/images/${food.foodImage}?t=${System.currentTimeMillis()}"
                     id="imgFood"/>
                <input type="file" name="foodImage" accept="image/png, image/jpeg" 
                       value="${food.foodImage}"/>
                <input type="hidden" value="${food.foodImage}" name="foodImage"/>
            </div>
                <div>Active: <input type="checkbox" 
                                    ${food.status == true ? "checked" : ""} 
                                    name="status" />
                </div>
            <div>
                <button type="submit" name="action" value="editFood">Save</button>
            </div>
        </form>
    </body>
</html>
