@DFWMS
Feature: CartPickConveyableMod
  
Scenario: CartPickConveyableMod Newark
	Given Login as WMUser
	Then Create DO xml "CartPickConNewark"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "CartPickConNewark"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"