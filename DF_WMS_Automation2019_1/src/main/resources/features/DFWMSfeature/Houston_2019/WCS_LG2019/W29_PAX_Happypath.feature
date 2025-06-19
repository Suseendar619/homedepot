@DFWMS
Feature: W29_PAX_Happypath

  Scenario: W29 PAX Happy Path
    Given Login as WMUser
	Then Create DO xml "PAX"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "PAX"
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
    Then Get details "ItemName" from xml for "PAX"
    Then Open "RF Menu" screen under "Distribution"
    Then Task Picking in RF "WCS_2019"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Validate status for CL_Message "TOTEDIRECTIVE"
    Then Validate status for CL_Message "OLPNDIRECTIVE"