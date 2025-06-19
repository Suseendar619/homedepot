package com.homer.po.DFWMS;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSInboundReceiveInPuttyPageObject extends PageBase{

	public List<String> LPNS = new ArrayList<String>();
	public List<String> SerialNo = new ArrayList<String>();
	public List<String> PalletIds = new ArrayList<String>();
	public static String palletId;
	public String serialNumber;
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSInboundReceiveInPuttyPageObject(InstanceContainer ic) {
		super(ic);
	}



	public List<String> inboundReceiveInPutty(List<String> itemName, String dockDoorNum, String asn, String screen, List<String> pendingLpns, String ShipmentId) throws Exception {
		String str = null;
		try {
			System.out.println("ItemName :"+itemName +" Dock Door Num : "+ dockDoorNum);

			int qty = 0;
			String currentDate = new SimpleDateFormat("MMddyymms").format(Calendar.getInstance().getTime());//TMMDDYYHHmm
			int LPN = Integer.parseInt(currentDate);
			palletId = "P"+currentDate;
			System.out.println("Pallet Id generated is " +palletId);
			if(screen.equalsIgnoreCase("VerifyIBShipment2019") 
					|| screen.equalsIgnoreCase("VerifyIBShipment2019Dallas") ){
				qty = 105;
			}else{
				qty = 1;
			}
			serialNumber = LPN +"0";
			System.out.println(serialNumber);

			Map item = new LinkedHashMap();
			item.put("dockdoor", dockDoorNum.trim());
			if (pendingLpns.size() > 0) {
				LPNS = pendingLpns;
				for (String lpn : LPNS) {
					for(int i=1;i<=LPNS.size();i++){    
						item.put("lpn" + i, lpn.trim());
						System.out.println(lpn);
						i++;
					}
				}
			} else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASN") 
					|| screen.equalsIgnoreCase("MultiItemRcvASN") 
					|| screen.equalsIgnoreCase("MultiItemASNRcvDtl") 
					|| screen.equalsIgnoreCase("VerifyIBShipment2019")
					|| screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
				// get lpns from asn
				int iSizeCount=0;
				List<String> siLPNValuefromDB;
				jd.dbDFWMSMapping();
				siLPNValuefromDB = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ASN_ID = '" + asn + "'");
				if (siLPNValuefromDB.size() > 0) {
					int i = 1;
					for (String lpn : siLPNValuefromDB) {
						if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASN") 
								|| screen.equalsIgnoreCase("MultiItemASNRcvDtl")
								|| screen.equalsIgnoreCase("VerifyIBShipment2019")
								|| screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
							item.put("lpn" + i, lpn.trim());
						}
						LPNS.add(lpn);
						i++;
					}
				}else {
					for(int i=1;i<=qty;i++){
						//Jan to Sep L0
						String lpn = "L0"+LPN++;
						LPNS.add(lpn);
						item.put("lpn"+i, lpn.trim());
						//System.out.println("LPN"+i + " "+ "L0"+LPN++);
					}
				}
				item.put("asn", asn);
			} else if (screen.equalsIgnoreCase("ASN_ShortageDallas")){
				for(int i=1;i<=3;i++){
					//Jan to Sep L0
					String lpn = "L0"+LPN++;
					LPNS.add(lpn);
					item.put("lpn"+i, lpn.trim());
				}
			}else {
				for(int i=1;i<=qty;i++){
					//Jan to Sep L0
					String lpn = "L0"+LPN++;
					LPNS.add(lpn);
					item.put("lpn"+i, lpn.trim());
				}
			}
			if(ShipmentId != null){
				item.put("ShipmentId", ShipmentId.trim());
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
			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
				item_root.put("env","Columbus");
			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
				item_root.put("env","LG");
			}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
				item_root.put("env","HGT");
			}


			if(screen.equalsIgnoreCase("MultiItemRcvASN")){
				item_root.put("key", "Inbound_rcv_asn");
				item_root.put("testcase_name", "Inbound_rcv_asn");
				item.put("asn", asn);
			}else if(screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
				item_root.put("key", "VerifyIBShipment_rcv");
				item_root.put("testcase_name", "VerifyIBShipment_rcv");
				item.put("itembc1", itemName.get(0).trim());
				item.put("itembc2", itemName.get(1).trim());
				item_root.put("lpnCount", qty);
			}else if(screen.equalsIgnoreCase("VerifyIBShipment2019")){
				item_root.put("key", "VerifyIBShipment_rcv_Baltimore");
				item_root.put("testcase_name", "VerifyIBShipment_rcv_Baltimore");
				item.put("itembc1", itemName.get(0).trim());
				item.put("itembc2", itemName.get(1).trim());
				item.put("itembc3", itemName.get(2).trim());
				item_root.put("lpnCount", qty);
			}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtl") || 
					screen.equalsIgnoreCase("ShipmentRcvDtl")){
				item.put("itembc1", itemName.get(0).trim());
				item.put("itembc2", itemName.get(1).trim());
				item_root.put("key", "Inbound_rcv");
				item_root.put("testcase_name", "Inbound_rcv");
				item_root.put("lpnCount", qty);
			}else if(screen.equalsIgnoreCase("Zone")){
				item.put("itembc", itemName.get(0).trim());
				item_root.put("key", "Inbound_rcv_zone_pack");
				item_root.put("testcase_name", "Inbound_rcv_zone_pack");
			}else if(screen.equalsIgnoreCase("SerialZone")){
				for(int i=1;i<=5;i++){
					String serial = "0"+LPN++;
					SerialNo.add(serial);
					System.out.println(serial);
					item.put("serial"+i, serial.trim());
				}
				item_root.put("key", "Inbound_rcv_zone_serial");
				item_root.put("testcase_name", "Inbound_rcv_zone_serial");
			}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")){
				item_root.put("key", "ShipmentMultiItemRcvASN");
				item_root.put("testcase_name", "ShipmentMultiItemRcvASN");
			}else if(screen.equalsIgnoreCase("DallasZone")){
				item.put("itembc", itemName.get(0).trim());
				item_root.put("key", "Inbound_rcv_zone");
				item_root.put("testcase_name", "Inbound_rcv_zone");
			}
			else if(screen.equalsIgnoreCase("AtlantaZone")){
				item.put("itembc", itemName.get(0).trim());
				item_root.put("key", "Inbound_rcv_zone");
				item_root.put("testcase_name", "Inbound_rcv_zone");
			}else if(screen.equalsIgnoreCase("ASN_OverageDallas")){
				item.put("choice", "4");
				item.put("itembc", itemName.get(0).trim());
				item.put("Qty", "2");
				item_root.put("key", "Inbound_rcv_ASN_Overage");
				item_root.put("testcase_name", "Inbound_rcv_ASN_Overage");
			}else if(screen.equalsIgnoreCase("ZonePack")
					|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASNHoutson")){ 
				item.put("itembc", itemName.get(0).trim());
				item_root.put("key", "Inbound_rcv_zone_pack");
				item_root.put("testcase_name", "Inbound_rcv_zone_pack");
			}else if(screen.equalsIgnoreCase("RcvAndSortiLPN")){
				item.put("itembc", itemName.get(0).trim());
				item.put("pallet", palletId.trim());
				item_root.put("key", "Inbound_rcv_and_sort");
				item_root.put("testcase_name", "Inbound_rcv_and_sort");
			}else if(screen.equalsIgnoreCase("ASN_ShortageDallas")){
				item.put("itembc", itemName.get(0).trim());
				item_root.put("key", "Inbound_rcv_ASN_Shortage");
				item_root.put("testcase_name", "Inbound_rcv_ASN_Shortage");
			}else if(screen.equalsIgnoreCase("RcvAndSortiLPN_MOD10")){
				//item.put("itembc", itemName.get(0).trim());
				item.put("pallet", palletId.trim());
				item.put("itembc1", itemName.get(0).trim());
				item.put("itembc2", itemName.get(1).trim());
				item_root.put("key", "Inbound_rcv_and_sort_MOD10");
				item_root.put("testcase_name", "Inbound_rcv_and_sort_MOD10");
			}

			/* if(screen.equalsIgnoreCase("RcvAndSortiLPN")){
                  item.put("choice", "1");
              }else if(screen.equalsIgnoreCase("DallasZone")){
                  item.put("choice", "3");
              }else{
                  item.put("choice", "4");
              }*/
			item_root.put("input_params",item);

			JSONObject json = new JSONObject(item_root);

			str = json.toJSONString();
			System.out.println(str);

			StringBuilder sc = new StringBuilder();
			//String port = "";
			jd.dbDFWMSMapping();
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019") 
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//				port = "3000"; //5000
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019") 
//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				port = "7000";
//			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//					port = "3000";
//				}



			URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase"); 
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			if(screen.equalsIgnoreCase("VerifyIBShipment2019") 
					|| screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
				conn.setReadTimeout(80000);
				conn.setConnectTimeout(80000);
			}else{
				conn.setReadTimeout(25000);
				conn.setConnectTimeout(25000);
			}
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
				Thread.sleep(4000);
				Thread.sleep(5000);
			}
			System.out.println("Putty Operations performed Successfully for Inbound Receive");
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully", StepResult.PASS);
		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations Fail", e.getMessage()+ " "+str, StepResult.PASS);
			//rc.logger.info(msg);
			// report.addReportStep("Post Message", str, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Inbound Receive");
		}
		return LPNS;
	}

	public List<String> getInTransitLpns(String aSNID) throws Exception {
		List<String> siLPNValuefromDB;
		jd.dbDFWMSMapping();
		siLPNValuefromDB = jd
				.array_Database_Connection("SELECT LP.TC_LPN_ID FROM LPN LP,LPN_DETAIL LPD,LPN_FACILITY_STATUS "
						+ "LFS WHERE LP.LPN_ID=LPD.LPN_ID AND LFS.LPN_FACILITY_STATUS = LP.LPN_FACILITY_STATUS AND "
						+ "LP.TC_ASN_ID = '" + aSNID
						+ "' and LP.LPN_FACILITY_STATUS ='0' AND LFS.INBOUND_OUTBOUND_INDICATOR = 'I'");
		System.out.println("iLPN's Geneated for ASN " + aSNID + "is" + siLPNValuefromDB);

		return siLPNValuefromDB;

	}

	@SuppressWarnings("unchecked")
	public String inboundReceiveByPalletInPutty(List<String> itemNames,
			String dockDoorNum, String aSNID, List<String> siLPNs) throws Exception {

		String str = "";
		try {
			System.out.println("ItemName :"+itemNames +" Dock Door Num : "+ dockDoorNum);
			String lpn1,lpn2,lpn3,lpn4,lpn5;
			int qty = 4;
			String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());//TMMDDYYHHmm
			if(!siLPNs.isEmpty()){
				Map item = new LinkedHashMap(); 
				//Pallet Id Generation
				palletId = "P0"+currentDate;
				item.put("pallet1", palletId.trim());
				int pallet2 =  Integer.parseInt(currentDate)+1;
				item.put("pallet2", "P0"+pallet2);
				System.out.println(palletId);
				item.put("asn", aSNID.trim());
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
				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
					item_root.put("env","Columbus");
				}else if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
					item_root.put("env","LG");
  				}else if(jd.envrnment.equalsIgnoreCase("Perris_2019")){
					item_root.put("env","Perris");
  				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
  					item_root.put("env","Columbus");
  				}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
  					item_root.put("env","HGT");
  				}



				int i = 1;
				for(String lpns:siLPNs){
					item.put("lpn" + i, lpns.trim());
					i++;  
				}
				item.put("ShipmentId", DFWMSInbounfFlowStepDefn.ShipmentIdInbound);
				item.put("dockdoor", dockDoorNum.trim());
				item.put("itembc1", itemNames.get(0).trim());
				item.put("itembc2", itemNames.get(1).trim());
				if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
					item_root.put("key","ShipmentMultiItemRcvASN");
					item_root.put("testcase_name","ShipmentMultiItemRcvASN");
				}else{
					item_root.put("key","Inbound_Rcv_ByPallet");
					item_root.put("testcase_name","Inbound_Rcv_ByPallet");
				}

				item_root.put("lpnCount", qty);
				item_root.put("input_params",item);



				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);

				StringBuilder sc = new StringBuilder();
				// URL url = new URL("http://wn3c3a:4000/executeTestcase");
				//String port = "";
				jd.dbDFWMSMapping();
