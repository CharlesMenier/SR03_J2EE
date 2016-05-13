package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnectionForm;
import model.dao.UserDao;

public class Connect extends Controller {
	
	private HttpSession session;
	
	private static final String PAGE = "/login.jsp";  
	private static final String URL = "/connexion/login";  
	
	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		getInfos(req, resp);
		
		if(CONTROLLER == null)
		{
			req.getRequestDispatcher(PAGE).forward(req, resp);
			return;
		}
				
		switch(CONTROLLER)
		{
		case "login":
			loginAction(req, resp);
			break;
			
		case "logout":
			logoutAction(req, resp);
			break;
			
		default:
			logoutAction(req, resp);
			break;	
		}
	}
	
	
	private void loginAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		ConnectionForm cf 	= new ConnectionForm();
		UserDao user 		= cf.connect(req);
		HttpSession session = req.getSession();
		
		// No error, we connect the user
		if(cf.getErrorMap().isEmpty())
		{
			session.setAttribute(USER_SESSION, user);
			
			if(user.isAdmin())
			{
				//req.getRequestDispatcher(DEFAULT_ADMIN).forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/admin/questionnaires");
				return;
			}
			else
			{
				//req.getRequestDispatcher(DEFAULT_USER).forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/user/questionnaires");
				return;
			}
		}
		else
		{
			req.setAttribute("errors", cf.getErrorMap());
			session.setAttribute(USER_SESSION, null);
			req.getRequestDispatcher(DEFAULT_NOT_CONNECTED).forward(req, resp);
			return;
		}
	}
	
	
	private void logoutAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect( req.getContextPath() + DEFAULT_NOT_CONNECTED );
	}

}
