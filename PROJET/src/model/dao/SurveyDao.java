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
	
	/*public static List<SurveyDao> findAll()
	{
		Connection cn 	= new DaoConnector().getConnection();
		List<SurveyDao> list = new ArrayList<SurveyDao>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM survey";
			ResultSet rs	= stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//System.out.println(rs.toString());
				//int status = res.getInt("srv_status");
				//list.add(new SurveyDao(rs.getInt("srv_id"), rs.getInt("srv_idSubject"), true));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query : SurveyDao / findAll()");
			e.printStackTrace();
			return list;
		}
	}*/
	
}
