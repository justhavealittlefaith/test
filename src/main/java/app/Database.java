package app;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Database{
	// JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost/db_nca";
    //  Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}catch(IllegalAccessException ex) {
			   System.out.println("Error: access problem while loading!");
			   System.exit(2);
			}catch(InstantiationException ex) {
			   System.out.println("Error: unable to instantiate driver!");
			   System.exit(3);
			}

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			// Clean-up environment
			// conn.close();
		} catch(SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			
		}
		
		return conn;
	}

}