package com.homer.po.DFWMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.opera.core.systems.scope.protos.UmsProtos.Format;

import groovyjarjarantlr.ParserSharedInputState;

public class TPEPostMessagePageObject extends PageBase {

	public TPEPostMessagePageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public static enum POTypes{
		XD,
		CONS,
		FLEET,
		STANDARD,
		TRACKINGMSG
	}
	
	public static enum MsgFieldTypes{
		PICKUPSTART ("PickupStart"),
		PICKUPEND ("PickupEnd"),
		DELIVERYSTART ("DeliveryStart"),
		DELIVERYEND ("DeliveryEnd");
		
		private String value;
		
		MsgFieldTypes(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	static final By POSTMESSAGEFRAME = By.xpath("//iframe[contains(@src,'PostTestMessage.jsflps')]");
	static final String ExpectedResponseType = "Confirmation";
	static final String ExpectedResponseCode = "25";
	public static final String ExpectedErrorType = "0";
	static final By Tpepage=By.xpath(".//span[contains(@id,'mps_solutions_menu')]");
	static final By LoadingPage=By.className("x-mask-msg-text");
	static final By MenuBtn=By.xpath(".//span[contains(@class,'wt-topbar-menu-icon') and contains(@id, 'button')]");
	static final By SearchInput=By.xpath(".//input[contains(@id,'mps_menusearch')]");
	static final By LoadingFrame=By.xpath(".//div[contains(@id,'loadmask')]");
	static final By MaximizeScreen=By.xpath(".//img[contains(@class,'x-tool-maximize')]");
	static final By ScreenHeader=By.xpath(".//span[contains(@id,'header_hd-textEl')]");
	static final By CloseScreen=By.xpath(".//img[@class='x-tool-img x-tool-close']");
	static final By ClosePoDetails=By.xpath(".//span[contains(text(),'Post Message')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By CloseTransact=By.xpath(".//span[contains(text(),'Transactions')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	//static final By ResultCode=By.xpath("//span[contains (@id,'resultCode_Column_Param_Text')]");
	//static final By ResultCode1=By.xpath("//span[@id='dataForm:tranlogTable:0:resultCode_Column_Param_Text']");  // 5/25
	static final By ResultCode2=By.xpath(".//*[@id='dataForm:tranlogTable:0:tranlogId_Column_Param_Text']");
	static final By DataCheck=By.xpath("//tr[@id='dataForm:tranlogTable:nodataRow']/td");
	static final By ResultCode=By.xpath("//*[@id='dataForm:tranlogTable:0:resultCode_Column_Param_Text']/../input[1]");
	
	//static final By PostMessageXml=By.id("dataForm:xmlString");
	static final By PostMessageXml=By.xpath("html/body/form/div[4]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div/textarea");
	
	static final By SendBtn=By.id("dataForm:postMessageCmdId");	
	static final By PostResultXml=By.id("dataForm:resultString");
	static final By ResetXml=By.id("dataForm:resetCmdId");
	static final By PurchaseOrderScreenHeader=By.xpath(".//span[contains(text(),'Purchase Orders') and contains(@id,'screen')]");
	static final By PrimaryFieldFilter=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[4]");
	static final By TRANSACTIONSFRAME = By.xpath("//iframe[contains(@src,'TranlogList.jsflps')]");
	
	
	static final By RTStab=By.id("PODetail_RTS_Tab_lnk");
	static final By RTSnumber=By.id("checkAll_c0_dataForm:lvrtsLineItems1:rtsDataTable");
	static final By Reset=By.id("dataForm:resetCmdId");
	
	
	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By MaximizePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	final static Logger logger = Logger.getLogger(TPEPostMessagePageObject.class);
	StreamResult streamResult;
	public static String po;
	public static String OrderId;
	public static String OrderQty;
	public static String OriginFacility;
	public static String DestinationFacility;
	public static String MessageTypeTag;
	public static String MessageType;
	public static String StopNumber;
	public String quantity;
	public String origin;
	public String destinatn;
	public static String povp;
	public String ShipmentNr;
	public String EventTimeTag;
	public String EventDateTime;
	//public static String OrderId;
	public static String orderID;
	
	
	/**
	 * This function generates and sends XML through the Post XML screen.
	 * This function only sends basic POs.
	 * 
	 * @param columnName Name of column in xls sheet that shows the type of
	 * XML sent
	 * @throws Exception
	 */
	public void postMessageXml(String columnName) throws Exception{
		postMessageXml(columnName, POTypes.STANDARD);
	}
	/**
	 * Method to enter search generate and send xml
	 * @return
	 * @throws Exception
	 * @param columnName Name of column in xls data sheet. Shows the type of XML sent
	 * 
	 */
	public void postMessageXml(String columnName, POTypes poType) throws Exception{		

		try{
			
			// If Post Message window not maximized, then maximize. Else, do nothing.				
			maximizeScreen();
			
			switchToPostMessageFrame();
			
			//Create message based on type
			generateXMLByType(columnName, poType);
			
			String str=transformXmltostring(dataTable.getData(columnName));
			
			rc.logger.info("Dynamic XML : " +str);
			driver.switchTo().activeElement();

			// Fill in form and send
			if(wh.isElementPresent(PostMessageXml, 5)){
				wh.clickElement(PostMessageXml);
				wh.sendKeys(PostMessageXml, str);				 
				System.out.println("XML Posted : " +str);
				sendBtn();	
				
				rc.addReportStep("Post Message", "Message of type [" + columnName + "] posted. "
				+ "Click <a href = 'Data/" + getXMLFileLocationForReport(columnName) + "'>here</a> to see actual message. ", StepResult.PASS);
				
				addFileToReport(columnName);
	
			}else{
				throw new Exception("Unable to locate textarea input box with the location: " + PostMessageXml);
			}

		}catch(Exception e){
			String msg = "Message not posted. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("Post Message" , 
				msg, StepResult.FAIL);
		rc.throwTCTerminationException();
		}
	}
	
	public void UserLsample() throws Exception {
		// Open the file
		//FileInputStream fstream = dataTable.getData(DataColumn.Poxml)  new FileInputStream("C://Users//nxn8648\git\TPENew\02-03-2017\TMS_Automation_2016//TestData//XML//VPT_Users_XML.xml");
		FileInputStream fstream = new FileInputStream("C:\\Users\\nxn8648\\git\\TPENew\\02-03-2017\\TMS_Automation_2016\\TestData\\XML\\VPT_Users_XML.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println (strLine);	

			// Fill in form and send
			if(wh.isElementPresent(PostMessageXml, 5)){
				wh.clickElement(PostMessageXml);
				wh.sendKeys(PostMessageXml, strLine);				 
				System.out.println("XML Posted : " +strLine);
				sendBtn();	
				postMessageResponse();
				wh.clickElement(Reset);	
			}
		}

		//Close the input stream
		br.close();
		
	}
	public void UserLsampleRTS() throws Exception {
		
		wh.waitUntilDisappear(LoadingFrame);		
		driver.switchTo().activeElement();
		wh.clickElement(MaximizePurchaseOrder);
		FileInputStream fstream = new FileInputStream("C:\\Users\\nxn8648\\git\\TPENew\\02-03-2017\\TMS_Automation_2016\\TestData\\XML\\RTS_PO.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println (strLine);	

			if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
				
			
					wh.clearElement(VPPurchaseOrderPageObject.PrimaryFieldVP);
					wh.sendKeys(VPPurchaseOrderPageObject.PrimaryFieldVP, "Purchase Order");	
					Thread.sleep(2000);
					
					wh.sendKeys(VPPurchaseOrderPageObject.PurchaseOrderInput, strLine);
					wh.clickElement(VPPurchaseOrderPageObject.PurchaseOrderApply);
					wh.waitUntilDisappear(LoadingFrame);
					Thread.sleep(2000);	
					wh.clickElement(VPPurchaseOrderPageObject.PurchaseOrderCheckboc1);
				}else{
					rc.terminateTestCase("TPE purchase order screen");
				}
			
			if (wh.isElementPresent(VPPurchaseOrderPageObject.RTSgrid))
			{
				wh.waitUntilDisappear(LoadingFrame);
				wh.contextClic(VPPurchaseOrderPageObject.RTSgrid);
				Thread.sleep(2000);	
				report.addReportStep("Right click on RTS", 
						"Right clicked on RTS", StepResult.PASS);
			
				//driver.switchTo().activeElement();
				//wh.clickElement(RTSselect);
				
				if(wh.isElementPresent(VPPurchaseOrderPageObject.RTSselect, 3))
				{
					//wh.mouseOver(RTSselect);
					wh.moveToElement(VPPurchaseOrderPageObject.RTSselect);
					Thread.sleep(2000);	
					if(wh.isElementPresent(VPPurchaseOrderPageObject.RTSready1, 4))
					{
					wh.clickElement(VPPurchaseOrderPageObject.RTSready1);
					
					report.addReportStep("Click on Ready to Ship", 
							"Clicked on Ready to Ship", StepResult.PASS);
					}
					else
					{
						report.addReportStep("Click on Ready to Ship", 
								"Ready to Ship was not selected", StepResult.FAIL);
						rc.terminateTestCase(" Not able to Select RTS");
					}
				}
				
				try{
					wh.clickElement(VPRTSListPageObject.MaximizeScreenRTS);
					driver.switchTo().frame(0);
					driver.switchTo().activeElement();	
					if(wh.isElementPresent(VPRTSListPageObject.RTSCapacity, 4000))
					{
						String s = wh.getAttribute(VPRTSListPageObject.RTSCapacity, "value");
						report.addReportStep("Checking the RTS Capacity in RTS List Screen ",
								"RTS Capacity available in Sceen is :"+s, 
								StepResult.PASS);					
						double d =Double.parseDouble(s);
						System.out.println("D"+d);
						if(d>40000)
						{
							wh.clearElement(VPRTSListPageObject.RTSCapacity);
							wh.sendKeys(VPRTSListPageObject.RTSCapacity, "40000");
						}
						
						
						report.addReportStep("Changing the RTS Capacity in RTS List Screen ",
								"RTS Capacity changed too :"+TPEDistributionOrderPageObject.PlannedWeight, 
								StepResult.PASS);
					}
					else
					{
						report.addReportStep("Checking the RTS Capacity in RTS List Screen ",
								"RTS Capacity is Not available", 
								StepResult.FAIL);
			
					}
					if(wh.isElementPresent(VPRTSListPageObject.RTSCubingTrailer, 4000))
					{
						String s = wh.getAttribute(VPRTSListPageObject.RTSCubingTrailer, "value");
						report.addReportStep("Checking the RTS Cubing Trailer in RTS List Screen ",
								"RTS Cubing Trailer available in Sceen is :"+s, 
								StepResult.PASS);
						
						
						double d =Double.parseDouble(s);
						if(d>3000)
						{
							wh.clearElement(VPRTSListPageObject.RTSCubingTrailer);
							wh.sendKeys(VPRTSListPageObject.RTSCubingTrailer, "3000");
						}
						
						
						report.addReportStep("Changing the RTS Cubing Trailer in RTS List Screen ",
								"The Cubing Trailer changed too :"+TPEDistributionOrderPageObject.PlannedVolume, 
								StepResult.PASS);
					}
					else
					{
						report.addReportStep("Checking the RTS Cubing Trailer in RTS List Screen ",
								"RTS Cubing Trailer  is Not available", 
								StepResult.FAIL);
						{
							
						}
						rc.terminateTestCase("At RTS Cubing Trailer change");
					}
				if(wh.isElementPresent(VPRTSListPageObject.RTSsave1))
				{
					wh.clickElement(VPRTSListPageObject.RTSsave1);
					Thread.sleep(4000);
					
					report.addReportStep("Selected the RTS List to Save",
							"Clicked on RTS Ready to Save", 
							StepResult.PASS);
				}
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
				VPRTSListPageObject s = new VPRTSListPageObject(ic);
				
				s.rtsacknowledgement();
			}
		  
		  
		  
		  
		  
			// Fill in form and send
			/*if(wh.isElementPresent(PostMessageXml, 5)){
				wh.clickElement(PostMessageXml);
				wh.sendKeys(PostMessageXml, strLine);				 
				System.out.println("XML Posted : " +strLine);
				sendBtn();	
				postMessageResponse();
				wh.clickElement(Reset);	
			}*/
			
			
		}

		//Close the input stream
		br.close();
	}
	public void postMessageXmlMQ(String columnName, POTypes poType,String QueueName) throws Exception{		

		try{
			//Create message based on type
			generateXMLByType(columnName, poType);
			
			String str=transformXmltostring(dataTable.getData(columnName));

			MQPageObject n = new MQPageObject(ic);
			n.MQMapping();
			n.init();
			n.putAndGetMessage(str,QueueName);
			report.addReportStep("Posting XML through MQ", "Message posted Sucessfully", StepResult.PASS);
			
		}
		catch(Exception e)
		{
			throw new Exception("Not able to Post XML");
		}
		}
	public void postingXML(String columnName, POTypes poType,String QueueName ) throws Exception{		

		try
			{
				if(dataTable.getData(DataColumn.PostinMQ).equalsIgnoreCase("Yes"))				
				{
					
					postMessageXmlMQ(columnName,poType,QueueName);
				    postMessageResponseMQ(columnName);
				
				}
				else if (dataTable.getData(DataColumn.PostinMQ).equalsIgnoreCase("No"))
				{
					TPEHomePageObject n = new TPEHomePageObject(ic);
					n.TPEmenu();		
					n.searchInput("Post Message","Technical");
					postMessageXml(columnName,poType);
					postMessageResponse();
				}
			
			}
		catch(Exception e)
			{
				throw new Exception("Not able to Post XML");
			}
		
		}
	public void postingXMLEDI(String columnName, POTypes poType,String QueueName ) throws Exception{		

		try
			{
				if(dataTable.getData(DataColumn.PostinMQ).equalsIgnoreCase("Yes"))				
				{
					
					postMessageXmlMQ(columnName,poType,QueueName);
				    //postMessageResponseMQ(columnName);
				
				}
				else if (dataTable.getData(DataColumn.PostinMQ).equalsIgnoreCase("No"))
				{
					TPEHomePageObject n = new TPEHomePageObject(ic);
					n.TPEmenu();		
					n.searchInput("Post Message","Technical");
					postMessageXml(columnName,poType);
					postMessageResponse();
				}
			
			}
		catch(Exception e)
			{
				throw new Exception("Not able to Post XML");
			}
		
		}
	
	
	public void postMessageResponseMQ(String columnName) throws Exception
	{
		try
		{
			TPEHomePageObject n = new TPEHomePageObject(ic);
			n.TPEmenu();		
			n.searchInput("Transactions","Technical");
			
			
			driver.switchTo().defaultContent();
			wh.isElementPresent(MaximizeScreen, 4000);
			wh.clickElement(MaximizeScreen);
			
			
			
			//driver.switchTo().frame(0);
			
			TPETransactionsPageObject b=new TPETransactionsPageObject(ic);
			
			// Click 'Expand' Filter button
			b.clickExpandOptionsButton();
			
			// Enter 'From' time
			
			b.enterFromDateTime("Last One Hour");
			
			// Enter 'To' time
			
			b.enterToDateTime("Now");
			// Set Shipment ID in 'Object ID' field
			VPPurchaseOrderPageObject c = new VPPurchaseOrderPageObject(ic);
			String poID = c.poxmlDatavp(dataTable.getData(columnName));
			b.enterObjectId(poID);
			
			// Click Apply (and wait for transaction to appear)
			//b.clickApplyButton();
			resultCode();
			
			
		}
		catch(Exception e)
		{
			
		}
		
		// Close window
				driver.switchTo().defaultContent();
				if(wh.isElementPresent(CloseTransact)){
					try{
						wh.clickElement(CloseTransact);
					}catch(Exception e){
						throw new Exception ("Unable to close message response screen. " + e.getMessage());
					}
					
				}else{
					throw new Exception("Unable to locate button to close PO response screen");
				}
	}
	
	public void resultCode() throws Exception
	{
		
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);
		while ( System.nanoTime() < endTime ){
		
			
			TPETransactionsPageObject s = new TPETransactionsPageObject(ic);
			s.switchToTransactionsFrame();
			Thread.sleep(4000);
			wh.clickElement(TPETransactionsPageObject.APPLYBUTTON);
			
			//String n = wh.getText(ResultCode1);
			String n = wh.getText(ResultCode2);
			System.out.println("Text:"+n);
			if(!n.isEmpty())
			{
				System.out.println("In loop");
				break;
			/*	if(n.equalsIgnoreCase("Message sent to destination successfully"))
				{
					report.addReportStep("Checking for the Status", "The MQ Messages was sent to destination ", StepResult.PASS);
					System.out.println("Done");
					break;
				}
				else
				{
					report.addReportStep("Checking for the Status", "The MQ Messages was not sent to destination :"+n, StepResult.FAIL);
					rc.terminateTestCase("Transaction Page");
				}
				break;*/
			}
			
			
			/*if(wh.isElementPresent(DataCheck, 4000))
			{
					 boolean b = wh.getText(DataCheck).equalsIgnoreCase("No data found");
					 if(b=true)
					 {
						 Thread.sleep(8000);
						 wh.clickElement(TPETransactionsPageObject.APPLYBUTTON);
						 System.out.println("in true");
						 continue;
					 }  
				break;
			}*/
		}	
			
		
			System.out.println("out of the while");
	}
		/*	if(wh.isElementPresent(TPETransactionsPageObject.ROWS, 2000))
			{			
				List<WebElement> wb = driver.findElements(ResultCode);
				Iterator<WebElement> itrDO = wb.iterator();
				while(itrDO.hasNext())
				{					
					String n = itrDO.next().getText();
					if(!n.equalsIgnoreCase("Message sent to destination successfully"))
					{
						report.addReportStep("Checking for the Status", "The MQ Messages was not sent to destination ", StepResult.PASS);
						break;
					}
				}
			}*/
			
		
	/**
	 * This function gets the XML file location to be used in reports
	 * 
	 * @param columnName
	 * @return
	 */
	private String getXMLFileLocationForReport(String columnName){
		int iterationNum = Math.round(Float.parseFloat(dataTable.getData("Iteration")));
		int subIterationNum = Math.round(Float.parseFloat(dataTable.getData("SubIteration")));
		String dataFileLocation = columnName + "_" + iterationNum + "_" + subIterationNum + ".xml";
		return dataFileLocation;
	}
	/**
	 * Method to validate response	 
	 * @throws Exception
	 */
	public void postMessageResponse() throws Exception{
		// Validate response
		try{
			po=postResultVerification();		

		}catch(Exception e){
			rc.addReportStep("Valdiate message response", "Message response not validated. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		// Close window
		driver.switchTo().defaultContent();
		if(wh.isElementPresent(ClosePoDetails)){
			try{
				wh.clickElement(ClosePoDetails);
			}catch(Exception e){
				throw new Exception ("Unable to close message response screen. " + e.getMessage());
			}
			
		}else{
			throw new Exception("Unable to locate button to close PO response screen");
		}
	}
	public void postMessageResponse1() throws Exception{
		po=postResultVerification();		
		//driver.switchTo().defaultContent();
		//wh.clickElement(ClosePoDetails);
	}

	public void clickResetButton() throws Exception{
		String stepName = "Click Reset Button";
		try{
			if(!wh.isElementPresent(Reset)){
				throw new Exception("Reset button not visible");
			}
		
			wh.clickElement(Reset);	
			report.addReportStep(stepName, "Clicked Reset button", StepResult.PASS);
		}catch(Exception e){
			String reason = "Unable to click reset button. " + e.getMessage();
			report.addReportStep(stepName, reason, StepResult.FAIL);
			
		}
		
	}
	/**
	 * Method to generate next day based on the parameter
	 * @return NextDay
	 * @throws Exception
	 * @param i, which will passed from 'dynamicXmlPO' method
	 */
	public String nextDay(int i) throws Exception{
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, i);
		c.add(Calendar.MINUTE, i);
		dt = c.getTime();			 
		String NextDay=new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(dt);
		rc.logger.info("Days generated are : "+NextDay);
		
		System.out.println(NextDay);
		return NextDay;
	}		 
	
	/**
	 * This function posts the EDI753 from a given XML path.
	 * We change the PO number to a dynamically-generated number
	 * @param xmlpath
	 * @throws Exception
	 */
	public void postEDI753(String xmlpath) throws Exception{
		
		String currentDate=new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(Calendar.getInstance().getTime()); 
		rc.logger.info("Current date and time :" +currentDate);
		System.out.println(currentDate);
		
		Document documentEDI753 = getFullXmlFromDocument(xmlpath);
		
		NodeList poIdListEDI753 = documentEDI753.getElementsByTagName("PoId"); 
		NodeList pickupStartListEDI753 = documentEDI753.getElementsByTagName("PickupDateStart");
		NodeList pickupEndListEDI753 = documentEDI753.getElementsByTagName("PickupDateEnd");
		NodeList deliveryStartListEDI753 = documentEDI753.getElementsByTagName("DeliveryDateStart");
		NodeList deliveryEndListEDI753 = documentEDI753.getElementsByTagName("DeliveryDateEnd");
		
		String pickupStartPO = getNodesFromXmlDoc(MsgFieldTypes.PICKUPSTART, DataColumn.Poxml).item(0).getTextContent();
		String pickupEndPO = getNodesFromXmlDoc(MsgFieldTypes.PICKUPEND, DataColumn.Poxml).item(0).getTextContent();
		String deliveryStartPO = getNodesFromXmlDoc(MsgFieldTypes.DELIVERYSTART, DataColumn.Poxml).item(0).getTextContent();
		String deliveryEndPO = getNodesFromXmlDoc(MsgFieldTypes.DELIVERYEND, DataColumn.Poxml).item(0).getTextContent();
		
		// Replace value in PoID and dates rows with current PO

		for (int i=0; i<poIdListEDI753.getLength(); i++){
			VPPurchaseOrderPageObject c = new VPPurchaseOrderPageObject(ic);
			String poID = c.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
			poIdListEDI753.item(i).setTextContent(poID);
			
			//poIdListEDI753.item(i).setTextContent(TPEPostMessagePageObject.po);			
		}
		
		for (int i=0; i<pickupStartListEDI753.getLength();i++){
			pickupStartListEDI753.item(i).setTextContent(pickupStartPO);
			
			pickupEndListEDI753.item(i).setTextContent(pickupEndPO);
			
			deliveryStartListEDI753.item(i).setTextContent(deliveryStartPO);
			
			deliveryEndListEDI753.item(i).setTextContent(deliveryEndPO);
		}
				
		updateDocument(documentEDI753, xmlpath);
		
		rc.logger.info("EDI753 was Updated Successfully");
		
		System.out.println("The XML File was Updated Successfully");
	}
	
	private Document getFullXmlFromDocument(String xmlPath) throws Exception{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;
		
		document = documentBuilder.parse(System.getProperty("user.dir") + xmlPath);
		return document;
	}
	
	private void updateDocument(Document document, String xmlPath) throws Exception{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource domSource = new DOMSource(document);			 

		streamResult = new StreamResult(new File(System.getProperty("user.dir") + xmlPath));			 

		transformer.transform(domSource, streamResult);
	}
	
	/**
	 * Method to generate dynamic xml
	 * @param -xml path passed from method 'postMessageXml'
	 * @throws Exception
	 */
	public void dynamicXmlPO(String xmlpath, POTypes poType) throws Exception{
		//PO xml		
		String currentDate=new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(Calendar.getInstance().getTime()); 
		rc.logger.info("Current date and time :" +currentDate);
		System.out.println(currentDate);
		
		Document document = getFullXmlFromDocument(xmlpath);
						
		if (!poType.equals(POTypes.TRACKINGMSG)) {
		orderID = generateId(document, poType);
		System.out.println("Test PO #####################################################:"+orderID);
		
		Node Tagnode = document.getElementsByTagName("OrderId").item(0);                   
		Tagnode.setTextContent(orderID);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();		

		Node Tagnode_1 = document.getElementsByTagName("ExtPurchaseOrderId").item(0);                   
		Tagnode_1.setTextContent(orderID);
		TransformerFactory transformerFactory_1 = TransformerFactory.newInstance();

		Node Tagnode_2 = document.getElementsByTagName("PickupStart").item(0);                   
		Tagnode_2.setTextContent(nextDay(1));
		TransformerFactory transformerFactory_2 = TransformerFactory.newInstance();

		Node Tagnode_3 = document.getElementsByTagName("PickupEnd").item(0);                   
		Tagnode_3.setTextContent(nextDay(3));
		TransformerFactory transformerFactory_3 = TransformerFactory.newInstance();

		Node Tagnode_4 = document.getElementsByTagName("DeliveryStart").item(0);                   
		Tagnode_4.setTextContent(nextDay(2));
		TransformerFactory transformerFactory_4 = TransformerFactory.newInstance();

		Node Tagnode_5 = document.getElementsByTagName("DeliveryEnd").item(0);                   
		Tagnode_5.setTextContent(nextDay(6));
		TransformerFactory transformerFactory_5 = TransformerFactory.newInstance();

		if(dataTable.getData(DataColumn.PoAutoCreateMethodCodeFlg).equalsIgnoreCase("Yes")){
			int value = Math.round(Float.parseFloat(dataTable.getData(DataColumn.PoAutoCreateMethodCodeValue)));
			String pomethodcodeval= String.valueOf(value);
			Node Tagnode_6 = document.getElementsByTagName("PoAutoCreateMethodCode").item(0);                   
			Tagnode_6.setTextContent(pomethodcodeval);
			TransformerFactory transformerFactory_6 = TransformerFactory.newInstance();
		}
		
		Node Tagnode_7 = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
		String DCvalue = dataTable.getData(DataColumn.DistributionCenter);		
			if (!DCvalue.isEmpty()){
			String DCvalue1 = DCvalue.substring(0,4);
			String DistributionCentervalue= String.valueOf(DCvalue1);
			Tagnode_7.setTextContent(DistributionCentervalue);
			TransformerFactory transformerFactory_7 = TransformerFactory.newInstance();
			System.out.println("DC : " +DistributionCentervalue );
			}
		
		}else{
		// Tags for Tracking message XML
		Node Tagnode_8 = document.getElementsByTagName("Shipment_ID").item(0);
		String shipmentNr = TPEDistributionOrderPageObject.getShipmentId();
		Tagnode_8.setTextContent(shipmentNr);
		TransformerFactory transformerFactory_8 = TransformerFactory.newInstance();
		
		Node Tagnode_9 = document.getElementsByTagName("Event_Timestamp").item(0);                   
		//Tagnode_8.setTextContent("12/22/20 21:00:00");
		Tagnode_9.setTextContent(nextDay(3));
		//Convert XML Date to required format
		EventTimeTag = Tagnode_9.getTextContent();		
		DateFormat df = new SimpleDateFormat("M/d/yy HH:mm");
		Date EventDateRaw = df.parse(EventTimeTag);
		EventDateTime = df.format(EventDateRaw);
		System.out.println("XML Formatted date: " + EventDateTime);	
		
		//For comparing XML date with UI date
		TPEShipmentDetailPageObject s = new TPEShipmentDetailPageObject(ic);
		s.Setdate(EventDateTime,"XML");		
		TransformerFactory transformerFactory_9 = TransformerFactory.newInstance();
		
		Node Tagnode_10 = document.getElementsByTagName("Carrier_Code").item(0);
		String carrierCode = TPEShipmentsPageObject.carriershipment;
		Tagnode_10.setTextContent(carrierCode);
		TransformerFactory transformerFactory_10 = TransformerFactory.newInstance();
		
		Node Tagnode_11 = document.getElementsByTagName("Message_Type").item(1);
		MessageTypeTag=Tagnode_11.getTextContent();
		if (MessageTypeTag.equalsIgnoreCase("45")){
			MessageType = "Arrival";
		}else if (MessageTypeTag.equalsIgnoreCase("50")){
			MessageType = "Departure";
		}
		TransformerFactory transformerFactory_11 = TransformerFactory.newInstance();
		
		Node Tagnode_12 = document.getElementsByTagName("Stop_Number").item(0);                   
		//Tagnode_11.setTextContent("12/22/20 21:00:00");
		StopNumber=Tagnode_12.getTextContent();
		TransformerFactory transformerFactory_12 = TransformerFactory.newInstance();

		
		rc.addReportStep("Tracking Message Type", "Tracking Mesage updated with Message Type: " +MessageType+ " and Stop Number: " +StopNumber , StepResult.PASS);
		
		}
		
		updateDocument(document, xmlpath);

		rc.logger.info("XML File was Updated Successfully");
		System.out.println("The XML File was Updated Successfully");
		
			if (!poType.equals(POTypes.TRACKINGMSG)){
			poxmlData(xmlpath);	
			}
		
		}
		
		 
	
	/**
	 * Method to generate random number
	 * @return randnumber
	 * @throws Exception
	 * comment- we are not using it currently	
	 */
	public int randmNumber() throws Exception{
		Random rand = new Random();
		int  randnumber = rand.nextInt(123456789) + 1;
		rc.logger.info("Random PO number generated :" +randnumber);
		System.out.println(randnumber);
		return randnumber;		
	}
	
	/**
	 * Method to extract PO number and generate new number by adding one to existing po number
	 * @return ponumberInc
	 * @throws Exception	
	 */
	public int poxmlExistingPOextract(Document document, POTypes poType) throws Exception{			                 			
		
		Node Tagnode = document.getElementsByTagName("OrderId").item(0);                   
		String OrderId=Tagnode.getTextContent();
		//AUTO0001
		//String ponumber=OrderId.toString();
		int startString = 0;
		int endString = 0;
		switch(poType){
			case FLEET: startString = 5; endString = 9; break;
			case CONS: startString = 5; endString = 9; break;
			case XD: startString = 7; endString = 11; break;
			case STANDARD: startString = 4; endString = 8; break;
			default: startString = 4; endString = 8; break;
		}
		
		String currentMilliS=new SimpleDateFormat("SSS").format(Calendar.getInstance().getTime()); 
		int ponumberInt = Integer.parseInt(currentMilliS);
		
		
		/*String ponumbersplitted=ponumber.substring(startString, endString);
		int ponumberInt = Integer.parseInt(ponumbersplitted);
		int ponumberInc=ponumberInt+1;
		
		if(ponumberInc==9999){
			ponumberInc=1000;
		}*/
		rc.logger.info("Random PO number generated :" +ponumberInt);
		System.out.println("ponumberInt: "+ponumberInt);
		return ponumberInt;		
	}
	public char alpha() throws Exception
	{
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int N = alphabet.length();

	    Random r = new Random();
	    char alpha = alphabet.charAt(r.nextInt(N));
	    System.out.println("ponumberAlp: "+ alpha);
		return alpha;

	}
	/**
	 * Method to get details from xml	
	 * @throws Exception	
	 * @param xmlpath
	 * details such as Order quantity, OriginFacility and OriginFacility
	 */
	public void poxmlData(String xmlpath) throws Exception{
		//PO xml				
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;

		document = documentBuilder.parse(System.getProperty("user.dir") + xmlpath); 				                 			
		
		Node Tagnode = document.getElementsByTagName("OrderQty").item(0);                   
		OrderQty=Tagnode.getTextContent();
		
		Node Tagnode_1 = document.getElementsByTagName("OriginFacilityAliasId").item(0);                   
		OriginFacility=Tagnode_1.getTextContent();
		
		Node Tagnode_2 = document.getElementsByTagName("DestinationFacilityAliasId").item(0);                   
		DestinationFacility=Tagnode_2.getTextContent();	
							
		streamResult = new StreamResult(new File(System.getProperty("user.dir") + xmlpath));				
	}		 
	
	/**
	 * Method to 	
	 * @throws Exception	
	 * @param xmlpath
	 * Details such as Order quantity, OriginFacility and OriginFacility
	 * Method called in postMessageXml
	 */
	public String transformXmltostring(String xmlpath) throws Exception{			 
		DocumentBuilder parser =DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(System.getProperty("user.dir") + xmlpath);
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		Source source = new DOMSource( document );

		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);
		transformer.transform( source, result );  
		StringBuffer sb = outWriter.getBuffer(); 
		String finalstring = sb.toString();
		finalstring = finalstring.replace("\t", "");
		return finalstring;
	}
	
