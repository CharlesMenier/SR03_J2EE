package controller;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckSurveyAnswerForm;
import model.dao.AnswerDao;
import model.dao.CourseDao;
import model.dao.QuestionDao;
import model.dao.SubjectDao;
import model.dao.SurveyDao;
import model.dao.UserDao;

public class UserSurvey extends Controller {

	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getInfos(req, resp);
		
		List<SurveyDao> surveys = null;
		List<SubjectDao> subjects = null;
		
		
		if (ACTION == null) 
		{
			// Display all surveys
			surveys = SurveyDao.findAll();
			subjects = SubjectDao.findAll();
			
			if (surveys.isEmpty() || subjects.isEmpty()) 
			{
				req.setAttribute("error", "Pas de questionnaire");
			}
			
			req.setAttribute("surveys", surveys);
			req.setAttribute("subjects", subjects);
			req.setAttribute("selectedSubject", "defaut");
			req.getRequestDispatcher("/user/survey.jsp").forward(req, resp);
		} else {
			switch (ACTION) {
			case "answer":
				answerSurvey(req, resp);
				break;
			case "submit":
				checkAnswer(req, resp);
				break;
			case "history":
				surveyHistory(req, resp);
				break;
			case "search":
				System.out.println(req.getParameter("selectSubject"));
				String subjectId;
				if (ID != null) {
					subjectId = ID;
				} else {
					subjectId = (String) req.getParameter("selectSubject");
				}
				if (subjectId.equals("default")) {
					surveys = SurveyDao.findAll();
				} else {
					surveys = getSurveyBySubject(Integer.parseInt(subjectId));
				}
				if (surveys.isEmpty()) {
					req.setAttribute("error", "Pas de questionnaire");
				}
				String curPage = req.getParameter("curPage");
				int currentPage;
				if (curPage == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(curPage);
				}
				ArrayList<SurveyDao> shownSurveys = new ArrayList<SurveyDao>();
				for (int i=5*(currentPage-1); i<5*currentPage; i++) {
					if (i < surveys.size()) {
						shownSurveys.add(surveys.get(i));
					}
				}
				
				req.setAttribute("pageNum", surveys.size()/5 + 1);
				req.setAttribute("currentPage", currentPage);
				req.setAttribute("surveys", shownSurveys);
				req.setAttribute("subjects", subjects);
				req.setAttribute("selectedSubject", String.valueOf(subjectId));
				req.getRequestDispatcher("/user/survey.jsp").forward(req, resp);
			default:
				break;
			}
		}
	}
	
	public void answerSurvey(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		SurveyDao survey = null;
		
		survey = SurveyDao.find(Integer.parseInt(ID));
		
		if (survey == null) 
		{
			req.setAttribute("error", "Erreur lors du chargement");
		} 
		else
		{
			// Get active questions
			List<QuestionDao> questions = QuestionDao.findAllActive(survey.getId());
			for (QuestionDao question : questions) 
			{
				// Get associated active answers
				List<AnswerDao> answers = AnswerDao.findAllActive(question.getId());
				question.setAnswers(answers);
			}
			req.setAttribute("questions", questions);
		}
		req.setAttribute("survey", survey);
		
		// TODO
		// add a course
		UserDao user = (UserDao) req.getSession().getAttribute(Controller.USER_SESSION);
		int courseId = CourseDao.size() + 1;
		Time time = new Time(new Date().getTime());
		CourseDao.insert(courseId, user.getId(), Integer.valueOf(ID), 0, time);
		req.getSession().setAttribute("courseId", courseId);
		
		req.getRequestDispatcher("/user/answerSurvey.jsp").forward(req, resp);
	}

	
	
	public void checkAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int surveyId = Integer.parseInt(ID);
		CheckSurveyAnswerForm checkSurveyAnswerForm = new CheckSurveyAnswerForm();
		checkSurveyAnswerForm.checkAnswer(surveyId, req);
		int score = checkSurveyAnswerForm.getScore();
		
		//TODO
		// update the course
		System.out.println("CourseID:" + String.valueOf((int) req.getSession().getAttribute("courseId")));
		CourseDao course = CourseDao.find((int) req.getSession().getAttribute("courseId"));
		Time currentTime = new Time(new Date().getTime());
		long diff = currentTime.getTime() - course.getTime().getTime();
		int diffSeconds = Math.toIntExact(diff / 1000 % 60);
        int diffMinutes = Math.toIntExact(diff / (60 * 1000) % 60);
        int diffHours = Math.toIntExact(diff / (60 * 60 * 1000) % 24);
		
		Time time = new Time(diffHours, diffMinutes, diffSeconds);
		
		CourseDao.update(course.getId(), score, time);
		req.getSession().removeAttribute("courseId");
		
		req.setAttribute("userName", course.getUser().getName());
		req.setAttribute("survey", course.getSurvey());
		req.setAttribute("time", time);
		req.setAttribute("score", score);
		req.getRequestDispatcher("/user/result.jsp").forward(req, resp);
		
//		CourseDao course = new CourseDao();
//		req.setAttribute("course", course);
		
	}
	
	public void surveyHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao user = (UserDao) req.getSession().getAttribute(Controller.USER_SESSION);
		ArrayList<CourseDao> courses = (ArrayList<CourseDao>) CourseDao.findAll(user.getId());
		
		req.setAttribute("courses", courses);
		req.getRequestDispatcher("/user/history.jsp").forward(req, resp);
	}
	
	
	public List<SurveyDao> getSurveyBySubject(int subjectId) {
		List<SurveyDao> surveys = SurveyDao.findAll();
		List<SurveyDao> result = new ArrayList<SurveyDao>();
		for (int i=0; i<surveys.size(); i++) {
			if (surveys.get(i).getSubject().getId() == subjectId) {
				result.add(surveys.get(i));
			}
		}
		
		return result;
	}
	
}
