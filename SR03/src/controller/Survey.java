package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.SubjectDao;
import model.dao.SurveyDao;

@SuppressWarnings("serial")
public class Survey extends Controller {
	
	SurveyDao survey;
	List<SurveyDao> surveys = null;
	List<SubjectDao> subjects = null;

	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PAGE 	= "/admin/survey.jsp"; 
		URL		= "/admin/questionnaires";
		getInfos(req, resp);
		
		surveys = SurveyDao.findAll();
		subjects = SubjectDao.findAll();
		
		req.setAttribute("surveys", surveys);
		req.setAttribute("subjects", subjects);
		
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
			if(GET)
			{
				survey = SurveyDao.find(Integer.parseInt(ID));
				req.setAttribute("survey", survey);
				//resp.sendRedirect(req.getContextPath() + "/admin/questions/show/" + ID);
				req.getRequestDispatcher(PAGE).forward(req, resp);
				return;
			}
			
			if(POST)
			{
				String subject = req.getParameter("selectSubject_edit");
				int status = (req.getParameter("status_edit") == null) ? 0 : 1 ;
				
				if(!SurveyDao.update(Integer.parseInt(ID), Integer.parseInt(subject), status))
				{
					req.setAttribute("error", "Erreur lors de l'édition");
				}
				
				//req.getRequestDispatcher(PAGE).forward(req, resp);
				resp.sendRedirect(req.getContextPath() + URL);;
				return;
			}
			
		}
		else resp.sendRedirect(req.getContextPath() + URL);
		
	}
	
	private void deleteAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// If there is no ID, we don't do anything
		if(ID != null)
		{
			if(!SurveyDao.delete(Integer.parseInt(ID)))
			{
				req.setAttribute("error", "Erreur lors de la suppression");
			}
			//req.getRequestDispatcher(PAGE).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL);
		}
	}
	
	private void addAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(POST)
		{
			String subject = req.getParameter("selectSubject");
			int status = (req.getParameter("status") == null) ? 0 : 1;
			
			if(!SurveyDao.insert(Integer.parseInt(subject), status))
			{
				req.setAttribute("error", "Erreur lors de l'ajout");
			}
			//req.getRequestDispatcher(PAGE).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL);
		}
	}
}
