@DFWMS
Feature: InboundShotgun
  
Scenario: Inbound Shot Gun Dallas Flow
	Given Login as WMUser
	Then  Create PO xml "ShotgunOrder"
	Then  Create PO xml "ShotgunASN"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "ShotgunOrder"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "ShotgunASN"
	Then  Validate status for "ASN" - "InTransit"
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "Shotgun"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Shotgun"
    Then  IB CheckIN "Zone"
	Then  Open "ASNs" screen under "Distribution"
	Then  Search "ASN" and Validate Status "In Transit" for "Shotgun"
	Then  Auto Receive
	Then  Validate status for "ASN" - "Receiving Verified"
	Then  Validate iLPNs and oLPNs status
	Then  Checkout Trailer	