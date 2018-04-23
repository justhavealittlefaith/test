package app;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Read extends Database{
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs = null;
	
	public Read(){
		this.conn = this.getConnection();
	}
	
	public ResultSet basedOn1Col(String tableName, String columnName1, String columnValue1){
		try {
			String query = "SELECT * FROM " + tableName + " WHERE " + columnName1 + " = ?"; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			this.rs = stmt.executeQuery();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return this.rs;
	}
	
	public ResultSet basedOn2Col(String tableName, String columnName1, String columnName2, String columnValue1, String columnValue2){
		try {
			String query = "SELECT * FROM " + tableName + " WHERE " + columnName1 + " = ? AND " + columnName2 + " = ?"; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, columnValue1);
			stmt.setString(2, columnValue2);
			this.rs = stmt.executeQuery();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return this.rs;
	}
	
	public ResultSet OrderBy(String tableName, String orderByCol){
		try {
			String query = "SELECT * FROM " + tableName + " ORDER BY ?"; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, orderByCol);
			this.rs = stmt.executeQuery();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return this.rs;
	}
	
	public ResultSet orderMore(String tableName, String more){
		try {
			String query = "SELECT * FROM " + tableName + " " + more; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
			this.rs = stmt.executeQuery();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return this.rs;
	}
	
	public ResultSet noCondition(String tableName){
		try {
			String query = "SELECT * FROM " + tableName; //table name cannot be parameterized in a prepared statement
			this.stmt = this.conn.prepareStatement(query);
			this.rs = stmt.executeQuery();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return this.rs;
	}
} 