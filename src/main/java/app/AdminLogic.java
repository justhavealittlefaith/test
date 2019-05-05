package app;
//testing May 05 2019
import java.util.*;
import java.text.*;
import org.springframework.ui.Model;
import org.springframework.web.context.request.*;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

public class AdminLogic{
	private HttpServletRequest request;
	
	private Connection conn;
	private Statement stmt;
	private String sql;
	private String message = "Performing sql queries:<br>";
	
	public AdminLogic(HttpServletRequest request, Model model){
		this.request = request;		
		
		Database db = new Database();
		this.conn = db.getConnection();
		try{
			this.stmt = this.conn.createStatement();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			message += "Error: "+se.getMessage();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public String getMessage(){
		if(!(request.getParameter("createMovies_info") == null)){
			return createMovies_info();
		}else if(!(request.getParameter("createWebsite_users") == null)){
			return createWebsite_users();
		}else if(!(request.getParameter("createProg_pricing") == null)){
			return createProg_pricing();
		}else if(!(request.getParameter("createLogin_record") == null)){
			return createLogin_record();
		}else if(!(request.getParameter("createAdmin_users") == null)){
			return createAdmin_users();
		}else if(!(request.getParameter("addNewMovie") == null)){
			return addNewMovie();
		}else if(!(request.getParameter("addProgPricing") == null)){
			return addProgPricing();
		}else if(!(request.getParameter("addNewAdmin") == null)){
			return addNewAdmin();
		}else if(!(request.getParameter("addNewUsers") == null)){
			return addNewUsers();
		}else if(!(request.getParameter("addNewTransactionRecord") == null)){
			return addNewTransactionRecord();
		}else if(!(request.getParameter("dropMovies_info") == null)){
			return dropMovies_info();
		}else if(!(request.getParameter("dropWebsite_users") == null)){
			return dropWebsite_users();
		}else if(!(request.getParameter("dropProg_pricing") == null)){
			return dropProg_pricing();
		}else if(!(request.getParameter("dropLogin_record") == null)){
			return dropLogin_record();
		}else if(!(request.getParameter("dropAdmin_users") == null)){
			return dropAdmin_users();
		}else if(!(request.getParameter("dropMovieTranTables") == null)){
			return dropMovieTranTables();
		}else if(!(request.getParameter("movieInfo2del") == null)){
			return movieInfo2del();
		}else if(!(request.getParameter("showTables") == null)){
			return showTables();
		}else if(!(request.getParameter("updateSth") == null)){
			return updateSth();
		}else{
			message += "error when admin's submitting a post request";
			return message;
		}
	}
	
	
	
	//CREATE TABLE movies_info
	public String createMovies_info(){
		sql = "CREATE TABLE movies_info (movieID int(15) NOT NULL auto_increment, movieName VARCHAR(50) NOT NULL, movieImage VARCHAR(50) NOT NULL, movieVideo VARCHAR(50) NOT NULL, addDate TIMESTAMP, viewsCounter int(15) NOT NULL, movieNameForTran VARCHAR(50) NOT NULL, PRIMARY KEY(movieID) )";
		try {
			stmt.execute(sql);	
			message += "Table created successfully<br>";
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		return message;
	}


	//CREATE TABLE website_users
	public String createWebsite_users(){
		sql = "CREATE TABLE website_users (userName VARCHAR(40) NOT NULL, pass VARCHAR(40) NOT NULL, userID int(15) NOT NULL auto_increment, email VARCHAR(40) NOT NULL, regDate TIMESTAMP, status VARCHAR(15) NOT NULL, PRIMARY KEY(userID) )";
		try {
			stmt.execute(sql);
			message += "Table website_users created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		message += "<br>";
		
		sql = "CREATE TABLE user_mostRecent10Records (userName VARCHAR(50) NOT NULL, userID int(15) NOT NULL, transactionID_1 int(20) DEFAULT 0 NOT NULL, transactionID_2 int(20) DEFAULT 0 NOT NULL, transactionID_3 int(20) DEFAULT 0 NOT NULL, transactionID_4 int(20) DEFAULT 0 NOT NULL, transactionID_5 int(20) DEFAULT 0 NOT NULL, transactionID_6 int(20) DEFAULT 0 NOT NULL, transactionID_7 int(20) DEFAULT 0 NOT NULL, transactionID_8 int(20) DEFAULT 0 NOT NULL, transactionID_9 int(20) DEFAULT 0 NOT NULL, transactionID_10 int(20) DEFAULT 0 NOT NULL, movieTableName_1 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_2 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_3 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_4 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_5 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_6 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_7 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_8 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_9 VARCHAR(50) DEFAULT 'NA' NOT NULL, movieTableName_10 VARCHAR(50) DEFAULT 'NA' NOT NULL, PRIMARY KEY(userID) )";
		try {
			stmt.execute(sql);
			message += "Table user_mostRecent10Records created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		message += "<br>";
		
		sql = "CREATE TRIGGER `triggerUser10Records_from_website_users` AFTER INSERT ON `website_users` FOR EACH ROW INSERT INTO user_mostRecent10Records (userName, userID) VALUES (NEW.userName, NEW.userID);";
		try {
			stmt.execute(sql);
			message += "Trigger triggerUser10Records_from_website_users created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//CREATE TABLE prog_pricing
	public String createProg_pricing(){
		sql = "CREATE TABLE prog_pricing (priceID int(9) NOT NULL auto_increment, viewsBelow int(15), priceHKD Decimal(3,2) NOT NULL, setDate TIMESTAMP, PRIMARY KEY(priceID) )";
		try {
			stmt.execute(sql);
			message += "Table created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//CREATE TABLE login_record
	public String createLogin_record(){
		sql = "CREATE TABLE login_record (userName VARCHAR(40) NOT NULL, loginID int(15) NOT NULL auto_increment, loginDate TIMESTAMP, PRIMARY KEY(loginID) )";
		try {
			stmt.execute(sql);
			message += "Table created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//CREATE TABLE admin_users
	public String createAdmin_users(){
		sql = "CREATE TABLE admin_users (adminID int(9) NOT NULL auto_increment, adminName VARCHAR(40) NOT NULL, email VARCHAR(40) NOT NULL, pass VARCHAR(40) NOT NULL, regDate TIMESTAMP, PRIMARY KEY(adminID) )";
		try {
			stmt.execute(sql);
			message += "Table created successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}



	//DROP TABLE movies_info
	public String dropMovies_info(){
		sql = "DROP TABLE movies_info";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//DROP TABLE website_users
	public String dropWebsite_users(){
		sql = "DROP TABLE website_users";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		message += "<br>";
		
		sql = "DROP TABLE user_mostRecent10Records";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		message += "<br>";
		
		sql = "DROP TRIGGER IF EXISTS triggerUser10Records_from_website_users";
		try {
			stmt.execute(sql);
			message += "Trigger dropped successfully<br>";
		}catch(SQLException se){
			message += "Error dropping trigger: "+se.getMessage();
			Back2AdminPage();
		}
		
		return message;
	}

	//DROP TABLE prog_pricing
	public String dropProg_pricing(){
		sql = "DROP TABLE prog_pricing";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//DROP TABLE login_record
	public String dropLogin_record(){
		sql = "DROP TABLE login_record";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//DROP TABLE admin_users
	public String dropAdmin_users(){
		sql = "DROP TABLE admin_users";
		try {
			stmt.execute(sql);
			message += "Table dropped successfully<br>";
		}catch(SQLException se){
			se.printStackTrace();
			message += "Error: "+sql+"<br>"+se.getMessage();
		}
		
		return message;
	}

	//DROP DB db_nca
	public String dropMovieTranTables(){
		String sql_movies_info = "SELECT * FROM movies_info";
		try{
			Statement stmt_movies_info = this.conn.createStatement();
			ResultSet result_movies_info = stmt_movies_info.executeQuery(sql_movies_info);
			
			while(result_movies_info.next()) {
				String movieTableName = result_movies_info.getString("movieNameForTran");
				
				sql = "DROP TABLE "+movieTableName;
				
				try {
					stmt.execute(sql);
					message += "Table "+movieTableName+" dropped successfully<br>";
				}catch(SQLException se){
					message += "Error: "+sql+"<br>"+se.getMessage();
					Back2AdminPage();
				}
				message += "<br>";
				
				sql = "DROP TRIGGER IF EXISTS triggerViewsCounter_from_"+movieTableName;
				
				try {
					stmt.execute(sql);
					message += "Trigger triggerViewsCounter_from_"+movieTableName+" dropped successfully<br>";
				}catch(SQLException se){
					message += "Error: "+sql+"<br>"+se.getMessage();
					Back2AdminPage();
				}
				
				message += "<br>";
			}
		}catch(SQLException se){
				message += "Error: "+sql_movies_info+"<br>"+se.getMessage();
				Back2AdminPage();
		}
		
		return message;
	}


	//DELETE ROW in TABLE movies_info
	public String movieInfo2del(){
		if(!(request.getParameter("movieName2del") == null)){
			String movieName = request.getParameter("movieName2del");
			sql = "DELETE FROM movies_info WHERE movieName = '"+movieName+"'";
			try {
				stmt.execute(sql);
				return movieName+" is deleted successfully<br>";
			}catch(SQLException se){
				message += "Error deleting movie: "+se.getMessage();;
				Back2AdminPage();
			}
		}
		else{
			message += "movieName to delete is required<br>";
			Back2AdminPage();
		}
		
		return message;
	}

	//create a hyperlink to direct to admin page
	public String Back2AdminPage(){
		message += "<br><a href='admin'>Go back to Admin page</a>";
		return message;
	}

	//for adding new movie to the database
	public String addNewMovie(){
		if(!(request.getParameter("movieName") == null) && !(request.getParameter("movieImage") == null) && !(request.getParameter("movieVideo") == null)){
			String movieName = request.getParameter("movieName");
			String movieImage = request.getParameter("movieImage");
			String movieVideo = request.getParameter("movieVideo");
			
			int end = movieImage.indexOf(".");
			String tableName = movieImage.substring(0, end);
			
			sql = "INSERT INTO movies_info (movieName, movieImage, movieVideo, viewsCounter, movieNameForTran) VALUES ('"+movieName+"', '"+movieImage+"', '"+movieVideo+"', '0', '"+tableName+"');";
			
			try {
				stmt.execute(sql);
				message += "Movie "+movieName+" created successfully<br>";
			}catch(SQLException se){
				message += "Error: "+sql+"<br>"+se.getMessage();
				Back2AdminPage();
			}
			
			message += "<br>";
			
			sql = "CREATE TABLE `"+tableName+"` (userName VARCHAR(50) NOT NULL, transactionID int(15) NOT NULL auto_increment, movieName VARCHAR(50) NOT NULL, priceHKD Decimal(3,2) NOT NULL, transactionDate TIMESTAMP, paymentMethod VARCHAR(50) NOT NULL, paypal_txn_id VARCHAR(30) NOT NULL, PRIMARY KEY(transactionID), FULLTEXT ftIndex(userName) )";
			try {
				stmt.execute(sql);
				message += "Table `"+tableName+"` created successfully<br>";
			}catch(SQLException se){
				se.printStackTrace();
				message += "Error: "+sql+"<br>"+se.getMessage();
			}
			
			message += "<br>";
			
			sql = "CREATE TRIGGER `triggerViewsCounter_from_"+tableName+"` AFTER INSERT ON `"+tableName+"` FOR EACH ROW UPDATE movies_info SET viewsCounter = viewsCounter + 1, addDate = addDate WHERE movieName = NEW.movieName";
			try {
				stmt.execute(sql);
				message += "Trigger triggerViewsCounter_from_"+tableName+" created successfully<br>";
			}catch(SQLException se){
				se.printStackTrace();
				message += "Error: "+sql+"<br>"+se.getMessage();
			}
			
		}
		else{
			message += "All fields are required<br>";
			Back2AdminPage();
		}
		
		return message;
	}


	//for adding new prog_pricing to the database
	public String addProgPricing(){
		sql = "INSERT INTO prog_pricing (viewsBelow, priceHKD) VALUES ('100000', '1'), ('250000', '1.01'), ('800000', '1.04'), ('1500000', '1.11'), ('3200000', '1.24'), ('5000000', '1.49'), ('7000000', '1.97'), ('10000000', '2.94'), ('15000000', '5'); ";
		
		try {
			stmt.executeUpdate(sql);
			message += "New records created successfully<br>";
		}catch(SQLException se){
			message += "Error: "+sql+"<br>"+se.getMessage();
			Back2AdminPage();
		}
		
		return message;
	}

	//for adding new admin users to the database
	public String addNewAdmin(){
		sql = "INSERT INTO admin_users (adminName, email, pass) VALUES ('Wayne', 'wayne@hku.hk', 'wayne');";
		sql += "INSERT INTO admin_users (adminName, email, pass) VALUES ('Charlie', 'charlie@hku.hk', 'charlie');";
		
		try {
			stmt.execute(sql);
			message += "New admin records created successfully<br>";
		}catch(SQLException se){
			message += "Error: "+sql+"<br>"+se.getMessage();
			Back2AdminPage();
		}
		
		return message;
	}

	//for adding new users to the database
	public String addNewUsers(){
		sql = "INSERT INTO website_users (userName, email, pass, status) VALUES ('Alvin', 'alvin@hku.hk', 'alvin', 'active'), ('Charlie', 'charlie@hku.hk', 'charlie', 'active'), ('Chris', 'chris@hku.hk', 'chris', 'active');";
		
		try {
			stmt.execute(sql);
			message += "New user records created successfully<br>";
		}catch(SQLException se){
			message += "Error: " + sql + "<br>"+se.getMessage();
			Back2AdminPage();
		}
		
		return message;
	}

	//The SQL code below is for loading hundreds of millions of test data to transaction_record table
	public String addNewTransactionRecord(){
		return "function not yet ready";
		
		// sql = "LOAD DATA LOCAL INFILE '/path/to/* TR.csv' INTO TABLE transaction_record
		// FIELDS TERMINATED BY ',' 
		// ENCLOSED BY '\"' 
		// LINES TERMINATED BY '\r\n'
		// (userName, movieName, priceHKD, paymentMethod);";
		
		// return $_SERVER['SCRIPT_FILENAME']."<br>";
		
		// try {
			// stmt.execute(sql);
			// message += "New transaction records created successfully<br>";
		// }catch(SQLException se){
			// message += "Error: "+sql+"<br>";
			// Back2AdminPage();
		// }
		
		// return message;
	}

	//for retrieving table info.
	public String showTables(){
		if(!(request.getParameter("tableName") == null)){
			String tableName = request.getParameter("tableName");
			sql = "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_NAME`='"+tableName+"'";
			try{	
				ResultSet rs = stmt.executeQuery(sql);
				
				message += "<fieldset style='width:auto'><legend>"+tableName+"</legend><table border='0'><tr>";
				
				ArrayList<String> output = new ArrayList<String>();
				
				while(rs.next()){
					String tempOutput = rs.getString("COLUMN_NAME");
					output.add(tempOutput);
					message += "<th>";
					message += rs.getString("COLUMN_NAME");
					message += "</th>";
				}
				message += "</tr>";

				if(tableName.substring(0,5).equals("Proto")){
					sql = "SELECT * from "+tableName+" ORDER BY transactionID DESC LIMIT 10";
				}else{
					sql = "SELECT * from "+tableName;
				}

				ResultSet rs_t_r = stmt.executeQuery(sql);
				// $count_t_r = $stmt->rowCount();
				if (!(rs_t_r == null)) {
					// output data of each row	 
					while(rs_t_r.next()){			
						message += "<tr>";
						for(int i=0; i<output.size(); i++){
							message += "<td>"+rs_t_r.getString(output.get(i))+"</td>";
						}
						message += "</tr>";
					}
				}else {
					message += "<tr>";
					message += "<td>no record</td>";
					message += "</tr>";
				}
			
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
				message += "Error: "+sql+"<br>"+se.getMessage();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}
			
		}else{
			message += "All fields are required<br>";
			Back2AdminPage();
		}
		
		return message;
	}


	public String updateSth(){
		Update update = new Update();
		
		if(!(request.getParameter("update_tableName") == null) && !(request.getParameter("update_setColumnName1") == null) && !(request.getParameter("update_ColumnName2") == null) && !(request.getParameter("update_setColumnValue1") == null) && !(request.getParameter("update_ColumnValue2") == null)){
			String tableName = request.getParameter("update_tableName");
			String setColumnName1 = request.getParameter("update_setColumnName1");
			String columnName2 = request.getParameter("update_ColumnName2");
			String setColumnValue1 = request.getParameter("update_setColumnValue1");
			String columnValue2 = request.getParameter("update_ColumnValue2");
			
			update.basedOn2Col(tableName, setColumnName1, columnName2, setColumnValue1, columnValue2);
	
			message += "["+tableName+"] ["+setColumnName1+"] = ["+setColumnValue1+"] WHERE ["+columnName2+"]  = ["+columnValue2+"] is updated successfully<br>";

		}else{
			message += "All fields are required<br>";
			Back2AdminPage();
		}
		
		return message;
	}

	//The VBA codes below is for creating test data in csv file
	/*
	Sub createDataFor_db_nca()
		Dim movieName As String, priceHKD As Integer, transactionDate As String, paymentMethod As String, userName As String
		Dim i As Long
		
		movieName = "Iron Man 3 2014"
		priceHKD = 1
		transactionDate = "2018-01-31 14:06:14.000000"
		paymentMethod = "Alipay"
		userName = "Charlie"
		
		i = 1
		While i < 1000001
			Cells(i, 1) = userName
			Cells(i, 2) = movieName
			Cells(i, 3) = priceHKD
			Cells(i, 4) = paymentMethod

			i = i + 1
		Wend
		
		MsgBox ("Job done")
	End Sub
	*/

	//The VBA codes below is for killing ghost cells
	/*
	Sub QuickReplace()
	Dim rng1 As Range
	Dim X
	Dim lngRow As Long
	Dim lngCol As Long

	ActiveSheet.UsedRange

	X = ActiveSheet.UsedRange.Value2
	For lngRow = 1 To UBound(X, 1)
		For lngCol = 1 To UBound(X, 2)
			If Len(X(lngRow, lngCol)) = 0 Then X(lngRow, lngCol) = vbNullString
		Next
	Next
	ActiveSheet.UsedRange.Value2 = X
	MsgBox ("Job done")
	End Sub
	*/


	//The SQL code below is for loading hundreds of millions of test data to transaction_record table
	/*
	LOAD DATA LOCAL INFILE 'C:/Apache24/htdocs/z_projects/z_TR1M.csv' INTO TABLE db_nca.proto_2014_iron_man_3
	FIELDS TERMINATED BY ',' 
	ENCLOSED BY '"' 
	LINES TERMINATED BY '\r\n'
	(userName, movieName, priceHKD, paymentMethod)
	*/

	/*
	insert into transaction_record (userName,movieName,priceHKD,paymentMethod) VALUES ('Charlie','Iron Man 3 2014','1','Paypal');
	*/


	
	
	
}