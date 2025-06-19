package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSToolsPageObject extends PageBase{

	JDBC_Connection jd = new JDBC_Connection(ic);
	public StreamResult streamResult;
	String path = System.getProperty("user.dir")+"\\TestData\\Excel\\SKU_Data.xlsx";
	public DFWMSToolsPageObject(InstanceContainer ic) {
		super(ic);
	}

	public Map<String, String> readSkuDataExcel(String DC) throws IOException {
		  FileInputStream fis = new FileInputStream(path);
		  Workbook workbook = new XSSFWorkbook(fis);
		  Sheet sheet = workbook.getSheetAt(0);
		  int lastRow = sheet.getLastRowNum();
		  Map<String, String> dataMap = new HashMap<String, String>();
		  //Looping over entire row
		  for(int i=0; i<=lastRow; i++){
			  String value = "";
			  String value1 = "";
			  String key = "";
			  NumberFormat formatter = new DecimalFormat("#");
			  Row row = sheet.getRow(i);
			  Cell keyCell = row.getCell(0); //desc
			  Cell valueCell = row.getCell(1);//item
			  if(keyCell.getCellType() == Cell.CELL_TYPE_STRING){
				  key = keyCell.getStringCellValue().trim();
			  }
			  
			  if(valueCell.getCellType() == Cell.CELL_TYPE_STRING){
				  value = valueCell.getStringCellValue().trim();
			  }else if(valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
				  value1 = formatter.format(valueCell.getNumericCellValue());
			  }
			  
			  if(key.contains(DC)){
				  dataMap.put(key, value);
				  if(dataMap.containsKey(key)){
					  if(value.isEmpty()){
						  dataMap.put(key, value1);
					  }else{
						  dataMap.put(key, value);
					  }
				  }
			  }
		  }
		  System.out.println("Final Map: "+dataMap);
		  return dataMap;
	  }

	public void validateAndUpdateItemZone(Map<String, String> itemMap) throws Exception {
		Map<String, String> itemZoneNotMatchedMap = new HashMap<String, String>();
		if(!itemMap.isEmpty()){
			System.out.println("Map Size: "+itemMap.size());
			for (Entry<String, String> map : itemMap.entrySet()) {
				String dbZone = null;
				String key = map.getKey();
				String value = map.getValue();
				//validate item
				jd.dbDFWMSMapping();
				if(key.contains("Active") || key.contains("Inbound")){
					dbZone = jd.str_Database_Connection("SELECT DISTINCT LH.ZONE FROM "
							+ " PICK_LOCN_HDR PLH,PICK_LOCN_DTL PLD,ITEM_CBO IC, ITEM_WMS IW,"
							+ " LOCN_HDR LH, ITEM_FACILITY_MAPPING_WMS IFW, WM_INVENTORY WI WHERE"
							+ "	PLH.LOCN_ID = PLD.LOCN_ID AND PLD.LOCN_ID = LH.LOCN_ID AND "
							+ "	PLD.ITEM_ID = IW.ITEM_ID AND IW.ITEM_ID = IC.ITEM_ID AND "
							+ "	LH.LOCN_ID  = WI.LOCATION_ID AND "
							+ "	IC.ITEM_NAME = '"+value+"'");
					
				}else if(key.contains("Reserve")){
					dbZone = jd.str_Database_Connection("select  unique lh.zone from "
							+ "resv_locn_hdr RLH,Item_CBO IC, Locn_hdr LH,"
							+ "item_facility_mapping_wms ifw, item_wms iw where "
							+ "LH.LOCN_ID = RLH.LOCN_ID AND RLH.DEDCTN_ITEM_ID = IC.ITEM_ID AND "
							+ "IC.ITEM_ID = IFW.ITEM_ID AND "
							+ "IC.ITEM_ID = IW.ITEM_ID AND IC.ITEM_NAME = '"+value+"'");
				}
				if(dbZone != null){
					dbZone = dbZone.substring(0,3);
					if(!dbZone.matches(".*\\d.*")){
						dbZone = dbZone.substring(0,2) + "1";
					}
					key = key.substring(0,3);
					if(!key.matches(".*\\d.*")){
						key = key.substring(0,2) + "1";
					}
					if(!key.contains(dbZone)){
						itemZoneNotMatchedMap.put(map.getKey(), value);
					}
				}else if(key.contains("Active") || key.contains("Inbound") || key.contains("Reserve")){
					itemZoneNotMatchedMap.put(map.getKey(), value);
				}
			}
			System.out.println("ItemMap: "+itemZoneNotMatchedMap);
			for (Entry<String, String> map : itemZoneNotMatchedMap.entrySet()) {
				String key = map.getKey();
				String zone = key.substring(0, 3);
				String zone1 = "";
				if(!zone.matches(".*\\d.*")){
					zone1 = zone.substring(0,2) + "2";
					zone = zone.substring(0,2) + "1";
				}
				String dbItem = null;
				if((key.contains("Active") || key.contains("Inbound")) && !zone.isEmpty()){
					dbItem = jd.str_Database_Connection("SELECT DISTINCT IC.ITEM_NAME FROM "
							+ " PICK_LOCN_HDR PLH,PICK_LOCN_DTL PLD,ITEM_CBO IC, ITEM_WMS IW,"
							+ " LOCN_HDR LH, ITEM_FACILITY_MAPPING_WMS IFW, WM_INVENTORY WI WHERE"
							+ "	PLH.LOCN_ID = PLD.LOCN_ID AND PLD.LOCN_ID = LH.LOCN_ID AND "
							+ "	PLD.ITEM_ID = IW.ITEM_ID AND IW.ITEM_ID = IC.ITEM_ID AND "
							+ "	LH.LOCN_ID  = WI.LOCATION_ID AND "
							+ "	LH.ZONE = '"+zone+"'");
				}else if((key.contains("Reserve")) && !zone.isEmpty()){
					dbItem =  jd.str_Database_Connection("select  unique IC.ITEM_NAME from "
							+ "resv_locn_hdr RLH,Item_CBO IC, Locn_hdr LH,"
							+ "item_facility_mapping_wms ifw, item_wms iw where "
							+ "LH.LOCN_ID = RLH.LOCN_ID AND RLH.DEDCTN_ITEM_ID = IC.ITEM_ID AND "
							+ "IC.ITEM_ID = IFW.ITEM_ID AND "
							+ "IC.ITEM_ID = IW.ITEM_ID AND lh.zone = '"+zone+"'");
					if(dbItem == null){
						dbItem =  jd.str_Database_Connection("select  unique IC.ITEM_NAME from "
								+ "resv_locn_hdr RLH,Item_CBO IC, Locn_hdr LH,"
								+ "item_facility_mapping_wms ifw, item_wms iw where "
								+ "LH.LOCN_ID = RLH.LOCN_ID AND RLH.DEDCTN_ITEM_ID = IC.ITEM_ID AND "
								+ "IC.ITEM_ID = IFW.ITEM_ID AND "
								+ "IC.ITEM_ID = IW.ITEM_ID AND lh.zone = '"+zone1+"'");
					}
				}
				
				if(dbItem != null){
					//update item in excel
					File myFile = new File(path);
					String item = "";
					FileInputStream fis = new FileInputStream(myFile);
					XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
					XSSFSheet mySheet = myWorkBook.getSheetAt(0);
					Iterator<Row> rowIterator = mySheet.iterator();
					while (rowIterator.hasNext()) {
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();
							boolean rowValue = false;
							Iterator<Cell> cellIterator = row.cellIterator();
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								switch (cell.getCellType()) {
									case Cell.CELL_TYPE_STRING:
										String cellValue = cell.getStringCellValue();
					   	            	if(cellValue.equalsIgnoreCase(key)){
					   	            		rowValue = true;
					   	            	}
					   	            	if(rowValue && cell.getColumnIndex() == 1){
					   	            		item = cell.getStringCellValue();
					   	            		item = item.replaceAll("[^a-zA-Z0-9]", " ");  
				                        	System.out.print("Item :"+item);
				                        	cell.setCellValue(dbItem);
				                        	report.addReportStep("Update Item", "Updating item for: "+key +". Updated value: "+item +"Previous value:" +map.getValue(), StepResult.PASS);
				                        	FileOutputStream os = new FileOutputStream(path); 
				        					myWorkBook.write(os); 
				        					System.out.println("Writing on XLSX file Finished ...");
				        					os.close();
				        					fis.close();
				        					myWorkBook = new XSSFWorkbook(new FileInputStream(path));
				        					break;
					   	            	}
					   	            	
									case Cell.CELL_TYPE_NUMERIC:
										NumberFormat formatter = new DecimalFormat("#");
				                        if(rowValue && cell.getColumnIndex() == 1){
				                        	item = formatter.format(cell.getNumericCellValue());
				                        	item = item.replaceAll("[^a-zA-Z0-9]", " ");  
				                        	System.out.print("Item :"+item);
				                        	cell.setCellValue(dbItem);
				                        	FileOutputStream os = new FileOutputStream(path); 
				        					myWorkBook.write(os); 
				        					System.out.println("Writing on XLSX file Finished ...");
				        					report.addReportStep("Update Item", "Updating item for: "+key +". Updated value: "+item, StepResult.PASS);
				        					os.close();
				        					fis.close();
				        					myWorkBook = new XSSFWorkbook(new FileInputStream(path));
				        					break;
				                        }
								}
							}
						}
					}
				}else{
					System.out.println("No SKU is present for :"+key);
					report.addReportStep("Validate and Update SKU", "No SKU present for: "+key, StepResult.PASS);
				}
			}
		}
	}

	public void getDockDoorDetails() throws Exception {
		wh.clickElement(Maximize);
		wh.clickElement(Refresh);
		driver.switchTo().frame(0);
		List<String> dockDoors = new ArrayList<String>();
		jd.dbDFWMSMapping();
		dockDoors = jd.array_Database_Connection("select dock_door_name from dock_door where dock_door_status = '212' and barcode is not null order by dock_door_name");
		System.out.println(dockDoors);
		if(dockDoors!=null){
			if(wh.isElementPresent(LastPage, 5)){
				String classProp = wh.getAttribute(LastPage, "class");
				if(!classProp.contains("Disabled")){
					wh.clickElement(LastPage);
				}
			}
			for (String dockDoor : dockDoors) {
				By DockDoorCheckout = By.xpath(".//span[contains(text(),'"+dockDoor+"')]");
				if(wh.isElementPresent(DockDoorCheckout, 5)){
					wh.clickElement(DockDoorCheckout);
					wh.clickElement(Checkout);
					Thread.sleep(500);
					wh.clickElement(Checkout);
					System.out.println("Checked-out dock door "+dockDoor);
				}
			}
		}else{
			report.addReportStep("CheckOut Dock Doors", "No dock doors returned from DB", StepResult.FAIL);
		}
	}

	public void getLaneDetails() throws Exception {
		wh.clickElement(Maximize);
		
		By InputDescDropDwn = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[1]");
		By InputRouteToTxtBox = By.xpath("(.//input [contains(@id,'textfield') and contains (@class,'x-form-text-default') and contains(@type,'text')])[2]");
		By BtnApply = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[12]");
		By LaneCheckBox = By.xpath("(//div[@role='presentation' and @class='x-grid-row-checker'])[1]");
		By LaneView = By.xpath("//span[contains(@id,'btnInnerEl') and contains(text(),'View') and contains(@class,' x-btn-inner')]");
		By RouteType1Value = By.xpath("(//span[text()='Route Type 1:']//following::div[@class= 'x-form-display-field x-form-display-field-default'])[21]");
		
		if (wh.isElementPresent(InputDescDropDwn, 5)) {
			wh.sendKeys(DropDwn,"Route To");
			Thread.sleep(1000);
			driver.findElement(InputDescDropDwn).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		}
		
		if (wh.isElementPresent(InputRouteToTxtBox, 5)) {
			wh.sendKeys(InputRouteToTxtBox,"2");
			Thread.sleep(2000);
			if (wh.isElementPresent(BtnApply, 5)) {
				wh.clickElement(BtnApply);
				Thread.sleep(2000);
			}
		}
		if (wh.isElementPresent(LaneCheckBox, 5)) {
			wh.clickElement(LaneCheckBox);
			Thread.sleep(2000);
			if (wh.isElementPresent(LaneView, 5)) {
				wh.clickElement(LaneView);
				Thread.sleep(3000);
			}
		}
		if (wh.isElementPresent(RouteType1Value, 5)) {
			String routeToValue = wh.getText(RouteType1Value);
			System.out.println("Route Value Returned " + routeToValue);
			Thread.sleep(2000);
		}
		
	}

	public void getLaneDetailsFromDB(String flow) throws Exception {
		String routeToValue = "";
		String filePath = "";
		String tagName = "";
		String xmlData = "";
		List<String> lanesfromDB = new ArrayList<>(); 
		Map<String, List<String>> laneValueMap = new HashMap<>(); 
		jd.dbDFWMSMapping();
		String key = "";
		
		if(flow.contains("LTL")){
			key = "2";
			filePath =  FilePathOutboundDallas;
		}else if(flow.contains("BVR")){
			key = "18";
			filePath =  FilePathOutboundBVRDallas;
		}else if(flow.contains("LTLHDU")){
			key = "3";
			filePath =  FilePathLTLHDUOutboundDallas;
		}else if(flow.contains("HDUTL")){
			key = "3";
			filePath =  FilePathHDUTLDallas;
		}
			
		try {
	
			
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(filePath);
	        doc.getDocumentElement().normalize();
	        Node routeType1 = doc.getElementsByTagName("RouteType1").item(0);
	        xmlData = routeType1.getTextContent();
	        System.out.println("Lane Value present in XML is " + xmlData );
	       
	        lanesfromDB = jd.array_Database_Connection(
					" select distinct  LV.ROUTE_TYPE_1 as ship_via from LANE_VIEW LV, LANE_DETAIL_VIEW LDV,CARRIER_CODE CC,SERVICE_LEVEL SL "
					+ "where LV.Lane_id = LDV.Lane_ID and "
					+ "LDV.Carrier_ID = CC.Carrier_ID and "
					+ "LDV.SERVICE_LEVEL_ID = SL.Service_Level_ID and "
					+ "LV.Route_to in ('"+key+"')");
			
			if(!lanesfromDB.isEmpty()){
				laneValueMap.put(key,lanesfromDB);
				
				for (Entry<String, List<String>> entry : laneValueMap.entrySet()) {
				    System.out.println(entry.getKey() + ":" + entry.getValue());
				    
				    if(entry.getValue().contains(xmlData)){
				    	System.out.println("Lane present in XML is Valid");
				    	report.addReportStep("Lane Validation", "Lane present in XML is Valid :" + xmlData, StepResult.PASS);
				    }else
				   // routeToValue = lanesfromDB.get(0);
				    	routeToValue = entry.getValue().get(0);
				    	System.out.println("Updated the Valid Lane in XML " + routeToValue);
				    	report.addReportStep("Lane Validation", "Lane present in XML is Not Valid.Updated the XML Lane with :" + routeToValue, StepResult.PASS);
				}
				
			}else {
				System.out.println("Lanes are not configured in DC");
				report.addReportStep("Lane Validation", "Lane Setup is not configured.No Lanes found in DC for :" + flow, StepResult.FAIL);
			}
		
			routeType1.setTextContent(routeToValue);
			
		}catch (IOException ioe){
			ioe.printStackTrace();
		} 
		
	}

	
	public void adjustItemInventory(Map<String, String> itemMap) throws Exception {
		Map<String, String> itemInventoryMap = new HashMap<String, String>();
		if(!itemMap.isEmpty()){
			System.out.println("Map Size: "+itemMap.size());
			for (Entry<String, String> map : itemMap.entrySet()) {
				List<String> dbZone = new ArrayList<String>();
				String key = map.getKey();
				String value = map.getValue();
				//validate item
				jd.dbDFWMSMapping();
				if(!key.contains("Active") || !key.contains("Inbound") || !key.contains("Reserve")){
					dbZone = jd.array_Database_Connection("select wi.on_hand_qty, wi.wm_allocated_qty "
							+ "from pick_locn_hdr plh, pick_locn_dtl pld, item_cbo ic, locn_hdr lh, "
							+ "item_facility_mapping_wms ifw, item_wms iw,wm_inventory wi where "
							+ "lh.locn_id = plh.locn_id and plh.locn_id = pld.locn_id and "
							+ "pld.item_id = ic.ITEM_ID and ic.item_id = ifw.item_id and "
							+ "ic.item_id = iw.item_id and lh.locn_id=wi.location_id and "
							+ "ic.item_name = '"+value+"'");
				}
				if(dbZone != null){
					int onHandQty = Integer.parseInt(dbZone.get(0));
					int allocatedQty = Integer.parseInt(dbZone.get(1));
					int qty = onHandQty - allocatedQty;
					if(qty < 0){
						itemInventoryMap.put(key, value);
					}else{
						System.out.println("Inventory is suuficient");
					}
					report.addReportStep("Adjust Item Inventory", "Inventory calculated. Need to modify inventory for :"+itemInventoryMap, StepResult.PASS);
				}else{
					report.addReportStep("Adjust Item Inventory", "No Data found in DB", StepResult.PASS);	
				}
			}
		}
	}

	
	

	public void getParcelDockDoorDetails(String flow) {
		List<String> hubCode = new ArrayList<String>();
		Map<String, String> hubCodeUpsMap = new HashMap<String, String>();
		Map<String, String> hubCodeFDXMap = new HashMap<String, String>();
		Map<String,String> dockDoorMap = new HashMap<String,String>();
		
		String sDockdoorname;
		String sDockdoorhubcode;
		try {
			jd.dbDFWMSMapping();
			
			hubCode = jd.array_Database_Connection("select short_desc, misc_flags from sys_code where code_type = '6A3'");
			//System.out.println(HubCode);
			if(hubCode.size()>0){
				for(int i=0;i<hubCode.size();i++){
					sDockdoorname = hubCode.get(i).toString();
					i++;
					sDockdoorhubcode = hubCode.get(i).toString();
					dockDoorMap.put(sDockdoorname, sDockdoorhubcode);
				}
				System.out.println(dockDoorMap);
			}
			 for (Entry<String, String> map: dockDoorMap.entrySet()){
			      //  System.out.println(map.getKey()+ " = "+ map.getValue());
			        String key = map.getKey();
					String value = map.getValue();
					String hub_code = value.substring(6,10);
			        if(value.contains("UPS")){
				    	//hubCodemap.put(map.getKey(), map.getValue());
			        	//System.out.println(value.substring(6,10));
				    	hubCodeUpsMap.put(key,hub_code);
				    }else {
				    	//hubCodeFDXMap.put(key,value);
				    	hubCodeFDXMap.put(key,hub_code);
				    	
				    }
			    }
			 
			 System.out.println("Fedex Dockdoor details are: " + hubCodeFDXMap);	
		     System.out.println("UPS Dockdoor details are: " + hubCodeUpsMap);
		     report.addReportStep("UPS Parcel DockDoor Details", "HubCode Data found in DB" + hubCodeUpsMap , StepResult.PASS);
		     report.addReportStep("FDX Parcel DockDoor Details", "HubCode Data found in DB" + hubCodeFDXMap , StepResult.PASS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> readExcel() {
		String excelFilePath = "";
		Map<String, String> dataMap = new HashMap<String, String>();
		try{
			//excelFilePath = MiamiBRTFileTest;
			//excelFilePath = MiamiBRTFile;
			//excelFilePath = Sample;
			excelFilePath = AtlantaBRT;
			File file = new File(excelFilePath);    
			FileInputStream fis = new FileInputStream(file); 
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();  
			
	        int i = 1;
	        String orderId, waveDesc, lpnType, item1, item2, item3, item4, flow, endDate, endTime;
	        orderId = waveDesc = lpnType = item1 = item2 = item3 = item4 = flow = endDate = endTime  = "";
	        int count, qty, itemCount;
	        count = qty = itemCount = 0;
	        while (iterator.hasNext()) {
	        	int j = 1;
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            while (cellIterator.hasNext()) {
	            	Cell cell = cellIterator.next();
	            	if(i == 1){ //header
	                	 i++;
	                	 break;
	                 }else{
		            	switch (cell.getCellType()) {
		            		case Cell.CELL_TYPE_STRING:
		            			if(j == 1 && cell.getColumnIndex() == 0){
		            				orderId = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 3 && cell.getColumnIndex() == 2){
		            				waveDesc = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 4 && cell.getColumnIndex() == 3){
		            				lpnType = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 10 && cell.getColumnIndex() == 9){
		            				flow = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 6 && cell.getColumnIndex() == 5){
		            				item1 = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 7 && cell.getColumnIndex() == 6){
		            				item2 = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 8 && cell.getColumnIndex() == 7){
		            				item3 = cell.getStringCellValue();
		            				++j;
		            			}else if(j == 9 && cell.getColumnIndex() == 8){
		            				item4 = cell.getStringCellValue();
		            				++j;
		            			}
		            			break;
		            		case Cell.CELL_TYPE_NUMERIC:
		            			if(j == 2 && cell.getColumnIndex() == 1){
		            				count = new Double(cell.getNumericCellValue()).intValue();
		            				++j;
		            			}else if(j == 5 && cell.getColumnIndex() == 4){
		            				qty = new Double(cell.getNumericCellValue()).intValue();
		            				++j;
		            			}else if(j == 6 && cell.getColumnIndex() == 5){
		            				item1 = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
		            				++j;
		            			}else if(j == 7 && cell.getColumnIndex() == 6){
		            				item2 = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
		            				++j;
		            			}else if(j == 8 && cell.getColumnIndex() == 7){
		            				item3 = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
		            				++j;
		            			}else if(j == 9 && cell.getColumnIndex() == 8){
		            				item4 = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
		            				++j;
		            			}else if(j == 11 && cell.getColumnIndex() == 10){
		            				DataFormatter dataFormatter = new DataFormatter();
		            				endTime = dataFormatter.formatCellValue(cell);
		            				++j;
		            			}else if(j == 12 && cell.getColumnIndex() == 11){
		            				DataFormatter dataFormatter = new DataFormatter();
		            				endDate = dataFormatter.formatCellValue(cell);
		            				++j;
		            			}else if(j == 13 && cell.getColumnIndex() == 12){
		            				itemCount = new Double(cell.getNumericCellValue()).intValue();
		            				++j;
		            			}
		            			break;
		            	}
	                 }
	            }
	            if(j == 10 && i == 2){
	            	dataMap.put("OrderId", orderId);
	            	dataMap.put("Count", String.valueOf(count));
	            	dataMap.put("LPNType", lpnType);
	            	dataMap.put("Qty", String.valueOf(qty));
	            	dataMap.put("Item1", item1);
	            	dataMap.put("Item2", item2);
	            	dataMap.put("Item3", item3);
	            	dataMap.put("Item4", item4);
	            	dataMap.put("Flow", flow);
	            	dataMap.put("Time", endTime+":00");
	            	dataMap.put("Date", endDate);
	            	dataMap.put("ItemCount", String.valueOf(itemCount));
	            	updateExcelData(dataMap);
	            }
	        }
			report.addReportStep("Read Excel", "Successfully Completed reading excel", StepResult.PASS);	
		}catch(Exception e){
			report.addReportStep("Read Excel", "Unable to read excel", StepResult.FAIL);	
		}
		return dataMap;
	}

	public Map<String, String> readExcelAndUpdateXML() {
		// TODO Auto-generated method stub
			String excelFilePath = "";
			Map<String, String> dataMap = new HashMap<String, String>();
			try{
				//excelFilePath = MiamiBRTFileTest;
				//excelFilePath = MiamiBRTFile;
				//excelFilePath = Sample;
				excelFilePath = AtlantaBRT;
				File file = new File(excelFilePath);    
				FileInputStream fis = new FileInputStream(file); 
				XSSFWorkbook wb = new XSSFWorkbook(fis);   
				XSSFSheet sheet = wb.getSheetAt(0);
				Iterator<Row> iterator = sheet.iterator();  
				
		        int i = 1;
		        String orderId, waveDesc, lpnType, item1, item2, item3, item4, flow, endDate, endTime;
		        orderId = waveDesc = lpnType = item1 = item2 = item3 = item4 = flow = endDate = endTime  = "";
		        int itemQty, qty, itemCount;
		        itemQty = qty = itemCount = 0;
		        while (iterator.hasNext()) {
		        	int j = 1;
		            Row nextRow = iterator.next();
		            Iterator<Cell> cellIterator = nextRow.cellIterator();
		            while (cellIterator.hasNext()) {
		            	Cell cell = cellIterator.next();
		            	if(i == 1){ //header
		                	 i++;
		                	 break;
		                 }else{
		                	 System.out.println("j column :"+j+"cell value :"+cell.getCellType()+"Cell column index"+cell.getColumnIndex());
		                	 switch (cell.getCellType()) {
			            		case Cell.CELL_TYPE_STRING:
			            			if(j == 1 && cell.getColumnIndex() == 0){
			            				orderId = cell.getStringCellValue();
			            				++j;
			            			}
			            			else if(j == 6 && cell.getColumnIndex() == 5){
			            				item2 =cell.getStringCellValue();
			            				++j;
			            			}
			            			else if(j == 7 && cell.getColumnIndex() == 6){
			            				item3 =cell.getStringCellValue();
			            				++j;
			            			}
			            			else if(j == 8 && cell.getColumnIndex() == 7){
			            				item4 =cell.getStringCellValue();
			            				++j;
			            				
			            			}else if(j == 9 && cell.getColumnIndex() == 8){
			            				flow =cell.getStringCellValue();
			            				++j;
			            				
			            			}
			            			else{
			            				++j;
			            			}
			            			
			            			break;
			            			
			            		case Cell.CELL_TYPE_NUMERIC:
			            			
			            			if(j == 4 && cell.getColumnIndex() == 3){
			            				itemQty = new Double(cell.getNumericCellValue()).intValue();
			            				++j;
			            			}
			            			else if(j == 5 && cell.getColumnIndex() == 4){
			            				item1 =String.valueOf(new Double(cell.getNumericCellValue()).intValue());
			            				++j;
			            			}
			            			
			            			else if(j == 10 && cell.getColumnIndex() == 9){
			            				DataFormatter dataFormatter = new DataFormatter();
			            				endTime = dataFormatter.formatCellValue(cell);
			            				++j;
			            			}
			            			else if(j == 11 && cell.getColumnIndex() == 10){
			            				DataFormatter dataFormatter = new DataFormatter();
			            				endDate = dataFormatter.formatCellValue(cell);
			            				++j;
			            			}
			            			else if(j == 12 && cell.getColumnIndex() == 11){
			            				itemCount =new Double(cell.getNumericCellValue()).intValue();
			            				++j;
			            			}
			            			else{
			            			++j;
			            			}
			            			break;
		                	 }
		                 
		                  }
		            }
		            if(j == 13 && i == 2){
		            	dataMap.put("OrderId", orderId);
		            	dataMap.put("Qty", String.valueOf(itemQty));
		            	dataMap.put("Item1", item1);
		            	dataMap.put("Item2", item2);
		            	dataMap.put("Item3", item3);
		            	dataMap.put("Item4", item4);
		            	dataMap.put("Flow", flow);
		            	dataMap.put("Time", endTime);
		            	dataMap.put("Date", endDate);
		            	dataMap.put("ItemCount", String.valueOf(itemCount));
		            	updateExcelData(dataMap);
		            }
		        }
				report.addReportStep("Read Excel", "Successfully Completed reading excel", StepResult.PASS);	
			}catch(Exception e){
				report.addReportStep("Read Excel", "Unable to read excel", StepResult.FAIL);	
			}
			return dataMap;
	}

	public void updateExcelData(Map<String, String> dataMap) {
		if(dataMap.size() > 0){
			try{
				String path = "";
				//path = flowXmls(dataMap.get("Flow"));
				path = flowChangeXmls(dataMap.get("Flow"));
				//check for empty
				if(!path.equalsIgnoreCase("")){
					//get count of line item tag
					int tagCount = DFWMSPOObject.getTagCount(path);
					
					//add or remove line item details
					DFWMSPOObject.updateLineItemData(Integer.parseInt(dataMap.get("ItemCount")), tagCount, path, path);
					
					//getting sku count
					//int skuCount = Integer.parseInt(dataMap.get("ItemCount"));
					List<String> items = new ArrayList<String>();
					for(int x = 0;x < 4;x++){
						if(!dataMap.get("Item1").equalsIgnoreCase("NA") && x == 0){
							items.add(dataMap.get("Item1"));
						}else if(!dataMap.get("Item2").equalsIgnoreCase("NA") && x == 1){
							items.add(dataMap.get("Item2"));
						}else if(!dataMap.get("Item3").equalsIgnoreCase("NA") && x == 2){
							items.add(dataMap.get("Item3"));
						}else if(!dataMap.get("Item4").equalsIgnoreCase("NA") && x == 3){
							items.add(dataMap.get("Item4"));
						}
					}
					
					//update excel data into xml
//					updateXML(dataMap.get("OrderId"),Integer.parseInt(dataMap.get("Count")),dataMap.get("LPNType"),
//							Integer.parseInt(dataMap.get("Qty")), items, dataMap.get("Flow"),dataMap.get("Time"),
//							dataMap.get("Date"),path);
					
					updateXMLValue(dataMap.get("OrderId"),Integer.parseInt(dataMap.get("ItemCount")),
							Integer.parseInt(dataMap.get("Qty")), items, dataMap.get("Flow"),dataMap.get("Time"),
							dataMap.get("Date"),path);
					report.addReportStep("Update Excel Data", "Reading Excel data and update xml success", StepResult.PASS);
				}else{
					report.addReportStep("Get Excel path", "Unable to get the excel path to update", StepResult.FAIL);
				}
			}catch(Exception e){
				report.addReportStep("Update Excel data for flow","Unable to update xml for flow "+dataMap.get("Flow"),StepResult.FAIL);	
			}
		}else{
			report.addReportStep("Update Excel Data", "Unable to retrieve data from excel", StepResult.FAIL);
		}
	}
	private String flowChangeXmls(String flow) {
		String path = "";
		if(flow.contains("FEDEX")){
			path = FilePathHDUMiamiBRT;
		}else if(flow.equalsIgnoreCase("UPS")){
			path = FilePathLTLMiamiBRT;
		}
//		else if(flow.contains("BVR")){
//			path = FilePathUPSMiamiBRT;
//		}
		return path;
	}
	private String flowXmls(String flow) {
		String path = "";
		if(flow.contains("HDU")){
			path = FilePathHDUMiamiBRT;
		}else if(flow.equalsIgnoreCase("LTL")){
			path = FilePathLTLMiamiBRT;
		}else if(flow.contains("MDO")){
			path = FilePathMDOMiamiBRT;
		}else if(flow.contains("FEDEX")){
			path = FilePathFedexMiamiBRT;
		}else if(flow.contains("UPS")){
			path = FilePathUPSMiamiBRT;
		}
		return path;
	}

	private void updateXML(String orderId, int count, String lpnType, int qty,
			List<String> items, String flow, String endTime, String endDate, String path) {
		List<String> orderIds = new ArrayList<String>();
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			document = documentBuilder.parse(path);
			
			final Calendar calendar = Calendar.getInstance();
			String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
	        String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
	        String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());
			
	        //OrderedDttm - current date and time
			Node orderDate = document.getElementsByTagName("OrderedDttm").item(0);
			orderDate.setTextContent(CurrentDate);
			
			//PickupStartDttm - current date and time but for parcel it should be working days
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";	
				} 
				Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
				pickupStartDttmDate.setTextContent(DeliveryDate);
			}else{
				Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
				pickupStartDttmDate.setTextContent(DeliveryDate+ "  " +"00:00:00");
			}
			
			endDate = "03/11/2022 "+endTime;
			Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
			pickupEndDttmDate.setTextContent(endDate);
			/*//PickupEndDttm - date feb mid after 20 and time from excel date must be working day
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDateCalendar.add(Calendar.MONTH, 1);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDateCalendar.add(Calendar.MONTH, 1);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				}
				Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
				pickupEndDttmDate.setTextContent(DeliveryDate);
			}else{
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				DeliveryDateCalendar.add(Calendar.MONTH, 1);
				DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
				pickupEndDttmDate.setTextContent(DeliveryDate);
			}*/
			
			Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
			deliveryStartDate.setTextContent("03/14/2022 00:00:00");
			/*//DeliveryStartDttm - march after pickup end. parcel must be working day
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDateCalendar.add(Calendar.MONTH, 2);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00";
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDateCalendar.add(Calendar.MONTH, 2);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00";
				}
				Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
				deliveryStartDate.setTextContent("03/01/2022 00:00:00");
			}else{
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				DeliveryDateCalendar.add(Calendar.MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00"; 
				Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
				deliveryStartDate.setTextContent("03/01/2022 00:00:00");
			}*/
			
			//DeliveryEndDttm
			Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
			deliveryEndDate.setTextContent("03/15/2022"+" "+endTime);
			
			//SchedDlvryEndDate 
			String marchEnd = "03/31/2022" + " " +"00:00:00";
			Node schedDlvryEndDate = document.getElementsByTagName("SchedDlvryEndDate").item(0);
			schedDlvryEndDate.setTextContent(marchEnd);
			
			//MustReleaseByDttm
			Node mustReleaseDate = document.getElementsByTagName("MustReleaseByDttm").item(0);
			mustReleaseDate.setTextContent("03/11/2022 "+endTime);
			
			//RouteType1,FederatedStoreNbr, DestinationFacilityAliasId for HDU orders
			String val = "";
			if(flow.contains("HDU")){
				if(flow.equalsIgnoreCase("HDU DA 1")){
					val = "60000MS005";
				}else{
					val = "60000FL017";
				}
			}
			if(val.contains("60000")){
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent(val);
				
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent(val);
				
				Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
				DestinationFacilityAliasId.setTextContent(val);
			}
			
			if(flow.equalsIgnoreCase("MDO 5848")){
				
				//FederatedStoreNbr
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent("MDO5829");
				
				//ReferenceField12
				Node ReferenceField12 = document.getElementsByTagName("ReferenceField12").item(0);
				ReferenceField12.setTextContent("5829");
				
				//RouteType1
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent("MDO5829");
			}else if(flow.equalsIgnoreCase("MDO 5841")){
				//FederatedStoreNbr
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent("MDO5841");
				
				//ReferenceField12
				Node ReferenceField12 = document.getElementsByTagName("ReferenceField12").item(0);
				ReferenceField12.setTextContent("5841");
				
				//RouteType1
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent("MDO5841");
			}
			//updating item, qty and lpn type
			for(int i=0;i<items.size();i++){
				//DoLineNbr
				Node DoLineNbr = document.getElementsByTagName("DoLineNbr").item(i);                   
				DoLineNbr.setTextContent(String.valueOf(i+1));
				
				//itemName
				Node ItemName = document.getElementsByTagName("ItemName").item(i);                   
				ItemName.setTextContent(items.get(i).trim());
				
				//OrderQty
				Node OrderQty = document.getElementsByTagName("OrderQty").item(i);                   
				OrderQty.setTextContent(String.valueOf(qty));
				
				//LpnType
				Node LpnType = document.getElementsByTagName("LpnType").item(i);                   
				LpnType.setTextContent(lpnType);
			}
			
			//increment code
			/*Pattern p = Pattern.compile("[0-9]+|[A-Z]+");
			Matcher m = p.matcher(orderId);
			String str = "";
			int id = 0;
			List<String> allMatches = new ArrayList<>();
			while (m.find()) {
			    allMatches.add(m.group());
			}
			if(allMatches.size()>0){
				str = allMatches.get(0);
				id = Integer.parseInt(allMatches.get(1));
			}*/
		//	for(int i=0;i<count;i++){
				//int str = sub1+i;
				//orderId = str+(id++);
				
				Node DistributionOrderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
				DistributionOrderId.setTextContent(orderId);
				orderIds.add(orderId);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult;
				streamResult = new StreamResult(new File(path).getPath());
				transformer.transform(domSource, streamResult);                 
				updateDocument(document, path);
				System.out.println("The XML File was Updated Successfully");
				
				//post xml
				//if(!flow.equalsIgnoreCase("FEDEX HUB 210") || !flow.equalsIgnoreCase("UPS HUB 211")){
					//postXml(flow);
					report.addReportStep("Post Order", "Posted Order: "+orderId, StepResult.PASS);
					System.out.println(orderId);
					if(wh.isElementPresent(Reset, 5)){
						wh.clickElement(Reset);
					}
			//	}
				report.addReportStep("Post Xml", "Posted "+flow + " "+orderIds+ " "+orderIds.size(), StepResult.PASS);
			//}
		}catch(Exception e){
			report.addReportStep("Update and posting item details in xml", "Unable to update and posting xml for flow :"+flow, StepResult.FAIL);	
		}
	}
	private void updateXMLValue(String orderId, int count,  int qty,
			List<String> items, String flow, String endTime, String endDate, String path) {
		
		// TODO Auto-generated method stub

		List<String> orderIds = new ArrayList<String>();
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			document = documentBuilder.parse(path);
			
			final Calendar calendar = Calendar.getInstance();
			String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
	        String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
	        String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());
			
	        //OrderedDttm - current date and time
			Node orderDate = document.getElementsByTagName("OrderedDttm").item(0);
			orderDate.setTextContent(CurrentDate);
			
			//PickupStartDttm - current date and time but for parcel it should be working days
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";	
				} 
				Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
				pickupStartDttmDate.setTextContent(DeliveryDate);
			}else{
				Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
				pickupStartDttmDate.setTextContent(DeliveryDate+ "  " +"00:00:00");
			}
			
			endDate = "03/11/2022 "+endTime;
			Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
			pickupEndDttmDate.setTextContent(endDate);
			/*//PickupEndDttm - date feb mid after 20 and time from excel date must be working day
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDateCalendar.add(Calendar.MONTH, 1);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDateCalendar.add(Calendar.MONTH, 1);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				}
				Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
				pickupEndDttmDate.setTextContent(DeliveryDate);
			}else{
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				DeliveryDateCalendar.add(Calendar.MONTH, 1);
				DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +endTime;
				Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
				pickupEndDttmDate.setTextContent(DeliveryDate);
			}*/
			
			Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
			deliveryStartDate.setTextContent("03/14/2022 00:00:00");
			/*//DeliveryStartDttm - march after pickup end. parcel must be working day
			if(flow.contains("FEDEX") || flow.contains("UPS")){
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				Date obDate = new Date();
				
				if(obDate.getDay() == 4){
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDateCalendar.add(Calendar.MONTH, 2);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00";
				}else{
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDateCalendar.add(Calendar.MONTH, 2);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00";
				}
				Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
				deliveryStartDate.setTextContent("03/01/2022 00:00:00");
			}else{
				SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
				final Calendar DeliveryDateCalendar = Calendar.getInstance();
				DeliveryDateCalendar.add(Calendar.MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ " " +"00:00:00"; 
				Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
				deliveryStartDate.setTextContent("03/01/2022 00:00:00");
			}*/
			
			//DeliveryEndDttm
			Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
			deliveryEndDate.setTextContent("03/15/2022"+" "+endTime);
			
			//SchedDlvryEndDate 
			String marchEnd = "03/31/2022" + " " +"00:00:00";
			Node schedDlvryEndDate = document.getElementsByTagName("SchedDlvryEndDate").item(0);
			schedDlvryEndDate.setTextContent(marchEnd);
			
			//MustReleaseByDttm
			Node mustReleaseDate = document.getElementsByTagName("MustReleaseByDttm").item(0);
			mustReleaseDate.setTextContent("03/11/2022 "+endTime);
			
			//RouteType1,FederatedStoreNbr, DestinationFacilityAliasId for HDU orders
			String val = "";
			if(flow.contains("HDU")){
				if(flow.equalsIgnoreCase("HDU DA 1")){
					val = "60000MS005";
				}else{
					val = "60000FL017";
				}
			}
			if(val.contains("60000")){
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent(val);
				
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent(val);
				
				Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
				DestinationFacilityAliasId.setTextContent(val);
			}
			
			if(flow.equalsIgnoreCase("MDO 5848")){
				
				//FederatedStoreNbr
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent("MDO5829");
				
				//ReferenceField12
				Node ReferenceField12 = document.getElementsByTagName("ReferenceField12").item(0);
				ReferenceField12.setTextContent("5829");
				
				//RouteType1
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent("MDO5829");
			}else if(flow.equalsIgnoreCase("MDO 5841")){
				//FederatedStoreNbr
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent("MDO5841");
				
				//ReferenceField12
				Node ReferenceField12 = document.getElementsByTagName("ReferenceField12").item(0);
				ReferenceField12.setTextContent("5841");
				
				//RouteType1
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent("MDO5841");
			}
			//updating item, qty and lpn type
			for(int i=0;i<items.size();i++){
				//DoLineNbr
				Node DoLineNbr = document.getElementsByTagName("DoLineNbr").item(i);                   
				DoLineNbr.setTextContent(String.valueOf(i+1));
				
				//itemName
				Node ItemName = document.getElementsByTagName("ItemName").item(i);                   
				ItemName.setTextContent(items.get(i).trim());
				
				//OrderQty
				Node OrderQty = document.getElementsByTagName("OrderQty").item(i);                   
				OrderQty.setTextContent(String.valueOf(qty));
				
				//LpnType
				Node LpnType = document.getElementsByTagName("LpnType").item(i);                   
				//LpnType.setTextContent(lpnType);
			}
			
			//increment code
			/*Pattern p = Pattern.compile("[0-9]+|[A-Z]+");
			Matcher m = p.matcher(orderId);
			String str = "";
			int id = 0;
			List<String> allMatches = new ArrayList<>();
			while (m.find()) {
			    allMatches.add(m.group());
			}
			if(allMatches.size()>0){
				str = allMatches.get(0);
				id = Integer.parseInt(allMatches.get(1));
			}*/
		//	for(int i=0;i<count;i++){
				//int str = sub1+i;
				//orderId = str+(id++);
				
				Node DistributionOrderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
				DistributionOrderId.setTextContent(orderId);
				orderIds.add(orderId);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult;
				streamResult = new StreamResult(new File(path).getPath());
				transformer.transform(domSource, streamResult);                 
				updateDocument(document, path);
				System.out.println("The XML File was Updated Successfully");
				
				//post xml
				//if(!flow.equalsIgnoreCase("FEDEX HUB 210") || !flow.equalsIgnoreCase("UPS HUB 211")){
					//postXml(flow);
					report.addReportStep("Post Order", "Posted Order: "+orderId, StepResult.PASS);
					System.out.println(orderId);
					if(wh.isElementPresent(Reset, 5)){
						wh.clickElement(Reset);
					}
			//	}
				report.addReportStep("Post Xml", "Posted "+flow + " "+orderIds+ " "+orderIds.size(), StepResult.PASS);
			//}
		}catch(Exception e){
			report.addReportStep("Update and posting item details in xml", "Unable to update and posting xml for flow :"+flow, StepResult.FAIL);	
		}
	
	}
	
	private void postXml(String flow) throws Exception {
		
		path = flowChangeXmls(flow);
		
		if(wh.isElementPresent(ChooseFile, 3)){
			wh.sendKeys(ChooseFile, path);
			wh.clickElement(Send);
			report.addReportStep("Post Xml", "posted "+flow, StepResult.PASS);
		}else{
			report.addReportStep("Post Xml", "Unable to Post xml for "+flow, StepResult.FAIL);
		}

		Thread.sleep(1000);
	}

	private void updateDocument(Document document, String xmlPath)throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource domSource = new DOMSource(document);
		streamResult = new StreamResult(xmlPath);
		transformer.transform(domSource, streamResult);
	}
}
