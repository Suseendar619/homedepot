package com.homer.po.DFWMS;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.glue.DFWMS.DFWMSHomePageStepDeftn;
import com.homer.po.PageBase;
import com.opera.core.systems.scope.protos.UmsProtos.Format;

import groovyjarjarantlr.ParserSharedInputState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.stream.StreamResult;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.*;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPuttyPageObject extends PageBase {
	
	public DFWMSPuttyPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	static final By PostXMLTXTArea = By.xpath("//TEXTAREA[@id='dataForm:xmlString']");
	static final By PostXMLTXTAreaResponse = By.xpath("//TEXTAREA[@id='dataForm:resultString']");
	static final By SendBtn = By.xpath("//INPUT[@id='dataForm:postMessageCmdId']");

	public static enum POTypes {
		STANDARD
	}

	public static enum MsgFieldTypes {
		PONBR("poNbr"), CUSTORDNBR("custOrdNbr"), POCRTDT("poCrtDt"), DOCTRCID(
				"docTrcID");

		private String value;

		MsgFieldTypes(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	StreamResult streamResult;
	//StreamResult streamResult1;
	JDBC_Connection jd = new JDBC_Connection(ic);

	public static String orderID;
	public static String poNBR;
	public static String prcssStatInd;
	public static String hasImportError;
	public static String wMSOrderCount;
	public static String doStatus;
	public static String dorPONBR;
	public static String sDFWMXXML;
	public String sNewXMLPath = null;
	public String sENV = null;
	public String str = null;
	public String Task_ID = null;

	public void puttyJson(String sOrderType) throws Exception {

		DFWMSLoginPageObject DFWMS = new DFWMSLoginPageObject(ic);
		sENV = DFWMS.readProp();		
		postMessageXml(DataColumn.Json,sOrderType);
	}

	
	/**
	 * Method to enter search generate and send xml
	 * 
	 * @return
	 * @throws Exception
	 * @param columnName
	 *            Name of column in xls data sheet. Shows the type of XML sent
	 * @return 
	 * 
	 */
	public void postMessageXml(String columnName,String sOrderType)
			throws Exception {
		
		
		try {
			System.out.println(sOrderType);
//			generateXMLByType(columnName, sOrderType);
//			str = transformXmltostring(sNewXMLPath);
			
			ArrayList Task_ID=DFWMSTasksPageObject.sTaskID;
			String tt=Task_ID.toString();
		    String newtask=tt.replaceAll("\\[","").replaceAll("\\]","");
		    System.out.println("Task_ID after to string conversion" +tt );
			
			String currentDate = new SimpleDateFormat("MMddyymmss")//TMMDDYYHHmm
					.format(Calendar.getInstance().getTime());
			String TOTE="T"+currentDate;
			System.out.println(TOTE+","+newtask );
			
			String QtyDB = gettoteqty(newtask);
			
//			str = "{    \"testcase_name\": \"parcel_order_picking\",  "
//					+ "  \"input_params\": {   \"task_id\" : \""+newtask+"\",  "
//					+ "      \""+TOTE+"\": \"T2019091376\",  \"qty\": \"1\"    }}";
			
			str = "{    \"testcase_name\": \"parcel_order_picking\",  "+ "  \"input_params\": {   \"task_id\" : \""+newtask+"\",  "
					+ "      \"tote\": \""+TOTE+"\",  \"qty\": \""+QtyDB+"\"    }}";
			
			//System.out.println("Siva to check+" +str );
			
			
			//String str = transformXmltostring(dataTable.getData(columnName));
//			rc.logger.info("Dynamic XML : " + str);
			//			System.out.println(str);
//			rc.addReportStep("Post Message", "DOR input canonical message. "
//					+ "Click <a href = 'Data/"
//					+ getXMLFileLocationForReport(columnName)
//					+ "'>here</a> to see actual message. ", StepResult.PASS);
//			addFileToReport(columnName);			
			StringBuilder sc = new StringBuilder();
		//	System.out.println("Siva to check+ URL before"  );
			URL url = new URL(" http://wnc9786:4000/executeTestcase");
		//	System.out.println("Siva to check+" +url );
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setReadTimeout(12000);
			conn.setConnectTimeout(12000);

			conn.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream()); // stores server response.
			writer.write(str);
			Thread.sleep(10000);
			writer.flush();
			String line2;
		//	System.out.println("Siva to check before append line Putty Operations performed Successfully" );
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream())); // reads line by line
		//	System.out.println("Siva to check after buffer reader line Putty Operations performed Successfully" );
			Thread.sleep(2000);
			while ((line2 = reader.readLine()) != null) {
				sc.append(line2);
				Thread.sleep(3000);
			//	System.out.println("Putty Operations performed Successfully" +line2);
				Thread.sleep(5000);
			}
//			String strResponse2 = sc.toString(); // object to string conversion
//			//			System.out.println("response is " + strResponse2);
//			
			System.out.println("Putty Operations performed Successfully");

//			if (strResponse2.contains("SUCCESS: saved new dor record")) {
//				report.addReportStep("Response for DOR Canonical post - Customer Order number "+orderID,
//						strResponse2, StepResult.PASS);
//			} else {
//				report.addReportStep("Response for DOR Canonical post",
//						strResponse2, StepResult.FAIL);
//				rc.throwTCTerminationException();
//			}
		} catch (Exception e) {
			String msg = "Message not posted. " + e.getMessage();
			rc.logger.info(msg);
			//System.out.println("Siva test on catch exception");
			report.addReportStep("Post Message", msg, StepResult.WARNING);
			//rc.throwTCTerminationException();
		}
	}
	
	public String gettoteqty(String newtask1) throws Exception {

		String sTaskQtyfromDB = "";
		jd.dbDFWMSMapping();
		sTaskQtyfromDB = jd.str_Database_Connection("SELECT QTY_ALLOC from TASK_DTL WHERE TASK_ID = '" + newtask1 + "'");
		//select ALLOC_UOM_QTY,ORIG_REQMT,QTY_ALLOC,QTY_PULLD  from Task_dtl where Task_ID = '21655788';
		System.out.println("Task_Qty from DB" +sTaskQtyfromDB);
		return (sTaskQtyfromDB);

	}

	public void postMessageResponse() throws Exception {

		try {

//			System.out.println(orderID);
			JDBC_Connection jd = new JDBC_Connection(ic);
			jd.dbDORMapping();
//			System.out.println(orderID);
			poNBR = jd
					.str_Database_Connection("SELECT PO_NBR FROM DIR_FULFL_RQST_HDR WHERE CUST_ORD_NBR IN '"
							+ orderID
							+ "' order by TO_CHAR(CRT_TS,'YY-MM-DD HH24:MI:SS') desc");
			System.out.println(poNBR);		 
//			System.out.println("WMS DO:"+poNBR);
			prcssStatInd = jd
					.str_Database_Connection("SELECT PRCSS_STAT_IND FROM DIR_FULFL_RQST_HDR WHERE CUST_ORD_NBR IN '"
							+ orderID
							+ "' order by TO_CHAR(CRT_TS,'YY-MM-DD HH24:MI:SS') desc");
//					System.out.println("New DOR Status:"+prcssStatInd);
			if (prcssStatInd.equalsIgnoreCase("SNT")) {
				report.addReportStep("DO xml to DF WMS",
						"Successfully posted the DOR Output canonical xml in to WMS - WMS DO NBR is "
								+ poNBR, StepResult.PASS);
			} else {
				report.addReportStep("DO xml to DF WMS",
						"DOR Canonical Post Failed to WMS - DOR PRCSS_STAT_IND - "
								+ prcssStatInd, StepResult.WARNING);
				
//				rc.throwTCTerminationException(); Not required
			}
			
			jd.dbLegacyDORMapping();
			sDFWMXXML = jd.str_Database_Connection("SELECT DFULFL_SHP_RECON_XML_TXT FROM dfulfl_shp_recon_xml WHERE PO_NBR IN '"+ poNBR + "' order by TO_CHAR(CRT_TS,'YY-MM-DD HH24:MI:SS') desc");
			
//			System.out.println("List DB:"+sDFWMXXML+"  "+("SELECT DFULFL_SHP_RECON_XML_TXT FROM dfulfl_shp_recon_xml WHERE PO_NBR IN '"+ poNBR + "' order by TO_CHAR(CRT_TS,'YY-MM-DD HH24:MI:SS') desc"));
			
			jd.dbDFWMSMapping();
			int num = 0;
			
			wMSOrderCount = jd.str_Database_Connection("select count(HAS_IMPORT_ERROR) from orders where tc_order_id = '"+ poNBR +"'");
			
			hasImportError = jd.str_Database_Connection("select HAS_IMPORT_ERROR from orders where tc_order_id = '"+ poNBR +"'");
			
			while (wMSOrderCount.equalsIgnoreCase("0")) {
				if(num<36 && prcssStatInd.equalsIgnoreCase("SNT")){
				    Thread.sleep(1000);
				    num++;
				    wMSOrderCount = jd.str_Database_Connection("select count(HAS_IMPORT_ERROR) from orders where tc_order_id = '"+ poNBR +"'");
				    hasImportError = jd.str_Database_Connection("select HAS_IMPORT_ERROR from orders where tc_order_id = '"+ poNBR +"'");
			    }
				else if(prcssStatInd.equalsIgnoreCase("ERR") && sDFWMXXML != null){	
					postHostXML(sDFWMXXML);
					hasImportError = jd.str_Database_Connection("select HAS_IMPORT_ERROR from orders where tc_order_id = '"+ poNBR +"'");
					break;
				}
				else if(prcssStatInd.equalsIgnoreCase("ERR")){
					report.addReportStep("DO in DF WMS", "Unable to post DO " + poNBR
							+ " to DF WMS, DOR Host batch prcssStatInd is in " +prcssStatInd+ " status",
							StepResult.FAIL);
					rc.throwTCTerminationException();
					break;
				}
				else if(num>=36 && prcssStatInd.equalsIgnoreCase("SNT")){
					postHostXML(sDFWMXXML);
					hasImportError = jd.str_Database_Connection("select HAS_IMPORT_ERROR from orders where tc_order_id = '"+ poNBR +"'");
					break;
				}
				else{						
					break;
				}
			}			
			// System.out.println("List DB:"+hasImportError);
			doStatus = jd
					.str_Database_Connection("select DESCRIPTION from DO_STATUS where ORDER_STATUS = (select DO_STATUS from orders where tc_order_id = '"
							+ poNBR + "')");
			// System.out.println("List DB:"+hasImportError);

			if (hasImportError.equalsIgnoreCase("0")) {
				report.addReportStep("DO in DF WMS", "Successfully imported DO "
						+ poNBR + " xml and DO is in " + doStatus
						+ " status in WMS", StepResult.PASS);
			} else {
				report.addReportStep("DO in DF WMS", "DO " + poNBR
						+ " xml import failed, has " + hasImportError + " errors",
						StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			String msg = "Not able to validate the Post Message response. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("Post Message Response", msg, StepResult.FAIL);
		//	rc.throwTCTerminationException();
		}

	}

	/**
	 * This function gets the XML file location to be used in reports
	 * 
	 * @param columnName
	 * @return
	 */
	private String getXMLFileLocationForReport(String columnName) {

		//		int iterationNum = Math.round(Float.parseFloat(dataTable
		//				.getData("Iteration")));		
		//		int subIterationNum = Math.round(Float.parseFloat(dataTable
		//				.getData("SubIteration")));		
		//		String dataFileLocation = columnName + "_" + iterationNum + "_"
		//				+ subIterationNum + orderID +".xml";
		String dataFileLocation = columnName + "_" + "1" + "_"
				+ "1" + orderID +".xml";
				System.out.println(dataFileLocation);
		return dataFileLocation;
	}

	/**
	 * Method to generate next day based on the parameter
	 * 
	 * @return NextDay
	 * @throws Exception
	 * @param i
	 *            , which will passed from 'dynamicXmlPO' method
	 */

	public String nextDay(int i) throws Exception {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, i);
		c.add(Calendar.MINUTE, i);
		dt = c.getTime();
		String NextDay = new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(dt);
		rc.logger.info("Days generated are : " + NextDay);

		System.out.println(NextDay);
		return NextDay;
	}

	private Document getFullXmlFromDocument(String xmlPath) throws Exception {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document;

		document = documentBuilder.parse(System.getProperty("user.dir")
				+ xmlPath);

		return document;

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

	/**
	 * Method to generate dynamic xml
	 * 
	 * @param -xml path passed from method 'postMessageXml'
	 * @throws Exception
	 */
	public void dynamicXmlPO(String xmlpath) throws Exception {
		// PO xml

//		String currentDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss")//TMMDDYYHHmm
//		.format(Calendar.getInstance().getTime());
//		rc.logger.info("Current date and time :" + currentDate);
//		//		System.out.println(currentDate);
//
//		String PODate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
//				.getInstance().getTime());
//		rc.logger.info("Current date and time :" + PODate);
		//		System.out.println(PODate);

		Document document = getFullXmlFromDocument(xmlpath);
		
		

//		orderID = generateId(document);
		//		System.out
		//				.println("Test PO #####################################################:"
		//						+ orderID);

//		Node Tagnode = document.getElementsByTagName("poNbr").item(0);
//		Tagnode.setTextContent(orderID);
//		TransformerFactory transformerFactory = TransformerFactory
//				.newInstance();
//
//		Node Tagnode_1 = document.getElementsByTagName("custOrdNbr").item(0);
//		Tagnode_1.setTextContent("QA" + orderID);
//		TransformerFactory transformerFactory_1 = TransformerFactory
//				.newInstance();
//
//		Node Tagnode_2 = document.getElementsByTagName("docTrcID").item(0);
//		Tagnode_2.setTextContent("00" + orderID);
//		TransformerFactory transformerFactory_2 = TransformerFactory
//				.newInstance();
//
//		Node Tagnode_3 = document.getElementsByTagName("poCrtDt").item(0);
//		Tagnode_3.setTextContent(PODate + "T10:00:00");
//		TransformerFactory transformerFactory_3 = TransformerFactory
//				.newInstance();

		updateDocument(document, xmlpath);

		rc.logger.info("XML File was Updated Successfully");
		//		System.out.println("The XML File was Updated Successfully");

	}

	/**
	 * Method to
	 * 
	 * @throws Exception
	 * @param xmlpath
	 *            Details such as Order quantity, OriginFacility and
	 *            OriginFacility Method called in postMessageXml
	 */
	public String transformXmltostring(String xmlpath) throws Exception {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = parser.parse(System.getProperty("user.dir")
				+ xmlpath);
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		Source source = new DOMSource(document);

		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);
		transformer.transform(source, result);
		StringBuffer sb = outWriter.getBuffer();
		String finalstring = sb.toString();
		finalstring = finalstring.replace("\t", "");
		return finalstring;
	}

	public NodeList getNodesFromXmlDoc(MsgFieldTypes fieldType, String xmlType)
			throws Exception {

		NodeList list = null;
		try {

			String location = dataTable.getData(xmlType);

			// Grab the elements from the XML document
			Document document = getFullXmlFromDocument(location);
			list = document.getElementsByTagName(fieldType.getValue());
		} catch (Exception e) {
			report.addReportStep("Get " + fieldType.getValue() + " from "
					+ xmlType + " xml file",
					"Could not get " + fieldType.getValue() + " from "
							+ xmlType + " xml file." + e.getMessage(),
							StepResult.FAIL);
		}

		return list;
	}

	/**
	 * This function generates the specific XML needed for a test. The
	 * generation happens differently based on the type of XML desired.
	 * 
	 * @param xmlType
	 *            Type of XML desired (i.e. "po", "edi753", etc)
	 * @throws Exception
	 */
	public void generateXMLByType(String type, String sOrderType) throws Exception {
		try {
			//			System.out.println(dataTable.getData(type));
			//			sNewXMLPath = dataTable.getData(type) + sENV + "\\" + sOrderType + ".xml";
			sNewXMLPath = "\\TestData\\XML\\" + sENV + "\\" + sOrderType + ".json";
			System.out.println(sNewXMLPath);
			
			if (type.equals(DataColumn.Json)) {
				System.out.println("JSON FILE FOUND");
				dynamicXmlPO(sNewXMLPath);
			} else {
				throw new Exception("PO Type, " + type + ", is not recognized.");
			}
		} catch (Exception e) {
			throw new Exception("Unable to generate json file. " + e.getMessage());
		}
	}

	private void addFileToReport(String type) throws IOException {

		File src = new File(System.getProperty("user.dir") + sNewXMLPath);

		//File src = new File(System.getProperty("user.dir") + dataTable.getData(type));

		String dstString = getDataFolderLocation() + "/"
				+ getXMLFileLocationForReport(type);
		File dst = new File(dstString);

		FileUtils.copyFile(src, dst);

		rc.logger.info("XML file saved FROM: [" + src.getPath() + "] TO: ["
				+ dst.getPath() + "]");
	}

	private String getDataFolderLocation() {
		String dataFolder = dataTable.getCurrentTestCase().reportFolder
				+ "/Data/";
		File dst = new File(dataFolder);

		return (dst.getPath());

	}

	private String generateId(Document document)
			throws Exception {

		String dateFormat = new SimpleDateFormat("MMdd").format(Calendar
				.getInstance().getTime());
		String currentMilliS = new SimpleDateFormat("HHmm").format(Calendar
				.getInstance().getTime());
		String orderID = dateFormat + currentMilliS;

		orderID = NextUpCheck(orderID);

		return orderID;

	}

	private String NextUpCheck(String orderID) throws Exception {
		try {

			String NextUpFilePath = System.getProperty("user.dir")
					+ "/TestData/NextUp/DORPO";

			File file = new File(NextUpFilePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				//				System.out.println(line);
				break;
			}
			fileReader.close();

			String replaceline;
			JDBC_Connection jd1 = new JDBC_Connection(ic);
			jd1.dbDORMapping();
			dorPONBR = jd1
					.str_Database_Connection("SELECT CUST_ORD_NBR FROM DIR_FULFL_RQST_HDR WHERE CUST_ORD_NBR IN '"
							+ line + "'");
			//System.out.println("List DB:" + dorPONBR);

			while (dorPONBR != null && dorPONBR.equalsIgnoreCase(line)) {
				line = Integer.toString(Integer.parseInt(line) + 1);
				System.out.println(line);
				dorPONBR = jd1
						.str_Database_Connection("SELECT CUST_ORD_NBR FROM DIR_FULFL_RQST_HDR WHERE CUST_ORD_NBR IN '"
								+ line + "'");
				//				System.out.println("List DB inside:" + dorPONBR);
				if (dorPONBR != null) {
				} else {
					break;
				}
			}

			replaceline = Integer.toString(Integer.parseInt(line) + 1);

			File file1 = new File(NextUpFilePath);
			FileWriter fileWriter = new FileWriter(file1);
			fileWriter.write(replaceline);
			fileWriter.close();

			orderID = line;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return orderID;
	}
	
	public void postHostXML(String sDFWMXXML) throws Exception {
		try {
			DFWMSHomePageObject PostXML = new DFWMSHomePageObject(ic);
			PostXML.WMSmenu();
			PostXML.searchInput("Post Message", "Integration");	
			StringSelection data = new StringSelection(sDFWMXXML);
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(data, data);
			Transferable t = cb.getContents(null);
	         if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
//	            System.out.println(t.getTransferData(DataFlavor.stringFlavor));
	        driver.findElement(PostXMLTXTArea).sendKeys(Keys.CONTROL + "v");
			Thread.sleep(2000);
			wh.clickElement(SendBtn);
			Thread.sleep(2000);
		} catch (Exception e) {
			throw new Exception("Unable to Post XML. " + e.getMessage());
		}
	}

}
