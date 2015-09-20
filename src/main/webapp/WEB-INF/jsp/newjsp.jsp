<%-- 
    Document   : newjsp
    Created on : 18-set-2015, 22.07.03
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<!--CSS-->
	
	<link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="./dist/css/poi_view.css" rel="stylesheet">
    <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
	
    <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
<link href="./dist/css/itinerary.css" rel="stylesheet">
    <!--SCRIPT-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

</head>
<body>
	<jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <div class="cover_itinerary_detail">
                	<div class="cover_itd cover_itd_sx">
                		<div class="itinerary_det">
                			<div class="img_poi_header">
                                <div class="rnd_it_detail" style="background-color:#cdcdcd;"></div> 
                            </div>
                            <div class="it_det_info">Itinerario di Andrea</div>
                            <div class="it_det_info it_det_date">Creato il 22/12/2015</div>

                		</div>
                	</div>
                	
                	<div class="cover_itd cover_itd_dx">
                		<div class="price_detail">
                			Totale da pagare: 50
                		</div>
                	</div>



                    
                </div>
            </div>

            

            <div class="col-md-5" style="margin-top:5px;">
               <!-- Modal -->
                <div id="modalOffer" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div id="modalContent" class="modal-content">
                            
                        </div>

                    </div>
                </div>
                
                

	            <div class="it_detail_action">
	            	<div class="it_detail_button it_detail_complete">
	            		<i class="fa fa-check"></i>
	            		completa
	            	</div>
	            	
	            	<div class="it_detail_button it_detail_delete">
	            		<i class="fa fa-times"></i>
	            		cancella
	            	</div>

	            </div>

	            <article class="component component-text">
                    <div class="details details_poi_itinerary" style="padding-left: 10px; padding-right: 10px;">
                        <div class="paragrafo">

                            <div class="poi_container">


                                <c:choose>
                                    <c:when test="${not empty itinerary}">
                                        <c:forEach var="it" items="${itinerary}">
                                

                                            <div class="poi" >
                                                <div class="img_poi">

	                                                <img class="rnd_it_detail" src="./dist/img/default_avatar.png" /> 
                                                </div>
                                                
                                                <div class="info_container">
                                                	<div class="poi_name_container">Pio Monte della Misericordia</div>
                                                	<div class="icons">
                                                		<i class="fa fa-info-circle info" style="font-size:14px; color:#2980B9;"></i>
                                                            <i class="fa fa-heart" style="font-size:14px; color:#ED5565"></i>
                                                            <i class="fa fa-credit-card" style="font-size:14px;"></i>
                                                            <i class="fa fa-times delPoi" style="font-size:14px; color:#ED5565;"></i>

                                                	</div>
                                                	<div class="select_off">Seleziona Offerte</div>
                                                </div>
                                                
	                                    	</div>
                                            
                                            
                                            
                                            
                                            
                                        </c:forEach>
                                    </c:when>    

                                    <c:otherwise>
                                        Nessun poi aggiunto all'itinerario
                                    </c:otherwise>
                                </c:choose>
                                <div class="clear"></div>    
                            </div>
                        </div>    
                    </div>
                </article>

	            
            </div>

            <div class="col-md-7">
                <article class="component component-text">
                    <div class="details details_map" style="height:280px; margin-top:5px; margin-bottom:5px; padding-left:0px; padding-right:0px;">
                    	
                        
                    </div>
                </article>
            </div>

            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>
        </div>
	</body>
</html>