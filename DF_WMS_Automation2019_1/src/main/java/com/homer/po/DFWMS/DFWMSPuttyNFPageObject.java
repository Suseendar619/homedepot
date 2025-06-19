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
import java.util.HashMap;
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

import com.homedepot.rf.TerminalAutomation.TerminalAutomation;
import com.homedepot.rf.TerminalAutomation.Utils.Utilities;
import com.homedepot.rf.TerminalAutomation.dto.ResponseDTO;
import com.homedepot.rf.TerminalAutomation.ssh.SSHChannel;
import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.*;
import com.homer.resuablecomponents.JDBC_Connection;

public class DFWMSPuttyNFPageObject extends PageBase {
	
	public DFWMSPuttyNFPageObject(InstanceContainer ic) {
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

	public static void RFPutty_newframework(String sTaskid) {
		// TODO Auto-generated method stub
		String host = "cpliqag4.homedepot.com";
		int port = 22;
		String username = "wmrf";
		String password = "23k9xmkp";
		int timeout = 10;
		
		System.out.println("Task_ID :" +sTaskid );
		
		TerminalAutomation tr = new TerminalAutomation();
		
		HashMap<Integer, String> assertionMap = new HashMap<>(); 
		
		SSHChannel session = null;
		
		ResponseDTO startConnectionOutput = 
				tr.startConnection(host, port, username, password, timeout);
		
		if(startConnectionOutput.getResponseContent().contains("Session opened succesfully")) {
			System.out.println("Session opned succesfully");
			session = (SSHChannel)startConnectionOutput.getChannel();
		}
		else {
			System.exit(0);
		}
		
		//*********************SendKeys to Login***************************************
		tr.sendText(session, host, "Pramodh", timeout, false, false);
		tr.sendSpecialKey(session, host, timeout, "CR", false);
		tr.sendText(session, host, "1234", timeout, false, false);
		assertionMap.put(1, "Choice");
		ResponseDTO loginScreenOutput = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, false);
		System.out.println(Utilities.CleanOutput(loginScreenOutput.getResponseContent()));
		System.out.println("SendKeys to Login Duration in MilliSeconds: "+ loginScreenOutput.getDuration());
		
		//*********************Open Search Screen***************************************
		System.out.println("Open Search Screen");
		assertionMap.clear();
		assertionMap.put(1, "ALL");
		ResponseDTO searchScreenOutput1 = tr.sendSpecialKeyAndWaitUntil(session, host, "CTRLT", assertionMap, "AND", timeout, false);
		System.out.println(Utilities.CleanOutput(searchScreenOutput1.getResponseContent()));
		System.out.println("Open Search Screen Duration in MilliSeconds: "+ searchScreenOutput1.getDuration());
		
		
		//*********************Search for a Rcv Transaction***************************************
		
		tr.sendText(session, host, "ALL", timeout, false, false);
		assertionMap.clear();
		assertionMap.put(1, "ALL");
		tr.sendSpecialKey(session, host, timeout, "CR", false);
		tr.sendSpecialKey(session, host, timeout, "CR", false);
		
		assertionMap.clear();
		assertionMap.put(1, "Choice");
		ResponseDTO searchScreenOutput2 = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, true);
		
		System.out.println(Utilities.CleanOutput(searchScreenOutput2.getResponseContent()));
		System.out.println("Search for a choice menu: "+ searchScreenOutput2.getDuration());
		
		assertionMap.put(1, "Task");
		ResponseDTO searchScreenOutput3 = tr.sendSpecialKeyAndWaitUntil(session, host, "CTRLE", assertionMap, "AND", timeout, true);
		tr.sendText(session, host, sTaskid.toString(), timeout, false, false);
		assertionMap.clear();
		assertionMap.put(1, "Tote");
		ResponseDTO searchScreenOutput4 = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, true);
		
		tr.sendText(session, host, "T0917201915", timeout, false, false);
		assertionMap.clear();
		assertionMap.put(1, "Qty");
		ResponseDTO searchScreenOutput5 = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, true);
		tr.sendText(session, host, "1", timeout, false, false);
		assertionMap.clear();
		assertionMap.put(1, "Task");
		ResponseDTO searchScreenOutput6 = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, true);
		System.out.println(Utilities.CleanOutput(searchScreenOutput6.getResponseContent()));
		
		//*********************Close Session***************************************
		tr.closeConnection(session, host);
		
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
	
}
