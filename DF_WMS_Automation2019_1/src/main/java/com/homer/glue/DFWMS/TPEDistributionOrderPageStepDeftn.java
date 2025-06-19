package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;

public class TPEDistributionOrderPageStepDeftn extends BaseStepDefn{

	public TPEDistributionOrderPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
					

	@When("^Filter with distribution order \"(.*?)\"$")
	public void filter_with_distribution_order_PrimaryFieldInstanceTwo(String DOSearch) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.distributionOrder(DOSearch);
	  
	}
		@Given("^Open Distribution Orders screen$")
	public void open_Distribution_Orders_screen() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEmenu();		
		tpehomepageobject.searchInput(TPEHomePageObject.SearchOptions.DISTRIBUTIONORDERS.getValue(),TPEHomePageObject.BoundListOptions.TLM.getValue());
		//tpehomepageobject.searchInput(dataTable.getData(DataColumn.SearchInputInstanceTwo),dataTable.getData(DataColumn.BoundListOptionInstanceTwo));
	}

	/*@When("^Filter with distribution order$")
	public void filter_with_distribution_order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.distributionOrder(dataTable.getData(DataColumn.PrimaryFieldInstanceTwo));
	}*/
	
	@And("^Apply and Verify Distribution Order$")
	public void apply_and_Verify_Distribution_Order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.distributionOrderApplied();		
		tpedistributionorderpageobject.distributionOrderNumberCheckBox();		
	}
	
	@And("^Select the order right click and Choose single to create a shipment for the order$")
	public void select_the_order_right_click_and_Choose_single_to_create_a_shipment_for_the_order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.manualplaningButton();
	}

	@Then("^User accept workspace to create the shipment$")
	public void user_accept_workspace_to_create_the_shipment() throws Throwable { 

		tpedistributionorderpageobject.acceptWorkspace();
		
		// Save Shipment ID to be used across scenarios
		String shipmentId = tpedistributionorderpageobject.getShipmentId();
		TPEShipmentsPageStepDeftn.setShipmentId(shipmentId);
	}	
	@When("^Filter with distribution orderEng$")
	public void filter_with_distribution_orderEng() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpedistributionorderpageobject.distributionOrderEng(dataTable.getData(DataColumn.PrimaryFieldInstanceTwo));
		tpedistributionorderpageobject.distributionOrderEngValidation("Distribution Order");
		tpedistributionorderpageobject.distributionOrderApplied();
		tpedistributionorderpageobject.dOValidation();
		tpedistributionorderpageobject.transportationStatusValidaion(); 	
	}
	@When("^Create a Filter$")
	public void Create_a_Filter() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.filterCreation();
	}
	@When("^Filter with distribution orderEngXD$")
	public void filter_with_distribution_orderEngXD() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpedistributionorderpageobject.distributionOrderEngValidation(dataTable.getData(DataColumn.PrimaryFieldInstanceTwo));
		tpedistributionorderpageobject.distributionOrderEngValidation("Distribution Order");
		tpedistributionorderpageobject.distributionOrderApplied();
		tpedistributionorderpageobject.dOValidation();
		tpedistributionorderpageobject.transportationStatusValidaion();
	}
	@When("^Filter with distribution orderEngFleet$")
	public void filter_with_distribution_orderEngFleet() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.distributionOrderEngValidation("Distribution Order");
		tpedistributionorderpageobject.distributionOrderApplied();
		tpedistributionorderpageobject.dOValidation();
		tpedistributionorderpageobject.transportationStatusValidaion();
	}
	
	
	@When("^Filter with multiple distribution orders$")
	public void filter_with_multiple_distribution_orders() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.distributionOrderEngValidation(dataTable.getData(DataColumn.PrimaryFieldInstanceTwo));
		tpedistributionorderpageobject.distributionOrderApplied();
		tpedistributionorderpageobject.dOValidation();
		tpedistributionorderpageobject.transportationStatusValidaion(); 	
	}
	
	@And("^Select and Combine multiple distribution orders$")
	public void select_and_Combine_multiple_distribution_orders() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.manualplaningCombineButton();
	}
	
	@Then("^Validate Shipment for Errors$")
	public void validate_Shipment_for_Errors() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.validateShipment();
	}

	@And("^Validate DO details in Shipment$")
	public void validate_DO_details_in_Shipment() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.viewordersstops();
		tpedistributionorderpageobject.compareDOorders();
	}

}
