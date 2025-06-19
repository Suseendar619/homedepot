package com.homer.po.DFWMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSAPPTCREATIONPageObject extends PageBase {

	public DFWMSAPPTCREATIONPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static final By inputEquipCode = By.id("dataForm:equipment_det");
	//*[@id="dataForm:equipment_det"]
	
	
	public static final By inputASN = By.id("dataForm:apptObjTable:0:asnId2");
	public static final By chckBoxAddApp = By.id("checkAll_c0_dataForm:apptObjTable");
	//public static final By btnSave  =By.id("button");
	
	public static String AppointmentID;
	public static final By InputASN1=By.id("dataForm:apptObjTable:0:asnId2");
	public static final By InputShipment1=By.id("dataForm:apptObjTable:0:shipId2");
	
	public static final By btnAdd  =By.id("dataForm:listView:apptList_btn_1");
	
	public static final By inputApptmtId = By.id("dataForm:listView:filterId:field1value1");
	
	public static final By chckBoxCheckIn = By.id("checkAll_c0_dataForm:listView:dataTable");
	public static final By btnCheckIn  =By.id("rmButton_1CheckIn1_167271203");	
	public static final By btnCheckIn22  =By.id("dataForm:listView:apptList_btn_4");
	
	
	
	public static final By inputtrailerV = By.id("dataForm:listViewTrailer:dataTableTrailer:0:trailerV");
	public static final By inputCurrLocation = By.id("dataForm:listViewTrailer:dataTableTrailer:0:currentLocationV");
	
		
	
	public static final By lnkMenu = By.xpath("//span[contains(text(),'enu')]");
	public String sDOCKSTATus = "204",Fname,Lname,LicenseNumber,Carrier,Statelist="GA",Strtrailer, DockDoor="[GR1]DD303",DockDoor1="DD372";
	
	
	
	//*[@id="dataForm:driverCRCode1"]
	
	public static ArrayList sDockdoorname = new ArrayList();
	public static ArrayList sDockdoorbarcode = new ArrayList();

	//public String Statelist = "GA";
	
	public String getRTEValue(String Donbr1) throws Exception {

		String sRTEValuefromDB = "";
		jd.dbDFWMSMapping();
		sRTEValuefromDB = jd.str_Database_Connection("Select RTE_TO from orders where TC_ORDER_ID = '" + Donbr1 + "'");
		System.out.println("Route value from DB "+sRTEValuefromDB);
		return (sRTEValuefromDB);
		

	}
	
	public void getdockname(String Routevalue) throws Exception{
		
		if((Routevalue.equals("9")|| Routevalue.equals("10")|| Routevalue.equals("11")|| Routevalue.equals("12"))){
			System.out.println("Route value in main function if loop "+Routevalue);
			int iSizeCount=0;
			ArrayList sDocKValuefromDB;
			//String sDocKValuefromDB = "";
			jd.dbDFWMSMapping();
			sDocKValuefromDB = jd.array_Database_Connection("select DOCK_DOOR_NAME,BARCODE from dock_door where DOCK_DOOR_STATUS =204 and Dock_door_name in (select concat('DD',substr(code_desc,14,3)) from SYS_CODE where Code_Type = '22B') AND ROWNUM <= 1");
			System.out.println("dock door sDocKValuefromDB in main function " +sDocKValuefromDB);
			sDockdoorname.add(sDocKValuefromDB.get(iSizeCount));
			iSizeCount++;
			sDockdoorbarcode.add(sDocKValuefromDB.get(iSizeCount));
			System.out.println("dock door barcode in main function " +sDockdoorbarcode +sDockdoorname);
			iSizeCount--;
			}
		 else if (Routevalue.equals("2") || Routevalue.equals("3")|| Routevalue.equals("18")){
			System.out.println("Route value in main function  else loop "+Routevalue);
			int iSizeCount=0;
			ArrayList sDocKValuefromDB;
			//String sDocKValuefromDB = "";
			jd.dbDFWMSMapping();
			sDocKValuefromDB = jd.array_Database_Connection("select DOCK_DOOR_NAME,BARCODE from dock_door where DOCK_DOOR_STATUS =204 and Dock_door_name  not in (select concat('DD',substr(code_desc,14,3)) from SYS_CODE where Code_Type = '22B') AND ROWNUM <= 1");
			System.out.println("dock door sDocKValuefromDB in main function " +sDocKValuefromDB);
			sDockdoorname.add(sDocKValuefromDB.get(iSizeCount));
			iSizeCount++;
			sDockdoorbarcode.add(sDocKValuefromDB.get(iSizeCount));
			System.out.println("dock door barcode in main function " +sDockdoorbarcode +sDockdoorname);
			iSizeCount--;
			
			
		}
		
		
		//return Carrier;
		
	}
	
public void getdockname1() throws Exception{
			
			int iSizeCount=0;
			ArrayList sDocKValuefromDB;
			jd.dbDFWMSMapping();
			sDocKValuefromDB = jd.array_Database_Connection("select DOCK_DOOR_NAME,BARCODE from dock_door where DOCK_DOOR_STATUS =204 and Dock_door_name not in (select concat('DD',substr(code_desc,14,3)) from SYS_CODE where Code_Type = '22B') AND ROWNUM <= 1");
			System.out.println("dock door sDocKValuefromDB in main function " +sDocKValuefromDB);
			sDockdoorname.add(sDocKValuefromDB.get(iSizeCount));
			iSizeCount++;
			sDockdoorbarcode.add(sDocKValuefromDB.get(iSizeCount));
			System.out.println("dock door barcode in main function " +sDockdoorbarcode +sDockdoorname);
			iSizeCount--;
			}
	
	public String DF_OB_APPT_CREATION(String Appttype,String Equipcode,String Shipment_ID)throws Exception
	
	//public String DF_OB_APPT_CREATION()throws Exception
	{
		try{
			System.out.println("inside try function in appt creation");
			System.out.println("DF_OB_APPT_CREATIO " +Shipment_ID);//+"DF_OB_APPT_CREATION_Equip_CODE "+Equipcode+"DF_OB_APPT_CREATION_Shipment_ID "+Shipment_ID);
			
			if (wh.isElementPresent(btnAdd, 2)) {
				wh.clickElement(btnAdd);
				Thread.sleep(2000);
				
				//wh.clickElement(inputApptdate);
			}
			
			if (wh.isElementPresent(inputEquipCode, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(inputEquipCode, Equipcode);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(inputApptdate, 2)) {
				Thread.sleep(2000);
				wh.sendKeydateVal(inputApptdate);
			}
			if (wh.isElementPresent(listApptype, 2)) {
				Thread.sleep(2000);
				wh.selectValue(listApptype, Appttype);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(InputShipment1, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(InputShipment1, Shipment_ID);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(btnSave, 2)) {
				Thread.sleep(2000);
				wh.clickElement(btnSave);
			}
			
			Thread.sleep(2000);
			if (wh.isElementPresent(eleAppointmentID, 2)){
			AppointmentID=wh.getText(eleAppointmentID);	
			System.out.println("Appointment Id : " + AppointmentID);
			report.addReportStep("Appointment Creation","Successfully Created Appointment for the shipment - " + AppointmentID,StepResult.PASS);
			}
			else{
				report.addReportStep("Appointment Creation","Appoinment Creation Failed " ,StepResult.FAIL);	
			}
			
			
		}catch (Exception e) {
			System.out.println("Appointment Screen Catch");
			report.addReportStep("Navigate to Appointment Screen screen","Unable to navigate to Appointment Screen screen - " + e.getMessage(),StepResult.WARNING);
			//rc.throwTCTerminationException();
		
	}
		return AppointmentID;
		//return null;
	}
	
	public  void DFOBCheckInNavigation(String ApptID) throws Exception {
		

		
		if (wh.isElementPresent(lnkMenu, 2)) {
		            wh.clickElement(lnkMenu);
		            Thread.sleep(1000);
        }
		if (wh.isElementPresent(lnkCheckIn, 2)) {
		           wh.clickElement(lnkCheckIn);
		           Thread.sleep(1000);
        }			
		
		if (wh.isElementPresent(inputApptmtId, 2)) {
			wh.sendKeys(inputApptmtId, ApptID);
	           Thread.sleep(1000);
			}	
		if (wh.isElementPresent(btnApply1, 2)) {
			
	           wh.clickElement(btnApply1);
	           wh.waitForPageLoaded();
	           Thread.sleep(2000);
			}
				
		Thread.sleep(1000);
		report.addReportStep("Appointment Page, AppointmentID Details",
				"Appointment Page, AppointmentID Details", 
				StepResult.DONE);
			}
	
	public  String DF_CheckIn_Fill_Details() throws Exception {	
		
		
		
		//String Fname,Lname,LicenseNumber,Carrier,Statelist,Strtrailer, DockDoor;
		
		String sDockDoor = "";
		String Donbr1 = DFWMSDORCanonicalPostPageObject.poNBR;
		//String Donbr1 = "1016592A";
		if (wh.isElementPresent(chckBoxCheckIn, 2)) {
		//	waitForWindowToLoad("Check In");
			Thread.sleep(1000);
	           wh.clickElement(chckBoxCheckIn);
	           Thread.sleep(1000);
			}
		if (wh.isElementPresent(btnCheckIn, 2)) {
			wh.waitForPageLoaded();
	           wh.clickElement(btnCheckIn);
	           Thread.sleep(1000);
			}
      						
		if (wh.isElementPresent(btnDriverName, 2)) {
			wh.waitForPageLoaded();
	           wh.clickElement(btnDriverName);
	           Thread.sleep(1000);
			}

		if (wh.isElementPresent(inputFname, 2)) {
			wh.waitForPageLoaded();
		Fname = "F"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
		Thread.sleep(2000);
		Lname = "L"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
		Thread.sleep(2000);
		wh.sendKeys(inputFname, Fname);
		wh.sendKeys(inputLname, Lname);
		}
		if (wh.isElementPresent(inputLICNo, 2)) {
			wh.waitForPageLoaded();
			Thread.sleep(1000);
			LicenseNumber = "L000"+new SimpleDateFormat("ddmmyy").format(Calendar.getInstance().getTime());
			Thread.sleep(2000);
			wh.sendKeys(inputLICNo, LicenseNumber);
		}
		System.out.println("Do number in appointment " +Donbr1);
		String Routevalue = getRTEValue(Donbr1).toString();
		Thread.sleep(1000);
		System.out.println("Route value in main function " +Routevalue);
		
		if (wh.isElementPresent(Carrierselect, 2)) {
			System.out.println("carrier value " );
		if(Routevalue.equals("9")|| Routevalue.equals("10")|| Routevalue.equals("11")|| Routevalue.equals("12")){
			System.out.println("Route value in main function if loop "+Routevalue);
				Thread.sleep(1000);
				wh.sendKeys(Carrierselect, "UPS");
				Thread.sleep(2000);
			}
		 else if (Routevalue.equals("FX") || Routevalue.equals("FG")){
			System.out.println("Route value in main function  else loop "+Routevalue);
				Thread.sleep(1000);
				wh.sendKeys(Carrierselect, "AGIL");
				Thread.sleep(2000);
			
			
		}
		}
		
		
		if (wh.isElementPresent(listLicenseState, 2)) {
			Thread.sleep(1000);
			wh.selectValue(listLicenseState, Statelist);
		}
		report.addReportStep("Appointment Page, Driver Details",
				"Appointment Page, Driver Details", 
				StepResult.DONE);
		if (wh.isElementPresent(btnCreate, 2)) {
		
        	wh.clickElement(btnCreate);
        	Thread.sleep(1000);
        }
		if (wh.isElementPresent(chckBoxAppmntList, 2)) {
			
        	wh.clickElement(chckBoxAppmntList);
        	Thread.sleep(1000);
        }
	
		if (wh.isElementPresent(inputtrailerV, 2)) {
				
		Strtrailer = "T" +new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		Thread.sleep(1000);
		wh.sendKeys(inputtrailerV, Strtrailer);
		Thread.sleep(1000);
		}
//		if (wh.isElementPresent(inputCurrLocation, 2)) {
//		wh.clearElement(inputCurrLocation);
//		wh.waitForElementPresent(inputCurrLocation);
//		Thread.sleep(1000);
//		wh.sendKeys(inputCurrLocation, DockDoor);
//		}
		
		getdockname(Routevalue);
		int iFieldVal1=0;
		String sDockdoor1 = sDockdoorname.get(iFieldVal1).toString();
		System.out.println("dockdoor name " +sDockdoor1);
		String sDockBC1 = sDockdoorbarcode.get(iFieldVal1).toString();
		System.out.println("dockdoor name " +sDockBC1);
		System.out.println("Dock Door name returned from DB" +sDockDoor);
		
		if (wh.isElementPresent(chkinddlkup, 2)) {
			Thread.sleep(2000);
			
			wh.clickElement(chkinddlkup);
			wh.waitForPageLoaded();
		}
		if (wh.isElementPresent(chkinddlkupsrch, 2)) {
			wh.waitForPageLoaded();
			Thread.sleep(2000);
			wh.clearElement(chkinddlkupsrch);
			wh.waitForElementPresent(chkinddlkupsrch);
			Thread.sleep(1000);
			wh.sendKeys(chkinddlkupsrch, sDockdoor1);
			}
		if (wh.isElementPresent(chkinddlkupfind, 2)) {
			wh.waitForPageLoaded();
			wh.clickElement(chkinddlkupfind);
			Thread.sleep(1000);
		}
			
		if (wh.isElementPresent(chkinddlkupslct2, 2)) {
			wh.waitForPageLoaded();
			Thread.sleep(2000);
			wh.clickElement(chkinddlkupslct2);
			Thread.sleep(1000);
		}
		
		if (wh.isElementPresent(chkinddlkupslct1, 2)) {
			wh.waitForPageLoaded();
			Thread.sleep(2000);
			wh.clickElement(chkinddlkupslct1);
			Thread.sleep(1000);
		}
		
		if (wh.isElementPresent(listtrailerCondition, 2)) {
		wh.selectValue(listtrailerCondition, "Good");
		wh.waitForPageLoaded();
		}
		if (wh.isElementPresent(chckBoxTrailerList, 2)) {
	
        	wh.clickElement(chckBoxTrailerList);
        }
		report.addReportStep("Appointment Page, Trailer Details",
				"Appointment Page, Trailer Details", 
				StepResult.DONE);
		wh.waitForPageLoaded();
		wh.clickElement(btnSave1);
		String AppmtSucessFullLbl = wh.getSource();	
		 report.addReportStep("Check-in confirmation Page", "Trailer Checked in Successfully Sucessfully Checked IN" +Strtrailer, StepResult.PASS);
			 Thread.sleep(2000);
	 driver.findElement(By.id("er_d1_c1")).click();
	 Thread.sleep(3000);
	return DockDoor;
}
	
public String DF_IB_APPT_CREATION(String Appttype,String Equipcode,String ASN_ID)throws Exception
	
	//public String DF_OB_APPT_CREATION()throws Exception
	{
		try{
			System.out.println("inside try function in appt creation");
			System.out.println("DF_OB_APPT_CREATIO ASN  " +ASN_ID);//+"DF_OB_APPT_CREATION_Equip_CODE "+Equipcode+"DF_OB_APPT_CREATION_Shipment_ID "+Shipment_ID);
			
			if (wh.isElementPresent(btnAdd, 2)) {
				wh.clickElement(btnAdd);
				Thread.sleep(2000);
				
				//wh.clickElement(inputApptdate);
			}
			
			if (wh.isElementPresent(inputEquipCode, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(inputEquipCode, Equipcode);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(inputApptdate, 2)) {
				Thread.sleep(2000);
				wh.sendKeydateVal(inputApptdate);
			}
			if (wh.isElementPresent(listApptype, 2)) {
				Thread.sleep(2000);
				wh.selectValue(listApptype, Appttype);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(InputASN1, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(InputASN1, ASN_ID);
				//wh.clickElement(inputApptdate);
			}
			if (wh.isElementPresent(btnSave, 2)) {
				Thread.sleep(2000);
				wh.clickElement(btnSave);
			}
			
			Thread.sleep(2000);
			if (wh.isElementPresent(eleAppointmentID, 2)){
			AppointmentID=wh.getText(eleAppointmentID);	
			System.out.println("Appointment Id : " + AppointmentID);
			report.addReportStep("Appointment Creation","Successfully Created Appointment for the shipment - " + AppointmentID,StepResult.PASS);
			}
			else{
				report.addReportStep("Appointment Creation","Appoinment Creation Failed " ,StepResult.FAIL);	
			}
			
			
		}catch (Exception e) {
			System.out.println("Appointment Screen Catch");
			report.addReportStep("Navigate to Appointment Screen screen","Unable to navigate to Appointment Screen screen - " + e.getMessage(),StepResult.WARNING);
			//rc.throwTCTerminationException();
		
	}
		return AppointmentID;
		//return null;
	}

public  String DF_IB_CheckIn_Fill_Details() throws Exception {	
	
	
	
	//String Fname,Lname,LicenseNumber,Carrier,Statelist,Strtrailer, DockDoor;
	
	String sDockDoor = "";
	String Donbr1 = DFWMSDORCanonicalPostPageObject.poNBR;
	//String Donbr1 = "1016592A";
	if (wh.isElementPresent(chckBoxCheckIn, 2)) {
	//	waitForWindowToLoad("Check In");
		Thread.sleep(1000);
           wh.clickElement(chckBoxCheckIn);
           Thread.sleep(1000);
		}
	if (wh.isElementPresent(btnCheckIn, 2)) {
		wh.waitForPageLoaded();
           wh.clickElement(btnCheckIn);
           Thread.sleep(1000);
		}
  						
	if (wh.isElementPresent(btnDriverName, 2)) {
		wh.waitForPageLoaded();
           wh.clickElement(btnDriverName);
           Thread.sleep(1000);
		}

	if (wh.isElementPresent(inputFname, 2)) {
		wh.waitForPageLoaded();
	Fname = "F"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
	Thread.sleep(2000);
	Lname = "L"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
	Thread.sleep(2000);
	wh.sendKeys(inputFname, Fname);
	wh.sendKeys(inputLname, Lname);
	}
	if (wh.isElementPresent(inputLICNo, 2)) {
		wh.waitForPageLoaded();
		Thread.sleep(1000);
		LicenseNumber = "L000"+new SimpleDateFormat("ddmmyy").format(Calendar.getInstance().getTime());
		Thread.sleep(2000);
		wh.sendKeys(inputLICNo, LicenseNumber);
	}
	
	if (wh.isElementPresent(Carrierselect, 2)) {
		System.out.println("carrier value " );
		wh.sendKeys(Carrierselect, "WENP");
			Thread.sleep(2000);
		}
	
	if (wh.isElementPresent(listLicenseState, 2)) {
		Thread.sleep(1000);
		wh.selectValue(listLicenseState, Statelist);
	}
	report.addReportStep("Appointment Page, Driver Details",
			"Appointment Page, Driver Details", 
			StepResult.DONE);
	if (wh.isElementPresent(btnCreate, 2)) {
	
    	wh.clickElement(btnCreate);
    	Thread.sleep(1000);
    }
	if (wh.isElementPresent(chckBoxAppmntList, 2)) {
		
    	wh.clickElement(chckBoxAppmntList);
    	Thread.sleep(1000);
    }

	if (wh.isElementPresent(inputtrailerV, 2)) {
			
	Strtrailer = "T" +new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
	Thread.sleep(1000);
	wh.sendKeys(inputtrailerV, Strtrailer);
	Thread.sleep(1000);
	}
//	if (wh.isElementPresent(inputCurrLocation, 2)) {
//	wh.clearElement(inputCurrLocation);
//	wh.waitForElementPresent(inputCurrLocation);
//	Thread.sleep(1000);
//	wh.sendKeys(inputCurrLocation, DockDoor);
//	}
	
	getdockname1();
	int iFieldVal1=0;
	String sDockdoor1 = sDockdoorname.get(iFieldVal1).toString();
	System.out.println("dockdoor name " +sDockdoor1);
	String sDockBC1 = sDockdoorbarcode.get(iFieldVal1).toString();
	System.out.println("dockdoor name " +sDockBC1);
	System.out.println("Dock Door name returned from DB" +sDockDoor);
	
	if (wh.isElementPresent(chkinddlkup, 2)) {
		Thread.sleep(2000);
		
		wh.clickElement(chkinddlkup);
		wh.waitForPageLoaded();
	}
	if (wh.isElementPresent(chkinddlkupsrch, 2)) {
		wh.waitForPageLoaded();
		Thread.sleep(2000);
		wh.clearElement(chkinddlkupsrch);
		wh.waitForElementPresent(chkinddlkupsrch);
		Thread.sleep(1000);
		wh.sendKeys(chkinddlkupsrch, sDockdoor1);
		}
	if (wh.isElementPresent(chkinddlkupfind, 2)) {
		wh.waitForPageLoaded();
		wh.clickElement(chkinddlkupfind);
		Thread.sleep(1000);
	}
		
	if (wh.isElementPresent(chkinddlkupslct2, 2)) {
		wh.waitForPageLoaded();
		Thread.sleep(2000);
		wh.clickElement(chkinddlkupslct2);
		Thread.sleep(1000);
	}
	
	if (wh.isElementPresent(chkinddlkupslct1, 2)) {
		wh.waitForPageLoaded();
		Thread.sleep(2000);
		wh.clickElement(chkinddlkupslct1);
		Thread.sleep(1000);
	}
	
	if (wh.isElementPresent(listtrailerCondition, 2)) {
	wh.selectValue(listtrailerCondition, "Good");
	wh.waitForPageLoaded();
	}
	if (wh.isElementPresent(chckBoxTrailerList, 2)) {

    	wh.clickElement(chckBoxTrailerList);
    }
	report.addReportStep("Appointment Page, Trailer Details",
			"Appointment Page, Trailer Details", 
			StepResult.DONE);
	wh.waitForPageLoaded();
	wh.clickElement(btnSave1);
	String AppmtSucessFullLbl = wh.getSource();	
	 report.addReportStep("Check-in confirmation Page", "Trailer Checked in Successfully Sucessfully Checked IN" +Strtrailer, StepResult.PASS);
		 Thread.sleep(2000);
 driver.findElement(By.id("er_d1_c1")).click();
 Thread.sleep(3000);
return DockDoor;
}

}
