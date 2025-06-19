@DFWMS
Feature: UndoDOPackedLacey
  
Scenario: OB Undo DO in Packed state Lacey
	Given Login as WMUser
	Then Create DO xml "LTLOBLacey_UndoDO1"
	Then Create DO xml "LTLHDUOBLacey_UndoDO2"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "LTLOBLacey_UndoDO1"
	Then Post PO xml "LTLHDUOBLacey_UndoDO2"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_UndoDO"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTLHDUOBLacey_UndoDO2"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "BVR_UndoDO"
    Then Undo Wave for "BVR_UndoDO"