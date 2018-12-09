<%-- 
    Document   : viewBooks
    Created on : 08-Dec-2018, 10:06:25
    Author     : samiwise
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTOs.Title"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file = "/includes/header.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=dataBundle.getString("viewBooks_title")%></title>
    </head>
    <body>
        <h1><%=dataBundle.getString("viewBooks_h1")%></h1>
        <%
            ArrayList<Title> titles = (ArrayList<Title>) session.getAttribute("titleList");

            if (titles != null && !titles.isEmpty()) {

        %>
        <table>
            <tr>
                <th>Title</th>
            </tr>
            <%                    for (Title t : titles) {
            %>
            <tr>
                <td><a href="viewBook.jsp?titleID=<%=t.getTitleID()%>"><%=t.getNovelName()%></a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
                out.println("No Books found. Please try again.");
            }
        %>
        <%@ include file = "/includes/footer.jsp" %>
    </body>
</html>
