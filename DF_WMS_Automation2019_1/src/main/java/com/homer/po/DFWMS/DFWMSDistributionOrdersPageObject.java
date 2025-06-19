package com.homer.po.DFWMS;

import java.util.List;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;

public class DFWMSDistributionOrdersPageObject extends PageBase {

	public DFWMSDistributionOrdersPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	static final By DOIDInput = By.xpath(".//input[@id='dataForm:DOList_entityListView:DistributionOrderlist1:field6value1']");
	static final By DOApply = By.xpath(".//input[@id='dataForm:DOList_entityListView:DistributionOrderlist1:DistributionOrderlist1apply']");
	//static final By DOStatus = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_OrderFulfillmentStatus_Output2']");
	
	static final By DOStatus = By.xpath("(.//div[contains(@class,'x-grid-cell-inner') and contains(@style,'text-align:left;')])[3]");
	
	static final By DORouteTo = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteTo_Output2']");
	static final By DORouteType1 = By.xpath("//*[@id='dataForm:DOList_entityListView:DOList_MainListTable:0:DOList_RteType1_Output2']");
		
	/**
	 * Method to validate WMS DO
	 * 
	 * @throws Exception
	 */
	
	
	/*public void DOInputandSearch(String DONBR, String screen, List<String> doIds) throws Exception {

		try {
			if (wh.isElementPresent(DOIDInput, 5)) {
				wh.waitForPageLoaded();
				wh.sendKeys(DOIDInput, DONBR.toString().trim());

			}

			if (wh.isElementPresent(DOApply, 5)) {
				wh.waitForPageLoaded();
				wh.clickElement(DOApply);
				if (wh.isElementPresent(DOStatus, 5)) {

					report.addReportStep("Input DO and Click Appply",
							"Successfully entered the DO and Clicked Apply",
							StepResult.PASS);
				} else {
					throw new Exception("DO is not populated."
							+ "XPath used is: " + DOStatus.toString());
				}
			}

		} catch (Exception e) {
			report.addReportStep("Input DO and Appply",
					"Unable to Input and Apply DO - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}*/

	/**
	 * This function opens the page that you designate (i.e. "Post Message",
	 * "Purchase Orders", etc) in the main Search Bar.
	 * 
	 * @param status
	 *            - Name of the page that you want to bring up
	 * @param moduleIndex
	 *            - Specific type of page (i.e. "Integration" or "Technical" for
	 *            "Post Message" page)
	 * 
	 * @throws Exception
	 */

	public void ValidateDOStatus(String status, String doId)
			throws Exception {

		try {
			
			//Thread.sleep(5000);
			wh.waitUntilDisappear(LoadingFrame);
			Thread.sleep(1000);
			wh.clickElement(Maximize);
			//Thread.sleep(1000);
			//todo
			// Type input into search bar
			if (wh.isElementPresent(DOStatus, 5)) {

			} else {
				throw new Exception("DO Fulfillment Status is not populated."
						+ "XPath used is: " + DOStatus.toString());
			}

			// Validate that DO
			if (wh.getText(DOStatus).equalsIgnoreCase(status)) {
				report.addReportStep("Validate DO status", "DO "
						+ doId + " status is " + status,
						StepResult.PASS);
			} else {
				report.addReportStep(
						"Validate DO Status",
						"DO is not in expected "+ status +" status. DO "
								+ doId + " is in"
								+ wh.getText(DOStatus), StepResult.FAIL);
				//rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			report.addReportStep(
					"Validate DO Status ",
					"Unable to validate DO " + doId + " status "
							+ e.getMessage(), StepResult.FAIL);
			//rc.throwTCTerminationException();
		}

		closebtn();
	}

	public void ValidateDORouteTo(String screenOption, String DONBRFORPRINT)
			throws Exception {

		try {
			// Type input into search bar
			if (wh.isElementPresent(DORouteTo, 5)) {

			} else {
				throw new Exception("DO Route To is not populated."
						+ "XPath used is: " + DORouteTo.toString());
			}

			// Validate that DO
			if (wh.getText(DORouteTo).equalsIgnoreCase(screenOption)) {
				report.addReportStep("Validate DO Route To", "DO "
						+ DONBRFORPRINT + " route to is " + screenOption,
						StepResult.PASS);
			} else {
				report.addReportStep(
						"Validate DO Route To",
						"DO is not with expected "+ screenOption +" Route To. DO "
								+ DONBRFORPRINT + " is in"
								+ wh.getText(DORouteTo), StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			report.addReportStep(
					"Validate DO Route To ",
					"Unable to validate DO " + DONBRFORPRINT + " Route To "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}
	
	public void ValidateDORouteType1(String screenOption, String DONBRFORPRINT)
			throws Exception {

		try {
			// Type input into search bar
			if (wh.isElementPresent(DORouteType1, 5)) {

			} else {
				throw new Exception("DO Route Type 1 is not populated."
						+ "XPath used is: " + DORouteType1.toString());
			}

			// Validate that DO
			if (wh.getText(DORouteType1).equalsIgnoreCase(screenOption)) {
				report.addReportStep("Validate DO Route Type 1", "DO "
						+ DONBRFORPRINT + " Route Type 1 is " + screenOption,
						StepResult.PASS);
			} else {
				report.addReportStep(
						"Validate DO Route Type 1",
						"DO is not with expected "+ screenOption +" Route Type 1. DO "
								+ DONBRFORPRINT + " is in"
								+ wh.getText(DORouteType1), StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			report.addReportStep(
					"Validate DO Route Type 1 ",
					"Unable to validate DO " + DONBRFORPRINT + " Route Type 1 "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}
	
}