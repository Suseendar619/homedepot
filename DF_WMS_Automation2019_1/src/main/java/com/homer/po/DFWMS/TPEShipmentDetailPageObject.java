package com.homer.po.DFWMS;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.tools.ant.filters.TokenFilter.Trim;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.opera.core.systems.scope.protos.UmsProtos.Command;



public class TPEShipmentDetailPageObject extends PageBase{

	public TPEShipmentDetailPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	//static final By ShipmentDetailStatus =By.id("dataForm:ShpDtl_Out_Stat");
	static final By ShipmentDetailFrame = By.xpath("//iframe[contains(@src,'ShipmentDetail.xhtml')]");	
	static final By StopListID = By.xpath(".//span[contains (@id,'dataForm:StopList_lv:StopList') and contains (@id,'Stop_StpSeq_Link_Param_Out')]");
	static final By DestinationListID = By.xpath(".//span[contains (@id,'dataForm:StopList_lv:StopList') and contains (@id,'Stop_facAli_Link_Param_Out')]");
	static final By ShipmentDetailStatus = By.xpath("//*[contains(@id,'dataForm:ShpDtl_Out_Stat')]");
	static final By MaximizeShipmentDetail=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By CloseShipmentDetail=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By ShipmentDetailsTrackingMsgScreen=By.xpath("(.//span[contains(text(),'Shipment Detail - Tracking Messages')])[1]");
	static final By ShipmentDetailsTenderDtlsScreen=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment Tender')]");
	static final By ShipmentDetailsAddTrackingMsgScreen=By.xpath(".//span[contains(text(),'Shipment Detail - Add Tracking Messages')]");
	static final By RejectedCarriersScreen=By.xpath(".//span[contains(text(),'Rejected Carriers')]");
	static final By RejectedCarrierLink=By.xpath(".//a[contains(@class,'listdatalink') and contains(text(),'Rejected Carriers')]");
	
	static final By Stopstab=By.id("ShipDtl_StopList_lnk");
	
	static final By MoreMenuOpt=By.xpath("//*[contains(@id,'moreButton')]");
	static final By TrackMessagOpt=By.id("ShpDtl_TrackMsgIndic_bttn");
	static final By TenderDetailsOpt=By.id("ShpDtl_TenderDetailsBtn");
	static final By AddTrackingMsgBtn = By.xpath("//button[contains(@onclick,'NewTrackingMessages')]");
	
	static final By MsgTypeList = By.xpath(".//*[@name='messageType']");
	static final By StopTxt = By.xpath(".//*[@name='stopNumber']");
	static final By EventDateTxt = By.xpath(".//*[@name='eventTimestamp']");
	
	static final By OverrideCodeWarningHeader = By.xpath(".//*[@id='test']/table[1]/tbody/tr[1]/td[2]");
	static final By OverrideCodeWarningMsg 	= 	By.xpath(".//*[@id='test']/table[1]/tbody/tr[2]/td[2]");
	//*[@name='overrideCode
	static final By OverrideCodeDropdown = By.xpath("//*[@id='test']/table[1]/tbody/tr[2]/td[3]/select ");
	
	static final By shipmentsave = By.xpath(".//button[text()='Save']");
	static final By OverrideCodeSave = By.xpath(".//*[@id='test']/table[2]/tbody/tr/td/button[1]");
	
	static final By shipmenttrackeventdate = By.xpath("//*[contains(@id,'jlisttable')]/tbody/tr[2]/td[6]");
	static final By shipmenttrackmessagetype = By.xpath("//*[contains(@id,'jlisttable')]/tbody/tr[2]/td[9]");
	
	static final By statusintransit = By.xpath(".//*[@id='dataForm:ShpDtl_Out_Stat']");
	static final By shipmentorderclick = By.xpath("//*[contains(@id,'jlisttable')]/tbody/tr[2]/td[4]/a");
	
