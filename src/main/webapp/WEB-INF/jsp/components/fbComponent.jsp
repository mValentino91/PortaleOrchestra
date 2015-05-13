<div id="fb-root"></div>
<script>
 //Facebook login script
 window.fbAsyncInit = function() {
        FB.init({
          appId      : '1507821126169380', // App ID
          channelUrl : 'http://www.orchestra.unina.it/orchestra/dist/fb_js_pack/channel.html', // Channel File
          status     : true, // check login status
          cookie     : true, // enable cookies to allow the server to access the session
          xfbml      : true  // parse XFBML
        });
 };

 /*Facebook login script
 window.fbAsyncInit = function() {
        FB.init({
          appId      : '1507821126169380', // App ID
          channelUrl : 'http://localhost:8080/orchestra/dist/fb_js_pack/channel.html', // Channel File
          status     : true, // check login status
          cookie     : true, // enable cookies to allow the server to access the session
          xfbml      : true  // parse XFBML
        });
 };
*/
/*Load the SDK asynchronously*/
(function(d){
       var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
       if (d.getElementById(id)) {return;}
       js = d.createElement('script'); js.id = id; js.async = true;
       js.src = "//connect.facebook.net/en_US/all.js";
       ref.parentNode.insertBefore(js, ref);
 }(document));
</script>