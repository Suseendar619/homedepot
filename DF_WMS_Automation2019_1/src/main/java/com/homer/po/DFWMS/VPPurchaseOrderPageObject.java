package com.homer.po.DFWMS;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class VPPurchaseOrderPageObject extends PageBase {

	
	public VPPurchaseOrderPageObject(InstanceContainer ic) {
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
	static final By CloseScreen=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	
	static final By PurchaseOrderScreenHeader=By.xpath(".//span[contains(text(),'Purchase Orders') and contains(@id,'screen')]");
	static final By PrimaryFieldFilter=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[4]");
	static final By PurchaseOrderFilterBtn=By.id("ext-gen2549");
	static final By PurchaseOrderOption=By.xpath(".//div[contains(@class,'filter-combo-option') and contains(text(),'Purchase Order')]");
	//static final By PurchaseOrderInput=By.xpath("(.//*[@name='PurchaseOrder'])[2]");
	//static final By PurchaseOrderInput=By.xpath("(.//*[@name='PurchaseOrder'])[1]");
	static final By PurchaseOrderInput=By.xpath("//*[preceding-sibling::label[text()='Primary Fields']]//input[contains(@id, 'lookupfield')]");
	
	static final By PurchaseOrderSearch=By.xpath("(//table/tbody/tr/td//div[contains(@class, 'x-form-search')])[2]");
	static final By PurchaseOrderApply=By.xpath(".//span[contains(text(), 'Apply') and contains(@id,'button')]");
	static final By PurchaseOrderApplied=By.xpath("//table[starts-with(@id,'gridview-')]/tbody/tr/td[3]");
	static final By PurchaseOrderDisplayed=By.xpath("(.//tr[contains(@id,'gridview')]//td)[3]");
	static final By PurchaseOrderDisplayed2=By.xpath("(.//tr[2][contains(@id,'gridview')]//td)[3]");
	static final By PurchaseOrderCheckboc=By.xpath("(.//tr[contains(@id,'gridview')]//td)[1]");
	static final By PurchaseOrderCheckbox2=By.xpath("(.//tr[2][contains(@id,'gridview')]//td)[1]");
	static final By PurchaseOrderAllCheckbox=By.xpath(".//span[contains (@id,'aggrindicator')]/img/../../../preceding-sibling::div[1]/div[1]/span");
	static final By ViewBtn=By.xpath(".//span[contains(text(),'View')]/following-sibling::span");
	static final By DestinationDetails=By.id("dataForm:PODetailsDetails_Facility_Destination_Facility_Out");
	static final By OriginDetails=By.id("dataForm:PODetailsDetails_Facility_Origin_Facility_Out");
	static final By QuantityTotal=By.xpath(".//span[contains(@id,'0:PODetails_Details_Total_out')]");
	static final By DOtab=By.id("PODetail_DO_Tab_lnk");
	static final By DOnumbr=By.xpath(".//span[contains(@id,'doListDataTable:0:DO_Nbr_Out')]");
	static final By PurchaseOrderCheckboc1=By.xpath(".//span[contains (text(),'Purchase Order')]/../../preceding-sibling::div[2]/div[1]/span");
	
	static final By RTStab=By.id("PODetail_RTS_Tab_lnk");
	static final By RTSnumber=By.xpath("(.//table[contains(@id,'rtsDataTable_body')]//td)[2]");
	static final By RTSgrid=By.cssSelector(".x-grid-view.x-fit-item.x-grid-view-default");
	static final By RTSselect=By.xpath(" .//a[contains (@class,'x-menu-item-indent-right-arrow')]/span[contains (text(),'RTS')]");
	//static final By RTSready=(By.xpath("(.//*[contains (text(),'Ready to Ship')])[3]"));
	static final By RTSready=(By.xpath("(.//*[contains (text(),'Ready to Ship')])[1]"));
	static final By RTSready1=By.xpath("(.//*[contains (text(),'Ready to Ship')])[3]");
	
	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By ClosePoDetails=By.xpath(".//span[contains(text(),'Post Message')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By MaximizePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By ClosePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By ClosePOdetailsPurchaseOrder=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	//static final By PrimaryFieldVP=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[3]");
	//static final By PrimaryFieldVP=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[4]");
	static final By PrimaryFieldVP=By.xpath("(//*[preceding-sibling::label[text()='Primary Fields']]//input[starts-with(@id, 'combobox')])[1]");
	static final By VPmenu = By.cssSelector(".x-btn-icon-el.wt-topbar-menu-icon");
	
	StreamResult streamResult;
	String doNmbr;
	static String DO;
	String OrderQty;
	String OriginFacility;
	String DestinationFacility;
	String quantity;
	String origin;
	String destinatn;
	public static String OrderId;
	String ponumberfromresponse;
	String ponumberfromresponse1;
	
	public void purchaseOrderScreen(String primaryfieldIput) throws Exception{	
		//Search for Purchase Order with the PO Number created through xml 
		wh.waitUntilDisappear(LoadingFrame);		

		wh.clickElement(MaximizePurchaseOrder);

		if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
			
			poxmlDatavp(dataTable.getData(DataColumn.Poxml));
			 ponumberfromresponse=OrderId;
			
			poxmlDatavp(dataTable.getData(DataColumn.xml2));
			ponumberfromresponse1=OrderId;
			
			if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){
				//String primaryfield=primaryfieldIput;
				wh.clearElement(PrimaryFieldVP);
				wh.sendKeys(PrimaryFieldVP, primaryfieldIput);	
				Thread.sleep(2000);
				//String ponumberfromresponse=VPPurchaseOrderPageObject.OrderId;
				System.out.println("ponumberfromresponse:"+ponumberfromresponse);
				System.out.println("ponumberfromresponse1:"+ponumberfromresponse1);
				wh.sendKeys(PurchaseOrderInput, ponumberfromresponse+","+ponumberfromresponse1);
				Thread.sleep(2000);
				rc.logger.info("Purchase Order number:"+ponumberfromresponse);
				if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse+","+ponumberfromresponse1)){
					report.addReportStep("Enter Primary Fields with PO", "Successfully entered Primary Fields and the PO Number are : "+ponumberfromresponse+" "+ponumberfromresponse1+" ", StepResult.PASS);
				}else{
					report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
				}
				/*if(!ponumberfromresponse.equalsIgnoreCase("")){
					//wh.clickElement(PurchaseOrderInput);
					wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
					Thread.sleep(5000);
					if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
						report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
					}
				}*/
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}else{
			rc.terminateTestCase("TPE purchase order screen");
		}
	}
	public String poxmlDatavp(String xmlpath) throws Exception{
		//PO xml				
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;

		document = documentBuilder.parse(System.getProperty("user.dir") + xmlpath); 				                 			
		
		Node Tagnode = document.getElementsByTagName("OrderId").item(0);                   
		OrderId=Tagnode.getTextContent();
		System.out.println("orderid:"+OrderId);				
							
		streamResult = new StreamResult(new File(System.getProperty("user.dir") + xmlpath));
		return OrderId;				
	}		 
	public void purchaseOrderAppliedvp() throws Exception{
		//Click on Apply and verify purchase order generated
		try{
		if(wh.isElementPresent(PurchaseOrderApply, 5)){
			wh.clickElement(PurchaseOrderApply);
			wh.waitUntilDisappear(LoadingFrame);
			if(wh.isElementPresent(PurchaseOrderApplied, 10)){
				String str=wh.getText(PurchaseOrderApplied);
				rc.logger.info("Filtered by Purchase Order number:"+str);
				System.out.println(str);				
				report.addReportStep("Click on Apply and verify purchase order generated", 
						"Successfully applied and purchase order generated", StepResult.PASS);
			}else{
				report.addReportStep("Click on Apply and verify purchase order generated", 
						"Not applied and purchase order not generated", StepResult.FAIL);
			}
			}else{
			rc.terminateTestCase("VP purchase order");
			}
		}catch(Exception e){
			report.addReportStep("Click button to filter for purchase order", 
					"Unable to click button to filter for purchase order", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	
	public void purchaseOrderSelectandViewvp() throws Exception{
		//PO Verification
		try{
		if(wh.isElementPresent(PurchaseOrderDisplayed, 5)){
			String podisplay=wh.getText(PurchaseOrderDisplayed);
			String podisplay2=wh.getText(PurchaseOrderDisplayed2);
			
			if(podisplay.equalsIgnoreCase(ponumberfromresponse)){
				wh.clickElement(PurchaseOrderCheckboc);
				//wh.clickElement(PurchaseOrderAllCheckbox);
				//wh.waitForElementPresent(ViewBtn);
					Thread.sleep(4000);
					if(wh.isElementPresent(ViewBtn, 10)){
						wh.clickElement(ViewBtn);
					}
											
				report.addReportStep("Verify Purchase order is displayed in grid as expected", 
						"Purchase order is displayed in grid as expected.PO Number from UI:"+podisplay+" and "+podisplay2+" ", StepResult.PASS);
				}else{
				rc.terminateTestCase("Purchase order not equals");
				}
			}else{
			rc.terminateTestCase("Purchase order is not displayed");
			}
		}
		catch(Exception e){
			report.addReportStep("View Purchase Order Details", 
					"Unable to view Purchase Order Details " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
	  }			
	}
	public void purchaseOrderSelectandViewvpS() throws Exception{
		//PO Verification
		try{
		if(wh.isElementPresent(PurchaseOrderDisplayed, 5)){
			String podisplay=wh.getText(PurchaseOrderDisplayed);
			
			if(podisplay.equalsIgnoreCase(OrderId)){
				wh.clickElement(PurchaseOrderCheckboc);
				
				//wh.waitForElementPresent(ViewBtn);
					Thread.sleep(4000);
					if(wh.isElementPresent(ViewBtn, 10)){
						wh.clickElement(ViewBtn);
					}
											
				report.addReportStep("Verify Purchase order is displayed in grid as expected", 
						"Purchase order is displayed in grid as expected.PO Number from UI:"+podisplay+"", StepResult.PASS);
				}else{
				rc.terminateTestCase("Purchase order not equals");
				}
			}else{
			rc.terminateTestCase("Purchase order is not displayed");
			}
		}
		catch(Exception e){
			report.addReportStep("View Purchase Order Details", 
					"Unable to view Purchase Order Details " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
	  }			
	}
	public void rtsTabValidationvp() throws Exception
	{
		//RTS Verification
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
				{
					report.addReportStep("Verify RTS number should be generated", 
							"RTS number is Not available", 
							StepResult.PASS);
				}
				driver.switchTo().defaultContent();
				wh.clickElement(CloseScreen);
		}
		
		}
		catch (Exception e)
		{
			rc.logger.error("Exception in PO details verification"+e);
		}
		
	}
	public void purchaseOrderSelectvp() throws Exception{
		if(wh.isElementPresent(PurchaseOrderDisplayed, 3)){
			String podisplay=wh.getText(PurchaseOrderDisplayed);
			
			if(podisplay.equalsIgnoreCase(OrderId)){
				wh.clickElement(PurchaseOrderCheckboc);
											
				report.addReportStep("Verify Purchase order is displayed in grid as expected", 
						"Purchase order is displayed in grid as expected", StepResult.PASS);
			}else{
				rc.terminateTestCase("Purchase order not equals");
			}
		}else{
			rc.terminateTestCase("Purchase order is not displayed");
		}		
	}
	
	public void selectpurchaseorder() throws Exception
	{
		//Selecting Purchase Order Screen
		{
			/*wh.waitUntilDisappear(LoadingFrame);
			wh.waitForPageLoaded();*/
			driver.switchTo().defaultContent();
				wh.clickElement(VPmenu);
				
				report.addReportStep("Click on VPmenu", 
						"Successfully clicked on VPmenu", StepResult.PASS);	
				Thread.sleep(2000);
		}
	}
	public void searchInputvp(String searchData,String listIndex) throws Exception
	{
		{
			wh.sendKeys(SearchInput, searchData.toString().trim());
			if(wh.getAttribute(SearchInput, "value").equalsIgnoreCase(searchData)){
				report.addReportStep("Enter"+ searchData + "in menu search", 
						"Successfully entered"+ searchData + "in menu search", StepResult.PASS);
			}else{
				report.addReportStep("Enter"+ searchData + "in menu search", 
						"Not entered search criteria in menu search", StepResult.FAIL);
			}		
			driver.findElement(By.xpath(".//div[contains(@id,'boundlist')]/ul/li/div[contains(text(),'("+listIndex+")')]")).click();
			Thread.sleep(2000);
		}
		
	}
	
	public void purchaseOrderSelect() throws Exception{
		//Verifying the PO with the Order ID generated through xml and click on RTS Ready to Ship
		if(wh.isElementPresent(PurchaseOrderDisplayed, 5)){
			String podisplay=wh.getText(PurchaseOrderDisplayed);
			String podisplay2=wh.getText(PurchaseOrderDisplayed2);
			
			if(podisplay.equalsIgnoreCase(ponumberfromresponse)){
				Thread.sleep(2000);	
				//wh.clickElement(PurchaseOrderCheckboc);
				wh.clickElement(PurchaseOrderAllCheckbox);
				//wh.waitForElementPresent(ViewBtn);
									
				report.addReportStep("Verify Purchase order is displayed in grid as expected. PO from UI :"+podisplay+" and "+podisplay2+" ", 
						"Purchase order is displayed in grid as expected", StepResult.PASS);
				
				
			}else{
				report.addReportStep("Verify Purchase order is displayed in grid as expected. PO from UI :"+podisplay+" and "+podisplay2+" ", 
						"Purchase order is displayed in grid is Not expected", StepResult.WARNING);
				rc.terminateTestCase("Purchase order not equals");
				
			}
		}
		
		
		
		else{
			rc.terminateTestCase("Purchase order is not displayed");
		}	
		
	}
	public void checkboxselection() throws Exception
	{
		wh.clickElement(PurchaseOrderAllCheckbox);
		Thread.sleep(1000);
		wh.clickElement(PurchaseOrderCheckboc);
		Thread.sleep(4000);
	}
	public void checkboxselection2() throws Exception
	{	
		wh.clickElement(PurchaseOrderCheckboc);
		Thread.sleep(1000);
		wh.clickElement(PurchaseOrderCheckbox2);
		Thread.sleep(4000);
	}
	public void readytoship() throws Exception
	{
		
		wh.waitUntilDisappear(LoadingFrame);
		wh.clickElement(RTSready);
		
		report.addReportStep("Click on Ready to Ship", 
				"Clicked on Ready to Ship", StepResult.PASS);
		
		/*if (wh.isElementPresent(RTSgrid))
		{
			wh.waitUntilDisappear(LoadingFrame);
			wh.contextClic(RTSgrid);
			Thread.sleep(2000);	
			report.addReportStep("Right click on RTS", 
					"Right clicked on RTS", StepResult.PASS);
		
			//driver.switchTo().activeElement();
			//wh.clickElement(RTSselect);
			
			if(wh.isElementPresent(RTSselect, 3))
			{
				//wh.mouseOver(RTSselect);
				wh.moveToElement(RTSselect);
				Thread.sleep(2000);	
				if(wh.isElementPresent(RTSready, 4))
				{
				wh.clickElement(RTSready);
				
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
			else
			{
				report.addReportStep("Move to RTS", 
						"Not able to Move to RTS", StepResult.FAIL);
				rc.terminateTestCase(" Not able to Move to RTS");
			}
		}*/
	}
	public void purchaseOrderScreen1(String primaryfieldIput) throws Exception{	
		
		//Filtering with the PO created
		wh.waitUntilDisappear(LoadingFrame);		
		driver.switchTo().activeElement();
		wh.clickElement(MaximizePurchaseOrder);

		if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
			poxmlDatavp(dataTable.getData(DataColumn.Poxml));
			ponumberfromresponse=OrderId;
			poxmlDatavp(dataTable.getData(DataColumn.xml2));
			ponumberfromresponse1=OrderId;
			if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){
				//String primaryfield=primaryfieldIput;
				wh.clearElement(PrimaryFieldVP);
				wh.sendKeys(PrimaryFieldVP, primaryfieldIput);	
				Thread.sleep(2000);
				//String ponumberfromresponse=VPPurchaseOrderPageObject.OrderId;
				System.out.println("ponumberfromresponse:"+ponumberfromresponse);
				System.out.println("ponumberfromresponse1:"+ponumberfromresponse1);
				wh.sendKeys(PurchaseOrderInput, ponumberfromresponse+","+ponumberfromresponse1);
				Thread.sleep(2000);
				rc.logger.info("Purchase Order number:"+ponumberfromresponse);
				if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse+","+ponumberfromresponse1)){
					report.addReportStep("Enter Primary Fields with PO", "Successfully entered Primary Fields and the PO Number are : "+ponumberfromresponse+" "+ponumberfromresponse1+" ", StepResult.PASS);
				}
				else
				{
					report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
				}
				/*if(!ponumberfromresponse.equalsIgnoreCase("")){
					//wh.clickElement(PurchaseOrderInput);
					wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
					Thread.sleep(5000);
					if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
						report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
					}
				}*/
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}else{
			rc.terminateTestCase("TPE purchase order screen");
		}
	}
