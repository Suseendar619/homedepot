@TPE
Feature: User stories for TPE

Background:
Given Login as shipper

  Scenario: TPE Post Message send XML request and verify response
    Given TPE home page
    #When Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    Then Posting XML
    #Then Verify the response
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Open Purchase Order Details screen
    And Verify General tab
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Promote the shipment to Resource selection
    #And Shipment Status is "Assigned"
    #And Unassign Shipment 
    When Move shipment to the "Accepted" status  
    And Shipment Status is "Accepted"
    And Open Shipment Details
    And Validate Shipment Status in Shipment Details screen is "Accepted"
    And Select and Add Tracking Message for Stop "1" and Message Type "Depart."
    Then Validate Tracking Message for Message Type "Departure"
    And Validate Shipment Status in Shipment Details screen is "In Transit"
    Given Select and Add Tracking Message for Stop "2" and Message Type "Depart."
    When Validate Tracking Message for Message Type "Departure"
    Then Validate Shipment Status in Shipment Details screen is "Delivered"
    And Logout

  @Scenario2
  Scenario: TPE Post Message send XML request and verify response - Recall
    Given TPE home page
    #When Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #Then Verify the response
    Then Posting XML
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Open Purchase Order Details screen
    And Verify General tab
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"    
    And Promote the shipment to Resource selection
    #Then Shipment Status is "Assigned"
    #When Select the created shipment
    When Move shipment to the "Accepted" status  
    And Shipment Status is "Accepted"
    When Shipment is Recalled
    Then Shipment Status is "Available"
    And Cancel the shipments
    And Logout

  @Scenario3
  Scenario: TPE IB - Create Appointment(old)
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Select the created shipment
    And Unassign Shipment
    When Move shipment to the "Accepted" status  
    And Shipment Status is "Accepted"
    Then Create the appointment
    #Then Create appointment
    #And Close appointment window
    And Show appointment
    And Logout
    
  @Scenario3
  Scenario: TPE IB - Create Appointment
    Given TPE home page
    #When Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #Then Verify the response
    Then Posting XML
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Open Purchase Order Details screen
    And Verify General tab
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    When Promote the shipment to Resource selection
    #And Shipment Status is "Assigned"
    #And Unassign Shipment
    And Move shipment to the "Accepted" status  
    And Shipment Status is "Accepted"
    #And Shipment is Assigned to Carrier "UPSG"
    Then Create appointment
    And Show appointment
    ### Canceling the created Shipments 
    Then Close the shipment screen
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Cancel the shipments
    And Logout
    
  @Scenario4
  Scenario: Manual RTS creation by EDI753 
    ##When Open "Post Message" screen under "Technical" Module
    ##And Enter dynamically generated XML
    ##And Verify the response 
    Then Posting XML
    And Logout 
    Then Login as vendor 
    And Navigate to Vendor Portal 
    And Verify no RTS has been created for po 
    And Logout 
    And Login as shipper 
    #And Post EDI753 
    ### Posting EDI753 
    Then Posting EDI753 XML
    And Logout 
    And Login as vendor 
    And Navigate to Vendor Portal 
    And Verify that an RTS has been created for po 
    And Validate quantity, weight, and volume in RTS is the same as in XML
    And Logout
    ###And Check whether DO is created for RTS. If not created, we need to create it
    Then Login as shipper
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Create DO if not already created
    And Open Purchase Order Details screen
    And Verify DO tab
    ### And Create shipment using the DO
    And Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    And User accept workspace to create the shipment
    ### And Move Shipment to Accepted status
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    And Promote the shipment to Resource selection
    And Move shipment to the "Accepted" status
    And Shipment Status is "Accepted"
    ### Validate Transaction in Transactions screen
 	And Wait for transactions system to get updated
    Then Open "Transactions" screen under "Integration" Module
    And Search for shipment ID in Transactions screen
    And Validate Response in Transactions table
    And Logout
    ### Validate that shipment appears in Vendor Portal
    And Login as vendor
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
   	And Filter with Shipment "Shipment"
   	### Canceling the created Shipments 
   	And Logout
   	Then Login as shipper
   	Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Cancel the shipments
    
	@Scenario5
    Scenario: Accept Tender 
	### Shipment must be created and tendered to the appropriate carrier
    #Given Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #And Verify the response
    Then Posting XML
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    When Move shipment to "Tendered" status  
    #And Shipment is Tendered to Carrier
    And Logout 
    ### Carrier accepts tender request
    When Login as carrier 
    And Navigate to Carrier Portal
    And Open "Web Tenders" screen under "Logistics Gateway" Module
    And Filter for Shipment ID 
    And Carrier verifies the Carrier response is "No response"
    And Accept Tender
    Then Carrier verifies the Carrier response is "Accept" 
    And Logout 
    ### Shipper verifies that shipment has been accepted
    And Login as shipper 
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Select the created shipment 
    And Shipment Status is "Accepted"
    ### Canceling the created Shipments
    And Cancel the shipments

	@Scenario6					
    Scenario: Decline Tender 
	### Shipment must be created and tendered to the appropriate carrier
    #Given Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #And Verify the response
    Then Posting XML
    And Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Filter with purchase order "Purchase Order"
    And Open Purchase Order Details screen
    And Verify Shipment ID in DO tab
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    When Move shipment to "Tendered" status 
    #And Shipment is Tendered to Carrier
    And Logout
    ### Carrier declines tender request
    When Login as carrier 
    And Navigate to Carrier Portal
    And Open "Web Tenders" screen under "Logistics Gateway" Module
    And Filter for Shipment ID 
    And Carrier verifies the Carrier response is "No response"
    And Decline Tender 
    Then Carrier verifies the Carrier response is "Reject"
    And Logout 
    ### Shipper verifies that the shipment is in "Available" status
    And Login as shipper 
    And Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    And Select the created shipment 
    And Shipment Status is "Available"
    ##And Validate Shipment for Rejection  -- from prod changes
    ### Canceling the created Shipments
    And Cancel the shipments
    
     @Scenario7
  Scenario: TPE send XML request and Create RTS from Vendor Portal Login
    Given TPE home page
    #And Post dynamically generated XML through MQ 
    #When Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #Then Verify the response
    Then Posting XML
    And Logout 
    Then Login as vendor 
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vpS
    Then Apply and Verify generated purchase ordervpS
    And Verify RTS tabvp
    #When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    #When Filter with purchase order in vpS1
    Then Apply and select ready to shipvp
    Then RTS list save vp
    Then RTS Acknowledgement 
    And RTS Verifications

  @Scenario8  
  Scenario: Cons Engine Run
		Given TPE home page
		Then Posting Cons XML1
		Then Posting Cons XML2
		#When Open "Post Message" screen under "Technical" Module
		#And Enter dynamically generated Cons XML	
		#Then Verify the response1
		#And Reset the Post Message screen
		#And Enter dynamically generated Cons XML2
		#Then Verify the response
		Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
		When Filter with distribution orderEng
		Then Open "Run Templates" screen under "Transportation Lifecycle Management" Module
		Then Run template Filter data Preview
		Then Select the Filter and Preview the data
		Then Run the Engine		
		#Then Run Engine Status Validation
		Then Run Engine Status Validation for Cons
		And Run Summaries Validation 
    And Promote the shipment and Status Check
    ### Canceling the created Shipments
    #And Cancel the shipments
    And Cancel the shipments for ConsXD
	  
  @Scenario9 
  Scenario: XD Engine Run
		Given TPE home page
		Then Posting XD XML1
		Then Posting XD XML2
		#When Open "Post Message" screen under "Technical" Module
		#And Enter dynamically generated XD XML
		#Then Verify the response1
		#And Reset the Post Message screen
		#And Enter dynamically generated XD XML2
		#Then Verify the response
		Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
		When Filter with distribution orderEngXD
		Then Open "Run Templates" screen under "Transportation Lifecycle Management" Module
		Then Run template Filter data PreviewXD
		Then Select the Filter and Preview the data
		Then Run the Engine
		##Then Run Engine Status Validation
		Then Run Engine Status Validation for XD
		And Run Summaries Validation 
		And Promote the shipment and Status Check
		### Canceling the created Shipments
		#And Cancel the shipments
		And Cancel the shipments for ConsXD
	  
  @Scenario10 
  Scenario: Fleet Engine Run
		Given TPE home page
		Then Posting Fleet XML1
		Then Posting Fleet XML2
		#When Open "Post Message" screen under "Technical" Module
		#And Enter dynamically generated Fleet XML
		#Then Verify the response1
		#And Reset the Post Message screen
		#And Enter dynamically generated Fleet2 XML
		#Then Verify the response
		Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
		When Filter with distribution orderEngFleet
		Then Open "Run Templates" screen under "Transportation Lifecycle Management" Module
		Then Run template Filter data PreviewFleet
		Then Select the Filter and Preview the data
		Then Run the Engine
		Then Run Engine Status Validation
		And Run Summaries Validation 
	  ##And Promote the shipment and Status Check
	  And Promote the shipment and Status Check for Fleet
	  	### Canceling the created Shipments
	  	And Cancel the shipments
	
		  
	@Scenario11
  Scenario: TPE Post Message send XML request and verify response - Reject
    Given TPE home page
    Then Posting XML
    #And Enter dynamically generated XML
    #Then Verify the response
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Apply and Verify generated purchase order
    And Verify General tab
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order 
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    And Filter with Shipment "Shipment"
    #And Select the created shipment
    When Promote the shipment to Resource selection
    And Move shipment to the "Accepted" status
    Then Shipment Status is "Accepted"
    When Shipment is Rejected
    Then Validate Shipment for Rejection
    #And Open Shipment Details
    #And Clear Rejected Carrier
    ### Canceling the created Shipments
    And Cancel the shipments
    And Logout
    
    
    @Scenario12
    Scenario: EDI 214 - Tracking Message
    Given TPE home page
    Then Posting XML
   #When Open "Post Message" screen under "Technical" Module
   #And Enter dynamically generated XML
   #Then Verify the response
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Apply and Verify generated purchase order
    And Verify General tab
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    When Filter with Shipment "Shipment"
    #And Select the created shipment
    And Promote the shipment to Resource selection
    And Move shipment to the "Accepted" status
    Then Shipment Status is "Accepted"
    #Given Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated Tracking Msg XML2
    #And Verify the response
    #When Open "Shipments" screen under "Transportation Lifecycle Management" Module
    When Filter with Shipment "Shipment"
    And Select the created shipment
    And Open Shipment Details
    Then Validate Tracking Message for XML Message Type
    And Validate Shipment Status in Shipment Details screen is "Delivered"
    And Logout

	@Scenario12
    Scenario: Transactions Resend 
    Then Open "Transactions" screen under "Integration" Module
    And Search in Transactions screen
    
    
    @Scenario12
    Scenario: UserLSample 
    Given TPE home page
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    And Navigate to MDA 

    
 
    