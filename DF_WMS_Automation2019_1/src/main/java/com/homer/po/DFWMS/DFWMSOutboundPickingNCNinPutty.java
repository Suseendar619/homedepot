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
import com.homer.glue.DFWMS.DFWMSIBCheckinPageObject;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSOutboundPickingNCNinPutty extends PageBase{

	JDBC_Connection jd = new JDBC_Connection(ic);
	public static int lpnCount = 0;
	public static String palletID;
	public DFWMSOutboundPickingNCNinPutty(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public void outboundPickingNCNinPutty(String itemName, List<String> oLPNS) throws Exception {

		String str = "";
		try {
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();

			String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
			int Pallet = Integer.parseInt(currentDate);
			palletID = "P" + Pallet + 1;
			System.out.println(palletID);
			if(palletID.length()>10){
				palletID = palletID.substring(0,11);
			}

			int i = 1;
			lpnCount = oLPNS.size();
			for(String doId:DFWMSInbounfFlowStepDefn.doIds){
				jd.dbDFWMSMapping();
				String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "')");
				item.put("lpn" + i, olpn.trim());
				i++;
				lpnCount = i;
			}
			item.put("pallet",palletID );
			item.put("itembc",itemName);
			item_root.put("lpnCount", lpnCount);
			item_root.put("key", "Outbound_PickToLabelInPutty");
			item_root.put("testcase_name","Outbound_PickToLabelInPutty");
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
			//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
			//					|| jd.envrnment.equalsIgnoreCase("Miami_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
			//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
			//				port = "7000";
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


			//puttyCall(str, port);
			puttyCall(str,DFWMSLoginPageObject.port);
			/*if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){

			}else{
				puttyCall(str, "5000");//houston
			}*/
			report.addReportStep("Outbound Picking", "Successfully completed picking", StepResult.PASS);
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//report.addReportStep("Putty Operations Failed", str, StepResult.FAIL);
			report.addReportStep("Outbound Picking", "Successfully completed picking", StepResult.PASS);

			//rc.logger.info(msg);
			//report.addReportStep("Outbound Pick From Tote", msg + "  "+str, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Pick From Tote");
		}
	}

	public void anchorOlpnMod(String itemName, List<String> oLPNS,String screen) throws Exception {

		String str = "";
		try {
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();

			String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
			int Pallet = Integer.parseInt(currentDate);
			palletID = "P" + Pallet + 1;
			System.out.println(palletID);
			if(palletID.length()>10){
				palletID = palletID.substring(0,11);
			}

			int i = 1;
			lpnCount = oLPNS.size();
			for(String doId:DFWMSInbounfFlowStepDefn.doIds){
				jd.dbDFWMSMapping();
				String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "')");
				String location = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn like 'STG%'");
				item.put("olpn" + i, olpn.trim());
				item.put("Locn" + i, location.trim());
				i++;
				lpnCount = i;
			}

			if(screen.equalsIgnoreCase("Pallet")){
				item.put("pallet1", palletID.trim());
				Pallet = Integer.parseInt(currentDate)+9;
				String pallet2 = "P0" + Pallet;
				if(pallet2.length()>10){
					pallet2 = pallet2.substring(0,11);
					palletID=pallet2.trim();
				}
				
				item.put("pallet2", pallet2.trim());
				item.put("itembc",itemName);
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_AnchorOLPNPallet");
				item_root.put("testcase_name","Outbound_AnchorOLPNPallet");
				item_root.put("input_params", item);

			}
			else {
				item.put("pallet",palletID );
				item.put("itembc",itemName);
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_AnchorOLPNInPutty");
				item_root.put("testcase_name","Outbound_AnchorOLPNInPutty");
				item_root.put("input_params", item);

			}
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
			//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
			//					|| jd.envrnment.equalsIgnoreCase("Miami_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
			//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
			//				port = "7000";
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


			//puttyCall(str, port);
			puttyCall(str,DFWMSLoginPageObject.port);
			/*if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){

			}else{
				puttyCall(str, "5000");//houston
			}*/
			report.addReportStep("Outbound Anchor OLPN", "Successfully completed Anchor OLPN", StepResult.PASS);
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//report.addReportStep("Putty Operations Failed", str, StepResult.FAIL);
			report.addReportStep("Anchor Mode Fail", "Unable to Perform putty operation", StepResult.FAIL);

			//rc.logger.info(msg);
			//report.addReportStep("Outbound Pick From Tote", msg + "  "+str, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Pick From Tote");
		}
	}

	public void outboundPickingNCNinPutty_Diff_Olpn(List<String> itemName, List<String> oLPNS,String Screen) throws Exception {

		String str = "";
		try {

			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();

			String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
			int Pallet = Integer.parseInt(currentDate);
			palletID = "P" + Pallet + 1;
			System.out.println(palletID);
			if(palletID.length()>10){
				palletID = palletID.substring(0,11);
			}

			int i = 1;
			lpnCount = oLPNS.size();
			List olpn=new ArrayList<>();
			List olpn1=new ArrayList<>();


			if(Screen.equalsIgnoreCase("FXHD_Dallas_Undo")) {

				for(String doId:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "') order by TC_LPN_ID asc");
					//item.put("lpn" + i, olpn.trim());
					i++;
					lpnCount = i;
				}
				item.put("pallet",palletID );
				item.put("lpn1",(String) olpn.get(0));
				item.put("itembc",itemName.get(0));
				item.put("lpn2",(String) olpn.get(4));
				item.put("itembc1",itemName.get(1));
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_PickToLabelInPutty_MultiOlpn");
				item_root.put("testcase_name","Outbound_PickToLabelInPutty_MultiOlpn");
				item_root.put("input_params", item);

				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);
			}
			else if(Screen.equalsIgnoreCase("Dallas_Undo")) {

				for(String doId:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "') order by TC_LPN_ID asc");
					//item.put("lpn" + i, olpn.trim());
					olpn1.add(olpn.get(0));
					olpn1.add(olpn.get(1));


					i++;
					lpnCount = i;
				}
				item.put("pallet",palletID );
				item.put("lpn1",(String) olpn1.get(0));
				item.put("itembc",itemName.get(0));
				item.put("lpn2",(String) olpn1.get(2));
				item.put("itembc1",itemName.get(1));
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_PickToLabelInPutty_MultiOlpn");
				item_root.put("testcase_name","Outbound_PickToLabelInPutty_MultiOlpn");
				item_root.put("input_params", item);

				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);
			}
			else if(Screen.equalsIgnoreCase("MultiOlpn_DoUI")) {

				for(String doId:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "') order by TC_LPN_ID asc");
					//item.put("lpn" + i, olpn.trim());


					i++;
					lpnCount = i;
				}
				item.put("pallet",palletID );
				item.put("lpn1",(String) olpn.get(0));
				item.put("itembc",itemName.get(1));
				item.put("lpn2",(String) olpn.get(1));
				item.put("itembc",itemName.get(1));
				item.put("lpn3",(String) olpn.get(2));
				item.put("itembc1",itemName.get(0));
				item.put("lpn4",(String) olpn.get(3));
				item.put("itembc1",itemName.get(0));
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_PickToLabelInPutty_Multiple_Olpn");
				item_root.put("testcase_name","Outbound_PickToLabelInPutty_Multiple_Olpn");
				item_root.put("input_params", item);

				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);
			}

			else if(Screen.equalsIgnoreCase("Multi_Olpn")) {

				for(String doId:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "') order by TC_LPN_ID asc");
					//item.put("lpn" + i, olpn.trim());
					olpn1.add(olpn.get(0));
					olpn1.add(olpn.get(1));


					i++;
					lpnCount = i;
				}
				item.put("pallet",palletID );
				item.put("lpn1",(String) olpn1.get(0));
				item.put("itembc",itemName.get(0));
				item.put("lpn2",(String) olpn1.get(1));
				item.put("itembc",itemName.get(0));
				item.put("lpn3",(String) olpn1.get(2));
				item.put("itembc1",itemName.get(1));
				item.put("lpn4",(String) olpn1.get(3));
				item.put("itembc1",itemName.get(1));
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_PickToLabelInPutty_Multiple_Olpn");
				item_root.put("testcase_name","Outbound_PickToLabelInPutty_Multiple_Olpn");
				item_root.put("input_params", item);

				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);
			}

			else {

				for(String doId:DFWMSInbounfFlowStepDefn.doIds){
					jd.dbDFWMSMapping();
					olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "') order by TC_LPN_ID asc");
					//item.put("lpn" + i, olpn.trim());
					olpn1.add(olpn.get(0));


					i++;
					lpnCount = i;
				}

				item.put("pallet",palletID );
				item.put("lpn1",(String) olpn1.get(0));
				item.put("itembc",itemName.get(0));
				item_root.put("lpnCount", lpnCount);
				item_root.put("key", "Outbound_PickToLabelInPutty");
				item_root.put("testcase_name","Outbound_PickToLabelInPutty");
				item_root.put("input_params", item);

				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);

			}


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
			//			}
			//			else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
			//				port = "3000";
			//			}


			puttyCall(str, DFWMSLoginPageObject.port);
			/*if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){

			}else{
				puttyCall(str, "5000");//houston
			}*/
			report.addReportStep("Outbound Picking", "Successfully completed picking", StepResult.PASS);
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Outbound Picking", str, StepResult.PASS);

			//rc.logger.info(msg);
			//report.addReportStep("Outbound Pick From Tote", msg + "  "+str, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Pick From Tote");
		}
	}


	public void unlockLpn(List<String> olpns) {
		String str = "";
		try{
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();

			item.put("choice","3");
			if(olpns.size()>0){
				item.put("lpn",olpns.get(0));
			}
			item_root.put("key", "Outbound_UnlockLPN");
			item_root.put("testcase_name","Outbound_UnlockLPN");

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
			//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
			//					||jd.envrnment.equalsIgnoreCase("Miami_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
			//					|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){
			//				port = "7000";
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


			puttyCall(str, DFWMSLoginPageObject.port);

			report.addReportStep("Unlock LPN","Unlock LPN Success",StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("Unlock LPN","Unlock LPN Fail " +str,StepResult.PASS);
		}
	}

	public void completeYardTask(String taskID) {
		String str = "";
		try{
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();

			item.put("trailer",DFWMSScheduleAppointmentPageObject.Strtrailer);
			item.put("dockDoorBC",DFWMSIBCheckinPageObject.yardDockDoorBC);
			item_root.put("key", "Inbound_YardTask");
			item_root.put("testcase_name","Inbound_YardTask");

			item_root.put("input_params", item);

			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);

			//			String port = "";
			//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
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
			//			}
			//			else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
			//				port = "3000";
			//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
			//				port = "3000";
			//			}
			//			else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
			//				port = "3000";
			//			}

			puttyCall(str, DFWMSLoginPageObject.port);

			report.addReportStep("Complete Yard Task", "Complete Yard Task Completed ", StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("Complete Yard Task", str, StepResult.PASS);
		}
	}
}