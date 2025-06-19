@DFWMS
Feature: Loadtrailer
  
Scenario: Single And Multi Olpn Using Pallet With Load Trailor Option
	Given Login as WMUser
	Then Create DO xml "LoadTrailor_NonParcel"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "LoadTrailor_NonParcel"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion   
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LoadTrailor_NonParcel"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Dallas"
	Then Open "Transactions" screen under "Configuration"
	Then Select Loadtrailer and and check the Max olpn Value
	Then Outbound LoadTrailer in Putty for "WM18"
    Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"