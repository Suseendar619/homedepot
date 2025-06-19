@DFWMS
Feature: BVR_Multi_Stop
  
Scenario: OB BVR Dallas Multi Stop
	Given Login as WMUser
	Then Create DO xml "BVR_MutliStop"
	Then Create DO xml "BVR_Dallas_MutliStop"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_MutliStop"
	Then Post PO xml "BVR_Dallas_MutliStop"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "UndoWaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed" 
    Then Get details "ItemName" from xml for "BVR_MutliStop"
    Then Get details "ItemName" from xml for "BVR_Dallas_MutliStop"
    Then Outbound Picking in Putty "Multi_Olpn"
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Mutli_Stop"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_MS"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Mutli_Stop"
	Then Outbound LoadTrailer in Putty for "DallasZone"
    Then Validate status for LM "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    ##Then CloseTrailer validation
    Then Validate status for LM "oLPNs" - "Shipped"