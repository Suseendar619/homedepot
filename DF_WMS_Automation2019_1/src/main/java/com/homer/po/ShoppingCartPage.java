package com.homer.po;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;

public class ShoppingCartPage extends PageBase<HomePage> {
	
	static final By verifyShoppingCartPage = By.id("cartContainer") ;
	static final By checkoutNowBtn = By.id("submitId1top");
	
	public ShoppingCartPage(InstanceContainer ic) {
		super(ic);
		
	}
	
	/**
	 * Method to verify Shopping Cart Page
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartPage verifyShoppingCartPage() throws Exception {
		
		if (wh.isElementPresent(verifyShoppingCartPage, 5)) {
			
			report.addReportStep("Verify Shopping Cart Page",
					"Shopping Cart Page is displayed", 
					StepResult.PASS);
		} else {
	
			report.addReportStep("Verify Shopping Cart Page",
					"Shopping Cart Page is not displayed", 
					StepResult.FAIL);
	
			rc.terminateTestCase("Shopping Cart Page");
		}
		
		return this;
	}
	
	/**
	 * Method to click checkout now
	 * @return
	 * @throws Exception
	 */
	public CheckoutSignInPage clickCheckoutNow() throws Exception {
		
		if (wh.isElementNotPresent(checkoutNowBtn)) {
			
			wh.waitForElementPresent(checkoutNowBtn,20);
		}
		
		wh.clickElement(checkoutNowBtn);
		
		report.addReportStep("Click checkout now button in Shopping Cart Page", 
				"Clicked check out now button in Shopping Cart Page", 
				StepResult.PASS);	
		
		return new CheckoutSignInPage(ic);
	}

}
