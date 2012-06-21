<%-- 
    Document   : webpage
    Created on : 23 mai 2012, 15:42:22
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList,java.util.Collection"
        import="org.opens.urlmanager.entity.tag.Tag,org.opens.urlmanager.entity.locale.Locale"
        import="org.opens.urlmanager.entity.webpage.Webpage"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Webpage details"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
        <c:choose>
        <c:when test="${webpage == null}">
            <p><em>ERROR :</em> an error occured. Please contact the web administrator</p>
        </c:when> <c:otherwise>
            <%-- FIXME : do something more generic and with less code --%>
            <%
            Collection<Long> webpageTagsId = new ArrayList<Long>();
            Collection<Long> webpageLocalesId = new ArrayList<Long>();
            Webpage webpage = (Webpage)request.getAttribute("webpage");
            
            for (Tag tag : webpage.getTags()) {
                webpageTagsId.add(tag.getId());
            }
            for (Locale locale : webpage.getLocales()) {
                webpageLocalesId.add(locale.getId());
            }
            request.setAttribute("webpageLocalesId", webpageLocalesId);
            request.setAttribute("webpageTagsId", webpageTagsId);
            %>
            <div class="page-header">
                <h1>Webpage details</h1>
            </div>
            <div class="row span12">
                <table class="data-table table">
                    <tr>
                        <th scope="row">Id</th>
                        <td>${webpage.id}</td>
                    </tr>
                    <tr>
                        <th scope="row"><abbr title="Uniform Resource Locator">URL</abbr></th>
                        <td><a href="${webpage.URL}">${webpage.URL}</a></td>
                    </tr>
                    <tr>
                        <th scope="row">Is a website root</th>
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
                    </tr>
                </table>
            </div>

            <div class="page-header">
                <h1>Modify</h1>
            </div>
            <div class="row span12">
                <form method="POST" action="${contextPath}/rest/webpage/update"
                    class="well form-horizontal">
                    <input type="hidden" name="id" value="${webpage.id}"/>
                    <input type="hidden" name="locales" value="${webpageLocalesId}"/>
                    <input type="hidden" name="tags" value="${webpageTagsId}"/>
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <div class="control-group">
                        <label class="control-label" for="update-webpage-isRoot">Is a website root</label>
                        <div class="controls">
                            <input id="update-webpage-isRoot" type="checkbox" name="isRoot" value="true"
                                <c:if test="${webpage.isRoot}">checked=""</c:if>/><br/>                            
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="update-webpage-URL">
                            <abbr title="Required field" class="mandatory">*</abbr>
                            <abbr title="Uniform Resource Locator">URL</abbr>
                        </label>
                        <div class="controls">
                            <input id="update-webpage-URL" type="text" name="URL" value="${webpage.URL}"/>
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
                <c:set var="delete_entity_url"
                    value="${contextPath}/rest/webpage/delete?id=${webpage.id}"/>
                <%@include file="/WEB-INF/jspf/blocks/delete-entity.jspf" %>
            </div>
            
            <div class="page-header">
                <h1>Associated tags</h1>
            </div>
            <div class="row span12">
                <c:choose>
                    <c:when test="${empty webpage.tags}">
                        <p class="alert alert-info">
                            This webpage has no tags.
                        </p>
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
                            for (Tag tag : webpage.getTags()) {
                                Collection<Long> webpageTagsIdWOCurrent = new ArrayList<Long>(webpageTagsId);

                                webpageTagsIdWOCurrent.remove((Object) tag.getId());
                                request.setAttribute("tag", tag);
                            %>
                                <tr>
                                    <td>${tag.id}</td>
                                    <td>
                                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&tags-label=${tag.label}"
                                           title="Consult webpages having the tag of id ${tag.id}"
                                           >${tag.label}</a>
                                    </td>
                                    <td>
                                        <form action="${contextPath}/rest/webpage/update"
                                            method="POST">
                                            <input type="hidden" name="id" value="${webpage.id}"/>
                                            <input type="hidden" name="URL" value="${webpage.URL}"/>
                                            <input type="hidden" name="isRoot" value="${webpage.isRoot}"/>
                                            <input type="hidden" name="locales" value="${webpageLocalesId}"/>
                                            <input type="hidden" name="tags" value="<%= webpageTagsIdWOCurrent.toString()%>"/>
                                            <c:set var="unlink_button_title" value="Unlink tag ${tag.label} (id ${tag.id}) of this webpage"/>
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
                <form action="${contextPath}/rest/webpage/update" accept-charset="utf-8"
                      method="POST" class="well form-horizontal">
                    <input type="hidden" name="id" value="${webpage.id}"/>
                    <input type="hidden" name="URL" value="${webpage.URL}"/>
                    <input type="hidden" name="isRoot" value="${webpage.isRoot}"/>
                    <input type="hidden" name="tags" value="${webpageTagsId}"/>
                    <input type="hidden" name="locales" value="${webpageLocalesId}"/>
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <div class="control-group">
                        <label class="control-label" for="update-webpages-tags-label">
                            <abbr title="Required field" class="mandatory">*</abbr> Tags
                        </label>
                        <div class="controls">
                            <input id="update-webpages-tags-label" type="text" name="tags-label" value=""/>
                            <p class="help-block">
                                Comma separated tags, exemple <code>tv,&nbsp;sport</code>
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
                <h1>Webpage's locales</h1>
            </div>
            <div class="row span12">
                <c:choose>
                    <c:when test="${empty webpage.locales}">
                        <p class="alert alert-info">
                            This webpages has no locales.
                        </p>
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
                            for (Locale locale : webpage.getLocales()) {
                                Collection<Long> webpageLocalesIdWOCurrent = new ArrayList<Long>(webpageLocalesId);

                                webpageLocalesIdWOCurrent.remove((Object) locale.getId());
                                request.setAttribute("locale", locale);
                                %>
                                <tr>
                                    <td>${locale.id}</td>
                                    <td>
                                        <a href="${contextPath}/rest/request/list-matching-webpages?id=0&locales-label=${locale.label}"
                                           title="Consult webpages having the locale of id ${locale.id}"
                                           >${locale.label}</a>
                                    </td>
                                    <td>
                                        <form action="${contextPath}/rest/webpage/update"
                                            method="POST">
                                            <input type="hidden" name="id" value="${webpage.id}"/>
                                            <input type="hidden" name="URL" value="${webpage.URL}"/>
                                            <input type="hidden" name="isRoot" value="${webpage.isRoot}"/>
                                            <input type="hidden" name="locales" value="<%=webpageLocalesIdWOCurrent.toString()%>"/>
                                            <input type="hidden" name="tags" value="${webpageTagsId}"/>
                                            <c:set var="unlink_button_title" value="Unlink locale ${locale.label} (id ${locale.id}) of this webpage"/>
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
                <h1>Add locales</h1>
            </div>
            <div class="row span12">
                <form action="${contextPath}/rest/webpage/update" method="POST"
                  class="well form-horizontal">
                    <input type="hidden" name="id" value="${webpage.id}"/>
                    <input type="hidden" name="URL" value="${webpage.URL}"/>
                    <input type="hidden" name="isRoot" value="${webpage.isRoot}"/>
                    <input type="hidden" name="locales" value="${webpageLocalesId}"/>
                    <input type="hidden" name="tags" value="${webpageTagsId}"/>
                    <%@include file="/WEB-INF/jspf/blocks/required-fields-alert.jspf" %>
                    <div class="control-group">
                        <label class="control-label" for="update-webpage-locales-label">
                            <abbr title="Required field" class="mandatory">*</abbr> Locales
                        </label>
                        <div class="controls">
                            <input id="update-webpage-locales-label" type="text" name="locales-label" value=""/>
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
