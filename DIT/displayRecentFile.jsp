<%--	The purpose of this <code>displayRecentFile</code> is to display the selected file in the text area. --%>

<%@ page import="java.io.File,java.io.FileReader,java.io.BufferedReader" %>

<%	String recentFilesDirectoryLocation=application.getInitParameter("recentFilesLocation");

 	if( request.getParameter("checkFileStatus") !=null )
	{
			File recentFilesDirectory=new File(application.getRealPath(recentFilesDirectoryLocation));
			System.out.println("Recent Directory Exists ? :"+recentFilesDirectory.exists());
		}
	
	File recentFilesDirectory=new File(application.getRealPath(recentFilesDirectoryLocation));
	System.out.println("File Requested to Display:"+request.getParameter("fileToDisplay"));
	BufferedReader fileToRead=new BufferedReader(new FileReader(recentFilesDirectory+"//"+request.getParameter("fileToDisplay")));
	StringBuilder originalQuery=new StringBuilder();
	String temporary="";
	while( (temporary=fileToRead.readLine()) != null )
		originalQuery.append(temporary+"\n");
			
	fileToRead.close();
%>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/recentFilesbackground.css">
</head>
	<body>
		<div id="header">
			<h1>Recent Files </h1> <h2> ${param.fileToDisplay} </h2>
				<textarea rows="20" cols="100" autofocus name="query" readonly><%= originalQuery %></textarea>
		</div>
	</body>
</html>	
