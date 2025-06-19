package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSPckstationPageObject;
import com.homer.po.DFWMS.DFWMSoLPNsPageObject;

public class DFWMSPackingstationPageStepDeftn extends BaseStepDefn{

	public DFWMSPackingstationPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	@Then("^Mobile Packing Station$")
	public void Mob_Packing_Station() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		//DFWMSPckstationPageObject.MobilePckStation();
		DFWMSPckstationPageObject.Packing_station_validation5(DFWMSoLPNsPageObject.soLPNs);
		System.out.println("print in packing station glue"+DFWMSoLPNsPageObject.soLPNs.get(0).toString());

	}
	
	@Then("^Suspend oLPN and Hospital Packing$")
	public void suspend_oLPN_and_Hospital_Packing() throws Throwable { 
	//	DFWMSPckstationPageObject.Packing_station_validation5(DFWMSoLPNsPageObject.soLPNs);
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSPckstationPageObject.Packing_station_suspend_validation(DFWMSoLPNsPageObject.soLPNs,DFWMSoLPNsPageObject.soLPNstatus);
	}

}
