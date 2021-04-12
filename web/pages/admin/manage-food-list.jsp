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
                        <form class="food-form">
                            <div class="row p-2 m-2 bg-white border rounded">
                                <div class="col-md-3 mt-1 p-0">
                                    <div class="d-flex flex-column">
                                        <div>
                                            <img class="img-fluid img-responsive rounded product-image" 
                                                 src="resources/images/${food.foodImage}">
                                        </div>
                                        <div>
                                            <label type="button" class="m-0 btn btn-outline-secondary btn-sm btn-block">
                                                Chọn
                                                <input type="file" name="foodImage" 
                                                       class="form-control" 
                                                       accept="image/png, image/jpeg" 
                                                       hidden required/>
                                            </label>
                                        </div>
                                    </div>                                   
                                </div>
                                <div class="col-md-9 mt-1">
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                          <span class="input-group-text width-100px">Tên</span>
                                        </div>
                                        <input type="text" class="form-control" 
                                               name="foodName"
                                               value="${food.foodName}"/>
                                    </div>
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text width-100px">Phân loại</span>
                                        </div>
                                        <select class="custom-select" id="category">
                                            <c:forEach items="${requestScope.CATEGORIES}" var="category">
                                                <option value="${category.categoryId}"
                                                        ${category.categoryId == food.category.categoryId ? 'selected' : ''}>
                                                    ${category.categoryName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text width-100px">Mô tả</span>
                                        </div>
                                        <textarea class="form-control" name="description">${food.description}</textarea>
                                    </div>
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                          <span class="input-group-text width-100px">Gía</span>
                                        </div>
                                        <input type="number" class="form-control" 
                                               step="1000" name="foodPrice"
                                               value="${food.foodPrice}"/>
                                    </div>
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                          <span class="input-group-text width-100px">Số lượng</span>
                                        </div>
                                        <input type="number" class="form-control" 
                                               step="1" name="foodQuantity"
                                               value="${food.foodQuantity}"/>
                                    </div>
                                    <div class="input-group mt-1 mb-1">
                                        <div class="input-group-prepend">
                                          <span class="input-group-text width-100px">Trạng thái</span>
                                        </div>
                                        <select name="status" class="form-control">
                                            <c:forEach items="${requestScope.STATUSES}" var="status">
                                                <option value="${status.code}" 
                                                        ${status.code == (food.status ? 1 : 0) ? 'selected' : ''}>
                                                    ${status.name}
                                                </option>
                                            </c:forEach>
                                        </select>
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
                                </div>                            
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <nav>
            <ul class="pagination">
                <c:forEach items="${requestScope.PAGES}" var="pageNo">
                    <li class="page-item ${pageNo == requestScope.PAGE ? 'active' : ''}}">
                        <button class="page-link"value="${pageNo}" name="page">
                            ${pageNo}
                        </button>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/utils.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
    <script>
        var PAGE_LINK_CLASS = '.page-link';

        // Click page button for pagination
        $(PAGE_LINK_CLASS).on('click', function(event) {
           var page = event.target.value;
           var condition = {page: page};

           condition = UTILS.getCondition(condition);

           getFoodList(condition);
        });
    </script>
</html>
