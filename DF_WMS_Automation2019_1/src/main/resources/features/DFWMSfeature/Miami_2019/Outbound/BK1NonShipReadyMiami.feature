@DFWMS
Feature: BK1NonShipReady
  
Scenario: BK1 Non Ship Ready Miami
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReadyMiami"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReadyMiami"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReadyTampa"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Get details "ItemName" from xml for "BK1NonShipReadyMiami"
    Then Validate status for "oLPNs" - "Printed"
    Then Outbound Picking in Putty
    #Then Validate status for "Distribution Orders" - "Packed"
    ##Then Validate status for "oLPNs" - "Packed"
    ##Then Validate status for "oLPNs" - "Weighed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "MDO_Miami"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "LaceyZoneOutbound"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "LTLOutboundHouston"
	Then Outbound LoadTrailer in Putty for "Zone"
    Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"    