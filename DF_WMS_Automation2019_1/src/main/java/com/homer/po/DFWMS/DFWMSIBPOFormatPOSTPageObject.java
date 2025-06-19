package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;


public class DFWMSIBPOFormatPOSTPageObject extends PageBase {

	public DFWMSIBPOFormatPOSTPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	public static final By btnReset  =By.id("dataForm:resetCmdId");
	public static final By ponbrapply = By.xpath("//*[@id='dataForm:POList_entityListView:POlistFil1:POlistFil1apply']");
	public static final By ponbrstatus = By.xpath("//*[@id='dataForm:POList_entityListView:dataTable:0:aicp2_2']");
	public String xmlpath = "\\TestData\\XML\\Troy\\Multi_Item_qty_PO.xml";
	public static final By ponbrasn = By.xpath("//*[@id='dataForm:filterId:field1value1']");
	public static final By ponbrasnapply = By.xpath("//*[@id='dataForm:filterId:filterIdapply']");
	public static final By asnadd = By.xpath("//*[@id='dataForm:cbaddasn']");
	public static final By asngen = By.xpath("//*[@id='dataForm:gena']");
	public static final By asngenread = By.xpath("//*[@id='dataForm:asnidh1']");
	public static final By asngendate = By.xpath("//*[@id='dataForm:sdqtyhcc']");
	public static final By asngenok = By.xpath("//*[@id='dataForm:sv']");
	public static final By asnnoenter = By.xpath("//*[@id='dataForm:filterId2:field1value1']");
	public static final By asnnoapply = By.xpath("//*[@id='dataForm:filterId2:filterId2apply']");
	public static final By asnnocheck = By.xpath("//*[@id='dataForm:atreeTable:0:atreeB:aadaptor:0::aselectId']");
	public static final By ponocheck = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::selectId']");
	public static final By potoasn = By.xpath("//*[@id='dataForm:cb5']");
	public static final By poasnsave = By.xpath("//*[@id='dataForm:cbdelasns']");
	public static final By poexpand = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:collapsed']");
	public static final By ASNexpand = By.xpath("//*[@id='dataForm:atreeTable:0:atreeB:aadaptor:0::j_id138:handle:img:collapsed']");
	
	public static final By pocollapse = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:expanded']");
	public static final By ASNcollapse = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:expanded']");
	
	
	
	
	public static final By asnnostatus = By.xpath("//*[@id='dataForm:ASNList_entityListView:dataTable:0:ASNStatusValueString']");
	public static final By asnnocheckbox = By.xpath("(.//div [contains(@class,'grid-row-checker')])[1]");
	public static final By asnmorebtn = By.xpath("//*[@id='soheaderbuttonsmoreButton']");
	public static final By crtilpns = By.xpath("//*[@id='CTO_ASNList_CreateiLPNs_moreCustom']");
	public static final By crtlilpns2 = By.xpath("//*[@id='dataForm:creatiLPN']");
	public static final By ilpnexpand = By.xpath("//*[@id='LPNList_Inbound_filterId1_fltrExpCol']");
	public static final By ilpnasnentr = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Inbound_filterId1:field6value1']");
	public static final By iLPNapply = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Inbound_filterId1:LPNList_Inbound_filterId1apply']");
	public static final By PIXapply = By.xpath("//*[@id='dataForm:lview:filterId:filterIdapply']");
	public static final By PIXexpand = By.xpath("//*[@id='filterId_fltrExpCol']");
	public static final By PIXfrom = By.xpath("//*[@id='as_bas1_in']");
	public static final By PixTO = By.xpath("//*[@id='as_bas2_in']");
	public static final By PixType = By.xpath("//*[@id='dataForm:lview:filterId:field0value1']");
	public static final By iLPNenter = By.xpath("//*[@id='dataForm:LPNListInOutboundMain_lv:LPNList_Inbound_filterId1:field1value1']");
	public static final By iLPNchkbox = By.xpath("//*[@id='checkAll_c0_dataForm:LPNListInOutboundMain_lv:dataTable']");
	public static final By iLPNlckunlck = By.xpath("//*[@id='LPNListInboundMain_commandbutton_LockUnlockLPN']");
	public static final By iLPNlck = By.xpath("//*[@id='rmButton_1Lock1_167270008']");
	public static final By iLPNlckdesc = By.xpath("//*[@id='dataForm:listView:dataTable:newRow_1:LockCodeSelect']");
	public static final By iLPNlcksave = By.xpath("//*[@id='rmButton_1Save1_167270010']");
	public static final By ilpnlcksrncncl = By.xpath("//*[@id='rmButton_1Cancel1_167270011']");
	public static final By iLPBchkcncl = By.xpath("//*[@id='LPNListInboundMain_commandbutton_CanceliLPN']");
	static final By cancelilpnOK = By.xpath(".//span[contains(text(),'OK')]");
	static final By cancelilpnOKClose = By.xpath(".//IMG[@src='/lps/resources/common/images/close.gif']");
	
