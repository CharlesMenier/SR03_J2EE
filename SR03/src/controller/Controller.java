package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public abstract class Controller extends HttpServlet {

	protected static final String USER_SESSION = "sessionUser";
	
	protected static final String DEFAULT_NOT_CONNECTED 	= "/login.jsp";
	protected static final String DEFAULT_ADMIN 			= "/admin/survey.jsp";
	protected static final String DEFAULT_USER 				= "/user/survey.jsp";
	
	protected static String PAGE;
	protected static String URL;
	protected static String PREVIOUS;
	
	protected String MODULE;
	protected String CONTROLLER;
	protected String ACTION;
	protected String ID;
	
	protected boolean POST = false;
	protected boolean GET = false;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		GET = true;
		POST = false;
		handleActions(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		POST = true;
		GET = false;
		handleActions(request, response);
	}
	
	protected void getInfos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PREVIOUS 	= MODULE + "/" + CONTROLLER + "/" + ACTION + "/" + ID;
		
		MODULE 		= (String)req.getAttribute("MODULE");
		CONTROLLER 	= (String)req.getAttribute("CONTROLLER");
		ACTION 		= (String)req.getAttribute("ACTION");
		ID 			= (String)req.getAttribute("ID");
		
		System.out.println("prev:" + PREVIOUS);
		System.out.println(MODULE + " / " + CONTROLLER + " / " + ACTION + " / " + ID);
	}
	
	abstract protected void handleActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	protected void defaultAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{		
		req.getRequestDispatcher(PAGE).forward(req,  resp);
		return;
	}
}
