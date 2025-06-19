package com.homer.po;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.homer.dao.CommonData;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.helper.DataTable;
import com.homer.reports.Report;
import com.homer.resuablecomponents.JDBC_Connection;
import com.homer.resuablecomponents.ReusableComponents;
import com.homer.resuablecomponents.WebDriverHelper;

import org.openqa.selenium.JavascriptExecutor;


public class PageBase<CHILD extends PageBase<CHILD>> {

	protected InstanceContainer ic;
	protected static WebDriver driver;
	protected static WebDriverHelper wh;
	protected static DataTable dataTable;
	protected static ReusableComponents rc;
	protected static Report report;
	protected CommonData commonData;
	protected JDBC_Connection jDBC_Connection;
	protected WebElement element;

	protected boolean expectedResult;
	protected boolean isListViewSelected;

	protected static By pageTitle = By.className("page-title");

	protected static By searchTxtBox = By.id("headerSearch");
	protected static By searchBtn = By.id("headerSearchButton");
	protected static By verifyPLPPage = By.id("hd_plp");
	protected static By breadCrumbDiv = By.id("breadcrumb");

	static final By signInLink = By
			.xpath("//div[@id='accountInfo']//a[text()='Sign In']");
	static final By verifySignInPage = By.id("userLogin");
	static final By emailTxtBox = By.id("email_id");
	static final By passwordTxtBox = By.id("password");;
	static final By signInBtn = By.id("signIn");
	static final By verifySignedInUser = By
			.xpath("//span[@id='navUserName'][contains(text(),'Hello')]");
	static final By verifyHomePage = By.className("switches");
	public static final By LOADINGMASK = By.className("x-mask-msg");

	private static String windowHeader = "//*[contains(@class, 'x-window-header') and text()='<WINDOWHEADER>'"
			+ " and not(contains(@id, 'ghost'))]";

	private static String filterDetailHeader = "//*[contains(@id, 'filterdetail') "
			+ "and text()= '<FILTERDETAILHEADER>' and ancestor::*[contains(@class, 'x-window') "
			+ "and descendant::*[text()='<WINDOWHEADER>']]]";
	public static final String PLACEHOLDER = "<PLACEHOLDER>";

	public static String dockDoor = "";
	public static String plannedDockCode = "";
	public static String MultipleFlowPath = System.getProperty("user.dir")+"\\TestData\\Excel\\MultipleFlows.xlsx";
	public static String BulkOrdersPath = System.getProperty("user.dir")+"\\TestData\\Excel\\BulkOrdersPosting.xlsx";
	public static String MiamiBRTFile = System.getProperty("user.dir")+"\\TestData\\Excel\\MiamiBRTData.xlsx";
	public static String Sample = System.getProperty("user.dir")+"\\TestData\\Excel\\Sample.xlsx";
	public static String AtlantaBRT = System.getProperty("user.dir")+"\\TestData\\Excel\\SamplePost.xlsx";
	public static String MiamiBRTFileTest = System.getProperty("user.dir")+"\\TestData\\Excel\\MiamiBRTDataTest.xlsx";
	//inbound
	protected static final By Menu = By.id("phMenu");
	protected static final By Integration = By.id("MIDP600");
	protected static final By PostMessage = By.id("MIDP616");
	protected static final String PageTitle = "Post Message";
	protected static final By ChooseFile = By.xpath(".//input[contains(@type,'file') and contains(@id,'dataForm:uploadedFileID')]");
	protected static final By Reset = By.xpath(".//input[contains(@type,'submit') and contains(@value,'Reset')]");
	protected static final String FilePath = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\InboundPO.xml";
	protected static final String FilePathInbound = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\InboundPO.xml";
	protected static final String FilePathVerifyIBShipment = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\VerifyIBShipments.xml";
	protected static final String FilePathVerifyIBShipment10Lpns = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\VerifyIBShipment10Lpns.xml";
	protected static final By ASNCloseButton = By.xpath(".//img[contains(@role,'presentation') and contains(@class,'tool-collapse-left')]");
	protected static final By ASNCheckbox = By.xpath(".//div[contains(@role,'presentation') and contains(@class,'x-grid-row-checker')]");
	protected static final By ASNMoreOptions = By.xpath("(.//span[contains(@id,'-btnInnerEl') and contains(@class,'-inner-default-small')])[6]");
	protected static final By AutoReceive = By.xpath("(.//span[contains(@id,'-textEl') and contains(@class,'x-menu-item-indent')])[24]");
	protected static final String FilePathMDO5823 = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\MDO5823.xml";
	protected static final String MDO_Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MDO_Houston.xml";
	protected static final String Parcel_Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\Parcel_Houston.xml";
	protected static final String FilePathInboundZonesHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\Inbound.xml";
	protected static final String FilePathInboundZonesGeneric = System.getProperty("user.dir")+"\\TestData\\XML\\Generic_XML\\Inbound.xml";
	protected static final String FilePathInboundZonesDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\Inbound.xml";
	protected static final String FilePathVerifyIBShipment2019 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\VerifyIBShipments2019.xml";
	protected static final String FilePathVerifyIBShipment2019Lacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\VerifyIBShipmentsGreaterThan100Lpns_Lacey.xml";
	protected static final String FilePathVerifyIBShipment2019Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\VerifyIBShipments2019Dallas.xml";
	protected static final String FilePathVerifyIBShipment2019Miami = System.getProperty("user.dir")+"\\TestData\\XML\\Miami\\VerifyIBShipments2019_Miami.xml";
	protected static final String FilePathMultiItemRcvASN = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MultiItemRcvASN.xml";
	protected static final String FilePathMultiItemRcvASNTampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\MultiItemRcvASNTampa.xml";
	protected static final String FilePathMultiItemRcvASNBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\MultiItemRcvASNBaltimore.xml";
	protected static final String FilePathASNMultiItemRcvDtl = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\ASNMultiItemRcvDtl.xml";
	protected static final String FilePathASNMultiItemRcvDtlBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\ASNMultiItemRcvDtlBaltimore.xml";
	protected static final String FilePathShotgunOrder = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\ShotgunOrder.xml";
	protected static final String FilePathShotgunASN = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\ShotgunASN.xml";
	protected static final String FilePathItem = System.getProperty("user.dir")+"\\TestData\\Excel\\Item_Data.xlsx";
	protected static final String FilePathItemXML = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\Item.xml";
	protected static final String FilePathItemXMLLG = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\Item_2019.xml";

	protected static final String FilePathShipmentMultiItemRcvDtlDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\ShipmentMultiItemRcvDtlIlpn_Dallas.xml";
	protected static final String FilePathShipmentMultiItemRcvDtlLacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\ShipmentMultiItemRcvDtlIlpn_Lacey.xml";
	protected static final String FilePathIBBaltimoreMOD10 = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\MOD10_Baltimore.xml";
	protected static final String FilePathIBLaceyMOD10 = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\MOD10_Lacey.xml";
	protected static final String FilePathIBMOD10Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MOD10_Houston.xml";
	protected static final String FilePathIBMOD10Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\MOD10_Tampa.xml";
	protected static final String FilePathIBMOD10Tracey = System.getProperty("user.dir")+"\\TestData\\XML\\Tracey\\MOD10_Tracey.xml";
	protected static final String FilePathIBMOD10Atlanta = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\MOD10_Atlanta.xml";



	//Miami
	protected static final String FilePathIBMOD10Miami = System.getProperty("user.dir")+"\\TestData\\XML\\Miami\\MOD10_Miami.xml";
	protected static final String FilePathMultiItemRcvASNMiami = System.getProperty("user.dir")+"\\TestData\\XML\\Miami\\MultiItemRcvASNMiami.xml";
	protected static final String FilePathUPSMI_Miami = System.getProperty("user.dir")+"\\TestData\\XML\\Miami\\UPS_MISP_Miami.xml";


