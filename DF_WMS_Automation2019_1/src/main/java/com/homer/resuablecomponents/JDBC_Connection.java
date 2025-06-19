package com.homer.resuablecomponents;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;
import com.homer.po.DFWMS.DFWMSLoginPageObject;
import com.mysql.jdbc.CallableStatement;

public class JDBC_Connection extends PageBase {
	
		public JDBC_Connection(InstanceContainer ic) {
		super(ic);
	}
		public  ResultSetMetaData metadata;
		//public  String JDBC_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
		//public String JDBC_DRIVER= "COM.ibm.db2.jdbc.net.DB2Driver";
		public  String JDBC_DRIVER_SQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		public  String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		//public String JDBC_DRIVER="com.ibm.db2.jcc.DB2Driver";
		public static String DB_URL = "";
		public static String USER = "";
		public static String PASS = "";
		public String DB_URL_SQL = "";
		public String USER_SQL = "";
		public String PASS_SQL = "";
	
		public static String envrnment = null;
	/*	public JDBC_Connection(){	
			ResultSetMetaData metadata;		
		}*/
		public String dbDFWMSMapping() throws Exception
		{
			DFWMSLoginPageObject s = new DFWMSLoginPageObject(ic);
			envrnment = s.readProp();			
								
				if(envrnment.equalsIgnoreCase("Perris_New")){
					DB_URL = "jdbc:oracle:thin:@ln4740.homedepot.com:1521:dad82dc";
					USER="DCDTFL04";
					PASS="dcdtfl044u";
				}else if (envrnment.equalsIgnoreCase("Perris_OLD")){
					DB_URL = "jdbc:oracle:thin:@cpliad1o.homedepot.com:1521:dad82dc";
					USER="DCDTFL04";
					PASS="dcdtfl044u";
				}else if (envrnment.equalsIgnoreCase("Troy")){
					DB_URL = "jdbc:oracle:thin:@ln0a13.homedepot.com:1521:dqa83dc";
					USER="dc999904";
					PASS="dc9999044u";
				}else if (envrnment.equalsIgnoreCase("LG")){
					DB_URL = "jdbc:oracle:thin:@cpliqag5.homedepot.com:1521:dqa81dc";
					USER="DCDTFL04";
					PASS="dcdtfl044u";
				}else if (envrnment.equalsIgnoreCase("LG_2012")){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa81dc_rw_9999_01";
					USER="wmlmqa01";
					PASS="WMLMNEWLGQA100";
				}else if (envrnment.equalsIgnoreCase("LG_2019")){
					//LG Q3
//					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa93dc_rw_9999_01";
//					USER="wmlmqa01";
//					PASS="WMLMQA01";
					//Normal LG
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa85dc_rw_9999_01";
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("LG_Q3_2019")|| envrnment.equalsIgnoreCase("Tools")
						){
					//DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
					//DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521:dqa84dc_rw_9999_01";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dad84dc_rw_9999_01"; 
					USER="WMLMAD01";
					PASS="NEWWMLMAD02";
				}else if(envrnment.equalsIgnoreCase("LG_Main_2019")
						){
					//DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
					//DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521:dqa84dc_rw_9999_01";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa92dc_rw_9999_01"; 
					USER="WMLMQA01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("HGT_2019")
						){
					//DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
					//DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521:dqa84dc_rw_9999_01";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa95dc_rw_9999_01"; 
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("New_Boston_2019")){
					//DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
					//DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521:dqa84dc_rw_9999_01";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa96dc_rw_9999_01"; 
					USER="WMLMQA01";
					PASS="WMLMQA01";
				}else if (envrnment.equalsIgnoreCase("Bulk")){
					DB_URL = "jdbc:oracle:thin:@ln0ca3.homedepot.com:1521:dqa82dc";
					USER="dc999934";
					PASS="ST5cHEz6kusw";
				}else if(envrnment.equalsIgnoreCase("LG_New")){
					DB_URL = "jdbc:oracle:thin:@ln4734.homedepot.com:1521:dqa81dc";
					USER="DCDTFL04";
					PASS="dcdtfl044u";
				}else if(envrnment.equalsIgnoreCase("Houston_2019")
						){
					System.out.println("DB_URL is:"+envrnment );
//					DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";   --Q1
					//DB_URL = "jdbc:oracle:thin:@lncfffb.homedepot.com:1521:dqa86dc";
					DB_URL = "jdbc:oracle:thin:@//snpagor84-scan.homedepot.com:1521/dqa86dc_rw_9999_01";
//					USER="DCDTFL04";
//					PASS="dcdtfl044u";
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("Dallas_2019")
						){
					//DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
					//DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521:dqa84dc_rw_9999_01";
					DB_URL = "jdbc:oracle:thin:@//snpagor84-scan.homedepot.com:1521/dqa84dc_rw_9999_01"; 
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}
				else if(envrnment.equalsIgnoreCase("Atlanta_2019")){
					DB_URL = "jdbc:oracle:thin:@//dpr47dc_5860_primary.pr.oradb.homedepot.com:1521/dpr47dc_rw_5860_02"; 
					USER="WMLM5860";
					PASS="WMLM5860";
				}else if(envrnment.equalsIgnoreCase("NorthLake")){
					DB_URL = "jdbc:oracle:thin:@ln4745.homedepot.com:1521:dad81dc";
					USER="WMLMSB01";
					PASS="WMLMSB01";
				}else if(envrnment.equalsIgnoreCase("Baltimore_2019") || envrnment.equalsIgnoreCase("Newark_2019")){
					//DB_URL = "jdbc:oracle:thin:@lncdc02.homedepot.com:1521/dqa85dc";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa85dc_rw_5829_01";
					USER="WMLMQA01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("Lacey_2019")){
					//DB_URL = "jdbc:oracle:thin:@lncfffb.homedepot.com:1521/dqa87dc";
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa87dc_rw_9999_01";
					USER="WMLMQA01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("Tampa_2019") 
						|| envrnment.equalsIgnoreCase("Tracey_2019")
						){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dad84dc_rw_9999_01";
					USER="WMLMAD01";
					PASS="WMLMAD01";
				}else if(envrnment.equalsIgnoreCase("Houston_Packsize")){
					//DB_URL = "jdbc:oracle:thin:@lncfffb.homedepot.com:1521/dqa87dc";
					DB_URL_SQL = "jdbc:sqlserver://wnca9e1:1433;database=THDMDC_PPD";
					USER_SQL="DFAUTO1";
					PASS_SQL="DFAUTO1";
				}else if(envrnment.equalsIgnoreCase("Miami_2019") 
						){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa85dc_rw_9999_01";
					USER="WMLMQA01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("Perris_2019") 
						){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dqa94dc_rw_9999_01";
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}else if(envrnment.equalsIgnoreCase("Boston_2019")){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dad84dc_rw_9999_01";
					USER="WMLMAD01";
					PASS="WMLMAD01";
				}else if(envrnment.equalsIgnoreCase("HoustonQP_2019")){
					DB_URL = "jdbc:oracle:thin:@snpagor84-scan.homedepot.com:1521/dad85dc_rw_9999_01";
					USER="wmlmad01";
					PASS="WMLMAD01";
				}else if(envrnment.equalsIgnoreCase("Columbus_2019")){
					System.out.println("DB_URL is:"+envrnment );
//					DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";   --Q1
					//DB_URL = "jdbc:oracle:thin:@lncfffb.homedepot.com:1521:dqa86dc";
					DB_URL = "jdbc:oracle:thin:@//snpagor84-scan.homedepot.com:1521/dqa88dc_rw_9999_01";
//					USER="DCDTFL04";
//					PASS="dcdtfl044u";
					USER="wmlmqa01";
					PASS="WMLMQA01";
				}
			System.out.println("DB_URL is:"+DB_URL );
			System.out.println("DB_URL is:"+envrnment );
			return DB_URL;
		}
		public String dbDORMapping() throws Exception
		{
			DB_URL = "jdbc:oracle:thin:@//snpagor61-scan.homedepot.com:1521/dqa19ss_rw_dfor_10"; 
			USER="DFORRW10";
			PASS="Df0rQa32Qp";
			//System.out.println("DB_URL is:"+DB_URL);
			return DB_URL;
		}
		public String dbLegacyDORMapping() throws Exception
		{
			DB_URL = "jdbc:oracle:thin:@cpliqag5.homedepot.com:1521:dqa01di"; 
			USER="didfor01";
			PASS="didfor014u";
			//System.out.println("DB_URL is:"+DB_URL);
			return DB_URL;
		}
		public ArrayList<String> array_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
			
