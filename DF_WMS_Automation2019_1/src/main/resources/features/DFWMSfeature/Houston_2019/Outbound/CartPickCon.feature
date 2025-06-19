@DFWMS
Feature: CartPickConveyableMod
   
Scenario: CartPickConveyableMod Houston
	Given Login as WMUser
	Then Create DO xml "CartPickCon"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "CartPickCon"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"