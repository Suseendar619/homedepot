@DFWMS

Feature: Shipment MultiItemRcvDtliLPN_Newark

Scenario: Shipment MultiItem Rcv Dtl iLPN flow Newark
    Given Login as WMUser
    Then  Create PO xml "ShipmentRcvDtlNewark"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentRcvDtlNewark"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
	Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "InTransit"
    Then  Create iLPNs
 	Then  Validate iLPN's
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "ShipmentRcvDtlInbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "HoustonZone"
 	Then  Get "ItemName" from xml "ShipmentRcvDtlNewark"
 	Then  Inbound Receive By Pallet in Putty
 	Then  Validate status for "ASN" - "Receiving Started"
 	Then  Inbound PutAway By Pallet in Putty
    Then  Validate status for "ASN" - "Receiving Verified"
  	Then  Checkout Trailer 	