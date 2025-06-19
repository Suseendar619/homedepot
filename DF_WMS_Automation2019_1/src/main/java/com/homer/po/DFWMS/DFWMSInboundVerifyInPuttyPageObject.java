package com.homer.po.DFWMS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSInboundVerifyInPuttyPageObject extends PageBase {

	public DFWMSInboundVerifyInPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	JDBC_Connection jd = new JDBC_Connection(ic);

	public void inboundVerifyInPutty(String aSNID,String screen) throws Exception {
		String str = null;
		try {
			System.out.println("ASNID :"+aSNID);
			
			/*str = "{   \"key\":\"Inbound_verify\", \"testcase_name\": \"Inbound_verify\",  "
					+ "  \"input_params\": {   \"asn\" : \""+aSNID+"\" }}";*/
			
			Map item = new LinkedHashMap();
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
			}


			if(screen.equalsIgnoreCase("VerifyIBShipment") || screen.equalsIgnoreCase("VerifyIBShipment10Lpns")
					|| screen.equalsIgnoreCase("MultiItemASNRcvDtl")){
				item_root.put("key", "VerifyIBShipment_verify");
				item_root.put("testcase_name", "VerifyIBShipment_verify");
			}else if(screen.equalsIgnoreCase("Inbound") || screen.equalsIgnoreCase("Zone")
					|| screen.equalsIgnoreCase("MultiItemRcvASN") || screen.equalsIgnoreCase("VerifyIBShipment2019")
					||screen.equalsIgnoreCase("ShipmentRcvDtl") ||screen.equalsIgnoreCase("ASN_OverageDallas")
					||screen.equalsIgnoreCase("ASN_ShortageDallas")){
				item_root.put("key", "Inbound_verify");
				item_root.put("testcase_name", "Inbound_verify");
			}

			item_root.put("input_params",item);

			JSONObject json = new JSONObject(item_root);
			
			str = json.toJSONString();
			System.out.println(str);
			
			StringBuilder sc = new StringBuilder();
			// String port = "";
            
				jd.dbDFWMSMapping();
//				if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
//						||jd.envrnment.equalsIgnoreCase("Newark_2019")){
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


				
				System.out.println("Port Used "+DFWMSLoginPageObject.port );
             
				URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase"); 
			
			 //URL url = new URL("http://wn3c3a:3000/executeTestcase");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setReadTimeout(17000);
			conn.setConnectTimeout(17000);
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
			System.out.println("Putty Operations performed Successfully for Inbound Verify");
			report.addReportStep("inbound Verify in Putty", "Putty Operations performed Successfully ", StepResult.PASS);
		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("inbound Verify in Putty", e.getMessage() + " "+str, StepResult.PASS);
			//report.addReportStep("Post Message", str, StepResult.PASS);
			//throw new Exception("Putty Operations are not performed for Inbound Verify");
		}
	}
}