package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.TPEHomePageObject;

public class RunEnginePageDefn extends BaseStepDefn {

	public RunEnginePageDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^Open Run Template Screen$")
	public void Open_Run_Template_Screen() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEmenu();
		tpehomepageobject.searchInput(TPEHomePageObject.SearchOptions.RUNTEMPLATES.getValue(), TPEHomePageObject.BoundListOptions.TLM.getValue());
	}
	@Then("^Run template Filter data Preview$")
	public void run_template_Filter_data_Preview() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runTemplateFilterPreview();
		runtemplatepageobject.runTemplateQuery(dataTable.getData(DataColumn.runTemplateName));
		runtemplatepageobject.runTemplateIterations();
	}
	@Then("^Select the Filter and Preview the data$")
	public void select_the_Filter_and_Preview_the_data() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runTemplateDetails();
	}
	@Then("^Run the Engine$")
	public void run_the_Engine() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runTheEngine();
	}
	@Then("^Run Engine Status Validation$")
	public void run_Engine_Status_Validation() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runStatusValidation();
	}
	
	@Then("^Run Engine Status Validation for Cons$")
	public void run_Engine_Status_Validation_for_Cons() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runStatusValidationCons();
	}
	
	@Then("^Run Engine Status Validation for XD$")
	public void run_Engine_Status_Validation_for_XD() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runStatusValidationXD();
	}
	
	@Then("^Run Summaries Validation$")
	public void run_Summaries_Validation() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.runSummaries();
		runtemplatepageobject.shipmentsCreated();
		runtemplatepageobject.review();
		runtemplatepageobject.shipmentSelect();
	}
	@Then("^Delete Filter$")
	public void delete_Filter() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		runtemplatepageobject.delete_Filter();
	}
	@Then("^Run template Filter data PreviewXD$")
	public void run_template_Filter_data_PreviewXD() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//runtemplatepageobject.runTemplateFilterPreviewXD();
		runtemplatepageobject.runTemplateFilterPreview();
		runtemplatepageobject.runTemplateQuery(dataTable.getData(DataColumn.runTemplateName));
		runtemplatepageobject.runTemplateIterations();
	}
	@Then("^Run template Filter data PreviewFleet$")
	public void run_template_Filter_data_PreviewFleet() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//runtemplatepageobject.runTemplateFilterPreviewFleet();
		runtemplatepageobject.runTemplateFilterPreview();
		runtemplatepageobject.runTemplateQuery(dataTable.getData(DataColumn.runTemplateName));
		runtemplatepageobject.runTemplateIterations();
	}
}
	