//				if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//						|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//					port = "6000";
//				}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//					port = "4000";
//				}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//						|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//					port = "7000";
//				}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//					port = "8000";
//				}
//				else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//					port = "3000";
//				}

				URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase");
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				conn.setReadTimeout(15000);
				conn.setConnectTimeout(15000);
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
				System.out.println("Putty Operations performed Successfully for Inbound Receive By pallet");
				report.addReportStep("Putty Operations", "Putty Operations performed Successfully", StepResult.PASS);
			}
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations failed", str, StepResult.PASS);
			//rc.logger.info(msg);
			//report.addReportStep("Post Message", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Inbound Receive");
		}
		return palletId;
	}

	public void inboundRcv() throws TCTerminationException{
		try{
			int j = 0;
			wh.clickElement(Maximize);
			driver.switchTo().frame(0);
			Thread.sleep(1000);

			clickElement(RFMenuInfo);
			clickElement(RFFindTrans);
			sendKeys(RFTransSearch, "Rcv Dtl (THD)");
			clickElement(RFFindTrans);
			clickElement(RFTransSearchMenu);
			sendKeys(RFDockDoor, DFWMSInbounfFlowStepDefn.DockDoorNum);
			driver.findElement(RFDockDoor).sendKeys(Keys.ENTER);
			driver.findElement(RFShipment).sendKeys(Keys.ENTER);
			for(int i = 0; i<LPNS.size();i++){
				if(LPNS.size()>i){
					sendKeys(RFLPN, LPNS.get(i));
					i++;
					driver.findElement(RFLPN).sendKeys(Keys.ENTER);
				}
				if(DFWMSInbounfFlowStepDefn.ItemNames.size()>j){
					sendKeys(RFItemBC, DFWMSInbounfFlowStepDefn.ItemNames.get(j));
					j++;
					driver.findElement(RFItemBC).sendKeys(Keys.ENTER);
				}
				sendKeys(RFPackQty, "1");
				driver.findElement(RFPackQty).sendKeys(Keys.ENTER);
				sendKeys(RFQty, "1");
				driver.findElement(RFQty).sendKeys(Keys.ENTER);
			}

			report.addReportStep("RF Menu","Opened RF Menu", StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("RF Menu","Unable to open RF Menu", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void outboundInPutty(String value,String screen) throws TCTerminationException{
		try{
			ArrayList<String> olpns = new ArrayList<String>();
			String[] words=null;
			int j = 0;
			wh.clickElement(Maximize);
			driver.switchTo().frame(0);
			Thread.sleep(1000);

			clickElement(RFMenuInfo);
			clickElement(RFFindTrans);
			sendKeys(RFTransSearch, value);
			clickElement(RFFindTrans);
			clickElement(RFTransSearchMenu);
			sendKeys(RFDockDoor_Outbound, DFWMSInbounfFlowStepDefn.DockDoorNum);
			driver.findElement(RFDockDoor_Outbound).sendKeys(Keys.ENTER);


			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);


			Thread.sleep(1000);

			ArrayList<String> lpns = jd.lpnValue("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");

			olpns.addAll(lpns);

			System.out.println("LPNS: "+olpns);


			driver.findElement(RFLPN_Outbound).sendKeys(olpns.get(0),Keys.ENTER);


			Thread.sleep(1000);
			//			r.keyPress(KeyEvent.VK_ENTER);
			//			r.keyRelease(KeyEvent.VK_ENTER);
			String oLPNText = driver.findElement(RFLPN_Outbound_Text).getText();

			if(screen.equalsIgnoreCase("Divert History Missing")||screen.equalsIgnoreCase("Invalid oLPN Status to be loaded")
					||screen.equalsIgnoreCase("has a lock code")||screen.equalsIgnoreCase("has Non-Parcel Ship Via")) {

				words=oLPNText.split("Error");//splits the string based on string

			}else if(screen.equalsIgnoreCase("Carrier does not match Dock Door Carrier")) {

				words=oLPNText.split("/");//splits the string based on string
			}

			//using java foreach loop to print elements of string array
			String text=words[1];
			System.out.println(text);


			if(text.trim().contains(screen)) {
				report.addReportStep("RF Menu",text, StepResult.PASS);

			}else {
				report.addReportStep("RF Menu","Unable to get text from  RF Menu", StepResult.FAIL);

			}


			

		}catch(Exception e){
			report.addReportStep("RF Menu","Unable to open RF Menu", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public static void sendKeys(By byElem, String searchString) throws Exception {
		if(wh.isElementPresent(byElem, 5)){
			wh.sendKeys(byElem, searchString);
		}else{
			report.addReportStep("Send Keys","Unable to send keys: "+byElem, StepResult.FAIL);
		}
	}

	public static void clickElement(By byElem) throws Exception {
		if(wh.isElementPresent(byElem, 5)){
			wh.clickElement(byElem);
		}else{
			report.addReportStep("Click Element","Unable to click element: "+byElem, StepResult.FAIL);
		}
	}

	public void inboundPutaway(String flow) throws TCTerminationException {
		try{
			wh.clickElement(Maximize);
			driver.switchTo().frame(0);
			Thread.sleep(1000);

			clickElement(RFMenuInfo);
			clickElement(RFChngTaskGrp);
			sendKeys(RFTaskGrp, "SH");
			driver.findElement(RFTaskGrp1).sendKeys(Keys.ENTER);
			driver.findElement(RFTaskGrp2).sendKeys(Keys.ENTER);
			clickElement(RFMenuInfo);
			clickElement(RFFindTrans);
			sendKeys(RFTransSearch, "Ptwy iLPN (THD)");
			clickElement(RFFindTrans);
			clickElement(RFTransSearchMenu);


			report.addReportStep("RF Menu","Inbound Putaway", StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("RF Menu","Inbound Putaway Failed", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
}