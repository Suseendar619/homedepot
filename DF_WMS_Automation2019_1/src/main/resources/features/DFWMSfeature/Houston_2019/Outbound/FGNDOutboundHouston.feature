@DFWMS
Feature: FGNDOutboundHouston
  
Scenario: Outbound FGND Flow Houston 
	Given Login as WMUser
	Then Create DO xml "FGND_Houston"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FGND_Houston"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "FGND_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for LM "ShipviaFGND"
    Then Validate status for LM "FTSR_NBR"
    Then Get details "ItemName" from xml for "FGND_Houston"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "FGND_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "FGND_Houston"
	Then Outbound LoadParcel in Putty for "FGND_Houston"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    Then Validate EPI status