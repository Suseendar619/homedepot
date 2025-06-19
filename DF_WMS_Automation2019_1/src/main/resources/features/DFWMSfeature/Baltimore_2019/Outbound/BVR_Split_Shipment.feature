@DFWMS
Feature: BVR_Split_Shipment
  
Scenario: OB BVR Baltimore Split Shipment
	Given Login as WMUser
	Then Create DO xml "BVR_Dallas_SplitShipment1"
	Then Create DO xml "BVR_Dallas_SplitShipment2"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Dallas_SplitShipment1"
	Then Post PO xml "BVR_Dallas_SplitShipment2"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Split_Shipment"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Dallas_SplitShipment1"
    Then Outbound PickToLabel in Putty for "BVR_Split_Shipment"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "Shipment1"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_MS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_MS"
	Then Outbound LoadTrailer in Putty for "BVR_Dallas_SplitShipment1"
	Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
	Then Validate status for "oLPNs" - "Shipped"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "Shipment2"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_MS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_MS"
	Then Outbound LoadTrailer in Putty for "BVR_Dallas_SplitShipment2"
	Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
	Then Validate status for "oLPNs" - "Shipped"