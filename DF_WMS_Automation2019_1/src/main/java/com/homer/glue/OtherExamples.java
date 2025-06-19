package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.Before;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.GenericUtil;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.dao.tdm.SKU;
import com.homer.enums.EnumClass.StepResult;

public class OtherExamples extends BaseStepDefn {
	
	public OtherExamples(DataClass data) {
		super(data);
	}
	
	@Given("^Beehive is down$")
	public void beehive_is_down() throws Throwable { 
		
		System.out.println("'Beehive is down' method is called");
	}
	
	@And("^Voltage service is up and running$")
	public void voltage_service_is_up_and_running() throws Throwable { 
		
		System.out.println("'Voltage service is up anod running' method is called");
	}

	@And("^Test DocString in Background with \"(.*?)\"$")
	public void test_DocString_in_Background_with_arg1(String arg1, String arg2) throws Throwable { 
		
		System.out.println(arg1);
		System.out.println(arg2);	  
	}

	@Given("^I am a \"(.*?)\" user on the xyz\\.com$")
	public void i_am_a_arg1_user_on_the_xyz_com(String arg1) throws Throwable { 
		
		System.out.println(arg1);	  
	}

	@When("^I place ([^\"]*) order using New ([^\"]*) \\+ ([^\"]*) Card$")
	public void i_place_order_order_using_New_credit__gift_Card(String order, String credit, String gift) throws Throwable { 
		
		System.out.println(dataTable.getData(DataColumn.Credit));
		System.out.println(dataTable.getData(DataColumn.Order));
		System.out.println(order);
		System.out.println(credit);
		System.out.println(gift);	  
	}

	@Then("^the order status must be ([^\"]*)$")
	public void the_order_status_must_be_status(String status) throws Throwable { 
		
		System.out.println(status);	  
	}
	
	@And("^I should see an \"(.*?)\" message in the \"(.*?)\" page for card ([^\"]*)\\:$")
	public void i_should_see_an_arg1_message_in_the_arg2_page_for_card_card(String arg1, String arg2, String card, String arg4) throws Throwable { 
		
		System.out.println(arg1);
		System.out.println(arg2);
		System.out.println(card);
		System.out.println(arg4);
	}

	@And("^\"(.*?)\"  and card ([^\"]*) is asked to confirm$")
	public void arg1_and_card_card_is_asked_to_confirm(String arg1, String card) throws Throwable { 
		
		System.out.println(arg1);
		System.out.println(card);	  
	}
	
	@When("^I place ([^\"]*) order using card$")
	public void i_place_order_order_using_card(String order, String arg2) throws Throwable { 
		
		System.out.println(order);
		System.out.println(arg2);
	}

	
	@Given("^I get string from PIP UI$")
	public void i_get_string_from_PIP_UI() throws Throwable { 
		
		System.out.println("String value from UI Map : " + GenericUtil.getPIPLoactor("pipString"));
		
		report.addReportStep("Get string value from UI Map",
				"String value from UI Map : " + GenericUtil.getPIPLoactor("pipString"),
				StepResult.PASS);	  
	}

	@When("^I get locator from PIP UI$")
	public void i_get_locator_from_PIP_UI() throws Throwable { 
		
		System.out.println("Locator value from UI Map : " + GenericUtil.getPIPLoactor("verifyPip"));	
		
		report.addReportStep("Get locator value from UI Map",
				"Locator value from UI Map : " + GenericUtil.getPIPLoactor("verifyPip"),
				StepResult.PASS);	  
	}
	
	@Given("^I am testing certona test case (\\d+) step$")
	public void i_am_testing_testing_certona_test_case_1_step() throws Throwable { 
		
		report.addReportStep("Call Certona AUT step", 
				"Certona AUT step called", 
				StepResult.PASS);	  
	}
	
	@Given("^I am testing DTA test case (\\d+) step$")
	public void i_am_testing_testing_DTA_test_case_1_step() throws Throwable { 
		
		report.addReportStep("Call DTA AUT step", 
				"DTA AUT step called", 
				StepResult.PASS);	
	}

	@Given("^I am testing IRG test case (\\d+) step$")
	public void i_am_testing_testing_IRG_test_case_1_step() throws Throwable {
		
		report.addReportStep("Call IRG AUT step", 
				"IRG AUT step called", 
				StepResult.PASS);	
	}
	
	@Given("^I am testing No AUT test case (\\d+) step$")
	public void i_am_testing_No_AUT_test_case_2_step() throws Throwable { 
		
		report.addReportStep("Call No AUT step", 
				"No AUT step called", 
				StepResult.PASS);		  
	}

	@Given("^Testing TDM Service Call$")
	public void testing_TDM_Service_Call() throws Throwable { 
	
		SKU sku = dataTable.getTDMData("BOSS_OutOfStock");
		System.out.println("SKU " + sku.getInternetSKU());
	  
	}
	
	@Given("^Testing Jenkins Filter one testing$")
	public void testing_Jenkins_Filter_one_testing() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}
}