	public static String sASNno,sASNno2,sASNno3 = "" ;
	StreamResult streamResult;
	JDBC_Connection jd = new JDBC_Connection(ic);
	public static ArrayList siLPNs = new ArrayList();
	public static ArrayList siLPNs3 = new ArrayList();
	public static int iArrayCount;
	public static ArrayList sASns = new ArrayList();
	public static ArrayList sTrancodedesc = new ArrayList();
	public static ArrayList sTrancodereceiptvar = new ArrayList();
	public static ArrayList sTrancodecount = new ArrayList();
	public static ArrayList sTrancodedesc1 = new ArrayList();
	public static ArrayList sTrancodereceiptvar1 = new ArrayList();
	public static ArrayList sTrancodecount1 = new ArrayList();
	public static ArrayList sTrancodedesc2 = new ArrayList();
	public static ArrayList sTrancodereceiptvar2 = new ArrayList();
	public static ArrayList sTrancodecount2 = new ArrayList();
	public static ArrayList sPODtl = new ArrayList();
	public static ArrayList sPIXTRANCODE01fromDB = new ArrayList();
	public static ArrayList sPIXTRANCODE02fromDB = new ArrayList();
	public static ArrayList siLPNNOs = new ArrayList();
	public static ArrayList siLPNnofromDB = new ArrayList();
	
	public static int itemcount = 5;
	public static String pixddt= "Today";
	public static String pixtype = "603-Verify receipt";
	public static int iArrayCount2;
	public static int iArrayCount3;
	
	public ArrayList getilpndetails(String ASNno)throws Exception{
		int iSizeCount=0;
		ArrayList siLPNValuefromDB;
		jd.dbDFWMSMapping();
		siLPNValuefromDB = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ASN_ID = '" + ASNno + "'");
		System.out.println("iLPN's Geneated for ASN " +ASNno +"is" +siLPNValuefromDB);
		siLPNs.add(siLPNValuefromDB.get(iSizeCount));
		iSizeCount++;
		return siLPNValuefromDB;
		
	}
	
	public ArrayList getValidatepodtls(String PONo)throws Exception{
		int iSizeCount=0;
		ArrayList spodtlsValuefromDB;
		jd.dbDFWMSMapping();
		spodtlsValuefromDB = jd.array_Database_Connection("select B.TC_PURCHASE_ORDERS_ID, C.ITEM_NAME,A.ORDER_QTY,A.SHIPPED_QTY,A.RECEIVED_QTY,A.INBD_LPNS_RCVD from WMLMQA01.PURCHASE_ORDERS_LINE_ITEM A,Purchase_orders B,ITEM_CBO C where A.PURCHASE_ORDERS_ID = B.PURCHASE_ORDERS_ID AND A.SKU_ID = C.ITEM_ID AND B.tc_purchase_orders_id = '" + PONo + "'");
		System.out.println("iLPN's Geneated for ASN " +PONo +"is" +spodtlsValuefromDB);
		sPODtl.add(spodtlsValuefromDB.get(iSizeCount));
		iSizeCount++;
		return spodtlsValuefromDB;
		
	}
	
	
	
	
	public String getiLPncount(String ASNNO) throws Exception {

		String siLPNcountfromDB = "";
		jd.dbDFWMSMapping();
		siLPNcountfromDB = jd.str_Database_Connection("SELECT COUNT(*) from LPN WHERE TC_ASN_ID = '" + ASNNO + "'");
		return (siLPNcountfromDB);

	}
	
	public ArrayList getlpndetailslck(String ASNNO) throws Exception {
		int iSizeCount=0;
		ArrayList siLPNnofromDB;
		jd.dbDFWMSMapping();
		System.out.println("SELECT TC_LPN_ID from LPN WHERE TC_ASN_ID = '" + ASNNO + "' AND ROWNUM <= 5");
		siLPNnofromDB = jd.array_Database_Connection("SELECT TC_LPN_ID from LPN WHERE TC_ASN_ID = '" + ASNNO + "' AND ROWNUM <= 5");
		System.out.println("ILPN top 5 count " +siLPNnofromDB);
		int iLPNcount = siLPNnofromDB.size();
		System.out.println("Array count in DB execution " +iLPNcount);
		while(iLPNcount!=0){
		siLPNNOs.add(siLPNnofromDB.get(iSizeCount));
		
		System.out.println("ILPN top 5 count from array "+siLPNNOs);
		iSizeCount++;
		iLPNcount--;
		}
		
		return (siLPNnofromDB);

	}
	
