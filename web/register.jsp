<%-- 
    Document   : register
    Created on : 09-Dec-2018, 23:34:09
    Author     : samiwise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action='FrontController' method='post'>
            <input type='hidden' name='action' value='register'/>
            <label>Email : </label>
            <input type='text' name='email' placeholder='enter an email' required/>
            <label>Password : </label>
            <input type='password' name='password' placeholder="password" required/>
            <label>First Name : </label>
            <input type='text' name='firstName' placeholder="first name" required/>
            <label>Last Name : </label>
            <input type='text' name='lastName' placeholder="last name" required/>
            <label>Address Line 1 : </label>
            <input type='text' name='primaryAddressLine1'  required/>
            <label>Address Line 2 : </label>
            <input type='text' name='primaryAddressLine2' />
            <label>Town : </label>
            <input type='text' name='primaryTown' required/>
            <label>County : </label>
            <input type='text' name='primaryCounty'  required/>
            <label>Eircode : </label>
            <input type='text' name='primaryEircode' required/>
            <label>(Optional) Address Line 1 : </label>
            <input type='text' name='optAddressLine1' />
            <label>(Optional) Address Line 2</label>
            <input type='text' name='optAddressLine2' />
            <label>(Optional) Town</label>
            <input type='text' name='optTown' />
            <label>(Optional) County</label>
            <input type='text' name='optCounty' />
            <label>(Optional) Eircode</label>
            <input type='text' name='optEircode'/>
            <input type='submit' value='register'/>
        </form>
        <%@ include file = "/includes/footer.jsp" %>
    </body>
</html>

