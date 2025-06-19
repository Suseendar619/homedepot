@DFWMS
Feature: LaneValidation
  
Scenario: Lane Validation
	Given Login as WMUser
	Then Open "Lane" screen under "Contract Management"
	Then Setup Lane for "BVR"
	Then Setup Lane for "LTL"
	Then Setup Lane for "LTLHDU"
	Then Setup Lane for "HDUTL"