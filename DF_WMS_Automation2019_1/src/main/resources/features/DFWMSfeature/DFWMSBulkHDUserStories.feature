@DFWMS
Feature: User stories for DF WMS

Background:
#Given Login as WMUser
    
Scenario: Post Bulk HG DOR Orders
    Given Login as WMUser
    Given WMS home page
    #And Post dynamically generated XML "1" times order type "Parcel6760Null"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2Parcel6760Null"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "Parcel6760NullHAZ"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2Parcel6760NullHAZ"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "2Parcel67601Null1HAZ"
    #Then Verify the post response
    #And Post dynamically generated XML "1" times order type "Parcel6760CP2HRP"
    #Then Verify the post response
    And Logout
    
Scenario: Bulk HG DF WMS Hourly Standard Flow
    Given Post dynamically generated XML "1" times order type "Parcel6760CP2HRP"
    Then Login as WMUser
    Then WMS home page
    Then Verify the post response
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Input and Apply DO
    Then Validate DO Status "Released"
    Then Open "Run Waves" screen under "Distribution" Module
    Then Select Wave "Hourly Standard" and Run
    Then Select Rule "Single Order" Define and Submit
    Then Open "Waves" screen under "Distribution" Module
    Then Input and Apply Wave
    Then Validate Wave Completion
    Then Open Wave Tasks
    Then Open "Distribution Orders" screen under "Distribution" Module
    Then Validate DO Status "DC Allocated"
    Then Open "oLPNs" screen under "Distribution" Module
    Then Input and Apply oLPNs
    Then Validate oLPN Status "Not Printed"
    Then Clear Array List
    And Logout 
