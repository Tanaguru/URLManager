<%-- 
    Document   : tag
    Created on : 4 juin 2012, 13:35:43
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Tag"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <c:set var="page" value="tags"/>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Manage the Tags</h1>
            </div>

            <div class="row span12">
                <h2>Create a Tag</h2>
                <%@include file="/WEB-INF/jspf/forms/tag/create.jspf" %>
            </div>

            <div class="row span12">
                <h2>Consult the existing Tags</h2>
                <div class="well">
                    <a href="rest/tag/list">Consult the tag list</a>.
                </div>
            </div>

            <div class="row span12">
                <h2>Search a Tag by name</h2>
                <%@include file="/WEB-INF/jspf/forms/tag/search-name.jspf" %>
            </div>

            <div class="row span12">
                <h2>Search a Tag by id</h2>
                <%@include file="/WEB-INF/jspf/forms/tag/search-id.jspf" %>
            </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
