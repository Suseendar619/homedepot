@TPE
Feature: Regression Shipper User stories for TPE

Background:
Given Login as shipper
    
  @Scenario1
  Scenario: TP&E_MCW_14
    Given TPE home page
    When Open "Post Message" screen under "Technical" Module
    And Enter dynamically generated XML
    Then Verify the response1
		And Reset the Post Message screen
		And Enter dynamically generated XML2
    Then Verify the response
    Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    When Filter with multiple distribution orders
    And Select and Combine multiple distribution orders
    Then Validate Shipment for Errors
    And Validate DO details in Shipment
    And Logout
    
    
    @Scenario2
  	Scenario: TP&E_MCW_36
  		Given TPE home page
    	When Open "Post Message" screen under "Technical" Module
    	And Enter dynamically generated XML
    	Then Verify the response1
			And Reset the Post Message screen
			And Enter dynamically generated XML2
    	Then Verify the response
    	Given Open "Distribution Orders" screen under "Transportation Lifecycle Management" Module
    	And Filter with multiple distribution orders
    	And Select and Combine multiple distribution orders
    	And User accept workspace to create the shipment
    	When Open "Shipments" screen under "Transportation Lifecycle Management" Module
    	And Filter with Shipment "Shipment"
    	And Select the created shipment
    	And Open Shipment Details
    	Then "Record" Stops under Shipment Detail
    	Given Move Shipment to MCW via Manual Planning
    	When Change Delivery Sequence and Validate
    	And Confirm Delivery Sequence, Save and Accept
    	And Open Shipment Details
    	Then "Compare" Stops under Shipment Detail
    	And Logout
    	
    	
    	
    	
