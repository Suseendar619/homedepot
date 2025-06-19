package com.homer.glue;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.dao.When;

public class PIPStepDefn extends BaseStepDefn {
	
	public PIPStepDefn(DataClass data) {
		super(data);
	}
	
	@Then("^I add to cart from PIP page$")
	public void i_add_to_cart_from_PIP_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	
	
	@Then("^I see PIP page displayed$")
	public void i_see_PIP_page_displayed() throws Throwable { 
		
		System.out.println("Step No - "+data.stepNo);
		System.out.println("Iteration No - "+data.iterationNo);
		
		pipPage.verifyPIPPage();
	}
	

	@Then("^I see PIP page for SKU displayed$")
	public void i_see_PIP_page_for_SKU_displayed() throws Throwable { 
		
		pipPage.verifyPIPForSKU();
	}
	
	@When("^I click on AddToCart in PIP page$")
	public void i_click_on_AddToCart_in_PIP_page() throws Throwable { 
		
	  shoppingCartPage = pipPage.clickAddToCart();
	}
}
