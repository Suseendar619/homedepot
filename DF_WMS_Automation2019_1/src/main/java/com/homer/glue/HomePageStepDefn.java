package com.homer.glue;

import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.When;
import com.homer.dao.tdm.SKU;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.HomePage;

public class HomePageStepDefn extends BaseStepDefn {
	
	public HomePageStepDefn(DataClass data) {
		super(data);
	}	
	
		
	@Given("^I am a customer on Home Depot$")
	public void i_am_a_customer_on_Home_Depot() throws Throwable {
		homePage.open();
		
		
/*		System.out.println("Test case name " +data.testCaseName);
		System.out.println("Report folder name " +data.reportFolder);
		System.out.println("Screenshot folder name "+data.screenShotFolder);
		
		System.out.println("Step No - "+data.stepNo);
		System.out.println("Iteration No - "+data.iterationNo);*/
//		System.out.println("Keyword: "+dataTable.getData(DataColumn.Keyword));
		/*homePage = new HomePage(ic);
		homePage.open();
		SKU sku = dataTable.getTDMData("BOSS_OutOfStock");
		System.out.println("SKU #: " + sku.getInternetSKU());
		System.out.println("Store #: " + sku.getStoreNumber());
//		throw new Exception("Testing error reporting");
		*/
		//rc.addReportStep("Test new report", "tested new report", StepResult.FAIL);
	}
	
	
	@When("^I search for \"(.*?)\"$")
	public void i_search_for_arg1(String arg1) throws Throwable { 
				
		System.out.println("Step No - "+data.stepNo);
		System.out.println("Iteration No - "+data.iterationNo);
		
		plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}
	
	@When("^I search for ([^\"]*)$")
	public void i_search_for_Keyword(String Keyword) throws Throwable { 		
		
		plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}

	@When("^I type keyword \"(.*?)\"$")
	public void i_land_on_PLP_page(String arg1) throws Throwable { 
		
		System.out.println(arg1);
		System.out.println(dataTable.getData(DataColumn.Keyword));
		
		plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));	  
	}
	
	@When("^I search for sku \"(.*?)\"$")
	public void i_search_for_sku_arg1(String arg1) throws Throwable { 
		
		pipPage = homePage.searchSkuNo(arg1);
	}
}
