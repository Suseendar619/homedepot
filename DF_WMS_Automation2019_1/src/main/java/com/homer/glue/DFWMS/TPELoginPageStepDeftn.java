package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.TPELoginPageObject.UserTypes;

public class TPELoginPageStepDeftn extends BaseStepDefn{
	
	public TPELoginPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
		
	/*@Given("^Login page$")
	public void login_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions		
		tpeloginpoobject.open();
	}
	
		@When("^Enter User name and Password$")
	public void enter_User_name_and_Password() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeloginpoobject.enterUserNameAndPassword();
	}

	@When("^Enter User name and Password for \"(.*?)\"$")
	public void enter_User_name_and_Password_for_userType(UserTypes type) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeloginpoobject.enterUserNameAndPassword(type);
	}
	
	@When("^Login as \"(.*?)\"$")
	public void login_as_userType(UserTypes type) throws Throwable {
		
		tpeloginpoobject.login(type);
	}
	
	@When("^Login as vendor$")
	public void login_as_vendor() throws Throwable{
		login_as_userType(UserTypes.VENDOR);
	}
	
	@When("^Login as shipper$")
	public void login_as_shipper() throws Throwable{
		login_as_userType(UserTypes.SHIPPER);
	}

	@When("^Login as carrier$")
	public void carrier() throws Throwable{
		login_as_userType(UserTypes.CARRIER);
	}
	
	@Then("^SignIn to application$")
	public void signIn_to_application() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeloginpoobject.sigInBtn();
	}
	
	@When("^Enter ([^\"]*) and ([^\"]*) for login$")
	public void Enter_Username_and_Password_for_login(String Username,String Password) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpeloginpoobject.loginsample(Username,Password);
	}
	
	@Given("^Login as WMUser$")
	public void login_as_WMUser() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		login_as_userType(UserTypes.SHIPPER);
	}

	@Given("^WMS home page$")
	public void wMS_home_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEpage();
	}*/

}
