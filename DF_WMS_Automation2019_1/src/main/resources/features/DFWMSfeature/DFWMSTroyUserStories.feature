@DFWMS
Feature: User stories for DF WMS

Background:
#Given Login as WMUser
    
Scenario: Post DOR Orders
    Given Login as WMUser
    Given WMS home page
    And Post dynamically generated XML "1" times order type "1FXC"
    And Post dynamically generated XML "1" times order type "1FGC"
    #And Post dynamically generated XML "1" times order type "2FXCMCP"
    #And Post dynamically generated XML "1" times order type "2FXCMCP2"
    #And Post dynamically generated XML "1" times order type "1BVRC"
    #And Post dynamically generated XML "1" times order type "1FGC"
    #And Post dynamically generated XML "1" times order type "1FXC"
    #And Post dynamically generated XML "1" times order type "1HDULTLC"
    #And Post dynamically generated XML "1" times order type "1HDUTLC"
    #And Post dynamically generated XML "1" times order type "1LTLC"
    #And Post dynamically generated XML "1" times order type "1PCLC"
    #And Post dynamically generated XML "1" times order type "3FXPAX"
    #And Post dynamically generated XML "1" times order type "2BVRNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2FGC"
    #And Post dynamically generated XML "1" times order type "2FXC"
    #And Post dynamically generated XML "1" times order type "2HDULTLNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2HDUTLNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2LTLNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2PCLNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "3FXNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "3FGNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "4FXNC"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "4FGNC"
    #And Post dynamically generated XML "1" times order type "3LTLNCMLT1"
    #And Post dynamically generated XML "1" times order type "3LTLNCMLT2"
    #And Post dynamically generated XML "1" times order type "4LTLCMLC1"
    #And Post dynamically generated XML "1" times order type "4LTLCMLC2"
    #Then Verify the post response
    #And Logout
    
Scenario: Troy DF WMS Convey Pack Bypass Flow
    Given Post dynamically generated XML "1" times order type "1FGC"
    Then Login as WMUser
    Then WMS home page
    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    #Then Validate DO Route To "18"
    #Then Validate DO Route Type 1 "5034"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000VA001"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000IL001"
    #Then Validate DO Route To "FG"
    #Then Validate DO Route Type 1 "6707_COM"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Pack Bypass Conveyable" and Run
    Then Select Rule "Wave by order" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    #Then Validate Wave Results Order "1" Units "1" Allocated "1" Shorted "0" Tasks "1" oLPNs "1" values
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Printed"
    Then Open "Waves" screen under "Distribution" Module
    Then Open Wave Tasks
    Then Get Task Details
    Then Validate Task Status "Released"
    Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Complete Picking Tasks
    Then RF to WMS HomePage
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Open Wave Tasks
    Then Validate Task Status "Complete"
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    #Then Validate DO Status "Weighed"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    #Then Validate oLPN Status "Packed"
    #Then Clear Array List
    And Logout
    
Scenario: Troy DF WMS Non Convey Pack Bypass Flow
    Given Post dynamically generated XML "1" times order type "3FXNC"
    Then Login as WMUser
    Then WMS home page 
    Then Verify the post response   
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    #Then Validate DO Status "Released"
    #Then Validate DO Route To "18"
    #Then Validate DO Route Type 1 "5089"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000PA006"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000FL004"
    #Then Validate DO Route To "FG"
    #Then Validate DO Route Type 1 "6707_COM"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Pack Bypass Noncon" and Run
    Then Select Rule "Test with Order ID" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    #Then Validate Wave Results Order "1" Units "5" Allocated "5" Shorted "0" Tasks "5" oLPNs "5" values
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Printed"
    Then Open "Waves" screen under "Distribution" Module
    Then Open Wave Tasks
    Then Get Task Details
    Then Validate Task Status "Released"
    Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Complete Picking Tasks
    Then RF to WMS HomePage
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Open Wave Tasks
    Then Validate Task Status "Complete"
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Weighed"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Packed"
    #Then Clear Array List
    And Logout   
    
