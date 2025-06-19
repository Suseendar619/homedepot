package com.homer.po.DFWMS;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.homer.po.DFWMS.*;

public class DFWMSRFPageObject extends PageBase {

	public DFWMSRFPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By ChoiceInput = By.xpath(".//INPUT[@id='dataForm:it_1']");
	static final By ChoiceInput1 = By.xpath("//*[@id='dataForm:it_1']");
	static final By PageUp = By.xpath(".//input[@type='button' and @value = 'Page Up' and @title = 'Ctrl-U Page Up']");
	static final By PageDown = By.xpath(".//input[@type='button' and @value = 'Page Down' and @title = 'Ctrl-D Page Down']");
	static final By ChgTaskGrp = By.xpath(".//input[@type='button' and @value = 'Chg Task Grp' and @title = 'Ctrl-T Chg Task Grp']");
	static final By EnterTask = By.xpath(".//input[@type='button' and @value = 'Enter Task' and @title = 'Ctrl-E Enter Task']");
	static final By FindTran = By.xpath(".//input[@type='button' and @value = 'Find Tran' and @title = 'CTRL-F Find Tran']");
	static final By FindTranInput = By.xpath("//input[@id='dataForm:it_1']");
	static final By TaskSelctn = By.xpath(".//input[@type='button' and @value = 'Task Selctn' and @title = 'CTRL-S Task Selctn']");
	static final By ChgTaskGrpInput = By.xpath("//INPUT[@id='dataForm:taskGrp']");
	static final By EnterTaskInput = By.xpath("//input[@id='dataForm:input1']");
	static final By EnterItemBrcd = By.xpath("//input[@id='itemId1Brcd']");
	static final By EnterItemBrcdNC = By.xpath("//input[@id='itemIdBrcd']");
	static final By EnteriLPN = By.xpath("//input[@id='barcode']");
	static final By EnterTote = By.xpath("//input[@id='barcode']");
	static final By LocnBrcd = By.xpath("//input[@id='barcode']");
	static final By DropLocn = By.xpath("//span[@id='dataForm:delLocn']");
	static final By DropLocnBrcd = By.xpath("//input[@id='barcode4']");
	static final By EnterPLTID = By.xpath("//input[@id='barcode2']");
	static final By EnterQTY = By.xpath("//input[@id='input1qty2']");
	static final By EnterToteQTY = By.xpath("//input[@id='input1qty221']");
	static final By PackToteQTY = By.xpath("//INPUT[@id='input1mxQt88']");
	static final By EnterPackoLPN = By.xpath("//input[@id='tote1re']");
	static final By GetLocation = By.xpath("//SPAN[@id='dataForm:item21']");
	static final By GridLocation = By.xpath("//INPUT[@id='dataForm:gridLoc2']");
	static final By GridLocation2 = By.xpath("//INPUT[@id='dataForm:Griduser']");

	static final By RFErrorMsg = By.xpath(".//DIV[@class='error']");

	static final By RFEnter = By.xpath(".//input[@type='button' and @value = 'Enter' and @title = 'CTRL-L Enter']");
	static final By RFEnter1 = By.xpath(".//input[@type='button' and @value = 'Enter']");
	static final By RFExit = By.xpath(".//input[@type='button' and @value = 'Exit' and @title = 'CTRL-X Exit']");
	static final By RFExit1 = By.xpath(".//input[@type='button' and @value = 'Exit']");
	static final By RFExitNC = By.xpath("//INPUT[@id='rfbtn_dataForm:ab2']");
	static final By RFExitTote = By.xpath("//INPUT[@id='rfbtn_dataForm:b2']");
	static final By GridExitTote = By.xpath("//INPUT[@id='rfbtn_dataForm:b12']");
	static final By NewRFAccptProceed = By.xpath("//INPUT[@id='rfbtn_dataForm:InfoAcceptKey']");	
	static final By RFAccptProceed = By.xpath(".//input[@type='button' and @value = 'Accpt/Proceed' and @title = 'CTRL-A Accpt/Proceed']");
	static final By RFAccptProceed1 = By.xpath(".//input[@type='button' and @value = 'Accpt/Proceed']");
	static final By FindTran1 = By.xpath(".//input[@type='button' and @value = 'Find Tran' and @title = 'CTRL-F Find Tran']");
	static final By RFScreenMessage = By.xpath("/html/body/form[@id='dataForm']/div[@id='dataForm:cifcheck1']");
	static final By RFAgiletrailerclick = By.xpath("//*[@id='dataForm:ot1']");
	static final By RFAgiletrailerclick1 = By.xpath("//*[@id='dataForm']");
	static final By RFAgileWarningclick=By.xpath("//*[@id='rfbtn_dataForm:SCEAcceptKey']");
	static final By RFAgileLoadtrlclick=By.xpath("//*[@id='rfbtn_dataForm:b55']");
	static final By RFAgileinfoclick=By.xpath("//*[@id='rfbtn_dataForm:InfoAcceptKey']");
	public static final By IBLPNinput = By.xpath("//*[@id='lpninput']");
	static By Picklocn = By.xpath("//SPAN[@id='dataForm:delLocn']");
	static By PicklocnBCEnt = By.xpath("//*[@id='barcode']");
	static By DRPlocn = By.xpath("//SPAN[@id='dataForm:delLocn']");
	static By DRPlocnBCEnt = By.xpath("//*[@id='barcode4']");
	static By Findtranbtn = By.xpath ("//*[@id='rfbtn_dataForm:b14']");
	static By SChoice = By.xpath("//*[@id='dataForm:it_1']");
	static By SLTLlocBrcd = By.xpath("//*[@id='dataForm:gridLoc2']");
	static By SLTLlocBrcd1 = By.xpath("//*[@id='dataForm:Griduser']");
	static By sEndoLPN= By.xpath("//*[@id='rfbtn_dataForm:InfoAcceptKey']");
	static By sEndTote= By.xpath("//*[@id='rfbtn_dataForm:InfoAcceptKey']");
	public static final By LTOLPN = By.id("HMDPbarcode");
	public static final By AGLOLPN = By.id("test");//*[@id="test"]
	public static final By DDbarcd = By.id("barcode10");
	public static final By DDbarcd1 = By.id("barcode13");
	public static final By sealbarcd = By.id("barcode2");
	public static final By GridExit1  =By.id("rfbtn_dataForm:bdkey2");
	public static final By LTLGdLocnolpnend = By.id("rfbtn_dataForm:InfoAcceptKey");
	public static final By GridExit  =By.id("rfbtn_dataForm:b12");
	public static final By clsGridExit1  =By.id("rfbtn_dataForm:b4");
	public static final By trailerener = By.xpath("//SPAN[@title=''][text()='Dock door:']");
	static final By IBbarcode = By.xpath("//*[@id='DockInp']");
	public static final By rcvdtlexit = By.xpath("//*[@id='rfbtn_dataForm:aa7']");
	public static final By IBGridExit  =By.id("rfbtn_dataForm:b8");
	public static final By IBvrfyasn  =By.id("rfbtn_dataForm:verify_asn_btn");
	public static final By vryasnyesno = By.xpath("//*[@id='dataForm:dtltxt7_b']");
	public static final By lcklpnacptprcd = By.xpath("//*[@id='rfbtn_dataForm:SCEAcceptKey']");
	
	
	
