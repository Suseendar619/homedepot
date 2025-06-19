package com.homer.po.DFWMS;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass;
import com.homer.po.PageBase;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.DFWMS.DFWMSHomePageObject2019;


public class DFWMS2019PostXmlPageObject extends PageBase{

	public DFWMS2019PostXmlPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void postXML() throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		System.out.println(wh.getText(Titletxt));

		if(wh.getText(Titletxt).equals(PageTitle)){
			report.addReportStep("Post Message Page","Post message Page Loaded successfully", StepResult.PASS);
		}

		else{
			report.addReportStep("Post Message Page", "Failed",
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		driver.switchTo().frame(0);
		if(wh.isElementPresent(PostChooseFile, 3)){
			wh.sendKeys(PostChooseFile, FilePath);
			wh.clickElement(PostSend);
			Thread.sleep(200);
			closebtn();
//			wh.clickElement(CloseWindow);
            report.addReportStep("Post Message send","Post message sent successfully", StepResult.PASS);
//			driver.switchTo().frame(0);
            System.out.println("Post Message window closed Successfully");

		}
		else{
            report.addReportStep("Post Message send", "Failed to post",
                    StepResult.FAIL);
            rc.throwTCTerminationException();
        }
	}


}