	static String strdate;
	public static String evntdate;
	public static String StrEventDate;
	public static String OriginType;
	public static Date DatEventDate;
	ArrayList<String> li = new ArrayList<String>();
	List<String> litest = new ArrayList<String>();
	List<String> litestadd = new ArrayList<String>();
	List<String> l1 = new ArrayList<String>();
	
	ArrayList<String> StopList = new ArrayList<String>();
	ArrayList<String> DestinationList = new ArrayList<String>();

	
	static HashMap<String, String> hmap = new HashMap<String, String>();
	static HashMap<String, String> hmap1 = new HashMap<String, String>();
	static HashMap<String, String> Combinehmap = new HashMap<String, String>();
	

	
	/**
	 * Method to verify the status in shipment details page
	 * @throws Exception
	 * @param status
	 */
	public void shipmentDetailsStatus(String status) throws Exception{
		wh.clickElement(MaximizeShipmentDetail);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		//driver.switchTo().activeElement();
		
		
		String shipmentstatus=wh.getText(ShipmentDetailStatus);		
		System.out.println("Expected Status is " +status);
		System.out.println("Status is " +shipmentstatus);
		
		if(wh.isElementPresent(ShipmentDetailStatus, 5)){
			String shipmentdetailsstatus=wh.getText(ShipmentDetailStatus);
			
			System.out.println("Expected Status is " +status);
			System.out.println("Status is " +shipmentdetailsstatus);
			
			if(shipmentdetailsstatus.equalsIgnoreCase(status)){

				rc.logger.info("Shipment status in Shipment Details is: "+ shipmentdetailsstatus);
				report.addReportStep("Verify Status in Shipment Details is "  +status, 
						"Status in Shipment Details Page is " +status, StepResult.PASS);
		}
			
			else 
					{
			report.addReportStep("Verify Status in Shipment Details is "  +status, 
					"Status in Shipment Details Page is not " +status+ " but " +shipmentdetailsstatus, StepResult.FAIL);
					}
			
	}
		else
		{
		rc.terminateTestCase("Shipment Details", StepResult.FAIL);
		}
	}
	
	/**
	 * Method to click on more & validate tracking messages in shipment details page
	 * @throws Exception	
	 */
	public void moreSelectionBtn_TrackingMsg() throws Exception{
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		if(wh.isElementPresent(MoreMenuOpt, 5)){
			wh.clickElement(MoreMenuOpt);
			report.addReportStep("Click on More button", 
					"Successfully clicked on More button", StepResult.PASS);
			
			if(wh.isElementPresent(TrackMessagOpt, 1)){
				wh.clickElement(TrackMessagOpt);
				report.addReportStep("Select Tracking Messages Option", 
						"Tracking Messages Option selected successfully", StepResult.PASS);
				
				driver.switchTo().defaultContent();
					if(wh.isElementPresent(ShipmentDetailsTrackingMsgScreen, 3)){
						report.addReportStep("Validate Tracking Messages Screen opens", 
								"Tracking Messages Screen opened successfully", StepResult.PASS);
					}else
					{	
						rc.terminateTestCase("Validate Tracking Messages Screen opens", StepResult.FAIL);
					}
						
			}else
				{	report.addReportStep("Select Tracking Messages Option", 
						"Tracking Messages Option was not selected", StepResult.FAIL);
				}
		}else
			{
			report.addReportStep("Click on More button", 
					"Did not click on More button", StepResult.FAIL);
			}		
		}
	
	
	/**
	 * Method to validate Shipment Details - Add Tracking Messages screen and enter stop,date and save 
	 * @throws Exception	
	 * @param stop and msgtype
	 */	
	public void Click_TrackingMsg(String stop, String msgtype) throws Exception{		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();		
		
		if(wh.isElementPresent(AddTrackingMsgBtn, 5)){
			wh.clickElement(AddTrackingMsgBtn);
			report.addReportStep("Click on Add Tracking Messages", 
					"Successfully clicked on Add Tracking Messages", StepResult.PASS);
			
		}else{	
			report.addReportStep("Click on Add Tracking Messages", 
					"Unable to click on Add Tracking Messages", StepResult.FAIL);
			}
		
		driver.switchTo().defaultContent();
		
		if(wh.isElementPresent(ShipmentDetailsAddTrackingMsgScreen, 3)){
			report.addReportStep("Validate Shipment Details - Add Tracking Messages screen", 
					"Shipment Details - Add Tracking Messages screen opened successfully", StepResult.PASS);
		}else{
			rc.terminateTestCase("Validate Shipment Details - Add Tracking Messages Screen", StepResult.FAIL);
		}
		
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		
		messageTypeList(msgtype);
		
		
		stop(stop);
		
		
		eventDateTime(stop);
				
		
		shipmentSave();
		
	}

