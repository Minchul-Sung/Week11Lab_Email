<%-- 
    Document   : reset
    Created on : Nov 25, 2020, 4:22:08 PM
    Author     : 808360
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <form action="reset" method="post">
            <label for="email">Email Address : </label>
            <input type="text" name="email" id="email">
            <input type="submit" value="Submit"><br>
        </form>        
    </body>
</html>
