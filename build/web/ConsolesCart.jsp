<%-- 
    Document   : ConsolesCart
    Created on : Nov 2, 2019, 6:03:44 PM
    Author     : shekh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="Console.css" />
        <header>Your Cart</header>
        <nav><%@include file="/NavBar.html" %></nav>
    </head>
    <body>
        <article>
            <table border="1">
                <c:choose>
                    <c:when test="${(empty consolelist or consolelist.size == 0)}">
                        Cart Empty!! </br>
                        No consoles added yet 
                    </c:when>
                    <c:otherwise>
                        <th>
                            Current Consoles
                        </th>
                        <c:forEach var="consoles" items="${(consolelist.consoles)}">
                            <tr>
                                <td>${consoles.name}</td>
                                <td>$${consoles.price}</td>
                                <td>
                                   <form action="RemoveConsoleServlet">
                                        <input type="submit" value="Remove from Cart"/>                        
                                        <input type="hidden" name="name" 
                                               value="${consoles.name}"/>
                                        <input type="hidden" name="consoleid" 
                                               value="${consoles.consoleid}"/>
                                   </form>
                                </td>
                            </tr>
                          </c:forEach>
                    </c:otherwise>
                </c:choose>
                        <c:if test="${(consolelist.size >= 1)}">
                            <tr>
                                <td>Cart Total: </td>
                                <td>
                                    
                                    <c:set var="totalprice" value="${0}" />
                                    <c:forEach var="consoles" items="${(consolelist.consoles)}">
                                        <c:set var="totalprice" value="${totalprice + consoles.price}" />
                                    </c:forEach>
                                    $${totalprice}
                                </td>
                            </tr>
                        </c:if>
            </table> 
        </article>
      <footer>Pathak Store &copy; </footer>     
    </body>
</html>