package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;

public class DFWMSTasksPageStepDeftn extends BaseStepDefn {

	public DFWMSTasksPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^Get Task Details$")
	public void Get_Task_Details() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmstaskspageobject.ClearTaskArrayList();
		dfwmstaskspageobject.GetTaskDetails();
		//		 System.out.println(dfwmstaskspageobject.sTaskID.length);
	}

	@Then("^Select and Release Wave Task$")
	public void release_Wave_Task() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmstaskspageobject.SelectandReleaseTaskList();
		
		//		 System.out.println(dfwmstaskspageobject.sTaskID.length);
	}

	@Then("^Validate Task Status \"(.*?)\"$")
	public void Validate_Task_Status(String taskstatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmstaskspageobject.validateTaskStatus(taskstatus);
		//		 System.out.println(dfwmstaskspageobject.sTaskID.length);
	}

}