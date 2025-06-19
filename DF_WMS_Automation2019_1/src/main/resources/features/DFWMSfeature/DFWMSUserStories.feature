@DFWMS
Feature: User stories for DF WMS

Background:
#Given Login as WMUser

Scenario: DF WMS Login New Perris and Logout1
		Given Login as WMUser
    #And Post dynamically generated XML "1" times order type "OGND00390680"
    #And Post dynamically generated XML "1" times order type "OGND00392801"
    #And Post dynamically generated XML "1" times order type "OGND00390742"
    #And Post dynamically generated XML "1" times order type "OGND00392685"
    #And Post dynamically generated XML "1" times order type "OGND003"
    #And Post dynamically generated XML "1" times order type "1OGND"
    #And Post dynamically generated XML "1" times order type "1OGND003"
    #And Post dynamically generated XML "1" times order type "1OGND005"
    #And Post dynamically generated XML "1" times order type "1OGNDNewHub"
    #And Post dynamically generated XML "1" times order type "3UPSPCLTOTNC"
    #And Post dynamically generated XML "1" times order type "1OGNDNewZip"
    #And Post dynamically generated XML "1" times order type "1PAXVPOGND005"
    #And Post dynamically generated XML "1" times order type "1VPOGND005"
    And Post dynamically generated XML "1" times order type "1MCPVPOGND005"

  Scenario: DF WMS Login New Perris and Logout
  	Given Login as WMUser
    Given WMS home page
    #And Post dynamically generated XML "1" times order type "OGND003"
    #And Post dynamically generated XML "1" times order type "1OGND"
    #And Post dynamically generated XML "1" times order type "1OGND003"
    #And Post dynamically generated XML "1" times order type "1OGND005"
    And Post dynamically generated XML "1" times order type "1OGNDNewHub"
    #And Post dynamically generated XML "1" times order type "3UPSPCLTOTNC"
    #And Post dynamically generated XML "1" times order type "1OGNDNewZip"
    #And Post dynamically generated XML "1" times order type "1PAXVPOGND005"
    #And Post dynamically generated XML "1" times order type "1VPOGND005"
    #And Post dynamically generated XML "1" times order type "1MCPVPOGND005"
    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard" and Run
    Then Select Rule "Wave by order number" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    #Then Validate Wave Results Order "1" Units "1" Allocated "1" Shorted "0" Tasks "1" oLPNs "1" values
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
    Then Clear Array List    
    And Logout
    
   Scenario: DF WMS Login New Perris and Logout Test
  	Given Login as WMUser
    Given WMS home page
    Given Open "Distribution Orders" screen under "Distribution" Module
    Then Open "Run Waves" screen under "Distribution" Module
    Then Open "Waves" screen under "Distribution" Module
    Then Open "oLPNs" screen under "Distribution" Module
    And Logout
    
  Scenario: DF WMS Login LG and Logout
    #Given Login as WMUser
    Given WMS home page
    And Post dynamically generated XML "1" times order type "HDUESTS"
    #Then Verify the post response
    #Given Open "Distribution Orders" screen under "Distribution" Module
    #When Input and Apply DO
    #Then Validate DO Attributes
    #Then Open "Run Waves" screen under "Distribution" Module
    #Then Select Wave "Hourly Standard" and Run
    #Then Select Rule "QA Test" Define and Submit
    #Then Open "Waves" screen under "Distribution" Module
    #Then Input and Apply Wave
    #Then Validate Wave Attributes
    #Then Validate Wave Results Order "1" Units "1" Allocated "1" Shorted "0" Tasks "1" oLPNs "1" values
    #Then Open Wave Tasks
    #Then Release Wave Task
    #Then Open "RF Menu" screen under "Distribution" Module
    #Then Login as RFUser
    #Then Complete Picking Tasks
    #Then RF to WMS HomePage
    #Then Open "Run Waves" screen under "Distribution" Module
    #Then Select Wave "Pack Bypass Noncon" and Run
    #Then Select Rule "QA Test" Define and Submit
    #And Logout

Scenario: DF WMS Wave Run and Task Execution
    #Given Login as WMUser
    Given WMS home page
    And Post dynamically generated XML "1" times order type "1BVRC"
    #Then Verify the post response
    Given Open "Distribution Orders" screen under "Distribution" Module
    When Input and Apply DO
    Then Validate DO Attributes
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard" and Run
    Then Select Rule "QA Test" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Attributes
    Then Validate Wave Results Order "1" Units "1" Allocated "1" Shorted "0" Tasks "1" oLPNs "1" values
    Then Open Wave Tasks
    Then Release Wave Task
    Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Complete Picking Tasks
    Then RF to WMS HomePage
    #And Logout
    
 Scenario: Post DOR Orders Troy
    #Given Login as WMUser
    #Given WMS home page
    #And Post dynamically generated XML "1" times order type "1BVRC"
    And Post dynamically generated XML "15" times order type "1FGC"
    And Post dynamically generated XML "15" times order type "1FXC"
    #And Post dynamically generated XML "1" times order type "1HDULTLC"
    #And Post dynamically generated XML "1" times order type "1HDUTLC"
    #And Post dynamically generated XML "1" times order type "1LTLC"
    #And Post dynamically generated XML "1" times order type "1PCLC"
    #And Post dynamically generated XML "1" times order type "3FXPAX"
    #And Post dynamically generated XML "1" times order type "2BVRNC"
    And Post dynamically generated XML "10" times order type "2FGC"
    And Post dynamically generated XML "10" times order type "2FXC"
    #And Post dynamically generated XML "1" times order type "2HDULTLNC"
    #And Post dynamically generated XML "1" times order type "2HDUTLNC"
    #And Post dynamically generated XML "1" times order type "2LTLNC"
    #And Post dynamically generated XML "1" times order type "2PCLNC"
    #And Post dynamically generated XML "1" times order type "3FXNC"
    #And Post dynamically generated XML "1" times order type "3FGNC"
    #And Post dynamically generated XML "1" times order type "4FXNC"
    #And Post dynamically generated XML "1" times order type "4FGNC"
    #And Logout
    
Scenario: Troy DF WMS Wave Run and Task Execution
    Given Login as WMUser
    Given WMS home page
    And Post dynamically generated XML "1" times order type "1FGC"
    #Then Verify the post response
    Given Open "Distribution Orders" screen under "Distribution" Module
    When Input and Apply DO
    Then Validate DO Attributes
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Convey Pack Bypass" and Run
    Then Select Rule "Wave by order" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Attributes
    Then Validate Wave Results Order "1" Units "1" Allocated "1" Shorted "0" Tasks "1" oLPNs "1" values
    Then Open Wave Tasks
    Then Release Wave Task
    Then Open "RF Menu" screen under "Distribution" Module
    Then Login as RFUser
    Then Complete Picking Tasks
    Then RF to WMS HomePage
    #And Logout    
    
Scenario: DF QP User Creation
    Given Login as WMUser
    Given WMS home page
    Then Open "Users" screen under "Administration" Module
    Then QP User Creation
    And Logout
    
Scenario: WMS Login Logout
    Given Login as WMUser
    Given WMS home page
    Then Open "Waves" screen under "Distribution" Module
    And Logout