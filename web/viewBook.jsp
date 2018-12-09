<%-- 
    Document   : viewBook
    Created on : 09-Dec-2018, 20:43:34
    Author     : samiwise
--%>

<%@page import="DTOs.Title"%>
<%@page import="DAOs.TitleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file = "/includes/header.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=dataBundle.getString("viewBook_title")%></title>
    </head>
    <body>
        <h1><%=dataBundle.getString("viewBook_h1")%></h1>
        <%
            String idVal = request.getParameter("titleID");
            if (idVal != null) {
                int titleID = 0;
                try {
                    titleID = Integer.parseInt(idVal);
                } catch (NumberFormatException e) {
                    String errorMessage = "Text was supplied for the titleID (INSTEAD OF A NUMBER)";
                    session.setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("error.jsp");
                }

                TitleDAO tdao = new TitleDAO("librarydb");
                Title t = tdao.searchByID(titleID);
                if (t != null) {
        %>
        <table cellspacing="10">
            <tr>
                <th>ID</th>
                <th>Book Title</th>
                <th>Author</th>
                <th>Summary</th>
                <th>Stock</th>
                <th>copies currently loaned</th>
            </tr>
            <tr>
                <td><%= t.getTitleID()%> </td>
                <td><%= t.getNovelName()%> </td>
                <td><%= t.getAuthor()%> </td>
                <td><textarea><%= t.getTitleDescription()%></textarea> </td>
                <td><%= t.getStock()%> </td>
                <td><%= t.getOnLoan()%> </td>
            </tr>
        </table>
        <%
        } else {
        %>
        <div><b>No Book was found with that ID... :(</b></div>
        <%
                }
            } else {
                String errorMessage = "No Title ID was supplied.";
                session.setAttribute(errorMessage, errorMessage);
                response.sendRedirect("error.jsp");

            }
        %>
        <%@ include file = "/includes/footer.jsp" %>
    </body>
</html>