			ArrayList<String> Db_datas = new ArrayList<String>();
		
			try
			{
//			System.out.println("Connecting to a selected database 1...");	
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			Statement stmt = null;
			// STEP 3: Open a connection
//			System.out.println("Connecting to a selected database 2...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			System.out.println("Connected database successfully...");
			// STEP 4: Execute a query
//			System.out.println("Creating statement...");
			stmt = conn.createStatement();

	        


//			System.out.println("Fetching records with condition...");
			String sql = query;		
			ResultSet rs = stmt.executeQuery(sql);
	        
			metadata = rs.getMetaData();
			  while(rs.next())
			{
				for(int i=1;i<=metadata.getColumnCount();i++)
				{
			//		System.out.println(i);
					if(rs.getString(i)!=null)
				 	Db_datas.add(rs.getString(i).trim());
					 }
//			System.out.println(Db_datas);	
				}
			rs.close();
			stmt.close();
			conn.close();
			
			return Db_datas;
			}
			catch(Exception e)
			{
				Db_datas.add(null);
//				System.out.println("Exception on executing the query");
				e.printStackTrace();
				return null;
			}
		 
	}


	public void array_Database_Connection_StoreProcedure(String query) throws InterruptedException,  IOException, ClassNotFoundException {
			
		
			try
			{
//			System.out.println("Connecting to a selected database 1...");	
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			Statement stmt = null;
			// STEP 3: Open a connection
//			System.out.println("Connecting to a selected database 2...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			System.out.println("Connected database successfully...");
			// STEP 4: Execute a query
//			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		      //Creating the Statement
		      //Query to create stored procedures
		      //Executing the query



//			System.out.println("Fetching records with condition...");
			int rs = stmt.executeUpdate(query);
		      System.out.println("Procedure Created......" + rs);

	        
			stmt.close();
			conn.close();
			
			}
			catch(Exception e)
			{
//				System.out.println("Exception on executing the query");
				e.printStackTrace();
			}
		 
	}

