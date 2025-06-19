@DFWMS
Feature: TestStory
  
Scenario: Outbound LTL HDU Flow Lacey
	Then Login as WMUser
    Then Create DO xml "LTL HDU Lacey"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "LTL HDU Lacey"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTL HDU Lacey"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_HDU_Lacey"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "LTL HDU Lacey"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "LTL HDU Lacey"
	Then Outbound LoadTrailer in Putty for "DallasZone"
	Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "LaceyZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"