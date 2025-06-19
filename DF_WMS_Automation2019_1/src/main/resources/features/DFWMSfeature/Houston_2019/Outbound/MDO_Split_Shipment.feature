@DFWMS
Feature: MDO_Split_Shipment
  
Scenario: OB MDO Houston Split Shipment
	Given Login as WMUser
	Then Create DO xml "MDO_Dallas_SplitShipment1"
	Then Create DO xml "MDO_Dallas_SplitShipment2"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "MDO_Dallas_SplitShipment1"
	Then Post PO xml "MDO_Dallas_SplitShipment2"
    Then Validate status for "Distribution Orders" - "Released"   
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "UndoWaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "MDO_Dallas_SplitShipment1"
    Then Outbound PickToLabel in Putty for "BVR_Split_Shipment"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "Shipment1"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_MS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_MS"
	Then Outbound LoadTrailer in Putty for "MDO_Dallas_SplitShipment1"
    Then Outbound CloseTrailer in Putty for "DallasZone"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "Shipment2"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_MS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_MS"
	Then Outbound LoadTrailer in Putty for "MDO_Dallas_SplitShipment2"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"