package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SurveyDao {

	private int id;
	private SubjectDao subject;
	private boolean status;
	
	public SurveyDao(int i, int iS, boolean s)
	{
		id = i;
		subject = SubjectDao.find(iS);
		status = s;
	}
	
	public SurveyDao(int i, String n, boolean s)
	{
		id = i;
		subject = SubjectDao.find(n);
		status = s;
	}
	
	public int getId(){ return id; }
	
	public SubjectDao getSubject(){ return subject; }
	
	public boolean getStatus(){ return status; }
	
	public static SurveyDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		SurveyDao survey = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM survey WHERE srv_id = " + Integer.toString(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				boolean status = (res.getInt("srv_status") == 1);
				
				survey = new SurveyDao(
						res.getInt("srv_id"),
						res.getInt("srv_idSubject"),
						status
						);
			}
			cn.close();
			return survey;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query SurveyDao.find(id)");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean delete(int i)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean deleted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "DELETE FROM survey WHERE srv_id=" + i;
			
			if(stmt.executeUpdate(sql) > 0) deleted = true;
			
			cn.close();
			return deleted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : SurveyDao.delete(id)");
			e.printStackTrace();
			return deleted;
		}
	}
	
	public static boolean insert(int subjectID, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "INSERT INTO survey(srv_status, srv_idSubject) VALUES('" + status  + "','" + subjectID + "')";
			
			if(stmt.executeUpdate(sql) > 0) inserted = true;
			
			cn.close();
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : SurveyDao.insert()");
			e.printStackTrace();
			return inserted;
		}
	}
	
	public static List<SurveyDao> findAll()
	{
		Connection cn = new DaoConnector().getConnection();
		List<SurveyDao> list = new ArrayList<SurveyDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM survey";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new SurveyDao(rs.getInt("srv_id"), rs.getInt("srv_idSubject"), rs.getBoolean("srv_status")));
			}
			
			cn.close();
			return list;
		} catch(SQLException e)
		{
			System.out.println("Query error : SurveyDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
}
