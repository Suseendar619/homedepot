package com.homer.po.DFWMS;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class DFWMSHomePageObject extends PageBase {

	public DFWMSHomePageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By MenuBtn = By
			.xpath(".//SPAN[@class='menulbl -hft_pht'][text()='enu']");
	static final By SearchInput = By.xpath(".//INPUT[contains(@id, 'as_bas')]");
	static final By HomePage=By.xpath(".//IMG[@src='/lps/resources/themes/icons/mablue/home.gif']");

	static final String PageTitle = "Add Appointment | Manhattan Associates";

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

		if(!driver.getTitle().equals(PageTitle)){
			wh.clickElement(HomePage);
			waitForWindowToLoad("Add Appointment");
		}		
		//		if (driver.getTitle().equalsIgnoreCase(
		//				"Add Appointment | Manhattan Associates")) {
		//			report.addReportStep("DF WMS Add Appointment Home Page",
		//					"Successful", StepResult.PASS);
		//		} else {
		//			report.addReportStep("DF WMS Add Appointment Home Page", "Failed",
		//					StepResult.FAIL);
		//			rc.throwTCTerminationException();
		//		}
	}

	public void WMSmenu() throws Exception {

		WMSpage();			
		driver.switchTo().defaultContent();
		// Wait for Main Menu button to appear
		// Click main menu button
		// Wait for Menu frame to appear

		try {
			if (wh.isElementPresent(MenuBtn, 8)) {
				wh.clickElement(MenuBtn);
				//				report.addReportStep("Click menu button",
				//						"Clicked Menu Button. ", StepResult.PASS);
				if (wh.isElementPresent(SearchInput, 6)) {
					//					report.addReportStep("Menu frame should appear",
					//							"Menu frame appeared", StepResult.PASS);
				} else {
					report.addReportStep("Menu frame should appear",
							"Menu frame not appeared", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			} else {
				report.addReportStep("Locate Main Menu button",
						"Unable to locate Main Menu button.  ", StepResult.FAIL);
				rc.throwTCTerminationException();
			}
		} catch (Exception e) {
			report.addReportStep("Open Main menu",
					"Unable to open the Main Menu. " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	/**
	 * This function opens the page that you designate (i.e. "Post Message",
	 * "Purchase Orders", etc) in the main Search Bar.
	 * 
	 * @param screenOption
	 *            - Name of the page that you want to bring up
	 * @param moduleIndex
	 *            - Specific type of page (i.e. "Integration" or "Technical" for
	 *            "Post Message" page)
	 * 
	 * @throws Exception
	 */

	public void searchInput(String screenOption, String moduleOption)
			throws Exception {
		try {
			// Type input into search bar
			if (wh.isElementPresent(SearchInput,5)) {
				wh.sendKeys(SearchInput, screenOption.toString().trim());
			} else {
				throw new Exception("Unable to locate search bar element. "
						+ "XPath used is: " + SearchInput.toString());
			}

			// Validate that data was input
			if (wh.getAttribute(SearchInput, "value").equalsIgnoreCase(
					screenOption)) {
				report.addReportStep("Enter " + screenOption
						+ " in menu search", "Successfully entered "
								+ screenOption + " in menu search", StepResult.PASS);
			} else {
				report.addReportStep("Enter " + screenOption
						+ " in menu search",
						"Not entered search criteria in menu search",
						StepResult.FAIL);
				rc.throwTCTerminationException();
			}

			try {
				By locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
				switch (screenOption){
				case "Waves":
					DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
					String envrnment = s.readProp();
					//					System.out.println(envrnment);
					if(envrnment.equalsIgnoreCase("Perris_New")){
						locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
					}
					else{
						locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");
					}
					break;
				case "Users":
					locator1 = By.xpath("//*[@id='as_bas1_dd_1_li']/div/a");					
					break;
				default:  
					locator1 = By.xpath("//*[@id='as_bas1_dd_0_li']/div/a");
					break;
				}

				Thread.sleep(1000);

				if (wh.isElementPresent(locator1,5)) {
					driver.findElement(locator1).click();
					report.addReportStep("Click button to load " + screenOption
							+ " window. ", "Clicked button to load "
									+ screenOption + " window", StepResult.PASS);
				}

			} catch (Exception e) {
				report.addReportStep("Click button to load " + screenOption
						+ " window. ", "Unable to click button to load "
								+ screenOption + " window" + e.getMessage(),
								StepResult.FAIL);
				rc.throwTCTerminationException();
			}

			// Wait for window to load
			//if(!screenOption.equalsIgnoreCase("RF Menu")) waitForWindowToLoad(screenOption);

		} catch (Exception e) {
			report.addReportStep(
					"Bring up " + screenOption + " window. ",
					"Unable to bring up " + screenOption + " window "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

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