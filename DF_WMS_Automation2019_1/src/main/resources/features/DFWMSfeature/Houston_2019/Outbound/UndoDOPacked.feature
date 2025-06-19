@DFWMS
Feature: UndoDOPacked
  
Scenario: OB UndoDO in Packed State Houston
	Given Login as WMUser
	Then Create DO xml "BVR_Houston"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Houston"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_UndoDO"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Houston"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "BVR_Houston"
    Then Undo Wave for "BVR_UndoDO"
    Then Validate status for "Distribution Orders" - "Released"