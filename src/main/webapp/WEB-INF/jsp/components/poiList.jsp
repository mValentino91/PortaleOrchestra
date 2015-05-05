<%-- 
    Document   : poiList
    Created on : 20-gen-2015, 13.55.50
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="filterOnPoiList" class="filterOnPoiList well">
    <jsp:include page="./filterOnPoiList.jsp" />
</div>
<div class="categoriesTailMacro">
    <div class="categoriesTails">
    </div>
    <div class="panel-group" id="categoriesPanelGroup">
    </div>
    <div id="poiListComponent">
    </div>
</div>
<div class="categoriesContainer">
    <div class="categoriesBox">
        <script>
            categoriesTail.init();
            $(document).on("loginDone", reload_filterOnPoiList);
            function reload_filterOnPoiList(event) {
                categoriesTail.init();
            }
        </script>
    </div>
</div>
<script>poiList.initPoiList();</script>