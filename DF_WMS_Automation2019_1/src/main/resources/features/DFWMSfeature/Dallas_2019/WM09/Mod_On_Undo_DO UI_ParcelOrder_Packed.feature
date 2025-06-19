@DFWMS
Feature: UndoDOPacked
   
   
Scenario: MOD ON_UNDO DO UI Custom Wave_Parcel orders _orders in both Packing and  Packed status
	Given Login as WMUser
	Then Create DO xml "FXHD_Dallas_Undo"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FXHD_Dallas_Undo"
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
    Then Get details "ItemName" from xml for "FXHD_Dallas_WM09"
    Then Outbound Picking in Putty
    Then Get details "ItemName" from xml for "UPS_Dallas_WM09"
    ##Then Validate status for "Distribution Orders" - "Weighed"
    ##Then Validate status for "oLPNs" - "Weighed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Undo Wave for "BVR_UndoDO"
    Then Validate status for "Distribution Orders" - "Released"
    