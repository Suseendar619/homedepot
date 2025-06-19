package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;

public class DFWMSLoginPageStepDeftn extends BaseStepDefn {

	public DFWMSLoginPageStepDeftn(DataClass data) {
		super(data);
	}

	@Given("^Login as WMUser$")
	public void login_as_WMUser() throws Throwable {
		dfwmsloginpoobject.login();
	}
	
	
	@Then("^Login as WynUser$")
	public void login_as_WynUser() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsloginpoobject.wynLogin();

	}
	
	@Then("^Login as PacksizeUser$")
	public void login_as_PacksizeUser() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsloginpoobject.packSizeLogin();
	}

	@Then("^Login as PacksizeDallasUser$")
	public void login_as_PacksizeDallasUser() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsloginpoobject.packSizeDallasLogin();

	}

	@Then("^Login as WESUser$")
	public void login_as_WESUser() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsloginpoobject.wesLogin();

	}


	@Then("^Clock in LM user$")
	public void clock_in_LM_user() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsloginpoobject.clockIn();

	}
	
	@Then("^Clock Out LM user$")
	public void clock_Out_LM_user() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsloginpoobject.clockOut();

	}
	@Given("^Login for Wynsoft$")
	public void login_for_Wynsoft() throws Throwable { 
		dfwmsloginpoobject.loginWynsoft();
	}

	@Given("^Login as RFUser$")
	public void login_as_RFUser() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsloginpoobject.RFLogin();
	}
	
	@Given("^RF to WMS HomePage$")
	public void rF_to_WMS_HomePage() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsloginpoobject.RFtoWMSHomePage();
	}

}
