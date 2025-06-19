package com.homer.glue.DFWMS;

import com.homer.dao.And;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Then;
import com.homer.dao.When;
import com.homer.glue.BaseStepDefn;
import com.homer.po.DFWMS.TPEPostMessagePageObject;
import com.homer.po.DFWMS.TPEPostMessagePageObject.POTypes;

public class TPEPostMessagePageStepDeftn extends BaseStepDefn{

	String po = "";
	public TPEPostMessagePageStepDeftn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
				

	@When("^Click on Menu and search for Post Message$")
	public void click_on_Menu_and_search_for_Post_Message() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpehomepageobject.TPEmenu();		
		tpehomepageobject.searchInput("Post Message", "Technical");
	}
	

	@And("^Enter dynamically generated XML$")
	public void enter_dynamically_generated_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.Poxml);
	}
	
	@And("^Enter dynamically generated XML2$")
	public void enter_dynamically_generated_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.xml2);
	}

	@Then("^Verify the response$")
	public void verify_the_response() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageResponse();
	}	
	@Then("^Verify the response1$")
	public void verify_the_response1() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageResponse1();
	}
	
	@And("^Reset the Post Message screen$")
	public void reset_the_post_message_screen() throws Throwable { 
		tpepostmessagepageobject.clickResetButton();
		Thread.sleep(4000);
	}

	@And("^Enter dynamically generated Cons XML$")
	public void enter_dynamically_generated_Cons_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.Poxml, TPEPostMessagePageObject.POTypes.CONS);
	}
	@And("^Enter dynamically generated Cons XML2$")
	public void enter_dynamically_generated_Cons_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	
		tpepostmessagepageobject.postMessageXml(DataColumn.xml2, TPEPostMessagePageObject.POTypes.CONS);
	}
	@And("^Enter dynamically generated XD XML$")
	public void enter_dynamically_generated_XD_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.Poxml, TPEPostMessagePageObject.POTypes.XD);
	}
	@And("^Enter dynamically generated XD XML2$")
	public void enter_dynamically_generated_XD_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpepostmessagepageobject.postMessageXmlXD(dataTable.getData(DataColumn.Poxml));
		tpepostmessagepageobject.postMessageXml(DataColumn.xml2, TPEPostMessagePageObject.POTypes.XD);
	}
	@And("^Posting XD XML1$")
	public void posting_XD_XML1() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.Poxml,POTypes.XD,dataTable.getData(DataColumn.QName));
	}
	@And("^Posting XD XML2$")
	public void posting_XD_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.xml2,POTypes.XD,dataTable.getData(DataColumn.QName));
	}
	@And("^Enter dynamically generated Fleet XML$")
	public void enter_dynamically_generated_Fleet_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.Poxml, TPEPostMessagePageObject.POTypes.FLEET);
	}
	@And("^Enter dynamically generated Fleet2 XML$")
	public void enter_dynamically_generated_Fleet2_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXml(DataColumn.xml2, TPEPostMessagePageObject.POTypes.FLEET);
	}
	@And("^Posting Fleet XML1$")
	public void posting_Fleet_XML1() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.Poxml,POTypes.FLEET,dataTable.getData(DataColumn.QName));
	}
	@And("^Posting Fleet XML2$")
	public void posting_Fleet_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.xml2,POTypes.FLEET,dataTable.getData(DataColumn.QName));
	}
	@And("^Post dynamically generated XML through MQ$")
	public void post_dynamically_generated_XML_through_MQ() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postMessageXmlMQ(DataColumn.Poxml,POTypes.STANDARD,dataTable.getData(DataColumn.QName));
	}
	@And("^Posting XML$")
	public void posting_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.Poxml,POTypes.STANDARD,dataTable.getData(DataColumn.QName));
	}
	@And("^Posting EDI753 XML$")
	public void posting_EDI753_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.EDI753,POTypes.STANDARD,dataTable.getData(DataColumn.RTSMQ));
	}
	@And("^Posting Cons XML1$")
	public void posting_Cons_XML1() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.Poxml,POTypes.CONS,dataTable.getData(DataColumn.QName));
	}
	@And("^Posting Cons XML2$")
	public void posting_Cons_XML2() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		tpepostmessagepageobject.postingXML(DataColumn.xml2,POTypes.CONS,dataTable.getData(DataColumn.QName));
	}
	
	@And("^Enter dynamically generated Tracking Msg XML2$")
	public void enter_dynamically_generated_Tracking_Msg_XML() throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
		//tpepostmessagepageobject.postingXML(DataColumn.xml2, TPEPostMessagePageObject.POTypes.TRACKINGMSG,dataTable.getData(DataColumn.RTSMQ));
		tpepostmessagepageobject.postingXMLEDI(DataColumn.xml2, TPEPostMessagePageObject.POTypes.TRACKINGMSG,dataTable.getData(DataColumn.RTSMQ));
		//tpepostmessagepageobject.postMessageXml(DataColumn.xml2, TPEPostMessagePageObject.POTypes.TRACKINGMSG);
	}
	
	@And("^Post PO$")
	public void post_po() throws Throwable { 

		post_xml_message(DataColumn.Poxml);

	}
	
	@And("^Post EDI753$")
	public void post_edi753() throws Throwable { 

		post_xml_message(DataColumn.EDI753);

	}
	
	public void post_xml_message(String type) throws Throwable {

		// Click Menu button
		// Type "Post Message" in search form
		
		click_on_Menu_and_search_for_Post_Message();
		
		// Click in text input area
		// Fill in form with data request PO
		// Click Send

		tpepostmessagepageobject.postMessageXml(type);

	}
	
}
