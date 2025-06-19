@DFWMS
Feature: Sample
  
Scenario: Hazmat Flow Houston 
	Given Login as WMUser
	Then Create DO xml "HazmatFlow"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "HazmatFlow"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "HazmatFlow"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Get details "ItemName" from xml for "HazmatFlow"
    Then Validate status for "oLPNs" - "Printed"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "MDO_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "ZoneOutbound"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "HoustonZone"
    Then Outbound LoadTrailer in Putty for "Zone"
    Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "Zone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"