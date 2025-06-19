package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.filters.TokenFilter.DeleteCharacters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class VPRTSListPageObject extends  PageBase {

	public VPRTSListPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	static final By MaximizeScreenRTS=By.xpath(".//span[contains(text(),'RTS List - Add Aggregated Routing Request')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By Refresh=By.xpath(".//*[contains(text(),'RTS List - Add Aggregated Routing Request')]/../following-sibling::div[1]/img");
	static final By RTSsave=By.xpath(".//*[@id='dataForm:readyToShipLink']");
	static final By RTSsave1 = By.xpath(".//*[@id='workareafooter']/div/div/input[2]");
	static final By RTSsaveack = By.xpath(".//*[@id='dataForm:pageHeaderId']");
	static final By ViewBtn=By.xpath(".//span[contains(text(),'View')]/following-sibling::span");
	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By RTStab=By.id("PODetail_RTS_Tab_lnk");
	static final By RTSnumber=By.xpath("(.//table[contains(@id,'rtsDataTable_body')]//td)[2]");
	static final By RTSnumber2=By.xpath("(.//table[contains(@id,'rtsDataTable_body')]//td[2])[2]");
	static final By CloseScreen=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By PickupStart = By.xpath("//input[@id='dataForm:headerPickupStartDTTM']");
	static final By PickupEnd = By.xpath(".//input[@id='dataForm:headerPickupEndDTTM']");
	static final By RTSPickupStart=By.xpath("(.//table[contains(@id,'rtsDataTable_body')]//td)[8]");
	static final By RTSAlert=By.xpath(".//*[@id='er_d1_bid']/ul/li[1]");
	static final By RTSAlert2=By.xpath(".//*[@id='er_d1_bid']/ul/li[2]");
	static final By RTSAlertClose=By.xpath(".//*[@id='er_d1_c1']");
	static final By RTSQuantity=By.xpath(".//*[contains (@id,'dataForm:AggRTSHeaderList_POLI:poListTable:0:rtsQtyValId')]");
	static final By RTSQuantity1=By.xpath(".//*[contains (@id,'dataForm:AggRTSHeaderList_POLI:poListTable:1:rtsQtyValId')]");
	static final By RTSQuantityinPOScreen=By.xpath(".//*[@id='dataForm:lvrtsLineItems1:rtsDataTable:0:cd9']");
	static final By RTSCapacity=By.xpath(".//input[@id='dataForm:totalRequestedWeightId']");
	static final By RTSCubingTrailer=By.xpath(".//input[@id='dataForm:mocValueId']");
	static final By RTSViewCheck=By.xpath(".//input[@id='checkAll_c0_dataForm:lvrtsLineItems1:rtsDataTable']");
	static final By RTSView=By.xpath(".//input[@id='dataForm:ViewRTS']");
	static final By RTSPoDetail=By.xpath(".//span[contains (text(),'PO Detail - Ready to Ship')]");
	static final By RTSLineItems=By.xpath(".//a[@id='Line_Items_Tab_lnk']");
	static final By RTSPoWeight=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:0:RTSView_Line_OutText_WEI_OutText']");
	static final By RTSPoWeight1=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:1:RTSView_Line_OutText_WEI_OutText']");
	static final By RTSPoVolume=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:0:RTSView_Line_OutText_VOL_OutText']");
	static final By RTSPoVolume1=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:1:RTSView_Line_OutText_VOL_OutText']");
	static final By RTSPoID=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:0:RTSView_Line_OutText_POID_OutText']");
	static final By RTSPoID1=By.xpath(".//*[@id='dataForm:RTSView_Line_dataTable:1:RTSView_Line_OutText_POID_OutText']");
	
	String nextdate;
	String Enddateadd;
	String calc ;
	String add ;
	
	
	public void datechanges() throws Exception
	{
		wh.clickElement(MaximizeScreenRTS);
		Thread.sleep(2000);
	/*	driver.switchTo().frame(1);
		driver.switchTo().defaultContent();
		driver.switchTo().activeElement();*/
		datevalidation();
		Enddateadd();
		startDateExcep();
		nextdate();
		pickUpdate();
	}
	public  void rtsreadytoship() throws Exception
	{
	
		
		try{
			/*driver.switchTo().frame(1);
			driver.switchTo().activeElement();*/
			
		if(wh.isElementPresent(RTSsave1))
		{
			wh.clickElement(RTSsave1);
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
			
		}
	public  String calculations(String input) throws Exception
	{
		Double rtsQuantity = Double.parseDouble(input); 
		System.out.println("rtsQuantity:"+rtsQuantity);
		Double divi = rtsQuantity/2;
		System.out.println("s_calc:"+divi);
		calc = divi.toString();
		return calc;
	}
	public  String add(String input) throws Exception
	{
		Double rtsQuantity = Double.parseDouble(input); 
		System.out.println("rtsQuantity:"+rtsQuantity);
		Double adding = rtsQuantity+10;
		System.out.println("s_add:"+adding);
		add = adding.toString();
		return add;
	}
	public void rtsQuantity() throws Exception
	{
		wh.clickElement(MaximizeScreenRTS);
		Thread.sleep(2000);
		wh.clickElement(Refresh);
		Thread.sleep(2000);
		
		try
		{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
		
				if(wh.isElementPresent(RTSQuantity, 2000))
			{
				wh.clearElement(RTSQuantity);
				wh.sendKeys(RTSQuantity, calc);
				wh.clearElement(RTSQuantity1);
				wh.sendKeys(RTSQuantity1, calc);
				
				report.addReportStep("Changing the RTS Quantities in RTS List Screen ",
						"RTS Quantities values are changed", 
						StepResult.PASS);
			}
				else
			{
					report.addReportStep("Trying to change the RTS Quantities in RTS List Screen ",
							"Not able to locate the quanties", 
							StepResult.FAIL);	
					rc.terminateTestCase("RTS Quantity Change in the RTS List");
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void rtsQuantityLimit() throws Exception
	{
		wh.clickElement(MaximizeScreenRTS);
		Thread.sleep(2000);
		wh.clickElement(Refresh);
		Thread.sleep(2000);
		
		try
		{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
		
				if(wh.isElementPresent(RTSQuantity, 2000))
			{
				wh.clearElement(RTSQuantity);
				wh.sendKeys(RTSQuantity, add);
				/*wh.clearElement(RTSQuantity1);
				wh.sendKeys(RTSQuantity1, calc);*/
				
				report.addReportStep("Changing the RTS Quantities in RTS List Screen ",
						"RTS Quantities values are changed", 
						StepResult.PASS);
			}
				else
			{
					report.addReportStep("Trying to change the RTS Quantities in RTS List Screen ",
							"Not able to locate the quanties", 
							StepResult.FAIL);	
					rc.terminateTestCase("RTS Quantity Change in the RTS List");
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void rtsCapacity() throws Exception
	{
		wh.clickElement(MaximizeScreenRTS);
		Thread.sleep(2000);
		wh.clickElement(Refresh);
		Thread.sleep(2000);
		
		try
		{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
			
			if(wh.isElementPresent(RTSCapacity, 4000))
			{
				String s = wh.getAttribute(RTSCapacity, "value");
				report.addReportStep("Checking the RTS Capacity in RTS List Screen ",
						"RTS Capacity available in Sceen is :"+s, 
						StepResult.PASS);
				
				wh.clearElement(RTSCapacity);
				wh.sendKeys(RTSCapacity, TPEDistributionOrderPageObject.PlannedWeight);
				
				report.addReportStep("Changing the RTS Capacity in RTS List Screen ",
						"RTS Capacity changed too :"+TPEDistributionOrderPageObject.PlannedWeight, 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Checking the RTS Capacity in RTS List Screen ",
						"RTS Capacity is Not available", 
						StepResult.FAIL);
				rc.terminateTestCase("At RTS Capacity change");
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void rtsCubingTrailer()throws Exception
	{
		try
		{
			if(wh.isElementPresent(RTSCubingTrailer, 4000))
			{
				String s = wh.getAttribute(RTSCubingTrailer, "value");
				report.addReportStep("Checking the RTS Cubing Trailer in RTS List Screen ",
						"RTS Cubing Trailer available in Sceen is :"+s, 
						StepResult.PASS);
				
				wh.clearElement(RTSCubingTrailer);
				wh.sendKeys(RTSCubingTrailer, TPEDistributionOrderPageObject.PlannedVolume);
				
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
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public  void pickUpdate() throws Exception
	{
		try{
			/*driver.switchTo().frame(1);
			driver.switchTo().activeElement();*/
			
			if(wh.isElementPresent(PickupStart, 4000))
			{
				wh.clearElement(PickupStart);
				wh.sendKeys(PickupStart, nextdate);
				report.addReportStep("Changing the Pickup Start Date",
						"The Pickup Start Date is Changed : Date Entered is "+nextdate+"", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Changing the Pickup Start Date",
						"Not able to Change the Pickup Start Date" , 
						StepResult.FAIL);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	public  void startDateExcep() throws Exception
	{
		try{
			/*driver.switchTo().frame(1);
			driver.switchTo().activeElement();*/
			
			if(wh.isElementPresent(PickupStart, 4000))
			{
				wh.clearElement(PickupStart);
				wh.sendKeys(PickupStart, Enddateadd);
				report.addReportStep("Changing the Pickup Start Date",
						"The Pickup Start Date is Changed, Date is incremented than the End Date", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Changing the Pickup Start Date",
						"Not able to Change the Pickup Start Date" , 
						StepResult.FAIL);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		try{
			/*driver.switchTo().frame(1);
			driver.switchTo().activeElement();*/
			
			if(wh.isElementPresent(RTSsave1))
			{
				wh.clickElement(RTSsave1);
				Thread.sleep(4000);
			
				report.addReportStep("Selected the RTS List to Save",
					"Clicked on RTS Ready to Save", 
					StepResult.PASS);
			}
			else
			{
				report.addReportStep("Selecting the RTS List to Save",
					"Not able to select RTS Ready to Save", 
					StepResult.FAIL);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
			
			if(wh.isElementPresent(RTSAlert, 5000))
			{
				if(wh.getText(RTSAlert).equalsIgnoreCase("Pickup Start cannot be after Pickup End."))
				{
					report.addReportStep("Alert Message",
						"Alert popup with message Pickup Start cannot be after Pickup End.", 
						StepResult.PASS);
				}
				else
				{
				report.addReportStep("Entered the Pickup Start Date after Pick up End date ",
						"Alert popup with message is not as Excepted", 
						StepResult.WARNING);

				}
			}
			else
			{
				report.addReportStep("Waiting for Alert Message ",
					"Alert Message not displayed", 
					StepResult.WARNING);
			}
		
				wh.clickElement(RTSAlertClose);
	}
	public void nextdate() throws Exception{					
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 2);
		dt = c.getTime();	
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy HH:mm");
		System.out.println("Unformatted date" + dt);
		nextdate = sdf.format(dt);
		System.out.println("nextdate:"+nextdate);
		//strdate = format(dt);
		rc.logger.info("Event data entered is: "+nextdate);					
	}
	public void Enddateadd() throws Exception{					
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 4);
		dt = c.getTime();	
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy HH:mm");
		System.out.println("Unformatted date" + dt);
		Enddateadd = sdf.format(dt);
		System.out.println("nextdate:"+nextdate);
		//strdate = format(dt);
		rc.logger.info("Event data entered is: "+nextdate);					
	}
	public  void datevalidation() throws Exception
	{
		try{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();
			
		if(wh.isElementPresent(PickupStart, 4000))
		{
			String pickupstart = wh.getAttribute(PickupStart, "value");
			System.out.println("pickupdateUI:"+pickupstart);
			String pickupxmlStart = TPEDistributionOrderPageObject.PickupStart;
			System.out.println("pickupxmlStart :"+pickupxmlStart);
			
			if(pickupstart.equalsIgnoreCase(pickupxmlStart))
			{
				report.addReportStep("Validating the Start date with the PO Creation Start date",
						"The Start date is Matched:"+pickupstart+"", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Validating the Start date with the PO Creation Start date",
						"Not able to match the start date:"+pickupstart+"", 
						StepResult.WARNING);
			}
			
			/*String pickupXmlStart = pickupxmlStart.substring(0, 14);
			System.out.println("pickupXmlStartXml:"+pickupXmlStart);*/
			/*char[] myChar = pickupXmlStart.toCharArray();
				
			
			for (int i=0;i<=myChar.length;i++)
			{
				output.add(myChar[i]);
			}
			
			
			if (myChar[3]=='0')
			{
				myChar[3]=' ';
				pickupXmlStart =String.valueOf(myChar);
				System.out.println("pickupXmlStartChar:"+pickupXmlStart);
			}
			if (myChar[0]=='0')
			{
				pickupXmlStart =String.valueOf(myChar);
				System.out.println("pickupXmlStartChar:"+pickupXmlStart);
			}

			char Sub = pickupXmlStart.charAt(0);
			System.out.println("Sub:"+Sub);
			
			char Sub1 = pickupXmlStart.charAt(3);
			System.out.println("Sub1:"+Sub1);
				
			if(pickupXmlStart.substring(1).equalsIgnoreCase("0"))
			{
				String Test = pickupXmlStart.replaceAll(pickupXmlStart.substring(1),"");
				System.out.println("Test:"+Test);
				String s = pickupXmlStart.substring(0);
				pickupXmlStart.replaceAll(s, "");
			}
			if(pickupXmlStart.substring(3).equalsIgnoreCase("0"))
			{
				pickupXmlStart = pickupXmlStart.replaceAll(pickupXmlStart.substring(3),"");
				String s = pickupXmlStart.substring(3);
				pickupXmlStart.replaceAll(s, "");
			}*/
			
		}
		if(wh.isElementPresent(PickupEnd, 4000))
		{
			String pickupend = wh.getAttribute(PickupEnd, "value");
			System.out.println("pickupEndUI:"+pickupend);
			String pickupxmlEnddate = TPEDistributionOrderPageObject.PickupEnd;
			System.out.println("pickupxmlEnddate:"+pickupxmlEnddate);
			/*String pickupXmlEnddate = pickupxmlEnddate.substring(0, 14);
			System.out.println("pickupXmlEnddateXml:"+pickupXmlEnddate);*/
			if(pickupend.equalsIgnoreCase(pickupxmlEnddate))
			{
				report.addReportStep("Validating the End date with the PO Creation End date",
						"The End date is Matched:"+pickupend+"", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Validating the End date with the PO Creation End date",
						"Not able to match the end date:"+pickupend+"", 
						StepResult.WARNING);
			}
		}
		}
		catch (Exception e)
		{
			rc.terminateTestCase("Datevalidation Failed:"+e+"", StepResult.WARNING);
			System.out.println(e);
		}
	}
	
     public void rtsacknowledgement() throws Exception
		{
			//Capturing RTS acknowledgement from the screen and validating
			WebElement ack =driver.findElement(RTSsaveack);
			if(wh.isElementPresent(RTSsaveack))
			{
				String result =ack.getText();
				System.out.println(result);
				if(result.equalsIgnoreCase("RTS Creation Acknowledgement"))
					
					report.addReportStep("Receive RTS Creation Acknowledgement",
							"RTS Creation is done and Acknowledgement Received", 
							StepResult.PASS);
					
				wh.clickElement(By.xpath(".//*[@id='dataForm:closeLink']"));
				Thread.sleep(2000);
			}
		}
     public void rtsVerifications() throws Exception
 	{
 		//Verification of RTS Creation and recording the RTS Number generated
 		if(wh.isElementPresent(ViewBtn, 10)){
 			wh.clickElement(ViewBtn);
 			Thread.sleep(2000);
 		}
 		wh.clickElement(MaximizePoDetails);	
 		
 		try{
 			driver.switchTo().frame(0);
 			driver.switchTo().activeElement();	
 		if(wh.isElementPresent(RTStab, 3)){			
 			wh.moveToElement(RTStab);
 			wh.clickElement(RTStab);			
 							
 				String rtsnumber=wh.getText(RTSnumber);
 				if(!rtsnumber.equalsIgnoreCase("")){						
 					rc.logger.info("RTS number"+rtsnumber);
 					report.addReportStep("Verify RTS number should be generated", 
 							"RTS number is available '"+rtsnumber+"'", 
 							StepResult.PASS);
 										
 						
 			}
 				else
 					report.addReportStep("Verify RTS number should be generated", 
 							"RTS number is Not available", 
 							StepResult.FAIL);
 				wh.clickElement(CloseScreen);
 		}
 		
 		}
 		catch (Exception e)
 		{
 			rc.logger.error("Exception in PO details verification"+e);
 		}
 		
 	}
	public void rtsVerification() throws Exception
	{
		//Verification of RTS Creation and recording the RTS Number generated
		driver.switchTo().defaultContent();
		driver.switchTo().activeElement();
		
		if(wh.isElementPresent(ViewBtn, 10)){
			wh.clickElement(ViewBtn);
			Thread.sleep(4000);
			
		}
		else
		{
			report.addReportStep("Searching View ", 
					"Not able to find the view button", 
					StepResult.FAIL);
		}
		wh.waitForPageLoaded();
		//wh.clickElement(MaximizePoDetails);	
		
			try{

				wh.clickElement(MaximizePoDetails);
				driver.switchTo().frame(0);
				driver.switchTo().activeElement();	
					if(wh.isElementPresent(RTStab, 3)){			
						wh.moveToElement(RTStab);
						wh.clickElement(RTStab);				
		}
								
		}
		catch (Exception e)
		{
			rc.logger.error("Exception in PO details verification"+e);
		}
		
	}
	
	public void rtsNumber() throws Exception
	{
		String rtsnumber=wh.getText(RTSnumber);
		if(!rtsnumber.equalsIgnoreCase("")){						
			rc.logger.info("RTS number"+rtsnumber);
			report.addReportStep("Verify RTS number should be generated", 
				"RTS number is available '"+rtsnumber+"'", 
				StepResult.PASS);	
		}
		else
		{
			report.addReportStep("Verify RTS number should be generated", 
				"RTS number is Not available", 
				StepResult.FAIL);
		}
	}
	public void rtsQuantities() throws Exception
	{
		if(wh.isElementPresent(RTSQuantityinPOScreen, 4000))
				{
					String s = wh.getText(RTSQuantityinPOScreen).replaceAll("[A-Z]", " ").trim();
					System.out.println("RTSQuantityinPOScreen:"+s);
					if(s.equalsIgnoreCase(calc))
					{
						report.addReportStep("Verifying the RTS Quantities in PO Screen ",
								"RTS Quantities are matching,The Quanties are:"+s, 
								StepResult.PASS);
					}
					else
					{
						report.addReportStep("Verifying the RTS Quantities in PO Screen ",
								"RTS Quantities are Not matching,The Quanties are:"+s, 
								StepResult.FAIL);
					}
				}
		else
		{
			report.addReportStep("Verifying the RTS Quantities in PO Screen ",
					"RTS Quantities grid Not Found", 
					StepResult.FAIL);
		}
	}
	public void twortsNumber() throws Exception
	{
		String rtsnumber=wh.getText(RTSnumber);
		String rtsnumber2=wh.getText(RTSnumber2);
		if(!rtsnumber.equalsIgnoreCase("")){						
			rc.logger.info("RTS number"+rtsnumber);
			report.addReportStep("Verify RTS number should be generated", 
				"RTS number is available '"+rtsnumber+"'", 
				StepResult.PASS);	
		}
		else
		{
			report.addReportStep("Verify RTS number should be generated", 
				"RTS number is Not available", 
				StepResult.FAIL);
		}
		if(!rtsnumber2.equalsIgnoreCase("")){						
			rc.logger.info("RTS number"+rtsnumber2);
			report.addReportStep("Verify RTS number should be generated", 
				"RTS number is available '"+rtsnumber2+"'", 
				StepResult.PASS);	
		}
		else
		{
			report.addReportStep("Verify RTS number should be generated", 
				"Second RTS number is Not available", 
				StepResult.FAIL);
		}
	}
	public void rtsClose() throws Exception
	{
		driver.switchTo().defaultContent();
		wh.clickElement(CloseScreen);
	}
	public void viewRTS() throws Exception
	{
		if(wh.isElementPresent(RTSViewCheck, 4000))
		{
			wh.clickElement(RTSViewCheck);
			if(wh.isElementPresent(RTSView, 7000))
			{
				wh.clickElement(RTSView);
				wh.waitForPageLoaded();
				report.addReportStep("Selecting the RTS and clicking on View RTS",
						"RTS was selected and View RTS was clicked", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Trying to click on View RTS",
						"Not able to click the View RTS", 
						StepResult.FAIL);
			}
		}
		else
		{
			report.addReportStep("Trying to select the check box in RTS Details Screen ",
					"Not able to select the RTS checkbox", 
					StepResult.FAIL);
		}
	}
	public void poDetailRTS() throws Exception
	{
		
		try
		{
			driver.switchTo().defaultContent();
			driver.switchTo().activeElement();
			if(wh.isElementPresent(RTSPoDetail, 4000))
			{
				if(wh.getText(RTSPoDetail).equalsIgnoreCase("PO Detail - Ready to Ship"))
				{
					report.addReportStep("Moving to PO Detail - Ready to Ship Screen",
							"PO Detail - Ready to Ship Screen page is loaded", 
							StepResult.PASS);
				}
			}
			else
			{
					report.addReportStep("Moving to PO Detail - Ready to Ship Screen",
							"Not able to locate PO Detail - Ready to Ship Screen", 
							StepResult.FAIL);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void lineItems() throws Exception
	{
		try
		{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();	
			if(wh.isElementPresent(RTSLineItems, 4000))
			{
				
				//wh.jsClick(RTSLineItems);
				wh.clickElement(RTSLineItems);
				Thread.sleep(2000);
				report.addReportStep("Select RTS Line Items Tab",
						"Selected RTS Line Items Tab", 
						StepResult.PASS);
			}
			else
			{
				report.addReportStep("Select RTS Line Items Tab",
						"Not able to selected RTS Line Items Tab", 
						StepResult.FAIL);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void rtsWeight() throws Exception
	{
		try
		{
			if(wh.isElementPresent(RTSPoID, 4000))
			{
				String s = wh.getText(RTSPoWeight).replaceAll("[A-Z]", " ").replaceAll(",", "").trim();
				System.out.println("RTSPoWeight:"+s);
				if(s.equalsIgnoreCase(calc))
				{
					report.addReportStep("Validating the Weight Calculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID)+" the Weight distribution is:"+s+" and calculation is matching with the Weights Entered", 
							StepResult.PASS);
				}
				else
				{
					report.addReportStep("Validating the Weight Calculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID)+" the Weight distribution is:"+s+" and calculation is Not matching with the Weights Entered", 
							StepResult.FAIL);
				}
				
			}
			else
			{
				report.addReportStep("Checking for the POID in PO Detail - RTS Screen",
						"Not able to find the POID", 
						StepResult.FAIL);
			}
			
			if(wh.isElementPresent(RTSPoID1, 4000))
			{
				String p = wh.getText(RTSPoWeight1).replaceAll("[A-Z]", " ").replaceAll(",", "").trim();
				System.out.println("RTSPoWeight1:"+p);
				if(p.equalsIgnoreCase(calc))
				{
					report.addReportStep("Validating the Weight Calaculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID1)+" the Weight distribution is:"+p+" and calculation is matching with the Weights Entered", 
							StepResult.PASS);
				}
				else
				{
					report.addReportStep("Validating the Weight Calaculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID1)+" the Weight distribution is:"+p+" and calculation is Not matching with the Weights Entered", 
							StepResult.FAIL);
				}
			}
			
			else
			{
				report.addReportStep("Checking for the POID in PO Detail - RTS Screen",
						"Not able to find the POID", 
						StepResult.WARNING);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void rtsVolume() throws Exception
	{
		try
		{
			if(wh.isElementPresent(RTSPoID, 4000))
			{
				
				String[] s = wh.getText(RTSPoVolume).split(" ");
				System.out.println("RTSPoWeight:"+s[0]);
				if(s[0].trim().equalsIgnoreCase(calc))
				{
					report.addReportStep("Validating the Volume Calculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID)+" the Volume distribution is:"+s[0]+" and calculation is matching with the Volume Entered", 
							StepResult.PASS);
				}
				else
				{
					report.addReportStep("Validating the Volume Calculations in Line Items Tab",
							"For the POID: "+wh.getText(RTSPoID)+" the Volume distribution is:"+s[0]+" and calculation is Not matching with the Volume Entered", 
							StepResult.FAIL);
				}
			}
			
			else
			{
				report.addReportStep("Checking for the POID in PO Detail - RTS Screen",
						"Not able to find the POID", 
						StepResult.FAIL);
			}
				
			
			if(wh.isElementPresent(RTSPoID1, 4000))
				{	
					String[] p = wh.getText(RTSPoVolume1).split(" ");
					System.out.println("RTSPoWeight:"+p[0]);
					if(p[0].trim().equalsIgnoreCase(calc))
					{
						report.addReportStep("Validating the Volume Calculations in Line Items Tab",
								"For the POID: "+wh.getText(RTSPoID1)+" the Volume distribution is:"+p[0]+" and calculation is matching with the Volume Entered", 
								StepResult.PASS);
					}
					else
					{
						report.addReportStep("Validating the Volume Calculations in Line Items Tab",
								"For the POID: "+wh.getText(RTSPoID1)+" the Volume distribution is:"+p[0]+" and calculation is Not matching with the Volume Entered", 
								StepResult.FAIL);
					}
				
				}
			else
			{
				report.addReportStep("Checking for the POID in PO Detail - RTS Screen",
						"Not able to find the POID", 
						StepResult.WARNING);
			}
			
			
		}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
	public void rtsPickUpStart() throws Exception
	{
		String rtsstartdate = wh.getText(RTSPickupStart);
		String s = rtsstartdate.replaceAll("[A-Z]", " ");
		//System.out.println("Test :"+s);
						
		if(nextdate.trim().equalsIgnoreCase(s.trim()))
		{
			rc.logger.info("RTS Picup Date"+s);
			report.addReportStep("Verify RTS Pickup Start Date", 
					"RTS Date is available and Updated Successfully, Date: '"+s+"'", 
					StepResult.PASS);
		}
		else
		{
			report.addReportStep("Verify RTS Pickup Start Date", 
					"RTS Date is available is Not Updated, Date: '"+s+"'", 
					StepResult.FAIL);
		}
	}
	public  void rtsreadytoshipvp() throws Exception
	{
		wh.clickElement(MaximizeScreenRTS);
	/*	driver.switchTo().frame(1);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.switchTo().activeElement();
		
		Thread.sleep(2000);
		//wh.clickElement(RTSsave);
		wh.clickElement(RTSsave1);
		Thread.sleep(4000);*/
		try{
			driver.switchTo().frame(0);
			driver.switchTo().activeElement();	
		if(wh.isElementPresent(RTSsave1))
		{
			report.addReportStep("View RTS List screen",
					"RTS Ready to Save", 
					StepResult.PASS);
			
			wh.clickElement(RTSsave1);
			report.addReportStep("Selected the RTS List to Save",
					"Clicked on RTS Ready to Save", 
					StepResult.PASS);
			
			Thread.sleep(4000);	
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
			
		}
	public void limitAlert() throws Exception
	{
	if(wh.isElementPresent(RTSAlert, 5000))
	{
		if(wh.getText(RTSAlert).equalsIgnoreCase("Error on line item ID 0001: The Ready To Ship Quantity cannot exceed the Total Ordered Quantity."))
		{
			report.addReportStep("Alert Message",
				"Alert popup with message: The Ready To Ship Quantity cannot exceed the Total Ordered Quantity.", 
				StepResult.PASS);
		}
		else
		{
		report.addReportStep("Entered the Pickup Start Date after Pick up End date ",
				"Alert popup with message is not as Excepted", 
				StepResult.FAIL);

		}
	}
	else
	{
		report.addReportStep("Waiting for Alert Message ",
			"Alert Message not displayed", 
			StepResult.WARNING);
	}
	if(wh.isElementPresent(RTSAlert2, 5000))
	{
		if(wh.getText(RTSAlert2).equalsIgnoreCase("Error on line item ID 0001: The Total Selected Quantity cannot exceed the difference between the current Ready To Ship Quantity and the Total Ordered."))
		{
			report.addReportStep("Alert Message",
				"Alert popup with message: The Total Selected Quantity cannot exceed the difference between the current Ready To Ship Quantity and the Total Ordered.", 
				StepResult.PASS);
		}
		else
		{
		report.addReportStep("Entered the Pickup Start Date after Pick up End date ",
				"Alert popup with message is not as Excepted", 
				StepResult.FAIL);

		}
	}
	else
	{
		report.addReportStep("Waiting for Alert Message 2",
			"Alert Message 2 is not displayed", 
			StepResult.WARNING);
	}
		wh.clickElement(RTSAlertClose);
}
}
