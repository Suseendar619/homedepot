package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSoLPNsPageObject;
import com.homer.po.DFWMS.DFWMSShpmtcreationPageObject;

public class DFWMSAPPTCREATIONPageStepDeftn extends BaseStepDefn {
	public String AppointmentID;
	public String Appointmenttype = "Drop Empty";
	public String Equipmentcode = "28";
	//public String AppointmentID1 = "131001197013";
	public DFWMSAPPTCREATIONPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^Appointment Creation$")
	public void DF_OB_NP_APPT_Creation() throws Throwable {
		
		AppointmentID = DFWMSAPPTCREATIONPageObject.DF_OB_APPT_CREATION(Appointmenttype,Equipmentcode,DFWMSSHPMTCRTPageStepDeftn.Generated_Shipment_ID);
		System.out.println("Generated_Shipvia in shipment creation glue" +DFWMSSHPMTCRTPageStepDeftn.Generated_Shipment_ID);
		System.out.println("Generated_Appointment_ID glue: " +AppointmentID);
		
	}
	
	@Then("^DF_OB_NP_CheckIn$")
	public void dF_CheckIn() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//DFWMSAPPTCREATIONPageObject.DFOBCheckInNavigation(AppointmentID);
		DFWMSAPPTCREATIONPageObject.DFOBCheckInNavigation(AppointmentID);
		//DFWMSAPPTCREATIONPageObject.DF_CheckIn_Fill_Details(dataTable.getData(DataColumn.Fname), dataTable.getData(DataColumn.Lname), dataTable.getData(DataColumn.LicenseNumber), dataTable.getData(DataColumn.Carrier), dataTable.getData(DataColumn.Statelist), dataTable.getData(DataColumn.Strtrailer), dataTable.getData(DataColumn.DockDoor));
		DFWMSAPPTCREATIONPageObject.DF_CheckIn_Fill_Details();
	}
	
}