	//BRT Miami
	protected static final String FilePathFedexMiamiBRT = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\FedexMiamiBRT.xml";
	protected static final String FilePathHDUMiamiBRT = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\HZ1Sample.xml";
	protected static final String FilePathLTLMiamiBRT = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\BK1Sample.xml";
	protected static final String FilePathMDOMiamiBRT = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\BK3Sample.xml";
	protected static final String FilePathUPSMiamiBRT = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\SR2Sample.xml";

	//BRT Atlanta
	protected static final String FilePathPCLAtlantaBRT = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\UPSMiamiBRT.xml";
	protected static final String FilePathBVRAtlantaBRT = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\UPSMiamiBRT.xml";


	//Outbound
	protected static final String FilePathOutbound2019 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BK1PalletJack.xml";
	protected static final String FilePathOutbound2012 = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\LG_Fedex_Fluid.xml";
	protected static final String FilePathOutbound2012_BVR = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\LG_BVR_2012.xml";
	protected static final String FilePathOutbound_PAX = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\PAX.xml";
	protected static final String FilePathOutbound_SCP = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\SCP.xml";
	protected static final String FilePathOutbound_MCP = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\MCP.xml";
	protected static final String FilePathOutbound2019Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\Outbound2019Dallas.xml";
	protected static final String FilePathIBDallasMOD10 = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\IB_Dallas_MOD10.xml";
	protected static final String FilePathDallasUndoDOInPacking = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\UndoDOInPackingBK1.xml";
	protected static final String FilePathValueAddedService = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\ValueAddedService.xml";
	protected static final String FilePathValueAddedServiceDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\ValueAddedServiceDallas.xml";
	protected static final String FilePathOutboundHDUTL = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\HDUTL.xml";
	protected static final String FilePathOutboundBVRDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVRDallas.xml";
	protected static final String FilePathOutboundMDODallas = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MDO_BK1PalletJack.xml";
	protected static final String FilePathUndoBVRDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVRDallas_Undo.xml";
	protected static final String FilePathHDUTLDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\HDU TL_Dallas.xml";
	protected static final String FilePathHDUTLHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\HDU TL_Dallas.xml";
	protected static final String FilePathOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\LTLOutboundDallas.xml";
	protected static final String FilePathOutboundHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\LTLOutboundDallas.xml";
	protected static final String FilePathOutBoundSplitShipment = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\LTL_Split.xml";
	protected static final String FilePathLTLHDUOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\LTL_HDUOutboundDallas.xml";
	protected static final String FilePathLTLHDUOutboundHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\LTL_HDUOutboundDallas.xml";
	protected static final String FilePathUPSOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\UPSOutboundDallas.xml";
	protected static final String FilePathWESOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\WESDallasxml.xml";
	protected static final String FilePathUPSOutboundDallasMISP = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\UPSMISP.xml";
	protected static final String FilePathUPSOutboundHoustonMISP = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\UPSHoustonMISP.xml";
	protected static final String FilePathBVROutboundDallasMIMP = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVRMIMP.xml";
	protected static final String FilePathFGNDOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\FGND_Dallas.xml";
	protected static final String FilePathFXHDOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\FXHD_Dallas.xml";
	protected static final String FilePathFXHDUndoDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\FXHD_Dallas_Undo.xml"; 
	protected static final String FilePathBVRShotGunOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVR _ShotGun_Dallas.xml"; 
	protected static final String FilePathEnvelopeSingleTote1Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\EnvelopSingleTote1.xml"; 
	protected static final String FilePathEnvelopeSingleTote1Baltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\EnvelopSingleTote1Baltimore.xml";
	protected static final String FilePathEnvelopeSingleTote2Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\EnvelopSingleTote2.xml"; 
	protected static final String FilePathEnvelopeSingleTote2Baltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\EnvelopSingleTote2Baltimore.xml";
	protected static final String FilePathBVRDallasSplitShipment1 = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVR_SpliShipment_1.xml";
	protected static final String FilePathBVRDallasSplitShipment2 = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVR_SpliShipment_2.xml";
	protected static final String FilePathBVRHoustonSplitShipment1 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BVR_SpliShipment_1.xml";
	protected static final String FilePathBVRHoustonSplitShipment2 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BVR_SpliShipment_2.xml";
	protected static final String FilePathMDODallasSplitShipment1 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MDO_SplitShipment1.xml";
	protected static final String FilePathMDODallasSplitShipment2 = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MDO_SplitShipment2.xml";
	protected static final String FilePathUPSHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\UPS_Houston.xml";
	protected static final String FilePathUPSOutboundBaltimoreMISP = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\UPS_MultiItem_Baltimore.xml";
	protected static final String FilePathOutboundBVRHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BVR_Houston.xml";
	protected static final String FilePathOutboundBVRMIHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BVR_MI_Houston.xml";
	protected static final String FilePathOutboundMDOMIHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BK1PalletJack_MultiItem.xml";
	protected static final String FilePathOutboundUPSMIHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\UPS_MI_Houston.xml";
	protected static final String FilePathOutboundLTLMIHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\LTL_MISP.xml";

	public static final By RuleCheckBoxInput = By.xpath("//*[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlRuleCmparValue']");
	public static final By MomentumWithDORadioBtn = By.xpath("(.//input[contains(@type,'radio') and contains(@id,'checkAll')])[2]");
	public static final By MomentumWithDORadioBtn2012 = By.xpath("(.//input[contains(@type,'radio') and contains(@id,'checkAll')])[3]");
	public static final By RuleOperatorInput = By.xpath("//*[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlOperatorList']");
	public static final By RuleOperatorInput2 = By.xpath("//select[@id='dataForm:ruleSelDtlDataTable:newRow_1:ruleSelDtlOperatorList']");
	public static final By RuleOperatorInput1 = By.xpath("//*[@id='dataForm:ruleSelDtlDataTable:6:ruleSelDtlOperatorList']");
	public static final By RuleColumnInput = By.xpath("//*[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlColumnList']");
	public static final By RuleOperatorAndOr = By.xpath("//select[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlAndOrOrList']");
	public static final By RuleOperatorItemConveyFlag = By.xpath("//select[@id='dataForm:ruleSelDtlDataTable:newRow_1:ruleSelDtlColumnList']");

	protected static final String FilePathEZShipmentHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\MDO_EZShipment_Houston.xml";
	protected static final String FilePathUPS = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\UPS.xml";
	protected static final String FilePathUPS_MIMP = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\UPS_MIMP.xml";
	protected static final String FilePathBVR = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\BVR.xml";
	protected static final String FilePathBVROutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\BVR_Cancel_Olpn.xml";
	protected static final String FilePathBVROutboundHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\BVR_Cancel_Olpn.xml";
	protected static final String FilePathEnvelopOutboundBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\EnvelopConBaltimore.xml";
	protected static final String FilePathEnvelopOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\EnvelopCon.xml";
	protected static final String FilePathEnvelopMultiOutboundDallas = System.getProperty("user.dir")+"\\TestData\\XML\\Dallas\\EnvelopConMulti.xml";
	protected static final String FilePathEnvelopMultiOutboundBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\EnvelopConMultiBaltimore.xml";

	protected static final String FilePathLTLHDUOutboundBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\HDU_LTL_Baltimore.xml";
	protected static final String FilePathOutboundFGNDHouston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\FGND_Houston.xml";

