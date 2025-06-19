@DFWMS
Feature: UndoDOPacked
   
   
Scenario: Change carrier code from E047 to E006
	Given Login as WMUser
	Then Create DO xml "UPS_Houston"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Houston"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "UPS_Houston"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed" 
    Then Validate status for LM "ShipviaUPS"
    Then Validate status for LM "FTSR_NBR"
	Then Open "Weigh and Manifest oLPN" screen under "Distribution"
	Then Change olpn carrier "FXHD"
	Then Validate status for LM "ShipviaFXHD"
    Then Validate status for LM "FTSR_NBR"
	Then Validate EPI status