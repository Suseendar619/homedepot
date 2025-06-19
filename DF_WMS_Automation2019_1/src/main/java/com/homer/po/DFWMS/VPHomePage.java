package com.homer.po.DFWMS;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class VPHomePage extends PageBase {

	public VPHomePage(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	static final By menu =By.xpath(".//*[starts-with (@id,'mps_solutions_menu') and contains (@id,'btnWrap')]") ;
	static final By vendorportal =By.xpath(".//*[contains (@id,'menuitem') and contains (text (),'Vendor Portal')]") ;
	static final By VPmenu = By.cssSelector(".x-btn-icon-el.wt-topbar-menu-icon");
	static final By LoadingFrame=By.cssSelector(".x-mask-msg-text");
	static final By poselect=By.xpath(".//*[contains (text(),'Purchase Orders') and contains (@id,'ext-gen')]/..");
	static final By SearchInput=By.xpath(".//input[contains(@id,'mps_menusearch')]");
	/*public void open() throws Exception
	{
		if(wh.isElementPresent(menu, 4))
		{
			wh.clickElement(menu);
			
			report.addReportStep("Click on Transportation Planning and Execution", 
					"Successfully clicked on Transportation Planning and Execution", StepResult.PASS);	
		}
		
		else
			
			rc.terminateTestCase("Vendor Portal", StepResult.FAIL);

		Thread.sleep(2000);
	}
	
	public void vendorselect() throws Exception
	{
		
		if(wh.isElementPresent(vendorportal, 2))
		{
			wh.clickElement(vendorportal);
			Thread.sleep(5000);
			report.addReportStep("Click on Vendor Portal", 
					"Successfully clicked on Vendor Portal", StepResult.PASS);	
		}
		
			else
			
			rc.terminateTestCase("Vendor Portal select", StepResult.FAIL);
		wh.waitUntilDisappear(LoadingFrame);
		Thread.sleep(5000);
		driver.switchTo().window("http://ln09d2.homedepot.com:50500/");
		Thread.sleep(5000);
		try {

	        //String winHandleBefore = driver.getWindowHandle();

	        for(String winHandle : driver.getWindowHandles()){
	            driver.switchTo().window(winHandle);
	        }
	        }catch(Exception e){
	        System.out.println(e.getMessage()); 
	        }
	}*/
	

	public void VPMenu() throws Exception
	
	{
		wh.waitUntilDisappear(LoadingFrame);
		//wh.waitForPageLoaded();
		if(wh.isElementPresent(VPmenu, 8))
		{
			wh.clickElement(VPmenu);
			
			report.addReportStep("Click on Vendor Portal menu", 
					"Successfully clicked on Vendor Portal menu", StepResult.PASS);	
			Thread.sleep(1000);
		}
		else
			report.addReportStep("Click on Vendor Portal menu", 
					" Vendor Portal menu was not selected", StepResult.FAIL);	
		
	}
	
	public void searchInput(String searchData, String listIndex) throws Exception
	{
		//Search for Purchase Order in the drop down of the Menu
		wh.sendKeys(SearchInput, searchData.toString().trim());
		if(wh.getAttribute(SearchInput, "value").equalsIgnoreCase(searchData)){
			report.addReportStep("Enter"+ searchData + "in menu search", 
					"Successfully entered"+ searchData + "in menu search", StepResult.PASS);
		}else{
			report.addReportStep("Enter"+ searchData + "in menu search", 
					"Not entered search criteria in menu search", StepResult.FAIL);
		}		
		driver.findElement(By.xpath(".//div[contains(@id,'boundlist')]/ul/li/div[contains(text(),'("+listIndex+")')]")).click();
		Thread.sleep(2000);
	}
	
	
	public void poselect() throws Exception
	{
		if(wh.isElementPresent(poselect, 8))
		{
			wh.clickElement(poselect);
			report.addReportStep("Click on Purchase Order", 
					"Successfully clicked on Purchase Order", StepResult.PASS);	
		}
		else 
			report.addReportStep("Np", 
					"Successfully clicked on Purchase Order", StepResult.FAIL);	
		Thread.sleep(2000);
	}
}