	//Lacey
	protected static final String FilePathShotgunOrderLacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\IB_ShotgunOrder_Lacey.xml";
	protected static final String FilePathShotgunASNlacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\IB_ShotgunASN_Lacey.xml";
	protected static final String FilePathBVRLacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\BVR_Lacey.xml";
	protected static final String FilePathBVRLaceyMultiItem = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\BVR_MI_Lacey.xml";
	protected static final String FilePathVASLacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\VAS_Lacey.xml";
	protected static final String FilePathVASBaltimore = System.getProperty("user.dir")+"\\TestData\\XML\\Baltimore\\VAS_Baltimore.xml";
	protected static final String FilePathLTLMILacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\LTL_Lacey_MI.xml";
	protected static final String FilePathUPSOutboundLaceyMISP = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\UPS_MultiItem_Lacey.xml";
	protected static final String FilePathOutboundLTLLacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\LTL_Lacey.xml";
	protected static final String FilePathLTL_SplitShipment_Lacey = System.getProperty("user.dir")+"\\TestData\\XML\\Lacey\\LTL_SplitShipment_Lacey.xml";
	protected static final String FilePathFXHD_Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\FXHD_Houston.xml";
	protected static final String FilePathFGND_Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\FGND_Houston.xml";
	protected static final String FilePathSplitAndCombine_Houston = System.getProperty("user.dir")+"\\TestData\\XML\\Houston\\SplitAndCombine.xml";

	//Tampa
	protected static final String FilePathLTLHDU_Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\HDU LTL_Tampa.xml";
	protected static final String FilePathUPS_Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\UPS XML_TAMPA.xml";
	protected static final String FilePathVAS_Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\VAS XML_TAMPA.xml";
	protected static final String FilePathEZShipment_Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\EZShipment_Tampa.xml";
	protected static final String FilePathUPSMI_Tampa = System.getProperty("user.dir")+"\\TestData\\XML\\Tampa\\UPS_MI_Tampa.xml";
	protected static final String FilePathUPSMI_Tracey = System.getProperty("user.dir")+"\\TestData\\XML\\Tracey\\UPS_MI_Tracey.xml";


	//Atlanta BRT

	protected static final String FilePathEZShipmentAtlanta = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\MDO_EZShipment_Atlanta.xml";
	protected static final String FilePathMultiItemRcvASNAtlanta = System.getProperty("user.dir")+"\\TestData\\XML\\Atlanta BRT\\MultiItemRcvASNAtlanta.xml";


	protected static final String FilePathUPSMI_Newark = System.getProperty("user.dir")+"\\TestData\\XML\\Newark\\UPS_MultiItem_Newark.xml";


	//Dallas Bulk

	protected static final String FilePathMDO5823_Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\5823_parcel_MDO_bulk.xml";
	protected static final String FilePathUPS5823_Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\5823_parcel_UPS_bulk.xml";
	protected static final String FilePathFXHD5823_Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\5823_parcel_FXHD_bulk.xml";
	protected static final String FilePathFGND5823_Dallas = System.getProperty("user.dir")+"\\TestData\\XML\\BRT\\5823_parcel_FGND_bulk.xml";




	public static By ASNClose =  By.xpath("(.//img[contains(@id,'-toolEl')])[15]");
	public static By ShipmentViewClose = By.xpath("(.//img[contains(@id,'-toolEl')])[22]");
	public static By DOViewClose = By.xpath("(.//img[contains(@id,'-toolEl')])[13]");


	public static final By ShipConfirmParcel = By.xpath(".//input[contains(@id,'checkAll') and contains(@value,'12')]");
	public static final By ShipConfirmEZ = By.xpath(".//input[contains(@id,'checkAll') and contains(@value,'0') and contains(@tabindex,'97')]");
	public static final By ShipConfirmView = By.xpath("//input[@value='View']"); // EZ 
	public static final By ShipConfirmViewParcel = By.xpath(".//input[contains(@value,'View') and contains(@tabindex,'144')]"); //PArcel
	public static final By ShipConfirmParcelRadio = By.xpath(".//input[contains(@id,'checkAll') and contains(@type,'radio') and contains(@tabindex,'68')]");
	public static final By ShipConfirmParcelDO = By.xpath("(.//input[contains(@id,'ruleSelDtlDataTable') and contains(@name,'ruleSelDtlDataTable') and contains(@type,'text')])[3]");
	public static final By ShipOrdrID = By.xpath("//input[contains(@id,'checkAll_c_dataForm:lview:dataTable') and contains(@tabindex,'71')]");
	public static final By ShipConfirmEZShipSubLocn = By.xpath("(.//input[contains(@id,'ruleSelDtlRuleCmparValue') and contains(@name,'ruleSelDtlRuleCmparValue') and contains(@type,'text')])[7]");
	public static final By ShipConfirmEZShipSubLocn1 = By.xpath("(.//input[contains(@id,'ruleSelDtlRuleCmparValue') and contains(@name,'ruleSelDtlRuleCmparValue') and contains(@type,'text')])[5]");
	public static final By ShipConfirm = By.xpath(".//input[contains(@value,'Ship Confirm') and contains(@type,'button')]");
	public static final By DefinitionTab = By.xpath(".//a[contains(@id,'definitionTab_lnk') and contains(@name,'definitionTab')]");
	public static By ShipmentAdd = By.xpath("(.//span[contains(text(),'Add') and contains(@id,'button') and contains(@class,'default-small')])[1]");
	public static By Shipment = By.xpath(".//input[contains(@id,'sc_hdr_IP_Ship')]");
	public static By BillingMethod = By.xpath(".//select[contains(@id,'BillingMethod')]");
	public static By DesignatedMode = By.xpath(".//select[contains(@id,'SCG_DsgMode')]");
	public static By ServiceLevel = By.xpath(".//select[contains(@id,'SCG_DsgServiceLevel')]");
	public static By StopsTab = By.xpath(".//a[contains(@id,'ShipmentCreateStops_lnk')]");
	public static By Facility1 = By.xpath(".//input[contains(@id,'newRow_1:StopFacilityAliasId')]");
	public static By Facility2 = By.xpath(".//input[contains(@id,'newRow_2:StopFacilityAliasId')]");
	public static By ShipmentSave = By.xpath(".//input[contains(@id,'ShipCreate_CB_Save') and contains(@tabindex,'332')]");
	public static By ShipmentMore = By.xpath(".//input[contains(@type,'button') and contains(@name,'j_id')]");
	public static By EditAssResources = By.xpath(".//a[contains(@id,'ShpDtl_EditAsgRSBtn')]");
	public static By ShipVia = By.xpath(".//select[contains(@name,'assignedShipvia')]");
	public static By ShipViaSave = By.xpath(".//button[contains(text(),'Save')]");

	//Rf Menu

	public static final By RFMenuInfo = By.xpath("//input[contains(@id,'tb_567')]");
	public static final By RFFindTrans = By.xpath("//span[contains(@value,'Find Tran')]");
	public static final By RFTransSearch = By.xpath("//input[contains(@id,'dataForm:it_')]");
	public static final By RFTransSearchMenu = By.xpath("//a[contains(@id,'mnbtn_dataForm:menuList:0')]");
	public static final By RFDockDoor = By.xpath("//input[contains(@id,'DockInp')]");
	public static final By RFDockDoor_Outbound = By.xpath("//input[contains(@id,'barcode')]");
	public static final By RFShipment = By.xpath("//input[contains(@id,'loadinid')]");
	public static final By RFLPN = By.xpath("//input[contains(@id,'lpninput')]");
	public static final By RFLPN_Outbound = By.xpath("//input[contains(@id,'test')]");
	public static final By RFLPN_Outbound_Text = By.cssSelector("#dataForm > div.error");
	public static final By RFItemBC = By.xpath("//input[contains(@id,'verfiyItemBrcd')]");
	public static final By RFPackQty = By.xpath("//input[contains(@id,'input1RFPackQty')]");
	public static final By RFQty = By.xpath("//input[contains(@id,'input1input2')]");
	public static final By RFChngTaskGrp = By.xpath("//span[contains(@value,'Chg Task Grp')]");
	public static final By RFTaskGrp = By.xpath("");
	public static final By RFTaskGrp1 = By.xpath("//input[contains(@id,'location_Input')]");
	public static final By RFTaskGrp2 = By.xpath("//input[contains(@id,'dataForm:INT')]");
	public static final By RFPutawayLPN = By.xpath("//input[contains(@id,'containerEntry')]");


