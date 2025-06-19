package com.homer.po;
import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;

public class PLPPage extends PageBase<PLPPage> {
	
	static final By verifyPLPPage = By.id("hd_plp");	
	static By firstItemDescSearchPLP = By.cssSelector("a[class='item_description position_tracking_btn']");
	
	public PLPPage(InstanceContainer ic) {
		 super(ic);
		 
	} 
	
	/**
	 * Method to verify PLP Page
	 * @return
	 * @throws Exception
	 */
	public PLPPage verifyPLPPage() throws Exception {
		
		expectedResult = wh.isElementPresent(verifyPLPPage, 3);
		
		//rc.scenarioWrite(expectedResult, "PLP page is not displayed");
		
		if(expectedResult) {
			
			report.addReportStep("Verify PLP page is displayed" , 
					"PLP page is displayed", 
					StepResult.PASS);
		} else {
			
			report.addReportStep("Verify PLP page is displayed" , 
					"PLP page is not displayed", 
					StepResult.FAIL);
		}
		
		return this;
	}	 
	
	/**
	 * Method to click first PLP POD
	 * @return
	 * @throws Exception
	 */
	public PIPPage clickFirstPLPPOD() throws Exception {
		
		wh.clickElement(firstItemDescSearchPLP);
		
		return new PIPPage(ic);
	}
	
	public void failStep1() throws Exception {
		/* report.addReportStep("Fail step Home Page",
				 "Failed step",
				 StepResult.FAIL);*/

		PIPPage pipPage = new PIPPage(ic);
		pipPage.failStep1();
	}

}
