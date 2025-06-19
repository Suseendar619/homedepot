@DFWMS
Feature: UndoWaveMultiDO
  
Scenario: Outbound Undo Wave Multi DO Newark
	Given Login as WMUser
	Then Create DO xml "BVR_Baltimore_SplitShipment1"
	Then Create DO xml "BVR_Baltimore_SplitShipment2"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Baltimore_SplitShipment1"
	Then Post PO xml "BVR_Baltimore_SplitShipment2"
    Then Validate status for "Distribution Orders" - "Released"  
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "AUTOMATION - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Split_Shipment"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "UndoWaveDO"
    Then Undo Wave
    Then Validate status for "Distribution Orders" - "Released"