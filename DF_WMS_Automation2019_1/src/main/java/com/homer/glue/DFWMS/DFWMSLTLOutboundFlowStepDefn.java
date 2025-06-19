package com.homer.glue.DFWMS;

import java.util.ArrayList;
import java.util.List;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSDOObject;
import com.homer.po.DFWMS.DFWMSOutboundPickingNCNinPutty;
import com.homer.po.DFWMS.DFWMSRunWavesPageObject;
import com.homer.po.DFWMS.DFWMSoLPNsPageObject;


public class DFWMSLTLOutboundFlowStepDefn extends BaseStepDefn{

	//	public String sStatus;
	//	public static String waveNumber;
	//public String oLPNS;
	public static String workAssignmentId = "";
	public static String shipment = "";
	public static String itemName;
	//public static String OrderQty;
	public static boolean multipleDo = false;
	public static String shipmentIDOutbound = "";
	public String cartID;
	public static String refFiled4;
	//	public String gridLocn;
	//	public String toteId,oLPN,locnValue,locnHDUBarcode,locnSNGHDU,upsLocnHDUBarcode,hduLocnHDUBarcode,ConPackByPassGridLocn,LTLMultiStop1LocnHDUBarcode,LTLMultiStop2LocnHDUBarcode,LTLSplitStop1LocnHDUBarcode,LTLSplitStop2LocnHDUBarcode;
	public String doID;
	public static List<String> doIds = new ArrayList<String>();
	public static List<String> Qty = new ArrayList<String>();
	
	List<String> ItemNames = new ArrayList<String>();
	List<String> xmlData,oLPNS = new ArrayList<String>(); 
	String AgileItemName,UPSItemName,HDUItemName;
	//	String AgileGridLocn,UPSGridLocn,HDUGridLocn;
	public String waveNumber;
	public String stgLocnBarcode = "";

	public DFWMSLTLOutboundFlowStepDefn(DataClass data) {
		super(data);
	}

