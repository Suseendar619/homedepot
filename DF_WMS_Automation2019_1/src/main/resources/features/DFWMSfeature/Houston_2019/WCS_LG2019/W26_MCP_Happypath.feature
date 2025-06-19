@DFWMS
Feature: W26_MCP_Happypath

  Scenario: W26 MCP Happy Path
    Given Login as WMUser
	Then Create DO xml "MCP"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "MCP"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Convey Flow" and Run
    ##Then Select Wave "Conveyable - Pick Modules" and Run
    Then Click Submit and get Wave number
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated" 
    ##Then Validate status for "oLPNs" - "Printed"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply
    Then Release Task From Wave "LG_2019"
    Then Get details "ItemName" from xml for "MCP"
    Then Open "RF Menu" screen under "Distribution"
    Then Task Picking in RF "WCS_2019"
    Then Store procedure "CONT_STAT_DIVERT"
    Then Store procedure "FULLPICK"
    Then Store procedure "CONT_STAT_PUSH"
    ##Then Store procedure "LIGHT"
    Then Open "Pack Station Multis" screen under "Distribution"
    Then Multis Packing in RF "WCS_2019"
    Then Validate status for "Distribution Orders" - "Packed"
    Then Validate status for "oLPNs" - "Packed"
    Then Validate status for CL_Message "TOTEDIRECTIVE"
    Then Validate status for CL_Message "OLPNDIRECTIVE"
    Then Validate status for CL_Message "LIGHTCONTROL"
    Then Open "Shipments" screen under "Distribution"
	Then Generate Shipment for "BVR_Houston"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "BVR_Houston"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "BVR_Houston"
	Then Outbound LoadTrailer in Putty for "Zone"
	Then Validate status for "Distribution Orders" - "Loaded"
    Then Validate status for "oLPNs" - "Loaded on Truck"
    Then Outbound CloseTrailer in Putty for "DallasZone"
    ##Then CloseTrailer validation
    Then Validate status for "Distribution Orders" - "Shipped"
    Then Validate status for "oLPNs" - "Shipped"
    