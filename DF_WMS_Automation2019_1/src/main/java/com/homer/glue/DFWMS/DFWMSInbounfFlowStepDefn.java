package com.homer.glue.DFWMS;

import java.util.ArrayList;
import java.util.List;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSPOObject;
import com.homer.po.DFWMS.DFWMSValidationsPageObject;

public class DFWMSInbounfFlowStepDefn extends BaseStepDefn{

	public static String poId;
	public String palletID;
	public static String doId;
	public static String ASNID, taskID;
	public String itemName;
	public static String ShipmentIdInbound = "";
	public String AppointmentID;
	public static String DockDoorNum;
	int ActualQty,FinalQty;
//	public String DockDoorNum = "2583001";
//	public String AppointmentID = "4610011870";
//	public static String ASNID = "M000001264";
//	public String poId = "PO11215833";
	public String Appointmenttype = "Live Load";
	public String Equipmentcode = "28";
	public List<String> LPNS,xmlData = new ArrayList<String>();
	public static List<String> ItemNames = new ArrayList<String>();
	public static List<String> doIds = new ArrayList<String>();
	public List<Integer> Qty = new ArrayList<Integer>();
	
	public DFWMSInbounfFlowStepDefn(DataClass data) {
		super(data);
	}
	@Then("^Create PO xml \"(.*?)\"$")
	public void create_PO_xml(String screen) throws Throwable {
	 // Write code here that turns the phrase above into concrete actions 
		poId = "";
        poId = dfwmsPOObject.createPOXml(screen);
	}
	
	/*@Then("^Validate item zone for \"(.*?)\"$")
	public void validate_item_zone_for_arg1(String zone) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.validateZone(zone,ItemNames);
	}*/
	
