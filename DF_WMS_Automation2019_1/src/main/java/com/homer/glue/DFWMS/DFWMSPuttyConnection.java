package com.homer.glue.DFWMS;

import com.homer.dao.DataClass;
import com.homer.dao.Then;
import com.homer.glue.BaseStepDefn;

public class DFWMSPuttyConnection extends BaseStepDefn {

	public DFWMSPuttyConnection(DataClass data) {
		super(data);

	}

	@Then("^Post dynamically generated json \"(.*?)\" with \"(.*?)\"$")
	public void post_dynamically_generated_json_arg1_with_arg2(String xmlCount, String sOrderType) throws Throwable {
		//Thread.sleep(3000);
		int iXMLCount = Integer.parseInt(xmlCount);
		System.out.println("Glue file xml count" +iXMLCount);
		while (iXMLCount > 0) {
			dfwmsputtyobject.puttyJson(sOrderType);
			// dfwmsdorcanonicalobject.postMessageResponse();
			iXMLCount--;

		}
	}

}
