package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.util.Properties;

public class DaoConnector {

	private Connection cn;
	//private Properties props;
	
	public DaoConnector()
	{
		cn 		= null;
		//props 	= new Properties();
		
		/*try {
			FileInputStream propsFile = new FileInputStream("DB.properties");
			props.load(propsFile);
			propsFile.close();
		} 
		catch(Exception e) 
		{
			System.out.println("Error while loading MySQL properties file.");
			e.printStackTrace();
			return;
		}*/
		
		try {
			String driver 	= "com.mysql.jdbc.Driver";//props.getProperty("driver");
			String url 		= "jdbc:mysql://tuxa.sme.utc:3306/sr03p002";//props.getProperty("url");
//			String url		= "jdbc:mysql://localhost/sr03p002";
			String user		= "sr03p002";//props.getProperty("user");
			String password	= "oVVEgn8n";//"oVVEgn8n";//props.getProperty("password");
			
			if(driver != null)
				Class.forName(driver);
			
			cn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e)
		{
			System.out.println("Error during the MySQL connection");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return cn;
	}
	
	public void closeConection()
	{
		try {
			cn.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
