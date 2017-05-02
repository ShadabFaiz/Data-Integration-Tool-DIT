<!-- 	This is the place where user will enter the query and get result.User have to enter query in <code> textarea </code> and
		and then click <code>Convert it...!!</code> to get the result -->
		
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/HomepageStyle.css" />
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
</head>
<body>
	<a href="${pageContext.request.contextPath}/LogOutHandler" class="link">Sign Out </a>
	<br><br>
	<a href="${pageContext.request.contextPath}/readRecentFilesHandler" class="link">Recent Files </a>
	<div id="header"><h1> < D I T > <br> DATA INTEGRATION TOOL</h1> 
	
	<h2>Original Query</h2>
	<!--	This is the area/form which will be send to <code>	QueryConverter </code> -->
		<form action="${pageContext.request.contextPath}/QueryConverter" method="POST" name="QueryForm">
			<textarea rows="20" cols="100" autofocus name="Originalquery" placeholder="<c:out value='${param.OriginalQuery}' default='aaaaaa' />" ><c:out value="${param.Originalquery}" /> </textarea>
			<br>
			<input type="submit" value="Convert it...!!">			
		</form> 
	</div>
	
	<!-- Additional links to learn more about MongoDB and MYSql -->
	<a href="https://docs.mongodb.com/manual/reference/sql-comparison/" class="footer">Example </a>
	<a href="http://johnjardin.ukuvuma.co.za/2014/01/07/convert-mysql-queries-mongodb-using-querymongo/" class="footer">Examples 2</a><br><br><br>
	<a href="testingError.jsp" class="footer">Test exception</a>
</body>
</html>


	