	/**
	 * Method to click Send button in PostMessage page	
	 * @throws Exception
	 */
	public void sendBtn() throws Exception{
		if(wh.isElementPresent(SendBtn)){
			wh.moveToElement(SendBtn);
			wh.jsClick(SendBtn);
			report.addReportStep("Send" , 
					"Successfully sent", 
					StepResult.PASS);	
		}			
	}

	/**
	 * Method to validate response
	 * @return
	 * @throws Exception
	 */
	public String postResultVerification() throws Exception{	
		try{
			String str=wh.getText(PostResultXml);
			
			rc.logger.info("XML response :" +str);

			System.out.println(str);
			
			if (verifyResponseType(str) && verifyResponseCode(str)){
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
				po=doc.getElementsByTagName("Imported_Object_Id").item(0).getTextContent();
			}
		}
		catch(Exception ex){
			rc.logger.info("Post xml screen"+ex);
			System.out.println(ex);
			throw ex;
		}
		return po;
	}
	
	public Boolean verifyResponseType(String fullXmlString){
	
		return verifyValueInXmlString(fullXmlString, "Response_Type", ExpectedResponseType);
	}
	
	public Boolean verifyResponseCode(String fullXmlString){
	
		return verifyValueInXmlString(fullXmlString, "Resp_Code", ExpectedResponseCode);
	}
	
