<%-- 
    Document   : userProfile
    Created on : 07-Dec-2018, 16:26:44
    Author     : samiwise
--%>
<%@page import="DTOs.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file = "/includes/header.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title><%=dataBundle.getString("profile_title")%></title>
    </head>
    <body>
         <%           
             //get user from session
            User user = (User) session.getAttribute("loggedInUser");
             if (user != null) {
        %>
        <h1><%=dataBundle.getString("profile_h1")%> <%=user.getFirstName()%> </h1>
        <table>
            <tr>
                <th>UserID</th>
                <th>Email</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Is Admin</th>
                
            </tr>
            <tr>
                <td><%=user.getUserID()  %></td>
                <td><%=user.getEmail()  %></td>
                <td><%=user.getPassword()  %></td>
                <td><%=user.getFirstName()  %></td>
                <td><%=user.getLastName()  %></td>
                <td><%=user.getIsAdmin()  %></td>
                
            </tr>
        </table>
                <%@ include file = "/includes/footer.jsp" %>
    </body>
</html>
