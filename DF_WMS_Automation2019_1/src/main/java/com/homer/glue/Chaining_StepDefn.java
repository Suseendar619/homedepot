package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.enums.EnumClass.StepResult;

public class Chaining_StepDefn extends BaseStepDefn {

	public Chaining_StepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@Given("^Step one$")
	public void step_one() throws Throwable { 
	 System.out.println("Step One"); 
	  
	}

	@Given("^Step two$")
	public void step_two() throws Throwable { 
		 System.out.println("Step Two"); 
	  
	}
	
	@Given("^Step three$")
	public void step_three() throws Throwable { 
		 System.out.println("Step Three"); 
	  
	}

	@Given("^Step four$")
	public void step_four() throws Throwable { 
		 System.out.println("Step Four"); 
	  
	}

	@Given("^Step five$")
	public void step_five() throws Throwable { 
		 System.out.println("Step Five"); 
	  
	}
	

	@Given("^Step seven$")
	public void step_seven() throws Throwable { 
		 System.out.println("Step Seven"); 
	  
	}
	
	@Given("^Step eight$")
	public void step_eight() throws Throwable { 
		 System.out.println("Step Eight"); 
	  
	}

	@Given("^Step nine$")
	public void step_nine() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Given("^Step ten$")
	public void step_ten() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Given("^Step eleven$")
	public void step_eleven() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Given("^Step twelve$")
	public void step_twelve() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}
	
	@When("^I type ([^\"]*) keyword in search text box chaining$")
	public void i_type_keyword_keyword_in_search_text_box_chaining(String keyword) throws Throwable { 
		report.addReportStep("keyword is", keyword, StepResult.PASS);
	}

	@When("^I type ([^\"]*) sku in search text box chaining$")
	public void i_type_sku_sku_in_search_text_box_chaining(String sku) throws Throwable { 
		report.addReportStep("sku is", sku, StepResult.PASS);
	}


	@And("^I click search button chaining$")
	public void i_click_search_button_chaining() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I see PLP page chaining$")
	public void i_see_PLP_page_chaining() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}

	@Then("^I see PIP page chaining$")
	public void i_see_PIP_page_chaining() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
	}
	
	@When("^I type \"(.*?)\" keyword in search text box chaining$")
	public void i_type_arg1_keyword_in_search_text_box_chaining(String keyword) throws Throwable { 
		report.addReportStep("keyword is", keyword, StepResult.PASS);	  
	}

	@When("^I type \"(.*?)\" sku in search text box chaining$")
	public void i_type_arg1_sku_in_search_text_box_chaining(String sku) throws Throwable { 
		report.addReportStep("sku is", sku, StepResult.PASS);	  
	}



}
