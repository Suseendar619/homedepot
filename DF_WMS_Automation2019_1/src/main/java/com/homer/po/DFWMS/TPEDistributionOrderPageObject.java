package com.homer.po.DFWMS;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;



public class TPEDistributionOrderPageObject extends PageBase{

	public TPEDistributionOrderPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
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
	
	static final By DistributionScreenHeader=By.xpath(".//span[contains(text(),'Distribution Orders') and contains(@id,'screen')]");
	static final By PrimaryFieldFilter=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[4]");
	static final By PurchaseOrderFilterBtn=By.id("ext-gen2549");
	static final By PurchaseOrderOption=By.xpath(".//div[contains(@class,'filter-combo-option') and contains(text(),'Purchase Order')]");
	//static final By PurchaseOrderInput=By.xpath(".//input[contains(@id, 'mps-lookupfield')]");
	static final By DistributionOrderInput=By.name("DistributionorderID");
		
	static final By DistributionOrderApply=By.xpath(".//span[contains(text(), 'Apply') and contains(@id,'button')]");
	static final By DistributionOrderApplied=By.xpath("//table[starts-with(@id,'gridview-')]/tbody/tr/td[3]");
	static final By DistributionOrderDisplayed=By.xpath("(.//tr[contains(@id,'gridview')]//td)[3]");
	static final By DistributionOrderCheckbox=By.xpath("(.//tr[contains(@id,'gridview')]//td)[1]");
	static final By DOSelectAllCheckbox=By.xpath(".//span[contains (@id,'aggrindicator')]/img/../../../preceding-sibling::div[1]/div[1]/span"); 
	static final By ViewBtn=By.xpath(".//span[contains(text(),'View')]/following-sibling::span");
	static final By DestinationDetails=By.id("dataForm:PODetailsDetails_Facility_Destination_Facility_Out");
	static final By OriginDetails=By.id("dataForm:PODetailsDetails_Facility_Origin_Facility_Out");
	static final By QuantityTotal=By.xpath(".//span[contains(@id,'0:PODetails_Details_Total_out')]");
	static final By DOtab=By.id("PODetail_DO_Tab_lnk");
	static final By DOnumbr=By.xpath(".//span[contains(@id,'doListDataTable:0:DO_Nbr_Out')]");
	static final By ManualPlaningBtn=By.xpath(".//span[contains(text(),'Manual Planning')]");
	//static final By ManualPlaningBtn=By.xpath("(.//span[contains(text(),'Manual Planning')])[2]");
	static final By ManualPlaningShip=By.xpath(".//span[contains(text(),'Shipments') and @class='x-header-text x-window-header-text x-window-header-text-default' and not (contains (@id,'ghost'))]/../../../../../following-sibling::div//*[contains(text(),'Manual Planning')]");
	static final By SingleMenuOpt=By.xpath(".//span[contains(text(),'Single') and starts-with(@id,'menuitem')]");
	static final By CombineMenuOpt=By.xpath(".//span[contains(text(),'Combine') and starts-with(@id,'menuitem')]");
	static final By ShipMentBody=By.id("shipmentList_wrapper_body");
	static final By AcceptShipment=By.xpath(".//div[contains(text(),'Accept Shipment(s)')]");
	static final By AcceptWorkspace=By.xpath(".//div[contains(text(),'Accept Workspace')]");
	static final By ValidateShipment=By.xpath(".//div[contains(text(),'Validate')]");
	static final By ViewOrdersStops=By.xpath(".//div[contains(text(),'View Orders/Stops')]");
	static final By ValidateShipmentError=By.xpath(".//span[contains(text(),'Errors')]");
	static final By ValidateErrorText=By.xpath(".//*[@id='error']/p");
	static final By ShipmentIdLink=By.xpath("(.//td[contains(@class,'shipment_id_link')])[1]");
	static final By DOIdLink1=By.xpath("(.//td[contains(@class,'assigned_order_id_link')])[1]");
	static final By DOIdLink2=By.xpath("(.//td[contains(@class,'assigned_order_id_link')])[2]");
	static final By ShipmentListNoRecord=By.xpath("//*[@id='shipmentList']/tbody/tr/td");
	static final By TransportationStatus=By.xpath("//table[starts-with(@id,'gridview-')]/tbody/tr/td[5]");
	
	
	static final By RTStab=By.id("PODetail_RTS_Tab_lnk");
	static final By RTSnumber=By.id("checkAll_c0_dataForm:lvrtsLineItems1:rtsDataTable");
	static final By Saveas=By.xpath(".//*[text()='Save As']");
	static final By Saveastext=By.xpath(" (.//*[contains (@id,'textfield') and contains (@class,'x-form-field x-form-required-field x-form-text')])[2]");
	static final By Save=By.xpath("(.//*[text()='Save'])[1]/following-sibling::span");
	static final By Message=By.xpath(".//*[contains (@id,'dataview')]/div/div/span[2]");
	
	
	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizeDistributionOrder=By.xpath(".//span[contains(text(),'Distribution Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By CloseConsolidationWorkspace=By.xpath(".//span[contains(text(),'Consolidation Workspace')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By CloseDistributionOrder=By.xpath(".//span[contains(text(),'Distribution Orders')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By CloseValidationErrors =By.xpath(".//span[contains(text(),'Errors')]/following-sibling::a/span");
	static final By ConfirmOkBtn=By.xpath("//div[@id='confirmDialog']/following-sibling::div//span[contains(text(),'Ok')]");
	
	
	StreamResult streamResult;
	String DO,primaryfield=null,donumber;
	//private String shipmentId;
	public static String shipmentId;
	public static String OrderId;
	public static String OrderId2;
	public static String PickupStart;
	public static String PickupEnd;
	public static String OrderQty;
	public static String PlannedWeight;
	public static String PlannedVolume;
	public static String DOnumberui;
	public static String DOOrderID1;
	public static String DOOrderID2;
	
	List<String> DOnumber = new ArrayList<String>();
	List<String> ShipmentDOnumber = new ArrayList<String>();
	
    List<String> e = new ArrayList<String>();

	/**
	 * Method to validate Distribution Order search
	 * @throws Exception
	 * @param primaryfieldinput
	 */
	public void distributionOrder(String primaryfieldinput) throws Exception{	
		wh.waitUntilDisappear(LoadingFrame);		

		wh.clickElement(MaximizeDistributionOrder);

		if(wh.isElementPresent(DistributionScreenHeader, 22)){			
			if(wh.getText(DistributionScreenHeader).equalsIgnoreCase("Distribution Orders")){
				//primaryfield=dataTable.getData(DataColumn.PrimaryFieldInstanceTwo);
				wh.clearElement(PrimaryFieldFilter);
				if(!primaryfieldinput.equalsIgnoreCase("")){
					Thread.sleep(4000);
					wh.sendKeys(PrimaryFieldFilter, primaryfieldinput);
					donumber=TPEPurchaseOrderPageObject.DO;
					if(!donumber.equalsIgnoreCase("")){
						wh.sendKeys(DistributionOrderInput, donumber);	
						rc.logger.info("DO number : "+donumber);

						if(wh.getAttribute(DistributionOrderInput, "value").equalsIgnoreCase(donumber)){
							report.addReportStep("Enter Primary Fields of Distribution Order", "Successfully entered Primary Fields", StepResult.PASS);
						}else{
							report.addReportStep("Enter Primary Fields of Distribution Order", "Primary Fields not entered", StepResult.FAIL);
						}
					}
				}
			}else{
				rc.terminateTestCase("TPE distribution order screen");
			}
		}else{
			rc.terminateTestCase("TPE distribution order screen");
		}
	}
	
	
	/**
	 * Method to validate Distribution Order is applied
	 * @throws Exception	 
	 */
	public void distributionOrderApplied() throws Exception{
		if(wh.isElementPresent(DistributionOrderApply, 3)){
			wh.clickElement(DistributionOrderApply);
			Thread.sleep(7500);
			
			if(wh.isElementPresent(DistributionOrderApplied, 3)){				
				 List<WebElement> DOCount = driver.findElements(DistributionOrderApplied);
				 //DOnumberui=wh.getText(DistributionOrderApplied);
					Iterator<WebElement> itrDO = DOCount.iterator();
					while(itrDO.hasNext())
					{					
						String s = itrDO.next().getText();
						DOnumber.add(s);
						rc.logger.info("Filtered by Distribution Order number:"+s);
						System.out.println(s);
						
						report.addReportStep("Click on Apply and verify distribution order generated", 
						" Successfully applied and distribution : "+s+" ", StepResult.PASS);
					}
				
			}else{
				report.addReportStep("Click on Apply and verify distribution order generated", 
						"Not applied and distribution order not generated", StepResult.FAIL);
			}
		}else{
			rc.terminateTestCase("TPE purchase order");
		}
		
 	}
	
	/*public void distributionOrderSelectandView() throws Exception{
		if(wh.isElementPresent(DistributionOrderDisplayed, 3)){
			String dodisplay=wh.getText(DistributionOrderDisplayed);
			rc.logWrite("DO number displayed as : "+dodisplay);
			if(dodisplay.equalsIgnoreCase(DO)){
				wh.clickElement(DistributionOrderCheckbox);
				
				//wh.waitForElementPresent(ViewBtn);
				Thread.sleep(6000);
					wh.jsClick(ViewBtn);
								
				report.addReportStep("Verify distribution order is displayed in grid as expected", 
						"Distribution order is displayed in grid as expected", StepResult.PASS);
			}else{
				rc.terminateTestCase("Distribution order not equals");
			}
		}else{
			rc.terminateTestCase("Distribution order is not displayed");
		}		
	}*/
	
	/**
	 * Method to validate Distribution Order is selected
	 * @throws Exception	
	 */
	public void distributionOrderNumberCheckBox() throws Exception{	
		String dodisplay=wh.getText(DistributionOrderDisplayed);
		//rc.logWriter("DO number displayed as : "+dodisplay);
		rc.logger.info("DO number displayed as : "+dodisplay);
		WebElement DOCheckBox=driver.findElement(By.xpath(".//div[contains(text(),'"+donumber+"')]/../preceding-sibling::td[2]//div[@class='x-grid-row-checker']"));
		if(dodisplay.equalsIgnoreCase(donumber)){
			if(!(DOCheckBox.isSelected())){								
				report.addReportStep("Verify Distribution Order checkbox is displayed", 
						"Distribution Order checkbox is available", StepResult.PASS);

				wh.clickElement(DOCheckBox);

				report.addReportStep("Click on Distribution Order checkbox", 
						"Successfully clicked on Distribution Order checkbox", StepResult.PASS);

			}else{
				report.addReportStep("Verify Distribution Order checkbox is displayed", 
						"Distribution Order checkbox is not available", StepResult.FAIL);
			}
		}
	}
	
	/**
	 * Method to validate manual planing -Single option button
	 * @throws Exception
	 */
	public void manualplaningButton() throws Exception{		
		if(wh.isElementPresent(ManualPlaningBtn, 3)){
			
			wh.clickElement(ManualPlaningBtn);
						
			if(wh.isElementPresent(SingleMenuOpt, 3)){				
				report.addReportStep("Click on Manual Planing -Single option", 
						"Successfully clicked on Manual Planing - Single option", StepResult.PASS);				
				wh.clickElement(SingleMenuOpt);
			}else{			
				rc.terminateTestCase("Distribution Orders", StepResult.FAIL);
			}
		}
	}
	
	
	/**
	 * Method to validate manual planing -Combine option button
	 * @throws Exception
	 */
	public void manualplaningCombineButton() throws Exception{	
		
		if(wh.isElementPresent(DOSelectAllCheckbox, 3)){			
			wh.clickElement(DOSelectAllCheckbox);		
		}else{
			rc.terminateTestCase("Select All DO checbox not present", StepResult.FAIL);
		}
		
		if(wh.isElementPresent(ManualPlaningBtn, 3)){
			
			wh.clickElement(ManualPlaningBtn);
						
				if(wh.isElementPresent(CombineMenuOpt, 3)){	
				wh.clickElement(CombineMenuOpt);
				report.addReportStep("Click on Manual Planing -Combine option", 
						"Successfully clicked on Manual Planing - Combine option", StepResult.PASS);				

				}else{			
				rc.terminateTestCase("Click on Manual Planing -Combine option unsuccessful", StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Manual Planning Button is disabled", StepResult.FAIL);
		}
	}
	
	/**
	 * Method to perform right click, accept workspace and on confirmation click on OK button
	 * @throws Exception
	 */
	public void acceptWorkspace() throws Exception{
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();

		Thread.sleep(5000);
		shipmentId = wh.getText(ShipmentIdLink);		
		
		System.out.println("Shipment ID is " + shipmentId);


		wh.contextClic(ShipmentIdLink);

		if(wh.isElementPresent(AcceptWorkspace, 6)){
			report.addReportStep("Right click on Shipment Id", 
					"Successfully right clicked on Shipment Id: "+getShipmentId(), StepResult.PASS);

			wh.clickElement(AcceptWorkspace);

			report.addReportStep("Click on Accept Workspace", 
					"Successfully clicked on Accept Workspace", StepResult.PASS);

		}else{
			report.addReportStep("Click on Accept Workspace", 
					"Not clicked on Accept Workspace", StepResult.FAIL);
		}
				
		if(wh.isElementPresent(ConfirmOkBtn, 6)){
			wh.clickElement(ConfirmOkBtn);
			
			report.addReportStep("Click on 'Ok' button in Confirm dialog box", 
					"Succefully clicked on 'Ok' button", StepResult.PASS);

		}else{
			rc.terminateTestCase("Consolidation workspace- confirm dialog box not available ", StepResult.FAIL);
		}
		
		driver.switchTo().defaultContent();
		wh.clickElement(CloseConsolidationWorkspace);
		wh.clickElement(CloseDistributionOrder);
	}
	
	
	
	/**
	 * Method to perform right click, Validate shipment and check for error messages
	 * @throws Exception
	 */
	public void validateShipment() throws Exception{
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		Thread.sleep(3000);
		
		shipmentId = wh.getText(ShipmentIdLink);

		wh.contextClic(ShipmentIdLink);

		if(wh.isElementPresent(ValidateShipment, 6)){
			wh.clickElement(ValidateShipment);
			Thread.sleep(5000);

			report.addReportStep("Click on Validate", 
					"Successfully clicked on Validate", StepResult.PASS);
			
			if (!wh.isElementPresent(ValidateShipmentError, 10)){
				report.addReportStep("Validate errors exist on Validation", 
						"No Errors are present on Validating Shipment", StepResult.PASS);
			}else{
				String validateErrormsg =wh.getText(ValidateErrorText);
								
				report.addReportStep("Validate errors exist on Validation", 
						"Error present on Validating Shipment: " +validateErrormsg , StepResult.WARNING);
				wh.clickElement(CloseValidationErrors);

			}
			

		}else{
			report.addReportStep("Click on Validate", 
					"Not clicked on Validate option", StepResult.FAIL);
		}
				
	}
	
	
	/**
	 * Method to perform right click and View Orders/Stops
	 * @throws Exception
	 */
	public void viewordersstops() throws Exception{
		

//		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		Thread.sleep(3000);
		
		shipmentId = wh.getText(ShipmentIdLink);
		wh.contextClic(ShipmentIdLink);

		if(wh.isElementPresent(ViewOrdersStops, 6)){
			wh.clickElement(ViewOrdersStops);
			Thread.sleep(5000);

			report.addReportStep("Click on View Orders/Stops", 
					"Successfully clicked on View Orders/Stops", StepResult.PASS);
			
	
			if(wh.isElementPresent(DOIdLink1,2) && wh.isElementPresent(DOIdLink2,2)){
				report.addReportStep("Multiple DO Orders combined in Shipment", 
						"Successfully combined DO Orders for Shipment", StepResult.PASS);
				
				DOOrderID1 = (wh.getText(DOIdLink1));
				ShipmentDOnumber.add(DOOrderID1);
				DOOrderID2 = (wh.getText(DOIdLink2));
				ShipmentDOnumber.add(DOOrderID2);		
				System.out.println("DO Order 1 is  " + DOOrderID1 + " DO Order 2 is  " + DOOrderID2 );
								
			}else{
				rc.terminateTestCase("DO Orders were not combined within Shipment");				
			}
	
			
		}else{
			report.addReportStep("Click on View Orders/Stops", 
					"Did not click on View Orders/Stops", StepResult.FAIL);
		}
				
	}
	
	/**
	 * Method to compare DO Orders within shipment after combining
	 * @throws Exception
	 */	
	public void compareDOorders() throws Exception{
		Collections.sort(DOnumber);		
		rc.logger.info("DOnumber:"+DOnumber);
		Collections.sort(ShipmentDOnumber);
		rc.logger.info("ShipmentDOnumber:"+ShipmentDOnumber);
		
		if(DOnumber.equals(ShipmentDOnumber))
		{
			report.addReportStep("Compare DO Numbers within Shipment after combining", 
					"DO Numbers " + DOnumber + " successfully combined and attached to Shipment : " + getShipmentId(), StepResult.PASS);
		}else{
			report.addReportStep("Compare DO Numbers within Shipment after combining", 
					"DO Numbers " + DOnumber + " NOT combined and attached to Shipment : "+ getShipmentId() , StepResult.FAIL);
		}
	}
	
	
	public void distributionOrderEng(String primaryfieldinput) throws Exception{
		//Fetching the DO with PO which is posted with XML
		//wh.waitUntilDisappear(LoadingFrame);		

		//wh.clickElement(MaximizeDistributionOrder);
			
		poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		String ponumberfromresponse=OrderId;
		
		if(wh.isElementPresent(DistributionScreenHeader, 10)){			
			if(wh.getText(DistributionScreenHeader).equalsIgnoreCase("Distribution Orders")){
				//primaryfield=dataTable.getData(DataColumn.PrimaryFieldInstanceTwo);
				wh.clearElement(PrimaryFieldFilter);
				if(!primaryfieldinput.equalsIgnoreCase("")){
					wh.sendKeys(PrimaryFieldFilter, primaryfieldinput);
					WebElement wb = driver.findElement(PrimaryFieldFilter);
					wb.sendKeys(Keys.ENTER);
					//donumber=TPEPurchaseOrderPageObject.DO;
					System.out.println("numberfromresponse:"+ponumberfromresponse);
				
					
					if(!ponumberfromresponse.equalsIgnoreCase("")){
						wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*");	
						//wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*");	
						//rc.logger.info("DO number : "+donumber);
						report.addReportStep("Enter Primary Fields of Distribution Order", "Successfully entered Primary Fields", StepResult.PASS);
						Thread.sleep(30000);
					}
					else{
						report.addReportStep("Enter Primary Fields of Distribution Order", "Primary Fields not entered", StepResult.FAIL);
					}
				}
			}else{
				rc.terminateTestCase("TPE distribution order screen");
			}
		}else{
			rc.terminateTestCase("TPE distribution order screen");
		}
		
		distributionOrderAppliedEng();	
	}
	
	public void poxmlDatavp(String xmlpath) throws Exception{
		//PO xml				
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;

		document = documentBuilder.parse(System.getProperty("user.dir") + xmlpath); 				                 			
		
		Node Tagnode = document.getElementsByTagName("OrderId").item(0);                   
		OrderId=Tagnode.getTextContent();
		System.out.println("orderid:"+OrderId);	
		
		Node Tagnode_3 = document.getElementsByTagName("PickupStart").item(0);                   
		PickupStart=Tagnode_3.getTextContent();
		
		Node Tagnode_4 = document.getElementsByTagName("PickupEnd").item(0);   
		PickupEnd=Tagnode_4.getTextContent();
		
		Node Tagnode_5 = document.getElementsByTagName("OrderQty").item(0);                   
		OrderQty=Tagnode_5.getTextContent();
		
		Node Tagnode_6 = document.getElementsByTagName("PlannedWeight").item(0);   
		PlannedWeight=Tagnode_6.getTextContent();
		
		Node Tagnode_7 = document.getElementsByTagName("PlannedVolume").item(0);   
		PlannedVolume=Tagnode_7.getTextContent();
							
		streamResult = new StreamResult(new File(System.getProperty("user.dir") + xmlpath));
		//return origin;				
	}
	
	public void distributionOrderAppliedEng() throws Exception{
		if(wh.isElementPresent(DistributionOrderApply, 3)){
			wh.clickElement(DistributionOrderApply);
			Thread.sleep(2000);
			if(wh.isElementPresent(DistributionOrderApplied, 3)){
				 DOnumberui=wh.getText(DistributionOrderApplied);
				
				rc.logger.info("Filtered by Distribution Order number:"+DOnumberui);
				System.out.println("DOnumberui:"+DOnumberui);				
				report.addReportStep("Click on Apply and verify distribution order generated", 
						"Successfully applied and distribution order generated", StepResult.PASS);
			}else{
				report.addReportStep("Click on Apply and verify distribution order generated", 
						"Not applied and distribution order not generated", StepResult.FAIL);
			}
		}else{
			rc.terminateTestCase("TPE purchase order");
		}
	}
	
	public void transportationStatusValidaion ()  throws Exception
	{
		List<WebElement> status = driver.findElements(TransportationStatus);
		
		Iterator<WebElement> itr = status.iterator();
		while(itr.hasNext())
		{
		
			String s = itr.next().getText();
			if(s.equalsIgnoreCase("Unplanned"))
			{
				report.addReportStep("Verifing Transportation Status generated", 
						" The Transportation Status is "+s+" ", StepResult.PASS);
			}
			else
				report.addReportStep("Verifing Transportation Status generated", 
						" The Transportation Status is "+s+" ", StepResult.FAIL);
		}
	}
	
	public void dOValidation ()  throws Exception
	{
		List<WebElement> DONumber = driver.findElements(DistributionOrderApplied);
		
		Iterator<WebElement> itrDO = DONumber.iterator();
		while(itrDO.hasNext())
		{
		
			String s = itrDO.next().getText();
			report.addReportStep("Distribution Orders:", 
			" Distribution Orders generated are : "+s+" ", StepResult.PASS);
		}
	}
	public void filterCreation() throws Exception
	{
		//Filter Creation for the DO 
		if (wh.isElementPresent(Saveas))
		{
			wh.clickElement(Saveas);
			Thread.sleep(1000);
			if(wh.isElementPresent(Saveastext, 2))
			{
				wh.clickElement(Saveastext);
				wh.sendKeys(Saveastext, dataTable.getData(DataColumn.FilterName));
				Thread.sleep(4000);
				rc.logger.info("FilterName : "+dataTable.getData(DataColumn.FilterName));
				//driver.switchTo().parentFrame();
				/*Alert alert = driver.switchTo().alert();
				alert.accept(); */
				
				//wh.jsClick(Save);
				
				WebElement wb = driver.findElement(Saveastext);
				wb.sendKeys(Keys.TAB,Keys.ENTER);
				
				String Messages = wh.getText(Message);
				System.out.println("Messages:"+Messages);
				if(Messages.equalsIgnoreCase("The View \""+dataTable.getData(DataColumn.FilterName)+"\" has been saved successfully"))
				{
					report.addReportStep("Entered the Filter Name and clicked on save", 
							"The Filter created is succesfully saved and acknowledgement is received",StepResult.PASS);
				
				}
				else
				{
					report.addReportStep("Entered the Filter Name and clicked on save", 
							"The Filter created is Not saved and acknowledgement is not received",StepResult.FAIL);
				}
			/*	if(wh.isElementPresent(Save, 4))
				{
					wh.clickElement(Save);
					Thread.sleep(4000);
					String Messages = wh.getText(Message);
					System.out.println("Messages:"+Messages);
					if(Messages.equalsIgnoreCase("The View "+dataTable.getData(DataColumn.FilterName)+" has been saved successfully"))
					{
						report.addReportStep("Entered the Filter Name and clicked on save", 
								"The Filter created is succesfully saved and acknowledgement is received",StepResult.PASS);
					
					}
					else
						
						report.addReportStep("Entered the Filter Name and clicked on save", 
								"The Filter created is Not saved and acknowledgement is not received",StepResult.FAIL);
				}*/
			}
		}
	}
	public void distributionOrderEngXD(String primaryfieldinput) throws Exception{	
		// Selecting the DO with the PO created with Post XML
		//wh.waitUntilDisappear(LoadingFrame);		

		//wh.clickElement(MaximizeDistributionOrder);
			
		poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		String ponumberfromresponse=OrderId;
		
		if(wh.isElementPresent(DistributionScreenHeader, 10)){			
			if(wh.getText(DistributionScreenHeader).equalsIgnoreCase("Distribution Orders")){
				//primaryfield=dataTable.getData(DataColumn.PrimaryFieldInstanceTwo);
				wh.clearElement(PrimaryFieldFilter);
				if(!primaryfieldinput.equalsIgnoreCase("")){
					wh.sendKeys(PrimaryFieldFilter, primaryfieldinput);
					WebElement wb = driver.findElement(PrimaryFieldFilter);
					wb.sendKeys(Keys.ENTER);
					//donumber=TPEPurchaseOrderPageObject.DO;
					System.out.println("numberfromresponse:"+ponumberfromresponse);
				
					
					if(!ponumberfromresponse.equalsIgnoreCase("")){
						wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*");	
						//wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*");	
						//rc.logger.info("DO number : "+donumber);
						report.addReportStep("Enter Primary Fields of Distribution Order", "Successfully entered Primary Fields", StepResult.PASS);
						Thread.sleep(30000);
					}
					else{
						report.addReportStep("Enter Primary Fields of Distribution Order", "Primary Fields not entered", StepResult.FAIL);
					}
				}
			}else{
				rc.terminateTestCase("TPE distribution order screen");
			}
		}else{
			rc.terminateTestCase("TPE distribution order screen");
		}
		
		distributionOrderAppliedEng();	
	}
	public void distributionOrderEngValidation(String primaryfieldinput) throws Exception{	
		//wh.waitUntilDisappear(LoadingFrame);		

		wh.clickElement(MaximizeDistributionOrder);
		wh.waitForPageLoaded();	
		wh.waitUntilDisappear(LoadingFrame);
		poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		String ponumberfromresponse=OrderId;
		poxmlDatavp(dataTable.getData(DataColumn.xml2));
		String ponumberfromresponse1=OrderId;
		
		
		if(wh.isElementPresent(DistributionScreenHeader, 22)){			
			if(wh.getText(DistributionScreenHeader).equalsIgnoreCase("Distribution Orders")){
				//primaryfield=dataTable.getData(DataColumn.PrimaryFieldInstanceTwo);
				wh.clearElement(PrimaryFieldFilter);
				if(!primaryfieldinput.equalsIgnoreCase("")){
					Thread.sleep(4000);
					wh.sendKeys(PrimaryFieldFilter, primaryfieldinput);
					//WebElement wb = driver.findElement(PrimaryFieldFilter);
					//wb.sendKeys(Keys.ENTER);
					//donumber=TPEPurchaseOrderPageObject.DO;
					System.out.println("numberfromresponse:"+ponumberfromresponse);
					System.out.println("numberfromresponse1:"+ponumberfromresponse1);
					
					if(!ponumberfromresponse.equalsIgnoreCase("")){
						wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*,"+ponumberfromresponse1+"*");
						//wh.sendKeys(DistributionOrderInput,ponumberfromresponse+"*");	
						//rc.logger.info("DO number : "+donumber);
						report.addReportStep("Enter Primary Fields of Distribution Order", "Successfully entered Primary Fields", StepResult.PASS);
						Thread.sleep(20000);
					}
					else{
						report.addReportStep("Enter Primary Fields of Distribution Order", "Primary Fields not entered", StepResult.FAIL);
					}
				}
			}else{
				rc.terminateTestCase("TPE distribution order screen");
			}
		}else{
			rc.terminateTestCase("TPE distribution order screen");
		}
		
		//distributionOrderAppliedEng();	
	}
	
	/**
	 * This function returns the shipment ID on this page
	 * 
	 * @return
	 */
	public static String getShipmentId() {
		return shipmentId;
	}
}