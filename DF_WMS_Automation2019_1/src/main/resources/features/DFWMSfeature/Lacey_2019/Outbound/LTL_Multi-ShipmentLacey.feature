@DFWMS
Feature: LTL_Multi-ShipmentLacey
  
Scenario: LTL MultiShipment Outbound Flow Lacey
    Then Login as WMUser
    Then Create DO xml "MultiShipmentLacey"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "MultiShipmentLacey"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "SplitShipmentLacey"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "MultiShipmentLacey"
    Then Outbound PickToLabel in Putty for "BVR_MS"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_Lacey"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "SplitShipmentLacey"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "SplitShipmentLacey"
	Then Outbound LoadTrailer in Putty for "Shipment1"
	Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "LaceyZone"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_Lacey"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "SplitShipmentLacey"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "SplitShipmentLacey"
	Then Outbound LoadTrailer in Putty for "Shipment2"
    Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"