<%-- 
    Document   : LinkedPoiComponent
    Created on : 30-apr-2015, 15.18.41
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

    .list_poi_element{
        clear: both;
        margin: 0px;
        position: relative;
        height: 35px;
    }	

    .div_line{
        background-position: center; 
        background-repeat: repeat-y; 
        background-image: url('./dist/img/semitrasparente.png');
        height: 100%; 
        width: 30px; 
        position: relative;
        float: left;
    }

    .poi-detail{
        position: relative;
        max-width: 230px;
        top: 50%;
        transform: translateY(-50%);	
        border: 0px solid green;	
        float:left;			
        font-family: "Open Sans",sans-serif;
        font-size: 13px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;		
    }

    .poi-detail a{
        text-decoration: none;
        color: grey;
    }	

    .poi-detail a:hover{
        text-decoration: none;
        color: #285E8E;
    }	

    .circle{
        margin: 0 auto;
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        border-radius: 50%;

    }

    .circle_small{
        width:15px; 
        height:15px; 	
    }

    .circle_big{
        width:23px; 
        height:23px; 	
        background-color:#285E8E;
        top: 50%;
        transform: translateY(-50%);
        position: relative;
    }

    .poi-detail-big{
        position: relative;
        border: 0px solid green;
        max-width: 235px;
        float:left;
        top: 50%;
        transform: translateY(-50%);
        font-family: "Open Sans",sans-serif;
        font-size: 14px;
    }	

    .circle_culture{
        background-color:#d24d57;
    }

    .circle_food{
        background-color:#f4d03e;
    }

    .circle_craft{
        background-color:#bf90d4;
    }

</style>
<!DOCTYPE html>
<div class="component">
    <div class="details">

        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_big"></div>
            </div>
            <div class="poi-detail-big"> 
                Questi sono POI Correlati
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_culture"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Pio Monte della misericordia</a>
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_culture"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Antonino Pio</a>
            </div>				
        </div>				
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_food"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Pizzeria Sorbillo</a>
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_food"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Limone'</a>
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_big"></div>
            </div>
            <div class="poi-detail-big"> 
                Pagine d'pprofondimento correlate
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_big"></div>
            </div>
            <div class="poi-detail-big"> 
                Pagine d'pprofondimento correlate
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_craft"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Quale di quanta bellezza napule bla bla ciccio bello lalla jolinnjh sadhuas a sdhskj adh aksjdh ak </a>
            </div>				
        </div>	
        <div class="list_poi_element">
            <div class="div_line">
                <div class="circle circle_small circle_craft"></div>
            </div>
            <div class="poi-detail"> 
                <a href="">Tizio che fa i pastori</a>
            </div>				
        </div>			
    </div>
</div>
