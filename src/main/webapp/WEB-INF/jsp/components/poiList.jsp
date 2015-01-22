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
<style>
    #poiListComponent{
        background-color: transparent;
        position: absolute;
        right: 5px;
        top: 135px;
        bottom: 25px;
        width: 300px;
        overflow: auto;
        display: none;
        padding-left: 0px;
    }
    .poiBox{
        background-color: white;
        border: 1px solid lightgray;
        padding-top: 10px;
        padding-bottom: 5px;
        border-top: none;
    }
    .poiBox:hover{
        background-color: whitesmoke;
        cursor:pointer
    }
    .poiBoxShortDescription{
        color: gray;
        display: none;
    }
    .buttonBar{
        background-color: white;
        position: absolute;
        right: 5px;
        top: 55px;
        height: 40px;
        width: 300px;
        overflow: auto;
        box-shadow: 0px 10px 5px -10px black;
        padding: 5px;
        box-shadow: 0px 0px 10px -2px black;
    }
    .filterOnPoiList{
        z-index: 5;
        background-color: white;
        position: absolute;
        right: 5px;
        top: 100px;
        height: 35px;
        width: 300px;
        overflow: visible;
        padding: 5px;
        display: none;
        border: 1px solid lightgray;
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

</style>
<div class="buttonBar text-center">
    <div  class="btn-group" role="group" data-toggle="buttons">
        <label id="searchMapCheckButton" type="button" class="btn btn-default btn-sm" onclick="interactiveMap.searchHandler()">
            <input type="checkbox" autocomplete="off">
            <i class="fa fa-search"></i>
        </label>
        <button type="button" class="btn btn-default btn-sm" onclick="interactiveMap.anmHandler()">
            <i class="fa fa-bus"></i>
        </button>
        <button type="button" class="btn btn-default btn-sm" onclick="$('#poiListComponent').toggle(300);
                $('.filterOnPoiList').toggle(300);">
            <i class="glyphicon glyphicon-map-marker"></i>
        </button>
    </div>
</div>
<div class="poiListContainer well">
    <div class="filterOnPoiList">
        <div class="btn-group">
            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
               <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="#"><i class="fa fa-globe">  Tutti</i></a></li>
                <li><a href="#"><i class="fa fa-star"></i>  Preferiti</a></li>
            </ul>
        </div>
    </div>
    <div id="poiListComponent">
        <c:forEach var = "poi" items = "${poiList}">
            <div id="${poi.id}" class="container-fluid poiBox" 
                 onmouseover="poiList.poiBoxDown('${poi.id}', '${poi.id}', true)"
                 onmouseout="poiList.poiBoxDown('${poi.id}', '${poi.id}', false)"
                 onmouseleave="poiList.poiBoxUp('${poi.id}','${poi.id}')"
                 onclick="poiList.poiClicked('${poi.id}')">
                <div class="col-md-4"> 
                    <img class="img-responsive" style="max-height: 45px"
                         src="./dist/poi/img/${poi.id}/cover.jpg" 
                         onError="this.src='./dist/img/notFound.png';" alt=""/>
                </div>
                <div class="col-md-6">
                    <p class="text-center" style="color: #242424">
                        ${poi.name}
                    </p>
                </div>
                <div class="col-md-1 text-right"><span onmouseover="this.className = 'fa fa-star fa-2x';" 
                                                       onmouseleave="this.className='fa fa-star-o fa-2x';"
                                                       onclick="alert('aggiungo a preferiti!')"
                                                       data-toggle="tooltip" title="Aggiungi Ai Preferiti!"
                                                       class="fa fa-star-o fa-2x"></span></div>
                <div class="col-md-12">
                    <p style="padding-top: 5px;" class="poiBoxShortDescription ${poi.id}">
                        <span style="color:gray; font-size: 11px;">${poi.address}</span>
                        <br><br>
                        ${poi.shortDescription}
                        <a href="./getPoi?id=${poi.id}">Altro...</a>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>poiList.initPoiList();</script>