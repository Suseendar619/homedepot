package com.homer.po.DFWMS;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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

import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;
import com.opera.core.systems.scope.protos.UmsProtos.Command;

public class TPEShipmentsPageObject extends PageBase{

	public TPEShipmentsPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By Tpepage=By.xpath(".//span[contains(@id,'mps_solutions_menu')]");
	static final By LoadingPage=By.className("x-mask-msg-text");
	static final By MenuBtn=By.xpath(".//span[contains(@class,'wt-topbar-menu-icon') and contains(@id, 'button')]");
	static final By SearchInput=By.xpath(".//input[contains(@id,'mps_menusearch')]");
	static final By LoadingFrame=By.xpath(".//div[contains(@id,'loadmask')]");
	static final By MaximizeScreen=By.xpath(".//img[contains(@class,'x-tool-maximize')]");
	static final By ScreenHeader=By.xpath(".//span[contains(@id,'header_hd-textEl')]");
	static final By CloseScreen=By.xpath(".//img[@class='x-tool-img x-tool-close']");
	static final By ShipmentScreenHeader=By.xpath(".//span[contains(text(),'Shipments') and contains(@id,'screen')]");
	//static final By ShipmentScreenHeader=By.xpath(".//span[contains(text(),'Shipments') and contains(@id,'screen')]");
	static final By PrimaryFieldFilter=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[3]");
	static final By PurchaseOrderFilterBtn=By.id("ext-gen2549");
	static final By PurchaseOrderOption=By.xpath(".//div[contains(@class,'filter-combo-option') and contains(text(),'Purchase Order')]");
	//static final By PurchaseOrderInput=By.xpath(".//input[contains(@id, 'mps-lookupfield')]");
	static final By TCshipmentID=By.xpath(".//table[contains(@class,'x-form-fieldcontainer')]//input");

	static final By ShipmentIDApply=By.xpath(".//span[contains(text(), 'Apply') and contains(@id,'button')]");
	static final By ShipmentApplied=By.xpath("//table[starts-with(@id,'gridview-')]/tbody/tr/td[4]");
	static final By ShipmentDisplayed=By.xpath("(.//tr[contains(@id,'gridview')]//td)[4]");

	static final By ResourceSelectionBtn=By.xpath(".//span[contains(text(),'Resource Selection')]");
	static final By TenderDtlsIcon=By.xpath("(.//tr[contains(@id,'gridview')]//td)[18]//div//span//img");
	static final By PromoteMenuOpt=By.xpath(".//span[contains(text(),'Promote') and starts-with(@id,'menuitem')]");
	static final By DemoteMenuOpt=By.xpath(".//span[contains(text(),'Demote') and starts-with(@id,'menuitem')]");
	static final By TenderMenuOpt=By.xpath(".//span[contains(text(),'Tender') and starts-with(@id,'menuitem')]");
	static final By UnassignMenuOpt=By.xpath(".//span[contains(text(),'Unassign') and starts-with(@id,'menuitem')]");
	static final By AcceptMenuOpt=By.xpath(".//span[contains(text(),'Accept') and starts-with(@id,'menuitem')]");
	static final By RecallMenuOpt=By.xpath(".//span[contains(text(),'Recall') and starts-with(@id,'menuitem')]");
	static final By RejectMenuOpt=By.xpath(".//span[contains(text(),'Reject') and starts-with(@id,'menuitem')]");
	static final By ShipMentBody=By.id("shipmentList_wrapper_body");

	static final By PromoteShipment=By.xpath(".//button[contains(text(),'Promote')]");
	static final By DemoteShipment=By.xpath(".//button[contains(text(),'Demote')]");
	static final By PromoteShipmentScreenHeader=By.xpath(".//span[contains(text(),'Promote - Promote Shipment')]");
	static final By RecallShipment=By.xpath(".//button[contains(text(),'Recall')]");
	static final By ConfirmBtn=By.xpath(".//span[contains(text(),'Yes')]");
	static final By PromoteOk=By.xpath("//*[@id='maincon']/table/tbody/tr/td/button");
	static final By DemoteOk=By.xpath("//*[@id='maincon']/table/tbody/tr/td/button");
	static final By DemoteCancel=By.xpath(".//button[contains(text(),'Cancel')]");
	
	static final By ManualPlanningBtn=By.xpath(".//span[contains(text(),'Manual Planning')]");
	static final By MovetoMCWMenuOpt=By.xpath(".//span[contains(text(),'Move to MCW') and starts-with(@id,'menuitem')]");

	static final By StatusShipment=By.xpath("(.//tr[contains(@id,'gridview')]//td)[5]");
	static final By CarrierShipment=By.xpath("(.//tr[contains(@id,'gridview')]//td)[6]");
	static final By StatusShipment1=By.xpath("(.//*[contains(@id,'gridview')]/td[5]/div)[1]");
	