	//tools
	protected static final By QuickFilterExpand = By.xpath(".//input[contains(@title,'Expand')]");
	protected static final By CarrierCodeTextBox = By.xpath(".//input[contains(@alt,'Find Carrier')]");
	protected static final By QuickFilterCollapse = By.xpath(".//input[contains(@title,'Collapse')]");
	protected static final By Checkout = By.xpath(".//input[contains(@id,'1CheckOut')]");
	protected static final By LastPage = By.xpath(".//input[contains(@type,'image') and contains(@id,'pager:last')]");

	protected static final By oLPNTxtBox = By.xpath(".//input[contains(@id,'dataForm:EnterLPNNumber') and contains(@type,'text') and contains(@name,'dataForm:EnterLPNNumber')]");
	protected static final By weightTxtBox = By.xpath(".//input[contains(@id,'dataForm:actWeight') and contains(@type,'text') and contains(@name,'dataForm:actWeight')]");
	protected static final By weighBtn = By.xpath(".//input[contains(@id,'dataForm:nextButton1') and contains(@type,'submit') and contains(@value,'Weigh >')]");
	protected static final By exitBtn = By.xpath(".//input[contains(@id,'dataForm:exitButton') and contains(@type,'submit') and contains(@value,'Exit')]");
	protected static final By nxtLPNTxtBox = By.xpath(".//input[contains(@id,'dataForm:nextTcLpnId') and contains(@type,'text') and contains(@name,'dataForm:nextTcLpnId')]");
	//Active skus
	//protected static final String BK1Active = "1000035126";
	//protected static final String BK3Active = "1001573378";
	//protected static final String SR1Active = "1004261684";
	//protected static final String SR2Active = "1000112653";
	//protected static final String CFActive = "1000074436";
	//protected static final String CantileverActive = "1000061977";
	//protected static final String HazmatActive = "1000393575";

	//protected static final String BK3NonShipReadyItem = "1000061289";
	//protected static final String BK1OutboundItem = "1003253072"; 
	//protected static final String BK1PalletJackItem = "1003151893";
	//protected static final String BK1PalletJackItem = "1004268616";
	//protected static final String BK1NonShipReady = "1004268343";
	//protected static final String CartPickConItem = "1004250551"; 	// 1003422972
	//protected static final String HazmatItem = "1002557900";
	//protected static final String WaveReplenishmentItem = "1000062466";  1003308387
	//protected static final String WaveReplenishmentItem = "1004268495";
	//protected static final String SR1Reserve = "1001054380";
	//protected static final String SR2Reserve = "1002557939";
	//protected static final String BK2Reserve = "1002241680";
	//protected static final String BK2Reserve = "1000061438";





	//Dallas
	//protected static final String BK1ActiveDallas = "1002241680";
	//protected static final String SR1ActiveDallas = "1000074436";
	//protected static final String BK3ActiveDallas = "1000000625";
	//protected static final String HZActiveDallas = "1000062234";
	//protected static final String CNActiveDallas = "1000000628";
	//protected static final String CFActiveDallas = "1000074785";
	//protected static final String BNActiveDallas = "1000061289"; 
	//protected static final String SR1ReserveDallas = "1000074436";
	//protected static final String CNReserveDallas = "1000207888";
	//protected static final String SR2ActiveDallas = "1000238782";
	//protected static final String SR2ReserveDallas = "1000238782";
	//protected static final String BK2ReserveDallas = "1000284714";
	//protected static final String TBLActiveDallas = "1002663995";

	//protected static final String BK1OutboundDallas = "1000035126";
	//protected static final String SR7InboundDallas = "1000206089";
	//protected static final String CF2InboundDallas = "1000293166";
	//protected static final String CF1ReserveDallas = "1000291848";
	//protected static final String CF2ReserveDallas = "1000293166";
	//protected static final String BN1InboundDallas = "1000236714";
	//protected static final String PF1InboundDallas = "1000293978";

	//protected static final String OutboundBVRDallas = "";


	/*//  active disp loc
	protected static final String BK1DisplayLoc = "BK1-010-052";
	protected static final String BK2DisplayLoc = "";
	protected static final String BK3DisplayLoc = "BK3-083-023";
	protected static final String SR1DisplayLoc = "SR1-030-046-A01";
	protected static final String SR2DisplayLoc = "SR2-055-014-A01";
	protected static final String CFDisplayLoc = "";
	protected static final String PFDisplayLoc = "";
	protected static final String BNDisplayLoc = "";
	protected static final String CantileverDisplayLoc = "";
	protected static final String HazmatDisplayLoc = "";

	//reserve skus

	protected static final String BK2Reserve = "";
	protected static final String SR1Reserve = "1003064704";
	protected static final String SR2Reserve = "1004028273";
	protected static final String CFReserve = "";
	protected static final String PFReserve = "";
	protected static final String BNReserve = "";
	protected static final String CantileverReserve = "";
	protected static final String HazmatReserve = "";

	//Stage to reserve
	protected static final String BK2DisplayLocReserve = "";
	protected static final String SR1DisplayLocReserve = "BK1-010-052";
	protected static final String SR2DisplayLocReserve = "";
	protected static final String CFDisplayLocReserve = "";
	protected static final String PFDisplayLocReserve = "";
	protected static final String BNDisplayLocReserve = "";
	protected static final String CantileverDisplayLocReserve = "";
	protected static final String HazmatDisplayLocReserve = "";
	//SR1-030-044-B02
	 */	




