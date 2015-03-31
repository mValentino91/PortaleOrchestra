<!doctype html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./dist/css/bootstrap.min.css">
<script src="./dist/js/jquery.js"></script>
<script src="./dist/js/bootstrap.min.js"></script>
    <script>
    function checkpassword() {
        var pass1= document.getElementById("p");
        var pass2= document.getElementById("confermap");
        
        if(pass1.value != pass2.value){
            alert("Le due password non combaciano!");
            pass2.value="";
        }
    }
    
    function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            var img= document.getElementById("imgdiv");
            img.setAttribute("src", e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

    </script>
</head>
<body>
    
           
           
<div class="container" style="padding-top: 60px;">
     <jsp:include page="components/sideBar.jsp"/>
  <h1 class="page-header">Sign In</h1>
  <form:form class="form-horizontal" enctype="multipart/form-data"  action="userEditProfile" method="POST">
  <div class="row">
    <!-- left column -->
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="text-center">
          
        <img src="./dist/img/default_avatar.png" id="imgdiv" style="width: 200px; height: 200px;" class="avatar img-circle img-thumbnail img-responsive" alt="avatar">
        <h6>Upload your avatar</h6>
        <input type="file" name="avatar" onchange="readURL(this)" id="tastoavatar" class="text-center center-block well well-sm" required>
      </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">

      <h3>Personal info</h3>
      
        <div class="form-group">
          <label class="col-lg-3 control-label">First name:</label>
          <div class="col-lg-8">
            <form:input class="form-control" path="firstName" value="${user.firstName}" required='required' />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Last name:</label>
          <div class="col-lg-8">
            <form:input class="form-control" path="lastName" value="${user.lastName}" type="text" required='required' />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Username:</label>
          <div class="col-lg-8">
            <form:input class="form-control" path="username" value="${user.username}" type="text" required='required' />
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Password:</label>
          <div class="col-md-8">
            <form:input class="form-control" id='p' path="password" type="password"  />
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Confirm password:</label>
          <div class="col-md-8">
            <input class="form-control" id="confermap" onblur="checkpassword()" type="password">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
            <input class="btn btn-primary" value="Submit" type="submit">
          </div>
        </div>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>