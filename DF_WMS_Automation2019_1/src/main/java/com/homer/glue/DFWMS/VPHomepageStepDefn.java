package com.homer.glue.DFWMS;

import org.openqa.selenium.By;

import com.homer.dao.And;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.TPEDistributionOrderPageObject;
import com.homer.po.DFWMS.TPEPostMessagePageObject;
import com.homer.po.DFWMS.VPPurchaseOrderPageObject;

public class VPHomepageStepDefn extends BaseStepDefn {

	public VPHomepageStepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^VP home page$")
	public void VP_home_page() throws Throwable { 
		//wh.clearHistory();
		//driver.get(dataTable.getCommonData(CommonDataColumn.EnvironmentUrlVP));
		//driver.navigate().to(dataTable.getCommonData(CommonDataColumn.EnvironmentUrlVP));
	 // Write code here that turns the phrase above into concrete actions 
		VPLoginPageObject.open();
		//VPLoginPageObject.login();
		//VPLoginPageObject.sigInBtn();
		//VPHomePage.open();
		//VPHomePage.vendorselect();
		//VPHomePage.switchwindow();
	}
	
	@When("^Click on Menu and search for Purchase Order$")
	public void click_on_Menu_and_search_for_Purchase_Order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		VPHomePage.VPMenu();
		VPHomePage.searchInput(dataTable.getData(DataColumn.SearchInputInstanceOne),dataTable.getData(DataColumn.BoundListOptionInstanceOne));
		//VPHomePage.poselect();
	}
	
	@When("^Filter with purchase order in vp$")
	public void Filter_with_purchase_order_in_vp() throws Throwable { 
		vppurchaseOrderPageObject.purchaseOrderScreen(dataTable.getData(DataColumn.PrimaryFieldInstanceOne));
		//VPHomePage.poselect();
	}
	@When("^Filter with purchase order in vp2$")
	public void Filter_with_purchase_order_in_vp2() throws Throwable { 
		vppurchaseOrderPageObject.purchaseOrderScreenS(dataTable.getData(DataColumn.PrimaryFieldInstanceOne));
		//VPHomePage.poselect();
	}
	@Then("^Apply and Verify generated purchase ordervp$")
	public void apply_and_Verify_generated_purchase_order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.purchaseOrderAppliedvp();
		vppurchaseOrderPageObject.purchaseOrderSelectandViewvp();
		
	}
	@Then("^Apply and Verify generated purchase ordervpS$")
	public void apply_and_Verify_generated_purchase_orderS() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.purchaseOrderAppliedvp();
		vppurchaseOrderPageObject.purchaseOrderSelectandViewvpS();
		
	}
	@And("^Verify RTS tabvp$")
	public void verify_RTS_tab() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.rtsTabValidationvp();
	}
	@Then("^Apply and Select purchase order$")
	public void Apply_and_Select_purchase_order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.purchaseOrderAppliedvp();
		vppurchaseOrderPageObject.purchaseOrderSelectvp();
		
	}
	@And("^Click the Menu for Purchase Order$")
	public void Click_the_Menu_for_Purchase_Order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.selectpurchaseorder();
		vppurchaseOrderPageObject.searchInputvp(dataTable.getData(DataColumn.SearchInputInstanceOne),dataTable.getData(DataColumn.BoundListOptionInstanceOne));
	}
	@Then("^Apply and select ready to ship$")
	public void Apply_and_select_ready_to_ship() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.purchaseOrderAppliedvp();
		vppurchaseOrderPageObject.purchaseOrderSelect();
		vppurchaseOrderPageObject.readytoship();
		
	}
	@Then("^Select ready to ship$")
	public void select_ready_to_ship() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.readytoship();
		
	}
	@Then("^Apply and select ready to shipvp$")
	public void Apply_and_select_ready_to_shipvp() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//vppurchaseOrderPageObject.purchaseOrderAppliedvp();
		//vppurchaseOrderPageObject.purchaseOrderSelectp();
		vppurchaseOrderPageObject.readytoship();
		
	}
	@When("^Filter with purchase order in vp1$")
	public void Filter_with_purchase_order_in_vp1() throws Throwable { 
		vppurchaseOrderPageObject.purchaseOrderScreen1(dataTable.getData(DataColumn.PrimaryFieldInstanceOne));
		//VPHomePage.poselect();
	}
	@When("^Filter with purchase order in vpS$")
	public void Filter_with_purchase_order_in_vpS() throws Throwable { 
		vppurchaseOrderPageObject.purchaseOrderScreenS(dataTable.getData(DataColumn.PrimaryFieldInstanceOne));
		//VPHomePage.poselect();
	}
	@When("^Filter with purchase order in vpS1$")
	public void Filter_with_purchase_order_in_vpS1() throws Throwable { 
		vppurchaseOrderPageObject.purchaseOrderScreenS1(dataTable.getData(DataColumn.PrimaryFieldInstanceOne));
		//VPHomePage.poselect();
	}
	@Then("^RTS list save$")
	public void RTS_list_save() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//TPEPostMessagePageObject.poxmlData(dataTable.getData(DataColumn.SearchInputInstanceOne));
		tpedistributionorderpageobject.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		vprtslistpageobject.datechanges();
		vprtslistpageobject.rtsreadytoship();
	}
	@Then("^RTS list save vp$")
	public void RTS_list_save_vp() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//TPEPostMessagePageObject.poxmlData(dataTable.getData(DataColumn.SearchInputInstanceOne));
		vprtslistpageobject.rtsreadytoshipvp();
	}
	@Then("^RTS list Validations$")
	public void RTS_list_Validations() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//TPEPostMessagePageObject.poxmlData(dataTable.getData(DataColumn.SearchInputInstanceOne));
		tpedistributionorderpageobject.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		vprtslistpageobject.calculations(TPEDistributionOrderPageObject.OrderQty);
		vprtslistpageobject.rtsQuantity();
		vprtslistpageobject.rtsreadytoship();
	}
	@Then("^RTS Quantity Changes$")
	public void rts_Quantity_Changes() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//TPEPostMessagePageObject.poxmlData(dataTable.getData(DataColumn.SearchInputInstanceOne));
		//tpedistributionorderpageobject.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		//vprtslistpageobject.calculations(TPEDistributionOrderPageObject.OrderQty);
		vprtslistpageobject.rtsQuantity();
		vprtslistpageobject.rtsreadytoship();
	}
	@Then("^RTS Quantity Limit changes$")
	public void RTS_Quantity_Limit_changes() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//TPEPostMessagePageObject.poxmlData(dataTable.getData(DataColumn.SearchInputInstanceOne));
		tpedistributionorderpageobject.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		vprtslistpageobject.add(TPEDistributionOrderPageObject.OrderQty);
		vprtslistpageobject.rtsQuantityLimit();
		vprtslistpageobject.rtsreadytoship();
		vprtslistpageobject.limitAlert();
	}
	@Then("^RTS capacity and cubing trailer changes$")
	public void RTS_capacity_and_cubing_trailer_changes() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpedistributionorderpageobject.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		//vprtslistpageobject.rtsQuantity();
		vprtslistpageobject.rtsCapacity();
		vprtslistpageobject.rtsCubingTrailer();	
		vprtslistpageobject.rtsreadytoship();
	}
	@Then("^RTS Acknowledgement$")
	public void RTS_Acknowledgement() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vprtslistpageobject.rtsacknowledgement();
	
		
	}
	@And("^RTS Verifications$")
	public void RTS_Verifications() throws Throwable { 
		vprtslistpageobject.rtsVerifications();
		tpepurchaseorderpageobject.doTabValidation();
		
	}
	
	@And("^RTS Verification$")
	public void RTS_Verification() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.checkboxselection();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.rtsPickUpStart();
		vprtslistpageobject.rtsNumber();
		vprtslistpageobject.rtsClose();
		vppurchaseOrderPageObject.checkboxselection2();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.rtsPickUpStart();
		vprtslistpageobject.rtsNumber();
		vprtslistpageobject.rtsClose();
		

	}
	@And("^RTS Quantities Verification$")
	public void RTS_Verification_Quantities() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.checkboxselection();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.rtsNumber();
		vprtslistpageobject.rtsQuantities();
		vprtslistpageobject.rtsClose();
		vppurchaseOrderPageObject.checkboxselection2();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.rtsNumber();
		vprtslistpageobject.rtsQuantities();
		vprtslistpageobject.rtsClose();
		

	}
	@And("^RTS Capacity and Volume Verification$")
	public void RTS_Capacity_and_Volume_Verification() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.checkboxselection();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.rtsNumber();
		vprtslistpageobject.viewRTS();
		vprtslistpageobject.poDetailRTS();
		vprtslistpageobject.lineItems();
		vprtslistpageobject.calculations(TPEDistributionOrderPageObject.PlannedWeight);
		vprtslistpageobject.rtsWeight();
		vprtslistpageobject.calculations(TPEDistributionOrderPageObject.PlannedVolume);
		vprtslistpageobject.rtsVolume();
		//vprtslistpageobject.rtsQuantities();
		//vprtslistpageobject.rtsClose();
	}
	
	@And("^Two RTS Validations$")
	public void two_RTS_Validations() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		vppurchaseOrderPageObject.checkboxselection();
		vprtslistpageobject.rtsVerification();
		vprtslistpageobject.twortsNumber();
		//vprtslistpageobject.viewRTS();
	}
	
	
}
