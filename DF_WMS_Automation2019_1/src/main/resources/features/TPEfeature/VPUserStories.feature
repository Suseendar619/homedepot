 @TPE
Feature: User stories for VP

Background:
Given Login as shipper


    
@Scenario  
 Scenario: Manual Shipment creation in shipper and verify in VP 
    Given TPE home page
    Then Posting XML
    #When Open "Post Message" screen under "Technical" Module
    #And Enter dynamically generated XML
    #Then Verify the response
    Given Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order "Purchase Order"
    Then Apply and Verify generated purchase order
    And Verify RTS tab
    And Verify DO tab
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with distribution order "Distribution Order"
    And Apply and Verify Distribution Order
    And Select the order right click and Choose single to create a shipment for the order
    Then User accept workspace to create the shipment
    Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    When Filter with Shipment "Shipment"
  	And Logout
  	Then Login as vendor
  	And Navigate to Vendor Portal
  	Given Open "Shipments" screen under "Transportation Lifecycle Management" Module
    When Filter with Shipment "Shipment"
    
 @Scenario7
  Scenario: TPE send XML request and Create RTS from Vendor Portal Login with Date and RTS validations
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
	And Reset the Post Message screen
	And Enter dynamically generated XML2
    Then Verify the response
    And Logout 
    Then Login as vendor
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp
    Then Apply and Verify generated purchase ordervp
    And Verify RTS tabvp
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp1
    Then Apply and select ready to ship
    Then RTS list save
    Then RTS Acknowledgement 
    And RTS Verification
    
    @Scenario
  Scenario: Create manual RTS from VP Login with Date and RTS Quantity Changes
    Given TPE home page
    Then Posting XML
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
	And Reset the Post Message screen
	And Enter dynamically generated XML2
    Then Verify the response
    And Logout 
    Then Login as vendor
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp
    Then Apply and Verify generated purchase ordervp
    And Verify RTS tabvp
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp1
    Then Apply and select ready to ship
    Then RTS list Validations
    Then RTS Acknowledgement 
    And RTS Quantities Verification
    
       @Scenario
  Scenario: Create manual RTS from VP Login with RTS Capacity and Cubing Trailer Changes
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
	And Reset the Post Message screen
	And Enter dynamically generated XML2
    Then Verify the response
    And Logout 
    Then Login as vendor
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp
    Then Apply and Verify generated purchase ordervp
    And Verify RTS tabvp
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp1
    Then Apply and select ready to ship
    #Then RTS list Validations
    Then RTS capacity and cubing trailer changes
    Then RTS Acknowledgement 
    And RTS Capacity and Volume Verification
    
    @Scenario
  Scenario: Create manual RTS from VP Login with RTS Quantity Limit change
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
	And Reset the Post Message screen
	And Enter dynamically generated XML2
    Then Verify the response
    And Logout 
    Then Login as vendor
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp
    Then Apply and Verify generated purchase ordervp
    And Verify RTS tabvp
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp1
    Then Apply and select ready to ship
    Then RTS Quantity Limit changes
    
    @Scenario
  Scenario: Create manual RTS from VP Login with Two RTS Quantity Changes
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
	And Reset the Post Message screen
	And Enter dynamically generated XML2
    Then Verify the response
    And Logout 
    Then Login as vendor
    And Navigate to Vendor Portal 
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp
    Then Apply and Verify generated purchase ordervp
    And Verify RTS tabvp
    When Open "Purchase Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with purchase order in vp1
    Then Apply and select ready to ship
    Then RTS list Validations
    Then RTS Acknowledgement 
    Then Select ready to ship
    Then RTS Quantity Changes
    Then RTS Acknowledgement 
    And Two RTS Validations
 
 
	
	
	  Given DB Test