package com.homer.po.DFWMS;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.glue.DFWMS.DFWMSLTLOutboundFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;


public class DFWMS2019IBPOFormatPOSTPageObject extends PageBase {

	public DFWMS2019IBPOFormatPOSTPageObject(InstanceContainer ic) {
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
//	public static final By potoasn = By.xpath("//*[@id='dataForm:cb5']");
	public static final By poasnsave = By.xpath("//*[@id='dataForm:cbdelasns']");
//	public static final By poexpand = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:collapsed']");
//	public static final By ASNexpand = By.xpath("//*[@id='dataForm:atreeTable:0:atreeB:aadaptor:0::j_id138:handle:img:collapsed']");
	
	public static final By pocollapse = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:expanded']");
	public static final By ASNcollapse = By.xpath("//*[@id='dataForm:treeTable:0:treeB:adaptor:0::tn1:handle:img:expanded']");
	
	
	
	
	public static final By asnnostatus = By.xpath("//*[@id='dataForm:ASNList_entityListView:dataTable:0:ASNStatusValueString']");
	public static final By asnnocheckbox = By.xpath("//*[@id='checkAll_c0_dataForm:ASNList_entityListView:dataTable']");
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

	//2019 Xpaths
	public 	 static final By POSrchApply = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[9]");
	public 	 static final By DOSrchApply = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[11]");
	public 	 static final By DOSrchApply2012 = By.xpath("//input[@id='dataForm:DOList_entityListView:DistributionOrderlist1:DistributionOrderlist1apply']");
	public 	 static By ASNSrch1Apply = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[10]");
	public   static final By POStatus = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ')])[4]");
	public   static final By ASNStatus = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ') and contains(@style,'text-align')])[10]");
	public   static final By ASNStatus1 = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ') and contains(@style,'text-align')])[37]");
	public   static final By ASNTransit = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ') and contains(@style,'text-align') and contains(text(),'InTransit')])");
	public   static final By ASNPONbrTxt = By.xpath("(.//input[contains(@id,'field10value1')and contains(@type,'text')])[1]");
//	public   static final By ASNPONbrTxt = By.xpath("(.//input[contains(@id,'field10value1')])[4]");
	public   static final By ASNPONbrApply = By.xpath(".//input[contains(@id,'filterIdapply')and contains(@type,'submit')]");
	public   static final By AddASNPO = By.xpath(".//input[contains(@id,'cbaddasn')and contains(@type,'button')]");
	public   static final By ASNGene = By.xpath(".//input[contains(@id,'dataForm:gena')and contains(@type,'button')]");
	public   static final By ASNGenerated = By.xpath(".//input[contains(@id,'dataForm:asnidh1')and contains(@type,'text')]");
	public   static final By ASNGenDate = By.xpath(".//input[contains(@id,'dataForm:sdqtyhcc')and contains(@type,'text')]");
	public   static final By ASNGenOK = By.xpath("(.//input[contains(@id,'dataForm:sv')and contains(@value,'OK')])[1]");
	public   static final By ASNSrchTxt = By.xpath("(.//input[contains(@id,'field10value1')and contains(@type,'text')])[2]");
	public   static final By ASNSrchApply = By.xpath(".//input[contains(@id,'filterId2apply')and contains(@type,'submit')]");
	public   static final By POnbrChk = By.xpath("(.//input[contains(@id,'selectId')and contains(@type,'checkbox') and contains(@title,'PO')])[1]");
	public   static final By ASNnbrChk = By.xpath("(.//input[contains(@id,'selectId')and contains(@type,'checkbox') and contains(@title,'ASN')])[1]");
	public   static final By potoasn = By.xpath(".//input[contains(@id,'dataForm:cb5')and contains(@type,'image')]");
	public   static final By potoasnSave = By.xpath(".//input[contains(@id,'dataForm:cbdelasns')and contains(@type,'button') and contains(@value,'Save')]");
	public   static final By poexpand = By.xpath("(.//img[contains(@id,'handle:img:collapsed')])[1]");
	public   static final By ASNexpand = By.xpath("(.//img[contains(@id,'handle:img:collapsed')])[2]");



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
		
	public void input_search(String Search, String PONBR, String ASNID) throws Throwable{
				//Thread.sleep(5000);
				wh.waitUntilDisappear(LoadingFrame);
				Thread.sleep(1000);
				wh.clickElement(Maximize);
				//Thread.sleep(1000);
		try{
			
			By DropDwn1 = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[5]");
			By DropDwn2 = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[9]");
			if (wh.isElementPresent(DropDwn, 5)) {
				wh.sendKeys(DropDwn,Search.toString().trim());
				Thread.sleep(1000);
				driver.findElement(DropDwn).sendKeys(Keys.ENTER);
	//			wh.sendKeys(POtxt, PONBR.toString().trim());
				Thread.sleep(1000);
			}else if(wh.isElementPresent(DropDwn1, 5)) {
				wh.sendKeys(DropDwn1,Search.toString().trim());
				Thread.sleep(1000);
				driver.findElement(DropDwn1).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}else if(wh.isElementPresent(DropDwn2, 5)) {
				wh.sendKeys(DropDwn2,Search.toString().trim());
				Thread.sleep(1000);
				driver.findElement(DropDwn2).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}
				
			if (Search.equalsIgnoreCase("Purchase Order")){

				if (wh.isElementPresent(PONbrTxt, 5)) {
					wh.sendKeys(PONbrTxt,PONBR.toString().trim());
					Thread.sleep(1000);
					driver.findElement(PONbrTxt).sendKeys(Keys.ENTER);
//				wh.sendKeys(POtxt, PONBR.toString().trim());
					Thread.sleep(1000);
				}

				if (wh.isElementPresent(POSrchApply, 5)) {
					wh.clickElement(POSrchApply);
				}

				if(wh.isElementPresent(POStatus, 5)) {

					report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
				} else {
					throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
				}

			}
			
			if (Search.equalsIgnoreCase("Distribution Order")){
				boolean doFlag = DFWMSLTLOutboundFlowStepDefn.multipleDo;
				if(doFlag){
					PONBR = String.join(",", DFWMSInbounfFlowStepDefn.doIds);
				}
				if (wh.isElementPresent(DONbrTxt, 5)) {
					wh.sendKeys(DONbrTxt,PONBR.toString().trim());
					Thread.sleep(1000);
					driver.findElement(DONbrTxt).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}

				if (wh.isElementPresent(DOSrchApply, 5)) {
					wh.clickElement(DOSrchApply);
				}
				report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
				/*if(wh.isElementPresent(POStatus, 5)) {

					report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
				} else {
					throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
				}*/

			}


			if (Search.equalsIgnoreCase("ASN")){

				By ASNNbrTxt1 = By.xpath("(.//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'asnId')])[2]");
				By ASNNbrTxt2 = By.xpath("(.//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'asnId')])[3]");
				if (wh.isElementPresent(ASNNbrTxt, 5)) {
					wh.sendKeys(ASNNbrTxt,ASNID.toString().trim());
					Thread.sleep(1000);
					driver.findElement(ASNNbrTxt).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}else if (wh.isElementPresent(ASNNbrTxt1, 5)) {
					wh.sendKeys(ASNNbrTxt1,ASNID.toString().trim());
					Thread.sleep(1000);
					driver.findElement(ASNNbrTxt1).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}else if (wh.isElementPresent(ASNNbrTxt2, 5)) {
					wh.sendKeys(ASNNbrTxt2,ASNID.toString().trim());
					Thread.sleep(1000);
					driver.findElement(ASNNbrTxt2).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}

				By ASNSrch1Apply1 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[23]");
				By ASNSrch1Apply2 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[36]");
				By ASNSrch1Apply3 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[33]");
				if (wh.isElementPresent(ASNSrch1Apply, 5)) {
					wh.clickElement(ASNSrch1Apply);
				}else if(wh.isElementPresent(ASNSrch1Apply1, 5)) {
					wh.clickElement(ASNSrch1Apply1);
				}else if(wh.isElementPresent(ASNSrch1Apply2, 5)) {
					wh.clickElement(ASNSrch1Apply2);
				}else if(wh.isElementPresent(ASNSrch1Apply3, 5)) {
					wh.clickElement(ASNSrch1Apply3);
				}
				
				
				//scrollPage(ASNStatus);
				By ASNClose1 = By.xpath("(.//img[contains(@id,'-toolEl')])[75]");
				By ASNClose2 = By.xpath("(.//img[contains(@id,'-toolEl')])[118]");
				By ASNClose3 = By.xpath("(.//img[contains(@id,'-toolEl')])[77]");
				if(wh.isElementPresent(ASNClose, 5)){
					wh.clickElement(ASNClose);
				}else if(wh.isElementPresent(ASNClose1, 5)){
					wh.clickElement(ASNClose1);
				}else if(wh.isElementPresent(ASNClose2, 5)){
					wh.clickElement(ASNClose2);
				}else if(wh.isElementPresent(ASNClose3, 5)){
					wh.clickElement(ASNClose3);
				}
				
				report.addReportStep("Input PO Apply","PO Applied Successfully",StepResult.PASS);

				/*if(wh.isElementPresent(ASNStatus, 5)) {

					report.addReportStep("Input ASN and Click  Apply","Successfully entered the ASN and Clicked Apply",StepResult.PASS);
				} else if(wh.isElementPresent(ASNStatus1, 5)){
					
					report.addReportStep("Input ASN and Click  Apply","Successfully entered the ASN and Clicked Apply",StepResult.PASS);
				}else{
					throw new Exception("ASN is not populated."+ "XPath used is: " + ASNStatus.toString());
				}*/

			}

//			if (Search.equalsIgnoreCase("Purchase Order")){
//
//				if(wh.isElementPresent(POStatus, 5)) {
//
//					report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
//				} else {
//					throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
//				}
//
//			}
//
//			if (Search.equalsIgnoreCase("ASN")){
//
//			}
//


//			(wh.isElementPresent(POStatus, 5)) {
//
//				report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
//			} else {
//				throw new Exception("PO is not populated."+ "XPath used is: " + ponbrstatus.toString());
//			}

		}catch (Exception e) {
			report.addReportStep("Input PO Apply","Unable to Input and Apply pO - " + e.getMessage(),StepResult.FAIL);
		}
	}

		public void ValidateStatus(String Search, String screenOption, String PONBR, String ASNID )
			throws Exception {

		try {
			// Type input into search bar
			if (Search.equalsIgnoreCase("Purchase Order")){

				if (wh.isElementPresent(POStatus, 5)) {

					wh.getText(POStatus);

				} else {
					throw new Exception("PO Fulfillment Status is not populated."+ "XPath used is: " + POStatus.toString());
				}

				// Validate that DO
				if (wh.getText(POStatus).equalsIgnoreCase(screenOption)) {
					report.addReportStep("Validate PO status", "PO "+ PONBR + " status is " + screenOption,StepResult.PASS);

				} else {
					report.addReportStep("Validate DO Status","DO is not in expected "+ screenOption +" status. DO "+ PONBR + " is in"+ wh.getText(ponbrstatus), StepResult.FAIL);
					rc.throwTCTerminationException();
				}

				closebtn();
			}
			if (Search.equalsIgnoreCase("ASN")){

//			    if (wh.isElementPresent(ASNStatus, 5)) {
//
//				} else {
//					throw new Exception("ASN Fulfillment Status is not populated."+ "XPath used is: " + ASNStatus.toString());
//				}

				// Validate that DO

//				if(screen.equalsIgnoreCase("VerifyIBShipment") || screen.equalsIgnoreCase("VerifyIBShipment10Lpns")
//						|| screen.equalsIgnoreCase("MultiItemASNRcvDtl")){
//					item_root.put("key", "VerifyIBShipment_verify");


                if(screenOption.equalsIgnoreCase("InTransit")||screenOption.equalsIgnoreCase("In Transit and Create LPNS")) {
                	By ASNTransit1 = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ') and contains(@style,'text-align') and contains(text(),'InTransit')])[3]");
                    if (wh.getText(ASNTransit).equalsIgnoreCase(screenOption) || wh.getText(ASNTransit).equalsIgnoreCase("InTransit")) {
                        report.addReportStep("Validate ASN status", "ASN ID : "+ ASNID + " status is " + screenOption,StepResult.PASS);

                    }else if (wh.getText(ASNTransit1).equalsIgnoreCase(screenOption)) {
                        report.addReportStep("Validate ASN status", "ASN ID : "+ ASNID + " status is " + screenOption,StepResult.PASS);

                    } else {
                        report.addReportStep("Validate ASN Status","ASN is not in expected status "+ screenOption +" status. ASN "+ ASNID + " is in"+ wh.getText(ASNTransit), StepResult.FAIL);
                        rc.throwTCTerminationException();
                    }

                }

				if((screenOption.equalsIgnoreCase("Receiving Started"))
						|| !(screenOption.equalsIgnoreCase("In Transit"))) {

//					closebtn();
				}
			}
			//closebtn();
		} catch (Exception e) {
			report.addReportStep("Validate Status ","Unable to validate status "+ e.getMessage(), StepResult.FAIL);
			//rc.throwTCTerminationException();
		}



	}

public void input_search2012(String Search, String PONBR, String ASNID) throws Throwable{
			//Thread.sleep(5000);
			//wh.waitUntilDisappear(LoadingFrame);
			Thread.sleep(1000);
			//wh.clickElement(Maximize);
			//Thread.sleep(1000);
	try{
		
		By DropDwn1 = By.xpath("//input[@alt='Find Distribution Order']");
		By DropDwn2 = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[9]");
//		if (wh.isElementPresent(DropDwn, 5)) {
//			wh.sendKeys(DropDwn,Search.toString().trim());
//			Thread.sleep(1000);
//			driver.findElement(DropDwn).sendKeys(Keys.ENTER);
////			wh.sendKeys(POtxt, PONBR.toString().trim());
//			Thread.sleep(1000);
//		}else if(wh.isElementPresent(DropDwn1, 5)) {
//			wh.sendKeys(DropDwn1,Search.toString().trim());
//			Thread.sleep(1000);
//			driver.findElement(DropDwn1).sendKeys(Keys.ENTER);
//			Thread.sleep(1000);
//		}else if(wh.isElementPresent(DropDwn2, 5)) {
//			wh.sendKeys(DropDwn2,Search.toString().trim());
//			Thread.sleep(1000);
//			driver.findElement(DropDwn2).sendKeys(Keys.ENTER);
//			Thread.sleep(1000);
//		}
			
		if (Search.equalsIgnoreCase("Purchase Order")){

			if (wh.isElementPresent(PONbrTxt, 5)) {
				wh.sendKeys(PONbrTxt,PONBR.toString().trim());
				Thread.sleep(1000);
				driver.findElement(PONbrTxt).sendKeys(Keys.ENTER);
//			wh.sendKeys(POtxt, PONBR.toString().trim());
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(POSrchApply, 5)) {
				wh.clickElement(POSrchApply);
			}

			if(wh.isElementPresent(POStatus, 5)) {

				report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
			} else {
				throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
			}

		}
		
		if (Search.equalsIgnoreCase("Distribution Order")){
			boolean doFlag = DFWMSLTLOutboundFlowStepDefn.multipleDo;
			if(doFlag){
				PONBR = String.join(",", DFWMSInbounfFlowStepDefn.doIds);
			}
			if (wh.isElementPresent(DropDwn1, 5)) {
				wh.sendKeys(DropDwn1,PONBR.toString().trim());
				Thread.sleep(1000);
				//driver.findElement(DropDwn1).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(DOSrchApply2012, 5)) {
				wh.clickElement(DOSrchApply2012);
			}
			
			
			report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
			/*if(wh.isElementPresent(POStatus, 5)) {

				report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
			} else {
				throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
			}*/

		}


		if (Search.equalsIgnoreCase("ASN")){

			By ASNNbrTxt1 = By.xpath("(.//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'asnId')])[2]");
			By ASNNbrTxt2 = By.xpath("(.//input [contains(@id,'mpslookupfield') and contains (@class,'x-form-text-default') and contains(@name,'asnId')])[3]");
			if (wh.isElementPresent(ASNNbrTxt, 5)) {
				wh.sendKeys(ASNNbrTxt,ASNID.toString().trim());
				Thread.sleep(1000);
				driver.findElement(ASNNbrTxt).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}else if (wh.isElementPresent(ASNNbrTxt1, 5)) {
				wh.sendKeys(ASNNbrTxt1,ASNID.toString().trim());
				Thread.sleep(1000);
				driver.findElement(ASNNbrTxt1).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}else if (wh.isElementPresent(ASNNbrTxt2, 5)) {
				wh.sendKeys(ASNNbrTxt2,ASNID.toString().trim());
				Thread.sleep(1000);
				driver.findElement(ASNNbrTxt2).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}

			By ASNSrch1Apply1 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[23]");
			By ASNSrch1Apply2 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[36]");
			By ASNSrch1Apply3 = By.xpath("(.//span[contains(@class,'x-btn-inner-default-small') and contains(@id,'btnInnerEl')])[33]");
			if (wh.isElementPresent(ASNSrch1Apply, 5)) {
				wh.clickElement(ASNSrch1Apply);
			}else if(wh.isElementPresent(ASNSrch1Apply1, 5)) {
				wh.clickElement(ASNSrch1Apply1);
			}else if(wh.isElementPresent(ASNSrch1Apply2, 5)) {
				wh.clickElement(ASNSrch1Apply2);
			}else if(wh.isElementPresent(ASNSrch1Apply3, 5)) {
				wh.clickElement(ASNSrch1Apply3);
			}
			
			
			//scrollPage(ASNStatus);
			By ASNClose1 = By.xpath("(.//img[contains(@id,'-toolEl')])[75]");
			By ASNClose2 = By.xpath("(.//img[contains(@id,'-toolEl')])[118]");
			By ASNClose3 = By.xpath("(.//img[contains(@id,'-toolEl')])[77]");
			if(wh.isElementPresent(ASNClose, 5)){
				wh.clickElement(ASNClose);
			}else if(wh.isElementPresent(ASNClose1, 5)){
				wh.clickElement(ASNClose1);
			}else if(wh.isElementPresent(ASNClose2, 5)){
				wh.clickElement(ASNClose2);
			}else if(wh.isElementPresent(ASNClose3, 5)){
				wh.clickElement(ASNClose3);
			}
			
			report.addReportStep("Input PO Apply","PO Applied Successfully",StepResult.PASS);

			/*if(wh.isElementPresent(ASNStatus, 5)) {

				report.addReportStep("Input ASN and Click  Apply","Successfully entered the ASN and Clicked Apply",StepResult.PASS);
			} else if(wh.isElementPresent(ASNStatus1, 5)){
				
				report.addReportStep("Input ASN and Click  Apply","Successfully entered the ASN and Clicked Apply",StepResult.PASS);
			}else{
				throw new Exception("ASN is not populated."+ "XPath used is: " + ASNStatus.toString());
			}*/

		}

//		if (Search.equalsIgnoreCase("Purchase Order")){
//
//			if(wh.isElementPresent(POStatus, 5)) {
//
//				report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
//			} else {
//				throw new Exception("PO is not populated."+ "XPath used is: " + POStatus.toString());
//			}
//
//		}
//
//		if (Search.equalsIgnoreCase("ASN")){
//
//		}
//


//		(wh.isElementPresent(POStatus, 5)) {
//
//			report.addReportStep("Input PO and Click  Apply","Successfully entered the PO and Clicked Apply",StepResult.PASS);
//		} else {
//			throw new Exception("PO is not populated."+ "XPath used is: " + ponbrstatus.toString());
//		}

	}catch (Exception e) {
		report.addReportStep("Input PO Apply","Unable to Input and Apply pO - " + e.getMessage(),StepResult.FAIL);
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

			wh.clickElement(Maximize);
			Thread.sleep(1000);
            driver.switchTo().frame(0);
            try{
				if(wh.isElementPresent(ASNPONbrTxt,5)){
					System.out.println("Element Present : " + ASNPONbrTxt);
				    wh.clickElement(ASNPONbrTxt);
				    Thread.sleep(1000);
					wh.sendKeys(ASNPONbrTxt,PONBR);
				}
				if(wh.isElementPresent(ASNPONbrApply)){
					wh.clickElement(ASNPONbrApply);
				}
				if(wh.isElementPresent(AddASNPO)){
					wh.clickElement(AddASNPO);
					Thread.sleep(2000);
				}
				if (wh.isElementPresent(ASNGene, 5)) {
					wh.waitForPageLoaded();
					wh.clickElement(ASNGene);
					wh.waitForPageLoaded();
					Thread.sleep(2000);
					sASNno = wh.getAttribute(ASNGenerated, "value");
				
					System.out.println("ASN no generated " +sASNno);
					Thread.sleep(2000);
					
				} else {
					throw new Exception("ASN Number not found."	+ "XPath used is: " + asngen.toString());
				}
				if(wh.isElementPresent(ASNGenDate)){
					wh.sendKeydateVal(ASNGenDate);
				}
				if(wh.isElementPresent(ASNGenOK)){
					wh.clickElement(ASNGenOK);
				}
				report.addReportStepWithScreenshots("ASN Number","Successfully Generated the ASN no. ASN number is "+ sASNno , StepResult.PASS);
			}catch (Exception e){
				report.addReportStep("Validate ASN PO Mapping ","Unable to Map PO to ASN " + PONBR + " status "+ e.getMessage(), StepResult.FAIL);
			}
				return sASNno;
		}
		public void validatepodtls(String PONBR) throws Throwable{
			ArrayList sPODTL1 = getValidatepodtls(PONBR);
			System.out.println("PO details : " +sPODTL1 );
		}
		
		public void POASNMapping(String ASNno)throws Throwable {
			try{
				if(wh.isElementPresent(ASNSrchTxt)){
					wh.sendKeys(ASNSrchTxt, ASNno.toString().trim());
				}
				if(wh.isElementPresent(ASNSrchApply)){
					Thread.sleep(1000);
					wh.clickElement(ASNSrchApply);
				}
				if(wh.isElementPresent(POnbrChk)){
					Thread.sleep(1000);
					wh.clickElement(POnbrChk);
				}
				if(wh.isElementPresent(ASNnbrChk)){
					Thread.sleep(1000);
					wh.clickElement(ASNnbrChk);
				}
				if(wh.isElementPresent(potoasn, 50)){
					Thread.sleep(1000);
					wh.clickElement(potoasn);
				}
				if(wh.isElementPresent(potoasnSave, 50)){
					wh.waitForPageLoaded();
					Thread.sleep(1000);
					wh.clickElement(potoasnSave);
				}
				if(wh.isElementPresent(POnbrChk)){
					wh.clickElement(POnbrChk);
					Thread.sleep(2000);
					wh.clickElement(poexpand);
					Thread.sleep(1000);
				}
				if(wh.isElementPresent(ASNnbrChk)){
					wh.clickElement(asnnocheck);
					Thread.sleep(2000);
					wh.clickElement(ASNexpand);
					Thread.sleep(1000);
				}
				report.addReportStepWithScreenshots("Validate ASN PO Mapping ","Map PO to ASN success" + ASNno , StepResult.PASS);
			}catch (Exception e){
				report.addReportStep("Validate ASN PO Mapping ","Unable to Map PO to ASN " + ASNno + " status "+ e.getMessage(), StepResult.FAIL);
			}

			closebtn();
		}
		
		public void InputASNApply(String ASNno){
			try{
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
				
				if(wh.isElementPresent(asnnoenterasnscr)){
					wh.sendKeys(asnnoenterasnscr, ASNno);
				}
				if(wh.isElementPresent(asnnoenterasnscraply)){
					wh.clickElement(asnnoenterasnscraply);
				}
				if(wh.isElementPresent(asnnocheckbox)){
					wh.clickElement(asnnocheckbox);
				}
				if(wh.isElementPresent(asnmorebtn)){
					wh.clickElement(asnmorebtn);
				}
				if(wh.isElementPresent(crtilpns)){
					wh.clickElement(crtilpns);
				}
				if(wh.isElementPresent(crtlilpns2)){
					wh.clickElement(crtlilpns2);
				}
			}catch (Exception e){
				report.addReportStep(
						"Validate Create iLPN ","Unable to Create ILPN for this ASN: " + ASNno + e.getMessage(), StepResult.FAIL);
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

		public void inputWaveNumber(String sWaveNumber, String screen) throws Exception {
			
			By InputDescDropDwn = By.xpath("(.//input [contains(@id,'combobox') and contains (@class,'x-form-text-default') and contains (@style,'ellipsis') and contains(@name,'combobox')])[1]");

			wh.waitUntilDisappear(LoadingFrame);
			Thread.sleep(6000);
			wh.clickElement(LoadingFilter);
			//driver.switchTo().frame(0);
			//wh.clickElement(Maximize);
			//driver.switchTo().frame(1);
				
			By InputWaveTxtBox = By.xpath("(//input[starts-with(@id,'textfield-') and contains(@id,'inputEl')])[2]");
			By BtnApply = By.xpath("//div//a[2]//span[text()='Apply']");
			//By EPIStatusLog = By.xpath(".//span[text()='Status']");
			//By EPIStatusLog = By.xpath(".//div[contains(@id,'titleEl') and contains (@class,'x-column-header-inner') and contains(@title,'Status')]");
			By ClsMsg = By.xpath(".//div[contains(@id,'collapseEl') and contains (@class,'x-layout-split-bottom') and contains(@role,'presentation')]");
			By EPIStatusSuccess = By.xpath("(.//div[contains(@class,'x-grid-cell-inner ') and contains(text(),'SUCCESS') ])[1]");
			By EPIServiceTypeShipAndRelease = By.xpath("//(.//div[contains(@class,'x-grid-cell-inner ') and contains(text(),'Ship and Release')])[1]");
			
			//(.//div[contains(@class,'x-grid-cell-inner ') and contains(text(),'Ship and Release') ])[1]
			if (wh.isElementPresent(InputDescDropDwn, 5)) {
				wh.sendKeys(DropDwn,"Wave Number");
				Thread.sleep(1000);
				driver.findElement(InputDescDropDwn).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
			}
				
			if (wh.isElementPresent(InputWaveTxtBox, 5)) {
				driver.findElement(InputWaveTxtBox).sendKeys(sWaveNumber);
				//wh.sendKeys(InputWaveTxtBox,sWaveNumber);
				//wh.sendKeys(InputWaveTxtBox,"200812314102");
				Thread.sleep(2000);
				if (wh.isElementPresent(BtnApply, 5)) {
					
					driver.findElement(BtnApply).click();
					Thread.sleep(2000);
				}
			}
			if(screen.contains("UPS")){
				if(wh.isElementPresent(EPIStatusSuccess, 5)) {
					wh.clickElement(EPIStatusSuccess);
					Thread.sleep(2000);
				}
					
			}else{
				if(wh.isElementPresent(EPIServiceTypeShipAndRelease, 5)) {
					if(wh.isElementPresent(EPIStatusSuccess, 5)) {
						wh.clickElement(EPIStatusSuccess);
						Thread.sleep(2000);
					}
				}
				
			}
			
			
			if(wh.isElementPresent(ClsMsg, 5)) {
				wh.clickElement(ClsMsg);
				Thread.sleep(2000);
			}
			closebtn();
			
			report.addReportStep("Input Wave and Apply", "Success", StepResult.PASS);
		}
		
}