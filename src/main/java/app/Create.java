package app;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Create extends Database{
	private Connection conn;
	private PreparedStatement stmt;
	
	public Create(){
		this.conn = this.getConnection();
	}
	
	public void basedOn1Col(String tableName, String columnName1, String columnValue1){
		try {
			String query = "INSERT INTO " + tableName + " ("+columnName1+") VALUES (?)"; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			stmt.executeUpdate(); //either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing. returns 1 here
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public void basedOn2Col(String tableName, String columnName1, String columnName2, String columnValue1, String columnValue2){
		try {
			String query = "INSERT INTO " + tableName + " ("+columnName1+", "+columnName2+") VALUES (?,?)";
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			stmt.setString(2, columnValue2);
			stmt.executeUpdate(); //either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing. returns 1 here
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public void basedOn4Col(String tableName, String columnName1, String columnName2, String columnName3, String columnName4, String columnValue1, String columnValue2, String columnValue3, String columnValue4){
		try {
			String query = "INSERT INTO " + tableName + " ("+columnName1+", "+columnName2+", "+columnName3+", "+columnName4+ ") VALUES (?,?,?,?)";
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			stmt.setString(2, columnValue2);
			stmt.setString(3, columnValue3);
			stmt.setString(4, columnValue4);
			stmt.executeUpdate(); //either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing. returns 1 here
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void basedOn5Col(String tableName, String columnName1, String columnName2, String columnName3, String columnName4, String columnName5, String columnValue1, String columnValue2, String columnValue3, String columnValue4, String columnValue5){
		try {
			String query = "INSERT INTO " + tableName + " ("+columnName1+", "+columnName2+", "+columnName3+", "+columnName4+", "+columnName5+ ") VALUES (?,?,?,?,?)";
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			stmt.setString(2, columnValue2);
			stmt.setString(3, columnValue3);
			stmt.setString(4, columnValue4);
			stmt.setString(5, columnValue5);
			stmt.executeUpdate(); //either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing. returns 1 here
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
} 