@DFWMS
Feature: UndoDOInPackedTampa
  
Scenario: OB Undo DO in Packed state Miami
	Given Login as WMUser
	Then Create DO xml "LTLOutboundMiami"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "LTLOutboundMiami"
	Then Validate status for "Distribution Orders" - "Released" 
	Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "LTLOutboundMiami"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTLOutboundMiami"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "LTLOutboundMiami"
    Then Undo Wave for "LTLOutboundMiami"
    