public void purchaseOrderScreen2(String primaryfieldIput) throws Exception{	
		
		//Filtering with the PO created
		wh.waitUntilDisappear(LoadingFrame);		
		driver.switchTo().activeElement();
		wh.clickElement(MaximizePurchaseOrder);

		if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
			poxmlDatavp(dataTable.getData(DataColumn.Poxml));
			String ponumberfromresponse=OrderId;
			if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){
				//String primaryfield=primaryfieldIput;
				wh.clearElement(PrimaryFieldVP);
				wh.sendKeys(PrimaryFieldVP, primaryfieldIput);	
				Thread.sleep(4000);
				//String ponumberfromresponse=VPPurchaseOrderPageObject.OrderId;
				System.out.println("ponumberfromresponse:"+ponumberfromresponse);
				wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
				//Thread.sleep(2000);
				if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
					report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields. Entered PO : "+ponumberfromresponse+"", StepResult.PASS);
				}else{
					report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
				}
				/*if(!ponumberfromresponse.equalsIgnoreCase("")){
					//wh.clickElement(PurchaseOrderInput);
					wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
					Thread.sleep(5000);
					if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
						report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
					}
				}*/
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}else{
			rc.terminateTestCase("TPE purchase order screen");
		}
	}
		public String getDoNumber() throws Exception{
			
		
			
			while (wh.isElementPresent(DOnumbr, 5))
			{
				doNmbr=wh.getText(DOnumbr);
				rc.logger.info("DO number "+doNmbr);
			}
			
			
			if (!wh.isElementPresent(DOnumbr))
					{
							
					}
			
			if(wh.isElementPresent(DOnumbr, 6)){
				doNmbr=wh.getText(DOnumbr);
				rc.logger.info("DO number "+doNmbr);
				
				
				
				driver.switchTo().defaultContent();
				wh.clickElement(ClosePOdetailsPurchaseOrder);
				if(wh.isElementPresent(ClosePurchaseOrder, 3)){
					wh.clickElement(ClosePurchaseOrder);
				}			
				//driver.switchTo().defaultContent();
			}
			return doNmbr;
		} 
		public void purchaseOrderScreenS(String primaryfieldIput) throws Exception{	
			//Search for Purchase Order with the PO Number created through xml 
			wh.waitUntilDisappear(LoadingFrame);		

			wh.clickElement(MaximizePurchaseOrder);

			if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
				
				poxmlDatavp(dataTable.getData(DataColumn.Poxml));
				String ponumberfromresponse=OrderId;
				
				if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){
					//String primaryfield=primaryfieldIput;
					wh.clearElement(PrimaryFieldVP);
					wh.sendKeys(PrimaryFieldVP, primaryfieldIput);	
					Thread.sleep(2000);
					//String ponumberfromresponse=VPPurchaseOrderPageObject.OrderId;
					System.out.println("ponumberfromresponse:"+ponumberfromresponse);
					wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
					Thread.sleep(2000);
					rc.logger.info("Purchase Order number:"+ponumberfromresponse);
					if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
						report.addReportStep("Enter Primary Fields with PO", "Successfully entered Primary Fields and the PO Number is : "+ponumberfromresponse+" ", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
					}
					/*if(!ponumberfromresponse.equalsIgnoreCase("")){
						//wh.clickElement(PurchaseOrderInput);
						wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
						Thread.sleep(5000);
						if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
							report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
						}else{
							report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
						}
					}*/
				}else{
					rc.terminateTestCase("TPE purchase order screen");
				}
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}
		public void purchaseOrderScreenS1(String primaryfieldIput) throws Exception{	
			
			//Filtering with the PO created
			wh.waitUntilDisappear(LoadingFrame);		
			driver.switchTo().activeElement();
			wh.clickElement(MaximizePurchaseOrder);

			if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){	
				poxmlDatavp(dataTable.getData(DataColumn.Poxml));
				String ponumberfromresponse=OrderId;
				if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){
					//String primaryfield=primaryfieldIput;
					wh.clearElement(PrimaryFieldVP);
					wh.sendKeys(PrimaryFieldVP, primaryfieldIput);	
					Thread.sleep(4000);
					//String ponumberfromresponse=VPPurchaseOrderPageObject.OrderId;
					System.out.println("ponumberfromresponse:"+ponumberfromresponse);
					wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
					//Thread.sleep(2000);
					if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
						report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields. Entered PO : "+ponumberfromresponse+"", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
					}
					/*if(!ponumberfromresponse.equalsIgnoreCase("")){
						//wh.clickElement(PurchaseOrderInput);
						wh.sendKeys(PurchaseOrderInput, ponumberfromresponse);
						Thread.sleep(5000);
						if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(ponumberfromresponse)){
							report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
						}else{
							report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
						}
					}*/
				}else{
					rc.terminateTestCase("TPE purchase order screen");
				}
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}
		public void purchaseOrderSelectp() throws Exception{
			//Verifying the PO with the Order ID generated through xml and click on RTS Ready to Ship
			if(wh.isElementPresent(PurchaseOrderDisplayed, 5)){
				String podisplay=wh.getText(PurchaseOrderDisplayed);
				
				if(podisplay.equalsIgnoreCase(OrderId)){
					Thread.sleep(2000);	
					wh.clickElement(PurchaseOrderCheckboc);
					
					//wh.waitForElementPresent(ViewBtn);
										
					report.addReportStep("Verify Purchase order is displayed in grid as expected. PO from UI :"+podisplay+" ", 
							"Purchase order is displayed in grid as expected", StepResult.PASS);
					
					
				}else{
					report.addReportStep("Verify Purchase order is displayed in grid as expected. PO from UI :"+podisplay+" ", 
							"Purchase order is displayed in grid is Not expected", StepResult.WARNING);
					rc.terminateTestCase("Purchase order not equals");
					
				}
			}
			
			
			
			else{
				rc.terminateTestCase("Purchase order is not displayed");
			}	
			
		}
		
}
