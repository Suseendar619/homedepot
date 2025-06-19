

package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSLoginPageObject extends PageBase {

	public DFWMSLoginPageObject(InstanceContainer ic) {
		super(ic);
	}

	JDBC_Connection jd = new JDBC_Connection(ic);
	public static String first_tab;
	public static String dc = "";
	public static String port = "";
	 static By UserName = By.name("j_username");
	 static By Password = By.name("j_password");
	final By WynsoftUsername = By.id("UserName");
	final By WynsoftPassword = By.id("Password");
	final By WynsoftSignIn = By.id("btnSubmit");
	final By WynsoftError = By.xpath(".//span[contains(@class,'field-validation-error')]");
	// static final By UserName=By.id("j_username");
	// static final By Password=By.id("j_password");
	final By SignInBtn = By.id("loginButton");
	final By SignInBtn2012 = By.xpath("//button[@name='btnEnter']");
	final By continueBtn= By.xpath("//button[text()='Continue']");
	final By RFSignInBtn = By.xpath(".//INPUT[@type='submit']");
	final By SelectWMS = By.xpath(".//button[contains(@class,'list-group-item') and contains(text(),'WMS')]");
	final By WMSOk = By.xpath(".//button[contains(@id,'go') and contains(text(),'OK')]");
	final By SendAnyway = By.xpath(".//button[contains(@class,'secondary-button small-link') and contains(text(),'Send anyway')]");
	final By Advance = By.xpath("//button[@id='details-button']");
	final By environmentText=By.xpath("//span[starts-with(@id, 'mps_regions_selector-') and contains(@id, '-btnInnerEl')]");
	final By environmentText2012=By.xpath("(//td//span[@class='name -hft_pht'])[3]");
	final By clockInLoginUsername = By.xpath("(//input[@alt='Find Login User ID or First Name or Last Name'])[1]");
	final By WynUsername = By.xpath("//input[@id='UserName']");
	final By WynPaasword = By.xpath("//input[@id='Password']");
	final By WynLoginBtn = By.xpath("//input[@id='btnSubmit']");

	//Packsize
	final By PckSizeUsername = By.xpath("//input[@id='username']");
	final By PckSizePaasword = By.xpath("//input[@id='password']");
	final By PckSizeLoginBtn = By.xpath("//button[@type='submit']");

	//WES
	final By WESUsername = By.xpath("//input[@name='username']");
	final By WESPaasword = By.xpath("//input[@name='password']");
	final By WESLoginBtn = By.xpath("//input[@name='login']");


	final By Submit = By.xpath("(//input[@type='submit'])[4]");
	final By checkUser = By.xpath("//input[@value='0']");
	final By clockIn = By.xpath("//input[@value='Clock In']");
	final By clockOut = By.xpath("//input[@value='Clock Out']");
	final By selectDateandTime = By.xpath("//input[@title='Select date and time']");
	final By sendClockIn = By.xpath("//input[@value='Clock In']");
	 // static final By
	// SignInBtn=By.xpath(".//a[contains(@class,'x-btn wt-login-btn-enabled')]");

	static String currentUsername = "";
	static String currentPassword = "";
	static String usernameColumn = "";
	static String passwordColumn = "";
	String envrnment = "";
	String envUrl = "";
	static String next_tab;
	/**
	 * Method to Open Application URL
	 * 
	 * @throws Exception
	 **/

	public void open() throws Exception {

		//envUrl = dataTable.getCommonData(CommonDataColumn.LG);
		//envUrl = dataTable.getCommonData(CommonDataColumn.LG_New);
		envUrl = dataTable.getCommonData(CommonDataColumn.Houston_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.NorthLake);
		//envUrl = dataTable.getCommonData(CommonDataColumn.BRT);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Dallas_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Baltimore_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Lacey_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Tracey_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Tampa_2019);
		//envUrl = dataTable.getCommonData(CommonDataColumn.Houston_Packsize);


		//envUrl= dataTable.getCommonData(CommonDataColumn.LG);
		rc.logger.info("URL used: " + envUrl);

		try {

			driver.manage().deleteAllCookies();
			driver.get(dataTable.getCommonData("EnvironmentUrl"));

			//Select Advance and click unsafe 

			if(wh.isElementPresent(Advance,3)){
				wh.clickElement(Advance);
				Thread.sleep(1000);
			}

			jd.dbDFWMSMapping();
			if(jd.envrnment.equalsIgnoreCase("LG_2012")||jd.envrnment.equalsIgnoreCase("Troy_2012")) {
				String currentUrl=dataTable.getCommonData("EnvironmentUrl").trim();

				String substring = currentUrl.substring(8, 15);

				System.out.println(substring);

				final By Unsafe=By.xpath("//p[@id='final-paragraph']//a[contains(text(),'"+substring+"')]");

				if(wh.isElementPresent(Unsafe,3)){
					wh.clickElement(Unsafe);
					Thread.sleep(1000);
				}

			}else {
				String currentUrl=dataTable.getCommonData("EnvironmentUrl").trim();

				String substring = currentUrl.substring(7, 14);

				System.out.println(substring);

				final By Unsafe=By.xpath("//p[@id='final-paragraph']//a[contains(text(),'"+substring+"')]");

				if(wh.isElementPresent(Unsafe,3)){
					wh.clickElement(Unsafe);
					Thread.sleep(1000);
				}

			}



			// driver.get(envUrl);
			rc.logger.debug("log4j");

		} catch (Exception ex) {
			rc.logger.error(ex.getMessage());
			System.out.println(ex.getMessage());
			wh.handleAlert();
		}

		driver.manage().window().maximize();
		if (wh.isElementPresent(UserName, 15)) {
		}

		if (driver.getTitle().equalsIgnoreCase(
				"Sign In | Manhattan Associates, Inc.")) {
			report.addReportStep(
					"Open Manhattan Associates, Inc login page",
					"Successfully navigated to Manhattan Associates, Inc login page",
					StepResult.PASS);
		} else {
			report.addReportStep(
					"Open Manhattan Associates, Inc page",
					"Manhattan Associates, Inc page is not  displayed successfully",
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}

	/**
	 * This function logs an user into the system. If the home page doesn't
	 * appear correctly after pushing the sign-in button, we'll try the entire
	 * login process again
	 * 
	 * @param type
	 * @throws Exception
	 **/

	public void login() throws Exception {

		Integer retryCount = 0;
		Integer numberOfAttempts = 2;
		Boolean loggedIn = false;

		// Make 2 attempts to log in
		while (retryCount < numberOfAttempts && !loggedIn) {

			retryCount++;

			System.out.println("Opening login screen: Attempt #" + retryCount);

			// Open login screen
			open();

			// Enter user name and password in form
			enterUserNameAndPassword();

			// Click sign-in button. Try again, if error occurs
			try {
				sigInBtn();

				if(wh.isElementPresent(SendAnyway,3)){
					//wh.clickElement(SendAnyway);
					Thread.sleep(3000);

					wh.jsClick(SendAnyway);
					Thread.sleep(1000);
				}

				Thread.sleep(2000);
				if(wh.isElementPresent(SelectWMS,3)){

					wh.clickElement(SelectWMS);
					Thread.sleep(1000);
					wh.clickElement(WMSOk);
					Thread.sleep(1000);
					System.out.println("Selected WMS Screen on Login Page");
					wh.waitForPageLoaded();
				}
				loggedIn = true;

				jd.dbDFWMSMapping();
				if(jd.envrnment.equalsIgnoreCase("LG_2012")||jd.envrnment.equalsIgnoreCase("Troy_2012")) {

					String text = driver.findElement(environmentText2012).getText();
					System.out.println(text);
					String[] split = text.split("- The Home Depot");
					System.out.println(split[0]);
					if(split[0].contains("MDC ")) {

						System.out.println(split[0].substring(4, 8));
						dc=split[0].substring(4, 9);

					}else if(split[0].contains("DF")) {

						System.out.println(split[0].substring(3, 8));
						dc=split[0].substring(3, 8);

					}

				}else {
					String text = driver.findElement(environmentText).getText();
					System.out.println(text);
					String[] split = text.split("- The Home Depot");
					System.out.println(split[0]);
					if(split[0].contains("MDC ")) {

						System.out.println(split[0].substring(4, 8));
						dc=split[0].substring(4, 9);

					}else if(split[0].contains("DF")) {

						System.out.println(split[0].substring(3, 8));
						dc=split[0].substring(3, 8);

					}
				}

				if(jd.envrnment.equalsIgnoreCase("Baltimore_2019") 
						|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
					port = "6000";
				}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")||jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019") 
						|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
					port = "7000";
				}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
					port = "5000";
				}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
					port = "8000";
				}
				else if(jd.envrnment.equalsIgnoreCase("Boston_2019")||jd.envrnment.equalsIgnoreCase("New_Boston_2019")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("Perris_2019")||jd.envrnment.equalsIgnoreCase("LG_2012")
						||jd.envrnment.equalsIgnoreCase("Troy_2012")){
					port = "3000";
				}else if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")) {
					port="3000";
				}
				else if(jd.envrnment.equalsIgnoreCase("HGT_2019")) {
					port="3000";
				}
				//				String text = driver.findElement(environmentText).getText();
				//				System.out.println(text);
				//				
				// loggedInAs = type;
			} catch (Exception e) {
				loggedIn = false;
				String failMsg = "Unable to log in for attempt #" + retryCount;
				report.addReportStep("Log In", failMsg, StepResult.WARNING);
				rc.logger.info(failMsg);
			}
		}

		// If still not logged in after 2 attempts, stop test
		if (!loggedIn) {
			String msg = "Unable to log in after " + numberOfAttempts
					+ " attempts";
			report.addReportStep("Log In", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}



	/**
	 * Method to enter user name and password
	 * 
	 * @throws Exception
	 */

	public void clockIn() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().defaultContent();
			wh.clickElement(Maximize);
			usernameColumn = CommonDataColumn.UIUsername;
			currentUsername = dataTable.getCommonData(usernameColumn);
			Thread.sleep(3000);
			driver.switchTo().frame(0);
			driver.findElement(clockInLoginUsername).sendKeys(currentUsername);
			driver.findElement(Submit).click();
			driver.findElement(checkUser).click();
			driver.findElement(clockIn).click();
			driver.findElement(selectDateandTime).click();
			driver.findElement(selectDateandTime).sendKeys(Keys.ENTER);
			driver.findElement(sendClockIn).click();

			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			closebtn();

			report.addReportStep("Clock in user", "Clock in LM user successfully",
					StepResult.PASS);

		}catch(Exception e){
			report.addReportStep(
					"Open Clock In page",
					"Clock In page is not  displayed ",
					StepResult.FAIL);

		}	

	}

	public void clockOut() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().defaultContent();
			wh.clickElement(Maximize);
			usernameColumn = CommonDataColumn.UIUsername;
			currentUsername = dataTable.getCommonData(usernameColumn);
			Thread.sleep(3000);
			driver.switchTo().frame(0);
			driver.findElement(clockInLoginUsername).sendKeys(currentUsername);
			driver.findElement(Submit).click();
			driver.findElement(checkUser).click();
			driver.findElement(clockOut).click();
			driver.findElement(selectDateandTime).click();
			driver.findElement(selectDateandTime).sendKeys(Keys.ENTER);
			driver.findElement(clockOut).click();

			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			closebtn();	

			report.addReportStep("Clock out user", "Clock out LM user successfully",
					StepResult.PASS);

		}

		catch(Exception e){
			report.addReportStep(
					"Open Clock In page",
					"Clock In page is not  displayed ",
					StepResult.FAIL);

		}
	}



	public void wynLogin() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(2000);
			first_tab = driver.getWindowHandle();

			System.out.println("Working on Manhattan");


			((JavascriptExecutor) driver).executeScript("window.open('"+CommonDataColumn.WynSoft+"');");

			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();
			while(i1.hasNext())
			{

				 next_tab = i1.next();
				if (!first_tab.equalsIgnoreCase(next_tab))
				{
					driver.switchTo().window(next_tab);
					System.out.println("Working on WynSoft");

					usernameColumn = CommonDataColumn.WynUIUsername;
					passwordColumn = CommonDataColumn.WynUIPassword;

					currentUsername = dataTable.getCommonData(usernameColumn).trim();
					currentPassword = dataTable.getCommonData(passwordColumn).trim();


					driver.findElement(WynUsername).sendKeys(currentUsername);
					driver.findElement(WynPaasword).sendKeys(currentPassword);
					driver.findElement(WynLoginBtn).click();

					Thread.sleep(2000);
					// closebtn();

					report.addReportStep("WynRight User ", "WynRight User logged in successfully",
							StepResult.PASS);

					// driver.close();

				}
			}

			Thread.sleep(4000);



		}catch(Exception e){
			report.addReportStep(
					"WynRight User page",
					"WynRight User page is not  displayed ",
					StepResult.FAIL);

		}	
	}

	public void packSizeLogin() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(2000);

			System.out.println("Working on Packsize");



			String parentHandle = driver.getWindowHandle(); // get the current window handle
			((JavascriptExecutor) driver).executeScript("window.open('"+CommonDataColumn.PackSize+"');");

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			usernameColumn = CommonDataColumn.PckSizeUIUsername;
			passwordColumn = CommonDataColumn.PckSizeUIPassword;

			currentUsername = dataTable.getCommonData(usernameColumn).trim();
			currentPassword = dataTable.getCommonData(passwordColumn).trim();


			driver.findElement(PckSizeUsername).sendKeys(currentUsername);
			driver.findElement(PckSizePaasword).sendKeys(currentPassword);
			driver.findElement(PckSizeLoginBtn).click();

			Thread.sleep(2000);
			// closebtn();

			report.addReportStep("Packsize User ", "Packsize User logged in successfully",
					StepResult.PASS);

			// driver.close();


			Thread.sleep(4000);



		}catch(Exception e){
			report.addReportStep(
					"Packsize User page",
					"Packsize User page is not  displayed ",
					StepResult.FAIL);

		}	
	}

	public void packSizeDallasLogin() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(2000);

			System.out.println("Working on Packsize");

			wh.waitForPageLoaded();

			String parentHandle = driver.getWindowHandle(); // get the current window handle
			((JavascriptExecutor) driver).executeScript("window.open('"+CommonDataColumn.DallasPackSize+"');");

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			usernameColumn = CommonDataColumn.PckSizeUIUsername;
			passwordColumn = CommonDataColumn.PckSizeUIPassword;

			currentUsername = dataTable.getCommonData(usernameColumn).trim();
			currentPassword = dataTable.getCommonData(passwordColumn).trim();


			driver.findElement(PckSizeUsername).sendKeys(currentUsername);
			driver.findElement(PckSizePaasword).sendKeys(currentPassword);
			driver.findElement(PckSizeLoginBtn).click();

			Thread.sleep(2000);
			// closebtn();

			report.addReportStep("Packsize User ", "Packsize User logged in successfully",
					StepResult.PASS);

			// driver.close();


			Thread.sleep(000);



		}catch(Exception e){
			report.addReportStep(
					"Packsize User page",
					"Packsize User page is not  displayed ",
					StepResult.FAIL);

		}	
	}

	public void wesLogin() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(2000);
			first_tab = driver.getWindowHandle();

			System.out.println("Working on Manhattan");

			jd.dbDFWMSMapping();

			if(jd.envrnment.equalsIgnoreCase("Dallas_2019")) {
				((JavascriptExecutor) driver).executeScript("window.open('"+CommonDataColumn.WES_url+"');");

				usernameColumn = CommonDataColumn.WESUIUsername;
				passwordColumn = CommonDataColumn.WESUIPassword;

				currentUsername = dataTable.getCommonData(usernameColumn).trim();
				currentPassword = dataTable.getCommonData(passwordColumn).trim();


			}
			else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {

				((JavascriptExecutor) driver).executeScript("window.open('"+CommonDataColumn.WES_Lacey_url+"');");

				usernameColumn = CommonDataColumn.WESLaceyUIUsername;
				passwordColumn = CommonDataColumn.WESLaceyUIPassword;

				currentUsername = dataTable.getCommonData(usernameColumn).trim();
				currentPassword = dataTable.getCommonData(passwordColumn).trim();

			}
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();
			while(i1.hasNext())
			{

				 next_tab = i1.next();
				if (!first_tab.equalsIgnoreCase(next_tab))
				{
					driver.switchTo().window(next_tab);
					System.out.println("Working on WES");


					driver.findElement(WESUsername).sendKeys(currentUsername);
					driver.findElement(WESPaasword).sendKeys(currentPassword);
					driver.findElement(WESLoginBtn).click();

					Thread.sleep(2000);
					// closebtn();

					report.addReportStep("WES User ", "WES User logged in successfully",
							StepResult.PASS);

					// driver.close();
				}
			}

			Thread.sleep(4000);



		}catch(Exception e){
			report.addReportStep(
					"WES User page",
					"WES User page is not  displayed ",
					StepResult.FAIL);

		}	
	}


	public static void enterUserNameAndPassword() throws Exception {

		try {
			usernameColumn = CommonDataColumn.UIUsername;
			passwordColumn = CommonDataColumn.UIPassword;

			currentUsername = dataTable.getCommonData(usernameColumn);
			currentPassword = dataTable.getCommonData(passwordColumn);

			if (wh.isElementPresent(UserName, 3)) {
				wh.clickElement(UserName);
				wh.sendKeys(UserName, currentUsername);
			} else {
				throw new Exception("Unable to locate username input field");
			}

			if (wh.isElementPresent(Password, 3)) {
				wh.clickElement(Password);
				wh.sendKeys(Password, currentPassword);
			} else {
				throw new Exception("Unable to locate password input field");
			}

			if (!wh.getAttribute(UserName, "value").equals("")
					&& !wh.getAttribute(Password, "value").equals("")) {
				String msg = "Sucessfully entered UserName and Password. Username: ["
						+ currentUsername + "], Password: **********";

				//rc.logger.info(msg);
				report.addReportStep("Enter UserName and Password", msg,
						StepResult.PASS);

			} else {
				throw new Exception(
						"Username and Password not entered correctly");
			}
		} catch (Exception e) {
			String msg = "Unable to fill out username and password for login. "
					+ e.getMessage();
			//rc.logger.info(msg);
			report.addReportStep("Fill out username and password for login",
					msg, StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}

	/**
	 * Method to Click Sign-in button and Validate Home Page
	 * 
	 * @throws Exception
	 **/

	public void sigInBtn() throws Exception {
		try {

			jd.dbDFWMSMapping();

			if(jd.envrnment.equalsIgnoreCase("LG_2012")||jd.envrnment.equalsIgnoreCase("Troy_2012")) {

				if (!wh.isElementPresent(SignInBtn2012, 3)) {

					throw new Exception("Unable to locate Sign-In button");
				}

			}else {
				if (!wh.isElementPresent(SignInBtn, 3)) {

					throw new Exception("Unable to locate Sign-In button");
				}

			}

			try {
				if(jd.envrnment.equalsIgnoreCase("LG_2012")||jd.envrnment.equalsIgnoreCase("Troy_2012")) {
					wh.jsClick(SignInBtn2012);

					if(wh.isElementPresent(continueBtn)) {

						wh.jsClick(continueBtn);
					}
				}else {

					wh.jsClick(SignInBtn);
				}

			} catch (Exception e) {
				throw new Exception("Unable to click Sign-In button. "
						+ e.getMessage());
			}

			// wh.waitUntilDisappear(TPEHomePageObject.LoadingPage);
			// Thread.sleep(5000);
			/*By locator = By.xpath("//span[contains(text(),'WMS')]");
			if (wh.isElementPresent(locator, 25)) {
				String msg = "Sucessfully opened the DF WMS home page";
				rc.logger.info(msg);
				report.addReportStep("Click Sign In button", msg,
						StepResult.PASS);
			} else {
				throw new Exception("User home page not displaying");
			}*/
		} catch (Exception e) {
			String msg = "Unable to log in. " + e.getMessage();
			report.addReportStep("Log In", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}
	}

	public String readProp() throws Exception {
		try {
			File file = new File("RunConfig.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				if (key.equalsIgnoreCase("Environment")) {
					envrnment = properties.getProperty(key);
					//					System.out.println(envrnment);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return envrnment;
	}


	public void RFLogin() throws Exception {

		enterUserNameAndPassword();

		try {
			wh.jsClick(RFSignInBtn);
			By locator = By.xpath(".//SPAN[@title=''][text()='Whse/BU :']");
			if (wh.isElementPresent(locator, 10)) {
				String msg = "Sucessfully opened the RF home page";
				rc.logger.info(msg);
				report.addReportStep("Click Enter button", msg, StepResult.PASS);
			} else {
				throw new Exception("RF home page not displaying");
			}
		} catch (Exception e) {
			String msg = "Unable to log in. " + e.getMessage();
			report.addReportStep("Log In", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

		//		driver.get(envUrl);
		//		waitForWindowToLoad("Add Appointment");
		//		Thread.sleep(2000);		

	}

	public void RFtoWMSHomePage() throws Exception {

		try {
			driver.get(envUrl);
			By locator = By.xpath(".//SPAN[contains(text(),'Add Appointment')]");
			if (wh.isElementPresent(locator, 15)) {
				String msg = "Sucessfully opened the WMS home page";
				rc.logger.info(msg);
				report.addReportStep("Navigate back to WMS Home Page", msg, StepResult.PASS);
			} else {
				throw new Exception("WMS home page not displaying");
			}
		} catch (Exception e) {
			String msg = "Unable to navigate to WMS Home Page. " + e.getMessage();
			report.addReportStep("WMS Home Page", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}
	}

	public void loginWynsoft() throws Exception {
		Integer retryCount = 0;
		Integer numberOfAttempts = 2;
		Boolean loggedIn = false;

		// Make 2 attempts to log in
		while (retryCount < numberOfAttempts && !loggedIn) {
			retryCount++;
			System.out.println("Opening login screen: Attempt #" + retryCount);
			try {
				driver.manage().window().maximize();
				driver.get(CommonDataColumn.WynSoft);
				driver.switchTo().alert().accept();

				Thread.sleep(3000);
				usernameColumn = CommonDataColumn.WynUIUsername;
				passwordColumn = CommonDataColumn.WynUIPassword;
				currentUsername = dataTable.getCommonData(usernameColumn);
				currentPassword = dataTable.getCommonData(passwordColumn);

				if (wh.isElementPresent(WynUsername, 3)) {
					wh.clickElement(WynUsername);
					wh.sendKeys(WynUsername, currentUsername);
				} else {
					throw new Exception("Unable to locate username input field");
				}

				if (wh.isElementPresent(WynPaasword, 3)) {
					wh.clickElement(WynPaasword);
					wh.sendKeys(WynPaasword, currentPassword);
				} else {
					throw new Exception("Unable to locate password input field");
				}

				if (wh.isElementPresent(WynLoginBtn, 3)) {
					wh.clickElement(WynLoginBtn);
				} else {
					throw new Exception("Unable to locate Sign In Vutton");
				}

				if(wh.isElementPresent(WynsoftError, 5)){
					String msg = wh.getText(WynsoftError);
					if(msg.contains("Invalid password.")){
						loggedIn = false;
						throw new Exception(msg);
					}
				}else{
					loggedIn = true;
				}
			} catch (Exception e) {
				loggedIn = false;
				String failMsg = "Unable to log in for attempt #" + retryCount;
				report.addReportStep("Log In", failMsg, StepResult.WARNING);
				rc.logger.info(failMsg);
			}
		}

		// If still not logged in after 2 attempts, stop test
		if (!loggedIn) {
			String msg = "Unable to log in after " + numberOfAttempts + " attempts";
			report.addReportStep("Log In", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}
	}
}