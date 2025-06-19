@DFWMS
Feature: UPSFlow
  
Scenario: Outbound UPS Flow Houston MultiItem SinglePallet
	Given Login as WMUser
	Then Create DO xml "UPS_Houston_MISP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Houston_MISP"
    Then Validate status for "Distribution Orders" - "Released"    
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Dallas_MISP"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion 
    Then Validate status for "Distribution Orders" - "DC Allocated"   
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for LM "ShipviaUPS"
    Then Validate status for LM "FTSR_NBR"
    Then Get details "ItemName" from xml for "UPS_Houston_MISP"
    Then Outbound PickToLabel in Putty for "UPS_Dallas_MISP"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "UPSDallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "UPS_Houston"
	Then Outbound LoadParcel in Putty for "UPS_Dallas_MIMP"
    ##Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for LM "oLPNs" - "Shipped"   
    Then Open "Ship Confirm Rules" screen under "Distribution"
    Then Ship Confirm Rules for "UPS_Dallas_MISP"
    ##Then Open "EPI Transaction Log" screen under "Distribution"
    ##Then Input and Apply WaveNumber for "UPS_Dallas"  