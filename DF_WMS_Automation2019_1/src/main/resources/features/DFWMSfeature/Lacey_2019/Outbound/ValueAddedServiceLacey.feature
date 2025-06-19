@DFWMS
Feature: ValueAddedServiceDallas
  
  
Scenario: Value Added Service Flow Lacey
	Given Login as WMUser
	Then Create DO xml "VAS_Lacey"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "VAS_Lacey"
	Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Value Added Services" and Run
    Then Select "VAS" Define and Submit for "DallasVAS"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "VAS_Lacey"
    Then PickCart Picking in Putty
    Then Outbound PackCart in Putty
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "DallasVAS"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneOutbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "DallasVAS"
	Then Outbound LoadTrailer in Putty for "DallasZone"
	Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    