	public String sTaskGroupCode = "";
	public String sFindTranVal = "";
	public String sPutToGrid = "";
	public String sPackFromTote = "";
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static ArrayList sTaskLocnBrcd = new ArrayList();
	public static ArrayList sTaskItemName = new ArrayList();
	public static ArrayList sTaskQTYALLOC = new ArrayList();
	public static ArrayList sTaskCompRef = new ArrayList();
	public static ArrayList siLPNs = new ArrayList();

	public String getiLPncount(String ASNNO) throws Exception {

		String siLPNcountfromDB = "";
		jd.dbDFWMSMapping();
		siLPNcountfromDB = jd.str_Database_Connection("SELECT COUNT(*) from LPN WHERE TC_ASN_ID = '" + ASNNO + "')");
		return (siLPNcountfromDB);

	}
	public ArrayList getilpndetails(String ASNno)throws Exception{
		int iSizeCount=0;
		ArrayList siLPNValuefromDB;
		jd.dbDFWMSMapping();
		siLPNValuefromDB = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ASN_ID = '" + ASNno + "'");
		System.out.println("iLPN's Geneated for ASN " +ASNno +"is" +siLPNValuefromDB);
		siLPNs.add(siLPNValuefromDB.get(iSizeCount));
		iSizeCount++;
		return siLPNs;
		
	}
	
	
	public String getRTEValue(String oLPN9) throws Exception {

		String sRTEValuefromDB = "";
		jd.dbDFWMSMapping();
		sRTEValuefromDB = jd.str_Database_Connection("Select RTE_TO from orders where TC_ORDER_ID = (Select TC_ORDER_ID from LPN where TC_LPN_ID = '" + oLPN9 + "')");
		return (sRTEValuefromDB);

	}

	public void completePickTasks() throws Exception {

		int iLength = DFWMSTasksPageObject.sTaskID.size();
		int iLength2 = 0;
		while (iLength != 0) {

			String sCaseValue = DFWMSTasksPageObject.sTaskType.get(iLength2).toString();
			String sCaseCat = DFWMSTasksPageObject.sTaskCat.get(iLength2).toString();
			//			System.out.println(sCaseCat);
			if (sCaseCat.equalsIgnoreCase("MLT") || sCaseCat.equalsIgnoreCase("MNP")) {
				if(sPutToGrid.isEmpty()){
					sPutToGrid = "Y";
//					System.out.println(sPutToGrid);
				}
			} else if (sCaseCat.equalsIgnoreCase("MLC")) {
				if(sPackFromTote.isEmpty()){
					sPackFromTote = "Y";
//					System.out.println(sPackFromTote);
				}
			}

			switch (sCaseValue){
			case "Voice Pick Slapper":
				getTaskGroupCode();
				changeTaskGroupCode();
				performPSPickTasks(iLength2);
				Thread.sleep(1000);
				break;
			case "Voice Pick Slapper SR":
				getTaskGroupCode();
				changeTaskGroupCode();
				performPSPickTasks(iLength2);
				Thread.sleep(1000);
				break;
			case "Conv Flow 3 PS":
				getTaskGroupCode();
				changeTaskGroupCode();
				performPSPickTasks(iLength2);
				Thread.sleep(1000);
				break;					
			case "Convey Flow 2 SCP HRP":
				getTaskGroupCode();
				changeTaskGroupCode();
				performConveyTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Conv Flow 1 MCP":
				getTaskGroupCode();
				changeTaskGroupCode();
				performConveyTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Convey Flow 3 Other":
				getTaskGroupCode();
				changeTaskGroupCode();
				performConveyTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Pack Cube Dir Noncon":
				sFindTranVal = "Pck Cubed Dir (DF)";
				FindTranandSelect();
				performNonConPickTasks(iLength2);
				wh.clickElement(RFExitNC);
				Thread.sleep(1000);
				break;
			case "Voice Pick to Tote":
				getTaskGroupCode();
				changeTaskGroupCode();
				performConveyTOTEPickTasks(iLength2);
				//					performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Hot Pick Voice Tote":
				getTaskGroupCode();
				changeTaskGroupCode();
				performConveyTOTEPickTasks(iLength2);
				//					performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "VB1/VB2":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "SR1":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "SR8":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Clamp Truck Picking":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Pallet Jack Picking":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Order Picker Picking":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			case "Reach Truck Picking":
				getTaskGroupCode();
				changeTaskGroupCode();
				performTOTEPickTasks(iLength2);
				wh.clickElement(RFExit1);
				Thread.sleep(1000);
				break;
			default:  
				System.out.println("Task Type Not Available - "+sCaseValue);
				break;
			}

			//			}
			ClearTaskDetailsArrayList();
			iLength--;
			iLength2++;				
		}

		if (sPutToGrid.equalsIgnoreCase("Y") || sPackFromTote.equalsIgnoreCase("Y")) {
			DFWMSTasksPageObject staskPO = new DFWMSTasksPageObject(ic);
			staskPO.ClearTaskArrayList();
			packAndPut();
		}

		wh.clickElement(RFExit1);
		Thread.sleep(1000);

	}

	public void getTaskGroupCode() throws Exception {

		DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
		String envrnment = s.readProp();
		if(envrnment.equalsIgnoreCase("Perris_New")||envrnment.equalsIgnoreCase("LG")||
				envrnment.equalsIgnoreCase("LG_New")||envrnment.equalsIgnoreCase("Bulk")|| envrnment.equalsIgnoreCase("Houston_2019")
				|| envrnment.equalsIgnoreCase("Dallas_2019")){
			sTaskGroupCode = "ALL";
		}
		else{
			sTaskGroupCode = "999";
		}

	}

