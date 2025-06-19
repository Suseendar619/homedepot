@DFWMS
Feature: BVR Flow MISP Anchor OLPN
  
Scenario: Outbound BVR Flow Houston MultiItem SinglePallet With Anchor OLPN
	Given Login as WMUser
	Then Create DO xml "BVR_Houston_MISP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Houston_MISP"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas_MISP"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Houston_MISP"
    Then Outbound PickToLabel in Putty for "BVR_Dallas_MISP"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Outbound Anchor Opn MOD "Pallet"
	Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Dallas_MISP"
	Then Open "Ship Confirm Rules" screen under "Distribution"
	Then Ship Confirm Rules for Anchor "EZShip_Houston"
	Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"