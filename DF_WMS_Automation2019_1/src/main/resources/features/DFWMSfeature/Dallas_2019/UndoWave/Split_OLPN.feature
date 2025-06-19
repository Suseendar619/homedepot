@DFWMS
Feature: UndoDOPacked
   
   
Scenario: Split oLPN with Qty greater than one
	Given Login as WMUser
	Then Create DO xml "Split_Combine"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "Split_Combine"
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
    Then Get details "ItemName" from xml for "Split_Combine"
    Then Validate status for LM "QTY"
    Then Outbound PickToLabel in Putty for "Split_Olpn"
    Then Validate status for LM "ShipviaFXHD"
    Then Validate status for LM "FTSR_NBR"
	Then Validate status for LM "QTY"
	