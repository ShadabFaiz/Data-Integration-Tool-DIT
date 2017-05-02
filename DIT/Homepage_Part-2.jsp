<!--	This is a 2nd/last part of the result page which will have converted query.The original query will be on <code> Homepage </code> and on this
		the converted query will be displayed.The parameter <code> query </code> will contain the converted query.This must be displayed on after
		<code> QueryConverter </code> has converted the whole query.
		-->

<html>
 <head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/testbackground.css" />
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	
		
 </head>
	<body>
	<br>
	<div id="header">
	<h1>Converted Query<br> </h1> <p class="warning"><c:out value="${param.warning}" /></p>
	
	
	<textarea id="ConvertedQuery" rows="20" cols="100" readonly ><c:out value="${param.convertedQuery}" />	
	</textarea>
		</div>
	</body>
</html>