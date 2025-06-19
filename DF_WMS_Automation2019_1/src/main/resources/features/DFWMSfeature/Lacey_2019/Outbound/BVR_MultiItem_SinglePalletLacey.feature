@DFWMS
Feature: BVR Flow
  
Scenario: Outbound BVR Flow Lacey MultiItem Single Pallet
	Given Login as WMUser
	Then Create DO xml "BVR_Lacey_MISP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Lacey_MISP"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Non-Con - Bulk 1" and Run  
    Then Select "Slapper Test" Define and Submit for "BVR_Lacey"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion   
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Lacey_MISP"
    Then Outbound PickToLabel in Putty for "BVR_Dallas_MISP"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Lacey"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_Lacey"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Dallas_MISP"
	Then Outbound LoadTrailer in Putty for "BVR_Dallas_MISP"
    Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"