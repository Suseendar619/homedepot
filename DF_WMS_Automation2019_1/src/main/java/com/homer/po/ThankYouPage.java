package com.homer.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.homer.dao.CommonData;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.helper.DataTable;
import com.homer.reports.Report;
import com.homer.resuablecomponents.ReusableComponents;
import com.homer.resuablecomponents.WebDriverHelper;

public class ThankYouPage {
	
	InstanceContainer ic;
	WebDriver driver;
	WebDriverHelper wh;
	DataTable dataTable;
	ReusableComponents rc;
	Report report;
	CommonData commonData;
	
	public boolean isProdEnv = false;
	
	static final By verifyOrderPage = By.cssSelector("div[id='bodfsThankYouPage'] h2[class='xlarge']");
	static final By orderNumber = By.cssSelector("span.tyOrderNumber");
	
	public ThankYouPage(InstanceContainer ic) {
		
		this.ic = ic;
		this.driver = ic.driver;
		this.wh = ic.wh;
		this.rc = ic.rc;
		this.dataTable = ic.dataTable;
		this.report = ic.report;
		this.commonData = ic.commonData;
	}
	
	/**
	 * Method to verify Thank You page
	 * @return
	 * @throws Exception
	 */
	public ThankYouPage verifyThankYouPage() throws Exception {
		
		if(isProdEnv) {
			
			report.addReportStep("Place order and verify 'Thank You' page",
					"We do not place order on Production", 
					StepResult.WARNING);
			
			return this;
		}
		
		wh.waitForElementPresent(verifyOrderPage, 4);

		if (wh.noWaitElementPresent(verifyOrderPage)) {

			report.addReportStep(
					"Click on 'Submit Order' button in payment after filling all the details.",
					"'Thank You for Your Order' page is displayed with Order Number : <b>"+ wh.getText(orderNumber)+ "</b>", 
					StepResult.PASS);
		} else {

			report.addReportStep(
					"Click on 'Submit Order' button in payment after filling all the details.",
					"'Thank You for Your Order' page was not displayed.",
					StepResult.FAIL);
			
			rc.terminateTestCase("Thank You for Your Order");
		}

		return this;
	}

}
