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


public class CourseAnswerDao {
	private int courseId;
	private int answerId;
	
	public CourseAnswerDao() {
		
	}

	public CourseAnswerDao(int id, int id2) {
		super();
		this.courseId = id;
		this.answerId = id2;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public int getAnswerId() {
		return answerId;
	}
	
	public static boolean insert(int courseId, int answerId) {
		Connection connection= new DaoConnector().getConnection();
		boolean inserted = false;
		
		try {
			String sql = "INSERT INTO courseanswer(crs_asw_idCourse, crs_asw_idAnswer) VALUES (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, answerId);
			
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
	
}
