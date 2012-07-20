<%-- 
    Document   : locale
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>
<%@taglib prefix="my" uri="/WEB-INF/t/my-tl.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Locale details"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <c:choose>
            <c:when test="${locale == null}">
                <p><em>ERROR :</em> an error occured. Please contact the web administrator</p>
            </c:when> <c:otherwise>
                <div class="page-header">
                    <h1>Locale details</h1>
                </div>
                <div class="row span12">
                    <table class="data-table table">
                        <tr>
                            <th scope="row">Id</th>
                            <td><c:out value="${locale.id}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Language</th>
                            <td><c:out value="${locale.language}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Full language name</th>
                            <td><c:out value="${locale.longLanguage}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Country</th>
                            <td><c:out value="${locale.country}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Full country name</th>
                            <td><c:out value="${locale.longCountry}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Representation</th>
                            <td><c:out value="${locale.label}"/></td>
                        </tr>
                    </table>
                </div>
                <div class="page-header">
                    <h1>Associated Webpages</h1>
                </div>
                <div class="row span12">
                    <div>
                        View the
                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&amp;tags-label=&amp;locales-label=<my:urlEncode value='${locale.label}'/>"
                        >list of webpages</a> having this locale.
                    </div>
                </div>
            </c:otherwise>
            </c:choose>
        </div>

        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
