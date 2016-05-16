package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao implements Comparable<AnswerDao> {
	private int id;
	private QuestionDao question;
	private String label;
	private int number;
	private boolean correct;
	private boolean status;
	
	public AnswerDao(int i, int iQ, String l, int n, boolean c, boolean s)
	{
		id = i;
		question = QuestionDao.find(iQ);
		label = l;
		number = n;
		correct = c;
		status = s;
	}
	
	public int getId(){ return id; }
	
	public QuestionDao getQuestion(){ return question; }
	
	public boolean getCorrect() { return correct; }
	
	public int getNumber(){ return number; }
	
	public String getLabel(){ return label; }
	
	public boolean getStatus(){ return status; }
	
	public static AnswerDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		AnswerDao answer = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM answer WHERE asw_id=" + id;
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				boolean status = (res.getInt("asw_status") == 1);
				boolean correct = (res.getInt("asw_correct") == 1);
				
				answer = new AnswerDao(
						res.getInt("asw_id"),
						res.getInt("asw_idQuestion"),
						res.getString("asw_label"),
						res.getInt("asw_number"),
						correct,
						status
						);
			}
			cn.close();
			return answer;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query AnswerDao.find(id)");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean exist(int idQuestion, int number)
	{
		Connection cn 	= new DaoConnector().getConnection();
		boolean exist = false;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM answer WHERE asw_idQuestion=" + idQuestion + ", asw_number=" + number;
			ResultSet res 	= stmt.executeQuery(sql);
			
			System.out.println(sql);
			
			if(res.next()) exist = true;
			cn.close();
			
			return exist;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query AnswerDao.exist()");
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
			String sql = "DELETE FROM answer WHERE asw_id=" + i;
			
			if(stmt.executeUpdate(sql) > 0) deleted = true;
			
			cn.close();
			return deleted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.delete(id)");
			e.printStackTrace();
			return deleted;
		}
	}
	
	public static boolean insert(int questionID, String label, int number, int correct, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "INSERT INTO answer(asw_idQuestion, asw_label, asw_number, asw_correct, asw_status) "
					+ "VALUES('" + questionID  + "','" + label + "', '" + number + "', '" + correct + "', '" + status + "')";
			
			if(stmt.executeUpdate(sql) > 0) inserted = true;
			
			cn.close();
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.insert()");
			e.printStackTrace();
			return inserted;
		}
	}
	
	public static List<AnswerDao> findAll()
	{
		Connection cn = new DaoConnector().getConnection();
		List<AnswerDao> list = new ArrayList<AnswerDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM answer";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new AnswerDao(rs.getInt("asw_id"), 
						rs.getInt("asw_idQuestion"), 
						rs.getString("asw_label"),
						rs.getInt("asw_number"),
						rs.getBoolean("asw_correct"),
						rs.getBoolean("asw_status")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static List<AnswerDao> findAll(int question)
	{
		Connection cn = new DaoConnector().getConnection();
		List<AnswerDao> list = new ArrayList<AnswerDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM answer WHERE asw_idQuestion=" + question;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new AnswerDao(rs.getInt("asw_id"), 
						rs.getInt("asw_idQuestion"), 
						rs.getString("asw_label"),
						rs.getInt("asw_number"),
						rs.getBoolean("asw_correct"),
						rs.getBoolean("asw_status")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static boolean update(int ID, int qID, String label, int number, int correct, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean edited = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "UPDATE answer " +
					"SET asw_idQuestion=" + qID + 
					", asw_label='" + label + 
					"', asw_number=" + number +
					"', asw_correct=" + correct +
					", asw_status=" + status +
					" WHERE asw_id=" + ID;
			
			if(stmt.executeUpdate(sql) > 0) edited = true;
			
			cn.close();
			return edited;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.update(id)");
			e.printStackTrace();
			return edited;
		}
	}
	
	@Override
	public int compareTo(AnswerDao answer)
	{
		if(id < answer.getId()) return 1;
		else if(id > answer.getId()) return -1;
		else return 0;
	}
	
}
