package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.TPEPostMessagePageObject;

public class TPEShipmentDetailPageStepDeftn extends BaseStepDefn{

	public TPEShipmentDetailPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@And("^Validate Shipment Status in Shipment Details screen is \"(.*?)\"$")
	public void validate_Shipment_Details_screen(String ShipDtlStatus) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  tpeshipmentdetailpageobject.shipmentDetailsStatus(ShipDtlStatus);
	}
	
	@And("^Select and Add Tracking Message for Stop \"(.*?)\" and Message Type \"(.*?)\"$")
	public void select_Tracking_Message(String Stop, String Type) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentdetailpageobject.moreSelectionBtn_TrackingMsg();
		tpeshipmentdetailpageobject.Click_TrackingMsg(Stop,Type);
		tpeshipmentdetailpageobject.OverideCode();
	}
	
	@Then("^Validate Tracking Message for Message Type \"(.*?)\"$")
	public void validate_Tracking_Message(String MsgType) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentdetailpageobject.validate_shipment_trackingmessage(MsgType);
	}
	
	@Then("^Validate Tracking Message for XML Message Type$")
	public void validate_Tracking_Message_for_XML_Message_Type() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  tpeshipmentdetailpageobject.moreSelectionBtn_TrackingMsg();
	  String MsgType = TPEPostMessagePageObject.MessageType;  
	  tpeshipmentdetailpageobject.validate_shipment_trackingmessage(MsgType);
	}
	
	@Then("^\"(.*?)\" Stops under Shipment Detail$")
	public void validate_Stops_under_Shipment_Detail(String Validator) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentdetailpageobject.validateStops(Validator);
	}
	
		@And("^Clear Rejected Carrier$")
	public void clear_Rejected_Carrier() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeshipmentdetailpageobject.moreSelectionBtn_TenderDtls();
		tpeshipmentdetailpageobject.clear_RejectedCarriers();
	}

}