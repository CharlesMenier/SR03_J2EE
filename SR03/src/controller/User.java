package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SubjectDao;
import model.dao.SurveyDao;
import model.dao.UserDao;

public class User extends Controller {  
	
	UserDao user;
	List<UserDao> users = null;

	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PAGE 	= "/admin/user.jsp"; 
		URL		= "/admin/utilisateurs";
		getInfos(req, resp);
		
		users = UserDao.findAll();
		
		req.setAttribute("users", users);
		
		// When no action set, we just display the page
		if(ACTION == null)
		{
			defaultAction(req, resp);
			return;
		}
		
		switch(ACTION)
		{
		case "edit":
			editAction(req, resp);
			break;
			
		case "delete":
			deleteAction(req, resp);
			break;
			
		case "add":
			addAction(req, resp);
			break;
			
		default:
			defaultAction(req, resp);
			break;
		}
	}
	
	private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(ID != null)
		{
			// Edit link clicked
			if(GET)
			{
				user = UserDao.find(Integer.parseInt(ID));
				req.setAttribute("user", user);
				//resp.sendRedirect(req.getContextPath() + "/admin/utilisateurs/show/" + ID);
				req.getRequestDispatcher(PAGE).forward(req, resp);
				return;
			}
			
			// Edit form validated
			if(POST)
			{
				String mail = req.getParameter("mail_edit");
				String password = req.getParameter("password_edit");
				String name = req.getParameter("name_edit");
				String society = req.getParameter("society_edit");
				String phone = req.getParameter("phone_edit");
				int admin = (req.getParameter("admin_edit") == null) ? 0 : 1 ;
				
				if(!UserDao.update(Integer.parseInt(ID), mail, password, name, society, phone, admin))
				{
					req.setAttribute("error", "Erreur lors de l'édition");
				}
				
				//req.getRequestDispatcher(PAGE).forward(req, resp);
				resp.sendRedirect(req.getContextPath() + URL);;
				return;
			}
		}
		
	}
	
	private void deleteAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// If there is no ID, we don't do anything
		if(ID != null)
		{
			if(!UserDao.delete(Integer.parseInt(ID)))
			{
				req.setAttribute("error", "Erreur lors de la suppression");
			}
			//req.getRequestDispatcher(URL).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL);
		}
	}
	
	private void addAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(POST)
		{
			String mail = req.getParameter("mail");
			String password = UserDao.generatePassword(6);
			String name = req.getParameter("name");
			String society = req.getParameter("society");
			String phone = req.getParameter("phone");
			int admin = (req.getParameter("admin") == null) ? 0 : 1;
			int status = 0;
			
			if(!UserDao.insert(mail, password, name, society, phone, admin, status))
			{
				req.setAttribute("error", "Erreur lors de l'ajout");
			}
			else
			{
				// Envoie du mail
			}
			//req.getRequestDispatcher(PAGE).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL);
		}
	}

}
