package com.homer.glue;

import com.homer.dao.DataClass;
import com.homer.dao.Then;

public class ThankYouStepDefn extends BaseStepDefn {
	
	public ThankYouStepDefn(DataClass data) {
		super(data);
	}
	
	@Then("^I see thank you page for order placed$")
	public void i_see_thank_you_page_for_order_placed() throws Throwable { 
	  
		//thankYouPage.verifyThankYouPage();
	}
}
