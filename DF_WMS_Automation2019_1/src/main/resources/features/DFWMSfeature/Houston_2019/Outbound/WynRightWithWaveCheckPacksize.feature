@DFWMS
Feature: WynrightFlow
  
Scenario: Wynright flow with wave and check packsize status
	Then Login as WMUser
	Then Create DO xml "UPS_Houston_Wyn"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "UPS_Houston_Wyn"
	Then Validate status for "Distribution Orders" - "Released"
	Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Cart Pick - Conveyable Mod" and Run
    Then Click Submit and get Wave number
	Then Open "Waves" screen under "Distribution"
	Then Input WaveNumber and Apply 
	Then Validate Wave Completion    
	Then Validate status for "Distribution Orders" - "DC Allocated"
	Then Validate status for "oLPNs" - "Printed"
	Then Validate status for LM "ShipviaUPS"
	Then Get details "ItemName" from xml for "UPS_Houston"
	Then Validate status for CL_Message "WaveData" 
    Then Login as WynUser
	Then Open Olpn Management and Search WaveNumber apply
    Then Select CartStart and Release
    Then Login as PacksizeUser
    Then Search Waves in Packsize
    