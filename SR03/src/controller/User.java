package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User extends Controller {

	private static final String PAGE = "/admin/user.jsp";   
	
	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(PAGE);
		req.getRequestDispatcher(PAGE).forward(req,  resp);
	}

}
