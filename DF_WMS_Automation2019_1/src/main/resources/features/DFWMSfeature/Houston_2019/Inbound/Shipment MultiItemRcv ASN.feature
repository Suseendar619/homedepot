@DFWMS
Feature: Shipment MultiItemRcv ASN

  Scenario: Shipment Multi ItemRcv ASN flow
    Given Login as WMUser
    Then  Create PO xml "ShipmentMultiItemRcvASN"
    Then  Get "ItemName" from xml "ShipmentMultiItemRcvASN"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "ShipmentMultiItemRcvASN"
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
    Then  IB CheckIN "ShipmentMultiItemRcvASN"
 	Then  Inbound Receive in Putty "ShipmentMultiItemRcvASNHoutson"
	Then  Validate status for "ASN" - "Receiving Started"
	Then  Validate iLPN's
	Then  Inbound Putaway in Putty "ShipmentMultiItemRcvASN"
	Then  Checkout Trailer