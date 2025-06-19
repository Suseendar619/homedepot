@DFWMS
Feature: OutboundYardCheckIn
  
Scenario: Outbound Yard CheckIn Lacey
	Given Login as WMUser
	Then Create DO xml "BVR_Lacey_Yard"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Lacey_Yard"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas_Yard"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Lacey_Yard"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "Outbound_Yard_BVR_Lacey"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "Yard"
	Then Validate yard rules for "OutboundLacey"
	Then Open "Yard Tasks" screen under "Distribution"
	Then Add Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Released"
	Then Complete Yard Task
	Then Open "Yard Tasks" screen under "Distribution"
	Then Validate Task status "Complete"