package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	private int id;
	private String mail;
	private String password;
	private String name;
	private String society;
	private String phone;
	private Date registration;
	private boolean isAdmin;
	private boolean isActive;
	
	public UserDao(int id, String m, String pwd,
			String n, String s, String p, Date r,
			boolean a, boolean b)
	{
		mail = m;
		password = pwd;
		name = n;
		society = s;
		phone = p;
		registration = r;
		isAdmin = a;
		isActive = b;
	}
	
	public String getMail() { return mail; }
	
	public boolean isAdmin() { return isAdmin; }
	
	public static UserDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		UserDao usr = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM user WHERE usr_id = " + Integer.toString(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				usr = new UserDao(
						res.getInt("usr_id"),
						res.getString("usr_mail"),
						res.getString("usr_password"),
						res.getString("usr_name"),
						res.getString("usr_society"),
						res.getString("usr_phone"),
						res.getDate("usr_registerDate"),
						res.getBoolean("usr_admin"),
						res.getBoolean("usr_status")
						);
			}
			cn.close();
			return usr;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query");
			e.printStackTrace();
			return null;
		}
	}
	
	public static UserDao find(String email, String pwd) {
		Connection cn 	= new DaoConnector().getConnection();
		UserDao usr = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM user WHERE usr_mail = \"" + email + 
								"\" AND usr_password=\"" + pwd + "\";";
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				usr = new UserDao(
						res.getInt("usr_id"),
						res.getString("usr_mail"),
						res.getString("usr_password"),
						res.getString("usr_name"),
						res.getString("usr_society"),
						res.getString("usr_phone"),
						res.getDate("usr_registerDate"),
						res.getBoolean("usr_admin"),
						res.getBoolean("usr_status")
						);
			}
			cn.close();
			return usr;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query");
			e.printStackTrace();
			return null;
		}
	}
	
}
