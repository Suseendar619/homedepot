package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;

public class DFWMSCycleCountStepDefn extends BaseStepDefn{

	public DFWMSCycleCountStepDefn(DataClass data) {
		super(data);
	}

	@Then("^CC Active Equals$")
	public void cC_Active_Equals() throws Throwable { 
	  dfwmsCycleCountPageObject.ccActiveEquals();
	}

	@Then("^CC Reserve Equals$")
	public void cC_Reserve_Equals() throws Throwable { 
		dfwmsCycleCountPageObject.ccReserveEquals();
	}

	@Then("^CC Active Less$")
	public void cC_Active_Less() throws Throwable { 
		dfwmsCycleCountPageObject.ccActiveLess();
	}

	@Then("^CC Reserve Less$")
	public void cC_Reserve_Less() throws Throwable { 
		dfwmsCycleCountPageObject.ccReserveLess();
	}

	@Then("^CC Active More$")
	public void cC_Active_More() throws Throwable { 
		dfwmsCycleCountPageObject.ccActiveMore();
	}

	@Then("^CC Reserve More$")
	public void cC_Reserve_More() throws Throwable { 
		dfwmsCycleCountPageObject.ccReserveMore();
	}
	
}
