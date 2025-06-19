package com.homer.po.DFWMS;



import java.util.HashMap;

import org.apache.log4j.Logger;

import com.homedepot.rf.TerminalAutomation.TerminalAutomation;
import com.homedepot.rf.TerminalAutomation.Utils.Utilities;
import com.homedepot.rf.TerminalAutomation.dto.ResponseDTO;
import com.homedepot.rf.TerminalAutomation.ssh.SSHChannel;

public class FirstTestCase {
	
	private static final String host = "cpliqag4.homedepot.com";
	private static final int port = 22;
	private static final String username = "wmrf";
	private static final String password = "23k9xmkp";
	private static int timeout = 10;
	private static SSHChannel session = null;
	private static HashMap<Integer, String> assertionMap = new HashMap<>();

	
	final static Logger logger = Logger.getLogger(FirstTestCase.class); 


	public static void main(String[] args) {
		
		TerminalAutomation tr = new TerminalAutomation();
		
		//*********************Start Connection***************************************
		ResponseDTO startConnectionOutput = 
				tr.startConnection(host, port, username, password, timeout);
		
		if(startConnectionOutput.getResponseContent().contains("Session opened succesfully")) {
			logger.info("Session opned succesfully");
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
		logger.debug("Open Search Screen");
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
		tr.sendText(session, host, "21656921", timeout, false, false);
		assertionMap.clear();
		assertionMap.put(1, "Tote");
		ResponseDTO searchScreenOutput4 = tr.sendSpecialKeyAndWaitUntil(session, host, "CR", assertionMap, "AND", timeout, true);
		
		tr.sendText(session, host, "T0917201912", timeout, false, false);
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

}