	public void changeTaskGroupCode() throws Exception {

		try {
			wh.clickElement(ChgTaskGrp);
			if (wh.isElementPresent(ChgTaskGrpInput, 2)) {
				wh.sendKeys(ChgTaskGrpInput, sTaskGroupCode);				
				if (wh.isElementPresent(RFEnter, 2)) {
					report.addReportStep("Enter Task Group Code", "Enter task group code - "+sTaskGroupCode, StepResult.PASS);
					wh.clickElement(RFEnter);
					if(wh.isElementPresent(RFErrorMsg, 2)) {
						throw new Exception(wh.getText(RFErrorMsg)+", Task Group Code is "+sTaskGroupCode);
					}
					else {
						report.addReportStep("Change Task Group Code", "Successfully changed task group code", StepResult.PASS);
					}
				}
				else {
					throw new Exception(wh.getText(RFErrorMsg)+", Task Group Code is "+sTaskGroupCode);
				}
			}
			else {
				throw new Exception("Change Task Group page not displaying");
			}				
		} catch (Exception e) {
			String msg = "Unable to change the Task Group Code. " + e.getMessage();
			report.addReportStep("Change Task Group Code", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void performPSPickTasks(int iLength1) throws Exception {

		String sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength1).toString();
		String sTaskItemBrcd = DFWMSTasksPageObject.sTaskItem.get(iLength1).toString();
		String sTaskiLPN = "S00"+sTaskIDExec;

		try {
			wh.clickElement(EnterTask);
			if (wh.isElementPresent(EnterTaskInput, 2)) {
				wh.sendKeys(EnterTaskInput, sTaskIDExec);
				report.addReportStep("Enter Task", "Successfully entered task id "+ sTaskIDExec, StepResult.PASS);
				wh.clickElement(RFEnter);
				//				checkRFErrorWarningMsg("Picking Enter Task ID");
				if (wh.isElementPresent(EnterItemBrcd, 2)) {
					wh.sendKeys(EnterItemBrcd, sTaskItemBrcd);
					report.addReportStep("Enter Item Brcd", "Successfully entered task item brcd "+ sTaskItemBrcd, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterItemBrcd).sendKeys(Keys.ENTER);
					//					checkRFErrorWarningMsg("Picking Enter Item ID");
					if (wh.isElementPresent(EnteriLPN, 2)) {
						wh.sendKeys(EnteriLPN,sTaskiLPN);
						report.addReportStep("Enter iLPN", "Successfully entered task iLPN "+ sTaskiLPN, StepResult.PASS);
						Thread.sleep(1000);
						driver.findElement(EnteriLPN).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						wh.clickElement(NewRFAccptProceed);
						Thread.sleep(1000);
						if (wh.isElementPresent(RFExit1, 2)) {
							wh.clickElement(RFExit1);
						}						
						//checkRFErrorWarningMsg("Picking Enter iLPN ID");
					}
					else {
						throw new Exception("Task page not displaying");
					}
				}
				else {
					throw new Exception("Task page not displaying");
				}
			}
			else {
				throw new Exception("Task page not displaying");
			}				
		} catch (Exception e) {
			String msg = "Unable to proceed Task picking. " + e.getMessage();
			report.addReportStep("Pick Tasks Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void checkRFErrorWarningMsg(String sErrorWarningStep) throws Exception {

		try {
			if(wh.isElementPresent(RFErrorMsg, 2)) {
				String sRFErrorMsg = wh.getText(RFErrorMsg);
				if(wh.getText(RFErrorMsg).contains("Warning")){
					report.addReportStep(sErrorWarningStep, "Warning Shown "+ sRFErrorMsg, StepResult.WARNING);
					wh.focusElement(RFAccptProceed1);
					wh.clickElement(RFAccptProceed1);
				} else if(wh.getText(RFErrorMsg).contains("Complete") || wh.getText(RFErrorMsg).contains("Info")){
					report.addReportStep(sErrorWarningStep, "Info Shown "+ sRFErrorMsg, StepResult.PASS);
					wh.focusElement(RFAccptProceed1);
					wh.clickElement(RFAccptProceed1);					
				} else{
					throw new Exception(wh.getText(RFErrorMsg));
				}
			}

		} catch (Exception e) {
			String msg = "Unable to proceed "+ sErrorWarningStep+ " " + e.getMessage();
			report.addReportStep(sErrorWarningStep, msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void FindTranandSelect() throws Exception {

		try {
			wh.clickElement(FindTran);
			if (wh.isElementPresent(FindTranInput, 2)) {
				wh.sendKeys(FindTranInput, sFindTranVal);				
				if (wh.isElementPresent(FindTran1, 2)) {
					report.addReportStep("Enter Transaction", "Enter Transaction - "+sFindTranVal, StepResult.PASS);
					wh.clickElement(FindTran1);
					if(wh.isElementPresent(RFErrorMsg, 2)) {
						throw new Exception(wh.getText(RFErrorMsg)+", Transaction is "+sFindTranVal);
					}
					else {
						report.addReportStep("Find Transaction", "Successfully found the Transaction", StepResult.PASS);
					}
				}
				else {
					throw new Exception(wh.getText(RFErrorMsg)+", Transaction is "+sFindTranVal);
				}
			}
			else {
				throw new Exception("Find Transaction page not displaying");
			}				
		} catch (Exception e) {
			String msg = "Unable to find the Transaction. " + e.getMessage();
			report.addReportStep("Find Transaction", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void performNonConPickTasks(int iLength1) throws Exception {

		String sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength1).toString();
		String sTaskItemBrcd = DFWMSTasksPageObject.sTaskItem.get(iLength1).toString();
		String sTaskPLTID = "P00"+sTaskIDExec;
		//		String sTaskoLPN = getoLPN(sTaskIDExec);
		//		String sTaskQTY = getQTY(sTaskIDExec);

		GetTaskDetails(sTaskIDExec);
		String sTaskoLPN = DFWMSRFPageObject.sTaskCompRef.get(iLength1).toString();
		String sTaskQTY = DFWMSRFPageObject.sTaskQTYALLOC.get(iLength1).toString();

		try {
			wh.sendKeys(ChoiceInput, "1");
			driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
			if (wh.isElementPresent(EnterPLTID, 2)) {
				wh.sendKeys(EnterPLTID, sTaskPLTID);
				report.addReportStep("Enter Pallet ID", "Successfully entered Pallet id "+ sTaskPLTID, StepResult.PASS);
				wh.clickElement(RFEnter);
				//checkRFErrorWarningMsg("Enter Pallet ID");
				if (wh.isElementPresent(EnteriLPN, 2)) {
					wh.sendKeys(EnteriLPN,sTaskoLPN);
					report.addReportStep("Enter oLPN", "Successfully entered oLPN "+ sTaskoLPN, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnteriLPN).sendKeys(Keys.ENTER);
					//checkRFErrorWarningMsg("Enter oLPN");
					if (wh.isElementPresent(EnterItemBrcdNC, 2)) {
						wh.sendKeys(EnterItemBrcdNC, sTaskItemBrcd);
						report.addReportStep("Enter Item Brcd", "Successfully entered item brcd "+ sTaskItemBrcd, StepResult.PASS);
						Thread.sleep(1000);
						driver.findElement(EnterItemBrcdNC).sendKeys(Keys.ENTER);
						//checkRFErrorWarningMsg("Enter ITem");
						if (wh.isElementPresent(EnterQTY, 2)) {
							wh.sendKeys(EnterQTY,sTaskQTY);
							report.addReportStep("Enter QTY", "Successfully entered QTY "+ sTaskQTY, StepResult.PASS);
							Thread.sleep(1000);
							driver.findElement(EnterQTY).sendKeys(Keys.ENTER);
							checkRFErrorWarningMsg("Info End of oLPN!");
						}
						else {
							throw new Exception("Pack Cubed Dir page not displaying");
						}
					}
					else {
						throw new Exception("Pack Cubed Dir page not displaying");
					}
				}
				else {
					throw new Exception("Pack Cubed Dir page not displaying");
				}
			}
		} catch (Exception e) {
			String msg = "Unable to proceed Pack Cubed Dir picking. " + e.getMessage();
			report.addReportStep("Pack Cubed Dir Task Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void performTOTEPickTasks(int iLength1) throws Exception {

		String sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength1).toString();
		String sTaskTote = "T00"+sTaskIDExec;
		String iPickLoopCount = DFWMSTasksPageObject.sTaskDetailsCount.get(iLength1).toString();
		String sTaskItemBrcd = null;
		String sTaskQTY = null;	
		String sTaskItemLocn = null;
		String sTaskDropLocn = null;
		//		System.out.println(sTaskIDExec+" Task ID, "+sTaskTote+" Tote, "+iPickLoopCount+" No of Tasks");
		GetTaskDetails(sTaskIDExec);

		try {
			wh.clickElement(EnterTask);
			if (wh.isElementPresent(EnterTaskInput, 2)) {
				wh.sendKeys(EnterTaskInput, sTaskIDExec);
				report.addReportStep("Enter Task", "Successfully entered task id "+ sTaskIDExec, StepResult.PASS);
				wh.clickElement(RFEnter);
				//checkRFErrorWarningMsg("Picking Enter Task ID");

				for(int iforloopcount =0;iforloopcount<Integer.parseInt(iPickLoopCount); iforloopcount++) {

					sTaskItemBrcd = sTaskItemName.get(iforloopcount).toString();
					sTaskQTY = sTaskQTYALLOC.get(iforloopcount).toString();
					sTaskItemLocn = sTaskLocnBrcd.get(iforloopcount).toString();

					//					System.out.println(sTaskItemLocn+" Locn, "+sTaskItemBrcd+" Item, "+sTaskQTY+" Qty");

					if (wh.isElementPresent(LocnBrcd, 2)) {
						wh.sendKeys(LocnBrcd, sTaskItemLocn);
						report.addReportStep("Enter Location Brcd", "Successfully entered Location brcd "+ sTaskItemLocn, StepResult.PASS);
						Thread.sleep(1000);
						driver.findElement(LocnBrcd).sendKeys(Keys.ENTER);
						if (wh.isElementPresent(EnterItemBrcd, 2)) {
							wh.sendKeys(EnterItemBrcd, sTaskItemBrcd);
							report.addReportStep("Enter Item Brcd", "Successfully entered Item brcd "+ sTaskItemBrcd, StepResult.PASS);
							Thread.sleep(1000);
							driver.findElement(EnterItemBrcd).sendKeys(Keys.ENTER);
							if (wh.isElementPresent(EnterToteQTY, 2)) {
								wh.sendKeys(EnterToteQTY,sTaskQTY);
								report.addReportStep("Enter Qty", "Successfully entered Qty "+ sTaskQTY, StepResult.PASS);
								Thread.sleep(1000);
								driver.findElement(EnterToteQTY).sendKeys(Keys.ENTER);
								Thread.sleep(1000);
								if (wh.isElementPresent(EnterTote, 2)) {
									wh.sendKeys(EnterTote, sTaskTote);
									report.addReportStep("Enter Tote Brcd", "Successfully entered Tote brcd "+ sTaskTote, StepResult.PASS);
									Thread.sleep(1000);
									driver.findElement(EnterTote).sendKeys(Keys.ENTER);
								}
								else {
									throw new Exception("Tote Entry Task page not displaying");
								}
							}
							else {
								throw new Exception("Quantity Task page not displaying");
							}
						}
						else {
							throw new Exception("Item Pick Task page not displaying");
						}
					}
					else {
						throw new Exception("Task page not displaying");
					}
				}
				if (wh.isElementPresent(DropLocn, 2)) {
					//					System.out.println(wh.getText(DropLocn));
					sTaskDropLocn = getLocn(wh.getText(DropLocn),"DROP");
					wh.sendKeys(DropLocnBrcd, sTaskDropLocn);
					report.addReportStep("Enter Drop Location Brcd", "Successfully entered Drop Location brcd " + sTaskDropLocn, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(DropLocnBrcd).sendKeys(Keys.ENTER);
				}
				else {
					throw new Exception("Tote Entry Task page not displaying");
				}
			}
			else {
				throw new Exception("Task page not displaying");
			}				
		} catch (Exception e) {
			String msg = "Unable to proceed Task picking. " + e.getMessage();
			report.addReportStep("Pick Tasks Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	public void ClearTaskDetailsArrayList(){

		sTaskLocnBrcd.clear();
		sTaskItemName.clear();
		sTaskQTYALLOC.clear();
		sTaskCompRef.clear();

	}

	public void performConveyTOTEPickTasks(int iLength1) throws Exception {

		String sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength1).toString();
		String sTaskTote = "T00"+sTaskIDExec;
		String iPickLoopCount = DFWMSTasksPageObject.sTaskDetailsCount.get(iLength1).toString();
		String sTaskItemBrcd = null;
		String sTaskQTY = null;	
		String sTaskItemLocn = null;
		String sTaskDropLocn = null;
		//		System.out.println(sTaskIDExec+" Locn, "+sTaskTote+" Item, "+iPickLoopCount+" Qty");
		GetTaskDetails(sTaskIDExec);

		try {
			wh.clickElement(EnterTask);
			if (wh.isElementPresent(EnterTaskInput, 2)) {
				wh.sendKeys(EnterTaskInput, sTaskIDExec);
				report.addReportStep("Enter Task", "Successfully entered task id "+ sTaskIDExec, StepResult.PASS);
				wh.clickElement(RFEnter);
				//checkRFErrorWarningMsg("Picking Enter Task ID");
				if (wh.isElementPresent(EnterTote, 2)) {
					wh.sendKeys(EnterTote, sTaskTote);
					report.addReportStep("Enter Tote Brcd", "Successfully entered Tote brcd "+ sTaskTote, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterTote).sendKeys(Keys.ENTER);

					for(int iforloopcount =0;iforloopcount<Integer.parseInt(iPickLoopCount); iforloopcount++) {

						sTaskItemBrcd = sTaskItemName.get(iforloopcount).toString();
						sTaskQTY = sTaskQTYALLOC.get(iforloopcount).toString();
						sTaskItemLocn = sTaskLocnBrcd.get(iforloopcount).toString();

						//					System.out.println(sTaskItemLocn+" Locn, "+sTaskItemBrcd+" Item, "+sTaskQTY+" Qty");

						if (wh.isElementPresent(EnterToteQTY, 2)) {
							wh.sendKeys(EnterToteQTY,sTaskQTY);
							report.addReportStep("Enter Qty", "Successfully entered Qty "+ sTaskQTY, StepResult.PASS);
							Thread.sleep(1000);
							driver.findElement(EnterToteQTY).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
						}
						else {
							throw new Exception("Quantity Task page not displaying");
						}
					}
				}
				else {
					throw new Exception("Tote Task page not displaying");
				}


			}

			else {
				throw new Exception("Task page not displaying");
			}				
		} catch (Exception e) {
			String msg = "Unable to proceed Task picking. " + e.getMessage();
			report.addReportStep("Pick Tasks Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}

	//	public String getoLPN(String sTaskIDExec) throws Exception {
	//
	//		String sOLPValuefromDB = "";
	//		jd.dbDFWMSMapping();
	//		sOLPValuefromDB = jd.str_Database_Connection("select TASK_CMPL_REF_NBR from TASK_DTL where TASK_ID = '" + sTaskIDExec + "'");
	//		return (sOLPValuefromDB);
	//
	//	}
	//
	//	public String getQTY(String sTaskIDExec) throws Exception {
	//
	//		String sQTYValuefromDB = "";
	//		jd.dbDFWMSMapping();
	//		sQTYValuefromDB = jd.str_Database_Connection("select QTY_ALLOC from TASK_DTL where TASK_ID = '" + sTaskIDExec + "'");
	//		return (sQTYValuefromDB);
	//
	//	}

	public String getLocn(String sInputLocn, String sLocType) throws Exception {

		String sLocValuefromDB = "";
		jd.dbDFWMSMapping();
		
		if (sLocType.equalsIgnoreCase("DROP")) {
			
			sLocValuefromDB = jd.str_Database_Connection("select LOCN_BRCD from LOCN_HDR where DSP_LOCN like '" + sInputLocn + "%' AND ROWNUM <= 1");
		
		}
		else if (sLocType.equalsIgnoreCase("PUT")) {
			
			sLocValuefromDB = jd.str_Database_Connection("SELECT LOCN_BRCD FROM LOCN_HDR WHERE LOCN_ID not in (select distinct CURR_SUB_LOCN_ID from LPN where INBOUND_OUTBOUND_INDICATOR = 'O' AND LPN_FACILITY_STATUS < '30' and CURR_SUB_LOCN_ID is not NULL) and LOCN_CLASS = 'S' and DSP_LOCN like '" + sInputLocn + "%' AND ROWNUM <= 1");
		
		}
		
		return (sLocValuefromDB);

	}

	public void GetTaskDetails(String sTaskIDDetailstoGet) throws Exception {

		ArrayList sTaskValuefromDB;
		jd.dbDFWMSMapping();
		sTaskValuefromDB = jd.array_Database_Connection("SELECT LH.LOCN_BRCD,CB.ITEM_NAME,TD.QTY_ALLOC,TD.TASK_CMPL_REF_NBR FROM TASK_DTL TD JOIN ITEM_CBO CB ON CB.ITEM_ID = TD.ITEM_ID JOIN LOCN_HDR LH ON LH.LOCN_ID = TD.PULL_LOCN_ID WHERE TASK_ID = '"+sTaskIDDetailstoGet+"' ORDER BY TASK_SEQ_NBR");
		//		System.out.println(sTaskValuefromDB);
		int iArrayCountDB = sTaskValuefromDB.size();
		int iSizeCount = 0;
		while (iArrayCountDB != 0) {
			sTaskLocnBrcd.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskItemName.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskQTYALLOC.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskCompRef.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
		}

	}

	public void GetPackTaskDetails(String sTaskIDDetailstoGet) throws Exception {

		ArrayList sTaskValuefromDB;
		jd.dbDFWMSMapping();
		sTaskValuefromDB = jd.array_Database_Connection("SELECT TD.CNTR_NBR,CB.ITEM_NAME,TD.QTY_ALLOC,TD.TASK_CMPL_REF_NBR FROM TASK_DTL TD JOIN ITEM_CBO CB ON CB.ITEM_ID = TD.ITEM_ID WHERE TASK_ID = '"+sTaskIDDetailstoGet+"' ORDER BY TD.TASK_SEQ_NBR");
//		System.out.println(sTaskValuefromDB);
		int iArrayCountDB = sTaskValuefromDB.size();
		int iSizeCount = 0;
		while (iArrayCountDB != 0) {
			sTaskLocnBrcd.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskItemName.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskQTYALLOC.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			sTaskCompRef.add(sTaskValuefromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
		}

	}

	public void packAndPut() throws Exception {

		String sTASKGENRTNREFNBR = DFWMSRunWavesPageObject.sWaveNumber;
		ArrayList sTaskListfromDB;
		jd.dbDFWMSMapping();
		sTaskListfromDB = jd.array_Database_Connection("SELECT TASK_ID,TASK_DESC FROM TASK_HDR WHERE TASK_GENRTN_REF_NBR like '"+sTASKGENRTNREFNBR+"%' ORDER BY TASK_ID");
//		System.out.println(sTaskListfromDB);
		int iArrayCountDB = sTaskListfromDB.size();
		int iSizeCount = 0;
		while (iArrayCountDB != 0) {
			DFWMSTasksPageObject.sTaskID.add(sTaskListfromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
			DFWMSTasksPageObject.sTaskType.add(sTaskListfromDB.get(iSizeCount));
			iSizeCount++;
			iArrayCountDB--;
		}
		int iLength = 0;
		iLength = DFWMSTasksPageObject.sTaskID.size();
		int iLength2 = 0;
		while (iLength != 0) {

			String sCaseValue = DFWMSTasksPageObject.sTaskType.get(iLength2).toString();
			switch (sCaseValue){
			case "Put to Grid":
				sFindTranVal = "Put to Grid (DF)";
				FindTranandSelect();					
				String sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength2).toString();
//				System.out.println("Put To Grid Task execution - "+sTaskIDExec);
				GetPackTaskDetails(sTaskIDExec);
				putToGrid();
//				wh.clickElement(RFExitNC);
				Thread.sleep(1000);
				break;
			case "Pack from Tote":
				sFindTranVal = "Pck From Tote";
				FindTranandSelect();					
				sTaskIDExec = DFWMSTasksPageObject.sTaskID.get(iLength2).toString();
//				System.out.println("Pack from Tote - "+sTaskIDExec);
				GetPackTaskDetails(sTaskIDExec);
				packFromTote();
//				wh.clickElement(RFExitNC);
				Thread.sleep(1000);
				break;
			default:  
//				System.out.println("No Put To Grid OR Pack from Tote tasks available");
				break;
			}
			ClearTaskDetailsArrayList();
			iLength--;
			iLength2++;				
		}

	}

	public void putToGrid() throws Exception {

		int iPickLoopCount = sTaskLocnBrcd.size();
		String sTaskDropLocn = "";
		String sPrevTote = "";
		String sCurrTote = "";
//		System.out.println(iPickLoopCount);
		try {

			wh.sendKeys(ChoiceInput, "1");
			driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
			for(int iforloopcount =0;iforloopcount<iPickLoopCount; iforloopcount++) {	

				String sTaskTote = sTaskLocnBrcd.get(iforloopcount).toString();
				String sTaskQTY = sTaskQTYALLOC.get(iforloopcount).toString();	
				String sTaskItem = sTaskItemName.get(iforloopcount).toString();
				String sTaskoLPN = sTaskCompRef.get(iforloopcount).toString();
				int iItemCount = Integer.parseInt(sTaskQTY);
				sCurrTote = sTaskTote;
//				System.out.println(!sPrevTote.equalsIgnoreCase(sCurrTote));
//				System.out.println(iItemCount);

				if (!sPrevTote.equalsIgnoreCase(sCurrTote)) {
					if (wh.isElementPresent(EnterTote, 2)) {
						wh.sendKeys(EnterTote, sTaskTote);
						Thread.sleep(1000);
						report.addReportStep("Enter Tote Brcd", "Successfully entered Tote Brcd "+ sTaskTote, StepResult.PASS);
						wh.clickElement(RFEnter);
						Thread.sleep(1000);
						sPrevTote = sTaskTote;
						//checkRFErrorWarningMsg("Picking Enter Task ID");
					}
					else {
						throw new Exception("Enter Tote page not displaying");
					}
				}
				for(int iforloopcount1=0;iforloopcount1<iItemCount; iforloopcount1++) {
//				System.out.println(iforloopcount1);
				if (wh.isElementPresent(EnterItemBrcd, 2)) {
					wh.sendKeys(EnterItemBrcd, sTaskItem);
					Thread.sleep(1000);
					report.addReportStep("Enter Item Brcd", "Successfully entered Item brcd "+ sTaskItem, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterItemBrcd).sendKeys(Keys.ENTER);
				} else if (wh.isElementPresent(EnterItemBrcdNC, 2)) {
					wh.sendKeys(EnterItemBrcdNC, sTaskItem);
					Thread.sleep(1000);
					report.addReportStep("Enter Item Brcd", "Successfully entered Item brcd "+ sTaskItem, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterItemBrcdNC).sendKeys(Keys.ENTER);
				}
				else {
					throw new Exception("Enter Item page not displaying");
				}

				if (wh.isElementPresent(GetLocation, 2)) {
					//System.out.println(wh.getText(DropLocn));
					sTaskDropLocn = getLocn(wh.getText(GetLocation),"PUT");
					wh.sendKeys(GridLocation, sTaskDropLocn);
					Thread.sleep(1000);
					report.addReportStep("Enter Grid Location Brcd", "Successfully entered Grid Location brcd " + sTaskDropLocn, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(GridLocation).sendKeys(Keys.ENTER);
				} else if (wh.isElementPresent(GridLocation2, 2)){
					wh.sendKeys(GridLocation2, sTaskDropLocn);
					Thread.sleep(1000);
					report.addReportStep("Enter Grid Location Brcd", "Successfully entered Grid Location brcd " + sTaskDropLocn, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(GridLocation2).sendKeys(Keys.ENTER);						
				}else{
					throw new Exception("Grid Location not displaying");
				}
				
				if (wh.isElementPresent(NewRFAccptProceed, 2)) {
					wh.clickElement(NewRFAccptProceed);
				}				
				
				}

			} 
			
			if (wh.isElementPresent(NewRFAccptProceed, 2)) {
				wh.clickElement(NewRFAccptProceed);
			}
			if (wh.isElementPresent(NewRFAccptProceed, 2)) {
				wh.clickElement(NewRFAccptProceed);
			}
			if (wh.isElementPresent(GridExitTote, 2)) {
				wh.clickElement(GridExitTote);
			}

		}

		catch (Exception e) {
			String msg = "Unable to proceed put to grid. " + e.getMessage();
			report.addReportStep("Put to Grid Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}
	
	public void packFromTote() throws Exception {

		int iPickLoopCount = sTaskLocnBrcd.size();
		String sTaskDropLocn = "";
		String sPrevTote = "";
		String sCurrTote = "";
//		System.out.println(iPickLoopCount);
		try {

			wh.sendKeys(ChoiceInput, "1");
			driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
			
			for(int iforloopcount =0;iforloopcount<iPickLoopCount; iforloopcount++) {	

				String sTaskTote = sTaskLocnBrcd.get(iforloopcount).toString();
				String sTaskQTY = sTaskQTYALLOC.get(iforloopcount).toString();	
				String sTaskItem = sTaskItemName.get(iforloopcount).toString();
				String sTaskoLPN = sTaskCompRef.get(iforloopcount).toString();
				int iItemCount = Integer.parseInt(sTaskQTY);
				sCurrTote = sTaskTote;
//				System.out.println(!sPrevTote.equalsIgnoreCase(sCurrTote));
//				System.out.println(iItemCount);

				if (!sPrevTote.equalsIgnoreCase(sCurrTote)) {
					if (wh.isElementPresent(EnterTote, 2)) {
						wh.sendKeys(EnterTote, sTaskTote);
						Thread.sleep(1000);
						report.addReportStep("Enter Tote Brcd", "Successfully entered Tote Brcd "+ sTaskTote, StepResult.PASS);
						wh.clickElement(RFEnter);
						Thread.sleep(1000);
						sPrevTote = sTaskTote;
						//checkRFErrorWarningMsg("Picking Enter Task ID");
					}
					else {
						throw new Exception("Enter Tote page not displaying");
					}
				}
				for(int iforloopcount1=0;iforloopcount1<iItemCount; iforloopcount1++) {
//					System.out.println(iforloopcount1);
				
				if (wh.isElementPresent(EnterItemBrcdNC, 2)) {
					wh.sendKeys(EnterItemBrcdNC, sTaskItem);
					Thread.sleep(1000);
					report.addReportStep("Enter Item Brcd", "Successfully entered Item brcd "+ sTaskItem, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterItemBrcdNC).sendKeys(Keys.ENTER);
				}
				else {
					throw new Exception("Enter Item page not displaying");
				}

				if (wh.isElementPresent(PackToteQTY, 2)) {
					wh.sendKeys(PackToteQTY, sTaskQTY);
					Thread.sleep(1000);
					report.addReportStep("Enter Item Qty", "Successfully entered Item Qty " + sTaskQTY, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(PackToteQTY).sendKeys(Keys.ENTER);
				}
				else{
					throw new Exception("Item Qty not displaying");
				}
				
				if (wh.isElementPresent(EnterPackoLPN, 2)){
					wh.sendKeys(EnterPackoLPN, sTaskoLPN);
					Thread.sleep(1000);
					report.addReportStep("Enter Pack oLPN", "Successfully entered oLPN " + sTaskoLPN, StepResult.PASS);
					Thread.sleep(1000);
					driver.findElement(EnterPackoLPN).sendKeys(Keys.ENTER);						
				}else{
					throw new Exception("Pack oLPN not displaying");
				}
				
				if (wh.isElementPresent(NewRFAccptProceed, 2)) {
					wh.clickElement(NewRFAccptProceed);
				}				
				
				}

			} 
			if (wh.isElementPresent(RFExit1, 2)) {
				wh.clickElement(RFExit1);
			}


		}

		catch (Exception e) {
			String msg = "Unable to proceed pack from tote. " + e.getMessage();
			report.addReportStep("Pack from Tote Failed", msg, StepResult.FAIL);
			rc.logger.info(msg);
			rc.throwTCTerminationException();
		}

	}
	
	public void Load_trailer(ArrayList oLPN11, ArrayList ShpVia, ArrayList sDockdr) throws Throwable {
		//String Dockdoor=DFWMSAPPTCREATIONPageObject.DockDoor;
//		String DockdoorBC="2493634";
//		System.out.println("Dockdoor test "+DockdoorBC);
		//DFWMSoLPNsPageObject oLPNPage3 = new DFWMSoLPNsPageObject(ic);		
		//int iArrayCount3 = DFWMSoLPNsPageObject.getoLPNCount();
		System.out.println("Arraylist in load trailer " +oLPN11);
		int iArraycount3 = oLPN11.size();
		int iFieldVal1=0;
		while (iArraycount3!=0){
		String sOLPN1 = oLPN11.get(iFieldVal1).toString();
		String sDckDR = sDockdr.get(iFieldVal1).toString();
		System.out.println("oLPN value in Load switch " +sOLPN1);
		System.out.println("Dock Door value in Load switch " +sDckDR);
	//	int loopqty4 = Integer.parseInt(iArraycount3);
		
			System.out.println("Array count in Packing_station_validation5 " +iArraycount3);
		String sRouteto = getRTEValue(sOLPN1);
		System.out.println("Route to value from DB "+sRouteto);
		String sShipvia = ShpVia.get(iFieldVal1).toString();
		System.out.println("oLPN status after loading in load trailer validate function " +sShipvia);
		iArraycount3--;
		iFieldVal1++;
		
		if ((sRouteto.equals("FX")|| sRouteto.equals("FG")))
				{
			
			Load_trailer_Agile(oLPN11,sDockdr);
			break;
		} else if ((sRouteto.equals("2")|| sRouteto.equals("3")|| sRouteto.equals("18")))
		{
			Load_trailer_np(oLPN11,sDockdr);
			Thread.sleep(2000);
			close_trailer_np(sDockdr);
			break;
		}else if ((sRouteto.equals("9")|| sRouteto.equals("10")|| sRouteto.equals("11")))
		{
			Load_trailer_PCL(oLPN11,sDockdr);
			break;
		}
	
		}
	}
		
		public void Load_trailer_np(ArrayList oLPN12,ArrayList Dockdr1) throws Throwable {
			//String Dockdoor=DFWMSAPPTCREATIONPageObject.DockDoor;
			//String DockdoorBC="2493634";
			//String DockdoorBC="2266740";
			System.out.println("Dockdoor test in Load trailer main function "+Dockdr1);
			//DFWMSoLPNsPageObject oLPNPage3 = new DFWMSoLPNsPageObject(ic);		
			//int iArrayCount3 = DFWMSoLPNsPageObject.getoLPNCount();
			System.out.println("Arraylist in load trailer " +oLPN12);
			int iArraycount3 = oLPN12.size();
			int iFieldVal1=0;
		//	int loopqty4 = Integer.parseInt(iArraycount3);
			String sOLPN1 = oLPN12.get(iFieldVal1).toString();
			String sdockdorbc = Dockdr1.get(iFieldVal1).toString();
			//String sRouteto = getRTEValue(oLPN12);
			//String sShipvia = ShpVia.get(iFieldVal1).toString();
			
		while (iArraycount3!=0){
				
		getTaskGroupCode();
		changeTaskGroupCode();
		//sFindTranVal = "Load Trailer (DF)";
		sFindTranVal = "Load Trailer";
		System.out.println("find tran value" +sFindTranVal);
		FindTranandSelect();
		if (wh.isElementPresent(ChoiceInput, 2)) {
		wh.sendKeys(ChoiceInput, "2");
		driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
		}
		System.out.println("find tran value" +sFindTranVal);
		if (wh.isElementPresent(LTOLPN, 2)) {
			Thread.sleep(1000);
			wh.sendKeys(LTOLPN, sOLPN1);
			 Thread.sleep(3000);
			    driver.findElement(LTOLPN).sendKeys(Keys.ENTER);
		}
		
		if (wh.isElementPresent(DDbarcd, 2)) {
	//	wh.sendKeys(DDbarcd, DockdoorBC);
			wh.sendKeys(DDbarcd, sdockdorbc);
			System.out.println("Entering Dock Door Barcode : " +sdockdorbc);
	    Thread.sleep(3000);
	    driver.findElement(DDbarcd).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
		}
		if (wh.isElementPresent(LTLGdLocnolpnend, 2)) {
	    wh.clickElement(LTLGdLocnolpnend);
	    Thread.sleep(3000);
		}
			
		iFieldVal1++;
		iArraycount3--;	
	}
		
		
		
		if (wh.isElementPresent(GridExit1, 2)) {
		    wh.clickElement(GridExit1);
		    Thread.sleep(3000);
			}
	}

		
		public void Load_trailer_Agile(ArrayList oLPN12, ArrayList Dckdr1) throws Throwable {

//			String DockdoorBC ="2570020";
//			System.out.println("Dockdoor test in Load trailer main function "+DockdoorBC);
			System.out.println("Arraylist in load trailer " +oLPN12);
			 Robot robot = new Robot();
		     robot.delay(250);
			int iArraycount3 = oLPN12.size();
			int iFieldVal1=0;
			getTaskGroupCode();
			changeTaskGroupCode();
			sFindTranVal = "PCL Load pcl LPN";
			System.out.println("find tran value" +sFindTranVal);
			FindTranandSelect();
			Thread.sleep(2000);
			if (wh.isElementPresent(ChoiceInput1, 2)) {
			wh.sendKeys(ChoiceInput1, "2");
			Thread.sleep(2000);
			driver.findElement(ChoiceInput1).sendKeys(Keys.ENTER);
			report.addReportStep("Enter Load Agile transaction", "Load Agile transaction " , StepResult.PASS);
			}
			System.out.println("find tran value" +sFindTranVal);
			
	    	while (iArraycount3!=0){
			String sOLPN1 = oLPN12.get(iFieldVal1).toString();
			String sDckdr1 = Dckdr1.get(iFieldVal1).toString();
			int iArraycount4 = iArraycount3;
			System.out.println("olpn in agile pcl " +sOLPN1);
			
		if (wh.isElementPresent(DDbarcd, 2)) {
			wh.sendKeys(DDbarcd, sDckdr1);
		    System.out.println("Entering Dock Door Barcode : " +sDckdr1);
		    Thread.sleep(3000);
		    driver.findElement(DDbarcd).sendKeys(Keys.ENTER);
		    report.addReportStep("Enter Load Agile transaction", "Successfully Entered Dock door barcode " +sDckdr1, StepResult.PASS);
		    Thread.sleep(3000);
			}
				
		wh.waitForPageLoaded();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
    	robot.delay(50);
//		if (wh.isElementPresent(RFAgiletrailerclick, 2)) {
//			wh.waitForPageLoaded();
//			 Thread.sleep(3000);
//		    driver.findElement(RFAgiletrailerclick).sendKeys(Keys.ENTER);
//		    //Thread.sleep(3000);
//			}
		
		if (wh.isElementPresent(AGLOLPN, 2)) {
			Thread.sleep(1000);
			wh.sendKeys(AGLOLPN, sOLPN1);
			 Thread.sleep(3000);
			    driver.findElement(AGLOLPN).sendKeys(Keys.ENTER);
			    report.addReportStep("Enter Load Agile transaction", "Successfully Entered oLPN to Load " +sOLPN1, StepResult.PASS);
		}
		
		if (wh.isElementPresent(RFAgileWarningclick, 2)) {
		    wh.clickElement(RFAgileWarningclick);
		    Thread.sleep(3000);
			}
		
		iFieldVal1++;
		iArraycount3--;	

		}
	if (wh.isElementPresent(RFAgileLoadtrlclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileLoadtrlclick);
	    Thread.sleep(3000);
		}
	
	if (wh.isElementPresent(RFAgileWarningclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileWarningclick);
	    Thread.sleep(3000);
		}
	if (wh.isElementPresent(RFAgileinfoclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileinfoclick);
	    Thread.sleep(3000);
		}
	report.addReportStep("Enter Load Agile transaction", "Successfully Loaded the Agile oLPN's " , StepResult.PASS);
			
		
	//}
		
		
		
		if (wh.isElementPresent(GridExit1, 2)) {
		    wh.clickElement(GridExit1);
		    Thread.sleep(3000);
			}
	}
		
		public void Load_trailer_PCL(ArrayList oLPN12, ArrayList Dckdr1) throws Throwable {
//			String DockdoorBC ="2570020";
//			System.out.println("Dockdoor test in Load trailer main function "+DockdoorBC);
			System.out.println("Arraylist in load trailer " +oLPN12);
			 Robot robot = new Robot();
		     robot.delay(250);
			int iArraycount3 = oLPN12.size();
			int iFieldVal1=0;
			getTaskGroupCode();
			changeTaskGroupCode();
			sFindTranVal = "Load Parcel Plt";
			System.out.println("find tran value" +sFindTranVal);
			FindTranandSelect();
			Thread.sleep(2000);
			if (wh.isElementPresent(ChoiceInput1, 2)) {
			wh.sendKeys(ChoiceInput1, "2");
			Thread.sleep(2000);
			driver.findElement(ChoiceInput1).sendKeys(Keys.ENTER);
			report.addReportStep("Enter Load Agile transaction", "Load Agile transaction " , StepResult.PASS);
			}
			System.out.println("find tran value" +sFindTranVal);
			
	    	while (iArraycount3!=0){
			String sOLPN1 = oLPN12.get(iFieldVal1).toString();
			String sDckdr1 = Dckdr1.get(iFieldVal1).toString();
			int iArraycount4 = iArraycount3;
			System.out.println("olpn in agile pcl " +sOLPN1);
			
		if (wh.isElementPresent(DDbarcd, 2)) {
			wh.sendKeys(DDbarcd, sDckdr1);
		    System.out.println("Entering Dock Door Barcode : " +sDckdr1);
		    Thread.sleep(3000);
		    driver.findElement(DDbarcd).sendKeys(Keys.ENTER);
		    report.addReportStep("Enter Load Agile transaction", "Successfully Entered Dock door barcode " +sDckdr1, StepResult.PASS);
		    Thread.sleep(3000);
			}
				
		wh.waitForPageLoaded();
		Thread.sleep(2000);
//		robot.keyPress(KeyEvent.VK_ENTER);
//    	robot.delay(50);
		if(wh.isElementPresent(RFExit1,2)){
			Thread.sleep(1000);
			driver.findElement(RFExit1).sendKeys(Keys.ENTER);
		}
//		if (wh.isElementPresent(RFAgiletrailerclick, 2)) {
//			wh.waitForPageLoaded();
//			 Thread.sleep(3000);
//		    driver.findElement(RFAgiletrailerclick).sendKeys(Keys.ENTER);
//		    //Thread.sleep(3000);
//			}
		
		if (wh.isElementPresent(AGLOLPN, 2)) {
			Thread.sleep(1000);
			wh.sendKeys(AGLOLPN, sOLPN1);
			 Thread.sleep(3000);
			    driver.findElement(AGLOLPN).sendKeys(Keys.ENTER);
			    report.addReportStep("Enter Load Agile transaction", "Successfully Entered oLPN to Load " +sOLPN1, StepResult.PASS);
		}
		
		if (wh.isElementPresent(RFAgileWarningclick, 2)) {
		    wh.clickElement(RFAgileWarningclick);
		    Thread.sleep(3000);
			}
		
		iFieldVal1++;
		iArraycount3--;	

		}
	if (wh.isElementPresent(RFAgileLoadtrlclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileLoadtrlclick);
	    Thread.sleep(3000);
		}
	
	if (wh.isElementPresent(RFAgileWarningclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileWarningclick);
	    Thread.sleep(3000);
		}
	if (wh.isElementPresent(RFAgileinfoclick, 2)) {
		Thread.sleep(2000);
	    wh.clickElement(RFAgileinfoclick);
	    Thread.sleep(3000);
		}
	report.addReportStep("Enter Load Agile transaction", "Successfully Loaded the Agile oLPN's " , StepResult.PASS);
			
		
	//}
		
		
		
		if (wh.isElementPresent(GridExit1, 2)) {
		    wh.clickElement(GridExit1);
		    Thread.sleep(3000);
			}
	}
		
	public void close_trailer_np(ArrayList sDckdr1) throws Throwable {

		System.out.println("Dockdoor test "+sDckdr1);
		int iFieldVal1 = 0;
		String sDckDr2 = sDckdr1.get(iFieldVal1).toString();
		sFindTranVal = "Close Trailer (DF)";
		System.out.println("find tran value" +sFindTranVal);
		FindTranandSelect();
		if (wh.isElementPresent(ChoiceInput, 2)) {
		wh.sendKeys(ChoiceInput, "1");
		driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
		}
		System.out.println("find tran value" +sFindTranVal);
		if (wh.isElementPresent(DDbarcd1, 2)) {
		wh.sendKeys(DDbarcd1, sDckDr2);
	    System.out.println("Entering Dock Door Barcode : " +sDckDr2);
	    Thread.sleep(3000);
	    driver.findElement(DDbarcd1).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
		}
		if (wh.isElementPresent(sealbarcd, 2)) {
			String SEal = "SEAL"+new SimpleDateFormat("ddmmss").format(Calendar.getInstance().getTime());
			Thread.sleep(2000);
			wh.sendKeys(sealbarcd, SEal);
			Thread.sleep(3000);
			driver.findElement(sealbarcd).sendKeys(Keys.ENTER);
	    }
					
		if (wh.isElementPresent(clsGridExit1, 2)) {
		    wh.clickElement(clsGridExit1);
		    Thread.sleep(3000);
			}
	}
	
	public void RCV_DTL(ArrayList siLPNs, ArrayList Dckdr1,String ASNID) throws Throwable {
		getilpndetails(ASNID);
		int iArraycount3 = siLPNs.size();
		System.out.println("ilpn count size from iarraycount " +iArraycount3);
		int iFieldVal1=0;
		int iFieldVal2=0;
		String sDckDR = Dckdr1.get(iFieldVal1).toString();
		getTaskGroupCode();
		changeTaskGroupCode();
		//sFindTranVal = "Load Trailer (DF)";
		sFindTranVal = "Rcv Dtl";
		System.out.println("find tran value" +sFindTranVal);
		FindTranandSelect();
		if (wh.isElementPresent(ChoiceInput, 2)) {
		wh.sendKeys(ChoiceInput, "3");
		driver.findElement(ChoiceInput).sendKeys(Keys.ENTER);
		}
		if(wh.isElementPresent(IBbarcode)){
			wh.sendKeys(IBbarcode, sDckDR);
			driver.findElement(IBbarcode).sendKeys(Keys.ENTER);
		}
		
		while (iArraycount3!=0){
		String siLPN1 = siLPNs.get(iFieldVal2).toString();
		
//		String countvalue = getiLPncount(ASNID);
//		int loopqty = Integer.parseInt(countvalue);
//				while(loopqty!=0){
					if(wh.isElementPresent(IBLPNinput)){
						Thread.sleep(1000);
						wh.sendKeys(IBLPNinput, siLPN1);
						driver.findElement(IBLPNinput).sendKeys(Keys.ENTER);
					}
					
//					loopqty--;
//				}
					iArraycount3--;
					iFieldVal2++;
	}
		if(wh.isElementPresent(IBvrfyasn)){
			Thread.sleep(1000);
			wh.clickElement(IBvrfyasn);
		}
		
		if(wh.isElementPresent(vryasnyesno)){
			Thread.sleep(1000);
			wh.sendKeys(vryasnyesno, "Y");
		}
		
		if(wh.isElementPresent(lcklpnacptprcd)){
			Thread.sleep(1000);
			wh.clickElement(lcklpnacptprcd);
		}
		
		if(wh.isElementPresent(rcvdtlexit)){
			Thread.sleep(1000);
			wh.clickElement(rcvdtlexit);
		}
		if(wh.isElementPresent(IBGridExit)){
			Thread.sleep(1000);
			wh.clickElement(IBGridExit);
		}
	}

}
