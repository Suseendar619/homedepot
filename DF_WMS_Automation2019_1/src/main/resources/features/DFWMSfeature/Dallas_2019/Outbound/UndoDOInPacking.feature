@DFWMS
Feature: UndoDOPacked
  
Scenario: OB Undo DO in InPacking state
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReadyDallas_UndoDO"
	Then Create DO xml "BVR_Dallas_UndoDO"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReadyDallas_UndoDO"
    Then Post PO xml "BVR_Dallas_UndoDO"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "UndoWaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BK1NonShipReadyDallas_UndoDO"
    Then Outbound Picking in Putty
    Then Validate status for LM "Distribution Orders" - "In Packing"
    Then Validate status for LM "oLPNs" - "Printed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "InPacking"
    Then Undo Wave for "BVR_UndoDO"
    Then Validate status for LM "Distribution Orders" - "Released"