@DFWMS
Feature: Checkout
  
Scenario: Checkout
	Given Login as WMUser
	Then Open "Check-Out" screen under "Distribution"
	Then Checkout