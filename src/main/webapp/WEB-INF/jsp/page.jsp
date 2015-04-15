<%-- 
    Document   : page
    Created on : 14-apr-2015, 10.17.04
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>


<html>
<head>
	<title>Orchestra</title>
	<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
	<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
	<link rel='stylesheet prefetch' href='https://s3-us-west-2.amazonaws.com/s.cdpn.io/6035/icomoon-uikit-feb.css'>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="css/poi_view.css">
	<link rel="stylesheet" href="css/struttura.css">
	<link rel="stylesheet" href="style.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<style>
		
	</style>

</head>

<body>

	<div class="container-fixed">
		<jsp:include page="components/sideBar.jsp"/>

		<div class="row">
			<div class="col-md-6 col-orc">
				<div class="box-orc">
					
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
						<c:forEach var="img" varStatus="cont" items="${pages.imgList}">
						  <li data-target="#myCarousel" data-slide-to="cont.count()" <c:if test="${cont.count() == 1}"> class="active" </c:if> > </li>
						</c:forEach>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox" style="height:100%">
                                                    <c:forEach var="img" varStatus="cont" items="${pages.imgList}">
                                                    <div class="item <c:if test="${cont.count() == 1}"> active </c:if> >">
								<img src="${img.link}"  >
			  			     </div>
                                                     </c:forEach>
							
			 
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						  <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						  <span class="sr-only">Precente</span>
						</a>

						<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						  <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						  <span class="sr-only">Prossimo</span>
						</a>
					</div>

				</div>
			</div>

			<div class="col-md-6 col-orc">
				<div class="col-md-12 col-orc box-orc-half">
                                    <c:forEach var="tile" items="${pages.tilesList}">
					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile" style="background-color: ${tile.color}">
								<img src="img/pizza_.png" style="margin-left:-5px; margin-top:-10px;">
							</div>

						</div>
					</div>
                                    </c:forEach>
					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile">dhjdh</div>

						</div>
					</div>

					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile" style="background-color:#D31E76;">
								<img src="img/coffee.png" style="margin-left:-5px; margin-top:-10px;">
							</div>

						</div>
					</div>					
				</div>

				<div class="col-md-12 col-orc box-orc-half">
					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile">dhjdh</div>

						</div>
					</div>

					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile" style="background-color:#009DE0;">
								<img src="img/spaghetti.png" style="margin-left:-5px; margin-top:-10px;">
							</div>

						</div>
					</div>
					<div class="col-md-4 col-orc">
						<div class="box-orc">

							<div class="tile">dhjdh</div>

						</div>
					</div>					
				</div>

			</div>

		</div>

		<div class="row" style="height:37px; margin-top:5px; margin-bottom:5px;">
			<div class="col-md-12 col-orc">
				<div class="box-orc">

					<div id="menu" style="background-color:#E74C3C; margin-top:-5px;">
			            <ul>
			              <li></li>
			              <li><a href="#">cat.1</a></li>
			              <li><a href="#">cat.2</a></li>
			              <li><a href="#">cat.3</a></li>
			              <li><a href="#">cat.4</a></li>    
			              <li><a href="#">cat.5</a></li>
			              <li><a href="#">cat.6</a></li>  
			            </ul>  
			        </div>

		    	</div>
		    </div>
		</div>

		<div class="row">
			<div class="col-md-4 col-orc" >
				<div class="box-orc">

					<div class="box-elem component component-text">
					  <div class="details">
						<p class="paragrafo">
							Il Pio Monte della Misericordia, istituzione nata a scopo benefico e ancora oggi attiva, fu fondata nel 1601 da sette nobili napoletani che decisero di dedicarsi alle opere di misericordia corporale (dar da mangiare agli affamati, dar da bere, agli assetati, vestire gli ignudi, ecc.). Tra questi, Giovan Battista Manso, raffinato intellettuale, che pare abbia avuto un ruolo importante per l'arrivo del Caravaggio a Napoli e, in particolare, nella commissione del dipinto per l'istituzione. Oggi la chiesa e la quadreria rappresentano un luogo privilegiato per conoscere l'arte a Napoli nel Seicento e nel Settecento.
						</p>
					  </div>
					</div> 
				
				</div>
			</div>

			<div class="col-md-2 col-orc">
				<div class="col-md-12 col-orc box-orc-half">
					<div class="box-orc">

						<div class="tile" style="background-color:#27AE60">
							<img src="img/calendar.png" style="margin-left:-10px; margin-top:-5px;"/>
						</div>

					</div>
				</div>

				<div class="col-md-12 col-orc box-orc-half">
					<div class="box-orc">

						<div class="tile"></div>

					</div>
				</div>
			</div>

			<div class="col-md-6 col-orc">
				<div class="box-orc">

					<img src="img/map.png" style="width:485px; height:320px; ">

				</div>			
			</div>

		</div>

		<div class="row">
			<div class="col-md-4 col-orc">
				<div class="box-orc">

						<div class="box-elem component  component-text" style="height:100%">
							<div class="big-header contact">
								<span class="caps">contatti</span>
							</div> 
						  
							<div class="details">
								<div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
									<div class="col-xs-1" style="margin-right:5px"><span class="icon-phone"></span></div>
									<div class="col-xs-10" style="border-left:1px solid">
										<div class="col-xs-12" style="margin-left:-25px;"><p><strong>tel1:</strong> xxx/xxxxxx</p></div>
										<div class="col-xs-12" style="margin-left:-25px;"><p><strong>tel2:</strong> xxx/xxxxxx</p></div>
										<div class="col-xs-12" style="margin-left:-25px;"><p><strong>fax:</strong> xxx/xxxxxx</p></div>
									</div>
								</div>

								<div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
									<div class="col-xs-1" style="margin-right:5px"><span class="icon-mail"></span></div>
									<div class="col-xs-10" style="border-left:1px solid">
										<div class="col-xs-12" style="margin-left:-25px;"><p><strong>ufficio:</strong> segreteria@piomontedellamisericordia.it</p></div>
										<div class="col-xs-12" style="margin-left:-25px;"><p><strong>casa:</strong> xxx/xxxxxx</p></div>
									</div>
								</div>

								<div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
									<div class="col-xs-1" style="margin-right:5px"><span class="icon-sphere"></span></div>
									<div class="col-xs-10" style="border-left:1px solid">
										<div class="col-xs-12" style="margin-left:-25px;"><a href="www.google.it">ddd</a></div>
										<div class="col-xs-12" style="margin-left:-25px;"><a href="www.google.it">dd</a></div>
									</div>
								</div>

								<div class="social" style="margin-top: 10px">
									<div class="col-xs-12" style="margin-left:-15px; margin-top:15px;">
										<div class="col-xs-3" style="padding-right:15px;">
											<div class="btn fb" style="text-align:center"><span class="icon-facebook" ></span></div>
										</div>
								
										<div class="col-xs-3" style="padding-right:15px;">
											<div class="btn gplus" style="text-align:center"><span class="icon-google-plus" ></span></div>
										</div>

										<div class="col-xs-3" style="padding-right:15px;">
											<div class="btn tweet"><span class="icon-twitter" ></span></div>
										</div>

										<div class="col-xs-3" style="padding-right:15px;">
											<div class="btn skype"><span class="icon-skype" ></span></div>
										</div>
									</div>
								</div>
							</div>

							<div class="clear"></div>
			  			</div>

				</div>			
			</div>

			<div class="col-md-4 col-orc">
				<div class="box-orc">
										
						<div class="box-elem component  component-text" style="height:100%">
							<div class="big-header contact">
								<span class="caps">Servizi speciali</span>
							</div> 
						  
							<div class="details">
								<p class="paragrafo">
						            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus facilisis risus consectetur convallis fermentum. 
						            Nam sodales volutpat pharetra. Sed vulputate, quam et fermentum porta, libero enim ultrices mi, quis dignissim purus elit ut nisi. 
						            Donec gravida porta lacus viverra dictum. Curabitur mollis consectetur est id efficitur. Phasellus venenatis mi ipsum, 
						            id blandit erat tincidunt vitae. Nam eget dolor lectus. Etiam aliquam diam in tortor ullamcorper cursus. 
						            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin malesuada enim at turpis vulputate 
						            varius. Nulla sed tempor quam, vel vulputate neque. 
						        </p>
							</div>

							<div class="clear"></div>
						</div>

				</div>			
			</div>

			<div class="col-md-4 col-orc">
				<div class="box-orc">

						<div class="box-elem component  component-text" style="height:100%">
							<div class="big-header contact">
								<span class="caps">Prodotti speciali</span>
							</div> 
						  
							<div class="details">
								<p class="paragrafo">
						            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus facilisis risus consectetur convallis fermentum. 
						            Nam sodales volutpat pharetra. Sed vulputate, quam et fermentum porta, libero enim ultrices mi, quis dignissim purus elit ut nisi. 
						            Donec gravida porta lacus viverra dictum. Curabitur mollis consectetur est id efficitur. Phasellus venenatis mi ipsum, 
						            id blandit erat tincidunt vitae. Nam eget dolor lectus. Etiam aliquam diam in tortor ullamcorper cursus. 
						            Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin malesuada enim at turpis vulputate 
						            varius. Nulla sed tempor quam, vel vulputate neque. 
						        </p>
							</div>

							<div class="clear"></div>
						</div>
						
				</div>			
			</div>

		</div>


	</div>
</body>
</html>
