@DFWMS
Feature: User stories for DF WMS
  
Scenario: LG DF WMS Hourly Standard Flow
#    Given Post dynamically generated XML "1" times order type "HDUESTS"
#    Given user connects to putty
#	Given Post dynamically generated XML "1" times order type "UPSPCL"
    Then Login as WMUser
    Then WMS home page
#    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard" and Run
    Then Select Rule "QA Test" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Not Printed"
    Then Open "Waves" screen under "Distribution" Module
    Then Open Wave Tasks
    Then Select and Release Wave Task
  ##  Then RFputty newframework
    #Then Open "RF Menu" screen under "Distribution" Module
    #Then Login as RFUser
#    Then Complete Picking Tasks
    #Then RF to WMS HomePage 
#    Then post the json service
    Then Post dynamically generated json "1" with "UPSSNGPCL"
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Open Wave Tasks
    Then Validate Task Status "Complete"
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
#    Then Validate DO Status "Packed"
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
   ## Then Validate oLPN Status "Consolidated"
    Then Validate oLPN Status "Not printed"
    Then Mobile Packing Station
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Packed"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Packed"
#    Then Open "Shipments" screen under "Distribution" Module
#    Then Shipment Creation
#   	Then Open "Appointments" screen under "Distribution" Module
#    Then Appointment Creation
#   	Then DF_OB_NP_CheckIn
#    Then Open "oLPNs" screen under "Distribution" Module
#    Then Input and Apply oLPNs
#    Then Validate oLPN Status "Packed"
#    Then Clear Array List

    And Logout
    
    
    Scenario: Login to Apllication
    
     Then Login as WMUser