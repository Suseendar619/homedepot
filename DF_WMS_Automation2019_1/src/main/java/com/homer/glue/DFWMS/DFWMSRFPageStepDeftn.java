package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;

public class DFWMSRFPageStepDeftn extends BaseStepDefn {

	public DFWMSRFPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^Complete Picking Tasks$")
	public void complete_Picking_Tasks() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrfpageobject.completePickTasks();
	}
	
	@Then("^Load Trailer$")
	public void LOAD_TRAILER_NP() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		//int iArrayCount = DFWMSoLPNsPageObject.getoLPNCount();
		dfwmsrfpageobject.Load_trailer(DFWMSoLPNsPageObject.soLPNs,DFWMSoLPNsPageObject.solpnshpvia,DFWMSAPPTCREATIONPageObject.sDockdoorbarcode);
	}
	
	@Then("^RFputty newframework$")
	public void rFputty_newframework() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSPuttyNFPageObject.RFPutty_newframework(dfwmstaskspageobject.sTaskID.get(0).toString());
	}
}
