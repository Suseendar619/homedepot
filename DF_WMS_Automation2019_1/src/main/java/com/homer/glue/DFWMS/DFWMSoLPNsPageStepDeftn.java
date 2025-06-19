package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;

public class DFWMSoLPNsPageStepDeftn extends BaseStepDefn {

	public DFWMSoLPNsPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	public String oLPN;
	/*@When("^Input and Apply oLPNs$")
	public void input_And_Apply_oLPNS() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsolpnspageobject.oLPNInputDOandSearch(DFWMSDORCanonicalPostPageObject.poNBR, "", "");
	//	dfwmsolpnspageobject.oLPNInputDOandSearch("0916190C");
		Thread.sleep(4000);
	}*/
	
	@Then("^Validate oLPN Status \"(.*?)\"$")
	public void validate_oLPN_Status(String sStatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsolpnspageobject.ValidateoLPNStatus(sStatus,DFWMSInbounfFlowStepDefn.doId);
		
//		dfwmsolpnspageobject.ValidateoLPNStatus(sStatus,"SV290104");
	}
	
	@Then("^Clear Array List$")
	public void Clear_Array_List() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsolpnspageobject.ClearoLPNArrayList();
		dfwmstaskspageobject.ClearTaskArrayList();
	}

}
