<%-- 
    Document   : Home
    Created on : Oct 21, 2019, 8:45:55 PM
    Author     : shekh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="Console.css" />
        <header>Consoles Home</header>
        <nav><%@include file="/NavBar.html" %></nav>
    </head>
    <body>
        <article>
            <%-- Test to see if the ArrayList to store console objects empty 
                 and if so redirecting to TestServlet to create new Objects --%>
            <c:if test="${empty ConsoleArray}">
                <c:redirect url="TestServlet"/>
            </c:if>
            <div>
            <table border="1">
            <th> Console Name </th>
            <c:forEach var="consoles" items="${ConsoleArray.consoles}">
                <tr>
                    <%-- <td>${consoles.name}</td> --%>
                    <td><a href='GetConsolesServlet?name=${consoles.name}&consoleid=${consoles.consoleid}'>
                        ${consoles.name}
                    </a>
                    </td>
                    <td>
                        <form action="AddConsoleServlet">
                            <input type="submit" value="Add to cart"/>                        
                            <input type="hidden" name="name" 
                                   value="${consoles.name}"/>
                            <input type="hidden" name="consoleid" 
                                   value="${consoles.consoleid}"/>
                        </form>
                    </td>
                </tr> 
            </c:forEach>
            </table>
            </div>
        </article>
            
      <footer>Pathak Store &copy; </footer>
    </body>
</html>
