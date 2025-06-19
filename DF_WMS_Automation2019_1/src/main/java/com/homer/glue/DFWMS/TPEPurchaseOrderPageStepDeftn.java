package com.homer.glue.DFWMS;

import org.openqa.selenium.By;
import org.w3c.dom.Node;

import com.homer.dao.And;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.glue.BaseStepDefn;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.Common;
import com.homer.po.DFWMS.TPEHomePageObject;
import com.homer.po.DFWMS.TPEPostMessagePageObject;
import com.homer.po.DFWMS.TPEPurchaseOrderPageObject;

public class TPEPurchaseOrderPageStepDeftn extends BaseStepDefn{

	public TPEPurchaseOrderPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
				
	@Given("^TOOpen \"(.*?)\" screen under \"(.*?)\" Module$")
	public void open_Purchase_Order_screen(String screen, String module ) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEmenu();		
		tpehomepageobject.searchInput(screen,module);
	}
	
		
	@Given("^Open Purchase Order screen$")
	public void open_Purchase_Order_screen() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEmenu();		
		tpehomepageobject.searchInput(TPEHomePageObject.SearchOptions.PURCHASEORDERS.getValue(), TPEHomePageObject.BoundListOptions.TLM.getValue());
			//tpehomepageobject.searchInput(dataTable.getData(DataColumn.SearchInputInstanceOne),dataTable.getData(DataColumn.BoundListOptionInstanceOne));
	}
	
	/*@When("^Filter with purchase order$")
	public void filter_with_purchase_order(String primaryfieldIput) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.purchaseOrderScreen(primaryfieldIput);
	}
	*/


	@When("^Filter with purchase order \"(.*?)\"$")
	public void filter_with_purchase_order_arg1(String POSearch) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.purchaseOrderScreen(POSearch);
		tpepurchaseorderpageobject.purchaseOrderApplied();
	  
	}
	@Then("^Apply and Verify generated purchase order$")
	public void apply_and_Verify_generated_purchase_order() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpepurchaseorderpageobject.purchaseOrderApplied();
		tpepurchaseorderpageobject.purchaseOrderSelectandView();	
	}
	@Then("^Open Purchase Order Details screen$")
	public void open_purchase_order_details_screen() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.purchaseOrderSelectandView();	
	}
	
	@And("^Close Purchase Order screen$")
	public void close_Purchase_Order_screen() throws Throwable { 

		Thread.sleep(3000);
		By locator = By.xpath("//*[contains(@class, 'x-tool-close') "
				+ "and parent::*[preceding-sibling::*[child::*[text()='Purchase Orders' and contains(@class, 'x-window-header-text')]]] "
				+ "and ancestor::*[not(contains(@id, 'ghost')) "
				+ "and contains(@class, 'x-window-closable')]]");
		driver.switchTo().defaultContent();
		
		wh.clickElement(locator);
		driver.switchTo().defaultContent();
	}
	
	@And("^Verify General tab$")
	public void verify_General_tab() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.verifyGeneralTab();		
	}

	@And("^Verify RTS tab$")
	public void verify_RTS_tab() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.rtsTabValidation();
	}

	@And("^Verify DO tab$")
	public void verify_DO_tab() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepurchaseorderpageobject.doTabValidation();
	}
	
	@And("^Verify Shipment ID in DO tab$")
	public void verify_Shipment_ID_in_DO_tab() throws Throwable { 

		tpepurchaseorderpageobject.doTabShipmentValidation();
		
		// Save Shipment ID to use in other scenarios
		String shipmentId = tpepurchaseorderpageobject.getShipmentId();
		TPEShipmentsPageStepDeftn.setShipmentId(shipmentId);
	}
	
	@And("^Verify no RTS has been created for po$")
	public void verify_no_RTS_has_been_created() throws Throwable { 

		verifyRTSCreated(false);
		
	}
	
	@And("^Verify that an RTS has been created for po$")
	public void verify_that_an_RTS_has_been_created_for_po() throws Throwable { 

		verifyRTSCreated(true);
	}
	
	@And("^Create DO if not already created$")
	public void create_DO_if_not_already_created() throws Throwable { 

		// If "Add to DO" button is enabled, create DO
		// Else, don't do anything
		tpepurchaseorderpageobject.selectPurchaseOrderFromTable();
		Boolean enabled = tpepurchaseorderpageobject.isAddToDOButtonEnabled();
		
		if (enabled){
			tpepurchaseorderpageobject.createDO();
		}
	}

	private void verifyRTSCreated(Boolean shouldBeCreated) throws Exception{
		
		// Click menu button
		tpehomepageobject.TPEmenu();
		
		// Search for "Purchase Orders" (for TLM)
		// Wait for screen to load
		
		tpehomepageobject.searchInput(TPEHomePageObject.SearchOptions.PURCHASEORDERS.getValue() , 
				TPEHomePageObject.BoundListOptions.TLM.getValue());
		
		// Select "Purchase Order" from dropdown menu in "Primary Fields section
		tpepurchaseorderpageobject.purchaseOrderScreen(TPEPurchaseOrderPageObject.PrimaryFields.PURCHASEORDER.getValue());
		
		// Click "Apply" and wait for PO screen to load
		
		tpepurchaseorderpageobject.purchaseOrderApplied();
		
		// Double click on PO
		
		tpepurchaseorderpageobject.purchaseOrderSelectandView();
		
		// Click "RTS" in PO detail screen
		
		tpepurchaseorderpageobject.clickRtsTab();
		
		// Get number of RTS rows from EDI753
		
		Integer expectedRowCount = tpepostmessagepageobject.getRtsCountFromEDI753();
		
		// Validate that the correct amount of RTS rows exist or doesn't exist
		
		tpepurchaseorderpageobject.validateCorrectNumberOfRtsRows(shouldBeCreated, expectedRowCount);
		
		
	}
	
	/**
	 * @param type
	 * @throws Throwable
	 */
	@And("^Validate quantity, weight, and volume in RTS is the same as in XML$")
	public void validate_quantity_weight_and_volume_in_RTS_is_the_same_as_in_XML() throws Throwable { 
				
		
		// Double-click RTS to open RTS Detail screen
		
		tpepurchaseorderpageobject.openRtsDetailScreen();
		
		// Click "Line Items" tab
		
		tpepurchaseorderpageobject.clickLineItemsTab_RTSDetail();
		
		// Get expected number of rows from XML
		Integer expectedRowCount = tpepostmessagepageobject.getRtsCountFromEDI753();
		
		// Validate correct number of 'Line Item' rows
		tpepurchaseorderpageobject.validateCorrectNumberOfRtsLineItemsRows(expectedRowCount);
		
		// For each row on screen, validate quantity, weight, and volume
		tpepurchaseorderpageobject.validateQuantityWeightAndVolume(expectedRowCount);
	}
}
