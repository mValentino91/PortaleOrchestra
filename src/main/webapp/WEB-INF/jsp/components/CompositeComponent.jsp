<%-- 
    Document   : CompositeComponent
    Created on : 11-dic-2014, 11.29.59
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
    <div class="panel-thumbnail " style=" background-color: #009688; padding-bottom: 1px; padding-top: 1px">
        <center>
            <i class="fa fa-info-circle" style="font-size: 39px; color: #FFF"></i>
        </center>
    </div>
    <div class="panel-body" style="padding-top: 0px; padding-bottom: 2px;">
        <c:if test="${not empty workingtime && not empty prices && not empty services}">
        <div class="row">

            <div class="col-md-4 ortab active" id="orari" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0; "><b>ORARI</b></p>
                </center>
            </div>

            <div class="col-md-4 ortab" id="prezzi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0;"><b>PREZZI</b></p>
                </center>
            </div>
            <div class="col-md-4 ortab" id="servizi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%;margin: 0 0 0 0;"><b>SERVIZI</b></p>
                </center>
            </div>
        </div>


        <div class="ortab-panel-container">
            <div class="ortab-panel show" id="orari-panel">
                <jsp:include page="leafes/WorkingTimeLeaf.jsp"/>
            </div>
            <div class="ortab-panel hidden" id="prezzi-panel">
                <jsp:include page="leafes/PricesLeaf.jsp"/>
            </div>
            <div class="ortab-panel hidden" id="servizi-panel">
                <jsp:include page="leafes/ServicesLeaf.jsp"/>
            </div>
        </div>
    </div>
</div>
        </c:if>

<c:if test="${not empty workingtime && not empty prices }">
        <div class="row">

            <div class="col-md-6 ortab active" id="orari" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0; "><b>ORARI</b></p>
                </center>
            </div>

            <div class="col-md-6 ortab" id="prezzi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0;"><b>PREZZI</b></p>
                </center>
            </div>
            
        </div>


        <div class="ortab-panel-container">
            <div class="ortab-panel show" id="orari-panel">
                <jsp:include page="leafes/WorkingTimeLeaf.jsp"/>
            </div>
            <div class="ortab-panel hidden" id="prezzi-panel">
                <jsp:include page="leafes/PricesLeaf.jsp"/>
            </div>
            
        </div>
    </div>
</div>
        </c:if>

<c:if test="${not empty workingtime && not empty services }">
        <div class="row">

            <div class="col-md-6 ortab active" id="orari" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0; "><b>ORARI</b></p>
                </center>
            </div>

            <div class="col-md-6 ortab" id="servizi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0;"><b>SERVIZI</b></p>
                </center>
            </div>
            
        </div>


        <div class="ortab-panel-container">
            <div class="ortab-panel show" id="orari-panel">
                <jsp:include page="leafes/WorkingTimeLeaf.jsp"/>
            </div>
            <div class="ortab-panel hidden" id="servizi-panel">
                <jsp:include page="leafes/ServicesLeaf.jsp"/>
            </div>
            
        </div>
    </div>
</div>
        </c:if>

<c:if test="${not empty prices && not empty services }">
        <div class="row">

            <div class="col-md-6 ortab active" id="prezzi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0; "><b>PREZZI</b></p>
                </center>
            </div>

            <div class="col-md-6 ortab" id="servizi" onclick="show(this.id)">
                <center>
                    <p style="font-size: 110%; margin: 0 0 0 0;"><b>SERVIZI</b></p>
                </center>
            </div>
            
        </div>


        <div class="ortab-panel-container">
            <div class="ortab-panel show" id="prezzi-panel">
                <jsp:include page="leafes/PricesLeaf.jsp"/>
            </div>
            <div class="ortab-panel hidden" id="servizi-panel">
                <jsp:include page="leafes/ServicesLeaf.jsp"/>
            </div>
            
        </div>
    </div>
</div>
        </c:if>
<script>
    //fix_height();
</script>