package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;
import com.homer.po.DFWMS.TPELoginPageObject.UserTypes;
import com.homer.po.DFWMS.TPEWebTendersPageObject.StatusTypes;

public class TPEAppointmentsPageStepDeftn extends BaseStepDefn {

	public TPEAppointmentsPageStepDeftn(DataClass data) {
		super(data);
		common = new Common(ic);
	}

	private String shipmentId;
	private Common common;
	
	// Used for validating data against both the shipper and carrier
	// appointment pages
	public static TPEAppointmentsPageObject shipperApptPage;
	public TPEAppointmentsPageObject carrierApptPage;
	
	private static String appointmentId;
	
	@And("^Filter for Shipment ID$")
	public void filter_for_Shipment_ID() throws Throwable {  
	  
		// Find Shipment number using filter
		//		Click "Filter List"
				
		tpewebtenderspageobject.clickFilterListLink();
		
		//		Click "New Filter"
		
		tpewebtenderspageobject.clickNewFilter();
		
		//		Fill in Shipment ID
		shipmentId = TPEShipmentsPageStepDeftn.getShipmentId();
		tpewebtenderspageobject.enterShipmentIDInFilterForm(shipmentId);
		
		//		Click "Apply"
		//		Wait for screen to disappear
		tpewebtenderspageobject.clickApplyButtonForNewFilter();
		tpewebtenderspageobject.switchToWebTendersFrame();

	}
	
	@And("^Carrier verifies the Carrier response is \"(.*?)\"$")
	public void carrier_verifies_the_Shipment_hasnt_been_accepted(String expected) throws Throwable { 
		
		tpewebtenderspageobject.switchToWebTendersFrame();
		String statusType = StatusTypes.CARRIERRESPONSE.getValue();
		String actual = tpewebtenderspageobject.getCarrierReply(shipmentId);
		
		common.compareStrings(expected, actual, statusType, true);

	}
	
	@And("^Carrier verifies the Tender Status is \"(.*?)\"$")
	public void carrier_verifies_the_tender_status(String expected) throws Throwable { 
		
		tpewebtenderspageobject.switchToWebTendersFrame();
		String statusType = StatusTypes.TENDERSTATUS.getValue();
		String actual = tpewebtenderspageobject.getTenderStatus(shipmentId);
		
		common.compareStrings(expected, actual, statusType, true);

	}
	
	@And("^Accept Tender$")
	public void accept_Tender() throws Throwable { 
		
		// Click checkbox to select for shipment ID
		
		tpewebtenderspageobject.clickCheckboxForShipmentId(shipmentId);
		
		// Click accept button
		
		tpewebtenderspageobject.clickAcceptButton();
	}
	
	@And("^Decline Tender$")
	public void decline_tender() throws Throwable { 
		
		// Click checkbox for shipment ID
		
		tpewebtenderspageobject.clickCheckboxForShipmentId(shipmentId);
		
		// Click accept button
		
		tpewebtenderspageobject.clickDeclineButton();
	}
	
	@And("^Set appointment date$")
	public void set_appointment_date() throws Throwable { 
	 
		// Click set button
		tpewebtenderspageobject.clickSetLink(shipmentId);
	
		// Wait for dialog to appear
		tpeappointmentspageobject.waitForRecommendationsDialog();
		
		// Click 'Select' to select date
		tpeappointmentspageobject.clickSelectButton_RecommendationsDialog();		
		
	}
	
	@And("^Validate appointment$")
	public void validate_appointment() throws Throwable { 
		
		// Click Validate button
		tpeappointmentspageobject.clickValidateButton_AddAppointment();
		
		// Validate 'Success'
		tpeappointmentspageobject.validateSuccess();
		
		// Close window
		tpeappointmentspageobject.closeMessagesDialog();
		
	}
	
	@And("^Save Appointment$")
	public void save_Appointment() throws Throwable { 

		// Click Save Button
		tpeappointmentspageobject.clickSaveButton_AddAppointment();
		
		// Save all details to page and for use in other test cases.
		// If logged in as carrier, save to carrier page. If logged in as 
		// shipper, save to shipper page.
		tpeappointmentspageobject.saveAllAppointmentDetails();
		appointmentId = tpeappointmentspageobject.getSavedValueAppointmentId();
		if (TPELoginPageObject.loggedInAs.equals(UserTypes.CARRIER)){
			carrierApptPage = tpeappointmentspageobject;
		}else if(TPELoginPageObject.loggedInAs.equals(UserTypes.SHIPPER)){
			shipperApptPage = tpeappointmentspageobject;
		}
	}
	
