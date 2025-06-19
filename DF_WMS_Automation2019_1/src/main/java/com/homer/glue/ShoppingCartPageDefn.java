package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Then;

public class ShoppingCartPageDefn extends BaseStepDefn {
	
	public ShoppingCartPageDefn(DataClass data) {
		super(data);
		
	}
	
	@Then("^I see Shopping Cart page$")
	public void i_see_Shopping_Cart_page() throws Throwable { 
		
		//shoppingCartPage.verifyShoppingCartPage();
	  
	}
	
	@And("^I click AddToCart in ShoppingCart page$")
	public void i_click_AddToCart_in_ShoppingCart_page() throws Throwable { 
		
		checkoutSignInPage = shoppingCartPage.clickCheckoutNow();
	}

	@Then("^I see Guest Checkout page$")
	public void i_see_Guest_Checkout_page() throws Throwable {
		
		checkoutSignInPage.verifySecurePage();		
	}

	@And("^I enter Guest email id and click continue$")
	public void i_enter_Guest_email_id_and_click_continue() throws Throwable { 
		
		shippingPage = checkoutSignInPage.guestSignInAndContinue();	  
	}
}
