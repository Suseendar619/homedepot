package com.homer.glue;

import org.openqa.selenium.WebDriver;

import com.homer.dao.CommonData;
import com.homer.dao.DataClass;
import com.homer.dao.InstanceContainer;
import com.homer.glue.DFWMS.DFWMSIBCheckinPageObject;
import com.homer.glue.DFWMS.DFWMSToolsStepDeftn;
import com.homer.helper.DataTable;
import com.homer.po.CheckoutSignInPage;
import com.homer.po.HomePage;
import com.homer.po.PIPPage;
import com.homer.po.PLPPage;
import com.homer.po.PaymentPage;
import com.homer.po.ShippingPage;
import com.homer.po.ShoppingCartPage;
import com.homer.po.ThankYouPage;
import com.homer.po.DFWMS.*;
import com.homer.reports.Report;
import com.homer.resuablecomponents.JDBC_Connection;
import com.homer.resuablecomponents.ReportUtil;
import com.homer.resuablecomponents.ReusableComponents;
import com.homer.resuablecomponents.WebDriverHelper;

public class BaseStepDefn {

	protected Report report;
	protected DataTable dataTable;
	protected JDBC_Connection jDBC_Connection;

	protected WebDriverHelper wh;
	protected DataClass data;

	protected WebDriver driver;
	protected ReusableComponents rc;
	protected InstanceContainer ic;

	protected HomePage homePage;
	protected PLPPage plpPage;
	protected PIPPage pipPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckoutSignInPage checkoutSignInPage;
	protected ShippingPage shippingPage;
	protected PaymentPage paymentPage;
	protected ThankYouPage thankYouPage;

	protected TPELoginPageObject tpeloginpoobject;
	protected TPEHomePageObject tpehomepageobject;
	protected TPEPostMessagePageObject tpepostmessagepageobject;
	protected TPEPurchaseOrderPageObject tpepurchaseorderpageobject;
	protected TPEDistributionOrderPageObject tpedistributionorderpageobject;
	protected TPEShipmentsPageObject tpeshipmentspageobject;
	protected TPEShipmentDetailPageObject tpeshipmentdetailpageobject;
	protected TPEWebTendersPageObject tpewebtenderspageobject;
	protected VPLoginPageObject VPLoginPageObject;
	protected VPPurchaseOrderPageObject vppurchaseOrderPageObject;
	protected VPRTSListPageObject vprtslistpageobject;
	protected RunTemplatePageObject runtemplatepageobject;
	protected TPEAppointmentsPageObject tpeappointmentspageobject;
	protected TPETransactionsPageObject tpetransactionspageobject;
	protected MQPageObject mqpageobject;
	protected DFWMSLoginPageObject dfwmsloginpoobject;
	protected DFWMSToolsPageObject dfwmsToolsPageObject;
	protected DFWMSDORCanonicalPostPageObject dfwmsdorcanonicalobject;
	protected DFWMSHomePageObject dfwmshomepageobject;
	protected DFWMSDistributionOrdersPageObject dfwmsdistributionorderspageobject;
	protected DFWMSRunWavesPageObject dfwmsrunwavespageobject;
	protected DFWMSTasksPageObject dfwmstaskspageobject;
	protected DFWMSRFPageObject dfwmsrfpageobject;
	protected DFWMSoLPNsPageObject dfwmsolpnspageobject;
	protected DFWMSPckstationPageObject DFWMSPckstationPageObject;
	protected DFWMSShpmtcreationPageObject DFWMSShpmtcreationPageObject;
	protected DFWMSAPPTCREATIONPageObject DFWMSAPPTCREATIONPageObject;
	protected DFWMSShpConfRulesObject DFWMSShpConfRulesObject;
	protected DFWMSIBPOFormatPOSTPageObject DFWMSIBPOFormatPOSTPageObject;
	protected DFWMSPuttyPageObject dfwmsputtyobject;
	//inbound
	protected DFWMSPOObject dfwmsPOObject;
	protected DFWMSDOObject dfwmsDOObject;
	protected DFWMSScheduleAppointmentPageObject dfwmsScheduleAppointmentPageObject;
	protected DFWMSIBCheckinPageObject dfwmsIBCheckinPageObject;
	protected DFWMSInboundReceiveInPuttyPageObject dfwmsInboundReceiveInPuttyPageObject;
	protected DFWMSValidationsPageObject dfwmsValidationsPageObject;
	protected DFWMSInboundVerifyInPuttyPageObject dfwmsInboundVerifyInPuttyPageObject;
	protected DFWMSInboundPutawayInPuttyPageObject dfwmsInboundPutawayInPuttyPageObject;
	protected DFWMSAddLocationsPageObject dfwmsAddLocationsPageObject;
	protected DFWMSVerifyIBShipmentCheckOutInPuttyPageObject dfwmsVerifyIBShipmentCheckOutInPuttyPageObject;
	protected DFWMSIBAssingShipmentPageObject dfwmsIBAssingShipmentPageObject;



