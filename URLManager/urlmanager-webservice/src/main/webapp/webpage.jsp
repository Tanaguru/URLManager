<%-- 
    Document   : tag
    Created on : 4 juin 2012, 13:35:43
    Author     : bcareil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:set var="title" value="URLM - Webpage"/>
    <%@include file="/WEB-INF/jspf/template/head.jspf" %>
    <body>
        <c:set var="page" value="webpages"/>
        <%@include file="/WEB-INF/jspf/template/header.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Manage the Webpages</h1>
            </div>

            <div class="row span12">
                <h2>Add a webpage</h2>
                <%@include file="../WEB-INF/jspf/forms/webpage/create.jspf" %>
            </div>

            <div class="row span12">
                <h2>Consult the existing webpages</h2>
                <div class="well">
                    Consult :
                    <ul>
                        <li><a href="rest/webpage/list">all the webpages</a></li>
                        <li><a href="rest/webpage/list-without-tag">the webpages which have no tags</a></li>
                        <li><a href="rest/webpage/list-without-locale">the webpages which have no locales</a></li>
                        <li><a href="rest/webpage/list-without-relation">the webpages which have neither tags nor locales</a></li>
                    </ul>
                </div>
            </div>

            <div class="row span12">
                <h2>Search a webpage by <abbr title="Uniform Resource Locator">URL</abbr></h2>
                <%@include file="../WEB-INF/jspf/forms/webpage/search-url.jspf" %>
            </div>

            <div class="row span12">
                <h2>Search a webpage by id</h2>
                <%@include file="../WEB-INF/jspf/forms/webpage/search-id.jspf" %>
            </div>

            <div class="row span12">
                <h2>Search a webpages by tag and locale</h2>
                <form action="/urlmanager/rest/request/list-matching-webpages"
                    method="POST" class="well form-horizontal">
                    <input type="hidden" name="id" value="0"/>
                    <div class="control-group">
                        <label class="control-label" for="search-webpage-tags-label">Tags</label>
                        <div class="controls">
                            <input id="search-webpage-tags-label" type="text" name="tags-label" value=""/>
                            <p class="help-block">
                                Comma separated tags, exemple <code>tv,&nbsp;sport</code>.

                                Note that an empty field means "all tags".
                            </p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="search-webpage-locales-label">Locales</label>
                        <div class="controls">
                            <input id="search-webpage-locales-label" type="text" name="locales-label" value=""/>
                            <p class="help-block">
                                Comma separated locales, exemple <code>fr_FR,&nbsp;fr_CA</code>.

                                Note that an empty field means "all locales".
                            </p>
                        </div>
                    </div>
                    <div class="form-actions">
                        <c:set var="search_button_label" value="Search"/>
                        <%@include file="/WEB-INF/jspf/inline/buttons/search.jspf" %>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="../WEB-INF/jspf/template/footer.jspf" %>              
    </body>
</html>
