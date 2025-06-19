package com.homer.runner;


import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

/**
 * Java class to connect to MQ. Post and Retreive messages.
 *
 */
@SuppressWarnings("deprecation")
public class MQSample {

	
	
	String qMngrStr = "QM1.QA.US.LN09D5";
   // String user = "NXN8648";
   // String password = "";
    String queueName = "DI.TP.2014.PO.IB";
    String hostName = "LN09D5.homedepot.com";
    int port = 17801;
    String channel = "TMS.APP.CHANNEL";
    //message to put on MQ.
    String msg = "Hello World, WelCome to MQ.";
    //Create a default local queue.
    MQQueue defaultLocalQueue;
    MQQueueManager qManager;
  
    static String s;
    //String str=transformXmltostring(dataTable.getData(DataColumn.Poxml));
    /**
     * Initialize the MQ
     *
     */
    @SuppressWarnings({ "unchecked" })
	public void init(){
        
        //Set MQ connection credentials to MQ Envorinment.
         MQEnvironment.hostname = hostName;
         MQEnvironment.channel = channel;
         MQEnvironment.port = port;
        // MQEnvironment.userID = user;
        // MQEnvironment.password = password;
         
         //set transport properties.
       //  MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES_CLIENT);
         
         try {
             //initialize MQ manager.
            qManager = new MQQueueManager(qMngrStr);
        } 
         catch (MQException e) {
            e.printStackTrace();
        }
    }
    
  

	/**
     * Method to put message to MQ.
     *
     */
    public void putAndGetMessage(){
        
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 
        try {
            defaultLocalQueue = qManager.accessQueue(queueName, openOptions);
            
            MQMessage putMessage = new MQMessage();
            putMessage.writeUTF(msg);
            
            //specify the message options...
            MQPutMessageOptions pmo = new MQPutMessageOptions(); 
            // accept 
            // put the message on the queue
            defaultLocalQueue.put(putMessage, pmo);
            
            System.out.println("Message is put on MQ.");
            
            //get message from MQ.
            MQMessage getMessages = new MQMessage();
            //assign message id to get message.
            getMessages.messageId = putMessage.messageId;
            System.out.println("ID:" +getMessages.messageId);
            
            //get message options.
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            defaultLocalQueue.get(getMessages, gmo);
            
            //queueName.get(getMessages, gmo);
            
            String retreivedMsg = getMessages.readUTF();
            System.out.println("Message got from MQ: "+retreivedMsg);
            
         
            
        } catch (MQException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Processing Main...");
        
        MQSample clientTest = new MQSample();
        
        //initialize MQ.
        clientTest.init();
        
        s = clientTest.transformXmltostring("\\TestData\\XML\\PO.xml");
       // System.out.println("S:"+s);
        //put and retreive message from MQ.
        clientTest.putAndGetMessage(); 		
        System.out.println("Done!");
    }
    public String transformXmltostring(String xmlpath) throws Exception{			 
		DocumentBuilder parser =DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(System.getProperty("user.dir") + xmlpath);
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		Source source = new DOMSource( document );

		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);
		transformer.transform( source, result );  
		StringBuffer sb = outWriter.getBuffer(); 
		String finalstring = sb.toString();
		finalstring = finalstring.replace("\t", "");
		return finalstring;
	}   
}
