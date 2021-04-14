<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="common/navbar.jspf" %>
        
        <c:set var="cart" value="${sessionScope.CART}"/>
        <div class="container page-content">
            <br>
            <h5>Giỏ hàng của bạn</h5>
            <div class="d-flex justify-content-center row">
                <div class="col-md-10">
                    <c:forEach items="${cart}" var="cartItem">
                        <div class="row p-2 m-2 bg-white border rounded">
                            <div class="col-md-3 mt-1">
                                <img class="img-fluid img-responsive rounded product-image" 
                                     src="/files/${cartItem.food.foodImage}">
                            </div>
                            <div class="col-md-7 mt-1">
                                    <h5>${cartItem.food.foodName}</h5>
                                    <div class="mt-1 mb-1 spec-1">
                                        <b>Phân loại</b>: ${cartItem.food.category.categoryName}
                                    </div>
                                    <div class="mt-1 mb-1 spec-1">
                                        <b>Giá</b>: ${cartItem.food.foodPrice}
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <!-- Update form -->
                                        <form class="d-flex justify-content-between" action="MainServlet" method="POST">
                                            <input type="hidden" name="action" 
                                                   value="updateCart"/>
                                            <input type="hidden" name="foodId"
                                                   value="${cartItem.food.foodId}"/>
                                            <input type="number" step="1" 
                                                   name="quantity"
                                                   value="${cartItem.quantity}"
                                                   class="form-control mr-2"/>
                                            <button type="submit" class="btn btn-outline-success form-control mr-2">Cập nhật</button>
                                        </form>
                                        <!-- Delete form -->
                                        <form action="MainServlet" method="POST" 
                                              onsubmit="return confirm('Sản phẩm sẽ bị xóa. Bạn có muốn tiếp tục?')">
                                            <input type="hidden" name="action" 
                                                   value="removeFoodFromCart"/>
                                            <input type="hidden" name="foodId"
                                                   value="${cartItem.food.foodId}"/>
                                            <button type="submit" class="btn btn-outline-danger">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                                                </svg>
                                                Xóa
                                            </button>
                                        </form>
                                    </div>
                            </div>
                            <div class="col-md-2 mt-1">
                                <div class="mb-3">
                                    <div class="pt-4 d-flex justify-content-between">
                                        <div>
                                            <h5>Tổng</h5>
                                        </div>
                                        <div>
                                            <p>${cartItem.quantity * cartItem.food.foodPrice}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <hr class="mb-4">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h4>Tổng giá trị đơn hàng</h4>
                        </div>
                        <div>
                            <h3>${sessionScope.TOTAL_PRICE} <small class="text-muted">VNĐ</small></h3>
                        </div>
                    </div>
                    <div class="mb-4">
                        <a href="MainServlet?action=saveOrder" 
                           class="btn btn-success form-control">
                            Đặt hàng
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
