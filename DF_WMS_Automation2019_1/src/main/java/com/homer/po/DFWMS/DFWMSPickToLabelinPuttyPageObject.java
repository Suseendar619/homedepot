package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPickToLabelinPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static int lpnCount = 0;
	public static String palletID = "";
	public static String palletID1 = "";

	public DFWMSPickToLabelinPuttyPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void pickToLabelInPutty(List<String> oLPNs, String itemName, List<String> itemNames, String screen) throws Exception {
		//String palletID = "";
		String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
		int Tote = Integer.parseInt(currentDate);
		palletID = "P0" + Tote;
		if(palletID.length()>10){
			palletID = palletID.substring(0,11);
		}
		
		System.out.println("Pallet ID: " + palletID);
		
		String str = "";
		try {
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			if(oLPNs.size()>0){
				if(screen.equalsIgnoreCase("LTLMultiStopLacey")){
					item.put("lpn1" ,oLPNs.get(1) );
				}else if(screen.equalsIgnoreCase("NonCon_LTL")){
					item.put("lpn1" , oLPNs.get(0));
					
				}else{
					int i = 1;
					for(String lpns:oLPNs){
						item.put("lpn" + i, lpns.trim());
						lpnCount = i; 
						i++;	
					}
				}
			}else{
				int i = 1;
				for(String lpn:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					if(screen.equalsIgnoreCase("BVR_Split_Shipment") 
							|| screen.equalsIgnoreCase("BVR_Dallas_MIMP") 
							|| screen.equalsIgnoreCase("BVR_Baltimore_MIMP")
							|| screen.equalsIgnoreCase("BVR_Dallas_MISP")
							|| screen.equalsIgnoreCase("Split_Shipment")){
						List<String> olpns = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + lpn + "')");
						lpnCount = olpns.size(); 
						for(String lpns:olpns){
							item.put("lpn" + i, lpns.trim());
							i++;	
						}
					}else if(screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
							|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")){
						String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doIds.get(1) + "')");
						item.put("lpn" + i, olpn.trim());
						lpnCount = i; 
						//i++;
					}
							
					else{
						String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + lpn + "')");
						item.put("lpn" + i, olpn.trim());
						lpnCount = i; 
						i++;
					}
				}
			}
			if(itemName != null){
				if(screen.equalsIgnoreCase("BVR_MS") 
						|| screen.equalsIgnoreCase("BVR_Split_Shipment")
						|| screen.equalsIgnoreCase("Split_Shipment")){
					item.put("itembc1", itemNames.get(0));
					item.put("itembc2", itemNames.get(1));
				}else{
					item.put("itembc", itemName.trim());
				}
			}

			if(screen.equalsIgnoreCase("NonCon")|| screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
					|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
					|| screen.equalsIgnoreCase("LTLMultiStopLacey")|| screen.equalsIgnoreCase("NonCon_LTL")){
				item.put("pallet", palletID.trim());
				item.put("choice", "1");
				item_root.put("key", "Outbound_PickToLabelInPutty");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty");
				item_root.put("lpnCount", Integer.toString(lpnCount));
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("Split_Olpn")){
				item.put("choice", "1");
				item_root.put("key", "SplitOlpn");
				item_root.put("testcase_name", "SplitOlpn");
				item_root.put("input_params", item);
				
				
			}else if(screen.equalsIgnoreCase("Combine_olpn")){
				item.put("choice", "1");
				item_root.put("key", "CombineOlpn");
				item_root.put("testcase_name", "CombineOlpn");
				item_root.put("input_params", item);
				
				
			}else if(screen.equalsIgnoreCase("LoadTrailor_MultiOlpn")){
				
				item.put("pallet", palletID.trim());
				item.put("choice", "1");
				item_root.put("key", "Outbound_PickToLabelInPutty");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty");
				item_root.put("lpnCount", Integer.toString(lpnCount));
				item_root.put("input_params", item);
			}
				
			else if(screen.equalsIgnoreCase("UPS_Dallas_MISP")){
			
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(0).trim());
				item.put("itembc2", itemNames.get(1).trim());
				item_root.put("key", "Outbound_Parcel_MISP");
				item_root.put("testcase_name", "Outbound_Parcel_MISP");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("BVR_Dallas_MIMP")
					|| screen.equalsIgnoreCase("BVR_Baltimore_MIMP")){
				item.put("pallet1", palletID.trim());
				Tote = Integer.parseInt(currentDate)+9;
				String pallet2 = "P0" + Tote;
				if(pallet2.length()>10){
					pallet2 = pallet2.substring(0,11);
					palletID1=pallet2.trim();
				}
				item.put("pallet2", pallet2.trim());
				item.put("itembc1", itemNames.get(0).trim());
				item.put("itembc2", itemNames.get(1).trim());
				item_root.put("key", "Outbound_PckDirCube_MIMP");
				item_root.put("testcase_name", "Outbound_PckDirCube_MIMP");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("BVR_Dallas_MISP")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(1).trim());
				item.put("itembc2", itemNames.get(0).trim());
				item_root.put("key", "Outbound_PckDirCube_MISP");
				item_root.put("testcase_name", "Outbound_PckDirCube_MISP");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("ShortLPN")
					|| screen.equalsIgnoreCase("ShortLPNHouston")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(0).trim());
				if(screen.equalsIgnoreCase("ShortLPN")){
					item_root.put("key", "Outbound_PckDirCube_ShortLPN");
					item_root.put("testcase_name", "Outbound_PckDirCube_ShortLPN");
				}else{
					item_root.put("key", "Outbound_PckDirCube_ShortLPN_Houston");
					item_root.put("testcase_name", "Outbound_PckDirCube_ShortLPN_Houston");
				}
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("ShortLPNBaltimore")){
				item.put("pallet", palletID.trim());
				if(itemName != null){
					item.put("itembc1", itemName.trim());
				}else if(!itemNames.isEmpty()){
					item.put("itembc1", itemNames.get(1));
				}
				
				item_root.put("key", "Outbound_PckDirCube_ShortLPN_Baltimore");
				item_root.put("testcase_name", "Outbound_PckDirCube_ShortLPN_Baltimore");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("SkipLPN")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(0).trim());
				item_root.put("key", "Outbound_PckDirCube_SkipLPN");
				item_root.put("testcase_name", "Outbound_PckDirCube_SkipLPN");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("SkipLPNBaltimore")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemName.trim());
				item_root.put("key", "Outbound_PckDirCube_SkipLPN");
				item_root.put("testcase_name", "Outbound_PckDirCube_SkipLPN");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("Split")){
				

				item.put("pallet", palletID.trim());
				item.put("itembc1", itemName.trim());
				item_root.put("key", "Outbound_PckDirCube_SkipLPN");
				item_root.put("testcase_name", "Outbound_PckDirCube_SkipLPN");
				item_root.put("input_params", item);
				

			}else if(screen.equalsIgnoreCase("BVR_MS")|| screen.equalsIgnoreCase("BVR_Split_Shipment")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(0));
				item.put("itembc2", itemNames.get(1));
				item_root.put("key", "Outbound_PckDirCube_MISP");
				item_root.put("testcase_name", "Outbound_PckDirCube_MISP");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("Split_Shipment")){
				item.put("pallet", palletID.trim());
				item.put("itembc1", itemNames.get(0));
				item.put("itembc2", itemNames.get(1));
				item_root.put("key", "Outbound_SplitShipment");
				item_root.put("testcase_name", "Outbound_SplitShipment");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("BVR_Mutli_Stop")){
				//olpn size indexout
				int olpnSize = DFWMSoLPNsPageObject.soLPNs.size()-1;
				String newOlpn = DFWMSoLPNsPageObject.soLPNs.get(olpnSize).toString();
				item.put("pallet", palletID.trim());
				item.put("newOLPN", newOlpn + 1);
				item_root.put("key", "Outbound_PckDirCubeInPutty");
				item_root.put("testcase_name", "Outbound_PckDirCubeInPutty");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("ConLacey")){
				item.put("pallet", palletID.trim());
				item.put("choice", "8");
				item_root.put("key", "Outbound_PickToLabelInPutty_Convey");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty_Convey");
				item_root.put("input_params", item);
			}
			else if(screen.equalsIgnoreCase("MultiCon")){
                item.put("pallet", palletID.trim());
                item.put("choice", "8");
                item_root.put("key", "Outbound_PickToLabelInPutty_Convey_MultiOlpn");
                item_root.put("testcase_name", "Outbound_PickToLabelInPutty_Convey_MultiOlpn");
                item_root.put("input_params", item);
			}
			else if(screen.equalsIgnoreCase("MultiItem_Con")){
				item.put("itembc1", itemNames.get(1).trim());
				item.put("itembc2", itemNames.get(0).trim());
				item_root.put("key", "Outbound_PickToLabelInPutty_MI_PLTZ_Convey");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty_MI_PLTZ_Convey");
				item_root.put("input_params", item);
			}else if(screen.equalsIgnoreCase("MultiItem_Con_Lacey")){
				item.put("itembc1", itemNames.get(1).trim());
				item.put("itembc2", itemNames.get(0).trim());
				item.put("pallet", palletID.trim());
				item_root.put("key", "Outbound_PickToLabelInPutty_MI_PLTZ_Convey");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty_MI_PLTZ_Convey");
				item_root.put("input_params", item);
			}else{
				item_root.put("key", "Outbound_PickToLabelInPutty_Convey");
				item_root.put("testcase_name", "Outbound_PickToLabelInPutty_Convey");
				item_root.put("input_params", item);
			}
			
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(json.toJSONString());
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
//					|| jd.envrnment.equalsIgnoreCase("Miami_2019")){
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
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound PickToLabel In Putty", StepResult.PASS);
			System.out.println("Putty Operations performed Successfully for Outbound PickToLabel In Putty");
		
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations", str, StepResult.PASS);
		}
	}

