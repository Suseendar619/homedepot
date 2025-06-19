@DFWMS
Feature: Undo Custom Wave DO UI_All
  
Scenario: Undo Wave using WaveUI for Parcel orders centero
	Given Login as WMUser
	Then Create DO xml "FGND_Dallas"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FGND_Dallas"
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
    Then Validate status for "EpiStatus" - "SUCCESS"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Undo Wave for "BVR_UndoDO"
    Then Validate status for "Distribution Orders" - "Released"
    ##Then Validate status for "Event_Id" - "6120"
    ##Then Validate status for CL_Message "CL_Message" 
    Then Validate status for "Service_Type" - "Cancel"
