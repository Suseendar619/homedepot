@DFWMS
Feature: OutboundYardCheckIn
  
Scenario: Outbound Yard CheckIn Miami
	Given Login as WMUser
	Then Create DO xml "LTLOutboundMiami"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "LTLOutboundMiami"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas_Yard"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion 
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTLOutboundMiami"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "Outbound_Yard_LTL"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "Yard"
	Then Validate yard rules for "OutboundBaltimore"
	Then Open "Yard Tasks" screen under "Distribution"
	Then Add Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Released"
	Then Complete Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Complete"