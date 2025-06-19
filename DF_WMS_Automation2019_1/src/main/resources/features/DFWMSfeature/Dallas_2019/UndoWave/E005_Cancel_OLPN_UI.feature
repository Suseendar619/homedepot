@DFWMS
Feature: UndoDOPacked
   
   
Scenario: Cancel oLPN from oLPNs UI E005
	Given Login as WMUser
	Then Create DO xml "FGND_Houston"
    Then Open "Post Message" screen under "Integration"
	Then Post PO xml "FGND_Houston"
    Then Validate status for "Distribution Orders" - "Released"   
    Then Open "Run Waves" screen under "Distribution"    
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BVR_Houston"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion    
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Validate status for "oLPNs" - "Printed"
    Then Get details "ItemName" from xml for "FGND_Houston"
    Then Open "oLPNs" screen under "Distribution"
    Then Cancel oLPN from oLPNs UI "Cancel"
    Then Validate status for LM "Canceled"
    