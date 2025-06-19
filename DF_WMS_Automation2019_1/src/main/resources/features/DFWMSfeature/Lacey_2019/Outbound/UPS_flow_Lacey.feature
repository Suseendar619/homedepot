@DFWMS
Feature: UPSFlow
  
Scenario: Outbound UPS Flow Lacey
	Given Login as WMUser
	Then Create DO xml "UPS_Lacey"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Lacey"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Lacey"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "UPS_Lacey"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "UPSDallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "UPS_Lacey"
	Then Outbound LoadParcel in Putty for "UPSDallas"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    Then Open "EPI Transaction Log" screen under "Distribution"
    Then Input and Apply WaveNumber for "UPS_Dallas"  