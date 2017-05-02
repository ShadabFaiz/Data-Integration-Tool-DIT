	
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Map;
import java.util.LinkedHashMap;

@WebListener
public class MyContextListener implements ServletContextListener
{
	static HttpSession session=null;
	static Connection dbConnection=null;
	public void contextInitialized(ServletContextEvent event)
	{
			System.out.println("\n \n \n \n DIT ServletContext is being initialized..... \n \n \n");
			createDatabaseConnection();
		}

		
	//Perform cleanups like removing DB connection.closing session etc. while closing appliction
	public void contextDestroyed(ServletContextEvent event)
	{
		System.out.println("\n (DIT) Performing Cleanups... \n ");
		}
		
		
	//Create the database connection.
	private void createDatabaseConnection()
	{
		System.out.println("Database connection is initiated.....");
		String dbUrl="jdbc:mysql://localhost:3306/dit?zeroDateTimeBehavior=convertToNull";
		try
		  {
			 dbConnection=DriverManager.getConnection(dbUrl,"root","MySql");
			System.out.println("\n Connection is  established successfully....");
	     } 
		catch(SQLException e){ System.out.println( e+"\n Connection is not established"); }		
	}
}