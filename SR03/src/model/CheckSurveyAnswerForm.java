package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.dao.AnswerDao;
import model.dao.QuestionDao;
import model.dao.SurveyDao;

public final class CheckSurveyAnswerForm {
	
	private int score;
	
	public CheckSurveyAnswerForm() {
		score = 0;
	}
	
	public int getScore() {
		return score;
	}
	
	public void checkAnswer(int surveyId, HttpServletRequest req) throws ServletException, IOException {
		SurveyDao survey = SurveyDao.find(surveyId);
		ArrayList<QuestionDao> questions = (ArrayList<QuestionDao>) QuestionDao.findAll(surveyId);
		for (QuestionDao question : questions) {
			ArrayList<AnswerDao> answers = (ArrayList<AnswerDao>) AnswerDao.findAll(question.getId());
			String[] userAnswers = req.getParameterValues(question.getLabel());
			ArrayList<String> rightAnswers = getRightAnswer(answers);
			
			if (userAnswers != null && rightAnswers.size() == userAnswers.length) {
				boolean isCorrect = true;
				for (int i=0; i<userAnswers.length; i++) {
					if (rightAnswers.indexOf(userAnswers[i]) == -1) {
						isCorrect = false;
					}
				}
				if (isCorrect) {
					score += 1;
				}
			}
		}
	}

	public ArrayList<String> getRightAnswer(ArrayList<AnswerDao> answers) {
		ArrayList<String> rightAnswers = new ArrayList<String>();
		for (AnswerDao answer : answers) {
			if (answer.getCorrect()) {
				rightAnswers.add(String.valueOf(answer.getId()));
			}
		}
		return rightAnswers;
	}
}
