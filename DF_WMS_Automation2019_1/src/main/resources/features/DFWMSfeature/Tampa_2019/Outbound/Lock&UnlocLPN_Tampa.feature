@DFWMS
Feature: LockUnlockLPN
  
Scenario: Lock and Unlock LPN Tampa
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReadyTampa"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReadyTampa"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BK1NonShipReadyTampa"
    Then Open "oLPNs" screen under "Distribution"
    Then Input oLPNs and Apply for "BK1NonShipReadyTampa"
    Then Lock LPN
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "MDO_Tampa"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "DallasZoneOutbound"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BK1NonShipReady"
	Then Outbound LoadTrailer in Putty for "DallasZone"
	Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
	Then Unlock LPN Putty