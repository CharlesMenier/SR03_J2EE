package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.Survey;


public class CourseDao {
	private int id;
	private UserDao user;
	private SurveyDao survey;
	private int score;
	private Time time;
	
	public CourseDao() {
		
	}

	public CourseDao(int id, UserDao user, SurveyDao survey, int score, Time time) {
		super();
		this.id = id;
		this.user = user;
		this.survey = survey;
		this.score = score;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public UserDao getUser() {
		return user;
	}

	public SurveyDao getSurvey() {
		return survey;
	}
	
	public int getScore() {
		return score;
	}

	public Time getTime() {
		return time;
	}
	
	
	public static CourseDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		CourseDao course = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM course WHERE crs_id = " + String.valueOf(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				course = new CourseDao(
						res.getInt("crs_id"),
						UserDao.find(res.getInt("crs_idUser")),
						SurveyDao.find(res.getInt("crs_idSurvey")),
						res.getInt("crs_score"),
						res.getTime("crs_time")
						);
			}
			cn.close();
			return course;
		} 
		catch(SQLException e)
		{
			System.out.println("Error during the query");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static List<CourseDao> findAll(int userId) {
		List<CourseDao> courses = new ArrayList<CourseDao>();
		Connection cn = new DaoConnector().getConnection();
		
		try {
			Statement stmt = (Statement)cn.createStatement();
			String sql = "SELECT * FROM course where crs_idUser=" + String.valueOf(userId);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				courses.add(new CourseDao(
						rs.getInt("crs_id"),
						UserDao.find(rs.getInt("crs_idUser")),
						SurveyDao.find(rs.getInt("crs_idSurvey")),
						rs.getInt("crs_score"),
						rs.getTime("crs_time")
						));
			}
			
			cn.close();
			return courses;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.findAll()");
			e.printStackTrace();
			return courses;
		}
	}
	
	
	public static boolean insert(int id, int userId, int surveyId, int score, Time time) {
		Connection connection= new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			String sql = "INSERT INTO course (crs_id, crs_idUser, crs_idSurvey, crs_score, crs_time) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, surveyId);
			preparedStatement.setInt(4, score);
			preparedStatement.setTime(5, time);
			System.out.println(preparedStatement);
			if(preparedStatement.executeUpdate() > 0) inserted = true;
			
			connection.close();
			return inserted;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : AnswerDao.insert()");
			e.printStackTrace();
			return inserted;
		}
	}
	
	
	public static boolean update(int id, int score, Time time)
	{
		Connection connection = new DaoConnector().getConnection();
		boolean updated = false;
		
		try {
			String sql = "UPDATE course SET " +  
					"crs_score='" + String.valueOf(score)+ 
					"', crs_time=? WHERE crs_id=" + String.valueOf(id);
		
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTime(1, time);
			
			if(preparedStatement.executeUpdate() > 0) updated = true;
			
			connection.close();
			return updated;
		} 
		catch(SQLException e)
		{
			System.out.println("Query error : UserDao.update()");
			e.printStackTrace();
			return updated;
		}
	}
	
	
	
	public static int size() {
		Connection connection = new DaoConnector().getConnection();
		String sql = "SELECT count(*) AS number FROM course";
		int number = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				number = resultSet.getInt(1);
			}
			connection.close();
			return number;
		} catch (Exception e) {
			e.printStackTrace();
			return number;
		}
	}
	
	
}
