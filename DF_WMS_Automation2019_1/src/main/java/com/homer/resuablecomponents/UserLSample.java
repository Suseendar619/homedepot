package com.homer.resuablecomponents;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.homer.dao.DataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;

public class UserLSample extends PageBase {

	public UserLSample(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}
	
	//public static void main(String[] args) throws Exception {
		
	
	public void UserLsample() throws Exception {
		// Open the file
		//FileInputStream fstream = new FileInputStream("C://Users//nxn8648\git\TPENew\02-03-2017\TMS_Automation_2016//TestData//XML//VPT_Users_XML.xml");
		FileInputStream fstream = new FileInputStream(dataTable.getData(DataColumn.PrimaryFieldInstanceTwo));
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println (strLine);
		}

		//Close the input stream
		br.close();
		
	}
	

}
