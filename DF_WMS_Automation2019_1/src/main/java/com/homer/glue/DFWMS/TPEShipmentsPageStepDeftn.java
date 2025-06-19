package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.Common;
import com.homer.po.DFWMS.TPEDistributionOrderPageObject;
import com.homer.po.DFWMS.TPEHomePageObject;
import com.homer.po.DFWMS.TPEPostMessagePageObject;
import com.homer.po.DFWMS.TPEPurchaseOrderPageObject;
import com.homer.po.DFWMS.TPEShipmentsPageObject;
import com.homer.po.DFWMS.TPEAppointmentsPageObject;


public class TPEShipmentsPageStepDeftn extends BaseStepDefn{

	public static String shipmentId;
	
	public TPEShipmentsPageStepDeftn(DataClass data) {
		super(data);
		common = new Common(ic);
	}
	
	Common common;
	@When("^Filter with Shipment \"(.*?)\"$")
	public void filter_with_Shipment_PrimaryFieldInstanceThree(String ShipmentSearch) throws Throwable { 
		
		// Search for shipment ID
		tpeshipmentspageobject.shipmentIdSearch(ShipmentSearch, shipmentId);
		
		// Click 'Apply' button 
		tpeshipmentspageobject.shipmentIdApplied();
	}

	/*@When("^Filter with Shipment$")
	public void filter_with_Shipment() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentIdSearch(dataTable.getData(DataColumn.PrimaryFieldInstanceThree));		
	}*/
	
	@And("^Select the created shipment$")
	public void select_the_created_shipment() throws Throwable { 
		
		// Click checkbox associated with shipment ID
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
	}

