@DFWMS
Feature: UPSFluidLoadFlow
  
Scenario: Outbound FedEx Fluid Load Flow Houston Carrier Code Does Not Match
	Given Login as WMUser
	Then Create DO xml "FXHD_Houston"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FXHD_Houston"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "FXHD_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Validate status for LM "ShipviaFXHD"
    Then Validate status for LM "FTSR_NBR"
    Then Get details "ItemName" from xml for "FXHD_Houston"
    Then Outbound PickToLabel in Putty for "Con"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "UPS_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "UPS_Houston"
	Then  Insert oLPNs into Divert history table "oLPNs" - "Printed"
	##Then Outbound PCLFluidLoad in Putty for "UPS_Houston"
	##Then Outbound LoadParcel in Putty for "UPS_Houston"
	Then  Open "RF Menu" screen under "Distribution"
	Then  Outbound PCLFluidLoad RF menu UI "PCL Fluid Load" and "Carrier does not match Dock Door Carrier"
	Then  Checkout Trailer