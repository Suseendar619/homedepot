@DFWMS
Feature: VerifyIBShipmentsGreaterThan100Lpns_Boston

  Scenario: Verify_IB_Shipments GreaterThan100Lpns Boston
    Given Login as WMUser
    Then  Create PO xml "VerifyIBShipment2019_Boston"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "VerifyIBShipment2019_Boston"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
    Then  Validate status for "ASN" - "InTransit"
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "VerifyIBShipment2019Inbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "HoustonZone"
 	Then  Get "ItemName" from xml "VerifyIBShipment2019_Boston"
 	Then  Inbound Receive in Putty "VerifyIBShipment2019" 
    Then  Validate status for "ASN" - "Receiving Started"
    Then  Validate iLPN's
 	Then  Inbound Verify in Putty "VerifyIBShipment2019"
    Then  Validate status for "ASN" - "Receiving Verified"
  	Then  Checkout Trailer