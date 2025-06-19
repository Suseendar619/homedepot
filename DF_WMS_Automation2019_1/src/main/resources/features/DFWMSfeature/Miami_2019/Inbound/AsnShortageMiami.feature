@DFWMS
Feature: AsnShortage
  
Scenario: ASN Shortage flow Miami
	Given Login as WMUser
	Then  Create PO xml "BK1ActiveMiami"
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
	Then  Inbound Receive in Putty "ASN_ShortageDallas"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Verify in Putty "ASN_ShortageDallas"
	Then  Validate status for "ASN" - "Receiving Verified"