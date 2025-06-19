@DFWMS
Feature: Sample
  
Scenario: IB Shotgun Lacey
	Then Login as WMUser
	Then  Create PO xml "ShotgunOrder_Lacey"
	Then  Create PO xml "ShotgunASN_Lacey"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "ShotgunOrder_Lacey"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "ShotgunASN_Lacey"
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "Shotgun"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Shotgun"
	Then  Open "ASNs" screen under "Distribution"
	Then  Search "ASN" and Validate Status "In Transit" for "Shotgun"
	Then  Auto Receive
	Then  Validate status for "ASN" - "Receiving Verified"
	Then  Validate iLPNs and oLPNs status		