@DFWMS
Feature: Undo Custom Wave DO UI_All
  
Scenario: Mod On Undo Custom Wave DO UI_Single Order Multi Wave Olpn Packed Dallas
	Given Login as WMUser
	Then Create DO xml "FXHD_Dallas_Undo"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FXHD_Dallas_Undo"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "ConveyFlagYes"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "Partially DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for "EpiStatus" - "SUCCESS"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "ConveyFlagNo"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for "EpiStatus" - "SUCCESS"
    Then Get details "ItemName" from xml for "FXHD_Dallas_Undo"
    Then Outbound Picking in Putty "FXHD_Dallas_Undo"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Undo Wave for "BVR_UndoDO"
    ##Then Validate status for "Distribution Orders" - "Released"
    ##Then Validate status for "Event_Id" - "6120"
    ##Then Validate status for CL_Message "CL_Message" 
    ##Then Validate status for "Service_Type" - "Cancel"