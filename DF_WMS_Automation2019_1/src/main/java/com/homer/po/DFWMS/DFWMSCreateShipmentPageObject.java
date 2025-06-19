package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSCreateShipmentPageObject extends PageBase {

	
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static ArrayList<String> shipmentIDfromDB = new ArrayList<String>() ;
	
	public String shipmentIDValue;
	
	
	
	public DFWMSCreateShipmentPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public String createShipment(String screen,String doId) throws Exception {
		
		Thread.sleep(8000);
		//wh.clickElement(Maximize);
		//Thread.sleep(1000);
		//driver.switchTo().frame(0);
		Thread.sleep(2000);
		
		try{
			if (wh.isElementPresent(ShipmentViewClose, 5)) {
				wh.clickElement(ShipmentViewClose);
			}
			//driver.switchTo().frame(0);
			
			if(wh.isElementPresent(DropDwn, 5)) {
				wh.sendKeys(DropDwn,"Distribution Order");
				driver.findElement(DropDwn).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
			}
			
			/*if (wh.isElementPresent(DONbrTxt, 5)) {
				wh.sendKeys(DONbrTxt,"05200807B");
				Thread.sleep(1000);
				driver.findElement(DONbrTxt).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				
			}*/
			
			if (wh.isElementPresent(DONbrTxt, 5)) {
			wh.sendKeys(DONbrTxt,doId.toString().trim());
			Thread.sleep(1000);
			driver.findElement(DONbrTxt).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			}
			
			
			
			
			By DOSrchApply1 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[4]");
			//By DOSrchApply1 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')and contains(text(),'Apply')])[3]");
			
			
			
			if (wh.isElementPresent(DOSrchApply1, 5)) {
				wh.clickElement(DOSrchApply1);
				Thread.sleep(3000);
			}
			if (wh.isElementPresent(DOViewClose, 5)) {
				wh.clickElement(DOViewClose);
			}
			
			
			if (wh.isElementPresent(DOCheckBox, 5)) {
				wh.clickElement(DOCheckBox);
				Thread.sleep(1000);
			}
			if(screen.equalsIgnoreCase("SplitShipmentDallas")){
			
				if (wh.isElementPresent(LPNCheckBox, 5)) {
					wh.clickElement(LPNCheckBox);
				}
			}else{
				if (wh.isElementPresent(LPNMainCheckBox, 5)) {
					wh.clickElement(LPNMainCheckBox);
				}
			}
			
			Actions actions = new Actions(driver);
			Thread.sleep(1000);
			actions.contextClick(driver.findElement(LPNClick)).perform();
			//Thread.sleep(3000);
			
			By CreateShipment1 = By.xpath("(//span[contains(@id,'menuitem-') and contains(@class,'x-menu-item-text') and contains(text(),'Create Shipment')])");
			
			if (wh.isElementPresent(CreateShipment, 5)) {
				wh.clickElement(CreateShipment);
				Thread.sleep(1000);
				
			}else if (wh.isElementPresent(CreateShipment1, 5)) {
				wh.clickElement(CreateShipment1);
				Thread.sleep(1000);
				
			}
			
			if (wh.isElementPresent(Override, 5)) {
				wh.clickElement(Override);
				Thread.sleep(3000);
				
			}
			
			/*if(wh.isElementPresent(ShipmentViewDODropDwn, 5)) {
				wh.sendKeys(ShipmentViewDODropDwn,"Containing Order Id");
				driver.findElement(ShipmentViewDODropDwn).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
			}
		    
			if (wh.isElementPresent(ShipmentDO, 5)) {
				wh.sendKeys(ShipmentDO,"05185137B");
				Thread.sleep(1000);
				driver.findElement(ShipmentDO).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				
			}*/
			
			
			
			jd.dbDFWMSMapping();
			shipmentIDfromDB = jd.array_Database_Connection("select shp.tc_shipment_id from LPN LP,Shipment shp where LP.TC_SHIPMENT_ID = shp.TC_SHIPMENT_ID and tc_order_id in ('"+doId+"')");
			if(!shipmentIDfromDB.isEmpty()){
				shipmentIDValue =  shipmentIDfromDB.get(0).toString();
			}
			System.out.println("shipment id returned from DB is" + shipmentIDValue);
			report.addReportStep("Create Shipment","Shipment created successfully - " + shipmentIDValue,StepResult.PASS);
			closebtn();
		}catch(Exception e) {
			System.out.println("Shipment Screen Catch");
			report.addReportStep("Navigate to Shipment Screen screen","Unable to navigate to Shipment Screen screen - " + e.getMessage(),StepResult.WARNING);
			//rc.throwTCTerminationException();
	}
		return shipmentIDValue;
		

}

	public String generateShipment(String screen, String doId) {
		String shipmentId = "";
		try {
			wh.clickElement(Maximize);
			Thread.sleep(2000);
			
			if (wh.isElementPresent(ShipmentAdd, 5)) {
				wh.clickElement(ShipmentAdd);
				Thread.sleep(5000);
			}
			
			driver.switchTo().frame(0);
			wh.clickElement(Maximize);
			String currentDate = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
			shipmentId = "S00000"+currentDate;
			
			//shipment id validation
			jd.dbDFWMSMapping();
			Thread.sleep(5000);
//			List<String> shipmentIds = new ArrayList<String>();
//			//shipmentIds = jd.array_Database_Connection("select tc_shipment_id from shipment where tc_shipment_id = '"+shipmentId+"'");
//			Thread.sleep(1000);
//			while(!shipmentIds.contains(shipmentId)){
//				shipmentId = "S10000"+currentDate;
//				//shipmentIds = jd.array_Database_Connection("select tc_shipment_id from shipment where tc_shipment_id = '"+shipmentId+"'");
//			}
//			
//			if (wh.isElementPresent(Shipment, 5)) {
//				wh.sendKeys(Shipment, shipmentId);
//				System.out.println("Shipment Id generated : "+shipmentId);
//				Thread.sleep(1000);
//			}
//			
			if (wh.isElementPresent(BillingMethod, 5)) {
				wh.selectValue(BillingMethod, "Consignee Bill");
				Thread.sleep(1000);
			}
			
			String DesignatedModeVal = "";
			if(screen.equalsIgnoreCase("Shipment1")
					|| screen.equalsIgnoreCase("Shipment2")
					|| screen.equalsIgnoreCase("Shipment_Multi")
					|| screen.equalsIgnoreCase("BVR_Mutli_Stop")
					||  screen.equalsIgnoreCase("BVR_Dallas_Yard")
					|| screen.equalsIgnoreCase("BVR_Lacey")
					|| screen.equalsIgnoreCase("BVR_Houston")
					||screen.equalsIgnoreCase("LoadTrailor_NonParcel")){
				DesignatedModeVal = "TL";
			}else{
				DesignatedModeVal = "LTL";
			}
			
			if (wh.isElementPresent(DesignatedMode, 5)) {
				wh.selectValue(DesignatedMode, DesignatedModeVal);
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);

			jd.dbDFWMSMapping();
			String shipVia = "";
			Thread.sleep(2000);
			if(screen.equalsIgnoreCase("BVR_Mutli_Stop")||screen.equalsIgnoreCase("Shipment1")){
				doId = DFWMSInbounfFlowStepDefn.doIds.get(0);
			}else if(screen.equalsIgnoreCase("Shipment2")) {
				
				doId = DFWMSInbounfFlowStepDefn.doIds.get(1);

			}else if(screen.equalsIgnoreCase("Shipment_Multi")) {
				
				doId = DFWMSInbounfFlowStepDefn.doIds.get(0);

			}
			ArrayList<String> shipViaList = jd.array_Database_Connection("select unique ship_via from LPN where TC_ORDER_ID = '"+doId+"'");
			if(!shipViaList.isEmpty()){
				shipVia =  shipViaList.get(0).toString();
			}
			System.out.println("shipVia id returned from DB is " + shipVia);
			//String ServiceLevelVal = "5823";
			if(shipVia.isEmpty() || shipVia.equalsIgnoreCase("0001")){
				//shipVia = "(none)";
				report.addReportStep("Create Shipment", "Ship via not generated", StepResult.FAIL);
				rc.throwTCTerminationException();
			}
			if (wh.isElementPresent(ServiceLevel, 5)) {
				wh.selectValue(ServiceLevel, shipVia);
				Thread.sleep(1000);
			}
			
			if (wh.isElementPresent(StopsTab, 5)) {
				wh.clickElement(StopsTab);
				Thread.sleep(1000);
			}
			
			String dc = "";
			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
				dc = "5829";
			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
				dc = "5832";
			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
				dc = "5823";
			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")||jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
				dc = "5831";
			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")){
				dc = "5855";
			}else if(jd.envrnment.equalsIgnoreCase("New_Boston_2019")){
				dc = "5882";
			}else if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
				dc = "5854";
			}else if(jd.envrnment.equalsIgnoreCase("Tracey_2019")){
				dc = "5857";
			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
				dc = "5841";
			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
				dc = "5860";
			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
				dc = "5523";
			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
				dc = "6705";
			}else if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")) {
			
				dc="6777";
				
			}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")) {
				dc="6760";
			}else if(jd.envrnment.equalsIgnoreCase("Perris_2019")){
				dc = "6006";
			}
			/*if(screen.equalsIgnoreCase("MDO_Houston")){
				facility1 = "5831";
			}else{
				facility1 = "5823";
			}*/
			
			if (wh.isElementPresent(Facility1, 5)) {
				wh.sendKeys(Facility1, dc);
			}
			
			String facility2 = "";
			if(screen.equalsIgnoreCase("BVR_Dallas")
					|| screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
					|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
					|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")
					|| screen.equalsIgnoreCase("BVR_Houston")
					|| screen.equalsIgnoreCase("LoadTrailor_NonParcel")
					/*screen.equalsIgnoreCase("Shipment1")
					|| screen.equalsIgnoreCase("Shipment2")*/){ 
				facility2 = "5023";
			}else if(screen.equalsIgnoreCase("BVR_Mutli_Stop")){
				facility2 = "5520";
			}/*else if(screen.equalsIgnoreCase("MDO_Dallas")){
				facility2 = "5823";
			}else if(screen.equalsIgnoreCase("HDU_TL_Dallas")
					|| screen.equalsIgnoreCase("LTL_HDU_Dallas")
					|| screen.equalsIgnoreCase("LTL_HDU_Houston")){
				facility2 = "60000TX012";  //s 60000PA014
			}else if(screen.equalsIgnoreCase("LTL_HDU_Baltimore")){
				facility2 = "60000TX013";
			}*/else if(screen.equalsIgnoreCase("BVR_Lacey")
					||screen.equalsIgnoreCase("Shipment1")
					|| screen.equalsIgnoreCase("Shipment2")
					|| screen.equalsIgnoreCase("Shipment_Multi")){
				jd.dbDFWMSMapping();
				if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("Houston_2019")) {
					facility2 = "5023";
				}else {
					facility2 = "6707";
				}
			}else{
				facility2 = "4787";
			}
			if (wh.isElementPresent(Facility2, 5)) {
				wh.sendKeys(Facility2, facility2);
			}
			
			if (wh.isElementPresent(ShipmentSave, 5)) {
				wh.clickElement(ShipmentSave);
				Thread.sleep(1000);
				
				 shipmentId = driver.findElement(By.xpath("//div//span[@id='dataForm:ShpDtl_Out_ShipID']")).getText();
				 
			}
			
			if (wh.isElementPresent(ShipmentMore, 5)) {
				wh.clickElement(ShipmentMore);
				Thread.sleep(1000);
			}
			
			if (wh.isElementPresent(EditAssResources, 5)) {
				wh.clickElement(EditAssResources);
				Thread.sleep(1000);
			}
			
			Set<String> handles = driver.getWindowHandles();
			String current = driver.getWindowHandle();
			handles.remove(current);
			String newTab = handles.iterator().next();
			driver.switchTo().window(newTab);
			
			if (wh.isElementPresent(ShipVia, 5)) {
				
				WebElement select = driver.findElement(By.name("assignedShipvia"));
				List<WebElement> options = select.findElements(By.tagName("option"));
				for (WebElement option : options) {
					
				 if(shipVia.equalsIgnoreCase("EXLA")) {
					 
					 wh.selectValue(ShipVia, "ESTES - EXLA;The Home Depot");
					 break;
				 }else if(shipVia.equalsIgnoreCase("5823")) {
					 
					 wh.selectValue(ShipVia, "5823 - Dallas - 5823;The Home Depot");
				 }
//				 else if(shipVia.equalsIgnoreCase("S777")) {
//					 
//					 wh.selectValue(ShipVia, "Shotgun 6777 - S777;The Home Depot");
//				 }
				 else if(shipVia.equalsIgnoreCase("4711")) {
					 
					 wh.selectValue(ShipVia, "4711 - 4711;The Home Depot");
				 }else if(shipVia.equalsIgnoreCase("WEND")) {
					 
					 wh.selectValue(ShipVia, "Werner Dedicated - WEND;The Home Depot");
				 }else if(shipVia.equalsIgnoreCase("UPOR")) {
					 
					 wh.selectValue(ShipVia, "UPOR - UPOR;The Home Depot");
				 }

				 else if(option.getText().contains(shipVia)){
				    option.click();
				    break;
				}
				
				}
				
//				if(screen.equalsIgnoreCase("MDO_Atlanta")){
//					wh.selectValue(ShipVia, "5860 - Atlanta - 5860;The Home Depot");  
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("MDO_Houston")){
//					wh.selectValue(ShipVia, "5831 - Houston - 5831;The Home Depot");  
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("MDO_Dallas")
//						|| screen.equalsIgnoreCase("BVR_Dallas")
//						|| screen.equalsIgnoreCase("LTL_HDU_Dallas")){
//					
//					if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {
//						wh.selectValue(ShipVia, "6707-Troy - 6707;The Home Depot");  //changed for dallas BVR_MultiShipment flow
//						Thread.sleep(1000);	
//					}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
//						wh.selectValue(ShipVia, "5120 - Van Buren - 5120;The Home Depot");
//						Thread.sleep(2000);
//					}
//					wh.selectValue(ShipVia, "5823 - Dallas - 5823;The Home Depot");  
//					Thread.sleep(1000);
//					}
//			
////				else if(screen.equalsIgnoreCase("BVR_Dallas")
////						|| screen.equalsIgnoreCase("LTL_HDU_Dallas")){
////					wh.selectValue(ShipVia, "5823 - Dallas - 5823;The Home Depot");  
////					Thread.sleep(1000);
////				}
//				else if(screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
//						|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
//						|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")){
//					wh.selectValue(ShipVia, "Shotgun 6777 - S777;The Home Depot");  
//					Thread.sleep(1000); 
//				}/*else if(screen.equalsIgnoreCase("BVR_Dallas")){
//					wh.selectValue(ShipVia, "5831 - Houston - 5831;The Home Depot");  
//					Thread.sleep(1000);
//				}*/else if(screen.equalsIgnoreCase("LTL_Dallas")
//						|| screen.equalsIgnoreCase("LTL_Lacey")
//						||screen.equalsIgnoreCase("LTL_Multistop")){
//					
//					if(jd.envrnment.equalsIgnoreCase("HGT_2019")) {
//						wh.selectValue(ShipVia, "AACT AAA Cooper - AACT;The Home Depot");
//					}else {
//						wh.selectValue(ShipVia, "03AM - 03AM;The Home Depot");  
//						Thread.sleep(1000);
//
//					}
//
//				}/*else if(
//						//|| screen.equalsIgnoreCase("HDU_TL_Dallas")
//						){
//					wh.selectValue(ShipVia, "5831 - Houston - 5831;The Home Depot");  
//					Thread.sleep(1000);
//				}*/else if(screen.equalsIgnoreCase("HDU_TL_Dallas")
//						||screen.equalsIgnoreCase("LTL_HDU_Lacey")){
//					
//					if(jd.envrnment.equalsIgnoreCase("Dallas_2019")) {
//						wh.selectValue(ShipVia, "AAA Cooper - AACT;The Home Depot");
//					}else {
//						wh.selectValue(ShipVia, "AACT AAA Cooper - AACT;The Home Depot");
//					}
//					
//					  
//					Thread.sleep(1000); 
//				}else if(screen.equalsIgnoreCase("MDO_Baltimore")){
//					wh.selectValue(ShipVia, "5829 - Baltimore - 5829;The Home Depot");  
//					Thread.sleep(1000); 
//				}else if(screen.equalsIgnoreCase("MDO_Lacey")){
//					wh.selectValue(ShipVia, "Packsize - 0001;The Home Depot");  
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("HDU_TL_Baltimore")
//						|| screen.equalsIgnoreCase("LTL_Baltimore")
//						|| screen.equalsIgnoreCase("LTL_HDU_Baltimore")
//						|| screen.equalsIgnoreCase("LTL_Houston")
//						|| screen.equalsIgnoreCase("MDO_VAS")
//						|| screen.equalsIgnoreCase("MDO_Miami")
//						){
//					wh.selectValue(ShipVia, "AACT AAA Cooper - AACT;The Home Depot");
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("HDUTL_Lacey")){
//					wh.selectValue(ShipVia, "Werner Dedicated - WEND;The Home Depot");
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("BVR_Lacey")
//						||screen.equalsIgnoreCase("Shipment1")
//						||screen.equalsIgnoreCase("Shipment2")
//						|| screen.equalsIgnoreCase("Shipment_Multi")){  
//					//wh.selectValue(ShipVia, "6707-Troy - 6707;The Home Depot");
//					jd.dbDFWMSMapping();
//					if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("Houston_2019")||jd.envrnment.equalsIgnoreCase("Perris_2019")||jd.envrnment.equalsIgnoreCase("Columbus_2019")) {
//						wh.selectValue(ShipVia, "5643 - Tolleson - 5643;The Home Depot");
//					}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {
//						wh.selectValue(ShipVia, "6707-Troy - 6707;The Home Depot");  //changed for dallas BVR_MultiShipment flow
//						Thread.sleep(1000);	
//					}
//					 else {
//						wh.selectValue(ShipVia, "6707 Shotgun - S707;The Home Depot");  //changed for dallas BVR_MultiShipment flow
//						Thread.sleep(1000);
//					}
//					
//				}else if(screen.equalsIgnoreCase("BVR_Houston")){
//					
//					if(jd.envrnment.equalsIgnoreCase("Perris_2019")) {
//						
//						wh.selectValue(ShipVia, "5085 - Lake Park - 5085;The Home Depot");
//					}else {
//						wh.selectValue(ShipVia, "5643 - Tolleson - 5643;The Home Depot");
//						Thread.sleep(1000);
//					}
//					
//				}
//					
//				else if(screen.equalsIgnoreCase("MDO_Tampa")){
//					wh.selectValue(ShipVia, "5855 - Tampa - 5855;The Home Depot");
//					Thread.sleep(1000);
//				}else if(screen.equalsIgnoreCase("MDO_Tracey")){
//					wh.selectValue(ShipVia, "5857 - Tracy - 5857;The Home Depot");
//				}else if(screen.equalsIgnoreCase("LTL_HDU_Houston")){
//					//wh.selectValue(ShipVia, "EXLA Estes - S831;The Home Depot");
//					wh.selectValue(ShipVia, "AACT AAA Cooper - AACT;The Home Depot");
//
//				}else{
//					
//					if(jd.envrnment.equalsIgnoreCase("Perris_2019")) {
//						
//						wh.selectValue(ShipVia, "5643 - Tolleson - 5643;The Home Depot");
//					}else {
//						wh.selectValue(ShipVia, "5023 - 5023;The Home Depot");
//					}
//					
//					Thread.sleep(1000);
//				}
			}
			
			if (wh.isElementPresent(ShipViaSave, 5)) {
				wh.clickElement(ShipViaSave);
				Thread.sleep(1000);
			}
			driver.switchTo().window(current);
			Thread.sleep(2000);

			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("document.body.style.zoom='80%'");
			driver.switchTo().frame(0);
			closebtn();
			Thread.sleep(2000);

			js.executeScript("document.body.style.zoom='100%'");
			driver.switchTo().defaultContent();
			report.addReportStep("Generate Shipment ","Shipment Generated: "+shipmentId, StepResult.PASS);
			closebtn();
		} catch (Exception e) {
			report.addReportStep("Navigate to Shipment Screen screen","Unable to navigate to Shipment Screen screen - " + e.getMessage(),StepResult.WARNING);
			if(e.getMessage().contains("locate element with text:")){
				//shipvia fix
				System.out.println();
			}
		}
		return shipmentId;
	}
}
