package com.homer.runner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSample{
	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {
		String DB_URL = "";
		String USER = "";
		String PASS = "";
		String envrnment = "Houston_2019";
		try{  
			
			if(envrnment.equalsIgnoreCase("Houston_2019")){
				DB_URL = "jdbc:oracle:thin:@lncfffb.homedepot.com:1521:dqa86dc";
				USER="wmlmqa01";
				PASS="WMLMQA01";
			}else if(envrnment.equalsIgnoreCase("Dallas_2019")){
				DB_URL = "jdbc:oracle:thin:@lncc05d.homedepot.com:1521:dqa84dc";
				USER="wmlmqa01";
				PASS="WMLMQA01";
			}else if(envrnment.equalsIgnoreCase("NorthLake")){
				DB_URL = "jdbc:oracle:thin:@ln4745.homedepot.com:1521:dad81dc"; 
				USER="WMLMSB01";
				PASS="WMLMSB01";
			}
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(DB_URL,USER,PASS);  
			Statement stmt=con.createStatement();  
			/*ResultSet rs=stmt.executeQuery("select ic.item_name, iw.convey_flag, lh.dsp_locn from item_cbo ic, "
                    + "item_wms iw, wm_inventory wi, locn_hdr lh where ic.item_id = iw.item_id and "
                    + "ic.item_id = wi.item_id and wi.location_id = lh.locn_id "
                    + "and iw.convey_flag = 'N' and ic.item_name = '1000707527' "
                    + "and ic.un_number_id is NULL and wi.locn_class = 'A' order by lh.dsp_locn");  */
			ResultSet rs = stmt.executeQuery("SELECT DOCK_DOOR_NAME,BARCODE FROM DOCK_DOOR WHERE DOCK_DOOR_STATUS =204 AND DOCK_DOOR_NAME NOT IN (SELECT CONCAT('DD',SUBSTR(CODE_DESC,14,3)) FROM SYS_CODE WHERE CODE_TYPE = '22B') AND BARCODE IS NOT NULL AND DOCK_DOOR_NAME NOT IN ('DD116','DD159') AND ROWNUM <= 1");
			//ResultSet rs = stmt.executeQuery("select DOCK_DOOR_NAME,BARCODE from dock_door where DOCK_DOOR_NAME ='DD831'");
			while(rs.next())  
			//System.out.println(rs.getString("item_name")+ "  "+rs.getString("dsp_locn"));  
			System.out.println(rs.getString("DOCK_DOOR_NAME")+ "  "+rs.getString("BARCODE")); 
			con.close();  
			}catch(Exception e){ 
				System.out.println(e);
			}  
	}
}

