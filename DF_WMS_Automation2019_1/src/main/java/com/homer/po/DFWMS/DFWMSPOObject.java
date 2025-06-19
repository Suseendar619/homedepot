package com.homer.po.DFWMS;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPOObject extends PageBase{
	public static boolean undoDOFlag = false;
	public StreamResult streamResult;
	 private static final List<Integer> NON_BUSINESS_DAYS = Arrays.asList(
             Calendar.SATURDAY,
             Calendar.SUNDAY
         );
	public static String asnId;
	public String shotGunOrderId;
	public static String SGStrtrailer;
	public static String refField4;
	public static String orderId;
	public static String DOOrderId;
	public String sgLPNID;
	List<String> doIds;
	public List<String> xmlData = new ArrayList<String>();
	JDBC_Connection jd = new JDBC_Connection(ic);
	public List<String> gridLocnLTL = new ArrayList<String>();
	public DFWMSPOObject(InstanceContainer ic) {
		super(ic);
	}

	public void postPOXml(String screen) throws Exception {
		if(!screen.equalsIgnoreCase("item") 
				&& !screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				&& !screen.equalsIgnoreCase("BVR_Houston_MutliStop")
				&& !screen.equalsIgnoreCase("MDO_Dallas_MutliStop")
				&& !screen.equalsIgnoreCase("FGND_Dallas_WM09")
				&& !screen.equalsIgnoreCase("UPS_Dallas_WM09")
				&& !screen.equalsIgnoreCase("EnvelopMulti_Dallas")
				&& !screen.equalsIgnoreCase("EnvelopMulti_Baltimore") 
				&& !screen.equalsIgnoreCase("Envelop_SingleTote2_Dallas")
				&& !screen.equalsIgnoreCase("Envelop_SingleTote2_Baltimore")
				&& !screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				&& !screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")
				&& !screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")
				&& !screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")
				&& !screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
				&& !screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
				&& !screen.equalsIgnoreCase("BVR_Newark_UndoDO")
				&& !screen.equalsIgnoreCase("BVR_Baltimore_UndoDO_Packed")
				&& !screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2")
				&& !screen.equalsIgnoreCase("LTLMultiStopLacey")
				&& !screen.equalsIgnoreCase("BVR_Houston_UndoDO")
				&& !screen.equalsIgnoreCase("LTL_Lacey_SplitShipment2")
				&& !screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")){ //second 
			Thread.sleep(1000);
			driver.switchTo().frame(0);
			//Resolution setting



		}


		String path = "";
		if(screen.equalsIgnoreCase("Inbound")){
			path = FilePathInbound;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment")){
			path = FilePathVerifyIBShipment;
		}else if(screen.equalsIgnoreCase("LTLOutbound")){
			path = FilePathOutbound ;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment10Lpns")){
			path = FilePathVerifyIBShipment10Lpns ;
		}else if(screen.equalsIgnoreCase("LTL HDU")){
			path = FilePathOutboundHDU ;
		}else if(screen.equalsIgnoreCase("Zone")){
			path =  FilePathInboundZonesHouston;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019")){
			path =  FilePathVerifyIBShipment2019;
		}else if(screen.equalsIgnoreCase("MultiItemRcvASN") 
				|| screen.equalsIgnoreCase("ShipmentRcvDtl") 
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")){
			path =  FilePathMultiItemRcvASN;
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtl")){
			path =  FilePathASNMultiItemRcvDtl;
		}else if(screen.equalsIgnoreCase("BK1PalletJack") 
				|| screen.equalsIgnoreCase("BK3NonShipReady")
				|| screen.equalsIgnoreCase("CartPickCon")
				||screen.equalsIgnoreCase("CartPickConBoston")
				|| screen.equalsIgnoreCase("CartPickConBaltimore")
				|| screen.equalsIgnoreCase("CartPickConNewark")
				|| screen.equalsIgnoreCase("HazmatFlow")
				|| screen.equalsIgnoreCase("BK1NonShipReady")
				|| screen.equalsIgnoreCase("BK1NonShipReadyLM")
				|| screen.equalsIgnoreCase("WaveReplenishment")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore")
				|| screen.equalsIgnoreCase("BK1NonShipReadyNewark")
				|| screen.equalsIgnoreCase("BK3NonShipReadyNewark")
				|| screen.equalsIgnoreCase("BK3NonShipReadyBaltimore")
				|| screen.equalsIgnoreCase("BK1NonShipReady_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyMiami")
				|| screen.equalsIgnoreCase("BK3NonShipReadyMiami")
				||screen.equalsIgnoreCase("BK1NonShipReadyBoston")
				|| screen.equalsIgnoreCase("BK3NonShipReadyBoston")){
			path =  FilePathOutbound2019;
		}else if(screen.equalsIgnoreCase("VAS")){
			path =  FilePathValueAddedService;
		}else if(screen.equalsIgnoreCase("LG_New")){
			path =  FilePathOutbound2012;
		}else if(screen.equalsIgnoreCase("BVR_LG")){
			path =  FilePathOutbound2012_BVR;
		}else if(screen.equalsIgnoreCase("PAX")){
			path =  FilePathOutbound_PAX;
		}
		else if(screen.equalsIgnoreCase("SCP")){
			path =  FilePathOutbound_SCP;
		}
		else if(screen.equalsIgnoreCase("MCP")){
			path =  FilePathOutbound_MCP;
		}else if(screen.equalsIgnoreCase("DallasZone")){
			path =  FilePathInboundZonesDallas;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Dallas")){
			path =  FilePathShipmentMultiItemRcvDtlDallas;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
			path =  FilePathVerifyIBShipment2019Dallas;
		}else if(screen.equalsIgnoreCase("BK1NonShipReadyDallas")
				|| screen.equalsIgnoreCase("BK1PalletJackDallas")
				|| screen.equalsIgnoreCase("BK1NonShipReadyLacey")
				|| screen.equalsIgnoreCase("BK1PalletJackLacey") 
				|| screen.equalsIgnoreCase("HazmatFlowDallas")
				|| screen.equalsIgnoreCase("HazmatFlowBaltimore")
				|| screen.equalsIgnoreCase("HazmatFlowNewark")
				|| screen.equalsIgnoreCase("WaveReplenishmentDallas")
				|| screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK3NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK3NonShipReadyTracey")
				|| screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyTracey")
				|| screen.equalsIgnoreCase("CartPickConTracey")
				|| screen.equalsIgnoreCase("BK1NonShipReadyAtlanta")
				||screen.equalsIgnoreCase("CartPickConAtlanta")
				||screen.equalsIgnoreCase("BK3NonShipReadyAtlanta")){
			path = FilePathOutbound2019Dallas;
		}else if(screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRNewark_UndoDO_IP")){
			path =  FilePathDallasUndoDOInPacking;
		}else if(screen.equalsIgnoreCase("DallasVAS")){
			path =  FilePathValueAddedServiceDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Dallas")
				|| screen.equalsIgnoreCase("HDUTL_Baltimore")
				|| screen.equalsIgnoreCase("HDUTL_Lacey")
				|| screen.equalsIgnoreCase("HDUTL_Newark")
				|| screen.equalsIgnoreCase("HDUTL_Tracey")
				|| screen.equalsIgnoreCase("HDUTL_Atlanta")){
			path = FilePathHDUTLDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Houston")){
			path = FilePathHDUTLHouston;
		}else if(screen.equalsIgnoreCase("LTLOutboundHouston")){
			path =  FilePathOutboundHouston;
		}else if(screen.equalsIgnoreCase("LTLOutboundDallas")
				|| screen.equalsIgnoreCase("LTLOutboundBaltimore")
				|| screen.equalsIgnoreCase("LTLOutboundLacey")
				|| screen.equalsIgnoreCase("LTLOBLacey_UndoDO1")
				|| screen.equalsIgnoreCase("LTL_Lacey")
				||screen.equalsIgnoreCase("LTL_Lacey")
				|| screen.equalsIgnoreCase("LTLOutboundTampa")
				|| screen.equalsIgnoreCase("LTLOutboundNewark")
				|| screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundTracey_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundTracey")
				|| screen.equalsIgnoreCase("LTLOutboundMiami")
				|| screen.equalsIgnoreCase("LTLOutboundBoston")
				||screen.equalsIgnoreCase("LTLOutboundAtlanta_UndoDO")
				||screen.equalsIgnoreCase("LTLOutboundAtlanta")){
			path =  FilePathOutboundDallas;
		}else if (screen.equalsIgnoreCase("SplitShipmentDallas")) {
			path =  FilePathOutBoundSplitShipment;
		}
		else if(screen.equalsIgnoreCase("LTL HDU Dallas")
				|| screen.equalsIgnoreCase("LTL HDU Lacey")
				|| screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2")
				|| screen.equalsIgnoreCase("LTL_HDU_Tracey")
				|| screen.equalsIgnoreCase("LTL_HDU_Miami")
				||screen.equalsIgnoreCase("LTL_HDU_Boston")
				||screen.equalsIgnoreCase("LTL_HDU_Atlanta")){
			path =  FilePathLTLHDUOutboundDallas;
		}else if(screen.equalsIgnoreCase("LTL HDU Houston")) {
			path =FilePathLTLHDUOutboundHouston;
		}else if(screen.equalsIgnoreCase("BVR_Undo")){

			path =  FilePathUndoBVRDallas;

		}else if(screen.equalsIgnoreCase("BVR_Dallas")
				|| screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				|| screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Newark_UndoDO")
				){
			path = FilePathOutboundBVRDallas;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MutliStop")) {
			path =FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_MutliStop")){
			path = FilePathOutboundMDODallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_Wyn")){
			path =  FilePathUPSOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_WES")){
			path =  FilePathWESOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas")
				||screen.equalsIgnoreCase("UPS_Dallas_WM09")
				||screen.equalsIgnoreCase("UPS_Lacey")){
			path = FilePathUPSOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_MISP")){
			path =  FilePathUPSOutboundDallasMISP;
		}
		else if(screen.equalsIgnoreCase("UPS_Houston_MISP")){
			path =  FilePathUPSOutboundHoustonMISP;
		}
		else if(screen.equalsIgnoreCase("FGND_Dallas")
				|| screen.equalsIgnoreCase("FGND_Baltimore")
				||screen.equalsIgnoreCase("FGND_Dallas_WM09")
				|| screen.equalsIgnoreCase("FGND_Newark")
				|| screen.equalsIgnoreCase("FGND_Lacey")
				|| screen.equalsIgnoreCase("FGND_Tampa")
				|| screen.equalsIgnoreCase("FGND_Tracey")
				|| screen.equalsIgnoreCase("FGND_Atlanta")
				|| screen.equalsIgnoreCase("FGND_Miami")
				|| screen.equalsIgnoreCase("FGND_Boston")){
			path =  FilePathFGNDOutboundDallas;
		}else if(screen.equalsIgnoreCase("FGND_Houston")){
			path =  FilePathOutboundFGNDHouston;
		}else if(screen.equalsIgnoreCase("FXHD_Dallas_Undo")) {

			path =  FilePathFXHDUndoDallas;

		}
		else if(screen.equalsIgnoreCase("FXHD_Dallas")
				|| screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				||screen.equalsIgnoreCase("FXHD_Baltimore")
				||screen.equalsIgnoreCase("FXHD_Lacey")
				||screen.equalsIgnoreCase("FXHD_Tampa")
				||screen.equalsIgnoreCase("FXHD_Newark")
				|| screen.equalsIgnoreCase("FXHD_Tracey")
				|| screen.equalsIgnoreCase("FXHD_Atlanta")
				|| screen.equalsIgnoreCase("FXHD_Miami")
				|| screen.equalsIgnoreCase("FXHD_Boston")){
			path =  FilePathFXHDOutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")){
			path =  FilePathBVRShotGunOutboundDallas;
		}else if(screen.equalsIgnoreCase("ShotgunOrder")){ 
			path =  FilePathShotgunOrder;
		}else if(screen.equalsIgnoreCase("ShotgunASN")){
			path =  FilePathShotgunASN;
		}else if(screen.equalsIgnoreCase("item")){
			path = FilePathItemXML;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_MIMP") 
				|| screen.equalsIgnoreCase("BVR_Dallas_MISP") 
				|| screen.equalsIgnoreCase("BVR_Baltimore_MIMP")){
			path =  FilePathBVROutboundDallasMIMP;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_OLPN")
				|| screen.equalsIgnoreCase("BVR_MutliStop") 
				|| screen.equalsIgnoreCase("BVR_Dallas_Yard")
				|| screen.equalsIgnoreCase("BVR_Dallas_OLPN_MS")
				){
			path =  FilePathBVROutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_MutliStop_Houston")|| screen.equalsIgnoreCase("BVR_Houston_Yard")) {
			
			path =  FilePathBVROutboundHouston;
		}else if(screen.equalsIgnoreCase("MDO_MutliStop")){
			
			path = FilePathOutbound2019;
		}else if(screen.equalsIgnoreCase("Envelop_Dallas")
				|| screen.equalsIgnoreCase("EnvelopMultiItem_Dallas")){
			path =  FilePathEnvelopOutboundDallas;
		}else if(screen.equalsIgnoreCase("Envelop_Baltimore")
				|| screen.equalsIgnoreCase("EnvelopMultiItem_Baltimore")){
			path =  FilePathEnvelopOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("EnvelopMulti_Dallas")){
			path =  FilePathEnvelopMultiOutboundDallas;
		}else if(screen.equalsIgnoreCase("EnvelopMulti_Baltimore")){
			path =  FilePathEnvelopMultiOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote1_Dallas")){
			path =  FilePathEnvelopeSingleTote1Dallas;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote1_Baltimore")){
			path =  FilePathEnvelopeSingleTote1Baltimore;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote2_Dallas")){
			path =  FilePathEnvelopeSingleTote2Dallas;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote2_Baltimore")){
			path =  FilePathEnvelopeSingleTote2Baltimore;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")){
			path =  FilePathBVRDallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")){
			path =  FilePathMDODallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")){
			path =  FilePathMDODallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")){
			path =  FilePathBVRDallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")){
			path =  FilePathBVRHoustonSplitShipment1;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")){
			path =  FilePathBVRHoustonSplitShipment2;
		}else if(screen.equalsIgnoreCase("UPS_Houston_Wyn")){
			path =  FilePathUPSHouston;
		}else if(screen.equalsIgnoreCase("UPS_Houston")
				||screen.equalsIgnoreCase("UPS_Baltimore")
				|| screen.equalsIgnoreCase("UPS_Newark")){
			path =  FilePathUPSHouston;
		}else if(screen.equalsIgnoreCase("EZShipment_Houston")
				|| screen.equalsIgnoreCase("EZShipment_Baltimore")
				|| screen.equalsIgnoreCase("EZShipment_Newark")
				|| screen.equalsIgnoreCase("EZShipment_Tracey")
				|| screen.equalsIgnoreCase("EZShipment_Miami")
				|| screen.equalsIgnoreCase("EZShipment_Boston")){
			path =  FilePathEZShipmentHouston;
		}else if(screen.equalsIgnoreCase("MOD10_Dallas")){
			path =  FilePathIBDallasMOD10;
		}else if(screen.equalsIgnoreCase("MOD10_Tampa")){
			path =  FilePathIBMOD10Tampa;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlTampa")){
			path =  FilePathMultiItemRcvASNTampa;
		}else if(screen.equalsIgnoreCase("MultiItemRcvASNBaltimore") 
				|| screen.equalsIgnoreCase("ShipmentRcvDtlBaltimore")
				|| screen.equalsIgnoreCase("ShipmentRcvDtlNewark")
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASNBaltimore")
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASNNewark")){
			path = FilePathMultiItemRcvASNBaltimore ; 
		}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASNTampa")){
			path = FilePathMultiItemRcvASNTampa ; 
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtlBaltimore")){
			path = FilePathASNMultiItemRcvDtlBaltimore ; 
		}else if(screen.equalsIgnoreCase("LTL_HDU_Baltimore")
				|| screen.equalsIgnoreCase("LTL_HDU_Newark")){
			path =  FilePathLTLHDUOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Lacey")){
			path = FilePathShipmentMultiItemRcvDtlLacey;
		}else if(screen.equalsIgnoreCase("ShotgunOrder_Lacey")){ 
			path =  FilePathShotgunOrderLacey;
		}else if(screen.equalsIgnoreCase("ShotgunASN_Lacey")){
			path =  FilePathShotgunASNlacey;
		}else if(screen.equalsIgnoreCase("BVR_Lacey")
				|| screen.equalsIgnoreCase("BVR_Lacey_Yard")){
			path =  FilePathBVRLacey;
		}else if(screen.equalsIgnoreCase("BVR_Lacey_MISP")){
			path =  FilePathBVRLaceyMultiItem;
		}else if(screen.equalsIgnoreCase("VAS_Lacey")){
			path =  FilePathVASLacey;
		}else if(screen.equalsIgnoreCase("VAS_Baltimore")
				|| screen.equalsIgnoreCase("VAS_Newark")){
			path =  FilePathVASBaltimore;
		}else if(screen.equalsIgnoreCase("MOD10_Baltimore")){
			path =  FilePathIBBaltimoreMOD10;
		} else if(screen.equalsIgnoreCase("MultiShipmentLacey")){
			path =  FilePathLTLMILacey;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Lacey")){
			path =  FilePathVerifyIBShipment2019Lacey;
		}else if(screen.equalsIgnoreCase("MOD10_Lacey")){
			path =  FilePathIBLaceyMOD10;
		}else if(screen.equalsIgnoreCase("UPS_Baltimore_MISP")){
			path =  FilePathUPSOutboundBaltimoreMISP;
		}else if(screen.equalsIgnoreCase("UPS_Lacey_MISP")){
			path =  FilePathUPSOutboundLaceyMISP;
		}else if(screen.equalsIgnoreCase("LTLMultiStopLacey")){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("BVR_Houston") 
				|| screen.equalsIgnoreCase("LoadTrailor_NonParcel")
				|| screen.equalsIgnoreCase("BVR_Houston_Cancel_olpn")
				|| screen.equalsIgnoreCase("BVR_Houston_UndoDO")){
			path =  FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("MOD10_Houston")){
			path =  FilePathIBMOD10Houston;
		}else if(screen.equalsIgnoreCase("BK1NonShipReady_MultiShipment")){
			path =  FilePathOutboundMDOMIHouston;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MISP")){
			path =  FilePathOutboundBVRMIHouston;
		}else if(screen.equalsIgnoreCase("LTL_Houston_MISP")){
			path =  FilePathOutboundLTLMIHouston;
		}
//		else if(screen.equalsIgnoreCase("UPS_Houston_MISP")){
//			path =  FilePathOutboundUPSMIHouston;
//		}
		else if(screen.equalsIgnoreCase("LTL_Lacey_SplitShipment1")){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("LTL_Lacey_SplitShipment2")){
			path =  FilePathLTL_SplitShipment_Lacey;
		}else if(screen.equalsIgnoreCase("FXHD_Houston")){
			path =  FilePathFXHD_Houston;
		}else if(screen.equalsIgnoreCase("Split_Combine")){
			path =  FilePathSplitAndCombine_Houston;
		}else if(screen.equalsIgnoreCase("LTL_HDU_Tampa")){
			path =  FilePathLTLHDU_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tampa")
				|| screen.equalsIgnoreCase("UPS_Tracey")
				|| screen.equalsIgnoreCase("UPS_Atlanta")
				|| screen.equalsIgnoreCase("UPS_Miami")||screen.equalsIgnoreCase("UPS_Boston")){
			path =  FilePathUPS_Tampa;
		}else if(screen.equalsIgnoreCase("VAS_Tampa")){
			path =  FilePathVAS_Tampa;
		}else if(screen.equalsIgnoreCase("EZShipment_Tampa")){
			path =  FilePathEZShipment_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tampa_MISP")){
			path =  FilePathUPSMI_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tracey_MISP")
				||screen.equalsIgnoreCase("UPS_Atlanta_MISP")){
			path =  FilePathUPSMI_Tracey;
		}else if(screen.equalsIgnoreCase("UPS_Newark_MISP")){
			path =  FilePathUPSMI_Newark;
		}else if(screen.equalsIgnoreCase("MOD10_Tracey")){
			path =  FilePathIBMOD10Tracey;
		}else if(screen.equalsIgnoreCase("MOD10_Miami")||screen.equalsIgnoreCase("MOD10_Boston")){
			path =  FilePathIBMOD10Miami;
		}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASNMiami")
				||screen.equalsIgnoreCase("ShipmentRcvDtlMiami")||screen.equalsIgnoreCase("ShipmentMultiItemRcvASNBoston")
				||screen.equalsIgnoreCase("ShipmentRcvDtlBoston")){
			path = FilePathMultiItemRcvASNMiami ; 
		}else if(screen.equalsIgnoreCase("UPS_Miami_MISP")||screen.equalsIgnoreCase("UPS_Boston_MISP")){
			path =  FilePathUPSMI_Miami;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Miami")||screen.equalsIgnoreCase("VerifyIBShipment2019_Boston")){
			path =  FilePathVerifyIBShipment2019Miami;
		}else if(screen.equalsIgnoreCase("EZShipment_Atlanta")){
			path =  FilePathEZShipmentAtlanta;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlAtlanta")){
			path =  FilePathMultiItemRcvASNAtlanta;
		}

		//		// click on post message
		//		if (wh.isElementPresent(Menu, 3)) {
		//			wh.clickElement(Menu);
		//			wh.clickElement(Integration);
		//			wh.clickElement(PostMessage);
		//		}

		//		//validate the page
		//		if(!driver.getTitle().equals(PageTitle)){
		//			waitForWindowToLoad("Post Message");
		//		}

		//post xml
		if(wh.isElementPresent(ChooseFile, 5)){
			driver.switchTo().defaultContent();
			wh.clickElement(Maximize);
			driver.switchTo().frame(0);
			wh.sendKeys(ChooseFile, path);
			wh.clickElement(Send);



		}else{
			report.addReportStep("Post XML", "Unable to find element", StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		Thread.sleep(1000);
		String doId = "";
		if(DFWMSInbounfFlowStepDefn.poId != null){
			doId = DFWMSInbounfFlowStepDefn.poId;
		}else if(DFWMSInbounfFlowStepDefn.doId != null){
			doId = DFWMSInbounfFlowStepDefn.doId;
		}
		report.addReportStepWithScreenshots(
				"Post PO xml in PO under Distribution",
				"Successfully posted PO xml. Order Id : "+doId,
				StepResult.PASS);
		if(screen.equalsIgnoreCase("item") 
				|| screen.equalsIgnoreCase("Envelop_Dallas")
				|| screen.equalsIgnoreCase("Envelop_Baltimore")
				|| screen.equalsIgnoreCase("BVR_MutliStop")
				|| screen.equalsIgnoreCase("BVR_MutliStop_Houston")
				|| screen.equalsIgnoreCase("MDO_MutliStop")
				|| screen.equalsIgnoreCase("Envelop_SingleTote1_Dallas") 
				|| screen.equalsIgnoreCase("Envelop_SingleTote1_Baltimore")
				|| screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")
				||screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")
				|| screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore_UndoDO")
				|| screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRNewark_UndoDO_IP")
				|| screen.equalsIgnoreCase("LTLOBLacey_UndoDO1")
				|| screen.equalsIgnoreCase("LTLOutboundLacey")
				|| screen.equalsIgnoreCase("BK1NonShipReady_UndoDO")
				|| screen.equalsIgnoreCase("LTL_Lacey_SplitShipment1")
				|| screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				//|| screen.equalsIgnoreCase("UPS_Dallas_WM09")
				|| screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")){
			if(wh.isElementPresent(Reset, 3)){
				wh.clickElement(Reset);
			}

			if(screen.equalsIgnoreCase("BK1NSRNewark_UndoDO_IP")){
				undoDOFlag = true;
			}
		}else{

			closebtn();

		}
	}

	public String createPOXml(String screen) throws Exception {

		String path = "";
		String PO = "PO";
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String OD = simpleDateFormat.format(new Date());
		String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String PO_DUE_DATE = PO_DUE_DATE_OD + " " + "00:00:00";
		String PurchaseOrder = PO + Order + " " + OD + " " + "00:00:00";
		//String path = System.getProperty("user.dir") + xmlpath;
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		simpleDateFormat.format(calendar.getTime());
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		System.out.println(DeliveryDate);

		if(screen.equalsIgnoreCase("Inbound")){
			path = FilePathInbound;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment")){
			path = FilePathVerifyIBShipment;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment10Lpns")){
			path = FilePathVerifyIBShipment10Lpns ;
		}else if(screen.equalsIgnoreCase("BK1Active") || screen.equalsIgnoreCase("BK2Active") 
				|| screen.equalsIgnoreCase("BN1Active")|| screen.equalsIgnoreCase("SR5Active")
				|| screen.equalsIgnoreCase("BK3Active")||screen.equalsIgnoreCase("BK4Active")|| screen.equalsIgnoreCase("SR1Active")
				|| screen.equalsIgnoreCase("SR2Active") ||screen.equalsIgnoreCase("SR4Active")|| screen.equalsIgnoreCase("SR1Reserve")
				||screen.equalsIgnoreCase("BK5Active")||screen.equalsIgnoreCase("BK6Active")
				|| screen.equalsIgnoreCase("SR2Reserve")||screen.equalsIgnoreCase("SR4Reserve") || screen.equalsIgnoreCase("CantileverActive")
				|| screen.equalsIgnoreCase("HazmatActive") || screen.equalsIgnoreCase("BK2Reserve")
				|| screen.equalsIgnoreCase("CPActive") ||  screen.equalsIgnoreCase("BK3ActiveBaltimore")
				|| screen.equalsIgnoreCase("BK1ActiveBaltimore") || screen.equalsIgnoreCase("CP1ActiveBaltimore")
				|| screen.equalsIgnoreCase("SR1ActiveBaltimore") || screen.equalsIgnoreCase("BK1ActiveLacey")
				|| screen.equalsIgnoreCase("SR2ActiveBaltimore")||  screen.equalsIgnoreCase("HazmatActivebaltimore")
				|| screen.equalsIgnoreCase("SR1ReserveBaltimore") || screen.equalsIgnoreCase("BK2ReserveBaltimore")
				|| screen.equalsIgnoreCase("SR2ReserveBaltimore")||screen.equalsIgnoreCase("CNActiveBaltimore")
				|| screen.equalsIgnoreCase("BK3ActiveLacey") || screen.equalsIgnoreCase("SR1ActiveLacey")
				|| screen.equalsIgnoreCase("CNActiveLacey") ||screen.equalsIgnoreCase("CFActiveLacey")
				|| screen.equalsIgnoreCase("BK2ActiveLacey") || screen.equalsIgnoreCase("BN1ActiveLacey")
				|| screen.equalsIgnoreCase("PF1ActiveLacey")|| screen.equalsIgnoreCase("SR2ActiveLacey")
				|| screen.equalsIgnoreCase("SR7ActiveLacey")|| screen.equalsIgnoreCase("SR1ReserveLacey")
				|| screen.equalsIgnoreCase("TBLActiveLacey") || screen.equalsIgnoreCase("TBLActiveHouston")
				|| screen.equalsIgnoreCase("BK1ActiveTampa") || screen.equalsIgnoreCase("BK3ActiveTampa")
				|| screen.equalsIgnoreCase("CNActiveTampa")||screen.equalsIgnoreCase("CPActiveTampa")
				|| screen.equalsIgnoreCase("HZActiveTampa")||screen.equalsIgnoreCase("SR1ActiveTampa")
				|| screen.equalsIgnoreCase("SR2ActiveTampa") ||screen.equalsIgnoreCase("BK2ReserveTampa")
				|| screen.equalsIgnoreCase("CNReserveTampa")||screen.equalsIgnoreCase("SR1ReserveTampa")
				|| screen.equalsIgnoreCase("SR2ReserveTampa")||screen.equalsIgnoreCase("SR2ActiveNewark")
				|| screen.equalsIgnoreCase("BK1ActiveNewark")||screen.equalsIgnoreCase("CNActiveNewark")
				|| screen.equalsIgnoreCase("BK3ActiveNewark")||screen.equalsIgnoreCase("CP1ActiveNewark")
				|| screen.equalsIgnoreCase("HazmatActiveNewark")||screen.equalsIgnoreCase("SR1ActiveNewark")
				|| screen.equalsIgnoreCase("BK2ReserveNewark")||screen.equalsIgnoreCase("SR1ReserveNewark")
				|| screen.equalsIgnoreCase("SR2ReserveNewark") || screen.equalsIgnoreCase("HZ1ActiveTracey")
				|| screen.equalsIgnoreCase("SR1ActiveTracey") || screen.equalsIgnoreCase("SR2ActiveTracey")
				|| screen.equalsIgnoreCase("SR1ReserveTracey") || screen.equalsIgnoreCase("SR2ReserveTracey")
				|| screen.equalsIgnoreCase("BK1ActiveTracey")|| screen.equalsIgnoreCase("BK3ActiveTracey")
				|| screen.equalsIgnoreCase("CNActiveTracey")|| screen.equalsIgnoreCase("CPActiveTracey")
				|| screen.equalsIgnoreCase("BK2ReserveTracey")|| screen.equalsIgnoreCase("CNReserveTracey")
				|| screen.equalsIgnoreCase("BK1ActiveMiami")|| screen.equalsIgnoreCase("BK3ActiveMiami")
				|| screen.equalsIgnoreCase("CNActiveMiami")|| screen.equalsIgnoreCase("CPActiveMiami")
				|| screen.equalsIgnoreCase("CPActiveMiami")|| screen.equalsIgnoreCase("HZ1ActiveMiami")
				|| screen.equalsIgnoreCase("SR1ActiveMiami")|| screen.equalsIgnoreCase("SR2ActiveMiami")
				|| screen.equalsIgnoreCase("BK2ReserveMiami")|| screen.equalsIgnoreCase("CNReserveMiami")
				|| screen.equalsIgnoreCase("SR1ReserveMiami")|| screen.equalsIgnoreCase("SR2ReserveMiami")
				|| screen.equalsIgnoreCase("BK1ActiveBoston")|| screen.equalsIgnoreCase("BK3ActiveBoston")
				|| screen.equalsIgnoreCase("CNActiveBoston")|| screen.equalsIgnoreCase("CPActiveBoston")
				|| screen.equalsIgnoreCase("CPActiveBoston")|| screen.equalsIgnoreCase("HZ1ActiveBoston")
				|| screen.equalsIgnoreCase("SR1ActiveBoston")|| screen.equalsIgnoreCase("SR2ActiveBoston")
				|| screen.equalsIgnoreCase("BK2ReserveBoston")|| screen.equalsIgnoreCase("CNReserveBoston")
				|| screen.equalsIgnoreCase("SR1ReserveBoston")|| screen.equalsIgnoreCase("SR2ReserveBoston")
				|| screen.equalsIgnoreCase("HZ1ActiveAtlanta")
				|| screen.equalsIgnoreCase("SR1ActiveAtlanta") || screen.equalsIgnoreCase("SR2ActiveAtlanta")
				|| screen.equalsIgnoreCase("SR1ReserveAtlanta") || screen.equalsIgnoreCase("SR2ReserveAtlanta")
				|| screen.equalsIgnoreCase("BK1ActiveAtlanta")|| screen.equalsIgnoreCase("BK3ActiveAtlanta")
				|| screen.equalsIgnoreCase("CNActiveAtlanta")|| screen.equalsIgnoreCase("CPActiveAtlanta")
				|| screen.equalsIgnoreCase("BK2ReserveAtlanta")|| screen.equalsIgnoreCase("CNReserveAtlanta")
				){
			path =  FilePathInboundZonesHouston;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019")){
			path =  FilePathVerifyIBShipment2019;
		}else if(screen.equalsIgnoreCase("MultiItemRcvASN") || screen.equalsIgnoreCase("ShipmentRcvDtl") 
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")){
			path =  FilePathMultiItemRcvASN;
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtl")){
			path = FilePathASNMultiItemRcvDtl ; 
		}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASNTampa")){
			path = FilePathMultiItemRcvASNTampa ; 
		}else if(screen.equalsIgnoreCase("EZShipment_Houston")
				|| screen.equalsIgnoreCase("EZShipment_Baltimore")
				|| screen.equalsIgnoreCase("EZShipment_Newark")){
			path =  FilePathEZShipmentHouston;
		}else if(screen.equalsIgnoreCase("BK1ActiveDallas") || screen.equalsIgnoreCase("SR1ActiveDallas")
				|| screen.equalsIgnoreCase("BK3ActiveDallas") || screen.equalsIgnoreCase("CNActiveDallas")
				|| screen.equalsIgnoreCase("HZActiveDallas") || screen.equalsIgnoreCase("BNActiveDallas")
				|| screen.equalsIgnoreCase("SR1ReserveDallas")|| screen.equalsIgnoreCase("CFActiveDallas")
				|| screen.equalsIgnoreCase("CFReserveDallas") || screen.equalsIgnoreCase("CNReserveDallas")
				|| screen.equalsIgnoreCase("HZReserveDallas") || screen.equalsIgnoreCase("SR2ActiveDallas") 
				|| screen.equalsIgnoreCase("SR2ReserveDallas") || screen.equalsIgnoreCase("BK2ReserveDallas") 
				|| screen.equalsIgnoreCase("BK2ActiveDallas") || screen.equalsIgnoreCase("SR7InboundDallas")
				|| screen.equalsIgnoreCase("CF2InboundDallas") || screen.equalsIgnoreCase("CF1ReserveDallas")
				|| screen.equalsIgnoreCase("CF2ReserveDallas")|| screen.equalsIgnoreCase("BN1InboundDallas")
				|| screen.equalsIgnoreCase("PF1InboundDallas")|| screen.equalsIgnoreCase("TBLActiveDallas")
				){ 
			path =  FilePathInboundZonesDallas;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Dallas")){ 
			path =  FilePathShipmentMultiItemRcvDtlDallas;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlTampa")){
			path =  FilePathMultiItemRcvASNTampa;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
			path =  FilePathVerifyIBShipment2019Dallas;
		}else if(screen.equalsIgnoreCase("ShotgunOrder")){ 
			path =  FilePathShotgunOrder;
		}else if(screen.equalsIgnoreCase("ShotgunASN")){
			path =  FilePathShotgunASN;
		}else if(screen.equalsIgnoreCase("MOD10_Dallas")){
			path =  FilePathIBDallasMOD10;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlBaltimore")
				|| screen.equalsIgnoreCase("ShipmentRcvDtlNewark")
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASNBaltimore")
				|| screen.equalsIgnoreCase("ShipmentMultiItemRcvASNNewark")){
			path = FilePathMultiItemRcvASNBaltimore ; 
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtlBaltimore")){
			path = FilePathASNMultiItemRcvDtlBaltimore ; 
		}else if(screen.equalsIgnoreCase("LTL_HDU_Baltimore")
				|| screen.equalsIgnoreCase("LTL_HDU_Newark")){
			path =  FilePathLTLHDUOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Lacey")){
			path = FilePathShipmentMultiItemRcvDtlLacey;
		}else if(screen.equalsIgnoreCase("ShotgunOrder_Lacey")){ 
			path =  FilePathShotgunOrderLacey;
		}else if(screen.equalsIgnoreCase("ShotgunASN_Lacey")){
			path =  FilePathShotgunASNlacey;
		}else if(screen.equalsIgnoreCase("MOD10_Baltimore")){
			path =  FilePathIBBaltimoreMOD10;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Lacey")){
			path =  FilePathVerifyIBShipment2019Lacey;
		}else if(screen.equalsIgnoreCase("MOD10_Lacey")){
			path =  FilePathIBLaceyMOD10;
		}else if(screen.equalsIgnoreCase("MOD10_Houston")){
			path =  FilePathIBMOD10Houston;
		}else if(screen.equalsIgnoreCase("MOD10_Tampa")){
			path =  FilePathIBMOD10Tampa;
		}else if(screen.equalsIgnoreCase("MOD10_Tracey")){
			path =  FilePathIBMOD10Tracey;
		}else if(screen.equalsIgnoreCase("MOD10_Miami")||screen.equalsIgnoreCase("MOD10_Boston")){
			path =  FilePathIBMOD10Miami;
		}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASNMiami")
				||screen.equalsIgnoreCase("ShipmentRcvDtlMiami")||screen.equalsIgnoreCase("ShipmentMultiItemRcvASNBoston")
				||screen.equalsIgnoreCase("ShipmentRcvDtlBoston")){
			path = FilePathMultiItemRcvASNMiami ; 
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Miami")|| screen.equalsIgnoreCase("VerifyIBShipment2019_Boston")){
			path =  FilePathVerifyIBShipment2019Miami;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlAtlanta")){
			path =  FilePathMultiItemRcvASNAtlanta;
		}
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;

			document = documentBuilder.parse(path);

			//DC
