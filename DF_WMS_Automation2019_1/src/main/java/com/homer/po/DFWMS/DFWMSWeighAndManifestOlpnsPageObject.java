package com.homer.po.DFWMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.Keys;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSWeighAndManifestOlpnsPageObject extends PageBase {
	public static String locnSTGBarcode = "";
	public static String locnSUBValue = "";
	public static String subLocnBC = "";
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSWeighAndManifestOlpnsPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public void weighLpns(List<String> oLPNs, String screen) throws Exception {

		Thread.sleep(8000);		
		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		
		try{
			if (wh.isElementPresent(oLPNTxtBox, 5)) {
				Thread.sleep(2000);
				wh.sendKeys(oLPNTxtBox,oLPNs.get(0));
				//wh.sendKeys(oLPNTxtBox,"C0000039801");
				Thread.sleep(1000);
				driver.findElement(oLPNTxtBox).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}
			
			if(wh.isElementPresent(weightTxtBox, 5)) {
				wh.sendKeys(weightTxtBox,"1");
				
			}
			
			if (wh.isElementPresent(weighBtn, 5)) {
				wh.clickElement(weighBtn);
				Thread.sleep(1000);
			}
			if (wh.isElementPresent(exitBtn, 5)) {
				wh.clickElement(exitBtn);
				Thread.sleep(1000);
			}
			
			report.addReportStep("Weigh and Manifest", "Weigh and Manifest Successful", StepResult.PASS);
			closebtn();
		
		}catch(Exception e) {
			System.out.println("Weigh And Manifest oLPNS Screen Catch");
			report.addReportStep("Navigate to Weigh And Manifest oLPNS  screen","Weigh And Manifest oLPNS Screen - " + e.getMessage(),StepResult.WARNING);
			rc.throwTCTerminationException();
	}
		
		
		
	}

	public void anchorForConPackByPass(String screen, String stgLocnBarcode,List<String> oLPNs, String palletID) {
try {
			
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			String olpn = oLPNs.get(0);
			/*int i = 1;
			for(String lpns:oLPNs){
					item.put("lpn" + i, lpns.trim());
					i++;	
			}*/
			
			
			
				
			
			
			stgLocnBarcode = getSTGLocnDetails(screen);
			item.put("pallet",palletID);
			item.put("stglocn",stgLocnBarcode);  
			item.put("olpn",olpn);
			
			
			item_root.put("key", "Outbound_AnchorInPutty");
			item_root.put("testcase_name","Outbound_AnchorInPutty");
			item_root.put("input_params", item);
			
			JSONObject json = new JSONObject(item_root);
			Thread.sleep(5000);
			str = json.toJSONString();
			System.out.println(str);
			
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019") 
//					|| jd.envrnment.equalsIgnoreCase("Newark_2019")){
//					port = "6000";
//				}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//					port = "4000";
//				}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
//						|| jd.envrnment.equalsIgnoreCase("Miami_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
//						|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//					port = "7000";
//				}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//					port = "8000";
//				}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//					port = "9000";
//				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//					port = "3000";
//				}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//					port = "3000";
//				}

			
			puttyCall(str, DFWMSLoginPageObject.port);
			Thread.sleep(5000);
			subLocnBC = getSUBLocnDetails(screen,olpn);
			Thread.sleep(5000);
			System.out.println("Sub locn value from db is " +subLocnBC);
			
		
		}
		catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("Outbound Anchor Transaction in Putty", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Anchor Transaction");
		}
	}

	String getSUBLocnDetails(String screen, String olpn) throws Exception {

		jd.dbDFWMSMapping();
		Thread.sleep(1000);
		
		locnSUBValue = jd.str_Database_Connection("SELECT CURR_SUB_LOCN_ID from LPN where TC_LPN_ID = '"+olpn+"' ");
		System.out.println("SUB Locn value for EZ Shipment " +locnSUBValue);
		
		return locnSUBValue;
	}

	public String getSTGLocnDetails(String screen) throws Exception{
		jd.dbDFWMSMapping();
		
		
		String stgLocn = "";
		if(screen.equalsIgnoreCase("EZShipment_Baltimore")){
			stgLocn = "STG-538";
			//locnSTGBarcode = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('STG-538')"); //STG-101
		}else if(screen.equalsIgnoreCase("EZShipment_Tampa")){
			stgLocn = "STG-239";
			//locnSTGBarcode = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('STG-538')"); //STG-101
		}else if(screen.equalsIgnoreCase("EZShipment_Newark")){
			stgLocn = "STG-704";
			//locnSTGBarcode = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('STG-538')"); //STG-101
		}else{	
			stgLocn = "STG-101";
		}
		locnSTGBarcode = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('"+stgLocn+"')");
		System.out.println("STG locn value for EZ Shipment " +locnSTGBarcode);
		return locnSTGBarcode;
	}
	
		
	}

	
		
	
	
	
	

