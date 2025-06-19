package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;

public class TPELoginPageObject extends PageBase{

	public TPELoginPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	static final By UserName=By.name("j_username");
	static final By Password=By.name("j_password");
	static final By SignInBtn=By.name("btnEnter");
	
	//static final By SignInBtn=By.xpath(".//a[contains(@class,'x-btn wt-login-btn-enabled')]");
	
	static String currentUsername = "";
	static String usernameColumn = "";
	static String passwordColumn = "";
	static String envrnment = null;
	public static UserTypes loggedInAs;
	
		public static enum UserTypes{
			SHIPPER ("shipper"),
			VENDOR("vendor"),
			CARRIER("carrier");

		private String value;
		
		UserTypes(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * Method to Open URL	
	 * @throws Exception
	 */
	public void open() throws Exception {
		 
		 String envUrl = dataTable.getCommonData(CommonDataColumn.EnvironmentUrl);
		 rc.logger.info("URL used: "	+ envUrl);

			try {

				driver.manage().deleteAllCookies();
				driver.get(dataTable.getCommonData("EnvironmentUrl"));				
				rc.logger.debug("log4j");
				
			} catch (Exception ex) {				
				rc.logger.error(ex.getMessage());
				System.out.println(ex.getMessage());
				wh.handleAlert();
			}
						
		driver.manage().window().maximize();
		if(driver.getTitle().equalsIgnoreCase("Sign In | Manhattan Associates, Inc.")) { 
			report.addReportStep("Open Manhattan Associates, Inc login page" , 
					"Successfully navigated to Manhattan Associates, Inc login page", 
					StepResult.PASS);			
		}else{
			report.addReportStep("Open Manhattan Associates, Inc page" , 
					"Manhattan Associates, Inc page is not  displayed successfully", 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	 }
	public String readProp() throws Exception{
		try {
			File file = new File("RunConfig.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();			
			while (enuKeys.hasMoreElements()) {				
				String key = (String) enuKeys.nextElement();
				if(key.equalsIgnoreCase("Environment")){
					envrnment = properties.getProperty(key);				
					System.out.println(envrnment);
				}				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return envrnment;
	}
	 
	/**
	 * This function logs a user into the system. If the home page doesn't appear
	 * correctly after pushing the sign-in button, we'll try the entire login process
	 * again
	 * 
	 * @param type
	 * @throws Exception
	 */
	public void login(UserTypes type) throws Exception{
		
		Integer retryCount = 0;
		Integer numberOfAttempts = 4;
		Boolean loggedIn = false;
		
		//Make 2 attempts to log in
		while(retryCount < numberOfAttempts && !loggedIn){
			
			retryCount++;
			
			System.out.println("Opening login screen: Attempt #" + retryCount);
			
			// Open login screen
			open();
			
			// Enter user name and password in form
			enterUserNameAndPassword(type);
			
			// Click sign-in button. Try again, if error occurs
			try{
				sigInBtn();
				loggedIn = true;
				loggedInAs = type;
			}catch(Exception e){
				loggedIn = false;
				String failMsg = "Unable to log in for attempt #" + retryCount;
				report.addReportStep("Log In" , failMsg, StepResult.WARNING);
				rc.logger.info(failMsg);
			}
		}
		
		// If still not logged in after 2 attempts, stop test
		if (!loggedIn){
			String msg = "Unable to log in after " + numberOfAttempts + " attempts";
			report.addReportStep("Log In" , msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}
		
		 
	}
	
	/**
	 * Method to enter user name and password TPE application	
	 * @throws Exception
	 */
	 public void enterUserNameAndPassword() throws Exception{
		
		 enterUserNameAndPassword(UserTypes.SHIPPER);
		 
	 }
	 public void enterUserNameAndPassword(UserTypes type) throws Exception{
		 
		 try{
			 readProp();
			 
			 //String usernameColumn = "";
			 //String passwordColumn = "";
		
			 switch (type){

				case SHIPPER :				

						usernameColumn = CommonDataColumn.UIUsername;
						passwordColumn = CommonDataColumn.UIPassword;	
					
					break;			
					
				default: 
					usernameColumn = CommonDataColumn.UIUsername;
					passwordColumn = CommonDataColumn.UIPassword;
					break;
			}
			 
			 //currentUsername = dataTable.getCommonData(usernameColumn);
			 //String currentPassword = dataTable.getCommonData(passwordColumn);
			 
			 currentUsername = dataTable.getCommonData("UIUsername");
			 String currentPassword = dataTable.getCommonData("UIPassword");
			 	 
			 
			 if(wh.isElementPresent(UserName, 3)){
				 wh.clickElement(UserName);
				 wh.sendKeys(UserName, currentUsername);
			 }else{
				 throw new Exception("Unable to locate username input field");
			 }

			 if(wh.isElementPresent(Password, 3)){
				 wh.clickElement(Password);
				 wh.sendKeys(Password, currentPassword);
			 }else{
				 throw new Exception("Unable to locate password input field");
			 }		
			 
			 if(wh.getAttribute(UserName, "value")!="" && wh.getAttribute(Password, "value")!=""){
				 String msg = "Sucessfully entered UserName and Password. Username: [" + currentUsername
						 + "], Password: **********";
				 
/*				 String msg = "Sucessfully entered UserName and Password. Username: [" + currentUsername
						 + "], Password: [" + currentPassword + "]";*/
				 
				 rc.logger.info(msg);
				 report.addReportStep("Enter UserName and Password" , 
							msg, 
							StepResult.PASS);	
				 
			 }else{
				 throw new Exception("Username and Password not entered correctly");
			 }
		 }catch(Exception e){
			 String msg = "Unable to fill out username and password for login. "
					 + e.getMessage();
			 rc.logger.info(msg);
			 report.addReportStep("Fill out username and password for login" , msg, StepResult.FAIL);
			 rc.throwTCTerminationException();
		 }
		 
					 
	 }	 
	 	
	 
	 /**
		 * Method to Sign-in button
		 * @throws Exception
		 */
	 public void sigInBtn() throws Exception{		 
	try{
		 if(!wh.isElementPresent(SignInBtn, 3)){
			 
			 throw new Exception("Unable to locate Sign-In button");
		 }
		 
		 try{
			 wh.jsClick(SignInBtn);
		 }catch(Exception e){
			 throw new Exception("Unable to click Sign-In button. " + e.getMessage());
		 }
		 
		 wh.waitUntilDisappear(TPEHomePageObject.LoadingPage);
		 Thread.sleep(10000);
		 // Wait until home page is displayed
//		 static final By lnkMenu  =By.xpath("//span[contains(text(),'enu')]");
		 By locator = By.xpath("//span[contains(text(),'enu')]");
		 if (wh.isElementPresent(locator, 25)){
			 String msg = "Sucessfully opened the DF WMS home page";
			 rc.logger.info(msg);
			 report.addReportStep("Click Sign In button" , 
						msg, 
						StepResult.PASS);
		 }else{
			 throw new Exception("User home page not displaying");
		 }
		 }catch(Exception e){
			 String msg = "Unable to log in. " + e.getMessage();
			 report.addReportStep("Log In" , msg, StepResult.FAIL);
			 rc.logger.info(msg);
			 rc.throwTCTerminationException();
		 }
	 }
public void loginsample(String Username,String Passwords) throws Exception{
		 
		 if(wh.isElementPresent(UserName, 10)){
			 wh.sendKeys(UserName, Username);
		 }

		 if(wh.isElementPresent(Password, 10)){
			 wh.sendKeys(Password, Passwords);
		 }		
		 
		 if(wh.getAttribute(UserName, "value")!="" && wh.getAttribute(Password, "value")!=""){
			 report.addReportStep("Enter UserName and Password" , 
						"Sucessfully entered UserName and Password", 
						StepResult.PASS);	
		 }else{
			 report.addReportStep("Enter UserName and Password" , 
						"Not entered UserName and Password", 
						StepResult.FAIL);	
		 }
					 
	 }	 
}
