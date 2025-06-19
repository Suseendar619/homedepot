@DFWMS
Feature: InboundSR6DropToReserve
  
  
Scenario: Inbound SR6 Drop To Reserve Flow Lacey
	Given Login as WMUser
	Then  Create PO xml "SR6ReserveLacey"
	Then  Get "ItemName" from xml "Zone"
	Then  Validate ItemZone in "Reserve" for "SR6"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "Zone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Zone"
	Then  Inbound Receive in Putty "ZonePack"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "DTAZoneDallasBoth"
	Then  Validate ILPN status and Qty "Case consumed to active"
	Then  Checkout Trailer