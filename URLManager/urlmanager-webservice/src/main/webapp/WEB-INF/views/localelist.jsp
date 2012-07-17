<%-- 
    Document   : localelist
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                        <c:when test="${empty localeList.list}">
                            <p class="alert alert-info"><strong>Empty !</strong> There are no locales in database.</p>
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-info"><strong>${fn:length(localeList.list)}</strong> locales in database.</p>
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
                                        <td><c:out value="${locale.id}"/></td>
                                        <td><c:out value="${locale.language}"/></td>
                                        <td><c:out value="${locale.longLanguage}"/></td>
                                        <td><c:out value="${locale.country}"/></td>
                                        <td><c:out value="${locale.longCountry}"/></td>
                                        <td><c:out value="${locale.label}"/></td>
                                        <td>
                                            <a href="${contextPath}/rest/request/list-matching-webpages?id=0&amp;locales=<c:out value='${locale.id}'/>"
                                               title="Consult the webpages having the locale <c:out value='${locale.label} (id ${locale.id})'/>"
                                            >Associated webpages</a>
                                        </td>
                                        <td>
                                            <c:set var="details_link_url" value="${contextPath}/rest/locale/read?id=${locale.id}"/>
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
