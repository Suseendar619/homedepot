package com.homer.po.DFWMS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.Select;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class RunTemplatePageObject extends PageBase {

	
	
	public RunTemplatePageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	static final By LoadingFrame=By.xpath(".//div[contains(@id,'loadmask')]");
	static final By MaximizeRunTemplate=By.xpath("(//span[contains(text(),'Run Templates')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')])[1]");
	static final By RunTemplateScreenHeader=By.xpath(".//span[contains(text(),'Run Templates') and contains(@id,'screen')]");
	static final By RDCCons=By.xpath(".//*[@id='jlisttable1']/tbody/tr[21]/td[3]/a");
	static final By SDCCons=By.xpath(".//*[@id='jlisttable1']/tbody/tr[7]/td[3]/a");
	static final By OrderFilter=By.id("orderFilterSelected");
	static final By PreviewFilter=By.xpath(".//*[@id='PFLTengineselected']/a");
	static final By PreviewDOScreen=By.xpath("(.//*[contains (@id,'screen') and @class='x-header-text x-window-header-text x-window-header-text-default'])[3]");
	static final By PreviewDOID=By.xpath(".//*[@id='dataForm:mcwOrderList_entityListView:mcwOrderList_MainListTable:0:outLinkOrderId']");
	static final By StartRun=By.id("dataForm:mcwcmdStartRunButton");
	static final By Status=By.xpath(".//*[@id='jlisttable1']/tbody/tr[2]/td[3]");
	static final By Refresh=By.xpath("(.//*[@class='x-tool-img x-tool-refresh'])[1]");
	static final By Refreshstatus=By.xpath("(.//div[contains(text(),'Displaying')]/preceding-sibling::a)[5]");
	static final By RunID=By.xpath(".//*[@id='jlisttable1']/tbody/tr[2]/td[2]/a");
	static final By RunStatus=By.xpath(".//*[@id='maincon']/table/tbody/tr/td/form[3]/table[2]/tbody/tr[2]/td[2]");
	static final By Review= By.xpath(".//*[@id='maincon']/table/tbody/tr/td/form[3]/table[2]/tbody/tr[6]/td[4]/table/tbody/tr/td[1]/button[1]");
	static final By NumberofShipmentscreated= By.xpath(".//*[@id='maincon']/table/tbody/tr/td/form[3]/table[3]/tbody/tr[4]/td[2]/a");
	//static final By ShipmentlistScreen= By.xpath(".//span[contains (text(),'Run Summaries - Run Summary Shipment List For RunID')]");
	static final By ShipmentlistScreen= By.xpath(".//*[@id='jlisttable1']/tbody/tr[1]/th[3]");
	static final By ShipmentlistBack= By.xpath(".//*[@id='backButton']/img");
	static final By SavedViews=By.xpath("(//table/tbody/tr/td[1]/input[starts-with(@id, 'combobox')])[2]");
	static final By Delete=By.xpath(".//*[text()='Delete']");
	static final By OK=By.xpath(".//*[text()='OK']");
	static final By MaximizeDistributionOrder=By.xpath(".//span[contains(text(),'Distribution Orders')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By Goto = By.xpath(".//*[@id='jlistctls1']/table/tbody/tr/td/table/tbody/tr/td[3]/input");
	static final By TemplateForward = By.xpath(".//*[@id='jlistctls1']/table/tbody/tr/td/table/tbody/tr/td[1]/button[3]");
	static final By TemplateBackward = By.xpath(".//*[@id='jlistctls1']/table/tbody/tr/td/table/tbody/tr/td[1]/button[2]");
	static final By TemplateCompleteForward = By.xpath(".//*[@id='jlistctls1']/table/tbody/tr/td/table/tbody/tr/td[1]/button[4]");
	static final By ConsXD=By.xpath(".//*[@id='jlisttable1']/tbody/tr[4]/td[3]/a");
	static final By FleetRDC=By.xpath(".//*[@id='jlisttable1']/tbody/tr[2]/td[3]/a");
	static final By MaximizeShipments=By.xpath(".//span[contains(text(),'Shipments')]/../following-sibling::div//img[contains(@class,'x-tool-maximize')]");
	static final By Statuscheck=By.xpath(".//tr[contains(@id,'gridview')]//td[5]");
	static final By Shipmentcheck=By.xpath(".//tr[contains(@id,'gridview')]//td[4]");
	//static final By ShipmentCheckBox=By.xpath("(.//span[contains (@id,'gridcolumn') and contains(@class,'x-column-header-text')])[1]");
	static final By ShipmentCheckBox=By.xpath(".//span[contains (text(),'Consolidation Run Id')]/../../preceding-sibling::div[1]/div[1]/span");
	
	String donumber;
	String shipment;
	String Query;
	
	ArrayList<String> ar = new ArrayList<String>();
	
	public void runTemplateFilterPreview() throws Exception
	{		
		//previewing the filter Created in the previous method and selecting the Template
		wh.waitUntilDisappear(LoadingFrame);	
		wh.clickElement(MaximizeRunTemplate);
		
		if(wh.isElementPresent(RunTemplateScreenHeader, 10))
		{
		
			
			if(wh.getText(RunTemplateScreenHeader).equalsIgnoreCase("Run Templates"))
			{	
				
				driver.switchTo().frame(0);
				
				report.addReportStep("Verify Run Template Screen", 
						"Successfully moved to Run Templates Screen", StepResult.PASS);
				/*int n=7;
				for(int i=0;i<=n;i++)
				{
					wh.isElementPresent(TemplateForward, 4000);
					wh.clickElement(TemplateForward);
					wh.waitForPageLoaded();
					
				}*/
				
			/*	if(wh.getText(SDCCons).equalsIgnoreCase("5852 SDC CONS"))
				{
				
					wh.clickElement(SDCCons);
					wh.waitForPageLoaded();
					Thread.sleep(4000);
					report.addReportStep("Click on the Template and verify the details ", "Clicked on the template and moved to Details Screen", StepResult.PASS);
				}
				else
				{
					report.addReportStep("Searching for 5852 SDC CONS ", "Not able to Find 5852 SDC CONS", StepResult.FAIL);
				}*/
			}
		}
		else 
			report.addReportStep("Searching for RunTemplate Screen Header ", "Screen Header Not Found", StepResult.FAIL);
	}
	
	public void runTemplateQuery(String templateName) throws Exception
	{
		try
		{
			System.out.println("templateName:"+templateName);
			JDBC_Connection jd = new JDBC_Connection(ic);
//			jd.dbMapping();
			Query=jd.str_Database_Connection("Select ROW_ID from (select DESCRIPTION, ROW_NUMBER() OVER (order by DESCRIPTION) AS ROW_ID from TPECASCHM.CONS_TEMPLATE order by DESCRIPTION) R1 where R1.DESCRIPTION = '"+templateName+"' with ur ");
			System.out.println("List DB:"+Query);
			
			WebElement wb = driver.findElement(Goto);
			//wh.isElementPresent(Goto, 4);
			wh.clickElement(Goto);
			wh.sendKeys(Goto, ""+Query.trim()+"");
			
			wb.sendKeys(Keys.ENTER);
			wh.waitForPageLoaded();
		}
		catch (Exception e)
		{
			throw new Exception("Query Not Executed"+e.getMessage());
		}
	}
	public void runTemplateIterations() throws Exception
	{
		try
		{
			for(int i=2;i<=21;i++)
			{
				WebElement wb = driver.findElement(By.xpath("//*[@id='jlisttable1']/tbody/tr["+i+"]/td[3]/a"));
				String s = wb.getText();
				if(s.equalsIgnoreCase(dataTable.getData(DataColumn.runTemplateName)))
						{
							System.out.println("In");
							wh.clickElement(wb);
							wh.waitForPageLoaded();
							Thread.sleep(4000);
							report.addReportStep("Click on the Template and verify the details ", "Clicked on the template "+s+" and moved to Details Screen", StepResult.PASS);
							break;
						}
				System.out.println("Iterator:"+s);
			}
		}
		catch (Exception e)
		{
			throw new Exception("Not able to Iterated the Template List"+e.getMessage());
		}
	}
	public void runTemplateDetails() throws Exception
	{
		//Fetching Run Template Details
		//driver.switchTo().frame(2);
		try{
		wh.clickElement(OrderFilter);
		Select dropdown = new Select(driver.findElement(By.id("orderFilterSelected")));
		dropdown.selectByVisibleText(dataTable.getData(DataColumn.FilterName));
		Thread.sleep(4000);
		report.addReportStep("Selected the filter ", "Filter "+dataTable.getData(DataColumn.FilterName)+" is selected", StepResult.PASS);
		
			if(wh.isElementPresent(PreviewFilter))
			{
				wh.clickElement(PreviewFilter);
				wh.waitForPageLoaded();
				report.addReportStep("Select the Filter and Click on Preview Filter", "Clicked on Preview Filter", StepResult.PASS);
				Thread.sleep(4000);
			}
			else
				report.addReportStep("Select the Filter and Click on Preview Filter", "Not able to find Preview Filter", StepResult.FAIL);
		
			}
		
		catch (Exception e)
		{
			report.addReportStep("Filter Select", "Not able to find the Filter. Please check the Filter Name and the Path :"+e, StepResult.FAIL);
		}
	}
	
	public void runTheEngine() throws Exception
	{
		// Running the Engine
		driver.switchTo().defaultContent();
		
		String preview = wh.getText(PreviewDOScreen);
		System.out.println("preview:"+preview);
				
		if(wh.getText(PreviewDOScreen).equalsIgnoreCase("Run Templates - Distribution Orders"))
		{	
			driver.switchTo().frame(0);
			String s = wh.getText(PreviewDOID);
			System.out.println("Distribution Orders:"+s);
				if (s.equalsIgnoreCase("No data found "))
				{
					report.addReportStep("Checking Distribution Orders in Run Template", "Distribution Orders are Empty in the Screeen", StepResult.FAIL);
					rc.terminateTestCase("Run Template", StepResult.FAIL);
				}
			donumber=TPEDistributionOrderPageObject.DOnumberui;
			System.out.println("Distribution Orders UI:"+donumber);
			
			if(wh.isElementPresent(StartRun))
			{
				wh.clickElement(StartRun);
				report.addReportStep("Running the Engine", "Engine Run is Started", StepResult.PASS);
				wh.waitForPageLoaded();
			}
		}
		else
		{
			report.addReportStep("Searching for Run Templates - Distribution Orders Screen ", "Screen Header Not Found", StepResult.FAIL);
		}
	}
	/*public void runStatusValidation() throws Exception
	{
		//driver.switchTo().frame(0);
		String StatusUI = wh.getText(Status);
		System.out.println("Status:"+StatusUI);
	
		
		if(StatusUI.equalsIgnoreCase("Starting") || StatusUI.equalsIgnoreCase("Running") || StatusUI.equalsIgnoreCase("Queued")|| StatusUI.equalsIgnoreCase("Selecting Orders") || StatusUI.equalsIgnoreCase("PreProcessing"))
		{
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(5L, TimeUnit.MINUTES);
			while ( System.nanoTime() < endTime ){
				driver.switchTo().defaultContent();
				wh.clickElement(Refresh);
				Thread.sleep(7000);
				driver.switchTo().frame(0);
				String StatusUI1 = wh.getText(Status);
				System.out.println("Status1:"+StatusUI1);
				if(StatusUI1.equals("Complete"))
				{
					report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
					wh.clickElement(RunID);
					wh.waitForPageLoaded();
					report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
					break;
				}
				if(StatusUI1.equals("Failed"))
				{
					report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
					break;
				}
				
			}
		}
		else	
			report.addReportStep("Checking the Job Status", "Job not yet completed ", StepResult.WARNING	);
	}*/
	public void runStatusValidation() throws Exception
	{
		
		//Status validation while Engine Run
		//driver.switchTo().frame(0);
		String StatusUI = wh.getText(Status);
		System.out.println("Status:"+StatusUI);
		String RunUI = wh.getText(RunID);
		System.out.println("RunUI:"+RunUI);
		
		if(StatusUI.equalsIgnoreCase("Starting") || StatusUI.equalsIgnoreCase("Running") || StatusUI.equalsIgnoreCase("Queued")|| StatusUI.equalsIgnoreCase("Selecting Orders") || StatusUI.equalsIgnoreCase("PreProcessing"))
		{
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(12L, TimeUnit.MINUTES);
			while ( System.nanoTime() < endTime ){
				driver.switchTo().defaultContent();
				Thread.sleep(15000);
				wh.clickElement(Refresh);
				driver.switchTo().frame(0);
				String RunUI1 = wh.getText(RunID);
				System.out.println("RunUI1:"+RunUI1);
				String StatusUI1 = wh.getText(Status);
				System.out.println("Status1:"+StatusUI1);
				
				if(RunUI.equals(RunUI1))
				{
					if(StatusUI1.equals("Complete"))
					{
					report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
					wh.clickElement(RunID);
					wh.waitForPageLoaded();
					report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
					break;
					}
					if(StatusUI1.equals("Failed"))
					{
					report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
					rc.terminateTestCase("Job", StepResult.FAIL);
					break;
					}
					continue;
				}
				if (!RunUI.equals(RunUI1))
				{
					WebElement wb = driver.findElement(By.xpath(".//*[text()="+RunUI+"]/../following-sibling::td[1]"));
					WebElement wbID = driver.findElement(By.xpath(".//*[text()="+RunUI+"]"));
					String StatusUI2 = wh.getText(wb);
					System.out.println("StatusUI2:"+StatusUI2);
					if(StatusUI2.equals("Complete"))
					{
						report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
						wh.clickElement(wbID);
						wh.waitForPageLoaded();
						report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
						break;
					}
					if(StatusUI2.equals("Failed"))
					{
						report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
						break;
					}
					continue;
				}
				
			}
		}
		else	
			report.addReportStep("Checking the Job Status", "Job not yet completed ", StepResult.WARNING	);
	}
	
	public void runStatusValidationCons() throws Exception
	{
		
		//Status validation while Engine Run
		//driver.switchTo().frame(0);
		String StatusUI = wh.getText(Status);
		System.out.println("Status:"+StatusUI);
		String RunUI = wh.getText(RunID);
		System.out.println("RunUI:"+RunUI);
		
		if(StatusUI.equalsIgnoreCase("Starting") || StatusUI.equalsIgnoreCase("Running") || StatusUI.equalsIgnoreCase("Queued")|| StatusUI.equalsIgnoreCase("Selecting Orders") || StatusUI.equalsIgnoreCase("PreProcessing"))
		{
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(12L, TimeUnit.MINUTES);
			while ( System.nanoTime() < endTime ){
				driver.switchTo().defaultContent();
				Thread.sleep(15000);
				wh.clickElement(Refresh);
				driver.switchTo().frame(0);
				String RunUI1 = wh.getText(RunID);
				System.out.println("RunUI1:"+RunUI1);
				String StatusUI1 = wh.getText(Status);
				System.out.println("Status1:"+StatusUI1);
				
				if(RunUI.equals(RunUI1))
				{
					if(StatusUI1.equals("Complete"))
					{
					report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
					wh.clickElement(RunID);
					wh.waitForPageLoaded();
					report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
					break;
					}
					if(StatusUI1.equals("Failed"))
					{
					report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
					rc.terminateTestCase("Job", StepResult.FAIL);
					break;
					}
					continue;
				}
				if (!RunUI.equals(RunUI1))
				{
					WebElement wb = driver.findElement(By.xpath(".//*[text()="+RunUI+"]/../following-sibling::td[1]"));
					WebElement wbID = driver.findElement(By.xpath(".//*[text()="+RunUI+"]"));
					String StatusUI2 = wh.getText(wb);
					System.out.println("StatusUI2:"+StatusUI2);
					if(StatusUI2.equals("Complete"))
					{
						report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
						wh.clickElement(wbID);
						wh.waitForPageLoaded();
						report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
						break;
					}
					if(StatusUI2.equals("Failed"))
					{
						report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
						break;
					}
					continue;
				}
				
			}
		}
		else	
			report.addReportStep("Checking the Job Status", "Job not yet completed ", StepResult.WARNING	);
	}
	
	
	
	public void runStatusValidationXD() throws Exception
	{
		
		//Status validation while Engine Run
		//driver.switchTo().frame(0);
		String StatusUI = wh.getText(Status);
		System.out.println("Status:"+StatusUI);
		String RunUI = wh.getText(RunID);
		System.out.println("RunUI:"+RunUI);
		
		if(StatusUI.equalsIgnoreCase("Starting") || StatusUI.equalsIgnoreCase("Running") || StatusUI.equalsIgnoreCase("Queued")|| StatusUI.equalsIgnoreCase("Selecting Orders") || StatusUI.equalsIgnoreCase("PreProcessing"))
		{
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(12L, TimeUnit.MINUTES);
			while ( System.nanoTime() < endTime ){
				driver.switchTo().defaultContent();
				Thread.sleep(15000);
				wh.clickElement(Refresh);
				driver.switchTo().frame(0);
				String RunUI1 = wh.getText(RunID);
				System.out.println("RunUI1:"+RunUI1);
				String StatusUI1 = wh.getText(Status);
				System.out.println("Status1:"+StatusUI1);
				
				if(RunUI.equals(RunUI1))
				{
					if(StatusUI1.equals("Complete"))
					{
					report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
					wh.clickElement(RunID);
					wh.waitForPageLoaded();
					report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
					break;
					}
					if(StatusUI1.equals("Failed"))
					{
					report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
					rc.terminateTestCase("Job", StepResult.FAIL);
					break;
					}
					continue;
				}
				if (!RunUI.equals(RunUI1))
				{
					WebElement wb = driver.findElement(By.xpath(".//*[text()="+RunUI+"]/../following-sibling::td[1]"));
					WebElement wbID = driver.findElement(By.xpath(".//*[text()="+RunUI+"]"));
					String StatusUI2 = wh.getText(wb);
					System.out.println("StatusUI2:"+StatusUI2);
					if(StatusUI2.equals("Complete"))
					{
						report.addReportStep("Checking the Job Status", "Job completed Successfully", StepResult.PASS);
						wh.clickElement(wbID);
						wh.waitForPageLoaded();
						report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
						break;
					}
					if(StatusUI2.equals("Failed"))
					{
						report.addReportStep("Checking the Job Status", "Job Failed", StepResult.FAIL);
						break;
					}
					continue;
				}
				
			}
		}
		else	
			report.addReportStep("Checking the Job Status", "Job not yet completed ", StepResult.WARNING	);
	}
	
	public void runSummaries() throws Exception
	{
		//Fetching the Run Summaries After job Completion
		//driver.switchTo().frame(0);
		String s = wh.getText(RunStatus);
		System.out.println("St:"+s);
		if(wh.getText(RunStatus).equalsIgnoreCase("Complete"))
		{
			report.addReportStep("Checking the Run Status", "Run Status is Complete", StepResult.PASS);
		}
		if(wh.getText(RunStatus).equalsIgnoreCase("Failed"))
		{
			report.addReportStep("Checking the Run Status", "Run Status is Failed", StepResult.FAIL);
		}
		/*if(wh.isElementPresent(RunID, 5))
		{
			wh.clickElement(RunID);
			wh.waitForPageLoaded();
			driver.switchTo().activeElement();
			String s = wh.getText(RunStatus);
			System.out.println("St:"+s);
			if(wh.getText(RunStatus).equalsIgnoreCase("Complete"))
			{
				report.addReportStep("Checking the Run Status", "Run Status is Complete and Run Summary is Displayed", StepResult.PASS);
			}
		}*/
	}
	
	public void shipmentsCreated() throws Exception
	{
		if (wh.isElementPresent(NumberofShipmentscreated, 2))
		{
			shipment = wh.getText(NumberofShipmentscreated);
			report.addReportStep("Verfiying the Number of Shipments Created ", "The total Number of Shipments Created are : "+shipment+"", StepResult.PASS);
			wh.clickElement(NumberofShipmentscreated);
			wh.waitForPageLoaded();
			report.addReportStep("Clicked on Shipments Created Total link ", "Successfully clicked on Shipments Created Total link", StepResult.PASS);
			Thread.sleep(2000);
			//driver.switchTo().defaultContent();
			String s = wh.getText(ShipmentlistScreen);
			System.out.println("ShipmentlistScreen:"+s);
			if(s.equalsIgnoreCase("Shipment ID"))
			{
				report.addReportStep("Move to Run Summaries - Run Summary Shipment Screen ", "Successfully clicked and moved to Run Summaries - Run Summary Shipment List For RunID Screen", StepResult.PASS);
				//driver.switchTo().frame(0);
				wh.clickElement(ShipmentlistBack);
				wh.waitForPageLoaded();
				Thread.sleep(2000);
			}
			else
			{
				report.addReportStep("Clicked on Shipments Created Total link ", " Not able to Navigate to Run Summaries - Run Summary Shipment List For RunID Screen", StepResult.FAIL);
			}
		}
	}
	public void review() throws Exception
	{
		if(wh.isElementPresent(Review))
		{
			wh.clickElement(Review);
			//wh.waitForPageLoaded();
			wh.waitUntilDisappear(LoadingFrame);
			driver.switchTo().defaultContent();
			if(wh.isElementPresent(MaximizeShipments, 4))
			{
			wh.clickElement(MaximizeShipments);
			report.addReportStep("Clicking on Review Button", "Clicked on Review Button and moved to Shipment Screen", StepResult.PASS);
			}
			wh.waitForPageLoaded();
			wh.waitUntilDisappear(LoadingFrame);
			Thread.sleep(6000);
			//driver.switchTo().frame(0);
			//shipmentStatusRefreshButton1();
			driver.switchTo().activeElement();
			if(wh.isElementPresent(Statuscheck, 4))
					{
					List<WebElement> wb = driver.findElements(Statuscheck); 
					
					Iterator<WebElement> itrDO = wb.iterator();
					while(itrDO.hasNext())
					{					
						String s = itrDO.next().getText();
						if(!s.equalsIgnoreCase("Unplanned"))
							
						{											
							
							report.addReportStep("Checking for the Status", "The Status is Not Unplanned", StepResult.PASS);
							continue;
						}
						/*else
						{
							report.addReportStep("Searching for the Status", "The Status is Unplanned", StepResult.FAIL);
						}*/
						
					//	to retrieve the shipment is from Shipment screen
						
						/*List<WebElement> wbship = driver.findElements(Shipmentcheck); 
						Iterator<WebElement> itrDOship = wbship.iterator();
						while(itrDOship.hasNext())
						{					
							String ship = itrDOship.next().getText();						
							ar.add(ship);
						}*/
					}
		}
			
		}	
		else
		{
			report.addReportStep("Searching for Review Button", "Not able to Find the Review Button", StepResult.FAIL);
		}
	}
	
	public void shipmentSelect() throws Exception
	{
		System.out.println("in shipmentSelect");		
		
									
		if(wh.isElementPresent(ShipmentCheckBox, 6))
		{
			Thread.sleep(6000);
			wh.clickElement(ShipmentCheckBox);
			Thread.sleep(1000);
			report.addReportStep("Checking the Shipments", "All the Shipments Selected", StepResult.PASS);
		}
		else
		{
			report.addReportStep("Checking the Shipments", "Not able to select the Shipments", StepResult.FAIL);
		}
	}
	
	public void delete_Filter() throws Exception
	{
		//Deleting the Filter to re.use
		wh.waitUntilDisappear(LoadingFrame);	
		wh.clickElement(MaximizeDistributionOrder);
		/*wh.clickElement(SavedViews);
		wh.sendKeys(SavedViews, dataTable.getData(DataColumn.FilterName));*/
		Thread.sleep(4000);
		if(wh.isElementPresent(SavedViews))
		{
			WebElement wb = driver.findElement(SavedViews);
			wh.clickElement(SavedViews);
			wh.sendKeys(SavedViews, dataTable.getData(DataColumn.FilterName));	
			Thread.sleep(4000);
			wb.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			wh.clickElement(Delete);
			Thread.sleep(4000);
			if(wh.isElementPresent(OK, 4))
			{
				wh.clickElement(OK);
			}
			
		}
		
	}
	public void runTemplateFilterPreviewXD() throws Exception
	{		
		//Preview of Filter Data Template
		wh.waitUntilDisappear(LoadingFrame);	
		wh.clickElement(MaximizeRunTemplate);
		
		if(wh.isElementPresent(RunTemplateScreenHeader, 10))
		{
		
			
			if(wh.getText(RunTemplateScreenHeader).equalsIgnoreCase("Run Templates"))
			{	
				
				driver.switchTo().frame(0);
				
				wh.isElementPresent(TemplateCompleteForward, 4000);
				wh.clickElement(TemplateCompleteForward);
				
				int n=2;
				for(int i=0;i<=n;i++)
				{
					wh.isElementPresent(TemplateBackward, 4000);
					wh.clickElement(TemplateBackward);
					wh.waitForPageLoaded();
				}
				
				/*WebElement wb = driver.findElement(Goto);
				//wh.isElementPresent(Goto, 4);
				wh.clickElement(Goto);
				wh.sendKeys(Goto, "270");
				wb.sendKeys(Keys.ENTER);
				wh.waitForPageLoaded();*/
				
				if(wh.getText(ConsXD).equalsIgnoreCase("Cons XD11 CS MTW + MS + LTL"))
				{
				
					wh.clickElement(ConsXD);
					wh.waitForPageLoaded();
					Thread.sleep(4000);
					report.addReportStep("Click on the Template and verify the details ", "Clicked on the template and moved to Details Screen", StepResult.PASS);
					rc.logger.info("Template Selection : Cons XD11 CS MTW + MS + LTL Selected");
				}
				else
				{
					report.addReportStep("Searching for Cons XD11 CS MTW + MS + LTL ", "Not able to Find Cons XD11 CS MTW + MS + LTL", StepResult.FAIL);
				}
			}
		}
		else 
			report.addReportStep("Searching for RunTemplate Screen Header ", "Screen Header Not Found", StepResult.FAIL);
	}
	public void runTemplateFilterPreviewFleet() throws Exception
	{		
		
		wh.waitUntilDisappear(LoadingFrame);	
		wh.clickElement(MaximizeRunTemplate);
		
		if(wh.isElementPresent(RunTemplateScreenHeader, 10))
		{
		
			
			if(wh.getText(RunTemplateScreenHeader).equalsIgnoreCase("Run Templates"))
			{	
				
				driver.switchTo().frame(0);
				
				int n=1;
				for(int i=0;i<n;i++)
				{
					wh.isElementPresent(TemplateForward, 4000);
					wh.clickElement(TemplateForward);
					wh.waitForPageLoaded();
					
				}
				
				
			/*	WebElement wb = driver.findElement(Goto);
				//wh.isElementPresent(Goto, 4);
				wh.clickElement(Goto);
				wh.sendKeys(Goto, "30");
				wb.sendKeys(Keys.ENTER);
				wh.waitForPageLoaded();*/
				
				if(wh.getText(FleetRDC).equalsIgnoreCase("5085 RDC Fleet"))
				{
				
					wh.clickElement(FleetRDC);
					wh.waitForPageLoaded();
					Thread.sleep(4000);
					report.addReportStep("Click on the Template and verify the details ", "Clicked on the template and moved to Details Screen", StepResult.PASS);
					rc.logger.info("Template Selection : 5085 RDC Fleet is Selected");
				}
				else
				{
					report.addReportStep("Searching for 5085 RDC Fleet ", "5085 RDC Fleet", StepResult.FAIL);
				}
			}
		}
		else 
			report.addReportStep("Searching for RunTemplate Screen Header ", "Screen Header Not Found", StepResult.FAIL);
	}


/**
 * Method to refresh shipment
 * @throws Exception
 */
public void shipmentStatusRefreshButton1() throws Exception
{
	wh.clickElement(Refreshstatus);
	Thread.sleep(2000);
}
}







