@DFWMS
Feature: UPSFluidLoadFlow
  
Scenario: Outbound UPS Fluid Load Flow Houston
	Given Login as WMUser
	Then Create DO xml "UPS_Houston"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "UPS_Houston"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "UPS_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for LM "ShipviaUPS"
    Then Validate status for LM "FTSR_NBR"
    Then Get details "ItemName" from xml for "UPS_Houston"
    Then Outbound PickToLabel in Putty for "MultiCon"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "UPS_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "UPS_Houston"
	Then Insert oLPNs into Divert history table "oLPNs" - "Printed"
	Then Outbound PCLFluidLoad in Putty for "UPS_Houston"
	##Then Outbound LoadParcel in Putty for "UPS_Houston"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    Then Validate Mhe_cntr_state for "oLPNs" - "99"
    Then Open "EPI Transaction Log" screen under "Distribution"
    Then Input and Apply WaveNumber for "UPS_Houston"  
    Then  Checkout Trailer