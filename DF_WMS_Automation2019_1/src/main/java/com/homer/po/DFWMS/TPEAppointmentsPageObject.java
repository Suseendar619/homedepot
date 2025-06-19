package com.homer.po.DFWMS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.Common;
import com.homer.po.DFWMS.TPELoginPageObject.UserTypes;

import org.openqa.selenium.support.ui.Select;

public class TPEAppointmentsPageObject extends PageBase {

	public TPEAppointmentsPageObject(InstanceContainer ic) {
		super(ic);
		common = new Common(ic);
	}

	public static final By APPOINTMENTVALUEINPUT = By.xpath("//*[@alt='Find  Appointment']");
	public static final By APPTDETAILSTAB = By.xpath("//a[contains(text(),'Appointment Details')]");
	public static final By APPOINTMENTIDVALUE=By.id("dataForm:appointmentId_d");
	public static final By FACILITYVALUE=By.id("dataForm:facility_d0");
	public static final By FACILITYNAMEVALUE=By.id("dataForm:facility_d00");
	public static final By SUGGESTEDSTARTDATEVALUE=By.id("dataForm:startTime_d1");
	public static final By APPOINTMENTTYPEVALUE=By.id("dataForm:type_d");
	public static final By EQUIPMENTCODEVALUE=By.id("dataForm:equipment_d");
	public static final By LOADCONFIGURATIONVALUE=By.id("dataForm:loadConfiguration_d0");
	public static final By ESTTRAILERDURATIONVALUE=By.id("dataForm:estTrailerDuration_d");
	public static final By ESTTRACTORDURATIONVALUE=By.id("dataForm:estTractorDuration_d");
	public static final By ESTDEPARTUREDATEVALUE=By.id("dataForm:estDepartureDateTime_d");
	public static final By APPOINTMENTREQUESTEDDATEVALUE=By.id("dataForm:requestedDateTime_d");
	public static final By ACTUALCHECKINDTTMVALUE=By.id("dataForm:actCDttm1");
	public static final By APPROVEBUTTON = By.xpath("//input[@value='Approve']");
	public static final By EDITBUTTON = By.xpath("//*[@value='Edit' and @type='button']");
	public static final By RECOMMENDTIMESLOTSBUTTON = By.xpath("//*[@value='Recommend Time Slots' and @type='button']");
	public static final By APPOINTMENTSHEADERROW=By.id("dataForm:listView:dataTable_headDiv");
	public static final By RECOMMENDATIONSDIALOG = By.xpath("//*[@id='dataForm:Recommendations_Dialog']");
	public static final By SELECTBUTTON_RECOMMENDATIONSDIALOG = By.xpath("//*[@id='dataForm:selectBtn']");
	public static final By SAVEBUTTON_ADDAPPOINTMENT = By.xpath(".//*[@value='Save' and @type='button']");
	public static final By VALIDATEBUTTON_ADDAPPOINTMENT = By.xpath("//*[@id='workareafootertd']//input[@value='Validate']");
	public static final By MESSAGESDIALOG = By.xpath("//*[contains(@id, 'dataForm:') and descendant::*[contains(@class, 'pop_hdr_inner') and contains(., 'Messages')]]");
	public static final By MESSAGEDIALOGCLOSEBUTTON = By.xpath("//*[contains(@id, 'dataForm:') and descendant::*[contains(@class, 'pop_hdr_inner') and contains(., 'Messages')]]//*[contains(@class, 'pop_close')]");	
	public static final By FACILITYDESTINPUT = By.xpath("//*[@id='dataForm:facility_det_carr' or @id='dataForm:facility_det']");
	public static final By SUGGESTEDSTARTDATEINPUT = By.id("dataForm:startTime_det");
	public static final By ESTDEPARTUREDATEINPUT = By.id("dataForm:estDepartureDateTime_det");
	public static final By APPOINTMENTREQUESTEDDATEINPUT = By.id("dataForm:requestedDateTime_det");
	public static final By appointwindwclose = By.xpath("(//*[contains(.,'Appointment')]//following::img[contains(@class,'close')])[5]");
	public static final By ADDITIIONALDETAILSTAB = By.xpath("//*[contains(@id,'TABH_dataForm') and @title='Additional Details']//a");
	public static final By CANCELEDCHECKBOX = By.xpath("//*[contains(@id, 'dataForm:cancelled')]");
	public static final By CANCELREASONCODEDROPDOWN = By.xpath("//*[contains(@onchange, 'populateReasonCode')]");
	public static final By APPTOBJECTSTABLECONTAINER = By.id("dataForm:apptObjTable_container");
	public static final By APPROPRIATECOMMENTS = By.xpath("//*[contains(@class, 'captionData') and contains(@id, 'dataForm:appointmentStatus')]");
	public static final By TABS = By.xpath("//*[contains(@class, 'tab_sel_top')]//a");
	
