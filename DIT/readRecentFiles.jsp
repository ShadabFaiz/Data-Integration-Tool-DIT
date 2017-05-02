<%-- 	The purpose of this page is too show the list of files in the <code>recentFiles</code> directory. --%>
		

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/recentFilesbackground.css">
<style>
h3 { 
    word-spacing: 100px;
}
</style>
</head>
	<body>
		<div id="header">
			<h1>Recent Files </h1>
		
			<%@ page import="java.io.File,java.io.FileWriter,java.io.BufferedWriter,java.util.*" %>
			<%! File recentFilesDirectory;
			File listofFiles[];
			%>
			<%	//Get the Location of the <code> recentFiles </code> directory.
				String recentFilesDirectoryLocation=application.getInitParameter("recentFilesLocation");
				System.out.println("\n application.getRealPath(recentFilesDirectoryLocation)"+application.getRealPath(recentFilesDirectoryLocation));
				System.out.println("\n application.getRealPath(displayRecentFile.jsp)"+application.getRealPath("./Homepage.jsp"));
				recentFilesDirectory=new File(application.getRealPath(recentFilesDirectoryLocation));
				
				//Get the list of all the most recent files.
				listofFiles=recentFilesDirectory.listFiles();
	
				//Create the link for all the files.
					for(File f:listofFiles)	
						out.write("<a href='./displayRecentFile.jsp?fileToDisplay="+f.getName()+"' background-color='white' class='link'>"+f.getName()+"</a><br>");	
			%>
		</div>
</body>
</html>
