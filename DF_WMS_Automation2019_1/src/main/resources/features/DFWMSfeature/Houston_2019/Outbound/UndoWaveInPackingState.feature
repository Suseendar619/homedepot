@DFWMS
Feature: UndoWaveInPackingState
  
Scenario: UndoWave In Packing State in Houston
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReady"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReady"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Get details "ItemName" from xml for "BK1NonShipReady"
    Then Validate status for "oLPNs" - "Printed"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed" 
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then UndoWave by WaveNumber