<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lịch sử mua hàng</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <%@include file="common/navbar.jspf" %>
        <div class="container page-content p-3">
            <h3>Lịch sử mua hàng</h3>
            
            <form action="MainServlet" method="GET">
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="keyword">
                            Người nhận
                        </label>
                    </div>
                    <input type="text" class="form-control"
                           id="keyword" name="keyword" value="${param.keyword}"/>
                </div>
                <div class="input-group mt-2 mb-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text width-100px" 
                               for="date">
                            Ngày
                        </label>
                    </div>
                    <input type="date" class="form-control"
                           id="date" name="date" value="${param.date}"/>
                </div>
                <input type="hidden" name="action" value="viewOrderHistory" />
                <input type="submit" class="btn btn-secondary" value="Tìm kiếm"/>
            </form>

            
            <c:forEach items="${requestScope.ORDERS_HISTORY}" var="order">
                <div class="mt-3">
                    <div class="card">
                        <div class="card-header">
                            Đơn hàng số ${order.orderId}
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${order.totalPrice} VNĐ</h5>
                            <p class="card-text">Người nhận: ${order.username}</p>
                            <p>Ngày ${order.createOrderAt}</p>
                            <p class="text-muted">${order.paymentMethodName}</p>
                            <a href="#" class="btn btn-primary">Chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
    <script src="resources/js/bootstrap.min.js" /></script>
    <script>
        $('#keyword').on('change', function() {
            $('#date').val(undefined);
        });
        
        $('#date').on('change', function() {
            $('#keyword').val(undefined);
        });
    </script>
</html>
