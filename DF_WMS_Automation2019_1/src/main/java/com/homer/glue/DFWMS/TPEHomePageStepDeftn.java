package com.homer.glue.DFWMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;

public class TPEHomePageStepDeftn extends BaseStepDefn{

	public TPEHomePageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
			
	@Given("^TPE home page$")
	public void tPE_home_page() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEpage();		
	}
	
	@Given("^Navigate to Vendor Portal$")
	public void navigate_to_vendor_portal() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.navigateToVendorPortal();	
	}
	
	@And("^Navigate to Carrier Portal$")
	public void navigate_to_carrier_portal() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.navigateToCarrierPortal();	
	}
	
	@Given("^Navigate to MDA$")
	public void navigate_to_mda() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpehomepageobject.navigateToMDA();	
		tpepostmessagepageobject.UserLsampleRTS();
	}
	
//	@And("^Logout$")
//	public void logout() throws Throwable { 
//		
//		tpehomepageobject.logout();
//	}
	@Given("^DB Test$")
	public void db_Test() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//Sample.dbtest();		
	}
	
}
