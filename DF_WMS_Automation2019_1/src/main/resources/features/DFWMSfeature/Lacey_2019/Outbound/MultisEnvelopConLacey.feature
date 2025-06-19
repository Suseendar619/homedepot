@DFWMS
Feature: EnvelopConDallas
  
Scenario: Outbound Envelop Convey Flow Lacey
	Given Login as WMUser
	Then Create DO xml "EnvelopMultiItem_Dallas"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "EnvelopMultiItem_Dallas"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Conveyable - Pick Modules (Tote)" and Run
    Then Select "Momentum Wave DO" Define and Submit for "EnvelopMultiItem_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion   
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Validate status for "oLPNs" - "Printed"