@DFWMS
Feature: WCS

  Scenario: WCS Sample
    Given Login as WMUser
	Then Open "Post Message" 2012 screen under "Integration"
    Then Create and post xml for 2012 "BVR_LG"
    Then Open "Run Waves" 2012 screen under "Distribution"
    Then Select Wave 2012 "Standard Wave" and Run
    ##Then Select Wave 2012 "Convey Flow" and Run
    Then Select "By Order" Define and Submit for 2012 "LG_New"
    Then Open "Waves" 2012 screen under "Distribution2012"
    Then Validate Wave Completion
    Then Release Task From Wave "LG_2012"
    Then Open "RF Menu" 2012 screen under "Distribution"
    Then Task Picking in RF "WCS"
    Then Open Remote Server "WCS"
    Then Open "MULTIS Packing Station (Supervisor)" 2012 screen under "Distribution"
    Then Multis Packing in RF "WCS"
    ##Then Open WCS Server in Remote Desktop "WCS"