package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSDORCanonicalPostPageObject;

public class DFWMSDistributionOrdersPageStepDeftn extends BaseStepDefn {

	public DFWMSDistributionOrdersPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/*@When("^Input and Apply DO$")
	public void input_And_Apply_DO() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		 dfwmsdistributionorderspageobject.DOInputandSearch(DFWMSDORCanonicalPostPageObject.poNBR);
	//	 dfwmsdistributionorderspageobject.DOInputandSearch("0916190C");
		Thread.sleep(4000);
	}*/

	@Then("^Validate DO Status \"(.*?)\"$")
	public void validate_DO_Status(String sStatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		 dfwmsdistributionorderspageobject.ValidateDOStatus(sStatus,DFWMSDORCanonicalPostPageObject.poNBR);
	//	 dfwmsdistributionorderspageobject.ValidateDOStatus("Released","0916190C");
	}
	
	@Then("^Validate DO Route To \"(.*?)\"$")
	public void validate_DO_RouteTo(String sStatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		 dfwmsdistributionorderspageobject.ValidateDORouteTo(sStatus,DFWMSDORCanonicalPostPageObject.poNBR);
	//	 dfwmsdistributionorderspageobject.ValidateDOStatus("Released","0916190C");
	}
	
	@Then("^Validate DO Route Type 1 \"(.*?)\"$")
	public void validate_DO_RouteType1(String sStatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		 dfwmsdistributionorderspageobject.ValidateDORouteType1(sStatus,DFWMSDORCanonicalPostPageObject.poNBR);
	//	 dfwmsdistributionorderspageobject.ValidateDOStatus("Released","0916190C");
	}

}
