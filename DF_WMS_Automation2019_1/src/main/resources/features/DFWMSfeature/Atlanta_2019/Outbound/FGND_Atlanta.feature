@DFWMS
Feature: FGNDOutbound
  
Scenario: Outbound FGND Flow Atlanta 
	Given Login as WMUser
	Then Create DO xml "FGND_Atlanta"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FGND_Atlanta"   
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "FGND_Tampa"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion  
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "FGND_Atlanta"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "FGND_Dallas"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "FGND_Atlanta"
	Then Outbound LoadParcel in Putty for "FGND_Dallas"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    Then Open "EPI Transaction Log" screen under "Distribution"
    Then Input and Apply WaveNumber for "FGND_Dallas" 