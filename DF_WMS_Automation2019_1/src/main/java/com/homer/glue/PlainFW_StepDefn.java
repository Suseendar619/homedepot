package com.homer.glue;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.homer.dao.After;
import com.homer.dao.Before;
import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.RunManagerColumns;
import com.homer.enums.EnumClass.StepResult;
import com.homer.resuablecomponents.DriverFactory;

public class PlainFW_StepDefn extends BaseStepDefn {

	public PlainFW_StepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	/*@Before 
	public void beforeMethod() throws Exception {
		
		System.out.println("Method called  before");
		
		//setDriverUsingRunConfig();
		//setDriverUsingRunManager();
	}*/

	private void setDriverUsingRunConfig() throws Exception {
		
		DriverFactory df = new DriverFactory();
		driver = df.getSauceLabDriver(data.testCaseName);
		
		data.tools = driver;
		
		Capabilities cap = ((RemoteWebDriver) driver)
				.getCapabilities();
		
		String browserDts = cap.getBrowserName() + " " + cap.getVersion();
		
		data.report.setHeaderLabelValue(browserDts);
	}
	
	private void setDriverUsingRunManager() throws Exception {
		
		DriverFactory df = new DriverFactory();
		
		String browserName = dataTable.getRunManagerData(RunManagerColumns.Browser);
		
		if(browserName.startsWith("I")) {
			
			browserName = "internet explorer";
		}
		
		String browserVersion = dataTable.getRunManagerData(RunManagerColumns.BrowserVersion);
		String platform = dataTable.getRunManagerData(RunManagerColumns.Platform);

		driver = df.getSauceLabDriverRunManager(data.testCaseName, browserName, browserVersion, platform);
		
		data.tools = driver;
		
		Capabilities cap = ((RemoteWebDriver) driver)
				.getCapabilities();
		
		String browserDts = cap.getBrowserName() + " " + cap.getVersion();
		
		data.report.setHeaderLabelValue(browserDts);
	}
	
	@Before("Priority2")
	public void beforePriority1() throws Exception {
			
		System.out.println("Method called  before priority two");
	}
	
	@After("Priority2")
	public void afterPriority1() throws Exception {
			
		System.out.println("Method called  after priority two");
	}
	
	@Before("Iteration")
	public void beforeIteration() throws Exception {
			
		System.out.println("Method called  before Iteration");
	}
	
	@After("Iteration")
	public void afterIteration() throws Exception {
			
		System.out.println("Method called  after Iteration");
	}	
	
	/*@After
	public void afterMethod() {
		
		System.out.println("Method called  after");
		
		//driver.quit();		
	}*/
	
	@Given("^I test tool created in before method$")
	public void i_test_tool_created_in_before_method() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	
		rptUtil.addReportStep("Test with screen shot",
				"Captured screen shot", 
				StepResult.FAIL);
	}

}
