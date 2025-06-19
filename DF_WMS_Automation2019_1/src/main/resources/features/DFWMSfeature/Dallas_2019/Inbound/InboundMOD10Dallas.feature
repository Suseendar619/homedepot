@DFWMS
Feature: InboundMOD10Dallas
  
Scenario: Inbound MOD10 flow Dallas
	Given Login as WMUser
	Then  Create PO xml "MOD10_Dallas"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "MOD10_Dallas"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Zone"
	Then  Get "ItemName" from xml "MOD10_Dallas"
	Then  Inbound Receive in Putty "RcvAndSortiLPN"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "MOD10_Dallas"
	Then  Checkout Trailer