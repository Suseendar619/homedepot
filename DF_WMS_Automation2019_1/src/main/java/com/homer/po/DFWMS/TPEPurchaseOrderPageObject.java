
package com.homer.po.DFWMS;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;

public class TPEPurchaseOrderPageObject extends PageBase{

	public TPEPurchaseOrderPageObject(InstanceContainer ic) {
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
	
	static final By PurchaseOrderScreenHeader=By.xpath(".//span[contains(text(),'Purchase Orders') and contains(@id,'screen')]");
	static final By PurchaseOrderDetailsScreen=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order') and contains(@id,'screen')]");
	static final By PurchaseOrderDetailHeader= By.xpath("//*[contains(@class, 'x-window-header') and text()='PO Detail - Purchase Order'"
			+ " and not(contains(@id, 'ghost'))]");
    //static final By PrimaryFieldFilter=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[4]");
	static final By PrimaryFieldFilter=By.xpath("(//*[preceding-sibling::label[text()='Primary Fields']]//input[starts-with(@id, 'combobox')])[1]");
	static final By PurchaseOrderFilterBtn=By.id("ext-gen2549");
	static final By PurchaseOrderOption=By.xpath(".//div[contains(@class,'filter-combo-option') and contains(text(),'Purchase Order')]");
	//static final By PurchaseOrderInput=By.xpath(".//input[contains(@id, 'mps-lookupfield')]");
	static final By PurchaseOrderInput=By.xpath("//*[preceding-sibling::label[text()='Primary Fields']]//input[contains(@id, 'lookupfield')]");
	//static final By PurchaseOrderInput=By.name("PurchaseOrder");
	static final By PurchaseOrderSearch=By.xpath("(//table/tbody/tr/td//div[contains(@class, 'x-form-search')])[2]");
	static final By PurchaseOrderApply=By.xpath(".//span[contains(text(), 'Apply') and contains(@id,'button')]");
	static final By PurchaseOrderApplied=By.xpath("//table[starts-with(@id,'gridview-')]/tbody/tr/td[3]");
	static final By PurchaseOrderDisplayed=By.xpath("(.//tr[contains(@id,'gridview')]//td)[3]");
	static final By PurchaseOrderCheckboc=By.xpath("(.//tr[contains(@id,'gridview')]//td)[1]");
	static final By ViewBtn=By.xpath(".//span[contains(text(),'View')]/following-sibling::span");
	
	static final By DestinationDetails=By.id("dataForm:PODetailsDetails_Facility_Destination_Facility_Out");
	//static final By DestinationDetails= By.xpath(".//span[contains(@id,'dataForm:PODetailsDetails_Facility_Destination_Facility_Out')]");

	static final By OriginDetails=By.id("dataForm:PODetailsDetails_Facility_Origin_Facility_Out");
	//static final By OriginDetails= By.xpath(".//span[contains(@id,'dataForm:PODetailsDetails_Facility_Origin_Facility_Out')]");
	
	static final By QuantityTotal=By.xpath(".//span[contains(@id,'0:PODetails_Details_Total_out')]");
	
	static final By DOtab=By.id("PODetail_DO_Tab_lnk");
	static final By DOnumbr=By.xpath(".//span[contains(@id,'doListDataTable:0:DO_Nbr_Out')]");
	static final By DOShipmentnumbr=By.xpath(".//span[contains(@id,'doListDataTable:0:PODO_ShipmentId_OutputLink_Text')]");
	
	static final By RTStab=By.id("PODetail_RTS_Tab_lnk");
	static final By RTSnumber=By.xpath("(.//table[contains(@id,'rtsDataTable_body')]//td)[2]");
	static final By RTSnumberAll = By.xpath("//table[contains(@id,'rtsDataTable_body')]//tr[not(contains(@id, 'nodataRow'))]");
	static final By LineItemRowsAll = By.xpath("//table[contains(@id,'RTSView_Line_dataTable_body')]//tr[not(contains(@id, 'nodataRow'))]");
	
	static final By MaximizePoDetails=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By ClosePoDetails=By.xpath(".//span[contains(text(),'Post Message')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By MaximizePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By ClosePurchaseOrder=By.xpath(".//span[contains(text(),'Purchase Orders')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	static final By ClosePOdetailsPurchaseOrder=By.xpath(".//span[contains(text(),'PO Detail - Purchase Order')]/../following-sibling::div//img[contains(@class,'x-tool-close')]");
	
	static final By MaximizePODetailRTS=By.xpath("//*[child::*[contains(@class, 'x-tool-maximize')] and preceding-sibling::*[child::*[text() = 'PO Detail - Ready to Ship']] and ancestor::*[not(contains(@id, 'ghost'))]]/img");
	static final By PODetailFrame = By.xpath("//iframe[contains(@src,'PODetail.xhtml')]");
	static final String DOTABSCROLLER = "dataForm:lvDOs1:doListDataTable_scrollDiv";
	public static final By ADDTODOARROWBUTTON = By.xpath("//a[.='Add to DO']//*[contains(@class, 'x-btn-split-right')]");
	public static final By CREATEDOOPTION = By.xpath("//a[.='Create DO']");
	public static final By SAVEBUTTON_CREATEDOSCREEN = By.xpath("//*[@id='dataForm:DOCreate_save_button']");
	public static final By DODETAILSMAINHEADERID = By.id("dataForm:DODetailsMainHeader_Out_DOID");
	
	// On RTS Detail section
	static final By LineItemsTab_RTSDetail = By.id("TABH_dataForm:Line_Items_Tab");
	static final String QuantityValueLocator = "dataForm:RTSView_Line_dataTable:<ROWNUM>:RTSView_Line_OutText_Qty_OutText";
	static final String WeightValueLocator = "dataForm:RTSView_Line_dataTable:<ROWNUM>:RTSView_Line_OutText_WEI_OutText";
	static final String VolumeValueLocator = "dataForm:RTSView_Line_dataTable:<ROWNUM>:RTSView_Line_OutText_VOL_OutText";
	
	StreamResult streamResult;
	String doNmbr;
	String DOShpmntNmbr;
	static String DO;
	private String DOShipment;
	
	String OrderQty=TPEPostMessagePageObject.OrderQty;
	String OriginFacility=TPEPostMessagePageObject.OriginFacility;
	String DestinationFacility=TPEPostMessagePageObject.DestinationFacility;
	String quantity1;
	String quantity;
	String origin;
	String destinatn;
	
	List<String> li = new ArrayList<String>(); 

	public static enum PrimaryFields{
		DELIVERYEND ("Delivery End"),
		DELIVERYSTART("Delivery Start"),
		DUEDATE("Due Date"),
		PICKUPEND("Pickup End"),
		PICKUPSTART("Pickup Start"),
		PURCHASEORDER("Purchase Order"),
		STATUS("Status");
		
		private String value;
		
		PrimaryFields(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * Method to validate purchase order screen and enter search input with Purchase order
	 * @return
	 * @throws Exception
	  */
	public void purchaseOrderScreen(String PrimaryInputField) throws Exception{
		/*VPPurchaseOrderPageObject v = new VPPurchaseOrderPageObject(ic);
		v.poxmlDatavp(dataTable.getData(DataColumn.Poxml));
		String poNumber = v.OrderId;*/
		
		//MQ C
		//String poNumber = TPEPostMessagePageObject.po;
		String poNumber = TPEPostMessagePageObject.orderID;
		purchaseOrderScreen(PrimaryInputField, poNumber);
	}
	
	public void purchaseOrderScreen(String primaryInputField, String poNumber) throws Exception{	
		wh.waitUntilDisappear(LoadingFrame);	
		
		try{
			By locator = getFilterDetailPanelHeaderLocation("Purchase Orders", "View Details");
			if (!wh.isElementPresent(locator, 10)){
				throw new Exception("Unable to fully load Purchase Order screen. Waiting for 'View Details' pane to show");
			}
			
			if (wh.isElementPresent(MaximizePurchaseOrder)){
				wh.clickElement(MaximizePurchaseOrder);
			}else{
				throw new Exception("Can't locate Maximize button for Purchase order screen. ");
			}

		if(wh.isElementPresent(PurchaseOrderScreenHeader, 10)){			
			if(wh.getText(PurchaseOrderScreenHeader).equalsIgnoreCase("Purchase Orders")){				
					if(wh.isElementPresent(PrimaryFieldFilter,20)){
						wh.clearElement(PrimaryFieldFilter);
						wh.sendKeys(PrimaryFieldFilter, primaryInputField);	
					
					}else{
						throw new Exception("Unable to locate Primary Field Filter field");
					}
					
					if(!poNumber.equalsIgnoreCase("")){
						if(wh.isElementPresent(PurchaseOrderInput)){
							wh.clickElement(PurchaseOrderInput);
							wh.sendKeys(PurchaseOrderInput, poNumber);
						}else{
							throw new Exception("Unable to locate input field for Purchase Order number");
						}
						
						if(wh.getAttribute(PurchaseOrderInput, "value").equalsIgnoreCase(poNumber)){
						report.addReportStep("Enter Primary Fields", "Successfully entered Primary Fields", StepResult.PASS);
					}else{
						report.addReportStep("Enter Primary Fields", "Primary Fields not entered", StepResult.FAIL);
							rc.throwTCTerminationException();
						}
				}
			}else{
				rc.terminateTestCase("TPE purchase order screen");
			}
		}else{
			rc.terminateTestCase("TPE purchase order screen");
		}
		}catch(Exception e){
			report.addReportStep("Filter by Purchase Order ", "Unable to filter by purchase order. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
	}
	
	}
	/**
	 * Method to apply purchase order and validate
	 * @return
	 * @throws Exception
	 */
	public void purchaseOrderApplied() throws Exception{
		try{
			if(wh.isElementPresent(PurchaseOrderApply, 3)){
				wh.clickElement(PurchaseOrderApply);
				
				//wait for Purchase Order Table to load
				if(wh.isElementPresent(PurchaseOrderApplied, 10)){
					String str=wh.getText(PurchaseOrderApplied);
					rc.logger.info("Filtered by Purchase Order number:"+str);
					System.out.println(str);				
					report.addReportStep("Click on Apply and verify purchase order generated", 
						"Successfully applied and purchase order "+ str +" generated", StepResult.PASS);
				}else{
					report.addReportStep("Click on Apply and verify purchase order generated", 
							"Not applied and purchase order not generated", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}else{
				rc.terminateTestCase("TPE purchase order");
			}
		}catch(Exception e){
			report.addReportStep("Click button to filter for purchase order", 
					"Unable to click button to filter for purchase order", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	
	/**
	 * This function selects the first Purchase Order from Table
	 */
	public void selectPurchaseOrderFromTable(){
		try{
			// Check to see if already selected
			WebElement checkbox = wh.getElement(PurchaseOrderCheckboc);
			WebElement container = checkbox.findElement(By.xpath(".//parent::tr"));
			if (!container.getAttribute("class").contains("x-grid-row-selected")){
				wh.clickElement(PurchaseOrderCheckboc);		
				
				//wh.waitForElementPresent(ViewBtn);
				Thread.sleep(4000);
			}
		}catch(Exception e){
			report.addReportStep("Select Purchase Order from table", 
					"Unable to select Purchase Order from table. " + e.getMessage(), StepResult.FAIL);
		}	
	}
	
	/**
	 * Method to verify Purchase order is displayed in grid
	 * @return
	 * @throws Exception
	 */
	public void purchaseOrderSelectandView() throws Exception{
		String purchaseOrder = TPEPostMessagePageObject.orderID;
		purchaseOrderSelectandView(purchaseOrder);
	}
	public void purchaseOrderSelectandView(String purchaseOrder) throws Exception{
		try{
			if(wh.isElementPresent(PurchaseOrderDisplayed, 3)){
				String podisplay=wh.getText(PurchaseOrderDisplayed);
				
				if(podisplay.equalsIgnoreCase(purchaseOrder)){
					
					report.addReportStep("Verify Purchase order is displayed in grid as expected", 
							"Purchase order is displayed in grid as expected", StepResult.PASS);
						
						selectPurchaseOrderFromTable();
						
						if(wh.isElementPresent(ViewBtn, 10)){
							wh.clickElement(ViewBtn);
						}else{
							throw new Exception("Unable to see view button for purchase order table");
						}
						
						if(wh.isElementPresent(PurchaseOrderDetailsScreen, 7)){
							report.addReportStep("Open Purchase Order Details window", 
									"Opened Purchase Order Details window", StepResult.PASS);
							Thread.sleep(1000);


							wh.clickElement(MaximizePoDetails);
							switchToPODetailFrame();
						}else{
							throw new Exception("Did not open Purchase Order Details window");
						}

				}else{
					rc.terminateTestCase("Purchase Order not equal",StepResult.FAIL);
				}
		}else{
			rc.terminateTestCase("Purchase Order is not displayed",StepResult.FAIL);
		}
		}catch(Exception e){
			report.addReportStep("View Purchase Order Details", 
					"Unable to view Purchase Order Details " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
	  }			
	}
	
	/**
	 * Method to verify Order quantity, Origin and Destination
	 * @return
	 * @throws Exception
	 */
	public void verifyGeneralTab() throws Exception{		
		wh.clickElement(MaximizePoDetails);
		try{
			switchToPODetailFrame();			

			orderQuantity();
			originFacility();
			destinationFacility();			
			
			String XMLQuantity = (TPEPostMessagePageObject.OrderQty).replaceAll(".00", "");
			String XMLOriginfacility = TPEPostMessagePageObject.OriginFacility;
			String XMLDestinationfacility = TPEPostMessagePageObject.DestinationFacility;
			
			System.out.println(quantity +" " + XMLQuantity);
			System.out.println(origin +" " + XMLOriginfacility);
			System.out.println(destinatn +" " +  XMLDestinationfacility);

			if(quantity.equalsIgnoreCase(XMLQuantity)&&origin.equalsIgnoreCase(XMLOriginfacility)&&destinatn.equalsIgnoreCase(XMLDestinationfacility)){
				report.addReportStep("Verify Order quantity, Origin and Destination should be equal", 
						"Order quantity, Origin and Destination are equal"
						+ "Expected Order Quantity: [" + XMLQuantity + "]. Actual: [" + quantity + "]\n"
						+ "Expected Origin: [" + XMLOriginfacility + "]. Actual: [" + origin + "]\n"
						+ "Expected Destination: [" + XMLDestinationfacility + "]. Actual: [" + destinatn + "]", StepResult.PASS);
			}else{
				report.addReportStep("Verify Order quantity, Origin and Destination should be equal", 
						"Order quantity, Origin and Destination are not equal. "
						+ "Expected Order Quantity: [" + XMLQuantity + "]. Actual: [" + quantity + "]\n"
						+ "Expected Origin: [" + XMLOriginfacility + "]. Actual: [" + origin + "]\n"
						+ "Expected Destination: [" + XMLDestinationfacility + "]. Actual: [" + destinatn + "]", StepResult.FAIL);
			}
		}catch(Exception ex){
			rc.logger.error("Exception in PO details verification"+ex);
		}
	}
	
	/**
	 * Method to validate Order quantity	
	 * @throws Exception
	 */
	public void orderQuantity() throws Exception{
		if(wh.isElementPresent(QuantityTotal, 3)){
			wh.moveToElement(QuantityTotal);
			String quantityTotalUI=wh.getText(QuantityTotal);
			String[] qttotUI=quantityTotalUI.split(" ");
			quantity=qttotUI[0];
			if(quantity.contains(",")){
				quantity=quantity.replaceAll(",", "");
				System.out.println(quantity);
			}

			rc.logger.info("Order quantity in PO: "+quantity);
			rc.logger.info("Order quantity in XML: "+ TPEPostMessagePageObject.OrderQty);
			System.out.println(quantity);
			
		}else{
			rc.terminateTestCase("Order quantity element is not present", StepResult.FAIL);			
		}
	}
	
	/**
	 * Method to validate Origin facility
	 * @throws Exception
	 */
	public void originFacility() throws Exception{
		if(wh.isElementPresent(OriginDetails, 3)){
			wh.moveToElement(OriginDetails);
			origin=wh.getText(OriginDetails);
			rc.logger.info("Origin facility in PO: " + origin);
			rc.logger.info("Origin facility in XML: " + TPEPostMessagePageObject.OriginFacility);
			System.out.println(origin);
		}else{
			rc.terminateTestCase("Origin element is not present", StepResult.FAIL);			
		}
	}
	
	/**
	 * Method to validate Destination facility
	 * @throws Exception
	 */
	public void destinationFacility() throws Exception{
		if(wh.isElementPresent(DestinationDetails, 3)){			
			wh.moveToElement(DestinationDetails);
			destinatn=wh.getText(DestinationDetails);
			rc.logger.info("Destination Facility in PO: "+destinatn);	
			rc.logger.info("Destination Facility in  XML: "+ TPEPostMessagePageObject.DestinationFacility);
		}else{
			rc.terminateTestCase("Destination element is not present", StepResult.FAIL);			
		}
	}
	
		/**
	 * This function switches to the 'PO Detail' frame
	 */
	public void switchToPODetailFrame(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(PODetailFrame));
	}
	
	/**
	 * Method to validate RTS tab and RTS number
	 * @throws Exception
	 */
	
	/**
	 * This function double clicks the first RTS number in the PO Detail --> RTS Detail screen
	 * @throws Exception
	 */
	public void openRtsDetailScreen() throws Exception{
		//switchToPODetailFrame();
		
		try{
			// Click on RTS number
			if (!wh.isElementPresent(RTSnumber)){
				throw new Exception ("Can't find RTS number to click on");
				
			}
			wh.doubleClickUsingAction(RTSnumber);
			
			// Wait for RTS Details screen to appear
			waitForRtsDetailScreen();
						
		}catch(Exception e){
			report.addReportStep("Open RTS Detail screen", 
					"Unable to open RTS Detail screen. " + e.getMessage(), 
					StepResult.FAIL);
		}	
	}
	
	/**
	 * This function clicks the 'Line Items' tab on the PO Detail --> RTS Detail
	 * @throws TCTerminationException 
	 * @throws Exception
	 */
	public void clickLineItemsTab_RTSDetail() throws TCTerminationException {		
		try{
			switchToPODetailFrame();
			if(!wh.isElementPresent(LineItemsTab_RTSDetail)){
				throw new Exception ("Can't locate 'Line Items' tab in RTS Detail Screen");
			}
			
			wh.clickElement(LineItemsTab_RTSDetail);
			Thread.sleep(5000);
		}catch(Exception e){
			report.addReportStep("Click Line Items Tab", "Unable to click 'Line Items' tab. " 
					+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	/**
	 * This function gets the Quantity from RTS detail screen
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Quantity
	 * @throws Exception
	 */
	public String getQuantity_RTSDetail(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolume_RTSDetail("quantity", lineItemNumber);
	}
	
	/**
	 * This function gets the Quantity from RTS detail screen
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Weight
	 * @throws Exception
	 */
	public String getWeight_RTSDetail(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolume_RTSDetail("weight", lineItemNumber);
	}
	
	/**
	 * This function gets the Quantity from RTS detail screen
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Volume
	 * @throws Exception
	 */
	public String getVolume_RTSDetail(Integer lineItemNumber) throws Exception{
		return getQuantityWeightOrVolume_RTSDetail("volume", lineItemNumber);
	}
	
	/**
	 * This function gets the Quantity, Weight, or Volume from RTS detail screen
	 * This function may work for additional elements in the future.
	 * @param unit  Type of unit we want to get. "quantity", "weight", or "volume"
	 * @param lineItemNumber Line Item number that you're requesting from
	 * @return Quantity, Weight, or Volume, depending on desired unit
	 * @throws Exception
	 */
	public String getQuantityWeightOrVolume_RTSDetail(String unit, Integer lineItemNumber) throws Exception{
		switchToPODetailFrame();
		String formatted=null;
		
		String locatorString = null;
		
		if (unit.toLowerCase().equals("quantity")){
			locatorString = QuantityValueLocator;
		}else if(unit.toLowerCase().equals("weight")){
			locatorString = WeightValueLocator;
		}else if(unit.toLowerCase().equals("volume")){
			locatorString = VolumeValueLocator;
		}else{
			throw new Exception("Unrecognized input for unit type. Passed input = [" + unit + "]");
		}
		
		locatorString = locatorString.replace("<ROWNUM>", lineItemNumber.toString());
		
		By locator = By.id(locatorString);
		try{
			if(!wh.isElementPresent(locator)){
				throw new Exception("No " + unit.toUpperCase() + "value is present");
			}
			
			String value = wh.getText(locator).trim();
			
			String [] splitValue = value.split(" ");
			
			//Format first value
			float unitValueFormatted = Float.parseFloat(splitValue[0].replace(",", ""));
			
			String uom = splitValue[1];
			
			formatted = Float.toString(unitValueFormatted) + " " + uom;

		
		}catch(Exception e){
			rc.addReportStep("Get " + unit + "  from RTS screen. ", 
					"Unable to get " + unit + " from RTS screen. " 
					+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		return formatted;
	}
	
	
	/**
	 * This function waits for the Rts Detail Screen to appear
	 * (After an RTS is clicked in Purchase Order Detail screen - RTS Tab)
	 */
	
	public void waitForRtsDetailScreen() throws Exception{
		Integer waitTime = 30; 
		if (!wh.isElementPresent(By.id("dataForm:ViewRTSMain_IT_RTSID"), waitTime)){
			throw new Exception("RTS Detail Screen did not load in " + waitTime + " seconds.");
		}
	}
	
	/**
	 * Method to validate RTS tab and RTS number
	 * @throws Exception
	 */
	public void rtsTabValidation() throws Exception{
		try{
			
			clickRtsTab();
			
			String rtsnumber=wh.getText(RTSnumber);
			if(rtsRowsExist()){						
				rc.logger.info("RTS number"+rtsnumber);
				report.addReportStep("Verify RTS number should be generated", 
							"RTS number "+rtsnumber+" is available", 
						StepResult.PASS);
			}else{
				throw new Exception("No RTS rows exist");
			}

		}catch(Exception e){
				rc.logger.error("RTS number not generated");
			report.addReportStep("Verify RTS number should be generated", 
					"Unable to verify that the RTS number is generated. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	/**
	 * This function clicks the RTS tab on the Purchase Order screen
	 * @throws Exception
	 */
	public void clickRtsTab() throws Exception{
		try{
			switchToPODetailFrame();
			
			// Click on RTS tab
			if(!wh.isElementPresent(RTStab)){
				throw new Exception("Unable to locate RTS Tab");
			}
			
			wh.moveToElement(RTStab);
			wh.clickElement(RTStab);
			
			//Wait for RTS Tab to be active
			By locator = By.xpath("//*[contains(@id, 'rtsDataTable_container')]");
			
			// Click on RTS tab
			if(!wh.isElementPresent(locator)){
				throw new Exception("RTS Tab did not load");
			}
			
			report.addReportStep("Click RTS Tab", "Clicked RTS Tab", 
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep("Click RTS Tab", "Did not click the RTS tab. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	
	/**
	 * This function checks to see if any rows exist in the RTS table
	 * @return
	 * @throws Exception
	 */
	public Boolean rtsRowsExist() throws Exception{
	
		return !rtsRowsExist(0);
	}
	
	public Boolean rtsRowsExist(Integer numOfExpectedRows) throws Exception{
		Boolean present = false;
		
		Integer rowCount = getRtsRowCount();
		if (rowCount == numOfExpectedRows){
			present = true;
		}
		
		return present;
	}
	
	public Integer getRtsRowCount() throws Exception{
		Integer rowSize = 0;
		try{
			List <WebElement> rows = wh.getElements(RTSnumberAll);
			rowSize = rows.size();
		}catch(Exception e){
			throw new Exception("Unable to get row count for 'RTS' table. " + e.getMessage());
		}
		
		return rowSize;
	}
	
	public Integer getLineItemsRowCount() throws Exception{
		Integer rowSize = 0;
		try{
			List <WebElement> rows = wh.getElements(LineItemRowsAll);
			rowSize = rows.size();
		}catch(Exception e){
			throw new Exception("Unable to get row count for 'Line Items' table. " + e.getMessage());
		}
		
		return rowSize;
	}
	
	/**
	 * Method to validate DO tab and Distribution order number
	 * @throws Exception
	 */
	public void doTabValidation() throws Exception{
		
		switchToPODetailFrame();
		
		try{
			if(!wh.isElementPresent(DOtab, 10)){
				throw new Exception("Unable to Find DO tab");
			}
	
			Thread.sleep(3000);

			wh.moveToElement(DOtab);
			wh.clickElement(DOtab);
			
			DO = getDoNumber();
			
			if(!DO.equalsIgnoreCase("")){
				rc.logger.info("Distribution Order Id: "+DO);
				report.addReportStep("Verify Distribution Order Id should be generated", 
						"Distribution Order Id "+DO+" is available", 
						StepResult.PASS);
			}else{
				String msg = "Distribution Order ID not available";
				rc.logger.info(msg);
				throw new Exception(msg);
			}
		}catch(Exception e){
			rc.addReportStep("Validate DO Tab", "Unable to Validate DO Tab. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}
	
	/**
	 * This function clicks the Maximize button for 'PO Detail-Ready to Ship' screen
	 * @throws Exception
	 */
	public void maximizePODetailRTSScreen() throws Exception{
		driver.switchTo().defaultContent();
		try{
			
			if (!wh.isElementPresent(MaximizePODetailRTS)){
				
				throw new Exception("Maximize button not present.");
			}
			wh.clickElement(MaximizePODetailRTS);
			Thread.sleep(5000);
		}catch(Exception e){
			report.addReportStep("Maximize 'PO Detail - RTS' Screen", 
					"Unable to Maximize the 'PO Detail - RTS' Screen. " + e.getMessage(), 
					StepResult.FAIL);
		}
	}
	
	/**
	 * Method to get Distribution order number
	 * @throws Exception
	 * @return doNmbr
	 */
	public String  getDoNumber() throws Exception{
		
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
        while ( System.nanoTime() < endTime ){
        	            	        	
            Thread.sleep(3000);
            //driver.switchTo().frame(0);
            
    		if(wh.isElementPresent(DOnumbr, 10)){
    			
    			doNmbr = wh.getText(DOnumbr);
    			    			
   			
    			driver.switchTo().defaultContent();
    			wh.clickElement(ClosePOdetailsPurchaseOrder);
    			
    			if(wh.isElementPresent(ClosePurchaseOrder, 3)){
    				wh.clickElement(ClosePurchaseOrder);
    			}			
    			
                break;
    			//driver.switchTo().RTS Tab and back;
    		}else{
    			wh.moveToElement(RTStab);
    			wh.clickElement(RTStab);
    			Thread.sleep(3000);
    			wh.moveToElement(DOtab);
    			wh.clickElement(DOtab);	
            }
                    	
    	}
		return doNmbr;
		
	
	}
	
	/**
	 * Method to validate DO tab and Shipment order number in the DO tab
	 * @throws Exception
	 */
	public void doTabShipmentValidation() throws Exception{
		
		
		if(wh.isElementPresent(DOtab, 10)){
			Thread.sleep(3000);
			wh.moveToElement(DOtab);
			wh.clickElement(DOtab);
			
			DOShipment = getDOShipNumber();
			
			if(!DOShipment.equalsIgnoreCase("")){
				rc.logger.info("Shipment Id: "+ DOShipment);
				report.addReportStep("Verify Shipment Id is generated in DO tab", 
						"Shipment Id "+ DOShipment +" is available", 
						StepResult.PASS);
			}else{
				rc.logger.info("Shipment Id not available in DO Tab");
				rc.terminateTestCase("Shipment Id in DO Tab", StepResult.FAIL);	
			}
		}		
	}
	
	
	/**
	 * Method to get Shipment ID from Distribution order tab
	 * @throws Exception
	 * @return doNmbr
	 */
	public String  getDOShipNumber() throws Exception{
		
		String stepName = "Find Shipment Number from DO tab";
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
		Integer attemptNumber = 0;
        while ( System.nanoTime() < endTime ){
        	
        	attemptNumber++;
            Thread.sleep(3000);
            //driver.switchTo().frame(0);
            
            // Scroll to shipment ID in table. Needed in Jenkins
			Boolean found  = false;
			Integer count = 0;
			Integer scrollMax = 10;
			while (!found && count<scrollMax){
				
				((JavascriptExecutor) driver).executeScript("document.getElementById('" + DOTABSCROLLER + "').scrollLeft +=100");
				
				found = wh.isElementPresent(DOShipmentnumbr, 1);
				
				count++;
			}
			
            
    		if(found) {
    			    			
    			DOShpmntNmbr = wh.getText(DOShipmentnumbr);
    			
    			if(!DOShpmntNmbr.equalsIgnoreCase("")){
    				
        			driver.switchTo().defaultContent();
        			wh.clickElement(ClosePOdetailsPurchaseOrder);
        			
        			if(wh.isElementPresent(ClosePurchaseOrder, 3)){
        				wh.clickElement(ClosePurchaseOrder);
        			}			
        			
                    break;
    			}
    			
    			//driver.switchTo().RTS Tab and back;
    			}else{
    				report.addReportStep(stepName, 
    						"Can't find DO number. Attempt #" + attemptNumber, 
    						StepResult.WARNING);
	    			wh.moveToElement(RTStab);
	    			wh.clickElement(RTStab);
	    			Thread.sleep(3000);
	    			wh.moveToElement(DOtab);
	    			wh.clickElement(DOtab);	
	    			
            }
                    	
    	}
        
        // If Shipment number not found, error and fail test
        if (DOShpmntNmbr == null || DOShpmntNmbr.equals("")){
        	report.addReportStep(stepName, 
        			"Can't find DO number in " + attemptNumber + " attempts", 
        			StepResult.FAIL);
        	rc.throwTCTerminationException();
        }
		return DOShpmntNmbr;
	}
	
	/**
	 * 
	 * @param shouldBeCreated
	 * @param expectedRowCount Amount of rows that we expect
	 * @throws Exception
	 */
	public void validateCorrectNumberOfRtsRows(Boolean shouldBeCreated, Integer expectedRowCount) throws Exception{
		try{
			if (!shouldBeCreated){
				if(!rtsRowsExist()){
					report.addReportStep("Rows should not exist in RTS table", 
							"No rows exist as expected in RTS table", 
							StepResult.PASS);
				}else{
					throw new Exception("An RTS exists when it should not.");
				}
			}else{

				// Get actual number of Rows
				Integer actualRowCount = getRtsRowCount();
				
				if(actualRowCount.equals(expectedRowCount)){

					report.addReportStep("Check for correct number of rows in RTS table", 
							"Correct number of rows exist in the RTS table. Number of rows = " + expectedRowCount + ".", 
							StepResult.PASS);
				}else{
					throw new Exception("Incorrect number or rows exist. Expected: [" + expectedRowCount + "]. Actual: [" + actualRowCount);
				}
			}
				
		}catch(Exception e){

			report.addReportStep("Check for correct number of rows in 'RTS' table", 
					"Did not validate the correct number of rows for 'RTS' table. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	
	public void validateCorrectNumberOfRtsLineItemsRows(Integer expectedRowCount) throws Exception{
		try{
			// Compare Line Item rows from UI (actual) to rows from XML (expected)
			
			Integer lineItemCountOnScreen = getLineItemsRowCount();
			
			if (!lineItemCountOnScreen.equals(expectedRowCount)){
				
				throw new Exception("Incorrect number of line items appear for RTS. Expected: [" 
								+ expectedRowCount + "] Actual: [" + lineItemCountOnScreen + "]");
			}
		}catch(Exception e){
			report.addReportStep("Check for correct number of rows in 'Line Items' table", 
					"Did not validate the correct number of rows for 'Line Items' table. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	public void validateQuantityWeightAndVolume(Integer expectedRowCount) throws Exception{
		// For each row on screen, validate quantity, weight, and volume
		for(Integer i=0; i< expectedRowCount; i++){
			Common common = new Common(ic);
			TPEPostMessagePageObject tpepostmessagepageobject = new TPEPostMessagePageObject(ic);
			
			// Validate Quantity
			String expectedQuantity = tpepostmessagepageobject.getQuantityFromXml(i);
			String actualQuantity = getQuantity_RTSDetail(i);
	
			common.compareStrings(expectedQuantity, actualQuantity, "Quantity", false);
			
			// Validate Weight
			String expectedWeight = tpepostmessagepageobject.getWeightFromXml(i);
			String actualWeight = getWeight_RTSDetail(i);
			
			common.compareStrings(expectedWeight, actualWeight, "Weight", false);
			
			// Validate Volume
			String expectedVolume = tpepostmessagepageobject.getVolumeFromXml(i);
			String actualVolume = getVolume_RTSDetail(i);
			
			common.compareStrings(expectedVolume, actualVolume, "Volume", false);
		}	
	}
	
	public Boolean isAddToDOButtonEnabled(){
		Boolean enabled = false;
		By locator = By.xpath("//a[.='Add to DO']");
		try{
			String classAttr = wh.getAttribute(locator, "class");
			if (!classAttr.contains("x-btn-disabled")){
				enabled = true;
			}
		}catch(Exception e){
			report.addReportStep("Verify if 'Add to DO' Button is enabled", 
					"Unable to verify if 'Add to DO' button is enabled. " + e.getMessage(), 
					StepResult.FAIL);
		}
		return enabled;
	}
	
	public void clickAddToDOArrowButton() throws TCTerminationException{

		WebElement actBtn =  driver.findElement(ADDTODOARROWBUTTON);

		int buttonWidth = Integer.parseInt(actBtn.getCssValue("width").substring(0, 2));

		int xOffset = buttonWidth - 3;

		Actions builder = new Actions(driver);

		builder.moveToElement(actBtn, xOffset, 0);

		builder.click().perform();
	}
	
	public void clickCreateDOOptionButton() throws TCTerminationException{
		Common common = new Common(ic);
		
		common.click(CREATEDOOPTION, "'Create DO' option button", 5, true);
	}
	
	public void createDO() throws TCTerminationException{
		clickAddToDOArrowButton();
		clickCreateDOOptionButton();

		By frameLocator = By.xpath("//*[contains(@src, 'DOCreateMainPO.xhtml')]");
		Common common = new Common(ic);
		common.switchToFrame(frameLocator, "Create DO");

		// Wait for Frame to appear
		
		// Click 'Save' button
		common.click(SAVEBUTTON_CREATEDOSCREEN, "Save button", 10, true);
		
		
		common.waitForElement(DODETAILSMAINHEADERID, "ID of saved DO", 60, true);
		
		// Close 'Create DO' screen
		
		By locator = By.xpath("//*[contains(@class, 'x-tool-close') "
				+ "and parent::*[preceding-sibling::*[child::*[text()='Create DO - Distribution Order' and contains(@class, 'x-window-header-text')]]] "
				+ "and ancestor::*[not(contains(@id, 'ghost')) "
				+ "and contains(@class, 'x-window-closable')]]");
		driver.switchTo().defaultContent();
		
		try {
			wh.clickElement(locator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		
	}
	/**
	 * This function returns the shipment ID from this page
	 * @return
	 */
	public String getShipmentId(){
		return DOShipment;
	}
}
