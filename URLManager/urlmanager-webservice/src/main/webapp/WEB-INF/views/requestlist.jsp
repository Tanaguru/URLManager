<%-- 
    Document   : requestlist
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Request list"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Existing Lists</h1>
            </div>            
            <div class="row">
                <p class="alert alert-info">
                    <strong>${requestList.list.size()}</strong> lists in database.
                </p>
                <table class="data-table table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Label</th>
                            <th scope="col">Consult list's webpages</th>
                            <th scope="col">Details</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="request" items="${requestList.list}">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.label}</td>
                            <td>
                                <a href="/urlmanager/rest/request/list-matching-webpages?id=${request.id}"
                                   title="Webpages in the list ${request.label} (id ${request.id})"
                                >view list</a>
                            </td>
                            <td>
                                <c:set var="details_link_url"
                                    value="/urlmanager/rest/request/read?id=${request.id}"/>
                                <c:set var="details_link_title"
                                    value="Detail for request ${request.label} (id ${request.id})"/>
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
