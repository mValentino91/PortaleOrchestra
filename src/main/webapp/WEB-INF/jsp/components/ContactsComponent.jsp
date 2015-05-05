<%-- 
    Document   : ContactsComponent
    Created on : 1-dic-2014, 14.31.40
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<article class="component component-text" style="word-wrap: break-word;" >
				  <div class="big-header contact">
					  <span class="caps"><spring:message code="label.contact"></spring:message></span>
				  </div> 
				<div class="details">
            <c:if test="${not empty contacts.emailsList}">   
                <div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
		<div class="col-xs-1" style="margin-right:5px"><span class="icon-mail"></span></div>
		<div class="col-xs-10" style="border-left:1px solid">
                <c:forEach var="contm" items="${contacts.emailsList}">
                    <div class="col-xs-12" style="margin-left:-25px;" ><p >
                    <c:if test="${not empty contm.label}">   
                    <strong>${contm.label}:</strong>
                    </c:if>
                        <a href="mailto:${contm.email}"  target="_top">${contm.email}</a></p>
                </div>
                </c:forEach>
                </div>
                </div>
            </c:if>
            <c:if test="${not empty contacts.phoneList || not empty contacts.faxList}">
                <div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
		<div class="col-xs-1" style="margin-right:5px"><span class="icon-phone"></span></div>
		<div class="col-xs-10" style="border-left:1px solid">
                    <c:if test="${not empty contacts.phoneList}">
                <c:forEach var="cont" items="${contacts.phoneList}">
                    <div class="col-xs-12" style="margin-left:-25px;"><p>
                    <c:if test="${not empty cont.label}">   
                    <strong>${cont.label}:</strong>
                    </c:if>
                    ${cont.number}</p>
                </div>
                </c:forEach>
                    </c:if>
                <c:if test="${not empty contacts.faxList}">
                
                <c:forEach var="cont" items="${contacts.faxList}">
                    <div class="col-xs-12" style="margin-left:-25px;"><p>
                            <strong>Fax </strong>
                    <c:if test="${not empty cont.label}">   
                    <strong>${cont.label}:</strong>
                    </c:if>
                    ${cont.fax}</p>
                    </div>
                </c:forEach>
                
            </c:if>
                </div>
                </div>
            </c:if>
               <c:if test="${not empty contacts.socialList}">
                   <div class="col-xs-12" name="cont" style="margin-left:-30px; margin-top:10px;">
                    <div class="col-xs-1" style="margin-right:5px"><span class="icon-sphere"></span></div>
		    <div class="col-xs-10" style="border-left:1px solid">
                <c:forEach var="cont" items="${contacts.socialList}">
                    <div class="col-xs-12" style="margin-left:-25px;"><p><a href="${cont.social}" target="_blank" >${cont.label}</a></p></div>
                    </c:forEach>
                    </div>
                   </div>
            </c:if>
                <div class="social" style="margin-top: 10px; text-align: center;">
		 <div class="col-xs-12" style="margin-top:15px; margin-left: -8px; text-align: center;">
                     
             
                <div class="col-xs-3" >
                    <a href="${contacts.facebook}" target="_blank"><div class=" btn <c:if test="${empty contacts.facebook}"> disabled </c:if> fb" style="text-align:center"><span class="icon-facebook" ></span></div></a>
		</div>
            
                 
                   
             
               <div class="col-xs-3">
                    <a href="${contacts.twitter}" target="_blank"><div class=" btn <c:if test="${empty contacts.twitter}"> disabled </c:if> tweet" style="text-align:center"><span class="icon-twitter" ></div></span></a>
		</div>
            
                
                    
              
            <div class="col-xs-3">
                <a  href="${contacts.google}" target="_blank" ><div  class=" btn  <c:if test="${empty contacts.google}"> disabled </c:if>   gplus" style="text-align:center;"><span class="icon-google-plus" ></div></span></a>
            </div>
            
                
                     
                
            <div class="col-xs-3">
                    <a href="${contacts.skype}" target="_blank"><div class="btn <c:if test="${empty contacts.skype}"> disabled </c:if> skype" style="text-align:center"><span class="icon-skype" ></div></span></a>
	    </div>
            
                     
                 </div>
                </div>
                     
            
          

        <div class="clear"></div>
</div>
</article>








