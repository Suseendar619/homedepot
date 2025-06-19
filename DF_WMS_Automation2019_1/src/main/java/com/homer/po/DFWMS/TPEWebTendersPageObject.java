package com.homer.po.DFWMS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homer.dao.DataClass;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;

public class TPEWebTendersPageObject extends PageBase{

	public TPEWebTendersPageObject(InstanceContainer ic) {
		// TODO Auto-generated constructor stub
		super(ic);
		common = new Common(ic);
	}
	
	static final By FILTERLISTLINK = By.xpath("//*[contains(@class, 'sectionheaderlink') and text()= 'Filter List']");
	static final By FILTERLISTFORM = By.xpath("//form[@name='FilterList']");
	static final By NEWFILTERLINK = By.xpath("//*[contains(@class, 'sectionheaderlink') and text()='New Filter']");
	static final By NEWFILTERFORM = By.xpath("//form[@name='FilterDetailsForm']");
	static final By SHIPMENTIDFILTERFORM = By.xpath("//input[@type='text' and parent::*[preceding-sibling::*[contains(@class,'textsmhead') and child::font[text()='Shipment ID']]]]");
	static final By WEBTENDERSFRAME = By.xpath("//iframe[contains(@src,'WebTenders.jsp')]");
	static final By FILTERLISTFRAME = By.xpath("//iframe[contains(@src,'FilterList.jsp')]");
	static final By APPLYBUTTONNEWFILTER = By.xpath("//button[contains(text(),'Apply')]");
	static final By ACCEPTBUTTON = By.xpath("//button[contains(@onclick, 'accept')]");
	
	
	static final String SETLINK = "//a[.='Set' and ancestor::tr[descendant::*[contains(text(), '" + PLACEHOLDER + "')]]]";
	static final String SHIPMENTROWSTRING= "//tr[child::*[contains(@class, 'listdata') and child::*[contains(text(), '" + PLACEHOLDER + "')]]]";
	static final String CHECKBOX="//*[@name='shipmentId' and parent::*[following-sibling::*[child::a[contains(@class, 'listdatalink') "
			+ "and text()='" + PLACEHOLDER + "']]]]";
	private Common common;
	public static enum Actions{
		ACCEPT ("Accept", By.xpath("//button[contains(@onclick, 'accept')]")),
		DECLINE("Decline", By.xpath("//button[contains(@onclick, 'decline')]"));
		
		private String value;
		private By buttonLocator;
		
		Actions(String value, By buttonLocator){
			this.value = value;
			this.buttonLocator = buttonLocator;
		}
		
		public String getValue(){
			return this.value;
		}
		
		public By getLocator(){
			return this.buttonLocator;
		}
	}
	
	public static enum StatusTypes{
		TENDERSTATUS ("Tender Status"),
		CARRIERRESPONSE("Carrier Response");
		
		private String value;
		
		StatusTypes(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}

