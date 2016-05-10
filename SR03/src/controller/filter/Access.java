package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Access implements Filter {
	
	private static final String DEFAULT = "/login.jsp";
	private static final String USER_SESSION = "sessionUser";
	private HttpSession session;
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest sReq, ServletResponse sResp, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest req 		= (HttpServletRequest)sReq;
		HttpServletResponse resp 	= (HttpServletResponse)sResp;
		
		session = req.getSession();
		
		if(session.getAttribute(USER_SESSION) == null)
		{
			req.setAttribute("error", "Vous n'avez pas accès à cette page car vous n'êtes pas connecté");
			req.getRequestDispatcher(DEFAULT).forward(req, resp);
		}
		else chain.doFilter(req, resp);
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
