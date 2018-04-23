package app;

import java.util.*;
import java.text.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.*;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.springframework.http.*;
import java.net.URI;
import java.net.*;
import org.springframework.core.io.InputStreamResource;
import org.apache.commons.io.IOUtils;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support


@Controller
public class MainController {
	private java.util.Date dNow = new java.util.Date( );//reference to Date is ambiguous, both class java.sql.Date in java.sql and class java.util.Date in java.util match
	private SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
	private String year = ft.format(dNow);
	
    @GetMapping("/greeting")
    public @ResponseBody String greeting(@RequestParam(name="name", required=false, defaultValue="World") String nameA, 
	@RequestParam(name="language", required=false, defaultValue="en") String language, 
	Model model) {
        model.addAttribute("year", year);
		model.addAttribute("viewName", nameA);
		
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
        return "greeting";
    }
	
	@GetMapping("/")
    public String index(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model) {
		model.addAttribute("year", year);//for footer
		model.addAttribute("videoSrcLink", "promo");
		
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
		System.out.println("--controller /--");
		
        return "index";
    }
	
	@GetMapping("/proposal")
    public String proposal(Model model) {
		model.addAttribute("year", year);
		
		return "proposal";
    }
	
	@GetMapping("/promo")
    public ResponseEntity<byte[]> promo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noStore().getHeaderValue());
		InputStream in = MainController.class.getResourceAsStream("/videos/protoVideos/UltnemaDemo_1min.mp4");
		byte[] media= null;
		
		try{
			media = IOUtils.toByteArray(in); //read the video and then convert it into a byte array
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		
		return responseEntity;
    }
	
	@GetMapping("/login")
    public String login(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model) {
		model.addAttribute("year", year);
		
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
		System.out.println("--controller login--");
		
        return "login";
    }
	
	@PostMapping("/login")
    public String postLogin(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("year", year);
		if(!(request.getParameter("languageOnCurrentPage") == null)){
			language = request.getParameter("languageOnCurrentPage");
		}
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
		System.out.println("--controller POST login--" + language);
		
		if(!(request.getParameter("login") == null)){
			LoginLogic loginLogic = new LoginLogic(request, model);
			String viewName = loginLogic.getViewName();
			model = loginLogic.getModel();
			
			// Create create = new Create();
			// create.basedOn1Col("login_record", "userName", "ben");
			
			// Update update = new Update();
			// update.basedOn2Col("website_users", "email", "userName", "abc@abc.com", "alvin");
			
			return viewName;
		}else if(!(request.getParameter("welcome") == null)){			
			HttpSession session = request.getSession(true);
			String username = (String)session.getAttribute("login_user");
			model.addAttribute("username", username);
			
			String viewName = "welcome";
			
			return viewName;
		}else if(!(request.getParameter("newRelease") == null)){
			NewReleaseLogic newReleaseLogic = new NewReleaseLogic(request, model);
			String viewName = newReleaseLogic.getViewName();
			model = newReleaseLogic.getModel();
			
			// Set refresh, autoload time as 10 seconds
			// response.setIntHeader("Refresh", 10);
			
			return viewName;
		}else if(!(request.getParameter("payment") == null)){
			PaymentLogic paymentLogic = new PaymentLogic(request, model);
			String viewName = paymentLogic.getViewName();
			model = paymentLogic.getModel();
			
			// Set refresh, autoload time as 5 seconds
			// response.setIntHeader("Refresh", 5);
			
			return viewName;
		}else if(!(request.getParameter("playing") == null)){
			PlayingLogic playingLogic = new PlayingLogic(request, model);
			String viewName = playingLogic.getViewName();
			model = playingLogic.getModel();
						
			return viewName;
		}else{
			return "login";
		}
		
    }
	
	@GetMapping("/success")
    public String paymentDone(String language, Model model, HttpServletRequest request) {
		model.addAttribute("year", year);
		//retrieve language variable from session
		HttpSession session = request.getSession(true);
		language = (String)session.getAttribute("language");
		
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
		if(!(request.getParameter("tx") == null)){
			PaymentDoneLogic paymentDoneLogic = new PaymentDoneLogic(request, model);
			String viewName = paymentDoneLogic.getViewName();
			model = paymentDoneLogic.getModel();
			
			return viewName;
		}
		
        return "login";
    }
	
	@GetMapping("/signup")
    public String signup(@RequestParam(name="language", required=false, defaultValue="en") String language, 
	Model model) {
		model.addAttribute("year", year);
		
		LangVariables langVariables = new LangVariables(language);
		Map<String, String> map = langVariables.getLangVariables();
		model.mergeAttributes(map);
		
        return "signup";
    }
	
	@GetMapping("/admin")
    public String admin(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model) {
        return "admin";
    }
	@PostMapping("/admin")
    public @ResponseBody String postAdmin(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model, HttpServletRequest request) {
        AdminLogic adminLogic = new AdminLogic(request, model);
		String message = adminLogic.getMessage();
		
		return message;
    }

}