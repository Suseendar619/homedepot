@DFWMS
Feature: Sample 
  
Scenario: Inbound Mod10 Flow Baltimore
	Given Login as WMUser
	Then  Create PO xml "MOD10_Baltimore"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "MOD10_Baltimore"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "DallasZoneInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Zone"
	Then  Get "ItemName" from xml "MOD10_Baltimore"
	Then  Inbound Receive in Putty "RcvAndSortiLPN"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Validate ILPN status and Qty "Allocated"
	Then  Inbound Putaway in Putty "MOD10_Dallas"
	Then  Validate ILPN status and Qty "Consumed"