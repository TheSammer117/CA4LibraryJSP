<%-- 
    Document   : error
    Created on : 07-Dec-2018, 16:27:28
    Author     : samiwise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@include file = "/includes/header.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=dataBundle.getString("error_title")%></title>
    </head>
    <body>
        <h1><%=dataBundle.getString("error_h1")%></h1>
                        <%
            // Get the error message variable out of the session
            Object msg = session.getAttribute("errorMessage");
            // Cast it to a String so we can use it
            String error = (String) msg;
            if(error != null){
            // Display the message
%>

<div><%=error%></div>
<%
    }
 // We have finished with the results of this action
            // so now we can remove the value from the session
            session.removeAttribute("errorMessage");
            // This makes sure that old error messages 
            // don't mistakenly get printed out later
%>
    </body>
</html>
