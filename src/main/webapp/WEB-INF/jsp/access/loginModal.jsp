<%-- 
    Document   : loginModal
    Created on : 2-mar-2015, 15.29.01
    Author     : antonio
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:url value="logout" var="logoutUrl"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   
<link rel="stylesheet" href="./dist/css/loginModal.css" />
<script src="./dist/js/loginModal.js"></script>

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header login_modal_header">
        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        		<h2 class="modal-title" id="myModalLabel">Login to Your Account</h2>
      		</div>
      		<div class="modal-body login-modal">
                    <iframe src="loginForm" height="300px" width="100%" scrolling="no" frameBorder="0" style="overflow: hidden"></iframe>               
		</div>
      		<div class="clearfix"></div>
      		<div class="modal-footer login_modal_footer">
                    <div style="font-size:85%" >
                        Don't have an account? 
                        <a href="#">
                            Sign Up Here!
                        </a>
                    </div>
      		</div>
    	</div>
  	</div>
</div>   