public void palletizeOlpnParcel(List<String> oLPNs, String screen) {
		
//		String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
//		int Tote = Integer.parseInt(currentDate);
//		String palletID = "P" + Tote;
//		if(palletID.length()>11){
//			palletID = palletID.substring(0,11);
//		}else if(palletID.length() == 10){
//			palletID = palletID+"0";
//		}
//		System.out.println("Pallet ID:" + palletID);
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			int i = 1;
			for(String lpns:oLPNs){
				item.put("lpn" + i, lpns.trim());
				i++;	
			}
			//Tote = Integer.parseInt(currentDate);
			item.put("pallet", DFWMSPickToLabelinPuttyPageObject.palletID.trim());
			if(screen.equalsIgnoreCase("MultiItemMultiPallet")){
				item.put("pallet", DFWMSPickToLabelinPuttyPageObject.palletID.trim());
				item_root.put("key", "Outbound_Multi_PalletizeOlpnParcel");
				item_root.put("testcase_name", "Outbound_Multi_PalletizeOlpnParcel");
				item_root.put("input_params", item);
			}else{
				item_root.put("key", "Outbound_PalletizeOlpn");
				item_root.put("testcase_name", "Outbound_PalletizeOlpn");
				item_root.put("input_params", item);	
			}
			
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(json.toJSONString());
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019") || 
//					jd.envrnment.equalsIgnoreCase("Miami_2019")){
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
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound Palletize OLPN In Putty", StepResult.PASS);
			System.out.println("Putty Operations performed Successfully for Outbound Palletize OLPN In Putty");
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//report.addReportStep("Palletize OLPN", msg, StepResult.FAIL);
		}
	}

	public void palletizeOlpn(List<String> oLPNs, String screen) {
		
		String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
		int Tote = Integer.parseInt(currentDate);
		String palletID = "P" + Tote;
		if(palletID.length()>11){
			palletID = palletID.substring(0,11);
		}else if(palletID.length() == 10){
			palletID = palletID+"0";
		}
		System.out.println("Pallet ID:" + palletID);
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			int i = 1;
			for(String lpns:oLPNs){
				item.put("lpn" + i, lpns.trim());
				i++;	
			}
			Tote = Integer.parseInt(currentDate);
			String pallet2 = ("P0" + (Tote+1));
			if(pallet2.length()>10){
				pallet2 = pallet2.substring(0,11);
			}
			item.put("pallet", palletID.trim());
			if(screen.equalsIgnoreCase("MultiItemMultiPallet")){
				item.put("pallet2", pallet2.trim());
				item_root.put("key", "Outbound_Multi_PalletizeOlpn");
				item_root.put("testcase_name", "Outbound_Multi_PalletizeOlpn");
				item_root.put("input_params", item);
			}else{
				item_root.put("key", "Outbound_PalletizeOlpn");
				item_root.put("testcase_name", "Outbound_PalletizeOlpn");
				item_root.put("input_params", item);	
			}
			
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(json.toJSONString());
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019") || 
//					jd.envrnment.equalsIgnoreCase("Miami_2019")){
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
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound Palletize OLPN In Putty", StepResult.PASS);
			System.out.println("Putty Operations performed Successfully for Outbound Palletize OLPN In Putty");
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//report.addReportStep("Palletize OLPN", msg, StepResult.FAIL);
		}
	}
}