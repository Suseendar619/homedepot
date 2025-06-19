package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;

public class DFWMSHomePageStepDeftn extends BaseStepDefn {

	public DFWMSHomePageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Given("^Open \"(.*?)\" screen under \"(.*?)\" Module$")
	public void open_Menu_Screen(String screen, String module)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmshomepageobject.WMSmenu();
		dfwmshomepageobject.searchInput(screen, module);
	}
	
	@Given("^WMS home page$")
	public void wms_Home_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmshomepageobject.WMSpage();
	}
	
	@And("^Logout$")
	public void logout() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions
		dfwmsolpnspageobject.ClearoLPNArrayList();
		dfwmstaskspageobject.ClearTaskArrayList();
		dfwmshomepageobject.logout();
	}
	
	@Given("^QP User Creation$")
	public void QP_User_Creation() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmshomepageobject.QPUserCopyCreation();
	}

}
