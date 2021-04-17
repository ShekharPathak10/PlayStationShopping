<%-- 
    Document   : CreateConsole
    Created on : Nov 18, 2019, 5:08:18 PM
    Author     : shekh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Console</title>
        <link rel="stylesheet" type="text/css" href="Console.css" />
        <header>Select the properties of your Console</header>
        <nav><%@include file="/NavBar.html" %></nav>
    </head>
    <body>
        <article>
            <form action="ValidateConsoleServlet">
                
                Console Name:
                <input type="text" name="name" value="${param.name}"/>
                    <c:if test="${errors.nameMissing}">
                        Name cannot be empty!
                    </c:if>
                </br>
                    
                
                Color: 
                <select name="color">
                    <option value="choose">choose</option>
                    <option value="black">black</option>
                    <option value="white">white</option>
                    <option value="silver">silver</option>
                    <option value="gold">gold</option>
                </select>
                    <c:if test="${errors.colorMissing}">
                        Must select a Console Color!
                    </c:if>
                
                </br>
                
                
                Price:
                <input type="text" name="price" value="${param.price}"/>
                    <c:if test="${errors.priceMissing}">
                        Must enter a price for the Console!
                    </c:if>
                    <c:if test="${errors.priceNotNumeric}">
                        The value for the price must be a whole number
                    </c:if>
                    <c:if test="${errors.priceTooSmall}">
                        The price must not be a negative number!
                    </c:if>
                </br>
                
                
                Memory: 
                <select name="memory" id="list">
                    <option value="choose">choose</option>
                    <option value="256">256 MB</option>
                    <option value="4">4 GB</option>
                    <option value="8">8 GB</option>
                </select>
                    <c:if test="${errors.memoryMissing}">
                        Please select the Memory Size of the console!
                    </c:if>
                
                </br>
               
                
                Storage:
                <input type="text" name="storage" value="${param.Storage}"/>
                <c:if test="${errors.storageMissing}">
                            Must enter a storage capacity for the Console!
                    </c:if>
                    <c:if test="${errors.storageIllegal}">
                        The value for the storage must be 256, 500 if GB, OR 1 or 2 if TB
                    </c:if> 
                </br>
       
                <input type="submit" value="CREATE CONSOLE" />   
            </form>
        </article>
        <footer>Pathak Store &copy; </footer>
    </body>
</html>
