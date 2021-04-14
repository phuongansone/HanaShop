<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin khách hàng</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="common/navbar.jspf" %>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <div class="container page-content">
            <div class="py-5 text-center">
                <h2>Thông tin đơn hàng</h2>
            </div>
            <div class="row g-5">
                <div class="col-md-5 col-lg-4 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-primary">Sản phẩm</span>
                        <span class="badge bg-primary rounded-pill">${fn:length(cart)}</span>
                    </h4>
                    <ul class="list-group mb-3">
                        <c:forEach items="${cart}" var="cartItem">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">${cartItem.food.foodName}</h6>
                                </div>
                                <span class="text-muted">${cartItem.quantity * cartItem.food.foodPrice}</span>
                            </li>                            
                        </c:forEach>
                        
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Total (VNĐ)</span>
                            <strong>${sessionScope.TOTAL_PRICE}</strong>
                        </li>
                    </ul>
                </div>
                <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">Thông tin khách hàng</h4>
                    <form action="MainServlet" method="POST" class="needs-validation" novalidate>
                        <div class="row g-3">
                            <div class="col-12">
                                <label for="fullname" class="form-label">Họ và Tên: </label>
                                <input type="text" class="form-control" name="username" id="fullname" required>
                                <div class="invalid-feedback">
                                    Xin vui lòng nhập tên
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                                <div class="invalid-feedback">
                                    Xin vui lòng nhập địa chỉ email hợp lệ
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="address" class="form-label">Địa chỉ giao hàng</label>
                                <input type="text" class="form-control" 
                                       id="address" name="address" required>
                                <div class="invalid-feedback">
                                    Xin vui lòng nhập địa chỉ giao hàng
                                </div>
                            </div>
                            
                            <div class="col-12">
                                <label for="phone" class="form-label">Số điện thoại người nhận</label>
                                <input type="tel" class="form-control" 
                                       id="phone" name="phone" required>
                                <div class="invalid-feedback">
                                    Xin vui lòng nhập số điện thoại
                                </div>
                            </div>
                            <input type="hidden" name="totalPrice" value="${sessionScope.TOTAL_PRICE}"/>
                        </div>
                        
                        <hr class="my-4">

                        <h4 class="mb-3">Payment</h4>

                        <div class="my-3">
                            <div class="form-check">
                                <input id="cash" name="paymentMethod" type="radio" 
                                       value="1" class="form-check-input" checked required>
                                <label class="form-check-label" for="cash">Thanh toán bằng tiền mặt khi nhận hàng</label>
                            </div>
                        </div>

                        <hr class="my-4">
                        <input type="hidden" name="action" value="saveOrder" />
                        <button class="w-100 btn btn-success" type="submit">Đặt hàng</button>
                        <hr class="my-4">
                    </form>
                </div>
            </div>
        </div>
        
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
