package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AnswerDao;
import model.dao.QuestionDao;

@SuppressWarnings("serial")
public class Question extends Controller {
	
	int SURVEY = 0;
	
	QuestionDao question;
	List<QuestionDao> questions = null;
	
	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PAGE 	= "/admin/question.jsp";
		URL 	= "/admin/questions/show/";
		getInfos(req, resp);
		
		if(SURVEY != 0)
		{
			questions = QuestionDao.findAll(SURVEY);
			req.setAttribute("questions", questions);
			req.setAttribute("SURVEY", SURVEY);
		}
		
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
			
		case "show":
			showAction(req, resp);
			break;
			
		default:
			defaultAction(req, resp);
			break;
		}		
	}
	
	private void showAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(ID != null)
		{
			SURVEY = Integer.parseInt(ID);
			questions = QuestionDao.findAll(Integer.parseInt(ID));
			req.setAttribute("questions", questions);
			//resp.sendRedirect(req.getContextPath() + "/admin/questions/show/" + ID);
			req.getRequestDispatcher(PAGE).forward(req, resp);
			return;
		}
		
	}
	
	private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(ID != null)
		{
			if(GET)
			{
				question = QuestionDao.find(Integer.parseInt(ID));
				req.setAttribute("question", question);
				//resp.sendRedirect(req.getContextPath() + "/admin/questions/show/" + ID);
				req.getRequestDispatcher(PAGE).forward(req, resp);
				return;
			}
			
			if(POST)
			{
				int survey = Integer.parseInt(req.getParameter("survey_edit"));
				String label = req.getParameter("label_edit");
				int number = Integer.parseInt(req.getParameter("number_edit"));
				int status = (req.getParameter("status_edit") == null) ? 0 : 1;
				
				if(QuestionDao.exist(survey, number))
				{
					req.setAttribute("error", "Une autre question porte déjà ce numéro");
				}
				else if(!QuestionDao.update(Integer.parseInt(ID), survey, label, number, status))
				{
					req.setAttribute("error", "Erreur lors de l'édition");
				}
				
				resp.sendRedirect(req.getContextPath() + URL + SURVEY);
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
			if(!QuestionDao.delete(Integer.parseInt(ID)))
			{
				req.setAttribute("error", "Erreur lors de la suppression");
			}
			//req.getRequestDispatcher(PAGE).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL + SURVEY);
		}
	}
	
	private void addAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int survey = Integer.parseInt(req.getParameter("survey"));
		String label = req.getParameter("label");
		int number = Integer.parseInt(req.getParameter("number"));
		int status = (req.getParameter("status") == null) ? 0 : 1;
		
		if(QuestionDao.exist(survey, number))
		{
			req.setAttribute("error", "Une autre question porte déjà ce numéro");
		}
		else if(!QuestionDao.insert(survey, label, number, status))
		{
			req.setAttribute("error", "Erreur lors de l'ajout");
		}
		//req.getRequestDispatcher(PAGE).forward(req,  resp);
		resp.sendRedirect(req.getContextPath() + URL + survey);
	}

}
