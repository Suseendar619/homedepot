@DFWMS
Feature: InboundCNDTR
  
Scenario: Inbound CN Drop to Reserve Tracey
	Given Login as WMUser
	Then  Create PO xml "CNReserveTracey"
	Then  Get "ItemName" from xml "Zone"
	#Then  Validate ItemZone in "Reserve" for "CN1"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "Zone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "IBZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "HoustonZone"
	Then  Inbound Receive in Putty "ZonePack"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "DTAZoneDallasBoth"
	Then  Validate ILPN status and Qty "Allocated"
	Then  Checkout Trailer