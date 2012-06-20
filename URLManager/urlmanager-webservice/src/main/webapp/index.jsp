<%-- 
    Document   : index
    Created on : 23 mai 2012, 16:16:17
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLManager - REST API"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        
        <c:set var="page" value="home"/>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>URLManager</h1>
            </div>
            <div class="row span12">
                <h2>Available operations</h2>
                <p>
                    <ul>
                        <li><a href="/urlmanager/request.jsp">Manage the Lists</a></li>
                        <li><a href="/urlmanager/webpage.jsp">Manage the Webpages</a></li>
                        <li><a href="/urlmanager/locale.jsp">Manage the Locales</a></li>
                        <li><a href="/urlmanager/tag.jsp">Manage the Tags</a></li>
                    </ul>
                </p>
            </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
