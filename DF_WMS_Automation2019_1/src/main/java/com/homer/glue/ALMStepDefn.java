package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.Before;
import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.enums.EnumClass.StepResult;

public class ALMStepDefn extends BaseStepDefn {

	public ALMStepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@Before("P1 feature")
	public void i_test_before_ALM_P1_feature() throws Throwable { 
		
		System.out.println("I create ALM Before test set");
		
		report.addReportStep("Test ALM Feature", 
				"Tested ALM feature", 
				StepResult.PASS);	  
	}
	
	
	@Given("^I test create ALM feature$")
	public void i_test_create_ALM_feature() throws Throwable { 
		
		System.out.println("I create ALM test set");
		
		report.addReportStep("Test ALM Feature", 
				"Tested ALM feature", 
				StepResult.PASS);	  
	}
	
	@And("^I added \"(.*?)\" item to cart$")
	public void i_added_arg1_item_to_cart(String arg1) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I checkout as guest user$")
	public void i_checkout_as_guest_user() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click continue button in checkout page$")
	public void i_click_continue_button_in_checkout_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I see payment page$")
	public void i_see_payment_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click submitorder button in Payment page$")
	public void i_click_submitorder_button_in_Payment_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I am a registered user$")
	public void i_am_a_registered_user() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I search for keyword$")
	public void i_search_for_keyword() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I click Add to cart in PLP$")
	public void i_click_Add_to_cart_in_PLP() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click Checkout Now in overlay$")
	public void i_click_Checkout_Now_in_overlay() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I click checkoutNow in ShoppingCart page$")
	public void i_click_checkoutNow_in_ShoppingCart_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click Continue shopping in overlay$")
	public void i_click_Continue_shopping_in_overlay() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I search for sku$")
	public void i_search_for_sku() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I see pickup option page$")
	public void i_see_pickup_option_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click on PickUpInStore radio button$")
	public void i_click_on_PickUpInStore_radio_button() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click Add to cart from PLP certona$")
	public void i_click_Add_to_cart_from_PLP_certona() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click checkout now$")
	public void i_click_checkout_now() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I verify certona section in PIP$")
	public void i_verify_certona_section_in_PIP() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click Add to cart from PIP certona$")
	public void i_click_Add_to_cart_from_PIP_certona() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I see special buy of the day page$")
	public void i_see_special_buy_of_the_day_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click Add to cart from sbotd page$")
	public void i_click_Add_to_cart_from_sbotd_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click your account in home page$")
	public void i_click_your_account_in_home_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I localize to store$")
	public void i_localize_to_store() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I edit account information in My Account page$")
	public void i_edit_account_information_in_My_Account_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click on ShipToStore radio button$")
	public void i_click_on_ShipToStore_radio_button() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I Sign Out My account$")
	public void i_Sign_Out_My_account() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I see session expired error msg in secure checkout page$")
	public void i_see_session_expired_error_msg_in_secure_checkout_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I enter password for persistent user$")
	public void i_enter_password_for_persistent_user() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@When("^I signin for persistent user in My Account page$")
	public void i_signin_for_persistent_user_in_My_Account_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@And("^I click cart breadcrumb$")
	public void i_click_cart_breadcrumb() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}
}
