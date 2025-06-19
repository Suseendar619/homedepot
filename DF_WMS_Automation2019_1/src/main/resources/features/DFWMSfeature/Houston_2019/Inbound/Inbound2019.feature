@DFWMS
Feature: Inbound2019
  
Scenario: IBFlow 2019 Setup
	Given Login as WMUser
	Then  Open "Schedule Appointment" screen under "Distribution"
	Then  Schedule Appointment "Inbound"
	Then  Open "Check-In" screen under "Distribution"
	Then  IB CheckIN "HoustonZone"
	Then  Get "ItemName" from xml "Inbound"
	Then  Inbound Receive in Putty "Inbound"
	Then  Open "iLPNs" screen under "Distribution"
	Then  Validate iLPN's
	Then Inbound Verify in Putty "Inbound"
	Then Open "PIX Transactions" screen under "Distribution"
	Then Validate PIX Transactions