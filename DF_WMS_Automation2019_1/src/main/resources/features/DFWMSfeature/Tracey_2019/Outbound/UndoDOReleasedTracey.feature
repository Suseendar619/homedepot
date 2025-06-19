@DFWMS
Feature: UndoDOReleasedTampa
  
Scenario: OB Undo DO in released state Tracey
	Given Login as WMUser
	Then Create DO xml "BK1NSRTampa_UndoDO"
	Then Create DO xml "LTLOutboundTampa_UndoDO"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NSRTampa_UndoDO"
	Then Post PO xml "LTLOutboundTampa_UndoDO"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_UndoDO"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "BVR_UndoDO"
    Then Undo Wave for "BVR_UndoDO"
    #Then Validate status for "Distribution Orders" - "Released"