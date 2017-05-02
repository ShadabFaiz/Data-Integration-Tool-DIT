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


@WebServlet("/Homepage")
public class Homepage extends HttpServlet
{
		
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
		try { 
				getServletContext().getRequestDispatcher("/Homepage.jsp").forward(request,response);
			}
			catch(Exception exception){ exception.printStackTrace(System.err); }
		}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doPost(request,response);
	}
}
		