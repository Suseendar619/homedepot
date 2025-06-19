package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSCycleCountPageObject extends PageBase{

	public String ActiveEquals = "1000035126";
	public String ReserveEquals = "1000707527";
	public String ActiveLess = "1002829917";
	public String ReserveLess = "1000535573";
	public String ActiveMore = "1000206348";
	public String ReserveMore = "1000535573";
	public String choice = "";
	public String locBCVal = "";
	String currentDate = new SimpleDateFormat("ddmm").format(Calendar.getInstance().getTime());//TMMDDYYHHmm
	int lpn = Integer.parseInt(currentDate);
	public DFWMSCycleCountPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void ccActiveEquals() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","5");
			String locBC = getItemDispLoc(ActiveEquals);
			locBCVal = getLocationBCVal(locBC);
			item.put("locBC",locBCVal);
			item.put("itembc",ActiveEquals);
			item.put("qty","11");
			item_root.put("key", "CC_ActiveEqual");
			item_root.put("testcase_name","CC_ActiveEqual");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count Equals Active",
					"Cycle Count Equals Active Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count Equals Active", 
					"Cycle Count Equals Active fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}

	public void ccReserveEquals() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","6");
			lpn = lpn+1;
	  		String ilpn= "C000000"+lpn;
			item.put("lpn",ilpn);
			item.put("itembc",ReserveEquals);
			String locBC = getItemDispLoc(ReserveEquals);
			locBCVal = getLocationBCVal(locBC);
			item.put("locBC",locBCVal);
			item.put("qty","17");
			item_root.put("key", "CC_Reserve");
			item_root.put("testcase_name","CC_Reserve");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count Equals Reserve",
					"Cycle Count Equals Reserve Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count Equals Reserve", 
					"Cycle Count Equals Reserve fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}

	public void ccActiveLess() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","5");
			String locBC = getItemDispLoc(ActiveLess);
			locBCVal = getLocationBCVal(locBC);
			item.put("locBC",locBCVal);
			item.put("itembc",ActiveLess);
			item.put("qty","10");
			item_root.put("key", "CC_ActiveLess");
			item_root.put("testcase_name","CC_ActiveLess");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count Less Active",
					"Cycle Count Less Active Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count Less Active", 
					"Cycle Count Less Active fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}

	public void ccReserveLess() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","6");
			lpn = lpn+1;
	  		String ilpn= "C000000"+lpn;
			item.put("lpn",ilpn);
			String locBC = getItemDispLoc(ReserveLess);
			locBCVal = getLocationBCVal(locBC);
			item.put("locBC",locBCVal);
			item.put("itembc",ReserveLess);
			item.put("qty","10");
			item_root.put("key", "CC_ReserveLess");
			item_root.put("testcase_name","CC_ReserveLess");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count Less Reserve",
					"Cycle Count Less Reserve Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count Less Reserve", 
					"Cycle Count Less Reserve fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}

	public void ccActiveMore() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","5");
			String locBC = getItemDispLoc(ActiveMore);
			locBCVal = getLocationBCVal(locBC);
			item.put("locBC",locBCVal);
			item.put("itembc",ActiveMore);
			item_root.put("key", "CC_ActiveMore");
			item_root.put("testcase_name","CC_ActiveMore");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count More Active",
					"Cycle Count More Active Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count More Active", 
					"Cycle Count More Active fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}

	public void ccReserveMore() {
		try{
			String str = "";
			Map<String, String> item = new LinkedHashMap<String, String>();
			Map item_root = new LinkedHashMap();
			item.put("choice","6");
			String locBC = getItemDispLoc(ActiveEquals);
			locBCVal = getLocationBCVal(locBC);
			item_root.put("key", "");
			item_root.put("testcase_name","");
			item_root.put("input_params", item);
			JSONObject json = new JSONObject(item_root);
			str = json.toJSONString();
			System.out.println(str);
			puttyCall(str, "3000");
			
			report.addReportStep(
					"Cycle Count More Reserve",
					"Cycle Count More Reserve Success.",
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Cycle Count More Reserve", 
					"Cycle Count More Reserve fail "+e.getMessage(), 
					StepResult.FAIL);
		}
	}
	
	private String getLocationBCVal(String loc) throws Exception {
		
		String locBCValDB = "";
		try{
			JDBC_Connection jd = new JDBC_Connection(ic);
			jd.dbDFWMSMapping();
			locBCValDB = jd.str_Database_Connection("select locn_brcd from locn_hdr where dsp_locn='"+loc+"'");
			System.out.println("Location Barcode from DB :"+locBCValDB);
		}catch(Exception e){
			throw new Exception("Unable to retriev loc BC val from DB "+e.getMessage());
		}
		
		return locBCValDB;
	}
	
	private String getItemDispLoc(String item) throws Exception {
		String locBC = "";
		try{
			JDBC_Connection jd = new JDBC_Connection(ic);
			jd.dbDFWMSMapping();
			locBC = jd.str_Database_Connection("SELECT LH.DSP_LOCN FROM ITEM_CBO IC, "
					+ "WM_INVENTORY WI, LOCN_HDR LH WHERE IC.ITEM_ID = WI.ITEM_ID AND "
					+ "WI.LOCATION_ID = LH.LOCN_ID AND IC.ITEM_NAME = '"+item+"' "
					+ "AND WI.LOCN_CLASS = 'A' ORDER BY LH.DSP_LOCN");
			System.out.println("Location Barcode from DB :"+locBC);
		}catch(Exception e){
			throw new Exception("Unable to retriev loc BC val from DB "+e.getMessage());
		}
		
		return locBC;
	}

}
