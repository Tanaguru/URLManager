<%-- 
    Document   : webpagelist
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Webpage list"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Webpage list</h1>
            </div>
            <div class="row span12">
                <p class="alert alert-info">
                    <strong>${fn:length(webpageList.list)}</strong> webpages found.
                </p>
                <table class="data-table table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Is a website root</th>
                            <th scope="col"><abbr title="Uniform Resource Locator">URL</abbr></th>
                            <th scope="col">Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="webpage" items="${webpageList.list}">
                            <tr>
                                <td>${webpage.id}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${webpage.isRoot}">
                                            <span class="icon-ok"></span> yes
                                        </c:when>
                                        <c:otherwise>
                                            <span class="icon-remove"></span> no
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="${webpage.URL}">${webpage.URL}</a></td>
                                <td>
                                    <c:set var="details_link_url" value="${contextPath}/rest/webpage/read?id=${webpage.id}"/>
                                    <c:set var="details_link_title" value="Details of webpage of id ${webpage.id}"/>
                                    <%@include file="/WEB-INF/jspf/inline/links/details.jspf" %>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
