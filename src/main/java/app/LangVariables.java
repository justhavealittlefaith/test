package app;

import java.util.*;

public class LangVariables {
	private String language;
	//possible values: 'Production' and 'Testing'
	private String environment = "Testing"; 
	
	public LangVariables(String language){
		this.language = language;
	}
	
	public Map<String, String> getLangVariables(){
		Map<String, String> map = new HashMap<>();

		if(language.equals("中文")){			
			//global
			map.put("languageOnCurrentPage", "中文");
			map.put("contentLang", "EN");
			map.put("contentLogin", "登入");
			map.put("contentLogout", "登出");
			map.put("contentLoginUsername", "用戶名稱");
			map.put("contentSubmit", "送出");
			map.put("contentLoginPasswordPlaceholder", "輸入密碼");
			map.put("contentBack", "返回");
			map.put("contentSignUpCheckingValid", "完美!");
			map.put("contentGeneralErrorMsg", "對不起，我生病了。");
			map.put("contentGeneralErrorComfortMsg", "我們會以最快的速度解決問題。");
			//index.php
			map.put("contentIndexContact", "聯絡");
			map.put("contentIndexContactRegion", "香港");
			map.put("contentIndexHowItWorks", "怎樣運作");
			map.put("contentIndexTryOutMsg", "立即試一試 ");
			map.put("contentIndexTryOutHref", "服務");
			map.put("contentIndexCheckOutDetailsMsg", "有關詳情，請按下 ");
			map.put("contentIndexCheckOutDetailsHref", "計劃(只提供EN)");
			map.put("contentIndexMainMsg", "不只是一個影院， <br>更是一個電影工業生物鏈的組合。");
			map.put("contentIndexHKD1", "HKD 1");
			map.put("contentIndexPortableCinema", "可攜帶的戲院");
			map.put("contentIndexWorldwideAccessibility", "任何地方");
			map.put("contentIndex247", "24小時");
			map.put("contentIndexUseBigData", "以數據分析觀眾喜好");
			map.put("contentIndexYouCouldBeTheDirector", "你的故事， 你話事");
			//login.php
			map.put("contentLoginPassword", "密碼");
			map.put("contentLoginUsernamePlaceholder", "輸入用戶名稱");
			map.put("contentLoginNewUser", "我是新用家");
			map.put("contentLoginAcctNotActivated", "用戶尚未認證");
			map.put("contentLoginCredentialInvalid", "用戶名稱或密碼錯誤");
			//signUp.php
			map.put("contentSignUpHere", "註冊");
			map.put("contentSignUpRequiredField", "必填");
			map.put("contentSignUpInstruction", "請由上至下填寫資料");
			map.put("contentSignUpEmail", "電郵");
			map.put("contentSignUpPassword", "密碼");
			map.put("contentSignUpConfirmPassword", "再次輸入密碼");
			map.put("contentSignUpUsernamePlaceholder", "輸入字母或數字");
			map.put("contentSignUpEmailPlaceholder", "正確電郵格式");
			map.put("contentSignUpPasswordPlaceholder", "密碼長度4-20");
			map.put("contentSignUpConfirmPasswordPlaceholder", "需與密碼一樣");
			map.put("contentSignUpGoBackToLoginPage", "返回登入");
			map.put("contentSignUpRegistrationCompletedMsg", "恭喜你! 你的註冊已完成。<br>請檢查電郵，並按下啟用連結。<br>");
			map.put("contentSignUpEmailSubject", "恭喜你! 你的ULTNEMA註冊已完成");
			map.put("contentSignUpEmailTime", "時間");
			map.put("contentClickBelowLinkMsg", "請按下連結， 並啟用你的帳戶");
			map.put("contentAlreadyAUserMsg", "不好意思! 你已是註冊的用戶。");
			//signUpChecking
			map.put("contentSignUpCheckingUsernameRequired", "用戶名稱是必須");
			map.put("contentSignUpCheckingUsernameLength", "長度必須是2-20之間");
			map.put("contentSignUpCheckingUsernameAlnum", "並不完全是字母或數字或兩者結合");
			map.put("contentSignUpCheckingUsernameAlreadyUsed", "不好意思! 已用過了");
			map.put("contentSignUpCheckingEmailRequired", "電郵是必須");
			map.put("contentSignUpCheckingEmailInvalid", "無效的電郵格式");
			map.put("contentSignUpCheckingEmailAlreadyUsed", "不好意思! 已用過了");
			map.put("contentSignUpCheckingPasswordRequired", "密碼是必須");
			map.put("contentSignUpCheckingPasswordLength", "長度必須在4-20之間");
			map.put("contentSignUpCheckingCPassRequired", "必須輸入密碼兩次");
			map.put("contentSignUpCheckingCPassIfTheSame", "兩個密碼輸入必須一樣");
			//accountActivation.php
			map.put("contentAcctActivation", "帳戶啟用");
			map.put("contentAcctActivationGreeting", "你好");
			map.put("contentAcctActivationSuccessful", "你的帳戶已成功啟用。");
			map.put("contentAcctActivationEmailNotCorrect", "不好意思! 你的電郵不正確。<br>你的帳戶尚未成功啟用。");
			map.put("contentAcctActivationAlreadyActivated", "你在此之前已啟用了這個帳戶。");
			map.put("contentAcctActivationButton", "啟用");
			
			//welcome.php
			map.put("contentWelcome", "歡迎");
			map.put("contentNewRelease", "新上映");
			map.put("contentWelcomeSelectPersonalInfo", "選擇 - 個人資料");
			map.put("contentWelcomeUserRecord", "觀看歷史");
			map.put("contentWelcomeAcctInfo", "用戶資料");
			map.put("contentWelcomeListedHere", "資料會顯示在這裡");
			//dropdown.php
			map.put("contentDropdownMsg", "排最頂的是最新的(只顯示最近10個)");
			map.put("contentDropdownReferenceNo", "參考編號");
			map.put("contentDropdownTransactionDate", "交易日期");
			map.put("contentDropdownMovieName", "電影名稱");
			map.put("contentDropdownPrice", "價錢(HKD)");
			map.put("contentDropdownPaymentMethod", "付款方法");
			map.put("contentDropdownEmail", "電郵");
			map.put("contentDropdownChangeEmail", "更改電郵");
			map.put("contentDropdownChangePassword", "更改密碼");
			map.put("contentDropdownChangeEmailPlaceholder", "輸入電郵");
			map.put("contentDropdown0Record", "0 紀錄");
			//dropdownChecking.php
			map.put("contentConnDropdownEmailRequired", "電郵是必需");
			map.put("contentConnDropdownEmailInvalid", "電郵格式錯誤");
			map.put("contentConnDropdownEmailSuccessful", "電郵更新成功。");
			map.put("contentConnDropdownPasswordRequired", "密碼是必需");
			map.put("contentConnDropdownPasswordInvalid", "請選擇4-8位為密碼");
			map.put("contentConnDropdownPasswordSuccessful", "密碼更新成功。");
			//protoNewRelease.php
			map.put("contentNewReleaseBoxOffice", "票房");
			map.put("contentNewReleaseViews", "觀看次數");
			map.put("contentNewReleasePay", "付費");
			map.put("contentNewReleaseReleaseDate", "上映日期");
			//protoPayment.php
			map.put("contentYourChoice", "你的選擇");
			map.put("contentProtoPaymentMovie", "電影");
			map.put("contentProtoPaymentCurrentPrice", "現時價錢");
			map.put("contentProtoPaymentPaypalServiceFee", "及Paypal所收取的服務費");
			map.put("contentProtoPaymentFinalPrice", "你需要付");
			map.put("contentProtoPaymentCautionMsg", "請註意: 你最長可逗留此頁(秒):");
			//protoPaymentDone.php
			map.put("contentProtoPaymentDonePaymentDetail", "付款資料");
			map.put("contentProtoPaymentDonePayerName", "付款人");
			map.put("contentProtoPaymentDonePaid", "已付");
			map.put("contentProtoPaymentDoneMsg", "你已成功完成付款，收據已發送到你的電郵。享受屬於你的時間!");
			map.put("contentProtoPaymentDoneGo", "立即觀看");
			//protoPlaying.php
			map.put("contentProtoPlayingStartTime", "開始時間");
			map.put("contentProtoPlayingCautionMsg", "請註意: 此頁面只能載入一次");
			map.put("contentProtoPlayingTimeAllowedMsg", "你最長可享受(分鐘)");
			map.put("contentProtoPlayingMovieLength", "電影長度");
			map.put("contentProtoPlayingLogoutMsg", "若電影播放完畢，頁面亦會自動跳回新上映。最後， 請享受這個旅程。");
			// map.put("contentProtoPlayingPlayPause", "播放/暫停");
			map.put("contentProtoPlayingFullScreen", "全螢幕");
			map.put("contentProtoPlayingPlay", "播放");
			map.put("contentProtoPlayingPause", "暫停");
		}else{			
			//global
			map.put("languageOnCurrentPage", "en");
			map.put("contentLang", "中文");
			map.put("contentLogin", "Login");
			map.put("contentLogout", "Logout");
			map.put("contentLoginUsername", "Username");
			map.put("contentSubmit", "Submit");
			map.put("contentLoginPasswordPlaceholder", "Enter Password");
			map.put("contentBack", "Back");
			map.put("contentSignUpCheckingValid", "Perfect!");
			map.put("contentGeneralErrorMsg", "Sorry! Something went wrong.");
			map.put("contentGeneralErrorComfortMsg", "We're working on it and we'll get it fixed as soon as we can.");
			//index.php
			map.put("contentIndexContact", "Contact");
			map.put("contentIndexContactRegion", "Hong Kong");
			map.put("contentIndexHowItWorks", "How it works");
			map.put("contentIndexTryOutMsg", "Try out in our ");
			map.put("contentIndexTryOutHref", "system");
			map.put("contentIndexCheckOutDetailsMsg", "For details, please check out our ");
			map.put("contentIndexCheckOutDetailsHref", "proposal");
			map.put("contentIndexMainMsg", "We are going to launch far beyond just an online cinema,<br> but to relocate the whole physical cinema’s ecosystem to the internet, to make it universally accessible and internet-oriented.");
			map.put("contentIndexHKD1", "HKD 1");
			map.put("contentIndexPortableCinema", "Portable cinema");
			map.put("contentIndexWorldwideAccessibility", "Worldwide accessibility");
			map.put("contentIndex247", "24/7 watching experience");
			map.put("contentIndexUseBigData", "Use big data to analyze audience's preference");
			map.put("contentIndexYouCouldBeTheDirector", "You could be the director of your story through crowdfunding");
			//login.php
			map.put("contentLoginPassword", "Password");
			map.put("contentLoginUsernamePlaceholder", "Enter Username");
			map.put("contentLoginNewUser", "I am a New User");
			map.put("contentLoginAcctNotActivated", "Account is not yet activated");
			map.put("contentLoginCredentialInvalid", "Username or Password is invalid");
			//signUp.php
			map.put("contentSignUpHere", "Sign Up Here");
			map.put("contentSignUpRequiredField", "Required field");
			map.put("contentSignUpInstruction", "Please fill in the following fields one by one");
			map.put("contentSignUpEmail", "Email");
			map.put("contentSignUpPassword", "Password");
			map.put("contentSignUpConfirmPassword", "Confirm password");
			map.put("contentSignUpUsernamePlaceholder", "Enter letters or digits");
			map.put("contentSignUpEmailPlaceholder", "Valid Email Format");
			map.put("contentSignUpPasswordPlaceholder", "Password Length between 4-20");
			map.put("contentSignUpConfirmPasswordPlaceholder", "Must be the same as password");
			map.put("contentSignUpGoBackToLoginPage", "Go Back to Login page");
			map.put("contentSignUpRegistrationCompletedMsg", "CONGRATULATIONS! YOUR REGISTRATION IS COMPLETED.<br>Please check your email to activate the account.<br>");
			map.put("contentSignUpEmailSubject", "CONGRATULATIONS! YOUR REGISTRATION ON ULTNEMA IS COMPLETED");
			map.put("contentSignUpEmailTime", "Time");
			map.put("contentClickBelowLinkMsg", "Please click the link below to activate your account");
			map.put("contentAlreadyAUserMsg", "SORRY! YOU ARE ALREADY A REGISTERED USER.");
			//signUpChecking
			map.put("contentSignUpCheckingUsernameRequired", "Username is required");
			map.put("contentSignUpCheckingUsernameLength", "You must choose a username between 2 and 20 characters");
			map.put("contentSignUpCheckingUsernameAlnum", "does not consist of all letters or digits or purely both");
			map.put("contentSignUpCheckingUsernameAlreadyUsed", "Sorry! Already used");
			map.put("contentSignUpCheckingEmailRequired", "Email is required");
			map.put("contentSignUpCheckingEmailInvalid", "Invalid email format");
			map.put("contentSignUpCheckingEmailAlreadyUsed", "Sorry! Already used");
			map.put("contentSignUpCheckingPasswordRequired", "Password is required");
			map.put("contentSignUpCheckingPasswordLength", "You must choose a password between 4 and 20 characters");
			map.put("contentSignUpCheckingCPassRequired", "Entering password twice is required");
			map.put("contentSignUpCheckingCPassIfTheSame", "Both password entries need to be the same");
			//accountActivation.php
			map.put("contentAcctActivation", "Account Activation");
			map.put("contentAcctActivationGreeting", "Hi");
			map.put("contentAcctActivationSuccessful", "Your account is activated successfully.");
			map.put("contentAcctActivationEmailNotCorrect", "Sorry! The email is not correct.<br>You haven't yet been activated.");
			map.put("contentAcctActivationAlreadyActivated", "Sorry! Your account is already activated.");
			map.put("contentAcctActivationButton", "Activate now");
			
			//welcome.php
			map.put("contentWelcome", "Welcome");
			map.put("contentNewRelease", "New Release");
			map.put("contentWelcomeSelectPersonalInfo", "Select - Personal Information");
			map.put("contentWelcomeUserRecord", "User Record");
			map.put("contentWelcomeAcctInfo", "Account Information");
			map.put("contentWelcomeListedHere", "Personal particulars will be listed here");
			//dropdown.php
			map.put("contentDropdownMsg", "Ordered according to your most recent views (only 10 views shown)");
			map.put("contentDropdownReferenceNo", "Reference No.");
			map.put("contentDropdownTransactionDate", "Transaction Date");
			map.put("contentDropdownMovieName", "Movie Name");
			map.put("contentDropdownPrice", "Price(HKD)");
			map.put("contentDropdownPaymentMethod", "Payment Method");
			map.put("contentDropdownEmail", "Email");
			map.put("contentDropdownChangeEmail", "Change Email");
			map.put("contentDropdownChangePassword", "Change Password");
			map.put("contentDropdownChangeEmailPlaceholder", "Enter Email");
			map.put("contentDropdown0Record", "0 record");
			//dropdownChecking.php
			map.put("contentConnDropdownEmailRequired", "Email is required");
			map.put("contentConnDropdownEmailInvalid", "Invalid email format");
			map.put("contentConnDropdownEmailSuccessful", "Email updated successfully.");
			map.put("contentConnDropdownPasswordRequired", "Password is required");
			map.put("contentConnDropdownPasswordInvalid", "You must choose a password between 4 and 8 digits");
			map.put("contentConnDropdownPasswordSuccessful", "Password updated successfully.");
			//protoNewRelease.php
			map.put("contentNewReleaseBoxOffice", "Box Office");
			map.put("contentNewReleaseViews", "Views");
			map.put("contentNewReleasePay", "Pay");
			map.put("contentNewReleaseReleaseDate", "Release Date");
			//protoPayment.php
			map.put("contentYourChoice", "Your choice");
			map.put("contentProtoPaymentMovie", "Movie");
			map.put("contentProtoPaymentCurrentPrice", "Current Price");
			map.put("contentProtoPaymentPaypalServiceFee", "And Paypal service fees");
			map.put("contentProtoPaymentFinalPrice", "You need to pay");
			map.put("contentProtoPaymentCautionMsg", "Attention: The maximum time (second) you can stay on this page is:");
			//protoPaymentDone.php
			map.put("contentProtoPaymentDonePaymentDetail", "Payment Detail");
			map.put("contentProtoPaymentDonePayerName", "Name");
			map.put("contentProtoPaymentDonePaid", "Paid");
			map.put("contentProtoPaymentDoneMsg", "Your transaction has been completed, and a receipt for your purchase has been emailed to you. Enjoy your movie!");
			map.put("contentProtoPaymentDoneGo", "Go!");
			//protoPlaying.php
			map.put("contentProtoPlayingStartTime", "Start time");
			map.put("contentProtoPlayingCautionMsg", "Attention: 'Refresh' is not allowed on this page");
			map.put("contentProtoPlayingTimeAllowedMsg", "The maximum time (mins) you can stay on this page is");
			map.put("contentProtoPlayingMovieLength", "movie length");
			map.put("contentProtoPlayingLogoutMsg", "Or you will be automatically redirected to New Release when movie ends. Have fun!");
			// map.put("contentProtoPlayingPlayPause", "Play/Pause");
			map.put("contentProtoPlayingFullScreen", "Full Screen");
			map.put("contentProtoPlayingPlay", "Play");
			map.put("contentProtoPlayingPause", "Pause");
		}
		
		//----------------------------------------
		//----------------------------------------
		//----------------------------------------
		//Variables to use in 'Production' or 'Testing' environment when accepting payment
		
		if(environment.equals("Testing")){
			// map.put("logoImgSrc", "/images/Logo_testing.png");
			map.put("logoImgSrc", "/images/Logo.png");
			map.put("paypalFormURL", "https://www.sandbox.paypal.com/cgi-bin/webscr");
			map.put("paypalImgSrc", "https://www.sandbox.paypal.com/en_GB/HK/i/btn/btn_paynowCC_LG.gif");
			map.put("paypal_hostname", "www.sandbox.paypal.com");
			map.put("paypal_identityToken", "PFjDN35hCKfqu3ll3u5FsnIouAiCtCbprTMymz6l-vnFp3eMzfg4kJMnemK");
			map.put("paypal_hosted_button_value_1_47", "KKFCN4UFR77VG"); //1
			map.put("paypal_hosted_button_value_1_48", "5JUAAL9LR2C7N");//1.01
			map.put("paypal_hosted_button_value_1_52", "CALZKQHGZQDZW");//1.04
			map.put("paypal_hosted_button_value_1_59", "PVFSB9SEYLL3A");//1.11
			map.put("paypal_hosted_button_value_1_73", "7Y7H9TKRP8KNQ");//1.24
			map.put("paypal_hosted_button_value_1_99", "P4YG5NC52L6Q6");//1.49
			map.put("paypal_hosted_button_value_2_49", "MUCW8MYZKE6AU");//1.97
			map.put("paypal_hosted_button_value_3_52", "N4WXEV7DF8V6L");//2.94
			map.put("paypal_hosted_button_value_5_68", "GZE29JEQ4DGU4");//5
		}else{ //Production
			map.put("logoImgSrc", "/images/Logo.png");
			map.put("paypalFormURL", "https://www.paypal.com/cgi-bin/webscr");
			map.put("paypalImgSrc", "https://www.paypalobjects.com/en_GB/HK/i/btn/btn_paynowCC_LG.gif");
			map.put("paypal_hostname", "www.paypal.com");
			map.put("paypal_identityToken", "XFwqDfuTckhPdM3b5HinNGny3w8OoTNV1AuZt-QGXdBt6C2qeME0Xmc34aO");
			map.put("paypal_hosted_button_value_1_47", "LUNZG3L5LYS96"); //1
			map.put("paypal_hosted_button_value_1_48", "MX9FDLMK6LNBS");//1.01
			map.put("paypal_hosted_button_value_1_52", "ZKYWHP84EW5EY");//1.04
			map.put("paypal_hosted_button_value_1_59", "KYVHEQ3DJN26E");//1.11
			map.put("paypal_hosted_button_value_1_73", "U66RQ6WPDJN22");//1.24
			map.put("paypal_hosted_button_value_1_99", "2ZKVEW6S4G976");//1.49
			map.put("paypal_hosted_button_value_2_49", "FHS3W7XSUCT28");//1.97
			map.put("paypal_hosted_button_value_3_52", "AUABUMWK5FADE");//2.94
			map.put("paypal_hosted_button_value_5_68", "MRZHLQ6K9JZCU");//5
		}
		
		return map;
	}
}