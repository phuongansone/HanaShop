<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/main.css"/>
    </head>
    <body>
        <h1>Login</h1>
        <form name="loginForm" action="/HanaShop/LoginServlet" method="POST">
            <div>
                <label>Username</label>
                <input type="text" placeholder="Username" name="username" />
            </div>
            <div>
                <label>Password</label>
                <input type="password" placeholder="Password" name="password" />
            </div>
            <div>
                <input type="submit" value="Login" />
            </div>
        </form> 
   </body>
   <script src="resources/js/bootstrap.min.js" />
</html>
        