Scenario: Troy DF WMS Hourly Standard Flow
##  Given Post dynamically generated XML "1" times order type "3FXNC"
	Given Post dynamically generated XML "1" times order type "2PCLNCMNP"
##    Given Post dynamically generated XML "1" times order type "4FXNC"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT1"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT2"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT3"
    #Given Post dynamically generated XML "1" times order type "4LTLCMLC1"
    #Given Post dynamically generated XML "1" times order type "4LTLCMLC2"
    ##Given Post dynamically generated XML "1" times order type "4LTLCMLC3"
    ##Given Post dynamically generated XML "1" times order type "2HDUTLNC"
    ##Given Post dynamically generated XML "1" times order type "2HDUTLNC"
    ##Given Post dynamically generated XML "1" times order type "1BVRC"
    ##Given Post dynamically generated XML "1" times order type "2LTLNC"
    Then Login as WMUser
    Then WMS home page
    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard 1" and Run
    Then Select Rule "Wave by Order QA" Define and Submit
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
    Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Complete Picking Tasks
    Then RF to WMS HomePage
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Open Wave Tasks
    Then Validate Task Status "Complete"
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Packed"
    ##Then Validate DO Status "DC Allocated"
  	Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Consolidated"
    ##Then Validate oLPN Status "Not printed"
    Then Mobile Packing Station
    ##Then Suspend oLPN and Hospital Packing
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Packed"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Packed"
    Then Open "Shipments" screen under "Distribution" Module
    Then Shipment Creation
   	Then Open "Appointments" screen under "Distribution" Module
    Then Appointment Creation
   	Then DF_OB_NP_CheckIn
   	Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
   	Then Load Trailer
   	Then RF to WMS HomePage
##   	Then Open "Distribution Orders" screen under "Distribution" Module
##    Then Input and Apply DO
##    Then Validate DO Status "Loaded"
##    Then Open "oLPNs" screen under "Distribution" Module
##    Then Input and Apply oLPNs
##    Then Validate oLPN Status "Loaded on truck"
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Manifested"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Manifested"
    Then Open "Ship Confirm Rules" screen under "Distribution" Module
    Then Ship Confirm Rules
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Shipped"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Shipped"
    #Then Clear Array List
    And Logout
    
