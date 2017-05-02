<%@ page isErrorPage="true" import="java.io.*" %>

<html>
<head>   
<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/loginBackground.css">
</head>
<body>
<div id="header">
<img src='images/404_error.jpg'/>  </div>

<% System.out.println(response.getStatus());
	
	if(exception != null )
			exception.printStackTrace();
%>

</body>
</html>