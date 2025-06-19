package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homedepot.rf.TerminalAutomation.TerminalAutomation;
import com.homedepot.rf.TerminalAutomation.Utils.Utilities;
import com.homedepot.rf.TerminalAutomation.dto.ResponseDTO;
import com.homedepot.rf.TerminalAutomation.ssh.SSHChannel;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSTasksPageObject extends PageBase {

	public DFWMSTasksPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By TaskCount = By.xpath(".//span[@class='pagerNoWrap'][1]");
	static By TaskID = By
			.xpath(".//span[@id='dataForm:lview:dataTable:0:taskIdVal']");
	
	static By TaskStatus = By
			.xpath(".//span[@id='dataForm:lview:dataTable:0:statusVal']");
	static By TaskCheckBox = By
			.xpath(".//input[@id='checkAll_c0_dataForm:lview:dataTable']");
	static By TaskItem = By
			.xpath(".//*[@id='dataForm:lview:dataTable:0:ItemBOMDetailsListEV_item_popup_button']");
	static final By ReleaseTask = By
			.xpath(".//input[contains(@id,'rmButton_1ReleaseTask1')]");
	static final By ReleaseTaskOK = By.xpath(".//span[contains(text(),'OK')]");
	static final By ReleaseTaskOKClose = By
			.xpath(".//IMG[@src='/lps/resources/common/images/close.gif']");

	static By TaskType = By.xpath(".//span[@id='dataForm:lview:dataTable:0:descVal1']");
	static By TaskCat = By.xpath(".//span[@id='dataForm:lview:dataTable:0:HMDPdescVal3']");	
	static By TaskStartWG = By.xpath(".//span[@id='dataForm:lview:dataTable:0:descVal10']");
	static By TaskStartWA = By.xpath(".//span[@id='dataForm:lview:dataTable:0:descVal10WA']");

	public int iArrayCount;
	int iFieldVal;
	

	
	
//	public static String[] sTaskID = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskStatus = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskItem = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskType = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskCat = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskStartWG = new String[DFWMSRunWavesPageObject.sTasksVal];
//	public static String[] sTaskStartWA = new String[DFWMSRunWavesPageObject.sTasksVal];
	
	public static ArrayList sTaskID = new ArrayList();
	public static ArrayList sTaskStatus = new ArrayList();
	public static ArrayList sTaskItem = new ArrayList();
	public static ArrayList sTaskType = new ArrayList();
	public static ArrayList sTaskCat = new ArrayList();
	public static ArrayList sTaskStartWG = new ArrayList();
	public static ArrayList sTaskStartWA = new ArrayList();
	public static ArrayList sTaskDetailsCount = new ArrayList();
	
	JDBC_Connection jd = new JDBC_Connection(ic);

	public void SelectandReleaseTaskList() throws Exception {

		try {

			iArrayCount = getTaskCount();

			iFieldVal = 0;

			while (iArrayCount != 0) {
				TaskID = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":taskIdVal']");
				TaskStatus = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":statusVal']");
				TaskCheckBox = By.xpath(".//input[@id='checkAll_c"+iFieldVal+"_dataForm:lview:dataTable']");
				TaskItem = By.xpath(".//*[@id='dataForm:lview:dataTable:"+iFieldVal+":ItemBOMDetailsListEV_item_popup_button']");
				TaskType = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal1']");
				TaskCat = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":HMDPdescVal3']");	
				TaskStartWG = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal10']");
				TaskStartWA = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal10WA']");


				if (wh.isElementPresent(TaskID, 5)) {
					sTaskID.add(wh.getText(TaskID));
					sTaskItem.add(wh.getText(TaskItem));
					sTaskStatus.add(wh.getText(TaskStatus));
					sTaskType.add(wh.getText(TaskType));
					sTaskCat.add(wh.getText(TaskCat));
					sTaskStartWG.add(wh.getText(TaskStartWG));
					sTaskStartWA.add(wh.getText(TaskStartWA));
//					validateTaskType(wh.getText(TaskID),wh.getText(TaskType),"Pack Cube Dir Noncon"); - Not Required, Only for Pack Bypass Flows
					wh.clickElement(TaskCheckBox);
					sTaskDetailsCount.add(GetTaskDetailsCount(wh.getText(TaskID)));
					Thread.sleep(2000);
				}
				iArrayCount--;
				iFieldVal++;
			}			
			wh.clickElement(ReleaseTask);
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			wh.handleAlert();
			wh.clickElement(ReleaseTaskOK);
			Thread.sleep(1000);
			wh.clickElement(ReleaseTaskOKClose);
			Thread.sleep(1000);
			validateTaskStatus("Released");
		}

		catch (Exception e) {
			report.addReportStep("Select & Release Task",
					"Unable to Release Task - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}
	
	public void GetTaskDetails() throws Exception {

		try {

			iArrayCount = getTaskCount();
			iFieldVal = 0;
			while (iArrayCount != 0) {
				
				TaskID = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":taskIdVal']");
				TaskStatus = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":statusVal']");
				TaskCheckBox = By.xpath(".//input[@id='checkAll_c"+iFieldVal+"_dataForm:lview:dataTable']");
				TaskItem = By.xpath(".//*[@id='dataForm:lview:dataTable:"+iFieldVal+":ItemBOMDetailsListEV_item_popup_button']");
				TaskType = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal1']");
				TaskCat = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":HMDPdescVal3']");	
				TaskStartWG = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal10']");
				TaskStartWA = By.xpath(".//span[@id='dataForm:lview:dataTable:"+iFieldVal+":descVal10WA']");

				
				if (wh.isElementPresent(TaskID, 5)) {

					sTaskID.add(wh.getText(TaskID));
					sTaskItem.add(wh.getText(TaskItem));
					sTaskStatus.add(wh.getText(TaskStatus));
					sTaskType.add(wh.getText(TaskType));
					sTaskCat.add(wh.getText(TaskCat));
					sTaskStartWG.add(wh.getText(TaskStartWG));
					sTaskStartWA.add(wh.getText(TaskStartWA));
//					validateTaskType(wh.getText(TaskID),wh.getText(TaskType),"Pack Cube Dir Noncon");
					sTaskDetailsCount.add(GetTaskDetailsCount(wh.getText(TaskID)));
				}
				iArrayCount--;
				iFieldVal++;
			}

		}
		catch (Exception e) {
			report.addReportStep("Get Task Details",
					"Unable to fetch the Task details - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}

	public int getTaskCount() throws Exception {

		String sTaskCount = wh.getText(TaskCount);
		int iPosStart = sTaskCount.indexOf("of");
		int iPosEnd = sTaskCount.indexOf("at");
		sTaskCount = sTaskCount.substring(iPosStart + 2, iPosEnd);
		sTaskCount = sTaskCount.replace(",","");
		iArrayCount = Integer.parseInt(sTaskCount.trim());
		if (iArrayCount == 0) {
			throw new Exception("No Task Available");
		}
		else {
			return (iArrayCount);
		}

	}
	
	
	public void validateTaskStatus(String sExpectedTaskStatus) throws Exception {
	
//		ClearTaskArrayList();
//		GetTaskDetails();
//	iArrayCount = getTaskCount();
	iArrayCount = DFWMSTasksPageObject.sTaskID.size();
	iFieldVal = 0;
	while (iArrayCount != 0) {
		Thread.sleep(5000);
		TaskStatus = By.xpath(".//span[@id='dataForm:lview:dataTable:"
				+ iFieldVal + ":statusVal']");

//		sTaskStatus[iFieldVal] = wh.getText(TaskStatus);
		if (wh.getText(TaskStatus).equalsIgnoreCase(sExpectedTaskStatus)) {
			report.addReportStep("Validate Task Status",
					"Successfully validated task status "+sTaskID.get(iFieldVal), StepResult.PASS);
		} else {
			report.addReportStep("Validate Task Status",
					"Task "+ sTaskID.get(iFieldVal)+" status is incorrect. ", StepResult.WARNING);
			throw new Exception("Task "+ sTaskID.get(iFieldVal)+" status is incorrect. "
					+ "Task status is: " + wh.getText(TaskStatus));
		}
		iArrayCount--;
		iFieldVal++;
		Thread.sleep(2000);
		report.addReportStep("Validate Task Status","Successfully validated task status" + sTaskID.get(iFieldVal),StepResult.PASS);

	}
	closebtn();
	}
	
	public void validateTaskType(String sTaskIDValue,String sActualTaskType,String sExpectedTaskType) throws Exception {
		
			if (sActualTaskType.equalsIgnoreCase(sExpectedTaskType)) {
				report.addReportStep("Validate Task Type",
						"Successfully validated task type for task "+sTaskIDValue, StepResult.PASS);
			} else {
				report.addReportStep("Validate Task Type",
						"Task "+ sTaskIDValue +" is not created with Task Type - "+sExpectedTaskType, StepResult.WARNING);
				throw new Exception("Task "+ sTaskIDValue+" is created with incorrect Task Type"
						+ "Task type is: " + sActualTaskType);
			}

		}
	
		public void ClearTaskArrayList(){

//			System.out.println(sTaskID.size());
			
			sTaskID.clear();
			sTaskStatus.clear();
			sTaskItem.clear();
			sTaskType.clear();
			sTaskCat.clear();
			sTaskStartWG.clear();
			sTaskStartWA.clear();
			sTaskDetailsCount.clear();
		
		}

	//	public String getTaskGroupCode() throws Exception {
	//		
	//		By QuickFilterDropDown = By.xpath(".//input[@id='filterId_fltrDownArrow']");
	//		By SavedFilters = By.xpath(".//*[@id='filterId_li1']");
	//		By TGEApply = By.xpath(".//input[@id='dataForm:listView:filterId:savedapply']");
	//
	//		return("549");
	//	}
		
	public String GetTaskDetailsCount(String sTaskIDDetailstoGet) throws Exception {
		
		String sTaskDetailsCountQry;
		jd.dbDFWMSMapping();
		sTaskDetailsCountQry = jd.str_Database_Connection("SELECT COUNT(TD.TASK_SEQ_NBR) FROM TASK_DTL TD WHERE TASK_ID = '"+sTaskIDDetailstoGet+"'");
//		System.out.println(sTaskIDDetailstoGet + " Task Detail Count is - " + sTaskDetailsCountQry);
		return(sTaskDetailsCountQry);
	
	}

	


}