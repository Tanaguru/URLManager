<%-- 
    Document   : matching-webpages
    Created on : 12 juin 2012, 08:45:12
    Author     : bcareil
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%-- Set is Custom list, set to true whether the list as been created on the fly --%>
    <c:set var="isCustomList" value="${request.label == null}"/>
    <%-- Set localesAsString from the request.locales list --%>
    <c:set var="localesAsString_locales" value="${request.locales}"/>
    <%@include file="/WEB-INF/jspf/setter/locales-as-string.jspf" %>
    <%-- Set tagsAsString from the request.tags list --%>
    <c:set var="tagsAsString_tags" value="${request.tags}"/>
    <%@include file="/WEB-INF/jspf/setter/tags-as-string.jspf" %>
    <%-- Set the initial title --%>
    <c:choose>
        <c:when test="${isCustomList}">
            <c:set var="title" value="Custom List"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="URLM - List ${request.label}"/>
        </c:otherwise>
    </c:choose>
    <%-- Concat the tags and locales --%>
    <c:if test="${not empty tagsAsString}">
        <c:set var="title" value="${title}, ${tagsAsString}"/>
    </c:if>
    <c:if test="${not empty localesAsString}">
        <c:set var="title" value="${title}, ${localesAsString}"/>
    </c:if>
    <%-- Concant the end of the title --%>
    <c:set var="title" value="${title} - URLM"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>
                    Consulting
                    <c:choose>
                        <c:when test="${isCustomList}">
                            a custom list
                        </c:when>
                        <c:otherwise>
                            list <em><c:out value="${request.label}"/></em>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${not empty tagsAsString}">
                        of webpages containing the tags <c:out value="${tagsAsString}"/>
                    </c:if>
                    <c:if test="${not empty localesAsString}">
                        <c:choose>
                            <c:when test="${empty tagsAsString }">
                                of webpages containing one of the locales
                            </c:when>
                            <c:otherwise>
                                and one of the locales
                            </c:otherwise>
                        </c:choose>
                        <c:out value="${localesAsString}"/>
                    </c:if>
                </h1>
            </div>
            <div class="row span12">
                <c:choose>
                    <c:when test="${empty webpageList.list}">
                        <p class="alert alert-info">
                            <strong>Empty</strong> : No webpages match the list criteria.
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="alert alert-info">
                            <strong>${fn:length(webpageList.list)}</strong> webpages in this list.
                        </p>
                        <table class="data-table table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Is a website root</th>
                                    <th scope="col"><abbr title="Uniform Resource Locator">URL</abbr></th>
                                    <th scope="col">Tags</th>
                                    <th scope="col">Locales</th>
                                    <th scope="col">Details</th>                            
                                </tr>
                            </thead>
                            <tbody>
                    <c:forEach var="webpage" items="${webpageList.list}">
                                <tr>
                                    <td><c:out value="${webpage.id}"/></td>
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
                                    <td><a href=<c:out value='"${webpage.URL}"'/>><c:out value="${webpage.URL}"/></a></td>
                                    <td>
                                        <c:set var="isFirstLoop" value="${true}"/>
                                        <c:forEach var="tag" items="${webpage.tags}">
                                            <c:choose>
                                                <c:when test="${isFirstLoop}">
                                                    <c:set var="isFirstLoop" value="${false}"/>
                                                </c:when>
                                                <c:otherwise>,</c:otherwise>
                                            </c:choose>
                                                <a href="${contextPath}/rest/request/list-matching-webpages?id=0&amp;tags-label=<c:out value='${tag.label}'/>"
                                                   title="Webpages having the tag of id <c:out value='${tag.id}'/>"
                                                ><c:out value="${tag.label}"/></a>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:set var="isFirstLoop" value="${true}"/>
                                        <c:forEach var="locale" items="${webpage.locales}">
                                            <c:choose>
                                                <c:when test="${isFirstLoop}">
                                                    <c:set var="isFirstLoop" value="${false}"/>
                                                </c:when>
                                                <c:otherwise>,</c:otherwise>
                                            </c:choose>
                                                <a href="${contextPath}/rest/request/list-matching-webpages?id=0&amp;locales-label=<c:out value='${locale.label}'/>"
                                                title="Webpages having the locale of id <c:out value='${locale.id}'/>"
                                                ><c:out value="${locale.label}"/></a>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:set var="details_link_url" value="${contextPath}/rest/webpage/read?id=${webpage.id}"/>
                                        <c:set var="details_link_title" value="Details of the webpage of id ${webpage.id}"/>
                                        <%@include file="/WEB-INF/jspf/inline/links/details.jspf" %>
                                    </td>
                                </tr>
                    </c:forEach>
                            </tbody>
                        </table>                            
                    </c:otherwise>
                </c:choose>
            </div>
            <c:choose>
                <c:when test="${isCustomList}">
                    <div class="page-header">
                        <h1>Create this list</h1>                        
                    </div>
                    <div class="row span12">
                        <%@include file="/WEB-INF/jspf/forms/request/create.jspf"%>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="page-header">
                        <h1>List details</h1>
                    </div>
                    <div class="row span12">
                        <div class="well">
                            <a href="${contextPath}/rest/request/read?id=<c:out value='${request.id}'/>">View list details</a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
