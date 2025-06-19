package com.homer.glue;

import com.homer.dao.And;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.enums.EnumClass.StepResult;

public class PLPStepDefn extends BaseStepDefn {
	
	public PLPStepDefn(DataClass data) {
		super(data);
	}

	
	@Then("^I see the initial search PLP$")
	public void i_see_the_initial_search_PLP() throws Throwable { 
			
		//newWebDriver.get("http://www.homedepot.com/");
		
		/*System.out.println("Step No - "+data.stepNo);
		System.out.println("Iteration No - "+data.iterationNo);
		
		plpPage.verifyPLPPage();*/
	}
	  
	@When("^I click first prod in Search PLP$")
	public void i_click_first_prod_in_Search_PLP() throws Throwable { 
		
		System.out.println("Step No - "+data.stepNo);
		System.out.println("Iteration No - "+data.iterationNo);
		
		pipPage = plpPage.clickFirstPLPPOD();
		
		report.addReportStep("Click on first PLP product",
				"Clicked first PLP product", 
				StepResult.DONE);
	  
	}
	
	@Then("^I sign in to site in PLP$")
	public void i_sign_in_to_site_in_PLP() throws Throwable { 
		
		plpPage.signInUser(dataTable.getCommonData(CommonDataColumn.SignInUser), dataTable.getCommonData(CommonDataColumn.SignInPassword));
	  
	}
	
	@And("^I sign in to site$")
	public void i_sign_in_to_site() throws Throwable { 
		
		plpPage.signInUser(dataTable.getCommonData(CommonDataColumn.SignInUser), dataTable.getCommonData(CommonDataColumn.SignInPassword));
	  
	}
}
