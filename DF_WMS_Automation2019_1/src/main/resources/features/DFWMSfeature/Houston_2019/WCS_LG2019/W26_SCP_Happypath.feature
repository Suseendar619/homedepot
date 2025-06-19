@DFWMS
Feature: W26_SCP_Happypath

  Scenario: W26 SCP Happy Path
    Given Login as WMUser
    ##Then Open "Pack Station Singles" screen under "Distribution"
    ##Then Singles Packing in RF "WCS_2019"
	Then Create DO xml "SCP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "SCP"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Conveyable - Pick Modules" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Validate status for "oLPNs" - "Printed"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then Release Task From Wave "LG_2019"
    Then Get details "ItemName" from xml for "SCP"
    Then Open "RF Menu" screen under "Distribution"
    Then Task Picking in RF "WCS_2019"
    Then Open "Pack Station Singles" screen under "Distribution"
    Then Singles Packing "WCS_2019"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Validate status for CL_Message "TOTEDIRECTIVE"
     Then Validate status for CL_Message "OLPNDIRECTIVE"