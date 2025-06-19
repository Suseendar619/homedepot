package com.homer.po.DFWMS;

import java.io.IOException;

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
import com.ibm.mq.jms.JMSC;

public class MQPageObject extends PageBase {

	public MQPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("deprecation")	
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
	    //String str=transformXmltostring(dataTable.getData(DataColumn.Poxml));
	    /**
	     * Initialize the MQ
	     *
	     */
	    static String envrnment = null;
	    
	    public String MQMapping() throws Exception
		{
	    	TPELoginPageObject s = new TPELoginPageObject(ic);
			envrnment = s.readProp();
			
								
				if(envrnment.equalsIgnoreCase("TPE_QA1")){
					qMngrStr="QM1.QA.US.LN09D5";
					hostName="LN09D5.homedepot.com";
					port = 17801;
				}
				else if (envrnment.equalsIgnoreCase("TPE_QA2"))
				{
					qMngrStr="QM2.QA.US.LN09D5";
					hostName="LN09D5.homedepot.com";
					port = 17802;
				}
				else if (envrnment.equalsIgnoreCase("TPE_QP"))
				{
					qMngrStr="QM0.QP.US.LN0A1011";
					hostName="wmqtmsqp1.homedepot.com";
					port = 17800;
				}
				else if (envrnment.equalsIgnoreCase("TPE_QA"))
				{
					qMngrStr="QM0.QA.US.LN2A8F8E";
					hostName="wmqtmsqa1.homedepot.com";
					port = 17800;
				}
	    	return queueName;
	    	
		}
	    
	    @SuppressWarnings({ "unchecked" })
		public void init(){
	        
	        //Set MQ connection credentials to MQ Envorinment.
	         MQEnvironment.hostname = hostName;
	         MQEnvironment.channel = channel;
	         MQEnvironment.port = port;
	        // MQEnvironment.userID = user;
	        // MQEnvironment.password = password;
	         
	         //set transport properties.
	         MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES_CLIENT);
	         //connectionFactory.setTransportType(JMSC.MQJMS_TP_C LIENT_MQ_TCPIP);
	         
	         try {
	             //initialize MQ manager.
	        	 System.out.println("QManager : "+qMngrStr);
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
	    @SuppressWarnings("deprecation")
		public void putAndGetMessage(String str,String QueueName){
	        
	        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 
	        try {
	            defaultLocalQueue = qManager.accessQueue(QueueName, openOptions);
	            
	            MQMessage putMessage = new MQMessage();
	            //putMessage.format = "MQSTR";
	            //System.out.println("String:"+str);
	            putMessage.writeString(str);
	            //specify the message options...
	            MQPutMessageOptions pmo = new MQPutMessageOptions(); 
	            // accept 
	            // put the message on the queue
	            defaultLocalQueue.put(putMessage, pmo);
	            System.out.println("QName:"+QueueName);
	            System.out.println("Message is put on MQ.");
	            
	         /*   //get message from MQ.
	            MQMessage getMessages = new MQMessage();
	            //assign message id to get message.
	            getMessages.messageId = putMessage.messageId;
	            
	            //get message options.
	            MQGetMessageOptions gmo = new MQGetMessageOptions();
	            defaultLocalQueue.get(getMessages, gmo);
	            
	            //queueName.get(getMessages, gmo);
	            
	            String retreivedMsg = getMessages.readUTF();
	            System.out.println("Message got from MQ: "+retreivedMsg);*/
	            
	         
	            
	        } catch (MQException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
}
