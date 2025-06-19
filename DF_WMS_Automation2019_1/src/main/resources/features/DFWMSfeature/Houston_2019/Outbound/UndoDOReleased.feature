@DFWMS
Feature: UndoDOReleased
  
Scenario: OB UndoDO in Released State Houston
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReady_UndoDO"
	Then Create DO xml "BVR_Houston_UndoDO"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReady_UndoDO"
	Then Post PO xml "BVR_Houston_UndoDO"
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
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply    
	Then UndoWave in WaveUI
    Then Validate status for "Distribution Orders" - "Released"    