@DFWMS
Feature: Outbound_LTLHDU_Flow_Newark
  
Scenario: Outbound LTL HDU Flow Newark
	Then Login as WMUser
    Then Create DO xml "LTL_HDU_Newark"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "LTL_HDU_Newark"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
	Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTL_HDU_Newark"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_HDU_Baltimore"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "LTL_HDU_Baltimore"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "LTL_HDU_Baltimore"
	Then Outbound LoadTrailer in Putty for "DallasZone"
	Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"