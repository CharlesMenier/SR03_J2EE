package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public UserDao(int i, String m, String pwd,
			String n, String s, String p, Date r,
			boolean a, boolean b)
	{
		id = i;
		mail = m;
		password = pwd;
		name = n;
		society = s;
		phone = p;
		registration = r;
		isAdmin = a;
		isActive = b;
	}
	
	public int getId() { return id; }
	
	public String getPassword() { return password; }

	public String getName() { return name; }

	public String getSociety() { return society; }

	public String getPhone() { return phone; }

	public Date getRegistration() { return registration; }

	public boolean isActive() { return isActive; }

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
	
	public static List<UserDao> findAll()
	{
		Connection cn = new DaoConnector().getConnection();
		List<UserDao> list = new ArrayList<UserDao>();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new UserDao(
						rs.getInt("usr_id"), 
						rs.getString("usr_mail"),
						rs.getString("usr_password"),
						rs.getString("usr_name"),
						rs.getString("usr_society"),
						rs.getString("usr_phone"),
						rs.getDate("usr_registerDate"),
						rs.getBoolean("usr_admin"),
						rs.getBoolean("usr_status")
						));
			}
			
			cn.close();
			return list;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.findAll()");
			e.printStackTrace();
			return list;
		}
	}
	
	public static boolean insert(String mail, String password, String name, String society, String phone, int admin, int status)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "INSERT INTO user(usr_mail, usr_password, usr_admin, "
					+ "usr_name, usr_society, usr_phone, usr_status, usr_registerDate) "
					+ "VALUES('" + mail  + "','" + password + "','" + admin + "','" + name + "','" + society + "','" + phone + "','" + status + "', now())";
			
			if(stmt.executeUpdate(sql) > 0) inserted = true;
			
			cn.close();
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.insert()");
			e.printStackTrace();
			return inserted;
		}
	}
	
	public static boolean delete(int i)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean deleted = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "DELETE FROM user WHERE usr_id=" + i;
			
			if(stmt.executeUpdate(sql) > 0) deleted = true;
			
			cn.close();
			return deleted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.delete(id)");
			e.printStackTrace();
			return deleted;
		}
	}
	
	public static boolean update(int ID, String mail, String password, String name, String society, String phone, int admin)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean edited = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "UPDATE user SET usr_mail='" + mail + 
					"', usr_password='" + password + 
					"', usr_name='" + name + 
					"', usr_society='" + society + 
					"', usr_phone='" + phone + 
					"', usr_admin='" + admin
					+ "' WHERE usr_id=" + ID;
			
			if(stmt.executeUpdate(sql) > 0) edited = true;
			
			cn.close();
			return edited;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.update()");
			e.printStackTrace();
			return edited;
		}
	}
	
	public boolean hasDoneSurvey(int id)
	{
		Connection cn = new DaoConnector().getConnection();
		boolean done = false;
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM course WHERE crs_idUser=" + this.id + " AND crs_idSurvey=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) done = true;
			
			cn.close();
			return done;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.update()");
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static String generatePassword(int length)
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random random = new Random();
		String password = "";
		for (int i = 0; i < length; i++) 
		{
		    char c = chars[random.nextInt(chars.length)];
		    password += c;
		}
		
		return password;
	}
	
}
