package com.homer.po;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;


import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
public class HomePage extends PageBase<HomePage> {
	
	static By betaWebServerBtn = By.xpath("//button[@id='button1'][contains(text(),'Beta Webservers')]");
	static By testWebServerBtn = By.xpath("//button[@id='button1'][contains(text(),'Test Webserver')]");
	
	static final By SearchTxtBox = By.id("searchFocus");
	static final By SearchBtn = By.id("searchButton");
	 
	 public HomePage(InstanceContainer ic) {
		 super(ic);        	     
	 }
	 
	 /**
	  * Method to open Home Page
	  * @throws Exception
	  */
	 public void open() throws Exception {
		 
		// rc.getXMLResponse("http://cpliqa9u.homedepot.com:1219/ProductAPI/v1/price?itemId=205092878");
		 String envUrl = dataTable
					.getCommonData(CommonDataColumn.EnvironmentUrl);

			try {

				driver.manage().deleteAllCookies();
				driver.get(dataTable.getCommonData("EnvironmentUrl"));
				
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				wh.handleAlert();
			}

//			if (envUrl.contains("usebeta") || envUrl.contains("testpage")) {
//
//				if (envUrl.contains("usebeta")) {
//
//					wh.clickElement(betaWebServerBtn);
//
//				} else {
//
//					try {
//
//						wh.clickElement(testWebServerBtn);
//
//					} catch (Exception ex) {
//
//						System.out.println(ex.getMessage());
//					}
//				}
//
//				if (wh.isAlertPresent()) {
//
//					wh.handleAlert();
//				}
//
//				final String thisWindow = driver.getWindowHandle();
//
//				String newWindow = new FluentWait<WebDriver>(driver)
//						.until(new ExpectedCondition<String>() {
//							public String apply(WebDriver d) {
//								Set<String> handles = d.getWindowHandles();
//								handles.remove(thisWindow);
//								return handles.size() > 0 ? handles.iterator()
//										.next() : null;
//							}
//						});
//
//				driver.close();
//				driver.switchTo().window(newWindow);
//				driver.manage().window().maximize();
//			}
		
		driver.manage().window().maximize();
		if(driver.getTitle().equalsIgnoreCase("The Home Depot")) { 
			report.addReportStep("Open Home Depot page" , 
					"Open Home Depot page is  displayed successfully", 
					StepResult.PASS);			
		}else{
			report.addReportStep("Open Home Depot page" , 
					"Open Home Depot page is not  displayed successfully", 
					StepResult.FAIL);
		}
		//expectedResult =  wh.isElementPresent(verifyHomePage, 1) ||rc.getAnalyticsValue("pageName").equalsIgnoreCase("HomePage") ? true : false;
		
	/*	if(expectedResult) {
			
			report.addReportStep("Open Home Depot page" , 
					"Open Home Depot page is displayed successfully", 
					StepResult.PASS);
		} else {
			
			report.addReportStep("Open Home Depot page" , 
					"Open Home Depot page is not displayed successfully", 
					StepResult.FAIL);
		}*/
	 }	
	 
	 public void failStep1() throws Exception {
		 
		 open();
		/* report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);*/
		 PLPPage plpPage = new PLPPage(ic);
		 
		 plpPage.failStep1();
		 
	 }
	 
	 public void failStep2() throws Exception {
		 
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	 }
	 
	 public void failStep3() throws Exception {
		 
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	 }
 
	 public void failStep4() throws Exception {
	 
		 open();
		 report.addReportStep("Fail step Home Page",
			 "Failed step",
			 StepResult.FAIL);
	 }
	 
	 public void failStep5() throws Exception {
	 
		 open();
		 report.addReportStep("Fail step Home Page",
			 "Failed step",
			 StepResult.FAIL);
	 }
	 
	 public void failStep6() throws Exception {
	 
		 open();
		 report.addReportStep("Fail step Home Page",
			 "Failed step",
			 StepResult.FAIL);
	 }
	 
	 public void failStep7() throws Exception {
		 
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	 
	 public void failStep8() throws Exception {
		 
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	 
	 public void failStep9() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	 
	 public void failStep10() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	 
	 public void failStep11() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	
	public void failStep12() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
	
	 public void failStep13() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	 }
	 
	 public void failStep14() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	}
 
	 public void failStep15() throws Exception {
		 open();
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
		 }
}
