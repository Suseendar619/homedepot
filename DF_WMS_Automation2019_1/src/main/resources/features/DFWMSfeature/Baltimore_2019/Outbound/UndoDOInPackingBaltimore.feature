@DFWMS
Feature: UndoDOInPackingBaltimore
  
Scenario: OB Undo DO in InPacking state Baltimore
	Given Login as WMUser
	Then Create DO xml "BK1NSRBaltimore_UndoDO_IP"
	Then Create DO xml "BVR_Baltimore_UndoDO"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NSRBaltimore_UndoDO_IP"
	Then Post PO xml "BVR_Baltimore_UndoDO"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_UndoDO"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Baltimore_UndoDO"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Packed"
	Then Validate status for "oLPNs" - "Packed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "UndoWaveDO"
    Then Undo Wave for "BK1NSRBaltimore_UndoDO_IP"