@DFWMS
Feature: WES
  
Scenario: WES flow with wave using Intelligrated
	Then Login as WMUser
	Then Create DO xml "UPS_Dallas_WES"
    Then Open "Post Message" screen under "Integration"
    Then Post PO xml "UPS_Dallas_WES"
	Then Validate status for "Distribution Orders" - "Released"
	Then Open "Distribution Orders" screen under "Distribution"
    Then Input and Apply DOId for "WaveDO"
    Then Run Wave for "WaveDO"
    Then Select Wave "Conveyable - Pick Modules (Packsize)" and Run
    Then Click Submit and get Wave number
	Then Open "Waves" screen under "Distribution"
	Then Input WaveNumber and Apply 
	Then Validate Wave Completion    
	Then Validate status for "Distribution Orders" - "DC Allocated"
	Then Validate status for "oLPNs" - "Printed"
	Then Get details "ItemName" from xml for "UPS_Dallas_WES"
	Then Validate status for CL_Message "WaveData"
	Then Login as WESUser
	Then Search OLPN number in Container and Tasks "CREATED"
	Then Open "RF MENU" screen under "Distribution"
	Then Picking with WM "WES"
	Then Validate status for "oLPNs" - "Weighed"
	Then Validate status for CL_Message "WES"
	Then Search OLPN number in Container and Tasks "Routes_Container"
	Then Search OLPN number in Container and Tasks "Routing"
	Then Open RouterIntlligrated and send request "Pick_Pack"
	Then Validate status for CL_Message "Diverted"
	Then Search OLPN number in Container and Tasks "SHIPPED"
	Then Validate status for "oLPNs" - "Weighed"
	Then Open "Schedule Appointment" screen under "Distribution"
	Then Schedule Appointment "FXHD_Dallas"
	Then Open "Check-In" screen under "Distribution"
	Then IB CheckIN "FXHD_Dallas"
	Then Outbound LoadParcel in Putty for "FXHD_WES"
    Then Validate status for "Distribution Orders" - "Shipped"    
    Then Validate status for "oLPNs" - "Shipped"
    ##Then Login as PacksizeDallasUser
    ##Then Search Waves in Packsize
     
    