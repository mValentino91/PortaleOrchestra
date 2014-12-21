<%-- 
    Document   : infopoi
    Created on : 2-dic-2014, 12.10.28
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/styles.css" rel="stylesheet">
        <link href="./dist/css/section.css" rel="stylesheet">
        <link href="./dist/css/composite.css" rel="stylesheet">
        <link href="./dist/css/contacts.css" rel="stylesheet">
        <script src="./dist/js/section.js"></script>
        <script src="./dist/js/composite.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        <script src="./dist/js/readmore.js"></script>
        <style>
            /* Custom container */
            body{
                background-color: lightgray;
                color: #285e8e;
            }
            .container-fixed {
                margin: 0 auto;
                max-width: 1150px;
                background-color: whitesmoke;
            }
            .container-fixed > hr {
                margin: 30px 0;
            }

            #footer{
                padding-top: 100px;
                padding-bottom: 20px;
            }
        </style>
        <title>${poi.name}</title>   
    </head>
    <body>
        
        <div class="container-fixed">
           
            <jsp:include page="components/sideBar.jsp"/>
            <c:if test="${not empty coverimg}">
                <jsp:include page="components/CoverComponent.jsp"/>
            </c:if>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-8">
                                <c:if test="${not empty description}">
                                <jsp:include page="components/SectionComponent.jsp"/> 
                                </c:if>
                                <c:if test="${not empty imagegallery}">
                                    <jsp:include page="components/ImgGalleryComponent.jsp"/> 
                                </c:if>
                            </div>
                            <div class="col-md-4">
                                <jsp:include page="components/mapComponent.jsp"/>
                                 
                                <c:if test="${not empty contacts}">
                                    <jsp:include page="components/ContactsComponent.jsp"/>
                                </c:if>
                                <c:choose>
                                   <c:when test="${not empty workingtime && not empty prices && not empty services || not empty workingtime && not empty prices || not empty workingtime && not empty services || not empty prices && not empty services}">
                                    <jsp:include page="components/CompositeComponent.jsp"/>
                                   </c:when> 
                                    <c:when test="${not empty workingtime}">
                                        <jsp:include page="components/WorkingTimeComponent.jsp"/>
                                    </c:when>
                                   <c:when test="${not empty prices}">
                                        <jsp:include page="components/PricesComponent.jsp"/>
                                    </c:when>
                                   <c:when test="${not empty services}">
                                        <jsp:include page="components/ServicesComponent.jsp"/>
                                    </c:when>
                                </c:choose>
                                    </div>  
                                </div>
                            </div>
                        </div>
                    </div>
                
                <div class="row">
                    <div class="col-md-12" id="footer">
                        <center>
                            <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                        </center>
                    </div>
                </div>
            </div>
       
       
        <script>
             $('article').readmore({ speed: 5, maxHeight: 200, moreLink: '<a href="#" style="text-align: right; font-size:200%; text-decoration: none; margin: 0 0 0 0;"><i class="fa fa-chevron-down"></i></a>', lessLink: '<a href="#" style="text-align: right; font-size:200%; text-decoration: none;"><i class="fa fa-chevron-up"></i></a>'});
        
           
      
        </script>
    </body>
</html>

