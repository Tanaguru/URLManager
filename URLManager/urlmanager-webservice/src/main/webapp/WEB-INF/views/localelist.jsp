<%-- 
    Document   : localelist
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Locale list"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <c:choose>
            <c:when test="${localeList == null}">
                <p><em>ERROR :</em> an error occured. Please contact the web administrator</p>
            </c:when> <c:otherwise>
                <div class="page-header">
                    <h1>Locale list</h1>
                </div>
                <div class="row span12">
                    <c:choose>
                        <c:when test="${localeList.list.isEmpty()}">
                            <p class="alert alert-info"><strong>Empty !</strong> There are no locales in database.</p>
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-info"><strong>${localeList.list.size()}</strong> locales in database.</p>
                            <table class="data-table table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Language</th>
                                        <th scope="col">Full language name</th>
                                        <th scope="col">Country</th>
                                        <th scope="col">Full country name</th>
                                        <th scope="col">Locale</th>
                                        <th scope="col">Associated webpages</th>
                                        <th scope="col">Consult details</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="locale" items="${localeList.list}">
                                    <tr>
                                        <td>${locale.id}</td>
                                        <td>${locale.language}</td>
                                        <td>${locale.longLanguage}</td>
                                        <td>${locale.country}</td>
                                        <td>${locale.longCountry}</td>
                                        <td>${locale.label}</td>
                                        <td>
                                            <a href="/urlmanager/rest/request/list-matching-webpages?id=0&locales=${locale.id}"
                                            title="Consult the webpages having the locale ${locale.label} (id ${locale.id})"
                                            >Associated webpages</a>
                                        </td>
                                        <td>
                                            <c:set var="details_link_url" value="/urlmanager/rest/locale/read?id=${locale.id}"/>
                                            <c:set var="details_link_title" value="Detail for locale ${locale.label} (id ${locale.id})"/>
                                            <%@include file="/WEB-INF/jspf/inline/links/details.jspf" %>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>                            
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:otherwise>
            </c:choose>
        </div>
            
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
