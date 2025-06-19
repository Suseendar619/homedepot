package com.homer.po.DFWMS;

import org.openqa.selenium.By;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class VPLoginPageObject extends PageBase {

	public VPLoginPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	static final By UserName=By.name("j_username");
	static final By Password=By.name("j_password");
	static final By SignInBtn=By.xpath(".//a[contains(@class,'x-btn wt-login-btn-enabled')]");
	
	
	

	public void open() throws Exception {
		 
		//driver.switchTo().window(CommonDataColumn.EnvironmentUrlVP);
		//driver.navigate().to(CommonDataColumn.EnvironmentUrlVP);
		// rc.getXMLResponse("http://cpliqa9u.homedepot.com:1219/ProductAPI/v1/price?itemId=205092878");
		wh.clearHistory(); 
		wh.handleAlert();
		Thread.sleep(4000);
		driver.get(dataTable.getCommonData(CommonDataColumn.EnvironmentUrlVP));
		wh.handleAlert();
		Thread.sleep(4000);
		 /*String envUrl = dataTable.getCommonData(CommonDataColumn.EnvironmentUrlVP);

			try {

				driver.manage().deleteAllCookies();
				driver.get(envUrl);	
				//driver.get(dataTable.getCommonData("EnvironmentUrl"));				
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
		}*/
		
	 }	
	 
	 
/*	 public void login() throws Exception{
		 
		 if(wh.isElementPresent(UserName, 3)){
			 wh.sendKeys(UserName, dataTable.getCommonData(CommonDataColumn.VendorUsername));
		 }

		 if(wh.isElementPresent(Password, 3)){
			 wh.sendKeys(Password, dataTable.getCommonData(CommonDataColumn.VendorPassword));
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
					 
	 }	*/ 
	 
	 public void sigInBtn() throws Exception{		 
		 if(wh.isElementPresent(SignInBtn, 3)){
			 wh.jsClick(SignInBtn);	
			 wh.waitForPageLoaded();
		 }
	 }

}