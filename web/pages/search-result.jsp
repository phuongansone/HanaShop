<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="enums.Role" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}" />
        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach items="${requestScope.FOODS}" var="food">
                        <div class="row p-2 m-2 bg-white border rounded">
                            <div class="col-md-3 mt-1">
                                <img class="img-fluid img-responsive rounded product-image" 
                                     src="/files/${food.foodImage}">
                            </div>
                            <div class="col-md-6 mt-1">
                                <h5>${food.foodName}</h5>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Phân loại</b>: ${food.category.categoryName}
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    Còn ${food.foodQuantity} sản phẩm
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Người tạo</b>: ${food.userCreated.username} 
                                    <b>vào ngày</b> ${food.createAt}
                                </div>
                                <p class="text-justify text-truncate para mb-0">
                                    <b>Mô tả</b>: ${food.description}<br><br>
                                </p>
                            </div>
                            <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                                <div class="d-flex flex-row align-items-center">
                                    <h4 class="mr-1">${food.foodPrice} VNĐ</h4>
                                </div>
                                <h6 class="text-success">Miễn phí vận chuyển</h6>
                                <c:if test="${user.roleId != Role.ADMIN.value}">
                                    <form action="MainServlet" method="POST">
                                        <div class="d-flex flex-column mt-4">
                                            <div class="mb-1">
                                                <input type="hidden" name="foodId" 
                                                       value="${food.foodId}" />
                                                <input type="number" name="quantity" 
                                                       value="1" step="1"
                                                       class="form-control"/>
                                            </div>
                                            <div class="mt-1">
                                                <button class="btn btn-primary btn-sm btn-block"  
                                                        type="submit" name="action" 
                                                        value="addToCart"
                                                        class="form-control">
                                                    Thêm vào giỏ
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>
                            </div>                            
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <nav aria-label="Page navigation">
              <ul class="pagination">
                <c:forEach items="${requestScope.PAGES}" var="pageNo">
                    <li class="page-item ${pageNo == requestScope.PAGE ? 'active' : ''}">
                        <button class="page-link" 
                                id="btnPage" value="${pageNo}" name="page">
                            ${pageNo}
                        </button>
                    </li>
                </c:forEach>
              </ul>
        </nav>
    </body>
    <script src="resources/js/search-result.js"></script>
</html>