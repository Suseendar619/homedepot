@DFWMS
Feature: Bulk Orders 

  Scenario: Bulk Orders DO
    Given Login as WMUser
    Then Open "Post Message" screen under "Integration"
    Then Read excel and update xml for "Houston"
    #Then Create and post xml for "LTLOutboundDallas" for "50" times