@DFWMS
Feature: Sample feature

  Scenario: Sample DO
    Given Login as WMUser
    Then Open "Post Message" screen under "Integration"
    ##Then Read excel and update xml for "BRT"
    Then Create and post xml for "UPS" for "1000" times