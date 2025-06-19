package com.homer.po;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;

public class PIPPage extends PageBase<PIPPage>{

	static final By verifyPIPPage = By.xpath("//div[@id='hd-pip' or @id='hd-bica']");
	static final By internetNo = By.id("product_internet_number");
	static final By addTocartBtn = By.cssSelector("a[class^='addToCart_btn dynamic_btn c']");
	static final By cartPopUpPrice = By.cssSelector("div.offerprice span.xlarge.item_price");
	static final By verifyAddToCartModal = By.cssSelector("div.containerAddToCartModel");
	static final By checkOutNowBtn = By.id("CartOverlayCheckoutId1");
	
	public PIPPage(InstanceContainer ic) {
		super(ic);
	}
	
	/**
	 * Method to verify PIP page
	 * @throws Exception
	 */
	public PIPPage verifyPIPPage() throws Exception {
		
		if(wh.isElementPresent(verifyPIPPage, 2)) {
			
			report.addReportStep("Verify PIP page is displayed",
					"PIP page is displayed", 
					StepResult.PASS);
			
		} else {
			
			report.addReportStep("Verify PIP page is displayed",
					"PIP page is not displayed", 
					StepResult.FAIL);
		}
		
		return this;
	}	
	
	/**
	 * Method to verify PIP for SKU
	 * @return
	 * @throws Exception
	 */
	public PIPPage verifyPIPForSKU() throws Exception {
		
		String sku = commonData.sku;
		
		if(wh.getText(internetNo).contains(sku)) {
			
			report.addReportStep("Verify PIP page is displayed for SKU - " + sku,
					"PIP page for SKU '"+sku+"'is displayed", 
					StepResult.PASS);
		} else {
			
			report.addReportStep("Verify PIP page is displayed for SKU - " + sku,
					"PIP page for SKU '"+sku+"'is not displayed", 
					StepResult.PASS);
		}
		
		return this;
	}
	
	/**
	 * Method to click add to cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartPage clickAddToCart() throws Exception {
		
		if (wh.isElementPresent(addTocartBtn, 20)) {
			
			wh.clickElement(addTocartBtn);
			wh.waitForElementPresent(cartPopUpPrice, 4);
			
			
		} else {

			report.addReportStep(
					"On PIP page, add to cart button should display",
					"'Add to Cart' button was not displayed.", StepResult.FAIL);
			
			rc.terminateTestCase("PIP Add To Cart not displayed");
		}	
		
		if (!wh.isElementPresent(verifyAddToCartModal, 10) ) {
			
			report.addReportStep("Click on 'Add to Cart' button",
					"User is not navigated to checkout pop up.",
					StepResult.FAIL);
			
			rc.terminateTestCase("Add To Cart not displayed");
		}

		wh.clickElement(checkOutNowBtn);
		
		report.addReportStep("Click on check out now button",
				"Clicked on checkout now button", 
				StepResult.PASS);
		
		return new ShoppingCartPage(ic);
	}
	
	public void failStep1() throws Exception {
		 report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);
	 }
}
