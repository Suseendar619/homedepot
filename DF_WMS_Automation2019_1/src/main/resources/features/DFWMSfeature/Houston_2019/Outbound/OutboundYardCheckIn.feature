@DFWMS
Feature: OutboundYardCheckIn
  
Scenario: Outbound Yard CheckIn Houston
	Given Login as WMUser
	Then Create DO xml "BVR_Houston_Yard"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Houston_Yard"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Houston_Yard"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion   
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Houston_Yard"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "Outbound_Yard"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "Outbound_Yard"
	Then Validate yard rules for "OutboundHouston"
	Then Open "Yard Tasks" screen under "Distribution"
	Then Add Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Released"
	Then Complete Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Complete"