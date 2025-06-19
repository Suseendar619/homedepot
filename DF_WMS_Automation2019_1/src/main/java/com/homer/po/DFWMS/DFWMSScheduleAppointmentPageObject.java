package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSLTLOutboundFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSScheduleAppointmentPageObject extends PageBase{

	public static String AppointmentID,Strtrailer,Fname,Lname,YardAppType,LicenseNumber,carrierCode,
	Statelist="GA";
	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSScheduleAppointmentPageObject(InstanceContainer ic) {
		super(ic);
	}


	public String createAppointmentID(String appointmenttype, String equipmentcode, String aSNID,
			String screen, String outboundShipment, String shipmentIdInbound) throws InterruptedException, Exception {

		Thread.sleep(5000);
		wh.clickElement(Maximize);
		//Thread.sleep(1000);
		driver.switchTo().frame(0);
		Thread.sleep(1000);

		try{
			DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
			jd.envrnment = s.readProp();	
			String HomeDomicileVal = DFWMSLoginPageObject.dc;
			//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
			//				HomeDomicileVal = "5829";
			//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
			//				HomeDomicileVal = "5832";
			//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
			//				HomeDomicileVal = "5823";
			//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
			//				HomeDomicileVal = "5831";
			//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")){
			//				HomeDomicileVal = "5855";
			//			}else if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
			//				HomeDomicileVal = "5854";
			//			}else if(jd.envrnment.equalsIgnoreCase("Tracey_2019")){
			//				HomeDomicileVal = "5857";
			//			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
			//				HomeDomicileVal = "5841";
			//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
			//				HomeDomicileVal = "5860";
			//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
			//				HomeDomicileVal = "5523";
			//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
			//				HomeDomicileVal = "6705";
			//			}
			//appointmenttype
			String AppType = "";

			if(screen.equalsIgnoreCase("Yard") 
					|| screen.contains("Outbound_Yard")){
				if(screen.equalsIgnoreCase("Yard")){
					YardAppType = "Drop Unload";
				}else if(screen.contains("Outbound_Yard")){
					YardAppType = "Drop Empty";
				}
				if (wh.isElementPresent(listApptype, 2)) {
					Thread.sleep(1000);
					wh.selectValue(listApptype, YardAppType);
				}
			}else{
				if(screen.contains("Inbound")
						|| screen.equalsIgnoreCase("Shotgun")
						|| screen.equalsIgnoreCase("MultiItemRcvASNBaltimore")){
					AppType = "Live Unload";
				}else if(screen.contains("Outbound")
						||screen.contains("HDU_TL_Dallas")
						||screen.contains("LTL_HDU_Baltimore")){
					AppType = appointmenttype;
				}else{
					AppType = appointmenttype;
				}
				if (wh.isElementPresent(listApptype, 3)) {
					Thread.sleep(1000);
					wh.selectValue(listApptype, AppType);
				}else {
					Thread.sleep(1000);
					wh.selectValue(listApptype, AppType);
				}

			}
			//equipment code
			//Thread.sleep(2000);
			if (wh.isElementPresent(equipCode, 2)) {
				Thread.sleep(1000);
				wh.sendKeys(equipCode, equipmentcode);
			}

			//date
			if (wh.isElementPresent(inputApptdate, 2)) {
				Thread.sleep(1000);
				wh.sendKeydateVal(inputApptdate);
			}

			/*	//asn
			if(screen.equalsIgnoreCase("Inbound") 
					|| screen.equalsIgnoreCase("MultiItemRcvASN")
					|| screen.equalsIgnoreCase("MultiItemRcvASNBaltimore")
					//|| screen.equalsIgnoreCase("LaceyZoneOutbound")
					){		*/	

			String shipId = "";


			if(DFWMSLTLOutboundFlowStepDefn.shipment.equalsIgnoreCase("Inbound")){
				if(screen.equalsIgnoreCase("UPS_Houston")||screen.equalsIgnoreCase("FGND_Houston")||screen.equalsIgnoreCase("FXHD_Houston")||screen.equalsIgnoreCase("UPSDallas")||screen.equalsIgnoreCase("FXHD_Dallas")||screen.equalsIgnoreCase("FGND_Dallas")) {

					shipId = "";
					aSNID = "";
				}else {
					shipId = shipmentIdInbound;
				}

			}else if(DFWMSLTLOutboundFlowStepDefn.shipment.equalsIgnoreCase("Outbound")){
				if(screen.equalsIgnoreCase("UPS_Houston")||screen.equalsIgnoreCase("FGND_Houston")||screen.equalsIgnoreCase("FXHD_Houston")||screen.equalsIgnoreCase("UPSDallas")||screen.equalsIgnoreCase("FXHD_Dallas")||screen.equalsIgnoreCase("FGND_Dallas")) {

					shipId = "";
					aSNID = "";
				}else {
					shipId = outboundShipment;
				}


			}


			if(aSNID != null || shipId != null){
				if(!shipId.isEmpty()){
					if (wh.isElementPresent(inputShipment, 5)) {
						Thread.sleep(1000);
						wh.sendKeys(inputShipment, shipId);
					}
				}else if(aSNID != null){
					if (wh.isElementPresent(inputASN, 2)) {
						Thread.sleep(1000);
						wh.sendKeys(inputASN, aSNID);
					}
				}
			}

			if(wh.isElementPresent(checkBox, 2)){
				Thread.sleep(2000);
				wh.clickElement(checkBox);
			}

			Thread.sleep(2000);
			//save
			if (wh.isElementPresent(btnSave, 2)) {
				Thread.sleep(1000);
				wh.clickElement(btnSave);
			}

			if(wh.isElementPresent(AptmtError, 5) && wh.isElementPresent(ErrorMsg, 5)){
				String msg = wh.getText(ErrorMsg);
				if(msg.contains(shipId)){
					report.addReportStep("Schedule Appointment","Shipment already exists" ,StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}
			Thread.sleep(3000);
			if (wh.isElementPresent(eleAppointmentID, 2)){
				AppointmentID=wh.getText(eleAppointmentID);	
				System.out.println("Appointment Id : " + AppointmentID);
			}
			report.addReportStepWithScreenshots("Schedule Appointment", "Created appointment and saved: "+AppointmentID, StepResult.PASS);
			//fill in details
			if(wh.isElementPresent(AppointmentCheckBox, 3)){
				wh.clickElement(AppointmentCheckBox);
				Thread.sleep(1000);
			}

			if(wh.isElementPresent(AppointmentEdit, 3)){
				Thread.sleep(1000);
				wh.clickElement(AppointmentEdit);
				Thread.sleep(2000);
				wh.waitForPageLoaded();
			}

			if(wh.isElementPresent(AdditionalDetails, 15)){
				Thread.sleep(1000);
				wh.clickElement(AdditionalDetails);
				Thread.sleep(2000);
			}

			String dispType = "";
			if(screen.contains("Yard")){
				if(screen.contains("LTL")){
					dispType = "LTL_TL_Parcel";
					DFWMSDOObject.DispostionType = dispType;
				}else if(screen.equalsIgnoreCase("Outbound_Yard_BVR_Lacey")){
					dispType = "HDU_BVR";
					DFWMSDOObject.DispostionType = dispType;
				}else if(screen.equalsIgnoreCase("Outbound_Yard")){
					
					jd.dbDFWMSMapping();
					if(jd.envrnment.equalsIgnoreCase("HGT_2019")) {

						dispType = "BK1";
					}else if(jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
						
						dispType = "BK5";

					}else {
						dispType = "BK1_SR1";

					}
					DFWMSDOObject.DispostionType = dispType;
				}else{
					dispType = DFWMSDOObject.DispostionType;
				}
				System.out.println("Disp type " + dispType);
				if(wh.isElementPresent(TrailerDSPType, 5)){
					Thread.sleep(1000);
					wh.selectText(TrailerDSPType, dispType);
				}
			}
			if(wh.isElementPresent(NewTrailer, 15)){
				Thread.sleep(1000);
				wh.clickElement(NewTrailer);
				Thread.sleep(2000);
			}

			driver.switchTo().frame(1);

			if(screen.equalsIgnoreCase("Shotgun")){
				Strtrailer = DFWMSPOObject.SGStrtrailer;
			}else{
				Strtrailer = "T" +new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
			}

			CaarierCode = By.xpath("(.//input [contains(@id,'carrierCode')])");
			String carrierCd = wh.getText(CaarierCode);
			String carrierCodeVal = "";
			if(carrierCd.isEmpty()){
				if(screen.equalsIgnoreCase("UPSDallas")
						|| screen.equalsIgnoreCase("UPS_Houston")
						|| screen.equalsIgnoreCase("BVR_MS") 
						|| screen.contains("Outbound_Yard")
						|| screen.equalsIgnoreCase("DallasZoneOutboundMS")
						|| screen.equalsIgnoreCase("AtlantaZoneOutbound")
						|| screen.equalsIgnoreCase("HDUTL_Lacey")
						|| screen.equalsIgnoreCase("BVR_Lacey")
						|| screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
						|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
						|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")
						|| screen.equalsIgnoreCase("SplitShipmentDallas")
						|| screen.equalsIgnoreCase("LTLOutboundDallas")
						|| screen.equalsIgnoreCase("LTLOutboundLacey")
						|| screen.equalsIgnoreCase("SplitShipmentLacey")
						|| screen.equalsIgnoreCase("Lacey_Multistop")
						|| screen.equalsIgnoreCase("BVR_MSDallas")
						|| screen.equalsIgnoreCase("DallasZoneOutbound")
						|| screen.equalsIgnoreCase("HDU_TL_Dallas")
						|| screen.equalsIgnoreCase("HDU_TL_Baltimore")
						|| screen.equalsIgnoreCase("LTLOutboundBaltimore")
						|| screen.equalsIgnoreCase("LTL_HDU_Baltimore")
						|| screen.equalsIgnoreCase("LTL HDU Lacey")
						|| screen.equalsIgnoreCase("LTLOutboundHouston")
						|| screen.equalsIgnoreCase("VASOutbound")
						|| screen.equalsIgnoreCase("LaceyZoneOutbound")
						||screen.equalsIgnoreCase("ZoneOutbound")
						||screen.equalsIgnoreCase("BVR_Houston")){

					// db query  to return carrier code
					if(screen.equalsIgnoreCase("UPSDallas")
							|| screen.equalsIgnoreCase("UPS_Houston")){
						carrierCodeVal = "UPS";
					}
					else if(shipId.isEmpty()){
						carrierCodeVal = "MDO";
					}
					else{
						jd.dbDFWMSMapping();
						carrierCodeVal = jd.str_Database_Connection("select assigned_carrier_code from shipment where tc_shipment_id = '"+shipId+"'");
						if(carrierCodeVal == null){
							report.addReportStep("Schedule Appointment", "Carrier Value not returned", StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}else if(screen.equalsIgnoreCase("HoustonZoneOutbound")|| screen.equalsIgnoreCase("HDU_TL_Dallas")||screen.equalsIgnoreCase("LTLOutboundHouston")){
					//carrierCodeVal = "EXLA";
					carrierCodeVal = "AACT";
				}
				else if(screen.equalsIgnoreCase("FGND_Dallas") 
						|| screen.equalsIgnoreCase("FXHD_Dallas")
						|| screen.equalsIgnoreCase("FGND_Lacey")
						|| screen.equalsIgnoreCase("FXHD_Houston")
						|| screen.equalsIgnoreCase("FGND_Houston")){
					carrierCodeVal = "FDX";
				}
//				else if(screen.equalsIgnoreCase("BVR_Houston")){
//
//					jd.dbDFWMSMapping();
//					if(jd.envrnment.equalsIgnoreCase("Houston_2019")||jd.envrnment.equalsIgnoreCase("HoustonQP_2019")) {
//
//						carrierCodeVal = "CRCR";
//
//					}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")) {
//
//						carrierCodeVal = "USXI";
//
//					}else if(jd.envrnment.equalsIgnoreCase("LG_2019")) {
//
//						carrierCodeVal = "LEGS";
//
//					}
//					else if(jd.envrnment.equalsIgnoreCase("Perris_2019")){
//
//						carrierCodeVal = "WEDV";
//
//					}
//					else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
//
//						carrierCodeVal = "HDRF";
//
//					}

				//}
				else {
					carrierCodeVal = "MDO";
				}

				if (wh.isElementPresent(CaarierCode, 2)) {
					carrierCode = carrierCodeVal;
					if(carrierCode.isEmpty()){
						report.addReportStep("Schedule Appointment", "Carrier Value not assigned", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
					System.out.println("carrier value " +carrierCodeVal);
					wh.sendKeys(CaarierCode, carrierCodeVal);
					Thread.sleep(2000);
				}
			}

			if (wh.isElementPresent(EquipmentID, 2)) {
				Thread.sleep(1000);
				wh.sendKeys(EquipmentID, Strtrailer);
				System.out.println(Strtrailer);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(HomeDomicile, 2)) {
				wh.sendKeys(HomeDomicile, HomeDomicileVal.trim());
				Thread.sleep(1000);
			}
			/*if(screen.equalsIgnoreCase("DallasZone") || screen.equalsIgnoreCase("VerifyIBShipment2019Dallas") || screen.equalsIgnoreCase("UPSDallas")
					|| screen.equalsIgnoreCase("FGND_Dallas") || screen.equalsIgnoreCase("FXHD_Dallas")
					|| screen.equalsIgnoreCase("BVR_ShotGun_Dallas") || screen.equalsIgnoreCase("Shotgun")
					|| screen.equalsIgnoreCase("LTLOutboundDallas") || screen.equalsIgnoreCase("BVR_MS")
					|| screen.equalsIgnoreCase("Yard")|| screen.equalsIgnoreCase("Outbound_Yard")){
				if (wh.isElementPresent(HomeDomicile, 2)) {
					wh.sendKeys(HomeDomicile, "5823");
					Thread.sleep(1000);
				}			
			}else if(screen.equalsIgnoreCase("Zone") 
					|| screen.equalsIgnoreCase("VerifyIBShipment2019") 
					|| screen.equalsIgnoreCase("IBZone")){
				if (wh.isElementPresent(HomeDomicile, 2)) {
					wh.sendKeys(HomeDomicile, "5831");
					Thread.sleep(1000);
				}
			}*/

			if (wh.isElementPresent(Region, 2)) {
				wh.sendKeys(Region, "Atlanta");
				Thread.sleep(1000);
			}

			if(wh.isElementPresent(TrailerSave, 3)){
				Thread.sleep(2000);
				wh.clickElement(TrailerSave);
				Thread.sleep(1000);
			}

			driver.switchTo().parentFrame();

			if(wh.isElementPresent(AppointmentSave,3)){
				Thread.sleep(1000);
				wh.clickElement(AppointmentSave);

				System.out.println("Schedule appointment Screen Saved Successfully");

				Thread.sleep(1000);

				if(wh.isElementPresent(SAErrorPopup, 5)){
					String errorMsg = wh.getText(SAErrorPopup);
					if(errorMsg.contains("Carrier on appointment is different from carrier on shipment")){
						if(wh.isElementPresent(SAErrorPopupClose, 5)){
							wh.clickElement(SAErrorPopupClose);
							Thread.sleep(1000);
						}
					}
				}
			}
			if(screen.equalsIgnoreCase("Shotgun")){
				if(wh.isElementPresent(AppointmentCheckBox, 3)){
					wh.clickElement(AppointmentCheckBox);
					Thread.sleep(1000);
				}

				if(wh.isElementPresent(AppointmentEdit, 3)){
					Thread.sleep(1000);
					wh.clickElement(AppointmentEdit);
					Thread.sleep(2000);
					wh.waitForPageLoaded();
				}

				getdockname();

				if(wh.isElementPresent(DockDetails, 3)){
					Thread.sleep(1000);
					wh.clickElement(DockDetails);
					Thread.sleep(2000);
					wh.waitForPageLoaded();
				}

				if(wh.isElementPresent(PlannedDock, 3)){
					wh.sendKeys(PlannedDock, plannedDockCode);
					Thread.sleep(2000);
				}

				if(wh.isElementPresent(PlannedDockDoor, 3)){
					wh.sendKeys(PlannedDockDoor, dockDoor);
					Thread.sleep(2000);
				}

				if(wh.isElementPresent(AppointmentSave,3)){
					Thread.sleep(1000);
					wh.clickElement(AppointmentSave);
					Thread.sleep(1000);
				}

				report.addReportStep("Appointment Creation","Successfully Created Appointment for the shipment - " + AppointmentID,StepResult.PASS);
			}/*else{
				report.addReportStep("Appointment Creation","Appointment Creation Failed " ,StepResult.FAIL);
			}*/
		}catch (Exception e) {
			System.out.println("Appointment Screen Catch");
			report.addReportStep("Navigate to Appointment Screen screen","Unable to navigate to Appointment Screen  - " + e.getMessage(),StepResult.FAIL);
			rc.throwTCTerminationException();

		}
		closebtn();
		return AppointmentID;
	}

	private void getdockname() throws Exception {
		int iSizeCount=0;
		ArrayList sDocKValuefromDB;
		jd.dbDFWMSMapping();
		sDocKValuefromDB = jd.array_Database_Connection("select DOCK_ID,DOCK_DOOR_NAME from dock_door where DOCK_DOOR_STATUS =204 AND MARK_FOR_DELETION ='0' AND ROWNUM <= 1");
		System.out.println("dock door sDocKValuefromDB in main function " +sDocKValuefromDB);	
		dockDoor = sDocKValuefromDB.get(1).toString();
		plannedDockCode = sDocKValuefromDB.get(iSizeCount).toString();
	}

}
