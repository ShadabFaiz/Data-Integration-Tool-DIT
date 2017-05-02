	/* 	Currently it only prints the notification when a session is destroyed for 
		a any user. */


import java.io.IOException;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener			
public class MySessionListener implements HttpSessionListener
{
	public void sessionCreated(HttpSessionEvent event)
	{
		}
	
	public void sessionDestroyed(HttpSessionEvent event)
	{
		System.out.println("Session is destroyed for user: "+event.getSession().getAttribute("username"));
	}
	
}