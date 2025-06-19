package com.homer.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.homer.dao.CommonData;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.helper.DataTable;
import com.homer.reports.Report;
import com.homer.resuablecomponents.ReusableComponents;
import com.homer.resuablecomponents.WebDriverHelper;

public class ShippingPage {

	InstanceContainer ic;
	WebDriver driver;
	WebDriverHelper wh;
	DataTable dataTable;
	ReusableComponents rc;
	Report report;
	CommonData commonData;
	
	static final By verifysecureCheckOutPage = By.xpath("//div[@class='chk_pageTitle'][text()='Shipping']");	
	static final By firstNameTxtBox = By.name("shipadd-fname_0");
	static final By lastNameTxtBox = By.name("shipadd-lname_0");
	static final By addr1TxtBox = By.name("shipadd-add-1_0");
	static final By zipCodeTxtBox = By.name("shipadd-zipcode_0");
	static final By phNoTxtBox = By.name("app_phn_1_0");
	static final By continueShippingBtn = By.cssSelector("div.checkout_navBtn button");
	static final By applyBtnZipCode = By.xpath("//div[@class = 'chk-subnav' and @id ='applyAddress']");
	static final By shippingInfo = By.xpath("//div[@class='shipping-info']");
	
	public ShippingPage(InstanceContainer ic) {
		
		this.ic = ic;
		this.driver = ic.driver;
		this.wh = ic.wh;
		this.rc = ic.rc;
		this.dataTable = ic.dataTable;
		this.report = ic.report;
		this.commonData = ic.commonData;
	}
	
	/**
	 * Method to verify Shipping Page
	 * @return
	 * @throws Exception
	 */
	public ShippingPage verifyShippingPage() throws Exception {
		
		wh.waitForElementPresent(verifysecureCheckOutPage, 5);
		
		if(wh.isElementPresent(verifysecureCheckOutPage, 2)) {
			
			report.addReportStep("Verify Shipping page is displayed",
					"Shipping page is displayed", 
					StepResult.PASS);
		} else {
			
			report.addReportStep("Verify Shipping page is displayed",
					"Shipping page is not displayed", 
					StepResult.FAIL);
		}		
		
		return this;
	}
	
	/**
	 * Method to enter shipping details
	 * @return
	 * @throws Exception
	 */
	public ShippingPage enterShippingDetails() throws Exception {
		
		if(wh.isElementPresent(firstNameTxtBox,2)){

			wh.sendKeys(firstNameTxtBox,
					dataTable.getCommonData(CommonDataColumn.ShippingFirstName));
			
			wh.sendKeys(lastNameTxtBox,
					dataTable.getCommonData(CommonDataColumn.ShippingLastName));
			
			wh.sendKeys(addr1TxtBox,
					dataTable.getCommonData(CommonDataColumn.ShippingAddr));
			
			wh.sendKeys(zipCodeTxtBox,
					dataTable.getCommonData(CommonDataColumn.ShippingZipCode));
			
			wh.sendKeys(phNoTxtBox,
					dataTable.getCommonData(CommonDataColumn.ShippingPhNo));
			
			if (wh.isElementPresent(applyBtnZipCode,3))
			{
				wh.clickElement(applyBtnZipCode);
			}
			
			wh.waitForElementPresent(shippingInfo,2);
		}
		
		report.addReportStep("Enter Shipping Details",
				"Shipping Details entered", 
				StepResult.PASS);
		
		return this;
	}
	
	/**
	 * Method to click continue button
	 * @return
	 * @throws Exception
	 */
	public PaymentPage clickContinueBtn() throws Exception {
		
		wh.clickElement(continueShippingBtn);
		
		report.addReportStep("Click checkout now button in Shipping Cart Page", 
				"Clicked check out now button in Shipping Cart Page", 
				StepResult.PASS);	
		
		return new PaymentPage(ic);
	}
	
}
