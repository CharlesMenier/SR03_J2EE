package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements Comparable<QuestionDao> {

	private int id;
	private SurveyDao survey;
	private String label;
	private int number;
	private boolean status;
	private List<AnswerDao> answers;
	
	public QuestionDao(int i, int iS, String l, int n, boolean s)
	{
		id = i;
		survey = SurveyDao.find(iS);
		label = l;
		number = n;
		status = s;
	}
	
	public int getId(){ return id; }
	
	public SurveyDao getSurvey(){ return survey; }
	
	public int getNumber(){ return number; }
	
	public String getLabel(){ return label; }
	
	public boolean getStatus(){ return status; }
	
	public List<AnswerDao> getAnswers() { return answers; }
	
	public void setAnswers(List<AnswerDao> list) { answers = list; }
	
	public static QuestionDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		QuestionDao question = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM question WHERE qst_id = " + id;
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				boolean status = (res.getInt("qst_status") == 1);
				
				question = new QuestionDao(
						res.getInt("qst_id"),
						res.getInt("qst_idSurvey"),
						res.getString("qst_label"),
						res.getInt("qst_number"),
						status
						);
			}
			cn.close();
			return question;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query QuestionDao.find(id)");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean exist(int idSurvey, int number)
	{
		Connection cn 	= new DaoConnector().getConnection();
		boolean exist = false;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM question WHERE qst_idSurvey=" + idSurvey + " AND qst_number=" + number;
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next()) exist = true;
			cn.close();
			
			return exist;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query QuestionDao.exist()");
			e.printStackTrace();
			return exist;
		}
	}
	
	public static boolean hasCorrect(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		boolean exist = false;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM answer WHERE asw_idQuestion=" + id + 
								" AND asw_correct = 1";
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next()) exist = true;
			cn.close();
			
			return exist;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query QuestionDao.hasCorrect()");
			e.printStackTrace();
			return exist;
		}
	}
	
	public static boolean delete(int i)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean deleted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "DELETE FROM question WHERE qst_id=" + i;
			
			if(stmt.executeUpdate(sql) > 0) deleted = true;
			
			cn.close();
			return deleted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.delete(id)");
			e.printStackTrace();
			return deleted;
		}
	}
	
	public static boolean insert(int surveyID, String label, int number, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "INSERT INTO question(qst_idSurvey, qst_label, qst_number, qst_status) "
					+ "VALUES('" + surveyID  + "','" + label + "', '" + number + "', '" + status + "')";
			
			if(stmt.executeUpdate(sql) > 0) inserted = true;
			
			cn.close();
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.insert()");
			e.printStackTrace();
			return inserted;
		}
	}
	
	public static List<QuestionDao> findAll()
	{
		Connection cn = new DaoConnector().getConnection();
		List<QuestionDao> list = new ArrayList<QuestionDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM question";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new QuestionDao(rs.getInt("qst_id"), 
						rs.getInt("qst_idSurvey"), 
						rs.getString("qst_label"),
						rs.getInt("qst_number"),
						rs.getBoolean("qst_status")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static List<QuestionDao> findAll(int survey)
	{
		Connection cn = new DaoConnector().getConnection();
		List<QuestionDao> list = new ArrayList<QuestionDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM question WHERE qst_idSurvey=" + survey + " ORDER BY qst_number";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new QuestionDao(rs.getInt("qst_id"), 
						rs.getInt("qst_idSurvey"), 
						rs.getString("qst_label"),
						rs.getInt("qst_number"),
						rs.getBoolean("qst_status")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static List<QuestionDao> findAllActive(int survey)
	{
		Connection cn = new DaoConnector().getConnection();
		List<QuestionDao> list = new ArrayList<QuestionDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM question WHERE qst_idSurvey=" + survey + " AND qst_status=1 ORDER BY qst_number";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new QuestionDao(rs.getInt("qst_id"), 
						rs.getInt("qst_idSurvey"), 
						rs.getString("qst_label"),
						rs.getInt("qst_number"),
						rs.getBoolean("qst_status")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.findAllActive()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static boolean update(int ID, int sID, String label, int number, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean edited = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "UPDATE question " +
					"SET qst_idSurvey=" + sID + 
					", qst_label='" + label + 
					"', qst_number=" + number +
					", qst_status=" + status +
					" WHERE qst_id=" + ID;
			
			if(stmt.executeUpdate(sql) > 0) edited = true;
			
			cn.close();
			return edited;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : QuestionDao.update(id)");
			e.printStackTrace();
			return edited;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof QuestionDao) {
			
			System.out.println("Comparison of QuestionDao");
			QuestionDao question = (QuestionDao) obj;
			return this.id == question.getId();
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public int compareTo(QuestionDao question)
	{
		if(id < question.getId()) return 1;
		else if(id > question.getId()) return -1;
		else return 0;
	}
	
	
	
}
