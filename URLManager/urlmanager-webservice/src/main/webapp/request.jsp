<%-- 
    Document   : tag
    Created on : 4 juin 2012, 13:35:43
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - List"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <c:set var="page" value="lists"/>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Manage the Lists</h1>
            </div>
            <div class="row span12">
                <h2>Create a List</h2>
                <p>
                    Create a list wich will contain all webpages matching all
                    the given tags and any of the given locales.
                </p>
                <%@include file="/WEB-INF/jspf/forms/request/create.jspf" %>
            </div>

            <div class="row span12">
                <h2>Consult the Lists</h2>
                <div class="well">
                    <a href="rest/request/list">Consult the existing lists</a>
                </div>
            </div>

            <div class="row span12">
                <h2>Search a List by name</h2>
                <%@include file="/WEB-INF/jspf/forms/request/search-name.jspf" %><br/>
            </div>

            <div class="row span12">
                <h2>Search a List by id</h2>
                <%@include file="/WEB-INF/jspf/forms/request/search-id.jspf" %>
            </div>
        </div>
                
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
