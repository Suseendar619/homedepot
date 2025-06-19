@DFWMS
Feature: LTLOutbound Split Shipment
  
Scenario: LTL Split Shipment Outbound Flow Newark
    Then Login as WMUser
    Then Create DO xml "SplitShipmentDallas"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "SplitShipmentDallas"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "SplitShipmentDallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "SplitShipmentDallas"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_Dallas"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "SplitShipmentDallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "SplitShipmentDallas"
	Then Outbound LoadTrailer in Putty for "DallasZone"
    Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_Dallas"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "SplitShipmentDallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "SplitShipmentDallas"
	Then Outbound LoadTrailer in Putty for "DallasZone"
    Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"