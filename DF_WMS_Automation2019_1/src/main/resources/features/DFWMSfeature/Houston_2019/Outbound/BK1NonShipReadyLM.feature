@DFWMS
Feature: BK1NonShipReady
  
Scenario: BK1 Non Ship Ready LM
	Given Login as WMUser
	Then Open "Clock In Clock Out" screen under "Labor Management"
    Then Clock in LM user
	Then Create DO xml "BK1NonShipReadyLM"
	Then Open "Post Message" screen under "Integration"
	Then Post PO xml "BK1NonShipReadyLM"
    Then Validate status for "Distribution Orders" - "Released"
    Then Open "Run Waves" screen under "Distribution"
    Then Select Wave "Automation - BK1 - Non Ship Ready" and Run
    Then Select "Bulk Non Ship Ready" Define and Submit for "BK1NonShipReady"
    Then Open "Waves" screen under "Distribution"
    Then Input WaveNumber and Apply 
    Then Validate Wave Completion
    Then Validate status for "Distribution Orders" - "DC Allocated"
    Then Get details "ItemName" from xml for "BK1NonShipReadyLM"
    Then Validate status for "oLPNs" - "Printed"
    Then Outbound Picking in Putty
    Then Validate status for "Distribution Orders" - "Weighed"
    Then Validate status for "oLPNs" - "Weighed"
    Then Open "Clock In Clock Out" screen under "Labor Management"
    Then Clock Out LM user
    Then Validate status for LM "Status" - "70"
    Then Validate status for LM "Act Name" - "PICK TO LABEL BULK TEAM"
    Then Validate status for LM "Compare table"
    Then Validate status for LM "TTPPL"
    Then Validate status for LM "TTLOC"
    Then Validate status for LM "TTCTN"
    Then Validate status for LM "PLTTP"
    