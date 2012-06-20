<%-- 
    Document   : tag
    Created on : 4 juin 2012, 13:35:43
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Locale"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <c:set var="page" value="locales"/>
        <%@include file="../WEB-INF/jspf/template/header.jspf" %>

        <div class="container">
            <div class="page-header">
                <h1>Manage the Locales</h1>
            </div>
            <div class="row span12">
                <h2>Consult the Locales</h2>
                <div class="well">
                    <a href="rest/locale/list">Consult the locale list</a>
                </div>
            </div>
            <div class="row span12">
                <h2>Search a Locale by id</h2>
                <%@include file="../WEB-INF/jspf/forms/locale/search-id.jspf" %>
            </div>
        </div>

        <%@include file="../WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
