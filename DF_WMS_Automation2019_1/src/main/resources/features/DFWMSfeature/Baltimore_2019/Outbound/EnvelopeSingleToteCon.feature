@DFWMS
Feature: EnvelopeSingleToteCon
  
Scenario: Outbound Envelop SingleTote Con Flow Baltimore
	Given Login as WMUser
	Then Create DO xml "Envelop_SingleTote1_Baltimore"
	Then Create DO xml "Envelop_SingleTote2_Baltimore"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "Envelop_SingleTote1_Baltimore"
	Then Post PO xml "Envelop_SingleTote2_Baltimore"
	Then Get details "ReferenceField4" from xml for "Envelop_SingleTote1_Baltimore"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Conveyable - Pick Modules (Tote)" and Run
    Then Select "Momentum Wave DO" Define and Submit for "EnvelopSingleTote_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"