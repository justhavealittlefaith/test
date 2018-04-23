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

public class LoginLogic{
	private String myusername;
	private String mypassword;
	private Map<String, Object> map;
	private HttpSession session;
	private HttpServletRequest request;
	private Model model;
	
	public LoginLogic(HttpServletRequest request, Model model){
		this.myusername = request.getParameter("username");
		this.mypassword = request.getParameter("password");
		this.model = model;
		this.map = model.asMap();//for reuse below
		this.request = request;		
	}
	
	public String getViewName(){
		Read read = new Read();
		ResultSet rs = read.basedOn2Col("website_users", "username", "pass", this.myusername, this.mypassword);
				
		try{
			if(!rs.next()){
				this.addAttribute("error", (String)this.map.get("contentLoginCredentialInvalid"));
			}else{
				if(rs.getString("status").equals("active")){
					this.session = this.request.getSession(true);  
					this.session.setAttribute("login_user",this.myusername); 					
					// this.setSession(session);
					this.addAttribute("username", this.myusername);

					return "welcome";
				}else{
					 //error
					 this.addAttribute("error", (String)this.map.get("contentLoginAcctNotActivated"));
				}
			}
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}		
		return "login";
	}
	
	public void addAttribute(String key, String value){
		this.model.addAttribute(key, value);
	}
	
	public Model getModel(){
		return this.model;
	}
	
	// public void setSession(HttpSession session){
		// this.session = session;
	// }
	
	// public HttpSession getSession(){
		// return this.session;
	// }
}