	@Then("^Create DO Xml for \"(.*?)\"$")
	public void create_DO_Xml_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		doID = dfwmsDOObject.createDOXml(screen);
		doIds.add(doID);
	}

	@Then("^Input and Apply DOId for \"(.*?)\"$")
	public void input_and_Apply_DOId_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		if(screen.equalsIgnoreCase("BVR_Mutli_Stop") || screen.equalsIgnoreCase("BVR_Split_Shipment")){
			multipleDo = true;
		}else if(screen.equalsIgnoreCase("UndoWaveDO")){
			multipleDo = false;
			DFWMSInbounfFlowStepDefn.doId = DFWMSInbounfFlowStepDefn.doIds.get(0)+","+DFWMSInbounfFlowStepDefn.doIds.get(1);
		}else if(screen.equalsIgnoreCase("InPacking")){
			multipleDo = false;
			DFWMSInbounfFlowStepDefn.doId = DFWMSInbounfFlowStepDefn.doIds.get(1);
		}
		dfwms2019IBPOFormatPostPageObject.input_search("Distribution Order", DFWMSInbounfFlowStepDefn.doId, "");
		//dfwmsdistributionorderspageobject.DOInputandSearch(doID, screen, doIds);
	}

	@Then("^Input and Apply DOId for (\\d+) \"(.*?)\"$")
	public void input_and_Apply_DOId_for_2012_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		if(screen.equalsIgnoreCase("BVR_Mutli_Stop") || screen.equalsIgnoreCase("BVR_Split_Shipment")){
			multipleDo = true;
		}else if(screen.equalsIgnoreCase("UndoWaveDO")){
			multipleDo = false;
			DFWMSInbounfFlowStepDefn.doId = DFWMSInbounfFlowStepDefn.doIds.get(0)+","+DFWMSInbounfFlowStepDefn.doIds.get(1);
		}
		dfwms2019IBPOFormatPostPageObject.input_search2012("Distribution Order", DFWMSInbounfFlowStepDefn.doId, "");

	}

	@Then("^Select \"(.*?)\" Define and Submit for \"(.*?)\"$")
	public void select_arg1_Define_and_Submit_for_arg2(String option, String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		if(DFWMSRunWavesPageStepDeftn.validation.equalsIgnoreCase("NoWave")){
			dfwmsHomePageObject2019.WMSmenu();
			dfwmsHomePageObject2019.searchInput("Ship Wave Templates", "Configuration");
			dfwmsrunwavespageobject.copyWave();
		}else{
			dfwmsrunwavespageobject.selectRuleCheckBox(option,DFWMSInbounfFlowStepDefn.doId,screen);
		}

	}

	@Then("^Select \"(.*?)\" Define and Submit for (\\d+) \"(.*?)\"$")
	public void select_arg1_Define_and_Submit_for_2012_arg2(String option, String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		if(DFWMSRunWavesPageStepDeftn.validation.equalsIgnoreCase("NoWave")){
			dfwmsHomePageObject2019.WMSmenu();
			dfwmsHomePageObject2019.searchInput("Ship Wave Templates", "Configuration");
			dfwmsrunwavespageobject.copyWave();
		}else{
			dfwmsrunwavespageobject.selectRuleCheckBox2012(option,DFWMSInbounfFlowStepDefn.doId,screen);
		}
	}



	/*@Then("^Undo Wave$")
	public void undo_Wave() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.undoWaveDO();
	}*/

	@Then("^Undo Wave for \"(.*?)\"$")
	public void undo_Wave_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.undoWaveDO(screen);
	}
	@Then("^Run Wave for \"(.*?)\"$")
	public void run_Wave_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.runWaves(screen);


	}


	@Then("^Input WaveNumber and Apply$")
	public void input_WaveNumber_and_Apply() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.enterWaveandApply();
	}


	@Then("^Open Olpn Management and Search WaveNumber apply$")
	public void open_Olpn_Management_and_Search_WaveNumber_apply() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.selectOlpnManagement();

	}

	@Then("^Open Order  and Search OrderNumber apply$")
	public void open_Order_and_Search_OrderNumber_apply() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsrunwavespageobject.searchOrderNumber();

	}


	@Then("^Select CartStart and Release$")
	public void select_CartStart_and_Release() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.selectCartandRelease();

	}

	@Then("^Search Waves in Packsize$")
	public void search_Waves_in_Packsize() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsrunwavespageobject.searchWaveNumberInPacksize();
	}



	@Then("^Select Order number and Release$")
	public void select_Order_number_and_Release() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsrunwavespageobject.searchOrderNumber();
	}


	@Then("^Search OLPN number in Container and Tasks \"(.*?)\"$")
	public void search_OLPN_number_in_Container_and_Tasks_arg1(String Screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsrunwavespageobject.searchOLPNInContainerAndTask(Screen);
	}
	@Then("^Open PacketSender and send request \"(.*?)\"$")
	public void open_PacketSender_and_send_request_arg1(String Screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.packetSenderRequest(Screen);

	}

	@Then("^Picking with WM \"(.*?)\"$")
	public void picking_with_WM_arg1(String Screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 
		dfwmsrunwavespageobject.PickingWithWM(itemName);
	}


	@Then("^Input oLPNs and Apply for \"(.*?)\"$")
	public void input_oLPNs_and_Apply_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsolpnspageobject.oLPNInputDOandSearch(DFWMSInbounfFlowStepDefn.doId, screen, doIds);
		System.out.println("olpns values returned from db" +DFWMSoLPNsPageObject.oLPNs );

	}

	@Then("^Lock LPN$")
	public void lock_LPN() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsolpnspageobject.lockLPN(DFWMSoLPNsPageObject.oLPNs);
	}

	@Then("^Validate DO_Status \"(.*?)\"$")
	public void validate_DO_Status_arg1(String sStatus) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsdistributionorderspageobject.ValidateDOStatus(sStatus,DFWMSInbounfFlowStepDefn.doId);
	}

	@Then("^Validate status for \"(.*?)\" - \"(.*?)\"$")
	public void validate_status_for_arg1__arg2(String screen, String status) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateStatus(screen,status);
	}

	@Then("^Validate status for LM \"(.*?)\" - \"(.*?)\"$")
	public void validate_status_for_LM_arg1__arg2(String screen, String status) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateStatusForLM(screen,status);

	}
	
	
	@Then("^Validate status for LM \"(.*?)\"$")
	public void validate_status_for_LM_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.compareTable(screen);

	}

	@Then("^Validate status for CL_Message \"(.*?)\"$")
	public void validate_status_for_CL_Message_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.compareTable(screen);

	}
	
	@Then("^Validate status for Olpn_misc \"(.*?)\"$")
	public void validate_status_for_Olpn_misc_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.compareTable(screen);

	}
	@Then("^Store procedure \"(.*?)\"$")
	public void store_procedure_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.storeProcedureForWCS(screen);

	}


	//Fluid insert query
	@Then("^Insert oLPNs into Divert history table \"(.*?)\" - \"(.*?)\"$")
	public void insert_oLPNs_into_Divert_history_table_arg1__arg2(String screen, String status) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.fluidLoadInsertOlpns(screen,status);

	}
	//Fluid insert Pallet
	@Then("^Insert Pallet into Divert history table \"(.*?)\" - \"(.*?)\"$")
	public void insert_Pallet_into_Divert_history_table_arg1__arg2(String screen, String status) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.fluidLoadInsertPallet(screen,status);

	}

	//Fluid validate status
	@Then("^Validate Mhe_cntr_state for \"(.*?)\" - \"(.*?)\"$")
	public void validate_Mhe_cntr_state_for_arg1__arg2(String screen, String status) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsValidationsPageObject.validateMheCntrStateinTable(screen,status);

	}
	@Then("^Outbound Picking in Putty$")
	public void outbound_Picking_in_Putty() throws Throwable { 
		dfwmsOutboundPickingNCNinPutty.outboundPickingNCNinPutty(itemName,DFWMSoLPNsPageObject.soLPNs);
	}

	@Then("^Outbound Picking in Putty \"(.*?)\"$")
	public void outbound_Picking_in_Putty_arg1(String Screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsOutboundPickingNCNinPutty.outboundPickingNCNinPutty_Diff_Olpn(ItemNames,DFWMSoLPNsPageObject.soLPNs,Screen);

	}

	@Then("^Outbound Anchor Opn MOD \"(.*?)\"$")
	public void outbound_Anchor_Opn_MOD_arg1(String screen) throws Throwable {  
		// Write code here that turns the phrase above into concrete actions 
		dfwmsOutboundPickingNCNinPutty.anchorOlpnMod(itemName,DFWMSoLPNsPageObject.soLPNs,screen);
	}

	@Then("^Get Work Assignment Id$")
	public void get_Work_Assignment_Id() throws Throwable { 
		workAssignmentId = dfwmsolpnspageobject.getWorkAssignmentId();
	}

	@Then("^Release Assignment$")
	public void release_Assignment() throws Throwable { 
		dfwmsolpnspageobject.releaseAssignment(workAssignmentId);
	}

	@Then("^Unlock LPN Putty$")
	public void unlock_LPN_Putty() throws Throwable { 
		dfwmsOutboundPickingNCNinPutty.unlockLpn(DFWMSoLPNsPageObject.oLPNs);
	}
	@Then("^Get details \"(.*?)\" from xml for \"(.*?)\"$")
	public void get_details_arg1_from_xml_for_arg2(String data, String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		xmlData = dfwmsDOObject.getXmlData(data,screen);
		if(screen.equalsIgnoreCase("VAS") 
				|| screen.equalsIgnoreCase("UPS_Dallas_MISP")
				|| screen.equalsIgnoreCase("BVR_Dallas_MISP")
				|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
				|| screen.equalsIgnoreCase("BVR_Dallas_MIMP")
				|| screen.equalsIgnoreCase("BVR_Baltimore_MIMP")
				|| screen.equalsIgnoreCase("BVR_Lacey_MISP")
				|| screen.equalsIgnoreCase("BVR_MutliStop")
				|| screen.equalsIgnoreCase("BVR_MutliStop_Houston")
				|| screen.equalsIgnoreCase("MDO_MutliStop")
				||screen.equalsIgnoreCase("VAS_Baltimore")
				||screen.equalsIgnoreCase("MultiShipmentLacey")
				||screen.equalsIgnoreCase("UPS_Baltimore_MISP")
				||screen.equalsIgnoreCase("UPS_Lacey_MISP")
				||screen.equalsIgnoreCase("BVR_Houston_MISP")
				||screen.equalsIgnoreCase("LTL_Houston_MISP")
				||screen.equalsIgnoreCase("UPS_Houston_MISP")
				||screen.equalsIgnoreCase("UPS_Tampa_MISP")
				||screen.equalsIgnoreCase("UPS_Newark_MISP")
				||screen.equalsIgnoreCase("UPS_Tracey_MISP")
				||screen.equalsIgnoreCase("UPS_Miami_MISP")
				||screen.equalsIgnoreCase("FXHD_Dallas_Undo")
				||screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				||screen.equalsIgnoreCase("FGND_Dallas_WM09")
				||screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				||screen.equalsIgnoreCase("BVR_Houston_MutliStop")
				||screen.equalsIgnoreCase("MDO_Dallas_MutliStop")
				||screen.equalsIgnoreCase("BK1NonShipReady_MultiShipment")
				||screen.equalsIgnoreCase("UPS_Boston_MISP")){
			if(data.equalsIgnoreCase("ItemName")){
				ItemNames= xmlData;
			}else{
				Qty= xmlData;
			}

		}else if(screen.equalsIgnoreCase("EnvelopMulti_Dallas")
				|| screen.equalsIgnoreCase("EnvelopMulti_Baltimore")
				|| screen.equalsIgnoreCase("Envelop_SingleTote1_Dallas")
				|| screen.equalsIgnoreCase("Envelop_SingleTote1_Baltimore")
				|| screen.equalsIgnoreCase("LTL_Multistop")
				|| screen.equalsIgnoreCase("BVR_UndoDO")
				){
			refFiled4= xmlData.get(0);
		}else if(screen.equalsIgnoreCase("BVR_Dallas_UndoDO") || screen.equalsIgnoreCase("LTLMultiStopLacey")
				|| screen.equalsIgnoreCase("BVR_Houston_UndoDO")){
			itemName = xmlData.get(1);
		}else if(screen.equalsIgnoreCase("LTLOutboundLacey") 
				){
			itemName = xmlData.get(2);
		}
		else{
			itemName = xmlData.get(0);
		}

	}

	@Then("^Get putaway type$")
	public void get_putaway_type() throws Throwable { 
		/*if(xmlData.size() > 0 || ItemNames.size() > 0){
			dfwmsDOObject.getPutawayType(xmlData.get(0));
		}else*/ if(DFWMSInbounfFlowStepDefn.ItemNames.size()>0){
			dfwmsDOObject.getPutawayType(DFWMSInbounfFlowStepDefn.ItemNames.get(0));
		}
	}
	/*@Then("^PackCubeDir in Putty$")
	public void packCubeDir_in_Putty() throws Throwable { 

		dfwmsPackCubeDirinPuttyPageObject.pckCubeDirInPutty(DFWMSoLPNsPageObject.oLPNs,itemName);
	}*/

	@Then("^PackCubeDir in Putty for \"(.*?)\"$")
	public void packCubeDir_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsPackCubeDirinPuttyPageObject.pckCubeDirInPutty(DFWMSoLPNsPageObject.soLPNs,itemName,screen);
	}

	@Then("^Outbound PickToLabel in Putty for \"(.*?)\"$")
	public void outbound_PickToLabel_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsPickToLabelinPuttyPageObject.pickToLabelInPutty(DFWMSoLPNsPageObject.soLPNs,itemName,ItemNames,screen);
	}

	@Then("^Task Picking in RF \"(.*?)\"$")
	public void task_Picking_in_RF_arg1(String Screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.taskPickingRF(Screen);
	}
	
	@Then("^Execute Voice Picking commands \"(.*?)\"$")
	public void execute_Voice_Picking_commands_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.voicePicking(screen);

		
	}

	/*@Then("^Palletize olpn$")
	public void palletize_olpn() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsPickToLabelinPuttyPageObject.palletizeOlpn(DFWMSoLPNsPageObject.soLPNs);
	}*/

	@Then("^PalletizeOlpnParcel fluid for \"(.*?)\"$")
	public void palletizeOlpnParcel_fluid_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsPickToLabelinPuttyPageObject.palletizeOlpnParcel(DFWMSoLPNsPageObject.soLPNs,screen);

	}

	@Then("^Palletize olpn for \"(.*?)\"$")
	public void palletize_olpn_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsPickToLabelinPuttyPageObject.palletizeOlpn(DFWMSoLPNsPageObject.soLPNs,screen);
	}

	@Then("^PickCart Picking in Putty$")
	public void pickCart_Picking_in_Putty() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		//cartID = dfwmsPickCartPickinginPuttyPageObject.pickCartinPutty(DFWMSoLPNsPageObject.oLPNs); 
		cartID = dfwmsPickCartPickinginPuttyPageObject.pickCartinPutty(DFWMSoLPNsPageObject.soLPNs);

		//System.out.println(DFWMSoLPNsPageObject.oLPNs);
	}
	@Then("^Outbound PackCart in Putty$")
	public void outbound_PackCart_in_Putty() throws Throwable { 
		dfwmsPackCartinPuttyPageObject.packCartInPutty(DFWMSoLPNsPageObject.soLPNs,ItemNames,cartID,itemName);

	}
	/*@Then("^Create Shipment$")
	public void create_Shipment() throws Throwable { 

		shipmentID = dfwmsCreateShipmentPageObject.createShipment(DFWMSInbounfFlowStepDefn.doId);
	}*/
	@Then("^Create Shipment for \"(.*?)\"$")
	public void create_Shipment_for_arg1(String screen) throws Throwable { 
		shipmentIDOutbound = "";
		shipmentIDOutbound = dfwmsCreateShipmentPageObject.createShipment(screen,DFWMSInbounfFlowStepDefn.doId);
		shipment = "Outbound";
	}

	@Then("^Generate Shipment for \"(.*?)\"$")
	public void generate_Shipment_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		shipmentIDOutbound = dfwmsCreateShipmentPageObject.generateShipment(screen, DFWMSInbounfFlowStepDefn.doId);

		shipment = "Outbound";


	}

	@Then("^Outbound LoadTrailer in Putty for \"(.*?)\"$")
	public void outbound_LoadTrailer_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsLoadTrailerInPuttyPageObject.loadTrailerInPutty(DFWMSoLPNsPageObject.soLPNs,DFWMSInbounfFlowStepDefn.DockDoorNum,shipmentIDOutbound,screen);
	}
	@Then("^Outbound LoadParcel in Putty for \"(.*?)\"$")
	public void outbound_LoadParcel_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsLoadTrailerInPuttyPageObject.loadParcelInPutty(DFWMSInbounfFlowStepDefn.DockDoorNum,DFWMSoLPNsPageObject.soLPNs,DFWMSInbounfFlowStepDefn.doId, screen);
	}

	//FluidLoad steps
	@Then("^Outbound PCLFluidLoad in Putty for \"(.*?)\"$")
	public void outbound_PCLFluidLoad_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsLoadTrailerInPuttyPageObject.fluidLoadInPutty(DFWMSInbounfFlowStepDefn.DockDoorNum,DFWMSoLPNsPageObject.soLPNs,DFWMSInbounfFlowStepDefn.doId, screen);

	}

	@Then("^Outbound CloseTrailer in Putty for \"(.*?)\"$")
	public void outbound_CloseTrailer_in_Putty_for_arg1(String screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsCloseTrailerInPuttyPageObject.closeTrailerInPutty(shipmentIDOutbound, screen);
	}

	@Then("^CloseTrailer validation$")
	public void closeTrailer_validation() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsCloseTrailerInPuttyPageObject.closeTrailerValidation(shipmentIDOutbound);
	}
	@Then("^ShipConfirm Rules for \"(.*?)\"$")
	public void shipConfirm_Rules_for_arg1(String screen) throws Throwable { 

		//DFWMSShpConfRulesObject.shpconfrules(doID, doIds, screen);
		//DFWMSShpConfRulesObject.shpconfrules(doID, doIds, screen);
	}

	@Then("^Input and Apply WaveNumber for \"(.*?)\"$")
	public void input_and_Apply_WaveNumber_for_arg1(String screen) throws Throwable { 

		dfwms2019IBPOFormatPostPageObject.inputWaveNumber(DFWMSRunWavesPageObject.sWaveNumber,screen);
	}
	@Then("^Weigh and Manifest oLPNS for \"(.*?)\"$")
	public void weigh_and_Manifest_oLPNS_for_arg1(String screen) throws Throwable { 

		//dfwmsWeighAndManifestOlpnsPageObject.weighLpns(DFWMSoLPNsPageObject.oLPNs,screen);
		dfwmsWeighAndManifestOlpnsPageObject.weighLpns(DFWMSoLPNsPageObject.soLPNs,screen);

	}
	@Then("^Validate EPI status for \"(.*?)\"$")
	public void validate_EPI_status_for_arg1() throws Throwable { 
		dfwmsLoadTrailerInPuttyPageObject.validateEpiStatus();
	}

	@Then("^Validate EPI status$")
	public void validate_EPI_status() throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsLoadTrailerInPuttyPageObject.validateEpiStatus();

	}

	/*@Then("^Get STGLocn Barcode for \"(.*?)\"$")
	public void get_STGLocn_Barcode_for_arg1(String screen) throws Throwable { 

		stgLocnBarcode = DFWMSDOObject.getSTGLocnDetails(screen);
	}*/

	@Then("^AnchorInPutty for \"(.*?)\"$")
	public void anchorInPutty_for_arg1(String screen) throws Throwable { 
		dfwmsWeighAndManifestOlpnsPageObject.anchorForConPackByPass(screen,stgLocnBarcode,DFWMSoLPNsPageObject.soLPNs,DFWMSOutboundPickingNCNinPutty.palletID);
	}







	/*

	@Then("^Get Task Dts$")
	public void get_Task_Dts() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsTasksPageObject.GetTaskDetails();
	}





		@Then("^Get GridLocn details for \"(.*?)\"$")
		public void get_GridLocn_details_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			gridLocn = dfwmsDOObject.getGridLocnDetails(screen);
			if(screen.equalsIgnoreCase("OutboundConveyAgile")){
				AgileGridLocn = gridLocn;
			}else if(screen.equalsIgnoreCase("OutboundConveyUPS")){
				UPSGridLocn = gridLocn;
			}else if(screen.equalsIgnoreCase("OutboundConveyHDU")){
				HDUGridLocn = gridLocn;
			}else if(screen.equalsIgnoreCase("ConPackBypass")){
				ConPackByPassGridLocn = gridLocn;
			}
		}






		@Then("^Outbound Load Trailer in Putty for \"(.*?)\"$")
		public void outbound_Load_Trailer_in_Putty_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			if(screen.equalsIgnoreCase("BVR_PAX")||screen.equalsIgnoreCase("OB_BVR")){
			dfwmsLoadTrailerInPuttyPageObject.loadTrailer(screen, DFWMSoLPNsPageStepDeftn.oLPN,DFWMSAPPTCREATIONPageStepDeftn.dockDoorID);
			}else{
			String hduDockDoor = "";
			String hduOLPN = "";
			if(DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap.size() > 0 && DFWMSoLPNsPageObject.soLPNs.size() > 0){
				hduDockDoor = DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap.get(screen);
				hduOLPN = (String) DFWMSoLPNsPageObject.soLPNs.get(2);
			}
			dfwmsLoadTrailerInPuttyPageObject.loadTrailerInPutty(screen, DFWMSoLPNsPageStepDeftn.oLPN, hduOLPN, DFWMSAPPTCREATIONPageStepDeftn.dockDoorID,hduDockDoor);
		}




		@Then("^Get HDU Locn from Task for \"(.*?)\"$")
		public void get_HDU_Locn_from_Task_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			locnValue = dfwmsTasksPageObject.getLocnFromTask(otaskId,screen);
			locnHDUBarcode = dfwmsDOObject.getLocnBC(locnValue);
			locnSNGHDU = dfwmsDOObject.getSNGHDULocnValue();

			if(screen.equalsIgnoreCase("OutboundConveyUPS")){
				upsLocnHDUBarcode = locnHDUBarcode;
			}else if(screen.equalsIgnoreCase("OutboundConveyHDU")){
				hduLocnHDUBarcode = locnHDUBarcode;
			}else if(screen.equalsIgnoreCase("LTLMultiStop1")){
				LTLMultiStop1LocnHDUBarcode = locnHDUBarcode;
			}else if(screen.equalsIgnoreCase("LTLMultiStop2")){
				LTLMultiStop2LocnHDUBarcode = locnHDUBarcode;
			}else if(screen.equalsIgnoreCase("LTLSplitStop1")){
				LTLSplitStop1LocnHDUBarcode = locnHDUBarcode;
			}else if(screen.equalsIgnoreCase("LTLSplitStop2")){
				LTLSplitStop2LocnHDUBarcode = locnHDUBarcode;
			}
		}

		@Then("^Validate PCL Ship Via$")
		public void validate_PCL_Ship_Via() throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			dfwmsValidationsPageObject.validatePclShipVia(oLPN);
		}

		@Then("^PCL Load Parcel in Putty for \"(.*?)\"$")
		public void pCL_Load_Parcel_in_Putty_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions
			dfwmsAgileLoadParcelInPuttyPageObject.agileLoadParcelInPutty(screen, DFWMSoLPNsPageStepDeftn.oLPN,DFWMSAPPTCREATIONPageStepDeftn.dockDoorID,DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap);
		}

		@Then("^Outbound NonParcelCloseTrailer in Putty$")
		public void outbound_NonParcelCloseTrailer_in_Putty() throws Throwable { 
			//DFWMSNonParcelCloseTrailerInPuttyPageObject.closetrailerNP();
		}


		@Then("^Outbound CloseTrailer_NonParcel in putty for \"(.*?)\"$")
		public void outbound_CloseTrailer_NonParcel_in_putty_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			String upsDockDoor = "";
			if(DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap.size() > 0){
				upsDockDoor = DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap.get(screen);
			}
			dfwmsCloseTrailerNonParcelInPuttyPageObject.npClosetrailer(DFWMSAPPTCREATIONPageStepDeftn.dockDoorID, screen, upsDockDoor);
		}


		@Then("^Load Trailer in Putty for \"(.*?)\"$")
		public void load_Trailer_in_Putty_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			if(screen.equalsIgnoreCase("LTLMultiStop")){
				dfwmsLoadTrailerInPuttyPageObject.loadTrailerInPuttyMultiSplit(screen,DFWMSoLPNsPageObject.soLPNs,DFWMSAPPTCREATIONPageStepDeftn.dockDoorID);
			}else if(screen.equalsIgnoreCase("LTLSplitStop2")|| screen.equalsIgnoreCase("LTLSplitStop1")){
				dfwmsLoadTrailerInPuttyPageObject.loadTrailerInPuttySplit(screen,DFWMSoLPNsPageObject.soLPNs,DFWMSAPPTCREATIONPageStepDeftn.dockDoorID, DFWMSAPPTCREATIONPageStepDeftn.DockDoorMap);
			}
		}

		@Then("^Picking for \"(.*?)\"$")
		public void picking_for_arg1(String screen) throws Throwable { 
		 // Write code here that turns the phrase above into concrete actions 
			System.out.println(DFWMSoLPNsPageObject.soLPNs);
			dfwmsOutboundPickFromToteInPuttyPageObject.pickingForNonConPackBypass(screen,DFWMSoLPNsPageObject.soLPNs,AgileItemName,UPSItemName,HDUItemName);
		}
	 */

}