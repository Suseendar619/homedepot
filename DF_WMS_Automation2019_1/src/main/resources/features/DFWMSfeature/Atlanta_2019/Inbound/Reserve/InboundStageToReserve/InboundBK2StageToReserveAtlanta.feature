@DFWMS
Feature: InboundBK2
  
Scenario: Inbound BK2 Stage to Reserve flow Atlanta
	Given Login as WMUser
	Then  Create PO xml "BK2ReserveAtlanta"
	Then  Get "ItemName" from xml "Zone"
	##Then  Validate ItemZone in "Reserve" for "BK2"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "Zone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "IBZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "HoustonZone"
	Then  Inbound Receive in Putty "ZonePack"
	Then Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "STFZoneActiveDallas_Rloc"
	Then  Validate ILPN status and Qty "Allocated"
	Then  Checkout Trailer