<%-- 
    Document   : userInfo
    Created on : 30-mar-2015, 19.42.40
    Author     : Alex
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!doctype html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./dist/css/poi_view.css">
        <link rel="stylesheet" type="text/css" href="./dist/css/font-awesome.min.css">

        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>

        <style>

            .bord_red{
                border: 1px solid red;
            }
            .row{
                margin-top: 5px;
                margin-bottom: 10px;	
            }

            .drop_down_icon{
                cursor: pointer;
                font-size: 18px;
            }

            .drop_down_icon:hover{
                color: #428BCA;
            }

            .fb_pill{
                float: left;
                margin-left: 10px;
                margin-right: 10px;	
            }

            .badge{
                background-color: rgba(66,139,202,0.7)!important;  
            }

            .drop_down_content{
                padding-left: 20px;
            }

            .edit_profile_icon{
                position: absolute;
                right: 20px;
                bottom: 5px;
            }

            .page-header{
                position: relative;	
            }


        </style>
    </head>



    <script type="text/javascript">

        $.extend({
            dropdown_enable: function() {

                $.each($('.drop_down_container'), function(index, element) {
                    var icon = $(element).find('.drop_down_icon').first();
                    var content = $(element).find('.drop_down_content').first();

                    //content.hide();
                    icon.click(function() {
                        if (content.is(":hidden")) {
                            content.slideDown("slow");
                            icon.removeClass("fa-caret-right");
                            icon.addClass("fa-caret-down");
                        } else {
                            content.slideUp("slow");
                            icon.removeClass("fa-caret-down");
                            icon.addClass("fa-caret-right");
                        }
                    });

                });
            }
        });

    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.dropdown_enable();

        });
    </script>  
    <body>
        <jsp:include page="components/topBar.jsp"/>

        <div class="container-fixed" style="padding-top: 60px;">
            <h1 class="page-header">Il tuo profilo  <a href="userEditProfile"><i class="fa fa-pencil-square-o edit_profile_icon"></i></a></h1>

            <div class="row">
                <!-- left column -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="text-center">
                        <img src="${avatar}" class="avatar img-circle img-thumbnail" alt="avatar">
                    </div>
                </div>
                <!-- edit form column -->
                <div class="col-md-8 col-sm-6 col-xs-12 personal-info drop_down_container">
                    <h3>Informazioni Personali <i class="fa fa-caret-down drop_down_icon"></i></h3>
                    <div class="info_container drop_down_content">
                        <div class="row">
                            <div class="col-md-12"><strong>Nome</strong></div>
                            <div class="col-md-12">${user.firstName}</div>
                        </div>
                        <div class="row">
                            <div class="col-md-12"><strong>Cognome</strong></div>
                            <div class="col-md-12">${user.lastName}</div>
                        </div>
                        <div class="row">
                            <div class="col-md-12"><strong>Email</strong></div>
                            <div class="col-md-12">${user.username}</div>
                        </div>
                    </div>		
                </div>
                <sec:authorize access="hasRole('ROLE_FB')">
                    <c:if test="${not empty categories}">
                    <div class="col-md-8 col-sm-6 col-xs-12 personal-info drop_down_container">
                        <h3>Facebook Info <i class="fa fa-caret-down drop_down_icon"></i></h3>
                        <div class="info_container drop_down_content">

                            <div class="row drop_down_container">
                                <div class="col-md-12"><strong>Likes Top Categories  <i class="fa fa-caret-right drop_down_icon"></i></strong></div>
                                <div class="col-md-12 drop_down_content" style="display: none">
                                    <%
                                        Map<String, List<String[]>> cat_list = (Map<String, List<String[]>>) request.getAttribute("categories");

                                        if (cat_list != null) {
                                            List<String[]> likes = cat_list.get("likes");
                                            for (String[] l : likes) {
                                    %>
                                    <p class="fb_pill"><%= l[0]%> <span class="badge"> <%= l[1]%> </span></p>
                                    <%
                                            }
                                        }


                                    %>
                                </div>
                            </div>
                            <div class="row drop_down_container">
                                <div class="col-md-12"><strong>Photos Top Categories  <i class="fa fa-caret-right drop_down_icon"></i></strong></div>
                                <div class="col-md-12 drop_down_content" style="display: none;">
                                    <%                                  if (cat_list != null) {
                                            List<String[]> photos = cat_list.get("photos");
                                            for (String[] l : photos) {
                                    %>
                                    <p class="fb_pill"><%= l[0]%> <span class="badge"> <%= l[1]%> </span></p>
                                    <%
                                            }
                                        }


                                    %>			  </div>
                            </div>
                            <div class="row drop_down_container">
                                <div class="col-md-12"><strong>Places Top Categories  <i class="fa fa-caret-right drop_down_icon"></i></strong></div>
                                <div class="col-md-12 drop_down_content" style="display: none">
                                    <%                                  if (cat_list != null) {
                                            List<String[]> places = cat_list.get("places");
                                            for (String[] l : places) {
                                    %>
                                    <p class="fb_pill"><%= l[0]%> <span class="badge"> <%= l[1]%> </span></p>
                                    <%
                                            }
                                        }


                                    %>		
                                </div>
                            </div>			
                        </div>		
                    </div>
                    </c:if>
                </sec:authorize>

            </div>
        </div>
    </body>
     <jsp:include page="access/loginModal.jsp" />
</html>

