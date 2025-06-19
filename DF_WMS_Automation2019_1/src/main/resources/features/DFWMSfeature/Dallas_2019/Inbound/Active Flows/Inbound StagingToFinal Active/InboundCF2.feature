@DFWMS
Feature: InboundCF2
  
Scenario: Inbound CF2 flow Dallas
	Given Login as WMUser
	Then  Create PO xml "CF2InboundDallas"
	Then  Open "Post Message" screen under "Integration"
	Then  Get "ItemName" from xml "DallasZone"
	Then  Validate ItemZone in "Active" for "CF2"
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
	Then  Inbound Putaway in Putty "STFZoneActiveDallas_Aloc"
	Then  Validate ILPN status and Qty "Case consumed to active"
	Then  Checkout Trailer