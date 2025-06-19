@DFWMS
Feature: InboundCF
  
Scenario: Inbound CF2 Stage to Reserve flow Dallas
	Given Login as WMUser
	Then  Create PO xml "CF2ReserveDallas"
	Then  Get "ItemName" from xml "DallasZone"
	Then  Validate ItemZone in "Reserve" for "CF2"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "DallasZone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Zone"
	Then  Inbound Receive in Putty "DallasZone"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "STFZoneActiveDallas_Rloc"
	Then  Validate ILPN status and Qty "Putaway"
	Then  Checkout Trailer