//			String dc = "";
//			jd.dbDFWMSMapping();
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
//				dc = "5829";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				dc = "5832";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				dc = "5823";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//				dc = "5831";
//			}else if(jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
//				dc = "5831";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")){
//				dc = "5855";
//			}else if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				dc = "5854";
//			}else if(jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				dc = "5857";
//			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				dc = "5841";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				dc = "5860";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				dc = "5882";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				dc = "5523";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2012")){
//				dc = "6777";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				dc = "6705";
//			}

			jd.dbDFWMSMapping();
			String poOrder = new SimpleDateFormat("MMddmm").format(Calendar.getInstance().getTime());
			if(screen.equalsIgnoreCase("ShotgunOrder")|| screen.equalsIgnoreCase("ShotgunOrder_Lacey")){ 
				//orderid
				shotGunOrderId = poOrder+"0R";
				Node orderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
				orderId.setTextContent(shotGunOrderId);

				Node RefShipmentNbr = document.getElementsByTagName("RefShipmentNbr").item(0);                   
				RefShipmentNbr.setTextContent(poOrder+"0");

				//asn
				asnId = "S000"+poOrder;
				Node asn = document.getElementsByTagName("AllocationSourceId").item(0);                   
				asn.setTextContent(asnId);

				//dates
				String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
				Node OrderedDttm = document.getElementsByTagName("OrderedDttm").item(0);                   
				OrderedDttm.setTextContent(date+ " " + "00:00:00");

				Node MustReleaseByDttm = document.getElementsByTagName("MustReleaseByDttm").item(0);                   
				MustReleaseByDttm.setTextContent(date+ " " + "00:00:00");

				Node DeliveryStartDttm = document.getElementsByTagName("DeliveryStartDttm").item(0);                   
				DeliveryStartDttm.setTextContent(date+ " " + "00:00:00");

				Node DeliveryEndDttm = document.getElementsByTagName("DeliveryEndDttm").item(0);                   
				DeliveryEndDttm.setTextContent(date+ " " + "23:59:59");
			}else if(screen.equalsIgnoreCase("ShotgunASN")|| screen.equalsIgnoreCase("ShotgunASN_Lacey")){

				//asn
				Node asn = document.getElementsByTagName("ASNID").item(0);                   
				asn.setTextContent(asnId);

				//BillOfLadingNumber
				long first14 = (long) (Math.random() * 100000000000000L);
				long number = 5200000000000000L + first14;
				Node BillOfLadingNumber = document.getElementsByTagName("BillOfLadingNumber").item(0);                   
				BillOfLadingNumber.setTextContent(String.valueOf(number)+"0");

				//PRONumber
				Random r = new Random();
				int numbers = 1000000000 + (int)(r.nextDouble() * 999999999);
				Node PRONumber = document.getElementsByTagName("PRONumber").item(0);                   
				PRONumber.setTextContent(String.valueOf(numbers)+"0");

				//trailer
				SGStrtrailer = "T" +new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
				Node TrailerNbr = document.getElementsByTagName("TrailerNbr").item(0);                   
				TrailerNbr.setTextContent(SGStrtrailer);

				//seal num
				String sealNum = "SEAL"+poOrder;
				Node SealNumber = document.getElementsByTagName("SealNumber").item(0);                   
				SealNumber.setTextContent(sealNum);

				//lpn
				sgLPNID = "DBH000"+poOrder;
				Node LPNID = document.getElementsByTagName("LPNID").item(0);                   
				LPNID.setTextContent(sgLPNID);

				//orderid
				Node CrossdockOrderId = document.getElementsByTagName("CrossdockOrderId").item(0);                   
				CrossdockOrderId.setTextContent(shotGunOrderId);

				int nodelist = document.getElementsByTagName("DestinationFacilityAliasId").getLength();
				for(int i = 0;i<nodelist;i++){
					Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(i);  
					DestinationFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);
				}

				int nodelist1 = document.getElementsByTagName("OriginFacilityAliasId").getLength();
				for(int i = 0;i<nodelist1;i++){
					Node OriginFacilityAliasId = document.getElementsByTagName("OriginFacilityAliasId").item(i);  
					OriginFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);
				}

			}else{
				//xmlpath
				Node refId = document.getElementsByTagName("Reference_ID").item(0);                   
				refId.setTextContent(PurchaseOrder);

				Node orderId = document.getElementsByTagName("OrderId").item(0);                   
				orderId.setTextContent(PO + Order);

				Node poDueDate = document.getElementsByTagName("PoDueDate").item(0);
				poDueDate.setTextContent(DeliveryDate);

				Node pODate = document.getElementsByTagName("PODate").item(0);
				pODate.setTextContent(PO_DUE_DATE);

				if(screen.equalsIgnoreCase("BK1Active") || screen.equalsIgnoreCase("BN1Active")
						|| screen.equalsIgnoreCase("SR2Reserve")|| screen.equalsIgnoreCase("SR5Active")  
						|| screen.equalsIgnoreCase("BK3Active")||screen.equalsIgnoreCase("BK4Active")
						|| screen.equalsIgnoreCase("SR1Reserve")||screen.equalsIgnoreCase("BK2Active")
						|| screen.equalsIgnoreCase("BK5Active")||screen.equalsIgnoreCase("BK6Active")
						||screen.equalsIgnoreCase("SR4Reserve")
						|| screen.equalsIgnoreCase("SR1Active")|| screen.equalsIgnoreCase("SR4Active")|| screen.equalsIgnoreCase("SR2Active")
						|| screen.equalsIgnoreCase("CantileverActive") || screen.equalsIgnoreCase("HazmatActive")
						|| screen.equalsIgnoreCase("BK2Reserve") || screen.equalsIgnoreCase("BK1ActiveDallas")
						|| screen.equalsIgnoreCase("SR1ActiveDallas") || screen.equalsIgnoreCase("CPActive")
						|| screen.equalsIgnoreCase("BNActiveDallas") || screen.equalsIgnoreCase("SR1ReserveDallas")
						|| screen.equalsIgnoreCase("CFActiveDallas") || screen.equalsIgnoreCase("CNReserveDallas")
						|| screen.equalsIgnoreCase("SR2ActiveDallas") || screen.equalsIgnoreCase("SR2ReserveDallas") 
						|| screen.equalsIgnoreCase("BK2ReserveDallas") || screen.equalsIgnoreCase("BK2ActiveDallas")
						|| screen.equalsIgnoreCase("SR7InboundDallas")|| screen.equalsIgnoreCase("CF2InboundDallas")
						|| screen.equalsIgnoreCase("CF1ReserveDallas") || screen.equalsIgnoreCase("CF2ReserveDallas")
						|| screen.equalsIgnoreCase("BN1InboundDallas")|| screen.equalsIgnoreCase("PF1InboundDallas")
						|| screen.equalsIgnoreCase("BK3ActiveDallas") || screen.equalsIgnoreCase("HZActiveDallas")
						|| screen.equalsIgnoreCase("CNActiveDallas") || screen.equalsIgnoreCase("BK3ActiveBaltimore")
						|| screen.equalsIgnoreCase("BK1ActiveBaltimore") || screen.equalsIgnoreCase("CP1ActiveBaltimore")
						|| screen.equalsIgnoreCase("SR1ActiveBaltimore")|| screen.equalsIgnoreCase("BK1ActiveLacey")
						|| screen.equalsIgnoreCase("SR1ReserveBaltimore") ||  screen.equalsIgnoreCase("BK2ReserveBaltimore")
						|| screen.equalsIgnoreCase("SR2ActiveBaltimore") ||  screen.equalsIgnoreCase("HazmatActivebaltimore")
						|| screen.equalsIgnoreCase("SR2ReserveBaltimore")|| screen.equalsIgnoreCase("CNActiveBaltimore")
						|| screen.equalsIgnoreCase("SR1ActiveLacey") || screen.equalsIgnoreCase("CNActiveLacey")
						|| screen.equalsIgnoreCase("CFActiveLacey") || screen.equalsIgnoreCase("BK2ActiveLacey")
						|| screen.equalsIgnoreCase("BN1ActiveLacey") || screen.equalsIgnoreCase("PF1ActiveLacey")
						|| screen.equalsIgnoreCase("SR2ActiveLacey") || screen.equalsIgnoreCase("SR7ActiveLacey")
						|| screen.equalsIgnoreCase("SR1ReserveLacey") || screen.equalsIgnoreCase("TBLActiveLacey")
						|| screen.equalsIgnoreCase("EZShipment_Houston") || screen.equalsIgnoreCase("EZShipment_Baltimore")
						|| screen.equalsIgnoreCase("BK1ActiveTampa") || screen.equalsIgnoreCase("BK3ActiveTampa")
						|| screen.equalsIgnoreCase("CNActiveTampa") || screen.equalsIgnoreCase("CPActiveTampa")
						|| screen.equalsIgnoreCase("HZActiveTampa") || screen.equalsIgnoreCase("SR1ActiveTampa")
						|| screen.equalsIgnoreCase("SR2ActiveTampa") || screen.equalsIgnoreCase("BK2ReserveTampa")
						|| screen.equalsIgnoreCase("CNReserveTampa") || screen.equalsIgnoreCase("SR1ReserveTampa")
						|| screen.equalsIgnoreCase("SR2ReserveTampa") || screen.equalsIgnoreCase("SR2ActiveNewark")
						|| screen.equalsIgnoreCase("BK1ActiveNewark") || screen.equalsIgnoreCase("CNActiveNewark")
						|| screen.equalsIgnoreCase("BK3ActiveNewark") || screen.equalsIgnoreCase("CP1ActiveNewark")
						|| screen.equalsIgnoreCase("HazmatActiveNewark") || screen.equalsIgnoreCase("SR1ActiveNewark")
						|| screen.equalsIgnoreCase("BK2ReserveNewark")|| screen.equalsIgnoreCase("SR1ReserveNewark")
						|| screen.equalsIgnoreCase("SR2ReserveNewark") || screen.equalsIgnoreCase("EZShipment_Newark")
						|| screen.equalsIgnoreCase("BK2Active") || screen.equalsIgnoreCase("BK3ActiveLacey") 
						|| screen.equalsIgnoreCase("SR1ActiveLacey") || screen.equalsIgnoreCase("TBLActiveHouston")
						|| screen.equalsIgnoreCase("SR1ActiveDallas") || screen.equalsIgnoreCase("CFReserveDallas") 
						|| screen.equalsIgnoreCase("HZReserveDallas") || screen.equalsIgnoreCase("TBLActiveDallas")
						|| screen.equalsIgnoreCase("HZ1ActiveTracey") || screen.equalsIgnoreCase("SR1ActiveTracey")
						|| screen.equalsIgnoreCase("SR2ActiveTracey") || screen.equalsIgnoreCase("SR1ReserveTracey") 
						|| screen.equalsIgnoreCase("SR2ReserveTracey") || screen.equalsIgnoreCase("BK1ActiveTracey")
						|| screen.equalsIgnoreCase("BK3ActiveTracey") || screen.equalsIgnoreCase("CNActiveTracey")
						|| screen.equalsIgnoreCase("CPActiveTracey") || screen.equalsIgnoreCase("BK2ReserveTracey")
						|| screen.equalsIgnoreCase("CNReserveTracey")|| screen.equalsIgnoreCase("BK1ActiveMiami")
						|| screen.equalsIgnoreCase("BK3ActiveMiami")|| screen.equalsIgnoreCase("CNActiveMiami")
						|| screen.equalsIgnoreCase("CPActiveMiami")|| screen.equalsIgnoreCase("CPActiveMiami")
						|| screen.equalsIgnoreCase("HZ1ActiveMiami")|| screen.equalsIgnoreCase("SR1ActiveMiami")
						|| screen.equalsIgnoreCase("SR2ActiveMiami")|| screen.equalsIgnoreCase("BK2ReserveMiami")
						|| screen.equalsIgnoreCase("CNReserveMiami")|| screen.equalsIgnoreCase("SR1ReserveMiami")
						|| screen.equalsIgnoreCase("SR2ReserveMiami") 
						||screen.equalsIgnoreCase("BK1ActiveBoston")|| screen.equalsIgnoreCase("BK3ActiveBoston")|| screen.equalsIgnoreCase("CNActiveBoston")
						|| screen.equalsIgnoreCase("CPActiveBoston")|| screen.equalsIgnoreCase("CPActiveBoston")
						|| screen.equalsIgnoreCase("HZ1ActiveBoston")|| screen.equalsIgnoreCase("SR1ActiveBoston")
						|| screen.equalsIgnoreCase("SR2ActiveBoston")|| screen.equalsIgnoreCase("BK2ReserveBoston")
						|| screen.equalsIgnoreCase("CNReserveBoston")|| screen.equalsIgnoreCase("SR1ReserveBoston")
						|| screen.equalsIgnoreCase("SR2ReserveBoston")|| screen.equalsIgnoreCase("HZ1ActiveAtlanta")
						|| screen.equalsIgnoreCase("SR1ActiveAtlanta") || screen.equalsIgnoreCase("SR2ActiveAtlanta")
						|| screen.equalsIgnoreCase("SR1ReserveAtlanta") || screen.equalsIgnoreCase("SR2ReserveAtlanta")
						|| screen.equalsIgnoreCase("BK1ActiveAtlanta")|| screen.equalsIgnoreCase("BK3ActiveAtlanta")
						|| screen.equalsIgnoreCase("CNActiveAtlanta")|| screen.equalsIgnoreCase("CPActiveAtlanta")
						|| screen.equalsIgnoreCase("BK2ReserveAtlanta")|| screen.equalsIgnoreCase("CNReserveAtlanta")){ 

					Node itemName = document.getElementsByTagName("ItemName").item(0);

					String item = getSku(screen);
					itemName.setTextContent(item);
				}

				int nodelist1 = document.getElementsByTagName("FinalDestFacilityAliasId").getLength();
				for(int i = 0;i<nodelist1;i++){
					Node FinalDestFacilityAliasId = document.getElementsByTagName("FinalDestFacilityAliasId").item(i);  
					FinalDestFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);
				}

				Node OriginFacilityAliasId = document.getElementsByTagName("OriginFacilityAliasId").item(0);     
				if(OriginFacilityAliasId!=null){
					OriginFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);	
				}

				Node FinalDestFacilityAliasId = document.getElementsByTagName("FinalDestFacilityAliasId").item(0);     
				if(FinalDestFacilityAliasId!=null){
					FinalDestFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);	
				}

				Node ReferenceField2 = document.getElementsByTagName("ReferenceField2").item(0);     
				if(ReferenceField2!=null){
					ReferenceField2.setTextContent(DFWMSLoginPageObject.dc);	
				}

				int nodelist = document.getElementsByTagName("DestinationFacilityAliasId").getLength();
				for(int i = 0;i<nodelist;i++){
					Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(i);  
					DestinationFacilityAliasId.setTextContent(DFWMSLoginPageObject.dc);
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult;
			streamResult = new StreamResult(new File(path).getPath());
			transformer.transform(domSource, streamResult);
			System.out.println("The XML File was Updated Successfully");
			updateDocument(document, path);
		} 
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} 
		catch (TransformerException tfe){
			tfe.printStackTrace();
		} 
		catch (IOException ioe){
			ioe.printStackTrace();
		} 
		catch (SAXException sae){
			sae.printStackTrace();
		}
		report.addReportStepWithScreenshots("PO XML File Was Updated","PO XML File Was Updated with PurchaseOrder "+PO+Order,StepResult.PASS);
		System.out.println(PO+Order);
		return PO + Order;
	}

	private String getSku(String screen) throws IOException {
		String item = "";
		String excelFilePath = System.getProperty("user.dir")+"\\TestData\\Excel\\SKU_Data.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			boolean rowValue = false;
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String cellValue = cell.getStringCellValue();
					if(cellValue.equalsIgnoreCase(screen)){
						rowValue = true;
					}
					if(rowValue && cell.getColumnIndex() == 1){
						item = cell.getStringCellValue();
						item = item.replaceAll("[^a-zA-Z0-9]", " ");  
						System.out.print("Item :"+item);
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					NumberFormat formatter = new DecimalFormat("#");
					if(rowValue && cell.getColumnIndex() == 1){
						item = formatter.format(cell.getNumericCellValue());
						item = item.replaceAll("[^a-zA-Z0-9]", " ");  
						System.out.print("Item :"+item);
					}
					break;
				}
			}
		}
		inputStream.close();
		return item;
	}

	public String getGridLocnDetails(String screen) throws Exception {
		jd.dbDFWMSMapping();
		String dispLoc = "";
		if(screen.equalsIgnoreCase("UPS") || screen.equalsIgnoreCase("AgileOutbound") || screen.equalsIgnoreCase("OutboundConveyUPS")){
			dispLoc = "PCL-A05";
		}else if(screen.equalsIgnoreCase("LTL HDU") || screen.equalsIgnoreCase("OB BVR") 
				|| screen.equalsIgnoreCase("HDUTLOutbound") || screen.equalsIgnoreCase("OutboundConveyHDU") || screen.equalsIgnoreCase("LTLOutbound")) {
			dispLoc = "LTL-A06";
		}else if(screen.equalsIgnoreCase("ConPackBypass")) {
			dispLoc = "LAN-385";
		}
		gridLocnLTL = jd.array_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('"+dispLoc+"')");
		System.out.println("Gridlocn value for LTL " +gridLocnLTL);
		String gridLocn = gridLocnLTL.get(0);
		return gridLocn;
	}

	public List<Integer> applyItemandGetQty(String item) throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(2000);

		driver.switchTo().frame(0);
		if(wh.isElementPresent(Item, 3)){
			wh.sendKeys(Item, item);
			wh.clickElement(ItemApply);
			wh.waitForPageLoaded();
		}

		List<Integer> QtyVal = new ArrayList<Integer>();
		String Qty = "";

		if(wh.isElementPresent(OnHandQty, 3)){
			Qty = wh.getText(OnHandQty);
		}

		QtyVal.add(Integer.parseInt(Qty));

		return QtyVal;
	}

	public String createDOXml(String screen) throws Exception {
		String path = "";
		if(screen.equalsIgnoreCase("BK1PalletJack") 
				|| screen.equalsIgnoreCase("BK3NonShipReady")
				|| screen.equalsIgnoreCase("CartPickCon")
				|| screen.equalsIgnoreCase("CartPickConBaltimore")
				|| screen.equalsIgnoreCase("CartPickConNewark")
				|| screen.equalsIgnoreCase("HazmatFlow")
				|| screen.equalsIgnoreCase("BK1NonShipReady")
				|| screen.equalsIgnoreCase("BK1NonShipReadyLM")
				|| screen.equalsIgnoreCase("WaveReplenishment")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore")
				|| screen.equalsIgnoreCase("BK1NonShipReadyNewark")
				|| screen.equalsIgnoreCase("BK3NonShipReadyBaltimore")
				|| screen.equalsIgnoreCase("BK1NonShipReady_UndoDO")
				|| screen.equalsIgnoreCase("BK3NonShipReadyNewark")
				|| screen.equalsIgnoreCase("BK1NonShipReadyMiami")
				|| screen.equalsIgnoreCase("BK3NonShipReadyMiami")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBoston")
				|| screen.equalsIgnoreCase("BK3NonShipReadyBoston")){
			path =  FilePathOutbound2019;
		}else if(screen.equalsIgnoreCase("VAS")){
			path =  FilePathValueAddedService;
		}else if(screen.equalsIgnoreCase("BVR_LG")){
			path =  FilePathOutbound2012_BVR;
		}else if(screen.equalsIgnoreCase("PAX")){
			path =  FilePathOutbound_PAX;
		}else if(screen.equalsIgnoreCase("SCP")){
			path =  FilePathOutbound_SCP;
		}else if(screen.equalsIgnoreCase("MCP")){
			path =  FilePathOutbound_MCP;
		}else if(screen.equalsIgnoreCase("LG_New")){
			path =  FilePathOutbound2012;
		}else if(screen.equalsIgnoreCase("BK1NonShipReadyDallas") 
				|| screen.equalsIgnoreCase("BK1PalletJackDallas")
				|| screen.equalsIgnoreCase("HazmatFlowDallas")
				|| screen.equalsIgnoreCase("HazmatFlowBaltimore")
				|| screen.equalsIgnoreCase("HazmatFlowNewark")
				|| screen.equalsIgnoreCase("HazmatFlowLacey")
				|| screen.equalsIgnoreCase("WaveReplenishmentDallas")
				|| screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyLacey")
				|| screen.equalsIgnoreCase("BK1PalletJackLacey")
				|| screen.equalsIgnoreCase("BK1NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK3NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK3NonShipReadyTracey")
				|| screen.equalsIgnoreCase("CartPickConTampa")
				|| screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyTracey")
				|| screen.equalsIgnoreCase("CartPickConTracey")
				|| screen.equalsIgnoreCase("BK1NonShipReadyAtlanta")
				||screen.equalsIgnoreCase("CartPickConAtlanta")){
			path = FilePathOutbound2019Dallas;
		}else if(screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRNewark_UndoDO_IP")){
			path =  FilePathDallasUndoDOInPacking;
		}else if(screen.equalsIgnoreCase("DallasVAS")){
			path =  FilePathValueAddedServiceDallas;
		}else if(screen.equalsIgnoreCase("BVR_Undo")){

			path =  FilePathUndoBVRDallas;

		}else if(screen.equalsIgnoreCase("BVR_Dallas")
				|| screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				|| screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Newark_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO_Packed")
				|| screen.equalsIgnoreCase("BVR_Newark_UndoDO_Packed")
				){
			path = FilePathOutboundBVRDallas;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MutliStop")) {
			path =FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_MutliStop")){
			path = FilePathOutboundMDODallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Dallas")
				|| screen.equalsIgnoreCase("HDUTL_Baltimore")
				|| screen.equalsIgnoreCase("HDUTL_Lacey")
				|| screen.equalsIgnoreCase("HDUTL_Newark")
				|| screen.equalsIgnoreCase("HDUTL_Tracey")
				|| screen.equalsIgnoreCase("HDUTL_Atlanta")){
			path = FilePathHDUTLDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Houston")){
			path = FilePathHDUTLHouston;
		}else if(screen.equalsIgnoreCase("LTLOutboundHouston")){
			path =  FilePathOutboundHouston;
		}else if(screen.equalsIgnoreCase("LTLOutboundDallas")
				|| screen.equalsIgnoreCase("LTLOutboundBaltimore")
				|| screen.equalsIgnoreCase("LTLOutboundLacey")
				|| screen.equalsIgnoreCase("LTLOBLacey_UndoDO1")
				|| screen.equalsIgnoreCase("LTL_Lacey")
				|| screen.equalsIgnoreCase("LTLOutboundTampa")
				|| screen.equalsIgnoreCase("LTLOutboundNewark")
				|| screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundTracey_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundTracey")
				|| screen.equalsIgnoreCase("LTLOutboundAtlanta_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundAtlanta")
				|| screen.equalsIgnoreCase("LTLOutboundMiami")
				|| screen.equalsIgnoreCase("LTLOutboundBoston")){
			path =  FilePathOutboundDallas;
		}else if(screen.equalsIgnoreCase("SplitShipmentDallas")) {
			path = FilePathOutBoundSplitShipment;
		}else if(screen.equalsIgnoreCase("LTL HDU Dallas")
				|| screen.equalsIgnoreCase("LTL HDU Lacey")
				|| screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2")
				|| screen.equalsIgnoreCase("LTL_HDU_Tracey")
				|| screen.equalsIgnoreCase("LTL_HDU_Atlanta")
				|| screen.equalsIgnoreCase("LTL_HDU_Miami")
				|| screen.equalsIgnoreCase("LTL_HDU_Boston")){
			path =  FilePathLTLHDUOutboundDallas;
		}else if(screen.equalsIgnoreCase("LTL HDU Houston")) {
			path =FilePathLTLHDUOutboundHouston;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_Wyn")){
			path =  FilePathUPSOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_WES")){
			path =  FilePathWESOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas")
				|| screen.equalsIgnoreCase("UPS_Dallas_WM09")
				|| screen.equalsIgnoreCase("UPS_Lacey")){
			path =  FilePathUPSOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_MISP")){
			path =  FilePathUPSOutboundDallasMISP;
		}
		else if(screen.equalsIgnoreCase("UPS_Houston_MISP")){
			path =  FilePathUPSOutboundHoustonMISP;
		}
		else if(screen.equalsIgnoreCase("FGND_Dallas")
				|| screen.equalsIgnoreCase("FGND_Baltimore")
				|| screen.equalsIgnoreCase("FGND_Newark")
				|| screen.equalsIgnoreCase("FGND_Lacey")
				||screen.equalsIgnoreCase("FGND_Dallas_WM09")
				|| screen.equalsIgnoreCase("FGND_Tampa")
				|| screen.equalsIgnoreCase("FGND_Tracey")
				|| screen.equalsIgnoreCase("FGND_Atlanta")
				|| screen.equalsIgnoreCase("FGND_Miami")
				|| screen.equalsIgnoreCase("FGND_Boston")
				){
			path =  FilePathFGNDOutboundDallas;
		}else if(screen.equalsIgnoreCase("FGND_Houston")){
			path =  FilePathOutboundFGNDHouston;
		}else if(screen.equalsIgnoreCase("FXHD_Dallas_Undo")) {

			path =  FilePathFXHDUndoDallas;

		}

		else if(screen.equalsIgnoreCase("FXHD_Dallas")
				|| screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				|| screen.equalsIgnoreCase("FXHD_Baltimore")
				|| screen.equalsIgnoreCase("FXHD_Lacey")
				|| screen.equalsIgnoreCase("FXHD_Tampa")
				|| screen.equalsIgnoreCase("FXHD_Newark")
				|| screen.equalsIgnoreCase("FXHD_Tracey")
				|| screen.equalsIgnoreCase("FXHD_Atlanta")
				|| screen.equalsIgnoreCase("FXHD_Miami")
				||screen.equalsIgnoreCase("FXHD_Boston")){
			path =  FilePathFXHDOutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")){
			path =  FilePathBVRShotGunOutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_MIMP")
				||screen.equalsIgnoreCase("BVR_Baltimore_MIMP")
				|| screen.equalsIgnoreCase("BVR_Dallas_MISP") ){
			path =  FilePathBVROutboundDallasMIMP;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_OLPN")
				|| screen.equalsIgnoreCase("BVR_MutliStop")
				|| screen.equalsIgnoreCase("BVR_Dallas_Yard")
				|| screen.equalsIgnoreCase("BVR_Dallas_OLPN_MS")){
			path =  FilePathBVROutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_MutliStop_Houston")|| screen.equalsIgnoreCase("BVR_Houston_Yard")) {
			
			path =  FilePathBVROutboundHouston;
		}else if(screen.equalsIgnoreCase("MDO_MutliStop")){
			
			path = FilePathOutbound2019;
		}else if(screen.equalsIgnoreCase("Envelop_Dallas") 
				|| screen.equalsIgnoreCase("EnvelopMultiItem_Dallas")){
			path =  FilePathEnvelopOutboundDallas;
		}else if(screen.equalsIgnoreCase("Envelop_Baltimore")
				|| screen.equalsIgnoreCase("EnvelopMultiItem_Baltimore")){
			path =  FilePathEnvelopOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("EnvelopMulti_Dallas")){
			path =  FilePathEnvelopMultiOutboundDallas;
		}else if(screen.equalsIgnoreCase("EnvelopMulti_Baltimore")){
			path =  FilePathEnvelopMultiOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote1_Dallas")){
			path =  FilePathEnvelopeSingleTote1Dallas;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote1_Baltimore")){
			path =  FilePathEnvelopeSingleTote1Baltimore;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote2_Dallas")){
			path =  FilePathEnvelopeSingleTote2Dallas;
		}else if(screen.equalsIgnoreCase("Envelop_SingleTote2_Baltimore")){
			path =  FilePathEnvelopeSingleTote2Baltimore;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")){
			path =  FilePathBVRDallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")){
			path =  FilePathMDODallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")){
			path =  FilePathMDODallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")){
			path =  FilePathBVRDallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")){
			path =  FilePathBVRHoustonSplitShipment1;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")){
			path =  FilePathBVRHoustonSplitShipment2;
		}else if(screen.equalsIgnoreCase("UPS_Houston_Wyn")){
			path =  FilePathUPSHouston;
		}else if(screen.equalsIgnoreCase("UPS_Houston")
				|| screen.equalsIgnoreCase("UPS_Baltimore")
				|| screen.equalsIgnoreCase("UPS_Newark")){
			path =  FilePathUPSHouston;
		}else if(screen.equalsIgnoreCase("EZShipment_Houston")
				|| screen.equalsIgnoreCase("EZShipment_Baltimore")
				|| screen.equalsIgnoreCase("EZShipment_Newark")
				|| screen.equalsIgnoreCase("EZShipment_Tracey")
				|| screen.equalsIgnoreCase("EZShipment_Miami")
				|| screen.equalsIgnoreCase("EZShipment_Boston")){
			path =  FilePathEZShipmentHouston;
		}else if(screen.equalsIgnoreCase("LTL_HDU_Baltimore")
				|| screen.equalsIgnoreCase("LTL_HDU_Newark")){
			path =  FilePathLTLHDUOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("BVR_Lacey")
				|| screen.equalsIgnoreCase("BVR_Lacey_Yard")){
			path =  FilePathBVRLacey;
		}else if(screen.equalsIgnoreCase("BVR_Lacey_MISP")){
			path =  FilePathBVRLaceyMultiItem;
		}else if(screen.equalsIgnoreCase("VAS_Lacey")){
			path =  FilePathVASLacey;
		}else if(screen.equalsIgnoreCase("VAS_Baltimore")
				|| screen.equalsIgnoreCase("VAS_Newark")){
			path =  FilePathVASBaltimore;
		}else if(screen.equalsIgnoreCase("MultiShipmentLacey")){
			path =  FilePathLTLMILacey;
		}else if(screen.equalsIgnoreCase("UPS_Baltimore_MISP")){
			path =  FilePathUPSOutboundBaltimoreMISP;
		}else if(screen.equalsIgnoreCase("UPS_Lacey_MISP")){
			path =  FilePathUPSOutboundLaceyMISP;
		}else if(screen.equalsIgnoreCase("LTLMultiStopLacey")){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("BVR_Houston")
				|| screen.equalsIgnoreCase("LoadTrailor_NonParcel")
				||screen.equalsIgnoreCase("BVR_Houston_Cancel_olpn")
				||screen.equalsIgnoreCase("BVR_Houston_UndoDO")
				||screen.equalsIgnoreCase("BVR_Houston_InPacking")){
			path =  FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("BK1NonShipReady_MultiShipment")){
			path =  FilePathOutboundMDOMIHouston;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MISP")){
			path =  FilePathOutboundBVRMIHouston;
		}else if(screen.equalsIgnoreCase("LTL_Houston_MISP")){
			path =  FilePathOutboundLTLMIHouston;
		}
//		else if(screen.equalsIgnoreCase("UPS_Houston_MISP")){
//			path =  FilePathOutboundUPSMIHouston;
//		}
		else if(screen.equalsIgnoreCase("LTL_Lacey_SplitShipment1")){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("LTL_Lacey_SplitShipment2")){
			path =  FilePathLTL_SplitShipment_Lacey;
		}else if(screen.equalsIgnoreCase("FXHD_Houston")){
			path =  FilePathFXHD_Houston;
		}else if(screen.equalsIgnoreCase("Split_Combine")){
			path =  FilePathSplitAndCombine_Houston;
		}else if(screen.equalsIgnoreCase("LTL_HDU_Tampa")){
			path =  FilePathLTLHDU_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tampa")
				|| screen.equalsIgnoreCase("UPS_Tracey")
				|| screen.equalsIgnoreCase("UPS_Atlanta")
				|| screen.equalsIgnoreCase("UPS_Miami")
				|| screen.equalsIgnoreCase("UPS_Boston")){
			path =  FilePathUPS_Tampa;
		}else if(screen.equalsIgnoreCase("VAS_Tampa")){
			path =  FilePathVAS_Tampa;
		}else if(screen.equalsIgnoreCase("EZShipment_Tampa")){
			path =  FilePathEZShipment_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tampa_MISP")){
			path =  FilePathUPSMI_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tracey_MISP")
				||screen.equalsIgnoreCase("UPS_Atlanta_MISP")){
			path =  FilePathUPSMI_Tracey;
		}else if(screen.equalsIgnoreCase("UPS_Newark_MISP")){
			path =  FilePathUPSMI_Newark;
		}else if(screen.equalsIgnoreCase("UPS_Miami_MISP")||screen.equalsIgnoreCase("UPS_Boston_MISP")){
			path =  FilePathUPSMI_Miami;
		}else if(screen.equalsIgnoreCase("EZShipment_Atlanta")){
			path =  FilePathEZShipmentAtlanta;
		}

		String DO = "QA";
		//String DOOrderId;
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String OD = simpleDateFormat.format(new Date());
		String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
		String RefOrderID = DO + Order + " " + Order +" " + OD + " " + "00:00:00";
		DOOrderId = Order+"R";
		Thread.sleep(1000);
		System.out.println("DO Order ID"+" "+DOOrderId); // order ido

		final Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		simpleDateFormat.format(calendar.getTime());
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		System.out.println(DeliveryDate);
		Date dt = new Date(0);
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("kk:mm");
		String date = dateFormat.format(dt);
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(path);

			//DC

			//DC
//			String dc = "";
//			jd.dbDFWMSMapping();
//
//			if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")){
//				dc = "5829";
//			}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")){
//				dc = "5832";
//			}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")){
//				dc = "5823";
//			}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")){
//				dc = "5831";
//			}else if(jd.envrnment.equalsIgnoreCase("HoustonQP_2019")){
//				dc = "5831";
//			}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")){
//				dc = "5855";
//			}else if(jd.envrnment.equalsIgnoreCase("Newark_2019")){
//				dc = "5854";
//			}else if(jd.envrnment.equalsIgnoreCase("Tracey_2019")){
//				dc = "5857";
//			}else if(jd.envrnment.equalsIgnoreCase("Miami_2019")){
//				dc = "5841";
//			}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")){
//				dc = "5860";
//			}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")){
//				dc = "5882";
//			}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
//				dc = "5523";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2012")){
//				dc = "6777";
//			}else if(jd.envrnment.equalsIgnoreCase("LG_2019")){
//				dc = "6705";
//			}
			
			String tagVal = "";
			jd.dbDFWMSMapping();

			if(screen.equalsIgnoreCase("FGND_Dallas")||screen.equalsIgnoreCase("FXHD_Dallas")||screen.equalsIgnoreCase("FGND_Houston")
					||screen.equalsIgnoreCase("FXHD_Houston")||screen.equalsIgnoreCase("FXHD_Dallas_WM09")
					||screen.equalsIgnoreCase("UPS_Dallas_WM09")||screen.equalsIgnoreCase("UPS_Dallas")||screen.equalsIgnoreCase("Split_Combine")) {
				 tagVal="";
			}
			else {
				
				tagVal= "MDO"+DFWMSLoginPageObject.dc;
				
				}

			


			//xmlpath
			Node refId = document.getElementsByTagName("Reference_ID").item(0);                   
			refId.setTextContent(RefOrderID);

			Node orderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
			orderId.setTextContent(DOOrderId);
			//orderId = orderId;
			
			Node orderDate = document.getElementsByTagName("OrderedDttm").item(0);
			orderDate.setTextContent(CurrentDate);

			Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
			pickupStartDttmDate.setTextContent(CurrentDate);

			Node DC = document.getElementsByTagName("OriginFacilityAliasId").item(0);                   
			DC.setTextContent(DFWMSLoginPageObject.dc);

			Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
			deliveryStartDate.setTextContent(CurrentDate);

			
			Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
			pickupEndDttmDate.setTextContent(DeliveryDate+" "+date);

			SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
			final Calendar DeliveryDateCalendar = Calendar.getInstance();

//			 only for UPS flow
			DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 1);

			Node deliveryStartDate1 = document.getElementsByTagName("DeliveryStartDttm").item(0);
			deliveryStartDate1.setTextContent(DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00");

//			Node deliveryStartDate1 = document.getElementsByTagName("DeliveryStartDttm").item(0);
//			deliveryStartDate1.setTextContent(DeliveryDate);

			//final Calendar DeliveryDateCalendar = Calendar.getInstance();

			if(screen.contains("UPS")){

				Date obDate = new Date();
				System.out.println("Day of the week" + obDate.getDay());


				if(obDate.getDay() == 4){

					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";
				}else{

					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 4);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";	
				}

			}else{

				DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 5);
				DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";


			}

			Node mustReleaseDate = document.getElementsByTagName("MustReleaseByDttm").item(0);
			mustReleaseDate.setTextContent(DeliveryDate);


			Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
			deliveryEndDate.setTextContent(DeliveryDate);

			Node schedDlvryEndDate = document.getElementsByTagName("SchedDlvryEndDate").item(0);
			schedDlvryEndDate.setTextContent(DeliveryDate);

			

			if( screen.equalsIgnoreCase("LTLOutboundBaltimore") || screen.equalsIgnoreCase("SplitShipmentDallas")
					|| screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("LTLOBLacey_UndoDO1")
					|| screen.equalsIgnoreCase("LTLOutboundTampa") || screen.equalsIgnoreCase("LTLOutboundNewark")
					|| screen.equalsIgnoreCase("LTL_Lacey") || screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")
					|| screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("SplitShipmentLacey")
					|| screen.equalsIgnoreCase("HDUTL_Baltimore") || screen.equalsIgnoreCase("HDUTL_Lacey")
					|| screen.equalsIgnoreCase("HDUTL_Newark") || screen.equalsIgnoreCase("LTLOutboundLacey")
					|| screen.equalsIgnoreCase("LTLOutboundTracey_UndoDO")){

				String lpnTypeVal = "";
				String lpnLabelTypeVal = "";
				if(screen.equalsIgnoreCase("LTLOutboundBaltimore") || screen.equalsIgnoreCase("SplitShipmentLacey")
						|| screen.equalsIgnoreCase("LTLOutboundLacey") /*|| screen.equalsIgnoreCase("LTLOutboundHouston")*/
						|| screen.equalsIgnoreCase("LTLOBLacey_UndoDO1") || screen.equalsIgnoreCase("LTLOutboundTampa")
						|| screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO") || screen.equalsIgnoreCase("LTLOutboundTracey_UndoDO")){
					lpnTypeVal = "PLT";
				}else{ 
					lpnTypeVal = "NBX";
				}

				if(screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")){
					lpnLabelTypeVal = "TLL";
				}else if(screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("SplitShipmentLacey")){
					lpnLabelTypeVal = "LTL";
				}else if(screen.equalsIgnoreCase("SplitShipmentDallas")||screen.equalsIgnoreCase("LTLOutboundDallas")) {
					lpnLabelTypeVal = "PCL";
				}
				else{ 
					lpnLabelTypeVal = "";
				}

				Node LpnType = document.getElementsByTagName("LpnType").item(0);
				LpnType.setTextContent(lpnTypeVal); 

				Node LpnLabelType = document.getElementsByTagName("LpnLabelType").item(0);
				LpnLabelType.setTextContent(lpnLabelTypeVal); 
			}else if(screen.equalsIgnoreCase("BVR_Dallas_OLPN_MS") || screen.equalsIgnoreCase("BVR_Baltimore_UndoDO") 
					|| screen.equalsIgnoreCase("BVR_Newark_UndoDO") || screen.equalsIgnoreCase("BVR_Houston_Cancel_olpn")
					 ||screen.equalsIgnoreCase("BVR_Houston_UndoDO") 
					|| screen.equalsIgnoreCase("BVR_Dallas_OLPN") || screen.equalsIgnoreCase("BVR_MutliStop")||screen.equalsIgnoreCase("MDO_MutliStop")
					|| screen.equalsIgnoreCase("BVR_Houston_InPacking") || screen.equalsIgnoreCase("LTLOutboundNewark")
					|| screen.equalsIgnoreCase("BVR_Dallas_Yard") || screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
					|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO_Packed") || screen.equalsIgnoreCase("BVR_Newark_UndoDO_Packed")
					|| screen.equalsIgnoreCase("BVR_Houston") || screen.equalsIgnoreCase("BVR_Houston_Yard")
					|| screen.equalsIgnoreCase("LoadTrailor_NonParcel")
					|| screen.equalsIgnoreCase("BVR_Lacey_Yard") || screen.equalsIgnoreCase("LTLOutboundLacey")
					|| screen.equalsIgnoreCase("BVR_MutliStop_Houston")){

				String qtyVal = "";
				if(screen.equalsIgnoreCase("BVR_Baltimore_UndoDO") || screen.equalsIgnoreCase("BVR_Houston_InPacking")
						|| screen.equalsIgnoreCase("BVR_Newark_UndoDO") || screen.equalsIgnoreCase("BVR_Houston_Cancel_olpn")
						||screen.equalsIgnoreCase("BVR_Houston_UndoDO")){
					qtyVal = "4";
				}else if(screen.equalsIgnoreCase("BVR_Dallas_OLPN") 
						|| screen.equalsIgnoreCase("BVR_MutliStop")||screen.equalsIgnoreCase("MDO_MutliStop")
						|| screen.equalsIgnoreCase("BVR_Dallas_OLPN_MS")
						|| screen.equalsIgnoreCase("BVR_MutliStop_Houston")){
					qtyVal = "2";
				}else if(screen.equalsIgnoreCase("BVR_Dallas_Yard") || screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
						|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO_Packed") || screen.equalsIgnoreCase("BVR_Newark_UndoDO_Packed")
						|| screen.equalsIgnoreCase("BVR_Houston")|| screen.equalsIgnoreCase("BVR_Houston_Yard")
						|| screen.equalsIgnoreCase("BVR_Lacey_Yard") || screen.equalsIgnoreCase("LTLOutboundLacey")
						|| screen.equalsIgnoreCase("LTLOutboundNewark") || screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
						|| screen.equalsIgnoreCase("MDO_Dallas_MutliStop")||screen.equalsIgnoreCase("BVR_Houston_MutliStop")){
					qtyVal = "1";
				}else if(screen.equalsIgnoreCase("LoadTrailor_NonParcel")){
					
					qtyVal = "2";
				}
				Node qty = document.getElementsByTagName("OrderQty").item(0);
				qty.setTextContent(qtyVal);
			}else if(screen.equalsIgnoreCase("BK1PalletJack") || screen.equalsIgnoreCase("BK3NonShipReady")
					|| screen.equalsIgnoreCase("CartPickCon") || screen.equalsIgnoreCase("CartPickConBaltimore")
					|| screen.equalsIgnoreCase("CartPickConNewark") || screen.equalsIgnoreCase("HazmatFlow")
					|| screen.equalsIgnoreCase("BK1NonShipReady") || screen.equalsIgnoreCase("LG_New")
					|| screen.equalsIgnoreCase("WaveReplenishment")|| screen.equalsIgnoreCase("BVR_LG")
					|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore") || screen.equalsIgnoreCase("BK1NonShipReadyNewark")
					|| screen.equalsIgnoreCase("BK3NonShipReadyBaltimore") || screen.equalsIgnoreCase("BK1NonShipReady_UndoDO")
					|| screen.equalsIgnoreCase("FGND_Dallas") || screen.equalsIgnoreCase("FGND_Baltimore")
					|| screen.equalsIgnoreCase("FGND_Newark") || screen.equalsIgnoreCase("FGND_Lacey")
					|| screen.equalsIgnoreCase("FGND_Houston") || screen.equalsIgnoreCase("FGND_Tampa")
					|| screen.equalsIgnoreCase("FXHD_Baltimore") || screen.equalsIgnoreCase("FXHD_Lacey")  
					|| screen.equalsIgnoreCase("FXHD_Tampa") || screen.equalsIgnoreCase("FXHD_Newark") 
					|| screen.equalsIgnoreCase("FXHD_Dallas") || screen.equalsIgnoreCase("FXHD_Dallas_Undo")
					|| screen.equalsIgnoreCase("FXHD_Dallas_WM09")|| screen.equalsIgnoreCase("UPS_Newark")
					|| screen.equalsIgnoreCase("UPS_Baltimore") || screen.equalsIgnoreCase("UPS_Lacey")
					|| screen.contains("EZShipment") || screen.equalsIgnoreCase("BK1NonShipReadyLacey")
					|| screen.equalsIgnoreCase("BK1NonShipReadyDallas") || screen.equalsIgnoreCase("BK1PalletJackDallas")
					|| screen.equalsIgnoreCase("HazmatFlowDallas") || screen.equalsIgnoreCase("HazmatFlowBaltimore")
					|| screen.equalsIgnoreCase("HazmatFlowNewark") || screen.equalsIgnoreCase("HazmatFlowLacey")
					|| screen.equalsIgnoreCase("WaveReplenishmentDallas") || screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
					|| screen.equalsIgnoreCase("BK1NonShipReadyLacey") || screen.equalsIgnoreCase("BK1PalletJackLacey")
					|| screen.equalsIgnoreCase("BK1NonShipReadyTampa") || screen.equalsIgnoreCase("BK3NonShipReadyTampa")
					|| screen.equalsIgnoreCase("CartPickConTampa") || screen.equalsIgnoreCase("BK1NSRTampa_UndoDO")
					|| screen.equalsIgnoreCase("UPS_Dallas") || screen.equalsIgnoreCase("UPS_Dallas_Wyn")
					||screen.equalsIgnoreCase("UPS_Dallas_WM09")|| screen.equalsIgnoreCase("UPS_Dallas_WES")
					|| screen.equalsIgnoreCase("UPS_Lacey")	||screen.equalsIgnoreCase("FGND_Dallas_WM09")
					|| screen.equalsIgnoreCase("BK1NonShipReadyNewark") || screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
					|| screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")
					|| screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("SplitShipmentLacey")
					|| screen.equalsIgnoreCase("LTL HDU Dallas") 
					|| screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2") || screen.equalsIgnoreCase("HDUTL_Baltimore") 
					|| screen.equalsIgnoreCase("LTL_HDU_Baltimore") || screen.equalsIgnoreCase("BK1NonShipReadyTracey")
					|| screen.equalsIgnoreCase("CartPickConTracey")|| screen.equalsIgnoreCase("LTL_HDU_Tracey") 
					|| screen.equalsIgnoreCase("BK3NonShipReadyTracey")|| screen.equalsIgnoreCase("FXHD_Tracey")
					|| screen.equalsIgnoreCase("FGND_Tracey")|| screen.equalsIgnoreCase("BK1NonShipReadyAtlanta")
					|| screen.equalsIgnoreCase("CartPickConAtlanta")|| screen.equalsIgnoreCase("BK3NonShipReadyAtlanta")){

				//FederatedStoreNbr 0401

				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);


				//DestinationFacilityAliasId 
				if(screen.equalsIgnoreCase("BK1NonShipReady")||screen.equalsIgnoreCase("BK1PalletJack")) {
					
//					String RoutTo = document.getElementsByTagName("RouteTo").item(0).getFirstChild().getNodeValue();
//
//					System.out.println("RoutTo value which is present in xmldata: "+RoutTo);
//					
//					List<String> lanesfromDB = new ArrayList<>(); 
//
//					 lanesfromDB = jd.array_Database_Connection(
//							" select distinct  LV.ROUTE_TYPE_1 as ship_via from LANE_VIEW LV, LANE_DETAIL_VIEW LDV,CARRIER_CODE CC,SERVICE_LEVEL SL "
//							+ "where LV.Lane_id = LDV.Lane_ID and "
//							+ "LDV.Carrier_ID = CC.Carrier_ID and "
//							+ "LDV.SERVICE_LEVEL_ID = SL.Service_Level_ID and "
//							+ "LV.Route_to in ('"+RoutTo+"') order by ship_via desc");
//					
//					
//					if(RoutTo.equalsIgnoreCase("2")) {
//						
//						RouteType1.setTextContent(lanesfromDB.get(0));
//						
//
//					}else if(RoutTo.equalsIgnoreCase("3")) {
//						
//						RouteType1.setTextContent(lanesfromDB.get(0));
//						
//					}else if(RoutTo.equalsIgnoreCase("22")) {
//						
//						RouteType1.setTextContent(lanesfromDB.get(0));
//						
//					}else if(RoutTo.equalsIgnoreCase("18")) {
//						
//						RouteType1.setTextContent(lanesfromDB.get(0));
//
//					}
				
				if(jd.envrnment.equalsIgnoreCase("LG_2019")){
					
					//RouteType1 
					RouteType1.setTextContent("60000GA005");
					
					DestinationFacilityAliasId.setTextContent("60000GA005");
					
					
					FederatedStoreNbr.setTextContent("60000GA005");

				}else if(jd.envrnment.equalsIgnoreCase("Perris_2019")){
					
					//RouteType1 
					RouteType1.setTextContent("MDO5841");
					
					DestinationFacilityAliasId.setTextContent("MDO5841");
					
					
					FederatedStoreNbr.setTextContent("MDO5841");

				}else if(jd.envrnment.equalsIgnoreCase("Columbus_2019")){
					
					//RouteType1 
					RouteType1.setTextContent("MDO5523");
					
					DestinationFacilityAliasId.setTextContent("MDO5523");
					
					
					FederatedStoreNbr.setTextContent("MDO5523");

				}else if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")){
					
					//RouteType1 
					RouteType1.setTextContent("MDO5523");
					
					DestinationFacilityAliasId.setTextContent("MDO5523");
					
					
					FederatedStoreNbr.setTextContent("MDO5523");

				}else if(jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
					
					//nee to change route to value 3
					//RouteType1 
					RouteType1.setTextContent("60000TX051");
					
					DestinationFacilityAliasId.setTextContent("6777");
					
					
					FederatedStoreNbr.setTextContent("6777");

				}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
					
					//RouteType1 
					RouteType1.setTextContent("MDO5869");
					
					DestinationFacilityAliasId.setTextContent("6760");
					
					
					FederatedStoreNbr.setTextContent("6760");

				}


				}else {
					
					if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
						//RouteType1 
						RouteType1.setTextContent("5250");
						
						DestinationFacilityAliasId.setTextContent("6777");
						
						
						FederatedStoreNbr.setTextContent("6777");

					}else if(jd.envrnment.equalsIgnoreCase("HGT_2019")){
						//RouteType1 
						if(screen.equalsIgnoreCase("LTL HDU Dallas")) {
						RouteType1.setTextContent("60000MS005");
						
						DestinationFacilityAliasId.setTextContent("6760");
						
						
						FederatedStoreNbr.setTextContent("6760");
						}else if(screen.equalsIgnoreCase("BK1NonShipReadyDallas")||screen.equalsIgnoreCase("BK1PalletJackDallas")) {
							
							RouteType1.setTextContent("MDO5869");
							
							DestinationFacilityAliasId.setTextContent("6760");
							
							
							FederatedStoreNbr.setTextContent("6760");
							}else {
							RouteType1.setTextContent(tagVal.trim());

							DestinationFacilityAliasId.setTextContent(tagVal.trim());
							//FederatedStoreNbr 0401
							FederatedStoreNbr.setTextContent(tagVal.trim());

						}
					}else {
					//RouteType1 
					
					RouteType1.setTextContent(tagVal.trim());

					DestinationFacilityAliasId.setTextContent(tagVal.trim());
					//FederatedStoreNbr 0401
					FederatedStoreNbr.setTextContent(tagVal.trim());

					}
				}



				String routeType = "";
				if(screen.equalsIgnoreCase("BVR_ShotGun_Lacey")){
					//RouteType1 5643
					tagVal = "5643";
					//FederatedStoreNbr 0401
					FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
					FederatedStoreNbr.setTextContent("0401");
				}else if(screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")
						|| screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("SplitShipmentLacey")
						||screen.equalsIgnoreCase("LTLOutboundTracey")|| screen.equalsIgnoreCase("HDUTL_Tracey")
						|| screen.equalsIgnoreCase("LTL_HDU_Tracey")||screen.equalsIgnoreCase("LTLOutboundAtlanta")
						|| screen.equalsIgnoreCase("HDUTL_Atlanta")){
					if(screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")
							||screen.equalsIgnoreCase("LTLOutboundTracey")||screen.equalsIgnoreCase("LTLOutboundMiami")
							||screen.equalsIgnoreCase("LTLOutboundAtlanta")||screen.equalsIgnoreCase("LTLOutboundBoston")){
						routeType = "AACT";
					}else if(screen.equalsIgnoreCase("LTL HDU Dallas")){
						routeType = "60000TX010";
					}else if(screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2") 
							||screen.equalsIgnoreCase("LTL HDU Lacey")){
						routeType = "60000PA014";
					}else if(screen.equalsIgnoreCase("HDUTL_Baltimore") || screen.equalsIgnoreCase("LTL_HDU_Baltimore")
							|| screen.equalsIgnoreCase("HDUTL_Tracey")|| screen.equalsIgnoreCase("LTL_HDU_Tracey")
							|| screen.equalsIgnoreCase("HDUTL_Atlanta")|| screen.equalsIgnoreCase("LTL_HDU_Atlanta")
							|| screen.equalsIgnoreCase("LTL_HDU_Miami")||screen.equalsIgnoreCase("LTL_HDU_Boston")){
						routeType = "60000TX013";
					}else if(screen.equalsIgnoreCase("LTL_HDU_Newark")){
						routeType = "60000TX012";
					}else{
						routeType = "03AM";
					}
					RouteType1 = document.getElementsByTagName("RouteType1").item(0);
					RouteType1.setTextContent(routeType);
				}else if(screen.equalsIgnoreCase("HDUTL_Newark")){
					String destFacilityAliasID = "5854";
					DestinationFacilityAliasId.setTextContent(destFacilityAliasID);
				}
			}
			else if(screen.equalsIgnoreCase("BK1NonShipReadyLM")) {
				//RouteType1 
				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
				RouteType1.setTextContent(tagVal);
				//DestinationFacilityAliasId 
				Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
				DestinationFacilityAliasId.setTextContent(tagVal);
				//FederatedStoreNbr 0401
				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
				FederatedStoreNbr.setTextContent(tagVal);

				String routeType = "";
				if(screen.equalsIgnoreCase("BVR_ShotGun_Lacey")){
					//RouteType1 5643
					tagVal = "5643";
					//FederatedStoreNbr 0401
					FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
					FederatedStoreNbr.setTextContent("0401");
				}else if(screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")
						|| screen.equalsIgnoreCase("LTLOutboundLacey") || screen.equalsIgnoreCase("SplitShipmentLacey")
						||screen.equalsIgnoreCase("LTLOutboundTracey")|| screen.equalsIgnoreCase("HDUTL_Tracey")
						|| screen.equalsIgnoreCase("LTL_HDU_Tracey")||screen.equalsIgnoreCase("LTLOutboundAtlanta")
						|| screen.equalsIgnoreCase("HDUTL_Atlanta")){
					if(screen.equalsIgnoreCase("LTLOutboundBaltimore")|| screen.equalsIgnoreCase("LTLOutboundNewark")
							||screen.equalsIgnoreCase("LTLOutboundTracey")||screen.equalsIgnoreCase("LTLOutboundMiami")
							||screen.equalsIgnoreCase("LTLOutboundAtlanta")||screen.equalsIgnoreCase("LTLOutboundBoston")){
						routeType = "AACT";
					}else if(screen.equalsIgnoreCase("LTL HDU Dallas")){
						routeType = "60000TX010";
					}else if(screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2") 
							||screen.equalsIgnoreCase("LTL HDU Lacey")){
						routeType = "60000PA014";
					}else if(screen.equalsIgnoreCase("HDUTL_Baltimore") || screen.equalsIgnoreCase("LTL_HDU_Baltimore")
							|| screen.equalsIgnoreCase("HDUTL_Tracey")|| screen.equalsIgnoreCase("LTL_HDU_Tracey")
							|| screen.equalsIgnoreCase("HDUTL_Atlanta")|| screen.equalsIgnoreCase("LTL_HDU_Atlanta")
							|| screen.equalsIgnoreCase("LTL_HDU_Miami")||screen.equalsIgnoreCase("LTL_HDU_Boston")){
						routeType = "60000TX013";
					}else if(screen.equalsIgnoreCase("LTL_HDU_Newark")){
						routeType = "60000TX012";
					}else{
						routeType = "03AM";
					}
					RouteType1 = document.getElementsByTagName("RouteType1").item(0);
					RouteType1.setTextContent(routeType);
				}else if(screen.equalsIgnoreCase("HDUTL_Newark")){
					String destFacilityAliasID = "5854";
					DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
					DestinationFacilityAliasId.setTextContent(destFacilityAliasID);
				}

				List<String> itemNames = jd.array_Database_Connection("SELECT IC.ITEM_NAME FROM LOCN_HDR LH INNER JOIN PICK_LOCN_HDR PLH ON PLH.LOCN_ID = LH.LOCN_ID INNER JOIN PICK_LOCN_DTL PLD ON PLD.LOCN_ID = PLH.LOCN_ID INNER JOIN ITEM_CBO IC ON IC.ITEM_ID = PLD.ITEM_ID INNER JOIN ITEM_FACILITY_MAPPING_WMS IFM ON IFM.item_id= IC.ITEM_ID WHERE LH.LOCN_CLASS = 'A' AND  LH.DSP_LOCN LIKE 'BK%' AND MISC_ALPHA_1 IN('TL')");

				String itemName = itemNames.get(0);

				System.out.println("ItemName: "+itemName);

				if(!itemName.isEmpty() || !itemName.equalsIgnoreCase("")){
					Node item = document.getElementsByTagName("ItemName").item(0);
					item.setTextContent(itemName);
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult;
				streamResult = new StreamResult(new File(path).getPath());
				transformer.transform(domSource, streamResult);
				System.out.println("The XML File was Updated Successfully");
				updateDocument(document, path);

			}
			
			else if(screen.equalsIgnoreCase("UPS_Houston_Wyn")) {


				
//				String routeType = "60000OR013";
//				
//				Node RouteType1 = document.getElementsByTagName("RouteType1").item(0);
//				RouteType1.setTextContent(routeType);
//				
//				Node DestinationFacilityAliasId = document.getElementsByTagName("DestinationFacilityAliasId").item(0);
//				DestinationFacilityAliasId.setTextContent(routeType);
//				
//				//FederatedStoreNbr 0401
//				Node FederatedStoreNbr = document.getElementsByTagName("FederatedStoreNbr").item(0);
//				FederatedStoreNbr.setTextContent(routeType);

				
				//List<String> cpLoc = jd.array_Database_Connection("select lh.dsp_locn from item_cbo ic, item_wms iw, wm_inventory wi, locn_hdr lh where ic.item_id = iw.item_id and ic.item_id = wi.item_id and wi.location_id = lh.locn_id and dsp_locn like 'CP%' and ic.unit_weight > 40 and iw.convey_flag = 'Y' and ic.un_number_id is NULL and wi.locn_class = 'A' and wi.on_hand_qty > 20");

				List<String> itemNames = jd.array_Database_Connection("select DISTINCT ic.item_name from wm_inventory wm,item_cbo ic,locn_hdr lh,item_wms iw,item_supplier_xref_cbo ix where wm.location_id=lh.locn_id and wm.item_id=ic.item_id and lh.locn_class in ('A') and ic.item_id=iw.item_id and ic.item_bar_code=ix.item_barcode and ix.supplier_item_barcode is not null and iw.convey_flag='Y' and lh.dsp_locn like 'CP%' order by 1 asc ");
				
				String itemName = itemNames.get(0);

				System.out.println("ItemName: "+itemName);

				if(!itemName.isEmpty() || !itemName.equalsIgnoreCase("")){
					Node item = document.getElementsByTagName("ItemName").item(0);
					item.setTextContent(itemName);
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult;
				streamResult = new StreamResult(new File(path).getPath());
				transformer.transform(domSource, streamResult);
				System.out.println("The XML File was Updated Successfully");
				updateDocument(document, path);

			}


			String itemName = getSku(screen);

			if(!itemName.isEmpty() || !itemName.equalsIgnoreCase("")){
				Node item = document.getElementsByTagName("ItemName").item(0);
				item.setTextContent(itemName);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult;
			streamResult = new StreamResult(new File(path).getPath());
			transformer.transform(domSource, streamResult);
			System.out.println("The XML File was Updated Successfully");
			updateDocument(document, path);
		} 
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} 
		catch (TransformerException tfe){
			tfe.printStackTrace();
		} 
		catch (IOException ioe){
			ioe.printStackTrace();
		} 
		catch (SAXException sae){
			sae.printStackTrace();
		}
		report.addReportStepWithScreenshots("PO XML File Was Updated","PO XML File Was Updated with PurchaseOrder",StepResult.PASS);
		return DOOrderId;
	}

	private void updateDocument(Document document, String xmlPath)throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource domSource = new DOMSource(document);
		streamResult = new StreamResult(xmlPath);
		transformer.transform(domSource, streamResult);
	}

	public List<String> getXmlData(String data, String screen) 
			throws SAXException, IOException, ParserConfigurationException {

		String path = "";
		if(screen.equalsIgnoreCase("Zone")){
			path =  FilePathInboundZonesHouston;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019")){
			path =  FilePathVerifyIBShipment2019;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlTampa")){
			path =  FilePathMultiItemRcvASNTampa;
		}else if(screen.equalsIgnoreCase("MultiItemRcvASN")
				||screen.equalsIgnoreCase("ShipmentRcvDtl")){
			path =  FilePathMultiItemRcvASN; 
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtl")){
			path = FilePathASNMultiItemRcvDtl ; 
		}else if(screen.equalsIgnoreCase("Inbound")){
			path = FilePathInbound;
		}else if(screen.equalsIgnoreCase("DallasZone")){
			path = FilePathInboundZonesDallas;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Dallas")){
			path =  FilePathShipmentMultiItemRcvDtlDallas;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019Dallas")){
			path =  FilePathVerifyIBShipment2019Dallas;
		}else if(screen.equalsIgnoreCase("MOD10_Dallas")){
			path =  FilePathIBDallasMOD10;
		}else if(screen.equalsIgnoreCase("MOD10_Tampa")){
			path =  FilePathIBMOD10Tampa;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlBaltimore")
				|| screen.equalsIgnoreCase("ShipmentRcvDtlNewark")
				||screen.equalsIgnoreCase("MultiItemRcvASNBaltimore")){
			path = FilePathMultiItemRcvASNBaltimore ; 
		}else if(screen.equalsIgnoreCase("MultiItemASNRcvDtlBaltimore")){
			path = FilePathASNMultiItemRcvDtlBaltimore ; 
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtl_Lacey")){
			path = FilePathShipmentMultiItemRcvDtlLacey;
		}else if(screen.equalsIgnoreCase("MOD10_Baltimore")){
			path =  FilePathIBBaltimoreMOD10;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Lacey")){
			path =  FilePathVerifyIBShipment2019Lacey;
		}else if(screen.equalsIgnoreCase("MOD10_Lacey")){
			path =  FilePathIBLaceyMOD10;
		}else if(screen.equalsIgnoreCase("MOD10_Houston")){
			path =  FilePathIBMOD10Houston;
		}else if(screen.equalsIgnoreCase("MOD10_Tracey")
				){
			path = FilePathIBMOD10Tracey ;
		}else if(screen.equalsIgnoreCase("MOD10_Miami")||screen.equalsIgnoreCase("MOD10_Boston")){
			path =  FilePathIBMOD10Miami;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlMiami")||screen.equalsIgnoreCase("ShipmentRcvDtlBoston")
				){
			path = FilePathMultiItemRcvASNMiami ; 
		}else if(screen.equalsIgnoreCase("UPS_Miami_MISP")||screen.equalsIgnoreCase("UPS_Boston_MISP")){
			path =  FilePathUPSMI_Miami;
		}else if(screen.equalsIgnoreCase("VerifyIBShipment2019_Miami")||screen.equalsIgnoreCase("VerifyIBShipment2019_Boston")){
			path =  FilePathVerifyIBShipment2019Miami;
		}else if(screen.equalsIgnoreCase("ShipmentRcvDtlAtlanta")){
			path =  FilePathMultiItemRcvASNAtlanta;
		}else if(screen.equalsIgnoreCase("ShipmentMultiItemRcvASN")) {
			path =  FilePathMultiItemRcvASN;
		}

		String tagName = "";
		File filePath = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filePath);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(data);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			tagName = nNode.getNodeName();
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				xmlData.add(eElement.getTextContent().toString());
				System.out.println(data + ": "+xmlData);
			}
		}
		report.addReportStep(
				"Get "+tagName+" from PO xml",
				"Successfully generated "+tagName+" from xml. "+tagName+" generated is: "+xmlData,
				StepResult.PASS);
		return xmlData;
	}

	public void createAndPostXml2012(String flow, String count) throws Exception {

		//wh.clickElement(Maximize);
		Thread.sleep(1000);
		//driver.switchTo().frame(0);

		String path = "";

		if(flow.equalsIgnoreCase("LTLOutboundDallas")){
			path =  FilePathOutboundDallas;
		}else if(flow.equalsIgnoreCase("BVR_Dallas")){
			path = FilePathOutboundBVRDallas;
		}else if(flow.equalsIgnoreCase("UPS") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS;
		}else if(flow.equalsIgnoreCase("UPS_MIMP") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS_MIMP;
		}


		String DO = "QA";
		String DOOrderId;
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String OD = simpleDateFormat.format(new Date());
		String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
		String RefOrderID = DO + Order + " " + Order +" " + OD + " " + "00:00:00";

		Thread.sleep(1000);

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		simpleDateFormat.format(calendar.getTime());
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		Date dt = new Date(0);
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("kk:mm");
		String date = dateFormat.format(dt);
		 doIds = new ArrayList<String>();
		for(int i = 1;i<=Integer.parseInt(count);i++){
			int order = Integer.parseInt(Order) + i;
			DOOrderId = "Wyn"+Integer.toString(order)+"B";
			System.out.println("DO Order ID"+" "+DOOrderId); // order ido
			//update xml
			updateDetails(path, RefOrderID, DOOrderId, CurrentDate, PO_DUE_DATE_OD, date, DeliveryDate);
			doIds.add(DOOrderId);
			//post xml
			postXml(flow);

			if(wh.isElementPresent(Reset, 5)){
				wh.clickElement(Reset);
			}
		}
		closebtn();
		report.addReportStepWithScreenshots("Create and post xml success","Created and posted xml. Generated DO Id's :"+doIds,StepResult.PASS);
	}

	public void createAndPostXml2012(String flow) throws Exception {

		//wh.clickElement(Maximize);
		Thread.sleep(1000);
		//driver.switchTo().frame(0);

		
		String path = "";

		jd.dbDFWMSMapping();

		if(flow.equalsIgnoreCase("LTLOutboundDallas")){
			path =  FilePathOutboundDallas;
		}else if(flow.equalsIgnoreCase("BVR_Dallas")){
			path = FilePathOutboundBVRDallas;
		}else if(flow.equalsIgnoreCase("UPS") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS;
		}else if(flow.equalsIgnoreCase("UPS_MIMP") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS_MIMP;
		}else if(flow.equalsIgnoreCase("BVR_LG")){
			path =  FilePathOutbound2012_BVR;
		}else if(flow.equalsIgnoreCase("LG_New")){
			path = FilePathOutbound2012;
		}


		String DO = "QA";
		String DOOrderId;
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String OD = simpleDateFormat.format(new Date());
		String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
		String RefOrderID = DO + Order + " " + Order +" " + OD + " " + "00:00:00";
		DOOrderId = Order+"R";
		Thread.sleep(1000);
		System.out.println("DO Order ID"+" "+DOOrderId); // order ido

		final Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		simpleDateFormat.format(calendar.getTime());
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		System.out.println(DeliveryDate);
		Date dt = new Date(0);
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("kk:mm");
		String date = dateFormat.format(dt);
			//update xml
			updateDetails(path, RefOrderID, DOOrderId, CurrentDate, PO_DUE_DATE_OD, date, DeliveryDate);
			DFWMSInbounfFlowStepDefn.doId=DOOrderId;
			//post xml
			postXml(flow);
		
		closebtn();
		report.addReportStepWithScreenshots("Create and post xml success","Created and posted xml. Generated DO Id's :"+DFWMSInbounfFlowStepDefn.doId,StepResult.PASS);
	}

	
	public void createAndPostXml(String flow, String count) throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);

		String path = "";

		if(flow.equalsIgnoreCase("LTLOutboundDallas")){
			path =  FilePathOutboundDallas;
		}else if(flow.equalsIgnoreCase("BVR_Dallas")){
			path = FilePathOutboundBVRDallas;
		}else if(flow.equalsIgnoreCase("UPS") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS;
		}else if(flow.equalsIgnoreCase("UPS_MIMP") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS_MIMP;
		}


		String DO = "QA";
		String DOOrderId;
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String OD = simpleDateFormat.format(new Date());
		String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
		String RefOrderID = DO + Order + " " + Order +" " + OD + " " + "00:00:00";

		Thread.sleep(1000);

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		simpleDateFormat.format(calendar.getTime());
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		Date dt = new Date(0);
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("kk:mm");
		String date = dateFormat.format(dt);
		 doIds = new ArrayList<String>();
		for(int i = 1;i<=Integer.parseInt(count);i++){
			int order = Integer.parseInt(Order) + i;
			DOOrderId = "LTL"+Integer.toString(order)+"H";
			System.out.println("DO Order ID"+" "+DOOrderId); // order ido

			//update xml
			updateDetails(path, RefOrderID, DOOrderId, CurrentDate, PO_DUE_DATE_OD, date, DeliveryDate);
			doIds.add(DOOrderId);
			//post xml
			postXml(flow);

			if(wh.isElementPresent(Reset, 5)){
				wh.clickElement(Reset);
			}
		}
		closebtn();
		report.addReportStepWithScreenshots("Create and post xml success","Created and posted xml. Generated DO Id's :"+doIds,StepResult.PASS);
	}

	
	
	public void multiPostxml() throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);

		//String path = "";

		List<String> fileNames = new ArrayList<>();
		
		List<String> failedFileNames = new ArrayList<>();

		    
        try {
            DirectoryStream<Path> directoryStream = Files
                .newDirectoryStream(Paths.get("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\TestData\\XML\\LG_BRT"));
 
            for (Path path: directoryStream) {    
                if(!Files.isDirectory(path))
                    fileNames.add(path.toString());

                if(wh.isElementPresent(ChooseFile, 3)){
        			wh.sendKeys(ChooseFile, path.toString());
        			wh.clickElement(Send);
        			
        		}else{
        			report.addReportStep("Post Xml", "Unable to Post xml for "+fileNames, StepResult.FAIL);
        		}
                
       		    Thread.sleep(2000);
                WebElement text = driver.findElement(By.xpath("//textarea[@id='dataForm:resultString']"));
                String textagain = text.getAttribute("value");
    		    System.out.println(textagain);
    		    
    		    if(textagain.contains("<Resp_Code>25</Resp_Code>")) {
                	
                	if(wh.isElementPresent(Reset, 5)){
        				wh.clickElement(Reset);
        			}
            }else {
            	
                failedFileNames.add(path.toString());

    			report.addReportStep("Post Xml", "Unable to Post xml for "+failedFileNames, StepResult.DONE);
				wh.clickElement(Reset);

            }
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
        System.out.println("Number of Files:" + fileNames.size());
   
		closebtn();
		report.addReportStepWithScreenshots("Post xml success","Posted xml."+fileNames ,StepResult.PASS);

			
		}

		private void postXml(String flow) throws Exception {

		String path = "";
		if(flow.equalsIgnoreCase("LTLOutboundDallas") || flow.equalsIgnoreCase("LTL")){
			path =  FilePathOutboundDallas;
		}else if(flow.equalsIgnoreCase("BVR_Dallas")){
			path = FilePathOutboundBVRDallas;
		}else if(flow.equalsIgnoreCase("UPS") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS;
		}else if(flow.equalsIgnoreCase("BVR_LG")){
			path =  FilePathOutbound2012_BVR;
		}else if(flow.equalsIgnoreCase("LG_New")){
			path = FilePathOutbound2012;
		}
        else if(flow.equalsIgnoreCase("BVR")){
			path = FilePathBVR;
		}else if(flow.equalsIgnoreCase("HDU")){
			path = FilePathHDUTLDallas;
		}else if(flow.equalsIgnoreCase("MDO 5831")){
			path = FilePathMDO5823;
		}else if(flow.equalsIgnoreCase("MDO_Houston")){
			path = MDO_Houston;
		}else if(flow.equalsIgnoreCase("Parcel_Houston")){
			path = Parcel_Houston;
		}else if(flow.equalsIgnoreCase("UPS_MIMP") || flow.equalsIgnoreCase("Parcel")){
			path =  FilePathUPS_MIMP;
		}


		if(wh.isElementPresent(ChooseFile, 3)){
			wh.sendKeys(ChooseFile, path);
			wh.clickElement(Send);
		}else{
			report.addReportStep("Post Xml", "Unable to Post xml for "+flow, StepResult.FAIL);
		}

		Thread.sleep(1000);
	}

		
	private void updateDetails(String path, String RefOrderID, 
			String DOOrderId, String CurrentDate,
			String PO_DUE_DATE_OD, String date, 
			String DeliveryDate) throws Exception {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;

			document = documentBuilder.parse(path);

			/*//xmlpath
			Node refId = document.getElementsByTagName("Reference_ID").item(0);                   
			refId.setTextContent(RefOrderID);*/

			Node orderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
			orderId.setTextContent(DOOrderId);

			/*Node orderDate = document.getElementsByTagName("OrderedDttm").item(0);
			orderDate.setTextContent(CurrentDate);*/

			/*Node mustReleaseDate = document.getElementsByTagName("MustReleaseByDttm").item(0);
			mustReleaseDate.setTextContent(CurrentDate);*/

			Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
			pickupStartDttmDate.setTextContent(CurrentDate);


			Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
			deliveryEndDate.setTextContent(DeliveryDate);

			/*Node schedDlvryEndDate = document.getElementsByTagName("SchedDlvryEndDate").item(0);
			schedDlvryEndDate.setTextContent(DeliveryDate);*/

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult;
			streamResult = new StreamResult(new File(path).getPath());
			transformer.transform(domSource, streamResult);
			System.out.println("The XML File was Updated Successfully");
			updateDocument(document, path);
		}catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} 
		catch (TransformerException tfe){
			tfe.printStackTrace();
		} 
		catch (IOException ioe){
			ioe.printStackTrace();
		} 
		catch (SAXException sae){
			sae.printStackTrace();
		}
	}

	public void autoReceive() throws Exception {

		if(wh.isElementPresent(ASNCheckbox, 3)){
			wh.clickElement(ASNCheckbox);
			Thread.sleep(1000);
		}

		if(wh.isElementPresent(ASNMoreOptions, 3)){
			wh.clickElement(ASNMoreOptions);
			Thread.sleep(1000);
		}

		if(wh.isElementPresent(AutoReceive, 3)){
			wh.clickElement(AutoReceive);
			Thread.sleep(1000);
		}
		report.addReportStepWithScreenshots("Auto Receive", "Success", StepResult.PASS);
	}

	public void updateXMLData() throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		String excelFilePath = System.getProperty("user.dir")+"\\TestData\\Excel\\Item_Data.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		int i = 1;
		int item = 0;
		String desc, putawayType, miscSA, convFlag;
		desc = putawayType = miscSA = convFlag = "";
		while (iterator.hasNext()) {
			int j = 1;
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(i == 1){
					i++;
					break;
				}else{
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(j == 1 && cell.getColumnIndex() == 1){
							desc = cell.getStringCellValue();
							++j;
						}else if(j == 2 && cell.getColumnIndex() == 8){
							putawayType = cell.getStringCellValue();
							++j;
						}else if(j == 3 && cell.getColumnIndex() == 9){
							miscSA = cell.getStringCellValue();
							++j;
						}else if(j == 4 && cell.getColumnIndex() == 10){
							convFlag = cell.getStringCellValue();
							j++;
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
						item = new Double(cell.getNumericCellValue()).intValue();
						break;
					default:
						if(j == 2 && cell.getColumnIndex() == 8){
							putawayType = cell.getStringCellValue();
							++j; 
						}else if(j == 3 && cell.getColumnIndex() == 9){
							miscSA = cell.getStringCellValue();
							++j;
						}else if(j == 4 && cell.getColumnIndex() == 10){
							convFlag = cell.getStringCellValue();
							j++; 
						}
					}
				}
			}
			if(j == 5 && i == 2){
				//updateXML(String.valueOf(item), desc, putawayType, miscSA, convFlag);
				postPOXml("item");
			}
		}
		inputStream.close();
	}
	public void updateXMLDataLG() throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		String excelFilePath = System.getProperty("user.dir")+"\\TestData\\Excel\\Item_Data_LG.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		int i = 1;
		int item = 0,ItemName = 0;
		double length =0,width=0,height=0,volume=0,weight=0;
		String desc, putawayType, miscSA2, convFlag;
		desc = putawayType = miscSA2 = convFlag = "";
		while (iterator.hasNext()) {
			int j = 1;
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(i == 1){
					i++;
					break;
				}
				else{
					
					//System.out.println(cell.getCellType()+" "+ "IndexValue:"+cell.getColumnIndex());
			
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(j == 2 && cell.getColumnIndex() == 1){
							desc = cell.getStringCellValue();
							++j;
						}else if(j == 3 && cell.getColumnIndex() == 2){
							convFlag = cell.getStringCellValue();
							++j;
						}else if(j == 4 && cell.getColumnIndex() == 4){
							putawayType = cell.getStringCellValue();
							++j;
						}else if(j == 5 && cell.getColumnIndex() == 8){
							miscSA2 = cell.getStringCellValue();
							j++;
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
						item = new Double(cell.getNumericCellValue()).intValue();
						
						if(j == 1 && cell.getColumnIndex() == 0){
							ItemName = (int) cell.getNumericCellValue();
							++j; 
						}
						else if(j == 6 && cell.getColumnIndex() == 12){
							length = (double) cell.getNumericCellValue();
							j++;
						}else if(j == 7 && cell.getColumnIndex() == 13){
							width = (int) cell.getNumericCellValue();
							j++;
						}else if(j == 8 && cell.getColumnIndex() == 14){
							height = (int) cell.getNumericCellValue();
							j++;
						}else if(j == 9 && cell.getColumnIndex() == 15){
							volume = (int) cell.getNumericCellValue();
							j++;
						}else if(j == 10 && cell.getColumnIndex() == 16){
							weight = (int) cell.getNumericCellValue();
							j++;
						}
						break;
					default:
						break;
					}
				}
			}
			if(j == 11 && i == 2){
				updateXML(String.valueOf(ItemName), desc, putawayType, miscSA2, convFlag,String.valueOf(length),String.valueOf(width),String.valueOf(height),String.valueOf(volume),String.valueOf(weight));
				postPOXml("item");
			}
		}
		inputStream.close();
		report.addReportStep("Read and Update Step","Completed updating and posting xml",StepResult.PASS);	
	}

	private void updateXML(String item,String desc, String putawayType, 
			String miscSA, String convFlag,String length,String width,String height,String volume,String weight) throws Exception {
		try{
			//String path = FilePathItemXML;
			String path = FilePathItemXMLLG;

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			document = documentBuilder.parse(path);

			//reference id
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String OD = simpleDateFormat.format(new Date());
			Node refId = document.getElementsByTagName("Reference_ID").item(0);                   
			refId.setTextContent(item+"_"+OD+ " " + "00:00:00.221");

			//ItemName
			Node ItemName = document.getElementsByTagName("ItemName").item(0);                   
			ItemName.setTextContent(item);

			//Description
			Node Description = document.getElementsByTagName("Description").item(0);                   
			Description.setTextContent(desc);

			//ItemUpcGtin
			Node ItemUpcGtin = document.getElementsByTagName("ItemUpcGtin").item(0);                   
			ItemUpcGtin.setTextContent(item);

			//ItemBarCode
			Node ItemBarCode = document.getElementsByTagName("ItemBarCode").item(0);                   
			ItemBarCode.setTextContent(item);

			//SupplierItemBarcode
			Node SupplierItemBarcode = document.getElementsByTagName("SupplierItemBarcode").item(0);                   
			SupplierItemBarcode.setTextContent("00"+item);

			//ItemBarCode
			Node ItemBarCode1 = document.getElementsByTagName("ItemBarCode").item(1);                   
			ItemBarCode1.setTextContent(item);

			//SupplierItemBarcode
			Node SupplierItemBarcode1 = document.getElementsByTagName("SupplierItemBarcode").item(1);                   
			SupplierItemBarcode1.setTextContent("000"+item);

			//ItemBarCode
			Node ItemBarCode2 = document.getElementsByTagName("ItemBarCode").item(2);                   
			ItemBarCode2.setTextContent(item);

			//PutawayType
			Node PutawayType = document.getElementsByTagName("PutawayType").item(0);                   
			PutawayType.setTextContent(putawayType);

			//MiscShortAlpha2
			if(miscSA.equalsIgnoreCase("NULL")){
				miscSA = "";
			}
			Node MiscShortAlpha2 = document.getElementsByTagName("MiscShortAlpha2").item(0);                   
			MiscShortAlpha2.setTextContent(miscSA);

			//ConveyFlag
			Node ConveyFlag = document.getElementsByTagName("ConveyFlag").item(0);                   
			ConveyFlag.setTextContent(convFlag);
			
			Node Length = document.getElementsByTagName("UnitLength").item(0);                   
			Length.setTextContent(length);

			Node Width = document.getElementsByTagName("UnitWidth").item(0);                   
			Width.setTextContent(width);

			Node Height = document.getElementsByTagName("UnitHeight").item(0);                   
			Height.setTextContent(height);

			Node Weight = document.getElementsByTagName("UnitWeight").item(0);                   
			Weight.setTextContent(weight);

			Node Volume = document.getElementsByTagName("UnitVolume").item(0);                   
			Volume.setTextContent(volume);

		
			

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult;
			streamResult = new StreamResult(new File(path).getPath());
			transformer.transform(domSource, streamResult);
			System.out.println("The XML File was Updated Successfully");
			updateDocument(document, path);
		} 
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} 
		catch (TransformerException tfe){
			tfe.printStackTrace();
		} 
		catch (IOException ioe){
			ioe.printStackTrace();
		} 
		catch (SAXException sae){
			sae.printStackTrace();
		}
	}

	public void validateLPNStatus() throws Exception {
		//ilpn status
		String ilpnStatus = "";
		String olpnStatus = "";
		jd.dbDFWMSMapping();
		Thread.sleep(2000);
		ilpnStatus = jd.str_Database_Connection("SELECT LFS.DESCRIPTION FROM LPN_FACILITY_STATUS LFS, "
				+ "LPN LP WHERE LFS.LPN_FACILITY_STATUS = LP.LPN_FACILITY_STATUS AND "
				+ "LFS.LPN_FACILITY_STATUS = 95 AND LFS.INBOUND_OUTBOUND_INDICATOR = 'I' AND "
				+ "LP.TC_LPN_ID = '" + sgLPNID + "'");

		if(ilpnStatus.equalsIgnoreCase("Consumed")){
			report.addReportStep(
					"Ilpn status validated",
					"Status of iLPN is "+ilpnStatus,
					StepResult.PASS);			
		}else{
			report.addReportStep(
					"Ilpn status validated",
					"Status of iLPN is "+ilpnStatus,
					StepResult.FAIL);
		}

		//olpn status
		olpnStatus = jd.str_Database_Connection("SELECT LFS.DESCRIPTION FROM LPN_FACILITY_STATUS LFS, "
				+ "LPN LP WHERE LFS.LPN_FACILITY_STATUS = LP.LPN_FACILITY_STATUS AND "
				+ "LFS.LPN_FACILITY_STATUS = 30 AND LFS.INBOUND_OUTBOUND_INDICATOR = 'O' AND "
				+ "LP.TC_LPN_ID = '" + sgLPNID + "'");

		if(olpnStatus.equalsIgnoreCase("Weighed")){
			report.addReportStep(
					"Ilpn status validated",
					"Status of oLPN is "+olpnStatus,
					StepResult.PASS);			
		}else{
			report.addReportStep(
					"Ilpn status validated",
					"Status of oLPN is "+olpnStatus,
					StepResult.PASS);	
		}
	}

	public void readAndUpdate(String screen) throws Exception {
		try{
			String excelFilePath = "";

			if(screen.equalsIgnoreCase("Houston")){
				excelFilePath = BulkOrdersPath;
			}else{
				//excelFilePath = MultipleFlowPath;
				excelFilePath = MiamiBRTFile;
			}
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

			/*wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);*/

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			int i = 1;

			int lineItems, count;
			lineItems = count = 0;
			String flow, items, orderType, orderId, CurrentDate, DeliveryDate;
			flow = items = orderType = orderId = CurrentDate = DeliveryDate = "";
			while (iterator.hasNext()) {
				int j = 1;
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(i == 1){
						i++;
						break;
					}else{
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if(j == 1 && cell.getColumnIndex() == 2){
								flow = cell.getStringCellValue();
								++j;
							}else if(j == 3 && cell.getColumnIndex() == 4){
								items = cell.getStringCellValue();
								++j;
							}else if(j == 4 && cell.getColumnIndex() == 5){
								orderType = cell.getStringCellValue();
								++j;
							}else if(j == 5 && cell.getColumnIndex() == 6){
								orderId = cell.getStringCellValue();
								++j;
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if(j == 2 && cell.getColumnIndex() == 3){
								lineItems = new Double(cell.getNumericCellValue()).intValue();
								++j;
							}else if(j == 3 && cell.getColumnIndex() == 4){
								items = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
								++j;
							}else if(j == 6 || j == 7){

								DataFormatter dataFormatter = new DataFormatter();
								if(cell.getColumnIndex() == 7){
									CurrentDate = dataFormatter.formatCellValue(cell);
									++j; 
								}else if(cell.getColumnIndex() == 8){
									DeliveryDate = dataFormatter.formatCellValue(cell);
									++j;
								}
							}else if(j == 8 && cell.getColumnIndex() == 9){
								count = new Double(cell.getNumericCellValue()).intValue();
								++j;
							}
							break;
						}
					}
				}
				if(j == 9 && i == 2){
					updateXmlBRT(flow, lineItems, items, orderType, orderId, CurrentDate, DeliveryDate, count);
				}
			}
			inputStream.close();
			report.addReportStep(
					"Read and Update Step",
					"Completed updating and posting xml",
					StepResult.PASS);	
		}catch(Exception e){
			report.addReportStep(
					"Read and Update Step",
					"Unable to update and posting xml",
					StepResult.FAIL);	
		}

	}

	private void updateXmlBRT(String flow, int lineItems, String items,
			String orderType, String orderId, String CurrentDate, 
			String DeliveryDate, int count) throws Exception {

		try{
			String path = "";
			String lineItemXmlPath = System.getProperty("user.dir")+"\\TestData\\XML\\LineItem.xml";

			if(flow.equalsIgnoreCase("LTLOutboundDallas") || flow.equalsIgnoreCase("LTL")){
				path =  FilePathOutboundDallas;
			}else if(flow.equalsIgnoreCase("BVR_Dallas")){
				path = FilePathOutboundBVRDallas;
			}else if(flow.equalsIgnoreCase("UPS") || flow.equalsIgnoreCase("Parcel")){
				path =  FilePathUPS;
			}else if(flow.equalsIgnoreCase("BVR")){
				path = FilePathBVR;
			}else if(flow.equalsIgnoreCase("HDU")){
				path = FilePathHDUTLDallas;
			}else if(flow.equalsIgnoreCase("MDO 5831")){
				path = FilePathMDO5823;
			}else if(flow.equalsIgnoreCase("MDO_Houston")){
				path = MDO_Houston;
			}else if(flow.equalsIgnoreCase("Parcel_Houston")){
				path = Parcel_Houston;
			}

			//get count of line item tag
			int tagCount = getTagCount(path);

			//update line item xml data in xml
			updateLineItemData(lineItems, tagCount, path, lineItemXmlPath);

			//update lineItemNum,item, orderType in xml
			updateItemDetails(path, items, orderType, orderId, CurrentDate, DeliveryDate, count, flow);

			report.addReportStep(
					"Update xml for flow",
					"Completed updating xml for flow "+flow,
					StepResult.PASS);
		}catch(Exception e){
			report.addReportStep(
					"Update xml for flow",
					"Unable to update xml for flow "+flow,
					StepResult.FAIL);	
		}
	}

	private void updateItemDetails(String path, String items, String orderType, 
			String orderId, String CurrentDate, 
			String DeliveryDate, int count, String flow){

		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			document = documentBuilder.parse(path);

			List<String> itemQty = Arrays.asList(items.split(",", -1));
			List<String> itemNames = new ArrayList<String>();
			List<String> qty = new ArrayList<String>();
			List<String> orderIds = new ArrayList<String>();
			for(int i=0;i<itemQty.size();i++){
				String str = itemQty.get(i);
				itemNames.add(str.substring(0, str.indexOf("-")).trim());
				qty.add(str.substring(str.indexOf("-")+1, str.length()).trim());
			}
			//update dates & orderid
			Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
			pickupStartDttmDate.setTextContent(CurrentDate+" 00:00:00");

			Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
			deliveryEndDate.setTextContent(DeliveryDate+" 00:00:00");

			for(int i=0;i<itemNames.size();i++){
				//DoLineNbr
				Node DoLineNbr = document.getElementsByTagName("DoLineNbr").item(i);                   
				DoLineNbr.setTextContent(String.valueOf(i+1));

				//ItemName
				Node ItemName = document.getElementsByTagName("ItemName").item(i);                   
				ItemName.setTextContent(itemNames.get(i).trim());

				//OrderQty
				Node OrderQty = document.getElementsByTagName("OrderQty").item(i);                   
				OrderQty.setTextContent(qty.get(i).trim());

				//LpnType
				Node LpnType = document.getElementsByTagName("LpnType").item(i);                   
				LpnType.setTextContent(orderType);
			}
			//int sub1 = Integer.parseInt(orderId.substring(orderId.indexOf("T")+1, orderId.length()));
			for(int i=0;i<count;i++){
				//int str = sub1+i;
				String order = orderId+i;
				Node DistributionOrderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
				DistributionOrderId.setTextContent(order);
				orderIds.add(order);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult;
				streamResult = new StreamResult(new File(path).getPath());
				transformer.transform(domSource, streamResult);                 
				System.out.println("The XML File was Updated Successfully");
				updateDocument(document, path);

				//post xml
				postXml(flow);

				if(wh.isElementPresent(Reset, 5)){
					wh.clickElement(Reset);
				}
			}
			System.out.println("Order Ids :"+orderIds+ " for flow :"+flow);
			report.addReportStep("Update and posting item details in xml", "Completed updating and posting xml. Posted Order IDs are "+orderIds+ " for flow :"+flow, StepResult.PASS);	
		}catch(Exception e){
			report.addReportStep("Update and posting item details in xml", "Unable to update and posting xml for flow :"+flow, StepResult.FAIL);	
		}
	}

	static void updateLineItemData(int lineItems, int tagCount, String path, String lineItemXmlPath){
		try{
			if(lineItems > tagCount){
				lineItems = lineItems - tagCount;
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document doc = documentBuilder.parse(path);
				for(int i = 1;i<= lineItems;i++){
					int count = 0;
					//update lineitem in xml
					Document lineItemDoc = documentBuilder.parse(lineItemXmlPath);
					NodeList lineItemData = lineItemDoc.getElementsByTagName("LineItem");
					NodeList root = doc.getElementsByTagName("DistributionOrder");
					if(lineItemData.getLength()>0){
						Node n=lineItemData.item(count);
						Node newNode = n.cloneNode(true);
						doc.adoptNode(newNode);
						root.item(0).appendChild(newNode);
						count++;
					}
				}

				DOMSource source = new DOMSource(doc);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				StreamResult result = new StreamResult(path);
				transformer.transform(source, result);
			}else if(lineItems == tagCount){
				//no need to update line item
			}else if(lineItems < tagCount){
				lineItems = (tagCount) - (lineItems);
				//delete line items
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setValidating(false);
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new FileInputStream(new File(path)));
				for(int i = 0;i< lineItems;i++){
					Element element = (Element) doc.getElementsByTagName("LineItem").item(0);
					if(element != null){
						element.getParentNode().removeChild(element);
						doc.normalize();
					}
				}
				Transformer tf = TransformerFactory.newInstance().newTransformer();
				tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				tf.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				tf.transform(source, new StreamResult(path));
			}
			report.addReportStep("Update lineitem Details in xml", "Completed updating lineitems in xml", StepResult.PASS);	
		}catch(TransformerException e){
			report.addReportStep("Update lineitem Details in xml", "Unable to update lineitem details in xml", StepResult.FAIL);	
		}catch (SAXException e) {
			report.addReportStep("Update lineitem Details in xml", "Unable to update lineitem details in xml", StepResult.FAIL);
		}catch (IOException e) {
			report.addReportStep("Update lineitem Details in xml", "Unable to update lineitem details in xml", StepResult.FAIL);
		}catch (ParserConfigurationException e) {
			report.addReportStep("Update lineitem Details in xml", "Unable to update lineitem details in xml", StepResult.FAIL);
		}
	}

	static int getTagCount(String path) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(path);
		NodeList nodes = doc.getElementsByTagName("LineItem");
		System.out.println("Total number of occurences: " + nodes.getLength());
		return nodes.getLength();
	}

	public void checkout() throws Exception {

		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);

		if(wh.isElementPresent(LastPage, 5)){
			wh.clickElement(LastPage);
		}

		selectCheckbox();

		By previous = By.xpath(".//input[contains(@type,'image') and contains(@id,'pager:previous')]");
		By next = By.xpath(".//input[contains(@type,'image') and contains(@id,'pager:next')]");
		WebElement previousel = driver.findElement(previous);
		WebElement LastPageel = driver.findElement(LastPage);
		if(previousel.isDisplayed()){
			if(wh.isElementPresent(previous, 5)){
				wh.clickElement(previous);
			}
		}else if(LastPageel.isDisplayed()){
			if(wh.isElementPresent(LastPage, 5)){
				wh.clickElement(LastPage);
			}
		}else{
			if(wh.isElementPresent(next, 5)){
				wh.clickElement(next);
			}
		}
		selectCheckbox();
	}

	private void selectCheckbox() throws InterruptedException, Exception {

		By checkboxList = By.xpath(".//input[contains(@name,'listView:dataTable') and contains(@type, 'checkbox')]");
		By validation = By.xpath(".//input[contains(@alt,'Find carrier') and contains(@id,'dataForm:b')]");
		By cancel = By.xpath(".//input[contains(@value, 'Cancel') and contains(@id,'rmButton')]");
		By checkout = By.xpath("(.//input[contains(@type, 'button') and contains(@value,'Check-Out')])[1]");
		List<WebElement> list = driver.findElements(checkboxList);
		int j = 0;
		for(int i=0;i<list.size()-1;i++){
			if (wh.isElementPresent(cancel, 5)) {
				wh.clickElement(cancel);
				j++;
				Thread.sleep(500);
			}else{
				//int count = list.size() - 1;
				By checkbox;
				if(j != 0){
					checkbox = By.xpath(".//input[contains(@value,'"+j+"') and contains(@name,'listView:dataTable') and contains(@type, 'checkbox')]");
				}else{
					checkbox = By.xpath(".//input[contains(@value,'0') and contains(@name,'listView:dataTable') and contains(@type, 'checkbox')]");
				}
				if (wh.isElementPresent(checkbox, 5)) {
					wh.clickElement(checkbox);
				}

				if (wh.isElementPresent(checkout, 5)) {
					wh.clickElement(checkout);
					Thread.sleep(500);
				}

				if (wh.isElementPresent(validation, 5)) {
					wh.clickElement(checkout);
				}
			}
		}
	}

	public void shipConfirmUsingOrderId(String doId, String screen) {
		// TODO Auto-generated method stub

		try {

			driver.switchTo().defaultContent();
			wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);

			if(screen.equalsIgnoreCase("EZShip_Houston")){

				if(wh.isElementPresent(ShipConfirmEZ, 5)){
					wh.clickElement(ShipConfirmEZ);
				}
				Thread.sleep(1000);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(ShipConfirmView));

				if(wh.isElementPresent(ShipConfirmView, 5)){
					wh.clickElement(ShipConfirmView);
					Thread.sleep(1000);
				}

				if(wh.isElementPresent(ShipOrdrID, 5)){
					wh.clickElement(ShipOrdrID);
				}
				Thread.sleep(1000);
				
				driver.findElement(By.xpath("//div[@id='TABH_dataForm:definitionTab']//a")).click();
				
				if (wh.isElementPresent(RuleOperatorInput, 5)) {
					wh.selectValue(RuleOperatorInput, "equals");
					wh.sendKeys(DFWMSRunWavesPageObject.DOParamInput, DFWMSInbounfFlowStepDefn.doId);
				}
				
				if(wh.isElementPresent(ShipConfirm, 5)){
					wh.clickElement(ShipConfirm);
					Thread.sleep(1000);
				}

				driver.switchTo().alert().accept();

				report.addReportStep("Ship Confirm Rules", "Completed Ship confirm rules", StepResult.PASS);	
				closebtn();

			}				
				
			
		}catch (Exception e) {
				report.addReportStep("Ship Confirm Rules", "Unable to complete Ship confirm rules", StepResult.FAIL);	
			}

	}
	public void shipConfirm(String doId, String screen) {
		try {

			wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);

			if(screen.equalsIgnoreCase("EZShipment_Houston")
					|| screen.equalsIgnoreCase("EZShipment_Baltimore")
					|| screen.equalsIgnoreCase("EZShipment_Tracey")){

				if(wh.isElementPresent(ShipConfirmEZ, 5)){
					wh.clickElement(ShipConfirmEZ);
				}
				Thread.sleep(1000);
				if(wh.isElementPresent(ShipConfirmView, 5)){
					wh.clickElement(ShipConfirmView);
					Thread.sleep(1000);
				}

			}else{
				if(wh.isElementPresent(ShipConfirmParcel, 5)){
					wh.clickElement(ShipConfirmParcel);
				}

				Thread.sleep(1000);
				if(wh.isElementPresent(ShipConfirmViewParcel, 5)){
					wh.clickElement(ShipConfirmViewParcel);
					Thread.sleep(1000);
				}
			}

			if(wh.isElementPresent(ShipConfirmParcelRadio, 5)){
				wh.clickElement(ShipConfirmParcelRadio);
				Thread.sleep(1000);
			}

			if(wh.isElementPresent(DefinitionTab, 5)){
				wh.clickElement(DefinitionTab);
				Thread.sleep(1000);
			}

			if(screen.equalsIgnoreCase("EZShipment_Houston")
					|| screen.equalsIgnoreCase("EZShipment_Baltimore")){

				if(wh.isElementPresent(ShipConfirmEZShipSubLocn, 5)){
					wh.sendKeys(ShipConfirmEZShipSubLocn, DFWMSWeighAndManifestOlpnsPageObject.locnSUBValue);
				}
			}else if(screen.equalsIgnoreCase("EZShipment_Tracey")){
				if(wh.isElementPresent(ShipConfirmEZShipSubLocn1, 5)){
					if(!DFWMSWeighAndManifestOlpnsPageObject.subLocnBC.isEmpty()){
						wh.sendKeys(ShipConfirmEZShipSubLocn1, DFWMSWeighAndManifestOlpnsPageObject.subLocnBC);
					}else{
						jd.dbDFWMSMapping();
						if(DFWMSoLPNsPageObject.soLPNs.size()>0){
							String locId = jd.str_Database_Connection("SELECT CURR_SUB_LOCN_ID from LPN where TC_LPN_ID = '"+DFWMSoLPNsPageObject.soLPNs.get(0).toString()+"' ");
							if(locId != null){
								wh.sendKeys(ShipConfirmEZShipSubLocn1, locId);
							}else{
								report.addReportStep("Ship Confirm Rules", "Loc is empty", StepResult.FAIL);
								rc.throwTCTerminationException();
							}
						}else{
							report.addReportStep("Ship Confirm Rules", "Olpns are not generated", StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}
			}else{
				if(wh.isElementPresent(ShipConfirmParcelDO, 5)){
					wh.sendKeys(ShipConfirmParcelDO, doId);
					Thread.sleep(1000);
				}
			}

			if(wh.isElementPresent(ShipConfirm, 5)){
				wh.clickElement(ShipConfirm);
				Thread.sleep(1000);
			}

			driver.switchTo().alert().accept();

			report.addReportStep("Ship Confirm Rules", "Completed Ship confirm rules", StepResult.PASS);	
			closebtn();
		} catch (Exception e) {
			report.addReportStep("Ship Confirm Rules", "Unable to complete Ship confirm rules", StepResult.FAIL);	
		}
	}
	public void validateZone(String locnzone, String skuzone, List<String> itemNames) throws Exception {
		String zone = "";
		if(itemNames.size()>0){
			jd.dbDFWMSMapping();
			if(locnzone.equalsIgnoreCase("Active")){
				zone = jd.str_Database_Connection("SELECT DISTINCT LH.ZONE FROM "
						+ " PICK_LOCN_HDR PLH,PICK_LOCN_DTL PLD,ITEM_CBO IC, ITEM_WMS IW,"
						+ " LOCN_HDR LH, ITEM_FACILITY_MAPPING_WMS IFW, WM_INVENTORY WI WHERE"
						+ "	PLH.LOCN_ID = PLD.LOCN_ID AND PLD.LOCN_ID = LH.LOCN_ID AND "
						+ "	PLD.ITEM_ID = IW.ITEM_ID AND IW.ITEM_ID = IC.ITEM_ID AND "
						+ "	LH.LOCN_ID  = WI.LOCATION_ID AND "
						+ "	IC.ITEM_NAME = '"+itemNames.get(0)+"'");
			}else{
				zone = jd.str_Database_Connection("select  unique lh.zone from "
						+ "resv_locn_hdr RLH,Item_CBO IC, Locn_hdr LH,"
						+ "item_facility_mapping_wms ifw, item_wms iw where "
						+ "LH.LOCN_ID = RLH.LOCN_ID AND RLH.DEDCTN_ITEM_ID = IC.ITEM_ID AND "
						+ "IC.ITEM_ID = IFW.ITEM_ID AND "
						+ "IC.ITEM_ID = IW.ITEM_ID AND IC.ITEM_NAME = '"+itemNames.get(0)+"'");
			}

			if(zone != null){
				if(zone.contains(skuzone)){
					report.addReportStep("Validate Zone", "success", StepResult.PASS);
				}else{
					report.addReportStep("Validate Zone", "fail. Returned Zone from DB: "+zone, StepResult.FAIL);
					throw new Exception("Zone validation failed");
				}
			}else{
				report.addReportStep("Validate Zone", "Zone not returned from DB", StepResult.FAIL);
				throw new Exception("Zone not returned from DB");
			}
		}else{
			report.addReportStep("Validate Zone", "Item not returned from xml", StepResult.FAIL);
			throw new Exception("Item not returned from xml");
		}
	}

	
	public static Date businessDaysFrom(Date date, int businessDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < Math.abs(businessDays);) {
            // here, all days are added/subtracted
            calendar.add(Calendar.DAY_OF_MONTH, businessDays > 0 ? 1 : -1);
            
            // but at the end it goes to the correct week day.
            // because i is only increased if it is a week day
            if (!NON_BUSINESS_DAYS.contains(calendar.get(Calendar.DAY_OF_YEAR))){
                i++;
            }
        }
        return calendar.getTime();
    }
}
