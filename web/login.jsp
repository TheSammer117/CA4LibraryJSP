<%-- 
    Document   : login
    Created on : 07-Dec-2018, 09:55:21
    Author     : samiwise
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file = "/includes/header.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=dataBundle.getString("login_title") %></title>
    </head>
    <body>
        <h1><%=dataBundle.getString("login_h1") %></h1>
        <%
            //check if session expired
            String sessionExpired = (String) session.getAttribute("sessionExpired");
            if(sessionExpired != null){
                out.println("<b>" + sessionExpired + "</b>");
                session.removeAttribute("sessionExpired");
    
            }
            %>
            <form action="FrontController" method="post">
                <input type="hidden" name="action" value="login">
                <label>Email : </label>
                <input type="text" name="email" placeholder="Enter email" required>
                <label>Password : </label>
                <input type='password' name='password' required>
                <input type='submit' value='login'>
            </form>
    </body>
</html>
