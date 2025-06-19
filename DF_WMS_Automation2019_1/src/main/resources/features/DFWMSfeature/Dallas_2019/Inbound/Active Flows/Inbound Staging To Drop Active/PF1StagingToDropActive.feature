@DFWMS
Feature: PF1StagingToDropActive
  
Scenario: Staging To Drop Active Flow PF1 Dallas
	Given Login as WMUser
	Then  Create PO xml "PF1InboundDallas"
	Then  Get "ItemName" from xml "DallasZone"
	Then  Validate ItemZone in "Active" for "PF1"
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
	Then  Inbound Putaway in Putty "STDZoneActiveDallas_Rloc"
	Then  Validate ILPN status and Qty "Allocated"
	Then  Checkout Trailer