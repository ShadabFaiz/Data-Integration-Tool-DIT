/* 	This class is for converting the queries.All the logic goes here.Once the converted query is obtained,
	Both the original query and converted query are passed to converge.jsp as parameter.They are send as
	<code>convertedQuery</code>convertedquery <code> &&Originalquery=</code> original query. */
	
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import java.net.URLDecoder;
import java.net.URLEncoder;

import java.io.PrintWriter;
import java.io.IOException;


@WebServlet("/QueryConverter")
public class QueryConverter extends HttpServlet
{
	
	private static String []leftOfWhereClause;
	private static String []whereClauseQuery;
	private static String tableName;
		public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
			ServletContext context=getServletContext();
			
			//Get the Originalquery submitted by user.
			String Originalquery=request.getParameter("Originalquery");;
			String convertedQuery;
			
		try { 	
				//Get the convertedQuery.
				convertedQuery=convertQuery(Originalquery);
				
				
				//Create the file for the query.
				request.setAttribute("convertedQuery",convertedQuery);
				request.getRequestDispatcher("/WEB-INF/recentFileHandler.jsp").include(request,response);
				convertedQuery=URLEncoder.encode(convertedQuery, "UTF-8");
				System.out.println("Redirecting to converge.jsp");
				response.sendRedirect(context.getContextPath()+"/conv/converge.jsp?convertedQuery="+convertedQuery+"&&Originalquery="+Originalquery);
			}
			catch(Exception e) { e.printStackTrace(System.err);	}
		}
		
		
		
		//This method <code>convertQuery</code> contains the logic to convert the query.
		public static String convertQuery(String Originalquery)
		{
			String warning="Warning..!! Check the Uppercase/LowerCase of your query";
			String tempConvertedQuery="";
			leftOfWhereClause=null;
			whereClauseQuery=null;
			tableName=null;
			
			//Remove the extra left/right space from the Original query.
			Originalquery=Originalquery.trim();
			
			/*  Check if there is any WHERE clause in the original query.If there is any,then split it.
				The First part will have left side of the WHERE clause and 2nd part will have the right 
				side part of the WHERE clause. */
			whereClauseQuery=Originalquery.split("(where)| (WHERE)");
			leftOfWhereClause=whereClauseQuery[0].split(" ");
			tableName=leftOfWhereClause[leftOfWhereClause.length-1];
			System.out.println(tableName);
			try
			{
				// To show all databases available.
				if( Originalquery.equals("show databases;") )
					tempConvertedQuery="show dbs;";
				
				
				// To use given databases.
				else if(	leftOfWhereClause[0].equals("use")	)
					tempConvertedQuery=Originalquery+".This command is same for both databases.";
				
				
				
				// To create a database.
				else if(	leftOfWhereClause[0].equals("create")	)
					{	
						try{
							if(leftOfWhereClause[1].equals("databases")	)
							tempConvertedQuery=  "use "+leftOfWhereClause[2];
							else if( leftOfWhereClause[1].toLowerCase().equals("databases")	)
								tempConvertedQuery= "use "+leftOfWhereClause[2]+"&&warning="+warning+" at databases command";
							else
								tempConvertedQuery=  "InvalidQuery/Query not support currently";
							}catch(ArrayIndexOutOfBoundsException exception)
							{
								exception.printStackTrace(System.err);
								tempConvertedQuery=  "&&warning=kindly specify the database name"; 
							}
					}
				else if(	leftOfWhereClause[0].toLowerCase().equals("create")	)
				{	try{
						tempConvertedQuery=  "use "+leftOfWhereClause[2]+"&&warning="+warning+" at create command" ;
						}
						catch(ArrayIndexOutOfBoundsException exception)
						{
							exception.printStackTrace(System.err);
							tempConvertedQuery=  "&&warning=kindly specify the database name";
						}
				}
						
				// To show all tables avialable in current databases. 
				else if(	Originalquery.toLowerCase().equals("show tables;")	)
					tempConvertedQuery=  "show collections;";
				
				
			// These following Queries have not been checked for case sensitivity. Kindly check them for case sensitivity before any use.	
				// Dropping the table.
				else if( leftOfWhereClause[0].toLowerCase().equals("drop") )
					tempConvertedQuery=  "db."+tableName.substring(0,tableName.length()-1)+".drop(";
				
				
				// To get all Field/Column details of the tables/collection.
				else if( leftOfWhereClause[0].toLowerCase().equals("select") )
					tempConvertedQuery=selectQuery();

				else if( leftOfWhereClause[0].toLowerCase().equals("insert") )
					tempConvertedQuery=insertQuery(Originalquery);
				
				
				
				
/* 				//From here on,the command conversion are from Mongo to MySql.
				else if( Originalquery.equals("show dbs;") )
					return "show databases;";
				
				else if(	leftOfWhereClause[0].equals("use")	)
					return "For showing list of DB:" +	Originalquery +	".\n For creating DB:create database "	+	leftOfWhereClause[1];
				
			
				else if(	Originalquery.toLowerCase().equals("show collections;")	)
					return "show tables;";
			 */
				else 
					tempConvertedQuery=  "Query not support currently";
		
			}catch(ArrayIndexOutOfBoundsException exception){	exception.printStackTrace(System.err); tempConvertedQuery=  "&&warning=kindly specify the database name"; }
				
				return tempConvertedQuery+");";
		}
		
		
		private static String selectQuery()
		{
				String tempQuery;
				tempQuery=  "db."+tableName.substring(0,tableName.length()-1)+".find(";
				boolean hasWHEREClause=false;
				/*  Check whether the query contains any WHERE clause or not.If it contains,its length will be greater than 1
					and all the conditional part will be in the 2nd index (i.e at index 1), other wise it will be 1. */
				if(whereClauseQuery.length > 1 )
				{
	
					tempQuery += "\n\t{\n\t";
					//	Split all the conditions into different parts.
					String []conditionsOfWhereClause=whereClauseQuery[1].split("(AND)| (and)| (&&)| (OR)|;");
					String conditionSymbol="noSymbol";
					String allConditions="";
					int index=0;
					for(String a:conditionsOfWhereClause)
					{
						hasWHEREClause=true;
						/*  Split each conditional statement into their column and value pair.
							1st index( 0th index) will have the column name and 2nd index(1th index) will 
							have its value to be checked against. */
						String []columnValueConditionPair=a.split("(>=)|(<=)|=|>|<");

						/* Check what condition is applied on the given column/Value pair. */
						if (a.indexOf(">=") != -1 )
							conditionSymbol=columnValueConditionPair[0]+":{ $gte:"+columnValueConditionPair[1]+"}";
						else if (a.indexOf("<=") != -1 )
							conditionSymbol=columnValueConditionPair[0]+":{ $lte:"+columnValueConditionPair[1]+"}";
						else if(a.indexOf("=") != -1 )
							conditionSymbol=columnValueConditionPair[0]+":"+columnValueConditionPair[1]+"";
						else if (a.indexOf(">") != -1 )
							conditionSymbol=columnValueConditionPair[0]+":{ $gt:"+columnValueConditionPair[1]+"}";
						else if(a.indexOf("<") != -1 )
							conditionSymbol=columnValueConditionPair[0]+":{ $lt:"+columnValueConditionPair[1]+"}";
						if(index++ < conditionsOfWhereClause.length-1 )
							conditionSymbol += ",\n\t";
						else
							conditionSymbol += "\n\t";
						allConditions += conditionSymbol;	
					}
					allConditions=allConditions.substring(0,allConditions.length()-1)+"\t";
					tempQuery += allConditions+"}";
				}
				
					if( leftOfWhereClause[1].equals("*") )
					{
						tempQuery += "";
						System.out.println("All column is showing");
					}
					// To get only the specified Fields of the collection.
					else 
					{	int columnNo=0;
						String []columnlist=leftOfWhereClause[1].split(",");
						if(hasWHEREClause)
							tempQuery += ",\n\t{\n\t";
						else
							tempQuery += "\n\t{\n\t";
						System.out.println("Converting query");
						while(	columnNo < columnlist.length	)
							tempQuery	+=	columnlist[columnNo++]+"=1,\n\t";
						tempQuery	+=	"remainingfields=0\n\t}\n";
						System.out.println("TEmpQuery=="+tempQuery);
					}
				
				return tempQuery.substring(0,tempQuery.length());
		}
		
		private static String insertQuery(String Query)
		{
			String []Originalquery=Query.split(" ",4);
			String tableName=Originalquery[2];
			Originalquery[3]=Originalquery[3].replace("VALUES","values");
			String []columnValuePair=Originalquery[3].split("values");
			
			String []columns=columnValuePair[0].split("\\(|,|\\)");

			String []values=columnValuePair[1].split("\\(|,|\\)");
			String tempQuery="";

			tempQuery="db."+tableName+".insertOne(\n\t{\n\t";
			int i=-1;

			while( ++i < columns.length )	
			{
				if(columns[i].trim().length() !=0 )
				{System.out.println(columns[i]+":"+values[i]); 	
				tempQuery += columns[i]+":"+values[i]+",\n\t"; }
			}
			tempQuery += "}\n";
			
			return tempQuery;
			
		}
		
		


}