	//DFWMSShpConfRulesObject

	//2019
	protected DFWMS2019HomePageObject dfwms2019HomePageObject;
	protected DFWMS2019PostXmlPageObject dfwms2019PostXmlPageObject;
	protected DFWMSHomePageObject2019 dfwmsHomePageObject2019;
	protected DFWMS2019IBPOFormatPOSTPageObject dfwms2019IBPOFormatPostPageObject;
	protected DFWMSOutboundPickingNCNinPutty dfwmsOutboundPickingNCNinPutty;
	protected DFWMSPickCartPickinginPuttyPageObject dfwmsPickCartPickinginPuttyPageObject;
	protected DFWMSPackCartinPuttyPageObject dfwmsPackCartinPuttyPageObject;
	protected DFWMSCreateShipmentPageObject dfwmsCreateShipmentPageObject;
	protected DFWMSLoadTrailerInPuttyPageObject dfwmsLoadTrailerInPuttyPageObject;
	protected DFWMSCloseTrailerInPuttyPageObject dfwmsCloseTrailerInPuttyPageObject;
	protected DFWMSShpConfRulesObject dfwmsShpConfRulesObject;
	protected DFWMSPackCubeDirinPuttyPageObject dfwmsPackCubeDirinPuttyPageObject;
	protected DFWMSPickToLabelinPuttyPageObject dfwmsPickToLabelinPuttyPageObject;
	protected DFWMSCycleCountPageObject dfwmsCycleCountPageObject;
	protected DFWMSWeighAndManifestOlpnsPageObject dfwmsWeighAndManifestOlpnsPageObject;
	
	protected VPHomePage VPHomePage;

	CommonData commonData;
	protected WebDriver newWebDriver;
	protected String testTxt;

	protected ReportUtil rptUtil;

