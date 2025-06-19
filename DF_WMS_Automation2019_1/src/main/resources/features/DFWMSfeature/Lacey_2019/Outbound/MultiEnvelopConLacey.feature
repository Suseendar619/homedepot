@DFWMS
Feature: MultiEnvelopConDallas
  
Scenario: Outbound Envelop Convey Multi Item Flow Lacey
	Given Login as WMUser
	Then Create DO xml "Envelop_Dallas"
	Then Create DO xml "EnvelopMulti_Dallas"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "Envelop_Dallas"
	Then Post PO xml "EnvelopMulti_Dallas"
	Then Get details "ReferenceField4" from xml for "EnvelopMulti_Dallas"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Conveyable - Pick Modules (Tote)" and Run
    Then Select "Momentum Wave DO" Define and Submit for "EnvelopMultiOrder_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"    
    Then Validate status for "oLPNs" - "Printed"