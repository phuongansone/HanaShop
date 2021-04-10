<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <c:if test="${requestScope.LOGIN == 'SUCCESS'}">
            <fmt:setBundle basename="InfoMessage_en" var="msg_bundle" />
            <div class="alert alert-success" role="alert">
                <fmt:message key="user.login.success" bundle="${msg_bundle}">
                    <fmt:param value="${sessionScope.USER.username}"/>
                </fmt:message>
            </div> 
        </c:if>
        
        <div class="container mt-5">
            <h1>Search</h1>
            
            <!-- Name filter -->
            
            <div class="input-group mt-2 mb-2">
                <div class="input-group-prepend">
                    <label class="input-group-text width-100px" 
                           for="nameFilter">
                        Tên
                    </label>
                </div>
                <input type="text" class="form-control" 
                       placeholder="Lọc theo tên" 
                       id="keyword" name="keyword" />
            </div>            
            
            <!-- Category filter -->
            <div class="input-group mt-2 mb-2">
                <div class="input-group-prepend">
                    <label class="input-group-text width-100px" for="category">Phân loại</label>
                </div>
                <select class="custom-select" id="category">
                    <option selected value="0">Lọc theo loại sản phẩm</option>
                    <c:forEach items="${requestScope.CATEGORIES}" var="category">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
            
            <!-- Filter by price range -->
            <div class="mt-2 mb-2">
                <label class="custom-label">Mức giá</label>
                <c:forEach items="${requestScope.PRICE_RANGES}" var="range">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="priceRange_${range.id}" 
                               name="rangeId" 
                               value="${range.id}"
                               class="custom-control-input">
                        <label class="custom-control-label" 
                               for="priceRange_${range.id}">
                            ${range.name}
                        </label>
                        <input type="hidden" id="from_${range.id}" 
                               value="${range.from}"/>
                        <input type="hidden" id="to_${range.id}" 
                               value="${range.to}"/>
                    </div>
                </c:forEach>
            </div>
            
            <button id="btnSearch" class="mt-2">Tìm kiếm</button>
        </div>
        
        <div id="searchResult">
            <!-- div that will contain search result -->
        </div>
        
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/search.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
