@DFWMS
Feature: BVR_PCD_Cancel_Olpn
  
Scenario: OB BVR Houston Cancel Olpn
	Given Login as WMUser
	Then Create DO xml "BVR_Houston_Cancel_olpn"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BVR_Houston_Cancel_olpn"
    Then Validate status for "Distribution Orders" - "Released"   
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "BVR_Houston_Cancel_olpn"
    Then PackCubeDir in Putty for "BVRDallas_PckCubeDir_Cancel_Olpn"
    Then Validate status for LM "Canceled"
       