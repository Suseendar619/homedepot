package com.homer.po.DFWMS;

import java.io.IOException;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSIBAssingShipmentPageObject extends PageBase {

	public String shipmentId;
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSIBAssingShipmentPageObject(InstanceContainer ic) {
		super(ic);
	}

	public String ValidateASNShipmentMap(String asn) {

		try{

			wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);
			Thread.sleep(2000);
			
			if(wh.isElementPresent(ASNPONbrTxt)){
				wh.sendKeys(ASNPONbrTxt, asn);
				wh.waitForPageLoaded();
			}
			
			if(wh.isElementPresent(ASNPONbrApply)){
				wh.clickElement(ASNPONbrApply);
				wh.waitForPageLoaded();
			}
			if(wh.isElementPresent(GenerateShip)){
				wh.clickElement(GenerateShip);
				wh.waitForPageLoaded();
			}
			
			if (wh.isElementPresent(Generate, 5)) {
				wh.waitForPageLoaded();
				wh.clickElement(Generate);
				wh.waitForPageLoaded();
				Thread.sleep(2000);
				shipmentId="";
				shipmentId = wh.getAttribute(ShipmentID, "value");
				String dbShipId = "";
				//validate shipment id
				dbShipId = getShipment(shipmentId);
				
				while(dbShipId != null){
					wh.clickElement(Generate);
					shipmentId="";
					shipmentId = wh.getAttribute(ShipmentID, "value");
					dbShipId = getShipment(shipmentId);
				}
				
				System.out.println("Shipment no generated " +shipmentId);
				Thread.sleep(2000);
				report.addReportStepWithScreenshots("Shipment Number","Successfully Generated the Shipment no. Shipment number is "+ shipmentId , StepResult.PASS);
			} else {
				throw new Exception("Shipment Number not found."	+ "XPath used is: " + Generate.toString());
			}
			if(wh.isElementPresent(genok)){
				wh.clickElement(genok);
			}
			
		} catch (Exception e){
			report.addReportStep("Validate Shipment ASN Mapping ","Unable to Map ASN to Shipment " + asn + " status "+ e.getMessage(), StepResult.FAIL);
		}
		return shipmentId;
	}

	private String getShipment(String shipmentId) throws Exception {
		jd.dbDFWMSMapping();
		String dbShipId = jd.str_Database_Connection("select * from shipment where tc_shipment_id = '"+shipmentId+"'");
		return dbShipId;
	}

	public void ShipmentASNMapping(String shipmentId) {
		
		try{

			if(wh.isElementPresent(ShipmentNbrTxt)){
				Thread.sleep(1000);
				wh.sendKeys(ShipmentNbrTxt, shipmentId);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(ShipmentNbrApply, 1000)){
				Thread.sleep(1000);
				wh.clickElement(ShipmentNbrApply);
			}

			if(wh.isElementPresent(ASNCheck)){
				Thread.sleep(1000);
				wh.clickElement(ASNCheck);
			}
			if(wh.isElementPresent(ShipmentCheck)){
				Thread.sleep(1000);
				wh.clickElement(ShipmentCheck);
			}

			if(wh.isElementPresent(ASNtoShipment)){
				Thread.sleep(1000);
				wh.clickElement(ASNtoShipment);
			}

			if(wh.isElementPresent(ShipmentASNSave, 1000)){
				wh.waitForPageLoaded();
				Thread.sleep(1000);
				wh.clickElement(ShipmentASNSave);
			}

			closebtn();
		}catch (Exception e){
			report.addReportStep("Validate ASN Shipment Mapping ","Unable to Map Shipment to ASN " + shipmentId + " status "+ e.getMessage(), StepResult.FAIL);
		}
	}

}
