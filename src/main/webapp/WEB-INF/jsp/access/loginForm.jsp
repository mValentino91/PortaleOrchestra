<%-- 
    Document   : login
    Created on : 24-feb-2015, 17.34.18
    Author     : antonio
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <link  rel="stylesheet" href="./dist/css/login_form.min.css">
        <link rel="stylesheet" href="./dist/css/font-awesome.min.css"/>
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./dist/js/loginForm.js"></script>
        <script type="text/javascript" src="./dist/fb_js_pack/js_fb.js"></script>
        
        <script type="text/javascript"> 
        //enable form submit button
        $(document).ready(function(){
            $.formSubmitEnable("btn-login", "loginform");
        });
        </script>        
        
    </head>
    <body>
	<div id="fb-root"></div>
	<script>
         /*Facebook login script
	 window.fbAsyncInit = function() {
		FB.init({
		  appId      : '1507821126169380', // App ID
		  channelUrl : 'http://www.smile.unina.it:8080/orchestra/dist/fb_js_pack/channel.html', // Channel File
		  status     : true, // check login status
		  cookie     : true, // enable cookies to allow the server to access the session
		  xfbml      : true  // parse XFBML
		});
	 };   
         */   
         //Facebook login script
	 window.fbAsyncInit = function() {
		FB.init({
		  appId      : '1507821126169380', // App ID
		  channelUrl : 'http://localhost:8080/orchestra/dist/fb_js_pack/channel.html', // Channel File
		  status     : true, // check login status
		  cookie     : true, // enable cookies to allow the server to access the session
		  xfbml      : true  // parse XFBML
		});
	 };

	/*Load the SDK asynchronously*/
        (function(d){
               var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
               if (d.getElementById(id)) {return;}
               js = d.createElement('script'); js.id = id; js.async = true;
               js.src = "//connect.facebook.net/en_US/all.js";
               ref.parentNode.insertBefore(js, ref);
         }(document));
        </script>
        
        <div class="container">    
            <%-- If user is not authenticated show login form --%>
            <sec:authorize access="isAnonymous()">
               <div style="padding-top:30px" class="panel-body" >

                       <form id="loginform" class="form-horizontal" role="form" action="j_spring_security_check" method="post">

                               <div style="margin-bottom: 25px" class="input-group">
                                       <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                       <input id="j_username" name="j_username" type="text" class="form-control" value="" placeholder="username">                                        
                               </div>

                               <div style="margin-bottom: 25px" class="input-group">
                                       <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                       <input id="j_password" name="j_password" type="password" class="form-control" placeholder="password">
                               </div>

                               <div class="input-group">
                                 <div class="checkbox">
                                       <label>
                                         <input id="login-remember" name='_spring_security_remember_me' type="checkbox" value="1"> Remember me
                                       </label>
                                 </div>
                               </div>


                               <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                      <a id="btn-login" href="#" class="btn btn-success">Login  </a>
                                      <a id="btn-fblogin" href="#" class="btn btn-primary" onclick='fb_login()'>Login with Facebook</a>

                                    </div>
                               </div>
 

                       </form>     

               </div>                     
            
                <c:if test="${not empty error}" >
                  <span class="login_fail">${error}</span>
                </c:if>  
                  
            </sec:authorize>
            
            <%-- If user is authenticated close modal --%>
            <sec:authorize access="isAuthenticated()">
                Login Effettuato
                <script>
                   //login done function
                   loginDone();
                </script>                
            </sec:authorize>  
            
        </div>
     
    </body>
</html>
