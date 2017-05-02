/* 	The main purpose of this class <code> MyFilter </code> is to prevent access of any page after the logout happens.
	To achieve this, 2 things have been done.
	1. 	Preventation of access of any page when user has not logout.To do so,every request has to be
		check if there is any session available.If no session is found or any session with no <code> username </code>
		is found,then request is redirected to the <code> Login </code> page.Otherwise,request is forwarded to appropriately.
	
	2.	Preventation of caching:When we press back button on any web browser,the browser uses cached page instead of sending request
		to Server. Due to this, even after logout,anyone can access the pages within the aplication.To prevent browser from caching any 
		page,we are setting certain header in the responses:-
		Cache-Control:no-store  HTTP 1.1.
		Pragma:no-cache			HTTP 1.0.
		Expires:-1
		 */

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

@WebFilter(	filterName = "MyFilter",
			urlPatterns={"/LogOutHandler","/Homepage","/conv/*" }, 
			description="Filter for checking session validation on every request")
			
public class MyFilter implements Filter
{
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException
	{
	try{
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		//Get the already created session .
		HttpSession session=req.getSession(false);
		
		//Prevent pages from being caching.
		res.setHeader("Cache-Control", "no-store"); // HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		res.setHeader("Expires", "-1"); // Proxies.
		
		//Checking if valid session is avialable or not nad perform task acoordingly.
	 	if( session != null && session.getAttribute("username") !=null	)
		{	chain.doFilter(request, response);
			}
		else
		{
			System.out.println("Session does not exit");
			res.sendRedirect(req.getServletContext().getContextPath()+"/Login.jsp");
		}
		
	    
		
	} catch(NullPointerException exception){ exception.printStackTrace(System.err); }
	}
	
	
	
	public void init(FilterConfig filterconfig)
	{
		}
	
	public void destroy()
	{	
		}
	
}