<!-- 	This is the Homepage/the first page user will see upon visiting the application.
		The authentication informations (username,password) will be send to <code>LogInHandler</code>
		which will grant or deny the access to the application. -->

<!DOCTYPE html>
<html>
 <head>
   <link rel="stylesheet" href="./Styles/loginBackground.css">
 </head>
 <body>
  <div class="container">
   <div class="ball"></div>
   <div class="ball"></div>
   <div class="ball"></div>
   <div class="ball"></div>
   <div class="ball"></div>
   <div class="ball"></div>
   <div class="ball"></div>
  </div>

  <div id="Login_Info">
  <h1> < D I T > <br> DATA INTEGRATION TOOL</h1>
	<form action="LogInHandler" method="POST" autocomplete="on">
		<input type="text" placeholder="Username" name="username" autofocus="on"><br>
		<input type="password" placeholder="Password" name="password"><br>
		<input type="submit" value="LogIn">
	</form><br>
		<a href="SignUp.jsp" class="signUp"> SignUp </a>
	</div>	
	
	<a href="About.jsp" class="link">ABOUT</a>
  </body>
</html>