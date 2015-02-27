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
<script src="./dist/components/poiList/poiList.js"></script>
<script src="./dist/js/bootstrap-treeview.js"></script>
<script src="./dist/components/categoriesTail/categoriesTail.js"></script>
<script src="./dist/js/transition.js"></script>
<style>
    .panel-heading>a{
        color:#285e8e;
    }
    .input-group-addon{
        background-color: white;
    }
    .categoriesTails>button>i {
        color:whitesmoke;
    }
    #poiListComponent{
        background-color: transparent;
        width: 300px;
        overflow: auto;
        max-height: 170px;
        margin-top: 5px;
        padding: 0px;
        display: none;
        box-shadow: 0px 0px 5px -2px black;
    }
    .categoriesContainer{
        z-index: 10 !important;
        background-color: transparent;
        position: absolute;
        right: 345px;
        top: 95px;
        bottom: 25px;
        width: auto;
        display: none;
        overflow: auto;
        padding-right: 5px;
    }
    .categoriesBox{
        background-color: white;
        padding: 10px;
        box-shadow: 0px 0px 5px -2px black;
    }
    .poiBox{
        background-color: white;
        padding-top: 5px;
        padding-bottom: 5px;
        box-shadow: 0px 0px 5px -2px black;
    }
    .poiBox:hover{
        background-color: whitesmoke;
        cursor:pointer
    }
    .poiBoxShortDescription{
        color: gray;
        display: none;
    }
    .searchBar{
        position: absolute;
        right: 5px;
        top: 55px;
        width: 340px;
        overflow: auto;
        box-shadow: 0px 0px 5px -2px black;
    }
    .searchBar > input{
        width: 100%;
    }
    .categoriesTailMacro{
        position: absolute;
        right: 45px;
        top: 127px;
        width: 300px;
        overflow: visible;
        padding: 0;
    }
    .categoriesTailsBox{
        position: absolute;
        right: 45px;
        top: 178px;
        width: 300px;
        overflow: visible;
    }
    .panel-group .panel{
        border-radius: 0px 0px 0px 0px;
    }
    .panel-group{
        margin-bottom: 0px;
        margin-top: 0px;
    }
    .panel{
        margin-top: 5px;
        margin-bottom: 0px;
        box-shadow: 0px 0px 5px -2px black;
    }
    .panel-heading{
        height: 25px;
        padding: 5px;
    }
    .categoriesTails{
        background-color: white;
        width: 100%;
        overflow: auto;
        padding:2px;
        box-shadow: 0px 0px 5px -2px black;
    }
    .categoriesTails > button{
        box-shadow: 0px 0px 5px -2px;
        z-index: 2 !important;
    }
    .categoriesTailsInner{
        background-color: white;
        width: 300px;
        max-height: 100px;
        box-shadow: 0px 0px 5px 0px #242424;
    }
    .categoriesInnerSub{
        margin-top: -60px;
    }
    .categoriesTailsInner-header{
        background-color: lightslategray;
        padding: 5px;
    }
    .filterOnPoiList{
        background-color: white;
        position: absolute;
        right: 45px;
        top: 95px;
        width: 300px;
        overflow: visible;
        padding: 2px;
        box-shadow: 0px 0px 5px -2px black;
    }
    #poiListComponent::-webkit-scrollbar-track {
        border-radius: 2px;
    }
    #poiListComponent::-webkit-scrollbar {
        width: 5px;
        background-color: #F7F7F7;
    }
    #poiListComponent::-webkit-scrollbar-thumb {
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
        background-color: #BFBFBF;
    }
    .categoriesContainer::-webkit-scrollbar-track {
        border-radius: 2px;
    }
    .categoriesContainer::-webkit-scrollbar {
        width: 5px;
        background-color: #F7F7F7;
    }
    .categoriesContainer::-webkit-scrollbar-thumb {
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
        background-color: #BFBFBF;
    }
    .well{
        border-radius: 0px 0px 0px 0px;
    }
</style>

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
                onclick="macroCategoryHandler('culture', 'Cultura')"
                title="Cultura">
            <i class="fa fa-university"></i>
        </button>
        <button type="button" class="btn btn-info btn-lg"
                onclick="macroCategoryHandler('mobility', 'Mobilità')"
                title="Mobilità">
            <i class="fa fa-bus"></i>
        </button>
        <button type="button" class="btn btn-success btn-lg"
                onclick="macroCategoryHandler('food', 'Enogastronomia')"
                title="Enogastronomia">
            <i class="fa fa-glass"></i>
        </button>
    </div>

    <div class="panel-group" id="categoriesPanelGroup">
    </div>
    <div id="poiListComponent">
        <c:forEach var = "poi" items = "${poiList}">
            <div id="${poi.id}" class="container-fluid poiBox" 
                 onmouseover="poiList.poiBoxDown('${poi.id}', '${poi.id}', true)"
                 onmouseout="poiList.poiBoxDown('${poi.id}', '${poi.id}', false)"
                 onmouseleave="poiList.poiBoxUp('${poi.id}','${poi.id}')"
                 onclick="poiList.poiClicked('${poi.id}')">
                <div class="col-md-4"> 
                    <img class="img-responsive" style="height: 45px"
                         src="./dist/poi/img/${poi.id}/cover.jpg" 
                         onError="this.src='./dist/img/notFound.png';" alt=""/>
                </div>
                <div class="col-md-6">
                    <span class="text-center" style="color: #242424">
                        ${poi.name}
                    </span>
                </div>
                <div class="col-md-1 text-right"><span onmouseover="this.className = 'fa fa-star fa-2x';" 
                                                       onmouseleave="this.className='fa fa-star-o fa-2x';"
                                                       onclick="poiList.addToCart('${poi.id}')"
                                                       data-toggle="tooltip" title="Aggiungi Ai Preferiti!"
                                                       class="fa fa-star-o fa-2x"></span></div>
                <div class="col-md-12">
                    <p style="padding-top: 5px;" class="poiBoxShortDescription ${poi.id}">
                        <span style="color:gray; font-size: 11px;">
                            ${poi.address}
                            <br>
                            <label>
                                <input type="checkbox" onclick="interactiveMap.drawCircleAroundPoi('${poi.id}', 0.15, this.checked)">
                                Nei dintorni (150 metri)
                            </label>
                        </span>
                        <br><br>
                        ${poi.shortDescription}
                        <a href="./getPoi?id=${poi.id}">Altro...</a>
                    </p>
                </div>
            </div>
        </c:forEach>
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