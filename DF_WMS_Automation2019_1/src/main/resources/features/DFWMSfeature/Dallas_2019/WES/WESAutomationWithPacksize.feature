@DFWMS
Feature: WES
  
Scenario: WES flow with wave with Packsize
	Then Login as WMUser
	Then Create DO xml "UPS_Dallas_Wyn"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "UPS_Dallas_Wyn"
	Then Validate status for "Distribution Orders" - "Released"
	Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Conveyable - Pick Modules (Packsize)" and Run
    Then Click Submit and get Wave number
	Then Open "Waves" screen under "Distribution"
	Then Input WaveNumber and Apply 
	Then Validate Wave Completion    
	Then Validate status for "Distribution Orders" - "DC Allocated"
	Then Validate status for "oLPNs" - "Printed"
	Then Get details "ItemName" from xml for "UPS_Dallas"
	Then Validate status for CL_Message "WaveData"
	Then Login as WESUser
	Then Select Order number and Release
    Then Login as PacksizeDallasUser
    Then Search Waves in Packsize
     
    