	public ArrayList getPIXcount(String ASNNO) throws Exception {
		int iSizeCount=0;
		ArrayList sPIXTRANCODE20fromDB;
		jd.dbDFWMSMapping();
		System.out.println("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('20') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		sPIXTRANCODE20fromDB = jd.array_Database_Connection("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('20') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		sTrancodedesc.add(sPIXTRANCODE20fromDB.get(iSizeCount));
		iSizeCount++;
		sTrancodereceiptvar.add(sPIXTRANCODE20fromDB.get(iSizeCount));
		iSizeCount++;
		sTrancodecount.add(sPIXTRANCODE20fromDB.get(iSizeCount));
		System.out.println("LPN level details " +sTrancodedesc +sTrancodereceiptvar +sTrancodecount);
		return (sPIXTRANCODE20fromDB);

	}
	
	public ArrayList getPIXtrncodeitm02(String ASNNO1) throws Exception {
		int iSizeCount1=0;
		ArrayList sPIXTRANCODE02fromDB1;
		jd.dbDFWMSMapping();
		//System.out.println("aSN no in getPIXtrncodeitm02 " +ASNNO1);
		do{
		//System.out.println("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('02') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO1 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		
		sPIXTRANCODE02fromDB1 = jd.array_Database_Connection("SELECT COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('02') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO1 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		//System.out.println("pixtrancode value from DB " +sPIXTRANCODE02fromDB1);
		iArrayCount2 = sPIXTRANCODE02fromDB1.size() ;
		//System.out.println("Iarraycount2 " +iArrayCount2);
		Thread.sleep(1000);
		}while(iArrayCount2!=1);
		if(iArrayCount2==1){
			//System.out.println("pixtrancode value(getPIXtrncodeitm02) function from DB after record " +sPIXTRANCODE02fromDB1);
			sPIXTRANCODE02fromDB = jd.array_Database_Connection("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('02') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO1 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
			//System.out.println("pixtrancode value from DB " +sPIXTRANCODE02fromDB);
			}
		sTrancodedesc1.add(sPIXTRANCODE02fromDB.get(iSizeCount1));
		iSizeCount1++;
		sTrancodereceiptvar1.add(sPIXTRANCODE02fromDB.get(iSizeCount1));
		iSizeCount1++;
		sTrancodecount1.add(sPIXTRANCODE02fromDB.get(iSizeCount1));
		//System.out.println("item level details " +sTrancodedesc1 +sTrancodereceiptvar1 +sTrancodecount1);
		System.out.println("NO of PIX transaction of " +sTrancodedesc1 + "count is "+sTrancodecount1  + " and receipt variance for header is " +sTrancodereceiptvar1);
		//break;
		
		
		return sPIXTRANCODE02fromDB;
	}
	
	public ArrayList getPIXtrncodehdr01(String ASNNO2) throws Exception {
		int iSizeCount2=0;
		//System.out.println("aSN no in getPIXtrncodeitm01 " +ASNNO2);
		ArrayList sPIXTRANCODE01fromDB1;
		jd.dbDFWMSMapping();
		//ArrayList sPIXTRANCODE01fromDB;
		do{
		//System.out.println("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('01') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO2 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		sPIXTRANCODE01fromDB1 = jd.array_Database_Connection("SELECT COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('01') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO2 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		//System.out.println("pixtrancode value from DB " +sPIXTRANCODE01fromDB1);
		iArrayCount3 = sPIXTRANCODE01fromDB1.size() ;
		//System.out.println("Iarraycount3 " +iArrayCount3);
		Thread.sleep(1000);
		}while(iArrayCount3!=1);
		if(iArrayCount3==1){
			//System.out.println("pixtrancode value(getPIXtrncodehdr01) Function from DB after record " +sPIXTRANCODE01fromDB1);
			sPIXTRANCODE01fromDB = jd.array_Database_Connection("SELECT B.PIX_DESC,A.RCPT_VARI,COUNT(A.ref_field_1) from PIX_TRAN A, PIX_TRAN_CODE B where A.TRAN_TYPE = B.TRAN_TYPE and A.TRAN_CODE = B.TRAN_CODE and A.TRAN_TYPE in ('603') and A.TRAN_CODE = ('01') and A.Proc_stat_CODE = '90' and B.ACTN_CODE in ('*') and A.ref_field_1 = '" + ASNNO2 + "' GROUP BY B.PIX_DESC,A.RCPT_VARI");
		}
			sTrancodedesc2.add(sPIXTRANCODE01fromDB.get(iSizeCount2));
			iSizeCount2++;
			sTrancodereceiptvar2.add(sPIXTRANCODE01fromDB.get(iSizeCount2));
			iSizeCount2++;
			sTrancodecount2.add(sPIXTRANCODE01fromDB.get(iSizeCount2));
			//System.out.println("Header level details " +sTrancodedesc2 +sTrancodereceiptvar2 +sTrancodecount2);
			System.out.println("NO of PIX transaction of " +sTrancodedesc2 + "count is "+sTrancodereceiptvar2  + " and receipt variance for header is " +sTrancodecount2);
		return (sPIXTRANCODE01fromDB1);

	}
	
	public String FormatXML() throws Exception
	{	
		String DistributionOrder = null;
		//String Item = dataTable.getCommonData(CommonDataColumn.itembd);
		
	try{

		
		String DO = "P";
		String Order = new SimpleDateFormat("MMddmmss").format(Calendar.getInstance().getTime());
		DistributionOrder = DO + Order;
		System.out.println(DistributionOrder);
		
		String OD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String PO_DUE_DATE = OD + " " + "01:00:00";
		
		String MD = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		String PO_DATE = MD + " " + "12:00:00";
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;

		//document = documentBuilder.parse(System.getProperty("user.dir") + "\\TestData\\XML\\Troy\\Multi_Item_qty_PO.xml"); 
		document = documentBuilder.parse(System.getProperty("user.dir") + xmlpath);
		
		//xmlpath
		Node Tagnode = document.getElementsByTagName("OrderId").item(0);                   
		Tagnode.setTextContent(DistributionOrder);
		Node Tagnode1 = document.getElementsByTagName("PoDueDate").item(0);
		Tagnode1.setTextContent(PO_DUE_DATE);
		Node Tagnode2 = document.getElementsByTagName("PODate").item(0);
		Tagnode2.setTextContent(PO_DATE);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult;
		streamResult = new StreamResult(new File(System.getProperty("user.dir") + xmlpath).getPath());
		transformer.transform(domSource, streamResult);
		System.out.println("The XML File was Updated Successfully");
		updateDocument(document, xmlpath);
		
		} 
	catch (ParserConfigurationException pce) 
		{
		pce.printStackTrace();
		} 
	catch (TransformerException tfe) 
		{
		tfe.printStackTrace();
		} 
	catch (IOException ioe) 
		{
		ioe.printStackTrace();
		} 
	catch (SAXException sae) 
		{
		sae.printStackTrace();
		}
		report.addReportStepWithScreenshots("PO XML File Was Updated","PO XML File Was Updated with DistributionOrder",StepResult.DONE);
		return DistributionOrder;
		}

	private void updateDocument(Document document, String xmlPath)
			throws Exception {

		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		DOMSource domSource = new DOMSource(document);
		streamResult = new StreamResult(new File(System.getProperty("user.dir")
				+ xmlPath));
		transformer.transform(domSource, streamResult);
	}
	public void DF_Upload_File(String path) throws Throwable {
		
	     System.out.println("the path is------"+path);
	                  final By Upload=By.xpath(".//input[@type='file']");
	                 wh.driver.findElement(Upload).sendKeys(path);
	                 final By Enter=By.id("dataForm:postMessageCmdId");
	                 wh.waitForPageLoaded();
	                 Thread.sleep(1000);
	                 wh.clickElement(Enter);

	                
	      // wh.waitForPageLoaded();
	      if (wh.isElementPresent(btnReset)) {
	          report.addReportStepWithScreenshots("Post Message Page, PO XML File Upload","Post Message Page, PO XML File Upload",StepResult.PASS);
	      } else {
	          report.addReportStepWithScreenshots("Post Message Page, PO XML File Upload","Post Message Page, PO XML File Upload",StepResult.FAIL);
	      }
	              }
		
	public void po_input_search(String PONBR) throws Throwable{
		try{
		
			if (wh.isElementPresent(ponbrtext, 5)) {
				wh.sendKeys(ponbrtext, PONBR.toString().trim());
				Thread.sleep(2000);
			}
			if (wh.isElementPresent(ponbrapply, 5)) {
				wh.clickElement(ponbrapply);
			}
			if (wh.isElementPresent(ponbrstatus, 5)) {

				report.addReportStep("Input PO and Click  Appply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
			} else {
				throw new Exception("PO is not populated."+ "XPath used is: " + ponbrstatus.toString());
			}
			
		}catch (Exception e) {
			report.addReportStep("Input PO Apply","Unable to Input and Apply pO - " + e.getMessage(),StepResult.FAIL);
		}
	}

		public void ValidatePOStatus(String screenOption, String PONBR)
			throws Exception {

		try {
			// Type input into search bar
			if (wh.isElementPresent(ponbrstatus, 5)) {

			} else {
				throw new Exception("DO Fulfillment Status is not populated."+ "XPath used is: " + ponbrstatus.toString());
			}

			// Validate that DO
			if (wh.getText(ponbrstatus).equalsIgnoreCase(screenOption)) {
				report.addReportStep("Validate PO status", "PO "+ PONBR + " status is " + screenOption,StepResult.PASS);
			
			} else {
				report.addReportStep("Validate DO Status","DO is not in expected "+ screenOption +" status. DO "+ PONBR + " is in"+ wh.getText(ponbrstatus), StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			report.addReportStep("Validate DO Status ","Unable to validate DO " + PONBR + " status "+ e.getMessage(), StepResult.FAIL);
			//rc.throwTCTerminationException();
		}

	}

		public void ValidatePOASNQTY(String PONBR,String ASNNO)throws Throwable {
		try{
			if(wh.isElementPresent(ponbrasn))
			{
				wh.sendKeys(ponbrasn, PONBR);
			}
			if(wh.isElementPresent(ponbrasnapply)){
				wh.clickElement(ponbrasnapply);
			}
			if(wh.isElementPresent(asnnoenter)){
				wh.sendKeys(asnnoenter, ASNNO.toString().trim());
			}
			if(wh.isElementPresent(asnnoapply)){
				Thread.sleep(1000);
				wh.clickElement(asnnoapply);
			}
			if(wh.isElementPresent(ponocheck)){
				wh.clickElement(ponocheck);
				Thread.sleep(2000);
				wh.clickElement(poexpand);
				Thread.sleep(1000);
			}
			if(wh.isElementPresent(asnnocheck)){
				wh.clickElement(asnnocheck);
				Thread.sleep(2000);
				wh.clickElement(ASNexpand);
				Thread.sleep(1000);
			}
			
		}catch (Exception e){
			report.addReportStep("Validate ASN PO Qty ","Unable to check PO ASN Qty " + PONBR +ASNNO + " status "+ e.getMessage(), StepResult.FAIL);
		}
		}
		
		public String ValidateASNPOMap(String PONBR)throws Throwable {
			try{
				Thread.sleep(2000);
				if(wh.isElementPresent(ponbrasn))
				{
					wh.sendKeys(ponbrasn, PONBR);
				}
				if(wh.isElementPresent(ponbrasnapply)){
					wh.clickElement(ponbrasnapply);
				}
				if(wh.isElementPresent(asnadd)){
					wh.clickElement(asnadd);
				}
				
				if (wh.isElementPresent(asngen, 5)) {
					wh.waitForPageLoaded();
					wh.clickElement(asngen);
					wh.waitForPageLoaded();
					Thread.sleep(2000);
					sASNno = wh.getAttribute(asngenread, "value");
				
					System.out.println("ASN no generated " +sASNno);
					Thread.sleep(2000);
					report.addReportStep("ASN Number","Successfully Generated the ASN no. ASN number is "+ sASNno , StepResult.PASS);
				} else {
					throw new Exception("ASN Number not found."	+ "XPath used is: " + asngen.toString());
			}
				if(wh.isElementPresent(asngendate)){
					wh.sendKeydateVal(asngendate);
				}
				if(wh.isElementPresent(asngenok)){
					wh.clickElement(asngenok);
				}
				
				} catch (Exception e){
				report.addReportStep(
						"Validate ASN PO Mapping ","Unable to Map PO to ASN " + PONBR + " status "+ e.getMessage(), StepResult.FAIL);
			}
			return sASNno;
				
			}
		public void validatepodtls(String PONBR) throws Throwable{
			ArrayList sPODTL1 = getValidatepodtls(PONBR);
			System.out.println("PO details : " +sPODTL1 );
		}
		
		public void POASNMapping(String ASNno)throws Throwable {
			try{
				if(wh.isElementPresent(asnnoenter)){
					wh.sendKeys(asnnoenter, ASNno.toString().trim());
				}
				if(wh.isElementPresent(asnnoapply)){
					Thread.sleep(1000);
					wh.clickElement(asnnoapply);
				}
				if(wh.isElementPresent(ponocheck)){
					Thread.sleep(1000);
					wh.clickElement(ponocheck);
				}
				if(wh.isElementPresent(asnnocheck)){
					Thread.sleep(1000);
					wh.clickElement(asnnocheck);
				}
				if(wh.isElementPresent(potoasn, 1000)){
					Thread.sleep(1000);
					wh.clickElement(potoasn);
				}
				if(wh.isElementPresent(poasnsave, 1000)){
					wh.waitForPageLoaded();
					Thread.sleep(1000);
					wh.clickElement(poasnsave);
				}
				if(wh.isElementPresent(ponocheck)){
					wh.clickElement(ponocheck);
					Thread.sleep(2000);
					wh.clickElement(poexpand);
					Thread.sleep(1000);
				}
				if(wh.isElementPresent(asnnocheck)){
					wh.clickElement(asnnocheck);
					Thread.sleep(2000);
					wh.clickElement(ASNexpand);
					Thread.sleep(1000);
				}
			}catch (Exception e){
				report.addReportStep("Validate ASN PO Mapping ","Unable to Map PO to ASN " + ASNno + " status "+ e.getMessage(), StepResult.FAIL);
			}
		}
		
		public void InputASNApply(String ASNno){
			try{
				wh.clickElement(Maximize);
				Thread.sleep(1000);
				if(ASNno == null){
					ASNno = DFWMSInbounfFlowStepDefn.ASNID;
				}
				if(wh.isElementPresent(asnnoenterasnscr)){
					wh.sendKeys(asnnoenterasnscr, ASNno);
				}
				if(wh.isElementPresent(asnnoenterasnscraply,5)){
					
					Thread.sleep(1000);
					wh.clickElement(asnnoenterasnscraply);
					if (wh.isElementPresent(asnnostatus, 5)) {

						report.addReportStep("Input ASN and Click Appply","Successfully entered the ASN and Clicked Apply",	StepResult.PASS);
					} else {
						throw new Exception("ASN is not populated."	+ "XPath used is: " + asnnostatus.toString());
					}
				}
			}catch (Exception e){
				report.addReportStep("ASN Page", "ASN number not available" +ASNno, StepResult.FAIL);
			}
		}
		
		public void ValidateASNStatus(String screenOption, String ASNno)
				throws Exception {

			try {
				// Type input into search bar
				
				if (wh.isElementPresent(asnnostatus, 5)) {

				} else {
					throw new Exception("DO Fulfillment Status is not populated."+ "XPath used is: " + asnnostatus.toString());
				}

				// Validate that DO
				if (wh.getText(asnnostatus).equalsIgnoreCase(screenOption)) {
					report.addReportStep("Validate ASN status", "ASN "
							+ ASNno + " status is " + screenOption,
							StepResult.PASS);
				} else {
					report.addReportStep("Validate ASN Status","ASN is not in expected "+ screenOption +" status. ASN "	+ ASNno + " is in"+ wh.getText(asnnostatus), StepResult.FAIL);
					//rc.throwTCTerminationException();
				}

			} catch (Exception e) {
				report.addReportStep("Validate ASN Status ","Unable to validate ASN " + ASNno + " status "+ e.getMessage(), StepResult.FAIL);
				//rc.throwTCTerminationException();
			}

		}
		
		public void CreateiLPNfromASN(String ASNno)throws Exception{
			try{
				
				
			By asnnocheckbox1 = By.xpath("(.//div [contains(@class,'grid-row-checker')])[5]");
			By asnnocheckbox2 = By.xpath("(.//div [contains(@class,'grid-row-checker')])[4]");
			By asnnocheckbox3 = By.xpath(".//div [contains(@class,'grid-row-checker') and contains(@role,'presentation')]");
			
				if(wh.isElementPresent(asnnocheckbox)){
					wh.clickElement(asnnocheckbox);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnnocheckbox1)){
					wh.clickElement(asnnocheckbox1);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnnocheckbox2)){
					wh.clickElement(asnnocheckbox2);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnnocheckbox3)){
					wh.clickElement(asnnocheckbox3);
					Thread.sleep(2000);
				}
				
				By asnscrmorebtn1 = By.xpath("(//span[contains(@id,'btnInnerEl') and contains(@class,'x-btn-inner-default-small')])[32]");
				By asnscrmorebtn2 = By.xpath("(//span[contains(@id,'btnInnerEl') and contains(@class,'x-btn-inner-default-small')])[29]");
				By asnscrmorebtn3 = By.xpath("(//span[contains(@id,'btnInnerEl') and contains(@class,'x-btn-inner-default-small')])[19]");
				if(wh.isElementPresent(asnscrmorebtn)){
					wh.clickElement(asnscrmorebtn);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnscrmorebtn1)){
					wh.clickElement(asnscrmorebtn1);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnscrmorebtn2)){
					wh.clickElement(asnscrmorebtn2);
					Thread.sleep(2000);
				}else if(wh.isElementPresent(asnscrmorebtn3)){
					wh.clickElement(asnscrmorebtn3);
					Thread.sleep(2000);
				}
				
				if(wh.isElementNotPresent(asnscrcreateilpns)) {
					
					Thread.sleep(2000);
					wh.doubleClickUsingAction(By.xpath("//div[@class='x-box-scroller x-box-scroller-bottom x-box-scroller-menu x-box-scroller-menu-default x-unselectable']"));
					Thread.sleep(2000);
					wh.doubleClickUsingAction(By.xpath("//div[@class='x-box-scroller x-box-scroller-bottom x-box-scroller-menu x-box-scroller-menu-default x-unselectable']"));
					Thread.sleep(2000);


				}
				if(wh.isElementPresent(asnscrcreateilpns)){
					wh.clickElement(asnscrcreateilpns);
					Thread.sleep(2000);
				}
				//closebtn();
				Thread.sleep(3000);
	            //driver.switchTo().frame(1);

	            //wh.clickElement(Maximize);
	            driver.switchTo().frame(0);
	            Thread.sleep(3000);
				if(wh.isElementPresent(asnscrcreateilpnsclick, 5)){
					wh.clickElement(asnscrcreateilpnsclick);
					System.out.println("iLPNS Created");
					
				}
				closebtn();
				report.addReportStep("Create iLPNs", "iLPNs created successfully", StepResult.PASS);
			}catch (Exception e){
				report.addReportStep("Validate Create iLPN ","Unable to Create ILPN for this ASN: " + ASNno + e.getMessage(), StepResult.FAIL);
			}
		}
		
		public ArrayList validateilpncrtn(String ASNno)throws Exception {
			
			try{
				if(wh.isElementPresent(ilpnexpand)){
					wh.waitForPageLoaded();
					Thread.sleep(2000);
					wh.clickElement(ilpnexpand);
				}
				if(wh.isElementPresent(ilpnasnentr)){
					wh.sendKeys(ilpnasnentr, ASNno);
				}
				if(wh.isElementPresent(iLPNapply)){
					wh.clickElement(iLPNapply);
					Thread.sleep(4000);
				}
			siLPNs3 = getilpndetails(ASNno);
			}catch (Exception e){
				report.addReportStep("Validate Created iLPNs ","iLPN's not available for this ASN: " + ASNno + e.getMessage(), StepResult.FAIL);
			}
			return siLPNs3;
		}
		
		//public ArrayList validatepixtran(String ASNno)throws Exception {
			public void validatepixtran(String ASNno)throws Exception {
			try{
			if(wh.isElementPresent(PIXexpand)){
				wh.clickElement(PIXexpand);
			}
			if(wh.isElementPresent(PIXfrom)){
				//wh.selectValue(PIXfrom, pixddt);
				wh.sendKeys(PIXfrom, pixddt);
			}
			if(wh.isElementPresent(PixTO)){
				//wh.selectValue(PixTO, pixddt);
				wh.sendKeys(PixTO, pixddt);
			}
			if(wh.isElementPresent(PixType)){
				wh.selectValue(PixType, pixtype);
			}
			if(wh.isElementPresent(PIXapply)){
				Thread.sleep(2000);
			wh.clickElement(PIXapply);
			wh.waitForPageLoaded();
			Thread.sleep(2000);
			}
				
			String countvalue = getiLPncount(ASNno);
			System.out.println("No of OLPN in the ASN "+ASNno +" is " +countvalue );
			getPIXcount(ASNno);
			System.out.println("No of Pix transaction " +sTrancodedesc + " count is " +sTrancodecount + " and receipt variance for all LPN is " +sTrancodereceiptvar);
			report.addReportStep("Validate Pix Transactions ","Pix Transactions is available for this ASN: "  + ASNno, StepResult.PASS);
			Thread.sleep(2000);
			} catch (Exception e){
				report.addReportStep("Validate Pix Transactions ","Pix Transactions not available for this ASN: "  + e.getMessage(), StepResult.FAIL);
			}
			}
			
			public void validatepixtran1(String ASNno1)throws Exception {
					try{
					//String itemcount1="5";
					System.out.println("ASN no in pixtram1 "+ASNno1);
					report.addReportStep("Validate Pix Transactions ","Pix Transactions is available for this ASN: "  + ASNno1, StepResult.PASS);
			getPIXtrncodeitm02(ASNno1);
			System.out.println("ASN no in pixtram1 after function "+ASNno1);
			System.out.println("No of Pix transaction " +sTrancodedesc1 + " count is " +sTrancodecount1 + " and receipt variance for all LPN is " +sTrancodereceiptvar1);
			Thread.sleep(2000);
			} catch (Exception e){
				report.addReportStep("Validate Pix Transactions ","Pix Transactions not available for this ASN: "  + e.getMessage(), StepResult.FAIL);
			}
			}
			
			public void validatepixtran2(String ASNno2)throws Exception {
				try{
					report.addReportStep("Validate Pix Transactions ","Pix Transactions is available for this ASN: "  + ASNno2, StepResult.PASS);
			getPIXtrncodehdr01(ASNno2);
			System.out.println("NO of PIX transaction of " +sTrancodedesc2 + "count is "+sTrancodecount2  + " and receipt variance for header is " +sTrancodereceiptvar2);
			}
			 catch (Exception e){
				report.addReportStep("Validate Pix Transactions ","Pix Transactions not available for this ASN: "  + e.getMessage(), StepResult.FAIL);
			}
		//	return null;
			
		}
		
			public void validatelcklpn(String ASNNO)throws Exception{
				int iFieldval = 0;
				try{
					getlpndetailslck(ASNNO);
					int iArrayCount1 = siLPNNOs.size();
					System.out.println("iArraycount in main function " +iArrayCount1);
					while(iArrayCount1!=0){
						String siLPN1 = siLPNNOs.get(iFieldval).toString();
//						DFWMSHomePageObject iLPNscrn = new DFWMSHomePageObject(ic);
//						iLPNscrn.WMSmenu();
//						iLPNscrn.searchInput("iLPNs", "Distribution");
						System.out.println("iLPN no in main function " +siLPN1);
						if(wh.isElementPresent(iLPNenter)){
							wh.waitForPageLoaded();
							Thread.sleep(2000);
							wh.sendKeys(iLPNenter, siLPN1);
							System.out.println("iLPN no ifield value " +iFieldval + " and corresponding iLPN is "+siLPN1);
						}
						if(wh.isElementPresent(iLPNapply)){
							wh.clickElement(iLPNapply);
							Thread.sleep(2000);
						}
						
						if(wh.isElementPresent(iLPNchkbox)){
							Thread.sleep(1000);
							wh.clickElement(iLPNchkbox);
						}
						if(wh.isElementPresent(iLPNlckunlck)){
							Thread.sleep(1000);
							wh.clickElement(iLPNlckunlck);
						}
						if(wh.isElementPresent(iLPNlck)){
							Thread.sleep(1000);
							wh.clickElement(iLPNlck);
						}
						if(wh.isElementPresent(iLPNlckdesc)){
							Thread.sleep(1000);
							wh.selectValue(iLPNlckdesc, "QA-In Quality Assurance Lock Code");
							wh.sendKeys(iLPNlckdesc, "QA-In Quality Assurance Lock Code");
						}
						
						if(wh.isElementPresent(iLPNlcksave)){
							Thread.sleep(1000);
							wh.clickElement(iLPNlcksave);
						}
						
						if(wh.isElementPresent(ilpnlcksrncncl)){
							Thread.sleep(1000);
							wh.clickElement(ilpnlcksrncncl);
						}
						if(iArrayCount1==1){
							if(wh.isElementPresent(iLPNenter)){
								Thread.sleep(2000);
								wh.sendKeys(iLPNenter, siLPN1);
								System.out.println("iLPN no ifield value with array count 4" +iFieldval + " and corresponding iLPN is "+siLPN1);
							}
							if(wh.isElementPresent(iLPNapply)){
								wh.clickElement(iLPNapply);
								Thread.sleep(2000);
							}
							
							if(wh.isElementPresent(iLPNchkbox)){
								Thread.sleep(1000);
								wh.clickElement(iLPNchkbox);
							}
							
							if(wh.isElementPresent(iLPBchkcncl)){
								Thread.sleep(1000);
								wh.clickElement(iLPBchkcncl);
							}
							Thread.sleep(1000);
							Alert alert = driver.switchTo().alert();
							Thread.sleep(2000);
							alert.accept();			
							wh.clickElement(cancelilpnOK);
							Thread.sleep(1000);
							wh.clickElement(cancelilpnOKClose);
							Thread.sleep(1000);
							wh.handleAlert();
						}
						iFieldval++;
						iArrayCount1--;
					}
					CleariLPNArrayList();
					
				}catch (Exception e){
					report.addReportStep("Lock iLPN with QA Lock","iLPN is not locked with QA lock: "  + e.getMessage(), StepResult.FAIL);
				}
			}
			
			
		public void CleariLPNArrayList(){

			sTrancodedesc.clear();
			sTrancodecount.clear();
			sTrancodereceiptvar.clear();
			sTrancodedesc1.clear();
			sTrancodecount1.clear();
			sTrancodereceiptvar1.clear();
			sTrancodedesc2.clear();
			sTrancodecount2.clear();
			sTrancodereceiptvar2.clear();
			sPIXTRANCODE01fromDB.clear();
			sPIXTRANCODE02fromDB.clear();
			
		
		}
		
		
}
