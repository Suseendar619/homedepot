@DFWMS
Feature: CartPickConveyableMod
  
Scenario: CartPickConveyableMod Miami
	Given Login as WMUser
	Then Create DO xml "CartPickConMiami"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "CartPickConMiami"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "CartPickConTampa"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"