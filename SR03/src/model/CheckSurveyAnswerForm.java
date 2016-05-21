package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.dao.AnswerDao;
import model.dao.CourseAnswerDao;
import model.dao.QuestionDao;
import model.dao.SurveyDao;

public final class CheckSurveyAnswerForm {
	
	private int score;
	private int courseId;
	
	public CheckSurveyAnswerForm(int id) {
		score = 0;
		courseId = id;
	}
	
	public int getScore() {
		return score;
	}
	
	public void checkAnswer(int surveyId, HttpServletRequest req) throws ServletException, IOException {
		
		SurveyDao survey = SurveyDao.find(surveyId);
		List<QuestionDao> questions = QuestionDao.findAllActive(surveyId);
		
		for (QuestionDao question : questions) 
		{
			List<AnswerDao> answers = AnswerDao.findAllActive(question.getId());
			String userAnswer = req.getParameter(question.getLabel());
			
			String rightAnswer = getRightAnswer(answers);
			
			if (userAnswer != null && userAnswer == rightAnswer) 
			{
				boolean isCorrect = true;
				if (isCorrect) {
					score += 1;
				}
			}
			
			CourseAnswerDao.insert(courseId, Integer.parseInt(userAnswer));
			
		}
	}

	public String getRightAnswer(List<AnswerDao> answers) 
	{
		for (AnswerDao answer : answers) 
		{
			if (answer.getCorrect()) {
				return String.valueOf(answer.getId());
			}
		}
		return null;
	}
}
