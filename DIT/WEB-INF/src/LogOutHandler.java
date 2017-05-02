/*	The only purpose of this servlet is to remove the session when the user log out.
	After remove session,the user is redirected to the Login page.
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

import java.util.*;

@WebServlet("/LogOutHandler")
public class LogOutHandler extends HttpServlet
{

	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		System.out.println("LogOutHandler");
		request.getSession().invalidate();
		response.setHeader("Cache-Control", "no-store"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "-1"); // Proxies.
		response.sendRedirect(getServletContext().getContextPath()+"/Login.jsp");
	
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		System.out.println("Get of LogOutHandler");
		doPost(request,response);
	}
}
