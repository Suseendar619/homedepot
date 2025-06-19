package com.homer.po.DFWMS;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;



public class DFWMSHomePageObject2019 extends PageBase {

	public DFWMSHomePageObject2019(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By MenuBtn = By
			.xpath(".//SPAN[@class='menulbl -hft_pht'][text()='enu']");
	static final By SearchInput = By.xpath(".//INPUT[contains(@id, 'as_bas')]");
	static final By HomePage=By.xpath(".//IMG[@src='/lps/resources/themes/icons/mablue/home.gif']");

	static final String PageTitle = "Manhattan Associates";

	static final By UserID = By.xpath(".//INPUT[@id='dataForm:listView:filterIdUser:field0value1']");
	static final By UsersApply = By.xpath("//INPUT[@id='dataForm:listView:filterIdUser:filterIdUserapply']");
	static final By UserCheckBox = By.xpath("//INPUT[@id='checkAll_c0_dataForm:listView:usertable']");
	static final By UserCopy = By.xpath("//INPUT[@id='dataForm:UserList_Button_Panel_Copy']");

	static final By UserFirstName = By.xpath("//INPUT[@id='dataForm:user_dtl_in_1']");
	static final By UserLastName = By.xpath("//INPUT[@id='dataForm:user_dtl_in_3']");
	static final By UserLoginID = By.xpath("//INPUT[@id='dataForm:user_dtl_in_13']");
	static final By UserPassword = By.xpath("//INPUT[@id='dataForm:user_dtl_in_28']");
	static final By UserConfirmPassword = By.xpath("//INPUT[@id='dataForm:user_dtl_in_29']");
	static final By UserEmail = By.xpath("//INPUT[@id='dataForm:user_dtl_in_9']");
	static final By UserSave = By.xpath("//input[@id='dataForm:UserDetail_Button_Panel_Save']");
	static final By UserSaveConfirmationClose = By.xpath("//IMG[@src='/lps/resources/common/images/close.gif']");
	static final By SignoutButton=By.xpath(".//*[@class='phlink -hft_pht']");
	static final By SignoutButtonOK=By.xpath(".//*[@id='SignoutOK']");

	//2019
	public static final By Menuclick =By.xpath(".//span[contains(@class,'wt-topbar-menu-icon') and contains(@id, 'button')]");
	public static final By Menuclick2012 =By.xpath("//a[@id='phMenu']//span[2]");
	public static final By Menusearchbar = By.xpath(".//input[contains(@id,'mps_menusearch')]");
	public static final By Menusearchbar2012 = By.xpath(".//input[@id='as_bas1_in']");
	public static final By Menusearchbar2012_ = By.xpath(".//input[@id='as_bas5_in']");
	public static final By Menusearchbar2012_2= By.xpath(".//input[@id='as_bas2_in']");
	public static final By HomepGE = By.xpath(".//span[contains(@class,'x-btn-icon-el-default-toolbar-medium wt-home') and contains(@id, 'btnIconEl')]");


	public static enum SearchOptions {
		POSTMESSAGE("Post Message"), PURCHASEORDERS("Purchase Orders"), DISTRIBUTIONORDERS(
				"Distribution Orders"), SHIPMENTS("Shipments"), WEBTENDERS(
						"Web Tenders"), RUNTEMPLATES("Run Templates");

		private String value;

		SearchOptions(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static enum BoundListOptions {
		TECHNICAL("Technical"), TLM("Transportation Lifecycle Management"), INTEGRATION(
				"Integration"), BUSINESSRULES("Business Rules"), LOGISTICSGATEWAY(
						"Logistics Gateway"), CONTRACTMANAGEMENT("ContractManagement"), SETUP(
								"Setup"), ADMINISTRATION("Administration"), SCOPESTUDIO(
										"Scope Studio Administration"), YARDMANAGEMENT(
												"Yard Management"), TRANSPORTEXECUTION(
														"Transportation Execution");

		private String value;

		BoundListOptions(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/**
	 * Method to validate WMS Menu and click on Menu button
	 * 
	 * @throws Exception
	 */
	public void WMSpage() throws Exception {

        wh.clickElement(HomepGE);
        Thread.sleep(5000);
        if(driver.getTitle().equals(PageTitle)){
		    report.addReportStep("WMS Home Page Load"," WMS Home Page Loaded Successfully", StepResult.PASS);
		}

				 else {
					report.addReportStep("DF WMS  Home Page Load", "Failed",
							StepResult.FAIL);
					rc.throwTCTerminationException();
				}
	}




	public void WMSmenu() throws Exception {

		WMSpage();			
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// Wait for Main Menu button to appear
		// Click main menu button
		// Wait for Menu frame to appear

		try {
			if (wh.isElementPresent(Menuclick, 8)) {
				wh.clickElement(Menuclick);
				Thread.sleep(1000);
				if(wh.isElementPresent(Menusearchbar, 6)) {
				} else {
					report.addReportStep("Menu frame should appear", "Menu frame not appeared", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			} else {
				report.addReportStep("Locate Main Menu button","Unable to locate Main Menu button.  ", StepResult.FAIL);
				rc.throwTCTerminationException();
			}
		} catch (Exception e) {
			report.addReportStep("Open Main menu", "Unable to open the Main Menu. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	public void WMSmenu2012() throws Exception {

		//WMSpage();
		Thread.sleep(1000);

		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// Wait for Main Menu button to appear
		// Click main menu button
		// Wait for Menu frame to appear

		try {
			if (wh.isElementPresent(Menuclick2012, 8)) {
				wh.clickElement(Menuclick2012);
				Thread.sleep(1000);
				if(wh.isElementPresent(Menusearchbar2012, 6)) {
				} else if (wh.isElementPresent(Menusearchbar2012_, 6)){
					
				}else if (wh.isElementPresent(Menusearchbar2012_2, 6)){
					
				}else {
					report.addReportStep("Menu frame should appear", "Menu frame not appeared", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			} else {
				report.addReportStep("Locate Main Menu button","Unable to locate Main Menu button.  ", StepResult.FAIL);
				rc.throwTCTerminationException();
			}
		} catch (Exception e) {
			report.addReportStep("Open Main menu", "Unable to open the Main Menu. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}


	/**
	 * This function opens the page that you designate (i.e. "Post Message",
	 * "Purchase Orders", etc) in the main Search Bar.
	 * 
	 * @param screenOption
	 *            - Name of the page that you want to bring up
//	 * @param moduleIndex
	 *            - Specific type of page (i.e. "Integration" or "Technical" for
	 *            "Post Message" page)
	 * 
	 * @throws Exception
	 */

	public void searchInput(String screenOption, String moduleOption) throws Exception {
		try {
			// Type input into search bar
			if (wh.isElementPresent(Menusearchbar,5)) {

				if(screenOption.equalsIgnoreCase("PIX Transactions")){
					wh.sendKeys(Menusearchbar, screenOption.toString().trim());
					Thread.sleep(1000);
					wh.clickElement(By.xpath("(.//li[contains(@class,'boundlist-item') and contains(@role,'option')])[2]"));
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				}else if(screenOption.equalsIgnoreCase("Waves")){
					wh.sendKeys(Menusearchbar, screenOption.toString().trim());
					Thread.sleep(1000);
					wh.clickElement(By.xpath("(.//li[contains(@class,'boundlist-item') and contains(@role,'option')])[5]"));
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				}else if(screenOption.equalsIgnoreCase("oLPNS")){
					wh.sendKeys(Menusearchbar, screenOption.toString().trim());
					Thread.sleep(1000);
					wh.clickElement(By.xpath("(.//li[contains(@class,'boundlist-item') and contains(@role,'option')])[2]"));
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				}else if(screenOption.equalsIgnoreCase("Transactions")){
					wh.sendKeys(Menusearchbar, screenOption.toString().trim());
					Thread.sleep(1000);
					wh.clickElement(By.xpath("(//li//div//b[text()='Transactions'])[1]"));
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(2000);
				}else{
					wh.sendKeys(Menusearchbar, screenOption.toString().trim());
//					wh.sendKeys(Menusearchbar, "ASNs");
					Thread.sleep(1000);
					driver.findElement(Menusearchbar).sendKeys(Keys.RETURN);
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				}
				report.addReportStepWithScreenshots("Sucessfully Opened ", screenOption +" screen under "+moduleOption, StepResult.PASS);
			} else {
				throw new Exception("Unable to locate search bar element. "
						+ "XPath used is: " + Menusearchbar.toString());
			}

			// Validate that data was input
//			if (wh.getAttribute(Menusearchbar, "value").equalsIgnoreCase(
//					screenOption)) {
//				report.addReportStep("Enter " + screenOption
//						+ " in menu search", "Successfully entered "
//								+ screenOption + " in menu search", StepResult.PASS);
//			} else {
//				report.addReportStep("Enter " + screenOption
//						+ " in menu search",
//						"Not entered search criteria in menu search",
//						StepResult.FAIL);
//				rc.throwTCTerminationException();
//			}

//			try {
////				By locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//				By locator1 = By.xpath(".//div[contains(@id,'boundlist')]/ul/li/div" + "[contains(text(),'("+moduleOption+")')]");
//				switch (screenOption){
//				case "Waves":
//					DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
//					String envrnment = s.readProp();
//					//					System.out.println(envrnment);
//					if(envrnment.equalsIgnoreCase("Perris_New")){
//						locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//					}
//					else{
//						//locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");
//						driver.findElement(locator1).click();
//					}
//					break;
//				case "Users":
////					locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");
//					driver.findElement(locator1).click();
//					break;
//				default:
////					locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//					driver.findElement(locator1).click();
//					break;
//				}
//
//				Thread.sleep(1000);
//
//				if (wh.isElementPresent(locator1,5)) {
//					driver.findElement(locator1).click();
//					report.addReportStep("Click button to load " + screenOption
//							+ " window. ", "Clicked button to load "
//									+ screenOption + " window", StepResult.PASS);
//				}
//
//			} catch (Exception e) {
//				report.addReportStep("Click button to load " + screenOption
//						+ " window. ", "Unable to click button to load "
//								+ screenOption + " window" + e.getMessage(),
//								StepResult.FAIL);
//				rc.throwTCTerminationException();
//			}

			// Wait for window to load
			//if(!screenOption.equalsIgnoreCase("RF Menu")) waitForWindowToLoad(screenOption);
			//driver.switchTo().frame(0);
		} catch (Exception e) {
			report.addReportStep(
					"Bring up " + screenOption + " window. ",
					"Unable to bring up " + screenOption + " window "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
//		System.out.println("Purchase order Screen opened");

	}
	
	public void searchInput2012(String screenOption, String moduleOption) throws Exception {
		try {
			// Type input into search bar
			if (wh.isElementPresent(Menusearchbar2012,5)) {

				 if(screenOption.equalsIgnoreCase("oLPNS")){
					wh.sendKeys(Menusearchbar2012, screenOption.toString().trim());
					Thread.sleep(1000);
					wh.clickElement(By.xpath("(.//li[contains(@class,'boundlist-item') and contains(@role,'option')])[2]"));
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				} if(moduleOption.equalsIgnoreCase("Distribution2012")){
					wh.sendKeys(Menusearchbar2012, screenOption.toString().trim());
//					wh.sendKeys(Menusearchbar, "ASNs");
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//div//a[text()='Waves (Distribution)'])[1]")).click();
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@alt='Find Ship Wave number']")).sendKeys(DFWMSRunWavesPageObject.sWaveNumber);
					driver.findElement(By.xpath("//input[@id='dataForm:listView:filterId:filterIdapply']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@value='0']")).click();
					Thread.sleep(2000);
					//driver.findElement(By.xpath("//a//img[@alt='Home Page']")).click();

				}else{
					wh.sendKeys(Menusearchbar2012, screenOption.toString().trim());
//					wh.sendKeys(Menusearchbar, "ASNs");
					Thread.sleep(1000);
					driver.findElement(Menusearchbar2012).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
					System.out.println(screenOption + "Screen Opened Successfully");
					Thread.sleep(1000);
				}
				report.addReportStepWithScreenshots("Sucessfully Opened ", screenOption +" screen under "+moduleOption, StepResult.PASS);
			}else if(wh.isElementPresent(Menusearchbar2012_)) {
				
				wh.sendKeys(Menusearchbar2012_, screenOption.toString().trim());
//				wh.sendKeys(Menusearchbar, "ASNs");
				Thread.sleep(1000);
				driver.findElement(Menusearchbar2012_).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				System.out.println(screenOption + "Screen Opened Successfully");
				Thread.sleep(1000);
				report.addReportStepWithScreenshots("Sucessfully Opened ", screenOption +" screen under "+moduleOption, StepResult.PASS);
			}else if(wh.isElementPresent(Menusearchbar2012_2)) {
				
				wh.sendKeys(Menusearchbar2012_2, screenOption.toString().trim());
//				wh.sendKeys(Menusearchbar, "ASNs");
				Thread.sleep(1000);
				driver.findElement(Menusearchbar2012_2).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				System.out.println(screenOption + "Screen Opened Successfully");
				Thread.sleep(1000);
				report.addReportStepWithScreenshots("Sucessfully Opened ", screenOption +" screen under "+moduleOption, StepResult.PASS);
			}else {
				throw new Exception("Unable to locate search bar element. "
						+ "XPath used is: " + Menusearchbar2012.toString());
			}

			// Validate that data was input
//			if (wh.getAttribute(Menusearchbar, "value").equalsIgnoreCase(
//					screenOption)) {
//				report.addReportStep("Enter " + screenOption
//						+ " in menu search", "Successfully entered "
//								+ screenOption + " in menu search", StepResult.PASS);
//			} else {
//				report.addReportStep("Enter " + screenOption
//						+ " in menu search",
//						"Not entered search criteria in menu search",
//						StepResult.FAIL);
//				rc.throwTCTerminationException();
//			}

//			try {
////				By locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//				By locator1 = By.xpath(".//div[contains(@id,'boundlist')]/ul/li/div" + "[contains(text(),'("+moduleOption+")')]");
//				switch (screenOption){
//				case "Waves":
//					DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
//					String envrnment = s.readProp();
//					//					System.out.println(envrnment);
//					if(envrnment.equalsIgnoreCase("Perris_New")){
//						locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//					}
//					else{
//						//locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");
//						driver.findElement(locator1).click();
//					}
//					break;
//				case "Users":
////					locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");
//					driver.findElement(locator1).click();
//					break;
//				default:
////					locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
//					driver.findElement(locator1).click();
//					break;
//				}
//
//				Thread.sleep(1000);
//
//				if (wh.isElementPresent(locator1,5)) {
//					driver.findElement(locator1).click();
//					report.addReportStep("Click button to load " + screenOption
//							+ " window. ", "Clicked button to load "
//									+ screenOption + " window", StepResult.PASS);
//				}
//
//			} catch (Exception e) {
//				report.addReportStep("Click button to load " + screenOption
//						+ " window. ", "Unable to click button to load "
//								+ screenOption + " window" + e.getMessage(),
//								StepResult.FAIL);
//				rc.throwTCTerminationException();
//			}

			// Wait for window to load
			//if(!screenOption.equalsIgnoreCase("RF Menu")) waitForWindowToLoad(screenOption);
			//driver.switchTo().frame(0);
		} catch (Exception e) {
			report.addReportStep(
					"Bring up " + screenOption + " window. ",
					"Unable to bring up " + screenOption + " window "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
//		System.out.println("Purchase order Screen opened");

	}


	public void QPUserCopyCreation() throws Exception {

		if (wh.isElementPresent(UserID, 5)) {
			wh.sendKeys(UserID, "QPTEST030");
			if (wh.isElementPresent(UserID, 5)) {
				wh.clickElement(UsersApply);
			}
			report.addReportStep("Click Apply button","Clicked Apply Button. ", StepResult.PASS);
			if (wh.isElementPresent(UserCheckBox, 6)) {
				wh.clickElement(UserCheckBox);
			} else {
				//			report.addReportStep("Menu frame should appear",
				//					"Menu frame not appeared", StepResult.FAIL);
				//			rc.throwTCTerminationException();
			}

		}

		int iUserNumber = 100;

		while(iUserNumber<271){

			try {
				if (wh.isElementPresent(UserID, 5)) {
					if (wh.isElementPresent(UserCopy, 6)) {
						wh.clickElement(UserCopy);
						report.addReportStep("Click Copy button","Clicked Copy Button. ", StepResult.PASS);
						Thread.sleep(3000);
					} else {
						report.addReportStep("Menu frame should appear",
								"Menu frame not appeared", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
					if (wh.isElementPresent(UserFirstName, 6)) {
						String sUserName = "QPTEST"+iUserNumber;
						wh.sendKeys(UserFirstName, sUserName);
						wh.sendKeys(UserLastName, sUserName);
						wh.sendKeys(UserEmail, sUserName+"@Homedepot.com");
						wh.sendKeys(UserLoginID, sUserName);
						wh.sendKeys(UserPassword, sUserName);
						wh.sendKeys(UserConfirmPassword, sUserName);
						Thread.sleep(1000);
						wh.clickElement(UserSave);
						wh.isElementPresent(UserSaveConfirmationClose, 6);
						wh.clickElement(UserSaveConfirmationClose);
						Thread.sleep(2000);
						report.addReportStep("Click Save button","Clicked Save Button. User Created Sucessfully "+sUserName, StepResult.PASS);
					} else {
						report.addReportStep("User Should be Saved Successfully",
								"Unable to add Users", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				} else {
					report.addReportStep("Locate User ID Input",
							"Unable to locate User ID Input.  ", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			} catch (Exception e) {
				report.addReportStep("Open Users menu",
						"Unable to open the Users Menu. " + e.getMessage(),
						StepResult.FAIL);
				rc.throwTCTerminationException();
			}

			iUserNumber++;

		}
	}

	public void logout() throws Exception{

		try{
			driver.switchTo().defaultContent();
			wh.clickElement(SignoutButton);

			String msg = "Clicked Sign Out button";
			report.addReportStep("Sign out", 
					msg, StepResult.PASS);
			rc.logger.info(msg);

			if(wh.isElementPresent(SignoutButtonOK, 10)){
				wh.clickElement(SignoutButtonOK);
				System.out.println(System.getProperty("user.home"));
			}

			try{
				WebDriverWait wait = new WebDriverWait(driver, 20);
//				wait.until(ExpectedConditions.titleContains("Sign In"));
				msg = "User Sucessfully logged out.";
				report.addReportStep("Sign out OK", 
						msg, StepResult.PASS);
				rc.logger.info(msg);

				driver.manage().deleteAllCookies();


			}catch(TimeoutException te){
				throw new Exception("Could not find the redirected 'Sign In' page. " + te.getMessage());
			}
		}catch(Exception e){
			String errorMsg = "User did not log out. " + e.getMessage();
			report.addReportStep("Log out", errorMsg
					, StepResult.FAIL);
			rc.logger.info(errorMsg);
			rc.throwTCTerminationException();
		}

	}

}