package app;

import java.util.*;
import java.text.*;
import org.springframework.ui.Model;
import org.springframework.web.context.request.*;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

public class Movie{
	private Integer movieID;
	private String movieName;
	private java.sql.Timestamp addDate;
	private String movieImage;
	private Integer viewsCounter; 
	private Double priceHKD; 
	private Double box_office_total; 
	private Double box_office_USD; 
	
	public Movie(Map<String, Object> map){
		this.movieID = (Integer)map.get("movieID");
		this.movieName = (String)map.get("movieName");
		this.addDate = (java.sql.Timestamp)map.get("addDate");
		this.movieImage = (String)map.get("movieImage");
		this.viewsCounter = (Integer)map.get("viewsCounter");
		this.priceHKD = (Double)map.get("priceHKD");
		this.box_office_total = (Double)map.get("box_office_total");
		this.box_office_USD = (Double)map.get("box_office_USD");
	}

	public Integer getMovieID(){
		return this.movieID;
	}
	public String getMovieName(){
		return this.movieName;
	}
	public java.sql.Timestamp getAddDate(){
		return this.addDate;
	}
	public String getMovieImage(){
		return this.movieImage;
	}
	public String getViewsCounter(){
		return String.format("%,d", this.viewsCounter);
	}
	public Double getPriceHKD(){
		return this.priceHKD;
	}
	public String getBox_office_total(){
		return String.format("%,.2f", this.box_office_total);
	}
	public String getBox_office_USD(){
		return String.format("%,.2f", this.box_office_USD);
	}

}