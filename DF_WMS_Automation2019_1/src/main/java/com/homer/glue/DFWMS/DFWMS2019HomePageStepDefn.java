package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSHomePageObject2019;

public class DFWMS2019HomePageStepDefn extends BaseStepDefn{

	public DFWMS2019HomePageStepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	
	@Then("^Open \"(.*?)\" screen under \"(.*?)\"$")
	public void open_arg1_screen_under_arg2(String screen, String module) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
//		dfwms2019HomePageObject.WMSmenu(screen, module);
		dfwmsHomePageObject2019.WMSmenu();
		dfwmsHomePageObject2019.searchInput(screen, module);

	}
	
	@Then("^Open \"(.*?)\" (\\d+) screen under \"(.*?)\"$")
	public void open_arg1_2012_screen_under_arg2(String screen, String module) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsHomePageObject2019.WMSmenu2012();
		dfwmsHomePageObject2019.searchInput2012(screen, module);

	}

	@Then("^Enter Value and scan Olpn$")
	public void enter_Value_and_scan_Olpn() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.enterValueandScanOlpn();
	}


	
	@Then("^Post xml$")
	public void post_xml() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwms2019PostXmlPageObject.postXML();
	}
	
}
