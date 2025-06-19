package com.homer.po.DFWMS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.ReusableComponents;

public class TPEHomePageObject extends PageBase{

	public TPEHomePageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By Tpepage=By.xpath(".//span[contains(@id,'mps_solutions_menu')]");
	static final By VendorPortalLink= By.xpath("//span[contains(@class,'x-menu-item') and text()='Vendor Portal']");
	static final By MDAPortalLink= By.xpath("//span[contains(@class,'x-menu-item') and text()='MDA']");
	static final By CarrierPortalLink= By.xpath("//span[contains(@class,'x-menu-item') and text()='Logistics Gateway']");
	static final By LoadingPage=By.className("x-mask-msg-text");
	static final By MenuBtn=By.xpath(".//span[contains(@class,'wt-topbar-menu-icon') and contains(@id, 'button')]");
	static final By SearchInput=By.xpath(".//input[contains(@id,'mps_menusearch')]");
	static final By LoadingFrame=By.xpath(".//div[contains(@id,'loadmask')]");
	static final By MaximizeScreen=By.xpath(".//img[contains(@class,'x-tool-maximize')]");
	static final By ScreenHeader=By.xpath(".//span[contains(@id,'header_hd-textEl')]");
	static final By CloseScreen=By.xpath(".//img[@class='x-tool-img x-tool-close']");
	static final By ProfileButton=By.xpath("//*[contains(@class, 'wt-user')]");
//	static final By SignoutButton=By.xpath(".//*[text()='Sign out']");
	static final By SignoutButton=By.xpath(".//*[@class='phlink -hft_pht']");
	static final By SignoutButtonOK=By.xpath(".//*[@id='SignoutOK']");
	
	public static enum SearchOptions{
		POSTMESSAGE ("Post Message"),
		PURCHASEORDERS("Purchase Orders"),
		DISTRIBUTIONORDERS("Distribution Orders"),
		SHIPMENTS("Shipments"),
		WEBTENDERS("Web Tenders"),
		RUNTEMPLATES("Run Templates");
		
		private String value;
		