	public void shipmentSave() throws Exception {
		if(wh.isElementPresent(shipmentsave, 4)){
			wh.clickElement(shipmentsave);
			report.addReportStep("Click on Save button", 
					"Save button clicked", StepResult.PASS);
		}else{
			report.addReportStep("Click on Save button", 
					"Unable to click the Save button", StepResult.FAIL);
			}
	}

	public void eventDateTime(String stop) throws Exception {
		if(wh.isElementPresent(EventDateTxt, 4)){
			
			if(stop.equalsIgnoreCase("1")){
				futuredate(3);	
			}else{
				futuredate(4);
			}
			//String s = TPEShipmentDetailPageObject.strdate;
			wh.sendKeys(EventDateTxt, strdate) ;
			report.addReportStep("Enter Event Date/Time", 
					"Event Date/Time entered successfully: " +strdate, StepResult.PASS);
		}else{
			report.addReportStep("Enter Event Date/Time", 
					"Unable to enter Event Date/Time", StepResult.FAIL);
			}
	}

	public void stop(String stop) throws Exception {
		if(wh.isElementPresent(StopTxt, 4)){
			//wh.sendKeys(StopTxt, dataTable.getData(DataColumn.shipmentstop));
			wh.sendKeys(StopTxt, stop);
			report.addReportStep("Enter value for Stop", 
					"Value entered for stop is: "+stop, StepResult.PASS);
		}else{
			report.addReportStep("Enter value for Stop", 
					"Unable to enter value for Stop", StepResult.FAIL);
			}
	}

	public void messageTypeList(String msgtype) throws Exception {
		if(wh.isElementPresent(MsgTypeList, 2)){
			//wh.selectValue(MsgTypeList, dataTable.getData(DataColumn.shipmenttrackingmessagetype));
			wh.selectValue(MsgTypeList, msgtype);
			report.addReportStep("Validate Message Type Dropdown", 
					"Message Type Dropdown is present and '" + msgtype + "' selected ", StepResult.PASS);
			
		}else{
			report.addReportStep("Validate Message Type Dropdown", 
					"Message Type Dropdown is absent", StepResult.FAIL);
			}
	}
	