//Last updated value in database
		
public ArrayList<String> lpnValue(String query) {
	ArrayList<String> Db_datas = new ArrayList<String>();
	DB_URL = "jdbc:oracle:thin:@//snpagor84-scan.homedepot.com:1521/dqa86dc_rw_9999_01";
//	USER="DCDTFL04";
//	PASS="dcdtfl044u";
	USER="wmlmqa01";
	PASS="WMLMQA01";
	try
	{
//	System.out.println("Connecting to a selected database 1...");	
	Class.forName(JDBC_DRIVER);
	Connection conn = null;
	Statement stmt = null;
	// STEP 3: Open a connection
//	System.out.println("Connecting to a selected database 2...");
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
//	System.out.println("Connected database successfully...");
	// STEP 4: Execute a query
//	System.out.println("Creating statement...");
	stmt = conn.createStatement();

//	System.out.println("Fetching records with condition...");
	String sql = query;		
	ResultSet rs = stmt.executeQuery(sql);
	metadata = rs.getMetaData();
	  while(rs.next())
	{
		for(int i=1;i<=metadata.getColumnCount();i++)
		{
	//		System.out.println(i);
			if(rs.getString(i)!=null)
		 	Db_datas.add(rs.getString(i).trim());
			 }
//	System.out.println(Db_datas);	
		}
	rs.close();
	stmt.close();
	conn.close();
	
	return Db_datas;
	}
	catch(Exception e)
	{
		Db_datas.add(null);
//		System.out.println("Exception on executing the query");
		e.printStackTrace();
		return null;
	}
}
		
	public String str_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
			
		   String Db_datas=null;
			try
			{
				
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			Statement stmt = null;
			// STEP 3: Open a connection
//			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			System.out.println("Connected database successfully...");
			// STEP 4: Execute a query
//			System.out.println("Creating statement...");
			stmt = conn.createStatement();
//			System.out.println("Fetching records with condition...");
			String sql = query;	
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			metadata = rs.getMetaData();
			while(rs.next()){
			Db_datas=rs.getString(1);			
			}
			//System.out.println(Db_datas);					
			rs.close();
			stmt.close();
			conn.close();			
			return Db_datas;
			}
			catch(Exception e)
			{
			//	Db_datas.add(null);
				System.out.println("Exception on executing the query");
				e.printStackTrace();
				return null;
			}
		 
	}

	public void str_Store_Proc_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
		
		try
		{
			
		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		Statement stmt = null;
		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		System.out.println("Fetching records with condition...");
		String sql = query;		
		stmt.executeUpdate(sql);		
		}
		catch(Exception e)
		{
		//	Db_datas.add(null);
			System.out.println("Exception on executing the query");
			e.printStackTrace();
		}
		 
	}

public String str_Database_Connection_sql(String query) throws InterruptedException,  IOException, ClassNotFoundException {
	String Db_datas=null;
	try{
		Class.forName(JDBC_DRIVER_SQL);
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL_SQL, USER_SQL, PASS_SQL);
		stmt = conn.createStatement();
		String sql = query;	
		ResultSet rs = stmt.executeQuery(sql);
		metadata = rs.getMetaData();
		while(rs.next()){
			Db_datas=rs.getString(1);			
		}
		rs.close();
		stmt.close();
		conn.close();			
		return Db_datas;
	}catch(Exception e){
		System.out.println("Exception on executing the query");
		e.printStackTrace();
		return null;
	}
}
public String str_Update_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
	
	   String Db_datas=null;
		try
		{
			
		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		Statement stmt = null;
		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		System.out.println("Fetching records with condition...");
		String sql = query;		
		stmt.executeUpdate(sql);		
		return Db_datas;
		}
		catch(Exception e)
		{
		//	Db_datas.add(null);
			System.out.println("Exception on executing the query");
			e.printStackTrace();
			return null;
		}
	 
}

}