	protected static final By Item = By.xpath(".//input[contains(@id,'dataForm:listView:filterId:itemLookUpId')]");
	protected static final By ItemApply = By.xpath(".//input[contains(@id,'dataForm:listView:filterId:filterIdapply') and contains(@class, 'btn  groupBtn')]");
	protected static final By OnHandQty = By.xpath("(.//span[contains(@id,':onHandQuantity')])[3]");
	protected static final By Send = By.xpath(".//input[contains(@type,'submit') and contains(@id,'dataForm:postMessageCmdId')]");
	public static final By ponbrtext = By.xpath("//*[@id='dataForm:POList_entityListView:POlistFil1:field6value1']");
	public static final By btnApply1  =By.id("dataForm:listView:filterId:filterIdapply");
	public static final By inputShipment = By.xpath("//*[@id='dataForm:apptObjTable:0:shipId2']"); 
	public   static final By ASNPONbrTxt = By.xpath("(.//input[contains(@id,'field10value1')and contains(@type,'text')])[1]");
	public   static final By ShipmentNbrTxt = By.xpath("(.//input[contains(@id,'field10value1')and contains(@type,'text')])[2]");
	public   static final By ASNPONbrApply = By.xpath("(.//input[contains(@id,'filterIdapply')and contains(@type,'submit')])[1]");
	public   static final By ShipmentNbrApply = By.xpath("(.//input[contains(@id,'filterIdapply')and contains(@type,'submit')])[2]");
	public   static final By GenerateShip = By.xpath(".//input[contains(@id,'dataForm')and contains(@type,'button') and contains(@value,'Create Shipment')]");
	public   static final By Generate = By.xpath(".//input[contains(@id,'dataForm')and contains(@type,'button') and contains(@value,'Generate')]");
	//public   static final By ShipmentID = By.xpath(".//input[contains(@id,'dataForm')and contains(@type,'button') and contains(@value,'Generate')]");
	public   static final By ShipmentID = By.xpath(".//input[contains(@id,'shipmentIdh')]");
	public   static final By genok = By.xpath(".//input[contains(@id,'dataForm')and contains(@type,'button') and contains(@value, 'OK')]");
	public   static final By ASNCheck = By.xpath("(.//input[contains(@id,'dataForm')and contains(@type,'checkbox') and contains(@title, 'ASN')])[1]");
	public   static final By ShipmentCheck = By.xpath("(.//input[contains(@id,'dataForm')and contains(@type,'checkbox') and contains(@title, 'Shipment')])[1]");
	public   static final By ASNtoShipment = By.xpath(".//input[contains(@id,'dataForm:cb5')and contains(@type,'image')]");
	public   static final By ShipmentASNSave = By.xpath(".//input[contains(@id,'cbdelasns')and contains(@type,'button') and contains(@value,'Save')]");
	public static final By ASNShipmentID = By.xpath("(.//span[contains(@class,'mps-link')])[3]");
	public static final By ASNMore = By.xpath("(//span[contains(@id,'-btnInnerEl')])[34]");
	//check in
	public static final By lnkMenu = By.xpath("//span[contains(text(),'enu')]");
	public static final By lnkCheckIn =By.linkText("Check-In");
	public static final By chckBoxAppmntList = By.id("checkAll_c0_dataForm:listView:apptListTable");
	public static final By chckBoxTrailerList = By.id("checkAll_c0_dataForm:listViewTrailer:dataTableTrailer");
	public static final By asnnoenterasnscr = By.xpath("//*[@id='dataForm:ASNList_entityListView:ASNList_filterId2:field30value1']");
	public static final By asnnoenterasnscraply = By.xpath("//*[@id='dataForm:ASNList_entityListView:ASNList_filterId2:ASNList_filterId2apply']");

	//asn screen
	public static final By asnfilter = By.xpath("//*[@id='button-3139-btnIconEl']");
	public static final By asndropdown = By.xpath("//*[@id='mpssearchfield-3143-btnIconEl']");
	public static final By asnnocheckbox = By.xpath("//*[@id='gridview-1226-record-2911']/tbody/tr/td[1]");
	public static final By asnnoapply = By.xpath("//*[@id='button-3395']");
	public static final By asnscrmorebtn = By.xpath("(//span[contains(@id,'btnInnerEl') and contains(@class,'x-btn-inner-default-small')])[6]");
	public static final By asnscrcreateilpns = By.xpath("//span[contains(@id,'menuitem') and contains(@class,'x-menu-item-text') and contains(text(), 'Create iLPNs')]");
	public static final By asnscrcreateilpnsclick = By.xpath("(//input[contains(@id,'rmButton') and contains(@class,'btn') and contains(@value, 'Create iLPNs')])[1]");


	//Outbound

	protected static final String FilePathHDUTLOutbound = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\HDUTLOutbound.xml";
	protected static final String FilePathOutboundHDU = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\OutboundLTLHDU.xml";
	protected static final String FilePathOutbound = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\LTLOutbound.xml";
	protected static final String FilePathAgileOutbound = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\AgileFlow.xml";
	protected static final String FilePathUPSOutbound = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\UPSFlow.xml";
	protected static final String FilePathOutboundConveyAgile = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\OutboundConveyAgile.xml";
	protected static final String FilePathOutboundConveyUPS = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\OutboundConveyUPS.xml";
	protected static final String FilePathOutboundConveyHDU = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\OutboundConveyHDU.xml";
	protected static final String FilePathOutboundUPSPAX = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\UPSPAXFLOW.xml";
	protected static final String FilePathOutboundBossViaRDC = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\BossViaRDC.xml";
	protected static final String FilePathOBHDULTLSplit1 = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\HDULTLMultistop1.xml";
	protected static final String FilePathOBHDULTLSplit2 = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\HDULTLMultistop2.xml";
	protected static final String FilePathOutboundLTLMultiStop1 = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\LTLMultiStop1.xml";
	protected static final String FilePathOutboundLTLMultiStop2 = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\LTLMultiStop2.xml";
	protected static final String FilePathOutboundNonConPackBypassAgile = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\NonConPackBypassAgile.xml";
	protected static final String FilePathOutboundNonConPackBypassUPS = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\NonConPackBypassUPS.xml";
	protected static final String FilePathOutboundNonConPackBypassLTL = System.getProperty("user.dir")+"\\TestData\\XML\\Troy\\NonConPackBypassLTL.xml";
	//validateTotal received Items
	public static final String ILPNPageTitle = "iLPNs";
	public static final By ILPNArrow = By.id("LPNList_Inbound_filterId1_fltrExpCol");
	public static final By ASN = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Inbound_filterId1:field6value1']");
	public static final By ILPNApply = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Inbound_filterId1:LPNList_Inbound_filterId1apply']");
	public static final By ILPN1 = By.id("dataForm:LPNListInOutboundMain_lv:dataTable:0:LPNList_Outbound_Link_NameText_param_out");
	public static final By ILPN2 = By.id("dataForm:LPNListInOutboundMain_lv:dataTable:1:LPNList_Outbound_Link_NameText_param_out");
	public static final By ILPN3 = By.id("dataForm:LPNListInOutboundMain_lv:dataTable:2:LPNList_Outbound_Link_NameText_param_out");
	public static final By ILPN4 = By.id("dataForm:LPNListInOutboundMain_lv:dataTable:3:LPNList_Outbound_Link_NameText_param_out");
	public static final By ILPN5 = By.id("dataForm:LPNListInOutboundMain_lv:dataTable:4:LPNList_Outbound_Link_NameText_param_out");