	private static String dropDownOptionString = "//option[text()='" + PLACEHOLDER + "']";
	private static String appointmentRowString="//*[@id='dataForm:listView:dataTable_bodyDiv' and descendant::*[contains(@id, 'apptId') and text()='" + PLACEHOLDER + "']]";
	private static String AppointmentSlotRadio = "(.//*[@id='checkAll_c_dataForm:recommendationTable'])[" + PLACEHOLDER + "]";	
	private static String AppointmentSlotsTime= ".//*[@id='dataForm:recommendationTable:" + PLACEHOLDER + ":recommendationSlotDesc']";
	
	public static final By MsgWindow = By.xpath("(//*[@id='er_d1_c1']");
	public static final By Popupclose = By.className("pop_close");
	public static final By RecomendationWindow = By.xpath("//*[@id='dataForm:Recommendations_DialogTemplate_cCId']/input");
	public static final By RecomendationTimeSlot = By.xpath("//*[@id='dataForm:apptList_btn_10']");
	public static final By RecomendationSelect = By.xpath("//*[@id='dataForm:selectBtn']");
	
	private String selectedStartTime;
	private String selectedDepartureTime;

	private String facilityValue;
	private String facilityNameValue;
	private String suggestedStartDateValue;
	private String appointmentTypeValue;
	private String appointmentIdValue;
	private String equipmentCodeValue;
	private String loadConfigurationValue;
	private String estTrailerDurationValue;
	private String estTractorDurationValue;
	private String estDepartureDateValue;
	private String appointmentRequestedDateValue;
	private String actualCheckInDttmValue;
	
	private static String CHECKBOX = "//input[@type ='checkbox' and "
			+ "parent::*[following-sibling::*[descendant::*[contains(@id, 'apptId') and .='" + PLACEHOLDER + "']]]]";
	
	Common common;
	
