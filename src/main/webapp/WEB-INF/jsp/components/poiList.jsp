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
<div class="filterOnPoiList well">
    <div  class="btn-group" role="group" data-toggle="buttons">
        <button type="button" class="btn btn-default btn-xs" 
                onclick="$('.categoriesContainer').toggle();">
            <i class="fa fa-bars"></i>
        </button>
    </div>
    <div  class="btn-group" role="group" data-toggle="buttons">
        <label id="searchMapCheckButton" type="button" class="btn btn-default btn-xs" onclick="interactiveMap.searchHandler()">
            <input type="checkbox" autocomplete="off">
            <i class="fa fa-search"></i>
        </label>
        <button type="button" class="btn btn-default btn-xs" onclick="interactiveMap.anmHandler()">
            <i class="fa fa-bus"></i>
        </button>
        <button type="button" class="btn btn-default btn-xs" onclick="$('#poiListComponent').slideToggle(150)">
            <i class="glyphicon glyphicon-map-marker"></i>
        </button>
    </div>
</div>
<div class="categoriesTailMacro">
    <div class="categoriesTails">
        <button type="button" class="btn btn-danger btn-lg"
                onclick="categoriesTail.macroCategoryHandler('culture', 'Cultura')"
                title="Cultura">
            <i class="fa fa-university"></i>
        </button>
        <button type="button" class="btn btn-info btn-lg"
                onclick="categoriesTail.macroCategoryHandler('mobility', 'Mobilità')"
                title="Mobilità">
            <i class="fa fa-bus"></i>
        </button>
        <button type="button" class="btn btn-success btn-lg"
                onclick="categoriesTail.macroCategoryHandler('food', 'Enogastronomia')"
                title="Enogastronomia">
            <i class="fa fa-glass"></i>
        </button>
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
        </script>
    </div>
</div>
<script>poiList.initPoiList();</script>