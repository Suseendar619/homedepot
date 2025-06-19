package com.homer.po.DFWMS;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSShpConfRulesObject extends PageBase {

	public DFWMSShpConfRulesObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	public static final By Distributiontab  = By.linkText("Distribution");
	public static final By linkShpCnfrmRule = By.id("MIDP69");
	public static final By invc_nonParcel_chkBx=By.id("checkAll_c0_dataForm:listView:dataTable");
	public static final By invc_Parcel_chkBxname=By.id("dataForm:listView:dataTable:1:invcParmDesc");
	public static final By invc_parcel_chkBx=By.id("checkAll_c1_dataForm:listView:dataTable");
	public static final By shpCnfrmRule_View=By.id("rmButton_2View1_167270940");
	public static final By Shipconfirmselect=By.xpath("//*[@id='dataForm:lview:dataTable:1:ruleNameText']");
	public static final By shipconfirmdo5 = By.xpath("//*[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlRuleCmparValue']");
	public static final By shpCnfrmRule_Definition=By.id("definitionTab_lnk");
	public static final By shpCnfrmRule_radBtn=By.id("checkAll_c_dataForm:ruleSelDtlDataTable");	
	public static final By shpCnfrmRule_ColDD=By.id("dataForm:ruleSelDtlDataTable:0:ruleSelDtlColumnList");
	public static final By shpCnfrmRule_OprDD=By.id("dataForm:ruleSelDtlDataTable:0:ruleSelDtlOperatorList");
	public static final By shpCnfrmRule_Txtbx=By.id("dataForm:ruleSelDtlDataTable:0:ruleSelDtlRuleCmparValue");
	public static final By shpCnfrmRule_shpcnfrmBtn=By.id("rmButton_1ShipConfirm1_8660000");
	public static final By shpCnfrmRule_View1=By.id("dataForm:lview:dataTable:1:PK_1");
	public static final By shpconfmconfirm = By.xpath(".//span[contains(text(),'OK')]");
	JDBC_Connection jd = new JDBC_Connection(ic);
	
	public String getRTEValue(String Donbr) throws Exception {

		String sRTEValuefromDB = "";
		jd.dbDFWMSMapping();
		sRTEValuefromDB = jd.str_Database_Connection("Select RTE_TO from orders where TC_ORDER_ID = '" + Donbr + "'");
		return (sRTEValuefromDB);

	}
	
	public void shpconfrules(String donbr)throws Throwable {
		
		String sRouteto = getRTEValue(donbr);
		
		if ((sRouteto.equals("FX")|| sRouteto.equals("FG")|| sRouteto.equals("9")|| sRouteto.equals("10")|| sRouteto.equals("11")))
		{
			shpconfirmrulespcl(donbr);
		
		}
		else if ((sRouteto.equals("2")|| sRouteto.equals("3")|| sRouteto.equals("18")))
		{
			shpconfirmrules_np(donbr);
		}
	}
	
	public void shpconfirmrulespcl(String Donbr1)throws Throwable {
		Robot robot = new Robot();
				try {
					
					if (wh.isElementPresent(invc_parcel_chkBx, 2)) {
						wh.clickElement(invc_parcel_chkBx);
						Thread.sleep(2000);
						
						//wh.clickElement(inputApptdate);
					}
					
			        
			        if(wh.isElementPresent(shpCnfrmRule_View,2)){
			        	Thread.sleep(2000);
			         wh.clickElement(shpCnfrmRule_View);
			        }
			        
			 
				    if(wh.isElementPresent(shpCnfrmRule_Definition,2)){
				    	Thread.sleep(2000);
				    wh.clickElement(shpCnfrmRule_Definition);
			        }
			        
			     if(wh.isElementPresent(Shipconfirmselect,2)){
			    	 Thread.sleep(1000);
			        	wh.clickElement(Shipconfirmselect);
			        }
			        if(wh.isElementPresent(shpCnfrmRule_Txtbx,2)){
			        	Thread.sleep(2000);
				    wh.sendKeys(shpCnfrmRule_Txtbx, Donbr1);
			        }

				    wh.waitForPageLoaded();
				    Thread.sleep(5000);
				    if(wh.isElementPresent(shpCnfrmRule_Txtbx,2)){
				    	 Thread.sleep(1000);
				    wh.clickElement(shpCnfrmRule_shpcnfrmBtn);
				    Thread.sleep(1000);
				    }
				    wh.waitForPageLoaded();
				    robot.keyPress(KeyEvent.VK_ENTER);
				    robot.delay(50);
			        robot.keyRelease(KeyEvent.VK_ENTER);
			        Thread.sleep(5000);
			        wh.isAlertPresent();
			        Thread.sleep(5000);
			        wh.handleAlert();
//				    if(wh.isElementPresent(shpconfmconfirm,2)){
//				     wh.clickElement(shpconfmconfirm);
//				    Thread.sleep(5000);
//				    if(wh.isAlertPresent()){
//				        Thread.sleep(2000);
//				        wh.handleAlert();
//				    }
//
//				    }			    
			
		}catch (Exception e) {
			report.addReportStep("Navigate to Ship Confirm rule screen","Unable to navigate to Ship Confirm rule screen - " + e.getMessage(),StepResult.WARNING);
			wh.handleAlert();
		}               
		
	}
	
	public void shpconfirmrules_np(String Donbr1)throws Throwable {
			
		Robot robot = new Robot();
		try {
			
			if (wh.isElementPresent(invc_nonParcel_chkBx, 2)) {
				wh.clickElement(invc_nonParcel_chkBx);
				Thread.sleep(2000);
				
				//wh.clickElement(inputApptdate);
			}
			
	        
	        if(wh.isElementPresent(shpCnfrmRule_View,2)){
	        	Thread.sleep(2000);
	         wh.clickElement(shpCnfrmRule_View);
	        }
	        
	 
		    if(wh.isElementPresent(shpCnfrmRule_Definition,2)){
		    	Thread.sleep(2000);
		    wh.clickElement(shpCnfrmRule_Definition);
	        }
	        
	     if(wh.isElementPresent(Shipconfirmselect,2)){
	    	 Thread.sleep(1000);
	        	wh.clickElement(Shipconfirmselect);
	        }
	        if(wh.isElementPresent(shpCnfrmRule_Txtbx,2)){
	        	Thread.sleep(2000);
		    wh.sendKeys(shpCnfrmRule_Txtbx, Donbr1);
	        }

		    wh.waitForPageLoaded();
		    Thread.sleep(5000);
		    if(wh.isElementPresent(shpCnfrmRule_Txtbx,2)){
		    	 Thread.sleep(1000);
		    wh.clickElement(shpCnfrmRule_shpcnfrmBtn);
		    Thread.sleep(1000);
		    }
		    wh.waitForPageLoaded();
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.delay(50);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(5000);
	        wh.isAlertPresent();
	        Thread.sleep(5000);
	        wh.handleAlert();
//		    if(wh.isElementPresent(shpconfmconfirm,2)){
//		    wh.clickElement(shpconfmconfirm);
//		    Thread.sleep(5000);
//		    if(wh.isAlertPresent()){
//		        Thread.sleep(2000);
//		        wh.handleAlert();
//		    }
//
//		    }	
	
}catch (Exception e) {
	report.addReportStep("Navigate to Ship Confirm rule screen","Unable to navigate to Ship Confirm rule screen - " + e.getMessage(),StepResult.WARNING);
	wh.handleAlert();
}               

}
		

}
