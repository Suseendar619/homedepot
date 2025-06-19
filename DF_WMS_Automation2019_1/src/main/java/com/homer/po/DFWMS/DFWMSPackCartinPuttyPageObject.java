package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPackCartinPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSPackCartinPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public void packCartInPutty(List<String> soLPNs, List<String> itemNames, String cartID, String itemName) throws Exception {
		String str = "";
		try {
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			int i = 1;
			for(String lpns:soLPNs){
					item.put("lpn" + i, lpns.trim());
					i++;	
			}
			item.put("cart",cartID );
			item.put("itembc1",itemName);
			//item.put("itembc2",itemNames.get(1));
			
			item_root.put("key", "Outbound_PackCart");
			item_root.put("testcase_name","Outbound_PackCart");
			item_root.put("input_params", item);
			
			JSONObject json = new JSONObject(item_root);
			Thread.sleep(5000);
			str = json.toJSONString();
			System.out.println(str);
			
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
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				port = "8000";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				port = "9000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}



			puttyCall(str, DFWMSLoginPageObject.port);
		
		}
		catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("Outbound Pack Cart in Putty", str, StepResult.PASS);
			//throw new Exception("Putty Operations are not performed for Outbound Pack Cart");
		}
	}

	
	
		
	}


