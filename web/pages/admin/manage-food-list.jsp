<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="container mt-5 mb-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach items="${requestScope.FOODS}" var="food">
                        <div class="row p-2 m-2 bg-white border rounded">
                            <div class="col-md-3 mt-1 p-0">
                                <div class="d-flex flex-column food-img">
                                    <div>
                                        <img class="img-fluid img-responsive rounded product-image" 
                                             src="/files/${food.foodImage}">
                                    </div>
                                </div>                                   
                            </div>
                            <div class="col-md-9 mt-1">
                                <h5>${food.foodName}</h5>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Phân loại</b>: ${food.category.categoryName}
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Mô tả</b>: ${food.description}
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Gía</b>: ${food.foodPrice} VNĐ
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Số lượng</b>: ${food.foodQuantity} sản phẩm
                                </div>
                                <div class="mt-1 mb-1 spec-1">
                                    <b>Trạng thái</b>: ${food.status ? 
                                                         'Đang kinh doanh' : 'Ngừng kinh doanh'}
                                </div>
                                <div class="mt-1 mb-1">
                                    <p class="d-inline-block mb-0"><b>Người tạo</b>: ${food.userCreated.username}
                                    <b>Ngày tạo</b>: ${food.createAt}</p>
                                </div>

                                <c:if test="${not empty food.userUpdate and not empty food.updateAt}">
                                    <div class="mt-1 mb-1">
                                        <p class="d-inline-block mb-0"><b>Người cập nhật</b>: ${food.userUpdate.username}
                                        <b>Ngày cập nhật</b>: ${food.updateAt}</p>
                                    </div>
                                </c:if>
                                <div class="d-flex justify-content-end">
                                    <a class="btn btn-success m-1" 
                                       href="MainServlet?action=editFood&foodId=${food.foodId}">Edit</a>
                                    <form class="delete-form" method="POST" action="MainServlet">
                                        <input type="hidden" name="action" value="deleteFood" />
                                        <button class="btn btn-danger m-1 btn-delete" 
                                                type="submit"
                                                value="${food.foodId}" name="foodId"
                                                name="foodId">Delete</button>
                                    </form>
                                </div>

                            </div>                            
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <nav>
            <ul class="pagination">
                <c:forEach items="${requestScope.PAGES}" var="pageNo">
                    <li class="page-item ${pageNo == requestScope.PAGE ? 'active' : ''}}">
                        <button class="page-link" value="${pageNo}" name="page">
                            ${pageNo}
                        </button>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </body>
    <script src="resources/js/manage-food-list.js"></script>
</html>
