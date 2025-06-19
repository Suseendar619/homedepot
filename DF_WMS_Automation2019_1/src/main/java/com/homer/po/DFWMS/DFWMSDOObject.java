package com.homer.po.DFWMS;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSDOObject extends PageBase{

	public StreamResult streamResult;
	JDBC_Connection jd = new JDBC_Connection(ic);
	public List<String> gridLocnLTL = new ArrayList<String>();
	public List<String> LocnBC = new ArrayList<String>();
	public List<String> locnSNGHDU = new ArrayList<String>();
	
	public String DOOrderId;
	public String locnBcSNGHDU;
	public static String DispostionType = "";
	

	public DFWMSDOObject(InstanceContainer ic) {
		super(ic);
	}
	
	public List<String> xmlData = new ArrayList<String>();
	
	public String createDOXml(String screen) throws Exception {
		
		String path = "";
		
		if(screen.equalsIgnoreCase("BK1PalletJack")){
			path =  FilePathOutbound2019;
		}if(screen.equalsIgnoreCase("LTLOutbound")){
			path = FilePathOutbound;
		}else if(screen.equalsIgnoreCase("LTL HDU")){
			path = FilePathOutboundHDU;
		}else if(screen.equalsIgnoreCase("HDUTLOutbound")){
			path = FilePathHDUTLOutbound;
		}else if(screen.equalsIgnoreCase("AgileOutbound")){
			path = FilePathAgileOutbound;
		}else if(screen.equalsIgnoreCase("UPS")){
			path = FilePathUPSOutbound;
		}else if(screen.equalsIgnoreCase("UPS_PAX")){
			path = FilePathOutboundUPSPAX;
		}else if(screen.equalsIgnoreCase("OutboundConveyAgile")){
			path = FilePathOutboundConveyAgile;
		}else if(screen.equalsIgnoreCase("OutboundConveyUPS")){
			path = FilePathOutboundConveyUPS;
		}else if(screen.equalsIgnoreCase("OutboundConveyHDU")){
			path = FilePathOutboundConveyHDU;
		}else if(screen.equalsIgnoreCase("BVR_PAX")){
			path = FilePathOutboundBossViaRDC;
		}else if(screen.equalsIgnoreCase("LTLMultiStop1")){
			path = FilePathOutboundLTLMultiStop1;
		}else if(screen.equalsIgnoreCase("LTLMultiStop2")){
			path = FilePathOutboundLTLMultiStop2;
		}else if(screen.equalsIgnoreCase("LTLSplitStop1")){
			path = FilePathOBHDULTLSplit1;
		}else if(screen.equalsIgnoreCase("LTLSplitStop2")){
			path = FilePathOBHDULTLSplit2;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassAgile")){
			path = FilePathOutboundNonConPackBypassAgile;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassUPS")){
			path = FilePathOutboundNonConPackBypassUPS;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassLTL")){
			path = FilePathOutboundNonConPackBypassLTL;
		}else if(screen.equalsIgnoreCase("BVR_Dallas")
				|| screen.equalsIgnoreCase("BVR_Lacey")){
			path = FilePathOutboundBVRDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Dallas")
				|| screen.equalsIgnoreCase("HDUTL_Baltimore")
				|| screen.equalsIgnoreCase("HDUTL_Lacey")){
			path = FilePathHDUTLDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Houston")){
			path = FilePathHDUTLHouston;
		}/*else if(screen.equalsIgnoreCase("OutboundConPackBypassAgile")){
			path = FilePathOutboundConPackBypassAgile;
		}else if(screen.equalsIgnoreCase("OutboundConPackBypassUPS")){
			path = FilePathOutboundConPackBypassUPS;
		}else if(screen.equalsIgnoreCase("OutboundConPackBypassLTL")){
			path = FilePathOutboundConPackBypassLTL;
		}else if(screen.equalsIgnoreCase("FXHD")){
			path = FilePathOutboundFXHD;
		}*/

		
		String DO = "QA";
			//String DOOrder;
        String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String OD = simpleDateFormat.format(new Date());
        String PO_DUE_DATE_OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String CurrentDate = PO_DUE_DATE_OD + " " + "00:00:00";
        String RefOrderID = DO + Order + " " + Order +" " + OD + " " + "00:00:00";
        DOOrderId = Order+"B";
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
      //System.out.println(dateFormat.format(dt));
      String date = dateFormat.format(dt);
				
				try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document;
	
				document = documentBuilder.parse(path);
				
				//xmlpath
				Node refId = document.getElementsByTagName("Reference_ID").item(0);                   
				refId.setTextContent(RefOrderID);
				
				Node orderId = document.getElementsByTagName("DistributionOrderId").item(0);                   
				orderId.setTextContent(DOOrderId);
				
				Node orderDate = document.getElementsByTagName("OrderedDttm").item(0);
				orderDate.setTextContent(CurrentDate);
				
				Node mustReleaseDate = document.getElementsByTagName("MustReleaseByDttm").item(0);
				mustReleaseDate.setTextContent(CurrentDate);
				
				Node pickupStartDttmDate = document.getElementsByTagName("PickupStartDttm").item(0);
				pickupStartDttmDate.setTextContent(CurrentDate);
				
				Node pickupEndDttmDate = document.getElementsByTagName("PickupEndDttm").item(0);
				pickupEndDttmDate.setTextContent(PO_DUE_DATE_OD+" "+date);
				
				Node deliveryStartDate = document.getElementsByTagName("DeliveryStartDttm").item(0);
				deliveryStartDate.setTextContent(CurrentDate);
				
				 
					SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
					final Calendar DeliveryDateCalendar = Calendar.getInstance();
					DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
					DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime())+ "  " +"00:00:00";
			
				
				Node deliveryEndDate = document.getElementsByTagName("DeliveryEndDttm").item(0);
				deliveryEndDate.setTextContent(DeliveryDate);
				
				Node schedDlvryEndDate = document.getElementsByTagName("SchedDlvryEndDate").item(0);
				schedDlvryEndDate.setTextContent(DeliveryDate);
				
				
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


	public List<String> getXmlData(String data, String screen) throws SAXException, IOException, ParserConfigurationException {
		String path = "";
		
		if(screen.equalsIgnoreCase("BK1PalletJack") 
				|| screen.equalsIgnoreCase("HazmatFlow") 
				|| screen.equalsIgnoreCase("BK1NonShipReady")
				||screen.equalsIgnoreCase("BK1NonShipReadyLM")
				|| screen.equalsIgnoreCase("BK3NonShipReady")
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
			path = FilePathOutbound2019;
		}else if(screen.equalsIgnoreCase("VAS")){
			path = FilePathValueAddedService;
		}else if(screen.equalsIgnoreCase("LTLOutbound")){
			path = FilePathOutbound;
		}else if(screen.equalsIgnoreCase("LTL HDU")){
			path = FilePathOutboundHDU;
		}else if(screen.equalsIgnoreCase("HDUTLOutbound")){
			path = FilePathHDUTLOutbound;
		}else if(screen.equalsIgnoreCase("BVR_Flow")){
			path = FilePathOutboundBVRDallas;
		}else if(screen.equalsIgnoreCase("AgileOutbound")){
			path = FilePathAgileOutbound;
		}else if(screen.equalsIgnoreCase("UPS")){
			path = FilePathUPSOutbound;
		}else if(screen.equalsIgnoreCase("UPS_PAX")){
			path = FilePathOutboundUPSPAX;
		}else if(screen.equalsIgnoreCase("OutboundConveyAgile")){
			path = FilePathOutboundConveyAgile;
		}else if(screen.equalsIgnoreCase("OutboundConveyUPS")){
			path = FilePathOutboundConveyUPS;
		}else if(screen.equalsIgnoreCase("OutboundConveyHDU")){
			path = FilePathOutboundConveyHDU;
		}else if(screen.equalsIgnoreCase("BVR_PAX")){
			path = FilePathOutboundBossViaRDC;
		}else if(screen.equalsIgnoreCase("LTLMultiStop1")){
			path = FilePathOutboundLTLMultiStop1;
		}else if(screen.equalsIgnoreCase("LTLMultiStop2")){
			path = FilePathOutboundLTLMultiStop2;
		}else if(screen.equalsIgnoreCase("LTLSplitStop1")){
			path = FilePathOBHDULTLSplit1;
		}else if(screen.equalsIgnoreCase("LTLSplitStop2")){
			path = FilePathOBHDULTLSplit2;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassAgile")){
			path = FilePathOutboundNonConPackBypassAgile;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassUPS")){
			path = FilePathOutboundNonConPackBypassUPS;
		}else if(screen.equalsIgnoreCase("OutboundNonConPackBypassLTL")){
			path = FilePathOutboundNonConPackBypassLTL;
		}else if(screen.equalsIgnoreCase("BVR_LG")){
			path =  FilePathOutbound2012_BVR;
		}else if(screen.equalsIgnoreCase("PAX")){
			path =  FilePathOutbound_PAX;
		}else if(screen.equalsIgnoreCase("SCP")){
			path =  FilePathOutbound_SCP;
		}
		else if(screen.equalsIgnoreCase("MCP")){
			path =  FilePathOutbound_MCP;
		}else if(screen.equalsIgnoreCase("BK1NonShipReadyDallas")
				|| screen.equalsIgnoreCase("BK1PalletJackDallas")
				|| screen.equalsIgnoreCase("HazmatFlowDallas")
				|| screen.equalsIgnoreCase("HazmatFlowBaltimore")
				|| screen.equalsIgnoreCase("HazmatFlowNewark")
				|| screen.equalsIgnoreCase("WaveReplenishmentDallas")
				|| screen.equalsIgnoreCase("BK1NonShipReadyDallas_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyBaltimore_UndoDO")
				|| screen.equalsIgnoreCase("BK1NonShipReadyLacey")
				|| screen.equalsIgnoreCase("BK1PalletJackLacey")
				||screen.equalsIgnoreCase("BK1NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK1NonShipReadyTracey")
				||screen.equalsIgnoreCase("BK3NonShipReadyTampa")
				|| screen.equalsIgnoreCase("BK3NonShipReadyTracey")
				||screen.equalsIgnoreCase("BK1NSRTampa_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NonShipReadyAtlanta")
				|| screen.equalsIgnoreCase("BK3NonShipReadyAtlanta")){
			path = FilePathOutbound2019Dallas;
		}else if(screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
				|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")){
			path =  FilePathDallasUndoDOInPacking;
		}else if(screen.equalsIgnoreCase("DallasVAS")){
			path =  FilePathValueAddedServiceDallas;
		}else if(screen.equalsIgnoreCase("LTLOutboundHouston")){
			path =  FilePathOutboundHouston;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_WES")){
			path =  FilePathWESOutboundDallas;
		}else if(screen.equalsIgnoreCase("LTLOutboundDallas")
				|| screen.equalsIgnoreCase("LTLOutboundBaltimore")
				|| screen.equalsIgnoreCase("LTLOutboundLacey")
				|| screen.equalsIgnoreCase("LTLOBLacey_UndoDO1")
				||screen.equalsIgnoreCase("LTL_Multistop")
				||screen.equalsIgnoreCase("LTL_Lacey")
				|| screen.equalsIgnoreCase("LTLOutboundNewark")
				||screen.equalsIgnoreCase("LTLOutboundTampa")
				||screen.equalsIgnoreCase("LTLOutboundTracey")
				|| screen.equalsIgnoreCase("LTLOutboundTampa_UndoDO")
				|| screen.equalsIgnoreCase("LTLOutboundTracey_UndoDO")
				||screen.equalsIgnoreCase("LTLOutboundMiami")
				||screen.equalsIgnoreCase("LTLOutboundBoston")
				||screen.equalsIgnoreCase("LTLOutboundAtlanta")
				||screen.equalsIgnoreCase("LTLOutboundAtlanta_UndoDO")){
			path =  FilePathOutboundDallas;
		}else if(screen.equalsIgnoreCase("LTL HDU Dallas")
				|| screen.equalsIgnoreCase("LTL HDU Lacey")
				|| screen.equalsIgnoreCase("LTLHDUOBLacey_UndoDO2")
				|| screen.equalsIgnoreCase("LTL_HDU_Tracey")
				|| screen.equalsIgnoreCase("LTL_HDU_Miami")
				|| screen.equalsIgnoreCase("LTL_HDU_Boston")
				|| screen.equalsIgnoreCase("LTL_HDU_Atlanta")){
			path =  FilePathLTLHDUOutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_Dallas")
				|| screen.equalsIgnoreCase("BVR_Dallas_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Dallas_MutliStop")
				|| screen.equalsIgnoreCase("BVR_Baltimore_UndoDO")
				|| screen.equalsIgnoreCase("BVR_Newark_UndoDO")){
			path = FilePathOutboundBVRDallas;
		}else if(screen.equalsIgnoreCase("LTL HDU Houston")) {
			path =FilePathLTLHDUOutboundHouston;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MutliStop")) {
			path =FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_MutliStop")){
			path = FilePathOutboundMDODallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Dallas")
				|| screen.equalsIgnoreCase("HDUTL_Baltimore")
				|| screen.equalsIgnoreCase("HDUTL_Lacey")
				|| screen.equalsIgnoreCase("HDUTL_Newark")
				|| screen.equalsIgnoreCase("HDUTL_Tracey")
				||screen.equalsIgnoreCase("HDUTL_Atlanta") ){
			path = FilePathHDUTLDallas;
		}else if(screen.equalsIgnoreCase("HDUTL_Houston")){
			path = FilePathHDUTLHouston;
		}else if(screen.equalsIgnoreCase("UPS_Dallas")
				|| screen.equalsIgnoreCase("UPS_Dallas_WM09")
				||screen.equalsIgnoreCase("UPS_Lacey")){
			path = FilePathUPSOutboundDallas;
		}else if(screen.equalsIgnoreCase("FGND_Dallas")
				||screen.equalsIgnoreCase("FGND_Dallas_WM09")
				|| screen.equalsIgnoreCase("FGND_Baltimore")
				|| screen.equalsIgnoreCase("FGND_Newark")
				|| screen.equalsIgnoreCase("FGND_Lacey")
				|| screen.equalsIgnoreCase("FGND_Tampa")
				|| screen.equalsIgnoreCase("FGND_Tracey")
				|| screen.equalsIgnoreCase("FGND_Miami")
				|| screen.equalsIgnoreCase("FGND_Boston")
				||screen.equalsIgnoreCase("FGND_Atlanta")){
			path = FilePathFGNDOutboundDallas;
		}else if(screen.equalsIgnoreCase("FXHD_Dallas")
				|| screen.equalsIgnoreCase("FXHD_Baltimore")
				|| screen.equalsIgnoreCase("FXHD_Lacey")
				|| screen.equalsIgnoreCase("FXHD_Tampa")
				|| screen.equalsIgnoreCase("FXHD_Newark")
				|| screen.equalsIgnoreCase("FXHD_Tracey")
				|| screen.equalsIgnoreCase("FXHD_Miami")
				|| screen.equalsIgnoreCase("FXHD_Boston")
				|| screen.equalsIgnoreCase("FXHD_Dallas_WM09")
				|| screen.equalsIgnoreCase("FXHD_Atlanta")){
			path =  FilePathFXHDOutboundDallas;
		}else if(screen.equalsIgnoreCase("FXHD_Dallas_Undo")) {

			path =  FilePathFXHDUndoDallas;

		}
		else if(screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Lacey")
				|| screen.equalsIgnoreCase("BVR_ShotGun_Baltimore")){
			path =  FilePathBVRShotGunOutboundDallas;
		}else if(screen.equalsIgnoreCase("UPS_Dallas_MISP")){
			path =  FilePathUPSOutboundDallasMISP;
		}else if(screen.equalsIgnoreCase("UPS_Houston_MISP")){
			path =  FilePathUPSOutboundHoustonMISP;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_MIMP")
				||screen.equalsIgnoreCase("BVR_Baltimore_MIMP")
				|| screen.equalsIgnoreCase("BVR_Dallas_MISP") ){
			path =  FilePathBVROutboundDallasMIMP;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_OLPN")
                || screen.equalsIgnoreCase("BVR_Dallas_Yard")
                ||screen.equalsIgnoreCase("BVR_MutliStop")
                || screen.equalsIgnoreCase("BVR_Dallas_OLPN_MS")){
			path =  FilePathBVROutboundDallas;
		}else if(screen.equalsIgnoreCase("BVR_MutliStop_Houston")|| screen.equalsIgnoreCase("BVR_Houston_Yard")) {
			
			path =  FilePathBVROutboundHouston;
		}else if(screen.equalsIgnoreCase("MDO_MutliStop")){
			
			path = FilePathOutbound2019;
		}
		else if(screen.equalsIgnoreCase("Envelop_Dallas")
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
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment1")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment1")){
			path =  FilePathBVRDallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("BVR_Dallas_SplitShipment2")
				|| screen.equalsIgnoreCase("BVR_Baltimore_SplitShipment2")){
			path =  FilePathBVRDallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment1")){
			path =  FilePathBVRHoustonSplitShipment1;
		}else if(screen.equalsIgnoreCase("BVR_Houston_SplitShipment2")){
			path =  FilePathBVRHoustonSplitShipment2;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment1")){
			path =  FilePathMDODallasSplitShipment1;
		}else if(screen.equalsIgnoreCase("MDO_Dallas_SplitShipment2")){
			path =  FilePathMDODallasSplitShipment2;
		}else if(screen.equalsIgnoreCase("BK1ActiveDallas")){
			path =  FilePathInboundZonesDallas;
		}else if(screen.equalsIgnoreCase("SplitShipmentDallas")
				){
			path =  FilePathOutBoundSplitShipment;
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
		}else if(screen.equalsIgnoreCase("BK1ActiveBaltimore")){
			path =  FilePathInboundZonesHouston;
		}else if(screen.equalsIgnoreCase("LTL_HDU_Baltimore")
				|| screen.equalsIgnoreCase("LTL_HDU_Newark")){
			path =  FilePathLTLHDUOutboundBaltimore;
		}else if(screen.equalsIgnoreCase("BVR_Lacey_MISP")){
			path =  FilePathBVRLaceyMultiItem;
		}else if(screen.equalsIgnoreCase("VAS_Lacey")){
			path =  FilePathVASLacey;
		}else if(screen.equalsIgnoreCase("BVR_Lacey")
				|| screen.equalsIgnoreCase("BVR_Lacey_Yard")){
			path =  FilePathBVRLacey;
		}else if(screen.equalsIgnoreCase("VAS_Baltimore")
				|| screen.equalsIgnoreCase("VAS_Newark")){
			path =  FilePathVASBaltimore;
		}else if(screen.equalsIgnoreCase("MultiShipmentLacey")){
			path =  FilePathLTLMILacey;
		}else if(screen.equalsIgnoreCase("UPS_Baltimore_MISP")){
			path =  FilePathUPSOutboundBaltimoreMISP;
		}else if(screen.equalsIgnoreCase("UPS_Lacey_MISP")){
			path =  FilePathUPSOutboundLaceyMISP;
		}else if(screen.equalsIgnoreCase("LTLMultiStopLacey")
				){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("BVR_Houston")
				|| screen.equalsIgnoreCase("LoadTrailor_NonParcel")
				|| screen.equalsIgnoreCase("BVR_Houston_Cancel_olpn")
				|| screen.equalsIgnoreCase("BVR_Houston_UndoDO")){
			path =  FilePathOutboundBVRHouston;
		}else if(screen.equalsIgnoreCase("BK1NonShipReady_MultiShipment")){
			path =  FilePathOutboundMDOMIHouston;
		}else if(screen.equalsIgnoreCase("BVR_Houston_MISP")){
			path =  FilePathOutboundBVRMIHouston;
		}else if(screen.equalsIgnoreCase("LTL_Houston_MISP")){
			path =  FilePathOutboundLTLMIHouston;
		}else if(screen.equalsIgnoreCase("LTL_Lacey_SplitShipment1")){
			path =  FilePathOutboundLTLLacey;
		}else if(screen.equalsIgnoreCase("FXHD_Houston")){
			path =  FilePathFXHD_Houston;
		}else if(screen.equalsIgnoreCase("Split_Combine")){
			path =  FilePathSplitAndCombine_Houston;
		}else if(screen.equalsIgnoreCase("LTL_HDU_Tampa")){
			path =  FilePathLTLHDU_Tampa;
		}else if(screen.equalsIgnoreCase("UPS_Tampa")
				|| screen.equalsIgnoreCase("UPS_Tracey")
				|| screen.equalsIgnoreCase("UPS_Miami")
				|| screen.equalsIgnoreCase("UPS_Atlanta")
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
		}else if(screen.equalsIgnoreCase("FGND_Houston")){
			path =  FilePathOutboundFGNDHouston;
		}else if(screen.equalsIgnoreCase("UPS_Miami_MISP")||screen.equalsIgnoreCase("UPS_Boston_MISP")){
			path =  FilePathUPSMI_Miami;
		}else if(screen.equalsIgnoreCase("EZShipment_Atlanta")){
			path =  FilePathEZShipmentAtlanta;
		}
		
		
		String tagName = "";
		//String path = System.getProperty("user.dir")+"\\TestData\\XML\\LG\\InboundPO.xml";
		File filePath = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(data);
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            tagName = nNode.getNodeName();
            //System.out.println("Current Element :" + nNode.getNodeName());
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

	public String getLocnBC(String locnBC) throws Exception {
		jd.dbDFWMSMapping();
		LocnBC = jd.array_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('"+locnBC+"')");
		System.out.println("locn value for LTL " +LocnBC);
		String locnHDUBC = (String) LocnBC.get(0);
		System.out.println("Locn Barcode " +locnHDUBC );
		
		return locnHDUBC;
	}

	public String getSNGHDULocnValue() throws Exception {
		jd.dbDFWMSMapping();
		locnSNGHDU = jd.array_Database_Connection("select locn_brcd from locn_hdr where dsp_locn in ('SNG-HDU')");
		System.out.println("SNG-HDUlocn value for LTL HDU " +locnSNGHDU);
		 locnBcSNGHDU = (String) locnSNGHDU.get(0);
		 System.out.println("SNGHDU Locn value" + locnBcSNGHDU);
		return locnBcSNGHDU;
	}

	public void getPutawayType(String item) throws Exception {
		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		
		if(wh.isElementPresent(ItemFacility, 5)){
			wh.sendKeys(ItemFacility, item);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(ItemFacilityApply, 5)){
			wh.clickElement(ItemFacilityApply);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(ItemFacilityCheckbox, 5)){
			wh.clickElement(ItemFacilityCheckbox);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(ItemFacilityView, 5)){
			wh.clickElement(ItemFacilityView);
			Thread.sleep(1000);
		}
		if(wh.isElementPresent(PutAwayType, 5)){
			DispostionType = wh.getText(PutAwayType);
			Thread.sleep(1000);
		}
		if(!DispostionType.isEmpty()){
			report.addReportStepWithScreenshots("Get Putaway Type", "Putaway Type returned :"+DispostionType, StepResult.PASS);
		}else{
			report.addReportStepWithScreenshots("Get Putaway Type", "Putaway Type not returned", StepResult.FAIL);
		}
		
		closebtn();
	}
}