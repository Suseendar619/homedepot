@DFWMS
Feature: BVR_PCD_Cancel_Olpn
  
Scenario: OB BVR Baltimore PackCubeDirected Cancel Olpn
	Given Login as WMUser
	Then Create DO xml "BVR_Dallas_OLPN"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Dallas_OLPN"
    Then Validate status for "Distribution Orders" - "Released" 
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Dallas_MISP"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Dallas_OLPN"
    Then PackCubeDir in Putty for "BVRDallas_PckCubeDir_Cancel_Olpn"   