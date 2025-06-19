package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.dao.When;

public class ShippingPageStepDefn extends BaseStepDefn {
	
	public ShippingPageStepDefn(DataClass data) {
		super(data);
	}
	
	@Then("^I see Shipping Details page$")
	public void i_see_Shipping_Details_page() throws Throwable { 
		
	 // shippingPage.verifyShippingPage();
	}
	
	@When("^I enter Shipping Details$")
	public void i_enter_Shipping_Details() throws Throwable { 
		
		//shippingPage.enterShippingDetails();	  
	}

	@And("^I click continue button in Shipping page$")
	public void i_click_continue_button_in_Shipping_page() throws Throwable { 
		
		paymentPage = shippingPage.clickContinueBtn();
	}
}
