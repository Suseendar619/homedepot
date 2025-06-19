@DFWMS
Feature: ShipmentMIRcvDtlASN

 Scenario: Shipment MI RcvDtl ASN flow Baltimore
 	Given Login as WMUser
	Then  Create PO xml "ShipmentMultiItemRcvASNBaltimore"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentMultiItemRcvASNBaltimore"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
    Then  Validate status for "ASN" - "InTransit"
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
    Then  Open "ASNs" screen under "Distribution"
	Then  Validate ShipmentId in ASN
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "ShipmentMultiItemRcvASNInbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "HoustonZone"
    Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "InTransit"
 	Then  Create iLPNs
 	Then  Inbound Receive in Putty "ShipmentMultiItemRcvASN"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "ShipmentMultiItemRcvASN"
	Then  Checkout Trailer