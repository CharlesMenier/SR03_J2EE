package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AnswerDao;
import model.dao.QuestionDao;
import model.dao.SurveyDao;
import model.dao.UserDao;

public class UserSurvey extends Controller {

	protected void handleActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getInfos(req, resp);
		
		List<SurveyDao> surveys = null;
		surveys = SurveyDao.findAll();
		
		if (surveys.isEmpty()) {
			System.out.println("No survey");
		} else {
			System.out.println("surveys exist");
			System.out.println(surveys.get(0).getStatus());
		}
		
		if (ACTION == null) {
			// Display all surveys
			if (surveys.isEmpty()) {
				req.setAttribute("error", "Pas de questionnaire");
			}
			req.setAttribute("surveys", surveys);
			req.getRequestDispatcher("/user/survey.jsp").forward(req, resp);
		} else {
			switch (ACTION) {
			case "answer":
				answerSurvey(req, resp);
				break;

			default:
				break;
			}
		}
	}
	
	public void answerSurvey(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SurveyDao survey = null;
		survey = SurveyDao.find(Integer.parseInt(ID));
		if (survey == null) {
			req.setAttribute("error", "Erreur lors du chargement");
		} else {
			List<QuestionDao> questions = QuestionDao.findALL(survey.getId());
			for (QuestionDao question : questions) {
				if (question.getStatus() == false) {
					// Remove invalid question
					questions.remove(question);
				} else {
					List<AnswerDao> answers = AnswerDao.findALL(question.getId());
					// Get valid answers
					for (AnswerDao answer : answers) {
						if (answer.getStatus() == false) {
							answers.remove(answer);
						}
					}
					Collections.sort(answers);
					
					req.setAttribute(question.getLabel(), answers);
				}
			}
			
			req.setAttribute("questions", questions);
		}
		req.setAttribute("survey", survey);
		
		// TODO
		// add a course
		
		req.getRequestDispatcher("/user/answerSurvey.jsp").forward(req, resp);
	}

	
}
