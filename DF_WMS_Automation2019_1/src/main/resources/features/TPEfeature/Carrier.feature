@TPE
Feature: User stories for Carrier Portal

Background:
Given Login as shipper

  Scenario: Schedule Appointment as Carrier, and Approve as Shipper
 	### Shipment must be created and tendered to the appropriate carrier
    Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    And Verify the response
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Shipment is Tendered to Carrier
 	And Logout 
  	### Set appointment date on carrier side
  	When Login as carrier 
  	And Navigate to Carrier Portal
    And Open "Web Tenders" screen under "Logistics Gateway" Module
    And Filter for Shipment ID 
    And Carrier verifies the Carrier response is "No response"
    And Carrier verifies the Tender Status is "New Request"
    And Accept Tender
    And Carrier verifies the Carrier response is "Accept"
    And Set appointment date
	And Validate appointment
    And Save Appointment
    And Validate Appointment Status is "Requested"
    And Logout
    ### Approve appointment and verify it has been scheduled on shipper side
    And Login as shipper
    And Open "Appointments" screen under "Yard Management" Module
    And Search for Appointment ID
    And Validate appointment details
    And Approve appointment
    And Validate Appointment Status is "Scheduled"
    And Logout
    ### Verify appointment is scheduled on carrier side
    And Login as carrier
    And Navigate to Carrier Portal
    And Open "Appointments" screen under "Logistics Gateway" Module
    And Search for Appointment ID
    And Validate Appointment Status is "Scheduled"
    
  Scenario: Schedule Appointment as Carrier, and Counter as Shipper
    ### Shipment must be created and tendered to the appropriate carrier
    Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    And Verify the response
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Shipment is Tendered to Carrier
 	And Logout 
  	### Set appointment on carrier side
  	Given Login as carrier 
  	And Navigate to Carrier Portal
    And Open "Web Tenders" screen under "Logistics Gateway" Module
    And Filter for Shipment ID 
    And Carrier verifies the Tender Status is "New Request"
    And Accept Tender
    And Set appointment date
	And Validate appointment
    And Save Appointment
    And Validate Appointment Status is "Requested"
    And Logout
    ### When I update the start date and time as a shipper
    When Login as shipper
    And Open "Appointments" screen under "Yard Management" Module
    And Search for Appointment ID
    And Validate appointment details
    And Change the recommended start date to recommendation "3"
    And Validate the suggested start date equal to selected date
    And Validate the estimated departure date equal to selected date
    #And Validate the appointment requested date is correct
    And Validate appointment
    And Save Appointment
    ### Then validate that the appointment status is countered
    ### on both the shipper and carrier side
    Then Validate Appointment Status is "Countered"
    And Logout
    And Login as carrier
    And Navigate to Carrier Portal
    And Open "Appointments" screen under "Logistics Gateway" Module
    And Search for Appointment ID
    And Validate Appointment Status is "Countered"
    ### Approve on carrier side, and validate that status is
    ### "Scheduled" on both the carrier and shipper side
    And Approve appointment
    And Validate Appointment Status is "Scheduled"
    And Logout
    And Login as shipper
    And Open "Appointments" screen under "Yard Management" Module
    And Search for Appointment ID
    And Validate Appointment Status is "Scheduled" 
    
    Scenario: Schedule Appointment as Shipper, and Approve as Carrier
    ### Shipment must be created and tendered to the appropriate carrier
    Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    And Verify the response
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Shipment is Tendered to Carrier
 	### Set appointment on shipper side. 
 	And Create appointment
	And Validate Appointment Status is "Scheduled"
 	And Logout
 	### Check for 'Scheduled' status on carrier side. Make changes to
 	### the appointment date on carrier side
 	And Login as carrier
 	And Navigate to Carrier Portal
 	And Open "Appointments" screen under "Logistics Gateway" Module
 	And Search for Appointment ID
 	And Validate Appointment Status is "Scheduled"
 	And Validate appointment details
 	And Change the recommended start date to recommendation "3"
 	And Validate the suggested start date equal to selected date
    And Validate the estimated departure date equal to selected date
    #And Validate the appointment requested date is correct
 	And Validate appointment
 	And Save Appointment
 	And Validate Appointment Status is "Requested"
    And Logout
    And Login as shipper
    And Open "Appointments" screen under "Yard Management" Module
    And Search for Appointment ID
    And Approve appointment
 	And Validate Appointment Status is "Scheduled"
 	And Logout
 	### Login as carrier and validate the appointment is in 'Scheduled' status
 	And Login as carrier
 	And Navigate to Carrier Portal
 	And Open "Appointments" screen under "Logistics Gateway" Module
    And Search for Appointment ID
    And Validate Appointment Status is "Scheduled"
    
    
    Scenario: Schedule Appointment as Shipper, and Cancel as Carrier
    ### Shipment must be created and tendered to the appropriate carrier
    Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    And Verify the response
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Shipment is Tendered to Carrier
 	### Set appointment on shipper side. 
 	And Create appointment
	And Validate Appointment Status is "Scheduled"
 	And Logout
 	### Check for 'Scheduled' status on carrier side. Cancel on carrier side
 	And Login as carrier
 	And Navigate to Carrier Portal
 	And Open "Appointments" screen under "Logistics Gateway" Module
 	And Search for Appointment ID
 	And Validate Appointment Status is "Scheduled"
 	And Validate appointment details
 	And Cancel appointment with code "Carrier Error"
 	And Save Appointment
 	And Validate Appointment Status is "Canceled"
    And Logout
    And Login as shipper
    And Open "Appointments" screen under "Yard Management" Module
    And Search for Appointment ID
 	And Validate Appointment Status is "Canceled"
 	
 	Scenario: Schedule Appointment as Carrier, and Cancel as Shipper
 	### Shipment must be created and tendered to the appropriate carrier
    Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    And Verify the response
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Shipment is Tendered to Carrier
 	And Logout 
  	### Set appointment on carrier side
  	Given Login as carrier 
  	And Navigate to Carrier Portal
    And Open "Web Tenders" screen under "Logistics Gateway" Module
    And Filter for Shipment ID 
    And Carrier verifies the Tender Status is "New Request"
    And Accept Tender
    And Set appointment date
	And Validate appointment
    And Save Appointment
    And Validate Appointment Status is "Requested"
    And Logout
    ### Cancel Appointment on Shipper side
    And Login as shipper
 	And Open "Appointments" screen under "Yard Management" Module
 	And Search for Appointment ID
 	And Validate Appointment Status is "Requested"
 	And Validate appointment details
 	And Cancel appointment with code "Planner Error"
 	And Save Appointment
 	And Validate Appointment Status is "Canceled"
    And Logout
    And Login as carrier
    And Navigate to Carrier Portal
    And Open "Appointments" screen under "Logistics Gateway" Module
    And Search for Appointment ID
 	And Validate Appointment Status is "Canceled"
 	
 	