	@Then("^Validate ItemZone in \"(.*?)\" for \"(.*?)\"$")
	public void validate_ItemZone_in_arg1_for_arg2(String locnzone, String skuzone) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.validateZone(locnzone,skuzone,ItemNames);
	}
	
	
	@Then("^Validate iLPNs and oLPNs status$")
	public void validate_iLPNs_and_oLPNs_status() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.validateLPNStatus();
	}
	
	@Then("^DO \"(.*?)\" for \"(.*?)\"$")
	public void dO_arg1_for_arg2(String doIdNum, String screen) throws Throwable { 
		if(screen.equalsIgnoreCase("BVRSample")){
			doId = doIdNum;
		}
	}
	@Then("^Create DO xml \"(.*?)\"$")
	public void create_DO_xml_arg1(String screen) throws Throwable { 
		doId = "";
		doId = dfwmsPOObject.createDOXml(screen);
		//doIds = new ArrayList<String>();
		 if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")
				 ||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")
				 || screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")
				 || screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")
				 || screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				 || screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				 || screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
				 || screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
				 || screen.equalsIgnoreCase("Envelop_SingleTote1_Dallas")
				 || screen.equalsIgnoreCase("Envelop_SingleTote1_Baltimore")
				 || screen.equalsIgnoreCase("MDO_MutliStop")
				 || screen.equalsIgnoreCase("BVR_MutliStop")
				 || screen.equalsIgnoreCase("BVR_MutliStop_Houston")
				 || screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				 || screen.equalsIgnoreCase("BVR_Houston_MutliStop")
				 || screen.equalsIgnoreCase("MDO_Dallas_MutliStop")
				 || screen.equalsIgnoreCase("Envelop_Dallas")
				 || screen.equalsIgnoreCase("Envelop_Baltimore")
				 || screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
				 || screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
				 || screen.equalsIgnoreCase("BVR_Newark_UndoDO")
				 || screen.equalsIgnoreCase("BVR_Baltimore_UndoDO_Packed")
				 || screen.equalsIgnoreCase("BVR_Newark_UndoDO_Packed")
				 || screen.equalsIgnoreCase("LTLOutboundLacey")
				 || screen.equalsIgnoreCase("LTLMultiStopLacey")
				 ||screen.equalsIgnoreCase("UPS_Houston_MISP")
				 ||screen.equalsIgnoreCase("BK1NonShipReady_UndoDO")
				 ||screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
				 ||screen.equalsIgnoreCase("BVR_Houston_UndoDO")
				 ||screen.equalsIgnoreCase("LTL_Lacey_SplitShipment1")
				 ||screen.equalsIgnoreCase("LTL_Lacey_SplitShipment2")
				 ||screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
				 ||screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				 ||screen.equalsIgnoreCase("UPS_Dallas_WM09")
				 ||screen.equalsIgnoreCase("FGND_Dallas_WM09")
				 ||screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")){
		 }else{
			 doIds = new ArrayList<String>();
		 }
		 /*if(doIdsFlag){
			 doIds = new ArrayList<String>();
		 }*/
		 doIds.add(doId);
	}
	
	@Then("^Ship Confirm Rules for \"(.*?)\"$")
	public void ship_Confirm_Rules_for_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.shipConfirm(doId, screen);
	}
	
	@Then("^Ship Confirm Rules for Anchor \"(.*?)\"$")
	public void ship_Confirm_Rules_for_Anchor_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		
		dfwmsPOObject.shipConfirmUsingOrderId(doId, screen);

		
	}

	@Then("^Create and post xml for \"(.*?)\" for \"(.*?)\" times$")
	public void create_and_post_xml_for_arg1_for_arg2_times(String flow, String count) throws Throwable { 
		dfwmsPOObject.createAndPostXml(flow, count);
	}
	
	
	@Then("^Create and post xml for (\\d+) \"(.*?)\" for \"(.*?)\" times$")
	public void create_and_post_xml_for_2012_arg1_for_arg2_times(String flow, String count) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.createAndPostXml2012(flow, count);

	}
	
	@Then("^Create and post xml for (\\d+) \"(.*?)\"$")
	public void create_and_post_xml_for_2012_arg1(String flow) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.createAndPostXml2012(flow);

	}



	/*@Then("^Read excel and update xml$")
	public void read_excel_and_update_xml() throws Throwable { 
		dfwmsPOObject.readAndUpdate();
	}*/
	
	@Then("^Read excel and update xml for \"(.*?)\"$")
	public void read_excel_and_update_xml_for_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//dfwmsPOObject.readAndUpdateXML();
		dfwmsPOObject.readAndUpdate(screen);
	}
	
	@Then("^Post PO xml \"(.*?)\"$")
	public void post_PO_xml(String screen) throws Throwable {
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.postPOXml(screen);
	}
	
	@Then("^Checkout$")
	public void checkout() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.checkout();
	}
	
	@Then("^Read excel and write xml$")
	public void read_excel_and_write_xml() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPOObject.updateXMLData();
	}
	
	@Then("^Read excel and write xml LG$")
	public void read_excel_and_write_xml_LG() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		
		dfwmsPOObject.updateXMLDataLG();

	}
	
	@Then("^Post Multiple xml$")
	public void post_Multiple_xml() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsPOObject.multiPostxml();

	}


	@Then("^Get \"(.*?)\" from xml \"(.*?)\"$")
	public void get_arg1_from_xml_arg2(String data, String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		xmlData = dfwmsPOObject.getXmlData(data, screen);
		if(data.equalsIgnoreCase("ItemName")){
			ItemNames = xmlData;
		}
	}
	
	@Then("^Search \"(.*?)\" and Validate Status \"(.*?)\"$")
	public void search_PO_and_Validate_PO_Status_arg1(String Search, String sStatus) throws Throwable {
	 // Write code here that turns the phrase above into concrete actions 
		dfwms2019IBPOFormatPostPageObject.input_search(Search,poId, ASNID);
		dfwms2019IBPOFormatPostPageObject.ValidateStatus(Search,sStatus,poId, ASNID);
	  
	}
	
	@Then("^Search \"(.*?)\" and Validate Status \"(.*?)\" for \"(.*?)\"$")
	public void search_arg1_and_Validate_Status_arg2_for_arg3(String Search, String sStatus, String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwms2019IBPOFormatPostPageObject.input_search(Search,"", DFWMSPOObject.asnId);
		dfwms2019IBPOFormatPostPageObject.ValidateStatus(Search,sStatus,"", DFWMSPOObject.asnId);
	}
	
	@Then("^Auto Receive$")
	public void auto_Receive() throws Throwable { 
		dfwmsPOObject.autoReceive();
	}
	
	@Then("^Map ASN and PO$")
	public void map_ASN_and_PO() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		ASNID = dfwms2019IBPOFormatPostPageObject.ValidateASNPOMap(poId);
		dfwms2019IBPOFormatPostPageObject.POASNMapping(ASNID);
	}
	
	@Then("^Apply ASN$")
	public void apply_ASN() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