	/**
	 * Method to handle Override code
	 * @throws Exception	
	 */	
		public void OverideCode() throws Exception{
			String status = wh.getText(OverrideCodeWarningHeader);
			if(status.equals("Warning")){
				String warningMsg = wh.getText(OverrideCodeWarningMsg);
				rc.logger.info("Warning Message shown: "+ warningMsg);
				report.addReportStep("Verify Warning Message is shown on saving Tracking Messages", 
						"Warning " + warningMsg + " shown", StepResult.PASS);
				
				//int overridedrpdwn = driver.findElements(OverrideCodeDropdown).size();
				//for(int i=0; i<=overridedrpdwn; i++){
				if (wh.isElementPresent(OverrideCodeDropdown, 1)){
					wh.selectValue(OverrideCodeDropdown, "Warning Override");
					report.addReportStep("Select Override Code", 
							"Override Code selected successfully", StepResult.PASS);
				
					if (wh.isElementPresent(OverrideCodeSave, 1)){
						wh.clickElement(OverrideCodeSave);
						report.addReportStep("Click Save button to save the Override Code", 
								"Save button clicked", StepResult.PASS);
						
						driver.switchTo().defaultContent();
						driver.switchTo().activeElement();
						
						if (wh.isElementPresent(ShipmentDetailsTrackingMsgScreen, 2)){
							report.addReportStep("Validate Shipment Details - Tracking Messages screen opens after saving Override Code", 
									"Validate Shipment Details - Tracking Messages screen opened successfully", StepResult.PASS);
						}else{
							rc.terminateTestCase("Validate Shipment Details - Tracking Messages screen opens after saving Override Code,", StepResult.FAIL);

						}
							
						
					}else{
						report.addReportStep("Click Save button to save the Override Code", 
								"Save button not clicked", StepResult.FAIL);
					}
				
				}else{
					report.addReportStep("Select Override Code", 
							"Override Code selection failed", StepResult.FAIL);
				}
				//}			
			}else{
				report.addReportStep("Verify Warning Message is shown on saving Tracking Messages", 
						"Warning Message not shown", StepResult.PASS);	
			}
			
		}
		
		/**
		 * Method to validate the event date/ time and message type
		 * @throws Exception	
		 * @param msgtypeconfirm
		 */		
		public void validate_shipment_trackingmessage(String msgtypeconfirm ) throws Exception{
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
			
			System.out.println("Messages: "+msgtypeconfirm);
			System.out.println("Event Date: "+StrEventDate);
			System.out.println("Origin Type of Date: "+OriginType);
			
			String eventdatetime = wh.getText(shipmenttrackeventdate).toString();			
			String s = eventdatetime.replaceAll("[A-Z]", " ");			
			String eventdatim = s.substring(0,14);
			
			if (OriginType.equalsIgnoreCase("XML")){
				//Code to check the Timezone
				String timeZoneCheck = eventdatetime.replaceAll("[^A-Z]", "").trim();
				System.out.println("Time zone is :"+timeZoneCheck);
				
				
				//Convert to Required Timezone the timestamp from XML OR entered in UI if not in EST
				if (!timeZoneCheck.equalsIgnoreCase("EST")){
				DateFormat formatter = new SimpleDateFormat("M/d/yy HH:mm");
				DatEventDate=formatter.parse(StrEventDate);
				
					if (timeZoneCheck.equalsIgnoreCase("CST")){
				        TimeZone obj = TimeZone.getTimeZone("CST");
				        formatter.setTimeZone(obj);
					}else if (timeZoneCheck.equalsIgnoreCase("CDT")) {
						TimeZone obj = TimeZone.getTimeZone("CST");
						formatter.setTimeZone(obj);
					}
								
					StrEventDate = formatter.format(DatEventDate);
					System.out.println("Event Date in corrected Time Zone:"+StrEventDate);
				}
			}
			
			
				
					
			String messagetype = wh.getText(shipmenttrackmessagetype).toString();			
			
			if ((StrEventDate.trim()).equalsIgnoreCase(eventdatim.trim())){
				report.addReportStep("Validate whether entered date and event date are same", 
						"Entered date: " + StrEventDate.trim() + " and event date " + eventdatim.trim() + " are same", StepResult.PASS);
			}else{
				report.addReportStep("Validate whether entered date and event date are same", 
						"Entered date: " + StrEventDate.trim() + " and event date " + eventdatim.trim() + " are not same", StepResult.FAIL);
			}
			if (messagetype.equalsIgnoreCase(msgtypeconfirm)){
				report.addReportStep("Validate whether message type is " +msgtypeconfirm, 
						"Message type is "+messagetype , StepResult.PASS);
			}else{
				report.addReportStep("Validate whether message type is " +msgtypeconfirm, 
						"Message type is not "+msgtypeconfirm, StepResult.FAIL);
			}
			if (wh.isElementPresent(shipmentorderclick, 2)){
				 wh.clickElement(shipmentorderclick);
				report.addReportStep("Click on shipment order number", 
						"Clicked on shipment order number", StepResult.PASS);
				Thread.sleep(2000);
			}else{
				report.addReportStep("Click on shipment order number", 
						"Unable to click on shipment order number", StepResult.FAIL);

			}
		}
		
		
		/**
		 * Method to generate future date for STOP 1
		 * @throws Exception			
		 */	
		public void futuredate(int i) throws Exception{					
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, i);
			dt = c.getTime();	
			//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy HH:mm");
			System.out.println("Unformatted date" + dt);
			strdate = sdf.format(dt);
			//strdate = format(dt);
			
