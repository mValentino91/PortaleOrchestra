/*Server Variables*/
var SEND_TOKEN = "http://localhost:8080/orchestra/fbLoginJs"


/*Facebook Login*/	
function FbLogin()
{
    FB.login(function(response) {
            if (response.authResponse){	                
              var access_token =   FB.getAuthResponse()['accessToken'];
              //alert(access_token);
              $.FbSendToken(access_token);

            } 
            else {
             console.log('User cancelled login or did not fully authorize.');
            }

     },{scope: 'read_stream, email, user_birthday, user_religion_politics, user_hometown, user_location, user_likes, user_education_history, user_work_history, user_photos, user_tagged_places'});
}


/*Facebook Logout*/
function FbLogout()
{
    FB.logout(function(){document.location.reload();});
}

/*
 * Send token to authentication
 */
$.extend({
	FbSendToken : function (access_token) {
            $.ajax({
                dataType: "json",
                url: SEND_TOKEN,
                data: "access_token="+access_token, 
                type: "GET", 	
                success: function(result, stato){
                     if(result.login=="ok"){
                         loginDone();
                     }
                },
                error: function(richiesta,stato,errori){
                 //  alert("error!");
                } 
             });
        }
});