	/**
	 * This function clicks the 'Filter List' link
	 * @throws TCTerminationException
	 */
	public void clickFilterListLink() throws TCTerminationException{
		try{
			switchToWebTendersFrame();

			if(!wh.isElementPresent(FILTERLISTLINK)){
				throw new Exception("Unable to find 'Filter List' link");
			}
			wh.clickElement(FILTERLISTLINK);
			rc.addReportStep("Click 'Filter List' link", "Clicked 'Filter List' link", StepResult.PASS);
		}catch(Exception e){
			String msg = "Unable to click 'Filter List' link. " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep("Click 'Filter List' link. ", msg, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		// Wait for form to appear
		try{
			switchToFilterListFrame();
			if(!wh.isElementPresent(FILTERLISTFORM)){
				throw new Exception("The Filter List Form not visible.");
			}
			rc.addReportStep("Wait for 'Filter List' form to appear", "'Filter List' form appears", StepResult.PASS);
		}catch(Exception e){
			String msg = "Unable to bring up 'Filter List' form. " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep("Wait for 'Filter List' form to appear. ", msg, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		
	}
	
	/**
	 * This function clicks the 'New Filter' link
	 * @throws TCTerminationException
	 */
	public void clickNewFilter() throws TCTerminationException{
		String stepName = "Wait for 'New Filter' form to appear";
		
		try{
			switchToFilterListFrame();
			if(!wh.isElementPresent(NEWFILTERLINK)){
				throw new Exception("Unable to find 'New Filter' link");
			}
			wh.clickElement(NEWFILTERLINK);
			rc.addReportStep("Click 'New Filter' link", "Clicked 'New Filter' link", StepResult.PASS);
		}catch(Exception e){
			String msg = "Unable to click 'New Filter' link. " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep("Click 'New Filter' link", msg, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		// Wait for 'New Filter' form to appear
		try{
			if(!wh.isElementPresent(NEWFILTERFORM)){
				throw new Exception("The New Filter link does not appear");
			}
			rc.addReportStep(stepName, "'New Filter' form appears", StepResult.PASS);
		}catch(Exception e){
			rc.addReportStep(stepName, 
					"'New Filter' form does not appear'. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}		
	}
	
	/**
	 * This function clicks the 'Apply' button when creating a new filter
	 * @throws TCTerminationException
	 */
	public void clickApplyButtonForNewFilter() throws TCTerminationException{
		String description = "Click 'Apply' button for 'Filter List' form. ";
		try{
			switchToFilterListFrame();

			if(!wh.isElementPresent(APPLYBUTTONNEWFILTER)){
				throw new Exception("Unable to find 'Apply Button'");
			}
			
			wh.clickElement(APPLYBUTTONNEWFILTER);
			rc.addReportStep(description, "Clicked 'Apply' Button for 'Filter List' form", StepResult.PASS);
			
		}catch(Exception e){
			String msg = "Unable to click 'Filter List' form. " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep(description, msg, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		
	}
	
	/**
	 * This function enters the Shipment ID in the Filter form
	 * @param id Shipment ID to filter
	 * @throws TCTerminationException
	 */
	public void enterShipmentIDInFilterForm(String id) throws TCTerminationException{
		String description = "Enter Shipment ID in Filter Form";
		try{
			if(!wh.isElementPresent(SHIPMENTIDFILTERFORM)){
				throw new Exception("Unable to find 'New Filter' link");
			}
			wh.clickElement(SHIPMENTIDFILTERFORM);
			wh.sendKeys(SHIPMENTIDFILTERFORM, id);

			rc.addReportStep(description, "Entered Shipment ID [" + id + "] in Filter form", StepResult.PASS);
		}catch(Exception e){
			String actualResult = "Unable to enter Shipment ID in Filter Form. " + e.getMessage();
			rc.logger.info(actualResult);
			rc.addReportStep(description, actualResult, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	/**
	 * This function gets the locator for the row in the Web Tenders table associated with
	 * a shipment ID
	 * @param shipmentId
	 * @return
	 */
	public By getShipmentRowLocator(String shipmentId){
		String locatorString = SHIPMENTROWSTRING.replace(PLACEHOLDER, shipmentId);
		By locator = By.xpath(locatorString);
		return locator;
	}
	
	/**
	 * This function gets the row in the Web Tenders table associated with a shipment ID
	 * @param shipmentId
	 * @return
	 * @throws TCTerminationException
	 */
	public WebElement getShipmentRowEl(String shipmentId) throws TCTerminationException{
		By locator = getShipmentRowLocator(shipmentId);
		return common.getElement(locator, "Shipment Row for Shipment ID: " + shipmentId, true);
	}
	
	/**
	 * This function gets the Tender Status/Carrier Response column number from table
	 * @return
	 * @throws Exception
	 */
	private Integer getStatusColumn() throws Exception {
		// Find column number for 'Tender Status'

		Integer columnNumber = 0;
		try{
			List <WebElement> columns = wh.getElements(By.xpath("//*[contains(@class, 'jlisthdrrow')]//td"));
		
			
			Boolean columnFound = false;
			Boolean linksFound = false;
	
			while (!columnFound && columnNumber < columns.size()){
				WebElement column = columns.get(columnNumber);
				List <WebElement> links = column.findElements(By.xpath(".//*"));
				Integer j = 0;
				while (!linksFound && j < links.size()){
					WebElement link = links.get(j);
					String header = StatusTypes.TENDERSTATUS.getValue();
					String text = link.getText().trim();
					if (text.equals(header)){
						linksFound = true;
						columnFound = true;
					}
					j++;
				}
				columnNumber++;
			}
		}catch(Exception e){
			throw new Exception("Unable to get column for Tender Status/Carrier Response. " + e.getMessage());
		}
		
		
		return columnNumber;
	}
	
	/**
	 * This function gets the Tender Status from the Web Tenders page
	 * @param shipmentId Shipment ID for table row
	 * @return
	 * @throws TCTerminationException
	 */
	public String getTenderStatus(String shipmentId) throws TCTerminationException{
		String tenderResponse = new String();
		
		try{
			// Get row
			WebElement el = getShipmentRowEl(shipmentId);
			
			// Get element in the 'Carrier Reply' column
			List <WebElement> els = el.findElements(By.tagName("td"));
			
			Integer i = getStatusColumn();
			WebElement tenderStatusCell = els.get(i-1);
			tenderResponse = tenderStatusCell.getText();
		 	tenderResponse = tenderResponse.substring(0, tenderResponse.indexOf("\n")).trim();
		}catch(Exception e){
			report.addReportStep("Get Tender Status", "Unable to get Tender Status. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		
		return tenderResponse;
		
	}
	
	/**
	 * This function gets the Carrier Reply status from the Web Tenders page
	 * @param shipmentId Shipment ID for table row
	 * @return
	 * @throws TCTerminationException
	 */
	public String getCarrierReply(String shipmentId) throws TCTerminationException{
		String carrierReply = new String();
		try{
			if (shipmentId.equals("") || shipmentId == null){
				throw new Exception ("Shipment ID is null or empty");
			}
			
			// Get row
			WebElement el = getShipmentRowEl(shipmentId);
			
			// Get element in the 'Carrier Reply' column
			List <WebElement> els = el.findElements(By.tagName("td"));
			Integer i = getStatusColumn();
			WebElement carrierReplyCell = els.get(i-1);
			carrierReply = carrierReplyCell.getText();
			carrierReply = carrierReply.substring(carrierReply.indexOf("\n")).trim();
			
		}catch(Exception e){
			report.addReportStep("Get Carrier Reply", "Unable to get Carrier Reply. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		return carrierReply;
		
	}

	/**
	 * This function clicks the checkbox associated with a shipment ID
	 * @param shipmentId
	 * @throws Exception
	 */
	public void clickCheckboxForShipmentId(String shipmentId) throws TCTerminationException{
		try{
			switchToWebTendersFrame();
			// Get row
			By rowLocator = getShipmentRowLocator(shipmentId);
			WebElement rowEl = wh.getElement(rowLocator);
			
			// Get checkbox in row
			WebElement checkBoxEl = rowEl.findElement(By.name("shipmentId"));

			// Click checkbox
			wh.clickElement(checkBoxEl);
			
			rc.addReportStep("Click Checkbox For Shipment ID", 
					"Clicked checkbox for ID: [" + shipmentId + "]. ", 
					StepResult.PASS);
		}catch(Exception e){
			rc.addReportStep("Click Checkbox For Shipment ID", 
					"Unable to click checkbox for Shipment ID + [" + shipmentId + "]. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	
	/**
	 * This function clicks the Accept button
	 * @throws TCTerminationException
	 */
	public void clickAcceptButton() throws TCTerminationException{
	
		clickAcceptDeclineButton(Actions.ACCEPT);
	}
	
	/**
	 * This function clicks the Decline button
	 * @throws TCTerminationException
	 */
	public void clickDeclineButton() throws TCTerminationException{
		
		clickAcceptDeclineButton(Actions.DECLINE);
	}
	
	/**
	 * This function either clicks the 'Accept' or 'Decline' button based on
	 * action given
	 * @param action
	 * @throws TCTerminationException
	 */
	public void clickAcceptDeclineButton(Actions action) throws TCTerminationException{
		String buttonType = action.getValue();
		By locator = action.getLocator();
		try{
			if (!wh.isElementPresent(locator)){
				throw new Exception ("'" + buttonType + "' button not visible");
			}
			
			wh.clickElement(locator);
			rc.addReportStep("Click '" + buttonType + "' Button", 
					"Clicked '" + buttonType + "' button", 
					StepResult.PASS);

		}catch(Exception e){
			rc.addReportStep("Click '" + buttonType + "' Button", "Unable to click '" +
					buttonType + "' button. " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	

	/**
	 * This function returns the locator for the Checkbox in the Web Tenders table
	 * associated with a shipment ID.
	 * @param shipmentId
	 * @return
	 */
	public By getCheckboxForShipmentId(String shipmentId){
	
		String locatorString = CHECKBOX.replace(PLACEHOLDER, shipmentId);
		By locator = By.xpath(locatorString);

		return locator;
	}
	
	/**
	 * This function switches to the 'Web Tenders' frame
	 * @throws Exception 
	 */
	public void switchToWebTendersFrame(){
		String description = "Bring up Web Tenders Frame";
		try{
			driver.switchTo().defaultContent();
			if(!wh.isElementPresent(WEBTENDERSFRAME)){
				throw new Exception("Unable to find 'Filter List' Frame.");
			}
			driver.switchTo().frame(driver.findElement(WEBTENDERSFRAME));
		}catch(Exception e){
			rc.addReportStep(description, "Unable to bring up Web Tenders frame. " + e.getMessage(), StepResult.FAIL);
		}
		
	}
	
	/**
	 * This function switches to the 'Filter List' frame
	 * @throws Exception 
	 */
	public void switchToFilterListFrame() throws Exception{
		switchToWebTendersFrame();
		if(!wh.isElementPresent(FILTERLISTFRAME)){
			throw new Exception("Unable to find 'Filter List' Frame.");
		}
		driver.switchTo().frame(driver.findElement(FILTERLISTFRAME));
	}
	
	/**
	 * This button clicks the "Set Link" button in the Web Tenders page
	 * @throws TCTerminationException 
	 */
	public void clickSetLink(String shipmentId) throws TCTerminationException{
		switchToWebTendersFrame();
		
		// Find location of link
		String locationStr = SETLINK.replace(PLACEHOLDER, shipmentId);
		By location = By.xpath(locationStr);
		
		// Click link
		common.click(location, "'Set' link in Web Tenders Table", 5, true);
	}
}
