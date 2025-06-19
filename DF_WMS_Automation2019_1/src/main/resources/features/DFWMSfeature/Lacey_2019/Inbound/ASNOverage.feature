@DFWMS
Feature: Sample
  
Scenario: ASN Overage flow Lacey
	Given Login as WMUser
	Then  Create PO xml "BK1ActiveLacey"
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
	Then  IB CheckIN "Zone"
	Then  Get "ItemName" from xml "Zone"
	Then  Inbound Receive in Putty "ASN_OverageDallas"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Verify in Putty "ASN_OverageDallas"
	Then  Validate status for "ASN" - "Receiving Verified"