//		DFWMSIBPOFormatPOSTPageObject.InputASNApply(ASNID);
		dfwms2019IBPOFormatPostPageObject.input_search("ASN",poId, ASNID);

	}

	@Then("^Validate ASN_Status \"(.*?)\"$")
	public void validate_ASN_Status_arg1(String sStatus) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.ValidateASNStatus(sStatus, ASNID);
	}
	
	@Then("^Schedule Appointment \"(.*?)\"$")
	public void schedule_Appointment(String screen) throws Throwable {
	 // Write code here that turns the phrase above into concrete actions 
		AppointmentID = dfwmsScheduleAppointmentPageObject.createAppointmentID(Appointmenttype, Equipmentcode, ASNID,screen, 
				DFWMSLTLOutboundFlowStepDefn.shipmentIDOutbound,ShipmentIdInbound);
	}
	
	@Then("^IB CheckIN \"(.*?)\"$")
	public void iB_CheckIN_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DockDoorNum = dfwmsIBCheckinPageObject.fillInDriverDetails(AppointmentID, screen);
	}
	
	@Then("^Get yards \"(.*?)\"$")
	public void get_yards_arg1(String zone) throws Throwable { 
		dfwmsIBCheckinPageObject.yards(zone);
	}
	
	@Then("^Add Yard Task$")
	public void add_Yard_Task() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		taskID = dfwmsIBCheckinPageObject.addYardTask();
	}
	
	
	@Then("^Validate yard rules for \"(.*?)\"$")
	public void check_yard_rules_for_arg1(String flow) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsIBCheckinPageObject.validateYardRules(flow);
	}
	
	@Then("^Complete Yard Task$")
	public void complete_Yard_Task() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsOutboundPickingNCNinPutty.completeYardTask(taskID);
	}
	
	@Then("^Validate Task status \"(.*?)\"$")
	public void validate_Task_status_arg1(String status) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsIBCheckinPageObject.validateTaskStatus(status, taskID);
	}
	
	@Then("^Validate Trailer Number$")
	public void validate_Trailer_Number() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsIBCheckinPageObject.validateTrailerNum();
	}

	@Then("^Map ASN and Shipment$")
	public void map_ASN_and_Shipment() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		ShipmentIdInbound = "";
		ShipmentIdInbound = dfwmsIBAssingShipmentPageObject.ValidateASNShipmentMap(ASNID);
		dfwmsIBAssingShipmentPageObject.ShipmentASNMapping(ShipmentIdInbound);
		
		DFWMSLTLOutboundFlowStepDefn.shipment = "Inbound";
	}
	
	@Then("^Validate ShipmentId in ASN$")
	public void validate_ShipmentId_in_ASN() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions
		dfwms2019IBPOFormatPostPageObject.input_search("ASN", poId, ASNID);
		dfwmsValidationsPageObject.validateShipmentInASN(ShipmentIdInbound);
	}
	
	@Then("^Inbound Receive in Putty \"(.*?)\"$")
	public void inbound_Receive_in_Putty_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//if(xmlData.size()>0){
			//itemName = xmlData.get(0);
			List<String> pendingLpns = new ArrayList<String>();
			LPNS = dfwmsInboundReceiveInPuttyPageObject.inboundReceiveInPutty(ItemNames, DockDoorNum, ASNID, screen, pendingLpns,ShipmentIdInbound);
			/*pendingLpns = dfwmsInboundReceiveInPuttyPageObject.getInTransitLpns(ASNID);
			if(pendingLpns.size()>0){
				LPNS = dfwmsInboundReceiveInPuttyPageObject.inboundReceiveInPutty(ItemNames, DockDoorNum, ASNID, screen, pendingLpns);
	        }*/
		/*}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")){
			LPNS = dfwmsInboundReceiveInPuttyPageObject.inboundReceiveInPutty(ItemNames, DockDoorNum, ASNID, screen, new ArrayList<String>());
		}*/
	}
	
	@Then("^Inbound Receive By Pallet in Putty$")
	public void inbound_Receive_By_Pallet_in_Putty() throws Throwable { 
		if(xmlData.size()>0){
			//List<String> pendingLpns = new ArrayList<String>();
			palletID = dfwmsInboundReceiveInPuttyPageObject.inboundReceiveByPalletInPutty(ItemNames, DockDoorNum, ASNID,DFWMSValidationsPageObject.siLPNs);
			System.out.println("Pallet id returned" + palletID);
		}
	
	}
	
	@Then("^Validate Total Received Items$")
	public void validate_Total_Received_Items() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateTotalItems();
	  
	}

	@Then("^Inbound Verify in Putty \"(.*?)\"$")
	public void inbound_Verify_in_Putty(String screen) throws Throwable {
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsInboundVerifyInPuttyPageObject.inboundVerifyInPutty(ASNID,screen);
	}
	
	@Then("^Validate iLPN's$")
	public void validate_iLPN_s() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateILPNs(LPNS,ASNID);
	}
	
	@Then("^Validate PIX Transactions$")
	public void validate_PIX_Transactions() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.validatepixtran(ASNID);
		Thread.sleep(1000);
		/*DFWMSIBPOFormatPOSTPageObject.getPIXtrncodehdr01(ASNID);
		Thread.sleep(1000);
		DFWMSIBPOFormatPOSTPageObject.getPIXtrncodeitm02(ASNID);*/
	}
	
	@Then("^Inbound PutAway By Pallet in Putty$")
	public void inbound_PutAway_By_Pallet_in_Putty() throws Throwable { 
		dfwmsInboundPutawayInPuttyPageObject.inboundPutAwayByPalletInPutty(DFWMSValidationsPageObject.siLPNs,palletID);
	  
	}
	
	@Then("^Inbound Putaway in Putty \"(.*?)\"$")
	public void inbound_Putaway_in_Putty_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsInboundPutawayInPuttyPageObject.inboundPutawayInPutty(LPNS, screen);
	}
	
	/*@Then("^Validate ILPN status and Qty$")
	public void validate_ILPN_status_and_Qty() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateIlpnStaus();
	}*/
	
	@Then("^Validate ILPN status and Qty \"(.*?)\"$")
	public void validate_ILPN_status_and_Qty_arg1(String status) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateIlpnStaus(status);
	}
	
	
	/*@Then("^Get GridLocn details for \"(.*?)\"$")
	public void get_GridLocn_details_for_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		gridLocn = dfwmsPOObject.getGridLocnDetails(screen);
		System.out.println(gridLocn);
	}*/
	
	@Then("^Add Location$")
	public void add_Location() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsAddLocationsPageObject.addLoc();
	}

	/*@Then("^Checkout the Trailer$")
	public void checkout_the_Trailer() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Check out starting");
	}*/
	@Then("^Checkout Trailer$")
	public void checkout_Trailer() throws Throwable { 
		dfwmsVerifyIBShipmentCheckOutInPuttyPageObject.checkOut(AppointmentID);
	}
	
	@Then("^Apply Item and get OnHand Qty$")
	public void apply_Item_and_get_OnHand_Qty() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		Qty = dfwmsPOObject.applyItemandGetQty(xmlData.get(0));
		if(!Qty.isEmpty()){
			ActualQty = Qty.get(0);
			FinalQty = Qty.get(1);
		}
	}
	
	@Then("^Validate OnHandQty$")
	public void validate_OnHandQty() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validteOnHandQty(ActualQty, FinalQty, xmlData.get(0));
	}
	

	
}
