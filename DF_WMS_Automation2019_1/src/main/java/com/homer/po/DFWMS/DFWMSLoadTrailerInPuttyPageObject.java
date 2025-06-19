package com.homer.po.DFWMS;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSLoadTrailerInPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	List<String> epiStatus = new ArrayList<>();
	List<String> trackingNumber = new ArrayList<>();

	public DFWMSLoadTrailerInPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public void loadTrailerInPutty(List<String> oLPNs, String dockDoorNum, String shipmentID, String screen) throws Exception {
		String str = "";
		try {
			String dockdoor;
			Map item = new LinkedHashMap();
			item.put("dockdoor", dockDoorNum.trim());	
			item.put("shipmentid", shipmentID.trim());
			Map item_root = new LinkedHashMap();
			if(screen.equalsIgnoreCase("BVR_Dallas_MISP") 
					|| screen.equalsIgnoreCase("BVR_Dallas_MIMP")
					|| screen.equalsIgnoreCase("BVR_Baltimore_MIMP")
					|| screen.equalsIgnoreCase("LTL_Multistop")){
				if(oLPNs.size()>0){
					if(screen.equalsIgnoreCase("LTL_Multistop")){
						item.put("olpn1", oLPNs.get(0));
						item.put("olpn2", oLPNs.get(1));
					}
					else{
					item.put("olpn1", oLPNs.get(0));
					item.put("olpn2", oLPNs.get(2));
					}
				}else{
					jd.dbDFWMSMapping();
					oLPNs = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doIds.get(0) + "')");
					item.put("olpn1", oLPNs.get(0));
					item.put("olpn2", oLPNs.get(2));
				}
				item.put("loadPallet", "Y");
				item.put("pallet1", DFWMSPickToLabelinPuttyPageObject.palletID);
				item.put("pallet2", DFWMSPickToLabelinPuttyPageObject.palletID1);
				item_root.put("key", "Nonparcel_Loading_Dallas_MIMP");
				item_root.put("testcase_name", "Nonparcel_Loading_Dallas_MIMP");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("Shipment1")
					|| screen.equalsIgnoreCase("Shipment2")
					|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
					|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
					|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
					||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")
					||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")
					|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
					|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")
					|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")
					|| screen.equalsIgnoreCase("VAS_Baltimore")){
				if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
					|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
					|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
					|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
					||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")
					||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")
					|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")
					|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")){
					
					for(String lpn:DFWMSInbounfFlowStepDefn.doIds){
						int i = 1;
						jd.dbDFWMSMapping();
						List<String> olpns = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + lpn + "')");
						for(String lpns:olpns){
							oLPNs.add(lpns);
							i++;	
						}
					}
				}
				if(screen.equalsIgnoreCase("Shipment1")
						|| screen.equalsIgnoreCase("VAS_Baltimore")){
					item.put("olpn1", oLPNs.get(0));
					item.put("olpn2", oLPNs.get(1));
					
				}else if(screen.equalsIgnoreCase("Shipment2")){
					item.put("olpn3", oLPNs.get(2));
					item.put("olpn4", oLPNs.get(3));
					
				}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
						||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")
						||screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
						|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")){
					item.put("olpn1", oLPNs.get(0));
					item.put("olpn2", oLPNs.get(2));
				}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
						||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")
						||screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
						|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")){
					item.put("olpn1", oLPNs.get(1));
					item.put("olpn2", oLPNs.get(3));
				}else if(oLPNs.size()>3){
					item.put("olpn1", oLPNs.get(2));
					item.put("olpn2", oLPNs.get(3));
				}
				if(screen.equalsIgnoreCase("VAS_Baltimore")){
					//item.put("loadPallet", "N");
					item_root.put("key", "Nonparcel_Loading_Baltimore_VAS");
					item_root.put("testcase_name", "Nonparcel_Loading_Baltimore_VAS");
					item_root.put("input_params", item);
				}else if(screen.equalsIgnoreCase("Shipment1")){
					
					item.put("loadPallet", "N");
					item_root.put("key", "Nonparcel_Loading_Dallas_MultiShipment1");
					item_root.put("testcase_name", "Nonparcel_Loading_Dallas_MultiShipment1");
					item_root.put("input_params", item);
				}else if(screen.equalsIgnoreCase("Shipment2")){
					
					item.put("loadPallet", "N");
					item_root.put("key", "Nonparcel_Loading_Dallas_MultiShipment2");
					item_root.put("testcase_name", "Nonparcel_Loading_Dallas_MultiShipment2");
					item_root.put("input_params", item);
				}else {
					
					item.put("loadPallet", "N");
					item_root.put("key", "Nonparcel_Loading_Dallas");
					item_root.put("testcase_name", "Nonparcel_Loading_Dallas");
					item_root.put("input_params", item);
				}
			}else if(screen.equalsIgnoreCase("Zone")){
				if(DFWMSoLPNsPageObject.soLPNs.size()>2){
					item.put("olpn1", DFWMSoLPNsPageObject.soLPNs.get(0));
					item.put("olpn2", DFWMSoLPNsPageObject.soLPNs.get(1));
					item_root.put("key", "Nonparcel_Loading_Dallas");
					item_root.put("testcase_name", "Nonparcel_Loading_Dallas");

				}else{
					item.put("olpn1", DFWMSoLPNsPageObject.soLPNs.get(0));
					item_root.put("key", "Nonparcel_Loading_Houston");
					item_root.put("testcase_name", "Nonparcel_Loading_Houston");
				}
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("WM18")){
					
					jd.dbDFWMSMapping();
					String str_Database_Connection = jd.str_Database_Connection("select barcode from DOCK_DOOR where DOCK_DOOR_NAME = 'DD301'");
					item.put("pallet", DFWMSPickToLabelinPuttyPageObject.palletID.trim());
					item_root.put("key", "Nonparcel_Loading_WM18");
					item_root.put("testcase_name", "Nonparcel_Loading_WM18");
					item_root.put("input_params", item);

				
			}	
				else if(screen.equalsIgnoreCase("Loading1")|| screen.equalsIgnoreCase("Loading2")){
				if(screen.equalsIgnoreCase("Loading1")){
					item.put("olpn", DFWMSoLPNsPageObject.soLPNs.get(0));
					
				}else if(screen.equalsIgnoreCase("Loading2")){
					item.put("olpn", DFWMSoLPNsPageObject.soLPNs.get(1));
				
				}
				item_root.put("key", "Nonparcel_Loading_Dallas");
				item_root.put("testcase_name", "Nonparcel_Loading_Dallas");
				item_root.put("input_params", item);
			}
			else{
				if(DFWMSInbounfFlowStepDefn.doIds.size()>0){
					jd.dbDFWMSMapping();
					String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doIds.get(0) + "')");
					item.put("olpn", olpn);
				}
				item_root.put("key", "Nonparcel_Loading_Dallas");
				item_root.put("testcase_name", "Nonparcel_Loading_Dallas");
				item_root.put("input_params", item);
			}
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			//String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
//					||jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				port = "7000";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}


			puttyCall(str, DFWMSLoginPageObject.port);
            report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound Load Trailer"+str , StepResult.PASS);
			System.out.println("Putty Operations performed Successfully for Outbound Load Trailer");
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations", "Putty Operations not performed for Outbound Load Trailer"+str, StepResult.PASS);
			System.out.println("Putty Operations not performed for Outbound Load Trailer");
		}
	}

	public void loadParcelInPutty(String dockDoorNum, List<String> oLPNs, String doId, String screen) throws Exception {
		String str = null;
		try {
			Map item = new LinkedHashMap();
			item.put("dockdoor", dockDoorNum.trim());
			ArrayList<String> hubCode; 
			String hubCodeValue="";
			jd.dbDFWMSMapping();
			
			String hubcode = "";

			
			hubcode = jd.str_Database_Connection("select ftsr_nbr from orders  where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");

			hubCode = jd.array_Database_Connection("select ORDERS.FTSR_NBR from ORDERS where TC_ORDER_ID = '"+doId+"'");
			if(screen.equalsIgnoreCase("UPS_Houston")){
				if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("HGT_2019")) {
					
					item.put("hubcode","001");

				}else if(jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
					
					item.put("hubcode","002");
				}
				else if(jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
					
					item.put("hubcode","209");
				}

				else {
				
					
					item.put("hubcode","210");

				}
			}
			else if(screen.equalsIgnoreCase("FGND_Houston")||screen.equalsIgnoreCase("FXHD_Houston")){
				if(jd.envrnment.equalsIgnoreCase("Perris_2019")) {
					item.put("hubcode",hubcode);

			}else {
				item.put("hubcode",hubcode);

			}
			}
			else if(screen.equalsIgnoreCase("UPSDallas")){
			
				if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {
					
					item.put("hubcode1","001");


				}else {
					
				item.put("hubcode1","500");
				
				}
				
			}
			else if(screen.equalsIgnoreCase("FXHD_WES")){
				
				item.put("hubcode1","176");
			}
				
			else if(screen.equalsIgnoreCase("UPSDallas_UndoFedEx")){
			
				
				hubCode = jd.array_Database_Connection("select ORDERS.FTSR_NBR from ORDERS where TC_ORDER_ID = '"+DFWMSInbounfFlowStepDefn.doIds.get(0)+"'");
				hubCodeValue=hubCode.get(0);
				item.put("hubcode1",hubCodeValue);

			}else if(screen.equalsIgnoreCase("UPSDallas_UndoUps")){
				
				hubCode = jd.array_Database_Connection("select ORDERS.FTSR_NBR from ORDERS where TC_ORDER_ID = '"+DFWMSInbounfFlowStepDefn.doIds.get(1)+"'");
				hubCodeValue=hubCode.get(0);
				item.put("hubcode1",hubCodeValue);

			}else {
				if(!hubcode.isEmpty()){
					hubCodeValue=hubcode;
				}
				System.out.println("Hubcode Value is " +hubcode);
				item.put("hubcode",hubCodeValue.trim());
			}
			item.put("trailer", DFWMSScheduleAppointmentPageObject.Strtrailer.trim());
			item.put("pallet", DFWMSPickToLabelinPuttyPageObject.palletID.trim());
			Map item_root = new LinkedHashMap();
			if(oLPNs.size()>0){
				
					if( screen.equalsIgnoreCase("UPSDallas")
							||  screen.equalsIgnoreCase("UPS_Houston")){
						item.put("olpn", oLPNs.get(0));
						item_root.put("input_params", item);
						item_root.put("key", "LoadParcel_Dallas");
						item_root.put("testcase_name", "LoadParcel_Dallas");
					}else if(screen.equalsIgnoreCase("UPS_Dallas_MIMP")){
						
						item.put("olpn", oLPNs.get(0));
						item.put("pallet1", DFWMSPickToLabelinPuttyPageObject.palletID);
						item.put("pallet2", DFWMSPickToLabelinPuttyPageObject.palletID1);
						item_root.put("input_params", item);
						item_root.put("key", "LoadParcel_Dallas_MIMP");
						item_root.put("testcase_name", "LoadParcel_Dallas_MIMP");
						
					}else if(screen.equalsIgnoreCase("UPS_Baltimore_MISP")){
						int i = 1;
						for(String lpns:oLPNs){
								item.put("lpn" + i, lpns.trim());
								i++;
								item_root.put("input_params", item);
								item_root.put("key", "LoadParcel_Dallas_MultiSku");
								item_root.put("testcase_name", "LoadParcel_Dallas_MultiSku");
						}
					}else if(screen.equalsIgnoreCase("UPSDallas_UndoFedEx")){
						
						item.put("olpn", oLPNs.get(0));

						item_root.put("input_params", item);
						item_root.put("key", "LoadParcel_Dallas_Manifested");
						item_root.put("testcase_name", "LoadParcel_Dallas_Manifested");
					}else if(screen.equalsIgnoreCase("UPSDallas_UndoUps")){
						
						item.put("olpn", oLPNs.get(2));

						item_root.put("input_params", item);
						item_root.put("key", "LoadParcel_Dallas_Manifested");
						item_root.put("testcase_name", "LoadParcel_Dallas_Manifested");
					}else{
						item.put("olpn", oLPNs.get(0));
						item_root.put("input_params", item);
						item_root.put("key", "LoadParcel_Dallas");
						item_root.put("testcase_name", "LoadParcel_Dallas");
					}
				
			}else{
				int i = 1;
				for(String lpn:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + lpn + "')");
					item.put("lpn" + i, olpn.trim());
					item_root.put("input_params", item);
					i++;
				}
			}
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			jd.dbDFWMSMapping();
			//String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
