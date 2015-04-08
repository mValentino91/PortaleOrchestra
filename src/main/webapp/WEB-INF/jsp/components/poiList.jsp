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
<div class="searchBar input-group">
    <input type="text" class="form-control" placeholder="Cerca" aria-describedby="searchSpan">
    <span class="input-group-addon" id="searchSpan">
        <i class="glyphicon glyphicon-search"></i>
    </span>
</div>
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
            $(function() {

                $.getJSON("./jsonDB/categoriesTree", function(data) {

                    $('.categoriesBox').treeview({
                        data: data,
                        showBorder: false,
                        levels: 1,
                        onNodeSelected: function(event, node) {

                            var catEvent = jQuery.Event("category_changed");
                            catEvent.target = node.slug;
                            $(document).trigger(catEvent);

                        }
                    });

                });
            });
            categoriesTail.init();
        </script>
    </div>
</div>
<script>poiList.initPoiList();</script>