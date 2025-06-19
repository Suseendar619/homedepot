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

public class PaymentPage {
	
	InstanceContainer ic;
	WebDriver driver;
	WebDriverHelper wh;
	DataTable dataTable;
	ReusableComponents rc;
	Report report;
	CommonData commonData;
	
	static final By verifyPaymentPage = By.xpath("//div[@class='checkout_nav xlarge'] //h1[@class = 'chk_pageTitle'] [contains(text(),'Payment')]");
	static final By cardNumber = By.id("WC_StandardCreditCard_FormInput_cardNumber_2");
	static final By nameOnCard = By.id("cardHolderName");
	static final By expirationMonth = By.id("cardExpiryMonthLabelCC");
	static final By expirationYear = By.id("cardExpiryYearLabelCC");
	static final By cardSecurityID = By.id("cardVerificationCodeCC");
	static final By firstName = By.id("firstName");
	static final By lastName = By.id("lastName");
	static final By address1 = By.id("address1");
	static final By zipCode = By.id("zipCode");
	static final By phoneNumber = By.id("billaddrphone");
	static final By emailAddress = By.id("email1");
	static final By submitOrderBtn = By.id("PaymentMethodBtnId_1");	
	static final By errorMsg = By.id("pageLevelErrMsg");
	static final By orderNumber = By.cssSelector("span.tyOrderNumber");
	
	public PaymentPage(InstanceContainer ic) {
		
		this.ic = ic;
		this.driver = ic.driver;
		this.wh = ic.wh;
		this.rc = ic.rc;
		this.dataTable = ic.dataTable;
		this.report = ic.report;
		this.commonData = ic.commonData;
	}
	
	
	/**
	 * Method to verify payment page
	 * @return
	 * @throws Exception
	 */
	public PaymentPage verifyPaymentPage() throws Exception {
		
		if (wh.isElementPresent(verifyPaymentPage, 5)) {
			
			report.addReportStep(
					"Enter the shipping details and click on 'Continue' button in 'Shipping Page'.",
					"'Payment' page is displayed", 
					StepResult.PASS);
			
			
		} else {
			
			report.addReportStep(
					"Enter the shipping details and click on 'Continue' button in 'Shipping Page'.",
					"'Payment' page is not displayed", StepResult.FAIL);
			
			rc.terminateTestCase("Payment");
		}
		
		return this;
	}
	
	/**
	 * Method to fill payment page details
	 * @return
	 * @throws Exception
	 */
	public PaymentPage fillPaymentPageDetails() throws Exception {

		wh.sendKeys(cardNumber,
				dataTable.getCommonData(CommonDataColumn.CardNumber));
		wh.sendKeys(nameOnCard,
				dataTable.getCommonData(CommonDataColumn.NameOnCard));
		
		wh.selectValue(expirationMonth,
				dataTable.getCommonData(CommonDataColumn.ExpirationMonth));
		wh.selectValue(expirationYear,
				dataTable.getCommonData(CommonDataColumn.ExpirationYear));
		wh.sendKeys(cardSecurityID,
				dataTable.getCommonData(CommonDataColumn.CardSecurityID));
		
		
		if(wh.isElementPresent(firstName,0)){

			wh.sendKeys(firstName,
			dataTable.getCommonData(CommonDataColumn.ShippingFirstName));
				wh.sendKeys(lastName,
			dataTable.getCommonData(CommonDataColumn.ShippingLastName));
				wh.sendKeys(address1,
			dataTable.getCommonData(CommonDataColumn.ShippingAddr));
				wh.sendKeys(zipCode,
			dataTable.getCommonData(CommonDataColumn.ShippingZipCode));
				wh.sendKeys(phoneNumber,
			dataTable.getCommonData(CommonDataColumn.ShippingPhNo));
			wh.sendKeys(emailAddress,
					dataTable.getCommonData(CommonDataColumn.GuestEmail));

		}
		
		report.addReportStep("Enter payment page details", 
				"Payment page details entered",
				StepResult.PASS);
		
		return this;	
	}
	
	/**
	 * Method to submit order
	 * @return
	 * @throws Exception
	 */
	public ThankYouPage submitOrder() throws Exception {
		
		ThankYouPage thankYouPage;
		
		if (rc.isProdEnvironment()) {

			report.addReportStep(
					"Click on 'Submit Order' button in payment after filling all the details.",
					"We do not place Orders on Production", StepResult.WARNING);
			
			thankYouPage = new ThankYouPage(ic);
			thankYouPage.isProdEnv = true;
			
			return thankYouPage;
		
		} else {

			wh.clickElement(submitOrderBtn);

			if (wh.noWaitElementPresent(errorMsg)) {

				report.addReportStep(
						"Click on 'Submit Order' button in payment after filling all the details.",
						"'Error mesage is displayed after clicking submit button",
						StepResult.FAIL);
				rc.terminateTestCase("Thank You for Your Order");
			}

			report.addReportStep("Click on 'Submit Order' button in payment after filling all the details", 
					"Submitted order for sku : "+commonData.sku,
					StepResult.PASS);
			
			thankYouPage = new ThankYouPage(ic);
			
			return thankYouPage;			
		}
	}
}
