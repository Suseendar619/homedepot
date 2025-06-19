package com.homer.po.DFWMS;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSoLPNsPageStepDeftn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

import org.openqa.selenium.support.ui.Select;



public class DFWMSShpmtcreationPageObject extends PageBase {

	public DFWMSShpmtcreationPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	public String Generated_Shipment_ID;
	static final By linkShipment = By.linkText("Shipments");
	public static final By AddBlankLTLShipment=By.id("dataForm:Shp_List_AddBlankLTLShipment_button");
	public static final By AddLTLShipment_Mode=By.id("dataForm:HMDPAddLTLShipment_SOM_Mode");
	public static final By AddLTLShipment_ShipVia=By.id("dataForm:HMDPAddLTLShipment_SOM_ShipVia");
	static final By ShpcrtSavebutton = By.xpath("//*[@id='dataForm:HMDPAddLTLShipmentSaveButton']");
	JDBC_Connection jd = new JDBC_Connection(ic);
	public String getRTEValue(String oLPN9) throws Exception {

		String sRTEValuefromDB = "";
		jd.dbDFWMSMapping();
		sRTEValuefromDB = jd.str_Database_Connection("Select RTE_TO from orders where TC_ORDER_ID = (Select TC_ORDER_ID from LPN where TC_LPN_ID = '" + oLPN9 + "')");
		return (sRTEValuefromDB);

	}
	
	public String shpmt_creation_validation(ArrayList oLPN1) throws Throwable{
		int iArraycount3 = oLPN1.size();
		int iFieldVal1=0;
		while (iArraycount3!=0){
		String sOLPN1 = oLPN1.get(iFieldVal1).toString();
		//String sDckDR = sDockdr.get(iFieldVal1).toString();
		System.out.println("oLPN value in Load switch " +sOLPN1);
	//	System.out.println("Dock Door value in Load switch " +sDckDR);
	//	int loopqty4 = Integer.parseInt(iArraycount3);
		
			System.out.println("Array count in Packing_station_validation5 " +iArraycount3);
		String sRouteto = getRTEValue(sOLPN1);
		System.out.println("Route to value from DB "+sRouteto);
		iArraycount3--;
		iFieldVal1++;
		
		if (!(sRouteto.equals("2")|| sRouteto.equals("3")|| sRouteto.equals("18")))
				{
					
			break;
		} else if ((sRouteto.equals("2")|| sRouteto.equals("3")|| sRouteto.equals("18")))
		{
			shpmt_creation();
			Thread.sleep(2000);
			break;
		}
	
		}
		return Generated_Shipment_ID;
		
		
	}
	
	
	public  String shpmt_creation()throws Throwable{
		
		try{
			System.out.println("inside try function");
			if (wh.isElementPresent(AddBlankLTLShipment, 2)) {
				wh.clickElement(AddBlankLTLShipment);
				report.addReportStep("Shipment Creation", "Successfully added LTL Shipment screen "+ AddBlankLTLShipment, StepResult.PASS);
			}
			if (wh.isElementPresent(AddLTLShipment_Mode, 2)) {
				Thread.sleep(2000);
		           wh.selectText(AddLTLShipment_Mode,"LTL");
		       }
			if (wh.isElementPresent(AddLTLShipment_ShipVia, 2)) {
				Thread.sleep(2000);
				String str = driver.findElement(By.id("dataForm:HMDPAddLTLShipment_SOM_ShipVia")).getText();
				wh.selectText(AddLTLShipment_ShipVia,"EXLA - ESTES");
				Thread.sleep(2000);}
				Generated_Shipment_ID = driver.findElement(By.id("dataForm:HMDPAddLTLShipment_SOM_Shipment")).getText();
				System.out.println("shipment id in po " +Generated_Shipment_ID);
			if (wh.isElementPresent(ShpcrtSavebutton, 2)) {
				Thread.sleep(2000);
				wh.clickElement(ShpcrtSavebutton);
				System.out.println("After LTL Shipment save button Click");
				Thread.sleep(2000);
				//wh.sendKeys(EnterTote, "T1120201805");
				report.addReportStep("Shipment Creation", "Successfully Created Shipment Save button "+ Generated_Shipment_ID, StepResult.PASS);
			}
			else{
				report.addReportStep("Shipment Creation", "Shipment Creation Failed ", StepResult.PASS);
			}
			
		}
		catch (Exception e) {
			System.out.println("Packing station Catch");
			report.addReportStep("Navigate to Shipment screen","Unable to navigate to Shipment screen - " + e.getMessage(),StepResult.WARNING);
			rc.throwTCTerminationException();
		
	}
 
		return Generated_Shipment_ID;
		
	}

}
