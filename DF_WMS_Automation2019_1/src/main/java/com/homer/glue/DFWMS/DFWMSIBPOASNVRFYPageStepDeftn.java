package com.homer.glue.DFWMS;

import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.stream.StreamResult;

import com.homer.dao.DataClass;
import com.homer.dao.InstanceContainer;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.DFWMSDORCanonicalPostPageObject;
import com.homer.po.DFWMS.DFWMSRFPageObject;

public class DFWMSIBPOASNVRFYPageStepDeftn extends BaseStepDefn {

	public String InboundPO, ASNID,AppointmentID;
	public String Appointmenttype = "Live Unload";
	public String Equipmentcode = "28";
	public static ArrayList siLPNs1 = new ArrayList();
	
	public DFWMSIBPOASNVRFYPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Then("^PO POST$")
	public void pO_POST() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		InboundPO = DFWMSIBPOFormatPOSTPageObject.FormatXML();
		System.out.println("Generated PO number::" + InboundPO);
		String filepath = null;
		filepath =  System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\Multi_Item_qty_PO.xml";
		StreamResult result = new StreamResult(new File(filepath));
		System.out.println(filepath);
		DFWMSIBPOFormatPOSTPageObject.DF_Upload_File(filepath);
		//DF_DO_Stat_Verify2.dO_Status_Post(DistrOrder);
		}
	@Then("^Input and Apply PO$")
	public void input_and_Apply_PO() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.po_input_search(InboundPO);
	}

	@Then("^Validate PO status \"(.*?)\"$")
	public void validate_PO_Status(String sStatus) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	//	DFWMSIBPOFormatPOSTPageObject.ValidatePOStatus(sStatus,InboundPO);
//		 dfwmsdistributionorderspageobject.ValidateDOAttributes("Released","1011584A");
	}
	
	@Then("^Validate PO Status \"(.*?)\"$")
	public void validate_PO_Status_arg1(String sStatus) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.ValidatePOStatus(sStatus,InboundPO);
	}

	@Then("^ASN PO mapping$")
	public void aSN_PO_mapping() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		ASNID = DFWMSIBPOFormatPOSTPageObject.ValidateASNPOMap(InboundPO);
		DFWMSIBPOFormatPOSTPageObject.POASNMapping(ASNID);
	//	POASNMapping
	}
	@Then("^Create iLPNs$")
	public void create_iLPNs() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.CreateiLPNfromASN(DFWMSInbounfFlowStepDefn.ASNID);
	}
	@Then("^Create iLPNS in ASN$")
	public void create_iLPNS_in_ASN() throws Throwable { 
		DFWMSIBPOFormatPOSTPageObject.CreateiLPNfromASN(DFWMSInbounfFlowStepDefn.ASNID);
	}
	@Then("^Input and Apply ASN$")
	public void input_and_Apply_ASN() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.InputASNApply(ASNID);
	}

	@Then("^Validate ASN Status \"(.*?)\"$")
	public void validate_ASN_Status_arg1(String sStatus) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.ValidateASNStatus(sStatus, ASNID);
	}
	

	@Then("^Validate iLPN$")
	public void validate_iLPN() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		siLPNs1 = DFWMSIBPOFormatPOSTPageObject.validateilpncrtn(ASNID);
	}
	
	@Then("^IB Appointment Creation$")
	public void iB_Appointment_Creation() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		AppointmentID = DFWMSAPPTCREATIONPageObject.DF_IB_APPT_CREATION(Appointmenttype, Equipmentcode, ASNID);
	}

	@Then("^DF_IB_NP_CheckIn$")
	public void dF_IB_NP_CheckIn() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSAPPTCREATIONPageObject.DFOBCheckInNavigation(AppointmentID);
		DFWMSAPPTCREATIONPageObject.DF_IB_CheckIn_Fill_Details();
	}
	
	@Then("^Receive Detail$")
	public void receive_Detail() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//dfwmsrfpageobject.RCV_DTL(DFWMSIBPOFormatPOSTPageObject.siLPNs,DFWMSAPPTCREATIONPageObject.sDockdoorbarcode,ASNID);
		dfwmsrfpageobject.RCV_DTL(siLPNs1,DFWMSAPPTCREATIONPageObject.sDockdoorbarcode,ASNID);
	}
	
	@Then("^Validate Open PO QTY$")
	public void validate_Open_PO_QTY() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.ValidatePOASNQTY(InboundPO, ASNID);
	}
	
	@Then("^Validate PO details$")
	public void validate_PO_details() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.getValidatepodtls(InboundPO);
	}
	
	@Then("^Validate Pix Transactions$")
	public void validate_Pix_Transactions() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.validatepixtran(ASNID);
		Thread.sleep(1000);
		DFWMSIBPOFormatPOSTPageObject.getPIXtrncodehdr01(ASNID);
		Thread.sleep(1000);
		DFWMSIBPOFormatPOSTPageObject.getPIXtrncodeitm02(ASNID);
	}
	
	@Then("^Validate LPNLCKQA$")
	public void validate_LPNLCKQA() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		DFWMSIBPOFormatPOSTPageObject.validatelcklpn(ASNID);
	}

}
