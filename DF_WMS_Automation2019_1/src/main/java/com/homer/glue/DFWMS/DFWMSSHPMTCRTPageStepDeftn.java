package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSShpmtcreationPageObject;
import com.homer.po.DFWMS.DFWMSoLPNsPageObject;

public class DFWMSSHPMTCRTPageStepDeftn extends BaseStepDefn {
	//static String AppointmentID,Generated_Shipment_ID,DistrOrder,Wave_Number,Task_ID,oLPN_ID,AutoTote ;
	public static String Generated_Shipment_ID;
	public String Generated_Shipvia;
	//public String Generated_Shipment_ID,Generated_Shipvia;
	public DFWMSSHPMTCRTPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	@Then("^Shipment Creation$")
	public void LTL_Shipment_Creation() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		//Generated_Shipvia = dfwmsolpnspageobject.getshipvia();
		//System.out.println("Generated_Shipvia in shipment creation glue" +DFWMSoLPNsPageStepDeftn.Generated_Shipvia);
		Generated_Shipment_ID = DFWMSShpmtcreationPageObject.shpmt_creation_validation(DFWMSoLPNsPageObject.soLPNs);
		//Generated_Shipment_ID = DFWMSShpmtcreationPageObject.shpmt_creation();
		System.out.println("Generated_Shipment_ID glue:"+Generated_Shipment_ID);
	}
}
