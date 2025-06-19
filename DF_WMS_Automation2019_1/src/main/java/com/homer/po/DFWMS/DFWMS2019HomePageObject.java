package com.homer.po.DFWMS;

import org.openqa.selenium.By;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;

public class DFWMS2019HomePageObject extends PageBase{

	public DFWMS2019HomePageObject(InstanceContainer ic) {
		super(ic);
	}

	public void WMSmenu(String screenOption, String module) throws Exception {
		try {
			if (wh.isElementPresent(Menuclick, 8)) {
				wh.clickElement(Menuclick);
				Thread.sleep(1000);
				wh.clickElement(ShowAll);
				Thread.sleep(1000);
				wh.clickElement(MenuIntegration);
				Thread.sleep(1000);
				wh.clickElement(PostMessageOption);
			}
			System.out.println("Entered into "+screenOption +" under "+module);
			
			report.addReportStep("Entered " + screenOption
					+ " in menu ", "Successfully entered "
							+ screenOption + " in menu ", StepResult.PASS);
		} catch (Exception e) {
			report.addReportStep("Open "+screenOption,
					"Unable to open . "+screenOption + " " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void searchInput(String screenOption, String module) throws TCTerminationException {
		try {
			
			if(wh.isElementPresent(ShowAll, 3)){
				
			}
			
			report.addReportStep("Enter " + screenOption
					+ " in menu search", "Successfully entered "
							+ screenOption + " in menu search", StepResult.PASS);
		} catch (Exception e) {
			report.addReportStep(
					"Bring up " + screenOption + " window. ",
					"Unable to bring up " + screenOption + " window "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	
}
