@DFWMS
Feature: UPS Skip LPN
  
Scenario: Outbound UPS Flow Skip LPN Pack Cube Dir Baltimore
	Given Login as WMUser
	Then Create DO xml "UPS_Baltimore_MISP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Baltimore_MISP" 
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Baltimore_MISP"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "UPS_Baltimore_MISP"
    Then Outbound PickToLabel in Putty for "SkipLPNBaltimore"
    Then Validate status for "Distribution Orders" - "In Packing"
    Then Validate status for "oLPNs" - "In Packing"
    Then Open "EPI Transaction Log" screen under "Distribution"
    Then Input and Apply WaveNumber for "UPS_Dallas"     