		SearchOptions(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public static enum BoundListOptions{
		TECHNICAL("Technical"),
		TLM ("Transportation Lifecycle Management"),
		INTEGRATION("Integration"),
		BUSINESSRULES("Business Rules"),
		LOGISTICSGATEWAY("Logistics Gateway"),
		CONTRACTMANAGEMENT("ContractManagement"),
		SETUP("Setup"),
		ADMINISTRATION("Administration"),
		SCOPESTUDIO("Scope Studio Administration"),
		YARDMANAGEMENT("Yard Management"),
		TRANSPORTEXECUTION("Transportation Execution");
		
		private String value;
		
		BoundListOptions(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
		static final String PageTitle = "Manhattan Associates";
	
	private String windowHeader= "//*[contains(@class, 'x-window-header') and text()='<WINDOWHEADER>'"
			+ " and not(contains(@id, 'ghost'))]";
	
	private String filterDetailHeader = "//*[contains(@id, 'filterdetail') "
			+ "and text()= '<FILTERDETAILHEADER>' and ancestor::*[contains(@class, 'x-window') "
			+ "and descendant::*[text()='<WINDOWHEADER>']]]";

	private By getWindowHeaderLocation(String headerTitle){
		windowHeader = windowHeader.replace("<WINDOWHEADER>", headerTitle);
		By location = By.xpath(windowHeader);

		return location;
	}
	
	public By getPurchaseOrderWindowHeaderLocation(){
		return getWindowHeaderLocation("Purchase Orders");
	}
	
	public By getViewDetailsHeaderForPurchaseOrderWindow(){
		return getFilterDetailPanelHeaderLocation("Purchase Orders", "View Details");
	}
	
	 /**
	 * Method to validate TPE home page
	 * @throws Exception
	 */
	public void TPEpage() throws Exception{
		wh.waitUntilDisappear(LoadingPage);
		if(wh.isElementPresent(Tpepage, 8)){			
			if(wh.getText(Tpepage).equalsIgnoreCase("Transportation Planning and Execution")){
				report.addReportStep("Transportation Planning and Execution page should be available", 
						"Transportation Planning and Execution page is available", StepResult.PASS);
			}else{
				report.addReportStep("Transportation Planning and Execution page should be available", 
						"Transportation Planning and Execution page is not available", StepResult.FAIL);
			}
		}
	}
	
	public void navigateToVendorPortal() throws Exception{
		try{
			
			//Click TPE portal at the top
			if(wh.isElementPresent(Tpepage, 5)){
				wh.clickElement(Tpepage);
				 report.addReportStep("Click Portal drop-down list at top of page",
						 "Clicked Portal drop-down list at top of page", StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Portal drop-down element");
			}
			
			// Click vendor portal link
			if(wh.isElementPresent(VendorPortalLink, 5)){
				wh.clickElement(VendorPortalLink);
				report.addReportStep("Click 'Vendor Portal' link",
						"Clicked 'Vendor Portal' link",
						StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Vendor Portal link");
			}
			
			// Switch to 2nd tab
			Thread.sleep(2000);
			driver.close();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(tabs.size()-1));
		    

		    // Wait for page to load
		    Integer loadTime = 45;
		    if(wh.isElementPresent(Tpepage, loadTime)){
		    	report.addReportStep("Wait for Vendor Portal page to load",
		    			"Vendor Poral loaded",
		    			StepResult.PASS);
		    }else{
		    	throw new Exception ("Vendor Portal page not loaded in " + loadTime + "seconds");
		    }
		    
			String actualHeaderText = driver.findElement(Tpepage).getText();
			if (wh.isElementPresent(Tpepage) && actualHeaderText.equals("Vendor Portal")){
				String msg = "Navigated to the vendor portal";
				rc.logger.debug(msg);
				report.addReportStep("Navigate to the vendor portal", 
						msg, StepResult.PASS);
				wh.waitUntilDisappear(LoadingFrame);
			}else{
				throw new Exception ("Vendor Portal header is not displayed");
			}
			
		}catch(Exception e){
			String msg = "Unable to navigate to the vendor portal. " + e.getMessage();
			report.addReportStep("Navigate to the vendor portal", 
					msg, StepResult.FAIL);
			rc.logger.debug(msg);
			rc.throwTCTerminationException();
		}
			
	}
	public void postUsers() throws Exception{
		TPEHomePageObject n = new TPEHomePageObject(ic);
		n.TPEmenu();		
		n.searchInput("Post Message","Integration");
		TPEPostMessagePageObject s = new TPEPostMessagePageObject(ic);
		s.maximizeScreen();
		s.switchToPostMessageFrame();
		s.UserLsample();
		
	}
	public void navigateToMDA() throws Exception{
		try{
			
			//Click TPE portal at the top
			if(wh.isElementPresent(Tpepage, 5)){
				wh.clickElement(Tpepage);
				 report.addReportStep("Click Portal drop-down list at top of page",
						 "Clicked Portal drop-down list at top of page", StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Portal drop-down element");
			}
			
			// Click vendor portal link
			if(wh.isElementPresent(MDAPortalLink, 5)){
				wh.clickElement(MDAPortalLink);
				report.addReportStep("Click 'Vendor Portal' link",
						"Clicked 'Vendor Portal' link",
						StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Vendor Portal link");
			}
			
			// Switch to 2nd tab
			Thread.sleep(2000);
			driver.close();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(tabs.size()-1));
		    

		    // Wait for page to load
		    Integer loadTime = 45;
		    if(wh.isElementPresent(Tpepage, loadTime)){
		    	report.addReportStep("Wait for Vendor Portal page to load",
		    			"Vendor Poral loaded",
		    			StepResult.PASS);
		    }else{
		    	throw new Exception ("Vendor Portal page not loaded in " + loadTime + "seconds");
		    }
		    
			String actualHeaderText = driver.findElement(Tpepage).getText();
			if (wh.isElementPresent(Tpepage) && actualHeaderText.equals("MDA")){
				String msg = "Navigated to the vendor portal";
				rc.logger.debug(msg);
				report.addReportStep("Navigate to the vendor portal", 
						msg, StepResult.PASS);
				wh.waitUntilDisappear(LoadingFrame);
			}else{
				throw new Exception ("Vendor Portal header is not displayed");
			}
			
		}catch(Exception e){
			String msg = "Unable to navigate to the vendor portal. " + e.getMessage();
			report.addReportStep("Navigate to the vendor portal", 
					msg, StepResult.FAIL);
			rc.logger.debug(msg);
			rc.throwTCTerminationException();
		}
		
		postUsers();
			
	}
	public void navigateToCarrierPortal() throws Exception{
		try{
			
			//Click TPE portal at the top
			if(wh.isElementPresent(Tpepage, 5)){
				wh.clickElement(Tpepage);
				 report.addReportStep("Click Portal drop-down list at top of page",
						 "Clicked Portal drop-down list at top of page", StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Portal drop-down element");
			}
			
			// Click vendor portal link
			if(wh.isElementPresent(CarrierPortalLink, 5)){
				wh.clickElement(CarrierPortalLink);
				report.addReportStep("Click 'Vendor Portal' link",
						"Clicked 'Vendor Portal' link",
						StepResult.PASS);
			}else{
				throw new Exception ("Unable to locate Vendor Portal link");
			}
			
			// Switch to 2nd tab
			Thread.sleep(2000);
			driver.close();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(tabs.size()-1));
		    

		    // Wait for page to load
		    Integer loadTime = 45;
		    if(wh.isElementPresent(Tpepage, loadTime)){
		    	report.addReportStep("Wait for Vendor Portal page to load",
		    			"Vendor Poral loaded",
		    			StepResult.PASS);
		    }else{
		    	throw new Exception ("Vendor Portal page not loaded in " + loadTime + "seconds");
		    }
		    
			String actualHeaderText = driver.findElement(Tpepage).getText();
			if (wh.isElementPresent(Tpepage) && actualHeaderText.equals("Logistics Gateway")){
				String msg = "Navigated to the vendor portal";
				rc.logger.debug(msg);
				report.addReportStep("Navigate to the vendor portal", 
						msg, StepResult.PASS);
				wh.waitUntilDisappear(LoadingFrame);
			}else{
				throw new Exception ("Vendor Portal header is not displayed");
			}
			
		}catch(Exception e){
			String msg = "Unable to navigate to the vendor portal. " + e.getMessage();
			report.addReportStep("Navigate to the vendor portal", 
					msg, StepResult.FAIL);
			rc.logger.debug(msg);
			rc.throwTCTerminationException();
		}
			
	}
	 /**
	 * Method to validate TPE Menu and click on Menu button
	 * @throws Exception
	 */
	public void TPEmenu() throws Exception{					 
		driver.switchTo().defaultContent();

		// Wait for Main Menu button to appear
		// Click main menu button
		// Wait for Menu frame to appear
		
		try{
		if(wh.isElementPresent(MenuBtn, 8)){
			wh.clickElement(MenuBtn);
				report.addReportStep("Click menu button", 
						"Clicked Menu Button. ", StepResult.PASS);
			if(wh.isElementPresent(SearchInput, 6)){
				report.addReportStep("Menu frame should appear", 
						"Menu frame appeared", StepResult.PASS);
			}else{
				report.addReportStep("Menu frame should appear", 
						"Menu frame not appeared", StepResult.FAIL);
					rc.throwTCTerminationException();
			}
		}else{
				report.addReportStep("Locate Main Menu button", 
						"Unable to locate Main Menu button.  ", StepResult.FAIL);
				rc.throwTCTerminationException();
		}
		}catch(Exception e){
			report.addReportStep("Open Main menu", 
					"Unable to open the Main Menu. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
	}
	}
	/**
	 * This function opens the page that you designate (i.e. "Post Message", 
	 * "Purchase Orders", etc) in the main Search Bar.
	 * 
	 * @param screenOption - Name of the page that you want to bring up
	 * @param moduleIndex - Specific type of page (i.e. "Integration" or "Technical"
	 * for "Post Message" page)
	 * 
	 * @throws Exception
	 */

	public void searchInput(String screenOption, String moduleIndex) throws Exception{	

		try{
			// Type input into search bar
			if(wh.isElementPresent(SearchInput)){
				wh.sendKeys(SearchInput, screenOption.toString().trim());
			}else{
				throw new Exception("Unable to locate search bar element. "
						+ "XPath used is: " + SearchInput.toString());
			}
			
			// Validate that data was inputted
			if(wh.getAttribute(SearchInput, "value").equalsIgnoreCase(screenOption)){
				report.addReportStep("Enter "+ screenOption + " in menu search", 
						"Successfully entered "+ screenOption + " in menu search", StepResult.PASS);
			}else{
				report.addReportStep("Enter "+ screenOption + " in menu search", 
						"Not entered search criteria in menu search", StepResult.FAIL);
				rc.throwTCTerminationException();
			}	
			
			// Click dropdown menu option to select page
			try{
				By locator = By.xpath(".//div[contains(@id,'boundlist')]/ul/li/div"
						+ "[contains(text(),'("+moduleIndex+")')]");
				if(wh.isElementPresent(locator)){
					driver.findElement(locator).click();
					report.addReportStep("Click button to load " + screenOption + " window. ", 
							"Clicked button to load " + screenOption + " window", StepResult.PASS);
				}
				
			}catch(Exception e){
				report.addReportStep("Click button to load " + screenOption + " window. ", 
						"Unable to click button to load " + screenOption + " window" + e.getMessage(), StepResult.FAIL);
				rc.throwTCTerminationException();
			}
			
			// Wait for window to load
			waitForWindowToLoad(screenOption);
			
		}catch(Exception e){
			report.addReportStep("Bring up " + screenOption + " window. ", 
					"Unable to bring up " + screenOption + " window " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		

	}	

	/**
	 * This function logs a user out of the system.
	 * @throws Exception
	 */
	
	public void logout() throws Exception{
		
		try{
			driver.switchTo().defaultContent();
//			wh.mouseOver(ProfileButton);
//			wh.clickElement(ProfileButton);
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
