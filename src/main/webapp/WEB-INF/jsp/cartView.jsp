<%-- 
    Document   : cartView
    Created on : 2-feb-2015, 15.35.36
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/styles.css" rel="stylesheet">
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
        <title>CartView</title>
        <link href="./dist/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="components/sideBar.jsp"/>
        <div class="container-fixed">
            <div class="container-fluid">
                <div class="header text-center" style="margin-bottom: 20px; margin-top: 60px;">
                    <h2><span class="glyphicon glyphicon-star"></span> Preferiti</h2>
                </div>
                <jsp:include page="components/cartComponent.jsp"/>
            </div>
        </div>
    </body>
</html>
