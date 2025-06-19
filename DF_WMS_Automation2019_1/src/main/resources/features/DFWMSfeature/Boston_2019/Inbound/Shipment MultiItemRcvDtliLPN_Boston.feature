@DFWMS
Feature: Shipment MultiItemRcvDtliLPN_Boston

  Scenario: Shipment MultiItem Rcv Dtl iLPN flow Boston
    Given Login as WMUser
    Then  Create PO xml "ShipmentRcvDtlBoston"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentRcvDtlBoston"
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
 	Then  Get "ItemName" from xml "ShipmentRcvDtlBoston"
 	Then  Inbound Receive By Pallet in Putty
 	Then  Validate status for "ASN" - "Receiving Started"
 	Then  Inbound PutAway By Pallet in Putty
 	Then  Inbound Verify in Putty "VerifyIBShipment2019"
    Then  Validate status for "ASN" - "Receiving Verified"
  	Then  Checkout Trailer 	