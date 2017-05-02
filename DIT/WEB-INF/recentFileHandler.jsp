<%-- 	The purpose of this <code> QueryConverter </code> is to take input from the <code> query </code> text area  from <code> Homepage </code>
		and produces its result by combining <code> Homepage and Homepage_Part-2 </code> in a single HTML page.It also saves the recent queries in the directory 
		<code>recentFiles</code> 	
		--%>

<%-- 	Create <code>recentFiles</code> directory to store recent queries.Each queries will be stored on each different "txt" file counting from 
		1 till the session last.For ex,For the first query,it will be stored in the file"1.txt" and so on 
		--%>
		
<%@ page import="java.io.File,java.io.FileWriter,java.io.BufferedWriter" %>
<%! File recentFilesDirectory;
	int fileCount=0; %>
<%
	//Location of the <code>recentFiles</code> directory. 
	String recentFilesDirectoryLocation=application.getInitParameter("recentFilesLocation");
	recentFilesDirectory=new File(application.getContextPath()+File.separator+recentFilesDirectoryLocation);
	
	//If the <code>recentFiles</code> directory does not exist,then create it. 
		if(!recentFilesDirectory.exists())
		{	recentFilesDirectory.mkdir();	}
	
		
	//Get the query entered by the User. 
		String Originalquery=request.getParameter("Originalquery");
		
	/**	If the user has not entered any query and then pressed enter,then do not create new File.It is done so to prevent the creation 
		of new File on page refresh.If we do not check for this,then each time the user refresh page with blank textarea,then each time 
		a new file will be created. **/
		
		if(request.getParameter("Originalquery").trim().length() != 0 )
		{
			System.out.println("Context Path:");
			File file=new File(application.getContextPath()+File.separator+recentFilesDirectoryLocation+File.separator+fileCount+".txt");
			BufferedWriter outputFile=new BufferedWriter(new FileWriter(application.getRealPath(recentFilesDirectoryLocation)+File.separator+fileCount+".txt"));
			fileCount++;
			
			//Save the original and converted query in the file.
			String border="------------------------------------------";
			outputFile.write("Original Query\n"+Originalquery+"\n\n"+border);
			outputFile.write("\n\n Converted Query\n\n"+request.getAttribute("convertedQuery"));
			
			outputFile.close();
			System.out.println("total files created:"+fileCount);
		}
		
%>
   