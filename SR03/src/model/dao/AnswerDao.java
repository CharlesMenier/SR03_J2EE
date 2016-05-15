package model.dao;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

public class AnswerDao implements Comparable<AnswerDao>{
	private int id;
	private QuestionDao question;
	private boolean isCorrect;
	private String label;
	private boolean status;
	private int number;
	
	public AnswerDao() {}

	public AnswerDao(int id, int questionId, boolean isCorrect, String label, boolean status, int number) {
		super();
		this.id = id;
		this.question = QuestionDao.find(questionId);
		this.isCorrect = isCorrect;
		this.label = label;
		this.status = status;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public QuestionDao getQuestion() {
		return question;
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
	
	
	public static AnswerDao find(int id) {
		AnswerDao answer= null;
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM answer WHERE id=" + String.valueOf(id) + ";";
			
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				answer = new AnswerDao(
						resultSet.getInt("asw_id"), 
						resultSet.getInt("asw_idQuestion"), 
						resultSet.getBoolean("asw_correct"),
						resultSet.getString("asw_label"),
						resultSet.getBoolean("asw_status"),
						resultSet.getInt("asw_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return answer;
	}
	
	
	public static List<AnswerDao> findALL() {
		List<AnswerDao> answers = new ArrayList<AnswerDao>();
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM answer;";
		
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				answers.add(new AnswerDao(
						resultSet.getInt("asw_id"), 
						resultSet.getInt("asw_idQuestion"), 
						resultSet.getBoolean("asw_correct"),
						resultSet.getString("asw_label"),
						resultSet.getBoolean("asw_status"),
						resultSet.getInt("asw_number")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return answers;
	}
	
	public static List<AnswerDao> findALL(int questionId) {
		List<AnswerDao> answers = new ArrayList<AnswerDao>();
		DaoConnector connector = new DaoConnector();
		
		try {
			Statement statement = connector.getConnection().createStatement();
			String sql = "SELECT * FROM answer WHERE qst_idSurvey=" + String.valueOf(questionId) + ";";
		
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				answers.add(new AnswerDao(
						resultSet.getInt("asw_id"), 
						resultSet.getInt("asw_idQuestion"), 
						resultSet.getBoolean("asw_correct"),
						resultSet.getString("asw_label"),
						resultSet.getBoolean("asw_status"),
						resultSet.getInt("asw_number")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connector.closeConection();
		return answers;
	}

	@Override
	public int compareTo(AnswerDao answerDao) {
		// TODO Auto-generated method stub
		if (this.id < answerDao.getId()) {
			return 1;
		} else if (this.id > answerDao.getId()) {
			return -1;
		} else {			
			return 0;
		}
	}
	
	
}
