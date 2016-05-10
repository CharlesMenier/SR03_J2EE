package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnectionForm;
import model.dao.DaoConnector;
import model.dao.SubjectDao;
import model.dao.UserDao;

public class Login extends HttpServlet {
	
	private static final String ADMIN = "/private/indexAdmin.jsp";
	private static final String USER = "/private/indexUser.jsp";
	private static final String DEFAULT = "/login.jsp";
	private static final String USER_SESSION = "sessionUser";
	
	private HttpSession session;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		session = req.getSession();
		if(session.getAttribute(USER_SESSION) == null)
			getServletContext().getRequestDispatcher(DEFAULT).forward(req, resp);
		else
		{
			UserDao user = (UserDao)session.getAttribute(USER_SESSION);
			if(user.isAdmin())
			{
				req.getRequestDispatcher(ADMIN).forward(req, resp);
			}
			else
			{
				req.getRequestDispatcher(USER).forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		ConnectionForm cf = new ConnectionForm();
		UserDao user = cf.connect(req);
		
		HttpSession session = req.getSession();
		
		// No error, we connect the user
		if(cf.getErrorMap().isEmpty())
		{
			session.setAttribute(USER_SESSION, user);
			if(user.isAdmin())
			{
				req.getRequestDispatcher(ADMIN).forward(req, resp);
			}
			else
			{
				req.getRequestDispatcher(USER).forward(req, resp);
			}
			
		}
		else
		{
			req.setAttribute("errors", cf.getErrorMap());
			session.setAttribute(USER_SESSION, null);
			req.getRequestDispatcher(DEFAULT).forward(req, resp);
		}
		
	}

}
