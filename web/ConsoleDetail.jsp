<%-- 
    Document   : ConsoleDetail
    Created on : Nov 1, 2019, 10:57:03 PM
    Author     : shekh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Console Details</title>
        <link rel="stylesheet" type="text/css" href="Console.css" />
        <header>Console Details</header>
        <nav><%@include file="/NavBar.html" %></nav>
    </head>
    <body>
        <article>
            <b> Console Name: ${currentconsole.name} </b> </br>
            <%--Console Unique Id: ${currentconsole.consoleid} </br> --%>
            Color: ${currentconsole.colour} </br>
            Price: $${currentconsole.price}  </br>
            Memory: ${currentconsole.memory} GB </br>
            Storage: ${currentconsole.storage}
            <c:choose>
                <c:when test="${(currentconsole.storage.length()) == 3}"> GB </c:when>
                <c:otherwise> TB </c:otherwise>
            </c:choose>
            <br>
                <%--<form action="AddConsoleServlet">
                        <input type="submit" value="Add to Cart"/>                        
                        <input type="hidden" name="name" 
                               value="${currentconsole.name}"/>
                        <input type="hidden" name="consoleid"   
                               value="${currentconsole.consoleid}"/>
                </form> --%>
        </article>
        <footer>Pathak Store &copy; </footer>
    </body>
</html>
