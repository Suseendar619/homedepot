@DFWMS
Feature: WCS

  Scenario: WCS Sample
    Given Login as WMUser
	Then Create DO xml "BVR_LG"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_LG"
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
    Then Release Task From Wave "LG_2019"
    Then Get details "ItemName" from xml for "BVR_LG"
    Then Open "RF Menu" screen under "Distribution"
    Then Task Picking in RF "WCS_2019"
    Then Open Remote Server "WCS"
    Then Store procedure "PUSHED"
    ##Then Validate status for CL_Message "DIVERTED"
    Then Open "Pack Station Multis" screen under "Distribution"
    Then Multis Packing in RF "Diff_Loc"
    ##Then Validate status for "Distribution Orders" - "Packed"
    ##Then Validate status for "oLPNs" - "Packed"
    