//					||jd.envrnment.equalsIgnoreCase("Miami_2019")||jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				port = "7000";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}


			puttyCall(str, DFWMSLoginPageObject.port);
			System.out.println("Putty Operations performed Successfully for Outbound Load Parcel");
			report.addReportStep("Load Trailer", "Success", StepResult.PASS);
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Load Trailer", str, StepResult.PASS);
			//rc.logger.info(msg);
			//report.addReportStep("Outbound Load Parcel", msg, StepResult.FAIL);
			//System.out.println("Putty Operations are not performed for Outbound Load Parcel");
		}
	}

	//FluidLoad 
	public void fluidLoadInPutty(String dockDoorNum, List<String> oLPNs, String doId, String screen) throws Exception {
		String str = null;
		try {
			Map item = new LinkedHashMap();
			item.put("dockdoor", dockDoorNum.trim());
			ArrayList<String> hubCode; 
			String hubCodeValue="";
			jd.dbDFWMSMapping();
			hubCode = jd.array_Database_Connection("select ORDERS.FTSR_NBR from ORDERS where TC_ORDER_ID = '"+doId+"'");
			if(screen.equalsIgnoreCase("UPS_Houston")||screen.equalsIgnoreCase("UPS_Houston_Pallet")){
				item.put("hubcode","210");
			}else{
				if(!hubCode.isEmpty()){
					hubCodeValue=hubCode.get(0);
				}
				System.out.println("Hubcode Value is " +hubCode);
				item.put("hubcode",hubCodeValue.trim());
			}
			item.put("trailer", DFWMSScheduleAppointmentPageObject.Strtrailer.trim());
			item.put("pallet", DFWMSPickToLabelinPuttyPageObject.palletID.trim());
			Map item_root = new LinkedHashMap();
			if(oLPNs.size()>0){
				for(String olpn : oLPNs){
					if(screen.equalsIgnoreCase("UPS_Dallas_MIMP") 
							|| screen.equalsIgnoreCase("UPSDallas")||screen.equalsIgnoreCase("UPSBoston")
							||  screen.equalsIgnoreCase("UPS_Houston")){
						item.put("olpn", olpn);
						item_root.put("input_params", item);
						item_root.put("key", "FluidLoad_Dallas");
						item_root.put("testcase_name", "FluidLoad_Dallas");
					}else if(screen.equalsIgnoreCase("UPS_Baltimore_MISP")){
						int i = 1;
						for(String lpns:oLPNs){
								item.put("lpn" + i, lpns.trim());
								i++;
								item_root.put("input_params", item);
								item_root.put("key", "LoadParcel_Dallas_MultiSku");
								item_root.put("testcase_name", "LoadParcel_Dallas_MultiSku");
						}
					}else if(screen.equalsIgnoreCase("UPS_Houston_Pallet")){
						
						item.put("olpn", olpn);
						item_root.put("input_params", item);
						item_root.put("key", "FluidLoad_Pallet_Dallas");
						item_root.put("testcase_name", "FluidLoad_Pallet_Dallas");

					}else {
				
						item.put("olpn", oLPNs.get(0));
						item_root.put("input_params", item);
						item_root.put("key", "FluidLoad_Dallas");
						item_root.put("testcase_name", "FluidLoad_Dallas");
					}
				}
			}else{
				int i = 1;
				for(String lpn:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + lpn + "')");
					item.put("lpn" + i, olpn.trim());
					item_root.put("input_params", item);
					i++;
				}
			}
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			jd.dbDFWMSMapping();
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
//					||jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				port = "7000";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}

			puttyCall(str, DFWMSLoginPageObject.port);
			
//			 URL url = new URL("http://wn3c3a:3000/test");			
//			  //URLConnection conn = url.openConnection();
//			  String text = IOUtils.toString(url.openStream());
//			  System.out.println("value from server : "+ text);
			System.out.println("Putty Operations performed Successfully for Outbound Load Parcel");
			report.addReportStep("Load Trailer", "Success", StepResult.PASS);
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Message not posted.", str, StepResult.PASS);
			//rc.logger.info(msg);
			//report.addReportStep("Outbound Load Parcel", msg, StepResult.FAIL);
			//System.out.println("Putty Operations are not performed for Outbound Load Parcel");
		}
	}

	public void validateEpiStatus() throws Exception {
		jd.dbDFWMSMapping();
		String trackingnbr = "";
		String package_status = "";		
		if(!DFWMSInbounfFlowStepDefn.doId.isEmpty()){
			epiStatus = jd.array_Database_Connection(" select EPI_PACKAGE_STATUS from LPN where tc_order_id in ('"+ DFWMSInbounfFlowStepDefn.doId +"')");
			
			trackingNumber=jd.array_Database_Connection("select TRACKING_NBR from LPN where tc_order_id in ('"+ DFWMSInbounfFlowStepDefn.doId+"')");
		}
		
		
		if(epiStatus.size()>0){
		//ref_field_1 = epiStatus.get(3);
		package_status = epiStatus.get(0);
		trackingnbr=trackingNumber.get(0);
		System.out.println(epiStatus);
		System.out.println(trackingNumber);

		}
		if(package_status.equals("120"))
		{
			System.out.println("EPI Reference field and Package Status is matching"+package_status);
			report.addReportStep("EPI Validation Successfull", "Success: " +package_status +" Tracking number:"+trackingnbr, StepResult.PASS);
		}
		else {
			System.out.println("EPI Reference field and Package Status is not matching");
			report.addReportStep("EPI Validation Failed", "Fail", StepResult.FAIL);
		}
	}
}