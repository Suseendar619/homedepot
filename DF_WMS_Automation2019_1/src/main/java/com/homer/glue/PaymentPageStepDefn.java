package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.dao.When;

public class PaymentPageStepDefn extends BaseStepDefn {
	
	public PaymentPageStepDefn(DataClass data) {
		super(data);
		
	}
	
	@Then("^I see Payment page$")
	public void i_see_Payment_page() throws Throwable { 
		
		paymentPage.verifyPaymentPage();	  
	}
	
	@When("^I fill payment details$")
	public void i_fill_payment_details() throws Throwable { 
		
		//paymentPage.fillPaymentPageDetails();
	}

	@And("^I click continue button in Payment page$")
	public void i_click_continue_button_in_Payment_page() throws Throwable { 
		
		thankYouPage = paymentPage.submitOrder();
	}
}
