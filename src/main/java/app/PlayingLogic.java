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
import java.nio.charset.StandardCharsets;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.net.URLDecoder;

// import org.json.*;
import javax.json.*;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

public class PlayingLogic{
	private HttpServletRequest request;
	private Model model;
	private HttpSession session;
	
	private Boolean firstTimeAccessVideo;
	private String yourMovie;
	private String movieID;

	public PlayingLogic(HttpServletRequest request, Model model){
		this.request = request;
		this.model = model;
		
		////////////////////////////////
		//initialize session variables//
		////////////////////////////////
		this.session = this.request.getSession(true);  
		String username = (String)this.session.getAttribute("login_user");
		this.firstTimeAccessVideo = (Boolean)this.session.getAttribute("firstTimeAccessVideo");
		this.yourMovie = (String)this.session.getAttribute("yourMovie");
		this.movieID = "" + (Integer)this.session.getAttribute("movieID");

		this.addAttribute("username", username);
		this.addAttribute("yourMovie", yourMovie);
	}
	
	public String getViewName(){
		return runLogic();
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
	
	public String runLogic(){
		String movieIDFromParam = null;
		String movieVideo = null;
		String videoSrcLink = null;
		Read read = new Read();
		ResultSet rs = null;
		
		if(!(this.request.getParameter("movieID") == null) && firstTimeAccessVideo) {
			movieIDFromParam = this.request.getParameter("movieID");
			
			if(movieIDFromParam.equals(this.movieID)){
				
				System.out.println("-----------true-------------");
				//retrieve data from movies_info
				try{
					//rs = read.basedOn2Col("movies_info", "movieID", "movieName", movieID, yourMovie);
					rs = read.basedOn1Col("movies_info", "movieID", movieID);
					rs.next();
					movieVideo = rs.getString("movieVideo");					
				}catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
				}catch(Exception e){
					//Handle errors for Class.forName
					e.printStackTrace();
				}

				videoSrcLink = "/promo";
				this.firstTimeAccessVideo = false;

				//////////////////////////////////////////////////
				//add model attributes and set session variables//
				//////////////////////////////////////////////////
				this.addAttribute("videoSrcLink", videoSrcLink);
				this.session.setAttribute("firstTimeAccessVideo", this.firstTimeAccessVideo);
				
				return "protoPlaying";
			}
		}
		//doesn't work
		return "protoNewRelease";//return to New Release page if conditions are not met
	}
}