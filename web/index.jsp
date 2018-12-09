<%-- 
    Document   : index
    Created on : 07-Dec-2018, 08:11:45
    Author     : samiwise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file = "/includes/header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title><%=dataBundle.getString("index_title")%></title>
        
    </head>
    <body>
        <a href="login.jsp">Login!</a>
        <a href="FrontController?action=viewAllBooks">View Library catalog</a>
                <%   
                    User user = (User) session.getAttribute("loggedInUser");
//if user is logged in, display link to account
            if (user != null) {
        %>
        <a href="MyAccount.jsp">Account</a>
        <%
            }
        %>
    </body>
</html>
