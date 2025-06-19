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

public class DFWMSPackCubeDirinPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSPackCubeDirinPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	

	public void pckCubeDirInPutty(List<String> oLPNs, String itemName, String screen) throws Exception {
		// TODO Auto-generated method stub
		
		
		String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
		int Tote = Integer.parseInt(currentDate);
		String palletID = "P0" + Tote;
		if(palletID.length()>10)
		{
			palletID = palletID.substring(0,11);
			
		}
		System.out.println("Pallet ID:" + palletID);
		String Time = new SimpleDateFormat("yymmss").format(Calendar.getInstance().getTime());
		int OLPN = Integer.parseInt(Time);
		String oLPN = "C0000" + OLPN;
		String str = "";
		
		try {
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			
			int i = 1;
			for(String lpns:oLPNs){
					item.put("lpn" + i, lpns.trim());
					i++;	
			}
			item.put("pallet", palletID.trim());
			item.put("itembc", itemName.trim());
			
			if(screen.equalsIgnoreCase("BVRDallas_PckCubeDir_Cancel_Olpn")){
				String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

				System.out.println("newOLPN generated is " +olpn);
				item.put("lpn1", olpn.trim());
				item_root.put("key", "Outbound_PckDirCubeInPutty_Cancel_Olpn");
				item_root.put("testcase_name", "Outbound_PckDirCubeInPutty_Cancel_Olpn");
			}else{
				item_root.put("key", "Outbound_PckDirCubeInPutty");
				item_root.put("testcase_name", "Outbound_PckDirCubeInPutty");
			}
			item_root.put("input_params", item);
			
			JSONObject json = new JSONObject(item_root);

			str = json.toJSONString();
			System.out.println(json.toJSONString());
			jd.dbDFWMSMapping();
//			String port = "";
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
//				port = "6000";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				port = "4000";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//				port = "5000";
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
		
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//rc.logger.info(msg);
			report.addReportStep("Outbound Pack Cube Dir failed ", str, StepResult.PASS);
			//throw new Exception("Putty Operations are not performed for Outbound Pack Cube Dir");
		}
		
		

	}

}
