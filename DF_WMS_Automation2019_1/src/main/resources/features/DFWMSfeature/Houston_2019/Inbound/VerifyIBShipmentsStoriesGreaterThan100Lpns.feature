@DFWMS
Feature: VerifyIBShipmentsStoriesGreaterThan100Lpns

  Scenario: Verify_IB_Shipments
    Given Login as WMUser
    Then  Create PO xml "VerifyIBShipment"
    Then  Open "Post Message" screen under "Integration"
    Then  Post PO xml "VerifyIBShipment"
    Then  Open "Purchase Orders" screen under "Distribution"
    Then  Search "Purchase Order" and Validate Status "Created"
    Then  Open "Create ASN from PO" screen under "Distribution"
    Then  Map ASN and PO
    Then  Open "Assign ASN to Shipment" screen under "Distribution"
    Then  Map ASN and Shipment
    Then  Open "Schedule Appointment" screen under "Distribution"
    Then  Schedule Appointment "VerifyIBShipmentInbound"
    Then  Open "Check-In" screen under "Distribution"
    Then  IB CheckIN "HoustonZone"
    Then  Create ILPNs
    Then  VerifyIBShipment Receive in Putty
    Then  Open "iLPNs" screen under "Distribution"
    Then  Validate iLPN's
    Then  Inbound Verify in Putty "VerifyIBShipment"
    Then  Open "PIX Transactions" screen under "Distribution"
    Then  Validate PIX Transactions
    Then  Checkout Trailer