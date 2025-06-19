package com.homer.po.DFWMS;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSoLPNsPageObject extends PageBase {

	JDBC_Connection jd = new JDBC_Connection(ic);
	public DFWMSoLPNsPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public static String Generated_Shipvia;
	public static String oLPN2;
	public static String oLPN3;
	static final By oLPNStatusFrom = By.xpath(".//SELECT[@id='dataForm:listView:filterId:field20value1']");
	static final By oLPNStatusTo = By.xpath(".//SELECT[@id='dataForm:listView:filterId:field20value2']");
	static final By oLPNFilterExpand = By.xpath(".//input[contains(@id,'filterId_fltrExpCol') and contains(@class,'fltrHidden')]");
	static final By oLPNApply = By.xpath(".//INPUT[@id='dataForm:listView:filterId:filterIdapply']");
	static final By oLPNDONBR = By.xpath(".//*[@id='dataForm:listView:filterId:field30value1' or @alt='Find Distribution Order']");
	static final By oLPN = By.xpath(".//INPUT[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Outbound_filterId1:field1value1']");
	static By oLPNStatus = By.xpath(".//*[@id='dataForm:listView:dataTable:0:LPNList_Outbound_lpnFacilityStatus_param_out']");
	static By oLPNShipVia = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteTo_Output2']");
	static final By oLPNTrackingNumber = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteType1_Output2']");
	static final By oLPNPCLShipVia = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteType1_Output2']");
	static By oLPNNumber = By.xpath("//*[@id='dataForm:listView:dataTable:0:LPNList_Outbound_Link_NameText_param_out']");
//	static final By oLPNTrackingNumber = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteType1_Output2']");
	static final By oLPNCount = By.xpath(".//span[@class='pagerNoWrap'][1]");
	static By oLPNCheckbox = By.xpath(".//input[contains(@id,'checkAll') and contains(@type,'checkbox')]");
	static By LockLPN = By.xpath(".//input[contains(@value,'Lock/Unlock') and contains(@id,'LPNLis') and contains(@type,'button')]");
	static By LockButton = By.xpath(".//input[contains(@value,'Lock') and contains(@type,'button') and contains(@tabindex,'83')]");
	static By LockReason = By.xpath(".//select[contains(@id,'LockCodeSelect') and contains(@name,'LockCodeSelect') and contains(@tabindex,'159')]");
	static By LockSave = By.xpath(".//input[contains(@value,'Save') and contains(@type,'button') and contains(@id,'rmButton')]");
	static By LockCancel = By.xpath(".//input[contains(@value,'Cancel') and contains(@type,'button') and contains(@id,'rmButton')]");
	static By LockValue = By.xpath(".//span[contains(@id,'LockCount_param')]");
	
	//packsize
	public static By CartStart= By.xpath(".//div[contains(text(),'Cart Start')]");
	public static By MenuClick = By.xpath(".//div[contains(@class,'flyout-button')][1]");
	public static By Assignment = By.xpath(".//input[contains(@id,'workAssignment')]");
	public static By CartStartApply = By.xpath(".//input[contains(@value,'Apply')]");
	public static By CartStartCheckbox = By.xpath(".//span[contains(@class,'checkbox') and contains(@style,'background-position: 0px 0px')]");
	public static By CartStartRelease = By.xpath(".//input[contains(@id,'release-workassignments')]");
	
	
	public static int iArrayCount;
	public static ArrayList soLPNs = new ArrayList();
	public static List<String> oLPNs  = new ArrayList<String>();
	public static ArrayList soLPNstatus = new ArrayList();
	public static ArrayList solpnshpvia = new ArrayList();
	int iFieldVal;
	
	
	/**
	 * Method to validate WMS oLPN
	 * @param doIds 
	 * @param screen 
	 * @return 
	 * 
	 * @throws Exception
	 */
	
	public void oLPNInputDOandSearch(String DONBR, String screen, List<String> doIds) throws Exception {
		
		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		soLPNs = new ArrayList();
		try {
			if (wh.isElementPresent(oLPNFilterExpand, 5)) {
				
				wh.clickElement(oLPNFilterExpand);
				wh.selectValue(oLPNStatusFrom, "Not printed");
				wh.selectValue(oLPNStatusTo, "Canceled");
				wh.sendKeys(oLPNDONBR, DONBR.toString().trim());
				//wh.sendKeys(oLPNDONBR,"1016592A"); //"1031560B");
				wh.clickElement(oLPNFilterExpand);
			}

			if (wh.isElementPresent(oLPNApply, 5)) {
				wh.clickElement(oLPNApply);
				if (wh.isElementPresent(oLPNStatus, 5)) {

					report.addReportStep("Input DO and Click oLPN Appply",
							"Successfully entered the DO and Clicked Apply",
							StepResult.PASS);
				} else {
					throw new Exception("oLPN is not populated."
							+ "XPath used is: " + oLPNStatus.toString());
				}
				
				iArrayCount = getoLPNCount(DONBR.toString().trim());
				iFieldVal = 0;
				
				while (iArrayCount != 0) {
					
				oLPNStatus = By.xpath(".//*[@id='dataForm:listView:dataTable:0:LPNList_Outbound_lpnFacilityStatus_param_out']");
				//oLPNStatus = By.xpath(".//*[@id='dataForm:LPNListInOutboundMain_lv:dataTable:"+iFieldVal+":LPNList_Outbound_lpnFacilityStatus_param_out']");
				//oLPNShipVia = By.xpath("//*[@id=1dataForm:LPNListInOutboundMain_lv:dataTable:"+iFieldVal+":LPNList_Common_ShipVialinkId']");
				soLPNs.add(wh.getText(oLPNNumber));
				soLPNstatus.add(wh.getText(oLPNStatus));
				//solpnshpvia.add(wh.getText(oLPNShipVia));
				iArrayCount--;
				iFieldVal++;
				}
				
//				System.out.println(soLPNs.size());
//				System.out.println(soLPNs.toString());				
				
			}

		} catch (Exception e) {
			report.addReportStep("Input DO and oLPN Appply",
					"Unable to Input and Apply DO - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void ValidateoLPNStatus(String screenOption, String DONBRFORPRINT)
			throws Exception {

		try {
			// Type input into search bar
			iArrayCount = soLPNs.size();
			iFieldVal = 0;
			while (iArrayCount != 0) {
				
				//oLPNNumber = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:dataTable:"+iFieldVal+":LPNList_Outbound_Link_NameText_param_out']");
				//oLPNStatus = By.xpath(".//*[@id='dataForm:LPNListInOutboundMain_lv:dataTable:"+iFieldVal+":LPNList_Outbound_lpnFacilityStatus_param_out']");

			if (wh.isElementPresent(oLPNStatus, 5)) {

			} else {
				throw new Exception("oLPN Status is not populated."
						+ "XPath used is: " + oLPNStatus.toString());
			}

			// Validate that DO
			oLPN2 = wh.getText(oLPNNumber);
			//System.out.println("oLPN status in Validate status function" +oLPN2);
			if (wh.getText(oLPNStatus).equalsIgnoreCase(screenOption)) {
				report.addReportStep("Validate oLPN status", "oLPN "
						+ wh.getText(oLPNNumber) + " status is " + screenOption,
						StepResult.PASS);
			} else {
				report.addReportStep(
						"Validate oLPN Status",
						"oLPN is not in expected "+ screenOption +" status. oLPN "
								+ wh.getText(oLPNNumber) + " is in"
								+ wh.getText(oLPNStatus), StepResult.FAIL);
				//rc.throwTCTerminationException();
			}
			
//			System.out.println(soLPNs.get(iFieldVal));
			iArrayCount--;
			iFieldVal++;
			}
			
//			System.out.println(soLPNs.size());
//			System.out.println(soLPNs.toString());

		} catch (Exception e) {
			report.addReportStep(
					"Validate oLPN Status ",
					"Unable to validate oLPNs status - "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		closebtn();
	}
	
	public int getoLPNCount(String doId) throws Exception {

		soLPNs.clear();
		/*String soLPNCount = wh.getText(oLPNCount);
//		System.out.println(wh.getText(oLPNCount));
		int iPosStart = soLPNCount.indexOf("of");
		soLPNCount = soLPNCount.substring(2,iPosStart);
//		soLPNCount = soLPNCount.replace(",","");
 * 		
*/		
		String siLPNValuefromDB;
		jd.dbDFWMSMapping();
		siLPNValuefromDB = jd.str_Database_Connection("Select count(TC_LPN_ID) from LPN where TC_ORDER_ID IN ('" + doId + "')");
		iArrayCount = Integer.parseInt(siLPNValuefromDB);
		oLPNs = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + doId + "')");
		
		System.out.println("OLPN count" +iArrayCount);
		System.out.println(oLPNs);
		
		if (iArrayCount == 0) {
			throw new Exception("No oLPN Available");
		}else {
			return (iArrayCount);
		}
       
	}
	
	public void ClearoLPNArrayList(){

		soLPNs.clear();
	
	}

	public void lockLPN(List<String> olpns) {
		
		try{
			if(wh.isElementPresent(oLPNCheckbox, 5)){
				wh.clickElement(oLPNCheckbox);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(LockLPN, 5)){
				wh.clickElement(LockLPN);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(LockButton, 5)){
				wh.clickElement(LockButton);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(LockReason, 5)){
				wh.selectValue(LockReason, "Damaged");
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(LockSave, 5)){
				wh.clickElement(LockSave);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(LockCancel, 5)){
				wh.clickElement(LockCancel);
				Thread.sleep(1000);
			}
			
			report.addReportStep("Lock LPN", "Lock LPN Success", StepResult.PASS);
			closebtn();
		}catch(Exception e){
			report.addReportStep("Lock LPN", "Unable to Lock LPN " + e.getMessage(), StepResult.FAIL);
		}
	}

	public String getWorkAssignmentId() throws Exception {
		String workAssignmentId = "";
		try{
			jd.dbDFWMSMapping();
			workAssignmentId = jd.str_Database_Connection_sql("select distinct WorkAssignmentId from "
					+ "oms_work_assignment_detail with (nolock) where CartonId in (select cartonid from PPE_WMS_Carton "
					+ "with (nolock) where WaveNumber = '202008040450')");
			if(workAssignmentId != null){
				report.addReportStep("Get Work Assignment Id", "Work Assignment Id retreived from DB: "+workAssignmentId, StepResult.PASS);
			}
		}catch(Exception e){
			report.addReportStep("Get Work Assignment Id", "Unable to get work assignment id from DB", StepResult.FAIL);
			throw new Exception("Unable to get work assignment id from DB");
		}
		return workAssignmentId;
	}

	public void releaseAssignment(String workAssignmentId) {

		try{
			if(wh.isElementPresent(CartStart, 5)){
				wh.clickElement(CartStart);
			}
			if(wh.isElementPresent(MenuClick, 5)){
				wh.clickElement(MenuClick);
			}
			if(wh.isElementPresent(Assignment, 5)){
				wh.clickElement(Assignment);
				wh.sendKeys(Assignment, "202110140288");
			}
			if(wh.isElementPresent(CartStartApply, 5)){
				Thread.sleep(500);
				wh.clickElement(CartStartApply);
			}
			if(wh.isElementPresent(CartStartCheckbox, 5)){
				wh.clickElement(CartStartCheckbox);
				Thread.sleep(500);
			}
			/*if(wh.isElementPresent(CartStartRelease, 5)){
				wh.clickElement(CartStartRelease);
			}*/
			report.addReportStepWithScreenshots("Release Work Assignment", "Assignment is successfully released", StepResult.PASS);
		}catch(Exception e){
			report.addReportStepWithScreenshots("Release Work Assignment", "Unable to release Assignment", StepResult.FAIL);
		}
	}
}