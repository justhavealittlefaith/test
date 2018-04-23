package app;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Update extends Database{
	private Connection conn;
	private PreparedStatement stmt;
	
	public Update(){
		this.conn = this.getConnection();
	}
	
	public void basedOn2Col(String tableName, String setColumnName1, String columnName2, String setColumnValue1, String columnValue2){
		try {
			String query = "UPDATE " + tableName + " SET "+setColumnName1+"=? WHERE "+columnName2+"=?";
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, setColumnValue1);
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
	
	public void basedOn3Col(String tableName, String setColumnName1, String columnName2, String columnName3, String setColumnValue1, String columnValue2, String columnValue3){
		try {
			String query = "UPDATE " + tableName + " SET "+setColumnName1+"=? WHERE "+columnName2+"=? AND "+columnName3+"=?";
			this.stmt = this.conn.prepareStatement(query);
						
			//Bind values into the parameters.
			stmt.setString(1, setColumnValue1);
			stmt.setString(2, columnValue2);
			stmt.setString(3, columnValue3);
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