	//2019
	public static final By MenuBtn = By.xpath("(//span[contains(@id,'btnIconEl')])[1]");
	public static final By MenuIntegration = By.xpath("(//span[contains(text(),'Integration')])[1]");
	public static final By SearchLocator = By.xpath("(//label[contains(@id,'label-')])[7]");
	public static final By ShowAll = By.xpath("(//span[contains(@id,'-btnInnerEl')])[9]");
	public static final By PostMessageOption = By.xpath("(//span[contains(text(),'Post Message')])");
	public static final By Menuclick =By.xpath(".//span[contains(@class,'wt-topbar-menu-icon') and contains(@id, 'button')]");
	public static final By Menusearchbar = By.xpath(".//input[contains(@id,'mps_menusearch')]");
	public static final By HomepGE = By.xpath(".//span[contains(@class,'x-btn-icon-el-default-toolbar-medium wt-home') and contains(@id, 'btnIconEl')]");
	public static final By Maximize = By.xpath(".//img[contains(@class,'x-tool-maximize') and contains(@id,'toolEl')]");
	public static final By Refresh = By.xpath(".//img[contains(@class,'x-tool-refresh') and contains(@id,'toolEl')]");
	public static final By Close = By.xpath(".//img[contains(@class,'x-tool-maximize') and contains(@id,'toolEl')]");
	public static final By Titletxt = By.xpath("(//div[contains(@class,'x-title-item') and contains(@id,'textEl')])[3]");
	public static final By PostChooseFile = By.xpath("//input[contains(@id,'dataForm:uploadedFileID')]");
	public static final By PostSend = By.xpath("//input[contains(@id,'dataForm:postMessageCmdId')]");
	public static final By CloseWindow = By.xpath("(.//img[contains(@class,'x-tool-close') and contains(@id,'toolEl')])[2]");
	public static By DropDwn = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[1]");
	public static final By PONbrTxt = By.xpath(".//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'PurchaseOrder')]");
	public static final By DONbrTxt = By.xpath(".//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'DistributionorderID')]");
	public static By ASNNbrTxt = By.xpath(".//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'asnId')]");
	public static final By closebtn2 = By.xpath("(.//img[contains(@class,'x-tool-close') and contains(@id,'toolEl')])[3]");
	public static final By closebtn1 = By.xpath("(.//img[contains(@class,'x-tool-close') and contains(@id,'toolEl')])[2]");
	public static final By listApptype = By.xpath(".//select[contains(@name,'dataForm:cd10')]");
	public static final By equipCode = By.xpath("//*[@id='dataForm:equipment_det']");
	public static final By inputApptdate = By.xpath(".//input[contains(@id,'dataForm:startTime_det') and contains(@type,'text')]");
	public static final By inputASN = By.xpath("//*[@id='dataForm:apptObjTable:0:asnId2']");
	public static final By checkBox = By.id("checkAll_c0_dataForm:apptObjTable");
	public static final By btnSave = By.xpath("//*[@id='apptList_btn_12']");
	public static final By AptmtError = By.xpath(".//div[contains(@id,'er_d1_bid')]");
	public static final By ErrorMsg = By.xpath("(.//li[contains(@class,'overlayerror -icons_er')])[1]");
	public static final By TaskIdVal = By.xpath(".//input[contains(@name,'filterId:field1') and contains(@type,'text')]");
	public static final By TaskApply = By.xpath(".//input[contains(@id,'filterIdapply')]");
	public static final By TaskCheckBox = By.xpath("//input[@value='0'][@tabindex='207']");
	public static final By Execute = By.xpath("//input[@value='Execute'][@tabindex='220']");
	public static final By ExecuteTask = By.xpath("//input[@value='Execute Task']");
	public static final By TaskStatus = By.xpath(".//span[contains(@id,'dataTable:0:dis5')]");
	public static final By eleAppointmentID = By.xpath(".//span[contains(@id,'apptId')]");
	public static final By InputAppointmentId = By.xpath("(.//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'appointment')])[1]");
	public static final By checkInApply  =By.xpath(".//span[contains(@id,'btnInnerEl') and contains (@class,'x-btn-inner') and contains(text(),'Search')]");
	public static final By CheckInCheckBox  = By.xpath("(.//div [contains (@class,'x-grid-row-checker') and contains(@role,'presentation')])[3]");
	public static final By CheckInCheckBox1  = By.xpath("(.//div [contains (@class,'x-grid-row-checker') and contains(@role,'presentation')])[1]");
	public static final By CheckInId = By.xpath(".//span [contains(@class,'x-btn-inner') and contains(text(),'Check-in')]");
	public static final By btnDriverName  =By.xpath("(.//div [contains(@class,'x-form-search-trigger-default') and contains(@id,'trigger-picker')])[16]");
	public static final By inputFname = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'textfield') and contains(@name,'firstNameCreate')]");
	public static final By inputLname = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'textfield') and contains(@name,'surNameCreate')]");
	public static final By inputLICNo = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'textfield') and contains(@name,'licenseNumCreate')]");
	public static final By Carrierselect = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'combobox') and contains(@name,'carrCodeCreate')]");
	public static final By DriverLicenseCntry = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'combobox') and contains(@name,'driverCountry')]");
	public static final By listLicenseState = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'combobox') and contains(@name,'driverStateProv')]");
	public static final By btnCreate = By.xpath(".//span[contains(@class,'x-btn-inner') and contains(@id,'button') and contains(text(),'Create')]");
	public static final By inputTrailer = By.xpath("(.//input [contains(@class,'x-form-text-default') and contains(@id,'mpslookupfield') and contains(@name,'trailerName')])[2]");
	public static final By AppointmentCheckBox = By.xpath("(.//input [contains(@name,'_dataForm:listView:dataTable')])");
	public static final By AppointmentEdit = By.xpath("(.//input [contains(@id,'dataForm:listView:apptList_btn')])[3]");
	public static final By DockDetails = By.xpath(".//a[contains(@id,'tab')and contains(@name,'tab') and contains(text(),'Dock Details')]");
	public static final By PlannedDock = By.xpath(".//input[contains(@id,'dockName')and contains(@name,'dockName') and contains(@type,'text')]");
	public static final By PlannedDockDoor = By.xpath(".//input[contains(@id,'dockDoorName')and contains(@name,'dockDoorName') and contains(@type,'text')]");
	public static final By AdditionalDetails = By.xpath(".//a[contains(@id,'tab')and contains(@name,'tab') and contains(text(),'Additional Details')]");
	public static final By NewTrailer = By.xpath("(.//input [contains(@id,'dataForm:newTrailerBtn')])");
	public static final By Carrier = By.xpath(".//input [contains(@name,'carrCodeCreate')]");
	public static final By EquipmentID = By.name("equipInstRefCarrier");
	public static final By HomeDomicile = By.id("domicile");
	public static final By HomeDomi = By.xpath(".//span[contains(@id,'mps_regions_selector') and contains(@data-ref,'btnInner')]");
	public static final By TrailerDSPType = By.xpath(".//select[contains(@id,'dataForm:id')]");
	public static final By Region = By.id("currDspRegionId");
	public static final By ItemFacility = By.xpath(".//input[contains(@name,'itemLookUpId')]");
	public static final By ItemFacilityApply = By.xpath(".//input[contains(@title,'Apply') and contains(@type,'button') and contains(@id,'filterIdapply')]");
	public static final By ItemFacilityCheckbox = By.xpath(".//input[contains(@name,'checkAll') and contains(@id,'checkAll')]");
	public static final By ItemFacilityView = By.xpath(".//input[contains(@value,'View') and contains(@type,'submit')]");
	public static final By PutAwayType = By.xpath(".//span[contains(@id,'PutawayType')]");
	public static final By TrailerSave = By.xpath("//*[@id='EquipmentInstance_Detail']/form/table[2]/tbody/tr/td/button[1]");
	public static final By DriverLookUp = By.xpath(".//img[contains(@id,'LookupDriver')]");
	public static final By FirstName = By.xpath("(.//input[contains(@id,'dataForm:driverNameNew')])");
	public static final By LastName = By.xpath("(.//input[contains(@id,'dataForm:driverLastNameNew')])");
	public static final By DriverLicNo = By.xpath(".//input [contains(@id,'dataForm:driverLicNoNew')]");
	public static By CaarierCode = By.xpath("(.//input [contains(@id,'dataForm:driverCRCode')])[5]");
	public static final By LicState = By.id("dataForm:State_List");
	public static final By CreateButton = By.xpath("(.//input[contains(@id,'dataForm:driver_locgetlist')])[2]");
	public static final By AppointmentSave = By.xpath(".//input[contains(@id,'apptList_btn_') and contains(@value,'Save')][1]");
	public static final By SAErrorPopup = By.xpath(".//li[contains(@class,'overlayerror -icons_er')]");
	public static final By SAErrorPopupClose = By.xpath(".//td[contains(@class,'er_dw_clstd')]");
	public static final By AppointmentCheckin = By.xpath("(.//input [contains(@id,'dataForm:listView:apptList_btn')])[4]");
	public static final By DockDoor = By.xpath(".//input[contains(@id,'mpslookupfield-') and contains(@name,'currentLocation')]");

	public static final By AddLoc = By.xpath("(.//input[contains(@id,'rmButton')])[2]");
	public static final By ClassVal = By.xpath(".//select[contains(@id,'locnClassdc')]");
	public static final By ZoneValReserve = By.xpath(".//input[contains(@id,'zoneRdc')]");
	public static final By ZoneValStaging = By.xpath(".//input[contains(@name,'zoneSdc')]");
	public static final By AisleValReserve = By.xpath(".//input[contains(@id,'aisleRdc')]");
	public static final By AisleValStaging = By.xpath(".//input[contains(@id,'aisleSdc')]");
	public static final By BarCodeVal = By.xpath("(.//input[contains(@id,'dataForm:b')])[1]");
	public static final By ReserveLocSave = By.xpath(".//input[contains(@id,'rmButton_1Save1')]");
	public static final By BayValReserve = By.xpath(".//input[contains(@id,'bayRdc')]");
	public static final By LevelValReserve = By.xpath(".//input[contains(@id,'lvlRdc')]");
	public static final By PositionValReserve = By.xpath(".//input[contains(@id,'posnRdc')]");
	public static final By LoadingFrame = By.xpath(".//div[contains(@id,'loadmask')]");
	public static final By LoadingFilter = By.xpath("//div[text()='Filter']");	
	public static final By AlertBox = By.xpath(".//div[contains(@id,'er_d1_c1')]");
	public static final By ReserveLocCancel = By.xpath(".//input[contains(@id,'rmButton_1Cancel1')]");
	public static final By CheckInCheckBox2  = By.xpath("(.//div [contains (@class,'x-grid-row-checker') and contains(@role,'presentation')])[2]");
	public static final By chkinddlkup2 = By.xpath("(.//div [contains(@class,'x-form-search-trigger') and contains(@id,'trigger-picker')])[17]");
	public static final By chkinddlkup = By.xpath("(.//div [contains(@class,'x-form-search-trigger') and contains(@id,'trigger-picker')])[15]");
	public static final By chkinLocation = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'mpslookupfield') and contains(@name,'currentLocation')]");
	public static final By chkinddlkup1 = By.xpath("(.//div [contains(@class,'x-form-search-trigger') and contains(@id,'trigger-picker')])[19]");
	public static final By chkinddlkup3 = By.xpath("(//label//span[text()='Location']//following::div[starts-with(@id,'mpslookupfield-')])[119]");
	public static final By chkinddlkupsrch = By.xpath(".//input [contains(@class,'x-form-text-default') and contains(@id,'textfield') and contains(@name, 'textfield') and contains(@data-tabindexsaved,'true')]");
	public static final By chkinddlkupsrch1 = By.cssSelector("html body div:nth-child(19) div:nth-child(13) div:nth-child(2) div div div:nth-child(1) table tbody tr td div div div div div:nth-child(1) div div div input");
	public static final By chkinddlkupfind = By.xpath(".//span [contains(@class,'x-btn-inner-default-small') and contains(@id,'button') and contains(text(), 'Find')]");
	public static final By chkinddlkupslct2 = By.xpath(".//div [contains(@class,'x-grid-cell-inner') and contains(@style,'text-align') and contains (text(),'DD')]");
	public static final By chkinddlkupslct1 = By.xpath(".//span [contains(@class,'x-btn-inner') and contains (text(),'Select')]");
	public static final By listtrailerCondition = By.xpath(".//input [contains(@class,'x-form-field') and contains (@name,'trailerCondition')]");
	public static final By LocationDone = By.xpath(".//span [contains(@class,'x-btn-inner') and contains(@id,'btnInnerEl') and contains(text(),'Done')]");
	public static final By btnSave1 = By.xpath("(.//span [contains(@class,'x-btn-inner') and contains (text(),'Confirm')])[1]");
	public static final By ASNselect = By.xpath("(.//div [contains(@class,'x-grid-row-checker') and contains(@role,'presentation')])[1]");
	public static final By ASNView = By.xpath(".//span [contains(@class,'x-btn-inner') and contains(@id,'btnInnerEl') and contains(text(),'View')]");
	public static final By TrailerNum = By.xpath("(.//div[contains(@class,'x-grid-cell-inner')])[13]");
	public static final By totalShipped = By.xpath(".//span[contains(@class,'captionData') and contains(@id,'ASN_Detail_Summary_TotalShippedQty')]");
	public static final By totalReceived = By.xpath(".//span[contains(@class,'captionData') and contains(@id,'ASN_Detail_TotalReceived_totalReceivedQtyString')]");
	public static final By AddYardTask = By.xpath(".//input[contains(@id,'AddYardTask')]");
	public static final By CarrierCodeYard = By.xpath(".//input[contains(@alt,'carrier')]");
	public static final By YardTrailerNum = By.xpath("(.//input[contains(@type,'text') and contains(@id,'dataForm:b')])[2]");
	public static final By TaskType = By.xpath(".//select[contains(@name,'tskType')]");
	public static final By TaskPrior = By.xpath(".//input[contains(@name,'taskPrior')]");
	public static final By SelectedYard = By.xpath(".//span[contains(text(),'location')]");
	public static final By ErrorSave = By.xpath(".//span[contains(text(),'Save')]");
	public static final By DestLoc = By.xpath(".//input[contains(@name,'tskDestn')]");
	public static final By AddTask = By.xpath(".//input[contains(@value,'Add Task')]");
	public static final By TaskId = By.xpath(".//span[contains(@id,':0:dis1')]");
	public static final By DestLocIcon = By.xpath(".//img[contains(@alt,'Find Location')]");
	public static final By DestLocSearch = By.xpath(".//input[contains(@id,'ymLocnSrch')]");
	public static final By DestLocFind = By.xpath(".//input[contains(@value,'Find') and contains(@id,'ymLocn')]");
	public static final By DestLocSelect = By.xpath(".//input[contains(@id,'ymLocn_SelectButton')]");
	public static final By Length = By.xpath(".//input [contains(@tabindex,'112') and contains(@id,'dataForm') and contains(@name,'dataForm')]");
	public static final By Width = By.xpath(".//input [contains(@tabindex,'113') and contains(@id,'dataForm') and contains(@name,'dataForm')]");
	public static final By Height = By.xpath(".//input [contains(@tabindex,'114') and contains(@id,'dataForm') and contains(@name,'dataForm')]");
	public static final By PutAwaySeq = By.xpath(".//input [contains(@tabindex,'87') and contains(@id,'dataForm') and contains(@name,'dataForm')]");
	public static final By PickSeq = By.xpath(".//input [contains(@tabindex,'86') and contains(@id,'dataForm') and contains(@name,'dataForm')]");
	public static final By ReserveLocPage = By.xpath(".//div[contains(@id,'PANEL_custom_locationSizePanel_top')]");

	public static final By DOCheckBox = By.xpath(".//div[contains(@class,'x-grid-row-checker')and contains(@role,'presentation')]");
	public static final By LPNMainCheckBox = By.xpath("(.//span[contains(@class,'x-column-header-text')and contains(@id,'gridcolumn-')])[16]");

	public static final By LPNCheckBox = By.xpath("//div[@title='LPN']//following::div[@role='presentation' and @class='x-grid-row-checker'][1]");
	public static final By LPNClick = By.xpath("//div[@title='LPN']//following::div[@role='presentation' and @class='x-grid-row-checker'][1]"); 
	public static final By CreateShipment = By.xpath("(//span[contains(@id,'menuitem-') and contains(@class,'x-menu-item-text') and contains(text(),'Create Shipment')])[2]"); 
	public static final By ShipmentViewDODropDwn = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[13]");
	public static final By ShipmentDO = By.xpath(".//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'ContainingOrderID')]");
	public static final By Override = By.xpath("(//span[contains(@id,'btnInnerE') and contains(@class,'x-btn-inner') and text()='Override'])");
	public PageBase(InstanceContainer ic) {

		this.ic = ic;
		this.driver = ic.driver;
		this.element= ic.element;
		this.wh = ic.wh;
		this.rc = ic.rc;
		this.dataTable = ic.dataTable;
		this.report = ic.report;
		this.commonData = ic.commonData;
		this.jDBC_Connection = ic.jDBC_Connection;
	}

	/**
	 * Method to search keyword
	 * 
	 * @return
	 * @throws Exception
	 */
	public PLPPage searchKeyword(String keyword) throws Exception {

		wh.clickElement(searchTxtBox);
		wh.sendKeys(searchTxtBox, keyword);
		wh.clickElement(searchBtn);

		expectedResult = (wh.isElementPresent(verifyPLPPage, 7) && wh.getText(
				breadCrumbDiv).contains(keyword)) ? true : false;

		if (expectedResult) {

			report.addReportStep("Search for keyword '" + keyword + "'",
					"Search PLP page for '" + keyword + "' is displayed",
					StepResult.PASS);

		} else {

			report.addReportStep("Search for keyword '" + keyword + "'",
					"Search PLP page for '" + keyword + "' is not displayed",
					StepResult.PASS);
		}

		return new PLPPage(ic);
	}

	/**
	 * Method to enter SKU
	 * 
	 * @param sku
	 * @return
	 * @throws Exception
	 */
	public PIPPage searchSkuNo(String sku) throws Exception {

		commonData.sku = sku;

		wh.sendKeys(searchTxtBox, sku);
		wh.clickElement(searchBtn);

		report.addReportStep("Type '" + sku
				+ "' in search text box and click on search button", "Typed '"
						+ sku + "' in search text box and clicked on search button",
						StepResult.PASS);

		return new PIPPage(ic);
	}

	/**
	 * Method to sign In User
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public CHILD signInUser(String email, String password) throws Exception {

		wh.clickElement(signInLink);

		if (wh.isElementPresentAfterWait(verifySignInPage)) {

			report.addReportStep(
					"Click on 'Sign in' link present on the top right side of the page",
					"Sign In page displayed successfully", StepResult.PASS);

			wh.sendKeys(emailTxtBox, email);
			wh.sendKeys(passwordTxtBox, password);
			wh.clickElement(signInBtn);

			if (wh.isElementPresentAfterWait(verifySignedInUser)) {

				report.addReportStep(
						"Enter valid email address and Password and click on the 'Sign in' button.",
						"Site navigated to the homepage after successful login",
						StepResult.PASS);
			} else {

				String stepDesc = "";

				if (!wh.isElementPresentAfterWait(verifySignedInUser)
						&& !wh.isElementPresentAfterWait(verifyHomePage)) {

					stepDesc = "Sign In failed for user and was not redirected to home page";

				} else if (!wh.isElementPresentAfterWait(verifySignedInUser)) {

					stepDesc = "User was not redirected to home page";

				} else {

					stepDesc = "User did not get signed in successfully";
				}

				report.addReportStep(
						"Enter valid email address and Password and click on the 'Sign in' button.",
						stepDesc, StepResult.FAIL);
			}

		} else {

			report.addReportStep("Click on Sign In link",
					"Sign In page did not get displayed", StepResult.FAIL);
		}

		return (CHILD) this;
	}

	/**
	 * This function waits for window to load
	 * 
	 * @param searchData
	 *            - Name of window to load
	 * @throws Exception
	 *             - Throws exception if window doesn't load
	 */

	public void waitForWindowToLoad(String searchData) throws Exception {

		By bodyLocator = By.xpath(".//SPAN[contains(text(),'" + searchData
				+ "')]");

		// Wait for window body to load

		if (!wh.isElementPresent(bodyLocator, 20)) {
			throw new Exception(
					"Page failed to load in 20 seconds. Looking for body of "
							+ searchData + " window. ");
		}

		// Wait until mask disappears
		wh.waitUntilDisappear(LOADINGMASK);

	}

	public static By getFilterDetailPanelHeaderLocation(String headerTitle,
			String filterDetailHeaderTitle) {

		windowHeader = windowHeader.replace("<WINDOWHEADER>", headerTitle);
		filterDetailHeader = filterDetailHeader.replace("<FILTERDETAILHEADER>",
				filterDetailHeaderTitle);

		By location = By.xpath(windowHeader);

		return location;
	}

	/**
	 * This function switches to the displayed frame. This is needed before any
	 * element can be found in that frame
	 */
	public void switchToDisplayedFrame() {

		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
	}


	public void closebtn() throws Exception{
		wh.isElementPresent(closebtn1);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		//System.out.println("close btn viewable in else " +closebtn1);
		wh.clickElement(closebtn1);
		wh.isElementPresent(closebtn2);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		//System.out.println("close btn viewable in else " +closebtn2);
		wh.clickElement(closebtn2);
	}

	public WebElement scrollPage(By by) {
		WebElement elem = driver.findElement(by);

		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)", elem);
		}
		return elem;
	}

	public static void puttyCallErrorValidation(String str, String port) throws IOException, InterruptedException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://wn3c3a:"+port+"/executeTestcase");

		// modified code below
		HttpEntity entity = new StringEntity(str, ContentType.create("application/json"));
		post.setEntity(entity);

		BufferedReader reader = new BufferedReader(new   
				InputStreamReader(client.execute(post).getEntity().getContent()));
		String response = reader.readLine();
		System.out.println(response);
	
		response = response.replaceAll("[^a-zA-Z0-9]", " "); 

		response=response.replaceAll("u001b", " ");

		response=response.replaceAll("u000f", " ");

        String[] arrOfStr = response.split("u000emqqqqqqqqqqqqqqqqqqqj", 2);
        
        System.out.println(arrOfStr);

            System.out.println(arrOfStr[1]);

            if(arrOfStr[1].contains("Error")) {
    			report.addReportStep("Putty result: ", arrOfStr[1], StepResult.FAIL);

    		}else {
    			report.addReportStep("Putty result: ", response, StepResult.PASS);

    		}

    }


	public static void puttyCall(String str, String port) throws IOException, InterruptedException {
		StringBuilder sc = new StringBuilder();
		URL url = new URL("http://wn3c3a:"+port+"/executeTestcase");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestProperty("Content-Type", "application/json");
		// stores server response.
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(str);
		//Thread.sleep(5000);
		writer.flush();
		String line2;
		// reads line by line
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		Thread.sleep(6000);
		while ((line2 = reader.readLine()) != null) {
			sc.append(line2);
			System.out.println(line2);

			line2 = line2.replaceAll("[^a-zA-Z0-9]", " "); 

			line2=line2.replaceAll("u001b", " ");

			line2=line2.replaceAll("u000f", " ");

			//line2=line2.replaceAll("nResponses", " ");

//			String[] split = line2.split("nResponses");
//			
//			System.out.println(split);
//
//			System.out.println(split[0]);
//
//			System.out.println(split[1]);

			report.addReportStep("Putty result: ", line2, StepResult.PASS);

			Thread.sleep(5000);
			//Thread.sleep(5000);
		}
	}


}
