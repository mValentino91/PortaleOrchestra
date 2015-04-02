<!doctype html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./dist/css/poi_view.css">
<link rel="stylesheet" type="text/css" href="./dist/css/font-awesome.min.css">
<script src="./dist/js/jquery.js"></script>
<script src="./dist/js/bootstrap.min.js"></script>
    <script>
      function checkpassword() {
        var pass1= document.getElementById("p");
        var pass2= document.getElementById("confermap");
        var label= document.getElementById("perror");
        if(pass1.value != pass2.value){
            var label= document.createElement("label");
            label.id="confermaperror";
            label.innerHTML = "Le password inserite non combaciano";
            label.setAttribute("style","color: red");
            $(label).insertAfter("#confermap");
          
            pass2.setAttribute("style", "border-color: red");
            pass2.value="";
        }
    }
    function checkPassLen() {
        var pass1= document.getElementById("p");
        if(pass1.value.length < 8 && pass1.value.length > 0){
            var label= document.createElement("label");
            label.id="perror";
            label.innerHTML = "La password deve essere di almeno 8 caratteri";
            label.setAttribute("style","color: red");
            $(label).insertAfter("#p");
            pass1.value="";
            pass1.setAttribute("style","border-color:red");
        }
    }
    function removeAttributes(obj) {
        var label= document.getElementById(obj.id+"error");
        if (label != undefined)
                label.remove();
        obj.removeAttribute("style");
       
    }
    
    function validateEmail(email) {
        if(email != "") {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    if (!re.test(email)) {
       var label= document.createElement("label");
            label.id="emerror";
            label.innerHTML = "Inserisci un indirizzo email corretto";
            label.setAttribute("style","color: red");
            $(label).insertAfter("#em");
            var em= document.getElementById("em");
            em.value="";
            em.setAttribute("style", "border-color: red");
    }
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
    
           
           
<div class="container-fixed" style="padding-top: 60px;">
     <jsp:include page="components/sideBar.jsp"/>
  <h1 class="page-header">Edit Your Profile - ${user.username}</h1>
  <form:form class="form-horizontal" enctype="multipart/form-data"  action="userEditProfile" method="POST">
  <div class="row">
    <!-- left column -->
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="text-center">
          
        <img src="${avatar}" id="imgdiv" style="width: 200px; height: 200px;" class="avatar img-circle img-thumbnail img-responsive" alt="avatar">
        <h6>Change your avatar</h6>
        <input type="file" name="avatar" onchange="readURL(this)" id="tastoavatar" class="text-center center-block well well-sm">
      </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">

      <h3>Personal info</h3>
       <form:input class="form-control" type="hidden" value="${user.username}" path="username" />
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
          <label class="col-md-3 control-label">New password:</label>
          <div class="col-md-8">
            <form:input class="form-control" placeholder="Not Changed" id='p' onfocus="removeAttributes(this)" onblur="checkPassLen()" path="password" type="password"  />
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Confirm new password:</label>
          <div class="col-md-8">
            <input class="form-control" id="confermap" placeholder="Not Changed" onfocus="removeAttributes(this)" onblur="checkpassword()" type="password">
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