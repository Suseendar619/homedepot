@DFWMS
Feature: ASNMultiItemRcvDtliLPN

 Scenario: ASN MultiItem Receive Detail flow
 	Given Login as WMUser
	Then  Create PO xml "MultiItemASNRcvDtl"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "MultiItemASNRcvDtl"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then Validate status for "ASN" - "InTransit"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "MultiItemASNRcvDtlInbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "HoustonZone"
	Then  Open "ASN" screen under "Distribution"
	Then  Search "ASN" and Validate Status "In Transit"
	Then  Create iLPNs
	Then  Get "ItemName" from xml "MultiItemASNRcvDtl"
	Then  Inbound Receive in Putty "MultiItemASNRcvDtl"
	Then  Open "ASN" screen under "Distribution"
	Then  Search "ASN" and Validate Status "Receiving Started"
	Then  Validate iLPN's
	Then Validate status for "ASN" - "Receiving Started"
	Then Validate status for "ASN" - "Receiving Verified"
	Then  Inbound Putaway in Putty "MultiItemASNRcvDtl"
	Then  Checkout Trailer