	static final By Shipmentdetailpopup=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment')]/../../../../../..//div[8]//img[1]");
	static final By StatusShipment2=By.xpath("(//SPAN[contains(text(),'Shipments')]/../../../../../..//td[5]//DIV[@class='x-grid-cell-inner '])[1]");
	static final By StatusShipment3=By.xpath("(//SPAN[contains(text(),'Shipments')]/../../../../../..//td[5]//DIV[@class='x-grid-cell-inner '])[2]");

	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizeShipments=By.xpath(".//span[contains(text(),'Shipments')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizePromote=By.xpath(".//span[contains(text(),'Promote - Promote Shipment')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By CloseShipments=By.xpath(".//span[contains(text(),'Shipments')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	
	static final By ConsolidationWorkspaceWindow=By.xpath(".//span[contains(text(),'Consolidation Workspace')]");
	static final By CloseConsolidationWorkspace=By.xpath(".//span[contains(text(),'Consolidation Workspace')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By RefreshConsolidationWorkspace=By.xpath(".//span[contains(text(),'Consolidation Workspace')]/../following-sibling::div//img[contains(@class,'x-tool-refresh')]");
	static final By ShipmentIdLink=By.xpath("(.//td[contains(@class,'shipment_id_link')])[1]");
	static final By MovetoMCWScreenHeader=By.xpath(".//span[contains(text(),'Move to MCW - Edit Draft Shipment Details') and contains(@id,'screen')]");
	
	static final By StopListDrpDwn = By.xpath(".//select[@name='stopseq']");
	static final By MCWStopListID = By.cssSelector(".listdata>select[name=stopseq]>option[selected= '']");
	static final By MCWDestinationListID = By.xpath(".//*[@name='stopseq']/../following-sibling::td[1][contains(@class,'listdata')]");
	
	static final By SHIPMENTDETAIL = By.xpath("//iframe[contains(@src,'ShipmentDetail.xhtml')]");
	static final By CANCELSHIPMENT = By.xpath("//iframe[contains(@src,'ProcessAggregateResponseNew.jsp')]");
	static final By RECALLSHIPMENT = By.xpath("//iframe[contains(@src,'AggregateConfirmation.jsp')]");	
//	static final By drop2=By.xpath(".//*[@id='2']/td[2]/select");
//	static final By drop3=By.xpath(".//*[@id='2']/td[3]/select");
	
	//static final By RefreshShipment =By.xpath("(.//tr[contains(@id,'button-1120-btnIconEl')])");
	static final By RefreshShipment =By.xpath("(.//div[contains(text(),'Displaying')]/preceding-sibling::a)[5]");
	static final By RefreshShipmentStatus =By.xpath("(.//div[contains(text(),'Displaying')]/preceding-sibling::a)[10]");
	static final By ShipmentDetailStatus =By.id("dataForm:ShpDtl_Out_Stat");
	static final By MaximizeShipmentDetail=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By ShipmentDetailsScreenHeader=By.xpath(".//span[contains(text(),'Shipment Detail - Shipment') and contains(@id,'screen')]");
	
	static final By ShipmentCheckBox=By.xpath(".//span[contains (text(),'Consolidation Run Id')]/../../preceding-sibling::div[1]/div[1]/span");
	static final By CheckBox1=By.xpath("(//SPAN[contains(text(),'Shipments')]/../../../../../..//DIV[@class='x-grid-row-checker'])[1]");
	static final By CheckBox2=By.xpath("(//SPAN[contains(text(),'Shipments')]/../../../../../..//DIV[@class='x-grid-row-checker'])[2]");	
	
	//Create Appointment page
	static final By CreateAppointment = By.xpath("//a[contains(.,'Create Appointment')]");	
	public static final By appointmenttime = By.xpath(".//a[contains(@href,'showAppointment')]");
	static final By appointmentslotsselect = By.id("dataForm:selectBtn");
	static final By AppointmentSlotRadio = By.xpath("(.//*[@id='checkAll_c_dataForm:recommendationTable'])[2]");
	static final By AppointmentSlotsTime= By.xpath(".//*[@id='dataForm:recommendationTable:1:recommendationSlotDesc']");
	static final By appointmentsavebtn = By.xpath(".//*[@value='Save' and @type='button']");
	static final By appointwindwclose = By.xpath("(//*[contains(.,'Appointment')]//following::img[contains(@class,'close')])[5]"); 
	
	//Shipment Tender Details page
	static final By ShipmentTenderScreen=By.xpath(".//span[contains(text(),'Shipment Tender')]");
	static final By OverrideWarningsScreen=By.xpath(".//span[contains(text(),'Override Warnings')]");
	static final By ShipmentTenderForm = By.xpath(".//form[contains(@action,'processTenderDetails')]");
	static final By ShipmentTender_Frame = By.xpath("//*[contains(@src, 'shipmentTender.jsp')]");
	static final By CarrierAssign_Frame = By.xpath("//*[contains(@src, 'shipmentAssignTP.jsp')]");
	static final By ShipmentTender_CarrierPopup = By.xpath("//div[@id='infoDiv_t']");
	static final By CarrierPopup_CodeText = By.xpath(".//td[(@class='detaildata')]//input[@id = 'assignedTPString']");
	static final By CarrierPopup_AssignBtn = By.xpath(".//button[contains(@onclick,'assign') and contains(@class,'btn')]");
	static final By CarrierOverride_Warning = By.xpath("//td[@class = 'listdata']//select[@name='overrideCode1']/../preceding-sibling::td");
	static final By CarrierOverride_Dropdown = By.xpath("//td[@class = 'listdata']//select[@name='overrideCode1']");
	static final By CarrierOverride_SaveBtn = By.xpath(".//td//button[(@class = 'btn') and contains(text(),'Save')]");
	static final By RefreshShipmentTender =By.xpath(".//img[contains(@class,'x-tool-refresh')]");
	static final By CloseShipmentTender=By.xpath(".//span[contains(text(),'Shipment Tender')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By ResetOption =By.xpath(".//a[contains(text(),'Reset') and (@onclick = 'restartResourceSelectionButton()')]");
	static final By ManualResourceSelection =By.xpath(".//a[contains(text(),'Manual Resource Selection') and (@onclick = 'moveToBaseEndButton()')]");
	static final By ManualAssignOption =By.xpath(".//a[contains(text(),'Manual Assign') and (@onclick = 'manualAssignButton()')]");	
	static final By PickupLabel = By.xpath(".//td[contains(@class,'detaillabel') and contains(text(),'Pickup')]");
	//static final By PickupLabel = By.xpath(".//*[@id='workareadiv']");
	static final By RSConfigCycleLink = By.xpath(".//td[contains(text(),'RS Config./Cycle')]/following-sibling::td//a[contains(@class,'datalink')]");
	static final By RSConfigCycleValue = By.xpath(".//td[contains(text(),'RS Config./Cycle')]/following-sibling::td[contains(@class,'detaildata')][1]");
	static final By AssignedCarrier = By.xpath("//td//b[text()='Assigned']/../following-sibling::td//a[contains(@href,'Carrier Details')]");
	
	
	static final By CancelMenuOpt=By.xpath(".//span[contains(text(),'Cancel') and starts-with(@id,'menuitem')]");
	static final By CancelDO=By.xpath(".//*[@id='CancelDO']");
	static final By DeleteRTS=By.xpath(".//*[@id='DeleteRTS']");
	static final By Save=By.xpath(".//*[contains(text(),'Save')]");
	static final By Cancelled=By.xpath(".//*[@id='dataForm:ShpDtlGen_SOM_Canceled']");
	
	// MCW details Page
	static final By newstop2 = By.xpath(".//*[@id='2']/td[2]/select");
	static final By newstop3 = By.xpath(".//*[@id='3']/td[2]/select");
		
	static final By validate = By.xpath(".//button[contains(text(),'Validate')]");
	static final By saveAccept = By.xpath(".//button[contains(text(),'Save and Accept')]");
	
	RunTemplatePageObject trun;

	ArrayList<String> MCWStopList  = new ArrayList<String>();
	ArrayList<String> MCWDestinationList = new ArrayList<String>();
	static HashMap<String, String> MCWCombinehmap = new HashMap<String, String>();
	
	StreamResult streamResult;
	String primaryfield=null,shipmentid;
	public static String carriershipment;
	String optcarrier;
	String AppointmentSlot;
	public static String shipmentIds;	
	static WebElement buttonselect;
	
	List<String> StopSequenceList = new ArrayList<String>();



	/**
	 * Method to validate Shipment Id search using Shipment ID from workspace
	 * @throws Exception
	 */
	public void shipmentIdSearch(String primaryfieldinput, String shipmentid) throws Exception{	

		try{
			
			if(shipmentid.equalsIgnoreCase("") || shipmentid == null){		
				 throw new Exception ("No value given for Shipment ID. Actual value: [" + shipmentid + "]");		
			}
			
			wh.waitUntilDisappear(LoadingFrame);		

			wh.clickElement(MaximizeShipments);

			// Wait for screen header
			if(!wh.isElementPresent(ShipmentScreenHeader, 10)){
				throw new Exception ("Shipment Screen Header is not visible.");
			}
			
			// Check for correct header title
			String headerTitle = "Shipments";
			String actualHeader = wh.getText(ShipmentScreenHeader);
			
			if(!actualHeader.equalsIgnoreCase(headerTitle)){
				throw new Exception ("Shipment Screen Header does not contain the appropriate header [" + headerTitle + "]. "
						+ "Actual title: [" + actualHeader + "].");
			}
			
			// Clear value in primary field
			//primaryfield=dataTable.getData(DataColumn.PrimaryFieldInstanceTwo);
			wh.clearElement(PrimaryFieldFilter);
			if(primaryfieldinput == null || primaryfieldinput.equalsIgnoreCase("")){
				throw new Exception ("No value given for Primary Field. Actual value: [" + primaryfieldinput + "]");
			}
			
			// Fill in Primary Field input on form
			wh.sendKeys(PrimaryFieldFilter, primaryfieldinput);
			
			//wh.clickElement();
			if (!wh.isElementPresent(TCshipmentID)){
				throw new Exception ("Unable to find the Shipment ID input field");
			}
			wh.focusElement(TCshipmentID);
			wh.clearElement(TCshipmentID);
			Thread.sleep(2000);
			wh.sendKeys(TCshipmentID, shipmentid);	
			//driver.findElement(By.xpath(".//table[contains(@class,'x-form-fieldcontainer')]//input")).sendKeys("123546");
			Thread.sleep(8000);
			rc.logger.info("Shipment Id : "+shipmentid);

			if(!wh.getAttribute(TCshipmentID, "value").equalsIgnoreCase(shipmentid)){
				throw new Exception("Shipment ID value in form does not equal Shipment ID");
			}
			
			report.addReportStep("Enter Primary Fields on Shipments Filter", "Successfully entered Primary Fields."
					+ "Primary Field: [" + primaryfieldinput + "]. Value = [" + shipmentid + "]", StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("Enter Primary Fields on Shipments Filter", 
					"Unable to enter Primary Fields correctly on Shipments Filter. " + e.getMessage() , 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	/**
	 * Method to validate shipment is applied
	 * @throws Exception
	 */
	public void shipmentIdApplied() throws Exception{
		if(wh.isElementPresent(ShipmentIDApply, 5)){
			wh.clickElement(ShipmentIDApply);
			Thread.sleep(10000);
			if(wh.isElementPresent(ShipmentApplied, 5)){
				String shipmentId=wh.getText(ShipmentApplied);

				rc.logger.info("Filtered by Shipment Id :"+shipmentId);
				System.out.println(shipmentId);				
				report.addReportStep("Click on Apply and verify shipment id", 
						"Successfully applied and verified shipment id: "+shipmentId, StepResult.PASS);
			}else{
				report.addReportStep("Click on Apply and verify shipment id", 
						"Not applied as expected", StepResult.FAIL);
			}
		}else{
			rc.terminateTestCase("Shipment");
		}
	}

	/**
	 * Method to shipment check box in grid
	 * @throws Exception
	 */
	public void shipmentIDCheckbox(String shipmentId) throws Exception{	
		String stepName= "Select the checkbox for shipment ID: " + shipmentId;
		try{
			if(wh.isElementPresent(ShipmentDisplayed, 10)){
				String shipmentdisplay = wh.getText(ShipmentDisplayed);
				//rc.logWriter("DO number displayed as : "+dodisplay);
				rc.logger.info("Shipment id displayed as : "+shipmentdisplay);
				WebElement shipmentcheckbox=driver.findElement(By.xpath(".//div[contains(text(),'"+shipmentId+"')]/../preceding-sibling::td[3]//div[@class='x-grid-row-checker']"));
				if(shipmentdisplay.equalsIgnoreCase(shipmentId)){
					if(!(shipmentcheckbox.isSelected())){								
						report.addReportStep("Verify Shipment ID checkbox is displayed", 
								"Shipment ID checkbox is available", StepResult.PASS);

						wh.clickElement(shipmentcheckbox);

						report.addReportStep("Click on Shipment id checkbox", 
								"Successfully clicked on Shipment id checkbox", StepResult.PASS);

					}else{
						throw new Exception ("Shipment ID checkbox is not available");
					}
				}
				}else{
					rc.terminateTestCase("Shipment", StepResult.FAIL);
				}
		}catch(Exception e){
			rc.addReportStep(stepName, "Could not select the checkbox. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	/**
	 * Method to select Tender Details Icon in grid resource selection button
	 * @throws Exception
	 */
	public void tenderDetailsSelection_Icon() throws Exception{
		
		if(wh.isElementPresent(TenderDtlsIcon, 5)){
			
			wh.clickElement(TenderDtlsIcon);

			driver.switchTo().defaultContent();
			if(wh.isElementPresent(ShipmentTenderScreen, 5)){
				report.addReportStep("Validate Shipment Tender Screen opens after clicking Tender Details Icon", 
						"Shipment Tender Screen opened successfully", StepResult.PASS);
			}else
			{	
				rc.terminateTestCase("Validate Shipment Tender Screen ", StepResult.FAIL);
			}
				
		}else{
			rc.terminateTestCase("Tender Details Icon", StepResult.FAIL);
		}
	}
	

	/**
	 * Method to validate resource selection button
	 * @throws Exception
	 */
	public void resourceSelectionBtn() throws Exception{
		System.out.println("Shipment Status before Promotion: "+ wh.getText(StatusShipment));
		if(wh.isElementPresent(ResourceSelectionBtn, 4)){
			
			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(PromoteMenuOpt, 4)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(PromoteMenuOpt);
			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}
		}
	}
	public void cancelshipments() throws Exception{
		System.out.println("in cancelshipments");
		driver.switchTo().defaultContent();
		
			if(wh.isElementPresent(TPEDistributionOrderPageObject.ManualPlaningShip, 7)){
				
				wh.clickElement(TPEDistributionOrderPageObject.ManualPlaningShip);
											
				if(wh.isElementPresent(CancelMenuOpt, 3)){				
					report.addReportStep("Click on Manual Planing -Cancel option", 
							"Successfully clicked on Manual Planing - Cancel option", StepResult.PASS);				
					wh.clickElement(CancelMenuOpt);
					wh.waitForPageLoaded();
					Thread.sleep(4000);
				}else{			
					rc.terminateTestCase("Distribution Orders", StepResult.FAIL);
				}
			}
		
	}
	public void cancelOrder() throws Exception{
		System.out.println("in cancelOrder");
		//driver.switchTo().frame(1);
		driver.switchTo().frame(driver.findElement(CANCELSHIPMENT));
		
		if (wh.isElementPresent(CancelDO, 7))
		{
			wh.clickElement(CancelDO);
			wh.clickElement(DeleteRTS);
			report.addReportStep("Click Cancel order, Delete RTS ", 
					"Successfully Cancelled the Orders Created ", StepResult.PASS);	
			wh.clickElement(Save);
			wh.waitForPageLoaded();
			Thread.sleep(15000);
			wh.handleAlert();
			report.addReportStep("Click Cancel order, Delete RTS and Save", 
					"Successfully Cancelled the Orders Created and Saved", StepResult.PASS);
			wh.waitUntilDisappear(LoadingFrame);
			Thread.sleep(4000);
			
					
		}
		else
		{
			report.addReportStep("Click Cancel order, Delete RTS and Save", 
					"Not able to Cancel the Orders ", StepResult.WARNING);		
		}
		
		
}
	public void cancelOrderVerify() throws Exception{
		System.out.println("in cancelOrderVerify");
		wh.doubleClickUsingAction(StatusShipment);
		wh.waitForPageLoaded();
		Thread.sleep(4000);
		driver.switchTo().frame(driver.findElement(SHIPMENTDETAIL));
			
		
		if(wh.isElementPresent(Cancelled, 7000))
		{
			String s = wh.getText(Cancelled);
			System.out.println("Cancelled Status :"+s);
			if(s.equalsIgnoreCase("Yes"))
			{
				report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
						"The Cancelled Status is Yes", StepResult.PASS);
				//driver.switchTo().frame(driver.findElement(SHIPMENTDETAIL));
				
				//driver.switchTo().activeElement();
				driver.switchTo().frame(0);				
				if(wh.isElementPresent(Shipmentdetailpopup))
				{
				
				wh.clickElement(Shipmentdetailpopup);
				}				
			}
			else
			{
				driver.switchTo().frame(0);				
				if(wh.isElementPresent(Shipmentdetailpopup))
				{
				
				wh.clickElement(Shipmentdetailpopup);
				}	
				report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
						"Cancelled Status is not as Expected:"+s, StepResult.WARNING);
			}
					
		}
		
	}
	
public void shipmentstatus() throws Exception{
	System.out.println("in shipst ");
		try
		{
			if(wh.isElementPresent(StatusShipment1, 7)){		
				String s = wh.getText(StatusShipment1);
				System.out.println("Stat:"+s);
					if(s.equalsIgnoreCase("Planned") || s.equalsIgnoreCase("Available") || s.equalsIgnoreCase("Assigned") || s.equalsIgnoreCase("Unplanned")){	
						
						RunTemplatePageObject  r = new RunTemplatePageObject(ic);
					    cancelshipments();
						cancelOrder();
						r.shipmentSelect();
						cancelOrderVerify();
						report.addReportStep("Shipment Cancel", 
								"Shipment Cancelled Successfully", StepResult.PASS);
					}
					else if(s.equalsIgnoreCase("Tendered") || s.equalsIgnoreCase("Accepted") )
							{
								RunTemplatePageObject  r = new RunTemplatePageObject(ic);
								resourceSelectionBtn_Recall();
								r.shipmentSelect();
								cancelshipments();
								cancelOrder();
								r.shipmentSelect();
								cancelOrderVerify();
								report.addReportStep("Shipment Cancel", 
										"Shipment Cancelled Successfully", StepResult.PASS);
							}
				
			}
			else
			{ 
				
				System.out.println("Element Not found");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	/**
	 * Method to validate resource selection button and tender option
	 * @throws Exception
	 */
	public void resourceSelectionBtn_Tender() throws Exception{		
		if(wh.isElementPresent(ResourceSelectionBtn, 4)){

			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(TenderMenuOpt, 4)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(TenderMenuOpt);

				if(wh.isElementPresent(ConfirmBtn, 6)){

					wh.clickElement(ConfirmBtn);
					
					// Wait until mask disappears
					Thread.sleep(10000);
					
					report.addReportStep("Click on Tender Option in Resource Selection options", 
							"Succefully clicked on Tender button", StepResult.PASS);
					

				}else{
					rc.terminateTestCase("Tender Option Selection failed ", StepResult.FAIL);
				}


			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}
		}
	}
	
	
	/**
	 * Method to validate resource selection button and tender option
	 * @throws Exception
	 */
	public void resourceSelectionBtn_Unassign() throws Exception{		
		if(wh.isElementPresent(ResourceSelectionBtn, 4)){

			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(UnassignMenuOpt, 4)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(UnassignMenuOpt);

				if(wh.isElementPresent(ConfirmBtn, 6)){

					wh.clickElement(ConfirmBtn);
					
					// Wait until mask disappears
					Thread.sleep(7500);
					
					report.addReportStep("Click on Unassign Option in Resource Selection options", 
							"Successfully clicked on Unassign button", StepResult.PASS);
					
					driver.switchTo().activeElement();				
					if(wh.getText(StatusShipment).equalsIgnoreCase("Available")){
						report.addReportStep("Validate Unassignment", 
								"Shipment successfully Unassigned and status changed back to Available", StepResult.PASS);					
					}else{
						rc.terminateTestCase("Shipment successfully Unassigned", StepResult.FAIL);
					}
					

				}else{
					rc.terminateTestCase("Unassign Option Selection ", StepResult.FAIL);
				}


			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}
		}
	}
	
	
	

	/**
	 * Method to validate resource selection button and accept option
	 * @throws Exception
	 */
	public void resourceSelectionBtn_Accept() throws Exception{		
		if(wh.isElementPresent(ResourceSelectionBtn, 6)){

			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(AcceptMenuOpt, 6)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(AcceptMenuOpt);

				if(wh.isElementPresent(ConfirmBtn, 6)){

					report.addReportStep("Click on Accept Option in Resource Selection options", 
							"Succefully clicked on Accept button", StepResult.PASS);
					wh.clickElement(ConfirmBtn);

				}else{
					rc.terminateTestCase("Accept Option Selection failed ", StepResult.FAIL);
				}


			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}

		}
	}

	/**
	 * Method to validate resource selection button and recall option
	 * @throws Exception
	 */
	public void resourceSelectionBtn_Recall() throws Exception{		
		
		if(wh.isElementPresent(ResourceSelectionBtn, 3)){

			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(RecallMenuOpt, 3)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(RecallMenuOpt);
				
				recallShipment();

				}else{
					rc.terminateTestCase("Recall Option Selection failed ", StepResult.FAIL);
				}


			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}

	}
	
	
	/**
	 * Method to validate resource selection button and reject option
	 * @throws Exception
	 */
	public void resourceSelectionBtn_Reject() throws Exception{		
		if(wh.isElementPresent(ResourceSelectionBtn, 3)){

			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(RejectMenuOpt, 3)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(RejectMenuOpt);
				
				if(wh.isElementPresent(ConfirmBtn, 6)){

					report.addReportStep("Click on Reject Option in Resource Selection options", 
							"Succefully clicked on Reject button", StepResult.PASS);
					wh.clickElement(ConfirmBtn);

				}else{
					rc.terminateTestCase("Reject Option Selection failed ", StepResult.FAIL);
				}

			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}
			
		}

	}


	/**
	 * Method to validate resource selection button and move to MCW option
	 * @throws Exception
	 */
	public void manualPlanningBtn_movetoMCW() throws Exception{		
		if(wh.isElementPresent(ManualPlanningBtn, 4)){

			wh.clickElement(ManualPlanningBtn);

			if(wh.isElementPresent(MovetoMCWMenuOpt, 4)){				
				report.addReportStep("Click on Manual Planning button", 
						"Successfully clicked on Manual Planning button", StepResult.PASS);				
				wh.clickElement(MovetoMCWMenuOpt);

					if(wh.isElementPresent(ConsolidationWorkspaceWindow, 10)){
	
						report.addReportStep("Select Move to MCW option to open Consoldiation Workspace", 
								"Successfully clicked on Move to MCW option to open Consoldiation Workspace", StepResult.PASS);
						
					}else{
						report.addReportStep("Select Move to MCW option to open Consoldiation Workspace", 
								"Click on Move to MCW option to open Consoldiation Workspace was unsuccessful", StepResult.FAIL);
					}
				}else{
					rc.terminateTestCase("Move to MCW option", StepResult.FAIL);
				}


			}else{			
				rc.terminateTestCase("Shipment Manual Planning", StepResult.FAIL);
			}
		}

	
	
	/**
	 * Method to promote shipment
	 * @throws Exception
	 */
	public void promoteShipment() throws Exception{
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();

		if(wh.isElementPresent(PromoteShipment, 3)){
			report.addReportStep("Verify Promote button is available in Promote Shipment screen", 
					"Promote button is available in Promote Shipment screen", StepResult.PASS);
			wh.clickElement(PromoteShipment);		
			if(wh.isElementPresent(StatusShipment, 3)){	
				report.addReportStep("Click on Promote button", 
						"Successfully navigated back to Shipment screen after clicking on Promote button", StepResult.PASS);
				Thread.sleep(6000);
				driver.switchTo().activeElement();				
				if(wh.getText(StatusShipment).equalsIgnoreCase("Planned")){	
					//rc.terminateTestCase("Promotion of Shipment", StepResult.FAIL);
					report.addReportStep("Promotion of Shipment", 
							"Promotion of Shipment", StepResult.WARNING);
				}else{
					report.addReportStep("Validate Promotion", 
							"Shipment successfully Promoted", StepResult.PASS);
				}
					
			}else{
				rc.terminateTestCase("Shipment screen after clicking on Promote button", StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Promote Button in Promote Shipments screen", StepResult.FAIL);
		}		
	}
	public void promoteShipmentAfterEngRun() throws Exception{
		
		
		if(wh.isElementPresent(PromoteShipmentScreenHeader, 4))
		{
			wh.clickElement(MaximizePromote);
			Thread.sleep(1000);
			String s = wh.getText(PromoteShipmentScreenHeader);
			if(s.equalsIgnoreCase("Promote - Promote Shipment"))
			{
			report.addReportStep("Checking For Promote Screen", 
					"Promote Screen is available", StepResult.PASS);
			}
			else
			{
				report.addReportStep("Checking For Promote Screen", 
						"Promote Screen is Not available", StepResult.FAIL);
			}
			driver.switchTo().frame(1);
			driver.switchTo().activeElement();
		if(wh.isElementPresent(PromoteShipment, 3)){
			report.addReportStep("Verify Promote button is available in Promote Shipment screen", 
					"Promote button is available in Promote Shipment screen", StepResult.PASS);
			
			wh.clickElement(PromoteShipment);
			
			Thread.sleep(6000);
			driver.switchTo().activeElement();
			wh.clickElement(RefreshShipmentStatus);
			Thread.sleep(6000);
			//driver.switchTo().activeElement();
			wh.clickElement(RefreshShipmentStatus);
			Thread.sleep(6000);
			//driver.switchTo().activeElement();
			wh.clickElement(RefreshShipmentStatus);		
			
			
			/*if(wh.isElementPresent(StatusShipment3, 2)){
			
			//if(wh.getText(StatusShipment3).equalsIgnoreCase("Assigned")||wh.getText(StatusShipment3).equalsIgnoreCase("Avalilable"))
				System.out.println("enter 1");
				for(int i=2;i<=5;i++)
				{
					if(wh.getText(StatusShipment3).equalsIgnoreCase("Assigned")||wh.getText(StatusShipment3).equalsIgnoreCase("Avalilable"))
					{
				
							System.out.println("Inside refresh");
							wh.clickElement(RefreshShipmentStatus);
							//wh.waitForPageLoaded();
							Thread.sleep(3000);
							break;
					}
				}	
				
				//System.out.println("Iterator:"+s1);
			}*/		
			
			report.addReportStep("Status refresh to verify status details ", "Clicked on referesh button to move Assigned status", StepResult.PASS);
			
			
			//shipmentstatus1();
									
			if(wh.isElementPresent(PromoteOk)){
				
				report.addReportStep("Validate Promotion", 
						"Shipment Status after Promotion", StepResult.PASS);
				wh.clickElement(PromoteOk);
				report.addReportStep("Validate Promotion", 
						"Shipment already Promoted", StepResult.PASS);
				driver.switchTo().activeElement();
				wh.clickElement(ShipmentCheckBox);
				
				Thread.sleep(6000);
				driver.switchTo().activeElement();
				wh.clickElement(RefreshShipmentStatus);
				Thread.sleep(6000);
				//driver.switchTo().activeElement();
				wh.clickElement(RefreshShipmentStatus);
				Thread.sleep(6000);
				//driver.switchTo().activeElement();
				wh.clickElement(RefreshShipmentStatus);	
				
				report.addReportStep("Shipment Status", 
						"Status Refreshed to Assigned", StepResult.PASS);
				
				//shipmentstatus1();
				
			}			
			
			if(wh.isElementPresent(StatusShipment, 3)){	
				report.addReportStep("Click on Promote button", 
						"Successfully navigated back to Shipment screen after clicking on Promote button", StepResult.PASS);
				Thread.sleep(6000);
				driver.switchTo().activeElement();
			}
				
			if(wh.getText(StatusShipment).equalsIgnoreCase("Planned")){	
					//rc.terminateTestCase("Promotion of Shipment", StepResult.FAIL);
					}else
					{
					report.addReportStep("Validate Promotion", 
							"Shipment successfully Promoted", StepResult.PASS);
					}
			
						
			}else{
				rc.terminateTestCase("Shipment screen after clicking on Promote button", StepResult.FAIL);
				}
	}else{
			rc.terminateTestCase("Promote Button in Promote Shipments screen", StepResult.FAIL);
		}	
		}


	
public void promoteShipmentAfterEngRunFleet() throws Exception{
		
		
		if(wh.isElementPresent(PromoteShipmentScreenHeader, 4))
		{
			wh.clickElement(MaximizePromote);
			Thread.sleep(1000);
			String s = wh.getText(PromoteShipmentScreenHeader);
			if(s.equalsIgnoreCase("Promote - Promote Shipment"))
			{
			report.addReportStep("Checking For Promote Screen", 
					"Promote Screen is available", StepResult.PASS);
			}
			else
			{
				report.addReportStep("Checking For Promote Screen", 
						"Promote Screen is Not available", StepResult.FAIL);
			}
			driver.switchTo().frame(1);
			driver.switchTo().activeElement();
		if(wh.isElementPresent(PromoteShipment, 3)){
			report.addReportStep("Verify Promote button is available in Promote Shipment screen", 
					"Promote button is available in Promote Shipment screen", StepResult.PASS);
			
			wh.clickElement(PromoteShipment);
			
												
			if(wh.isElementPresent(PromoteOk)){
				
				report.addReportStep("Validate Promotion", 
						"Shipment Status after Promotion", StepResult.PASS);
				wh.clickElement(PromoteOk);
				report.addReportStep("Validate Promotion", 
						"Shipment already Promoted", StepResult.PASS);
				driver.switchTo().activeElement();
				wh.clickElement(ShipmentCheckBox);
				
				}			
			
			if(wh.isElementPresent(StatusShipment, 3)){	
				report.addReportStep("Click on Promote button", 
						"Successfully navigated back to Shipment screen after clicking on Promote button", StepResult.PASS);
				Thread.sleep(6000);
				driver.switchTo().activeElement();
			}
				
			if(wh.getText(StatusShipment).equalsIgnoreCase("Planned")){	
					//rc.terminateTestCase("Promotion of Shipment", StepResult.FAIL);
					}else
					{
					report.addReportStep("Validate Promotion", 
							"Shipment successfully Promoted", StepResult.PASS);
					}
			
						
			}else{
				rc.terminateTestCase("Shipment screen after clicking on Promote button", StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Promote Button in Promote Shipments screen", StepResult.FAIL);
		}	
		}
	
	/**
	 * Method to validate and click on recall shipment
	 * @throws Exception
	 */
	public void recallShipment() throws Exception{
		//driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(RECALLSHIPMENT));
		driver.switchTo().activeElement();

		if(wh.isElementPresent(RecallShipment, 3)){
			report.addReportStep("Verify Recall button is available in Recall Shipments screen", 
					"Recall button is available in Recall Shipments screen", StepResult.PASS);
			wh.clickElement(RecallShipment);		
			if(wh.isElementPresent(StatusShipment, 3)){	
				report.addReportStep("Click on Recall button", 
						"Successfully navigated back to Shipment screen after clicking on Recall button", StepResult.PASS);
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				wh.waitUntilDisappear(LoadingFrame);
				if(wh.getText(StatusShipment1).equalsIgnoreCase("Available")|| wh.getText(StatusShipment2).equalsIgnoreCase("Available")|| wh.getText(StatusShipment3).equalsIgnoreCase("Available")){
					report.addReportStep("Validate Recall", 
							"Shipment successfully Recalled and status changed back to Available", StepResult.PASS);					
				}else{
					rc.terminateTestCase("Shipment successfully Recalled", StepResult.FAIL);
				}
					
			}else{
				rc.terminateTestCase("Did not navigate back to Shipment screen after clicking on Recall button", StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Recall Button absent in Recall Shipments screen", StepResult.FAIL);
		}		
	}

	/**
	 * Method to validate shipment status check
	 * @throws Exception
	 * @param status
	 */
	public void shipmentStatusCheck(String status) throws Exception{
		if(wh.isElementPresent(StatusShipment, 3)){	
								
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
            while ( System.nanoTime() < endTime ){
            	//driver.switchTo().defaultContent();
            	Thread.sleep(8000);            	
            	shipmentStatusRefreshButton();           	
                Thread.sleep(3000);
                //driver.switchTo().frame(0);
                
                String statusshipment=wh.getText(StatusShipment);
                carriershipment = wh.getText(CarrierShipment);
            	System.out.println("#### Status to be checked : "+status);
    			System.out.println("**** Current Status: "+statusshipment);
    			System.out.println("Assigned Carrier: "+carriershipment);

                
                if(statusshipment.equalsIgnoreCase(status)){
                	rc.logger.info("Shipment status to be checked: "+ status);
                	rc.logger.info("Shipment status in System: "	+ statusshipment);
                	
                	report.addReportStep("Verify Status is promoted as "  +status, 
                			"Status is promoted as " +statusshipment, StepResult.PASS);
                	break;
                }
//                else if (!statusshipment.equalsIgnoreCase(status)){
//                	shipmentIDCheckbox();
//                	resourceSelectionBtn_Tender();
//                	Thread.sleep(6000);
//                	shipmentIDCheckbox();
//                	resourceSelectionBtn_Accept();
//                	break;
//                }
        	}
            
            String statusshipment1=wh.getText(StatusShipment);
            if (!statusshipment1.equalsIgnoreCase(status)){
            	rc.logger.info("Shipment status to be checked: "+ status);
            	rc.logger.info("Shipment status in System: "	+ statusshipment1);
            	
            	report.addReportStep("Verify Status is promoted as "  +status, 
            			"Status is promoted as " +statusshipment1, StepResult.FAIL);
            
            }
          
		}
		  else
          {
			rc.terminateTestCase("Shipment Status not available", StepResult.FAIL);
         }
	}

	/**
	 * Method to refresh shipment
	 * @throws Exception
	 */
	public void shipmentStatusRefreshButton() throws Exception{
		wh.clickElement(RefreshShipment);
		Thread.sleep(2000);
	}
	
	
	
	/**
	 * Method to double click shipment status grid
	 * @throws Exception
	 * @param status
	 */
	public void doubleClickShipment()throws Exception{
		driver.switchTo().defaultContent();
		if(wh.isElementPresent(StatusShipment, 3)){
			
			/*wh.clickElement(By.xpath(".//tr[contains(@id,'gridview')]//td[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("(.//tr[contains(@id,'gridview')]//td)[5]"))).doubleClick().perform();*/
			//wh.doubleClickUsingAction(StatusShipment);
			
	wh.doubleClickUsingAction(StatusShipment);	
			
			if(wh.isElementPresent(ShipmentDetailsScreenHeader, 3)){
				report.addReportStep("Verify Shipment Details Screen opens", 
						"Shipment Details Screen opens successfully", StepResult.PASS);
				//wh.clickElement(CloseShipments);	
			}else
				report.addReportStep("Verify Shipment Details Screen opens", 
						"Shipment Details Screen failed to open", StepResult.FAIL);
								
		}
			//wh.clickElement(CloseShipments);						
		
	}
	
	/**
	 * Method to double click shipment id link in Consolidation Workspace and open MCW Screen
	 * @throws Exception
	 */
	public void doubleClickShipmentID()throws Exception{
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		
		
		if(wh.isElementPresent(TPEDistributionOrderPageObject.ShipmentIdLink, 5)){			

			wh.doubleClickUsingAction(TPEDistributionOrderPageObject.ShipmentIdLink);
			
			driver.switchTo().defaultContent();			
			Thread.sleep(2000);
			
			if(wh.isElementPresent(MovetoMCWScreenHeader, 5)){
				report.addReportStep("Verify Move to MCW - Edit Draft Shipment Details Screen opens", 
						"Move to MCW - Edit Draft Shipment Details Screen opens successfully", StepResult.PASS);

			}else
				report.addReportStep("Verify Move to MCW - Edit Draft Shipment Details Screen opens", 
						"Move to MCW - Edit Draft Shipment Details Screen failed to open", StepResult.FAIL);
								
		}else
			rc.terminateTestCase("Shipment ID link ", StepResult.FAIL);						
		
	}
	


	public void moveToStatus(String status, String shipmentId, String carrier) throws Exception{
		if(!wh.isElementPresent(StatusShipment, 3)){	
			throw new Exception("Unable to find status for shipment");
		}
		
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
		Thread.sleep(5000);
		shipmentStatusRefreshButton();           	
        Thread.sleep(5000);
		
        // Get status
		String actualStatus = wh.getText(StatusShipment);

		while(!status.equals(actualStatus) && System.nanoTime() < endTime){
			
        	System.out.println("#### Status to be checked : "+status);
			System.out.println("**** Current Status: "+actualStatus);
			
			
			
			if (actualStatus.equals("Available")){
				// Move to 'Assigned'
				shipmentIDCheckbox(shipmentId);
				tenderDetailsSelection_Icon();
				resetShipmentTender_Screen();
				shipmentTender_manualAssignCarrier(carrier);
				
			}else if(actualStatus.equals("Assigned")){
				// Move to 'Accepted'
				shipmentIDCheckbox(shipmentId);
				resourceSelectionBtn_Tender();
				
			}else if(actualStatus.equals("Tendered")){
				// Move to 'Accepted'
				shipmentIDCheckbox(shipmentId);
				resourceSelectionBtn_Accept();
			}
			
			shipmentStatusRefreshButton();           	
            Thread.sleep(5000);
            actualStatus = wh.getText(StatusShipment);
		}
		
		 String statusshipment1=wh.getText(StatusShipment);
         if (!statusshipment1.equalsIgnoreCase(status)){
         	rc.logger.info("Expected Shipment Status: "+ status);
         	rc.logger.info("Actual Shipment Status: "	+ statusshipment1);
         	
         	report.addReportStep("Verify Status is promoted as "  +status, 
         			"Unable to move to correct shipment status. Expected Shipment Status: "+ status +
         			"; Actual Shipment Status: "	+ statusshipment1, StepResult.FAIL);
         
         }
		
		report.addReportStep("Move to [" + status + "] status", 
				"Moved to correct shipment status. Expected Shipment Status: "+ status +
     			"; Actual Shipment Status: "	+ statusshipment1, StepResult.PASS);
	}
	
	/**
	 * Method to change delivery sequence in MCW page
	 * @throws Exception
	 */
	public void changeDeliverySequence()throws Exception{
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		
		if(wh.isElementPresent(newstop2,4000))
		{
			wh.clickElement(newstop2);
			wh.selectValue(newstop2, "3");
			Thread.sleep(1000);
			report.addReportStep("Changing the New stop", 
					"Changed the New stop from 2 to 3", StepResult.PASS);
		}else
		{
			report.addReportStep("Changing the New stop", 
					"Not able to change the New stop from 2 to 3", StepResult.FAIL);
			rc.terminateTestCase("Stop Sequence dropdown", StepResult.FAIL);	
		}
			if(wh.isElementPresent(newstop3,4000))
			{
				wh.clickElement(newstop3);
				wh.selectValue(newstop3, "2");
				Thread.sleep(1000);
				report.addReportStep("Changing the New stop", 
						"Changed the New stop from 3 to 2", StepResult.PASS);
			}
			else
			{
				report.addReportStep("Changing the New stop", 
						"Changed the New stop from 3 to 2", StepResult.FAIL);
			}
	}
	
	/**
	 * Method to select Buttons in MCW Details page
	 * @throws Exception
	 */
	
	public void MovetoMCWB_SelectOption(String SelectBtn) throws Exception{
		if(SelectBtn.equalsIgnoreCase("Validate"))
			{
			buttonselect= driver.findElement(validate);
			System.out.println("Button Select: "+buttonselect);			
			}
		else if
			(SelectBtn.equalsIgnoreCase("Save and Accept"))
			{
			buttonselect= driver.findElement(saveAccept);
			System.out.println("Button Select: "+buttonselect);			
			}
			
			
		
		if(wh.isElementPresent(buttonselect, 7000))
		{
			wh.clickElement(buttonselect);
			report.addReportStep("Click on " + SelectBtn, 
					"Clicked on " + SelectBtn + " Button", StepResult.PASS);
			wh.waitForPageLoaded();
		}
		else
		{
			report.addReportStep("Click on " + SelectBtn, 
					"Not able to Click on "+ SelectBtn + " Button", StepResult.FAIL);
		}
	}
	
	
	/**
	 * Method to validate Stop - Destination combination in MCW Details page 
	 * @throws Exception	
	 */	
	
	public void validateMCWStops() throws Exception{
		
		if(wh.isElementPresent(StopListDrpDwn))
		{
		  		
			//Identify the Stop List		
			TPEShipmentDetailPageObject a = new TPEShipmentDetailPageObject(ic);

			MCWStopList.addAll(a.ListofElements(MCWStopListID));			
			System.out.println("List of MCW Stops: "+MCWStopList);
			
			// Identify the Destination List
			MCWDestinationList.addAll(a.ListofElements(MCWDestinationListID));				
			System.out.println("List of Destinations: "+MCWDestinationList);

			
			//Combine Stop with Destination 
			MCWCombinehmap = a.ListCombination(MCWStopList,MCWDestinationList);
			rc.logger.info("Hmap of combined list is : "+ MCWCombinehmap);
			System.out.println("Hmap of combined list is : "+ MCWCombinehmap);
			
			report.addReportStep("Evaluate the list of Stops versus Destination in MCW screen", 
				"The MCW screen is populated with the following Stop Versus Destination combination: "+MCWCombinehmap, StepResult.PASS);
			
		}else{
			rc.terminateTestCase("Stop List Dropdown",StepResult.FAIL);
		}
		
		
	}

	
	
	/**
	 * Method to create appointment
	 * @throws Exception
	 */
	public void clickCreateAppointmentLink() throws Exception{		
		driver.switchTo().defaultContent();
		
		if(wh.isElementPresent(CreateAppointment, 3)){
			wh.clickElement(CreateAppointment);
			report.addReportStep("Check whether create appointment option is available", 
					"Create appointment option is available and clicked", StepResult.PASS);
		}else
			rc.terminateTestCase("Create appointment option is not available ", StepResult.FAIL);
	}
	
	/**
	 * Method to double click shipment status grid
	 * @throws Exception
	 * @param appttime
	 */
	public void show_appointment() throws Exception{
		
		driver.switchTo().defaultContent();
		wh.clickElement(RefreshShipment);
		
		if(wh.isElementPresent(appointmenttime, 3)){
			String appttime = wh.getText(appointmenttime);
			
			rc.logger.info("Appointment slot saved: "+ appttime);
			System.out.println("Appointment slot saved: "+ appttime);
			
			report.addReportStep("Check whether appointment time is displayed in Shipment Screen", 
					"Appointment time displayed in Shipment Screen: '"+appttime+"'", StepResult.PASS);
			
		}else
			rc.terminateTestCase("Appointment time in Shipment Screen: ", StepResult.FAIL);
		
	}
	
	
		/**
		 * Method to close Consolidation Workspace Window
		 * @throws Exception
		 * @param appttime
		 */
		public void Close_ConsolidationWorkspace() throws Exception{		
	
		driver.switchTo().defaultContent();
		driver.switchTo().activeElement();
		
		if(wh.isElementPresent(ConsolidationWorkspaceWindow,5))
			
		{
			System.out.println("Detected Window");
			wh.clickAndWaitUntilDisappear(ConsolidationWorkspaceWindow, CloseConsolidationWorkspace);
		}
		
	}

/**
	 * Method to shipment check box in grid
	 * @throws Exception
	 */
	public void shipmentIDCheckbox() throws Exception{	
		if(wh.isElementPresent(ShipmentDisplayed, 10)){
		String shipmentdisplay = wh.getText(ShipmentDisplayed);
		//rc.logWriter("DO number displayed as : "+dodisplay);
		rc.logger.info("Shipment id displayed as : "+shipmentdisplay);
		WebElement shipmentcheckbox=driver.findElement(By.xpath(".//div[contains(text(),'"+shipmentid+"')]/../preceding-sibling::td[3]//div[@class='x-grid-row-checker']"));
		if(shipmentdisplay.equalsIgnoreCase(shipmentid)){
			if(!(shipmentcheckbox.isSelected())){								
				report.addReportStep("Verify Shipment ID checkbox is displayed", 
						"Shipment ID checkbox is available", StepResult.PASS);

				wh.clickElement(shipmentcheckbox);

				report.addReportStep("Click on Shipment id checkbox", 
						"Successfully clicked on Shipment id checkbox", StepResult.PASS);

			}else{
				report.addReportStep("Verify Shipment id checkbox is displayed", 
						"Shipment id checkbox is not available", StepResult.FAIL);
			}
		}
		}else{
			rc.terminateTestCase("Shipment", StepResult.FAIL);
		}
	} 

	
	/**
	 * Method to create appointment
	 * @throws Exception
	 */
	public void create_appointment() throws Exception{
		
		driver.switchTo().defaultContent();
		
		if(wh.isElementPresent(CreateAppointment, 3)){
			wh.clickElement(CreateAppointment);
			report.addReportStep("Check whether create appointment option is available", 
					"Create appointment option is available and clicked", StepResult.PASS);
		}else
			rc.terminateTestCase("Create appointment option is not available ", StepResult.FAIL);
		
		driver.switchTo().defaultContent();
		By frameLocator = By.xpath("//*[contains(@src, 'scheduleAppointment.jsflps')]");
		driver.switchTo().frame(wh.getElement(frameLocator));
		
		//if(wh.isElementPresent(appointmentslotsselect, 3)){
			
		if(wh.isElementPresent(AppointmentSlotRadio, 7)){
		wh.clickElement(AppointmentSlotRadio);
			
			AppointmentSlot = wh.getText(AppointmentSlotsTime);
			
			rc.logger.info("Appointment slot selected: "+ AppointmentSlot);
			System.out.println("Appointment slot selected: "+ AppointmentSlot);
			
			wh.clickElement(appointmentslotsselect);			
			report.addReportStep("Check whether appointment slot is selected", 
					"Appointment slot is selected: " + AppointmentSlot, StepResult.PASS);
		}else
			report.addReportStep("Check appointment slot is selected", 
					"Couldn't select appointment slot", StepResult.FAIL);
		
		if(wh.isElementPresent(appointmentsavebtn, 3)){
			wh.clickElement(appointmentsavebtn);
			report.addReportStep("Check whether save button is clicked for appointment", 
					"save button is clicked", StepResult.PASS);
		}else
			report.addReportStep("Check whether save button is clicked for appointment", 
					"save button is not clicked", StepResult.FAIL);
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		wh.clickElement(appointwindwclose); 

	}
	
	
	
	/**
	 * Method to reset Shipment Tender Screen for reassigning carrier
	 * @throws Exception
	 * @param appttime
	 */
	
	
	public void resetShipmentTender_Screen() throws Exception{
		
		driver.switchTo().frame(0);
		
		if(!wh.isElementPresent(RSConfigCycleValue, 3)){	
			throw new Exception("Unable to find RS Config./Cycle link");
		}
		
		String RSConfig = wh.getText(RSConfigCycleValue);		
		System.out.println("RS Config value is " +RSConfig);
		
		//Select Reset option if RS Config./cycle equals "Shipment not in RS"
		if (RSConfig.equals("Shipment not in RS")){
			
			wh.contextClic(PickupLabel);
			
				if (wh.isElementPresent(ResetOption, 2)){
				wh.clickElement(ResetOption);				
				Thread.sleep(2000);		
				wh.handleAlert();	
				
				report.addReportStep("Right Click and select Reset option" , 
				         "Successfully selected Reset Option", StepResult.PASS);
				
				Thread.sleep(3000);
				System.out.println("RS Config value is: "+RSConfig);
				shipmentTenderRefreshButton();
				
				}else{
					throw new Exception("Unable to find Reset option");
				}
			}
			
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
		
		//driver.switchTo().defaultContent();	
		
		while(RSConfig.equals("Shipment not in RS") && System.nanoTime() < endTime){     	
			
			Thread.sleep(5000);
        	shipmentTenderRefreshButton();
        	
            Thread.sleep(3000);
            RSConfig = wh.getText(RSConfigCycleValue);
            System.out.println("RS Config value is: "+RSConfig);
		}
		
		if (!RSConfig.equals("Shipment not in RS")){
        	report.addReportStep("Verify RSConfig Cycle value is not 'Shipment not in RS'" , 
         			"RSConfig Cycle value is '"+ RSConfig+"'", StepResult.PASS);
			
		}else{
			report.addReportStep("Verify RSConfig Cycle value is not Shipment not in RSS" , 
         			"RSConfig Cycle value did not change from 'Shipment not in RS'", StepResult.FAIL);
		}
		

         
    }
	
	
	/**
	 * Method to manually assign to required carrier in Shipment Tender page
	 * @throws Exception
	 */
	
	
	public void shipmentTender_manualAssignCarrier(String carrier) throws Exception{

		
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(0);
//		driver.switchTo().activeElement();

		if(wh.isElementPresent(ShipmentTenderForm, 5)){			
			shipmentTender_rightClickOptions("Manual Resource Selection");			
		}else{
			throw new Exception("Shipment Tender Screen");
		}
		
	
		if(wh.isElementPresent(ShipmentTenderForm, 5)){			
			shipmentTender_rightClickOptions("Manual Assign");			
		}else{
			throw new Exception("Shipment Tender Screen");
		}
		
		shipmentTender_identifyOptimumCarrier();
		shipmentTender_selectCarrier(carrier);
	
	}
	
	
	/**
	 * Method to right click and select options
	 * @throws Exception
	 */
	
	public void shipmentTender_rightClickOptions(String option) throws Exception{
		
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(0);
//		driver.switchTo().activeElement();
		try{
			wh.contextClic(PickupLabel);
		}
		catch(Exception e)
		{
			System.out.println("Not able to right click"+e.toString());
		}
		
		
		switch (option){
		
		case "Manual Resource Selection" :
			
			if (wh.isElementPresent(ManualResourceSelection, 4)){
				wh.clickElement(ManualResourceSelection);
				
				System.out.println("Option is " +option);
				
				wh.handleAlert();				
				Thread.sleep(2000);	
				report.addReportStep("Right Click and select " +option+ " option", 
				         "Successfully selected " +option+ " Option", StepResult.PASS);
			}else{
				throw new Exception("Unable to find " +option+ " Option");
			}
			break;
		
		
		case "Manual Assign":
			
			if (wh.isElementPresent(ManualAssignOption, 2)){
				wh.clickElement(ManualAssignOption);
				
				System.out.println("Option is " +option);
					
				report.addReportStep("Right Click and select " +option+ " option", 
			         "Successfully selected " +option+ " Option", StepResult.PASS);
			}else{
				throw new Exception("Unable to find " +option+ " Option");
			}
				
			break;
			
		default:
			
			throw new Exception("Unable to find " +option+ " Option");
			
		}
	}
	
	/**
	 * Method to return optimum carrier for Manual assignment
	 * @throws Exception
	 */
	
	public String shipmentTender_identifyOptimumCarrier() throws Exception{
		
		return optcarrier;
	}
	
		
	/**
	 * Method to assign required carrier
	 * @throws Exception
	 */
	
	public void shipmentTender_selectCarrier(String carrier) throws Exception{
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(ShipmentTender_Frame));
		driver.switchTo().frame(driver.findElement(CarrierAssign_Frame));

		
		
			if(wh.isElementPresent(CarrierPopup_CodeText, 5)){
				wh.sendKeys(CarrierPopup_CodeText, carrier);
				report.addReportStep("Carrier Code entered into textbox", 
				         "Carrier Code UPSG entered in textbox", StepResult.PASS);
				
				wh.clickElement(CarrierPopup_AssignBtn);
				
								
					if(wh.isElementPresent(OverrideWarningsScreen, 5)){						
						driver.switchTo().frame(0);
						
						String Warning = wh.getText(CarrierOverride_Warning);
						rc.logger.info("Warning Msg: "+Warning);
						
						wh.selectValue(CarrierOverride_Dropdown, "Shipper Override");
						report.addReportStep("Override option selected", 
						         "Shipper Override selected from Override Code dropdown for Warning '" +Warning+"'", StepResult.PASS);
						
						wh.clickElement(CarrierOverride_SaveBtn);
						
						Thread.sleep(2000);
						
						String CarrierAssigned = wh.getText(AssignedCarrier);
						
						if(CarrierAssigned.equalsIgnoreCase(carrier)){						
							report.addReportStep("Validate Manual assignment of Carrier", 
							         "Carrier " +carrier+ " assigned successfully", StepResult.PASS);
						}else{
							report.addReportStep("Validate Manual assignment of Carrier", 
							         "Carrier " +carrier+ " was not assigned successfully", StepResult.FAIL);
						}
													
						driver.switchTo().defaultContent();
						wh.clickElement(CloseShipmentTender);
						
					}
				
			}else{
				rc.terminateTestCase("Carrier Code textbox ", StepResult.FAIL);
			}
					
	}
	
	/* Method to validate if rejection of carrier occurred 
	 * by checking if shipment status is Available or carrier has changed
	 * @throws Exception
	 * @param appttime
	 */
	public void validate_RejectedCarriers() throws Exception{
		
		if(wh.isElementPresent(StatusShipment, 3)){	
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
            while ( System.nanoTime() < endTime ){
            	//driver.switchTo().defaultContent();
            	            	
            	shipmentStatusRefreshButton();           	
                Thread.sleep(5000);
                //driver.switchTo().frame(0);
                
                String statusshipment=wh.getText(StatusShipment);
                System.out.println("#### Current Status after rejection : "+statusshipment);
                
                String carrierpostrejection = wh.getText(CarrierShipment);
                System.out.println("Assigned Carrier before rejection: "+carriershipment);
                System.out.println("Assigned Carrier after rejection: "+carrierpostrejection);
                
              if(statusshipment.equalsIgnoreCase("Available")){
                	report.addReportStep("Verify Status is promoted as Available after Carrier was Rejected", 
                			"Status is promoted as " +statusshipment, StepResult.PASS);
                	break;
                	
                }else{
                	if(!carrierpostrejection.equalsIgnoreCase(carriershipment)||(!carrierpostrejection.isEmpty()) ){
                	
                		report.addReportStep("Verify Carrier assigned is NOT rejected carrier", 
                    			"Status is promoted as " +statusshipment+ " and Assigned Carrier " +carrierpostrejection+ " is different from Rejected Carrier "+carriershipment, StepResult.PASS);
                	}
                	
                	else{
                		report.addReportStep("Verify Carrier assigned is NOT rejected carrier", 
                    			"Status is " +statusshipment+ " and Assigned Carrier " +carrierpostrejection+ " is same as Rejected Carrier "+carriershipment+ " OR No Carrier assigned", StepResult.FAIL);
                	}    
                	
                	break;
                	
                }
                
                
            } 
          }else{
            	rc.terminateTestCase("Shipment Status not available", StepResult.FAIL);
          }
         
	
	}

	
		/**
	 * Method to refresh shipment tender
	 * @throws Exception
	 */
	public void shipmentTenderRefreshButton() throws Exception{
		wh.clickElement(RefreshShipmentTender);
		Thread.sleep(2000);
	}
	public void CloseShipmentScreen() throws Exception{
		wh.clickElement(CloseShipments);
		Thread.sleep(2000);
	}

	
	public void resourceSelectionBtn_Demote() throws Exception{
		System.out.println("in demote shipments");
		//driver.switchTo().defaultContent();
		
		if(wh.isElementPresent(ResourceSelectionBtn,2)){
			
			wh.clickElement(ResourceSelectionBtn);

			if(wh.isElementPresent(DemoteMenuOpt)){				
				report.addReportStep("Click on Resource Selection button", 
						"Successfully clicked on Resource Selection button", StepResult.PASS);				
				wh.clickElement(DemoteMenuOpt);
				
				demoteShipment();
				
			}else{			
				rc.terminateTestCase("Shipment Resource Selection", StepResult.FAIL);
			}
		}
	}
	
	
	public void demoteShipment() throws Exception{
		//driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(RECALLSHIPMENT));
		driver.switchTo().activeElement();

		if(wh.isElementPresent(DemoteShipment)){
			report.addReportStep("Verify Demote button is available in Demote Shipment screen", 
					"Demote button is available in Demote Shipment screen", StepResult.PASS);
			wh.clickElement(DemoteShipment);
			
			if(wh.isElementPresent(DemoteOk)){
				
				wh.clickElement(DemoteOk);
				
				wh.clickElement(RefreshShipmentStatus);			
				}	
				else if(wh.isElementPresent(DemoteCancel)){
				
				wh.clickElement(DemoteCancel);
				report.addReportStep("Demotion Cancel", 
						"Demoted cancel as shipment is not in Available status", StepResult.PASS);
				
				wh.clickElement(RefreshShipmentStatus);
			}
			
			
			if(wh.isElementPresent(StatusShipment)){	
				report.addReportStep("Click on Demote button", 
						"Successfully navigated back to Shipment screen after clicking on Demote button", StepResult.PASS);
				Thread.sleep(2000);
				driver.switchTo().activeElement();				
				if(wh.getText(StatusShipment2).equalsIgnoreCase("Planned")|| wh.getText(StatusShipment3).equalsIgnoreCase("Planned") ){	
					//rc.terminateTestCase("Promotion of Shipment", StepResult.FAIL);
					report.addReportStep("Validate Demotion", 
							"Shipment successfully Demoted", StepResult.PASS);					
				}
				else{
					
					report.addReportStep("Demotion of Shipment", 
							"Demotion of Shipment", StepResult.WARNING);
				}
					
			}else{
				rc.terminateTestCase("Shipment screen after clicking on Demote button", StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Demote Button in Demote Shipments screen", StepResult.FAIL);
		}		
	}
	
	//FOR RUN ENGINES:: Method to perform Recall, Demote & Cancel for 2nd row shipment Id..
	

		public void shipmentstatusrowcheck2() throws Exception{
		System.out.println("in news shipst ");
		Thread.sleep(2000);
		wh.clickElement(RefreshShipmentStatus);
			try
			{
				if(wh.isElementPresent(StatusShipment3)){		
					String s = wh.getText(StatusShipment3);
					System.out.println("Stat:"+s);
						if(s.equalsIgnoreCase("Planned") || s.equalsIgnoreCase("Available") || s.equalsIgnoreCase("Assigned") || s.equalsIgnoreCase("Unplanned")){	
							wh.clickElement(CheckBox2);
							cancelshipments();
							cancelOrder();
							cancelOrderVerifyrow2();
							wh.clickElement(CheckBox2);
							
							report.addReportStep("Shipment Cancel for 2nd row", 
									"Shipment Cancelled Successfully", StepResult.PASS);
							
						}
						else if(s.equalsIgnoreCase("Tendered") || s.equalsIgnoreCase("Accepted") )
								{
									System.out.println("Stat:"+s);
									wh.clickElement(CheckBox2);
									resourceSelectionBtn_Recall();
									//Thread.sleep(2000);
									wh.clickElement(CheckBox2);
									resourceSelectionBtn_Demote();
									if(wh.isElementPresent(StatusShipment2)){		
										String s1 = wh.getText(StatusShipment2);
										System.out.println("Stat:"+s1);									
									
									if(s1.equalsIgnoreCase("Tendered") || s1.equalsIgnoreCase("Accepted")){
										wh.clickElement(CheckBox2);
										resourceSelectionBtn_Recall();
										wh.clickElement(CheckBox2);
										resourceSelectionBtn_Demote();	
									}
									}
									wh.clickElement(CheckBox2);
									cancelshipments();
									cancelOrder();
									wh.clickElement(CheckBox2);
									cancelOrderVerifyrow2();
									wh.clickElement(CheckBox2);
									
									report.addReportStep("Shipment Cancel for 2nd row", 
											"Shipment Cancelled Successfully", StepResult.PASS);
																			
				}
					
				}
				else
				{ 
					
					System.out.println("Element Not found");
				}
			
				}
			
			catch(Exception e)
			{
				System.out.println(e.toString());
			}	
	}
		
		//FOR RUN ENGINES::Method to perform Recall, Demote & Cancel for 1st row shipment Id..
		
		public void shipmentstatusrowcheck1() throws Exception{
			System.out.println("in news shipst ");
			wh.clickElement(RefreshShipmentStatus);
				try
				{
						if(wh.isElementPresent(StatusShipment2)){		
						String s = wh.getText(StatusShipment2);
						System.out.println("Stat:"+s);
							if(s.equalsIgnoreCase("Planned") || s.equalsIgnoreCase("Available") || s.equalsIgnoreCase("Assigned") || s.equalsIgnoreCase("Unplanned")){	
								wh.clickElement(CheckBox1);
								cancelshipments();
								cancelOrder();
								cancelOrderVerifyrow1();
								wh.clickElement(CheckBox1);
								
								report.addReportStep("Shipment Cancel for 1st row", 
										"Shipment Cancelled Successfully", StepResult.PASS);
							}
							else if(s.equalsIgnoreCase("Tendered") || s.equalsIgnoreCase("Accepted") )
									{
										System.out.println("Stat:"+s);
										wh.clickElement(CheckBox1);
										resourceSelectionBtn_Recall();
										//Thread.sleep(2000);
										wh.clickElement(CheckBox1);
										resourceSelectionBtn_Demote();
										
										if(wh.isElementPresent(StatusShipment2)){		
											String s1 = wh.getText(StatusShipment2);
											System.out.println("Stat:"+s1);
										
										if(s1.equalsIgnoreCase("Tendered") || s1.equalsIgnoreCase("Accepted")){
											wh.clickElement(CheckBox1);
											resourceSelectionBtn_Recall();
											wh.clickElement(CheckBox1);
											resourceSelectionBtn_Demote();											
										}
										}
										wh.clickElement(CheckBox1);
										cancelshipments();
										cancelOrder();
										cancelOrderVerifyrow1();
										wh.clickElement(CheckBox1);
										report.addReportStep("Shipment Cancel for 1st row", 
												"Shipment Cancelled Successfully", StepResult.PASS);																				
					}
						
					}
					else
					{ 
						
						System.out.println("Element Not found");
					}
				
					}
				
				catch(Exception e)
				{
					System.out.println(e.toString());
				}	
		}
				
							
				
// Method to verify cancel shipment for shipment ID row 1.

	public void cancelOrderVerifyrow1() throws Exception{
	System.out.println("in cancelOrderVerify");
	wh.doubleClickUsingAction(StatusShipment2);
	wh.waitForPageLoaded();
	Thread.sleep(4000);		
	driver.switchTo().frame(driver.findElement(SHIPMENTDETAIL));
	
	wh.clickElement(Shipmentdetailpopup);
		
	
	if(wh.isElementPresent(Cancelled, 7000))
	{
		String s = wh.getText(Cancelled);
		System.out.println("Cancelled Status :"+s);
		if(s.equalsIgnoreCase("Yes"))
		{
			report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
					"The Cancelled Status is Yes", StepResult.PASS);
			
			driver.switchTo().defaultContent();
			
			if(wh.isElementPresent(Shipmentdetailpopup, 2000))
			{
			
			wh.clickElement(Shipmentdetailpopup);
			}				
		}
		else
		{
			driver.switchTo().defaultContent();
			
			if(wh.isElementPresent(Shipmentdetailpopup))
			{
			
			wh.clickElement(Shipmentdetailpopup);
			}	
			report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
					"Cancelled Status is not as Expected:"+s, StepResult.WARNING);
		}
				
	}
	
}

	// Method to verify cancel shipment for shipment ID row 2.
	
	public void cancelOrderVerifyrow2() throws Exception{
	System.out.println("in cancelOrderVerify");
	wh.doubleClickUsingAction(StatusShipment3);
	wh.waitForPageLoaded();
	Thread.sleep(4000);
	driver.switchTo().frame(driver.findElement(SHIPMENTDETAIL));
		
	
	if(wh.isElementPresent(Cancelled, 7000))
	{
		String s = wh.getText(Cancelled);
		System.out.println("Cancelled Status :"+s);
		if(s.equalsIgnoreCase("Yes"))
		{
			report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
					"The Cancelled Status is Yes", StepResult.PASS);
			//driver.switchTo().frame(driver.findElement(SHIPMENTDETAIL));
			
			driver.switchTo().defaultContent();
			
			if(wh.isElementPresent(Shipmentdetailpopup))
			{
			
			wh.clickElement(Shipmentdetailpopup);
			}				
		}
		else
		{
			driver.switchTo().defaultContent();
			
			if(wh.isElementPresent(Shipmentdetailpopup))
			{
			
			wh.clickElement(Shipmentdetailpopup);
			}	
			report.addReportStep("Checking the Cancelled Status in Shipment Detail Screen", 
					"Cancelled Status is not as Expected:"+s, StepResult.WARNING);
		}
				
	}
}
}





