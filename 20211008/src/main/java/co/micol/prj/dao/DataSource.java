package co.micol.prj.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
	private static DataSource dataSource = new DataSource();
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	private DataSource() {
		dbconfig();
	} 

	public static DataSource getInstance() {
		return dataSource;
	}

	private void dbconfig() { 
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getFile();
		try {
			properties.load(new FileReader(resource));
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		
		try {
			Class.forName(driver); // ojdbc load
			conn=DriverManager.getConnection(url,user,password);
		
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn; 
	}
}
