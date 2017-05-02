<!-- 	This is the Homepage/the first page user will see upon visiting the application.
		The authentication informations (username,password) will be send to <code>LogInHandler</code>
		which will grant or deny the access to the application. -->

<!DOCTYPE html>
<html>
 <head>
   <link rel="stylesheet" href="./Styles/signUp.css">
 </head>
 <body>


  <div id="signUp">
  <h1> < D I T > <br> User Creation</h1>
	<form action="userCreation" method="POST" autocomplete="on">
		<h3>
		<p class="warning"> ${param.warning} </p>
		User Name: <input type="text" placeholder="i wonder what username you want..." name="username" autofocus="on" required><br>
		Password: <input type="password" placeholder="give a nice password...	 " name="password" required><br>
		Occupation: <input type="text" placeholder="so who are you? a student/professional..?" name="password" required><br>
		Email:   <input type="text" placeholder="email address...." name="occupation"><br>
		<input type="submit" value="create New User">
	</form><br>
	</div>	
  </body>
</html>