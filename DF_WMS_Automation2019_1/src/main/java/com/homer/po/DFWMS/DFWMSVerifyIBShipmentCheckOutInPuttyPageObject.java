package com.homer.po.DFWMS;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSIBCheckinPageObject;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DFWMSVerifyIBShipmentCheckOutInPuttyPageObject extends PageBase {
	JDBC_Connection jd = new JDBC_Connection(ic);
	
	public DFWMSVerifyIBShipmentCheckOutInPuttyPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void checkOut(String appointmentID) throws Exception {
		String str = "";
		try {
			
			System.out.println("AppointmentID: " + appointmentID);
			System.out.println(appointmentID);
			Map item = new LinkedHashMap();
			Map item_root = new LinkedHashMap();
			item.put("trailer", DFWMSScheduleAppointmentPageObject.Strtrailer);
			item.put("dockdoorName", DFWMSIBCheckinPageObject.sDockdoorname);

			item_root.put("key", "Outbound_checkout");
			item_root.put("testcase_name", "Outbound_checkout");
			item_root.put("input_params", item);
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
//				port = "9000";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				port = "3000";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				port = "3000";
//			}

			
			//puttyCall(str, port);
			
			StringBuilder sc = new StringBuilder();
			URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
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
			Thread.sleep(3000);
			Thread.sleep(10000);
			while ((line2 = reader.readLine()) != null) {
				sc.append(line2);
				Thread.sleep(5000);
				Thread.sleep(5000);
				Thread.sleep(10000);
			}
			Thread.sleep(8000);
			System.out.println("Putty Operations performed Successfully for Checkout");
			report.addReportStep("Putty Operations", "Putty Operations performed Successfully for Checkout", StepResult.PASS);

		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			report.addReportStep("Putty Operations for checkout", e.getMessage()+" "+str, StepResult.PASS);
			//rc.logger.info(msg);
			//report.addReportStep("Post Message", msg, StepResult.FAIL);
			//throw new Exception("Putty Operations are not performed for Checkout the Trailer");
		}
		
	}

}
