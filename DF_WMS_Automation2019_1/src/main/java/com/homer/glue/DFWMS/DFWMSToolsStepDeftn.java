package com.homer.glue.DFWMS;

import java.util.HashMap;
import java.util.Map;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;

public class DFWMSToolsStepDeftn extends BaseStepDefn{

	public Map<String, String> itemMap, dataMap = new HashMap<String, String>();
	public String flow = " " ;
	public DFWMSToolsStepDeftn(DataClass data) {
		super(data);
	}

	/*@Then("^Read SKU Data for \"(.*?)\"$")
	public void read_SKU_Data_for_arg1(String DC) throws Throwable { 
		itemMap = dfwmsToolsPageObject.readSkuDataExcel(DC);
	}
	 */
	@Then("^Read SKU Data for \"(.*?)\"$")
	public void read_SKU_Data_for_arg1(String DC) throws Throwable { 
		itemMap = dfwmsToolsPageObject.readSkuDataExcel(DC);
	}



	@Then("^Validate and update item zone$")
	public void validate_and_update_item_zone() throws Throwable { 
		dfwmsToolsPageObject.validateAndUpdateItemZone(itemMap);
	}

	@Then("^Get dock door details to checkout$")
	public void get_dock_door_details_to_checkout() throws Throwable { 
		dfwmsToolsPageObject.getDockDoorDetails();
	}


	@Then("^Setup Lane for \"(.*?)\"$")
	public void setup_Lane_for_arg1(String flow) throws Throwable { 
		dfwmsToolsPageObject.getLaneDetailsFromDB(flow);
	}

	@Then("^Setup Dockdoor for \"(.*?)\"$")
	public void setup_Dockdoor_for_arg1(String flow) throws Throwable { 
		dfwmsToolsPageObject.getParcelDockDoorDetails(flow);
	}


	@Then("^Adjust Item Inventory$")
	public void adjust_Item_Inventory() throws Throwable { 
		dfwmsToolsPageObject.adjustItemInventory(itemMap);
	}

	@Then("^Read excel$")
	public void read_excel() throws Throwable { 
		dataMap = dfwmsToolsPageObject.readExcel(); 
	}

	@Then("^Read excel and update xml$")
	public void read_excel_and_update_xml() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dataMap=dfwmsToolsPageObject.readExcelAndUpdateXML();
	}

	@Then("^Inbound Receive RF UI$")
	public void inbound_Receive_RF_UI() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsInboundReceiveInPuttyPageObject.inboundRcv();
	}
	
	
	@Then("^Outbound PCLFluidLoad RF menu UI \"(.*?)\" and \"(.*?)\"$")
	public void outbound_PCLFluidLoad_RF_menu_UI_arg1_and_arg2(String flow, String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsInboundReceiveInPuttyPageObject.outboundInPutty(flow,screen);

	}

	@Then("^Inbound Putaway UI \"(.*?)\"$")
	public void inbound_Putaway_UI_arg1(String flow) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsInboundReceiveInPuttyPageObject.inboundPutaway(flow);
	}
}