	/**
	 * This function searches for appointment. First, we enter the appointment ID into the form.
	 * Then, we press 'Enter' and wait for the page to fully load
	 * @param appointmentId
	 */
	public void searchForAppointment(String appointmentId){
		String stepName = "Search for Appointment";
		
		try{
			switchToAppointmentsFrame();
			common.click(APPOINTMENTVALUEINPUT, "Appointment Value Input", 15, true);
			wh.sendKeys(APPOINTMENTVALUEINPUT, appointmentId);
			common.getElement(APPOINTMENTVALUEINPUT, "Appointment Value Input", true).sendKeys(Keys.ENTER);

			waitForApptTabsToLoad_DefaultView();
			
			report.addReportStep(stepName, "Searched for and found appointment #" + appointmentId, StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(stepName, "Unable to search for appointment. " + e.getMessage(), StepResult.FAIL);
		}
	}

	/**
	 * This function waits for the Tabs at the bottom of the page to fully load
	 * in default mode (not edit mode)
	 * @throws TCTerminationException
	 */
	public void waitForApptTabsToLoad_DefaultView() throws TCTerminationException{
		
			// Wait for tab to appear
			common.waitForElement(TABS, "Details tab on the bottom of Appointments page", 25, true);
			
			// Get the name of the selected tab
			WebElement selectedTab = common.getElement(TABS, "Select Details tab", true);
			
			String tabName = selectedTab.getText();
			
			By locator = null;
			String description = "";
			
		try{	
			
			if(tabName.equalsIgnoreCase("Appointment Details")){
				
				locator = FACILITYVALUE;
				description = "Appointment Details Tab on 'Appointments' Page";
				
			}else if(tabName.equalsIgnoreCase("Appointment Objects")){
				
				locator = APPTOBJECTSTABLECONTAINER;
				description = "Appointment Objects table container";
				
			}else if(tabName.equalsIgnoreCase("Additional Details")){
				
				locator = APPROPRIATECOMMENTS;
				description = "Appropriate Comments on Additional Details tab";
				
			}else{
				throw new Exception("Selected Tab [" + tabName + "] does not match any expected"
						+ "values");
			}
			
			common.waitForElement(locator, description, 25, true);
		}catch(Exception e){
			report.addReportStep("Wait for Appointment Details tabs to load", 
					"Error waiting for full page to load. " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}		
	}
	
	/**
	 * This function waits for the Appointment Details Tab to fully load
	 * @throws TCTerminationException
	 */
	public void waitForApptDetailsTab_EditView() throws TCTerminationException{
		common.waitForElement(FACILITYDESTINPUT, "Edit screen for Appointment Details Tab", 25, false);
	}
	
	/**
	 * This function returns the Appointment Status
	 * @param appointmentId
	 * @return
	 */
	public String getAppointmentStatus(String appointmentId){
		String appointmentStatus = new String();
		
		try{
			Integer columnNumber = getColumnNumber_AppointmentsPage("Appointment Status");
			
			// Get row
			WebElement el = getAppointmentsRowEl(appointmentId);
			
			// Get element in the 'Carrier Reply' column
			List <WebElement> els = el.findElements(By.tagName("td"));
			
			WebElement statusCell = els.get(columnNumber-1);
			appointmentStatus = statusCell.getText();
		}catch(Exception e){
			report.addReportStep("Get Appointment Status", 
					"Unable to get appointment status for Appointment ID: " + appointmentId + ". " + e.getMessage(), 
					StepResult.FAIL);
		}
	
		return appointmentStatus;
	}
	
	/**
	 * This function gets the row element (WebElement) from the desired appointment ID
	 * @param appointmentId
	 * @return
	 * @throws Exception
	 */
	public WebElement getAppointmentsRowEl(String appointmentId) throws Exception{
		By locator = getAppointmentRowLocator(appointmentId);
		
		
		return wh.getElement(locator);
	}
	
	/**
	 * This function gets the row locator (By) for the desired appointment ID
	 * @param appointmentId
	 * @return
	 */
	public By getAppointmentRowLocator(String appointmentId){
		String locatorString = appointmentRowString.replace(PLACEHOLDER, appointmentId);
		By locator = By.xpath(locatorString);
		return locator;
	}
	
	/**
	 * This function gets the row for the desired appointment ID
	 * @param appointmentId
	 * @return
	 * @throws TCTerminationException
	 */
	public WebElement getAppointmentRowEl(String appointmentId) throws TCTerminationException{
		By locator = getAppointmentRowLocator(appointmentId);
		return common.getElement(locator, "Appointment Row", true);
	}
	
	/**
	 * This function gets the column number for the desired column header from the appointments page
	 * @param desiredHeader
	 * @return
	 * @throws TCTerminationException
	 */
	private Integer getColumnNumber_AppointmentsPage(String desiredHeader) throws TCTerminationException{

		List <WebElement> columns = common.getElement(APPOINTMENTSHEADERROW, "Appointments Header Row", true).findElements(By.tagName("td"));
		Integer i = 0;
		Boolean columnFound = false;
		
		while (!columnFound && i < columns.size()){
			WebElement column = columns.get(i);
			
			String actualColumnText = column.getText();
			if (actualColumnText.equals(desiredHeader)){
				columnFound = true;
			}
			i++;
		}
		
		if (!columnFound){
			report.addReportStep("Get Appointmnent Status Column", "Unable to find column header named '" + desiredHeader + "'", StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
		return i;
	}
	
	/**
	 * This function gets the Appointment ID value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getAppointmentId() throws TCTerminationException{
		return getAppointmentDetails(APPOINTMENTIDVALUE, "Appointment ID value");
	}
	
	/**
	 * This function gets the Facility value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getFacility() throws TCTerminationException{
		return getAppointmentDetails(FACILITYVALUE, "Facility value");
	}
	
	/**
	 * This function gets the Facility Name value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getFacilityName() throws TCTerminationException{
		return getAppointmentDetails(FACILITYNAMEVALUE, "Facility Name value");
	}
	
	/**
	 * This function gets the Suggested Start Date value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getSuggestedStartDate() throws TCTerminationException{
		return getAppointmentDetails(SUGGESTEDSTARTDATEVALUE, "Suggested Start Date value");
	}
	
	/**
	 * This function gets the Appointment Type value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getAppointmentType() throws TCTerminationException{
		return getAppointmentDetails(APPOINTMENTTYPEVALUE, "Appointment Type value");
	}
	
	/**
	 * This function gets the Equipment Code value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getEquipmentCode() throws TCTerminationException{
		return getAppointmentDetails(EQUIPMENTCODEVALUE, "Equipment Code value");
	}
	
	/**
	 * This function gets the Load Configuration value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getLoadConfiguration() throws TCTerminationException{
		return getAppointmentDetails(LOADCONFIGURATIONVALUE, "Load Configuration value");
	}
	
	/**
	 * This function gets the Est Trailer Duration value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getEstTrailerDuration() throws TCTerminationException{
		return getAppointmentDetails(ESTTRAILERDURATIONVALUE, "Est Trailer Duration value");
	}
	
	/**
	 * This function gets the Est Tractor Duration value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getEstTractorDuration() throws TCTerminationException{
		return getAppointmentDetails(ESTTRACTORDURATIONVALUE, "Est Tractor Duration value");
	}
	
	/**
	 * This function gets the Est Departure Date value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getEstDepartureDate() throws TCTerminationException{
		return getAppointmentDetails(ESTDEPARTUREDATEVALUE, "Est Departure Date value");
	}
	
	/**
	 * This function gets the Appointment Requested Date value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getAppointmentRequestedDate() throws TCTerminationException{
		return getAppointmentDetails(APPOINTMENTREQUESTEDDATEVALUE, "Appointment Requested Date value");
	}
	
	/**
	 * This function gets the Actual Check In Dttm value
	 * @return
	 * @throws TCTerminationException
	 */
	public String getActualCheckInDttm() throws TCTerminationException{
		return getAppointmentDetails(ACTUALCHECKINDTTMVALUE, "actualCheckInDttm value");
	}
	
	/**
	 * This function gets the appointment details based on the type of value desired
	 * @param locator Locator on 'Appointment Details' tab
	 * @param name Name of element desired
	 * @return
	 * @throws TCTerminationException
	 */
	private String getAppointmentDetails(By locator, String name) throws TCTerminationException{
		String text = new String();
		WebElement el = common.getElement(locator, name, true);
		if(el==null){
			text = "";
		}else{
			text = el.getText();
		}
		
		return text;
	}
	
	/**
	 * This function clicks the 'Approve' button and waits for page to load
	 * @throws TCTerminationException
	 */
	public void clickApproveButton() throws TCTerminationException{
		common.click(APPROVEBUTTON, "'Approve' button on Appointments Page", 5, true);
		waitForApptTabsToLoad_DefaultView();
	}
	
	/**
	 * This function gets the checkbox locator associated with an appointment ID
	 * @param appointmentId
	 * @return
	 */
	public By getCheckboxBy(String appointmentId){
		String locatorString = CHECKBOX.replace(PLACEHOLDER, appointmentId);
		By locator = By.xpath(locatorString);
		return locator;
	}
	
	/**
	 * This function gets the checkbox element associated with an appointment ID
	 * @param appointmentId
	 * @return
	 * @throws TCTerminationException
	 */
	public WebElement getCheckboxEl(String appointmentId) throws TCTerminationException{
		// Check for checkbox to be selected
		By locator = getCheckboxBy(appointmentId);
		WebElement el = common.getElement(locator, "Checkbox for 'Appointments' table'", true);
		return el;
	}
	
	/**
	 * This function selects the checkbox associated with an appointment ID
	 * @param appointmentId
	 * @throws TCTerminationException
	 */
	public void selectCheckbox(String appointmentId) throws TCTerminationException{
		// Check for checkbox to be selected
		By locator = getCheckboxBy(appointmentId);
		Boolean selected = isCheckboxSelected(appointmentId);
		
		// If not selected, click checkbox
		
		if (!selected){
			common.click(locator, "Checkbox for 'Appointments' table", 5, true);
		}
		
		waitForApptTabsToLoad_DefaultView();
	}
	
	/**
	 * This function checks to see if the appointments checkbox is selected for 
	 * a given appointment ID
	 * @param appointmentId
	 * @return
	 * @throws TCTerminationException
	 */
	private Boolean isCheckboxSelected(String appointmentId) throws TCTerminationException{
		Boolean isSelected = null;
		
		// Get Checkbox
		WebElement checkBoxEl = getCheckboxEl(appointmentId);
		
		// Get row that the checkbox is in
		WebElement rowEl = checkBoxEl.findElement(By.xpath("//ancestor::tr[contains(@class, 'advtbl_row')]"));
		
		// Check the row to see if it's selected or not
		String classAttribute = rowEl.getAttribute("class");
		if (classAttribute.contains("dg_tsr")){
			isSelected = true;
		}else{
			isSelected = false;
		}
		
		return isSelected;
	}
	
	/**
	 * This function selects the appointment slot on the 'Recommendations' dialog
	 * @param terminateOnFailure
	 * @throws Exception 
	 */
	public Boolean selectAppointmentSlot(Integer slotNumber, Boolean terminateOnFailure) throws Exception {

		switchToAppointmentsFrame();
		Boolean slotSelected = false;
		
		// Record Date/Time for recommendation. Used for reporting
		Integer textPosition = slotNumber - 1;
		String locatorStr = AppointmentSlotsTime.replace(PLACEHOLDER, textPosition.toString());
		By locator = By.xpath(locatorStr);
		
		// Check to see if Appointment Slots exist
		
		WebElement AppointmentSlotEl = common.getElement(locator, "Appointment Slot #" + slotNumber, terminateOnFailure);
		
		if (AppointmentSlotEl != null){
		
			String AppointmentSlot = AppointmentSlotEl.getText();
			
			// Save selected recommended start time
			String startString = "Start-Time:";
			String endString = "Departure-Time:";
			Integer startIndex = AppointmentSlot.indexOf(startString) + startString.length();
			Integer endIndex = AppointmentSlot.indexOf(endString);
			selectedStartTime = AppointmentSlot.substring(startIndex, endIndex).trim();
			
			// Save selected departure time. Handle string differently if logged in as carrier
			startString = endString;
			endString = "Appointment Slots:";
			
			if (AppointmentSlot.contains(endString)){
				endIndex = AppointmentSlot.indexOf(endString);
			}else{
				endIndex = AppointmentSlot.length();
			}
			startIndex = AppointmentSlot.indexOf(startString) + startString.length();
			
			selectedDepartureTime = AppointmentSlot.substring(startIndex, endIndex).trim();
			
			// Click Recommendation Slot
			locatorStr = AppointmentSlotRadio.replace(PLACEHOLDER, slotNumber.toString());
			locator = By.xpath(locatorStr);

			common.click(locator, "Time slot recommendation #" + slotNumber + " (" + AppointmentSlot + ") on 'Recommendations' dialog", 5, true);
			slotSelected = true;
			
			report.addReportStep("Select Appointment Slot", 
					"Appointment slot selected" , StepResult.PASS);
			
		}	
			
		else if (AppointmentSlotEl == null){	
				
				
				
				// If Appointment slots are not visible.
					
					// close msg and recommendation window
				
				//driver.switchTo().defaultContent();
						
				
				
					wh.clickElement(Popupclose);
					Thread.sleep(4000);
					System.out.println("Switching successfull");			
				
					
										
						driver.switchTo().defaultContent();
						
						
						driver.switchTo().frame(0);
						System.out.println("Switching1 successfull");							
					
						
							wh.clickElement(RecomendationWindow);						
							Thread.sleep(4000);		
				
											
						
					// select appointment type as drop unload
						
						driver.switchTo().defaultContent();					
						
							driver.switchTo().frame(0);	
							System.out.println("Switching2 successfull");
						

					Select oSelect = new Select(driver.findElement(By.id("dataForm:cd10")));
					oSelect.selectByVisibleText("Drop Unload");
					
					report.addReportStep("Appointment Drop Unload selected", 
							"App type change from Live to Drop Unload " , StepResult.PASS);
					
					Thread.sleep(2000);			
						
					
					//select recommendation time slot button

					wh.clickElement(RecomendationTimeSlot);	
					Thread.sleep(2000);	
									

					slotSelected = true;
				
						
			}

		else{
			report.addReportStep("Select Appointment Slot", 
					"Unable to click appointment slot #" + slotNumber + ". Value not shown in the screen"
					, StepResult.FAIL);
		}
		
		return slotSelected;
	}

	/**
	 * This function closes the 'Appointment' window
	 * @throws Exception
	 */
	public void closeAppointmentWindow() throws Exception{
		driver.switchTo().defaultContent();
		wh.clickElement(appointwindwclose);
	}
	
	/**
	 * This function clicks the 'Select' Button for the Recommendations Dialog
	 * when setting appointment time
	 * @param terminateOnFailure
	 * @throws TCTerminationException
	 */
	public void clickSelectButton_RecommendationsDialog() throws TCTerminationException{
		common.click(SELECTBUTTON_RECOMMENDATIONSDIALOG, "'Select' Button in Recommendations Dialog", 5, true);

		common.waitUntilElementNotVisible(RECOMMENDATIONSDIALOG, "Recommendations Dialog", 15, true);
	}
	
	/**
	 * This function waits for the 'Recommendations' Dialog for setting appointment time
	 * @throws TCTerminationException
	 */
	public void waitForRecommendationsDialog() throws TCTerminationException {
		switchToAppointmentsFrame();
		
		common.waitForElement(RECOMMENDATIONSDIALOG, "Recommendations Dialog", 20, false);
		
	}
	
	/**
	 * This function clicks the 'Save' button on the Add Appointment screen
	 * @throws TCTerminationException
	 */
	public void clickSaveButton_AddAppointment() throws TCTerminationException{
		switchToAppointmentsFrame();
		common.click(SAVEBUTTON_ADDAPPOINTMENT, "Save button on 'Add Appointment' screen", 4, true);
		waitForApptTabsToLoad_DefaultView();
	}
	
	/**
	 * This function clicks the 'Validate' button for the Add Appointment screen
	 * @throws TCTerminationException
	 */
	public void clickValidateButton_AddAppointment() throws TCTerminationException{
		switchToAppointmentsFrame();
		common.click(VALIDATEBUTTON_ADDAPPOINTMENT, "Validate button on 'Add Appointment' screen", 5, true);
		common.waitUntilElementVisible(MESSAGESDIALOG, "Messages Header", 20, true);
	}
	
	/**
	 * This function checks the text in the 'Messages' dialog box for "Validation successful)
	 * @throws TCTerminationException
	 */
	public void validateSuccess() throws TCTerminationException{
		switchToAppointmentsFrame();
		
		common.waitForElement(MESSAGESDIALOG, "'Messages' Dialog box", 5, false);
		
		String status = common.getElement(MESSAGESDIALOG, "Messages Dialog text", true).getText();
		status = status.substring(status.lastIndexOf('\n')).trim().replace(".", "");
		
		common.compareStrings("Validation successful", status, "Appointment Validation Message", true);
	}
	
	/**
	 * This function closes the 'Messages' dialog box and waits until it disappears
	 * @throws TCTerminationException
	 */
	public void closeMessagesDialog() throws TCTerminationException{
		switchToAppointmentsFrame();
		common.click(MESSAGEDIALOGCLOSEBUTTON, "'Close button for Message Dialog'", 5, true);
		common.waitUntilElementNotVisible(MESSAGESDIALOG, "Message Dialog box", 3, true);
	}
	
	/**
	 * This function clicks the edit button
	 * @throws TCTerminationException
	 */
	public void clickEditButton() throws TCTerminationException{
		switchToAppointmentsFrame();
		common.click(EDITBUTTON, "'Edit' button in Appointments", 5, true);

		waitForApptDetailsTab_EditView();		
	}
	
	/**
	 * This function clicks the 'Recommend Time Slots' button
	 * @throws TCTerminationException
	 */
	public void clickRecommendTimeSlotsButton() throws TCTerminationException{
		switchToAppointmentsFrame();
		common.click(RECOMMENDTIMESLOTSBUTTON, "'Recommend Time Slots' button in Appointments", 10, true);
		waitForRecommendationsDialog();
	}
	
	/**
	 * This function returns the saved selected start time from the 'Recommendations' dialog
	 * This is used so that we can validate this selection in other areas.
	 * @return
	 */
	public String getSelectedStartTime(){
		return this.selectedStartTime;
	}
	
	/**
	 * This function returns the saved selected departure time from the 'Recommendations' dialog.
	 * This is used so that we can validate this selection in other areas.
	 */
	public String getSelectedDepartureTime(){
		return this.selectedDepartureTime;
	}
	
	/**
	 * This function gets the suggested start date value from the edit screen
	 * @return
	 * @throws TCTerminationException
	 */
	public String getSuggestedStartDateValue_EditScreen() throws TCTerminationException{
		WebElement el = common.getElement(SUGGESTEDSTARTDATEINPUT, "Suggested Start Date/Time value", true);
		String value = el.getAttribute("value");
		return value;
	}
	
	/**
	 * This function gets the Departure Date value from the Edit screen
	 * @return
	 * @throws TCTerminationException
	 */
	public String getEstDepartureDateValue_EditScreen() throws TCTerminationException{
		WebElement el = common.getElement(ESTDEPARTUREDATEINPUT, "Estimated Departure Date/Time value", true);
		String value = el.getAttribute("value");
		return value;
	}
	
	/**
	 * This function gets the Appointment Requested Date value from the edit screen
	 * @return
	 * @throws TCTerminationException
	 */
	public String getAppointmentRequestedDateValue_EditScreen() throws TCTerminationException{
		WebElement el = common.getElement(APPOINTMENTREQUESTEDDATEINPUT, "Appointment Requested Date/Time value", true);
		String value = el.getAttribute("value");
		return value;
	}
	
	/**
	 * This function saves all appointment details to the appointment. Used for validation steps
	 * @throws TCTerminationException
	 */
	public void saveAllAppointmentDetails() throws TCTerminationException{
		clickAppointmentDetailsTab();
		
		appointmentIdValue = getAppointmentId();
		facilityValue = getFacility();
		facilityNameValue = getFacilityName();
		suggestedStartDateValue = getSuggestedStartDate();
		appointmentTypeValue = getAppointmentType();
		equipmentCodeValue = getEquipmentCode();
		loadConfigurationValue = getLoadConfiguration();
		estTrailerDurationValue = getEstTrailerDuration();
		estTractorDurationValue = getEstTractorDuration();
		estDepartureDateValue = getEstDepartureDate();
		appointmentRequestedDateValue = getAppointmentRequestedDate();
		actualCheckInDttmValue = getActualCheckInDttm();
		
		report.addReportStep("Record appointment Details", "Recorded appointment details. See below:"
				+ "<br><br>Appointment ID: " + appointmentIdValue 
				+ "<br>Facility Name: " + facilityNameValue
				+ "<br>Suggested Start Date/Time: " + suggestedStartDateValue
				+ "<br>Appointment Type: " + appointmentTypeValue
				+ "<br>Equipment Code: " + equipmentCodeValue
				+ "<br>Load Configuration: " + loadConfigurationValue
				+ "<br>Est Trailer Duration: " + estTrailerDurationValue
				+ "<br>Est Tractor Duration: " + estTractorDurationValue
				+ "<br>Est Departure Date: " + estDepartureDateValue
				+ "<br>AppointmentRequestedDate: " + appointmentRequestedDateValue
				+ "<br>ActualCheckInDttm: " + actualCheckInDttmValue,
				StepResult.PASS);
	}
	
	/**
	 * This function returns the saved value for appointment ID
	 * @return
	 */
	public String getSavedValueAppointmentId(){
		return appointmentIdValue;
	}
	
	/**
	 * This function returns the saved value for Facility
	 * @return
	 */
	public String getSavedValueFacility(){
		return facilityValue;
	}
	
	/**
	 * This function returns the saved value for facility name
	 * @return
	 */
	public String getSavedValueFacilityName(){
		return facilityNameValue;
	}
	
	/**
	 * This function returns the saved value for suggested start date
	 * @return
	 */
	public String getSavedValueSuggestedStartDate(){
		return suggestedStartDateValue;
	}
	
	/**
	 * This function returns the saved value for appointment type
	 * @return
	 */
	public String getSavedValueAppointmentType(){
		return appointmentTypeValue;
	}
	
	/**
	 * This function returns the saved value for equipment code
	 * @return
	 */
	public String getSavedValueEquipmentCode(){
		return equipmentCodeValue;
	}
	
	/**
	 * This function returns the saved value for load configuration
	 * @return
	 */
	public String getSavedValueLoadConfiguration(){
		return loadConfigurationValue;
	}
	
	/**
	 * This function returns the saved value for est trailer duration
	 * @return
	 */
	public String getSavedValueEstTrailerDuration(){
		return estTrailerDurationValue;
	}
	
	/**
	 * This function returns the saved value for tractor duration
	 * @return
	 */
	public String getSavedValueEstTractorDuration(){
		return estTractorDurationValue;
	}
	
	/**
	 * This function returns the saved value for est departure date
	 * @return
	 */
	public String getSavedValueEstDepartureDate(){
		return estDepartureDateValue;
	}
	
	/**
	 * This function returns the saved value for appointment requested date
	 * @return
	 */
	public String getSavedValueAppointmentRequestedDate(){
		return appointmentRequestedDateValue;
	}
	
	/**
	 * This function returns the saved value for Actual Check In Dttm
	 * @return
	 */
	public String getSavedValueActualCheckInDttm(){
		return actualCheckInDttmValue;
	}
	
	/**
	 * This function clicks the Appointment Details tab
	 * @throws TCTerminationException
	 */
	public void clickAppointmentDetailsTab() throws TCTerminationException{
		common.click(APPTDETAILSTAB, "'Appointment Details' tab on Appointments Page", 5, true);	
	}
	
	/**
	 * This function clicks the Additional Details tab
	 * @throws TCTerminationException
	 */
	public void clickAdditionalDetailsTab() throws TCTerminationException{
		common.click(ADDITIIONALDETAILSTAB, "'Additional Details' tab on Appointments Page", 5, true);	
	}
	
	/**
	 * This function clicks the Canceled checkbox on the Additional Details tab
	 * @throws TCTerminationException
	 */
	public void clickCanceledCheckbox() throws TCTerminationException{
		common.click(CANCELEDCHECKBOX, "'Canceled' checkbox on Appointments Page", 5, true);	
	}
	
	/**
	 * This function selects the dropdown menu option for Cancellation Reason in the
	 * Addition details tab
	 * @param reason
	 * @throws TCTerminationException
	 */
	public void selectDropdownOption(String reason) throws TCTerminationException{
		String locatorStr = dropDownOptionString.replace(PLACEHOLDER, reason);
		By locator = By.xpath(locatorStr);
		
		common.click(CANCELREASONCODEDROPDOWN, "'Cancel Reason Code' dropdown", 5, true);
		common.click(locator, "Cancel Reason code option [" + reason + "]", 5, true);	
	}
	
	/**
	 * This function switches to the Appointments frame
	 * @throws TCTerminationException
	 */
	public void switchToAppointmentsFrame() throws TCTerminationException{
		driver.switchTo().defaultContent();
		By frameLocator = By.xpath("//*[contains(@src, '/appointment/ui/jsf/') or contains(@src, 'WebTenders.jsp')]");

		common.switchToFrame(frameLocator, "Appointments");

	}
}
