@DFWMS
Feature: TBLLocationDallas
  
  
Scenario: TBL Location Active Lacey
	Given Login as WMUser
	Then  Create PO xml "TBLActiveLacey"
	Then  Get "ItemName" from xml "Zone"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "Zone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Zone"
	Then  Inbound Receive in Putty "ZonePack"
	Then Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "TBLZoneActiveDallas"
	Then  Validate ILPN status and Qty "Putaway"
	Then  Checkout Trailer