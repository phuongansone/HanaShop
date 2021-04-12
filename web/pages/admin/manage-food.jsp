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
        <div class="container page-content">
            <h1>Quản lí sản phẩm</h1>
            <%@include file="../common/navbar.jspf" %>

            <div id="foodList">
                <!-- div that will contain food list for managing -->
            </div>            
        </div>

    </body>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/utils.js"></script>
    <script src="resources/js/manage-food.js"></script>
    <script src="resources/js/bootstrap.min.js" /></script>
</html>
