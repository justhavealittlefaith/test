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

public class PaymentDoneLogic{
	private HttpServletRequest request;
	private Model model;
	private HttpSession session;
	private Map<String, Object> map;
	private Boolean paymentDone;

	public PaymentDoneLogic(HttpServletRequest request, Model model){
		this.request = request;
		this.model = model;
		this.map = model.asMap();//for reuse below
		
		////////////////////////////////
		//initialize session variables//
		////////////////////////////////
		this.session = this.request.getSession(true);  
		String username = (String)this.session.getAttribute("login_user");
		String yourMovie = (String)this.session.getAttribute("yourMovie");
		Double yourPirceHKD = (Double)this.session.getAttribute("yourPirceHKD");
		Integer movieID = (Integer)this.session.getAttribute("movieID");
		this.paymentDone = (Boolean)this.session.getAttribute("paymentDone");
		
		this.addAttribute("username", username);
		this.addAttribute("yourMovie", yourMovie);
		this.addAttribute("yourPirceHKD", yourPirceHKD);
		this.addAttribute("movieID", movieID);
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
		
		// String tempURL = "http://ipinfo.io/json";
				
		// try{
			// HttpURLConnection con = (HttpURLConnection) new URL(tempURL).openConnection();
			// con.setRequestMethod("GET");
			// InputStream inputStream = con.getInputStream();
			
			// Scanner s = new Scanner(inputStream).useDelimiter("\\A");
			// String result = s.hasNext() ? s.next() : "nothing";
			
			// System.out.println("--------"+result+"-------------");
			
			// JSONParser parser = new JSONParser();
			// Object obj = parser.parse(result);
			// JSONObject obj2 = (JSONObject)obj;
			// System.out.println("--------"+obj2.get("country")+"-------------"); 

		// }catch(ParseException pe){
			 // System.out.println("position: " + pe.getPosition());
			 // System.out.println(pe);
		// }catch(Exception e){
			// //Handle errors for Class.forName
			// e.printStackTrace();
		// }
		
		Boolean isDuplicated = true;//assumming it's duplicated until checking is done
		Boolean firstTimeAccessVideo = false;//assumming not the first time until checking is done
		Double yourPirceHKD = 0.0;
		String tx_token = null;
		String movieTableName = null;
		String pp_hostname = null;
		String auth_token = null;
		String req = null;
		String yourMovie = null;
		String result = null;
		String firstname = null;
		String lastname = null;
		String itemname = null;
		String amount = null;
		String payment_status = null;
		
		String[] lines = null;
		Read read = new Read();
		ResultSet rs_movies_info = null;
		ResultSet rs_movieTableName = null;
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(!(this.request.getParameter("tx") == null) ) {
			//example query string from paypal: http://ultnema.net46.net/response.php?success&amt=1.40&cc=HKD&item_name=FinalPriceHKD1.4&st=Completed&tx=1LE361253R822912L	
			tx_token = this.request.getParameter("tx");
			
			//check if txn_id has been previously processed, one by one from transaction record tables
			rs_movies_info = read.noCondition("movies_info");
						
			try{
				while(rs_movies_info.next()){
					movieTableName = rs_movies_info.getString("movieNameForTran");
					//check that txn_id has not been previously processed from individual transaction record table
					rs_movieTableName = read.basedOn1Col(movieTableName, "paypal_txn_id", tx_token);
					
					if(!rs_movieTableName.next()){
						isDuplicated = false;
					}
				}
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////// to be removed when paypal Payment Data Transfer variables are accessible
			isDuplicated = true;
			yourMovie = (String)this.session.getAttribute("yourMovie");
			yourPirceHKD = (Double)this.session.getAttribute("yourPirceHKD");
			this.session.setAttribute("firstTimeAccessVideo", true);
			this.addAttribute("yourMovie", yourMovie);
			this.addAttribute("yourPirceHKD", yourPirceHKD);
			this.addAttribute("firstname", "Jane");
			this.addAttribute("lastname", "Doe");
			this.addAttribute("amount", "999");
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////
			
			//When txn_id is not duplicated
			if(isDuplicated.equals(false)){
				
				pp_hostname = (String)map.get("paypal_hostname"); // Change to www.sandbox.paypal.com to test against sandbox
				auth_token = (String)map.get("paypal_identityToken");//auth_token is retrieved from paypal account "my selling tools"
				req = "cmd=_notify-synch&tx="+tx_token+"&at="+auth_token;
				
				//allow to watch a movie
				firstTimeAccessVideo = true;
				
				//setting curl session
				String tempUrl = "https://"+pp_hostname+"/cgi-bin/webscr";

				try{
					//setting curl session
					HttpURLConnection httpCon = (HttpURLConnection) new URL(tempUrl).openConnection();
					httpCon.setDoOutput(true);
					httpCon.setRequestMethod("POST");
					OutputStream os = httpCon.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
					osw.write(req);
					osw.flush();
					osw.close();
					os.close();  //don't forget to close the OutputStream
					httpCon.connect();
					
					InputStream inputStream = httpCon.getInputStream();
					
					Scanner s = new Scanner(inputStream).useDelimiter("\\n");
					result = s.hasNext() ? s.next() : "nothing";
					
					System.out.println("--------"+result+"-------------");
					
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(result);
					JSONObject obj2 = (JSONObject)obj;
					System.out.println("--------"+obj2.get("tx")+"-------------"); 

				}catch(ParseException pe){
					 System.out.println("position: " + pe.getPosition());
					 System.out.println(pe);
				}catch(Exception e){
					//Handle errors for Class.forName
					e.printStackTrace();
				}

				//Paypal Payment Data Transfer variables (delimited by "\n"):
				//SUCCESS mc_gross=0.10 protection_eligibility=Eligible payer_id=629GH4TT77R74 tax=0.00 payment_date=07%3A10%3A39+Apr+03%2C+2018+PDT payment_status=Completed charset=Big5 first_name=Hung+Yu mc_fee=0.10 custom= payer_status=unverified business=ultnema%40outlook.com quantity=1 payer_email=charlieng2%40outlook.com txn_id=37P68100LL702851G payment_type=instant last_name=NG receiver_email=ultnema%40outlook.com payment_fee= shipping_discount=0.00 insurance_amount=0.00 receiver_id=2PHD2U59ZSBTC txn_type=web_accept item_name=currentPriceHKDtesting discount=0.00 mc_currency=HKD item_number= residence_country=HK handling_amount=0.00 shipping_method=Default transaction_subject= payment_gross= shipping=0.00
				
				//curl response from paypal
				if(result == null){
					//for debugging
					String err = "curl cannot return response - HTTP error";
					System.out.println("--------"+err+"-------------");
					
					// customError_captureErrorMsg($_SESSION['login_user'], $err, $_SERVER['REQUEST_URI'], '');
					// header("Location: $protoNewReleaseSrcLink");
				}else{
					 // parse the data
					lines = result.split("\n");
					
					if (lines[0].compareTo("SUCCESS") == 0) {
						for (int i = 1; i < lines.length; i++) {
							String[] temp = lines[i].split("=", 2);
							try{
								resultMap.put(URLDecoder.decode(temp[0], "UTF-8"), URLDecoder.decode(temp[1], "UTF-8"));
							}catch(Exception e){
								e.printStackTrace();
							}
						}

						// check that receiver_email is your Primary PayPal email
						// check that payment_amount/payment_currency are correct
						// process payment
						firstname = resultMap.get("first_name");
						lastname = resultMap.get("last_name");
						itemname = resultMap.get("item_name");
						amount = resultMap.get("mc_gross");
						payment_status = resultMap.get("payment_status");// check the payment_status is Completed

						if(this.paymentDone && payment_status.equals("Completed")) {
							PaymentDoneUponSuccessfulPayment paymentDoneUponSuccessfulPayment = new PaymentDoneUponSuccessfulPayment(this.request, this.model);
							
							this.model = paymentDoneUponSuccessfulPayment.getModel();
							
							// header("Refresh:0");
							this.paymentDone = false;
						}else{
							//for debugging
							// $err = "\$_SESSION['paymentDone'] is not true or payment_status is not \"Completed\"";
							// customError_captureErrorMsg($_SESSION['login_user'], $err, $_SERVER['REQUEST_URI'], '');
							
							// header("Location: $protoNewReleaseSrcLink");
						}

					}else{
						//for debugging
						// $err = "FAIL is sent by curl. Check \$req[$req] sent to paypal";
						// customError_captureErrorMsg($_SESSION['login_user'], $err, $_SERVER['REQUEST_URI'], '');
						
						// header("Location: $protoNewReleaseSrcLink");
					}
				}
				
			}else{ //When txn_id is duplicated
				// $msg = "Sorry this transaction has been processed already.";
				// require 'classErrorPage.php';
				// $errorPageObj = new ErrorPage($msg);
				// echo $errorPageObj->showErrorPage();
			}
			
			//////////////////////////////////////////////////
			//add model attributes and set session variables//
			//////////////////////////////////////////////////
			// this.addAttribute("firstname", firstname);
			// this.addAttribute("lastname", lastname);
			// this.addAttribute("amount", amount);
			// this.session.setAttribute("firstTimeAccessVideo", firstTimeAccessVideo);
			// this.session.setAttribute("paymentDone", paymentDone);

			return "protoPaymentDone";
		}
		return "login";//return to login page if conditions are not met
	}
}