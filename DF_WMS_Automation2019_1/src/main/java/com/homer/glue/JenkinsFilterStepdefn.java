package com.homer.glue;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.When;
import com.homer.enums.EnumClass.StepResult;

public class JenkinsFilterStepdefn extends BaseStepDefn {

	public JenkinsFilterStepdefn(DataClass data) {
		super(data);
		
	}
	
	@Given("^Testing Jenkins Filter one$")
	public void testing_Jenkins_Filter_one() throws Throwable { 
		
		/*driver.get("http://www.homedepot.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		rptUtil.addReportStep("Testing fail step", "Fail step tested", StepResult.FAIL);*/
		
		//System.out.println("Testing Jenkins Filter one");
		
		homePage.failStep1();
	}

	@Given("^Testing Jenkins Filter two$")
	public void testing_Jenkins_Filter_two() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter two");
		
		/*report.addReportStep("Fail tc two", 
				"Failed tc", 
				StepResult.FAIL);*/
		
		//homePage.failStep2();		
	}

	@Given("^Testing Jenkins Filter three$")
	public void testing_Jenkins_Filter_three() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter three");
		
		report.addReportStep("Fail tc  3", 
				"Failed tc", 
				StepResult.FAIL);
		
		homePage.failStep3();
	}

	@Given("^Testing Jenkins Filter four$")
	public void testing_Jenkins_Filter_four() throws Throwable { 
	  
		System.out.println("Testing Jenkins Filter four");
		
		report.addReportStep("Fail tc 4", 
				"Failed tc", 
				StepResult.FAIL);	
		
		homePage.failStep4();
	}

	@Given("^Testing Jenkins Filter five$")
	public void testing_Jenkins_Filter_five() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter five");
		
		//homePage.failStep5();
	}

	@Given("^Testing Jenkins Filter six$")
	public void testing_Jenkins_Filter_six() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter six");
		//homePage.failStep6();
	}

	@Given("^Testing Jenkins Filter seven$")
	public void testing_Jenkins_Filter_seven() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter seven");
		/*report.addReportStep("Fail tc 5", 
				"Failed tc", 
				StepResult.FAIL);
		
		report.addReportStep("Fail tc 10 one", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 two", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 one", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 two", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 three", 
				"Failed tc", 
				StepResult.FAIL);*/
		
		//homePage.failStep7();
	}

	@Given("^Testing Jenkins Filter eight$")
	public void testing_Jenkins_Filter_eight() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter eight");
		homePage.failStep8();
	}

	@Given("^Testing Jenkins Filter nine$")
	public void testing_Jenkins_Filter_nine() throws Throwable {
		
		System.out.println("Testing Jenkins Filter nine");	 
		
		homePage.failStep9();
		report.addReportStep("Fail tc 9", 
				"Failed tc", 
				StepResult.FAIL);
	}

	@Given("^Testing Jenkins Filter ten$")
	public void testing_Jenkins_Filter_ten() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter ten");	
	/*	report.addReportStep("Fail tc 10", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 one", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 two", 
				"Failed tc", 
				StepResult.FAIL);
		
		report.addReportStep("Fail tc 10 one", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 two", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 one", 
				"Failed tc", 
				StepResult.FAIL);
		report.addReportStep("Fail tc 10 two", 
				"Failed tc", 
				StepResult.FAIL);*/
		
		homePage.failStep10();
	}

	@Given("^Testing Jenkins Filter eleven$")
	public void testing_Jenkins_Filter_eleven() throws Throwable { 
	  
		System.out.println("Testing Jenkins Filter eleven");	
		homePage.failStep11();
	}

	@Given("^Testing Jenkins Filter twelve$")
	public void testing_Jenkins_Filter_twelve() throws Throwable {
	  
		System.out.println("Testing Jenkins Filter twelve");
		homePage.failStep12();
	}
	
	@Given("^Testing Jenkins Filter thirteen$")
	public void testing_Jenkins_Filter_thirteen() throws Throwable { 
	  
		System.out.println("Testing Jenkins Filter thirteen");
		homePage.failStep13();
	}

	@Given("^Testing Jenkins Filter fourteen$")
	public void testing_Jenkins_Filter_fourteen() throws Throwable { 
	  
		System.out.println("Testing Jenkins Filter fourteen");
		homePage.failStep14();
	}

	@Given("^Testing Jenkins Filter fifteen$")
	public void testing_Jenkins_Filter_fifteen() throws Throwable { 
		
		System.out.println("Testing Jenkins Filter fifteen");
		homePage.failStep15();
	}
	
	@Given("^Testing Jenkins Filter MultiIteration$")
	public void testing_Jenkins_Filter_MultiIteration() throws Throwable { 
		
		System.out.println("Iteration is "+data.iterationNo);	  
	}
	
	@When("^I place order using card$")
	public void i_place_order_using_card(String arg1) throws Throwable { 
	  
		report.addReportStep("test card dtls" +arg1, "tested card dtls", StepResult.PASS);
	}

}
