package app;

import java.util.*;
import java.text.*;
import org.springframework.ui.Model;
import org.springframework.web.context.request.*;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
// import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.net.URLDecoder;

// import org.json.*;
import javax.json.*;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

public class PaymentDoneUponSuccessfulPayment{
	private HttpServletRequest request;
	private Model model;
	private HttpSession session;
	
	private Connection conn;
	private Statement stmt;
	private String username;
	private String yourMovie;
	private String yourPriceHKD;
	private String tx_token;
	
	public PaymentDoneUponSuccessfulPayment(HttpServletRequest request, Model model){
		this.request = request;
		this.model = model;
		
		////////////////////////////////
		//initialize session variables//
		////////////////////////////////
		this.session = this.request.getSession(true);  
		this.username = (String)this.session.getAttribute("login_user");
		this.yourMovie = (String)this.session.getAttribute("yourMovie");
		this.yourPriceHKD = "" + (Double)this.session.getAttribute("yourPriceHKD");
		this.tx_token = (String)this.session.getAttribute("tx_token");
		
		Database db = new Database();
		this.conn = db.getConnection();
		
		try{
			this.stmt = this.conn.createStatement();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		runLogic();
	}
	
	public void addAttribute(String key, String value){
		this.model.addAttribute(key, value);
	}
	
	public void addAttribute(String key, Object value){
		this.model.addAttribute(key, value);
	}

	public Model getModel(){
		return this.model;
	}
	
	public void runLogic(){
		//To be included in protoPaymentDone.php

		//-----------------------------------------
		//-----------------------------------------
		//-----------------------------------------
		//Insert into the table of this movie
		
		String movieTableName = null;
		String sql = null;
		Read read = new Read();
		Create create = new Create();
		ResultSet rs_movies_info = null;
		ResultSet rs_last_tr = null;
		ResultSet rs_mostRecent10 = null;
		
		try{
			rs_movies_info = read.basedOn1Col("movies_info", "movieName", this.yourMovie);
			
			movieTableName = rs_movies_info.getString("movieNameForTran");
			
			create.basedOn5Col(movieTableName, "userName", "movieName", "priceHKD", "paymentMethod", "paypal_txn_id", this.username, this.yourMovie, this.yourPriceHKD, "paypal/credit card", this.tx_token);
			//movieName and priceHKD and movieID sent from Session variable	
						
			//-----------------------------------------
			//-----------------------------------------
			//-----------------------------------------
			//UPDATE Table user_mostRecent10Records by traversing from right(transactionID_10) to left(transactionID_1)	
			rs_last_tr = read.orderMore(movieTableName, "ORDER BY transactionID DESC LIMIT 1");
			// $row_last_tr = $stmt_last_tr->fetch(PDO::FETCH_ASSOC);
			
			rs_mostRecent10 = read.basedOn1Col("user_mostRecent10Records", "userName", rs_last_tr.getString("userName"));//mysql Table name is case sensitive
			// $row_mostRecent10 = $stmt_mostRecent10->fetch(PDO::FETCH_ASSOC);
			
			//transactionID_1 << transactionID_2
			//transactionID_2 << transactionID_3
			//transactionID_3 << transactionID_4 and so on
			sql = "UPDATE user_mostRecent10Records SET transactionID_1 = '"+rs_mostRecent10.getInt("transactionID_2")+"', movieTableName_1 = '"+rs_mostRecent10.getString("movieTableName_2")+"'";
			
			for(int i=2, j=3; j<=10; i++,j++){		
				sql += ", transactionID_"+i+" = '"+rs_mostRecent10.getInt("transactionID_"+j)+"', movieTableName_"+i+" = '"+rs_mostRecent10.getString("movieTableName_"+j)+"'";
			}
			
			//transactionID_10 << new transactionID
			sql += ", transactionID_10 = '"+rs_mostRecent10.getInt("transactionID")+"', movieTableName_10 = '"+movieTableName+"'";
			sql += " WHERE userName = '"+rs_mostRecent10.getString("userName")+"'";

			try {
				this.stmt.execute(sql);
			}catch(SQLException se){
				se.printStackTrace();
			}
		
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		//////////////////////////////////////////////////
		//add model attributes and set session variables//
		//////////////////////////////////////////////////

	}
}