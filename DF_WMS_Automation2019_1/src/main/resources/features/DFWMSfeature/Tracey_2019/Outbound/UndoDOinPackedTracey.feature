@DFWMS
Feature: UndoDOInPackedTampa
  
Scenario: OB Undo DO in Packed state Tracey
	Given Login as WMUser
	Then Create DO xml "LTLOutboundTracey_UndoDO"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "LTLOutboundTracey_UndoDO"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "LTLOutboundTampa_UndoDO"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTLOutboundTracey_UndoDO"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "LTLOutboundTampa_UndoDO"
    Then Undo Wave for "LTLOutboundTampa_UndoDO"
    