<%-- 
    Document   : header
    Created on : 07-Dec-2018, 08:18:37
    Author     : samiwise
--%>
<%@page import="DTOs.User"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Retrieve the appropriate Locale - check if it's already been set within the site
    Locale clientLocale = (Locale) session.getAttribute("currentLocale");

    //If not locale set i.e. first time here or session has ended/timed out
    if (clientLocale == null) {
        //get locale for client's browser
        clientLocale = request.getLocale();
        //save it a the selected locale
        session.setAttribute("currentLocale", clientLocale);
    }
%>
<!-- form for changing language-->
<form action="FrontController" method="post">
    <input type="hidden" name="action" value="changeLanguage"/>
    <!--submit value whenever value of drop down changes-->
    <select name="language" onchange="this.form.submit()">
        <%
            String language = (String) session.getAttribute("language");
            if (language == null || language.equals("en")) {
        %>
        <option value="en" selected>English</option>
        <option value="fr">Français</option>
        <%
        } else {
        %>
        <option value="en">English</option>
        <option value="fr" selected>Français</option>
        <%
            }
        %>
    </select>
</form>
<!-- Create the resource bundle and include it on all pages -->
<%
    ResourceBundle dataBundle = (ResourceBundle) session.getAttribute("dataBundle");
    //if no bundle stored
    if (dataBundle == null) {
        //make on with the client current locale
        dataBundle = ResourceBundle.getBundle("properties.LibrarySystem", clientLocale);

//store this resouce bundle for future use
        session.setAttribute("dataBundle", dataBundle);
    }
%>
<div>
    <%
        //get user from session
        User user = (User) session.getAttribute("loggedInUser");
        //if user is logged in, display logout
        if (user != null) {
    %>
    <a href="FrontController?action=logout"><%=dataBundle.getString("logout")%></a>
    <%} //Otherwise, display login form
    else {
    %>
    <%@include file="login,jsp" %>
    <% }%>
</div>