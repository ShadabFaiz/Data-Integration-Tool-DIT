/* 	*This class is responsible for handling the LogIn.
	*Correct Information to login:
	*					username:anything (it doesn't matter).
	*					password:SNisha.
	*					
	*Once the information has been verified,it will create a new session from <code>createSession()</code> method and add the "username" and "password"
	*attribute the the created session.
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
import java.sql.ResultSet;



@WebServlet("/LogInHandler")
public class LogInHandler extends HttpServlet
{
	private static Connection dbConnection=null;
	private static PreparedStatement checkUserStatement=null;
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
		try { 
			response.setContentType("text/html");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			PrintWriter pw=response.getWriter();
			boolean userExists=false;
			
			//If password is correct, then only proceed to Homepage otherwise redirect to Login Page.
			userExists=checkUser(request,response);
			
			System.out.println("userExists:"+userExists);
			if(userExists)
				{
						createSession(username,password,request);
						getServletContext().getRequestDispatcher("/QueryConvertorOption.jsp").forward(request,response);
				}
			else
			{ response.sendRedirect(getServletContext().getContextPath()+"/Login.jsp") ;}
		} catch(Exception e){e.printStackTrace(System.err); }
		}
		
		private boolean checkUser(HttpServletRequest request,HttpServletResponse response)
		{
			
			try{
				//Get the dbConnection from MyContextListener class.
				dbConnection=MyContextListener.dbConnection;
				checkUserStatement=dbConnection.prepareStatement("select * from userpass where username=? && password=?;");
				
				// Get the username and password user typed from request body.
				checkUserStatement.setString(1,request.getParameter("username"));
				checkUserStatement.setString(2,request.getParameter("password"));
				
				/* 	If the query below  runs successfully,then a Resultset will be obtained.
					This ResultSet contains any rowsthen it means the user with given password 
					exists otherwise no user with given password exists. */
				ResultSet rs=checkUserStatement.executeQuery();
				if(rs.next())
				{		rs.beforeFirst(); return true; }
				else 
				{	rs.beforeFirst(); return false;	}
			}
			catch(SQLException exception)
			{ exception.printStackTrace(System.err); 
					return false; }
		}
		
		
		/* This method is meant to create a new Session upon successful logIn. */
		private  void createSession(String username,String password,HttpServletRequest request)
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			System.out.println("Session created for\n username: "+username+"\n password: "+password);
			MyContextListener.session=session;
		}
		
			public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
			{	doPost(request,response);
				}

}