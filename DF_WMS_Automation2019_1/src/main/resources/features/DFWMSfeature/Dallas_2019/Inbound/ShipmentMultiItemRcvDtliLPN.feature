@DFWMS
Feature: ShipmentMultiItemRcvDtliLPN

  Scenario: Shipment Multi ItemRcv Dtl iLPN flow
    Given Login as WMUser
    Then  Create PO xml "ShipmentRcvDtl_Dallas"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentRcvDtl_Dallas"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
	Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "InTransit"
    Then Create iLPNs
 	Then Validate iLPN's
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
	##Then  Open "ASNs" screen under "Distribution"
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "ShipmentRcvDtl_DallasInbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "Zone"
 	Then Get "ItemName" from xml "ShipmentRcvDtl_Dallas"
 	Then Inbound Receive By Pallet in Putty
 	Then Open "ASNs" screen under "Distribution"
    Then Search "ASN" and Validate Status "Receiving Started"
 	Then Inbound PutAway By Pallet in Putty
	Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "Receiving Verified"
    Then  Checkout Trailer