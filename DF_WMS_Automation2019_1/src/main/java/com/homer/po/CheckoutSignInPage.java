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

	public class CheckoutSignInPage {
	
	InstanceContainer ic;
	WebDriver driver;
	WebDriverHelper wh;
	DataTable dataTable;
	ReusableComponents rc;
	Report report;
	CommonData commonData;
	
	static final By verifySignInPage = By.name("CheckoutSignInForm");
	static final By checkoutGuestRadioBtn = By.id("passRadioCont");
	static final By guestEmailTxtBox = By.id("logonId1");
	static final By continueBtn = By.id("submitId1bottom");
		
	public CheckoutSignInPage(InstanceContainer ic) {
		
		this.ic = ic;
		this.driver = ic.driver;
		this.wh = ic.wh;
		this.rc = ic.rc;
		this.dataTable = ic.dataTable;
		this.report = ic.report;
		this.commonData = ic.commonData;
	}
	
	/**
	 * Method to verify secure page
	 * @return
	 * @throws Exception
	 */
	public CheckoutSignInPage verifySecurePage() throws Exception {
	
		if(wh.isElementPresent(verifySignInPage, 2)) {
			
			report.addReportStep("Verify Checkout Sign In Page",
					"Checkout Sign In Page is displayed", 
					StepResult.PASS);
		} else {
			
			report.addReportStep("Verify Checkout Sign In Page",
					"Checkout Sign In Page is not displayed", 
					StepResult.FAIL);
		}		
		
		return this;
	}
	
	/**
	 * Method to sign in as guest 
	 * @return
	 * @throws Exception
	 */
	public ShippingPage guestSignInAndContinue() throws Exception {
		
		wh.clickElement(checkoutGuestRadioBtn);
		wh.sendKeys(guestEmailTxtBox, dataTable.getCommonData(CommonDataColumn.GuestEmail));

		wh.clickElement(continueBtn);
		
		report.addReportStep("Select guest user, enter email and click on continue",
				"Selected guest user, enter email and clicked on continue", 
				StepResult.PASS);
		
		return new ShippingPage(ic);
	}
}
