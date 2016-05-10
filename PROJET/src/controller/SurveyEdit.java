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

public class SurveyEdit extends HttpServlet {
	
	private List<SubjectDao> subjects = null;
	private List<SurveyDao> surveys = null;
	private List<QuestionDao> questions = null;
	private List<AnswerDao> answers = null;
	
	private String action = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		// We handle all the survey/question/answer editing with a single Servlet
		action = (String)req.getAttribute("action");
		
		if(action == null) action = "survey";
		
		switch(action)
		{
		// Editing surveys
		case "survey":
			//initSurveys(req, resp);
			break;
			
		// Editing questions
		case "question":
			initQuestions(req, resp);
			break;
			
		// Editing answers
		case "answer":
			initAnswers(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
	}
	
	private void initSurveys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("subjects", SubjectDao.findAll());
		req.setAttribute("surveys", SurveyDao.findAll());
		req.getRequestDispatcher("/private/survey.jsp").forward(req, resp);
	}
	
	private void initQuestions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("subjects", SubjectDao.findAll());
		req.setAttribute("surveys", SurveyDao.findAll());
		req.getRequestDispatcher("/private/survey.jsp").forward(req, resp);
	}
	
	private void initAnswers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("subjects", SubjectDao.findAll());
		req.setAttribute("surveys", SurveyDao.findAll());
		req.getRequestDispatcher("/private/survey.jsp").forward(req, resp);
	}
	
}
