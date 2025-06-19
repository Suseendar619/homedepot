@DFWMS
Feature: VerifyIBShipmentsStoriesGreaterThan100Lpns2019

  Scenario: Verify_IB_Shipments GreaterThan100Lpns 2019 Dallas
    Given Login as WMUser
    Then  Create PO xml "VerifyIBShipment2019Dallas"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "VerifyIBShipment2019Dallas"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
	Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "InTransit"
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "VerifyIBShipment2019DallasInbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "Zone"
    Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "In Transit"
 	Then  Create iLPNs
 	Then  Get "ItemName" from xml "VerifyIBShipment2019Dallas"
 	Then  Inbound Receive in Putty "VerifyIBShipment2019Dallas" 
	Then  Open "ASNs" screen under "Distribution"
    Then  Search "ASN" and Validate Status "Receiving Started"
    Then  Validate iLPN's
  	Then  Checkout Trailer