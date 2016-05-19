package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AnswerDao;
import model.dao.QuestionDao;

@SuppressWarnings("serial")
public class Answer extends Controller {
	
	int QUESTION = 0;
	
	AnswerDao answer;
	List<AnswerDao> answers = null;
	
	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PAGE 	= "/admin/answer.jsp";
		URL 	= "/admin/reponses/show/";
		getInfos(req, resp);
		
		if(QUESTION != 0)
		{
			answers = AnswerDao.findAll(QUESTION);
			req.setAttribute("answers", answers);
			req.setAttribute("QUESTION", QUESTION);
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
			QUESTION = Integer.parseInt(ID);
			answers = AnswerDao.findAll(Integer.parseInt(ID));
			req.setAttribute("answers", answers);
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
				answer = AnswerDao.find(Integer.parseInt(ID));
				req.setAttribute("answer", answer);
				//resp.sendRedirect(req.getContextPath() + "/admin/questions/show/" + ID);
				req.getRequestDispatcher(PAGE).forward(req, resp);
				return;
			}
			
			if(POST)
			{
				int question = Integer.parseInt(req.getParameter("question_edit"));
				String label = req.getParameter("label_edit");
				int number = Integer.parseInt(req.getParameter("number_edit"));
				int correct = (req.getParameter("correct_edit") == null) ? 0 : 1;
				int status = (req.getParameter("status_edit") == null) ? 0 : 1;
				
				//@todo corriger
				
				if(AnswerDao.exist(question, number))
				{
					req.setAttribute("error", "Une autre réponse porte déjà ce numéro");
				}
				else if(QuestionDao.hasCorrect(question) && correct == 1)
				{
					req.setAttribute("error", "Cette question a déjà une réponse correcte");
				}
				else if(!AnswerDao.update(Integer.parseInt(ID), question, label, number, correct, status))
				{
					req.setAttribute("error", "Erreur lors de l'édition");
				}
				
				resp.sendRedirect(req.getContextPath() + URL + QUESTION);
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
			if(!AnswerDao.delete(Integer.parseInt(ID)))
			{
				req.setAttribute("error", "Erreur lors de la suppression");
			}
			//req.getRequestDispatcher(PAGE).forward(req,  resp);
			resp.sendRedirect(req.getContextPath() + URL + QUESTION);
		}
	}
	
	private void addAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int question = Integer.parseInt(req.getParameter("question"));
		String label = req.getParameter("label");
		int number = Integer.parseInt(req.getParameter("number"));
		int correct = (req.getParameter("correct") == null) ? 0 : 1;
		int status = (req.getParameter("status") == null) ? 0 : 1;
		
		if(AnswerDao.exist(question, number))
		{
			req.setAttribute("error", "Une autre réponse porte déjà ce numéro");
		}
		else if(QuestionDao.hasCorrect(question) && correct == 1)
		{
			req.setAttribute("error", "Cette question a déjà une réponse correcte");
		}
		else if(!AnswerDao.insert(question, label, number, correct, status))
		{
			req.setAttribute("error", "Erreur lors de l'ajout");
		}
		//req.getRequestDispatcher(PAGE).forward(req,  resp);
		resp.sendRedirect(req.getContextPath() + URL + question);
	}

}
