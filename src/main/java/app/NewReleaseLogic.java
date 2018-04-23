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

public class NewReleaseLogic{
	private HttpServletRequest request;
	private Model model;
	private HttpSession session;
	private Map<String, Object> aMovieMap = new HashMap<>();
	private ArrayList<Object> moviesArray = new ArrayList<Object>();
	
	public NewReleaseLogic(HttpServletRequest request, Model model){
		this.request = request;		
		this.model = model;
		////////////////////////////////
		//initialize session variables//
		////////////////////////////////
		this.session = this.request.getSession(true);
		String username = (String)this.session.getAttribute("login_user");
		this.addAttribute("username", username);
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
		boolean chkIfPriceExists = false;
		int viewsCounter = 0;
		int previous_views = 0;
		int viewsBelow = 0;
		double priceHKD = 0;
		double pirceHKDhighest = 0;
		double box_office = 0;
		double box_office_total = 0;
		double box_office_USD = 0;
		double USDtoHKDrate = 7.8;
		Read read = new Read();
		ResultSet rs_p_p = null;

		//retrieve data from movies_info
		ResultSet rs = read.OrderBy("movies_info", "movieID");
		
		try{
			while(rs.next()){
				viewsCounter = rs.getInt("viewsCounter");//retrieve the number of each movie's transactions
	
				//retrieve data from prog_pricing
				rs_p_p = read.noCondition("prog_pricing");

				while(rs_p_p.next()){
					viewsBelow = rs_p_p.getInt("viewsBelow");
					priceHKD = rs_p_p.getDouble("priceHKD");
					
					//Once the transaction records are fewer than viewsBelow, the codes below would be executed and then break the loop.
					if(viewsCounter < viewsBelow){
						//calculation of total box office of a particular movie
						box_office = (viewsCounter - previous_views) * priceHKD;
						box_office_total = box_office_total + box_office;
						
						chkIfPriceExists = true;
						break;
					}
					
					//calculation of box office of standard progressive pricing table when !(viewsCounter < rs_p_p.getInt("viewsBelow"))
					box_office = (viewsBelow-previous_views) * priceHKD;
					box_office_total = box_office_total + box_office;
					
					//store the highest price if views is not matched
					pirceHKDhighest = priceHKD;
					previous_views = viewsBelow;
				}
				//if viewsCounter > the views of the highest prog_pricing priceHKD
				if(!chkIfPriceExists){
					priceHKD = pirceHKDhighest;
					
					//calculation of box office
					box_office = (viewsCounter - previous_views) * pirceHKDhighest;
					box_office_total = box_office_total + box_office;
				}
				
				box_office_USD = box_office_total/USDtoHKDrate;
				
				//put every piece of info of this movie into an object
				this.aMovieMap.put("movieID", rs.getInt("movieID"));
				this.aMovieMap.put("movieName", rs.getString("movieName"));
				this.aMovieMap.put("addDate", rs.getTimestamp("addDate"));
				this.aMovieMap.put("movieImage", rs.getString("movieImage"));
				this.aMovieMap.put("viewsCounter", viewsCounter);
				this.aMovieMap.put("priceHKD", priceHKD);
				this.aMovieMap.put("box_office_total", box_office_total);
				this.aMovieMap.put("box_office_USD", box_office_USD);
				
				//create a Movie object
				Movie aMovieObj = new Movie(aMovieMap);
				//put a movie to an ArrayList "moviesArray"
				moviesArray.add(aMovieObj);
			}
			
			//////////////////////////////////////////////////
			//add model attributes and set session variables//
			//////////////////////////////////////////////////
			//put the ArrayList "moviesArray" to a model
			this.addAttribute("moviesStr", moviesArray);
			//put the ArrayList "moviesArray" to a session variable
			this.session.setAttribute("newRelease", moviesArray);
			
			return "protoNewRelease";
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return "login";//return to login page if conditions are not met
	}
	
}