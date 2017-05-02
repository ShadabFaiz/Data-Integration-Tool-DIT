

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.*;

@WebServlet("/readRecentFilesHandler")
public class readRecentFilesHandler extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{		
			try{
				getServletContext().getRequestDispatcher("/readRecentFiles.jsp").forward(request,response);
			}catch(Exception e) { e.printStackTrace(System.err); }
	}
}