package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AnswerDao;
import model.dao.QuestionDao;
import model.dao.SubjectDao;
import model.dao.SurveyDao;

public class AdminRouter extends HttpServlet {
	
	private List<SubjectDao> subjects = null;
	private List<SurveyDao> surveys = null;
	private List<QuestionDao> questions = null;
	private List<AnswerDao> answers = null;
	
	private String infos;
	private String data = null;
	private String action = null;
	private int id = 0;
	
	private String last = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		reset();
		handleRoute(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		reset();
		handleRoute(req, resp);
	}
	
	
	private void handleRoute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// We handle all the survey/question/answer editing with a single Servlet
		infos = (String)req.getPathInfo();
		
		String[] tmp = infos.split("/");
		
		if(tmp.length > 1)
		{
			data = tmp[1];
			last = data;
		}
		if(tmp.length > 2) action = tmp[2];
		if(tmp.length > 3) id = Integer.parseInt(tmp[3]);
		
		
		switch(data)
		{
		// Editing surveys
		case "questionnaires":
			initSurveys(req, resp);
			break;
			
		// Editing questions
		case "questions":
			initQuestions(req, resp);
			break;
			
		// Editing answers
		case "reponses":
			initAnswers(req, resp);
			break;
			
		case "utilisateurs":
			initUsers(req, resp);
			break;
		}
	}
	
	private void initSurveys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// No action, we display the default page
		if(action == null)
		{
			req.setAttribute("subjects", SubjectDao.findAll());
			req.setAttribute("surveys", SurveyDao.findAll());
			req.getRequestDispatcher("/private/survey.jsp").forward(req, resp);
		}
		else
		{
			switch(action)
			{
			case "add":
				String subject = req.getParameter("selectSubject");
				int status = (req.getParameter("status") == null) ? 0 : 1;
				
				System.out.println(subject);
				System.out.println(status);
	
				if(!SurveyDao.insert(Integer.parseInt(subject), status)) req.setAttribute("error", "Erreur durant l'ajout du questionnaire");
				reset();
				resp.sendRedirect(req.getContextPath() + "/private/admin/questionnaires");
				break;
				
			case "edit":
				req.setAttribute("id", id);
				req.getRequestDispatcher("/private/surveyEdit.jsp").forward(req, resp);
				break;
				
			case "delete":
				if(!SurveyDao.delete(id))
				{
					req.setAttribute("error", "Erreur pendant la suppression du questionnaire n°" + id);
				}
				reset();
				resp.sendRedirect(req.getContextPath() + "/private/admin/questionnaires");
				break;
			}
		}
	}
	
	private void initQuestions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// No action, we display the default page
		if(action == null)
		{
			req.setAttribute("surveys", SurveyDao.findAll());
			req.getRequestDispatcher("/private/question.jsp").forward(req, resp);
		}
	}
	
	private void initAnswers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// No action, we display the default page
		if(action == null)
		{
			req.setAttribute("subjects", SubjectDao.findAll());
			req.setAttribute("surveys", SurveyDao.findAll());
			req.getRequestDispatcher("/private/answer.jsp").forward(req, resp);
		}
	}
	
	private void initUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// No action, we display the default page
		if(action == null)
		{
			//req.setAttribute("subjects", SubjectDao.findAll());
			//req.setAttribute("surveys", SurveyDao.findAll());
			req.getRequestDispatcher("/private/user.jsp").forward(req, resp);
		}
	}
	
	private void reset()
	{
		data = last;
		action = null;
		id = 0;
	}
	
}
