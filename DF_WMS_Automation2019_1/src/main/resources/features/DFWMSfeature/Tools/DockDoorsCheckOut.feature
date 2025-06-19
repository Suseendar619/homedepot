@DFWMS
Feature: DockDoorsCheckOut
  
Scenario: Dock Doors CheckOut
	Given Login as WMUser
	Then Open "Check-Out" screen under "Distribution"
	Then Get dock door details to checkout