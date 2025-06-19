@DFWMS
Feature: WCS

  Scenario: Voice picking Demo 
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
    ##Then Open "RF Menu" screen under "Distribution"
    ##Then Task Picking in RF "VoicePicking"
    Then Execute Voice Picking commands "VoicePicking"
  