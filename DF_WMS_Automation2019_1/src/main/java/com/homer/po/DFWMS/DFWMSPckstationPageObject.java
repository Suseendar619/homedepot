package com.homer.po.DFWMS;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.resuablecomponents.JDBC_Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPckstationPageObject extends PageBase{

	
	public DFWMSPckstationPageObject(InstanceContainer ic) {
		// TODO Auto-generated constructor stub
		super(ic);
	}
	static final By TaskCount = By.xpath(".//span[@class='pagerNoWrap'][1]");
	static final By Pckstnnbr = By.xpath("//*[@id='dataForm:PutPack_INP_PackStatnNbr_input']");
	static final By sngPckstnnbr = By.xpath("//*[@id='dataForm:PutPack_INP_PackStatnNbr_input']");
	static final By Pckstnnbrsubmit = By.xpath("//*[@id='dataForm:submitButton']");

	static final By Pckstnolpnenter = By.xpath("//*[@id='dataForm:PutPack_INP_oLPNNbr']");
	static final By sngPckstnToteenter = By.xpath("//*[@id='dataForm:SinglePack_INP_ToteNbr']");
	static final By PckstnvrfyItemBC = By.xpath("//*[@id='dataForm:PutWallPackStation_Input_VerifyItemBarcode']");
	static final By PckstnMobileverifyall = By.xpath("//*[@id='dataForm:PutWallPackStation_VerifyAll_button']");
	//*[@id="MIDP75"]/a
	static final By sngPckstnItemBC = By.xpath("//*[@id='dataForm:SinglePack_INP_ItemNbrS11']");
	
	static final By PckstnCmplpn = By.xpath("//*[@id='dataForm:PutWallPackStation_CompleteoLPN_button']");
	
	static final By Pckstnsuspendlpn = By.xpath("//*[@id='dataForm:PutWallPackStation_SuspendoLPN_button']");
	static final By Pckstnsplitlpn = By.xpath("//*[@id='dataForm:PutWallPackStation_SplitoLPN_button']");
	static final By Pckstnprntshplabel = By.xpath("//*[@id='dataForm:PutWallPackStation_PrintShippingLabel_button']");
	
	public int iArraycnt1;
	//Single pck station
	public static final By Singlepckstlnk = By.xpath("//*[@id='MIDP73']/a");
	public static final By mobpckstlnk = By.xpath("//*[@id='MIDP74']/a");
	//*[@id="MIDP74"]/a
	public static final By mobpckstsuplnk = By.xpath("//*[@id='MIDP75']/a");
	public static final By Hospckstlnk = By.xpath("//*[@id='MIDP76']/a");
	public static final By lnkMenu = By.xpath("//span[contains(text(),'enu')]");
	
	
	
	JDBC_Connection jd = new JDBC_Connection(ic);
	

	public String getLPNSusflag(ArrayList oLPN4) throws Exception {

		String sOLPnSusflgfromDB = "";
		jd.dbDFWMSMapping();
		sOLPnSusflgfromDB = jd.str_Database_Connection("Select MISC_INSTR_CODE_4 from LPN where TC_LPN_ID = '" + oLPN4 + "'");
		return (sOLPnSusflgfromDB);

	}
	
	public String getLPNQTY(String oLPN2) throws Exception {

		String sLPNQTYValuefromDB = "";
		jd.dbDFWMSMapping();
		sLPNQTYValuefromDB = jd.str_Database_Connection("Select TOTAL_LPN_QTY from LPN where TC_LPN_ID = '" + oLPN2 + "'");
		return (sLPNQTYValuefromDB);

	}
	
	
	public String getLPNTOTE(String oLPNno) throws Exception{
		String sLPNTOTEfromDBlpn = "";
		jd.dbDFWMSMapping();
		sLPNTOTEfromDBlpn = jd.str_Database_Connection("SELECT CNTR_NBR FROM TASK_DTL WHERE TASK_CMPL_REF_NBR = (SELECT TC_LPN_ID FROM LPN WHERE TC_LPN_ID = '" + oLPNno + "')");
		return (sLPNTOTEfromDBlpn);
		//return oLPNno;
		
	}
	
	public String getLPNChute(String oLPNno) throws Exception{
		String sCATValuefromDBlpn = "";
		jd.dbDFWMSMapping();
		sCATValuefromDBlpn = jd.str_Database_Connection("Select CHUTE_ASSIGN_TYPE from LPN where TC_LPN_ID = '" + oLPNno + "'");
		return (sCATValuefromDBlpn);
		//return oLPNno;
		
	}
	
	public String getLPNstatus1(String oLPNno) throws Exception{
		String sLPNstatusfromDBlpn = "";
		jd.dbDFWMSMapping();
		sLPNstatusfromDBlpn = jd.str_Database_Connection("Select LPN_FACILITY_STATUS from LPN where TC_LPN_ID = '" + oLPNno + "'");
		return (sLPNstatusfromDBlpn);
		//return oLPNno;
		
	}
		
	public String getItemBCLPN(String oLPNno) throws Exception {

		String sLPNITEMBCfromDB = "";
		jd.dbDFWMSMapping();
		//sOLPValuefromDB = jd.str_Database_Connection("select TASK_CMPL_REF_NBR from TASK_DTL where TASK_ID = '" + sTaskIDExec + "'");
		sLPNITEMBCfromDB = jd.str_Database_Connection("select ITEM_BAR_CODE from ITEM_CBO where ITEM_ID = (Select Item_id from LPN where TC_LPN_ID = '"+ oLPNno + "')");
		return (sLPNITEMBCfromDB);
	}
	
	public String getMultiItemcnt(String oLPNno) throws Exception {

		String smultiitemcntfromDB = "";
		//String sItemBCfromDB = "";
		jd.dbDFWMSMapping();
		smultiitemcntfromDB = jd.str_Database_Connection("Select count(Item_ID) from LPN where TC_LPN_ID = '"+ oLPNno + "'");
		return (smultiitemcntfromDB);
	}
	

public void Packing_station_validation5(ArrayList oLPN10) throws Exception {
	
	
	DFWMSoLPNsPageObject oLPNPage1 = new DFWMSoLPNsPageObject(ic);		
	//int iArrayCount1 = oLPNPage1.getoLPNCount();
	int iArrayCount1 = oLPN10.size();
	
	System.out.println("Array count in Packing_station_validation5 " +iArrayCount1);
	int iField1 = 0;
	String sOLPN1 = oLPN10.get(iField1).toString();
	System.out.println("OLPN in packing station " +sOLPN1);
	while (iArrayCount1 != 0) {
	System.out.println("Array count in Packing_station_validation5 " +iArrayCount1);
	//System.out.println("Array count in Packing_station_validation5 " +oLPN10.get(1).toString());
//	String sOLPNChutetype = getLPNChute(oLPN10.get(iField1).toString());
//	System.out.println("Chute Type " +sOLPNChutetype);
//	String sLPNTote = getLPNTOTE(oLPN10.get(iField1).toString());
//	String sLPNStatus = getLPNstatus1(oLPN10.get(iField1).toString());
//	String solpnSuspendflag = getLPNSusflag(oLPN10.get(iField1).toString());
	
	
	String sOLPNChutetype = getLPNChute(sOLPN1);
	System.out.println("Chute Type " +sOLPNChutetype);
	String sLPNTote = getLPNTOTE(sOLPN1);
	String sLPNStatus = getLPNstatus1(sOLPN1);
	//String solpnSuspendflag = getLPNSusflag(sOLPN1.get(iField1).toString());
	
	System.out.println("Chute Type " +sOLPNChutetype);
	//System.out.println("oLPN Suspend Flag "+solpnSuspendflag);
	System.out.println("Tote value from DB "+sLPNTote);
	System.out.println("LPN status from DB "+sLPNStatus);
	int lpnstatus12 = Integer.parseInt(sLPNStatus);
	iField1++;
	iArrayCount1--;
	System.out.println("Array count in Packing_station_validation5 after execution " +iArrayCount1);
	System.out.println("iField1 count in Packing_station_validation5 after execution " +iField1);
	
					if (!(sOLPNChutetype.equals("MLT") || sOLPNChutetype.equals("MNP") || sOLPNChutetype.equals("PAX")))
			//if (sTaskCAT == "SCP")
		{
						if (lpnstatus12 == 0){
			
			SinglePckStation(oLPN10);
			System.out.println("Chute Assignment Type Singles packing station" +sOLPNChutetype);
			//break;
						}
		}
//			else if (sOLPNChutetype.equals("MLT") || sOLPNChutetype.equals("MNP"))
//		{
				if (lpnstatus12 == 20){
			MobilePckStation4(oLPN10);
			System.out.println("Chute Assignment mobile packing station Type " +sOLPNChutetype);
			break;
		}
//		}
			else
		
		System.out.println("PAX CAT not required Packing " +sOLPNChutetype);

		}

}
	
	
	
	
	public void SinglePckStation(ArrayList oLPNno5) throws Exception {
//		// TODO Auto-generated method stub
		
		DFWMSoLPNsPageObject oLPNPage2 = new DFWMSoLPNsPageObject(ic);		
		//int iArrayCount3 = oLPNPage2.getoLPNCount();
		int iArrayCount3 = oLPNno5.size();
		String Pckstnno = "011";
		int iFieldVal1=0;
	
		while (iArrayCount3!=0){
			String sToteDB = getLPNTOTE(oLPNno5.get(iFieldVal1).toString());
			String solpnSuspendflag = getLPNSusflag(oLPNno5);
			String SLPNQty = getLPNQTY(oLPNno5.get(iFieldVal1).toString());
			String SLPNItemBC = getItemBCLPN(oLPNno5.get(iFieldVal1).toString());
			String sMultiVerifyall1 = getMultiItemcnt(oLPNno5.get(iFieldVal1).toString());
			
		int loopqty = Integer.parseInt(SLPNQty);
		int itemctqty = Integer.parseInt(sMultiVerifyall1);
		System.out.println("Loopqty "+loopqty);
		System.out.println("sMultiVerifyall " +itemctqty);
		try {
			System.out.println("inside try function");
//			if (wh.isElementPresent(lnkMenu, 2)) {
//				wh.clickElement(lnkMenu);
//				
//			}
//			if (wh.isElementPresent(Singlepckstlnk, 2)) {
//				wh.clickElement(Singlepckstlnk);
//				
//			}
			
			DFWMSHomePageObject Singlepacking = new DFWMSHomePageObject(ic);
			Singlepacking.WMSmenu();
			Singlepacking.searchInput("SINGLES Packing Station", "Distribution");
			
			if (wh.isElementPresent(sngPckstnnbr, 2)) {
				wh.sendKeys(sngPckstnnbr, Pckstnno);
				System.out.println("Packing station no" +Pckstnno);
				report.addReportStep("Enter Packing Station no", "Successfully entered Packing station no "+ Pckstnno, StepResult.PASS);
				Thread.sleep(2000);
				driver.findElement(sngPckstnnbr).sendKeys(Keys.ENTER);
			}
			if (wh.isElementPresent(Pckstnnbrsubmit, 2)) {
				Thread.sleep(2000);
				wh.clickElement(Pckstnnbrsubmit);
				report.addReportStep("Enter Packing Station submit button", "Successfully entered Packing station submit ", StepResult.PASS);
			}
			if (wh.isElementPresent(sngPckstnToteenter, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(sngPckstnToteenter, sToteDB);
				Thread.sleep(1000);
				driver.findElement(sngPckstnToteenter).sendKeys(Keys.ENTER);
				report.addReportStep("Enter Packing Station oLPN no button", "Successfully entered Packing station Tote NO "+ sngPckstnToteenter, StepResult.PASS);
			}
				if (wh.isElementPresent(sngPckstnItemBC, 2)) {
					Thread.sleep(1000);
					wh.sendKeys(sngPckstnItemBC, SLPNItemBC);
					report.addReportStep("Enter Item Barcode", "Successfully entered Item brcd "+ SLPNItemBC, StepResult.PASS);
					driver.findElement(sngPckstnItemBC).sendKeys(Keys.ENTER);
				}
			
			
			if (wh.isElementPresent(Pckstnprntshplabel, 2)) {
				report.addReportStep("Enter Transaction", "Enter Transaction - Verify Shipping Label", StepResult.PASS);
				wh.clickElement(Pckstnprntshplabel);
				System.out.println("Complete oLPN Put to grid");
				}
			iFieldVal1++;
			iArrayCount3--;
		}catch (Exception e) {
			System.out.println("Packing station Catch");
			report.addReportStep("Navigate to Packing station screen","Unable to navigate to put to grid screen - " + e.getMessage(),StepResult.WARNING);
//			//rc.throwTCTerminationException();
	
	}}
}
	
	public void MobilePckStation4(ArrayList oLPNno1) throws Exception {
		// TODO Auto-generated method stub
		
		DFWMSoLPNsPageObject oLPNPage2 = new DFWMSoLPNsPageObject(ic);		
		//int iArrayCount3 = oLPNPage2.getoLPNCount();
		int iArrayCount3 = oLPNno1.size();
		String Pckstnno = "011";
		int iFieldVal1=0;
		System.out.println("iarray count in mobile packing station " +iArrayCount3);
		
		while (iArrayCount3!=0){
//			String sOLPN1 = oLPNPage2.soLPNs.get(iFieldVal1).toString();
//			String solpnSuspendflag = getLPNSusflag(oLPNno1.get(iFieldVal1).toString());
			String sOLPN1 = oLPNno1.get(iFieldVal1).toString();
			String solpnSuspendflag = getLPNSusflag(oLPNno1);

		System.out.println("Packing Station no " +Pckstnno);
		
			String SLPNQty = getLPNQTY(oLPNno1.get(iFieldVal1).toString());
			String SLPNItemBC = getItemBCLPN(oLPNno1.get(iFieldVal1).toString());
			String sMultiVerifyall1 = getMultiItemcnt(oLPNno1.get(iFieldVal1).toString());
			
		int loopqty = Integer.parseInt(SLPNQty);
		int itemctqty = Integer.parseInt(sMultiVerifyall1);
		System.out.println("Loopqty "+loopqty);
		System.out.println("sMultiVerifyall " +itemctqty);
		try {
			
			if (itemctqty == 0){
				System.out.println("inside if function itemcount");
//				if (wh.isElementPresent(lnkMenu, 2)) {
//					wh.clickElement(lnkMenu);
//					
//				}
//				if (wh.isElementPresent(mobpckstsuplnk, 2)) {
//					wh.clickElement(mobpckstsuplnk);
//					
//				}
				DFWMSHomePageObject Mobilepacking = new DFWMSHomePageObject(ic);
				Mobilepacking.WMSmenu();
				Mobilepacking.searchInput("MOBILE Packing Station (Supervisor)", "Distribution");
				
				if (wh.isElementPresent(Pckstnnbr, 2)) {
					wh.sendKeys(Pckstnnbr, Pckstnno);
//					wh.sendKeys(Pckstnnbr, "011");
					System.out.println("Packing station no" +Pckstnno);
					report.addReportStep("Enter Packing Station no", "Successfully entered Packing station no "+ Pckstnno, StepResult.PASS);
					Thread.sleep(2000);
					driver.findElement(Pckstnnbr).sendKeys(Keys.ENTER);
				}
				if (wh.isElementPresent(Pckstnnbrsubmit, 2)) {
					Thread.sleep(2000);
					wh.clickElement(Pckstnnbrsubmit);
					report.addReportStep("Enter Packing Station submit button", "Successfully entered Packing station submit "+ Pckstnno, StepResult.PASS);
				}
				if (wh.isElementPresent(Pckstnolpnenter, 2)) {
					Thread.sleep(2000);
					wh.sendKeys(Pckstnolpnenter, sOLPN1);
					Thread.sleep(1000);
					driver.findElement(Pckstnolpnenter).sendKeys(Keys.ENTER);
					report.addReportStep("Enter Packing Station oLPN no button", "Successfully entered Packing station oLPN NO "+ Pckstnno, StepResult.PASS);
				}
				
				System.out.println("Verify all if loop");
				if (wh.isElementPresent(PckstnMobileverifyall, 2)) {
					Thread.sleep(2000);
					wh.clickElement(PckstnMobileverifyall);
					report.addReportStep("Enter Transaction", "Multi Item - Verify ALL", StepResult.PASS);
					System.out.println("Multi Item - Verify ALL");
					}
			}
			else {
		
			System.out.println("inside try function");
//			if (wh.isElementPresent(lnkMenu, 2)) {
//				wh.clickElement(lnkMenu);
//				
//			}
//			if (wh.isElementPresent(mobpckstlnk, 2)) {
//				wh.clickElement(mobpckstlnk);
//				
//			}
			
			DFWMSHomePageObject Mobilepacking = new DFWMSHomePageObject(ic);
			Mobilepacking.WMSmenu();
			Mobilepacking.searchInput("MOBILE Packing Station", "Distribution");
			if (wh.isElementPresent(Pckstnnbr, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(Pckstnnbr, Pckstnno);
//				wh.sendKeys(Pckstnnbr, "011");
				System.out.println("Packing station no in else" +Pckstnno);
				report.addReportStep("Enter Packing Station no", "Successfully entered Packing station no "+ Pckstnno, StepResult.PASS);
				Thread.sleep(2000);
				driver.findElement(Pckstnnbr).sendKeys(Keys.ENTER);
			}
			if (wh.isElementPresent(Pckstnnbrsubmit, 2)) {
				Thread.sleep(2000);
				wh.waitForPageLoaded();
				wh.clickElement(Pckstnnbrsubmit);
				report.addReportStep("Enter Packing Station submit button", "Successfully entered Packing station submit "+ Pckstnno, StepResult.PASS);
			}
			if (wh.isElementPresent(Pckstnolpnenter, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(Pckstnolpnenter, sOLPN1);
				Thread.sleep(1000);
				driver.findElement(Pckstnolpnenter).sendKeys(Keys.ENTER);
				report.addReportStep("Enter Packing Station oLPN no button", "Successfully entered Packing station oLPN NO "+ Pckstnno, StepResult.PASS);
			}

					while (loopqty != 0) {
				System.out.println("mobile packing station while loop loopqty" +loopqty);
				if (wh.isElementPresent(PckstnvrfyItemBC, 2)) {
					Thread.sleep(2000);
					wh.waitForPageLoaded();
					wh.sendKeys(PckstnvrfyItemBC, SLPNItemBC);
					report.addReportStep("Enter Item Barcode", "Successfully entered Item brcd "+ SLPNItemBC, StepResult.PASS);
					driver.findElement(PckstnvrfyItemBC).sendKeys(Keys.ENTER);
				}
				loopqty--;
			}
			
			if (wh.isElementPresent(PckstnCmplpn, 2)) {
				report.addReportStep("Enter Transaction", "Enter Transaction - Complete oLPN", StepResult.PASS);
				Thread.sleep(2000);
				wh.waitForPageLoaded();
				wh.clickElement(PckstnCmplpn);
				System.out.println("Complete oLPN Put to grid");
				}
			}
			
			if (wh.isElementPresent(Pckstnprntshplabel, 2)) {
				Thread.sleep(2000);
				wh.waitForPageLoaded();
				report.addReportStep("Enter Transaction", "Enter Transaction - Verify Shipping Label", StepResult.PASS);
				wh.clickElement(Pckstnprntshplabel);
				}
			iFieldVal1++;
			iArrayCount3--;

		System.out.println("array count after executed in loop " +iArrayCount3);
		System.out.println("ifield value after executed in loop " +iFieldVal1);
		}catch (Exception e) {
			System.out.println("Packing station Catch");
			report.addReportStep("Navigate to Packing station screen","Unable to navigate to put to grid screen - " + e.getMessage(),StepResult.WARNING);
		}

		}

}
	
	public void Packing_station_suspend_validation(ArrayList oLPN4, ArrayList Status2) throws Exception {
		
		DFWMSoLPNsPageObject oLPNPage2 = new DFWMSoLPNsPageObject(ic);		
		//int iArrayCount3 = oLPNPage2.getoLPNCount();
		int iArrayCount3 = oLPN4.size();
		String Pckstnno = "011";
		int iFieldVal1=0;
		
		System.out.println("iarray count in mobile packing station " +iArrayCount3);
		while (iArrayCount3!=0){
			String sOLPN1 = oLPNPage2.soLPNs.get(iFieldVal1).toString();
			String solpnSuspendflag = getLPNSusflag(oLPN4);

		System.out.println("Packing Station no " +Pckstnno);
		
			String SLPNQty = getLPNQTY(oLPN4.get(iFieldVal1).toString());
			String SLPNItemBC = getItemBCLPN(oLPN4.get(iFieldVal1).toString());
			String sMultiVerifyall1 = getMultiItemcnt(oLPN4.get(iFieldVal1).toString());
			
		int loopqty = Integer.parseInt(SLPNQty);
		int itemctqty = Integer.parseInt(sMultiVerifyall1);
		System.out.println("Loopqty "+loopqty);
		System.out.println("sMultiVerifyall " +itemctqty);
		try {
			
				
			System.out.println("inside try function");
			System.out.println("inside try function");
			if (wh.isElementPresent(lnkMenu, 2)) {
				wh.clickElement(lnkMenu);
				
			}
			if (wh.isElementPresent(mobpckstlnk, 2)) {
				Thread.sleep(2000);
				wh.clickElement(mobpckstlnk);
				
			}
			if (wh.isElementPresent(Pckstnnbr, 2)) {
				wh.sendKeys(Pckstnnbr, Pckstnno);
//				wh.sendKeys(Pckstnnbr, "011");
				System.out.println("Packing station no" +Pckstnno);
				report.addReportStep("Enter Packing Station no", "Successfully entered Packing station no "+ Pckstnno, StepResult.PASS);
				Thread.sleep(2000);
				driver.findElement(Pckstnnbr).sendKeys(Keys.ENTER);
			}
			if (wh.isElementPresent(Pckstnnbrsubmit, 2)) {
				Thread.sleep(2000);
				wh.clickElement(Pckstnnbrsubmit);
				report.addReportStep("Enter Packing Station submit button", "Successfully entered Packing station submit "+ Pckstnno, StepResult.PASS);
			}
			if (wh.isElementPresent(Pckstnolpnenter, 2)) {
				Thread.sleep(2000);
				wh.sendKeys(Pckstnolpnenter, sOLPN1);
				Thread.sleep(1000);
				System.out.println("sOLPN number" +sOLPN1);
				driver.findElement(Pckstnolpnenter).sendKeys(Keys.ENTER);
				report.addReportStep("Enter Packing Station oLPN no button", "Successfully entered Packing station oLPN NO "+ Pckstnno, StepResult.PASS);
			}
			if (wh.isElementPresent(Pckstnsuspendlpn, 2)) {
				Thread.sleep(2000);
				wh.clickElement(Pckstnsuspendlpn);
				report.addReportStep("Enter Packing Station suspend button", "Successfully suspended oLPN in Packing station submit ", StepResult.PASS);
			}
			
			
			if (solpnSuspendflag != null){
				System.out.println("suspend flag " +solpnSuspendflag);
				if (wh.isElementPresent(lnkMenu, 2)) {
					wh.clickElement(lnkMenu);
					
				}
				if (wh.isElementPresent(Hospckstlnk, 2)) {
					wh.clickElement(Hospckstlnk);
					
				}
				if (wh.isElementPresent(Pckstnnbr, 2)) {
					wh.sendKeys(Pckstnnbr, Pckstnno);
//					wh.sendKeys(Pckstnnbr, "011");
					System.out.println("Packing station no" +Pckstnno);
					report.addReportStep("Enter Packing Station no", "Successfully entered Hospital Packing station no "+ Pckstnno, StepResult.PASS);
					Thread.sleep(2000);
					driver.findElement(Pckstnnbr).sendKeys(Keys.ENTER);
				}
				if (wh.isElementPresent(Pckstnnbrsubmit, 2)) {
					Thread.sleep(2000);
					wh.clickElement(Pckstnnbrsubmit);
					report.addReportStep("Enter Packing Station submit button", "Successfully entered Hospital Packing station submit "+ Pckstnno, StepResult.PASS);
				}
				if (wh.isElementPresent(Pckstnolpnenter, 2)) {
					Thread.sleep(2000);
					wh.sendKeys(Pckstnolpnenter, sOLPN1);
					Thread.sleep(1000);
					driver.findElement(Pckstnolpnenter).sendKeys(Keys.ENTER);
					report.addReportStep("Enter Packing Station oLPN no button", "Successfully entered Packing station oLPN NO "+ Pckstnno, StepResult.PASS);
				}
			}
			
			while (loopqty != 0) {
				System.out.println("mobile packing station while loop loopqty" +loopqty);
				if (wh.isElementPresent(PckstnvrfyItemBC, 2)) {
					Thread.sleep(1000);
					wh.sendKeys(PckstnvrfyItemBC, SLPNItemBC);
					report.addReportStep("Enter Item Barcode", "Successfully entered Item brcd in Hospital Packing station"+ SLPNItemBC, StepResult.PASS);
					driver.findElement(PckstnvrfyItemBC).sendKeys(Keys.ENTER);
				}
				loopqty--;
			}
			
			if (wh.isElementPresent(PckstnCmplpn, 2)) {
				report.addReportStep("Enter Transaction", "Hospital Packing station - Complete oLPN", StepResult.PASS);
				wh.clickElement(PckstnCmplpn);
				System.out.println("Complete oLPN Put to grid");
				}
			
			if (wh.isElementPresent(Pckstnprntshplabel, 2)) {
				report.addReportStep("Enter Transaction", "Hospital Packing station - Verify Shipping Label", StepResult.PASS);
				wh.clickElement(Pckstnprntshplabel);
				System.out.println("Complete oLPN Put to grid");
				}
			iFieldVal1++;
			iArrayCount3--;
//		
		}catch (Exception e) {
			System.out.println("Packing station Catch");
			report.addReportStep("Navigate to Packing station screen","Unable to navigate to put to grid screen - " + e.getMessage(),StepResult.WARNING);
//			//rc.throwTCTerminationException();
	
	}

		
	
	}
		

//	public void Packing_station_suspend_validation(ArrayList soLPNs) {
//		// TODO Auto-generated method stub
//		
//	}
	
}}

