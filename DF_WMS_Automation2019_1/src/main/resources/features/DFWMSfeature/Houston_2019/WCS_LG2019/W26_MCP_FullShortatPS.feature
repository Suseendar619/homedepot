@DFWMS
Feature: W26_MCP_FullShortatPS

  Scenario: W26 MCP Full Short at PS
    Given Login as WMUser
	Then Create DO xml "MCP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "MCP"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    ##Then Select Wave "Convey Flow" and Run
    Then Select Wave "Conveyable - Pick Modules" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then Release Task From Wave "LG_2019"
    Then Get details "ItemName" from xml for "MCP"
    Then Open "RF Menu" screen under "Distribution"
    Then Task Picking in RF "WCS_2019"
    Then Store procedure "CONT_STAT_DIVERT"
    Then Store procedure "FULLPICK"
    Then Store procedure "CONT_STAT_PUSH"
    Then Open "Pack Station Multis" screen under "Distribution"
    Then Short at Multis "WCS_2019"
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for CL_Message "LIGHTCONTROL"
    Then Validate status for CL_Message "TOTEDIRECTIVE"
    Then Validate status for Olpn_misc "Suspended Short Multis"
    
    
    
    
    
    
   
    