	public BaseStepDefn(DataClass data) {

		this.data = data;
		this.driver = data.driver;
		this.report = data.report;
		this.dataTable = data.dataTable;
		this.commonData = (CommonData) data.commonData;
		

		wh = new WebDriverHelper(driver, report, dataTable);
		rc = new ReusableComponents(driver, report, wh, dataTable, data);

		ic = new InstanceContainer(driver, report, dataTable, wh, rc,
				commonData, jDBC_Connection);

		if (data.tools != null) {

			driver = (WebDriver) data.tools;
			rptUtil = new ReportUtil(driver, report, data);
		}

		/*
		 * if(data.tools!=null) {
		 * 
		 * System.out.println(data.tools.toString());
		 * 
		 * HashMap<String, Object> mapObj = (HashMap<String, Object>)
		 * data.tools;
		 * 
		 * newWebDriver = (WebDriver) mapObj.get("FFDriver"); testTxt = (String)
		 * mapObj.get("TxtMsg"); //Typecast and assign }
		 */

		homePage = new HomePage(ic);
		plpPage = new PLPPage(ic);
		pipPage = new PIPPage(ic);
		shoppingCartPage = new ShoppingCartPage(ic);
		checkoutSignInPage = new CheckoutSignInPage(ic);
		shippingPage = new ShippingPage(ic);
		paymentPage = new PaymentPage(ic);
		thankYouPage = new ThankYouPage(ic);

		tpeloginpoobject = new TPELoginPageObject(ic);
		tpehomepageobject = new TPEHomePageObject(ic);
		tpepostmessagepageobject = new TPEPostMessagePageObject(ic);
		tpepurchaseorderpageobject = new TPEPurchaseOrderPageObject(ic);
		tpedistributionorderpageobject = new TPEDistributionOrderPageObject(ic);
		tpeshipmentspageobject = new TPEShipmentsPageObject(ic);
		tpeshipmentdetailpageobject = new TPEShipmentDetailPageObject(ic);
		tpewebtenderspageobject = new TPEWebTendersPageObject(ic);
		VPHomePage = new VPHomePage(ic);
		VPLoginPageObject = new VPLoginPageObject(ic);
		vppurchaseOrderPageObject = new VPPurchaseOrderPageObject(ic);
		vprtslistpageobject = new VPRTSListPageObject(ic);
		runtemplatepageobject = new RunTemplatePageObject(ic);
		tpeappointmentspageobject = new TPEAppointmentsPageObject(ic);
		tpetransactionspageobject = new TPETransactionsPageObject(ic);
		mqpageobject = new MQPageObject(ic);
		dfwmsloginpoobject = new DFWMSLoginPageObject(ic);
		dfwmsToolsPageObject = new DFWMSToolsPageObject(ic);
		dfwmsdorcanonicalobject = new DFWMSDORCanonicalPostPageObject(ic);
		dfwmshomepageobject = new DFWMSHomePageObject(ic);
		dfwmsdistributionorderspageobject = new DFWMSDistributionOrdersPageObject(ic);
		dfwmsrunwavespageobject = new DFWMSRunWavesPageObject(ic);
		dfwmstaskspageobject = new DFWMSTasksPageObject(ic);
		dfwmsrfpageobject = new DFWMSRFPageObject(ic);
		dfwmsolpnspageobject = new DFWMSoLPNsPageObject(ic);
		DFWMSPckstationPageObject = new DFWMSPckstationPageObject(ic);
		DFWMSShpmtcreationPageObject = new DFWMSShpmtcreationPageObject(ic);
		DFWMSAPPTCREATIONPageObject = new DFWMSAPPTCREATIONPageObject(ic);
		DFWMSShpConfRulesObject = new DFWMSShpConfRulesObject(ic);
		DFWMSIBPOFormatPOSTPageObject = new DFWMSIBPOFormatPOSTPageObject(ic);
		dfwmsputtyobject = new DFWMSPuttyPageObject(ic);
		
		
		//inbound
		dfwmsPOObject = new DFWMSPOObject(ic);
		dfwmsDOObject = new DFWMSDOObject(ic);
		dfwmsScheduleAppointmentPageObject = new DFWMSScheduleAppointmentPageObject(ic);
		dfwmsIBCheckinPageObject = new DFWMSIBCheckinPageObject(ic);
		dfwmsInboundReceiveInPuttyPageObject = new DFWMSInboundReceiveInPuttyPageObject(ic);
		dfwmsValidationsPageObject = new DFWMSValidationsPageObject(ic);
		dfwmsInboundVerifyInPuttyPageObject = new DFWMSInboundVerifyInPuttyPageObject(ic);
		dfwmsInboundPutawayInPuttyPageObject = new DFWMSInboundPutawayInPuttyPageObject(ic);
		dfwmsAddLocationsPageObject = new DFWMSAddLocationsPageObject(ic);
		dfwmsVerifyIBShipmentCheckOutInPuttyPageObject = new DFWMSVerifyIBShipmentCheckOutInPuttyPageObject(ic);
		
		
		//2019
		dfwms2019HomePageObject = new DFWMS2019HomePageObject(ic);
		dfwms2019PostXmlPageObject = new DFWMS2019PostXmlPageObject(ic);
		dfwmsHomePageObject2019 = new DFWMSHomePageObject2019(ic);
		dfwms2019IBPOFormatPostPageObject = new DFWMS2019IBPOFormatPOSTPageObject(ic);
		dfwmsIBAssingShipmentPageObject = new DFWMSIBAssingShipmentPageObject(ic);
		dfwmsOutboundPickingNCNinPutty = new DFWMSOutboundPickingNCNinPutty(ic);
		dfwmsPickCartPickinginPuttyPageObject = new DFWMSPickCartPickinginPuttyPageObject(ic);
		dfwmsPackCartinPuttyPageObject = new DFWMSPackCartinPuttyPageObject(ic);
		dfwmsCreateShipmentPageObject = new DFWMSCreateShipmentPageObject(ic);
		dfwmsLoadTrailerInPuttyPageObject = new DFWMSLoadTrailerInPuttyPageObject(ic);
		dfwmsCloseTrailerInPuttyPageObject = new DFWMSCloseTrailerInPuttyPageObject(ic);
		dfwmsShpConfRulesObject = new DFWMSShpConfRulesObject(ic);
		dfwmsPackCubeDirinPuttyPageObject = new DFWMSPackCubeDirinPuttyPageObject(ic);
		dfwmsCycleCountPageObject = new DFWMSCycleCountPageObject(ic);
		dfwmsPickToLabelinPuttyPageObject = new DFWMSPickToLabelinPuttyPageObject(ic);
		dfwmsWeighAndManifestOlpnsPageObject = new DFWMSWeighAndManifestOlpnsPageObject(ic);
	}

	// Please do not add any step definition implementation methods in this
	// class
}