	@And("^Validate Appointment Status is \"(.*?)\"$")
	public void validate_Appointment_Status_is_arg1(String expected) throws Throwable { 

		// Get actual appointment status
		String actual = tpeappointmentspageobject.getAppointmentStatus(appointmentId);
		
		// Compare with expected status
		common.compareStrings(expected, actual, "Appointment Status", true);
	}

	@And("^Search for Appointment ID$")
	public void search_for_Appointment_ID() throws Throwable { 

		// Search for appointment
		tpeappointmentspageobject.searchForAppointment(appointmentId);
		
	}
	
	/**
	 * This function validates values from 2 different pages
	 * @throws Throwable
	 */
	@And("^Validate appointment details$")
	public void validate_appointment_details() throws Throwable { 
		
		// If on carrier screen, get all values from carrier screen, and match against
		// shipper screen. Else, if on shipper screen, do the reverse.
		TPEAppointmentsPageObject currentPage = getCurrentPage();
		TPEAppointmentsPageObject originalPage = getOriginalPage();
		
		String currentApptId = currentPage.getAppointmentId();
		common.compareStrings(appointmentId, currentApptId, "Appointment ID", false);
		
		String currentFacility = currentPage.getFacility();
		common.compareStrings(originalPage.getSavedValueFacility(), currentFacility, "Facility", false);
		
		String currentFacilityName = currentPage.getFacilityName();
		common.compareStrings(originalPage.getSavedValueFacilityName(), currentFacilityName, "Facility Name", false);

		String currentStartDate = currentPage.getSuggestedStartDate();
		common.compareStrings(originalPage.getSavedValueSuggestedStartDate(), currentStartDate, "Suggested Start Date/Time", false);
		
		String currentAppointmentType = currentPage.getAppointmentType();
		common.compareStrings(originalPage.getSavedValueAppointmentType(), currentAppointmentType, "Appointment Type", false);
		
		String currentEquipmentCode = currentPage.getEquipmentCode();
		common.compareStrings(originalPage.getSavedValueEquipmentCode(), currentEquipmentCode, "Shipper Equipment Code", false);
		
		String currentLoadConfiguration = currentPage.getLoadConfiguration();
		common.compareStrings(originalPage.getSavedValueLoadConfiguration(), currentLoadConfiguration, "Load Configuration", false);
		
		String currentEstTrailerDuration = currentPage.getEstTrailerDuration();
		common.compareStrings(originalPage.getSavedValueEstTrailerDuration(), currentEstTrailerDuration, "Est Trailer Duration", false);
		
		String currentEstTractorDuration = currentPage.getEstTractorDuration();
		common.compareStrings(originalPage.getSavedValueEstTractorDuration(), currentEstTractorDuration, "Est Tractor Duration", false);
		
		String currentEstDepartureDate = currentPage.getEstDepartureDate();
		common.compareStrings(originalPage.getSavedValueEstDepartureDate(), currentEstDepartureDate, "Departure Date", false);
		
		String currentAppointmentRequestedDate = currentPage.getAppointmentRequestedDate();
		common.compareStrings(originalPage.getSavedValueAppointmentRequestedDate(), currentAppointmentRequestedDate, "Appointment Requested Date", false);
		
		String currentActualCheckInDttm = currentPage.getActualCheckInDttm();
		common.compareStrings(originalPage.getSavedValueActualCheckInDttm(), currentActualCheckInDttm, "Actual Check In Dttm", false);
		
	}
	
	@And("^Approve appointment$")
	public void approve_appointment() throws Throwable { 

		// Select appointment row
		tpeappointmentspageobject.selectCheckbox(appointmentId);
		
		// Click "Approve" button
		tpeappointmentspageobject.clickApproveButton();
		
	}
	
	@And("^Change the recommended start date to recommendation \"(.*?)\"$")
	public void change_the_recommended_start_date(String slotNumber) throws Throwable { 
	
		// Select row
		tpeappointmentspageobject.selectCheckbox(appointmentId);
		
		// Click 'Edit' button
		tpeappointmentspageobject.clickEditButton();
		
		// Click 'Recommended Time Slots'
		tpeappointmentspageobject.clickRecommendTimeSlotsButton();
		
		// Click third time slot
		tpeappointmentspageobject.selectAppointmentSlot(Integer.parseInt(slotNumber), true);
		
		// Click 'Select'
		tpeappointmentspageobject.clickSelectButton_RecommendationsDialog();

	}

