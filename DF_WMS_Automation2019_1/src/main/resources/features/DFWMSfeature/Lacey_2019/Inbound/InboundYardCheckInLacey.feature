@DFWMS
Feature: InboundYardCheckIn
  
Scenario: Inbound Yard CheckIn Lacey
	Given Login as WMUser
	Then  Create PO xml "BK1ActiveLacey"
	Then  Open "Post Message" screen under "Integration"
	Then  Post PO xml "Zone"
	Then  Open "Create ASN from PO" screen under "Distribution"
	Then  Map ASN and PO
	Then  Open "Assign ASN to Shipment" screen under "Distribution"
	Then  Map ASN and Shipment
	Then  Get "ItemName" from xml "Zone"
	Then  Open "Item Facilities" screen under "Configuration"
	Then  Get putaway type
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "Yard"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "Yard"
	Then  Validate yard rules for "InboundLacey"
	Then  Open "Yard Tasks" screen under "Distribution"
	Then  Add Yard Task
	Then  Open "Yard Tasks" screen under "Distribution"
	Then  Validate Task status "Released"
	Then  Complete Yard Task
	Then  Open "Yard Tasks" screen under "Distribution"
	Then  Validate Task status "Complete"