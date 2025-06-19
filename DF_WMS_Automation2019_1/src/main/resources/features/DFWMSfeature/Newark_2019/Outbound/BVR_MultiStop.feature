@DFWMS
Feature: BVR_Multi_Stop
  
Scenario: OB BVR Newark Multi Stop
	Given Login as WMUser
	Then Create DO xml "BVR_MutliStop"
	Then Create DO xml "BVR_Dallas_MutliStop"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_MutliStop"
	Then Post PO xml "BVR_Dallas_MutliStop"
    Then Validate status for "Distribution Orders" - "Released"  
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Mutli_Stop"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_MutliStop"
    Then Outbound PickToLabel in Putty for "BVR_Mutli_Stop"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Mutli_Stop"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "DallasZoneOutboundMS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Mutli_Stop"
	Then Outbound LoadTrailer in Putty for "DallasZone"
	Then Validate status for "Distribution Orders" - "Loaded"
	Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "BaltimoreZone"
    Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
	Then Validate status for "oLPNs" - "Shipped"