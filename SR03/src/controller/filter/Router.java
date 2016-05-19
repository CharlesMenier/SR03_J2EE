package controller.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router implements Filter {
	
	private static final Pattern URL_PATTERN = Pattern.compile("/SR03/([\\w\\.]+)/?([\\w\\.]+)?/?([\\w\\.]+)?/?([\\w\\.]+)?"); 
	private static final String DEFAULT = "/login.jsp";	
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	private String URL;
	private String MODULE;
	private String CONTROLLER;
	private String ACTION;
	private String ID;
	
	
	@Override
	public void init(FilterConfig fc) throws ServletException 
	{
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Cast to servlet objects
		req 	= (HttpServletRequest)request;
		resp 	= (HttpServletResponse)response;
		
		URL	= req.getRequestURI();
		
		Matcher m = URL_PATTERN.matcher(URL);
	
		if(!m.matches())
		{
			req.setAttribute("error", "L'url demandée est incorrecte");
			req.getRequestDispatcher(DEFAULT).forward(req, resp);
		}
		else
		{
			MODULE 		= m.group(1);
			CONTROLLER 	= m.group(2);
			ACTION		= m.group(3);
			ID			= m.group(4);
			
			if(MODULE == null) req.getRequestDispatcher(DEFAULT).forward(req, resp);
			else
			{
				req.setAttribute("MODULE", MODULE);
				req.setAttribute("CONTROLLER", CONTROLLER);
				req.setAttribute("ACTION", ACTION);
				req.setAttribute("ID", ID);
				
				chain.doFilter(req, resp);
			}
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
