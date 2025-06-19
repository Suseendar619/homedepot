@DFWMS
Feature: EZ_Shipment
  
Scenario: EZ Shipment Flow Miami 
	Given Login as WMUser
	Then Create DO xml "EZShipment_Miami"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "EZShipment_Miami"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "EZShipment_Tampa"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Get details "ItemName" from xml for "EZShipment_Miami"
    Then Validate status for "oLPNs" - "Printed"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Weigh and Manifest oLPN" screen under "Distribution"
    Then Weigh and Manifest oLPNS for "EZShipment_Baltimore"
    Then Validate status for "oLPNs" - "Weighed"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then AnchorInPutty for "EZShipment_Tracey"
    Then Validate status for "Distribution Orders" - "Staged"
    Then Open "Ship Confirm Rules" screen under "Distribution"
    Then Ship Confirm Rules for "EZShipment_Tracey"
    Then Validate status for "oLPNs" - "Shipped"
    Then Validate status for "Distribution Orders" - "Shipped"    