/* 	
	*/

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;



@WebServlet("/userCreation")
public class UserCreation extends HttpServlet
{
	static private Connection dbConnection=null;
	static private PreparedStatement createUserStatement=null;
	//private static Map<String,String> userpassMap=new LinkedHashMap<>();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
		try { 
			//useMapforCreation(request,response);
			useDatabaseforCreation(request,response);
		} catch(Exception e){e.printStackTrace(System.err); }
		}
		
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
			{	doPost(request,response);
				}
				
				
	static private void useMapforCreation(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{		
			
	}		
	
	
	/* For the development purpose,we are assuming that the database <code> dit </code> is already in the database and also the table
	<code> userpass </code>. */
	private void useDatabaseforCreation(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		try{
		dbConnection=MyContextListener.dbConnection;
		createUserStatement=dbConnection.prepareStatement("insert into userpass(username,password,email,occupation) value(?,?,?,?);");
		createUserStatement.setString(1,request.getParameter("username"));
		createUserStatement.setString(2,request.getParameter("password"));
		createUserStatement.setString(3,request.getParameter("email"));
		createUserStatement.setString(4,request.getParameter("occupation"));
		createUserStatement.executeUpdate();
		System.out.println(request.getParameter("username"));
		response.sendRedirect(getServletContext().getContextPath()+"/userCreationStatus.html");
		}
		catch(SQLException exception)
		{	String warning="";
			System.out.println(exception.getSQLState()+"/n" + exception.getErrorCode());
			if(	exception.getSQLState().equals("23000")	)
					warning="Username already exits";
			request.getRequestDispatcher("/SignUp.jsp?warning="+warning).forward(request,response);
		}
		
	}
}