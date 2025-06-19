@DFWMS
Feature: Shipment MultiItemRcvDtliLPN_Atlanta

  Scenario: Shipment MultiItem Rcv Dtl iLPN flow Atlanta
    Given Login as WMUser
    Then  Create PO xml "ShipmentRcvDtlAtlanta"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentRcvDtlAtlanta"
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
 	Then  Get "ItemName" from xml "ShipmentRcvDtlAtlanta"
 	Then  Inbound Receive By Pallet in Putty
 	Then  Validate status for "ASN" - "Receiving Started"
 	Then  Inbound PutAway By Pallet in Putty
 	Then  Inbound Verify in Putty "VerifyIBShipment2019"
    Then  Validate status for "ASN" - "Receiving Verified"
  	Then  Checkout Trailer 	