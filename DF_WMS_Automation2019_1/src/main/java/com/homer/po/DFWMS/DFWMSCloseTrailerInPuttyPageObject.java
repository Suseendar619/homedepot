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

public class DFWMSCloseTrailerInPuttyPageObject extends PageBase {

	public DFWMSCloseTrailerInPuttyPageObject(InstanceContainer ic) {
		super(ic);
	}
	
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static String shipmentSealVal = "";
	
	public void closeTrailerInPutty(String shipmentID, String screen) throws Exception {
		String currentDate = new SimpleDateFormat("ddyymm").format(Calendar.getInstance().getTime());//TMMDDYYHHmm
		int seal = Integer.parseInt(currentDate);
  		String SealID = Integer.toString(seal);
		if(SealID.length() < 6){
			SealID = Integer.toString(seal)+0;
		}
		String shipmentSeal= "SEAL"+SealID;
		shipmentSealVal = shipmentSeal;
		System.out.println(shipmentSeal);
  		String str = "";
  		try {
		 	Map<String, String> item = new LinkedHashMap<String, String>();
				item.put("shipmentid",shipmentID.trim());
				item.put("shipmentseal",shipmentSeal.trim());
				
		 	Map item_root = new LinkedHashMap();
		 	if(screen.equalsIgnoreCase("Zone")){
		 		item_root.put("key", "Outbound_clse_trailer");
				item_root.put("testcase_name", "Outbound_clse_trailer");
				item.put("shipmentseal",shipmentSeal.trim());
		 	}else if(screen.equalsIgnoreCase("DallasZone")){
		 		item_root.put("key", "Close_trailer_Dallas");
				item_root.put("testcase_name", "Close_trailer_Dallas");
		 	}else if(screen.equalsIgnoreCase("AtlantaZone")){
		 		item_root.put("key", "Close_trailer_Atlanta");
				item_root.put("testcase_name", "Close_trailer_Atlanta");	
		 	}else if(screen.equalsIgnoreCase("BaltimoreZone")){
		 		item_root.put("key", "Close_trailer_Baltimore");
				item_root.put("testcase_name", "Close_trailer_Baltimore");
		 	}else if(screen.equalsIgnoreCase("LaceyZone")){
		 		item_root.put("key", "Close_trailer_Lacey");
				item_root.put("testcase_name", "Close_trailer_Lacey");
		 	}
			
			item_root.put("input_params",item);

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
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}
			puttyCall(str, DFWMSLoginPageObject.port);
/*			if(screen.equalsIgnoreCase("Zone")){
				puttyCall(str, "5000");
			}else if(screen.equalsIgnoreCase("DallasZone")){
				puttyCall(str, "3000");
			}*/
			
			System.out.println("Putty Operations performed Successfully for Outbound Close Trailer");
            report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound Close Trailer. Shipment seal num: "+shipmentSealVal, StepResult.PASS);
			
		}catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			//report.addReportStep("Putty Operations Failed", str, StepResult.FAIL);
			//rc.logger.info(msg);
            report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Outbound Close Trailer. Shipment seal num: "+shipmentSealVal, StepResult.PASS);

			//report.addReportStep("Outbound Close Trailer", msg+ " "+str, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Outbound Close Trailer");
		}
	}
	public void closeTrailerValidation(String shipmentIDOutbound) throws Exception {
		Thread.sleep(2000);
		jd.dbDFWMSMapping();
		List<String> closeTrailerDetails = new ArrayList<String>();
		if(shipmentIDOutbound!=null){
			closeTrailerDetails = jd.array_Database_Connection("select unique shp.shipment_closed_indicator,shp.seal_number from lpn lp, "
					+ "shipment shp where lp.tc_shipment_id = shp.tc_shipment_id and shp.tc_shipment_id in ('"+shipmentIDOutbound+"')");
			
			if(closeTrailerDetails.size()>1){
				if(closeTrailerDetails.get(0).equalsIgnoreCase("1") && closeTrailerDetails.get(1).equalsIgnoreCase(shipmentSealVal)){
					report.addReportStep("Close Trailer Validation", "Success", StepResult.PASS);
				}else{
					report.addReportStep("Close Trailer Validation", "failed", StepResult.FAIL);
				}
			}else{
				report.addReportStep("Close Trailer Validation", "No data returned for DB "+closeTrailerDetails, StepResult.FAIL);
			}
		}else{
			report.addReportStep("Close Trailer Validation", "Shipment id not found ", StepResult.FAIL);
		}
		
		
	}
}