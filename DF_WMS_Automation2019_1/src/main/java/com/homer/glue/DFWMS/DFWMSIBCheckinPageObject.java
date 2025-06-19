package com.homer.glue.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.DFWMSDOObject;
import com.homer.po.DFWMS.DFWMSScheduleAppointmentPageObject;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSIBCheckinPageObject extends PageBase{


	JDBC_Connection jd = new JDBC_Connection(ic);
	public static String sDockdoorname = "";
	public static String carrierCode="";
	public static String sDockdoorbarcode,yardDockDoorBC = "";
	public String Fname,Lname,LicenseNumber,Statelist="GA",
			Strtrailer,TrailerNumber;
	public String yard,yardZone,yardDockDoorName  = "";

	public DFWMSIBCheckinPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void checkIn(String appointmentID) throws InterruptedException, Exception {

		wh.waitForPageLoaded();
		wh.clickElement(Maximize);
		Thread.sleep(2000);
		//		driver.switchTo().frame(0);

		if (wh.isElementPresent(InputAppointmentId, 3)) {
			Thread.sleep(1000);
			wh.sendKeys(InputAppointmentId, appointmentID);
			Thread.sleep(3000);
		}	
		if (wh.isElementPresent(checkInApply, 3)) {
			wh.clickElement(checkInApply);
			//           wh.waitForPageLoaded();
			Thread.sleep(3000);
		}
		if (wh.isElementPresent(CheckInCheckBox, 3)) {
			wh.clickElement(CheckInCheckBox);
			Thread.sleep(2000);
		}
		else {
			Thread.sleep(2000);
			wh.clickElement(CheckInCheckBox);
		}

		if (wh.isElementPresent(CheckInId, 2)) {
			wh.clickElement(CheckInId);
			Thread.sleep(1000);
		}			


		Thread.sleep(1000);
		report.addReportStep("Appointment Page, AppointmentID Details",
				"Appointment Page, AppointmentID Details", 
				StepResult.PASS);
	}

	public String fillInDriverDetails(String appointmentID, String screen) 
			throws InterruptedException, Exception {
		try{	
			wh.clickElement(Maximize);
			Thread.sleep(2000);
			if(appointmentID != null){

				if (wh.isElementPresent(InputAppointmentId, 3)) {
					wh.sendKeys(InputAppointmentId, appointmentID);
					Thread.sleep(1000);
				}	
				if (wh.isElementPresent(checkInApply, 3)) {
					wh.clickElement(checkInApply);
					Thread.sleep(1000);
				}

				if(wh.isElementPresent(AppointmentCheckBox, 3)){
					wh.clickElement(AppointmentCheckBox);
				}else if (wh.isElementPresent(CheckInCheckBox, 3)) {
					wh.clickElement(CheckInCheckBox);
					Thread.sleep(1000);
				}else if (wh.isElementPresent(CheckInCheckBox1, 3)) {
					wh.clickElement(CheckInCheckBox1);
					Thread.sleep(1000);
				}else if (wh.isElementPresent(CheckInCheckBox2, 3)) {
					wh.clickElement(CheckInCheckBox2);
					Thread.sleep(1000);
				}else{
					report.addReportStepWithScreenshots("Check-in", "Appointment not created", StepResult.FAIL);
					rc.throwTCTerminationException();
				}

				if (wh.isElementPresent(CheckInId, 2)) {
					wh.clickElement(CheckInId);
					Thread.sleep(500);
				}

				if(screen.equalsIgnoreCase("Shotgun")){
					sDockdoorname = dockDoor;
				}else{
					if(!screen.equalsIgnoreCase("Yard")){
						fillDockDetails(screen);
					}
				}

						if(screen.contains("Houston") || screen.contains("UPS") 
								|| screen.contains("FXHD") || screen.contains("FGND")
								||screen.contains("HoustonZone")||screen.equalsIgnoreCase("LTLOutbound")
								||screen.equalsIgnoreCase("BVR")
								||screen.equalsIgnoreCase("Generic")
								||screen.equals("ShipmentRcvDtlInbound")
								||screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")
								||screen.contains("Zone")||screen.equalsIgnoreCase("LTL_Multistop")
								||screen.contains("Yard")||screen.contains("FXHD_Dallas")||screen.contains("BVR_Dallas_MISP")
								||screen.contains("BVR_Dallas_MIMP")||screen.contains("BVR_MS")||screen.contains("HDUTL_Dallas")
								||screen.contains("UPS_Houston")){
							//trailer details
							By TrailerName = By.xpath("(.//input[contains(@name,'trailerName')])[2]");
							if(wh.isElementPresent(TrailerName, 5)){
								wh.sendKeys(TrailerName, DFWMSScheduleAppointmentPageObject.Strtrailer);
								wh.clickElement(TrailerName);
							}
						}
				//		//trailer validation
				//		jd.dbDFWMSMapping();
				//		String shipment = "";
				//		
				//		if(DFWMSInbounfFlowStepDefn.ShipmentIdInbound.equalsIgnoreCase("")){
				//			shipment = DFWMSLTLOutboundFlowStepDefn.shipmentIDOutbound;
				//		}else if(!DFWMSInbounfFlowStepDefn.ShipmentIdInbound.equalsIgnoreCase("")){
				//			shipment = DFWMSInbounfFlowStepDefn.ShipmentIdInbound;
				//		}
				//		if(!shipment.equalsIgnoreCase("")){
				//			String trailer = jd.str_Database_Connection("select trailer_number from shipment where tc_shipment_id = '"+shipment+"'");
				//			if(trailer == null){
				//				By TrailerName = By.xpath("(.//input[contains(@name,'trailerName')])[2]");
				//				if(wh.isElementPresent(TrailerName, 5)){
				//					wh.sendKeys(TrailerName, DFWMSScheduleAppointmentPageObject.Strtrailer);
				//					wh.clickElement(TrailerName);
				//				}
				//			}else{
				//				By TrailerName = By.xpath("(.//input[contains(@name,'trailerName')])[2]");
				//				if(wh.isElementPresent(TrailerName, 5)){
				//					wh.sendKeys(TrailerName, trailer);
				//					wh.clickElement(TrailerName);
				//				}
				//			}
				//		}else{
				//			//need query for inbound and con orders
				//		}
				//		
				//driver details
				By DriverSearch = By.xpath("(.//div[contains(@id,'mpslookupfield-') and contains(@class,'x-form-search-trigger-default')])[16]");
				if(wh.isElementPresent(DriverSearch, 3)){
					wh.waitForPageLoaded();
					wh.clickElement(DriverSearch);
					Thread.sleep(3000);
				}

				//driver.switchTo().frame(1);

				By DriverFirstName = By.xpath("(.//input[contains(@name,'firstNameCreate')])");
				By DriverLastName = By.xpath("(.//input[contains(@name,'surNameCreate')])");
				if (wh.isElementPresent(DriverFirstName, 2)) {
					wh.waitForPageLoaded();
					Thread.sleep(2000);
					Fname = "F"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
					Thread.sleep(5000);
					Lname = "L"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
					Thread.sleep(5000);
					wh.sendKeys(DriverFirstName, Fname);
					wh.sendKeys(DriverLastName, Lname);
				}

				By LicNo = By.xpath(".//input [contains(@name,'licenseNumCreate')]");
				if (wh.isElementPresent(LicNo, 2)) {
					wh.waitForPageLoaded();
					Thread.sleep(2000);
					LicenseNumber = "L000"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
					Thread.sleep(2000);
					wh.sendKeys(LicNo, LicenseNumber);
				}
				carrierCode = "";

				jd.dbDFWMSMapping();
								
		
				 if(screen.contains("Houston")||screen.equalsIgnoreCase("Generic")||screen.equalsIgnoreCase("LTL_Multistop")){
					By Carrier = By.xpath(".//input [contains(@name,'carrCodeCreate')]");
					String carriedCodeVal = wh.getText(Carrier);
					if(carriedCodeVal.isEmpty()){
						if(screen.equalsIgnoreCase("UPSDallas")
								|| screen.equalsIgnoreCase("UPS_Houston") 
								|| screen.equalsIgnoreCase("UPS_Baltimore")||screen.equalsIgnoreCase("UPSBoston")){
							carrierCode = "UPS";
						}else if(screen.equalsIgnoreCase("HDUTL_Dallas")
								|| screen.equalsIgnoreCase("HDUTL_Baltimore")
								|| screen.equalsIgnoreCase("HDUTL_Lacey")
								//|| screen.equalsIgnoreCase("LTLOutboundHouston")
								//|| screen.equalsIgnoreCase("LTL HDU Houston")
								||screen.equalsIgnoreCase("HoustonZone")
								){
							if(jd.envrnment.equalsIgnoreCase("LG_2019")){
								
								carrierCode = "MDO";//Houston change AACT

							}else {
								
								carrierCode = DFWMSScheduleAppointmentPageObject.carrierCode;

							}
						}else if(screen.equalsIgnoreCase("LTL_Multistop")){
							carrierCode = "EXLA";
						}else if(screen.equalsIgnoreCase("FXHD_Houston") 
								|| screen.equalsIgnoreCase("FGND_Houston")){
							carrierCode = "FDX";
						}else{
							carrierCode = "MDO";
						}
					}
				}else{
					carrierCode = DFWMSScheduleAppointmentPageObject.carrierCode;
				}

				if (wh.isElementPresent(Carrier, 2)) {
					Thread.sleep(2000);
					wh.sendKeys(Carrier, carrierCode);
					System.out.println("Carrier Code taken" +carrierCode );
				}

				By LicCountry = By.xpath(".//input [contains(@name,'driverCountry')]");

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(LicCountry));

				if (wh.isElementPresent(LicCountry, 2)) {
					Thread.sleep(3000);
					wh.sendKeys(LicCountry, "United States");
					driver.findElement(LicCountry).sendKeys((Keys.ENTER));

				}


				By LicState = By.xpath(".//input [contains(@name,'driverStateProv')]");

				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(LicState));

				if (wh.isElementPresent(LicState, 2)) {
					Thread.sleep(4000);
					wh.sendKeys(LicState, "GA");
					driver.findElement(LicState).sendKeys(Keys.ENTER);
				}

				By create = By.xpath("//span[contains(text(),'Create') and contains(@id,'button')]");
				if (wh.isElementPresent(create, 2)) {
					Thread.sleep(4000);
					wh.clickElement(create);
				}
				//driver.switchTo().parentFrame();

				wh.waitForPageLoaded();

				/*By trailerNo = By.xpath("(.//input[contains(@type,'text') and contains(@class,'x-form-text') and contains(@name,'trailerName')])[2]");
		if (wh.isElementPresent(trailerNo, 2)) {
			String trailerValue = wh.getText(trailerNo);
			System.out.println("Trailer Value From UI is " + trailerValue);
		}*/

				wh.clickElement(btnSave1);

				if(wh.isElementPresent(ErrorSave, 5)){
					wh.clickElement(ErrorSave);
				}
				if(!wh.isElementPresent(SelectedYard, 5)){
					report.addReportStep("Check-in Failed", wh.getText(SelectedYard)+DriverFirstName+DriverLastName+" "+DriverLicNo, StepResult.FAIL);
					rc.throwTCTerminationException();
				}

				report.addReportStep("Check-in confirmation Page", "Dock Door assigned" +sDockdoorname, StepResult.PASS);
				Thread.sleep(2000);
				By closeBtn = By.xpath("(.//img[contains(@class,'x-tool-img') and contains(@role,'presentation')])[13]");
				if(wh.isElementPresent(closeBtn, 5)){
					wh.clickElement(closeBtn);
					wh.clickElement(btnSave1);
				}
				if(screen.equalsIgnoreCase("Yard")){
					getSelectedYard();
				}

			}else{
				report.addReportStep("Check-in ","Appointment value is null", StepResult.FAIL);
			}
			String selectedDockDoor = wh.getText(SelectedYard);
			if(selectedDockDoor.contains(sDockdoorname)){
				report.addReportStep("Check-in ",selectedDockDoor, StepResult.PASS);
				closebtn();
			}else{
				report.addReportStep("Check-in ",selectedDockDoor, StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		}catch(Exception e){
			report.addReportStep("Check-in ",wh.getText(SelectedYard), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		return sDockdoorbarcode;
	}

	private void getSelectedYard() throws Exception {
		if(wh.isElementPresent(SelectedYard, 5)){
			yard = wh.getText(SelectedYard);
			int beginIndex = yard.indexOf("n ");
			int endIndex = yard.indexOf(".T");
			yard = yard.substring(beginIndex+1, endIndex).trim();
			System.out.println("Selected yard: "+yard);
			report.addReportStep("Selected yard: ", yard, StepResult.PASS);
		}
	}

	private void fillDockDetails(String screen) throws InterruptedException, Exception {
		// Location
		getdockname1(screen);
		int iFieldVal1=0;
		Thread.sleep(5000);
		System.out.println("dockdoor name " +sDockdoorname);
		System.out.println("dockdoor Number " +sDockdoorbarcode);

		if(wh.isElementPresent(chkinddlkup3,3)) {
			Thread.sleep(2000);

			wh.clickElement(chkinddlkup3);
			wh.waitForPageLoaded();

		}
		else if (wh.isElementPresent(chkinddlkup, 2)) {
			Thread.sleep(2000);

			wh.clickElement(chkinddlkup);
			wh.waitForPageLoaded();
		}else if (wh.isElementPresent(chkinddlkup1, 2)) {
			Thread.sleep(2000);

			wh.clickElement(chkinddlkup1);
			wh.waitForPageLoaded();
		}
		//		
		//		if (wh.isElementPresent(chkinddlkup3, 2)) {
		//			Thread.sleep(2000);
		//
		//			wh.clickElement(chkinddlkup3);
		//			wh.waitForPageLoaded();
		//		}

		if (wh.isElementPresent(chkinddlkupsrch, 2)) {
			wh.waitForPageLoaded();
			Thread.sleep(5000);
			wh.clearElement(chkinddlkupsrch);
			wh.waitForElementPresent(chkinddlkupsrch);
			Thread.sleep(5000);
			wh.sendKeys(chkinddlkupsrch, sDockdoorname);
			Thread.sleep(3000);

		}

		if (wh.isElementPresent(chkinddlkupfind, 2)) {
			Thread.sleep(2000);

			wh.waitForPageLoaded();
			wh.clickElement(chkinddlkupfind);
		}

		if (wh.isElementPresent(chkinddlkupslct2, 2)) {
			Thread.sleep(2000);

			wh.waitForPageLoaded();
			wh.clickElement(chkinddlkupslct2);
		}

		if (wh.isElementPresent(chkinddlkupslct1, 5)) {
			Thread.sleep(2000);

			wh.waitForPageLoaded();
			wh.clickElement(chkinddlkupslct1);
		}

		if (wh.isElementPresent(LocationDone, 2)) {
			Thread.sleep(2000);

			wh.clickElement(LocationDone);
		}		
	}

	public void getdockname1(String screen) throws Exception{

		int iSizeCount=0;
		ArrayList sDocKValuefromDB;
		jd.dbDFWMSMapping();
		String query = "";
		if(screen.equalsIgnoreCase("UPS_Houston")){
			if(jd.envrnment.equalsIgnoreCase("LG_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('1042') and DOCK_DOOR_STATUS =204";
				
			}else {
				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
						+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3)) "
						+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
						+ "AND DOCK_DOOR_ID IN ('1279','992','1040','1379','336','901','334') AND ROWNUM <= 1";//DD307,DD702,DD240,DD216,dd206,DD302
			}
		}else if(screen.equalsIgnoreCase("FGND_Houston")){
			if(jd.envrnment.equalsIgnoreCase("LG_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('1112') and DOCK_DOOR_STATUS =204";
			}else {
				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
						+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
						+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
						+ "AND DOCK_DOOR_ID IN ('1295','932','907','1433','341','1044','339') AND ROWNUM <= 1"; //DD312,DD219,DD708,DD375,DD318
			}
		}else if(screen.equalsIgnoreCase("FXHD_Houston")){
			if(jd.envrnment.equalsIgnoreCase("LG_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('1045') and DOCK_DOOR_STATUS =204";
			}else {


				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
						+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
						+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
						+ "AND DOCK_DOOR_ID IN ('1295','935','1045','1433','341','909','339') AND ROWNUM <= 1"; //DD312,DD219, DD375, DD710, DD318
			}
		}else if(screen.equalsIgnoreCase("UPSDallas")){
			if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('483') and DOCK_DOOR_STATUS =204";//DD348
			}else {
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('24', '212','31','938') AND ROWNUM <= 1"; //642 , 540 , 649
			}
		}else if(screen.equalsIgnoreCase("FGND_Dallas")){
			if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('489') and DOCK_DOOR_STATUS =204";//DD354
			}else {
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('185','932') AND ROWNUM <= 1"; //508
			}
		}else if(screen.equalsIgnoreCase("FXHD_Dallas")){
			if(jd.envrnment.equalsIgnoreCase("Lacey_2019")) {

				query="select dock_door_name,barcode from dock_door where dock_door_id in('490') and DOCK_DOOR_STATUS =204";//DD355
			}else {
				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('226','935') AND ROWNUM <= 1"; //556
			}
		}else if(screen.equalsIgnoreCase("UPS_Baltimore")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('569') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("UPS_Lacey")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('516','517') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("UPS_Tampa")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('647','648') AND ROWNUM <= 1"; //DD105,106
		}else if(screen.equalsIgnoreCase("UPS_Newark")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('663','693','712') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("UPS_Tracey")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('821','826') AND ROWNUM <= 1";//DD101 , DD106
		}else if(screen.equalsIgnoreCase("FGND_Baltimore")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('568') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("FGND_Lacey")){//190
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('503','505') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("FGND_Newark")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('827') AND ROWNUM <= 1"; //DD866
		}else if(screen.equalsIgnoreCase("FXHD_Baltimore")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('570') AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("FXHD_Lacey")){//180
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ( ) AND ROWNUM <= 1";
		}else if(screen.equalsIgnoreCase("FXHD_Tampa")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('650') AND ROWNUM <= 1"; //DD108
		}else if(screen.equalsIgnoreCase("FXHD_Newark")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('678') AND ROWNUM <= 1"; //DD717
		}else if(screen.equalsIgnoreCase("FXHD_Tracey")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('868') AND ROWNUM <= 1"; //DD148
		}else if(screen.equalsIgnoreCase("FGND_Tracey")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('823') AND ROWNUM <= 1";  //DD103
		}else if(screen.equalsIgnoreCase("UPS_Miami")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('882','894' ,'900') AND ROWNUM <= 1";//DD131 , DD143 , DD149 ('882','894' ,'900')
		}else if(screen.equalsIgnoreCase("FGND_Miami")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('897') AND ROWNUM <= 1";//DD146  
		}else if(screen.equalsIgnoreCase("FXHD_Miami")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('896') AND ROWNUM <= 1";//DD145  
		}else if(screen.equalsIgnoreCase("UPS_Boston")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('882','894' ,'900') AND ROWNUM <= 1";//DD131 
		}else if(screen.equalsIgnoreCase("FGND_Boston")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('897') AND ROWNUM <= 1";//DD146  
		}else if(screen.equalsIgnoreCase("FXHD_Boston")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('896') AND ROWNUM <= 1";//DD145  
		}
		else if(screen.equalsIgnoreCase("UPS_Atlanta")){
			query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 "
					+ "AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3))"
					+ "FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 "
					+ "AND DOCK_DOOR_ID IN ('880') AND ROWNUM <= 1";//DD201
		}else{
			if(jd.envrnment.equalsIgnoreCase("Dallas_2019") || jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3)) FROM SYS_CODE WHERE CODE_TYPE = '22B') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 AND ROWNUM <= 1";
			}else{
				query = "SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3)) FROM SYS_CODE WHERE CODE_TYPE = '6A3') AND BARCODE IS NOT NULL AND MARK_FOR_DELETION = 0 AND DOCK_DOOR_NAME NOT IN ('DD116','DD123','DD159','DD228','DD103','DD208','DD354','DD149','DD119','DD112','DD352') AND ROWNUM <= 1";
			}
		}
		sDocKValuefromDB = jd.array_Database_Connection(query);
		if(sDocKValuefromDB.isEmpty()){
			report.addReportStep("Select Dock Door", "Dock Door not returned from DB", StepResult.FAIL);
			throw new Exception();
		}else{
			System.out.println("dock door sDocKValuefromDB in main function " +sDocKValuefromDB);
			sDockdoorname = sDocKValuefromDB.get(iSizeCount).toString();
			iSizeCount++;
			sDockdoorbarcode = sDocKValuefromDB.get(iSizeCount).toString();
			System.out.println("dock door barcode in main function " +sDockdoorbarcode +" "+sDockdoorname);
			iSizeCount--;
		}
	}

	public void validateTrailerNum() throws Exception {

		if(wh.isElementPresent(TrailerNum, 3)){
			TrailerNumber = wh.getText(TrailerNum);
			if(TrailerNumber != null || !TrailerNumber.isEmpty() || TrailerNumber != ""){
				System.out.println("Trailer Number: "+TrailerNumber);
				report.addReportStep("Validating Trailer Number", "Trailer Number is Successfully created" +TrailerNum, StepResult.PASS);
			}else{
				throw new Exception("Trailer Number is not populated."	+ "XPath used is: " + TrailerNum.toString());
			}
		}
	}

	public String addYardTask() throws Exception {
		wh.clickElement(Maximize);
		Thread.sleep(1000);
		wh.clickElement(Refresh);

		driver.switchTo().frame(0);
		if(wh.isElementPresent(AddYardTask, 5)){
			wh.clickElement(AddYardTask);
			Thread.sleep(1000);
		}
		String carrierCode = "";
		if(DFWMSDOObject.DispostionType.equalsIgnoreCase("HDU_BVR")){
			carrierCode = "WEND";
		}else{
			jd.dbDFWMSMapping();

			if(jd.envrnment.equalsIgnoreCase("LG_2019")) {
				carrierCode = "LEGS";
				yardZone = "OB2";

			}else if(jd.envrnment.equalsIgnoreCase("Perris_2019")){

				carrierCode = "WEDV";

			}else {

				carrierCode = "MDO";
			}

		}
		if(wh.isElementPresent(CarrierCodeYard, 5)){
			wh.sendKeys(CarrierCodeYard, carrierCode);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(YardTrailerNum, 5)){
			wh.sendKeys(YardTrailerNum, DFWMSScheduleAppointmentPageObject.Strtrailer);
			Thread.sleep(1000);
		}

		if(wh.isElementPresent(TaskType, 5)){
			wh.selectValue(TaskType, "Move");
			Thread.sleep(1000);
		}

		if(wh.isElementPresent(TaskPrior, 5)){
			wh.sendKeys(TaskPrior, "40");
			Thread.sleep(1000);
		}

		if(wh.isElementPresent(AddTask, 5)){
			wh.clickElement(AddTask);
			Thread.sleep(1000);
		}


		if(wh.isElementPresent(DestLoc, 5)){
			//based on put away type and yard
			//String destLocation = "";
			//below condition for bk1 items only
			/*if((yardZone.equalsIgnoreCase("IB3") 
					&& (DFWMSDOObject.DispostionType).equalsIgnoreCase("BK1_SR1")) 
					|| (yardZone.equalsIgnoreCase("OB2") 
							&& (DFWMSDOObject.DispostionType).equalsIgnoreCase("HDU_BVR"))){

			}else */
			if(!DFWMSDOObject.DispostionType.isEmpty()){
				if(DFWMSDOObject.DispostionType.equalsIgnoreCase("BK1_SR1")){
					yardZone = "IB2";
				}else if(DFWMSDOObject.DispostionType.equalsIgnoreCase("BK2_SR2")){
					yardZone = "IB4";
				}else if(DFWMSDOObject.DispostionType.equalsIgnoreCase("BK3")){
					yardZone = "IB5";
				}else if(DFWMSDOObject.DispostionType.equalsIgnoreCase("HZ1_CN1")){
					yardZone = "IB6";
				}else if(DFWMSDOObject.DispostionType.equalsIgnoreCase("LTL_TL_Parcel")
						|| DFWMSDOObject.DispostionType.equalsIgnoreCase("HDU_BVR")){
					yardZone = "OB2";
				}else if(DFWMSDOObject.DispostionType.equalsIgnoreCase("BK1")){
					yardZone = "IB3";
				}
			}else{
				report.addReportStep("Add Yard Task - Get Yard Dock Door", 
						"Unable to find yard dock door for item putaway type", StepResult.FAIL);
			}

			getYardDockDoor(yardZone);
			//destLocation = yardDockDoorName;
			if(wh.isElementPresent(DestLocIcon, 5)){
				wh.clickElement(DestLocIcon);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(DestLocSearch, 5)){
				wh.sendKeys(DestLocSearch, yardDockDoorName);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(DestLocFind, 5)){
				wh.clickElement(DestLocFind);
				Thread.sleep(1000);
			}
			By DestDockSelect = By.xpath(".//option[contains(@value,'"+yardDockDoorName+"')]");
			if(wh.isElementPresent(DestDockSelect, 5)){
				wh.clickElement(DestDockSelect);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(DestLocSelect, 5)){
				wh.clickElement(DestLocSelect);
				Thread.sleep(1000);
			}
		}

		if(wh.isElementPresent(AddTask, 5)){
			wh.clickElement(AddTask);
			Thread.sleep(1000);
		}

		String taskId = "";
		if(wh.isElementPresent(TaskId, 5)){
			taskId = wh.getText(TaskId);
		}
		report.addReportStepWithScreenshots("Add Yard Task", "Created Yard Task. Task Id: "+taskId, StepResult.PASS);
		closebtn();
		return taskId;
	}

	private void getYardDockDoor(String yardZoneDD) throws Exception {
		int iSizeCount=0;
		jd.dbDFWMSMapping();
		List<String> yardDockDoor = new ArrayList<String>();
		List<String> checkOutDockDoors = new ArrayList<String>();

		yardDockDoor = jd.array_Database_Connection("SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR "
				+ "WHERE DOCK_ID = '"+yardZoneDD+"' AND BARCODE IS NOT NULL AND DOCK_DOOR_STATUS = 204 AND ROWNUM <= 1");
		if(yardDockDoor.size() > 0){
			yardDockDoorName = yardDockDoor.get(iSizeCount).toString();
			iSizeCount++;
			yardDockDoorBC = yardDockDoor.get(iSizeCount).toString();
			System.out.println("dock door barcode in main function " +yardDockDoorName +" " +yardDockDoorBC);
			iSizeCount--;
		}else{
			checkOutDockDoors = jd.array_Database_Connection("SELECT DOCK_DOOR_NAME FROM DOCK_DOOR "
					+ "WHERE DOCK_ID = '"+yardZoneDD+"' AND BARCODE IS NOT NULL AND DOCK_DOOR_STATUS = 212");
			System.out.println("Checkout Dock doors" +checkOutDockDoors);
			report.addReportStep("Yard task - Dock door selection", "Need to checkout dock doors: "+checkOutDockDoors, StepResult.FAIL);
			rc.terminateTestCase("Check-out dock door - Yard dock door selection");
			//checkout dock doors
		}
	}

	public void validateYardRules(String flow) throws Exception {

		if(flow.equalsIgnoreCase("Inbound")){
			List<String> IY1 = new ArrayList<String>();
			List<String> IY3 = new ArrayList<String>();
			List<String> IY4 = new ArrayList<String>();

			IY1 = getYards("IY1");
			Thread.sleep(1000);
			IY3 = getYards("IY3");
			Thread.sleep(1000);
			IY4 = getYards("IY4");

			//inbound drop west zone start
			if(IY3.size() > 0){
				Thread.sleep(1000);
				if(IY3.contains(yard)){
					yardZone = "IB3"; 
					System.out.println("Yard validation completed");
				}

			}else if(IY1.size() > 0){
				Thread.sleep(1000);
				if(IY1.contains(yard)){
					yardZone = "IB3"; 
					System.out.println("Yard validation completed");
				}
			}else if(IY4.size() > 0){
				Thread.sleep(1000);
				if(IY4.contains(yard)){
					yardZone = "IB3"; 
					System.out.println("Yard validation completed");
				}
			}

			//inbound drop end
		}else if(flow.equalsIgnoreCase("Outbound")){
			List<String> OY2 = new ArrayList<String>();
			List<String> OY5 = new ArrayList<String>();
			List<String> OY4 = new ArrayList<String>();

			OY2 = getYards("OY2");
			Thread.sleep(1000);
			OY5 = getYards("OY5");
			Thread.sleep(1000);
			OY4 = getYards("OY4");
			if(OY2.size() > 0){
				Thread.sleep(1000);
				if(OY2.contains(yard)){
					yardZone = "OB2"; 
					System.out.println("Yard validation completed");
				}

			}else if(OY5.size() > 0){
				Thread.sleep(1000);
				if(OY5.contains(yard)){
					yardZone = "OB2"; 
					System.out.println("Yard validation completed");
				}
			}else if(OY4.size() > 0){
				Thread.sleep(1000);
				if(OY4.contains(yard)){
					yardZone = "OB2"; 
					System.out.println("Yard validation completed");
				}
			}
		}else if(flow.equalsIgnoreCase("OutboundBaltimore")){
			List<String> OY1 = new ArrayList<String>();
			List<String> OY2 = new ArrayList<String>();
			String selectedYard = "";
			OY1 = getYards("OY1");
			Thread.sleep(1000);
			OY2 = getYards("OY2");
			Thread.sleep(1000);

			if(OY2.size()>0 || OY1.size()>0){
				if(OY1.contains(yard)){
					selectedYard = "OY1";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(OY2.contains(yard)){
					selectedYard = "OY2";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}
			}else{
				report.addReportStep("Yards are not free", "Failure", StepResult.FAIL);
			}
		}else if(flow.equalsIgnoreCase("InboundBaltimore")){
			List<String> IY2 = new ArrayList<String>();
			List<String> IY1 = new ArrayList<String>();
			List<String> IY3 = new ArrayList<String>();
			String selectedYard = "";
			IY2 = getYards("IY2");
			Thread.sleep(1000);
			IY1 = getYards("IY1");
			Thread.sleep(1000);
			IY3 = getYards("IY3");
			Thread.sleep(1000);

			if(IY2.size()>0 || IY3.size()>0 || IY1.size()>0){
				if(IY2.contains(yard)){
					selectedYard = "IY2";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(IY1.contains(yard)){
					selectedYard = "IY1";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(IY3.contains(yard)){
					selectedYard = "IY3";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}
			}else{
				report.addReportStep("Yards are not free", "Failure", StepResult.FAIL);
			}
		}else if(flow.equalsIgnoreCase("InboundLacey")){
			List<String> IY2 = new ArrayList<String>();
			List<String> IY3 = new ArrayList<String>();
			List<String> IY4 = new ArrayList<String>();
			String selectedYard = "";
			IY2 = getYards("IY2");
			Thread.sleep(1000);
			IY3 = getYards("IY3");
			Thread.sleep(1000);
			IY4 = getYards("IY4");

			if(IY2.size()>0 || IY3.size()>0 || IY4.size()>0){
				if(IY2.contains(yard)){
					selectedYard = "IY2";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(IY3.contains(yard)){
					selectedYard = "IY3";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(IY4.contains(yard)){
					selectedYard = "IY4";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}
			}else{
				report.addReportStep("Yards are not free", "Failure", StepResult.FAIL);
			}
		}else if(flow.equalsIgnoreCase("OutboundLacey")){
			List<String> OY3 = new ArrayList<String>();
			List<String> OY2 = new ArrayList<String>();
			String selectedYard = "";
			OY3 = getYards("OY3");
			Thread.sleep(1000);
			OY2 = getYards("OY2");
			Thread.sleep(1000);

			if(OY2.size()>0 || OY3.size()>0){
				//Parcel/LTL/TL
				if(OY3.contains(yard)){
					selectedYard = "OY3";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}else if(OY2.contains(yard)){
					selectedYard = "OY2";
					report.addReportStep("Selected yard: "+selectedYard, "Sucess", StepResult.PASS);
				}
			}else{
				report.addReportStep("Yards are not free", "Failure", StepResult.FAIL);
			}
		}else if(flow.equalsIgnoreCase("OutboundHouston")){
			System.out.println();
		}else if(flow.equalsIgnoreCase("InboundHouston")){
			System.out.println();
		}
	}

	private List<String> getYards(String yardZone) throws Exception {
		List<String> yards = new ArrayList<String>();
		jd.dbDFWMSMapping();
		yards = jd.array_Database_Connection("SELECT YZS.YARD_ZONE_SLOT_NAME FROM "
				+ "YARD_ZONE_SLOT YZS, YARD_ZONE YZ WHERE YZ.YARD_ID = YZS.YARD_ID "
				+ "AND YZ.YARD_ZONE_ID = YZS.YARD_ZONE_ID "
				//+ "AND YZS.YARD_ZONE_SLOT_STATUS = 204" available yards condition
				+ "AND YZ.YARD_ZONE_NAME = '"+yardZone+"'");

		return yards;
	}

	public void validateTaskStatus(String status, String taskID) throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);

		if(wh.isElementPresent(TaskIdVal, 5)){
			wh.sendKeys(TaskIdVal, taskID);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(TaskApply, 5)){
			wh.clickElement(TaskApply);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(TaskStatus, 5)){
			String tskStatus = wh.getText(TaskStatus);
			if(tskStatus.equalsIgnoreCase(status)){
				report.addReportStep("Task Status Validation", "Task Status is "+tskStatus, StepResult.PASS);
			}
			Thread.sleep(1000);
		}


		closebtn();
	}

	public List<String> yards(String zone) throws Exception {
		return getYards(zone);
	}
}
