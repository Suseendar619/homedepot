@DFWMS
Feature: Sample feature

  Scenario: Multiple Olpn Validation LG
    Given Login as WMUser
	Then Open "Post Message" 2012 screen under "Integration"
    Then Create and post xml for 2012 "LG_New"
    Then Open "Run Waves" 2012 screen under "Distribution"
    Then Select Wave 2012 "Convey Flow" and Run
    Then Select "By Order" Define and Submit for 2012 "LG_New"
    Then Open "Waves" 2012 screen under "Distribution2012"
    ##Then Validate Wave Completion
    ##Then Validate Olpn Status "Consolidated"
    Then Open "MULTIS" 2012 screen under "Distribution"
    Then Enter Value and scan Olpn