@DFWMS
Feature: LockUnlockLPN
  
Scenario: Lock and Unlock LPN Houston
	Given Login as WMUser
	Then Create DO xml "BK1NonShipReady"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReady"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BK1NonShipReady"
    Then Open "oLPNs" screen under "Distribution"
    Then Input oLPNs and Apply for "BK1NonShipReady"
    Then Lock LPN
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "MDO_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "HoustonLock"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "HoustonZone"
	Then Outbound LoadTrailer in Putty for "Zone"
	Then Unlock LPN Putty