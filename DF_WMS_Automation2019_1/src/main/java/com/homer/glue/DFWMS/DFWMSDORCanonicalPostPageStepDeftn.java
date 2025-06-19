package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.*;

public class DFWMSDORCanonicalPostPageStepDeftn extends BaseStepDefn {

	public DFWMSDORCanonicalPostPageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@And("^Post dynamically generated XML \"(.*?)\" times order type \"(.*?)\"$")
	public void post_dynamically_generated_XML(String xmlCount, String sOrdType) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		int iXMLCount = Integer.parseInt(xmlCount);
		while(iXMLCount>0){
		 dfwmsdorcanonicalobject.postMessageXml(sOrdType);
//		 dfwmsdorcanonicalobject.postMessageResponse();
		 iXMLCount--;
		}
	}

	@Then("^Verify the post response$")
	public void verify_the_response() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		 dfwmsdorcanonicalobject.postMessageResponse();
	}

}
