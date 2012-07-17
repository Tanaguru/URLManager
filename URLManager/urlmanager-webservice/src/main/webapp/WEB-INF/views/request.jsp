<%-- 
    Document   : request
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList,java.util.Collection"
        import="org.opens.urlmanager.entity.tag.Tag,org.opens.urlmanager.entity.locale.Locale"
        import="org.opens.urlmanager.entity.request.Request"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - List details"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
        <c:choose>
        <c:when test="${request == null}">
            <p class="alert-error"><strong>ERROR :</strong> an error occured. Please contact the web administrator</p>
        </c:when> <c:otherwise>
            <%-- FIXME: do something more generic with less code --%>
            <%
            Collection<Long> requestTagsId = new ArrayList<Long>();
            Collection<Long> requestLocalesId = new ArrayList<Long>();
            Request req = (Request)request.getAttribute("request");
            
            for (Tag tag : req.getTags()) {
                requestTagsId.add(tag.getId());
            }
            for (Locale locale : req.getLocales()) {
                requestLocalesId.add(locale.getId());
            }
            request.setAttribute("requestTagsId", requestTagsId);
            request.setAttribute("requestLocalesId", requestLocalesId);
            %>
            <div class="page-header">
                <h1>List details</h1>
            </div>
            <div class="row span12">
                <table class="data-table table">
                    <tr>
                        <th scope="row">Id</th>
                        <td><c:out value="${request.id}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Name</th>
                        <td><c:out value="${request.label}"/></td>
                    </tr>
                </table>
            </div>

            <div class="page-header">
                <h1>Modify</h1>
            </div>
            <div class="row span12">
                <form method="POST" action="${contextPath}/rest/request/update"
                    class="well form-horizontal">
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <input type="hidden" name="id" value="<c:out value='${request.id}'/>"/>
                    <input type="hidden" name="tags" value="<c:out value='${requestTagsId}'/>"/>
                    <input type="hidden" name="locales" value="<c:out value='${requestLocalesId}'/>"/>
                    <div class="control-group">
                        <label class="control-label" for="update-request-label">
                            <abbr title="Required field" class="mandatory">*</abbr> Name
                        </label>
                        <div class="controls">
                            <input id="update-request-label" type="text" name="label" value="<c:out value='${request.label}'/>"/>
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
                <c:set var="delete_entit_url"
                    value="${contextPath}/rest/request/delete?id='${request.id}"/>
                <%@include file="/WEB-INF/jspf/blocks/delete-entity.jspf" %>
            </div>
            
            <div class="page-header">
                <h1>Show content</h1>
            </div>
            <div class="row span12">
                <div class="well">
                    <a href="${contextPath}/rest/request/list-matching-webpages?id=<c:out value='${request.id}'/>"
                    >Consult webpage list</a>.
                </div>
            </div>

            <div class="page-header">
                <h1>Associated tags</h1>
            </div>
            <div class="row span12">
                <c:choose>
                    <c:when test="${empty request.tags}">
                        <p class="alert alert-info">This list has no tags.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Unlink</th>
                                    <th scope="col">Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            for (Tag tag : req.getTags()) {
                                Collection<Long> requestTagsIdWOCurrent =
                                        new ArrayList(requestTagsId);

                                requestTagsIdWOCurrent.remove((Object)tag.getId());
                                request.setAttribute(
                                        "requestTagsIdWOCurrent",
                                        requestTagsIdWOCurrent
                                        );
                                request.setAttribute("tag", tag);
                                %>
                                <tr>
                                    <td><c:out value="${tag.id}"/></td>
                                    <td>
                                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&tags-label=<c:out value='${tag.label}'/>"
                                           title="Consult webpages having the tag of id <c:out value='${tag.id}'/>"
                                           ><c:out value='${tag.label}'/></a>
                                    </td>
                                    <td>
                                        <form action="${contextPath}/rest/request/update"
                                            method="POST">
                                            <input type="hidden" name="id" value="<c:out value='${request.id}'/>"/>
                                            <input type="hidden" name="label" value="<c:out value='${request.label}'/>"/>
                                            <input type="hidden" name="tags" value="<c:out value='${requestTagsIdWOCurrent}'/>"/>
                                            <input type="hidden" name="locales" value="<c:out value='${requestLocalesId}'/>"/>
                                            <c:set var="unlink_button_title" value="Unlink tag ${tag.label} (id ${tag.id}) of this list"/>
                                            <%@include file="/WEB-INF/jspf/inline/buttons/unlink.jspf" %>
                                        </form>
                                    </td>
                                    <td>
                                        <c:set var="details_link_url" value="${contextPath}/rest/tag/read?id=${tag.id}"/>
                                        <c:set var="details_link_title" value="Details of the tag ${tag.label} (id ${tag.id})"/>
                                        <%@include file="/WEB-INF/jspf/inline/links/details.jspf" %>
                                    </td>
                                </tr>
                                <%
                            }
                            %>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
                    
            <div class="page-header">
                <h1>Add tags</h1>
            </div>
            <div class="row span12">
                <form action="${contextPath}/rest/request/update"
                    method="POST" class="well form-horizontal">
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <input type="hidden" name="id" value="<c:out value='${request.id}'/>"/>
                    <input type="hidden" name="label" value="<c:out value='${request.label}'/>"/>
                    <input type="hidden" name="tags" value="<c:out value='${requestTagsId}'/>"/>
                    <input type="hidden" name="locales" value="<c:out value='${requestLocalesId}'/>"/>
                    <div class="control-group">
                        <label class="control-label" for="update-request-tags-label">
                            <abbr title="Required field" class="mandatory">*</abbr> Tags
                        </label>
                        <div class="controls">
                            <input id="update-request-tags-label" type="text" name="tags-label" value=""/>
                            <p class="help-block">
                                Comma serparated, exemple <code>tv,&nbsp;sport</code>
                            </p>
                        </div>
                    </div>
                    <div class="form-actions">
                        <c:set var="add_button_label" value="Add tags"/>
                        <%@include file="/WEB-INF/jspf/inline/buttons/add.jspf" %>
                    </div>
                </form>
            </div>
            
            <div class="page-header">
                <h1>Associated locales</h1>
            </div>
            <div class="row span12">
                <c:choose>
                    <c:when test="${empty request.locales}">
                        <p class="alert alert-info">This list has no locales.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Locale</th>
                                    <th scope="col">Unlink</th>
                                    <th scope="col">Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            for (Locale locale : req.getLocales()) {
                                Collection<Long> requestLocalesIdWOCurrent =
                                        new ArrayList(requestLocalesId);

                                requestLocalesIdWOCurrent.remove((Object)locale.getId());
                                request.setAttribute(
                                        "requestLocalesIdWOCurrent",
                                        requestLocalesIdWOCurrent
                                        );
                                request.setAttribute("locale", locale);
                                %>
                                <tr>
                                    <td><c:out value="${locale.id}"/></td>
                                    <td>
                                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&locales-label=<c:out value='${locale.label}'/>"
                                           title="Consult webpages having the locale of id <c:out value='${locale.id}'/>"
                                           ><c:out value="${locale.label}"/></a>
                                    </td>
                                    <td>
                                        <form action="${contextPath}/rest/request/update"
                                            method="POST" class="form-inline">
                                            <input type="hidden" name="id" value="<c:out value='${request.id}'/>"/>
                                            <input type="hidden" name="label" value="<c:out value='${request.label}'/>"/>
                                            <input type="hidden" name="tags" value="<c:out value='${requestTagsId}'/>"/>
                                            <input type="hidden" name="locales" value="<c:out value='${requestLocalesIdWOCurrent}'/>"/>
                                            <c:set var="unlink_button_title" value="Unlink locale ${locale.label} (id ${locale.id}) of this list"/>
                                            <%@include file="/WEB-INF/jspf/inline/buttons/unlink.jspf" %>
                                        </form>
                                    </td>
                                    <td>
                                        <c:set var="details_link_url" value="${contextPath}/rest/locale/read?id=${locale.id}"/>
                                        <c:set var="details_link_title" value="Details of the locale ${locale.label} (id ${locale.id})"/>
                                        <%@include file="/WEB-INF/jspf/inline/links/details.jspf" %>
                                    </td>
                                </tr>
                                <%
                            }
                            %>
                            </tbody>
                        </table>                        
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="page-header">
                <h1>Add Locales</h1>
            </div>
            <div class="row span12">
                <form action="${contextPath}/rest/request/update"
                    method="POST" class="well form-horizontal">
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <input type="hidden" name="id" value="<c:out value='${request.id}'/>"/>
                    <input type="hidden" name="label" value="<c:out value='${request.label}'/>"/>
                    <input type="hidden" name="tags" value="<c:out value='${requestTagsId}'/>"/>
                    <input type="hidden" name="locales" value="<c:out value='${requestLocalesId}'/>"/>
                    <div class="control-group">
                        <label class="control-label" for="update-request-locales-label">
                            <abbr title="Required field" class="mandatory">*</abbr> Locales
                        </label>
                        <div class="controls">
                            <input id="update-request-locales-label" type="text" name="locales-label" value=""/>
                            <p class="help-block">
                                Comma separated locales, exemple <code>fr_FR,&nbsp;fr_CA</code>
                            </p>
                        </div>
                    </div>
                    <div class="form-actions">
                        <c:set var="add_button_label" value="Add locales"/>
                        <%@include file="/WEB-INF/jspf/inline/buttons/add.jspf" %>
                    </div>
                </form>
            </div>

        </c:otherwise>
        </c:choose>
        </div>
            
        <%@include file="/WEB-INF/jspf/template/footer.jspf" %>
    </body>
</html>