			Setdate(strdate,"UI");
		}
		
		/**
		 * Method to set date for tracking message as well as captures origin, i.e. either XML or UI entry of date
		 * @throws Exception	
		 */	
		public void Setdate(String eventdate,String orgintype ) {
			// TODO Auto-generated method stub
			
			StrEventDate = eventdate;
			OriginType = orgintype;
			rc.logger.info("Event data entered is: "+StrEventDate);
			rc.logger.info("Origin of Entered date is: "+OriginType);
//			System.out.println("Event data entered is: "+StrEventDate);
//			System.out.println("Origin of Entered date is: "+OriginType);

		}
				
		/**
		 * Method to generate future date for STOP 2
		 * @throws Exception
		 */	
		/*public void futuredate1() throws Exception{
					
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 4);
			dt = c.getTime();	
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
			strdate = sdf.format(dt);
			rc.logger.info("Event data entered is: "+strdate);
					
		}*/
		
		
		/**
		 * Method to click on more & select tender details options
		 * @throws Exception	
		 */
		public void moreSelectionBtn_TenderDtls() throws Exception{
			
			wh.clickElement(MaximizeShipmentDetail);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			if(wh.isElementPresent(MoreMenuOpt, 5)){
				wh.clickElement(MoreMenuOpt);
				report.addReportStep("Click on More button", 
						"Successfully clicked on More button", StepResult.PASS);
				
				if(wh.isElementPresent(TenderDetailsOpt, 1)){
					wh.clickElement(TenderDetailsOpt);
					report.addReportStep("Select Tender Details Option", 
							"Tender Details selected successfully", StepResult.PASS);
					
					driver.switchTo().defaultContent();
						if(wh.isElementPresent(ShipmentDetailsTenderDtlsScreen, 3)){
							report.addReportStep("Validate Tender Details Screen opens", 
									"Tender Details Screen opened successfully", StepResult.PASS);
						}else
						{	
							rc.terminateTestCase("Validate Tender Details Screen opens", StepResult.FAIL);
						}
							
				}else
					{	report.addReportStep("Select Tender Details Option", 
							"Tender Details Option was not selected", StepResult.FAIL);
					}
			}else
				{
				report.addReportStep("Click on More button", 
						"Did not click on More button", StepResult.FAIL);
				}		
			}
		
		/**
		 * Method to validate and compare Shipment Details - Stops 
		 * @throws Exception	
		 */	
		public void validateStops(String Comparator) throws Exception{
			
			wh.clickElement(MaximizeShipmentDetail);
			switchToShipmentDetailFrame();
			
			// Click on Stops tab
			if(wh.isElementPresent(Stopstab)){
				
				wh.moveToElement(Stopstab);
				wh.clickElement(Stopstab);
			
				// Identify the Stop List				
				StopList.addAll(ListofElements(StopListID));
				System.out.println("List of Stops: "+StopList);
				
				// Identify the Destination List
				DestinationList.addAll(ListofElements(DestinationListID));				
				System.out.println("List of Destinations: "+DestinationList);
				
				//Combine Stop with Destination 
				Combinehmap = ListCombination(StopList,DestinationList);
				System.out.println("Hmap of combined list is : "+ Combinehmap);
				rc.logger.info("Hmap of combined list is : "+ Combinehmap);
				
				report.addReportStep("Evaluate the list of Stops versus Destination", 
					"The Stops Tab is populated with the following Stop Versus Destination combination: "+Combinehmap, StepResult.PASS);
				
			}else{
				throw new Exception("Unable to locate Stops Tab");
				}
			
			
			//To compare Stop - Destination hmap with list from MCW page
			if (Comparator.equalsIgnoreCase("Compare")) {
				
				if (Combinehmap.equals(TPEShipmentsPageObject.MCWCombinehmap)){
					report.addReportStep("Compare the Stop-Destination list in Shipment page with that saved in MCW page", 
							"The Stop-Destination list "+Combinehmap+" in Shipment Detail page matches with Stop-Destination list "+TPEShipmentsPageObject.MCWCombinehmap+ " entered in MCW page", StepResult.PASS);
					
				}else{
					report.addReportStep("Compare the Stop-Destination list in Shipment page with that saved in MCW page", 
							"The Stop-Destination list "+Combinehmap+" in Shipment Detail page does NOT matche with Stop-Destination list "+TPEShipmentsPageObject.MCWCombinehmap+ " entered in MCW page", StepResult.FAIL);
								
				}
				
							
			}			
			driver.switchTo().defaultContent();			
			wh.clickElement(CloseShipmentDetail);
		}
		
		
		/**
		 * This function switches to the 'Shipment Detail' frame
		 */
		public void switchToShipmentDetailFrame(){
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(ShipmentDetailFrame));
		}
		
		/**
		 * Method to collect element into a list
		 * @throws Exception	
		 */	
		
		
		public ArrayList<String> ListofElements(By elementlist) throws Exception
		{
			li.clear();
			List<WebElement> l1 = driver.findElements(elementlist);
			Iterator<WebElement> wb = l1.iterator();
			while(wb.hasNext())
			{
				String s = wb.next().getText();
				li.add(s);
			}
			rc.logger.info("List created from selection of elements : "+ li);
			return li;
		}
		
		
		public HashMap ListCombination(ArrayList<String> List1 , ArrayList<String> List2 ) throws Exception
		{

				hmap.clear();
		       Iterator<String> itr = List1.iterator();
		       while(itr.hasNext())
		       {
		              Iterator<String> itr1 = List2.iterator();
		              
		              while(itr1.hasNext())
		              {
		              String s = itr.next().toString();
		              String s1 = itr1.next().toString();
		              hmap.put(s, s1);
		              }
		       }
		       
		       return hmap;
		       
		}
		
		/**
		 * Method to clear Rejected carriers
		 * @throws Exception	
		 */
		public void clear_RejectedCarriers() throws Exception{
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
			
						
			if(wh.isElementPresent(RejectedCarrierLink, 5)){
				wh.clickElement(RejectedCarrierLink);			
				report.addReportStep("Click on Rejected Carriers link", 
						"Successfully clicked on Rejected Carriers link", StepResult.PASS);
				
					if(wh.isElementPresent(RejectedCarriersScreen, 3)){
	
						report.addReportStep("Validate Rejected Carriers Screen", 
								"Rejected Carriers Screen opened successfully", StepResult.PASS);
//					
//					driver.switchTo().defaultContent();
//						if(wh.isElementPresent(ShipmentDetailsTenderDtlsScreen, 3)){
//							report.addReportStep("Validate Tender Details Screen opens", 
//									"Tender Details Screen opened successfully", StepResult.PASS);
//						}else
//						{	
//							rc.terminateTestCase("Validate Tender Details Screen opens", StepResult.FAIL);
//						}
//							
					}else
						{	report.addReportStep("Validate Rejected Carriers Screen", 
								"Rejected Carriers Screen did not open successfully", StepResult.FAIL);
						}
			}else
				{
				report.addReportStep("Click on Rejected Carriers link", 
						"Did not click on Rejected Carriers link", StepResult.FAIL);
				}		
			}
		
		public void rejectFilter() throws Exception
		{
			
		}
}