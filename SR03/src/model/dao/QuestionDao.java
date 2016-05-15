package model.dao;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.xml.internal.bind.v2.model.core.ID;

import controller.Connect;
import controller.Question;

public class QuestionDao implements Comparable<QuestionDao> {
	private int id;
	private SurveyDao survey;
	private String label;
	private boolean status;
	private int number;
	
	public QuestionDao() {}

	public QuestionDao(int id, int survey, String label, boolean status, int number) {
		super();
		this.id = id;
		this.survey = SurveyDao.find(survey);
		this.label = label;
		this.status = status;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public SurveyDao getSurvey() {
		return survey;
	}

	public String getLabel() {
		return label;
	}

	public boolean getStatus() {
		return status;
	}

	public int getNumber() {
		return number;
	}
	
	
	public static QuestionDao find(int id) {
		QuestionDao question = null;
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM question WHERE id=" + String.valueOf(id) + ";";
			
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				question = new QuestionDao(
						resultSet.getInt("qst_id"), 
						resultSet.getInt("qst_idSurvey"), 
						resultSet.getString("qst_label"),
						resultSet.getBoolean("qst_status"),
						resultSet.getInt("qst_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return question;
	}
	
	
	public static List<QuestionDao> findALL() {
		List<QuestionDao> questions = new ArrayList<QuestionDao>();
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM question;";
		
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				questions.add(new QuestionDao(
						resultSet.getInt("qst_id"), 
						resultSet.getInt("qst_idSurvey"), 
						resultSet.getString("qst_label"),
						resultSet.getBoolean("qst_status"),
						resultSet.getInt("qst_number")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return questions;
	}
	
	public static List<QuestionDao> findALL(int surveyId) {
		List<QuestionDao> questions = new ArrayList<QuestionDao>();
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM question WHERE qst_idSurvey=" + String.valueOf(surveyId) + ";";
		
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				questions.add(new QuestionDao(
						resultSet.getInt("qst_id"), 
						resultSet.getInt("qst_idSurvey"), 
						resultSet.getString("qst_label"),
						resultSet.getBoolean("qst_status"),
						resultSet.getInt("qst_number")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return questions;
	}

	@Override
	public int compareTo(QuestionDao questionDao) {
		// TODO Auto-generated method stub
		if (this.id < questionDao.getId()) {
			return 1;
		} else if (this.id > questionDao.getId()) {
			return -1;
		} else {			
			return 0;
		}
	}
	
	
	
}