	@And("^Validate the suggested start date equal to selected date$")
	public void validate_the_suggested_start_date_equal_to_selected_date() throws Throwable { 

		// Get the value that we selected
		String expected = tpeappointmentspageobject.getSelectedStartTime();
		
		// Get actual value from the screen
		String actual = tpeappointmentspageobject.getSuggestedStartDateValue_EditScreen();
		
		// Compare values
		common.compareStrings(expected, actual, "Suggested Start Date/Time", false);
	}
	
	@And("^Validate the estimated departure date equal to selected date$")
	public void validate_the_estimated_departure_date_equal_to_selected_date() throws Throwable { 

		// Get the value that we selected
		String expected = tpeappointmentspageobject.getSelectedDepartureTime();
				
		// Get actual value from the screen
		String actual = tpeappointmentspageobject.getEstDepartureDateValue_EditScreen();
				
		// Compare values
		common.compareStrings(expected, actual, "Estimated Departure Date/Time", false);
	}
	
	@And("^Cancel appointment with code \"(.*?)\"$")
	public void cancel_appointment(String reasonCode) throws Throwable { 

		// Select row
		tpeappointmentspageobject.selectCheckbox(appointmentId);
				
		// Click 'Edit' button
		tpeappointmentspageobject.clickEditButton();
		
		// Click 'Additional Details' tab
		tpeappointmentspageobject.clickAdditionalDetailsTab();
		
		// Click checkbox near 'Canceled'
		tpeappointmentspageobject.clickCanceledCheckbox();
		
		// Click dropdown list and mark Carrier code option
		tpeappointmentspageobject.selectDropdownOption(reasonCode);
	}
	
	/**
	 * This function checks both the shipper and carrier appointment page to ensure that they
	 * use the same appointment requested date.
	 * @throws Throwable
	 */
	@And("^Validate the appointment requested date is correct$")
	public void validate_the_appointment_requested_date_is_correct() throws Throwable { 
		// If on carrier screen, get all values from carrier screen, and match against
		// shipper screen. Else, if on shipper screen, do the reverse.
		TPEAppointmentsPageObject originalPage = getOriginalPage();

		// Get the value original
		String expected = originalPage.getSavedValueAppointmentRequestedDate();
		
		// Get actual value from the screen
		String actual = tpeappointmentspageobject.getAppointmentRequestedDateValue_EditScreen();
		
		// Compare values
		common.compareStrings(expected, actual, "Appointment Requested Date/Time", false);
	}
	
	public static void setAppointmentId(String id){
		appointmentId = id;
	}
	
	public static void setShipperAppointmentPage(TPEAppointmentsPageObject page){
		shipperApptPage = page;
	}
	
	/**
	 * This function gets the current page (for data validations). If logged in as carrier, this function returns the carrier
	 * appointment page. If logged in as shipper, this function returns the shipper appointment page
	 * @return
	 * @throws Exception 
	 */
	public TPEAppointmentsPageObject getCurrentPage() throws TCTerminationException{
		
		TPEAppointmentsPageObject currentPage = new TPEAppointmentsPageObject(ic);
		if (TPELoginPageObject.loggedInAs.equals(UserTypes.CARRIER)){
			if (carrierApptPage == null){
				carrierApptPage = new TPEAppointmentsPageObject(ic);
			}
			currentPage = carrierApptPage;
		}else if(TPELoginPageObject.loggedInAs.equals(UserTypes.SHIPPER)){
			if (shipperApptPage == null){
				shipperApptPage = new TPEAppointmentsPageObject(ic);
			}
			currentPage = shipperApptPage;
			
			
		}else{
			report.addReportStep("Get appointments page", 
					"No appointments page for type: " + TPELoginPageObject.loggedInAs.getValue(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		return currentPage;
	}
	
	/**
	 * This function gets the original page (for data validations). If logged in as carrier, this 
	 * function returns the shipper appointment page. If logged in as shipper, this function returns 
	 * the carrier appointment page
	 * @return
	 * @throws Exception 
	 */
	public TPEAppointmentsPageObject getOriginalPage(){
		TPEAppointmentsPageObject originalPage = new TPEAppointmentsPageObject(ic);
		if (TPELoginPageObject.loggedInAs.equals(UserTypes.CARRIER)){
			originalPage = shipperApptPage;
		}else if(TPELoginPageObject.loggedInAs.equals(UserTypes.SHIPPER)){
			originalPage = carrierApptPage;
		}
		return originalPage;
	}
}
