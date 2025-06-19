@DFWMS
Feature: UndoDOInPacking
  
Scenario: OB UndoDO In Packing state Tracey
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReadyTracey"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReadyTracey"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReadyTampa"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BK1NonShipReadyTracey"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "In Packing"
	#Then Validate status for "oLPNs" - "In Packing"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "BK1NonShipReadyTampa"
    Then Undo Wave for "BK1NonShipReadyTampa"