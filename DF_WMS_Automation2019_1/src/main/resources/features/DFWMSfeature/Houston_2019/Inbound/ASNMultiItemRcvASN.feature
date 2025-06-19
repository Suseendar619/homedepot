@DFWMS
Feature: MultiItemRcvASN

 Scenario: ASN MultiItem Receive ASN flow
 	Given Login as WMUser
	Then  Create PO xml "MultiItemRcvASN"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "MultiItemRcvASN"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "MultiItemRcvASN"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "MultiItemRcvASNHouston"
	Then Validate status for "ASN" - "InTransit"
	Then  Create iLPNs
	Then  Get "ItemName" from xml "MultiItemRcvASN"
	Then  Inbound Receive in Putty "MultiItemRcvASN"
	Then Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "MultiItemRcvASN"
	Then  Checkout Trailer