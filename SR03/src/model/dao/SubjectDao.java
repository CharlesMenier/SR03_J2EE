package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {

	private int id;
	private String name;
	
	public SubjectDao(int i, String n)
	{
		id = i;
		name = n;
	}
	
	public int getId(){ return id; }
	
	public String getName(){ return name; }
	
	public static SubjectDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		SubjectDao sbj 	= null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM subject WHERE sbj_id = " + Integer.toString(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				sbj = new SubjectDao(res.getInt("sbj_id"), res.getString("sbj_name"));
			}
			cn.close();
			return sbj;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query : SubjectDao / find(id)");
			e.printStackTrace();
			return null;
		}
	}
	
	public static SubjectDao find(String n)
	{
		Connection cn 	= new DaoConnector().getConnection();
		SubjectDao sbj 	= null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM subject WHERE sbj_name = '" + n + "'";
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				sbj = new SubjectDao(res.getInt("sbj_id"), res.getString("sbj_name"));
			}
			cn.close();
			return sbj;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query : SubjectDao / find(name)");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insert(String name)
	{		
		Connection cn 	= new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "INSERT INTO subject('sbj_name') VALUES('" + name + "')";
			
			if(stmt.executeUpdate(sql) > 0) inserted = true;
			cn.close();
			
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query : SubjectDao / insert(name)");
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<SubjectDao> findAll()
	{
		Connection cn 	= new DaoConnector().getConnection();
		List<SubjectDao> list = new ArrayList<SubjectDao>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM subject";
			ResultSet res	= stmt.executeQuery(sql);
			
			while(res.next())
			{
				list.add(new SubjectDao(res.getInt("sbj_id"), res.getString("sbj_name")));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query : SubjectDao / insert(name)");
			e.printStackTrace();
			return list;
		}
	}
	
}
