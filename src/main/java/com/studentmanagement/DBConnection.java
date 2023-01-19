package com.studentmanagement;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
public class DBConnection {
	private static Connection con=null;
	private static Logger logger=(Logger) LogManager.getLogger(DBConnection.class);
	private DBConnection() {
		
	}
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			 con=DriverManager.getConnection("jdbc:postgresql:seneca","postgres","1234");
			 logger.info("DB connection created");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static Connection getcon() {
		logger.info("Connection method got called");
		return con;
		
	}
}
