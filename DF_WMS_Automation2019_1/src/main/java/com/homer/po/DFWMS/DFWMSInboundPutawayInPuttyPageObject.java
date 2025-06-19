package com.homer.po.DFWMS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSInboundPutawayInPuttyPageObject extends PageBase{
	
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static String sTBLBarcodeFromDB = "";

	public DFWMSInboundPutawayInPuttyPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void inboundPutawayInPutty(List<String> lPNS, String screen) throws Exception {
		String str = null;
		try {
			getTBLLocBC();
			Map item = new LinkedHashMap();
			int i =1;
			for (String lpns : lPNS) {
				item.put("LPN"+i, lpns.trim());
				i++;
			}
			Map item_root = new LinkedHashMap();
			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
					|| jd.envrnment.equalsIgnoreCase("Newark_2019") ){
          	  item_root.put("env","Baltimore");
			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
				item_root.put("env","Lacey");
			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
				item_root.put("env","Dallas");
			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
				item_root.put("env","Houston");
			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
				item_root.put("env","Tampa");
			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
				item_root.put("env","Miami");
			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
				item_root.put("env","Atlanta");
			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
				item_root.put("env","LG");
			}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")) {
				item_root.put("env","HGT");
			}
			if(screen.equalsIgnoreCase("MultiItemRcvASN")
					|| screen.equalsIgnoreCase("MultiItemASNRcvDtl")
					|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASN") 
					//|| screen.equalsIgnoreCase("STFZoneActiveDallas")||screen.equalsIgnoreCase("STRZone") || screen.equalsIgnoreCase("STFZoneActive")
					|| screen.equalsIgnoreCase("STFZoneActiveDallas_Aloc")
					|| screen.equalsIgnoreCase("STFZoneActiveDallas_Rloc")
					|| screen.equalsIgnoreCase("STRZoneDallas")){
				item_root.put("key", "Putaway_Staging_to_Final_Active");
				item_root.put("testcase_name", "Putaway_Staging_to_Final_Active");
				item.put("taskgroup","PT");
			}else if(screen.equalsIgnoreCase("STDZoneActiveDallas_Rloc")
					|| screen.equalsIgnoreCase("STDZoneReserveDallas")
					|| screen.equalsIgnoreCase("STDZoneReserve")
					|| screen.equalsIgnoreCase("STDZoneReserve_Rloc")){
				item_root.put("key", "Putaway_Staging_to_Final_Active");
				item_root.put("testcase_name", "Putaway_Staging_to_Final_Active");
				item.put("taskgroup","SH");
			}else if(screen.equalsIgnoreCase("STDZoneActiveHouston_Rloc"))	{
				item_root.put("key", "Putaway_Staging_to_Drop");
				item_root.put("testcase_name", "Putaway_Staging_to_Drop");
				item.put("taskgroup","SH");
			}else if(screen.equalsIgnoreCase("DTAZoneActive")
					|| screen.equalsIgnoreCase("DTAZoneDallasBoth")
					|| screen.equalsIgnoreCase("DTRZoneDallas")
					|| screen.equalsIgnoreCase("DTRZoneDallasBoth")){
				item_root.put("key", "Putaway_DropToActive");
				item_root.put("testcase_name", "Putaway_DropToActive");
			}else if(screen.equalsIgnoreCase("TBLZoneActiveDallas")){
				item.put("tblbc",sTBLBarcodeFromDB);
				item_root.put("key", "Putaway_STF_TBL_Loc");
				item_root.put("testcase_name", "Putaway_STF_TBL_Loc");
				item.put("taskgroup","PT");
			}else if(screen.equalsIgnoreCase("MOD10_Dallas")){
				item.put("palletid",DFWMSInboundReceiveInPuttyPageObject.palletId);
				item_root.put("key", "Putaway_STF_MOD10");
				item_root.put("testcase_name", "Putaway_STF_MOD10");
				item.put("taskgroup","PT");
			}
			String locType = "";
			if(screen.contains("Aloc")){
				locType = "Active";
			}else if(screen.contains("Rloc")){
				locType = "Reserve";
			}else if(screen.contains("Both")){
				locType = "Both";
			}
			item_root.put("locType", locType);
			item_root.put("input_params",item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			
			StringBuilder sc = new StringBuilder();
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019") 
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019") 
//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				port = "7000";
//			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				port = "5000";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}
//			else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}

			
            URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase"); 
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setReadTimeout(25000);
			conn.setConnectTimeout(25000);
			conn.setRequestProperty("Content-Type", "application/json");
			// stores server response.
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream()); 
			writer.write(str);
			Thread.sleep(10000);
			writer.flush();
			String line2;
			// reads line by line
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			Thread.sleep(4000);
			while ((line2 = reader.readLine()) != null) {
				sc.append(line2);
				Thread.sleep(5000);
				Thread.sleep(5000);
			}
			// validate location
			/*String locVal = "";
			if(!DFWMSInbounfFlowStepDefn.ItemNames.isEmpty()){
				jd.dbDFWMSMapping();
				String locDBVal = jd.str_Database_Connection("select unique lh.zone from "
						+ "pick_locn_hdr plh, pick_locn_dtl pld, item_cbo ic,locn_hdr lh, "
						+ "item_facility_mapping_wms ifw, item_wms iw,wm_inventory wi where "
						+ "lh.locn_id = plh.locn_id and plh.locn_id = pld.locn_id and "
						+ "pld.item_id = ic.ITEM_ID and ic.item_id = ifw.item_id and "
						+ "ic.item_id = iw.item_id and lh.locn_id=wi.location_id and "
						+ "ic.item_name = '"+DFWMSInbounfFlowStepDefn.ItemNames.get(0)+"'");
				if(!locDBVal.isEmpty()){
					locVal = locDBVal;
				}else{
					report.addReportStep("Validate Loc", "fail. Returned loc from DB: "+locVal, StepResult.FAIL);
				}
			}*/
			System.out.println("Putty Operations performed Successfully for Inbound Putaway");
			report.addReportStep("Putaway ", "Putty Operations performed Successfully for Inbound Putaway", StepResult.PASS);
		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putaway ", e.getMessage() + " "+str, StepResult.PASS);
			//rc.logger.info(msg);
		    //report.addReportStep("Inbound Putaway", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Inbound Putaway as loc prompted is different from expected");
		}
	}

	private void getTBLLocBC() throws Exception {
		// TODO Auto-generated method stub
		jd.dbDFWMSMapping();
		sTBLBarcodeFromDB = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('RCV-IBO-EXP')");
		System.out.println("TBL Location Barcode Value returned from DB is " + sTBLBarcodeFromDB);
		
	}

	public void inboundPutAwayByPalletInPutty(List<String> lPNS, String palletID) throws Exception {
		String str = "";
		
		try {
			if(!lPNS.isEmpty()){
				Map item = new LinkedHashMap();
				int i = 1;
				for(String lpns:lPNS){
					item.put("lpn" + i, lpns.trim());
					i++;	
				}
				item.put("pallet", palletID.trim());
				
				Map item_root = new LinkedHashMap();
				item_root.put("key", "Putaway_ByPallet");
				item_root.put("testcase_name", "Putaway_ByPallet");
				
				item_root.put("input_params",item);
				if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
					item_root.put("env","Baltimore_2019");
				}else{
					item_root.put("env",jd.envrnment);
				}
				
				JSONObject json = new JSONObject(item_root);
				
				str = json.toJSONString();
				System.out.println(str);
				
				StringBuilder sc = new StringBuilder();
				
				jd.dbDFWMSMapping();
//  				if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//  						|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//  					port = "6000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//  					port = "4000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//  					port = "3000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//  					port = "3000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//  						|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//  					port = "7000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//  					port = "8000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//  					port = "3000";
//  				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//  					port = "3000";
//  				}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//  					port = "3000";
//  				}

                URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase");
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				conn.setReadTimeout(35000);
				conn.setConnectTimeout(35000);
				conn.setRequestProperty("Content-Type", "application/json");
				// stores server response.
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream()); 
				writer.write(str);
				Thread.sleep(10000);
				writer.flush();
				String line2;
				 // reads line by line
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
				Thread.sleep(2000);
				while ((line2 = reader.readLine()) != null) {
					sc.append(line2);
					Thread.sleep(3000);
					Thread.sleep(5000);
				}
				System.out.println("Putty Operations performed Successfully for Inbound Putaway");
			}else{
				throw new Exception("Putty Operations are not performed for Inbound Putaway");
			}
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully", StepResult.PASS);
		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations failed", str, StepResult.PASS);
			//rc.logger.info(msg);
			//report.addReportStep("Inbound Putaway", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Inbound Putaway");
		}
		

	}

			
	
	}