	@And("^Promote the shipment to Resource selection$")
	public void promote_the_shipment_to_Resource_selection() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn();
		tpeshipmentspageobject.promoteShipment();
	}
	
	@And("^Promote the shipment and Status Check$")
	public void promote_the_shipment_and_Status_Check() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.resourceSelectionBtn();
		tpeshipmentspageobject.promoteShipmentAfterEngRun();
	}
	
	@And("^Promote the shipment and Status Check for Fleet$")
	public void promote_the_shipment_and_Status_Check_for_Fleet() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.resourceSelectionBtn();
		tpeshipmentspageobject.promoteShipmentAfterEngRunFleet();
	}
	
	@And("^Cancel the shipments$")
	public void cancel_the_shipments() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentStatusRefreshButton(); 
		runtemplatepageobject.shipmentSelect();
		tpeshipmentspageobject.shipmentstatus();
		
		
	/*	runtemplatepageobject.shipmentSelect();
		tpeshipmentspageobject.cancelshipments();
		tpeshipmentspageobject.cancelOrder();
		runtemplatepageobject.shipmentSelect();
		tpeshipmentspageobject.cancelOrderVerify();*/
	}
	
	@And("^Cancel the shipments for ConsXD$")
	public void cancel_the_shipments_for_ConsXD() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentStatusRefreshButton(); 
		tpeshipmentspageobject.shipmentstatusrowcheck1();
		tpeshipmentspageobject.shipmentstatusrowcheck2();
		//tpeshipmentspageobject.cancelorderverifyconxd();
	}
	
	@Then("^Close the shipment screen$")
	public void close_the_shipment_screen() throws Throwable { 
		
		// Click checkbox associated with shipment ID
		/*tpehomepageobject.TPEmenu();		
		tpehomepageobject.searchInput("Shipments","Transportation Lifecycle Management");*/
		tpeshipmentspageobject.CloseShipmentScreen();
	}

	@And("^Unassign Shipment$")
	public void unassign_Shipment() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn_Unassign(); 
		
	  
	}

	@Then("^Shipment Status is \"(.*?)\"$")
	public void shipment_Status_is_Assigned(String Assign) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentStatusCheck(Assign);
	}
	
	@When("^Shipment is Tendered to Carrier$")
	public void shipment_is_Tendered_to_Carrier() throws Throwable { 
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn_Tender();
	}
	
	@When("^Shipment is Assigned to Carrier \"(.*?)\"$")
	public void shipment_is_Assigned_to_Carrier(String Carrier) throws Throwable { 
		
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.tenderDetailsSelection_Icon();
		tpeshipmentspageobject.resetShipmentTender_Screen();
		tpeshipmentspageobject.shipmentTender_manualAssignCarrier(Carrier);
	}
	
	@Given("^Tendered Shipment is Accepted by Carrier$")
	public void tendered_Shipment_is_Accepted() throws Throwable { 
				
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn_Accept();
	}
	
		
	@When("^Open Shipment Details$")
	public void open_Shipment_Details() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.doubleClickShipment();
	}
	
	@When("^Shipment is Recalled$")
	public void shipment_is_Recalled() throws Throwable { 
		
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn_Recall(); 
	}
	
	@When("^Shipment is Rejected$")
	public void shipment_is_Rejected() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.resourceSelectionBtn_Reject(); 
	}
	
	@Then("^Create appointment$")
	public void create_appointment() throws Throwable { 
		
		// Click checkbox associated with shipment ID 
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		
		// Click 'Create Appointment' link
		tpeshipmentspageobject.clickCreateAppointmentLink();
		

		// Select appointment slot
		Boolean slotSelected = tpeappointmentspageobject.selectAppointmentSlot(2, false);
		
		// If Appointment slots are not visible, then skip every other step
		if (slotSelected){
			
			// Click 'Select' on Recommendations dialog
			tpeappointmentspageobject.clickSelectButton_RecommendationsDialog();
			
			// Click 'Save' button on Appointments page
			tpeappointmentspageobject.clickSaveButton_AddAppointment();
			
			// Save all appointment details. Save appointment ID in
			// appointments page glue file to be used for appointment test cases.
			// Also, save entire page to be used by steps in the Appointments test cases
			tpeappointmentspageobject.saveAllAppointmentDetails();
			String appointmentId = tpeappointmentspageobject.getAppointmentId();
			TPEAppointmentsPageStepDeftn.setAppointmentId(appointmentId);
			TPEAppointmentsPageStepDeftn.setShipperAppointmentPage(tpeappointmentspageobject);
			
		}		
		
	}
	
	@And("^Close appointment window$")
	public void close_appt_window() throws Throwable {
		// Close appointments window
		tpeappointmentspageobject.closeAppointmentWindow();
	}
	
		@Then("^Create the appointment$")
	public void create_the_appointment() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions
		tpeshipmentspageobject.shipmentIDCheckbox(shipmentId);
		tpeshipmentspageobject.create_appointment();
		
	}
	
	@And("^Show appointment$")
	public void show_appointment() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.show_appointment();
	}
	
	@Given("^Move Shipment to MCW via Manual Planning$")
	public void move_Shipment_to_MCW_via_Manual_Planning() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.manualPlanningBtn_movetoMCW();
		tpeshipmentspageobject.doubleClickShipmentID();
		
	}
	
	
	@When("^Change Delivery Sequence and Validate$")
	public void change_Delivery_Sequence_and_Validate() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.changeDeliverySequence();
		tpeshipmentspageobject.MovetoMCWB_SelectOption("Validate");
	  
	}

	@And("^Confirm Delivery Sequence, Save and Accept$")
	public void confirm_Delivery_Sequence_Save_and_Accept() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions
		tpeshipmentspageobject.validateMCWStops();
		tpeshipmentspageobject.MovetoMCWB_SelectOption("Save and Accept");
		tpeshipmentspageobject.Close_ConsolidationWorkspace();
		
	}

	@And("^Search for shipment ID in Transactions screen$")
	public void search_for_shipment_ID_in_Transactions_screen() throws Throwable { 

		// Click 'Expand' Filter button
		tpetransactionspageobject.clickExpandOptionsButton();
		
		// Enter 'From' time
		
		tpetransactionspageobject.enterFromDateTime("Last One Hour");
		
		// Enter 'To' time
		
		tpetransactionspageobject.enterToDateTime("Now");
		
		// Set Message Type
		
		tpetransactionspageobject.selectMessageType("Routing_Instruction");
		
		// Set Shipment ID in 'Object ID' field

		tpetransactionspageobject.enterObjectId(shipmentId);
		
		// Click Apply (and wait for transaction to appear)
		tpetransactionspageobject.clickApplyButton();
		
		// Ensure that only one row appears
		Integer rowSize = tpetransactionspageobject.getRowSize();
		common.compareIntegers(1, rowSize, "Number of rows in 'Transactions' table", true);
	}

	@And("^Validate Response in Transactions table$")
	public void validate_Response_in_Transactions_table() throws Throwable { 

		// Double click on row and wait for screen to appear
		tpetransactionspageobject.doubleClickFirstRow();
		
		// Get Response XML
		String xml = tpetransactionspageobject.getResponseXml();
		
		// Check response XML for appropriate response code and errorType
		tpepostmessagepageobject.verifyResponseCode(xml);
		tpepostmessagepageobject.verifyErrorType(xml);
	}
	
	@Then("^Validate Shipment for Rejection$")
	public void validate_Shipment_for_Rejection() throws Throwable { 
	// Write code here that turns the phrase above into concrete actions 
		tpeshipmentspageobject.validate_RejectedCarriers();
		
	}

	@And("^Move shipment to the \"(.*?)\" status$")
	public void move_shipment_to_status(String desiredStatus) throws Throwable { 
		//Change Carrier to be manually assigned based on life cycle
		tpeshipmentspageobject.moveToStatus(desiredStatus, shipmentId,"UPSG");
	 }
	
	@And("^Move shipment to \"(.*?)\" status$")
	public void move_shipment_to_Tendered_status(String desiredStatus) throws Throwable { 
		//Change Carrier to be manually assigned based on life cycle
		tpeshipmentspageobject.moveToStatus(desiredStatus, shipmentId,"HJCS");
	 }
	
	@And("^Wait for transactions system to get updated$")
	public void wait_for_transactions_system_to_get_updated() throws Throwable { 

		Thread.sleep(30000);
	}
	/**
	 * This function gets the shipment ID used across multiple glue files in
	 * a scenario.
	 * @return
	 */
	public static String getShipmentId(){
		return shipmentId;
	}
	
	/**
	 * This function sets the shipment ID to be used across multiple glue files
	 * in a scenario 
	 * 
	 * @param id
	 */
	public static void setShipmentId(String id){
		 shipmentId = id;
	}
	@And("^Search in Transactions screen$")
	public void search_in_Transactions_screen() throws Throwable { 

		// Click 'Expand' Filter button
		tpetransactionspageobject.clickExpandOptionsButton();
		
		// Enter 'From' time
		
		tpetransactionspageobject.enterFromDateTime("3/9/17 12:00");
		
		// Enter 'To' time
		
		tpetransactionspageobject.enterToDateTime("3/10/17 12:00");
		
		// Set Message Type
		
		tpetransactionspageobject.selectMessageType("PurchaseOrder");
		
		// Set Shipment ID in 'Object ID' field

		tpetransactionspageobject.enterObjectId(shipmentId);
		
		// Click Apply (and wait for transaction to appear)
		tpetransactionspageobject.clickApplyButton();
		
		// Ensure that only one row appears
		Integer rowSize = tpetransactionspageobject.getRowSize();
		common.compareIntegers(1, rowSize, "Number of rows in 'Transactions' table", true);
	}
}
