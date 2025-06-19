@DFWMS
Feature: UPS Short LPN
  
Scenario: Outbound UPS Flow Short LPN Pack Cube Dir Newark
	Given Login as WMUser
	Then Create DO xml "UPS_Newark"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Newark" 
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Newark"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion 
    Then Validate status for "Distribution Orders" - "DC Allocated"   
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "UPS_Newark"
    Then Outbound PickToLabel in Putty for "ShortLPNBaltimore"
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Cancelled"
    Then Open "EPI Transaction Log" screen under "Distribution"
    Then Input and Apply WaveNumber for "UPS_Newark"  