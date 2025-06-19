@DFWMS
Feature: UPSFlow
  
Scenario: Outbound UPS Flow Dallas
	Given Login as WMUser
	Then Create DO xml "UPS_Dallas"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Dallas"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for LM "ShipviaUPS"
    Then Validate status for LM "FTSR_NBR"
    Then Get details "ItemName" from xml for "UPS_Dallas"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "UPSDallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "UPSDallas"
	Then Outbound LoadParcel in Putty for "UPSDallas"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    Then Validate EPI status
    Then  Checkout Trailer