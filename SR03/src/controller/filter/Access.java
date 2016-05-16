package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDao;

public class Access implements Filter {
	
	private static final String USER_SESSION = "sessionUser";
	private static final String DEFAULT_NOT_CONNECTED 	= "/login.jsp";
	private static final String DEFAULT_ADMIN 			= "/admin/survey.jsp";
	private static final String DEFAULT_USER 			= "/user/survey.jsp";
	
	private UserDao user = null;

	private String allow;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	private String MODULE;
	private String CONTROLLER;
	private String ACTION;
	private String ID;

	@Override
	public void init(FilterConfig fc) throws ServletException 
	{
		allow = fc.getInitParameter("allow");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Cast to servlet objects
		req 	= (HttpServletRequest)request;
		resp 	= (HttpServletResponse)response;
		session = req.getSession();
		
		user = (UserDao)session.getAttribute(USER_SESSION);
		
		MODULE		= (String)req.getAttribute("MODULE");
		CONTROLLER	= (String)req.getAttribute("CONTROLLER");
		ACTION		= (String)req.getAttribute("ACTION");
		ID			= (String)req.getAttribute("ID");		
		
		// Allow access to resources and connection module
		if(MODULE.equals(allow) || MODULE.equals("connexion"))
		{
			chain.doFilter(req, resp);
			return;
		}
		
		// Not connected, we send the user back to the connection form
		if(user == null)
		{
			req.setAttribute("error", "Vous n'êtes pas connecté");
			req.getRequestDispatcher(DEFAULT_NOT_CONNECTED).forward(req, resp);
			return;
		}
		else
		{			
			// Normal users can't access admin module
			if(!user.isAdmin() && MODULE == "admin")
			{
				req.getRequestDispatcher(DEFAULT_USER).forward(req, resp);
				return;
			}
			else chain.doFilter(req, resp);
		}
		
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
