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

public class DFWMSPickCartPickinginPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	public String cartID;
	public DFWMSPickCartPickinginPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public String pickCartinPutty(List<String> soLPNs) throws Exception {
		try {
			
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			String currentDate = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
			int Cart = Integer.parseInt(currentDate);
			cartID = "CRT" + Cart;
			if(cartID.length() < 7){
				cartID = cartID+0;
			}
			
			String Slot = Integer.toString(Cart);
			if(Slot.length() < 4){
				Slot = Integer.toString(Cart)+0;
			}
			
			int i = 1;
			for(String lpns:soLPNs){
					item.put("lpn" + i, lpns.trim());
					i++;	
			}
			item.put("cart",cartID );
			item.put("slot",Slot );
			//item.put("itembc1",itemNames.get(0));
			
			item_root.put("key", "Outbound_PickCart_Picking");
			item_root.put("testcase_name","Outbound_PickCart_Picking");
			
			item_root.put("input_params", item);
			
			JSONObject json = new JSONObject(item_root);

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
			//rc.logger.info(msg);
			//report.addReportStep("Outbound Pick Cart Picking", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Pick From Tote");
		}
		return cartID;
	}

		
	}


