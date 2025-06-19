@DFWMS
Feature: Demo
  
Scenario: IBFlow Setup
	Given Login as WMUser
	Then Create PO xml
	Then Open "Post Message" screen under "Integration" Module
    Then Post PO xml
    Then Open "Purchase Orders" screen under "Distribution" Module
    Then Search PO and Validate PO Status "Created"
    Then Open "Create ASN from PO" screen under "Distribution" Module
    Then Map ASN and PO
    Then Open "ASNs" screen under "Distribution" Module
    Then Apply ASN
	Then Validate ASN_Status "InTransit"
    Then Open "Schedule Appointment" screen under "Distribution" Module
    Then Schedule Appointment
    Then Open "Check-In" screen under "Distribution" Module
   	Then IB CheckIN
   	Then Open "ASNs" screen under "Distribution" Module
    Then Apply ASN
    Then Validate Trailer Number
    Then Get "ItemName" from xml
    Then Inbound Receive in Putty
    Then Open "ASNs" screen under "Distribution" Module
    Then Apply ASN 
    Then Validate ASN_Status "Receiving Started" 
    Then Validate Total Received Items
    Then Open "iLPNs" screen under "Distribution" Module
    Then Validate iLPN's
    Then Inbound Verify in Putty
    Then Open "ASNs" screen under "Distribution" Module
    Then Apply ASN 
    Then Validate ASN_Status "Receiving Verified" 
    Then Open "PIX Transactions" screen under "Distribution" Module
    Then Validate PIX Transactions
    Then Inbound Putaway in Putty
    #Then Validate ILPN status and Qty  	