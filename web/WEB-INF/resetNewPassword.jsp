<%-- 
    Document   : resetNewPassword
    Created on : Nov 25, 2020, 4:29:26 PM
    Author     : 808360
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset new password</title>
    </head>
    <body>
        <h1>Enter a new Password</h1>
        <form name="reset" method="post">
            <input type="password" name="newpassword"><br>
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
