<%-- 
    Document   : tag
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Tag list"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>

        <div class="container">
            <div class="page-header">
                <h1>Tag list</h1>
            </div>
            <div class="row span12">
                <p class="alert alert-info">
                    <strong>${fn:length(tagList.list)}</strong> tags in database.
                </p>
                <table class="data-table table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Associated Webpages</th>
                            <th scope="col">Details</th>
                    </thead>
                    <tbody>
                        <c:forEach var="tag" items="${tagList.list}">
                            <tr>
                                <td>${tag.id}</td>
                                <td>${tag.label}</td>
                                <td>
                                    <a href="/urlmanager/rest/request/list-matching-webpages?id=0&tags-label=${tag.label}&locales-label="
                                       title="Webpages associated with the tag ${tag.label} (id ${tag.id})"
                                       >Associated Webpages</a>
                                </td>
                                <td>
                                    <c:set var="details_link_url" value="/urlmanager/rest/tag/read?id=${tag.id}"/>
                                    <c:set var="details_link_title" value="Detail for tag no ${tag.id}"/>
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
