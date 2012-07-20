<%-- 
    Document   : tag
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Tag details"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <c:choose>
            <c:when test="${tag == null}">
                <p><em>ERROR :</em> an error occured. Please contact the web administrator</p>
            </c:when>
            <c:otherwise>
                <div class="page-header">
                    <h1>Tag details</h1>
                </div>
                <div class="row span12">
                    <table class="data-table table">
                        <tr>
                            <th scope="row">Id</th>
                            <td><c:out value='${tag.id}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">Name</th>
                            <td><c:out value='${tag.label}'/></td>
                        </tr>
                    </table>
                </div>

                <div class="page-header">
                    <h1>Modify</h1>
                </div>
                <div class="row span12">
                    <form action="${contextPath}/rest/tag/update"
                          accept-charset="UTF-8"
                          method="POST" class="well form-horizontal">
                        <input type="hidden" name="id" value="<c:out value='${tag.id}'/>"/>
                        <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                        <div class="control-group">
                            <label class="control-label" for="update-tag-label">
                                <abbr title="Required field" class="mandatory">*</abbr> Name
                            </label>
                            <div class="controls">
                                <input id="update-tag-label" type="text" name="label" value="<c:out value='${tag.label}'/>"/>
                                <p class="help-block">
                                    Mandatory field
                                </p>
                            </div>
                        </div>
                        <div class="form-actions">
                            <%@include file="/WEB-INF/jspf/inline/buttons/update.jspf" %>
                        </div>
                    </form>
                </div>
                            
                <div class="page-header">
                    <h1>Delete</h1>
                </div>
                <div class="row span12">
                    <c:set var="delete_entity_url" value="${contextPath}/rest/tag/delete"/>
                    <c:set var="delete_entity_id" value="${tag.id}"/>
                    <%@include file="/WEB-INF/jspf/blocks/delete-entity.jspf" %>
                </div>
                
                <div class="page-header">
                    <h1>Associated Webpages</h1>
                </div>
                <div class="row span12">
                    <p>
                        View the
                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&amp;tags=${tag.id}"
                           >list of the webpages</a> having this tag.
                    </p>
                </div>
            </c:otherwise>
            </c:choose>
        </div>
                
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
