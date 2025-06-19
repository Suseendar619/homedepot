package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.DFWMSDORCanonicalPostPageObject;

public class DFWMSRunWavesPageStepDeftn extends BaseStepDefn {

	 public String poNBR = "0916190C";
	 public static String validation = "";
	public DFWMSRunWavesPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^Click Submit and get Wave number$")
	public void click_Submit_and_get_Wave_number() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.selectSubmitBtn();
	}

	@Then("^UndoWave in WaveUI$")
	public void undoWave_in_WaveUI() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.unDoWaveInWaveUI();

	}
	
	@Then("^UndoWave in WaveUI Packed$")
	public void undoWave_in_WaveUI_Packed() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.unDoWaveInWaveUIPacked();

	}


	@Then("^Select Wave \"(.*?)\" and Run")
	public void select_Wave(String screen) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		validation = dfwmsrunwavespageobject.selectWaveCheckBox(screen);
	}

	@Then("^Select Wave (\\d+) \"(.*?)\" and Run$")
	public void select_Wave_2012_arg1_and_Run(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		validation = dfwmsrunwavespageobject.selectWaveCheckBox2012(screen);

	}

	@Then("^Select Loadtrailer and and check the Max olpn Value$")
	public void select_Loadtrailer_and_and_check_the_Max_olpn_Value() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.selectLoadTrailer();
	}

	@Then("^Select Rule \"(.*?)\" Define and Submit")
	public void select_Wave_Rule(String screen) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrunwavespageobject.selectRuleCheckBox(screen,
				DFWMSDORCanonicalPostPageObject.poNBR); 

	}

	@Then("^Input and Apply Wave")
	public void input_Apply_Wave() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrunwavespageobject.enterWaveandApply();
	}

	@Then("^Validate Wave Completion")
	public void validate_Wave_Attribute() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrunwavespageobject.ValidateWaveAttributes();
	}
	
	@Then("^Cancel oLPN from oLPNs UI \"(.*?)\"$")
	public void cancel_oLPN_from_oLPNs_UI_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.cancelOlpnFromUI(screen);

	}

	@Then("^Change olpn carrier \"(.*?)\"$")
	public void change_olpn_carrier_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.changeOLPNCarrier(screen);

	}

	@Then("^Validate Wave Results Order \"(.*?)\" Units \"(.*?)\" Allocated \"(.*?)\" Shorted \"(.*?)\" Tasks \"(.*?)\" oLPNs \"(.*?)\" values$")
	public void validate_Wave_Result(String sOrder, String sUnits,String sAllocated, String sShorted,String sTasks, String soLPNs) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrunwavespageobject.ValidateWaveResults(sOrder,sUnits,sAllocated,sShorted,sTasks,soLPNs);
	}
	
	@Then("^Release Task From Wave \"(.*?)\"$")
	public void release_Task_From_Wave_arg1(String Screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 

		dfwmsrunwavespageobject.releaseTaskFromWave(Screen);
	}

	@Then("^Open Remote Server \"(.*?)\"$")
	public void open_Remote_Server_arg1(String Screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.openRemoteServer(Screen);

	}
	
	
	@Then("^Open RouterIntlligrated and send request \"(.*?)\"$")
	public void open_RouterIntlligrated_and_send_request_arg1(String Screen) throws Throwable { 
		// Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.RouterIntelligrated(Screen);
	}

	@Then("^Multis Packing in RF \"(.*?)\"$")
	public void multis_Packing_in_RF_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.multisPackingInRF(screen);
		
	}
	
	@Then("^Singles Packing \"(.*?)\"$")
	public void Singles_Packing_in_RF_arg1(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.SinglesPacking(screen);
		
	}
	
	@Then("^Short at Multis \"(.*?)\"$")
	public void Short_at_Multis(String screen) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	  
		dfwmsrunwavespageobject.ShortatMultis(screen);
		
	}

//	@Then("^Task Picking in RF \"(.*?)\"$")
//	public void task_Picking_in_RF_arg1(String Screen) throws Throwable { 
//	 // Write code here that turns the phrase above into concrete actions 
//	  
//		dfwmsrunwavespageobject.taskPickingRF(Screen);
//	}
//	
	@Then("^Open Wave Tasks")
	public void open_Wave_Tasks() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dfwmsrunwavespageobject.OpenWaveTasks();
	}
	@Then("^Open Wave Lpns$")
	public void open_Wave_Lpns() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		dfwmsrunwavespageobject.OpenWaveLpns();
	}
	
	@Then("^UndoWave by WaveNumber$")
	public void undoWave_by_WaveNumber() throws Throwable { 
		
		dfwmsrunwavespageobject.undoWave();
	  
	}

}