Scenario: Perris DF WMS Hourly Standard Flow
    #Given Post dynamically generated XML "1" times order type "3FXNC"
    #Given Post dynamically generated XML "1" times order type "4FXNC"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT1"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT2"
    #Given Post dynamically generated XML "1" times order type "3LTLNCMLT3"
    #Given Post dynamically generated XML "1" times order type "4LTLCMLC1"
    #Given Post dynamically generated XML "1" times order type "4LTLCMLC2"
    ##Given Post dynamically generated XML "1" times order type "4LTLCMLC3"
    ##Given Post dynamically generated XML "1" times order type "2HDUTLNC"
    ##Given Post dynamically generated XML "1" times order type "2HDUTLNC"
 ##   Given Post dynamically generated XML "1" times order type "2LTLNC"
    Then Login as WMUser
    Then WMS home page
 ##   Then Verify the post response
 ##   Then Open "Distribution Orders" screen under "Distribution" Module
 ##   Then Input and Apply DO
 ##   Then Validate DO Status "Released"
    #Then Validate DO Route To "18"
    #Then Validate DO Route Type 1 "5034"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000VA001"
    #Then Validate DO Route To "3"
    #Then Validate DO Route Type 1 "60000IL001"
    #Then Validate DO Route To "FX"
    #Then Validate DO Route Type 1 "6707_COM"
 ##   Then Open "Run Waves" screen under "Distribution" Module
 ##   Then Select Wave "Hourly Standard" and Run
 ##   Then Select Rule "Wave by order number" Define and Submit
 ##   Then Open "Waves" screen under "Distribution" Module
 ##   Then Input and Apply Wave
 ##   Then Validate Wave Completion
 ##   Then Open "Distribution Orders" screen under "Distribution" Module
 ##   Then Validate DO Status "DC Allocated"
 ##   Then Open "oLPNs" screen under "Distribution" Module
 ##   Then Input and Apply oLPNs
 ##   Then Validate oLPN Status "Not Printed"
 ##   Then Open "Waves" screen under "Distribution" Module
 ##   Then Open Wave Tasks
 ##   Then Select and Release Wave Task
 ##   Then Open "RF Menu" screen under "Distribution" Module
 ##   Then Login as RFUser
 ##   Then Complete Picking Tasks
 ##   Then RF to WMS HomePage
 ##   Then Open "Waves" screen under "Distribution" Module
 ##   Then Input and Apply Wave
 ##   Then Open Wave Tasks
 ##   Then Validate Task Status "Complete"
 ##   Then Open "Distribution Orders" screen under "Distribution" Module
 ##   Then Input and Apply DO
 ##   Then Validate DO Status "Packed"
 ##   ##Then Validate DO Status "DC Allocated"
 ## 	Then Open "oLPNs" screen under "Distribution" Module
 ##   Then Input and Apply oLPNs
 ##   Then Validate oLPN Status "Consolidated"
 ##   ##Then Validate oLPN Status "Not printed"
 ##   Then Mobile Packing Station
 ##   ##Then Suspend oLPN and Hospital Packing
 ##   Then Open "Distribution Orders" screen under "Distribution" Module
 ##   Then Input and Apply DO
 ##   Then Validate DO Status "Packed"
 ##   Then Open "oLPNs" screen under "Distribution" Module
 ##   Then Input and Apply oLPNs
 ##   Then Validate oLPN Status "Packed"
 ##   Then Open "Shipments" screen under "Distribution" Module
 ##   Then Shipment Creation
 ##  	Then Open "Appointments" screen under "Distribution" Module
 ##   Then Appointment Creation
   	Then DF_OB_NP_CheckIn
 ##  	Then Open "RF Menu" screen under "Distribution" Module
 ##   Then Login as RFUser
 ##  	Then Load Trailer
 ##  	Then RF to WMS HomePage
    #Then Clear Array List
    And Logout    
    
    
Scenario: Troy DF WMS Test
    Given Post dynamically generated XML "1" times order type "2FXCMCP2"
    Then Login as WMUser
    Then WMS home page
    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard 1" and Run
    Then Select Rule "Wave by Order QA" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Not Printed"
    Then Clear Array List
    And Logout 
    
    Scenario: Troy DF WMS Inbound ASN
    Given Login as WMUser
    Then Open "Post Message" screen under "Integration" Module
    Then PO POST "\\Troy\\Multi_Item_qty_PO.xml"
    Then Open "Purchase Orders" screen under "Distribution" Module
    Then Input and Apply PO
    Then Validate PO Status "Created"
  ##  Then Validate PO status "Created" 
  	Then Open "Create ASN from PO" screen under "Distribution" Module
    Then ASN PO mapping
    Then Open "ASNs" screen under "Distribution" Module
    Then Create iLPNs
    Then Open "ASNs" screen under "Distribution" Module
    Then Input and Apply ASN
    Then Validate ASN Status "InTransit"
    Then Open "iLPNs" screen under "Distribution" Module
    Then Validate LPNLCKQA
    Then Validate iLPN
    Then Open "Appointments" screen under "Distribution" Module
    Then IB Appointment Creation
   	Then DF_IB_NP_CheckIn
   	Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Receive Detail
    Then RF to WMS HomePage
	  Then Open "ASNs" screen under "Distribution" Module
    Then Input and Apply ASN
    Then Validate ASN Status "Receiving Verified"
    Then Open "Create ASN from PO" screen under "Distribution" Module
    Then Validate Open PO QTY
    Then Validate PO details
    Then Open "PIX Transactions" screen under "Distribution" Module
    Then Validate Pix Transactions
    And Logout 