	public Boolean verifyErrorType(String fullXmlString){
		
		return verifyValueInXmlString(fullXmlString, "Error_Type", ExpectedErrorType);
	}

	public Boolean verifyValueInXmlString(String fullXmlString, String elementName, String expectedValue) {
		Boolean verified = false;
		try{
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(fullXmlString)));
			String actualValue=doc.getElementsByTagName(elementName).item(0).getTextContent();
			
			rc.logger.info(elementName + " = " + actualValue);
		
			if (actualValue.equalsIgnoreCase(expectedValue)){
				verified = true;
				report.addReportStep("Verify " + elementName, 
						elementName + " is "+actualValue, 
						StepResult.PASS);
			}else{
				String failMsg = elementName + " is incorrect. Actual " + elementName + " = " + actualValue 
						+ ". Expected " + elementName + " = " + expectedValue;
	
				System.out.println("Not equals");
				report.addReportStep("Verify " , failMsg, StepResult.WARNING);
						 
						
			}
		
		
		}catch(Exception e){
			report.addReportStep("Verify " + elementName + " value", 
					"Unable to verify " + elementName + " value in xml. " + e.getMessage(), 
					StepResult.FAIL);
		}
		
		return verified;
	}
	
	public String postResultVerificationvp() throws Exception
	{
		try{
			String str=wh.getText(PostResultXml);
			rc.logger.info("XML response :" +str);
			System.out.println(str);

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));

			String message = doc.getDocumentElement().getTextContent();
			String responsetype=doc.getElementsByTagName("Response_Type").item(0).getTextContent();
			String responsecode=doc.getElementsByTagName("Resp_Code").item(0).getTextContent();
			
			rc.logger.info("Response type "+responsetype);
			rc.logger.info("Response code "+responsecode);
			
			povp=doc.getElementsByTagName("Imported_Object_Id").item(0).getTextContent();
		}
		catch(Exception ex){
			rc.logger.info("Post xml screen"+ex);
			System.out.println(ex);
		}
		return povp;
	}
	
	/**
	 * Get quantity value from XML file
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Quantity value
	 * @throws Exception
	 */
	public String getQuantityFromXml(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolumeFromXml(lineItemNumber, "quantity");
	}
	
	/**
	 * Get weight value from XML file
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Weight value
	 * @throws Exception
	 */
	public String getWeightFromXml(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolumeFromXml(lineItemNumber, "weight");
	}
	
	/**
	 * Get volume value from XML file
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Volume value
	 * @throws Exception 
	 */
	public String getVolumeFromXml(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolumeFromXml(lineItemNumber, "volume");
	}
	
	/**
	 * This function gets the Quantity, Weight, or Volume from an XML file.
	 * This function may work for additional elements in the future.
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @param unit - Type of unit we want to get. "quantity", "weight", or "volume"
	 * @return Quantity, Weight, or Volume Value
	 * @throws Exception 
	 */
	private String getQuantityWeightOrVolumeFromXml(Integer lineItemNumber, String unit) throws Exception {
		
		String fullUnitString = null;
		
		try{
			String location = dataTable.getData(DataColumn.Poxml);
			
			String unitNameInXML = null;
			String unitOfMeasure = null;
			
			// Look for different elements in XML based on unit desired
			
			if(unit.toLowerCase().equals("quantity")){
				unitNameInXML = "OrderQty";
				unitOfMeasure = "QtyUOM";
			}else if(unit.toLowerCase().equals("weight")){
				unitNameInXML = "PlannedWeight";
				unitOfMeasure = "WeightUOM";
			}else if (unit.toLowerCase().equals("volume")){
				unitNameInXML = "PlannedVolume";
				unitOfMeasure = "VolumeUOM";
			}else{
				throw new Exception("Unrecognized input for unit type. Passed input = [" + unit + "]");
			}
			
			// Grab the elements from the XML document
			Document document = getFullXmlFromDocument(location);
			String unitAmount = document.getElementsByTagName(unitNameInXML).item(lineItemNumber).getTextContent();
			String uom = document.getElementsByTagName(unitOfMeasure).item(lineItemNumber).getTextContent();

			float unitInFloat = Float.parseFloat(unitAmount);
			
			fullUnitString = Float.toString(unitInFloat) + " " + uom;
			
		}catch(Exception e){
			rc.addReportStep("Get " + unit + " from XML", "Unable to get " + unit + " from XML " 
						+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		return fullUnitString;
	}
	
	public Integer getRtsCountFromEDI753() throws Exception{
		Integer amount = 0;
		try{
			// Open EDI753
			
			String location = dataTable.getData(DataColumn.EDI753);
			
			// Grab the elements from the XML document
			Document document = getFullXmlFromDocument(location);
			NodeList rtsList = document.getElementsByTagName("RtsDetail");
			amount = rtsList.getLength();
		}catch(Exception e){
			report.addReportStep("Get RTS Count from EDI753", "Could not get RTS Count from EDI753. " 
					+ e.getMessage(), StepResult.FAIL);
		}
		
		
		return amount;
	}
	
	public NodeList getNodesFromXmlDoc(MsgFieldTypes fieldType, String xmlType) throws Exception{

		NodeList list = null;
		try{
			
			String location = dataTable.getData(xmlType);
			
			// Grab the elements from the XML document
			Document document = getFullXmlFromDocument(location);
			list = document.getElementsByTagName(fieldType.getValue());
		}catch(Exception e){
			report.addReportStep("Get " + fieldType.getValue() + " from " + xmlType + " xml file", 
					"Could not get " + fieldType.getValue() + " from " + xmlType + " xml file." 
					+ e.getMessage(), StepResult.FAIL);
		}
		
		
		return list;
	}
	
	/**
	 * This function generates the specific XML needed for a test. The generation happens
	 * differently based on the type of XML desired.
	 * @param xmlType Type of XML desired (i.e. "po", "edi753", etc)
	 * @throws Exception 
	 */
	public void generateXMLByType(String type, POTypes poType) throws Exception{
		try{
			if (type.equals(DataColumn.Poxml)){
				dynamicXmlPO(dataTable.getData(type), poType);
			}else if(type.equals(DataColumn.EDI753)){
				postEDI753(dataTable.getData(type));
			}else if(type.equals(DataColumn.xml2)){
				dynamicXmlPO(dataTable.getData(type), poType);
			}
			else{
				throw new Exception("PO Type, " + type + ", is not recognized.");
			}
		}catch(Exception e){
			throw new Exception("Unable to generate XML. " + e.getMessage());
		}
	}
	
	private void addFileToReport(String type) throws IOException{

		File src = new File(System.getProperty("user.dir") + dataTable.getData(type));

		String dstString = getDataFolderLocation() + "/" + getXMLFileLocationForReport(type);
		File dst = new File(dstString);
		
		FileUtils.copyFile(src, dst);
		rc.logger.info("XML file saved FROM: [" + src.getPath() + "] TO: [" + dst.getPath() + "]");
	}
	
	private String getDataFolderLocation(){
		String dataFolder = dataTable.getCurrentTestCase().reportFolder + "/Data/";
		File dst = new File(dataFolder);
		return dst.getPath();

	}
	
	private String generateId(Document document, POTypes poType) throws Exception{
		int poextract=poxmlExistingPOextract(document, poType);
		char alpha = alpha();
		Node Tagnode_dest = document.getElementsByTagName("DestinationFacilityAliasId").item(0);  
		
		String dateFormat=new SimpleDateFormat("MMddyy").format(Calendar.getInstance().getTime());
		String idBeginning = "";
		
		switch(poType){
			case CONS: idBeginning = "EngPO"; break;
			case FLEET: idBeginning = "EngPO"; break;
			case XD: idBeginning = "EngPOXD"; break;
			case STANDARD: idBeginning = "AUTO"; break;
			default: idBeginning = "AUTO"; break;
		}
		String orderID=idBeginning+poextract+alpha+Tagnode_dest.getTextContent()+dateFormat;
		return orderID;	
	}
	
	public Boolean isMaximized() throws Exception{
		Boolean isMaximized =null;
		try{
			driver.switchTo().defaultContent();
			WebElement maximizeImg = driver.findElement(MaximizeScreen);
			WebElement maximizeContainer = maximizeImg.findElement(By.xpath(".."));
			isMaximized = maximizeContainer.getAttribute("style").contains("display: none");
		
		}catch(Exception e){
			throw new Exception("Unable to check to see if 'Post Message' screen is maximized" + e.getMessage());
		}
		
		return isMaximized;
	}
	
	public void maximizeScreen() throws Exception{
		try{
			if (!isMaximized()){
				
				Thread.sleep(1000);
				if (!wh.isElementPresent(MaximizeScreen)){
					throw new Exception("Maximize Screen element is not present");
				}
				wh.clickElement(MaximizeScreen);
			}	
		}catch(Exception e){
			throw new Exception("Unable to maximize screen. " + e.getMessage());
		}
	}
	/**
	 * This function switches to the 'Web Tenders' frame
	 * @throws Exception 
	 */
	public void switchToPostMessageFrame() throws Exception{
		String description = "Bring up Post Message Frame";
		try{
			driver.switchTo().defaultContent();
			if(!wh.isElementPresent(POSTMESSAGEFRAME)){
				throw new Exception("Unable to find 'Filter List' Frame.");
			}
			driver.switchTo().frame(driver.findElement(POSTMESSAGEFRAME));
		}catch(Exception e){
			rc.addReportStep(description, "Unable to bring up Web Tenders frame. " + e.getMessage(), StepResult.FAIL);
		}
		
	}
}