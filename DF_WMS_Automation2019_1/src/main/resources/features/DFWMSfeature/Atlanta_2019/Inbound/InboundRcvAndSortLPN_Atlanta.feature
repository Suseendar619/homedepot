@DFWMS
Feature: InboundRcvAndSortLPN
  
Scenario: Inbound Rcv and Sort iLPN Atlanta
	Given Login as WMUser
	Then  Create PO xml "BK1ActiveAtlanta"
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
	Then  Get "ItemName" from xml "Zone"
	Then  Inbound Receive in Putty "RcvAndSortiLPN"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Validate ILPN status and Qty "Allocated"