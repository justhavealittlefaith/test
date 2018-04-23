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

public class PaymentLogic{
	private HttpServletRequest request;
	private Model model;
	private HttpSession session;
	private Map<String, Object> map;
	private ArrayList<Object> newRelease;

	public PaymentLogic(HttpServletRequest request, Model model){
		this.request = request;
		this.model = model;
		this.map = model.asMap();//for reuse below
		
		////////////////////////////////
		//initialize session variables//
		////////////////////////////////
		this.session = this.request.getSession(true);  
		String username = (String)this.session.getAttribute("login_user");
		this.newRelease = (ArrayList<Object>)this.session.getAttribute("newRelease");
		this.addAttribute("username", username);
	}
	
	public String getViewName(){
		return runLogic();
	}
	
	public void addAttribute(String key, String value){
		this.model.addAttribute(key, value);
	}

	public Model getModel(){
		return this.model;
	}
	
	public String runLogic(){
		Boolean paymentDone = false;
		Integer movieID = 0;
		Double priceHKD = 0.0;
		Double paypalFixedFee = 0.0;
		Double paypalPercentageFee = 0.0;
		Double paypalServiceFee = 0.0;
		Double finalPrice = 0.0;
		String movieIDStr = null;
		String movieName = null;
		String paymMethod = null;
		String paypal_hosted_button_value = null;
		String priceHKDStr = null;
		String paypalServiceFeeStr = null;
		String finalPriceStr = null;
		Movie aMovieObj = null;
		ArrayList<Double> allPriceLevels = new ArrayList<Double>();
		Read read = new Read();
		ResultSet rs = null;

		if(!(this.request.getParameter("movieID") == null) ) {
			//request.getParameter() always returns a String type
			movieIDStr = this.request.getParameter("movieID");
			movieID = Integer.parseInt(movieIDStr);
			
			for(int j=0; j<this.newRelease.size(); j++){
				aMovieObj = (Movie)this.newRelease.get(j);
				
				if(aMovieObj.getMovieID().equals(movieID)){
					//movieName and priceHKD sent from session variable	'newRelease'
					movieName = aMovieObj.getMovieName();
					priceHKD = aMovieObj.getPriceHKD();
					//set up payment method
					paymMethod = "paypal";					

					// priceHKD = 5;
					//Paypal service fee formula
					paypalFixedFee = 0.4;
					paypalPercentageFee = 0.05;
					paypalServiceFee = (priceHKD + paypalFixedFee)/(1-paypalPercentageFee) - priceHKD;
					finalPrice = priceHKD + paypalServiceFee;

					//read prog_pricing levels from database
					rs = read.noCondition("prog_pricing");	

					try{
						while(rs.next()){
							Double tempDouble = rs.getDouble("priceHKD");
							allPriceLevels.add(tempDouble);
							// System.out.println("------"+tempDouble+"--------");
						}
					}catch(SQLException se){
						//Handle errors for JDBC
						se.printStackTrace();
					}catch(Exception e){
						//Handle errors for Class.forName
						e.printStackTrace();
					}
					
					//different prices to pay			
					if(priceHKD.equals(allPriceLevels.get(0))){
						// 444LAFYZV2XUL//hkd0.1
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_47");//1.00
					}else if(priceHKD.equals(allPriceLevels.get(1))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_48");//1.01
					}else if(priceHKD.equals(allPriceLevels.get(2))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_52");//1.04
					}else if(priceHKD.equals(allPriceLevels.get(3))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_59");//1.11
					}else if(priceHKD.equals(allPriceLevels.get(4))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_73");//1.24
					}else if(priceHKD.equals(allPriceLevels.get(5))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_1_99");//1.49
					}else if(priceHKD.equals(allPriceLevels.get(6))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_2_49");//1.97
					}else if(priceHKD.equals(allPriceLevels.get(7))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_3_52");//2.94
					}else if(priceHKD.equals(allPriceLevels.get(8))){
						paypal_hosted_button_value = (String)this.map.get("paypal_hosted_button_value_5_68");//5
					}
					
					//for paymentDoneLogic to use
					paymentDone = true;

					//format (round to 2 decimal places) those double values for use in view
					priceHKDStr = "" + priceHKD;
					paypalServiceFeeStr = String.format("%,.2f", paypalServiceFee);
					finalPriceStr = String.format("%,.2f", finalPrice);

					break;
				}
			}
			
			//////////////////////////////////////////////////
			//add model attributes and set session variables//
			//////////////////////////////////////////////////
			this.addAttribute("movieName", movieName);
			this.addAttribute("priceHKD", priceHKDStr);
			this.addAttribute("paypalServiceFee", paypalServiceFeeStr);
			this.addAttribute("finalPrice", finalPriceStr);
			this.addAttribute("paypal_hosted_button_value", paypal_hosted_button_value);
			//for storing user's choice into session variables
			this.session.setAttribute("yourMovie", movieName);
			this.session.setAttribute("yourPirceHKD", priceHKD);
			this.session.setAttribute("movieID", movieID);
			this.session.setAttribute("paymMethod", paymMethod);
			this.session.setAttribute("paymentDone", paymentDone);
			this.session.setAttribute("language", (String)this.map.get("languageOnCurrentPage"));

			return "protoPayment";
		}
		return "login";//return to login page if conditions are not met
	}
}