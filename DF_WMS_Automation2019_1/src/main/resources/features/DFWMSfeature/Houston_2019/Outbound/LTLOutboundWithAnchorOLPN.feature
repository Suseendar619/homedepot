@DFWMS
Feature: LTLOutboundAnchorMOD
  
Scenario: LTL Outbound Flow With Anchor OLPN
    Then Login as WMUser
    Then Create DO xml "LTLOutboundHouston"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "LTLOutboundHouston"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "LTLOutboundHouston"
    Then Outbound PickToLabel in Putty for "NonCon"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Outbound Anchor Opn MOD "OLPN"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "LTL_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "LTLOutboundHouston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "LTLOutboundHouston"
	Then Open "Ship Confirm Rules" screen under "Distribution"
	Then Ship Confirm Rules for Anchor "EZShip_Houston"
	Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"